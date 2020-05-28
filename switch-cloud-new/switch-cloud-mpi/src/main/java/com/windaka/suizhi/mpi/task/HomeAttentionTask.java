package com.windaka.suizhi.mpi.task;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.common.utils.Tools;
import com.windaka.suizhi.mpi.dao.auto.CaptureAlarmAttentionMapper;
import com.windaka.suizhi.mpi.dao.auto.FaceAttentionDetailMapper;
import com.windaka.suizhi.mpi.message.SendMessage;
import com.windaka.suizhi.mpi.model.*;
import com.windaka.suizhi.mpi.service.UserService;
import com.windaka.suizhi.mpi.websocket.AlarmSocket;
import com.windaka.suizhi.mpi.websocket.AppSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName HomeAttention
 * @Description 首页推送重点关注并添加重点关注预警
 * @Author lixianhua
 * @Date 2020/4/28 19:48
 * @Version 1.0
 */
@Slf4j
@Service
public class HomeAttentionTask {

    @Autowired
    private FaceAttentionDetailMapper faceAttentionDetailMapper;

    @Autowired
    private CaptureAlarmAttentionMapper captureAlarmAttentionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AlarmSocket alarmSocket;

    @Autowired
    private AppSocket appSocket;

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }

    public void run() {
        log.info("首页推送重点关注任务开始,开始时间:" + TimesUtil.getServerDateTime(9, new Date()));
        int num = 0;
        // 获取重点关注人员
        FaceAttentionDetailExample example = new FaceAttentionDetailExample();
        // 已经布控且还没有布控结束（布控结束时间小于当前时间）
        example.createCriteria().andStatusEqualTo(false).andEndTimeGreaterThanOrEqualTo(new Date());
        List<FaceAttentionDetail> detailList = this.faceAttentionDetailMapper.selectByExample(example);
        if (null != detailList && detailList.size() > 0) {
            List<FaceAttentionDetail> resultList = new ArrayList<>();
            detailList.forEach(model -> {
                long captureTime = model.getCaptureTime().getTime();
                long startTime = model.getStartTime().getTime();
                if (captureTime == startTime) {// 布控之后从未被抓拍到
                    // 获取最后预警时间段
                    CaptureAlarmAttentionExample alarmExample = new CaptureAlarmAttentionExample();
                    alarmExample.createCriteria().andCreateTimeGreaterThan(model.getStartTime()).andAttentionIdEqualTo(model.getId());
                    alarmExample.setOrderByClause("create_time desc");
                    List<CaptureAlarmAttention> alarmList = this.captureAlarmAttentionMapper.selectByExample(alarmExample);
                    if (null != alarmList && alarmList.size() > 0) {// 已经预警过
                        CaptureAlarmAttention captureAlarm = alarmList.get(0);
                        int oldDays = Integer.parseInt(captureAlarm.getDuration()) / 24;
//                        // 获取未感知天数
                        int days = TimesUtil.getDayByTwo(model.getCaptureTime(), new Date());
//                        // 小于当前时间则预警
                        if (days > oldDays) {
                            // 将未感知时长放在updateUser中，在后面直接取值
                            model.setUpdateUser(String.valueOf(days * 24));
                            resultList.add(model);
                        }
                        // 测试代码
//                        int oldDays = Integer.parseInt(captureAlarm.getDuration());
                        // 获取未感知天数
//                        int days = TimesUtil.getMinByTwo(model.getCaptureTime(), new Date());
                        // 测试
//                        int days = Integer.parseInt(captureAlarm.getDuration())  + 2;
//                        Date date = TimesUtil.addMinute(model.getCaptureTime(), days);
                        // 小于当前时间则预警
//                        if (days > oldDays && (days - 3) % 2 == 0) {
//                            // 将未感知时长放在updateUser中，在后面直接取值
//                            model.setUpdateUser(String.valueOf(days));
//                            resultList.add(model);
//                        }
                    } else {// 没有预警过
                        int days = TimesUtil.getDayByTwo(model.getCaptureTime(), new Date());
//                        // 开始未感知天数设置为48小时
                        if (days > 2) {
                            // 将未感知时长房子updateUser中在后面直接取值
                            model.setUpdateUser(String.valueOf(days * 24));
                            resultList.add(model);
                        }
                        // 测试代码
//                        int mins = TimesUtil.getMinByTwo(model.getCaptureTime(), new Date());
//                        if (mins > 3) {
//                            // 将未感知时长房子updateUser中在后面直接取值
//                            model.setUpdateUser(String.valueOf(mins));
//                            resultList.add(model);
//                        }
                    }
                } else {// 布控之后有被抓拍到
                    CaptureAlarmAttentionExample alarmExample = new CaptureAlarmAttentionExample();
                    alarmExample.createCriteria().andCreateTimeGreaterThan(model.getCaptureTime()).andAttentionIdEqualTo(model.getId());
                    alarmExample.setOrderByClause("create_time desc");
                    List<CaptureAlarmAttention> alarmList = this.captureAlarmAttentionMapper.selectByExample(alarmExample);
                    if (null != alarmList && alarmList.size() > 0) {// 已经预警过
                        CaptureAlarmAttention captureAlarm = alarmList.get(0);
                        if(model.getCaptureTime().getTime()>captureAlarm.getCreateTime().getTime()){// 抓拍时间大于最后预警时间则重新计时
                            int days = TimesUtil.getDayByTwo(model.getCaptureTime(), new Date());
                            if(days>0){
                                model.setUpdateUser(String.valueOf(days * 24));
                                resultList.add(model);
                            }
                        }else{// 最后预警时间大于最近抓拍时间
                            int days = TimesUtil.getDayByTwo(model.getCaptureTime(), new Date());
                            int oldDays = Integer.parseInt(captureAlarm.getDuration()) / 24;
                            if (days > oldDays) {
                                model.setUpdateUser(String.valueOf(days * 24));
                                resultList.add(model);
                            }
                        }
                        // 测试代码
//                        CaptureAlarmAttention captureAlarm = alarmList.get(0);
//                        int days = TimesUtil.getMinByTwo(model.getCaptureTime(), new Date());
//                        int oldDays = Integer.parseInt(captureAlarm.getDuration());
//                        if (days > oldDays && (days - 3) % 2 == 0) {
//                            model.setUpdateUser(String.valueOf(days));
//                            resultList.add(model);
//                        }
                    } else {// 没有预警过
                        int days = TimesUtil.getDayByTwo(model.getCaptureTime(), new Date());
                        if (days > 1) {
                            model.setUpdateUser(String.valueOf(days * 24));
                            resultList.add(model);
                        }
                        // 测试代码
//                        int days = TimesUtil.getMinByTwo(model.getCaptureTime(), new Date());
//                        if (days > 1) {
//                            model.setUpdateUser(String.valueOf(days));
//                            resultList.add(model);
//                        }
                    }
                }
            });
            if (resultList.size() > 0) {
                for (FaceAttentionDetail detail : resultList) {
                    CaptureAlarmAttention alarm = new CaptureAlarmAttention();
                    alarm.setAttentionId(detail.getId());
                    // 从updateUser中取未感知时长
                    alarm.setDuration(detail.getUpdateUser());
                    alarm.setPersonCode(detail.getPersonCode());
                    alarm.setLevel(Integer.parseInt(detail.getLevel()));
                    alarm.setLevelName(detail.getLevelName());
                    alarm.setAlarmTime(new Date());
                    alarm.setLastCaptureTime(detail.getCaptureTime());
                    alarm.setCaptureLocation(detail.getCommunityName());
                    alarm.setCaptureLocationCode(detail.getCommunityCode());
                    alarm.setCaptureDevice(detail.getDeviceName());
                    alarm.setReason(detail.getReason());
                    alarm.setName(detail.getPersonName());
                    alarm.setPhone(detail.getPhone());
                    alarm.setIdNo(detail.getIdNo());
                    alarm.setPicUrl(detail.getImgUrl());
                    alarm.setAttentionCode(detail.getAttentionCode());
                    alarm.setAttentionName(detail.getAttentionName());
                    alarm.setHandelStatus(true);
                    alarm.setCreateTime(new Date());
                    alarm.setCreateUser(detail.getCreateUser());
                    alarm.setCreateUserId(detail.getCreateUserId());
                    int insertNum = this.captureAlarmAttentionMapper.insertSelective(alarm);
                    if (insertNum > 0) {
                        // 获取待发送用户集合
                        List<HtUser> userList = this.userService.getUserListById(detail.getCreateUserId());
                        AppModel appModel = new AppModel();
                        appModel.setNum(insertNum);
                        appModel.setSubType("1");
                        appModel.setType("1");
                        String appResult = JSON.toJSONString(appModel);
                        // 发送socket给警务通
                        appSocket.sendMessageToUser(appResult, userList);
                        HomeAlarmModel model = new HomeAlarmModel();
                        model.setId(Tools.getUUID());
                        model.setEvent(detail.getLevel());
                        model.setLocation(detail.getCommunityName());
                        model.setPersonName(detail.getPersonName());
                        model.setGroupPic(PropertiesUtil.getLocalTomcatImageIp() + detail.getImgUrl());
                        model.setDuration(detail.getUpdateUser() + "小时");
                        model.setTimeStamp(System.currentTimeMillis() + "");
                        model.setGroupName(detail.getAttentionName() + "未感知");
                        String resultJson = JSON.toJSONString(model);
                        // 发送websocket给公安段
                        alarmSocket.sendMessageToUser(resultJson, userList);
                        try {
                            log.info("*******************发送重点关注报警短信开始*******************");
                            // 获取手机号
                            HtUser condition = new HtUser();
                            condition.setUserId(detail.getCreateUserId());
                            HtUser htUser = this.userService.getUserList(condition).get(0);
                            List<String> phones = new ArrayList<>();
                            phones.add(htUser.getPhone());
                            // 测试用逄华电话
                            phones.add("13668840365");
                            SendMessage.send(phones,
                                    "橙色预警：重点人员疑似离开辖区\n事件类型：" + detail.getAttentionName() + "\n姓名：" + detail.getPersonName() +
                                            "\n身份证号：" + detail.getIdNo() + "\n电话号码：" + detail.getPhone() + "\n小区：" + detail.getCommunityName() +
                                            "\n未感知时长：" + detail.getUpdateUser() + "小时");
                            log.info("*******************发送重点关注报警短信结束");
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("*******************重点关注报警短信发送失败*******************");
                        }
                        num++;
                    }
                }
            }
        }
        log.info("首页推送重点关注任务结束,结束时间:" + TimesUtil.getServerDateTime(9, new Date()) + "一天添加" + num + "条记录");
    }
}
