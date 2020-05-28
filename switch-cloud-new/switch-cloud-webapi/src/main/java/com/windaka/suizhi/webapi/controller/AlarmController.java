package com.windaka.suizhi.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.webapi.model.*;
import com.windaka.suizhi.webapi.model.ext.*;
import com.windaka.suizhi.webapi.service.AlarmService;
import com.windaka.suizhi.webapi.util.Paginator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AlarmController
 * @Description 预警控制器
 * @Author lixianhua
 * @Date 2020/3/31 15:44
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController extends BaseController {

    @Autowired
    private AlarmService alarmService;

    /**
     * 功能描述: 获取首页列表
     * @auther: lixianhua
     * @date: 2020/4/10 18:09
     * @param:
     * @return:
     */
    @GetMapping("/homeList")
    public Map<String, Object> homeList(
            HttpServletRequest request) {
        JSONObject object = new JSONObject();
        try {
            List<ExtHomeAlarmModel> list = this.alarmService.selectHomeList();
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<CaptureAlarmPerson>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 查询人员预警信息
     *
     * @auther: lixianhua
     * @date: 2020/4/1 9:27
     * @param:
     * @return:
     */
    @GetMapping("/personList")
    public Map<String, Object> getPersonList(
            HttpServletRequest request, ExtCaptureAlarmPerson person) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAlarmPerson> list = this.alarmService.selectPersonList(person,request);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<CaptureAlarmPerson>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 查询车辆预警信息
     *
     * @auther: lixianhua
     * @date: 2020/4/1 10:08
     * @param:
     * @return:
     */
    @GetMapping("/carList")
    public Map<String, Object> getCarList(
            HttpServletRequest request,ExtCaptureAlarmCar car) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAlarmCar> list = this.alarmService.selectCarList(car,request);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<CaptureAlarmPerson>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 异常预警信息
     *
     * @auther: lixianhua
     * @date: 2020/4/1 10:09
     * @param:
     * @return:
     */
    @GetMapping("/abnormalList")
    public Map<String, Object> getAbnormalList(
            HttpServletRequest request,   ExtCaptureAbnormal abnormal) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAbnormal> list = this.alarmService.selectAbnormalList(abnormal,request);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<CaptureAlarmPerson>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }
    /**
     * 功能描述: 重点关注预警列表
     * @auther: lixianhua
     * @date: 2020/4/28 11:01
     * @param:
     * @return:
     */
    @GetMapping("/attentionList")
    public Map<String, Object> getAttentionList(
            HttpServletRequest request,   ExtCaptureAlarmAttention attention) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCaptureAlarmAttention> list = this.alarmService.selectAttentionList(attention,request);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<CaptureAlarmPerson>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 根据主键获取人员预警
     *
     * @auther: lixianhua
     * @date: 2020/4/1 14:28
     * @param:
     * @return:
     */
    @GetMapping(value = "/personInfo/{id}")
    public Map<String, Object> queryPersonAlarmInfo(@PathVariable("id") Integer id) {
        try {
            CaptureAlarmPerson person = alarmService.selectPersonById(id);
            return render(person);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 根据主键获取车辆预警
     *
     * @auther: lixianhua
     * @date: 2020/4/1 14:28
     * @param:
     * @return:
     */
    @GetMapping(value = "/carInfo/{id}")
    public Map<String, Object> queryCarAlarmInfo(@PathVariable("id") Integer id) {
        try {
            CaptureAlarmCar car = alarmService.selectCarById(id);
            return render(car);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 根据主键获取异常信息
     *
     * @auther: lixianhua
     * @date: 2020/4/1 14:29
     * @param:
     * @return:
     */
    @GetMapping(value = "/abnormalInfo/{id}")
    public Map<String, Object> queryAbnormalInfo(@PathVariable("id") Integer id) {
        try {
            CaptureAbnormal abnormal = alarmService.selectAbnormalById(id);
            return render(abnormal);
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
    /**
     * 功能描述: 获取操作人员集合
     * @auther: lixianhua
     * @date: 2020/5/7 15:26
     * @param:
     * @return:
     */
    @GetMapping(value = "/operateList/{type}")
    public Map<String, Object> getOperateList(@PathVariable("type") String type) {
        try {
            List<HtUser> list = alarmService.getOperateList(type);
            return render(list);
        } catch (Exception e) {
            return failRender(e);
        }
    }

}
