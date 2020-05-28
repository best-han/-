package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.CarGroup;
import com.windaka.suizhi.manageport.service.CarGroupService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CarGroupController  extends BaseController {

    @Autowired
    private CarGroupService carGroupService;
    @Autowired
    private BaseServiceImpl baseService;

    @GetMapping("/car/group")
    public Map<String,Object> selectCarGroupList(@RequestParam Map<String,Object> params){
        try {
            Map resultMap=carGroupService.selectCarGroupList(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.selectCarGroupList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PostMapping("/car/group")
    public Map<String, Object> insertCarGroup(@RequestBody Map<String, Object> params) {
        try {
            String innerParams= JSON.toJSONString(params);
            carGroupService.insertCarGroup((List)params.get("carGroups"));
            baseService.senderMessage("POST","/car/group",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.insertCarGroup,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PutMapping("/car/group")
    public Map<String, Object> updateCarGroupByCode(@RequestBody CarGroup carGroup) {
        try {
            String innerParams=JSON.toJSONString(carGroup);
            carGroupService.updateCarGroupByCode(carGroup);
            baseService.senderMessage("PUT","/car/group",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.updateCarGroupByCode,参数：{},异常信息{}]", carGroup, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/car/group/{code}")
    public Map<String, Object> deleteCarGroupByCode(@PathVariable("code") String code) {
        try {
            String innerParams=code;
            carGroupService.deleteCarGroupByCode(code);
            baseService.senderMessage("DELETE","/car/group",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.deleteCarGroupByCode,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }

}
