package com.windaka.suizhi.manageport.controller;


import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.BaseCommunity;
import com.windaka.suizhi.manageport.service.BaseCommunityService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BaseCommunityController  extends BaseController {
    @Autowired
    private BaseCommunityService baseCommunityService;

    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 小区信息 新增 ygy
     * */
    @PostMapping("/community")
    public Map<String, Object> insertCommunity(@RequestBody Map<String, Object> communities) {
        try {
            String innerParams= JSON.toJSONString(communities);
            baseCommunityService.insertCommunity((List) communities.get("communities") );
            baseService.senderMessage("POST","/community",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseCommunityController.insertCommunity,参数：{},异常信息{}]", communities, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 小区信息 删除 ygy
     * */
    @DeleteMapping("/community/{code}")
    public Map<String, Object> deleteByCode(@PathVariable("code") String code) {
        try {
            String innerParams=JSON.toJSONString(code);
            baseCommunityService.deleteByCode(code);
            baseService.senderMessage("DELETE","/community",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseCommunityController.deleteByCode,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 小区信息 更新 ygy
     * */
    @PutMapping("/community")
    public Map<String, Object> updateByCode(@RequestBody BaseCommunity baseCommunity) {
        try {
            String innerParams=JSON.toJSONString(baseCommunity);
            baseCommunityService.updateByCode(baseCommunity);
            baseService.senderMessage("PUT","/community",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseCommunityController.updateByCode,参数：{},异常信息{}]", baseCommunity, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 小区信息 列表查询 ygy
     * */
    @GetMapping("/community")
    public Map<String, Object> selectCommunityList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map = baseCommunityService.selectCommunityList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[SysDicController.selectCommunityList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/community/empty/{code}")
    public Map<String, Object> emptyCommunity(@PathVariable String code){
        try {
            String innerParams=JSON.toJSONString(code);
            baseCommunityService.emptyCommunity(code);
            baseService.senderMessage("DELETE","/community/empty/",innerParams);
            return render();
        }catch (Exception e){
            log.info("[SysDicController.emptyCommunity,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }
}
