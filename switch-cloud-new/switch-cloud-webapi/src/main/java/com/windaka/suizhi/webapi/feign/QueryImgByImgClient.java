package com.windaka.suizhi.webapi.feign;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.config.FeignHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "police-face-server",configuration = FeignHeaderInterceptor.class)
public interface QueryImgByImgClient {

    
    /**
     * @author ：ygy
     * @date   ：2020/4/11 下午8:49
     * @description：  抓拍人员【以图搜图】 图像查询
     *
     * 参数：  图像base64 face_image
     * 返回      (正常人特征表中personCode) ; (陌生人特征表中personCode) ; (犯罪人特征表中personCode)  ; type
     * 解释： type=0 未识别(人脸库中未找到此人脸特征)
     *      type=1  （人脸库中找到此人脸特征，并返回特征对应的person_code）
     *      type=2 异常
     */
    @PostMapping("/compare")
    String getPersonCodeByImg(@RequestBody Map<String, Object> params) throws OssRenderException;
    
}
