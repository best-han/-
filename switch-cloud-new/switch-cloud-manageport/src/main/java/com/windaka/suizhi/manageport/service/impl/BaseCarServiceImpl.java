package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.BaseCarService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BaseCarServiceImpl  implements BaseCarService {
    @Autowired
    private BaseCarMapper baseCarMapper;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private CarGroupDetailMapper carGroupDetailMapper;
    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;

    @Override
    public void insertCar(String communityCode, List cars) throws OssRenderException, IOException {
        if (cars != null) {
            Iterator i = cars.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();



                //判断必要数据不能为空
                if (communityCode == null || StringUtils.isEmpty(communityCode)) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区编码不能为空");
                }
                if (t.get("num") == null || StringUtils.isEmpty(t.get("num").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "车牌号不能为空");
                }
                if (t.get("personCode") == null || StringUtils.isEmpty(t.get("personCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "车主代码不能为空");
                }
                t.put("communityCode", communityCode);

                Map params;
                //防止插入重复数据
                params=new HashMap();
                params.put("num",t.get("num"));
                List<BaseCar> baseCarList=baseCarMapper.selectCarList(params);
                if (baseCarList.size()>0){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "车牌号已存在");
                }

                //查询绑定小区 是否存在
                params = new HashMap<>();
                params.put("code", t.get("communityCode"));
                List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
                if (baseCommunityList.size() > 0) {
                    String communityName = baseCommunityList.get(0).getName();
                    t.put("communityName", communityName);//若存在  存小区名称
                } else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, communityCode + "小区不存在");
                }

                //处理数据
//                if (t.get("numColor") != null && !StringUtils.isEmpty(t.get("numColor").toString())) {
//                    t.put("numColorName", DicUtils.getValue("base_car|num_color", t.get("numColor").toString(), "base_car|num_color无此代码颜色"));
//                }
//                if (t.get("color") != null && !StringUtils.isEmpty(t.get("color").toString())) {
//                    t.put("colorName", DicUtils.getValue("base_car|color", t.get("color").toString(), "base_car|color无此代码颜色"));
//                }
//                if (t.get("type") != null && !StringUtils.isEmpty(t.get("type").toString())) {
//                    t.put("typeName", DicUtils.getValue("base_car|type", t.get("type").toString(), "base_car|type无此代码车型"));
//                }
//                if (t.get("brand") != null && !StringUtils.isEmpty(t.get("brand").toString())) {
//                    t.put("brandName", DicUtils.getValue("base_car|brand", t.get("brand").toString(), "base_car|brand无此代码品牌"));
//                }

                params = new HashMap<>();
                params.put("code", t.get("personCode"));
                List<BasePerson> persons = basePersonMapper.selectPersonList(params);
                if (persons.size() <= 0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, t.get("personCode") + "该人员不存在");
                }

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("base_car");
                if(t.get("image")!=null && !StringUtils.isEmpty(t.get("image").toString())){

//                    String carImg=t.get("image").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(carImg.split(",")[1]);//base64 码 去前缀
//                    byte[] byteArr= PicUtil.stringToInputStream(carImg);
//
//                    //rename pic
//                    String fileName = PicUtil.getPicName("base_car", nextId);
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

                BaseCar baseCar = JSON.parseObject(JSON.toJSONString(t), BaseCar.class);//Map 转 实体
                baseCarMapper.insertCar(baseCar);
                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("base_car",null,nextId+"","insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteByNum(String num) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("num", num);
        List<BaseCar> baseCars = baseCarMapper.selectCarList(params);
        if (baseCars.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, num + "车辆不存在");
        }
        BaseCar baseCar=baseCars.get(0);
        baseCarMapper.deleteByNum(num);
        //级联删除-布控明细 setnull
        Map innerParam=new HashMap();
        innerParam.put("carNum",num);
        List<CarGroupDetail> carGroupDetailList=carGroupDetailMapper.selectCarGroupDetailList(innerParam);
        Iterator<CarGroupDetail> cgdi=carGroupDetailList.iterator();
        while (cgdi.hasNext()){
            CarGroupDetail cgdt=cgdi.next();
            cgdt.setCarNum(cgdt.getCarNum()+"(已删除)");
            carGroupDetailMapper.updateByPrimaryKey(cgdt);
        }
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("base_car",null,baseCar.getId().toString(),"delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);

    }

    @Override
    public void updateByNum(BaseCar baseCar) throws OssRenderException, IOException {
        //判断必要数据不能为空
        if (baseCar.getCommunityCode() == null || StringUtils.isEmpty(baseCar.getCommunityCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区编码不能为空");
        }
        if (baseCar.getNum() == null || StringUtils.isEmpty(baseCar.getNum())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "车牌号不能为空");
        }
        if (baseCar.getPersonCode() == null || StringUtils.isEmpty(baseCar.getPersonCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "车主代码不能为空");
        }
        baseCar.setCommunityCode(baseCar.getCommunityCode());

        //添加小区名称
        Map<String, Object> params = new HashMap<>();
        params.put("code",baseCar.getCommunityName());
        List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
        if (baseCommunityList.size() > 0) {
            String communityName = baseCommunityList.get(0).getName();
            baseCar.setCommunityName(communityName);//若存在  存小区名称
        } else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, baseCar.getCommunityCode() + "小区不存在");
        }

        //处理数据
//        if (baseCar.getNumColor() != null && !StringUtils.isEmpty(baseCar.getNumColor().toString())) {
//            baseCar.setNumColorName(DicUtils.getValue("base_car|num_color", baseCar.getNumColor().toString(), "base_car|num_color无此代码颜色"));
//        }
//        if (baseCar.getColor() != null && !StringUtils.isEmpty(baseCar.getColor().toString())) {
//            baseCar.setColorName(DicUtils.getValue("base_car|color", baseCar.getColor().toString(), "base_car|color无此代码颜色"));
//        }
//        if (baseCar.getType()!= null && !StringUtils.isEmpty(baseCar.getType().toString())) {
//            baseCar.setTypeName(DicUtils.getValue("base_car|type", baseCar.getType().toString(), "base_car|type无此代码车型"));
//        }
//        if (baseCar.getBrand()!= null && !StringUtils.isEmpty(baseCar.getBrand().toString())) {
//            baseCar.setBrandName(DicUtils.getValue("base_car|brand", baseCar.getBrand().toString(), "base_car|brand无此代码品牌"));
//        }
        Map<String, Object> params1 = new HashMap<>();
        params1.put("code", baseCar.getPersonCode());
        List<BasePerson> persons = basePersonMapper.selectPersonList(params1);
        if (persons.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, baseCar.getPersonCode() + "该人员不存在");
        }

        params=new HashMap<>();
        params.put("num",baseCar.getNum());
        List<BaseCar> baseCarListM=baseCarMapper.selectCarList(params);
        Integer id=null;
        if(baseCarListM.size()>0){
            BaseCar baseCarM=baseCarListM.get(0);
            id=baseCarM.getId();
        }
        else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, baseCar.getNum() + "该车辆不存在");
        }

        //图片上传
        if(baseCar.getImage()!=null && !StringUtils.isEmpty(baseCar.getImage())){

//            String carImg=baseCar.getImage();
////            byte[] byteArr= PicUtil.stringToInputStream(carImg.split(",")[1]);
//            byte[] byteArr= PicUtil.stringToInputStream(carImg);
//            // 根据最大主键
//            //rename pic
//            String fileName = PicUtil.getPicName("base_car",id);
//
//            //封装访问路径：年/月/日
//            Date date=new Date();
//            //图片放入打包路径
//            FileUploadUtil.inputStreamToLocalFile(byteArr,
//                    CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//            baseCar.setImage(PicUtil.getPicRelativePath(date)+ fileName);


            //保存图片到fastDFS
            Map<String,Object> imageMap=imageToFdfsService.saveImageToFdfs(baseCar.getImage());
            baseCar.setImage(imageMap.get("thumbFullPath").toString());
        }

        baseCarMapper.updateByNum(baseCar);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("base_car",baseCar.getNum(),null,"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectCarList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<BaseCar> list = baseCarMapper.selectCarList(params);
        map.put("list", list);
        return map;
    }
}
