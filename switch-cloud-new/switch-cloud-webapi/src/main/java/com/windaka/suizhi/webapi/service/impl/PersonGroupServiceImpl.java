package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.*;
import com.windaka.suizhi.webapi.dao.FaceGroupMapper;
import com.windaka.suizhi.webapi.dao.auto.BasePersonAutoMapper;
import com.windaka.suizhi.webapi.dao.auto.FaceGroupDetailMapper;
import com.windaka.suizhi.webapi.dao.auto.FaceGroupRecordMapper;
import com.windaka.suizhi.webapi.dao.auto.FaceOwnerInfoMapper;
import com.windaka.suizhi.webapi.dao.ext.ExtFaceGroupDetailMapper;
import com.windaka.suizhi.webapi.feign.ImageFeatureClient;
import com.windaka.suizhi.webapi.model.*;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroup;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroupDetail;
import com.windaka.suizhi.webapi.service.PersonGroupService;
import com.windaka.suizhi.webapi.service.UserService;
import com.windaka.suizhi.webapi.util.Paginator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName PersonGroupServiceImpl
 * @Description 人员布控实现类
 * @Author lixianhua
 * @Date 2020/4/8 9:59
 * @Version 1.0
 */
@Service
public class PersonGroupServiceImpl implements PersonGroupService {

    @Autowired
    private FaceGroupMapper faceGroupMapper;

    @Autowired
    private FaceGroupDetailMapper faceGroupDetailMapper;

    @Autowired
    private ExtFaceGroupDetailMapper extFaceGroupDetailMapper;

    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;

    @Autowired
    private FaceGroupRecordMapper faceGroupRecordMapper;

    @Autowired
    private ImageFeatureClient imageFeatureClient;

    @Autowired
    private BasePersonAutoMapper basePersonAutoMapper;

    @Autowired
    private FaceOwnerInfoMapper faceOwnerInfoMapper;

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 获取人员布控库集合
     *
     * @auther: lixianhua
     * @date: 2020/4/8 10:12
     * @param:
     * @return:
     */
    @Override
    public List<ExtFaceGroup> selectGroupList(ExtFaceGroup faceGroup, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        if (StringUtils.isBlank(faceGroup.getOpUserId())) {
            List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
            faceGroup.setUserList(userList);
        }
        String type = request.getParameter("type");
        if (StringUtils.isBlank(type)) {// 1:获取下拉
            Paginator.pageHelper(request);
        }
        List<ExtFaceGroup> list = this.faceGroupMapper.selectList(faceGroup);
        return list;
    }

    /**
     * 功能描述: 添加人员布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/8 11:24
     * @param:
     * @return:
     */
    @Override
    public int insertRecord(FaceGroup faceGroup) throws OssRenderException {
        if (StringUtils.isBlank(faceGroup.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员布控库名称不能为空");
        }
        // 查看库名称是否存在
        ExtFaceGroup group = this.faceGroupMapper.getRecordByName(faceGroup.getName());
        if (null != group) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员布控库名称已经存在");
        }
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        faceGroup.setCode(UUID.randomUUID().toString().replace("-", ""));
        faceGroup.setOpTime(new Date());
        faceGroup.setOpUser(loginAppUser.getUsername());
        faceGroup.setOpUserId(loginAppUser.getUserId());
        int num = this.faceGroupMapper.insertRecord(faceGroup);
        return num;
    }

    /**
     * 功能描述: 更新人员布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/8 16:40
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGroup(FaceGroup faceGroup) throws OssRenderException {
        if (StringUtils.isBlank(faceGroup.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员布控库名称不能为空");
        }
        if (null == faceGroup.getId()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "主键不能为空");
        }
        FaceGroup group = this.faceGroupMapper.getRecordByName(faceGroup.getName());
        if (null != group && !group.getId().equals(faceGroup.getId())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该布控名已存在");
        }
        int num = this.faceGroupMapper.updateRecordById(faceGroup);
        if (num > 0) {
            FaceGroup face = this.faceGroupMapper.getRecordById(faceGroup.getId());
            FaceGroupDetailExample example = new FaceGroupDetailExample();
            example.createCriteria().andGroupCodeEqualTo(face.getCode());
            FaceGroupDetail detail = new FaceGroupDetail();
            detail.setGroupName(faceGroup.getName());
            // 更新该布控库下的布控人员
            this.faceGroupDetailMapper.updateByExampleSelective(detail, example);
        }
        return num;
    }

    /**
     * 功能描述: 获取人员布控集合
     *
     * @auther: lixianhua
     * @date: 2020/4/8 17:04
     * @param:
     * @return:
     */
    @Override
    public List<ExtFaceGroupDetail> selectPersonList(ExtFaceGroupDetail groupDetail, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        if (StringUtils.isBlank(groupDetail.getCreateUserId())) {
            List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
            groupDetail.setUserList(userList);
        }
        if (StringUtils.isNotBlank(groupDetail.getStartTimeStr())) {
            Date startTime = TimesUtil.stringToDate(groupDetail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            groupDetail.setStartTime(startTime);
        }
        if (StringUtils.isNotBlank(groupDetail.getEndTimeStr())) {
            Date endTime = TimesUtil.stringToDate(groupDetail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            groupDetail.setEndTime(endTime);
        }
        if (StringUtils.isNotBlank(groupDetail.getWithdrawTimeStr())) {
            Date withdrawTime = TimesUtil.stringToDate(groupDetail.getWithdrawTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            groupDetail.setWithdrawTime(withdrawTime);
        }
        if (StringUtils.isNotBlank(groupDetail.getWithdrawStartStr())) {
            Date withdrawStart = TimesUtil.stringToDate(groupDetail.getWithdrawStartStr(), TimesUtil.DATE_TIME_FORMAT);
            groupDetail.setWithdrawStart(withdrawStart);
        }
        if (StringUtils.isNotBlank(groupDetail.getWithdrawEndStr())) {
            Date withdrawEnd = TimesUtil.stringToDate(groupDetail.getWithdrawEndStr(), TimesUtil.DATE_TIME_FORMAT);
            groupDetail.setWithdrawEnd(withdrawEnd);
        }
        // 分页
        if (groupDetail.getPageFlag()) {
            Paginator.pageHelper(request);
            // 获取图片地址前缀
            groupDetail.setPersonImage(PropertiesUtil.getLocalTomcatImageIp());
        }
        List<ExtFaceGroupDetail> list = this.extFaceGroupDetailMapper.selectList(groupDetail);
        return list;
    }

    /**
     * 功能描述: 添加人员布控
     *
     * @auther: lixianhua
     * @date: 2020/4/9 13:59
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPerson(ExtFaceGroupDetail faceGroupDetail) throws OssRenderException, IOException {
        if (null == faceGroupDetail.getGroupCode()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "所属人员库不能为空");
        }
        if (null == faceGroupDetail.getLevel()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "布控等级不能为空");
        }
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        FaceGroupDetail model = new FaceGroupDetail();
        FaceGroup group = this.faceGroupMapper.getRecordByCode(faceGroupDetail.getGroupCode());
        BeanUtils.copyProperties(faceGroupDetail, model);
        model.setGroupName(group.getName());
        Date startTime = TimesUtil.stringToDate(faceGroupDetail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT);
        model.setStartTime(startTime);
        Date endTime = TimesUtil.stringToDate(faceGroupDetail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT);
        model.setEndTime(endTime);
        if (1 == faceGroupDetail.getLevel()) {
            model.setLevelName("高");
        } else if (2 == faceGroupDetail.getLevel()) {
            model.setLevelName("中");
        } else if (3 == faceGroupDetail.getLevel()) {
            model.setLevelName("低");
        }
        BasePerson basePerson = null;
        if (StringUtils.isBlank(faceGroupDetail.getPersonCode())) {
            // 图片保存到fastdfs中
            Map<String, Object> imageMap = imageToFdfsService.saveImageToFdfs(faceGroupDetail.getPersonImage());
            model.setPersonImage(imageMap.get("thumbFullPath").toString());
            model.setPersonCode(UUID.randomUUID().toString().replace("-", ""));
        } else {
            BasePersonExample basePersonExample = new BasePersonExample();
            basePersonExample.createCriteria().andCodeEqualTo(faceGroupDetail.getPersonCode());
            // 获取小区用户
            basePerson = this.basePersonAutoMapper.selectByExample(basePersonExample).get(0);
            model.setPersonName(basePerson.getName());
            model.setPersonPhone(basePerson.getPhone());
            model.setPersonPaperNumber(basePerson.getPaperNumber());
            model.setPersonCode(basePerson.getCode());
            model.setPersonImage(basePerson.getImage());
            // 获取用户特征
            FaceOwnerInfoExample faceOwnerInfoExample = new FaceOwnerInfoExample();
            faceOwnerInfoExample.createCriteria().andPersonCodeEqualTo(basePerson.getCode());
            List<FaceOwnerInfo> ownerInfos = this.faceOwnerInfoMapper.selectByExampleWithBLOBs(faceOwnerInfoExample);
            if (null == ownerInfos || ownerInfos.size() == 0) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员没有特征信息");
            }
            FaceOwnerInfo info = ownerInfos.get(0);
            model.setPersonFace(info.getPersonFace());
        }
        model.setCreateTime(new Date());
        model.setCreateUser(loginAppUser.getUsername());
        model.setCreateUserId(loginAppUser.getUserId());
        model.setStatus(false);
        model.setSource(false);
        int num = this.faceGroupDetailMapper.insertSelective(model);
        if (num == 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "添加布控人员失败");
        }
        // 添加布控记录
        FaceGroupRecord record = new FaceGroupRecord();
        record.setCreateTime(new Date());
        record.setCreateUser(loginAppUser.getUsername());
        record.setCreateUserId(loginAppUser.getUserId());
        record.setDetailId(model.getId());
        // 类型为布控
        record.setType(false);
        record.setStartTime(model.getStartTime());
        record.setEndTime(model.getEndTime());
        faceGroupRecordMapper.insertSelective(record);
        if (StringUtils.isBlank(faceGroupDetail.getPersonCode())) {
            Map<String, Object> map = new HashMap<>();
            map.put("face_id", model.getId());
            map.put("face_image", faceGroupDetail.getPersonImage());
            String feature = this.imageFeatureClient.getBaseImageFeature(map);
            System.out.println("********************返回人脸特征为:"+feature+"*********************************");
            String[] featureStr = feature.split(";");
            if ("1".equals(featureStr) || "0".equals(featureStr)) {// 0:没有正脸1：图片异常
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "人脸特征获取失败");
            }
            FaceGroupDetail updateEntity = new FaceGroupDetail();
            updateEntity.setId(model.getId());
            updateEntity.setPersonFace(featureStr[1]);
            // 根据ID更新人员布控信息
            int updateNum = this.faceGroupDetailMapper.updateByPrimaryKeySelective(updateEntity);
            if (updateNum == 0) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "人脸特征存入失败");
            }
        } else {
            // 更新人的face_group_detail_id
            basePerson.setFaceGroupDetailId(model.getId().toString());
            this.basePersonAutoMapper.updateByPrimaryKeySelective(basePerson);
        }
        return num;
    }

    /**
     * 功能描述: 根据id删除人员布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/14 15:05
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteGroupById(String id) throws OssRenderException {
        if (StringUtils.isBlank(id)) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员库ID为空");
        }
        FaceGroup faceGroup = this.faceGroupMapper.getRecordById(Integer.parseInt(id));
        int num = this.faceGroupMapper.deleteGroupById(Integer.parseInt(id));
        if (num > 0) {
            FaceGroupDetailExample detailExample = new FaceGroupDetailExample();
            detailExample.createCriteria().andGroupCodeEqualTo(faceGroup.getCode());
            // 删除所属该人员库的人
            this.faceGroupDetailMapper.deleteByExample(detailExample);
        }
        // 根据id获取布控记录
        return num;
    }

    /**
     * 功能描述: 根据ID删除人员布控信息
     *
     * @auther: lixianhua
     * @date: 2020/4/17 16:53
     * @param:
     * @return:
     */
    @Override
    public int deletePerson(String id) {
        int num = this.faceGroupDetailMapper.deleteByPrimaryKey(Integer.parseInt(id));
        if (num > 0) {
            // 调用算法
            String result = this.imageFeatureClient.deleteFeature(id);
            System.out.println(result);
        }
        return num;
    }

    /**
     * 功能描述: 根据ID更新人员布控信息
     *
     * @auther: lixianhua
     * @date: 2020/4/17 16:54
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePersonById(ExtFaceGroupDetail detail) throws OssRenderException, IOException {
        FaceGroupDetail entity = new FaceGroupDetail();
        BeanUtils.copyProperties(detail, entity);
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(loginAppUser.getUsername());
        entity.setUpdateUserId(loginAppUser.getUserId());
        FaceGroupRecord record = new FaceGroupRecord();
        record.setDetailId(detail.getId());
        record.setCreateTime(new Date());
        record.setCreateUserId(loginAppUser.getUserId());
        record.setCreateUser(loginAppUser.getUserId());
        if (detail.getStatus()) {
            // 撤控
            entity.setWithdrawTime(new Date());
            record.setWithdrawTime(new Date());
            record.setType(true);
            this.faceGroupRecordMapper.insert(record);
        } else if (!detail.getStatus()) {
            // 重新布控
            if (StringUtils.isBlank(detail.getGroupCode()) || null == detail.getLevel() || StringUtils.isBlank(detail.getStartTimeStr()) || StringUtils.isBlank(detail.getEndTimeStr())) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "必填项未填写");
            }
            if (1 == detail.getLevel()) {
                entity.setLevelName("高");
            } else if (2 == detail.getLevel()) {
                entity.setLevelName("中");
            } else if (3 == detail.getLevel()) {
                entity.setLevelName("低");
            }
            if (StringUtils.isNotBlank(detail.getPersonImage())) {
                // 图片保存到fastdfs中
                Map<String, Object> imageMap = imageToFdfsService.saveImageToFdfs(detail.getPersonImage());
                entity.setPersonImage(imageMap.get("thumbFullPath").toString());
                // 删除算法的人脸特征
              String result =  this.imageFeatureClient.deleteFeature(detail.getId().toString());
              if(!"1".equals(result)){
                  throw new OssRenderException(ReturnConstants.CODE_FAILED, "人脸特征获取更新失败");
              }
                Map<String, Object> map = new HashMap<>();
                map.put("face_id", detail.getId());
                map.put("face_image", detail.getPersonImage());
                // 图片传给算法
                String feature = this.imageFeatureClient.getBaseImageFeature(map);
                String[] featureStr = feature.split(";");
                if ("1".equals(featureStr) || "0".equals(featureStr)) {// 0:没有正脸1：图片异常
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "人脸特征获取失败");
                }
                entity.setPersonFace(featureStr[1]);
            }
            FaceGroup group = this.faceGroupMapper.getRecordByCode(detail.getGroupCode());
            entity.setGroupName(group.getName());
            Date startTime = TimesUtil.stringToDate(detail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            record.setStartTime(startTime);
            entity.setStartTime(startTime);
            Date endTime = TimesUtil.stringToDate(detail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            record.setEndTime(endTime);
            entity.setEndTime(endTime);
            record.setType(false);
            this.faceGroupRecordMapper.insert(record);
        }
        int num = this.faceGroupDetailMapper.updateByPrimaryKeySelective(entity);
        return num;
    }

    /**
     * 功能描述: 批量添加人员布控
     *
     * @auther: lixianhua
     * @date: 2020/5/14 15:30
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBatchRecord(List<ExtFaceGroupDetail> list) throws OssRenderException {
        int insertNum = 0;
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        for (ExtFaceGroupDetail faceGroupDetail : list) {
            byte[] picArr = faceGroupDetail.getPicData().getData();
            FaceGroupDetail detail = new FaceGroupDetail();
            BeanUtils.copyProperties(faceGroupDetail, detail);
            Map<String, Object> map = this.imageToFdfsService.uploadImage(picArr);
            detail.setPersonImage(map.get("thumbFullPath").toString());
            detail.setCreateUserId(loginAppUser.getUserId());
            detail.setCreateUser(loginAppUser.getUsername());
            detail.setCreateTime(new Date());
            detail.setStatus(false);
            detail.setPersonCode(Tools.getUUID());
            int num = this.faceGroupDetailMapper.insertSelective(detail);
            if (num > 0) {
                // 添加布控记录
                FaceGroupRecord record = new FaceGroupRecord();
                record.setCreateTime(new Date());
                record.setCreateUser(loginAppUser.getUsername());
                record.setCreateUserId(loginAppUser.getUserId());
                record.setDetailId(detail.getId());
                // 类型为布控
                record.setType(false);
                record.setStartTime(detail.getStartTime());
                record.setEndTime(detail.getEndTime());
                faceGroupRecordMapper.insertSelective(record);
                insertNum++;
                // 更新人脸特征
                String base64 = PicUtil.byte2Base64StringFun(picArr);
                Map<String, Object> faceMap = new HashMap<>();
                faceMap.put("face_id", detail.getId());
                faceMap.put("face_image", base64);
                String feature = this.imageFeatureClient.getBaseImageFeature(faceMap);
                String[] featureStr = feature.split(";");
                if ("1".equals(featureStr) || "0".equals(featureStr)) {// 0:没有正脸1：图片异常
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "人脸特征获取失败");
                }
                FaceGroupDetail updateEntity = new FaceGroupDetail();
                updateEntity.setId(detail.getId());
                updateEntity.setPersonFace(featureStr[1]);
                // 根据ID更新人员布控信息
                int updateNum = this.faceGroupDetailMapper.updateByPrimaryKeySelective(updateEntity);
                if (updateNum == 0) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "人脸特征存入失败");
                }
            }
        }
        return insertNum;
    }

}
