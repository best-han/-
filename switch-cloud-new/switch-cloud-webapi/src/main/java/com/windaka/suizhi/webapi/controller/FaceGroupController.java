package com.windaka.suizhi.webapi.controller;

import com.windaka.suizhi.common.controller.BaseController;
//import com.windaka.suizhi.webapi.model.FaceLibrary;
import com.windaka.suizhi.webapi.service.FaceGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 人脸相关
 */
@Slf4j
@RestController
@RequestMapping("/library")
public class FaceGroupController extends BaseController {

    @Autowired
    FaceGroupService faceGroupService;

    /**
     *人脸类型上传（人脸库）
     * @param params
     * @return
     */
    @PostMapping("/addFaceGroup")
    public Map<String,Object> saveFaceGroup(@RequestBody Map<String,Object> params){
    	try{
            faceGroupService.saveFaceGroup(params);
            return render();
        }catch(Exception e){
            log.info("[FaceController.saveFaceGroup,参数：{},异常信息：{}]",params,e.getMessage());
            return failRender(e);
        }
    }
    /**
     * 人脸类型修改
     * @param params
     * @return
     */
    @PutMapping("/updateFaceGroup")
    public Map<String,Object> updateFaceGroup(@RequestBody Map<String,Object> params){
    	try{
            faceGroupService.updateFaceGroup(params);
            return render();
        }catch(Exception e){
            log.info("[FaceController.updateFaceGroup,参数：{},异常信息：{}]",params,e.getMessage());
            return failRender(e);
        }
    }
    /**
     * 人脸类型删除
     * @param id
     * @return
     */
    @DeleteMapping("/deleteFaceGroup/{id}")
    public Map<String,Object> deleteFaceGroup(@PathVariable("id") String id){
        try{
            faceGroupService.deleteFaceGroup(id);
            return render();
        }catch(Exception e){
            log.info("[FaceController.deleteFaceGroup,参数：{},异常信息：{}]",id,e.getMessage());
            return failRender(e);
        }
    }
    /**
     * 人脸类型查询
     * @param params
     * @return
     */
    @GetMapping("/selectFaceGroupList")
    public Map<String,Object> queryFaceGroups(@RequestParam Map<String,Object> params){
        try{
            Map<String,Object> map=faceGroupService.queryFaceGroups(params);
            return render(map);
        }catch(Exception e){
            log.info("[FaceController.queryFaceGroups,参数：{},异常信息：{}]",params,e.getMessage());
            return failRender(e);
        }
    }
    
  /*  @GetMapping("/selectFaceGroup")
    public Map<String,Object> selectFaceGroup(@RequestParam Map<String,Object> params){
    	try{
    		FaceLibrary faceLibrary=faceGroupService.selectFaceGroup(params);
    		return render(faceLibrary);
    	}catch(Exception e){
    		log.info("[FaceController.selectFaceGroup,参数：{},异常信息：{}]",params,e.getMessage());
    		return failRender(e);
    	}
    }*/
}
