package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.CaptureDevice;
import com.windaka.suizhi.manageport.service.CaptureDeviceService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CaptureDeviceController  extends BaseController {
    @Autowired
    private CaptureDeviceService captureDeviceService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 设备 新增 ygy
     * */
    @PostMapping("/device")
    public Map<String, Object> insertDevice(@RequestBody Map<String, Object> devices) {
        try {
            String innerParams= JSON.toJSONString(devices);
            captureDeviceService.insertDevice(devices.get("communityCode").toString(),(List) devices.get("devices"));
            baseService.senderMessage("POST","/device",innerParams);

            return render();
        } catch (Exception e) {
            log.info("[CaptureDeviceController.insertDevice,参数：{},异常信息{}]", devices, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 设备 删除 ygy
     * */
    @DeleteMapping("/device/{code}")
    public Map<String, Object> deleteByCode(@PathVariable("code") String code) {
        try {
            String innerParams=code;
            captureDeviceService.deleteByCode(code);
            baseService.senderMessage("DELETE","/device",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureDeviceController.deleteByCode,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 设备 更新 ygy
     * */

    @PutMapping("/device")
    public Map<String, Object> updateByCode(@RequestBody CaptureDevice captureDevice) {
        try {
            String innerParams=JSON.toJSONString(captureDevice);
            captureDeviceService.updateByCode(captureDevice);
            baseService.senderMessage("PUT","/device",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureDeviceController.updateByCode,参数：{},异常信息{}]", captureDevice, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 设备 列表查询 ygy
     * */
    @GetMapping("/device")
    public Map<String, Object> selectDeviceList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =captureDeviceService.selectDeviceList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CaptureDeviceController.selectDeviceList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

}
