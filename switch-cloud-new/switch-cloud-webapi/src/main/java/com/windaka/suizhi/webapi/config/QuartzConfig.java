package com.windaka.suizhi.webapi.config;

import com.windaka.suizhi.webapi.task.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 定时任务 hjt
 * 因短信发送只在公安内网进行，目前只支持内网执行此定时任务
 */
@Configuration
@EnableScheduling
@EnableAsync
public class QuartzConfig {

    /*@Resource
    SuspectedAddTask suspectedAddTask;

    @Resource
    SuspectedQuitTask suspectedQuitTask;

    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void SuspectedAddTask(){
        try {
            suspectedAddTask.executeInternal();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void SuspectedQuitTask(){
        try {
            suspectedQuitTask.executeInternal();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

//    定时任务触发人脸抓拍图像
//    @Resource
//    WebsocketSendTask websocketSendTask;
//    @Scheduled(cron = "0/10 * * * * ?")
//    @Async
//    public void websocketSendTask(){
//        try {
//            websocketSendTask.executeInternal();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    //定时任务触发疑似迁出人员
    @Resource
    PersonQuitTask personQuitTask;
    @Scheduled(cron = "0 0 2 * * ?")
    @Async
    public void personQuitTask(){
        try {
            //查询疑似迁出人列表并插入展示表 1天1次
            personQuitTask.executeInternal();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    定时任务触发疑似新增人员
    @Resource
    PersonAddTask personAddTask;
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void personAddTask(){
        try {
            //查询疑似新增人列表并插入展示表 1天1次
            personAddTask.executeInternal();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //疑似闲置房屋添加定时任务
  /*  @Resource
    IdleRoomTask idleRoomTask;
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void idleRoomAddTask(){
        try {
            idleRoomTask.idleRoomAdd();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    //增加闲置房屋疑似居住定时任务
  /*  @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void idleRoomLivedAddTask(){
        try {
            idleRoomTask.idleRoomLivedAdd();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    //实有房产列表查询定时任务
   /* @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void jBaseHouseAddTask(){
        try {
            idleRoomTask.jBaseHouseAdd();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    //群租房添加定时任务
    /*@Resource
    GroupRentalRoomAddTask groupRentalRoomAddTask;
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void groupRentalRoomAddTask(){
        try {
            groupRentalRoomAddTask.executeInternal();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

}
