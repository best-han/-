package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.constants.CommonConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.FileUploadUtil;
import com.windaka.suizhi.common.utils.PicUtil;
import com.windaka.suizhi.manageport.dao.BasePersonMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.dao.FaceGroupDetailMapper;
import com.windaka.suizhi.manageport.dao.FaceGroupMapper;
import com.windaka.suizhi.manageport.model.BasePerson;
import com.windaka.suizhi.manageport.model.FaceGroup;
import com.windaka.suizhi.manageport.model.FaceGroupDetail;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.FaceGroupDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class FaceGroupDetailServiceImpl implements FaceGroupDetailService {
    @Autowired
    private FaceGroupDetailMapper faceGroupDetailMapper;
    @Autowired
    private FaceGroupMapper faceGroupMapper;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Override
    public void insertFaceGroupDetail(List faceGroupDetail) throws OssRenderException {
        if (faceGroupDetail != null) {
            Iterator i = faceGroupDetail.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                //判断必要数据是否为空
                if (t.get("groupCode") == null || StringUtils.isEmpty(t.get("groupCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库不能为空");
                }
                if (t.get("level") == null || StringUtils.isEmpty(t.get("level").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控人员等级不能为空");
                }
                if (t.get("personName") == null || StringUtils.isEmpty(t.get("personName").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控人员名称不能为空");
                }



                Map params;
                //查询添加关联数据

                //布控库名称
                params = new HashMap();
                params.put("code", t.get("groupCode"));
                List<FaceGroup> faceGroupList = faceGroupMapper.selectFaceGroupList(params);
                if (faceGroupList.size() > 0) {
                    FaceGroup faceGroup = faceGroupList.get(0);
                    t.put("groupName", faceGroup.getName());
                    t.put("groupLevel",faceGroup.getLevel());
                } else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "该布控库不存在");
                }
                //添加人员信息
                if (t.get("personCode") != null && !StringUtils.isEmpty(t.get("personCode").toString())) {
                    params = new HashMap();
                    params.put("code", t.get("personCode"));
                    List<BasePerson> basePersonList = basePersonMapper.selectPersonList(params);
                    if (basePersonList.size() > 0) {
                        BasePerson basePerson = basePersonList.get(0);
                        t.put("personName", basePerson.getName());
                        t.put("personPhone", basePerson.getPhone());
                        t.put("personPaperNumber", basePerson.getPaperNumber());
                    }
                }
                //布控库等级名称
//                t.put("levelName", DicUtils.getValue("face_group_detail|level", t.get("level").toString(), "face_group_detail|level无此代码字典"));

                //查询添加绑定信息
                //操作人
//                String str = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                t.put("createUser",loginAppUser.getUserId());
                t.put("updateUser",loginAppUser.getUserId());

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("face_group_detail");
                if(t.get("personImage")!=null && !StringUtils.isEmpty(t.get("personImage").toString())){

                    String personImage=t.get("personImage").toString();
//                    byte[] byteArr= PicUtil.stringToInputStream(personImage.split(",")[1]);
                    byte[] byteArr= PicUtil.stringToInputStream(personImage);
                    //rename pic
                    String fileName = PicUtil.getPicName("face_group_detail", nextId);

                    //封装访问路径：年/月/日
                    Date date=new Date();
                    //图片放入打包路径
                    FileUploadUtil.inputStreamToLocalFile(byteArr,
                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+ PicUtil.getPicRelativePath(date),fileName);

                    t.put("personImage", PicUtil.getPicRelativePath(date)+ fileName);
                }

                FaceGroupDetail faceGroupDetail1 = JSON.parseObject(JSON.toJSONString(t), FaceGroupDetail.class);//Map 转 实体
                faceGroupDetailMapper.insertFaceGroupDetail(faceGroupDetail1);
                Map innerParam=new HashMap();
                innerParam.put("code",t.get("personCode"));
                List<BasePerson> basePersonList=basePersonMapper.selectPersonList(innerParam);
                if(basePersonList.size()>0){
                    BasePerson basePerson=basePersonList.get(0);
                    basePerson.setFaceGroupDetailId(nextId+"");
                    basePersonMapper.updateByCode(basePerson);
                }
                //记录操作
                //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("face_group_detail",null,nextId+"","insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteById(String id) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<FaceGroupDetail> faceGroupDetailList = faceGroupDetailMapper.selectFaceGroupDetailList(params);
        if (faceGroupDetailList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, id + "布控人员不存在");
        }
        FaceGroupDetail faceGroupDetail=faceGroupDetailList.get(0);
        String personCode=faceGroupDetail.getPersonCode();
        if(personCode!=null && !StringUtils.isEmpty(personCode))
        {
            Map innerParam=new HashMap();
            innerParam.put("code",personCode);
            List<BasePerson> basePersonList=basePersonMapper.selectPersonList(innerParam);
            if(basePersonList.size()>0){
                BasePerson basePerson=basePersonList.get(0);
                basePerson.setFaceGroupDetailId(null);
                basePersonMapper.updateByCode(basePerson);
            }
        }
        faceGroupDetailMapper.deleteById(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("face_group_detail",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateById(FaceGroupDetail faceGroupDetail) throws OssRenderException {
        //判断必要数据是否为空
        if (faceGroupDetail.getGroupCode() == null || StringUtils.isEmpty(faceGroupDetail.getGroupCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控库不能为空");
        }
        if (faceGroupDetail.getLevel() == null || StringUtils.isEmpty(faceGroupDetail.getLevel().toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控人员等级不能为空");
        }
        if (faceGroupDetail.getPersonName() == null || StringUtils.isEmpty(faceGroupDetail.getPersonName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控人员名称不能为空");
        }

        Map params;
        //查询添加关联数据

        //布控库名称
        params = new HashMap();
        params.put("code", faceGroupDetail.getGroupCode());
        List<FaceGroup> faceGroupList = faceGroupMapper.selectFaceGroupList(params);
        if (faceGroupList.size() > 0) {
            FaceGroup faceGroup = faceGroupList.get(0);
            faceGroupDetail.setGroupName(faceGroup.getName());
        } else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该布控库不存在");
        }
        //添加人员信息
        if (faceGroupDetail.getPersonCode() != null && !StringUtils.isEmpty(faceGroupDetail.getPersonCode())) {
            //解绑原本布控人员
            params = new HashMap<>();
            params.put("id", faceGroupDetail.getId());
            List<FaceGroupDetail> faceGroupDetailList = faceGroupDetailMapper.selectFaceGroupDetailList(params);
            if (faceGroupDetailList.size() <= 0) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, faceGroupDetail.getId() + "布控明细不存在");
            }
            FaceGroupDetail faceGroupDetailP=faceGroupDetailList.get(0);
            String personCode=faceGroupDetailP.getPersonCode();
            if(personCode!=null && !StringUtils.isEmpty(personCode))
            {
                Map innerParam=new HashMap();
                innerParam.put("code",personCode);
                List<BasePerson> basePersonList=basePersonMapper.selectPersonList(innerParam);
                if(basePersonList.size()>0){
                    BasePerson basePerson=basePersonList.get(0);
                    basePerson.setFaceGroupDetailId(null);
                    basePersonMapper.updateByCode(basePerson);
                }
            }
            //修改新的布控车辆
            params = new HashMap();
            params.put("code", faceGroupDetail.getPersonCode());
            List<BasePerson> basePersonList = basePersonMapper.selectPersonList(params);
            if (basePersonList.size() > 0) {
                BasePerson basePerson = basePersonList.get(0);
                faceGroupDetail.setPersonName(basePerson.getName());
                faceGroupDetail.setPersonPhone(basePerson.getPhone());
                faceGroupDetail.setPersonPaperNumber(basePerson.getPaperNumber());
                basePerson.setFaceGroupDetailId(faceGroupDetail.getId()+"");
                basePersonMapper.updateByCode(basePerson);
            }
        }
        //布控库等级名称
//        faceGroupDetail.setLevelName(DicUtils.getValue("face_group_detail|level", faceGroupDetail.getLevel().toString(), "face_group_detail|level无此代码字典"));

        //操作人
//        String str = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        faceGroupDetail.setUpdateUser(loginAppUser.getUserId());

        //图片上传
        if(faceGroupDetail.getPersonImage()!=null && !StringUtils.isEmpty(faceGroupDetail.getPersonImage())){

            String personImage=faceGroupDetail.getPersonImage();
            byte[] byteArr= PicUtil.stringToInputStream(personImage.split(",")[1]);
            // 根据最大主键
            Integer id=faceGroupDetail.getId();
            //rename pic
            String fileName = PicUtil.getPicName("face_group_detail", id);

            //封装访问路径：年/月/日
            Date date=new Date();
            //图片放入打包路径
            FileUploadUtil.inputStreamToLocalFile(byteArr,
                    CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+ PicUtil.getPicRelativePath(date),fileName);

            faceGroupDetail.setPersonImage(PicUtil.getPicRelativePath(date)+ fileName);
        }


        faceGroupDetailMapper.updateById(faceGroupDetail);
        //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("face_group_detail",null,faceGroupDetail.getId().toString(),"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectFaceGroupDetailList(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        List<FaceGroupDetail> list = faceGroupDetailMapper.selectFaceGroupDetailList(params);
        map.put("list", list);
        return map;
    }
}
