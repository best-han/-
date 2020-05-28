package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.service.FaceStrangerInfoService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FaceStrangerInfoController extends BaseController {

    @Autowired
    private FaceStrangerInfoService faceStrangerInfoService;

    @Autowired
    private BaseServiceImpl baseService;
    /**
     * @author ：ygy
     * @date   ：2020/3/30 上午8:06
     * @description：   陌生人脸库 上传
     */

    @PostMapping("/face/stranger/info")
    public Map<String, Object> insertFaceStrangerInfo(@RequestBody Map<String, Object> params) {
        try {
            String innerParams= JSON.toJSONString(params);
            faceStrangerInfoService.insertFaceStrangerInfo((List) params.get("list"));
            baseService.senderMessage("POST","/face/stranger/info", innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceStrangerInfoController.insertFaceStrangerInfo,参数：{},异常信息{}]", params, e.toString());
            return failRender(e);
        }
    }

}
