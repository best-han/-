package com.windaka.suizhi.mpi.task;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.common.utils.Tools;
import com.windaka.suizhi.mpi.dao.CarRecordDao;
import com.windaka.suizhi.mpi.dao.MsgPhoneDao;
import com.windaka.suizhi.mpi.dao.MsgSocketIdDao;
import com.windaka.suizhi.mpi.dao.auto.CaptureAbnormalMapper;
import com.windaka.suizhi.mpi.dao.auto.MsgSocketIdMapper;
import com.windaka.suizhi.mpi.message.SendMessage;
import com.windaka.suizhi.mpi.model.*;
import com.windaka.suizhi.mpi.service.UserService;
import com.windaka.suizhi.mpi.websocket.AlarmSocket;
import com.windaka.suizhi.mpi.websocket.AppSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: 推送异常信息给首页（流浪动物，车辆占道）
 *
 * @auther: lixianhua
 * @date: 2020/4/10 16:16
 * @param:
 * @return:
 */
@Slf4j
@Service
public class HomeAbnormalTask {
    @Autowired
    CarRecordDao carRecordDao;
    @Autowired
    MsgSocketIdDao msgSocketIdDao;
    @Autowired
    MsgSocketIdMapper msgSocketIdMapper;
    @Autowired
    MsgPhoneDao msgPhoneDao;
    @Autowired
    private CaptureAbnormalMapper captureAbnormalMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private AlarmSocket alarmSocket;
    @Autowired
    private AppSocket appSocket;
    // 运行状态标识
    private Boolean taskFlag = true;

    long lastId = 0;

    public void executeInternal() {
        if (taskFlag) {
            taskFlag = false;
            log.info("首页推送异常行为任务开始,开始时间:" + TimesUtil.getServerDateTime(9, new Date()));
            MsgSocketIdExample msgExample = new MsgSocketIdExample();
            msgExample.createCriteria().andRecordNameEqualTo("record_abnormal");
            MsgSocketId msg = this.msgSocketIdMapper.selectByExample(msgExample).get(0);
            lastId = msg.getMaxId();
            CaptureAbnormalExample example = new CaptureAbnormalExample();
            List<Short> eventList = new ArrayList<>();
            eventList.add(Short.valueOf("0"));
            eventList.add(Short.valueOf("4"));
            example.createCriteria().andIdGreaterThan((int) lastId).andEventIn(eventList);
            List<CaptureAbnormal> abnormalRecordList = captureAbnormalMapper.selectByExample(example);
            if (null != abnormalRecordList && abnormalRecordList.size() > 0) {
                abnormalRecordList.forEach(temp -> {
                    HomeAlarmModel model = new HomeAlarmModel();
                    model.setId(Tools.getUUID());
                    if (0 == temp.getEvent()) {// 车辆占道
                        model.setGroupName("车辆占用消防通道");
                    } else if (4 == temp.getEvent()) {
                        model.setGroupName("流浪动物");
                    }
                    model.setEvent(temp.getEvent().toString());
                    model.setLocation(temp.getCommunityName() + temp.getDeviceName());
                    model.setImg(PropertiesUtil.getLocalTomcatImageIp() + temp.getCapImage());
                    model.setTimeStamp(System.currentTimeMillis() + "");
                    model.setCaptureTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(temp.getCaptureTime()));
                    // 获取待发送人集合
                    List<HtUser> list = this.userService.getUserListByXqCode(temp.getCommunityCode());
                    // 公安socket
                    this.alarmSocket.sendMessageToUser(JSON.toJSONString(model), list);
                    AppModel app = new AppModel();
                    app.setType("1");
                    app.setSubType("4");
                    app.setNum(1);
                    // 警务通socket
                    this.appSocket.sendMessageToUser(JSON.toJSONString(app), list);
                    log.info("*************发送异常事件短信开始*************");
                    try {
                        // 获取有该小区权限用户
                        List<HtUser> userList = this.userService.getUserListByXqCode(temp.getCommunityCode());
                        List<String> phones = new ArrayList<>();
                        if (null != userList && userList.size() > 0) {
                            for (HtUser user : userList) {
                                phones.add(user.getPhone());
                            }
                        }
                        // 测试用逄华电话
                        phones.add("13668840365");
                        SendMessage.send(phones,
                                "黄色预警：发现" + model.getGroupName() + "\n事件类型：" + model.getGroupName() + "\n出现地点：" + model.getLocation() + "\n抓拍时间：" +
                                        model.getCaptureTimeStr());
                        log.info("发送异常事件短信成功");
                    } catch (Exception e) {
                        log.error("发送异常事件短信失败");
                        e.printStackTrace();
                    }
                    log.info("*************发送异常事件短信结束*************");
                });
                msg.setMaxId(Long.valueOf(abnormalRecordList.get(abnormalRecordList.size() - 1).getId()));
                // 更新最大值
                this.msgSocketIdMapper.updateByPrimaryKeySelective(msg);
            }
            taskFlag = true;
            log.info("首页推送异常行为任务结束,结束时间:" + TimesUtil.getServerDateTime(9, new Date()));
        }
    }
}
