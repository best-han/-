package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.CarGroupDetailMapper;
import com.windaka.suizhi.manageport.dao.CarGroupMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.model.CarGroup;
import com.windaka.suizhi.manageport.model.CarGroupDetail;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.CarGroupDetailService;
import com.windaka.suizhi.manageport.service.CarGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CarGroupServiceImpl implements CarGroupService {

    @Autowired
    private CarGroupMapper carGroupMapper;

    @Autowired
    private CarGroupDetailMapper carGroupDetailMapper;

    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;

    @Autowired
    private CarGroupDetailService carGroupDetailService;

    @Override
    public Map<String,Object> selectCarGroupList(Map<String, Object> params) throws OssRenderException {
        Map map=new HashMap();
        List<CarGroup> carGroupList=carGroupMapper.selectCarGroupList(params);
        map.put("list",carGroupList);
        return map;
    }

    @Override
    public void insertCarGroup(List carGroupList) throws OssRenderException {
        if(carGroupList!=null) {
            Iterator i = carGroupList.iterator();
            while (i.hasNext()){
                Map<String, Object> t = (Map<String, Object>) i.next();
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
                }
                if (t.get("type") == null || StringUtils.isEmpty(t.get("type").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "type不能为空");
                }
                if (t.get("name") == null || StringUtils.isEmpty(t.get("name").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "name不能为空");
                }
                //字典检索
//                //布控库类型 type
//                t.put("typeName", DicUtils.getValue("car_group|type",t.get("type").toString(),"布控库类型值非法"));
//                //布控库等级 level
//                if (t.get("level") != null && !StringUtils.isEmpty(t.get("level").toString())) {
//                    t.put("levelName",DicUtils.getValue("car_group|level", t.get("level").toString(),"布控库等级值非法"));
//                }
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                t.put("opUser",loginAppUser.getUserId());
//                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
//                    String code = StringUtils.uuId(t.get("type").toString());
//                    t.put("code",code);
//                }
                CarGroup carGroup= JSON.parseObject(JSON.toJSONString(t), CarGroup.class);
                carGroupMapper.insert(carGroup);
                //记录操作
                //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("car_group",carGroup.getCode(),null,"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void updateCarGroupByCode(CarGroup carGroup) throws OssRenderException {
        if (carGroup.getCode() == null || StringUtils.isEmpty(carGroup.getCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
        }
        if (carGroup.getType() == null || StringUtils.isEmpty(carGroup.getType().toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "type不能为空");
        }
        if (carGroup.getName() == null || StringUtils.isEmpty(carGroup.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "name不能为空");
        }
        //字典检索
        //布控库类型 type
//        carGroup.setTypeName(DicUtils.getValue("car_group|type",carGroup.getType().toString(),"布控库类型值非法"));
//        //布控库等级 level
//        if(carGroup.getLevel()!=null && !StringUtils.isEmpty(carGroup.getLevel().toString())){
//            carGroup.setLevelName(DicUtils.getValue("car_group|level", carGroup.getLevel().toString(),"布控库等级值非法"));
//        }
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        carGroup.setOpUser(loginAppUser.getUserId());
        carGroupMapper.updateByCode(carGroup);
        Map innerParam=new HashMap();
        innerParam.put("groupCode",carGroup.getCode());
        List<CarGroupDetail> carGroupDetailList=carGroupDetailMapper.selectCarGroupDetailList(innerParam);
        Iterator<CarGroupDetail> cgdI=carGroupDetailList.iterator();
        while (cgdI.hasNext()){
            CarGroupDetail cgdT=cgdI.next();
            cgdT.setGroupName(carGroup.getName());
            cgdT.setGroupLevel(carGroup.getLevel());
            carGroupDetailMapper.updateByPrimaryKey(cgdT);
        }
        //记录操作
        //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("car_group",carGroup.getCode(),null,"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void deleteCarGroupByCode(String code) throws OssRenderException {
        carGroupMapper.deleteByCode(code);
        Map innerParam=new HashMap();
        innerParam.put("groupCode",code);
        List<CarGroupDetail> carGroupDetailList=carGroupDetailMapper.selectCarGroupDetailList(innerParam);
        if(carGroupDetailList.size()<=0){
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "车辆布控库不存在");
        }
        Iterator<CarGroupDetail> cgdI=carGroupDetailList.iterator();
        while (cgdI.hasNext()){
            CarGroupDetail cgdT=cgdI.next();
            carGroupDetailService.deleteCarGroupDetailById(cgdT.getId());
        }
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("car_group",code,null,"delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }
}
