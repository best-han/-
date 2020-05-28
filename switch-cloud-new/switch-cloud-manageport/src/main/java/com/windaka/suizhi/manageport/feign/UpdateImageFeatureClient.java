package com.windaka.suizhi.manageport.feign;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.config.FeignHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "police-face-server", configuration = FeignHeaderInterceptor.class)
public interface UpdateImageFeatureClient {

    /**
     * @author ：ygy
     * @date   ：2020/5/8 上午9:47
     * @description：  公安端更新特征库 通知算法更新
     */


    /*
    * 公安端新增人脸库
    * 传参    face_type   face_id   feature
    * 解释: face_type  (1: 正常人脸库  2:陌生人脸库)   face_id (personCode)
    * */
    @PostMapping("/cloud_add")
    String insertImageFeature(@RequestBody Map<String, Object> params) throws OssRenderException;

    /*
     * 公安端删除人脸库
     * 传参    face_type  face_id
     * 解释: face_type  (1: 正常人脸库  2:陌生人脸库)   face_id (personCode)
     * */
    @PostMapping("/cloud_delete")
    String deleteImageFeature(@RequestBody Map<String, Object> params) throws OssRenderException;
}
