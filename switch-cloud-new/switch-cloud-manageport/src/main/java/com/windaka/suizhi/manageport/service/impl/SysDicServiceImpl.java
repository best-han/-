package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.dao.SysDicMapper;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.model.SysDic;
import com.windaka.suizhi.manageport.service.SysDicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SysDicServiceImpl implements SysDicService {
    @Autowired
    private SysDicMapper sysDicMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Override
    public void insertSysDic(List sysDics) throws OssRenderException {
        if (sysDics != null) {
            Iterator i = sysDics.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                if (t.get("dicCode") == null || StringUtils.isEmpty(t.get("dicCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicCode不能为空");
                }
                if (t.get("dicName") == null || StringUtils.isEmpty(t.get("dicName").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicName不能为空");
                }
                Map<String, Object> params = new HashMap<>();
                params.put("dicCode", t.get("dicCode"));
                List<SysDic> sysDicList = sysDicMapper.selectSysDicList(params);
                if (sysDicList.size() > 0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicCode不能重复");
                }
                SysDic sysDic = JSON.parseObject(JSON.toJSONString(t), SysDic.class);
                sysDicMapper.insertSysDic(sysDic);
                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("sys_dic",sysDic.getDicCode(),null,"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteByCode(String code) throws OssRenderException {

        Map<String, Object> params = new HashMap<>();
        params.put("dicCode", code);
        List<SysDic> sysDicList = sysDicMapper.selectSysDicList(params);
        if (sysDicList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, code+"记录不存在");
        }
        sysDicMapper.deleteByCode(code);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("sys_dic",code,null,"delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateByCode(SysDic sysDic) throws OssRenderException {
        if (sysDic.getDicCode() == null || StringUtils.isEmpty(sysDic.getDicCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicCode不能为空");
        }
        if (sysDic.getDicName()  == null || StringUtils.isEmpty(sysDic.getDicName() )) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicName不能为空");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("dicCode", sysDic.getDicCode());
        List<SysDic> sysDicList = sysDicMapper.selectSysDicList(params);
        if (sysDicList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, sysDic.getDicCode()+"记录不存在");
        }
        sysDicMapper.updateByCode(sysDic);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("sys_dic",sysDic.getDicCode(),null,"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectSysDicList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<SysDic> list = sysDicMapper.selectSysDicList(params);
        map.put("list", list);
        return map;
    }
}
