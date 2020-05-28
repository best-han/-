package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.SysDic;
import com.windaka.suizhi.manageport.service.SysDicService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class SysDicController  extends BaseController {
    @Autowired
    private SysDicService sysDicService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 字典 新增 ygy
     * */
    @PostMapping("/dic")
    public Map<String, Object> insertSysDic(@RequestBody Map<String, Object> sysDic) {
        try {
            String innerParams= JSON.toJSONString(sysDic);
            sysDicService.insertSysDic((List) sysDic.get("dic"));
            baseService.senderMessage("POST","/dic",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[SysDicController.insertSysDic,参数：{},异常信息{}]", sysDic, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 字典 删除 ygy
     * */
    @DeleteMapping("/dic/{code}")
    public Map<String, Object> deleteByCode(@PathVariable("code") String code) {
        try {
            String innerParams=code;
            sysDicService.deleteByCode(code);
            baseService.senderMessage("DELETE","/dic",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[SysDicController.deleteByCode,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 字典 更新 ygy
     * */
    @PutMapping("/dic")
    public Map<String, Object> updateByCode(@RequestBody SysDic sysDic) {
        try {
//            System.out.println(sysDic.getDicCode() + "  " + sysDic.getDicName());
            String innerParams=JSON.toJSONString(sysDic);
            sysDicService.updateByCode(sysDic);
            baseService.senderMessage("PUT","/dic",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[SysDicController.updateByCode,参数：{},异常信息{}]", sysDic, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 字典 列表查询 ygy
     * */
    @GetMapping("/dic")
    public Map<String, Object> selectSysDicList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map = sysDicService.selectSysDicList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[SysDicController.selectSysDicList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }
}
