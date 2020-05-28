package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.BaseCommunityMapper;
import com.windaka.suizhi.manageport.dao.CaptureAnimalMapper;
import com.windaka.suizhi.manageport.dao.CaptureDeviceMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.model.BaseCommunity;
import com.windaka.suizhi.manageport.model.CaptureAnimal;
import com.windaka.suizhi.manageport.model.CaptureDevice;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.CaptureAnimalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class  CaptureAnimalServiceImpl implements CaptureAnimalService {
    @Autowired
    private CaptureAnimalMapper captureAnimalMapper;
    @Autowired
    private CaptureDeviceMapper captureDeviceMapper;
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;
    @Override
    public void insertCapturePets(String communityCode, List capturePets) throws OssRenderException, IOException {
        if (capturePets!=null){
            Iterator i=capturePets.iterator();
            while (i.hasNext()){
                Map<String,Object> t=(Map<String,Object>)i.next();
                //判断必要数据是否为空
                if (communityCode == null || StringUtils.isEmpty(communityCode)) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区编码不能为空");
                }
                if (t.get("deviceCode") == null || StringUtils.isEmpty(t.get("deviceCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "抓拍设备编号不能为空");
                }
                if (t.get("capImage") == null || StringUtils.isEmpty(t.get("capImage").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "抓拍图像不能为空");
                }

                Map params;

                //查询添加关联数据
                t.put("communityCode",communityCode);
                //查询绑定小区 是否存在
                params = new HashMap<>();
                params.put("code", t.get("communityCode"));
                List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
                if (baseCommunityList.size() > 0) {
                    String communityName = baseCommunityList.get(0).getName();
                    t.put("communityName", communityName);//若存在  存小区名称
                } else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, communityCode + "小区不存在");
                }

                //添加绑定设备信息
                params=new HashMap();
                params.put("code",t.get("deviceCode"));
                List<CaptureDevice> captureDeviceList=captureDeviceMapper.selectDeviceList(params);
                if (captureDeviceList.size()>0){
                    CaptureDevice captureDevice=captureDeviceList.get(0);
                    t.put("deviceName",captureDevice.getName());
                    t.put("deviceType",captureDevice.getType());
                    t.put("deviceTypeName",captureDevice.getTypeName());
                    t.put("deviceLocation",captureDevice.getLocation());
                }else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED,  t.get("deviceCode")+ "设备不存在");
                }

                //抓拍宠物信息
//                if (t.get("capBreed") != null && !StringUtils.isEmpty(t.get("capBreed").toString())) {
//                    t.put("capBreedName", DicUtils.getValue("base_pet|breed", t.get("capBreed").toString(), "base_pet|breed,字典无此代码"));
//                }
//                if (t.get("capColor") != null && !StringUtils.isEmpty(t.get("capColor").toString())) {
//                    t.put("capColorName", DicUtils.getValue("capture_animal|cap_color", t.get("capColor").toString(), "capture_animal|cap_color,字典无此代码"));
//                }

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("capture_animal");
                if(t.get("capImage")!=null && !StringUtils.isEmpty(t.get("capImage").toString())){

//                    String capImage=t.get("capImage").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(capImage.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(capImage);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("capture_animal", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                    //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("capImage", PicUtil.getPicRelativePath(date)+ fileName);

                    Map<String,Object> imageMap=imageToFdfsService.saveImageToFdfs(t.get("capImage").toString());
                    t.put("capImage",imageMap.get("thumbFullPath"));
                }



                CaptureAnimal captureAnimal = JSON.parseObject(JSON.toJSONString(t), CaptureAnimal.class);//Map 转 实体
                captureAnimalMapper.insertCapturePets(captureAnimal);
                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("capture_animal",null,nextId+"","insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteById(String id) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<CaptureAnimal> captureAnimalList= captureAnimalMapper.selectCapturePetList(params);
        if (captureAnimalList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, id+ "抓拍宠物不存在");
        }
        captureAnimalMapper.deleteById(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("capture_animal",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectCapturePetsList(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        List<CaptureAnimal> list= captureAnimalMapper.selectCapturePetList(params);
        map.put("list", list);
        return map;
    }
}
