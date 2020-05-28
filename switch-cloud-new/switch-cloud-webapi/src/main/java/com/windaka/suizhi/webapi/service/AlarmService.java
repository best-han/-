package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.model.CaptureAbnormal;
import com.windaka.suizhi.webapi.model.CaptureAlarmCar;
import com.windaka.suizhi.webapi.model.CaptureAlarmPerson;
import com.windaka.suizhi.webapi.model.HtUser;
import com.windaka.suizhi.webapi.model.ext.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName AlarmService
 * @Description 预警接口
 * @Author lixianhua
 * @Date 2020/3/31 15:46
 * @Version 1.0
 */
public interface AlarmService {
    /**
     * 功能描述: 获取人员预警集合
     * @auther: lixianhua
     * @date: 2020/3/31 15:57
     * @param:
     * @return:
     */
    List<ExtCaptureAlarmPerson> selectPersonList(ExtCaptureAlarmPerson person, HttpServletRequest request);
    /**
     * 功能描述: 获取车辆预警集合
     * @auther: lixianhua
     * @date: 2020/4/1 9:33
     * @param:
     * @return:
     */
    List<ExtCaptureAlarmCar> selectCarList(ExtCaptureAlarmCar car,HttpServletRequest request);
    /**
     * 功能描述: 获取异常预警集合
     * @auther: lixianhua
     * @date: 2020/4/1 10:18
     * @param:
     * @return:
     */
    List<ExtCaptureAbnormal> selectAbnormalList(ExtCaptureAbnormal abnormal,HttpServletRequest request);
    /**
     * 功能描述: 根据ID获取人员预警
     * @auther: lixianhua
     * @date: 2020/4/1 10:45
     * @param:
     * @return:
     */
    CaptureAlarmPerson selectPersonById(Integer id) throws OssRenderException;
    /**
     * 功能描述: 根据ID获取车辆预警
     * @auther: lixianhua
     * @date: 2020/4/1 10:45
     * @param:
     * @return:
     */
    CaptureAlarmCar selectCarById(Integer id) throws OssRenderException;
    /**
     * 功能描述: 根据ID获取异常预警
     * @auther: lixianhua
     * @date: 2020/4/1 10:45
     * @param:
     * @return:
     */
    CaptureAbnormal selectAbnormalById(Integer id) throws OssRenderException;
    /**
     * 功能描述: 处理预警信息
     * @auther: lixianhua
     * @date: 2020/4/1 14:37
     * @param:
     * @return:
     */
    boolean handleAlarm(String alarmType, Integer id, String content) throws OssRenderException;
    /**
     * 功能描述: 获取首页列表
     * @auther: lixianhua
     * @date: 2020/4/10 18:06
     * @param:
     * @return:
     */
    List<ExtHomeAlarmModel> selectHomeList();
    /**
     * 功能描述: 获取重点关注预警
     * @auther: lixianhua
     * @date: 2020/4/28 10:12
     * @param:
     * @return:
     */
    List<ExtCaptureAlarmAttention> selectAttentionList(ExtCaptureAlarmAttention attention, HttpServletRequest request);

    /**
     * 功能描述: 根据类型获取操作人列表
     * @auther: lixianhua
     * @date: 2020/5/11 9:15
     * @param:
     * @return:
     */
    List<HtUser> getOperateList(String type) throws OssRenderException;
    /**
     * 功能描述: 获取待处理预警数量
     * @auther: lixianhua
     * @date: 2020/5/11 9:15
     * @param:
     * @return:
     */
    Map<String, Integer> getWaitCount();
    /**
     * 功能描述: 警务通  获取重点关注预警集合
     * @auther: lixianhua
     * @date: 2020/5/12 15:00
     * @param:
     * @return:
     */
    List<ExtCaptureAlarmAttention> selectAppAttentionList(HttpServletRequest request, ExtCaptureAlarmAttention attention);
    /**
     * 功能描述: 警务通 获取人员预警集合
     * @auther: lixianhua
     * @date: 2020/5/12 18:17
     * @param:
     * @return:
     */
    List<ExtCaptureAlarmPerson> selectAppPersonList(HttpServletRequest request, ExtCaptureAlarmPerson person);
    /**
     * 功能描述: 警务通 获取车辆预警集合
     * @auther: lixianhua
     * @date: 2020/5/12 18:36
     * @param:
     * @return:
     */
    List<ExtCaptureAlarmCar> selectAppCarList(HttpServletRequest request, ExtCaptureAlarmCar car);
    /**
     * 功能描述: 警务通 获取异常事件集合
     * @auther: lixianhua
     * @date: 2020/5/12 19:01
     * @param:
     * @return:
     */
    List<ExtCaptureAbnormal> selectAppAbnormalList(HttpServletRequest request, ExtCaptureAbnormal abnormal);
}
