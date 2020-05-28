package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.BaseCommunityMapper;
import com.windaka.suizhi.manageport.dao.CaptureDeviceMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.dao.SysDicDataMapper;
import com.windaka.suizhi.manageport.model.BaseCommunity;
import com.windaka.suizhi.manageport.model.CaptureDevice;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.CaptureDeviceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CaptureDeviceServiceImpl implements CaptureDeviceService {
    @Autowired
    private CaptureDeviceMapper captureDeviceMapper;
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private SysDicDataMapper sysDicDataMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Override
    public void insertDevice(String communityCode, List devices) throws OssRenderException {
        if (devices != null) {
            Iterator i = devices.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                if (communityCode == null || StringUtils.isEmpty(communityCode)) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "社区代码不能为空");
                }
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备编号code不能为空");
                }
                if (t.get("name") == null || StringUtils.isEmpty(t.get("name").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备名称name不能为空");
                }
                if (t.get("type") == null || StringUtils.isEmpty(t.get("type").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备类型type不能为空");
                }

                t.put("communityCode", communityCode);//小区代码

                Map params;
                //根据type  查询typeName
//                String typeStr=t.get("type").toString();
//                String typeName="";
//                String[] str= Tools.extNum(typeStr);//提取type 字符串中数字 转化为 字符串数组  （1，2，3）----》[1,2,3]
//
//                for (int k=0;k<str.length;k++){//根据[1,2,3] 设备类型代码 查询相对应 设备类型名词
//                    if (k!=str.length-1){
//                        typeName+= DicUtils.getValue("capture_device|type",str[k],"字典无此代码数据")+",";
//                    }else {
//                        typeName+=DicUtils.getValue("capture_device|type",str[k],"字典无此代码数据");
//                    }
//                }

                //添加设备表 typeName
//                t.put("typeName",typeName);

                //查询绑定小区 是否存在

                params = new HashMap<>();
                params.put("code", t.get("communityCode"));
                List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
                if (baseCommunityList.size() >0) {
                   String communityName=baseCommunityList.get(0).getName();
                   t.put("communityName",communityName);//若存在  存小区名称
                }else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, communityCode+ "小区不存在");
                }

                //防止插入 相同设备信息
                 params = new HashMap<>();
                params.put("code", t.get("code"));
                List<CaptureDevice> captureDevices = captureDeviceMapper.selectDeviceList(params);
                if (captureDevices.size() >0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, t.get("code")+ "设备已存在");
                }

                CaptureDevice captureDevice= JSON.parseObject(JSON.toJSONString(t), CaptureDevice.class);//Map 转 实体
                captureDeviceMapper.insertDevice(captureDevice);
                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("capture_device",captureDevice.getCode(),null,"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteByCode(String code) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        List<CaptureDevice> captureDevices = captureDeviceMapper.selectDeviceList(params);
        if (captureDevices.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, code+"设备不存在");
        }
        captureDeviceMapper.deleteByCode(code);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("capture_device",code,null,"delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateByCode(CaptureDevice captureDevice) throws OssRenderException {
        //必要数据不可为空
        if (captureDevice.getCode() == null || StringUtils.isEmpty(captureDevice.getCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备编号不能为空");
        }
        if (captureDevice.getCommunityCode() == null || StringUtils.isEmpty(captureDevice.getCommunityCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "社区代码不能为空");
        }
        if (captureDevice.getName() == null || StringUtils.isEmpty(captureDevice.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备名称name不能为空");
        }
        if (captureDevice.getType() == null || StringUtils.isEmpty(captureDevice.getType())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备类型type不能为空");
        }
        //数据处理
        //查询绑定小区 是否存在
        Map<String, Object> params = new HashMap<>();
        params.put("code", captureDevice.getCommunityCode());
        List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
        if (baseCommunityList.size() >0) {
            String communityName=baseCommunityList.get(0).getName();
            captureDevice.setCommunityName(communityName);//若存在  存小区名称
        }else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, captureDevice.getCommunityCode()+"小区不存在");
        }
        //根据type  查询typeName
//        String typeStr=captureDevice.getType();
//        String typeName="";
//        String[] str= Tools.extNum(typeStr);//提取type 字符串中数字 转化为 字符串数组  （1，2，3）----》[1,2,3]
//
//        for (int k=0;k<str.length;k++){//根据[1,2,3] 设备类型代码 查询相对应 设备类型名词
//            if (k!=str.length-1){
//                typeName+= DicUtils.getValue("capture_device|type",str[k],"capture_device|type字典无此代码数据")+",";
//            }else {
//                typeName+=DicUtils.getValue("capture_device|type",str[k],"capture_device|type字典无此代码数据");
//            }
//        }
//
//        //添加设备表 typeName
//        captureDevice.setTypeName(typeName);

        captureDeviceMapper.updateByCode(captureDevice);

        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("capture_device",captureDevice.getCode(),null,"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectDeviceList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<CaptureDevice> list = captureDeviceMapper.selectDeviceList(params);
        map.put("list", list);
        return map;
    }
}
