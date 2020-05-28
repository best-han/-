package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.elasticsearch.model.CarAccessES;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.CarAccessService;
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
public class CarAccessServiceImpl implements CarAccessService {
    @Autowired
    private CarAccessMapper carAccessMapper;
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
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
    public void insertCarAccess(String communityCode, List carAccesses) throws OssRenderException, IOException {
        if (carAccesses != null) {
            Iterator i = carAccesses.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();

                //判断必要数据是否为空
                if (communityCode == null || StringUtils.isEmpty(communityCode)) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区代码不能为空");
                }
                if (t.get("captureTime") == null || StringUtils.isEmpty(t.get("captureTime").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "抓拍时间不能为空");
                }
//                if (t.get("carNum") == null || StringUtils.isEmpty(t.get("carNum").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "车牌号不能为空");
//                }
                if (t.get("carDirect") == null || StringUtils.isEmpty(t.get("carDirect").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "行驶方向不能为空");
                }
//                if (t.get("capImage") == null || StringUtils.isEmpty(t.get("capImage").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "抓拍图像不能为空");
//                }

                Map params;
                //添加小区关联数据
                t.put("communityCode", communityCode);
                t.put("communityName", "");
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

                //添加车辆信息
                params = new HashMap();
                params.put("num", t.get("carNum"));
                t.put("carNumKeyword",t.get("carNum"));
                t.put("carNumText",t.get("carNum"));
                if(t.get("carNum")!=null && !StringUtils.isEmpty(t.get("carNum").toString()))
                {
                    List<BaseCar> baseCarList = baseCarMapper.selectCarList(params);
                    if (baseCarList.size() > 0) {
                        BaseCar baseCar = baseCarList.get(0);
                        /*
                        elasticSearch 车辆相关
                         */
                        t.put("carNumColor",baseCar.getNumColor());
                        t.put("carNumColorName",baseCar.getNumColorName());
                        t.put("carColor",baseCar.getColor());
                        t.put("carColorName",baseCar.getColorName());
                        t.put("carType",baseCar.getType());
                        t.put("carTypeName",baseCar.getTypeName());
                        t.put("carBrand",baseCar.getBrand());
                        t.put("carBrandName",baseCar.getBrandName());
                        t.put("carImage",baseCar.getImage());
                        t.put("carGroupDetailId",baseCar.getCarGroupDetailId());
                        /*
                        elasticSearch 车辆相关 end
                         */
                        //添加车主信息
                        t.put("personCode", baseCar.getPersonCode());
                        t.put("personName", "");
                        Map innerParams;
                        innerParams = new HashMap<>();
                        innerParams.put("code", t.get("personCode"));
                        List<BasePerson> persons = basePersonMapper.selectPersonList(innerParams);
                        if (persons.size() > 0) {
                            /*
                            elasticSearch 人员相关
                             */
                            for (BasePerson basePerson : persons) {
                                //BasePerson basePerson = persons.get(0);
                                t.put("personName", basePerson.getName());
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
                                /*
                                elasticSearch 房屋相关
                                 */
                                Map innerParams2=new HashMap();
                                innerParams2.put("code",roomCode);
                                innerParams2.put("communityCode",t.get("communityCode"));
                                List<BaseRoom> baseRoomList=baseRoomMapper.selectRoomList(innerParams2);
                                if(baseRoomList.size()>0)
                                {
                                    BaseRoom baseRoom=baseRoomList.get(0);
                                    String roomName=baseRoom.getCommunityName()+" "+baseRoom.getBuildingName()+" "+baseRoom.getUnitName()+" "+baseRoom.getRoomName();
                                    if(!StringUtils.isEmpty(roomName.trim()))
                                    {
                                        t.put("roomType",baseRoom.getType());
                                        t.put("roomTypeName",baseRoom.getTypeName());
                                        t.put("roomUseType",baseRoom.getUseType());
                                        t.put("roomUseTypeName",baseRoom.getUseTypeName());
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
                                /*
                                elasticSearch 房屋相关 end
                                 */
                            }
                            t.put("type","1");
                            /*
                            elasticSearch 人员相关 end
                             */
                        }
                        else {
                            t.put("type","2");
                        }
                    }
                    else {
                        t.put("type","2");
                    }
                }

                //添加绑定设备信息
                params = new HashMap();
                if(t.get("deviceName")==null || StringUtils.isEmpty(t.get("deviceName").toString()))
                {
                    if(t.get("deviceCode")!=null && !StringUtils.isEmpty(t.get("deviceCode").toString()))
                    {
                        params.put("code", t.get("deviceCode"));
                        List<CaptureDevice> captureDeviceList = captureDeviceMapper.selectDeviceList(params);
                        if (captureDeviceList.size() > 0) {
                            CaptureDevice captureDevice = captureDeviceList.get(0);
                            t.put("deviceName", captureDevice.getName());
                            t.put("deviceType", captureDevice.getType());
                            t.put("deviceTypeName", captureDevice.getTypeName());
                            t.put("deviceLocation", captureDevice.getLocation());
                        } else {
                            throw new OssRenderException(ReturnConstants.CODE_FAILED, t.get("deviceCode") + "设备不存在");
                        }
                    }
                }

                //图片上传
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("car_access");
                if(t.get("capImage")!=null && !StringUtils.isEmpty(t.get("capImage").toString())){

//                    String capImage=t.get("capImage").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(capImage.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(capImage);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("car_access", nextId);
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

                CarAccess carAccess = JSON.parseObject(JSON.toJSONString(t), CarAccess.class);
                carAccessMapper.insertCarAccess(carAccess);

                //elasticSearch 插入
                t.put("id",nextId);
                //确保缺省列不被忽略 方便查询
                CarAccessES carAccessES =  JSON.parseObject(JSON.toJSONString(t), CarAccessES.class);
                String elasticJSON = JSON.toJSONStringWithDateFormat(carAccessES,"yyyy-MM-dd HH:mm:ss",WriteDateUseDateFormat,WriteMapNullValue,WriteNullStringAsEmpty);
                Map<String,Object> elasticMap = JSON.parseObject(elasticJSON);
                ElasticSearchUtil.insert("switch_cloud_new4","car_access",elasticMap);

                //记录操作
                LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord=new OperateRecord("car_access",null,nextId+"","insert",loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }

    @Override
    public void deleteById(String id) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<CarAccess> carAccesses = carAccessMapper.selectCarAccessList(params);
        if (carAccesses.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, id + "车辆不存在");
        }
        carAccessMapper.deleteById(id);
        //记录操作
        LoginAppUser loginAppUser= AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord=new OperateRecord("car_access",null,id+"","delete",loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectCarAccessList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<CarAccess> list = carAccessMapper.selectCarAccessList(params);
        map.put("list", list);
        return map;
    }
}
