package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.FaceGroupDetail;
import com.windaka.suizhi.manageport.service.FaceGroupDetailService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class FaceGroupDetailController  extends BaseController {
    @Autowired
    private FaceGroupDetailService faceGroupDetailService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 布控明细 新增 ygy
     * */
    @PostMapping("/face/group/detail")
    public Map<String, Object> insertFaceGroupDetail(@RequestBody Map<String, Object> faceGroupDetails) {
        try {
            String innerParams= JSON.toJSONString(faceGroupDetails);
            faceGroupDetailService.insertFaceGroupDetail((List) faceGroupDetails.get("faceGroupDetails"));
            baseService.senderMessage("POST","/face/group/detail",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceGroupDetailController.insertFaceGroupDetail,参数：{},异常信息{}]", faceGroupDetails, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 布控明细 ygy
     * */
    @DeleteMapping("/face/group/detail/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") String id) {
        try {
            String innerParams=id;
            faceGroupDetailService.deleteById(id);
            baseService.senderMessage("DELETE","/face/group/detail",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceGroupDetailController.deleteById,参数：{},异常信息{}]",id, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 布控明细 更新 ygy
     * */

    @PutMapping("/face/group/detail")
    public Map<String, Object> updateById(@RequestBody FaceGroupDetail faceGroupDetail) {
        try {
            String innerParams=JSON.toJSONString(faceGroupDetail);
            faceGroupDetailService.updateById(faceGroupDetail);
            baseService.senderMessage("PUT","/face/group/detail",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceGroupDetailController.updateById,参数：{},异常信息{}]", faceGroupDetail, e.getMessage());
            return failRender(e);
        }
    }

    /*
     布控明细 列表查询 ygy
     * */
    @GetMapping("/face/group/detail")
    public Map<String, Object> selectFaceGroupDetail(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =faceGroupDetailService.selectFaceGroupDetailList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[FaceGroupDetailController.selectFaceGroupDetail,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }


}
