package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.dao.SysDicDataMapper;
import com.windaka.suizhi.manageport.dao.SysDicMapper;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.model.SysDic;
import com.windaka.suizhi.manageport.model.SysDicData;
import com.windaka.suizhi.manageport.service.SysDicDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SysDicDataServiceImpl implements SysDicDataService {
    @Autowired
    private SysDicMapper sysDicMapper;
    @Autowired
    private SysDicDataMapper sysDicDataMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Override
    public void insertSysDicData(String dicCode, List sysDicDatas) throws OssRenderException {
        if (sysDicDatas != null) {
            Iterator i = sysDicDatas.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                if (dicCode == null || StringUtils.isEmpty(dicCode)) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicCode不能为空");
                }
                if (t.get("dicKey") == null || StringUtils.isEmpty(t.get("dicKey").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicKey不能为空");
                }
                if (t.get("dicValue") == null || StringUtils.isEmpty(t.get("dicValue").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicValue不能为空");
                }
                t.put("dicCode", dicCode);

                Map<String, Object> params = new HashMap<>();
                params.put("dicCode", t.get("dicCode"));
                params.put("dicKey", t.get("dicKey"));

                List<SysDic> sysDicList = sysDicMapper.selectSysDicList(params);
                if (sysDicList.size() <= 0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, dicCode + "字典不存在,请先新建字典");
                }

                List<SysDicData> sysDicDataList = sysDicDataMapper.selectSysDicDataList(params);
                if (sysDicDataList.size() > 0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicKey不能重复");
                }

                SysDicData sysDicData = JSON.parseObject(JSON.toJSONString(t), SysDicData.class);//Map 转 实体
                sysDicDataMapper.insertSysDicData(sysDicData);
                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("sys_dic_data",null,sysDicData.getId().toString(),"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteById(String id) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<SysDicData> sysDicDataList = sysDicDataMapper.selectSysDicDataList(params);
        if (sysDicDataList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, id + "记录不存在");
        }
        sysDicDataMapper.deleteById(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("sys_dic_data",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateById(SysDicData sysDicData) throws OssRenderException {
        if (sysDicData.getDicCode() == null || StringUtils.isEmpty(sysDicData.getDicCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "dicCode不能为空");
        }
        if (sysDicData.getDicKey() == null || StringUtils.isEmpty(sysDicData.getDicKey().toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "Dickey不能为空");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("dicCode", sysDicData.getDicCode());
        List<SysDic> sysDicList=sysDicMapper.selectSysDicList(params);
        if (sysDicList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, sysDicData.getDicCode() + "字典不存在,请先新建字典");
        }

        params.put("dicKey", sysDicData.getDicKey());//更新 防止相同字典中 出现相同key值
        List<SysDicData> sysDicDataList = sysDicDataMapper.selectSysDicDataList(params);
        int id=sysDicDataList.get(0).getId();
        int id2=sysDicData.getId();
        if (id!=id2) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, sysDicData.getDicCode()+"字典中 "+"dicKey不能重复");
        }
        sysDicDataMapper.updateById(sysDicData);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("sys_dic_data",null,sysDicData.getId().toString(),"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectSysDicDataList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<SysDicData> list = sysDicDataMapper.selectSysDicDataList(params);
        map.put("list", list);
        return map;
    }
}
