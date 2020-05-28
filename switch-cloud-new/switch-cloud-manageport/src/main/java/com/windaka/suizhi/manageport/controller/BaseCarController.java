package com.windaka.suizhi.manageport.controller;


import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.BaseCar;
import com.windaka.suizhi.manageport.service.BaseCarService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class BaseCarController extends BaseController {
    @Autowired
    private BaseCarService baseCarService;

    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 车辆 新增 ygy
     * */
    @PostMapping("/car")
    public Map<String, Object> insertCar(@RequestBody Map<String, Object> cars) {
        try {
            String innerParams=JSON.toJSONString(cars);
            baseCarService.insertCar(cars.get("communityCode").toString(),(List) cars.get("lists"));
            baseService.senderMessage("POST","/car", innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseCarController.insertCar,参数：{},异常信息{}]", cars, e.toString());
            return failRender(e);
        }
    }

    /*
     * 车辆删除 ygy
     * */
    @DeleteMapping("/car")
    public Map<String, Object> deleteByNum(@RequestBody Map<String,Object> num) {
        try {
            String innerParams=JSON.toJSONString(num);
            baseCarService.deleteByNum(num.get("num").toString());
            baseService.senderMessage("DELETEWITHBODY","/car/delete",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseCarController.deleteByNum,参数：{},异常信息{}]",num, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 车辆 更新 ygy
     * */

    @PutMapping("/car")
    public Map<String, Object> updateByCode(@RequestBody BaseCar baseCar) {
        try {
            String innerParams=JSON.toJSONString(baseCar);
            baseCarService.updateByNum(baseCar);
            baseService.senderMessage("PUT","/car",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseCarController.updateByNum,参数：{},异常信息{}]", baseCar, e.getMessage());
            return failRender(e);
        }
    }

    /*
     *车辆 列表查询 ygy
     * */
    @GetMapping("/car")
    public Map<String, Object> selectDeviceList(@RequestParam Map<String, Object> params) {
        try {
            String innerParams=JSON.toJSONString(params);
            Map<String, Object> map =baseCarService.selectCarList(params);
            baseService.senderMessage("GET","/car",innerParams);
            return render(map);
        } catch (Exception e) {
            log.info("[BaseCarController.selectCarList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }
}
