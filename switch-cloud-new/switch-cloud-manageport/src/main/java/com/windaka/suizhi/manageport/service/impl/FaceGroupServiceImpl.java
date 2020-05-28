package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.dao.FaceGroupDetailMapper;
import com.windaka.suizhi.manageport.dao.FaceGroupMapper;
import com.windaka.suizhi.manageport.model.FaceGroup;
import com.windaka.suizhi.manageport.model.FaceGroupDetail;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.FaceGroupDetailService;
import com.windaka.suizhi.manageport.service.FaceGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FaceGroupServiceImpl implements FaceGroupService {
    @Autowired
    private FaceGroupMapper faceGroupMapper;
    @Autowired
    private FaceGroupDetailMapper faceGroupDetailMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private FaceGroupDetailService faceGroupDetailService;
    @Override
    public void insertFaceGroup(List faceGroups) throws OssRenderException {
        if (faceGroups != null) {
            Iterator i = faceGroups.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                //判断必要数据是否为空
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
                }
                if (t.get("type") == null || StringUtils.isEmpty(t.get("type").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库类型不能为空");
                }
                if (t.get("name") == null || StringUtils.isEmpty(t.get("name").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库名称不能为空");
                }

                //查询添加绑定信息
                //操作人
//                String str = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                t.put("createUser",loginAppUser.getUserId());

                //布控库类型名称
//                t.put("typeName", DicUtils.getValue("face_group|type",t.get("type").toString(),"face_group|type字典无此编码"));
//                //code
//                //t.put("code",StringUtils.uuId(t.get("type").toString()));
//                if (t.get("level") != null && ! StringUtils.isEmpty(t.get("level").toString())) {
//                   t.put("levelName",DicUtils.getValue("face_group|level",t.get("level").toString(),"face_group|level字典无此编码"));
//                }
                FaceGroup faceGroup = JSON.parseObject(JSON.toJSONString(t), FaceGroup.class);//Map 转 实体
                faceGroupMapper.insertFaceGroup(faceGroup);
                //记录操作
                //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("face_group",faceGroup.getCode(),null,"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteByCode(String code) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        List<FaceGroup> faceGroupList = faceGroupMapper.selectFaceGroupList(params);
        if (faceGroupList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, code + "布控库不存在");
        }
        faceGroupMapper.deleteByCode(code);
        Map innerParam=new HashMap();
        innerParam.put("groupCode",code);
        List<FaceGroupDetail> faceGroupDetailList=faceGroupDetailMapper.selectFaceGroupDetailList(innerParam);
        Iterator<FaceGroupDetail> fgdI=faceGroupDetailList.iterator();
        while (fgdI.hasNext()){
            FaceGroupDetail fgdT=fgdI.next();
            faceGroupDetailService.deleteById(fgdT.getId().toString());
        }
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("face_group",code,null,"delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateByCode(FaceGroup faceGroup) throws OssRenderException {
        //判断必要数据不能为空
        if (faceGroup.getType() == null || StringUtils.isEmpty(faceGroup.getType().toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库类型不能为空");
        }
        if (faceGroup.getName() == null || StringUtils.isEmpty(faceGroup.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库名称不能为空");
        }
        //查询添加绑定信息
        //操作人
//        String str = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        faceGroup.setOpUser(loginAppUser.getUserId());

        //布控库类型名称
//        faceGroup.setTypeName(DicUtils.getValue("face_group|type",faceGroup.getType().toString(),"face_group|type字典无此编码"));
//        if (faceGroup.getLevel() != null && ! StringUtils.isEmpty(faceGroup.getLevel().toString())) {
//            faceGroup.setLevelName(DicUtils.getValue("face_group|level",faceGroup.getLevel().toString(),"face_group|level典无此编码"));
//        }

        faceGroupMapper.updateByCode(faceGroup);
        Map innerParam=new HashMap();
        innerParam.put("groupCode",faceGroup.getCode());
        List<FaceGroupDetail> faceGroupDetailList=faceGroupDetailMapper.selectFaceGroupDetailList(innerParam);
        Iterator<FaceGroupDetail> fgdI=faceGroupDetailList.iterator();
        while (fgdI.hasNext()){
            FaceGroupDetail fgdT=fgdI.next();
            fgdT.setGroupLevel(faceGroup.getLevel());
            fgdT.setGroupName(faceGroup.getName());
            faceGroupDetailMapper.updateById(fgdT);
        }
        //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("face_group",faceGroup.getCode(),null,"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectFaceGroupList(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        List<FaceGroup> list = faceGroupMapper.selectFaceGroupList(params);
        map.put("list", list);
        return map;
    }
}
