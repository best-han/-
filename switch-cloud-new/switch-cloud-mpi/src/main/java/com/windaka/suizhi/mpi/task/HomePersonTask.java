package com.windaka.suizhi.mpi.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.common.utils.Tools;
import com.windaka.suizhi.mpi.config.RabbitmqConfig;
import com.windaka.suizhi.mpi.dao.MsgPhoneDao;
import com.windaka.suizhi.mpi.dao.MsgSocketIdDao;
import com.windaka.suizhi.mpi.dao.auto.CaptureAlarmPersonMapper;
import com.windaka.suizhi.mpi.dao.auto.CapturePersonMapper;
import com.windaka.suizhi.mpi.dao.auto.FaceGroupDetailMapper;
import com.windaka.suizhi.mpi.message.SendMessage;
import com.windaka.suizhi.mpi.model.*;
import com.windaka.suizhi.mpi.service.UserService;
import com.windaka.suizhi.mpi.websocket.AlarmSocket;
import com.windaka.suizhi.mpi.websocket.AppSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class HomePersonTask {

    @Autowired
    private CapturePersonMapper capturePersonMapper;
    @Autowired
    FaceGroupDetailMapper faceGroupDetailMapper;
    @Autowired
    MsgSocketIdDao msgSocketIdDao;
    @Autowired
    private CaptureAlarmPersonMapper captureAlarmPersonMapper;
    @Autowired
    MsgPhoneDao msgPhoneDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AlarmSocket alarmSocket;
    @Autowired
    private AppSocket appSocket;


    /**
     * 功能描述: 获取抓拍人员信息和犯罪人员信息比对结果  发送websocket
     *
     * @auther: lixianhua
     * @date: 2019/12/16 16:28
     * @param:
     * @return:
     */
    @RabbitListener(queues = RabbitmqConfig.QUEUE_POLICE_FACEALARM)
    @RabbitHandler
    public void process(@Payload String content) {
        log.info("QUEUE_POLICE_FACEALARM*******************接收到的消息体：" + content + "**********************");
        JSONObject contentJson = JSONObject.parseObject(content);
        // 人员ID
        String id = (String) contentJson.get("Id");
        // 根据主键抓拍获取人员信息
        CapturePerson capturePerson = this.capturePersonMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (null == capturePerson) {
            return;
        }
        String simiArr [] = ((String) contentJson.get("similarity")).split(",");
        String strArr[] = ((String) contentJson.get("crimeFeatureId")).split(",");
        for (int i = 0; i < strArr.length; i++) {
            // 相似度
            String similarity = simiArr[i];
            // 人员布控code
            String crimeFeatureId = strArr[i];//face_crime_feature
            // 推送信息
            HomeAlarmModel record = new HomeAlarmModel();
            record.setId(Tools.getUUID());

            // 根据主键获取布控人员信息
            FaceGroupDetail groupDetail = this.faceGroupDetailMapper.selectByPrimaryKey(Integer.parseInt(crimeFeatureId));
            if (null == groupDetail|| groupDetail.getStatus()) {
                continue;
            }
            record.setLocation(capturePerson.getCommunityName() + capturePerson.getDeviceName());
            record.setXqName(capturePerson.getCommunityName());
            // 抓拍时间
            record.setCaptureTimeStr(TimesUtil.dateToString(capturePerson.getCaptureTime(), TimesUtil.DATE_TIME_FORMAT));
            // 抓拍信息
            record.setImg(null != capturePerson.getCapImage() ? PropertiesUtil.getLocalTomcatImageIp() + capturePerson.getCapImage() :
                    "");
            record.setTimeStamp(System.currentTimeMillis() + "");
            // 姓名
            record.setPersonName(StringUtils.isBlank(groupDetail.getPersonName()) ? "未知" : groupDetail.getPersonName());
            // 人脸库图片
            record.setGroupPic(StringUtils.isNotBlank(groupDetail.getPersonImage()) ? PropertiesUtil.getLocalTomcatImageIp() + groupDetail.getPersonImage() : "");
            // 库名称
            record.setGroupName(groupDetail.getGroupName());
            // 报警等级（用于判断弹窗颜色）
            String colorName = "";
            String event = "";
            if (null != groupDetail.getLevel()) {
                if ("1".equals(groupDetail.getLevel().toString())) {
                    colorName = "红色";
                    event = "8";
                } else if ("2".equals(groupDetail.getLevel().toString())) {
                    colorName = "橙色";
                    event = "9";
                } else if ("3".equals(groupDetail.getLevel().toString())) {
                    colorName = "黄色";
                    event = "10";
                }
            }
            record.setEvent(event);
            // 相似度
            record.setSimilar(similarity);
            CaptureAlarmPerson alarmPerson = new CaptureAlarmPerson();
            alarmPerson.setCommunityCode(capturePerson.getCommunityCode());
            alarmPerson.setCaptureTime(capturePerson.getCaptureTime());
            alarmPerson.setDeviceCode(capturePerson.getDeviceCode());
            alarmPerson.setDeviceName(capturePerson.getDeviceName());
            alarmPerson.setDeviceType(Short.valueOf(capturePerson.getDeviceType()));
            alarmPerson.setDeviceTypeName(capturePerson.getDeviceTypeName());
            alarmPerson.setDeviceLocation(capturePerson.getDeviceLocation());
            // 大图
            alarmPerson.setCapImage(capturePerson.getCapImage());
            // 小图
            alarmPerson.setCapSmallImage(capturePerson.getCapPersonImage());
            alarmPerson.setCapVideo(capturePerson.getCapVideo());
            alarmPerson.setDetailId(groupDetail.getId().toString());
            alarmPerson.setDetailImage(groupDetail.getPersonImage());
            alarmPerson.setDetailLevel(groupDetail.getLevel());
            alarmPerson.setDetailLevelName(groupDetail.getLevelName());
            alarmPerson.setDetailReason(groupDetail.getReason());
            alarmPerson.setDetailPersonCode(groupDetail.getPersonCode());
            alarmPerson.setDetailPersonName(groupDetail.getPersonName());
            alarmPerson.setDetailPersonPaperNum(groupDetail.getPersonPaperNumber());
            alarmPerson.setDetailPersonPhone(groupDetail.getPersonPhone());
            alarmPerson.setGroupCode(groupDetail.getGroupCode());
            alarmPerson.setGroupName(groupDetail.getGroupName());
            alarmPerson.setContrastValue(similarity);
            // 未处理
            alarmPerson.setHandelStatus(new Byte("1"));
            alarmPerson.setCreateTime(new Date());
            alarmPerson.setCreateUser(groupDetail.getCreateUser());
            alarmPerson.setCreateUserId(groupDetail.getCreateUserId());
            // 人脸报警记录中添加一条记录
            int insertNum = this.captureAlarmPersonMapper.insertSelective(alarmPerson);
            if (insertNum > 0) {
                String resultJson = JSON.toJSONString(record);
                List<HtUser> userList = new ArrayList<>();
                // 获取待发送用户集合
                if (groupDetail.getSource()) {// 市局数据给所有用户发送
                    HtUser condition = new HtUser();
                    userList = this.userService.getUserList(condition);
                } else {// 非市局数据根据权限发送
                    userList = this.userService.getUserListById(groupDetail.getCreateUserId());
                }
                // 公安socket
                alarmSocket.sendMessageToUser(resultJson, userList);
                AppModel app = new AppModel();
                app.setType("1");
                app.setSubType("2");
                app.setNum(insertNum);
                String appMessage = JSON.toJSONString(app);
                // 警务通socket
                appSocket.sendMessageToUser(appMessage, userList);
                try {
                    log.info("*******************发送人脸报警短信开始*******************");
                    // 获取手机号
                    HtUser condition = new HtUser();
                    condition.setUserId(groupDetail.getCreateUserId());
                    HtUser htUser = this.userService.getUserList(condition).get(0);
                    List<String> phones = new ArrayList<>();
                    phones.add(htUser.getPhone());
                    // 测试用逄华电话
                    phones.add("13668840365");
//                    SendMessage.send(phones,
//                            colorName + "预警：发现" + groupDetail.getGroupName() + "人员\n姓名：" + groupDetail.getPersonName() + "\n证件号码：" + groupDetail.getPersonPaperNumber() + "\n出现地点：" + record.getLocation() + "\n抓拍时间：" + record.getCaptureTimeStr());
                    log.info("*******************发送人脸报警短信结束*******************");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("*******************人脸报警短信发送失败*******************");
                }
            }
        }
    }

}
