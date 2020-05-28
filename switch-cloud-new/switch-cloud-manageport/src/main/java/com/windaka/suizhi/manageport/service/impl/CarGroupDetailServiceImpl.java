package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.constants.CommonConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.FileUploadUtil;
import com.windaka.suizhi.common.utils.PicUtil;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.CarGroupDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class CarGroupDetailServiceImpl implements CarGroupDetailService {

    @Autowired
    private CarGroupDetailMapper carGroupDetailMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Autowired
    private CarGroupMapper carGroupMapper;

    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;

    @Autowired
    private BaseCarMapper baseCarMapper;

    @Override
    public Map<String, Object> selectCarGroupDetailList(Map<String, Object> params) throws OssRenderException {
        Map map=new HashMap();
        List<CarGroupDetail> carGroupDetailList=carGroupDetailMapper.selectCarGroupDetailList(params);
        map.put("list",carGroupDetailList);
        return map;
    }

    @Override
    public void insertCarGroupDetail(List carGroupDetails) throws OssRenderException {
        if(carGroupDetails!=null) {
            Iterator i = carGroupDetails.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                if (t.get("groupCode") == null || StringUtils.isEmpty(t.get("groupCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "groupCode不能为空");
                }
                if (t.get("carNum") == null || StringUtils.isEmpty(t.get("carNum").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "carNum不能为空");
                }
                if (t.get("level") == null || StringUtils.isEmpty(t.get("level").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "level不能为空");
                }
                Map<String,Object> innerParams;
                //查询小区是否存在
                if (t.get("communityCode") != null && !StringUtils.isEmpty(t.get("communityCode").toString())) {
                    innerParams=new HashMap<>();
                    innerParams.put("code",t.get("communityCode"));
                    List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(innerParams);
                    if(baseCommunityList==null||baseCommunityList.isEmpty()) {
                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "该小区不存在");
                    }
                    else{
                        BaseCommunity baseCommunity=baseCommunityList.get(0);
                        t.put("communityName",baseCommunity.getName());
                    }
                }
                //查询布控库是否存在
                innerParams=new HashMap<>();
                innerParams.put("code",t.get("groupCode"));
                List<CarGroup> carGroupList=carGroupMapper.selectCarGroupList(innerParams);
                if(carGroupList==null||carGroupList.isEmpty()){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库不存在");
                }
                else {
                    CarGroup carGroup=carGroupList.get(0);
                    t.put("groupName",carGroup.getName());
                    t.put("groupLevel",carGroup.getLevel());
                }
                //字典查询
                //布控明细等级 level
//                t.put("levelName", DicUtils.getValue("car_group_detail|level",t.get("level").toString(),"布控明细等级值非法"));
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                t.put("createUser",loginAppUser.getUserId());
                t.put("updateUser",loginAppUser.getUserId());

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("car_group_detail");
                if(t.get("carImage")!=null && !StringUtils.isEmpty(t.get("carImage").toString())){

                    String carImage=t.get("carImage").toString();
//                    byte[] byteArr= PicUtil.stringToInputStream(carImage.split(",")[1]);
                    byte[] byteArr= PicUtil.stringToInputStream(carImage);
                    //rename pic
                    String fileName = PicUtil.getPicName("car_group_detail", nextId);

                    //封装访问路径：年/月/日
                    Date date=new Date();
                    //图片放入打包路径
                    FileUploadUtil.inputStreamToLocalFile(byteArr,
                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+ PicUtil.getPicRelativePath(date),fileName);

                    t.put("carImage", PicUtil.getPicRelativePath(date)+ fileName);
                }


                CarGroupDetail carGroupDetail= JSON.parseObject(JSON.toJSONString(t), CarGroupDetail.class);
                carGroupDetailMapper.insert(carGroupDetail);
                Map innerParam=new HashMap();
                innerParam.put("num",t.get("carNum"));
                List<BaseCar> baseCarList=baseCarMapper.selectCarList(innerParam);
                if(baseCarList.size()>0){
                    BaseCar baseCar=baseCarList.get(0);
                    baseCar.setCarGroupDetailId(nextId+"");
                    baseCarMapper.updateByNum(baseCar);
                }

                //记录操作
                //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("car_group_detail",null,nextId+"","insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void updateCarGroupDetailById(CarGroupDetail carGroupDetail) throws OssRenderException {
        if (carGroupDetail.getId() == null || StringUtils.isEmpty(carGroupDetail.getId().toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "id不能为空");
        }
        if (carGroupDetail.getGroupCode() == null || StringUtils.isEmpty(carGroupDetail.getGroupCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "groupCode不能为空");
        }
        if (carGroupDetail.getCarNum() == null || StringUtils.isEmpty(carGroupDetail.getCarNum())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "carNum不能为空");
        }
        if (carGroupDetail.getLevel() == null || StringUtils.isEmpty(carGroupDetail.getLevel().toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "level不能为空");
        }
        Map<String,Object> innerParams;
        //查询小区是否存在
        if (carGroupDetail.getCommunityCode() != null && !StringUtils.isEmpty(carGroupDetail.getCommunityCode())) {
            innerParams=new HashMap<>();
            innerParams.put("code",carGroupDetail.getCommunityCode());
            List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(innerParams);
            if(baseCommunityList==null||baseCommunityList.isEmpty()) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "该小区不存在");
            }
        }
        //查询布控库是否存在
        innerParams=new HashMap<>();
        innerParams.put("code",carGroupDetail.getGroupCode());
        List<CarGroup> carGroupList=carGroupMapper.selectCarGroupList(innerParams);
        if(carGroupList==null||carGroupList.isEmpty()){
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库不存在");
        }
        else {
            CarGroup carGroup=carGroupList.get(0);
            carGroupDetail.setGroupName(carGroup.getName());
            carGroupDetail.setGroupLevel(carGroup.getLevel());
        }
        //解绑原本布控车辆
        innerParams = new HashMap<>();
        innerParams.put("id", carGroupDetail.getId());
        List<CarGroupDetail> carGroupDetailList = carGroupDetailMapper.selectCarGroupDetailList(innerParams);
        if (carGroupDetailList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, carGroupDetail.getId() + "布控明细不存在");
        }
        CarGroupDetail carGroupDetailP=carGroupDetailList.get(0);
        String carNum=carGroupDetailP.getCarNum();
        if(carNum!=null && !StringUtils.isEmpty(carNum))
        {
            Map innerParam=new HashMap();
            innerParam.put("num",carNum);
            List<BaseCar> baseCarList=baseCarMapper.selectCarList(innerParam);
            if(baseCarList.size()>0){
                BaseCar baseCar=baseCarList.get(0);
                baseCar.setCarGroupDetailId(null);
                baseCarMapper.updateByNum(baseCar);
            }
        }
        //修改新的布控车辆
        innerParams = new HashMap();
        innerParams.put("num", carGroupDetail.getCarNum());
        List<BaseCar> baseCarList = baseCarMapper.selectCarList(innerParams);
        if (baseCarList.size() > 0) {
            BaseCar baseCar = baseCarList.get(0);
            baseCar.setCarGroupDetailId(carGroupDetail.getId()+"");
            baseCarMapper.updateByNum(baseCar);
        }
        //字典查询
        //布控明细等级 level
//        carGroupDetail.setLevelName(DicUtils.getValue("car_group_detail|level",carGroupDetail.getLevel().toString(),"布控明细等级值非法"));
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        carGroupDetail.setUpdateUser(loginAppUser.getUserId());

        //图片上传
        if(carGroupDetail.getCarImage()!=null && !StringUtils.isEmpty(carGroupDetail.getCarImage())){

            String carImage=carGroupDetail.getCarImage();
//            byte[] byteArr= PicUtil.stringToInputStream(carImage.split(",")[1]);
            byte[] byteArr= PicUtil.stringToInputStream(carImage);
            // 根据最大主键
            Integer id=carGroupDetail.getId();
            //rename pic
            String fileName = PicUtil.getPicName("car_group_detail", id);

            //封装访问路径：年/月/日
            Date date=new Date();
            //图片放入打包路径
            FileUploadUtil.inputStreamToLocalFile(byteArr,
                    CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+ PicUtil.getPicRelativePath(date),fileName);

            carGroupDetail.setCarImage(PicUtil.getPicRelativePath(date)+ fileName);
        }


        carGroupDetailMapper.updateByPrimaryKey(carGroupDetail);
        //记录操作
        //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("car_group_detail",null,carGroupDetail.getId().toString(),"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void deleteCarGroupDetailById(int id) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<CarGroupDetail> carGroupDetailList = carGroupDetailMapper.selectCarGroupDetailList(params);
        if (carGroupDetailList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, id + "布控车辆不存在");
        }
        CarGroupDetail carGroupDetail=carGroupDetailList.get(0);
        String carNum=carGroupDetail.getCarNum();
        if(carNum!=null && !StringUtils.isEmpty(carNum))
        {
            Map innerParam=new HashMap();
            innerParam.put("num",carNum);
            List<BaseCar> baseCarList=baseCarMapper.selectCarList(innerParam);
            if(baseCarList.size()>0){
                BaseCar baseCar=baseCarList.get(0);
                baseCar.setCarGroupDetailId(null);
                baseCarMapper.updateByNum(baseCar);
            }
        }
        carGroupDetailMapper.deleteByPrimaryKey(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("car_group_detail",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }
}
