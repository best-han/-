package com.windaka.suizhi.mpi.task;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.utils.EnumDateStyle;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.common.utils.Tools;
import com.windaka.suizhi.mpi.dao.CaptureCarMapper;
import com.windaka.suizhi.mpi.dao.MsgSocketIdDao;
import com.windaka.suizhi.mpi.dao.auto.CaptureAlarmCarMapper;
import com.windaka.suizhi.mpi.dao.auto.MsgSocketIdMapper;
import com.windaka.suizhi.mpi.message.SendMessage;
import com.windaka.suizhi.mpi.model.*;
import com.windaka.suizhi.mpi.model.ext.ExtCaptureCar;
import com.windaka.suizhi.mpi.service.UserService;
import com.windaka.suizhi.mpi.websocket.AlarmSocket;
import com.windaka.suizhi.mpi.websocket.AppSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HomeCarTask
 * @Description 首页车辆报警弹窗定时任务
 * @Author lixianhua
 * @Date 2020/1/7 9:45
 * @Version 1.0
 */
@Slf4j
@Service
public class HomeCarTask {

    @Autowired
    private MsgSocketIdDao msgSocketIdDao;

    @Autowired
    private MsgSocketIdMapper msgSocketIdMapper;

    @Autowired
    private CaptureCarMapper captureCarMapper;

    @Autowired
    private AlarmSocket alarmSocket;

    @Autowired
    UserService userService;


    @Autowired
    private CaptureAlarmCarMapper captureAlarmCarMapper;

    @Autowired
    private AppSocket appSocket;

    public void run() {
        log.info("首页推送车辆报警任务开始,开始时间:" + TimesUtil.getServerDateTime(9, new Date()));
        // 获取车辆抓拍最大值
        MsgSocketIdExample msgExample = new MsgSocketIdExample();
        msgExample.createCriteria().andRecordNameEqualTo("car_access_record_home");
        MsgSocketId msg = this.msgSocketIdMapper.selectByExample(msgExample).get(0);
        int lastId = msg.getMaxId().intValue();
        // 获取最大车辆出入id;
        Integer maxId = this.captureCarMapper.getMaxId();
        List<ExtCaptureCar> carInOutList = captureCarMapper.queryCarForAlarm(lastId);
        carInOutList.forEach(temp -> {
//            CaptureAlarmCarExample example = new CaptureAlarmCarExample();
//            example.createCriteria().andCarNumEqualTo(temp.getCarNum());
//            List<CaptureAlarmCar> alarmCarList = this.captureAlarmCarMapper.selectByExample(example);
//            boolean insertFlag = true;
            // 查询车辆报警是否存在该车记录,存在则不再添加数据库
//            if (null != alarmCarList && alarmCarList.size() > 0) {
//                insertFlag = false;
//                // 已处理则不弹窗，未处理则弹窗
//                if ("0".equals(alarmCarList.get(0).getHandelStatus())) {
//                    return;
//                }
//            }
            String event = "";
            String colorName = "";
            if ("1".equals(temp.getDetailLevel())) {// 高
                event = "5";
                colorName = "红色";
            } else if ("2".equals(temp.getDetailLevel())) {// 中
                event = "6";
                colorName = "橙色";
            } else if ("3".equals(temp.getDetailLevel())) {// 低
                event = "7";
                colorName = "黄色";
            }

            CaptureAlarmCar car = new CaptureAlarmCar();
            car.setCommunityCode(temp.getCommunityCode());
            car.setCaptureTime(temp.getCaptureTime());
            car.setDeviceCode(temp.getDeviceCode());
            car.setDeviceName(temp.getDeviceName());
            car.setDeviceType(Short.valueOf(temp.getDeviceType()));
            car.setDeviceTypeName(temp.getDeviceTypeName());
            car.setDeviceLocation(temp.getDeviceLocation());
            // 大图
            car.setCapImage(temp.getFullImage());
            // 小图
            car.setCapSmallImage(temp.getCapImage());
            car.setCapVideo(temp.getCapVideo());
            car.setCarNum(temp.getCarNum());
            car.setDetailId(temp.getDetailId());
            car.setDetailLevel(Short.valueOf(temp.getDetailLevel()));
            car.setDetailLevelName(temp.getDetailLevelName());
            car.setDetailReason(temp.getDetailReason());
            car.setGroupCode(temp.getGroupCode());
            car.setGroupName(temp.getGroupName());
            car.setHandelStatus(new Byte("1"));
            car.setCreateTime(new Date());
            car.setCreateUser(temp.getCreateUser());
            car.setCreateUserId(temp.getCreateUserId());
            // 添加车辆报警记录
            int insertNum = this.captureAlarmCarMapper.insertSelective(car);
            if (insertNum > 0) {
                HomeAlarmModel model = new HomeAlarmModel();
                model.setId(Tools.getUUID());
                model.setCarNum(temp.getCarNum());
                model.setGroupName(temp.getGroupName());
                model.setLocation(temp.getCommunityName() + temp.getDeviceName());
                model.setTimeStamp(System.currentTimeMillis() + "");
                model.setEvent(event);
                model.setImg(PropertiesUtil.getLocalTomcatImageIp() + temp.getCapImage());
                model.setCaptureTimeStr(null == temp.getCaptureTime() ? "" : TimesUtil.dateToString(temp.getCaptureTime(), EnumDateStyle.YYYY_MM_DD_HH_MM_SS_CN));
                // 获取待发送用户集合
                List<HtUser> userList = this.userService.getUserListById(temp.getCreateUserId());
                String message = JSON.toJSONString(model);
                // 公安socket
                this.alarmSocket.sendMessageToUser(message, userList);
                AppModel app = new AppModel();
                app.setType("1");
                app.setSubType("3");
                app.setNum(insertNum);
                String appMessage = JSON.toJSONString(app);
                // 警务通socket
                this.appSocket.sendMessageToUser(appMessage, userList);
                try {
                    log.info("*******************发送车辆报警短信开始*******************");
                    // 获取手机号
                    HtUser condition = new HtUser();
                    condition.setUserId(temp.getCreateUserId());
                    HtUser htUser = this.userService.getUserList(condition).get(0);
                    List<String> phones = new ArrayList<>();
                    phones.add(htUser.getPhone());
                    // 测试用逄华电话
                    phones.add("13668840365");
                    SendMessage.send(phones,
                            colorName + "预警：发现" + temp.getGroupName() + "车辆\n车牌号：" + temp.getCarNum() + "\n出现地点：" + temp.getCommunityName() + temp.getDeviceName() + "\n抓拍时间：" + TimesUtil.dateToString(temp.getCaptureTime(), EnumDateStyle.YYYY_MM_DD_HH_MM_SS_CN));
                    log.info("*******************发送车辆报警短信结束");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("*******************车辆报警短信发送失败*******************");
                }
            }
        });
        msg.setMaxId(Long.valueOf(null!=maxId?maxId:0));
        // 更新最大值
        this.msgSocketIdMapper.updateByPrimaryKeySelective(msg);
        log.info("首页推送车辆报警任务结束,结束时间:" + TimesUtil.getServerDateTime(9, new Date()));
    }
}
