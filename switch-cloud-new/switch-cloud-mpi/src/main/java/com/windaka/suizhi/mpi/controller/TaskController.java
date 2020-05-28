package com.windaka.suizhi.mpi.controller;

import com.windaka.suizhi.common.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName AlarmController
 * @Description TODO
 * @Author lixianhua
 * @Date 2020/5/15 14:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/taskJob")
public class TaskController extends BaseController {


    /**
     * 功能描述: 更新已经布控结束的记录
     *
     * @auther: lixianhua
     * @date: 2020/5/15 14:31
     * @param:
     * @return:
     */
    @GetMapping(value = "/updateGroupAlarm")
    public Map<String, Object> queryAbnormalInfo() {
        try {
            return render("123456");
        } catch (Exception e) {
            return failRender(e);
        }
    }
}
