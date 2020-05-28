package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.Tools;
import com.windaka.suizhi.manageport.dao.BaseCommunityMapper;
import com.windaka.suizhi.manageport.dao.BaseParkingMapper;
import com.windaka.suizhi.manageport.dao.BasePersonMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.model.BaseCommunity;
import com.windaka.suizhi.manageport.model.BaseParking;
import com.windaka.suizhi.manageport.model.BasePerson;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.BaseParkingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BaseParkingServiceImpl implements BaseParkingService {

    @Autowired
    private BaseParkingMapper baseParkingMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;

    @Autowired
    private BasePersonMapper basePersonMapper;

    @Override
    public void insertBaseParking(String communityCode, List list) throws OssRenderException {
        if(list!=null) {
            Iterator i = list.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                if(t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())){
                    String code= Tools.getUUID(communityCode,20);
                    t.put("code",code);
                }
                //1 查询小区名称是否存在
                Map innerParams=new HashMap<>();
                innerParams.put("code",communityCode);
                if(communityCode!=null && !StringUtils.isEmpty(communityCode)){
                    List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(innerParams);
                    if(baseCommunityList==null||baseCommunityList.isEmpty()) {
                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "该小区不存在");
                    }
                    else{
                        BaseCommunity baseCommunity=baseCommunityList.get(0);
                        t.put("communityCode",baseCommunity.getCode());
                        t.put("communityName",baseCommunity.getName());
                    }
                }
                else
                {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区code不能为空");
                }

                innerParams=new HashMap<>();
                innerParams.put("code",t.get("personCode"));
                if(t.get("personCode")!=null && !StringUtils.isEmpty(t.get("personCode").toString()))
                {
                    List<BasePerson> basePersonListM=basePersonMapper.selectPersonList(innerParams);
                    if(basePersonListM.size()<=0) {
                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "该人员不存在");
                    }
                }

                BaseParking baseParking= JSON.parseObject(JSON.toJSONString(t), BaseParking.class);
                baseParkingMapper.insertBaseParking(baseParking);

                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("base_parking",t.get("code").toString(),null,"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void updateBaseParking(BaseParking baseParking) throws OssRenderException {
        //1 查询小区名称是否存在
        Map innerParams=new HashMap<>();
        innerParams.put("code",baseParking.getCommunityCode());
        if(baseParking.getCommunityCode()!=null && !StringUtils.isEmpty(baseParking.getCommunityCode())){
            List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(innerParams);
            if(baseCommunityList==null||baseCommunityList.isEmpty()) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "该小区不存在");
            }
            else{
                BaseCommunity baseCommunity=baseCommunityList.get(0);
                baseParking.setCommunityCode(baseCommunity.getCode());
                baseParking.setCommunityName(baseCommunity.getName());
            }
        }
        else
        {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区code不能为空");
        }

        innerParams=new HashMap<>();
        innerParams.put("code",baseParking.getPersonCode());
        if(baseParking.getPersonCode()!=null && !StringUtils.isEmpty(baseParking.getPersonCode()))
        {
            List<BasePerson> basePersonListM=basePersonMapper.selectPersonList(innerParams);
            if(basePersonListM.size()<=0) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "该人员不存在");
            }
        }

        baseParkingMapper.updateBaseParking(baseParking);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("base_parking",baseParking.getCode(),null,"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void deleteBaseParking(String code) throws OssRenderException {
        baseParkingMapper.deleteBaseParking(code);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("base_parking",code,null,"delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }
}
