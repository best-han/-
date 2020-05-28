package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.constants.CommonConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.FileUploadUtil;
import com.windaka.suizhi.common.utils.PicUtil;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.dao.FaceCriminalInfoMapper;
import com.windaka.suizhi.manageport.model.FaceCriminalInfo;
import com.windaka.suizhi.manageport.service.FaceCriminalInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FaceCriminalInfoServiceImpl implements FaceCriminalInfoService {
    @Autowired
    private FaceCriminalInfoMapper faceCriminalInfoMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Override
    public void insertFaceCriminalInfo(List faceCriminalInfos) throws OssRenderException {
        if (faceCriminalInfos!=null){
            Iterator i=faceCriminalInfos.iterator();
            while(i.hasNext()){
                Map<String,Object> t=(Map<String, Object>) i.next();
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("face_criminal_info");
                //图片上传
                if(t.get("personImage")!=null && !StringUtils.isEmpty(t.get("personImage").toString())){

                    String personImg=t.get("personImage").toString();
//                    byte[] byteArr= PicUtil.stringToInputStream(personImg.split(",")[1]);
                    byte[] byteArr= PicUtil.stringToInputStream(personImg);
                    //rename pic
                    String fileName = PicUtil.getPicName("face_criminal_info", nextId);

                    //封装访问路径：年/月/日
                    Date date=new Date();
                    //图片放入打包路径
                    FileUploadUtil.inputStreamToLocalFile(byteArr,
                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+ PicUtil.getPicRelativePath(date),fileName);

                    t.put("personImage", PicUtil.getPicRelativePath(date)+ fileName);
                }
                FaceCriminalInfo faceCriminalInfo= JSON.parseObject(JSON.toJSONString(t),FaceCriminalInfo.class);
                faceCriminalInfoMapper.insert(faceCriminalInfo);
            }
        }
    }

    @Override
    public void deleteFaceCriminalInfo(Map<String, Object> params) throws OssRenderException {
        faceCriminalInfoMapper.delete(params);
    }
}
