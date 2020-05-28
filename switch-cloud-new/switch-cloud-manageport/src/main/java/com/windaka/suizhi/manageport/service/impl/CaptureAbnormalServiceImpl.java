package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.BaseCommunityMapper;
import com.windaka.suizhi.manageport.dao.CaptureAbnormalMapper;
import com.windaka.suizhi.manageport.dao.CaptureDeviceMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.model.BaseCommunity;
import com.windaka.suizhi.manageport.model.CaptureAbnormal;
import com.windaka.suizhi.manageport.model.CaptureDevice;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.CaptureAbnormalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CaptureAbnormalServiceImpl  implements CaptureAbnormalService {
    @Autowired
    private CaptureAbnormalMapper captureAbnormalMapper;
    @Autowired
    private CaptureDeviceMapper captureDeviceMapper;
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;
    @Override
    public void insertCaptureAbnormal(String communityCode, List captureAbnormal) throws OssRenderException, IOException {
        if (captureAbnormal!=null){
            Iterator i=captureAbnormal.iterator();
            while (i.hasNext()){
                Map<String,Object> t=(Map<String, Object>) i.next();
                //判断必要数据是否为空
                if (communityCode == null || StringUtils.isEmpty(communityCode)) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区编码不能为空");
                }
                if (t.get("deviceCode") == null || StringUtils.isEmpty(t.get("deviceCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备编号不能为空");
                }
                if (t.get("event") == null || StringUtils.isEmpty(t.get("event").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "事件编号不能为空");
                }
                if("0".equals(t.get("event").toString())){
                    t.put("eventName","车辆占道");
                }
                //查询添加关联数据
                Map params;

                //添加社区名称
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

                //事件名称
//                t.put("eventName", DicUtils.getValue("capture_abnormal|event",t.get("event").toString(),"capture_abnormal|event字典无此编码数据"));

                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("capture_abnormal");
                //图片上传
                if(t.get("capImage")!=null && !StringUtils.isEmpty(t.get("capImage").toString())){

//                    String capImg=t.get("capImage").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(capImg.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(capImg);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("capture_abnormal", nextId);
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


                //操作人
//                String str = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
//                t.put("handelUser",loginAppUser.getUserId());

                CaptureAbnormal captureAbnormal1 = JSON.parseObject(JSON.toJSONString(t), CaptureAbnormal.class);//Map 转 实体
                captureAbnormalMapper.insertCaptureAbnormal(captureAbnormal1);
                //记录操作
                //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("capture_abnormal",null,nextId+"".toString(),"insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteById(String id) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<CaptureAbnormal> captureAbnormalList = captureAbnormalMapper.selectCaptureAbnormalList(params);
        if (captureAbnormalList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, id + "异常行为记录不存在");
        }
        captureAbnormalMapper.deleteById(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("capture_abnormal",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateById(CaptureAbnormal captureAbnormal) throws OssRenderException {

        /*
        * 修改 只需需改 事件状态  及记录操作人、操作时间
        * */
//        //判断必要数据是否为空
//        if (captureAbnormal.getCommunityCode() == null || StringUtils.isEmpty(captureAbnormal.getCommunityCode())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区编码不能为空");
//        }
//        if (captureAbnormal.getDeviceCode() == null || StringUtils.isEmpty(captureAbnormal.getDeviceCode() .toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备编号不能为空");
//        }
//        if (captureAbnormal.getEvent() == null || StringUtils.isEmpty(captureAbnormal.getEvent().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "事件编号不能为空");
//        }

        //操作人
//        String str = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        captureAbnormal.setHandelUser(loginAppUser.getUserId());

        captureAbnormalMapper.updateById(captureAbnormal);
        //记录操作
        //LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("capture_abnormal",null,captureAbnormal.getId().toString(),"update",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectCaptureAbnormalList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<CaptureAbnormal> list= captureAbnormalMapper.selectCaptureAbnormalList(params);
        map.put("list", list);
        return map;
    }
}
