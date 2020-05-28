package com.windaka.suizhi.manageport.controller;


import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.CaptureAbnormal;
import com.windaka.suizhi.manageport.service.CaptureAbnormalService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CaptureAbnormalController extends BaseController {
    @Autowired
    private CaptureAbnormalService captureAbnormalService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 异常行为 新增 ygy
     * */
    @PostMapping("/capture/abnormal")
    public Map<String, Object> insertCaptureAbnormal(@RequestBody Map<String, Object> captureAbnormal) {
        try {
            String innerParams= JSON.toJSONString(captureAbnormal);
            captureAbnormalService.insertCaptureAbnormal((String)captureAbnormal.get("communityCode"),(List) captureAbnormal.get("captureAbnormalList"));
            baseService.senderMessage("POST","/capture/abnormal",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureAbnormalController.insertCaptureAbnormal,参数：{},异常信息{}]", captureAbnormal, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 异常行为 删除 ygy
     * */
    @DeleteMapping("/capture/abnormal/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            captureAbnormalService.deleteById(id);
            baseService.senderMessage("DELETE","/capture/abnormal",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureAbnormalController.deleteById,参数：{},异常信息{}]",id, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 异常行为 更新 ygy
     * */

    @PutMapping("/capture/abnormal")
    public Map<String, Object> updateById(@RequestBody CaptureAbnormal captureAbnormal) {
        try {
            String innerParams=JSON.toJSONString(captureAbnormal);
            captureAbnormalService.updateById(captureAbnormal);
            baseService.senderMessage("PUT","/capture/abnormal",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[CaptureAbnormalController.updateById,参数：{},异常信息{}]", captureAbnormal, e.getMessage());
            return failRender(e);
        }
    }

    /*
     *异常行为 列表查询 ygy
     * */
    @GetMapping("/capture/abnormal")
    public Map<String, Object> selectCaptureAbnormalList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =captureAbnormalService.selectCaptureAbnormalList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BaseCarController.selectCarList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }
}
