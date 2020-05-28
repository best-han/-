package com.windaka.suizhi.manageport.service.impl;


import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.BaseCommunityMapper;
import com.windaka.suizhi.manageport.dao.BasePersonMapper;
import com.windaka.suizhi.manageport.dao.BasePetMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.model.BaseCommunity;
import com.windaka.suizhi.manageport.model.BasePerson;
import com.windaka.suizhi.manageport.model.BasePet;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.BasePetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BasePetServiceImpl implements BasePetService {
    @Autowired
    private BasePetMapper basePetMapper;
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;
    @Override
    public void insertPets(String communityCode,List pets) throws OssRenderException, IOException {
        if (pets != null) {
            Iterator i = pets.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();

                //判断必要数据是否为空
                if (communityCode == null || StringUtils.isEmpty(communityCode)) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区编码不能为空");
                }
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠物编码不能为空");
                }
//                if (t.get("breed") == null || StringUtils.isEmpty(t.get("breed").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠物品种不能为空");
//                }
                if (t.get("personCode") == null || StringUtils.isEmpty(t.get("personCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠主不能为空");
                }
                if (t.get("count") == null || StringUtils.isEmpty(t.get("count").toString())){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠物数量不能为空");
                }
                Map params;
                //查询绑定关联数据

                //查询绑定小区 是否存在
                t.put("communityCode",communityCode);
                params = new HashMap<>();
                params.put("code", t.get("communityCode"));
                List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
                if (baseCommunityList.size() > 0) {
                    String communityName = baseCommunityList.get(0).getName();
                    t.put("communityName", communityName);//若存在  存小区名称
                } else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, communityCode + "小区不存在");
                }

                //查询宠物code是否已经存在
                params = new HashMap<>();
                params.put("code", t.get("code"));
                List<BasePet> basePetList = basePetMapper.selectPetsList(params);
                if(basePetList.size()>0){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, communityCode + "宠物code重复");
                }

//                //添加breedName
//                t.put("breedName", DicUtils.getValue("base_pet|breed", t.get("breed").toString(), "base_pet|breed,字典无此代码"));
//
//                //other
//                if (t.get("sex") != null && !StringUtils.isEmpty(t.get("sex").toString())) {
//                    t.put("sex", DicUtils.getValue("base_pet|sex", t.get("sex").toString(), "base_pet|sex,字典无此代码"));
//                }
//                if (t.get("color") != null && !StringUtils.isEmpty(t.get("color").toString())) {
//                    t.put("colorName", DicUtils.getValue("base_pet|color", t.get("color").toString(), "base_pet|color,字典无此代码"));
//                }

                params = new HashMap<>();
                params.put("code",t.get("personCode"));
                List<BasePerson> persons = basePersonMapper.selectPersonList(params);
                if (persons.size() <= 0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, t.get("personCode") + "该人员不存在");
                }

                //图片上传
                if(t.get("image")!=null && !StringUtils.isEmpty(t.get("image").toString())){

//                    String petImg=t.get("image").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(petImg.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(petImg);
//                    // 根据最大主键
//                    Integer nextId = cloudPlatformMapper.getNextId("base_pet");
//                    //rename pic
//                    String fileName = PicUtil.getPicName("base_pet", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                    //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("image", PicUtil.getPicRelativePath(date)+ fileName);

                    //保存图片到fastDFS
                    Map<String,Object> imageMap=imageToFdfsService.saveImageToFdfs(t.get("image").toString());
                    t.put("image",imageMap.get("thumbFullPath"));
                }

                BasePet basePet = JSON.parseObject(JSON.toJSONString(t), BasePet.class);//Map 转 实体
                basePetMapper.insertPets(basePet);


                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("base_pet",basePet.getCode(),null,"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }

    }

    @Override
    public void deleteByCode(String code) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        List<BasePet> basePetList = basePetMapper.selectPetsList(params);
        if (basePetList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, code + "宠物不存在");
        }
        basePetMapper.deleteByCode(code);

        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("base_pet",code,null,"delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateByCode(BasePet basePet) throws OssRenderException, IOException {
        //判断必要数据是否为空
        if (basePet.getCode() == null || StringUtils.isEmpty(basePet.getCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠物编码不能为空");
        }
//        if (basePet.getBreed() == null || StringUtils.isEmpty(basePet.getBreed().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠物品种不能为空");
//        }
        if (basePet.getPersonCode() == null || StringUtils.isEmpty(basePet.getPersonCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠主不能为空");
        }
        if (basePet.getCommunityCode()== null || StringUtils.isEmpty(basePet.getCommunityCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区代码不能为空");
        }
        if (basePet.getCount() == null || StringUtils.isEmpty(basePet.getCount().toString())){
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "宠物数量不能为空");
        }

        Map params;
        //查询绑定小区 是否存在
        params = new HashMap<>();
        params.put("code", basePet.getCommunityCode());
        List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
        if (baseCommunityList.size() > 0) {
            String communityName = baseCommunityList.get(0).getName();
            basePet.setCommunityName(communityName);//若存在  存小区名称
        } else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, basePet.getCommunityCode()+ "小区不存在");
        }

//        //处理数据
//        //添加breedName
//        basePet.setBreedName(DicUtils.getValue("base_pet|breed", basePet.getBreed().toString(), "base_pet|breed字典无此代码"));
//        //other
//        if (basePet.getSex() != null && !StringUtils.isEmpty(basePet.getSex().toString())) {
//            basePet.setSex(DicUtils.getValue("base_pet|sex",basePet.getSex(), "base_pet|sex字典无此代码"));
//        }
//        if (basePet.getColor() != null && !StringUtils.isEmpty(basePet.getColor().toString())) {
//            basePet.setColorName(DicUtils.getValue("base_pet|color", basePet.getColor().toString(), "base_pet|color字典无此代码"));
//        }
        params = new HashMap<>();
        params.put("code",basePet.getPersonCode());
        List<BasePerson> persons = basePersonMapper.selectPersonList(params);
        if (persons.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, basePet.getPersonCode() + "该人员不存在");
        }

        params = new HashMap<>();
        params.put("code",basePet.getCode());
        List<BasePet> basePetListM = basePetMapper.selectPetsList(params);
        Integer id=null;
        if(basePetListM.size() > 0){
            BasePet basePetM=basePetListM.get(0);
            id=basePetM.getId();
        }
        else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, basePet.getCode() + "该宠物不存在");
        }

        //图片上传
        if(basePet.getImage()!=null && !StringUtils.isEmpty(basePet.getImage())){

//            String petImg=basePet.getImage();
////            byte[] byteArr= PicUtil.stringToInputStream(petImg.split(",")[1]);
//            byte[] byteArr= PicUtil.stringToInputStream(petImg);
//            // 根据最大主键
//            //Integer id=basePet.getId();
//            //rename pic
//            String fileName = PicUtil.getPicName("base_pet", id);
//
//            //封装访问路径：年/月/日
//            Date date=new Date();
//            //图片放入打包路径
//            FileUploadUtil.inputStreamToLocalFile(byteArr,
//                    CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);

            Map<String,Object> imageMap=imageToFdfsService.saveImageToFdfs(basePet.getImage());
            basePet.setImage(imageMap.get("thumbFullPath").toString());
        }
        basePetMapper.updateByCode(basePet);

        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("base_pet",basePet.getCode(),null,"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectPetsList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<BasePet> list = basePetMapper.selectPetsList(params);
        map.put("list", list);
        return map;
    }
}
