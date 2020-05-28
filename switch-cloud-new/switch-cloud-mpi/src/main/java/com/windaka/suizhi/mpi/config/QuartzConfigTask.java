package com.windaka.suizhi.mpi.config;

import com.windaka.suizhi.mpi.task.SuspectedAppTask;
import com.windaka.suizhi.mpi.task.WebsocketSendTask;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/*
zdq ygy 人/车抓拍推送
 */
@Configuration
@EnableScheduling // 启用定时任务
@EnableAsync
public class QuartzConfigTask {
    @Resource
    WebsocketSendTask websocketSendTask;
    @Scheduled(cron = "0/10 * * * * ?")
    @Async
    public void websocketSendTask(){
        try {
            websocketSendTask.executeInternal();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Resource
    SuspectedAppTask suspectedAppTask;
    @Scheduled(cron = "0 0 8 * * ?")
    @Async
    public void appSendTask(){
        try {
            suspectedAppTask.executeInternal();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
