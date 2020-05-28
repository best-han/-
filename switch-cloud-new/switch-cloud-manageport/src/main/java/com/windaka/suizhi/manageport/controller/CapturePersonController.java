package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.CapturePersonService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CapturePersonController  extends BaseController {

    @Autowired
    private CapturePersonService capturePersonService;
    @Autowired
    private BaseServiceImpl baseService;

    @GetMapping("/person/capture")
    public Map<String,Object> selectCapturePersonList(@RequestParam Map<String, Object> params){
        try {
            Map<String, Object> map = capturePersonService.selectCapturePersonList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CaptureDeviceController.selectCapturePersonList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PostMapping("/person/capture")
    public Map<String,Object> insertCapturePerson(@RequestBody Map<String, Object> params){
        try {
            log.info("添加人脸信息，抓拍小区为:"+ (String)params.get("communityCode"));
            capturePersonService.insertCapturePerson((String)params.get("communityCode"),(List)params.get("lists"), (List)params.get("resultMapList"));
            return render();
        } catch (Exception e) {
            log.info("[CapturePersonController.insertCapturePerson,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/person/capture/{id}")
    public Map<String,Object> deleteCapturePersonById(@PathVariable("id") String id){
        try {
            String innerParams=id;
            capturePersonService.deleteCapturePersonById(Integer.parseInt(id));
            baseService.senderMessage("DELETE","/person/capture",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CapturePersonController.deleteCapturePersonById,参数：{},异常信息{}]", id, e.getMessage());
            return failRender(e);
        }
    }
}
