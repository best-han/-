package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.FaceOwnerInfoService;
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
public class FaceOwnerInfoController extends BaseController {

    @Autowired
    private FaceOwnerInfoService faceOwnerInfoService;

    @Autowired
    private BaseServiceImpl baseService;

    /**
     * @author ：ygy
     * @date   ：2020/3/30 上午8:06
     * @description：   正常人脸库添加
     */

    @PostMapping("/face/owner/info")
    public Map<String, Object> insertFaceOwnerInfo(@RequestBody Map<String, Object> params) {
        try {
            String innerParams= JSON.toJSONString(params);
            faceOwnerInfoService.insertFaceOwnerInfo((List) params.get("list"));
            baseService.senderMessage("POST","/face/owner/info", innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceOwnerInfoController.FaceOwnerInfo,参数：{},异常信息{}]", params, e.toString());
            return failRender(e);
        }
    }

    /**
     * @author ：ygy
     * @date   ：2020/3/30 上午8:07
     * @description：  正常人脸库 删除
     */
    @DeleteMapping("/face/owner/info")
    public Map<String, Object> deleteFaceOwnerInfo(@RequestBody Map<String, Object> params) {
        try {
            String innerParams= JSON.toJSONString(params);
            faceOwnerInfoService.deleteFaceOwnerInfo(params);
            baseService.senderMessage("DELETE","/face/owner/info", innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceOwnerInfoController.FaceOwnerInfo,参数：{},异常信息{}]", params, e.toString());
            return failRender(e);
        }
    }
}
