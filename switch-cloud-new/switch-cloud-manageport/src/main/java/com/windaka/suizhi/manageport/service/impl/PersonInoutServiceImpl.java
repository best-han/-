package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.dao.auto.FaceAttentionDetailMapper;
import com.windaka.suizhi.manageport.elasticsearch.model.PersonInoutES;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.PersonInoutService;
import com.windaka.suizhi.manageport.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Service
public class PersonInoutServiceImpl implements PersonInoutService {

    @Autowired
    private PersonInoutMapper personInoutMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Autowired
    private CaptureDeviceMapper captureDeviceMapper;

    @Autowired
    private BasePersonMapper basePersonMapper;

    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;

    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;

    @Autowired
    private ImageFeatureServiceImpl imageFeatureService;

    @Autowired
    private FaceStrangerInfoMapper faceStrangerInfoMapper;

    @Autowired
    private FaceAttentionDetailMapper faceAttentionDetailMapper;

    @Autowired
    private BaseRoomMapper baseRoomMapper;

    @Autowired
    private UpdateImageFeatureServiceImpl updateImageFeatureService;

    @Override
    public Map<String, Object> selectPersonInoutList(Map<String, Object> params) throws OssRenderException {
        Map map=new HashMap();
        List<PersonInout> personInoutList=personInoutMapper.selectPersonInoutList(params);
        map.put("list",personInoutList);
        return map;
    }

    @Override
    public void insertPersonInout(String communityCode, List lists, List resultMapList) throws OssRenderException, IOException {
        if(lists!=null)
        {
            Iterator i=lists.iterator();
            Iterator ri=resultMapList.iterator();
            while (i.hasNext()){
                Map<String,Object> t=(Map<String, Object>) i.next();
                Map<String,Object> rt=(Map<String, Object>) ri.next();
                t.put("communityCode",communityCode);
                if (t.get("communityCode") == null || StringUtils.isEmpty(t.get("communityCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
                }
                if (t.get("captureTime") == null || StringUtils.isEmpty(t.get("captureTime").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "captureTime不能为空");
                }
//                if (t.get("capImage") == null || StringUtils.isEmpty(t.get("capImage").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "capImage不能为空");
//                }
                Map<String,Object> innerParams;
                //1 查询小区名称是否存在
                innerParams=new HashMap<>();
                innerParams.put("code",communityCode);
                List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(innerParams);
                if(baseCommunityList==null||baseCommunityList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "该小区不存在");
                }
                else{
                    BaseCommunity baseCommunity=baseCommunityList.get(0);
                    t.put("communityName",baseCommunity.getName());
                }
                //查询设备是否存在
                innerParams=new HashMap<>();
                if(t.get("deviceName")==null || StringUtils.isEmpty(t.get("deviceName").toString()))
                {
                    if(t.get("deviceCode")!=null && !StringUtils.isEmpty(t.get("deviceCode").toString()))
                    {
                        innerParams.put("code",t.get("deviceCode"));
                        List<CaptureDevice> captureDeviceList = captureDeviceMapper.selectDeviceList(innerParams);
                        if(captureDeviceList==null||captureDeviceList.isEmpty()){
                            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该设备不存在");
                        }
                        else {
                            CaptureDevice captureDevice=captureDeviceList.get(0);
                            t.put("deviceName",captureDevice.getName());
                            t.put("deviceType",captureDevice.getType());
                            t.put("deviceTypeName",captureDevice.getTypeName());
                            t.put("deviceLocation",captureDevice.getLocation());
                        }
                    }
                }
                //字典查询
//                //抓拍类型 capType
//                if(t.get("capType") != null && !StringUtils.isEmpty(t.get("capType").toString())) {
//                    t.put("capTypeName", DicUtils.getValue("person_inout|cap_type",t.get("capType").toString(),"抓拍人员类型值非法"));
//                }
//                //开门结果
//                if(t.get("openResult") != null && !StringUtils.isEmpty(t.get("openResult").toString())) {
//                    t.put("openResultName",DicUtils.getValue("person_inout|open_result",t.get("openResult").toString(),"开门结果值非法"));
//                }
//                //开门方式
//                if(t.get("openType") != null && !StringUtils.isEmpty(t.get("openType").toString())) {
//                    t.put("openTypeName",DicUtils.getValue("person_inout|open_type",t.get("openType").toString(),"开门类型值非法"));
//                }

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("person_inout");
                if(t.get("capImage")!=null && !StringUtils.isEmpty(t.get("capImage").toString())){

//                    String capImage=t.get("capImage").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(capImage.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(capImage);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("person_inout", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                    //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("capImage", PicUtil.getPicRelativePath(date)+ fileName);

                    //保存图片到fastDFS
                    String base64String=t.get("capImage").toString();
                    Map<String,Object> imageMap=imageToFdfsService.saveImageToFdfs(t.get("capImage").toString());
                    t.put("capImage",imageMap.get("thumbFullPath"));

                    Map resultMap=rt;
                    String type=resultMap.get("type").toString();
                    if(type.equals("0")){

                        t.put("personCode",resultMap.get("face_id"));
                        t.put("type",1);
                        //查询人员是否存在
                        innerParams=new HashMap<>();
                        if(t.get("personCode")!=null && !StringUtils.isEmpty(t.get("personCode").toString()))
                        {
                            innerParams.put("code",t.get("personCode"));
                            List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParams);
                            if(basePersonList!=null&&!basePersonList.isEmpty()){
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
                                for (BasePerson basePerson : basePersonList) {
                                    //BasePerson basePerson=basePersonList.get(0);
                                    t.put("personName",basePerson.getName());
                                    t.put("personPhone",basePerson.getPhone());
                                    t.put("personSex",basePerson.getSex());
                                    t.put("personBirthday",basePerson.getBirthday());
                                    t.put("personCountry",basePerson.getCountry());
                                    t.put("personCountryName",basePerson.getCountryName());
                                    t.put("personNationality",basePerson.getNationality());
                                    t.put("personNationalityName",basePerson.getNationalityName());
                                    t.put("personPaperType",basePerson.getPaperType());
                                    t.put("personPaperTypeName",basePerson.getPaperTypeName());
                                    t.put("personPaperNumber",basePerson.getPaperNumber());
                                    t.put("personAddress",basePerson.getAddress());
                                    t.put("personMarriage",basePerson.getMarriage());
                                    t.put("personMarriageName",basePerson.getMarriageName());
                                    t.put("personPolitical",basePerson.getPolitical());
                                    t.put("personPoliticalName",basePerson.getPoliticalName());
                                    t.put("personEducation",basePerson.getEducation());
                                    t.put("personEducationName",basePerson.getEducationName());
                                    t.put("personOccupation",basePerson.getOccupation());
                                    t.put("personImagePath",basePerson.getImage());

                                    t.put("faceGroupDetailId",basePerson.getFaceGroupDetailId());
                                    t.put("groupType",basePerson.getGroupType());
                                    t.put("livePlace",basePerson.getLivePlace());

                                    String roomCode=basePerson.getRoomCode();
                                    t.put("roomCode",roomCode);

                                    Map<String,Object> roomParams=new HashMap<>();
                                    roomParams.put("roomCode",roomCode);
                                    roomParams.put("communityCode",communityCode);
                                    String roomName=baseRoomMapper.queryHouseNameByRoomCode3(roomParams);

                                    if(roomName!=null && !StringUtils.isEmpty(roomName))
                                    {
                                        t.put("personType",basePerson.getType());
                                        t.put("personTypeName",basePerson.getTypeName());
                                        t.put("roomName",roomName);
                                        t.put("checkinTime",basePerson.getCheckinTime());
                                        t.put("residencePermit",basePerson.getResidencePermit());
                                        t.put("relation",basePerson.getRelation());
                                        t.put("relationName",basePerson.getRelationName());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else if(type.equals("1")){
                        t.put("personCode",resultMap.get("face_id"));
                        t.put("type",2);
                        t.put("personName","陌生人");
                    }
                    else if(type.equals("2")){
                        t.put("personCode",resultMap.get("face_id"));
                        t.put("type",2);
                        t.put("personName","陌生人");

                        Map faceOwnerInfoMap=new HashMap<>();
                        faceOwnerInfoMap.put("personCode",resultMap.get("face_id"));
                        faceOwnerInfoMap.put("personFace",resultMap.get("feature"));
                        faceOwnerInfoMap.put("communityCode",communityCode);
                        FaceStrangerInfo faceStrangerInfo=JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceStrangerInfo.class);
                        faceStrangerInfoMapper.insert(faceStrangerInfo);

                        //更新算法端人脸特征
                        Map<String,Object> featureParams=new HashMap<>();
                        featureParams.put("face_type",2);
                        featureParams.put("face_features",faceOwnerInfoMap.get("personFace"));
                        featureParams.put("face_id",faceOwnerInfoMap.get("personCode"));
                        updateImageFeatureService.insertImageFeature(featureParams);
                    }
                    else if(type.equals("3")){
                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "对比失败");
                    }
                }
                else
                {
                    //查询人员是否存在
                    innerParams=new HashMap<>();
                    if(t.get("personCode")!=null && !StringUtils.isEmpty(t.get("personCode").toString()))
                    {
                        innerParams.put("code",t.get("personCode"));
                        List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParams);
                        if(basePersonList!=null&&!basePersonList.isEmpty()){
                            for (BasePerson basePerson : basePersonList) {
                                //BasePerson basePerson=basePersonList.get(0);
                                t.put("personName",basePerson.getName());
                                t.put("personPhone",basePerson.getPhone());
                                t.put("personSex",basePerson.getSex());
                                t.put("personBirthday",basePerson.getBirthday());
                                t.put("personCountry",basePerson.getCountry());
                                t.put("personCountryName",basePerson.getCountryName());
                                t.put("personNationality",basePerson.getNationality());
                                t.put("personNationalityName",basePerson.getNationalityName());
                                t.put("personPaperType",basePerson.getPaperType());
                                t.put("personPaperTypeName",basePerson.getPaperTypeName());
                                t.put("personPaperNumber",basePerson.getPaperNumber());
                                t.put("personAddress",basePerson.getAddress());
                                t.put("personMarriage",basePerson.getMarriage());
                                t.put("personMarriageName",basePerson.getMarriageName());
                                t.put("personPolitical",basePerson.getPolitical());
                                t.put("personPoliticalName",basePerson.getPoliticalName());
                                t.put("personEducation",basePerson.getEducation());
                                t.put("personEducationName",basePerson.getEducationName());
                                t.put("personOccupation",basePerson.getOccupation());
                                t.put("personImagePath",basePerson.getImage());

                                t.put("faceGroupDetailId",basePerson.getFaceGroupDetailId());
                                t.put("groupType",basePerson.getGroupType());
                                t.put("livePlace",basePerson.getLivePlace());

                                String roomCode=basePerson.getRoomCode();
                                t.put("roomCode",roomCode);

                                Map<String,Object> roomParams=new HashMap<>();
                                roomParams.put("roomCode",roomCode);
                                roomParams.put("communityCode",communityCode);
                                String roomName=baseRoomMapper.queryHouseNameByRoomCode3(roomParams);

                                if(roomName!=null && !StringUtils.isEmpty(roomName))
                                {
                                    t.put("personType",basePerson.getType());
                                    t.put("personTypeName",basePerson.getTypeName());
                                    t.put("roomName",roomName);
                                    t.put("checkinTime",basePerson.getCheckinTime());
                                    t.put("residencePermit",basePerson.getResidencePermit());
                                    t.put("relation",basePerson.getRelation());
                                    t.put("relationName",basePerson.getRelationName());
                                    break;
                                }
                            }
                            t.put("type",1);
                        }
                        else
                        {
                            t.put("type",2);
                            t.put("personName","陌生人");
                        }
                    }
                    else {
                        t.put("type",2);
                        t.put("personName","陌生人");
                    }
                }

                PersonInout personInout= JSON.parseObject(JSON.toJSONString(t), PersonInout.class);
                personInoutMapper.insert(personInout);
                //elasticSearch 插入
                t.put("id",nextId);
                //确保缺省列不被忽略 方便查询
                PersonInoutES personInoutES =  JSON.parseObject(JSON.toJSONString(t), PersonInoutES.class);
                String elasticJSON = JSON.toJSONStringWithDateFormat(personInoutES,"yyyy-MM-dd HH:mm:ss",WriteDateUseDateFormat,WriteMapNullValue,WriteNullStringAsEmpty);
                Map<String,Object> elasticMap = JSON.parseObject(elasticJSON);
                ElasticSearchUtil.insert("switch_cloud_new","person_inout",elasticMap);
                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("person_inout",null,nextId+"","insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deletePersonInoutById(int id) throws OssRenderException {
        personInoutMapper.deleteByPrimaryKey(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("person_inout",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }
}
