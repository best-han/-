package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.CarGroupDetail;
import com.windaka.suizhi.manageport.service.CarGroupDetailService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CarGroupDetailController  extends BaseController {

    @Autowired
    private CarGroupDetailService carGroupDetailService;
    @Autowired
    private BaseServiceImpl baseService;

    @GetMapping("/car/group/detail")
    public Map<String,Object> selectCarGroupDetailList(@RequestParam Map<String,Object> params){
        try {
            Map resultMap=carGroupDetailService.selectCarGroupDetailList(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.selectCarGroupDetailList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PostMapping("/car/group/detail")
    public Map<String, Object> insertCarGroupDetail(@RequestBody Map<String,Object> params) {
        try {
            String innerParams= JSON.toJSONString(params);
            carGroupDetailService.insertCarGroupDetail((List)params.get("carGroupDetails"));
            baseService.senderMessage("POST","/car/group/detail",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.insertCarGroupDetail,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PutMapping("/car/group/detail")
    public Map<String, Object> updateCarGroupDetailById(@RequestBody CarGroupDetail carGroupDetail) {
        try {
            String innerParams=JSON.toJSONString(carGroupDetail);
            carGroupDetailService.updateCarGroupDetailById(carGroupDetail);
            baseService.senderMessage("PUT","/car/group/detail",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.updateCarGroupDetailById,参数：{},异常信息{}]", carGroupDetail, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/car/group/detail/{id}")
    public Map<String, Object> deleteCarGroupDetailById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            carGroupDetailService.deleteCarGroupDetailById(Integer.parseInt(id));
            baseService.senderMessage("DELETE","/car/group/detail",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.deleteCarGroupDetailById,参数：{},异常信息{}]", id, e.getMessage());
            return failRender(e);
        }
    }

}
