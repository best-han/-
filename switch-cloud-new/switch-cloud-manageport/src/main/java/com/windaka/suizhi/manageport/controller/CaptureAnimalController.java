package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.CaptureAnimalService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CaptureAnimalController  extends BaseController {
    @Autowired
    private CaptureAnimalService captureAnimalService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 抓拍宠物 新增 ygy
     * */
    @PostMapping("/pet/capture")
    public Map<String, Object> insertCapturePets(@RequestBody Map<String, Object> capturePets) {
        try {
            String innerParams= JSON.toJSONString(capturePets);
            captureAnimalService.insertCapturePets(capturePets.get("communityCode").toString(),(List) capturePets.get("capturePets"));
            baseService.senderMessage("POST","/pet/capture",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureAnimalController.insertCapturePets,参数：{},异常信息{}]", capturePets, e.getMessage());
            return failRender(e);
        }
    }

    /*
     *抓拍宠物 删除 ygy
     * */
    @DeleteMapping("/pet/capture/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            captureAnimalService.deleteById(id);
            baseService.senderMessage("DELETE","/pet/capture",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureAnimalController.deleteById,参数：{},异常信息{}]",id ,e.getMessage());
            return failRender(e);
        }
    }



    /*
     *抓拍宠物 列表查询 ygy
     * */
    @GetMapping("/pet/capture")
    public Map<String, Object> selectCapturePetsList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =captureAnimalService.selectCapturePetsList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[CaptureAnimalController.selectCapturePetsList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }


}
