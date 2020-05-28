package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.BaseCommunityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BaseCommunityServiceImpl implements BaseCommunityService {
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private BaseCarMapper baseCarMapper;
    @Autowired
    private BaseRoomMapper baseRoomMapper;
    @Autowired
    private CaptureDeviceMapper captureDeviceMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private BasePetMapper basePetMapper;

    @Override
    public void insertCommunity(List BaseCommunity) throws OssRenderException {
        if (BaseCommunity != null) {
            Iterator i = BaseCommunity.iterator();
            while (i.hasNext()) {

                //判断必要数据是否为空
                Map<String, Object> t = (Map<String, Object>) i.next();
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区code不能为空");
                }
                if (t.get("name") == null || StringUtils.isEmpty(t.get("name").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "name不能为空");
                }

                //判断数据合法性
                Map params;
                params = new HashMap<>();
                params.put("code", t.get("code"));
                List<com.windaka.suizhi.manageport.model.BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
                if (baseCommunityList.size() > 0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区code不能重复");
                }

                com.windaka.suizhi.manageport.model.BaseCommunity baseCommunity = JSON.parseObject(JSON.toJSONString(t), com.windaka.suizhi.manageport.model.BaseCommunity.class);
                baseCommunityMapper.insertCommunity(baseCommunity);
                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord = new OperateRecord("base_community", baseCommunity.getCode(), null, "insert", loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteByCode(String code) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
        if (baseCommunityList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, code + "号社区不存在");
        }
        BaseCommunity baseCommunity = baseCommunityList.get(0);
        baseCommunityMapper.deleteByCode(code);



        Map innerParam;
        innerParam = new HashMap();
        innerParam.put("communityCode", baseCommunity.getCode());
        innerParam.put("communityName", baseCommunity.getName()+"(该小区已删除)");
        //级联修改-人
//        List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParam);
//        Iterator<BasePerson> bpI = basePersonList.iterator();
//        while (bpI.hasNext()) {
//            BasePerson bpT = bpI.next();
//            bpT.setCommunityName(baseCommunity.getName());
//            basePersonMapper.updateByCode(bpT);
//        }
        basePersonMapper.updatePersonByCommunityCode(innerParam);

        //级联修改-车
//        List<BaseCar> baseCarList = baseCarMapper.selectCarList(innerParam);
//        Iterator<BaseCar> bcI = baseCarList.iterator();
//        while (bcI.hasNext()) {
//            BaseCar bcT = bcI.next();
//            bcT.setCommunityName(baseCommunity.getName());
//            baseCarMapper.updateByNum(bcT);
//        }
        baseCarMapper.updateCarByCommunityCode(innerParam);

        //级联修改-房屋
        baseRoomMapper.updateRoomByCommunityCode(innerParam);

        //级联修改-设备
//        List<CaptureDevice> captureDeviceList = captureDeviceMapper.selectDeviceList(innerParam);
//        Iterator<CaptureDevice> cdI = captureDeviceList.iterator();
//        while (cdI.hasNext()) {
//            CaptureDevice cdT = cdI.next();
//            cdT.setCommunityName(baseCommunity.getName());
//            captureDeviceMapper.updateByCode(cdT);
//        }
        captureDeviceMapper.updateDeviceByCommunityCode(innerParam);

        //级联修改-宠物
//        List<BasePet> basePetList = basePetMapper.selectPetsList(innerParam);
//        Iterator<BasePet> bppI=basePetList.iterator();
//        while (bppI.hasNext()){
//            BasePet bppT=bppI.next();
//            bppT.setCommunityName(baseCommunity.getName());
//            basePetMapper.updateByCode(bppT);
//        }
        basePetMapper.updatePetByCommunityCode(innerParam);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_community", baseCommunity.getCode(), null, "delete", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateByCode(BaseCommunity baseCommunity) throws OssRenderException {

        //判断必要数据是否为空
        if (baseCommunity.getCode() == null || StringUtils.isEmpty(baseCommunity.getCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区code不能为空");
        }
        if (baseCommunity.getName() == null || StringUtils.isEmpty(baseCommunity.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区name不能为空");
        }

        //判断数据合法性
        Map<String, Object> params = new HashMap<>();
        params.put("code", baseCommunity.getCode());
        List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
        if (baseCommunityList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, baseCommunity.getCode() + "号社区不存在");
        }
        String communityNameBefore=baseCommunityList.get(0).getName();
        baseCommunityMapper.updateByCode(baseCommunity);
        String communityNameAfter=baseCommunity.getName();

        if(!communityNameBefore.equals(communityNameAfter))
        {
            Map innerParam;
            innerParam = new HashMap();
            innerParam.put("communityCode", baseCommunity.getCode());
            innerParam.put("communityName", baseCommunity.getName());
            //级联修改-人
//        List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParam);
//        Iterator<BasePerson> bpI = basePersonList.iterator();
//        while (bpI.hasNext()) {
//            BasePerson bpT = bpI.next();
//            bpT.setCommunityName(baseCommunity.getName());
//            basePersonMapper.updateByCode(bpT);
//        }
            basePersonMapper.updatePersonByCommunityCode(innerParam);

            //级联修改-车
//        List<BaseCar> baseCarList = baseCarMapper.selectCarList(innerParam);
//        Iterator<BaseCar> bcI = baseCarList.iterator();
//        while (bcI.hasNext()) {
//            BaseCar bcT = bcI.next();
//            bcT.setCommunityName(baseCommunity.getName());
//            baseCarMapper.updateByNum(bcT);
//        }
            baseCarMapper.updateCarByCommunityCode(innerParam);

            //级联修改-房屋
            baseRoomMapper.updateRoomByCommunityCode(innerParam);

            //级联修改-设备
//        List<CaptureDevice> captureDeviceList = captureDeviceMapper.selectDeviceList(innerParam);
//        Iterator<CaptureDevice> cdI = captureDeviceList.iterator();
//        while (cdI.hasNext()) {
//            CaptureDevice cdT = cdI.next();
//            cdT.setCommunityName(baseCommunity.getName());
//            captureDeviceMapper.updateByCode(cdT);
//        }
            captureDeviceMapper.updateDeviceByCommunityCode(innerParam);

            //级联修改-宠物
//        List<BasePet> basePetList = basePetMapper.selectPetsList(innerParam);
//        Iterator<BasePet> bppI=basePetList.iterator();
//        while (bppI.hasNext()){
//            BasePet bppT=bppI.next();
//            bppT.setCommunityName(baseCommunity.getName());
//            basePetMapper.updateByCode(bppT);
//        }
            basePetMapper.updatePetByCommunityCode(innerParam);
        }

        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_community", baseCommunity.getCode(), null, "update", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectCommunityList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<BaseCommunity> list = baseCommunityMapper.selectCommunityList(params);
        map.put("list", list);
        return map;
    }

    @Override
    public void emptyCommunity(String code) throws OssRenderException {
        basePersonMapper.deletePersonByCommunityCode(code);
        baseRoomMapper.deleteAnyByCommunityCode(code);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_community", code, null, "empty", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }
}
