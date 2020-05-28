package com.windaka.suizhi.webapi.feign;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.config.FeignHeaderInterceptor;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "police-face-server", configuration = FeignHeaderInterceptor.class)
public interface ImageFeatureClient {

    /**
     * 功能描述: 添加布控人员
     * @auther: lixianhua
     * @date: 2020/4/17 9:39
     * @param: 
     * @return: 
     */
    @PostMapping("/add")
    String getBaseImageFeature(@RequestBody Map<String, Object> params) throws OssRenderException;

    @PostMapping("/delete")
    String deleteFeature(@RequestParam("face_id") String face_id);


}
