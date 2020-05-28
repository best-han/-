package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.FaceGroup;
import com.windaka.suizhi.manageport.service.FaceGroupService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class FaceGroupController  extends BaseController {
    @Autowired
    private FaceGroupService faceGroupService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
     * 布控库 新增 ygy
     * */
    @PostMapping("/face/group")
    public Map<String, Object> insertFaceGroup(@RequestBody Map<String, Object> faceGroups) {
        try {
            String innerParams= JSON.toJSONString(faceGroups);
            faceGroupService.insertFaceGroup((List) faceGroups.get("faceGroups"));
            baseService.senderMessage("POST","/face/group",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceGroupController.insertFaceGroup,参数：{},异常信息{}]", faceGroups, e.getMessage());
            return failRender(e);
        }
    }

    /*
     *  布控库 删除 ygy
     * */
    @DeleteMapping("/face/group/{code}")
    public Map<String, Object> deleteByNum(@PathVariable("code") String code) {
        try {
            String innerParams=code;
            faceGroupService.deleteByCode(code);
            baseService.senderMessage("DELETE","/face/group",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceGroupController.deleteByCode,参数：{},异常信息{}]",code, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 布控库 更新 ygy
     * */

    @PutMapping("/face/group")
    public Map<String, Object> updateByCode(@RequestBody FaceGroup faceGroup) {
        try {
            String innerParams=JSON.toJSONString(faceGroup);
            faceGroupService.updateByCode(faceGroup);
            baseService.senderMessage("PUT","/face/group",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[FaceGroupController.updateByCode,参数：{},异常信息{}]", faceGroup, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 布控库列表查询 ygy
     * */
    @GetMapping("/face/group")
    public Map<String, Object> selectFaceGroupList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map =faceGroupService.selectFaceGroupList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BaseCarController.selectFaceGroupList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

}
