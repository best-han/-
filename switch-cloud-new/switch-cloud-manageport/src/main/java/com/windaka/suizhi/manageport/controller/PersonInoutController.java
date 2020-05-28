package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.PersonInoutService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class PersonInoutController  extends BaseController {

    @Autowired
    private PersonInoutService personInoutService;
    @Autowired
    private BaseServiceImpl baseService;

    @GetMapping("/person/capture/access")
    public Map<String,Object> selectPersonInoutList(@RequestParam Map<String,Object> params){
        try {
            Map<String, Object> map = personInoutService.selectPersonInoutList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CaptureDeviceController.selectPersonInoutList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @PostMapping("/person/capture/access")
    public Map<String,Object> insertPersonInout(@RequestBody Map<String,Object> params){
        try {
            String innerParams= JSON.toJSONString(params);
            personInoutService.insertPersonInout((String)params.get("communityCode"),(List)params.get("lists"),(List)params.get("resultMapList"));
            baseService.senderMessage("POST","/person/capture/access",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseCommunityController.insertPersonInout,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/person/capture/access/{id}")
    public Map<String, Object> deletePersonInoutById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            personInoutService.deletePersonInoutById(Integer.parseInt(id));
            baseService.senderMessage("DELETE","/person/capture/access",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureDeviceController.deletePersonInoutById,参数：{},异常信息{}]", id, e.getMessage());
            return failRender(e);
        }
    }
}
