package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.BasePet;
import com.windaka.suizhi.manageport.service.BasePetService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class BasePetController extends BaseController {
    @Autowired
    private BasePetService basePetService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 宠物 新增 ygy
     * */
    @PostMapping("/pet")
    public Map<String, Object> insertPets(@RequestBody Map<String, Object> pets) {
        try {
            String innerParams= JSON.toJSONString(pets);
            basePetService.insertPets(pets.get("communityCode").toString(),(List) pets.get("pets"));
            baseService.senderMessage("POST","/pet",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePetController.insertPets,参数：{},异常信息{}]", pets, e.getMessage());
            return failRender(e);
        }
    }

    /*
     *宠物 ygy
     * */
    @DeleteMapping("/pet/{code}")
    public Map<String, Object> deleteByCode(@PathVariable("code") String code) {
        try {
            String innerParams=code;
            basePetService.deleteByCode(code);
            baseService.senderMessage("DELETE","/pet",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePetController.deleteByCode,参数：{},异常信息{}]",code, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 宠物 更新 ygy
     * */

    @PutMapping("/pet")
    public Map<String, Object> updateByCode(@RequestBody BasePet basePet) {
        try {
            String innerParams=JSON.toJSONString(basePet);
            basePetService.updateByCode(basePet);
            baseService.senderMessage("PUT","/pet",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePetController.updateByCode,参数：{},异常信息{}]", basePet, e.getMessage());
            return failRender(e);
        }
    }

    /*
     *宠物 列表查询 ygy
     * */
    @GetMapping("/pet")
    public Map<String, Object> selectPetsList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =basePetService.selectPetsList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BasePetController.selectPetsList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }


}
