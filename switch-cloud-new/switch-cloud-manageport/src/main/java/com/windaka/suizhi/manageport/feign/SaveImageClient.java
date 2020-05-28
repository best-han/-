package com.windaka.suizhi.manageport.feign;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.config.FeignHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "image-server", configuration = FeignHeaderInterceptor.class)
public interface SaveImageClient {


    /**
     * @author ：ygy
     * @date   ：2020/4/13 上午10:12
     * @description：  保存图像到fastdfs
     *
     * 参数： image (图像的 base64编码)
     * 返回Map<String,Object>
     *       resultMap.put("fullPath", storePath.getFullPath());// 获取图像路径
     *         resultMap.put("thumbFullPath", thumbImageConfig.getThumbImagePath(storePath.getFullPath()));// 获取缩略图路径
     */
    @PostMapping("/image")
    Map<String,Object> saveImageToFdfs(@RequestBody String image) throws OssRenderException;
}
