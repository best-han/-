package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.BasePerson;
import com.windaka.suizhi.manageport.service.BasePersonService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class BasePersonController extends BaseController {

    @Autowired
    private BasePersonService basePersonService;
    @Autowired
    private BaseServiceImpl baseService;

    @PostMapping("/person")
    public Map<String, Object> insertPerson(@RequestBody Map<String, Object> params) {
        try {
            String innerParams= JSON.toJSONString(params);
            basePersonService.insertPerson((String)params.get("communityCode"),(List)params.get("persons"), (List)params.get("resultMapList"));
            baseService.senderMessage("POST","/person",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.insertPerson,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @GetMapping("/person")
    public Map<String, Object> selectPersonList(@RequestParam Map<String, Object> params) {
        try {
            Map resultMap=basePersonService.selectPersonList(params);
            return render(resultMap);
        } catch (Exception e) {
            log.info("[BasePersonController.selectPersonList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PutMapping("/person")
    public Map<String, Object> updatePersonByCode(@RequestBody Map<String, Object> params){
        try {
            basePersonService.updatePersonByCode((Map)params.get("basePersonMap"),(Map)params.get("parseResultMap"));
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.updatePersonByCode,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/person/{code}")
    public Map<String, Object> deletePersonByCode(@PathVariable("code") String code){
        try {
            String innerParams=code;
            basePersonService.deletePersonByCode(code);
            baseService.senderMessage("DELETE","/person",code);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.deletePersonByCode,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }

    @PostMapping("/person/house")
    public Map<String, Object> insertPersonHouse(@RequestBody Map<String, Object> params){
        try {
            String innerParams=JSON.toJSONString(params);
            basePersonService.insertPersonHouse((List)params.get("list"));
            baseService.senderMessage("POST","/person/house",innerParams);
            return render();
        }catch (Exception e) {
            log.info("[BasePersonController.insertPersonHouse,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PutMapping("/person/house")
    public Map<String, Object> updatePersonHouseByCode(@RequestBody BasePerson basePerson){
        try {
            String innerParams=JSON.toJSONString(basePerson);
            basePersonService.updatePersonHouseByCode(basePerson);
            baseService.senderMessage("PUT","/person/house",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.updatePersonHouseByCode,参数：{},异常信息{}]", basePerson, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/person/house")
    public Map<String, Object> deletePersonHouseByCode(@RequestBody BasePerson basePerson){
        try {
            String innerParams=JSON.toJSONString(basePerson);
            basePersonService.deletePersonHouseByCode(basePerson);
            baseService.senderMessage("DELETEWITHBODY","/person/house",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BasePersonController.deletePersonHouseByCode,参数：{},异常信息{}]", basePerson, e.getMessage());
            return failRender(e);
        }
    }

    @PostMapping("/person/single")
    public Map<String, Object> insertPersonSingle(@RequestBody Map<String, Object> params){
        try {
            String innerParams= JSON.toJSONString(params);
            basePersonService.insertPersonSingle((String)params.get("communityCode"),(List)params.get("persons"), (List)params.get("resultMapList"));
            baseService.senderMessage("POST","/person/single",innerParams);
            return render();
        }catch (Exception e){
            log.info("[BasePersonController.insertPersonSingle,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }
}
