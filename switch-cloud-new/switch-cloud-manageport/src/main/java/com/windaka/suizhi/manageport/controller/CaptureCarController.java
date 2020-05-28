package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.CaptureCarService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CaptureCarController  extends BaseController {
    @Autowired
    private CaptureCarService captureCarService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 抓拍车辆 新增 ygy
     * */
    @PostMapping("/car/capture")
    public Map<String, Object> insertCaptureCar(@RequestBody Map<String, Object> captureCars) {
        try {
            String innerParams= JSON.toJSONString(captureCars);
            captureCarService.insertCaptureCar( (String)captureCars.get("communityCode"),(List) captureCars.get("captureCars"));
            baseService.senderMessage("POST","/car/capture",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureCarController.insertCaptureCar,参数：{},异常信息{}]", captureCars, e.getMessage());
            return failRender(e);
        }
    }

    /*
     *抓拍车辆 删除  ygy
     * */
    @DeleteMapping("/car/capture/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            captureCarService.deleteById(id);
            baseService.senderMessage("DELETE","/car/capture",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureCarController.deleteByIdm,参数：{},异常信息{}]",id, e.getMessage());
            return failRender(e);
        }
    }


    /*
     *抓拍车辆  列表查询 ygy
     * */
    @GetMapping("/car/capture")
    public Map<String, Object> selectCaptureCarList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =captureCarService.selectCaptureCarList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CaptureCarController.selectCaptureCarList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

}
