package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.SysDicData;
import com.windaka.suizhi.manageport.service.SysDicDataService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class SysDicDataController extends BaseController {

    @Autowired
    private SysDicDataService sysDicDataService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 字典数值 新增 ygy
     * */
    @PostMapping("/dic/data")
    public Map<String, Object> insertSysDicData(@RequestBody Map<String, Object> sysDicData) {
        try {
            String innerParams= JSON.toJSONString(sysDicData);
            sysDicDataService.insertSysDicData(sysDicData.get("dicCode").toString(),(List) sysDicData.get("data"));
            baseService.senderMessage("POST","/dic/data",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[SysDicDataController.insertSysDicData,参数：{},异常信息{}]", sysDicData, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 字典数值 删除 ygy
     * */
    @DeleteMapping("/dic/data/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            sysDicDataService.deleteById(id);
            baseService.senderMessage("DELETE","/dic/data",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[SysDicDataController.deleteById,参数：{},异常信息{}]", id, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 字典数值 更新 ygy
     * */

    @PutMapping("/dic/data")
    public Map<String, Object> updateById(@RequestBody SysDicData sysDicData) {
        try {
            String innerParams=JSON.toJSONString(sysDicData);
            sysDicDataService.updateById(sysDicData);
            baseService.senderMessage("PUT","/dic/data",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[SysDicDataController.updateById,参数：{},异常信息{}]", sysDicData, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 字典 列表查询 ygy
     * */
    @GetMapping("/dic/data")
    public Map<String, Object> selectSysDicDataList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map = sysDicDataService.selectSysDicDataList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[SysDicDataController.selectSysDicDataList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

}
