package com.windaka.suizhi.webapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.webapi.dao.CaptureAbnormalMapper;
import com.windaka.suizhi.webapi.dao.CaptureAlarmCarMapper;
import com.windaka.suizhi.webapi.dao.CaptureAlarmPersonMapper;
import com.windaka.suizhi.webapi.dao.ext.ExtCaptureAlarmAttentionMapper;
import com.windaka.suizhi.webapi.model.*;
import com.windaka.suizhi.webapi.model.ext.*;
import com.windaka.suizhi.webapi.service.AlarmService;
import com.windaka.suizhi.webapi.service.UserService;
import com.windaka.suizhi.webapi.util.Paginator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName AlarmServiceImpl
 * @Description 预警实现类
 * @Author lixianhua
 * @Date 2020/3/31 15:46
 * @Version 1.0
 */
@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private CaptureAlarmPersonMapper captureAlarmPersonMapper;

    @Autowired
    private CaptureAlarmCarMapper captureAlarmCarMapper;

    @Autowired
    private CaptureAbnormalMapper captureAbnormalMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ExtCaptureAlarmAttentionMapper extCaptureAlarmAttentionMapper;

    /**
     * 功能描述: 获取待处理预警数量
     *
     * @auther: lixianhua
     * @date: 2020/5/11 9:15
     * @param:
     * @return:
     */
    @Override
    public Map<String, Integer> getWaitCount() {
        Map<String, Integer> map = new HashMap<>();
        // 获取重点关注待处理数量
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());

        List<HtUserXq> xqList = this.userService.getUserXqByUserId(loginAppUser.getUserId());
        ExtCaptureAlarmAttention attention = new ExtCaptureAlarmAttention();
        // 未处理
        attention.setHandelStatus(true);
        attention.setUserList(userList);
        // 获取重点关注待处理数量
        Integer attentionNum = this.extCaptureAlarmAttentionMapper.countAttentionList(attention);
        map.put("attention", attentionNum);
        ExtCaptureAlarmPerson person = new ExtCaptureAlarmPerson();
        person.setUserList(userList);
        person.setHandelStatus(new Byte("1"));
        // 获取人员布控待处理数量
        Integer personNum = this.captureAlarmPersonMapper.countPersonList(person);
        map.put("person", personNum);
        ExtCaptureAlarmCar car = new ExtCaptureAlarmCar();
        car.setUserList(userList);
        car.setHandelStatus(new Byte("1"));
        // 获取车辆布控待处理数量
        Integer carNum = this.captureAlarmCarMapper.countCarList(car);
        map.put("car", carNum);
        // 根据userId获取小区集合
        ExtCaptureAbnormal abnormal = new ExtCaptureAbnormal();
        abnormal.setXqList(xqList);
        abnormal.setHandelStatus(new Byte("1"));
        // 获取异常行为待处理数量
        Integer abnormalNum = this.captureAbnormalMapper.countAbnormal(abnormal);
        map.put("abnormal", abnormalNum);
        return map;
    }

    /**
     * 功能描述: 警务通 获取重点关注预警集合
     *
     * @auther: lixianhua
     * @date: 2020/5/12 15:01
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAlarmAttention> selectAppAttentionList(HttpServletRequest request, ExtCaptureAlarmAttention attention) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
        attention.setUserList(userList);
        attention.setPicUrl(PropertiesUtil.getLocalTomcatImageIp());
        if (attention.getHandelStatus()) {// 未处理
            attention.setOrderByClause("level asc,alarm_time desc");
        } else {// 已处理
            attention.setOrderByClause("handel_time desc");
        }
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAlarmAttention> list = this.extCaptureAlarmAttentionMapper.selectAPPAttentionList(attention);
        return list;
    }

    /**
     * 功能描述: 警务通 获取人员预警集合
     *
     * @auther: lixianhua
     * @date: 2020/5/12 18:17
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAlarmPerson> selectAppPersonList(HttpServletRequest request, ExtCaptureAlarmPerson person) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
        person.setUserList(userList);
        // 获取图片地址前缀
        person.setCapImage(PropertiesUtil.getLocalTomcatImageIp());
        if (new Byte("1").equals(person.getHandelStatus())) {// 未处理
            person.setOrderByClause("detail_level asc ,capture_time desc");
        } else {// 已处理
            person.setOrderByClause("handel_time desc");
        }
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAlarmPerson> list = this.captureAlarmPersonMapper.selectAppPersonAlarmList(person);
        return list;
    }

    /**
     * 功能描述: 警务通 获取车辆预警集合
     *
     * @auther: lixianhua
     * @date: 2020/5/12 18:36
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAlarmCar> selectAppCarList(HttpServletRequest request, ExtCaptureAlarmCar car) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
        car.setUserList(userList);
        // 获取图片地址前缀
        car.setCapImage(PropertiesUtil.getLocalTomcatImageIp());
        if(new Byte("1").equals(car.getHandelStatus())){// 未处理
            car.setOrderByClause("detail_level asc ,capture_time desc");
        }else{// 已处理
            car.setOrderByClause("handel_time desc");
        }
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAlarmCar> list = this.captureAlarmCarMapper.selectAppCarAlarmList(car);
        return list;
    }

    /**
     * 功能描述: 警务通 获取异常事件集合
     *
     * @auther: lixianhua
     * @date: 2020/5/12 19:02
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAbnormal> selectAppAbnormalList(HttpServletRequest request, ExtCaptureAbnormal abnormal) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        // 获取用户下的所有小区
        List<HtUserXq> xqList = this.userService.getUserXqByUserId(loginAppUser.getUserId());
        // 获取图片地址前缀
        abnormal.setCapImage(PropertiesUtil.getLocalTomcatImageIp());
        abnormal.setXqList(xqList);
        if(new Byte("1").equals(abnormal.getHandelStatus())){// 未处理
            abnormal.setOrderByClause("m.level asc ,capture_time desc");
        }else{// 已处理
            abnormal.setOrderByClause("handel_time desc");
        }
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAbnormal> list = this.captureAbnormalMapper.selectAppAbnormalList(abnormal);
        return list;
    }

    /**
     * 功能描述: 首页警告列表
     *
     * @auther: lixianhua
     * @date: 2020/4/10 18:06
     * @param:
     * @return:
     */
    @Override
    public List<ExtHomeAlarmModel> selectHomeList() {
        ExtHomeAlarmModel model = new ExtHomeAlarmModel();
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
        model.setUserList(userList);
        model.setImg(PropertiesUtil.getLocalTomcatImageIp());
        // 获取用户下的所有小区
        List<HtUserXq> xqList = this.userService.getUserXqByUserId(loginAppUser.getUserId());
        model.setXqList(xqList);
        List<ExtHomeAlarmModel> list = this.captureAlarmPersonMapper.getHomeList(model);
        return list;
    }

    /**
     * 功能描述: 获取操作人员下拉列表
     *
     * @auther: lixianhua
     * @date: 2020/4/28 11:36
     * @param:
     * @return:
     */
    @Override
    public List<HtUser> getOperateList(String type) throws OssRenderException {
        if (StringUtils.isBlank(type)) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "报警类型不能为空");
        }
        ExtAlarmModel model = new ExtAlarmModel();
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> list = new ArrayList<>();
        if ("4".equals(type)) {// 异常事件
            List<HtUserXq> xqList = this.userService.getUserXqByUserId(loginAppUser.getUserId());
            model.setXqList(xqList);
            list = this.extCaptureAlarmAttentionMapper.getUserListByXq(model);
        } else {
            List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
            model.setUserList(userList);
            if ("1".equals(type)) {// 人员
                model.setTableName("capture_alarm_person");
            } else if ("2".equals(type)) {// 车辆
                model.setTableName("capture_alarm_car");
            } else if ("3".equals(type)) {// 重点关注
                model.setTableName("capture_alarm_attention");
            }
            list = this.extCaptureAlarmAttentionMapper.getUserListByType(model);
        }
        return list;
    }

    /**
     * 功能描述: 获取人员预警集合
     *
     * @auther: lixianhua
     * @date: 2020/3/31 16:05
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAlarmPerson> selectPersonList(ExtCaptureAlarmPerson person, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
        person.setUserList(userList);
        // 获取图片地址前缀
        person.setCapImage(PropertiesUtil.getLocalTomcatImageIp());
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAlarmPerson> list = this.captureAlarmPersonMapper.selectPersonAlarmList(person);
        return list;
    }

    /**
     * 功能描述: 获取车辆预警集合
     *
     * @auther: lixianhua
     * @date: 2020/4/1 9:33
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAlarmCar> selectCarList(ExtCaptureAlarmCar car, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
        car.setUserList(userList);
        // 获取图片地址前缀
        car.setCapImage(PropertiesUtil.getLocalTomcatImageIp());
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAlarmCar> list = this.captureAlarmCarMapper.selectCarAlarmList(car);
        return list;
    }

    /**
     * 功能描述: 获取异常预警集合
     *
     * @auther: lixianhua
     * @date: 2020/4/1 10:19
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAbnormal> selectAbnormalList(ExtCaptureAbnormal abnormal, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        // 获取用户下的所有小区
        List<HtUserXq> xqList = this.userService.getUserXqByUserId(loginAppUser.getUserId());
        // 获取图片地址前缀
        abnormal.setCapImage(PropertiesUtil.getLocalTomcatImageIp());
        abnormal.setXqList(xqList);
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAbnormal> list = this.captureAbnormalMapper.selectAbnormalList(abnormal);
        return list;
    }

    /**
     * 功能描述: 获取重点关注预警信息
     *
     * @auther: lixianhua
     * @date: 2020/4/28 10:13
     * @param:
     * @return:
     */
    @Override
    public List<ExtCaptureAlarmAttention> selectAttentionList(ExtCaptureAlarmAttention attention, HttpServletRequest request) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        List<HtUser> userList = this.userService.getUserListByUserId(loginAppUser.getUserId());
        attention.setUserList(userList);
        attention.setPicUrl(PropertiesUtil.getLocalTomcatImageIp());
        HashMap<String, Integer> pageNumSizeMap = Paginator.getPageNumPageSize(request);
        PageHelper.startPage(pageNumSizeMap.get("pageNum"), pageNumSizeMap.get("pageSize"));
        List<ExtCaptureAlarmAttention> list = this.extCaptureAlarmAttentionMapper.selectAttentionList(attention);
        return list;
    }


    /**
     * 功能描述: 根据ID获取人员预警
     *
     * @auther: lixianhua
     * @date: 2020/4/1 10:46
     * @param:
     * @return:
     */
    @Override
    public CaptureAlarmPerson selectPersonById(Integer id) throws OssRenderException {
        if (null == id) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "主键不能为空");
        }
        CaptureAlarmPerson result = this.captureAlarmPersonMapper.getPersonById(id);
        return result;
    }

    /**
     * 功能描述: 根据ID获取车辆预警
     *
     * @auther: lixianhua
     * @date: 2020/4/1 10:46
     * @param:
     * @return:
     */
    @Override
    public CaptureAlarmCar selectCarById(Integer id) throws OssRenderException {
        if (null == id) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "主键不能为空");
        }
        Map<String, Object> condition = new HashMap<>(8);
        condition.put("id", id);
        condition.put("prefixUrl", PropertiesUtil.getLocalTomcatImageIp());
        CaptureAlarmCar result = this.captureAlarmCarMapper.getCarById(id);
        return result;
    }

    /**
     * 功能描述: 根据ID获取异常预警
     *
     * @auther: lixianhua
     * @date: 2020/4/1 10:46
     * @param:
     * @return:
     */
    @Override
    public CaptureAbnormal selectAbnormalById(Integer id) throws OssRenderException {
        if (null == id) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "主键不能为空");
        }
        CaptureAbnormal result = this.captureAbnormalMapper.getAbnormalById(id);
        return result;
    }

    @Override
    public boolean handleAlarm(String alarmType, Integer id, String content) throws OssRenderException {
        if (StringUtils.isBlank(alarmType)) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "报警类型不能为空");
        }
        if (null == id) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "报警主键不能为空");
        }
        if (StringUtils.isBlank(content) || StringUtils.isBlank(content.trim())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "报警内容不能为空");
        }
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        int updateNum = 0;
        // 人员报警
        if ("1".equals(alarmType)) {
            CaptureAlarmPerson person = new CaptureAlarmPerson();
            person.setId(id);
            person.setHandelStatus((byte) 0);
            person.setHandelTime(new Date());
            person.setHandelConn(content);
            person.setHandelUser(loginAppUser.getUsername());
            person.setHandelUserId(loginAppUser.getUserId());
            updateNum = this.captureAlarmPersonMapper.updatePersonAlarmById(person);
            // 车辆报警
        } else if ("2".equals(alarmType)) {
            CaptureAlarmCar car = new CaptureAlarmCar();
            car.setId(id);
            car.setHandelStatus((byte) 0);
            car.setHandelTime(new Date());
            car.setHandelConn(content);
            car.setHandelUser(loginAppUser.getUsername());
            car.setHandelUserId(loginAppUser.getUserId());
            updateNum = this.captureAlarmCarMapper.updateCarAlarmById(car);
            // 事件报警
        } else if ("3".equals(alarmType)) {
            CaptureAbnormal abnormal = new CaptureAbnormal();
            abnormal.setId(id);
            abnormal.setHandelStatus((byte) 0);
            abnormal.setHandelTime(new Date());
            abnormal.setHandelConn(content);
            abnormal.setHandelUser(loginAppUser.getUsername());
            abnormal.setHandelUserId(loginAppUser.getUserId());
            updateNum = this.captureAbnormalMapper.updateAbnormalById(abnormal);
        } else if ("4".equals(alarmType)) {
            CaptureAlarmAttention attention = new CaptureAlarmAttention();
            attention.setId(id);
            attention.setHandelStatus(false);
            attention.setHandelTime(new Date());
            attention.setHandelConn(content);
            attention.setHandelUser(loginAppUser.getUsername());
            attention.setHandelUserId(loginAppUser.getUserId());
            updateNum = this.extCaptureAlarmAttentionMapper.updateAttentionById(attention);
        }
        if (updateNum == 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "处理失败");
        }
        return updateNum > 0 ? true : false;
    }


}
