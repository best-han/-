package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.BaseParking;
import com.windaka.suizhi.manageport.service.BaseParkingService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BaseParkingController extends BaseController {
    @Autowired
    private BaseParkingService baseParkingService;
    @Autowired
    private BaseServiceImpl baseService;

    /**
     *
     *@description: 停车位增加
     *@author: zdq
     *@time: 4/13/20 8:12 PM
     *
     */
    @PostMapping("/parking")
    public Map<String, Object> insertParking(@RequestBody Map<String, Object> params) {
        try {
            String innerParams= JSON.toJSONString(params);
            baseParkingService.insertBaseParking(params.get("communityCode").toString(),(List) params.get("lists"));
            baseService.senderMessage("POST","/parking", innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseParkingController.insertParking,参数：{},异常信息{}]", params, e.toString());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 停车位修改
     *@author: zdq
     *@time: 4/13/20 8:12 PM
     *
     */
    @PutMapping("/parking")
    public Map<String, Object> updateParking(@RequestBody BaseParking baseParking) {
        try {
            String innerParams=JSON.toJSONString(baseParking);
            baseParkingService.updateBaseParking(baseParking);
            baseService.senderMessage("PUT","/parking",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseParkingController.updateParking,参数：{},异常信息{}]", baseParking, e.getMessage());
            return failRender(e);
        }
    }

    /**
     *
     *@description: 停车位删除
     *@author: zdq
     *@time: 4/13/20 8:14 PM
     *
     */
    @DeleteMapping("/parking/{code}")
    public Map<String, Object> deleteParking(@PathVariable("code") String code){
        try {
            String innerParams=code;
            baseParkingService.deleteBaseParking(code);
            baseService.senderMessage("DELETE","/parking",code);
            return render();
        } catch (Exception e) {
            log.info("[BaseParkingController.deleteParking,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }
}
