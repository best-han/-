package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.PageUtil;
//import com.windaka.suizhi.webapi.model.FaceLibrary;
import com.windaka.suizhi.webapi.dao.FaceGroupDao;
import com.windaka.suizhi.webapi.dao.FaceGroupPersonDao;
import com.windaka.suizhi.webapi.service.FaceGroupService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FaceGroupServiceImpl implements FaceGroupService {

    @Autowired
    FaceGroupDao faceGroupDao;
    @Autowired
    FaceGroupPersonDao faceGroupPersonDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFaceGroup(Map<String,Object> params) throws OssRenderException {
        LoginAppUser appUser= AppUserUtil.getLoginAppUser();
        String userId=appUser.getUserId();
        params.put("op_user",userId);
        String uuId= UUID.randomUUID().toString();
        params.put("faceGroupCode",uuId);
        faceGroupDao.saveFaceGroup(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFaceGroup(Map<String,Object> params) throws OssRenderException {
        LoginAppUser appUser= AppUserUtil.getLoginAppUser();
        String userId=appUser.getUserId();
        params.put("updateBy",userId);
        faceGroupDao.updateFaceGroup(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFaceGroup(String id) throws OssRenderException {
        faceGroupDao.deleteFaceGroupByCode(id);
        faceGroupPersonDao.deleteFaceGroupPersonByCode(id);
    }

    @Override
    public Map<String,Object> queryFaceGroups(Map<String,Object> params) throws OssRenderException {
        if(params.get("limit")==null || params.get("limit").toString().trim().equals("")){
            params.put("limit",10);
        }
        if(params.get("page")==null || params.get("page").toString().trim().equals("")){
            params.put("page",1);
        }
        int totalRows = faceGroupDao.queryTotalFaceGroup(params);
        List<Map<String,Object>> list = Collections.emptyList();
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list=faceGroupDao.queryFaceGroups(params);
        }
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;

    }
    
    /*public FaceLibrary selectFaceGroup(Map<String,Object> params) throws OssRenderException {
    	FaceLibrary face=faceGroupDao.selectFaceGroup(params);
    	return face==null?new FaceLibrary():face;
    }*/


}
