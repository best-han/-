package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.CarAccessService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CarAccessController  extends BaseController {
    @Autowired
    private CarAccessService carAccessService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 抓拍车辆出入 新增 ygy
     * */
    @PostMapping("/car/capture/access")
    public Map<String, Object> insertCarAccess(@RequestBody Map<String, Object> carAccesses) {
        try {
            String innerParams= JSON.toJSONString(carAccesses);
            carAccessService.insertCarAccess((String)carAccesses.get("communityCode"),(List) carAccesses.get("accessCars"));
            baseService.senderMessage("POST","/car/capture",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CarAccessController.insertCarAccess,参数：{},异常信息{}]", carAccesses ,e.getMessage());
            return failRender(e);
        }
    }

    /*
     *抓拍车辆出入 删除  ygy
     * */
    @DeleteMapping("/car/capture/access/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            carAccessService.deleteById(id);
            baseService.senderMessage("DELETE","/car/capture/access",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CarAccessController.deleteById,参数：{},异常信息{}]",id, e.getMessage());
            return failRender(e);
        }
    }


    /*
     *抓拍车辆出入  列表查询 ygy
     * */
    @GetMapping("/car/capture/access")
    public Map<String, Object> selectCarAccess(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =carAccessService.selectCarAccessList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CaptureCarController.selectCaptureCarList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }



}
