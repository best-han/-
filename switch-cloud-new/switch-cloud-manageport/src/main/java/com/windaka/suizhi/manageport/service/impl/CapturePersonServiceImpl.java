package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.dao.auto.FaceAttentionDetailMapper;
import com.windaka.suizhi.manageport.elasticsearch.model.CapturePersonES;
import com.windaka.suizhi.manageport.elasticsearch.model.PersonInoutES;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.CapturePersonService;
import com.windaka.suizhi.manageport.util.DicUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Service
@Slf4j
public class CapturePersonServiceImpl implements CapturePersonService {

    @Autowired
    private CapturePersonMapper capturePersonMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Autowired
    private CaptureDeviceMapper captureDeviceMapper;

    @Autowired
    private BasePersonMapper basePersonMapper;

    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;

    @Autowired
    private BaseServiceImpl baseService;

    @Autowired
    private ImageFeatureServiceImpl imageFeatureService;

    @Autowired
    private FaceStrangerInfoMapper faceStrangerInfoMapper;

    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;

    @Autowired
    private FaceAttentionDetailMapper faceAttentionDetailMapper;

    @Autowired
    private BaseRoomMapper baseRoomMapper;

    @Autowired
    private UpdateImageFeatureServiceImpl updateImageFeatureService;

    @Override
    public Map<String, Object> selectCapturePersonList(Map<String, Object> params) throws OssRenderException {
        Map map = new HashMap();
        List<CapturePerson> capturePersonList = capturePersonMapper.selectCapturePersonList(params);
        map.put("list", capturePersonList);
        return map;
    }

    @Override
    public void insertCapturePerson(String communityCode, List lists, List resultMapList) throws OssRenderException, IOException {
        if (lists != null) {
            Iterator i = lists.iterator();
            Iterator ri = resultMapList.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                Map<String, Object> rt = (Map<String, Object>) ri.next();

                t.put("communityCode", communityCode);

                if (t.get("communityCode") == null || StringUtils.isEmpty(t.get("communityCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
                }
                if (t.get("captureTime") == null || StringUtils.isEmpty(t.get("captureTime").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "captureTime不能为空");
                }
                if (t.get("deviceCode") == null || StringUtils.isEmpty(t.get("deviceCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "deviceCode不能为空");
                }
                if (t.get("capPersonImage") == null || StringUtils.isEmpty(t.get("capPersonImage").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "capPersonImage不能为空");
                }
                if (t.get("capImage") == null || StringUtils.isEmpty(t.get("capImage").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "capImage不能为空");
                }
                if (t.get("video") != null && !StringUtils.isEmpty(t.get("video").toString())) {
                   t.put("capVideo",t.get("video"));
                }
                Map<String, Object> innerParams;
                //1 查询小区名称是否存在
                innerParams = new HashMap<>();
                innerParams.put("code", communityCode);
                List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(innerParams);
                if (baseCommunityList == null || baseCommunityList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "该小区不存在");
                } else {
                    BaseCommunity baseCommunity = baseCommunityList.get(0);
                    t.put("communityName", baseCommunity.getName());
                }
                //查询设备是否存在
                innerParams = new HashMap<>();
                innerParams.put("code", t.get("deviceCode"));
                List<CaptureDevice> captureDeviceList = captureDeviceMapper.selectDeviceList(innerParams);
                if (captureDeviceList == null || captureDeviceList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "该设备不存在");
                } else {
                    CaptureDevice captureDevice = captureDeviceList.get(0);
                    t.put("deviceName", captureDevice.getName());
                    t.put("deviceType", captureDevice.getType());
                    t.put("deviceTypeName", captureDevice.getTypeName());
                    t.put("deviceLocation", captureDevice.getLocation());
                    t.put("gbCode",captureDevice.getGbCode());
                    t.put("gbCodeseq",captureDevice.getGbCodeSeq());
                    t.put("dchnlRtsp",captureDevice.getDchnlRtsp());
                }
//                else
//                {
//                    //在人脸布控明细中查询人员是否存在
//                    innerParams=new HashMap<>();
//                    innerParams.put("personCode",t.get("personCode"));
//                    List<FaceGroupDetail> faceGroupDetailList=faceGroupDetailMapper.selectFaceGroupDetailList(innerParams);
//                    if(faceGroupDetailList!=null&&!faceGroupDetailList.isEmpty()){
//                        FaceGroupDetail faceGroupDetail=faceGroupDetailList.get(0);
//                        t.put("personName",faceGroupDetail.getPersonName());
//                        t.put("personPhone",faceGroupDetail.getPersonPhone());
//                        t.put("personPaperNumber",faceGroupDetail.getPersonPaperNumber());
//                    }
//                }
                //字典检索
//                //查询上报人员类型 type
//                if(t.get("type") != null && !StringUtils.isEmpty(t.get("type").toString())) {
//                    DicUtils.getValue("capture_person|type",t.get("type").toString(),"上报人员类型值非法");
//                }
//                //查询识别-性别 capSex
//                if(t.get("capSex") != null && !StringUtils.isEmpty(t.get("capSex").toString())) {
//                    DicUtils.getValue("capture_person|cap_sex",t.get("capSex").toString(),"识别-性别值非法");
//                }
//                //查询识别-年龄 capAge
                if (t.get("capAge") != null && !StringUtils.isEmpty(t.get("capAge").toString())) {
                    t.put("capAge", DicUtils.getValue("capture_person|cap_age", t.get("capAge").toString(), "识别-年龄值非法"));
                }
//                //查询识别-国别 capCountry
//                if(t.get("capCountry") != null && !StringUtils.isEmpty(t.get("capCountry").toString())) {
//                    DicUtils.getValue("capture_person|cap_country",t.get("capCountry").toString(),"识别-国别值非法");
//                }
//                //查询识别-上衣种类 capCoatType
//                if(t.get("capCoatType") != null && !StringUtils.isEmpty(t.get("capCoatType").toString())) {
//                    t.put("capCoatTypeName", DicUtils.getValue("capture_person|cap_coat_type",t.get("capCoatType").toString(),"识别-上衣种类值非法"));
//                }
//                //查询识别-上衣颜色 capCoatColor
//                if(t.get("capCoatColor") != null && !StringUtils.isEmpty(t.get("capCoatColor").toString())) {
//                    t.put("capCoatColorName", DicUtils.getValue("capture_person|cap_coat_color",t.get("capCoatColor").toString(),"识别-上衣颜色值非法"));
//                }
//                //查询识别-裤子种类 capTrousersType
//                if(t.get("capTrousersType") != null && !StringUtils.isEmpty(t.get("capTrousersType").toString())) {
//                    t.put("capTrousersTypeName", DicUtils.getValue("capture_person|cap_trousers_type",t.get("capTrousersType").toString(),"识别-裤子种类值非法"));
//                }
//                //查询识别-裤子颜色 capTrousersColor
//                if(t.get("capTrousersColor") != null && !StringUtils.isEmpty(t.get("capTrousersColor").toString())) {
//                    t.put("capTrousersColorName", DicUtils.getValue("capture_person|cap_trousers_color",t.get("capTrousersColor").toString(),"识别-裤子颜色值非法"));
//                }
//                //查询识别-人员类型 capType
//                if(t.get("capType") != null && !StringUtils.isEmpty(t.get("capType").toString())) {
//                    DicUtils.getValue("capture_person|cap_type",t.get("capType").toString(),"识别-人员类型值非法");
//                }

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("capture_person");
                //t.put("id",nextId);
                if (t.get("capImage") != null && !StringUtils.isEmpty(t.get("capImage").toString())) {

//                    String capImage=t.get("capImage").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(capImage.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(capImage);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("capture_person_body", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                    //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("capImage", PicUtil.getPicRelativePath(date)+ fileName);
                    //保存图片到fastDFS
                    Map<String, Object> imageMap = imageToFdfsService.saveImageToFdfs(t.get("capImage").toString());
                    t.put("capImage", imageMap.get("thumbFullPath"));

                }

                if (t.get("capPersonImage") != null && !StringUtils.isEmpty(t.get("capPersonImage").toString())) {

//                    String capPersonImage=t.get("capPersonImage").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(capPersonImage.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(capPersonImage);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("capture_person_face", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                    //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("capPersonImage", PicUtil.getPicRelativePath(date)+ fileName);
                    String base64String = t.get("capPersonImage").toString();
                    Map<String, Object> imageMap = imageToFdfsService.saveImageToFdfs(t.get("capPersonImage").toString());
                    t.put("capPersonImage", imageMap.get("thumbFullPath"));

                    Map resultMap = rt;
                    String type = resultMap.get("type").toString();
                    if (type.equals("0")) {

                        t.put("personCode", resultMap.get("face_id"));
                        t.put("type", 1);
                        //查询人员是否存在
                        innerParams = new HashMap<>();
                        if (t.get("personCode") != null && !StringUtils.isEmpty(t.get("personCode").toString())) {
                            innerParams.put("code", t.get("personCode"));
                            List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParams);
                            if (basePersonList != null && !basePersonList.isEmpty()) {
                                // 判断是否已经被重点关注
                                FaceAttentionDetailExample example = new FaceAttentionDetailExample();
                                // 查询personCode为抓拍人且处于布控状态且布控结束时间大于当前时间的数据
                                example.createCriteria().andPersonCodeEqualTo(t.get("personCode").toString()).andStatusEqualTo(false).andEndTimeGreaterThanOrEqualTo(new Date());
                                List<FaceAttentionDetail> attentionDetails =
                                        this.faceAttentionDetailMapper.selectByExample(example);
                                if (null != attentionDetails && attentionDetails.size() == 1) {
                                    FaceAttentionDetail model = attentionDetails.get(0);
                                    model.setCaptureTime(TimesUtil.stringToDate(t.get("captureTime").toString(), TimesUtil.DATE_TIME_FORMAT));
                                    model.setUpdateTime(new Date());
                                    model.setCommunityCode(t.get("communityCode").toString());
                                    model.setCommunityCode(t.get("communityName").toString());
                                    model.setDeviceName(t.get("deviceName").toString());
                                    // 更新重点关注人员的最后抓拍时间
                                    this.faceAttentionDetailMapper.updateByPrimaryKeySelective(model);
                                }
                                BasePerson basePerson = basePersonList.get(0);
                                t.put("personName", basePerson.getName());
                                t.put("personType", basePerson.getType());
                                t.put("personTypeName", basePerson.getTypeName());
                                t.put("personPhone", basePerson.getPhone());
                                t.put("personSex", basePerson.getSex());
                                t.put("personBirthday", basePerson.getBirthday());
                                t.put("personCountry", basePerson.getCountry());
                                t.put("personCountryName", basePerson.getCountryName());
                                t.put("personNationality", basePerson.getNationality());
                                t.put("personNationalityName", basePerson.getNationalityName());
                                t.put("personPaperType", basePerson.getPaperType());
                                t.put("personPaperTypeName", basePerson.getPaperTypeName());
                                t.put("personPaperNumber", basePerson.getPaperNumber());
                                t.put("personAddress", basePerson.getAddress());
                                t.put("livePlace",basePerson.getLivePlace());
                                t.put("personMarriage", basePerson.getMarriage());
                                t.put("personMarriageName", basePerson.getMarriageName());
                                t.put("personPolitical", basePerson.getPolitical());
                                t.put("personPoliticalName", basePerson.getPoliticalName());
                                t.put("personEducation", basePerson.getEducation());
                                t.put("personEducationName", basePerson.getEducationName());
                                t.put("personOccupation", basePerson.getOccupation());
                                t.put("personImagePath", basePerson.getImage());
                                t.put("faceGroupDetailId",basePerson.getFaceGroupDetailId());
                                t.put("relation",basePerson.getRelation());
                                t.put("relationName",basePerson.getRelationName());
                                t.put("residencePermit",basePerson.getResidencePermit());
                                t.put("checkinTime",basePerson.getCheckinTime());
                                t.put("groupType",basePerson.getGroupType());
                                t.put("roomCode",basePerson.getRoomCode());
                                String roomCode=basePerson.getRoomCode();
                                String roomName=baseRoomMapper.queryHouseNameByRoomCode2(roomCode);
                                t.put("roomName",roomName);

                            }
                        }
                    } else if (type.equals("1")) {
                        t.put("personCode", resultMap.get("face_id"));
                        t.put("type", 2);
                        t.put("personName", "陌生人");
                    } else if (type.equals("2")) {
                        t.put("personCode", resultMap.get("face_id"));
                        t.put("type", 2);
                        t.put("personName", "陌生人");
                        Map faceOwnerInfoMap = new HashMap<>();
                        faceOwnerInfoMap.put("personCode", resultMap.get("face_id"));
                        faceOwnerInfoMap.put("personFace", resultMap.get("feature"));
                        faceOwnerInfoMap.put("communityCode", communityCode);
                        FaceStrangerInfo faceStrangerInfo = JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceStrangerInfo.class);
                        faceStrangerInfoMapper.insert(faceStrangerInfo);

                        //更新算法端人脸特征
                        Map<String,Object> featureParams=new HashMap<>();
                        featureParams.put("face_type",2);
                        featureParams.put("face_features",faceOwnerInfoMap.get("personFace"));
                        featureParams.put("face_id",faceOwnerInfoMap.get("personCode"));
                        updateImageFeatureService.insertImageFeature(featureParams);

                    } else if (type.equals("3")) {
                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "对比失败");
                    }
                }


                CapturePerson capturePerson = JSON.parseObject(JSON.toJSONString(t), CapturePerson.class);
                // 测试用
                log.info("添加人脸信息，抓拍时间为:"+ TimesUtil.getServerDateTime(8,capturePerson.getCaptureTime()));
                capturePersonMapper.insert(capturePerson);
                // 发送抓拍消息给算法
                baseService.senderCapture(String.valueOf(nextId), capturePerson.getPersonCode(),
                        String.valueOf(capturePerson.getType()));

                //elasticSearch 插入
                //确保缺省列不被忽略 方便查询
                t.put("id",nextId);
                CapturePersonES capturePersonES =  JSON.parseObject(JSON.toJSONString(t), CapturePersonES.class);
                String elasticJSON = JSON.toJSONStringWithDateFormat(capturePersonES,"yyyy-MM-dd HH:mm:ss",WriteDateUseDateFormat,WriteMapNullValue,WriteNullStringAsEmpty);
                Map<String,Object> elasticMap = JSON.parseObject(elasticJSON);
                ElasticSearchUtil.insert("switch_cloud_new2","capture_person",elasticMap);


                //记录操作
                LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord = new OperateRecord("capture_person", null, nextId + "", "insert", loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteCapturePersonById(int id) throws OssRenderException {
        capturePersonMapper.deleteByPrimaryKey(id);
        //记录操作
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("capture_person", null, id + "", "delete", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }
}
