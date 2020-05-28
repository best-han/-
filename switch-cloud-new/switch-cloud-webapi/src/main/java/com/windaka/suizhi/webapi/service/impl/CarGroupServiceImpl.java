package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.dao.CarGroupMapper;
import com.windaka.suizhi.webapi.dao.auto.CarGroupDetailMapper;
import com.windaka.suizhi.webapi.dao.auto.CarGroupRecordMapper;
import com.windaka.suizhi.webapi.dao.ext.ExtCarGroupDetailMapper;
import com.windaka.suizhi.webapi.model.*;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroup;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroupDetail;
import com.windaka.suizhi.webapi.service.CarGroupService;
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
import java.util.UUID;

/**
 * @ClassName CarGroupServiceImpl
 * @Description 车辆布控实现类
 * @Author lixianhua
 * @Date 2020/4/8 10:01
 * @Version 1.0
 */
@Service
public class CarGroupServiceImpl implements CarGroupService {

    @Autowired
    private CarGroupMapper carGroupMapper;

    @Autowired
    private CarGroupDetailMapper carGroupDetailMapper;

    @Autowired
    private CarGroupRecordMapper carGroupRecordMapper;

    @Autowired
    private ExtCarGroupDetailMapper extCarGroupDetailMapper;

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 获取车辆库列表
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:18
     * @param:
     * @return:
     */
    @Override
    public List<ExtCarGroup> selectGroupList(ExtCarGroup carGroup, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        if (StringUtils.isBlank(carGroup.getOpUserId())) {
            List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
            carGroup.setUserList(userList);
        }
        if (StringUtils.isBlank(request.getParameter("type"))) {
            // 分页
            Paginator.pageHelper(request);
        }
        List<ExtCarGroup> list = this.carGroupMapper.selectList(carGroup);
        return list;
    }

    /**
     * 功能描述: 添加车辆库
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:19
     * @param:
     * @return:
     */
    @Override
    public int insertRecord(CarGroup carGroup) throws OssRenderException {
        if (StringUtils.isBlank(carGroup.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "车辆库不能为空");
        }
        ExtCarGroup group = this.carGroupMapper.getRecordByName(carGroup.getName());
        if (null != group) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "车辆库名称已存在");
        }
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        carGroup.setCode(UUID.randomUUID().toString().replace("-", ""));
        carGroup.setOpTime(new Date());
        carGroup.setOpUser(loginAppUser.getUsername());
        carGroup.setOpUserId(loginAppUser.getUserId());
        int num = this.carGroupMapper.insertRecord(carGroup);
        return num;
    }

    /**
     * 功能描述: 根据ID更新车辆库
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:19
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGroup(CarGroup carGroup) throws OssRenderException {
        if (null == carGroup.getId()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "主键不能为空");
        }
        if (StringUtils.isBlank(carGroup.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "车辆库不能为空");
        }
        CarGroup group = this.carGroupMapper.getRecordByName(carGroup.getName());
        if (group != null && !group.getId().equals(carGroup.getId())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该车辆库名称已存在");
        }
        int num = this.carGroupMapper.updateRecordById(carGroup);
        if (num > 0) {// 更新成功
            CarGroup car = this.carGroupMapper.getRecordById(carGroup.getId());
            CarGroupDetailExample example = new CarGroupDetailExample();
            example.createCriteria().andGroupCodeEqualTo(car.getCode());
            CarGroupDetail detail = new CarGroupDetail();
            detail.setGroupName(carGroup.getName());
            // 更新该库下面的布控车辆
            this.carGroupDetailMapper.updateByExampleSelective(detail, example);
        }
        return num;
    }

    /**
     * 功能描述: 根据ID删除车辆库
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:19
     * @param:
     * @return:
     */
    @Override
    public int deleteGroupById(String id) {
        // 根据ID获取车辆库
        CarGroup carGroup = this.carGroupMapper.getRecordById(Integer.parseInt(id));
        int num = this.carGroupMapper.deleteGroupById(Integer.parseInt(id));
        if (num > 0) {
            // 删除车辆布控库关联车辆
            CarGroupDetailExample example = new CarGroupDetailExample();
            example.createCriteria().andGroupCodeEqualTo(carGroup.getCode());
            this.carGroupDetailMapper.deleteByExample(example);
        }
        return num;

    }

    /**
     * 功能描述: 获取车辆布控集合
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:20
     * @param:
     * @return:
     */
    @Override
    public List<ExtCarGroupDetail> selectCarList(ExtCarGroupDetail groupDetail, HttpServletRequest request) {
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
            groupDetail.setCarImage(PropertiesUtil.getLocalTomcatImageIp());
        }
        List<ExtCarGroupDetail> list = this.extCarGroupDetailMapper.selectList(groupDetail);
        return list;
    }

    /**
     * 功能描述: 添加车辆布控
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:21
     * @param:
     * @return:
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCar(ExtCarGroupDetail carGroupDetail) throws OssRenderException {
        if (StringUtils.isBlank(carGroupDetail.getCarNum()) || StringUtils.isBlank(carGroupDetail.getGroupCode()) || null == carGroupDetail.getLevel() || StringUtils.isBlank(carGroupDetail.getStartTimeStr()) || StringUtils.isBlank(carGroupDetail.getEndTimeStr())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "存在必填项未填写");
        }
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        CarGroupDetail model = new CarGroupDetail();
        BeanUtils.copyProperties(carGroupDetail, model);
        CarGroup carGroup = this.carGroupMapper.getRecordByCode(carGroupDetail.getGroupCode());
        model.setGroupName(carGroup.getName());
        Date startTime = TimesUtil.stringToDate(carGroupDetail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT);
        model.setStartTime(startTime);
        Date endTime = TimesUtil.stringToDate(carGroupDetail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT);
        model.setEndTime(endTime);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setStatus(false);
        if (1 == carGroupDetail.getLevel()) {
            model.setLevelName("高");
        } else if (2 == carGroupDetail.getLevel()) {
            model.setLevelName("中");
        } else if (3 == carGroupDetail.getLevel()) {
            model.setLevelName("低");
        }
        model.setCreateUser(loginAppUser.getUsername());
        model.setCreateUserId(loginAppUser.getUserId());
        model.setUpdateUser(loginAppUser.getUsername());
        model.setUpdateUserId(loginAppUser.getUserId());
        model.setUpdateTime(new Date());
        int num = this.carGroupDetailMapper.insertSelective(model);
        if (num > 0) {
            CarGroupRecord record = new CarGroupRecord();
            record.setStartTime(model.getStartTime());
            record.setEndTime(model.getEndTime());
            record.setCreateTime(new Date());
            record.setCreateUser(loginAppUser.getUsername());
            record.setCreateUser(loginAppUser.getUserId());
            record.setDetailId(model.getId());
            record.setType(false);
            this.carGroupRecordMapper.insert(record);
        }
        return num;
    }

    /**
     * 功能描述: 根据ID删除车辆布控信息
     *
     * @auther: lixianhua
     * @date: 2020/4/16 10:18
     * @param:
     * @return:
     */
    @Override
    public int deleteCar(String id) {
        int num = this.carGroupDetailMapper.deleteByPrimaryKey(Integer.parseInt(id));
        return num;
    }

    /**
     * 功能描述: 根据ID更新布控车辆
     *
     * @auther: lixianhua
     * @date: 2020/4/16 10:56
     * @param:
     * @return:
     */
    @Override
    public int updateCarById(ExtCarGroupDetail detail) throws OssRenderException {
        CarGroupDetail entity = new CarGroupDetail();
        BeanUtils.copyProperties(detail, entity);
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(loginAppUser.getUsername());
        entity.setUpdateUserId(loginAppUser.getUserId());
        if (null != detail.getStatus()) {
            CarGroupRecord record = new CarGroupRecord();
            record.setDetailId(detail.getId());
            record.setCreateTime(new Date());
            if (detail.getStatus()) {
                // 撤控
                entity.setWithdrawTime(new Date());
                record.setType(true);
                this.carGroupRecordMapper.insert(record);
            } else {
                if (StringUtils.isBlank(detail.getGroupCode()) || null == detail.getLevel() || StringUtils.isBlank(detail.getStartTimeStr()) || StringUtils.isBlank(detail.getEndTimeStr())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "存在必填项未填写");
                }
                // 重新布控
                if (1 == detail.getLevel()) {
                    entity.setLevelName("高");
                } else if (2 == detail.getLevel()) {
                    entity.setLevelName("中");
                } else if (3 == detail.getLevel()) {
                    entity.setLevelName("低");
                }
                CarGroup carGroup = this.carGroupMapper.getRecordByCode(detail.getGroupCode());
                entity.setGroupName(carGroup.getName());
                Date startTime = TimesUtil.stringToDate(detail.getStartTimeStr(), TimesUtil.DATE_TIME_FORMAT);
                record.setStartTime(startTime);
                entity.setStartTime(startTime);
                Date endTime = TimesUtil.stringToDate(detail.getEndTimeStr(), TimesUtil.DATE_TIME_FORMAT);
                record.setEndTime(endTime);
                entity.setEndTime(endTime);
                record.setType(false);
                this.carGroupRecordMapper.insert(record);
            }
        }
        int num = this.carGroupDetailMapper.updateByPrimaryKeySelective(entity);
        return num;
    }

    /**
     * 功能描述: 批量添加车辆布控信息
     *
     * @auther: lixianhua
     * @date: 2020/5/14 16:43
     * @param:
     * @return:
     */
    @Override
    public int insertBatchRecord(List<CarGroupDetail> list) {
        int insertNum = 0;
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        for (CarGroupDetail detail : list) {
            detail.setCreateTime(new Date());
            detail.setCreateUserId(loginAppUser.getUserId());
            detail.setCreateUser(loginAppUser.getUsername());
            detail.setStatus(false);
            int num = this.carGroupDetailMapper.insertSelective(detail);
            if(num>0){
                insertNum++;
                CarGroupRecord record = new CarGroupRecord();
                record.setStartTime(detail.getStartTime());
                record.setEndTime(detail.getEndTime());
                record.setCreateTime(new Date());
                record.setCreateUser(loginAppUser.getUsername());
                record.setCreateUser(loginAppUser.getUserId());
                record.setDetailId(detail.getId());
                // 状态为布控
                record.setType(false);
                this.carGroupRecordMapper.insert(record);
            }
        }
        return insertNum;
    }
}
