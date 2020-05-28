package com.windaka.suizhi.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.webapi.model.CaptureAlarmPerson;
import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.ext.*;
import com.windaka.suizhi.webapi.service.AlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AppController
 * @Description 警务通报警接口
 * @Author lixianhua
 * @Date 2020/5/11 8:51
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/app/alarm")
public class AppController extends BaseController {

    @Autowired
    private AlarmService alarmService;

    /**
     * 功能描述: 获取待处理预警数量
     * @auther: lixianhua
     * @date: 2020/5/11 8:55
     * @param:
     * @return:
     */
    @GetMapping("/waitCount")
    public Map<String, Object> personList(HttpServletRequest request) {
        try {
            Map<String,Integer> map = this.alarmService.getWaitCount();
            return render(map);
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }
    /**
     * 功能描述: 获取重点关注预警集合
     * @auther: lixianhua
     * @date: 2020/5/12 14:57
     * @param:
     * @return:
     */
    @GetMapping("/attentionList")
    public  Map<String, Object> attentionList(HttpServletRequest request, ExtCaptureAlarmAttention attention) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAlarmAttention> list = this.alarmService.selectAppAttentionList(request,attention);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<ExtCaptureAlarmAttention>());
            }
            return render(object);
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }
    /**
     * 功能描述: 获取人员预警集合
     * @auther: lixianhua
     * @date: 2020/5/12 18:33
     * @param:
     * @return:
     */
    @GetMapping("/personList")
    public  Map<String, Object> personList(HttpServletRequest request, ExtCaptureAlarmPerson person) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAlarmPerson> list = this.alarmService.selectAppPersonList(request,person);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<ExtCaptureAlarmPerson>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }
    /**
     * 功能描述: 获取车辆预警集合
     * @auther: lixianhua
     * @date: 2020/5/12 19:00
     * @param:
     * @return:
     */
    @GetMapping("/carList")
    public  Map<String, Object> carList(HttpServletRequest request, ExtCaptureAlarmCar car) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAlarmCar> list = this.alarmService.selectAppCarList(request,car);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<ExtCaptureAlarmCar>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }
    @GetMapping("/abnormalList")
    public  Map<String, Object> abnormalList(HttpServletRequest request, ExtCaptureAbnormal abnormal) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAbnormal> list = this.alarmService.selectAppAbnormalList(request,abnormal);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<ExtCaptureAbnormal>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 处理预警信息（更新处理内容）
     *
     * @auther: lixianhua
     * @date: 2020/4/1 14:33
     * @param:
     * @return:
     */
    @PutMapping(value = "/handleAlarm")
    public Map<String, Object> addHandleInfo(String alarmType, Integer id, String content) {
        try {
            boolean flag = this.alarmService.handleAlarm(alarmType, id, content);
            if (flag) {
                return render();
            }
            return failRender(ReturnConstants.CODE_FAILED, "更新失败");
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }






}
