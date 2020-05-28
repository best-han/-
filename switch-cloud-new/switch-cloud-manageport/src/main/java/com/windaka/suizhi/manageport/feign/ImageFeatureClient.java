package com.windaka.suizhi.manageport.feign;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.config.FeignHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "python-face-server", configuration = FeignHeaderInterceptor.class)
public interface ImageFeatureClient {

    /**
     *
     * 提取正常人脸图像特征
     * 传参  params  :( record_id,face_id,image_path)
     *解释：记录id +  person_code (云端生成) +  image路径
     *
     *
     * @description： 基础图像  返回 字符串 ===》(recordid ,faceid ,feature ,type )
     *解释： 记录id +person_code+ feature +type
     * (type=0 陌生人脸中无此模板 返回云端生成person_code  云端负责添加到正常人脸库
     * type=1 陌生人脸中有模板 并返回对应的personCode（list）  云端根据personCode 将其删除   并将云端生成的person_code + feature  添加到正常人脸库
     * type= 2未提取特征)
     *
     */
    @GetMapping("/feat")
    String getBaseImageFeature(@RequestParam Map<String, Object> params) throws OssRenderException;

    /**
     *
     * 提取抓拍人脸图像特征信息
     * 传参  params  :(record_id,image_path)
     * 解释：记录id +  image路径
     *
     * @description： 抓拍图像  返回 字符串 ===》(recordid , faceid , feature , type ,sim )
     *  解释： 记录id  +  person_code +  feature  + type(0 正常人  1 陌生人  2 未识别  3 异常)  + similarity
     *    person_code (对比为正常人：返回正常人脸库对应person_code    对比为陌生人：返回陌生人脸库对应person_code   未识别：算法端生成person_code 云端负责将其添加到陌生人脸库 )
     */
    @GetMapping("/compare")
    String getCapImageFeature(@RequestParam Map<String, Object> params) throws OssRenderException;

}
