package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.elasticsearch.model.CaptureCarES;
import com.windaka.suizhi.manageport.elasticsearch.model.CapturePersonES;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.CaptureCarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Service
public class CaptureCarServiceImpl implements CaptureCarService {
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private CaptureCarMapper captureCarMapper;
    @Autowired
    private CaptureDeviceMapper captureDeviceMapper;
    @Autowired
    private BaseCarMapper baseCarMapper;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;
    @Autowired
    private BaseRoomMapper baseRoomMapper;
    @Override
    public void insertCaptureCar(String communityCode, List cars) throws OssRenderException, IOException {
        if (cars!=null){
            Iterator i=cars.iterator();
            while (i.hasNext()){
                Map<String, Object> t=(Map<String, Object>) i.next();

                //判断必要数据是否为空
                if(communityCode==null || StringUtils.isEmpty(communityCode)){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区代码不能为空");
                }
                if(t.get("captureTime")==null || StringUtils.isEmpty(t.get("captureTime").toString())){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "抓拍时间不能为空");
                }
                if(t.get("deviceCode")==null || StringUtils.isEmpty(t.get("deviceCode").toString())){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "设备编码不能为空");
                }
                if(t.get("carNum")==null || StringUtils.isEmpty(t.get("carNum").toString())){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "车牌号不能为空");
                }
                if(t.get("capImage")==null || StringUtils.isEmpty(t.get("capImage").toString())){
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "抓拍图片不能为空");
                }

                //此操作方便ES 对carNum 聚合及字段查询
                t.put("carNumKeyword",t.get("carNum"));//聚合
                t.put("carNumText",t.get("carNum"));//字段

                Map params;

                //添加关联数据
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
                    t.put("deviceName", captureDevice.getName());
                    t.put("deviceType", captureDevice.getType());
                    t.put("deviceTypeName", captureDevice.getTypeName());
                    t.put("deviceLocation", captureDevice.getLocation());
                    t.put("gbCode",captureDevice.getGbCode());
                    t.put("gbCodeseq",captureDevice.getGbCodeSeq());
                    t.put("dchnlRtsp",captureDevice.getDchnlRtsp());
                }else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED,  t.get("deviceCode")+ "设备不存在");
                }

                //添加车辆信息
                params=new HashMap();
                params.put("num",t.get("carNum"));
                List<BaseCar> baseCarList=baseCarMapper.selectCarList(params);
                if (baseCarList.size()>0){
                    BaseCar baseCar=baseCarList.get(0);
                    t.put("carNumColor",baseCar.getNumColor());
                    t.put("carNumColorName",baseCar.getNumColorName());
                    t.put("carType",baseCar.getType());
                    t.put("carTypeName",baseCar.getTypeName());
                    t.put("carBrand",baseCar.getBrand());
                    t.put("carBrandName",baseCar.getBrandName());
                    t.put("carColor",baseCar.getColor());
                    t.put("carColorName",baseCar.getColorName());
                    t.put("carImage",baseCar.getImage());
                    t.put("carGroupDetailId",baseCar.getCarGroupDetailId());

                    //添加车主信息
                    t.put("personCode",baseCar.getPersonCode());
                    t.put("personName","");
                    Map innerParams;
                    innerParams=new HashMap<>();
                    innerParams.put("code",baseCar.getPersonCode());
                    List<BasePerson> persons = basePersonMapper.selectPersonList(innerParams);
                    if (persons.size() > 0) {
                        BasePerson basePerson= persons.get(0);
                        t.put("type","1");
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
                        Map<String,Object> roomParams=new HashMap<>();
                        roomParams.put("code",roomCode);
                        String roomName=baseRoomMapper.queryHouseNameByRoomCode2(roomCode);
                        t.put("roomName",roomName);

                        List<BaseRoom> baseRoom=baseRoomMapper.selectRoomList(roomParams);
                        if (null!=baseRoom && baseRoom.size()>0){
                            t.put("roomType",baseRoom.get(0).getType());
                            t.put("roomTypeName",baseRoom.get(0).getTypeName());
                            t.put("roomUseType",baseRoom.get(0).getUseType());
                            t.put("roomUseTypeName",baseRoom.get(0).getUseTypeName());
                        }

                    }
                    else
                    {
                        t.put("type","2");
                    }
                }else{
                    t.put("type","2");
                }

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("capture_car");
                if(t.get("capImage")!=null && !StringUtils.isEmpty(t.get("capImage").toString())){

//                    String capImage=t.get("capImage").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(capImage.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(capImage);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("capture_car", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                    //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("capImage", PicUtil.getPicRelativePath(date)+ fileName);

                    //保存图片到fastDFS
                    Map<String,Object> imageMap=imageToFdfsService.saveImageToFdfs(t.get("capImage").toString());
                    t.put("capImage",imageMap.get("thumbFullPath"));
                }
                //全景图
                if (t.get("fullImage")!=null && !StringUtils.isEmpty(t.get("fullImage").toString())){
                    //保存图片到fastDFS
                    Map<String,Object> imageMap=imageToFdfsService.saveImageToFdfs(t.get("fullImage").toString());
                    t.put("fullImage",imageMap.get("thumbFullPath"));
                }

                CaptureCar captureCar= JSON.parseObject(JSON.toJSONString(t), CaptureCar.class);
                captureCarMapper.insertCaptureCar(captureCar);


                //elasticSearch 插入
                //确保缺省列不被忽略 方便查询
                t.put("id",nextId);

                CaptureCarES captureCarES =  JSON.parseObject(JSON.toJSONString(t), CaptureCarES.class);
                String elasticJSON = JSON.toJSONStringWithDateFormat(captureCarES,"yyyy-MM-dd HH:mm:ss",WriteDateUseDateFormat,WriteMapNullValue,WriteNullStringAsEmpty);
                Map<String,Object> elasticMap = JSON.parseObject(elasticJSON);
                ElasticSearchUtil.insert("switch_cloud_new3","capture_car",elasticMap);


                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("capture_car",null,nextId+"","insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteById(String id) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<CaptureCar> captureCars=captureCarMapper.selectCaptureCarList(params);
        if (captureCars.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, id + "车辆不存在");
        }
        captureCarMapper.deleteById(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("capture_car",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectCaptureCarList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<CaptureCar> list=captureCarMapper.selectCaptureCarList(params);
        map.put("list", list);
        return map;
    }
}
