package com.windaka.suizhi.manageport.controller;


import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.FaceCriminalInfoService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FaceCriminalInfoController extends BaseController {
    @Autowired
    private FaceCriminalInfoService faceCriminalInfoService;
    @Autowired
    private BaseServiceImpl baseService;

    /**
     * @author ：ygy
     * @date   ：2020/3/28 上午10:11
     * @description：  犯罪人脸库插入
     */
    @PostMapping("/faceCriminalInfo")
    public Map<String,Object> insertFaceCriminalInfo(@RequestBody Map<String,Object> faceCriminalInfos){
        try{
            String innerParams= JSON.toJSONString(faceCriminalInfos);
            faceCriminalInfoService.insertFaceCriminalInfo((List)faceCriminalInfos.get("list"));
            baseService.senderMessage("POST","/faceCriminalInfo", innerParams);
            return render();
        }catch (Exception e){
            log.info("[FaceCriminalInfoController.insertFaceCriminalInfo,参数：{},异常信息{}]",faceCriminalInfos, e.getMessage());
            return failRender(e);
        }
    }

    @DeleteMapping("/faceCriminalInfo")
    public Map<String,Object> deleteFaceCriminalInfo(@RequestBody Map<String,Object> params){
        try{
            String innerParams= JSON.toJSONString(params);
            faceCriminalInfoService.deleteFaceCriminalInfo(params);
            baseService.senderMessage("DELETEWITHBODY","/faceCriminalInfo", innerParams);
            return render();
        }catch (Exception e){
            log.info("[FaceCriminalInfoController.deleteFaceCriminalInfo,参数：{},异常信息{}]",params, e.getMessage());
            return failRender(e);
        }
    }
}
