package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.dao.auto.BasePersonAutoMapper;
import com.windaka.suizhi.webapi.dao.auto.FaceAttentionDetailMapper;
import com.windaka.suizhi.webapi.dao.auto.FaceAttentionRecordMapper;
import com.windaka.suizhi.webapi.dao.auto.SysDicDataAutoMapper;
import com.windaka.suizhi.webapi.dao.ext.ExtFaceAttentionMapper;
import com.windaka.suizhi.webapi.model.*;
import com.windaka.suizhi.webapi.model.ext.ExtFaceAttentionDetail;
import com.windaka.suizhi.webapi.service.FaceAttentionService;
import com.windaka.suizhi.webapi.service.UserService;
import com.windaka.suizhi.webapi.util.Paginator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FaceAttentionServiceImpl
 * @Description 重点人员接口实现类
 * @Author lixianhua
 * @Date 2020/4/18 11:17
 * @Version 1.0
 */
@Service
public class FaceAttentionServiceImpl implements FaceAttentionService {

    @Autowired
    private FaceAttentionDetailMapper faceAttentionDetailMapper;

    @Autowired
    private FaceAttentionRecordMapper faceAttentionRecordMapper;

    @Autowired
    private SysDicDataAutoMapper sysDicDataAutoMapper;

    @Autowired
    private BasePersonAutoMapper basePersonAutoMapper;

    @Autowired
    private ExtFaceAttentionMapper extFaceAttentionMapper;

    @Autowired
    private UserService userService;


    /**
     * 功能描述: 获取重点关注人员集合
     *
     * @auther: lixianhua
     * @date: 2020/4/20 16:36
     * @param:
     * @return:
     */
    @Override
    public List<ExtFaceAttentionDetail> selectPersonList(ExtFaceAttentionDetail attentionDetail, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        if (StringUtils.isBlank(attentionDetail.getCreateUserId())) {
            List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
            attentionDetail.setUserList(userList);
        }
        if (StringUtils.isNotBlank(attentionDetail.getComeInStartStr())) {
            attentionDetail.setComeInStart(TimesUtil.stringToDate(attentionDetail.getComeInStartStr(), TimesUtil.DATE_TIME_FORMAT));
        }
        if (StringUtils.isNotBlank(attentionDetail.getComeInEndStr())) {
            attentionDetail.setComeInEnd(TimesUtil.stringToDate(attentionDetail.getComeInEndStr(), TimesUtil.DATE_TIME_FORMAT));
        }
        if (StringUtils.isNotBlank(attentionDetail.getStartTimeStr())) {
            attentionDetail.setStartTime(TimesUtil.stringToDate(attentionDetail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT));
        }
        if (StringUtils.isNotBlank(attentionDetail.getEndTimeStr())) {
            attentionDetail.setEndTime(TimesUtil.stringToDate(attentionDetail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT));
        }
        // 分页
        if(attentionDetail.getPageFlag()){
            Paginator.pageHelper(request);
            attentionDetail.setImgUrl(PropertiesUtil.getLocalTomcatImageIp());
        }
        List<ExtFaceAttentionDetail> list = this.extFaceAttentionMapper.selectPersonList(attentionDetail);
        return list;
    }

    /**
     * 功能描述: 添加重点关注人员
     *
     * @auther: lixianhua
     * @date: 2020/4/18 11:31
     * @param:
     * @return:
     */
    @Override
    public int insertPerson(ExtFaceAttentionDetail attentionDetail) throws OssRenderException {
        if (null == attentionDetail.getStatus() || StringUtils.isBlank(attentionDetail.getPersonCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "存在未填写项");
        }
        // 查看是否被布控
        FaceAttentionDetailExample attentionDetailExample = new FaceAttentionDetailExample();
        attentionDetailExample.createCriteria().andPersonCodeEqualTo(attentionDetail.getPersonCode());
        List<FaceAttentionDetail> details = this.faceAttentionDetailMapper.selectByExample(attentionDetailExample);
        if (null != details && details.size() > 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该用户已布控");
        }
        FaceAttentionDetail attention = new FaceAttentionDetail();
        BeanUtils.copyProperties(attentionDetail, attention);
        // 根据人员code获取人员信息
        BasePersonExample baseExample = new BasePersonExample();
        baseExample.createCriteria().andCodeEqualTo(attentionDetail.getPersonCode());
        BasePerson basePerson = this.basePersonAutoMapper.selectByExample(baseExample).get(0);
        if (null == basePerson) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员信息异常");
        }
        attention.setCommunityCode(basePerson.getCommunityCode());
        attention.setCommunityName(basePerson.getCommunityName());
        attention.setPersonCode(basePerson.getCode());
        attention.setPersonName(basePerson.getName());
        attention.setPhone(basePerson.getPhone());
        attention.setIdNo(basePerson.getPaperNumber());
        attention.setImgUrl(basePerson.getImage());
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        FaceAttentionRecord record = new FaceAttentionRecord();
        record.setCreateTime(new Date());
        record.setCreateUser(loginAppUser.getUsername());
        record.setCreateUserId(loginAppUser.getUserId());
        if (attentionDetail.getStatus()) {// 未布控
            record.setType(true);
            attention.setStatus(true);
        } else {// 布控
            if (StringUtils.isBlank(attentionDetail.getLevel()) || StringUtils.isBlank(attentionDetail.getStartTimeStr()) || StringUtils.isBlank(attentionDetail.getEndTimeStr())) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "存在必填项未填写");
            }
            Date startTime = TimesUtil.stringToDate(attentionDetail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            attention.setStartTime(startTime);
            attention.setCaptureTime(startTime);
            record.setStartTime(startTime);
            Date endTime = TimesUtil.stringToDate(attentionDetail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            attention.setEndTime(endTime);
            record.setEndTime(endTime);
            if ("1".equals(attentionDetail.getLevel())) {
                attention.setLevelName("高");
            } else if ("2".equals(attentionDetail.getLevel())) {
                attention.setLevelName("中");
            } else if ("3".equals(attentionDetail.getLevel())) {
                attention.setLevelName("低");
            }
            record.setType(false);
            attention.setStatus(false);
        }
        SysDicDataExample example = new SysDicDataExample();
        SysDicDataExample.Criteria criteria = example.createCriteria();
        criteria.andDicKeyEqualTo(Short.valueOf(attentionDetail.getAttentionCode()));
        criteria.andDicCodeEqualTo("attention_type");
        // 获取布控名称
        List<SysDicData> dicList = sysDicDataAutoMapper.selectByExample(example);
        attention.setAttentionName(dicList.get(0).getDicValue());
        attention.setCreateTime(new Date());
        attention.setCreateUser(loginAppUser.getUsername());
        attention.setCreateUserId(loginAppUser.getUserId());
        int num = this.faceAttentionDetailMapper.insertSelective(attention);
        if (num > 0) {
            record.setDetailId(attention.getId());
            // 添加布控操作记录
            this.faceAttentionRecordMapper.insertSelective(record);
        }
        return num;
    }

    /**
     * 功能描述: 批量添加重点关注人员
     *
     * @auther: lixianhua
     * @date: 2020/4/27 10:34
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBatchPerson(ExtFaceAttentionDetail attentionDetail) throws OssRenderException {
        if (null == attentionDetail.getCodeList() || attentionDetail.getCodeList().size() == 0 || StringUtils.isBlank(attentionDetail.getAttentionCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "请求参数异常");
        }
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        SysDicDataExample example = new SysDicDataExample();
        SysDicDataExample.Criteria criteria = example.createCriteria();
        criteria.andDicKeyEqualTo(Short.valueOf(attentionDetail.getAttentionCode()));
        criteria.andDicCodeEqualTo("attention_type");
        // 获取布控名称
        SysDicData dic = sysDicDataAutoMapper.selectByExample(example).get(0);
        for (String code : attentionDetail.getCodeList()) {
            // 查看是否被布控
            FaceAttentionDetailExample attentionDetailExample = new FaceAttentionDetailExample();
            attentionDetailExample.createCriteria().andPersonCodeEqualTo(code);
            List<FaceAttentionDetail> details = this.faceAttentionDetailMapper.selectByExample(attentionDetailExample);
            if (null != details && details.size() > 0) {// 查看该用户是否已被布控
                continue;
            }
            // 根据人员code获取人员信息
            BasePersonExample baseExample = new BasePersonExample();
            baseExample.createCriteria().andCodeEqualTo(code);
            BasePerson basePerson = this.basePersonAutoMapper.selectByExample(baseExample).get(0);
            if (null == basePerson) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员信息异常");
            }
            FaceAttentionDetail attention = new FaceAttentionDetail();
            attention.setAttentionCode(attentionDetail.getAttentionCode());
            attention.setCommunityCode(basePerson.getCommunityCode());
            attention.setCommunityName(basePerson.getCommunityName());
            attention.setPersonCode(basePerson.getCode());
            attention.setPersonName(basePerson.getName());
            attention.setPhone(basePerson.getPhone());
            attention.setIdNo(basePerson.getPaperNumber());
            attention.setImgUrl(basePerson.getImage());
            attention.setStatus(true);
            attention.setAttentionName(dic.getDicValue());
            attention.setCreateTime(new Date());
            attention.setCreateUser(loginAppUser.getUsername());
            attention.setCreateUserId(loginAppUser.getUserId());
            this.faceAttentionDetailMapper.insertSelective(attention);
        }
        return  attentionDetail.getCodeList().size();
    }

    /**
     * 功能描述: 根据ID删除重点关注人员
     *
     * @auther: lixianhua
     * @date: 2020/4/18 11:31
     * @param:
     * @return:
     */
    @Override
    public int deletePerson(String id) {
        int num = this.faceAttentionDetailMapper.deleteByPrimaryKey(Integer.parseInt(id));
        return num;
    }

    /**
     * 功能描述: 根据ID更新重点关注人员
     *
     * @auther: lixianhua
     * @date: 2020/4/18 11:32
     * @param:
     * @return:
     */
    @Override
    public int updatePersonById(ExtFaceAttentionDetail detail) {
        FaceAttentionDetail attention = new FaceAttentionDetail();
        BeanUtils.copyProperties(detail, attention);
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        attention.setUpdateTime(new Date());
        attention.setUpdateUser(loginAppUser.getUsername());
        attention.setUpdateUserId(loginAppUser.getUserId());
        FaceAttentionRecord record = new FaceAttentionRecord();
        record.setCreateTime(new Date());
        record.setCreateUser(loginAppUser.getUsername());
        record.setCreateUserId(loginAppUser.getUserId());
        if (detail.getStatus()) {// 撤控
            record.setType(true);
            record.setWithdrawTime(new Date());
            attention.setWithdrawTime(new Date());
        } else {// 布控
            record.setType(false);
            Date startTime = TimesUtil.stringToDate(detail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            // 布控开始时间和最后一次抓拍时间一样
            attention.setStartTime(startTime);
            attention.setCaptureTime(startTime);
            record.setStartTime(startTime);
            Date endTime = TimesUtil.stringToDate(detail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT);
            attention.setEndTime(endTime);
            record.setEndTime(endTime);
            if ("1".equals(detail.getLevel())) {
                attention.setLevelName("高");
            } else if ("2".equals(detail.getLevel())) {
                attention.setLevelName("中");
            } else if ("3".equals(detail.getLevel())) {
                attention.setLevelName("低");
            }
        }
        int num = this.faceAttentionDetailMapper.updateByPrimaryKeySelective(attention);
        if (num > 0) {
            // 添加布控操作记录
            this.faceAttentionRecordMapper.insertSelective(record);
        }
        return num;
    }

}
