package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.common.utils.Tools;
import com.windaka.suizhi.manageport.dao.*;
import com.windaka.suizhi.manageport.model.*;
import com.windaka.suizhi.manageport.service.BaseCarService;
import com.windaka.suizhi.manageport.service.BasePersonService;
import com.windaka.suizhi.manageport.service.BasePetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BasePersonServiceImpl implements BasePersonService {

    @Autowired
    private BasePersonMapper basePersonMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Autowired
    private BaseRoomMapper baseRoomMapper;

    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;

    @Autowired
    private BaseCarService baseCarService;

    @Autowired
    private BaseCarMapper baseCarMapper;

    @Autowired
    private BasePetService basePetService;

    @Autowired
    private BasePetMapper basePetMapper;

    @Autowired
    private FaceGroupDetailMapper faceGroupDetailMapper;

    @Autowired
    private ImageFeatureServiceImpl imageFeatureService;

    @Autowired
    private FaceOwnerInfoMapper faceOwnerInfoMapper;

    @Autowired
    private FaceStrangerInfoMapper faceStrangerInfoMapper;
    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;

    @Autowired
    private BaseParkingMapper baseParkingMapper;
    @Autowired
    private JRoomRentRecordMapper jRoomRentRecordMapper;

    @Autowired
    private UpdateImageFeatureServiceImpl updateImageFeatureService;

    @Override
    public void insertPerson(String communityCode, List persons, List resultMapList) throws OssRenderException, IOException {
        if (persons != null) {
            Iterator i = persons.iterator();
            Iterator ri = resultMapList.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                Map<String, Object> rt = (Map<String, Object>) ri.next();
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
                    //String code = StringUtils.(t.get("communityCode").toString());
                    //t.put("code",code);
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
                }


                Map<String, Object> innerParams;
                //判断该code是否已经存在（一人多房的情况）
                int flag = 0;
                innerParams = new HashMap<>();
                innerParams.put("code", t.get("code"));
                List<BasePerson> basePersonList1 = basePersonMapper.selectPersonList(innerParams);
                if (basePersonList1 == null || basePersonList1.isEmpty()) {
                    //如果没有这个人，那么判断其证件信息是否与之前的业主重复
                    if (t.get("paperNumber") != null && !StringUtils.isEmpty(t.get("paperNumber").toString())
                            && t.get("paperType") != null && !StringUtils.isEmpty(t.get("paperType").toString())) {
                        innerParams = new HashMap<>();
                        innerParams.put("paperType", t.get("paperType"));
                        innerParams.put("paperNumber", t.get("paperNumber"));
                        List<BasePerson> basePersonList10 = basePersonMapper.selectPersonList(innerParams);
                        if (basePersonList10 != null && !basePersonList10.isEmpty())
                            throw new OssRenderException(ReturnConstants.CODE_FAILED, "存在拥有相同证件信息的其它业主信息");
                    }
                } else {
//                    //如果存在这个人，则判断不会随房屋发生变化的其它基本信息是否一致
//                    //name,sex,birthday,country,nationality,paperType,paperNumber,address,marriage,petCount,political,education,occupation,image
//                    //上述信息统一在updatePersonByCode修改
//                    BasePerson basePerson=basePersonList1.get(0);
//                    String exceptionStr="当前输入信息与人员 "+basePerson.getName()+" ("+basePerson.getCode()+") 在房间"+basePerson.getRoomCode()+"中预留的";
//                    if(!t.get("name").toString().equals(basePerson.getName())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"姓名不一致");
//                    }
//                    if(!t.get("phone").toString().equals(basePerson.getPhone())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"电话不一致");
//                    }
//                    if(!t.get("sex").toString().equals(basePerson.getSex())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"性别不一致");
//                    }
//                    if(!t.get("birthday").toString().equals(DateUtil.getStringDate(basePerson.getBirthday()))){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"出生日期不一致");
//                    }
//                    if(!t.get("country").toString().equals(basePerson.getCountry().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"国别不一致");
//                    }
//                    if(!t.get("nationality").toString().equals(basePerson.getNationality().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"民族不一致");
//                    }
//                    if(!t.get("paperType").toString().equals(basePerson.getPaperType().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"证件类型不一致");
//                    }
//                    if(!t.get("paperNumber").toString().equals(basePerson.getPaperNumber())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"证件号不一致");
//                    }
//                    if(!t.get("address").toString().equals(basePerson.getAddress())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"户籍地址不一致");
//                    }
//                    if(!t.get("marriage").toString().equals(basePerson.getMarriage().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"婚姻状况不一致");
//                    }
//                    if(!t.get("petCount").toString().equals(basePerson.getPetCount().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"犬只状况不一致");
//                    }
//                    if(!t.get("political").toString().equals(basePerson.getPolitical().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"政治面貌不一致");
//                    }
//                    if(!t.get("education").toString().equals(basePerson.getEducation().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"教育程度（学历）不一致");
//                    }
//                    if(!t.get("occupation").toString().equals(basePerson.getOccupation())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"职业不一致");
//                    }
//                    if(!t.get("image").toString().equals(basePerson.getImage())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"图片不一致");
//                    }
//                    if(!t.get("residencePermit").toString().equals(basePerson.getResidencePermit().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"居住证状况不一致");
//                    }
                    //如果存在这个人，则默认填入该人员已有的基本信息
                    //name,sex,birthday,country,nationality,paperType,paperNumber,address,marriage,petCount,political,education,occupation,image
                    //上述信息统一在updatePersonByCode修改
//                    flag=1;
//                    BasePerson basePerson=basePersonList1.get(0);
//                    t.put("name",basePerson.getName());
//                    t.put("sex",basePerson.getSex());
//                    t.put("birthday",basePerson.getBirthday());
//                    t.put("country",basePerson.getCountry());
//                    t.put("nationality",basePerson.getNationality());
//                    t.put("paperType",basePerson.getPaperType());
//                    t.put("paperNumber",basePerson.getPaperNumber());
//                    t.put("address",basePerson.getAddress());
//                    t.put("marriage",basePerson.getMarriage());
//                    t.put("political",basePerson.getPolitical());
//                    t.put("education",basePerson.getEducation());
//                    t.put("occupation",basePerson.getOccupation());
//                    t.put("image",basePerson.getImage());
//                    t.put("phone",basePerson.getPhone());
//                    t.put("residencePermit",basePerson.getResidencePermit());
//                    t.put("faceGroupDetailId",basePerson.getFaceGroupDetailId());
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "此人员已存在");
                }
                t.put("communityCode", communityCode);

                if (t.get("communityCode") == null || StringUtils.isEmpty(t.get("communityCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
                }
                if (t.get("roomCode") == null || StringUtils.isEmpty(t.get("roomCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "roomCode不能为空");
                }
                if (t.get("name") == null || StringUtils.isEmpty(t.get("name").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "name不能为空");
                }
//                if (t.get("type") == null || StringUtils.isEmpty(t.get("type").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "type不能为空");
//                }
//                if (t.get("sex") == null || StringUtils.isEmpty(t.get("sex").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "sex不能为空");
//                }
//                if (t.get("birthday") == null || StringUtils.isEmpty(t.get("birthday").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "birthday不能为空");
//                }
//                if (t.get("country") == null || StringUtils.isEmpty(t.get("country").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "country不能为空");
//                }
//                if (t.get("nationality") == null || StringUtils.isEmpty(t.get("nationality").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "nationality不能为空");
//                }
//                if (t.get("paperType") == null || StringUtils.isEmpty(t.get("paperType").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "paperType不能为空");
//                }
//                if (t.get("paperNumber") == null || StringUtils.isEmpty(t.get("paperNumber").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "paperNumber不能为空");
//                }
//                if (t.get("relation") == null || StringUtils.isEmpty(t.get("relation").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "relation不能为空");
//                }
//                if (t.get("checkinTime") == null || StringUtils.isEmpty(t.get("checkinTime").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "checkinTime不能为空");
//                }

                //判断常住人口 流动人口
                //根据户籍地 和现居地 判断是否在市内七区(现居地 根据小区所在地判断 ---->则肯定在七区内)
                if (t.get("address") != null && StringUtils.isNotEmpty(t.get("address").toString())) {
                    String address = t.get("address").toString();
                    //返回true or false
                    if (Tools.judgeNatureOfHousehold(address)) {
                        t.put("addressType", 1);
                    } else {
                        t.put("addressType", 2);
                    }
                } else {
                    //户籍地为空 默认为 常住人口
                    t.put("addressType", 1);
                }

                if (t.get("country") != null && StringUtils.isNotEmpty(t.get("country").toString())) {
                    String country = t.get("country").toString();
                    if (!country.equals("1")){
                        //外籍人 不做处理
                        t.put("addressType", "");
                    }
                }


                //新增一个人  若此人为租户   则  -->日租 短期租  插入j_room_rent_record记录表
                if (t.get("type") != null && StringUtils.isNotBlank(t.get("type").toString())) {
                    String type = t.get("type").toString();
                    if (type.equals("3")) {//根据type 获取身份-->租户
                        innerParams = new HashMap<>();
                        innerParams.put("code", t.get("roomCode"));
                        innerParams.put("communityCode", t.get("communityCode"));
                        List<BaseRoom> baseRooms = baseRoomMapper.selectRoomList(innerParams);//房屋信息
                        BaseRoom baseRoom = baseRooms.get(0);

                        innerParams = new HashMap<>();
                        innerParams.put("roomCode", t.get("roomCode"));
                        List<BasePerson> basePeopleLists = basePersonMapper.selectRoomOwnerList(innerParams);//业主信息
                        BasePerson basePerson = basePeopleLists.get(0);

                        JRoomRentRecord jRoomRentRecord = new JRoomRentRecord();
                        jRoomRentRecord.setRoomCode(baseRoom.getCode());
                        jRoomRentRecord.setCommunityCode(baseRoom.getCommunityCode());
                        jRoomRentRecord.setCommunityName(baseRoom.getCommunityName());
                        jRoomRentRecord.setUseType(baseRoom.getUseType());
                        jRoomRentRecord.setUseTypeName(baseRoom.getUseTypeName());
                        jRoomRentRecord.setHouseInfo(baseRoom.getBuildingName() + " " + baseRoom.getUnitName() + " " + baseRoom.getRoomName());
                        jRoomRentRecord.setOwnerName(basePerson.getName());
                        jRoomRentRecord.setPhone(basePerson.getPhone());

                        jRoomRentRecordMapper.insertRoomRentRecord(jRoomRentRecord);
                    }
                }

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
                //查询房间是否存在
                innerParams = new HashMap<>();
                innerParams.put("code", t.get("roomCode"));
                List<BaseRoom> baseRoomList = baseRoomMapper.selectRoomList(innerParams);
                if (baseRoomList == null || baseRoomList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "该房间不存在");
                }
                //查询人员与房屋之间是否已经存在关联
                innerParams = new HashMap<>();
                innerParams.put("code", t.get("code"));
                innerParams.put("roomCode", t.get("roomCode"));
                List<BasePerson> basePersonList2 = basePersonMapper.selectPersonList(innerParams);
                if (basePersonList2 != null && !basePersonList2.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "当前人员与房屋之间已经存在关联");
                }
                //字典检索
//                //查询人员类型 type
//                t.put("typeName", DicUtils.getValue("base_person|type",t.get("type").toString(),"人员类型值非法"));
//                //查询性别 sex
//                t.put("sexName",DicUtils.getValue("base_person|sex",t.get("sex").toString(),"性别值非法"));
//                //查询国别 country
//                t.put("countryName",DicUtils.getValue("base_person|country",t.get("country").toString(),"国别值非法"));
//                //查询民族 nationality
//                t.put("nationalityName",DicUtils.getValue("base_person|nationality",t.get("nationality").toString(),"民族值非法"));
//                //查询证件类型 paperType
//                t.put("paperTypeName",DicUtils.getValue("base_person|paper_type",t.get("paperType").toString(),"证件类型值非法"));
//                //查询婚姻状况 marriage
//                if (t.get("marriage") != null && !StringUtils.isEmpty(t.get("marriage").toString())) {
//                    t.put("marriageName",DicUtils.getValue("base_person|marriage", t.get("marriage").toString(),"婚姻状况值非法"));
//                }
//                //查询政治面貌 political
//                if (t.get("political") != null && !StringUtils.isEmpty(t.get("political").toString())) {
//                    t.put("politicalName",DicUtils.getValue("base_person|political",t.get("political").toString(),"政治面貌值非法"));
//                }
//                //查询教育状况（学历） education
//                if (t.get("education") != null && !StringUtils.isEmpty(t.get("education").toString())) {
//                    t.put("educationName",DicUtils.getValue("base_person|education",t.get("education").toString(),"教育状况（学历）值非法"));
//                }
                //与户主关系 relation
//                t.put("relationName",DicUtils.getValue("base_person|relation",t.get("relation").toString(),"与户主关系值非法"));
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("base_person");
                //图片上传
                if (t.get("image") != null && !StringUtils.isEmpty(t.get("image").toString()) && flag == 0) {

//                    String personImg=t.get("image").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(personImg.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(personImg);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("base_person", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                     //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("image", PicUtil.getPicRelativePath(date)+ fileName);

                    //保存图片到fastDFS
                    String base64String = t.get("image").toString();
                    Map<String, Object> imageMap = imageToFdfsService.saveImageToFdfs(t.get("image").toString());
                    t.put("image", imageMap.get("thumbFullPath"));

                    Map resultMap = rt;
                    String type = resultMap.get("type").toString();
                    if (type.equals("0")) {
                        Map faceOwnerInfoMap = new HashMap<>();
                        faceOwnerInfoMap.put("personCode", t.get("code"));
                        faceOwnerInfoMap.put("personFace", resultMap.get("feature"));
                        faceOwnerInfoMap.put("communityCode", communityCode);
                        FaceOwnerInfo faceOwnerInfo = JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceOwnerInfo.class);
                        faceOwnerInfoMapper.insert(faceOwnerInfo);

                        //更新算法端人脸特征
                        Map<String, Object> featureParams = new HashMap<>();
                        featureParams.put("face_type", 1);
                        featureParams.put("face_features", faceOwnerInfoMap.get("personFace"));
                        featureParams.put("face_id", faceOwnerInfoMap.get("personCode"));
                        updateImageFeatureService.insertImageFeature(featureParams);
                    } else if (type.equals("1")) {
                        String delStranger = resultMap.get("face_id").toString();
                        delStranger = delStranger.substring(1, delStranger.length() - 1);
                        String[] delStrangerCodes = delStranger.split(",");
                        int dn = delStrangerCodes.length;
                        for (int j = 0; j < dn; j++) {
                            faceStrangerInfoMapper.deleteByCode(delStrangerCodes[j]);

                            //更新算法端人脸特征
                            Map<String, Object> featureParams = new HashMap<>();
                            featureParams.put("face_type", 2);
                            featureParams.put("face_id", delStrangerCodes[j]);
                            updateImageFeatureService.deleteImageFeature(featureParams);

                        }
                        Map faceOwnerInfoMap = new HashMap<>();
                        faceOwnerInfoMap.put("personCode", t.get("code"));
                        faceOwnerInfoMap.put("personFace", resultMap.get("feature"));
                        faceOwnerInfoMap.put("communityCode", communityCode);
                        FaceOwnerInfo faceOwnerInfo = JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceOwnerInfo.class);
                        faceOwnerInfoMapper.insert(faceOwnerInfo);

                        //更新算法端人脸特征
                        Map<String, Object> featureParams = new HashMap<>();
                        featureParams.put("face_type", 1);
                        featureParams.put("face_features", faceOwnerInfoMap.get("personFace"));
                        featureParams.put("face_id", faceOwnerInfoMap.get("personCode"));
                        updateImageFeatureService.insertImageFeature(featureParams);

                    }
                }

                BasePerson basePerson = JSON.parseObject(JSON.toJSONString(t), BasePerson.class);
                basePersonMapper.insert(basePerson);
                //记录操作
                LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord = new OperateRecord("base_person", basePerson.getCode(), nextId + "", "insert", loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);


            }
        }
    }

    @Override
    public void updatePersonByCode(Map basePersonMap, Map parseResultMap) throws OssRenderException, IOException {
        BasePerson basePerson = JSON.parseObject(JSON.toJSONString(basePersonMap), BasePerson.class);
        if (basePerson.getCode() == null || StringUtils.isEmpty(basePerson.getCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
        }
        if (basePerson.getCommunityCode() == null || StringUtils.isEmpty(basePerson.getCommunityCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
        }
        if (basePerson.getName() == null || StringUtils.isEmpty(basePerson.getName())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "name不能为空");
        }
//        if (basePerson.getSex() == null || StringUtils.isEmpty(basePerson.getSex())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "sex不能为空");
//        }
//        if (basePerson.getBirthday() == null || StringUtils.isEmpty(basePerson.getBirthday().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "birthday不能为空");
//        }
//        if (basePerson.getCountry() == null || StringUtils.isEmpty(basePerson.getCountry().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "country不能为空");
//        }
//        if (basePerson.getNationality() == null || StringUtils.isEmpty(basePerson.getNationality().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "nationality不能为空");
//        }
//        if (basePerson.getPaperType() == null || StringUtils.isEmpty(basePerson.getPaperType().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "paperType不能为空");
//        }
//        if (basePerson.getPaperNumber()== null || StringUtils.isEmpty(basePerson.getPaperNumber())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "paperNumber不能为空");
//        }

        //判断常住人口 流动人口
        //根据户籍地 和现居地 判断是否在市内七区(现居地 根据小区所在地判断 ---->则肯定在七区内)
        if (basePerson.getAddress() != null && StringUtils.isNotEmpty(basePerson.getAddress())) {
            String address = basePerson.getAddress();
            //返回true or false
            if (Tools.judgeNatureOfHousehold(address)) {
                basePerson.setAddressType(Short.parseShort("1"));
            } else {
                basePerson.setAddressType(Short.parseShort("2"));
            }
        } else {
            //户籍地为空 默认为 常住人口
            basePerson.setAddressType(Short.parseShort("1"));
        }

        if (basePerson.getCountry() != null && StringUtils.isNotEmpty(basePerson.getCountry().toString())) {
            String country = basePerson.getCountry().toString();
            if (!country.equals("1")){
                //外籍人 不做处理
                basePerson.setAddressType(Short.parseShort(""));
            }
        }


        Map innerParams;
        if (basePerson.getCommunityCode() != null && !StringUtils.isEmpty(basePerson.getCommunityCode())) {
            //1 查询小区名称是否存在
            String communityCode = basePerson.getCommunityCode();
            innerParams = new HashMap<>();
            innerParams.put("code", communityCode);
            List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(innerParams);
            if (baseCommunityList == null || baseCommunityList.isEmpty()) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "该小区不存在");
            } else {
                BaseCommunity baseCommunity = baseCommunityList.get(0);
                basePerson.setCommunityCode(baseCommunity.getCode());
                basePerson.setCommunityName(baseCommunity.getName());
            }
        }
//        //字典检索
//        //查询性别 sex
//        if(basePerson.getSex() != null && !StringUtils.isEmpty(basePerson.getSex())) {
//            String sexName = DicUtils.getValue("base_person|sex", basePerson.getSex(), "性别值非法");
//            basePerson.setSexName(sexName);
//        }
//        //查询国别 country
//        if(basePerson.getCountry() != null && !StringUtils.isEmpty(basePerson.getCountry().toString())){
//            String countryName = DicUtils.getValue("base_person|country", basePerson.getCountry().toString(), "国别值非法");
//            basePerson.setCountryName(countryName);
//        }
//        //查询民族 nationality
//        if(basePerson.getNationality() != null && !StringUtils.isEmpty(basePerson.getNationality().toString())){
//            String nationalityName = DicUtils.getValue("base_person|nationality", basePerson.getNationality().toString(), "民族值非法");
//            basePerson.setNationalityName(nationalityName);
//        }
//        //查询证件类型 paperType
//        if(basePerson.getPaperType() != null && !StringUtils.isEmpty(basePerson.getPaperType().toString())){
//            String paperTypeName = DicUtils.getValue("base_person|paper_type", basePerson.getPaperType().toString(), "证件类型值非法");
//            basePerson.setPaperTypeName(paperTypeName);
//        }
//        //查询婚姻状况 marriage
//        if(basePerson.getMarriage() != null && !StringUtils.isEmpty(basePerson.getMarriage().toString())){
//            String marriageName = DicUtils.getValue("base_person|marriage", basePerson.getMarriage().toString(), "婚姻状况值非法");
//            basePerson.setMarriageName(marriageName);
//        }
//        //查询政治面貌 political
//        if(basePerson.getPolitical() != null && !StringUtils.isEmpty(basePerson.getPolitical().toString())){
//            String politicalName = DicUtils.getValue("base_person|political", basePerson.getPolitical().toString(), "政治面貌值非法");
//            basePerson.setPoliticalName(politicalName);
//        }
//        //查询教育状况（学历） education
//        if(basePerson.getEducation() != null && !StringUtils.isEmpty(basePerson.getEducation().toString())){
//            String educationName = DicUtils.getValue("base_person|education", basePerson.getEducation().toString(), "教育状况（学历）值非法");
//            basePerson.setEducationName(educationName);
//        }
        innerParams = new HashMap<>();
        innerParams.put("code", basePerson.getCode());
        List<BasePerson> basePersonListM = basePersonMapper.selectPersonList(innerParams);
        Integer id = null;
        if (basePersonListM.size() > 0) {
            BasePerson basePersonM = basePersonListM.get(0);
            id = basePersonM.getId();
        } else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该人员不存在");
        }
        //图片上传
        if (basePerson.getImage() != null && !StringUtils.isEmpty(basePerson.getImage())) {
//            String personImg=basePerson.getImage();
////            byte[] byteArr= PicUtil.stringToInputStream(personImg.split(",")[1]);
//            byte[] byteArr= PicUtil.stringToInputStream(personImg);
//            // 根据最大主键
//            //Integer id=basePerson.getId();
//            //rename pic
//            String fileName = PicUtil.getPicName("base_person", id);
//
//            //封装访问路径：年/月/日
//            Date date=new Date();
//            //图片放入打包路径
//            FileUploadUtil.inputStreamToLocalFile(byteArr,
//                    CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//            basePerson.setImage(PicUtil.getPicRelativePath(date)+ fileName);

            //保存图片到fastDFS
            String base64String = basePerson.getImage();
            Map<String, Object> imageMap = imageToFdfsService.saveImageToFdfs(basePerson.getImage());
            basePerson.setImage(imageMap.get("thumbFullPath").toString());


            Map faceOwnerInfoMap = new HashMap<>();
            faceOwnerInfoMap.put("personCode", basePerson.getCode());
            faceOwnerInfoMap.put("communityCode", basePerson.getCommunityCode());
            faceOwnerInfoMapper.delete(faceOwnerInfoMap);

            Map<String, Object> featureParams = new HashMap<>();
            featureParams.put("face_type", 1);
            featureParams.put("face_id", basePerson.getCode());
            updateImageFeatureService.deleteImageFeature(featureParams);


            Map resultMap = parseResultMap;
            String type = resultMap.get("type").toString();
            if (type.equals("0")) {
                faceOwnerInfoMap = new HashMap<>();
                faceOwnerInfoMap.put("personCode", basePerson.getCode());
                faceOwnerInfoMap.put("personFace", resultMap.get("feature"));
                faceOwnerInfoMap.put("communityCode", basePerson.getCommunityCode());
                FaceOwnerInfo faceOwnerInfo = JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceOwnerInfo.class);
                faceOwnerInfoMapper.insert(faceOwnerInfo);


                //更新算法端人脸特征
                featureParams = new HashMap<>();
                featureParams.put("face_type", 1);
                featureParams.put("face_features", faceOwnerInfoMap.get("personFace"));
                featureParams.put("face_id", basePerson.getCode());
                updateImageFeatureService.insertImageFeature(featureParams);
            } else if (type.equals("1")) {
                String delStranger = resultMap.get("face_id").toString();
                delStranger = delStranger.substring(1, delStranger.length() - 1);
                String[] delStrangerCodes = delStranger.split(",");
                int dn = delStrangerCodes.length;
                for (int j = 0; j < dn; j++) {
                    faceStrangerInfoMapper.deleteByCode(delStrangerCodes[j]);
                    //更新算法端人脸特征
                    featureParams = new HashMap<>();
                    featureParams.put("face_type", 2);
                    featureParams.put("face_id", delStrangerCodes[j]);
                    updateImageFeatureService.deleteImageFeature(featureParams);

                }
                faceOwnerInfoMap = new HashMap<>();
                faceOwnerInfoMap.put("personCode", basePerson.getCode());
                faceOwnerInfoMap.put("personFace", resultMap.get("feature"));
                faceOwnerInfoMap.put("communityCode", basePerson.getCommunityCode());
                FaceOwnerInfo faceOwnerInfo = JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceOwnerInfo.class);
                faceOwnerInfoMapper.insert(faceOwnerInfo);


                //更新算法端人脸特征
                featureParams = new HashMap<>();
                featureParams.put("face_type", 1);
                featureParams.put("face_features", faceOwnerInfoMap.get("personFace"));
                featureParams.put("face_id", basePerson.getCode());
                updateImageFeatureService.insertImageFeature(featureParams);
            }
        }


        basePersonMapper.updateByCode(basePerson);
        //级联修改-布控明细
        Map innerParam = new HashMap();
        innerParam.put("personCode", basePerson.getCode());
        List<FaceGroupDetail> faceGroupDetailList = faceGroupDetailMapper.selectFaceGroupDetailList(innerParam);
        Iterator<FaceGroupDetail> fgdi = faceGroupDetailList.iterator();
        while (fgdi.hasNext()) {
            FaceGroupDetail fgdt = fgdi.next();
            fgdt.setPersonImage(basePerson.getImage());
            fgdt.setPersonName(basePerson.getName());
            fgdt.setPersonPaperNumber(basePerson.getPaperNumber());
            fgdt.setPersonPhone(basePerson.getPhone());
            faceGroupDetailMapper.updateById(fgdt);
        }

        //记录操作
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_person", basePerson.getCode(), null, "update", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void deletePersonByCode(String code) throws OssRenderException {
        Map innerParam = new HashMap();
        innerParam.put("code", code);
        List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParam);
        if (basePersonList == null || basePersonList.isEmpty()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "人员不存在");
        }
        BasePerson delPerson = basePersonList.get(0);
        basePersonMapper.deleteByCode(code);
        //级联删除-车
        innerParam = new HashMap();
        innerParam.put("personCode", code);
        List<BaseCar> baseCarList = baseCarMapper.selectCarList(innerParam);
        Iterator<BaseCar> bci = baseCarList.iterator();
        while (bci.hasNext()) {
            BaseCar bct = bci.next();
            baseCarService.deleteByNum(bct.getNum());

        }
        //级联删除-宠物
        innerParam = new HashMap();
        innerParam.put("personCode", code);
        List<BasePet> basePetList = basePetMapper.selectPetsList(innerParam);
        Iterator<BasePet> bpi = basePetList.iterator();
        while (bpi.hasNext()) {
            BasePet bpt = bpi.next();
            basePetService.deleteByCode(bpt.getCode());
        }
        //级联删除-布控明细 setnull
        innerParam = new HashMap();
        innerParam.put("personCode", code);
        List<FaceGroupDetail> faceGroupDetailList = faceGroupDetailMapper.selectFaceGroupDetailList(innerParam);
        Iterator<FaceGroupDetail> fgdi = faceGroupDetailList.iterator();
        while (fgdi.hasNext()) {
            FaceGroupDetail fgdt = fgdi.next();
            fgdt.setPersonCode(fgdt.getPersonCode() + "(已删除)");
            fgdt.setPersonImage(null);
            fgdt.setPersonName(null);
            fgdt.setPersonPaperNumber(null);
            fgdt.setPersonPhone(null);
            faceGroupDetailMapper.updateById(fgdt);
        }
        //级联删除-停车位 setnull
        innerParam = new HashMap();
        innerParam.put("personCode", code);
        List<BaseParking> baseParkingList = baseParkingMapper.selectBaseParkingList(innerParam);
        Iterator<BaseParking> bpli = baseParkingList.iterator();
        while (bpli.hasNext()) {
            BaseParking bplt = bpli.next();
            bplt.setPersonCode(null);
            baseParkingMapper.updateBaseParking(bplt);
        }
        //级联删除-正常人脸库
        innerParam = new HashMap();
        innerParam.put("personCode", code);
        innerParam.put("communityCode", delPerson.getCommunityCode());
        faceOwnerInfoMapper.delete(innerParam);

        //更新算法端人脸特征
        Map<String, Object> featureParams = new HashMap<>();
        featureParams.put("face_type", 1);
        featureParams.put("face_id", code);
        updateImageFeatureService.deleteImageFeature(featureParams);

        //记录操作
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_person", code, null, "delete", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void insertPersonHouse(List list) throws OssRenderException {
        if (list != null) {
            Iterator i = list.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                BasePerson basePerson = JSON.parseObject(JSON.toJSONString(t), BasePerson.class);
                if (basePerson.getCode() == null || StringUtils.isEmpty(basePerson.getCode())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
                }
                if (basePerson.getRoomCode() == null || StringUtils.isEmpty(basePerson.getRoomCode())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "roomCode不能为空");
                }
                Map innerParams;
                //查询code对应本人
                innerParams = new HashMap<>();
                innerParams.put("code", basePerson.getCode());
                List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParams);
                if (basePersonList == null || basePersonList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "此人不存在");
                }
                //查询房间是否存在
                innerParams = new HashMap<>();
                innerParams.put("code", basePerson.getRoomCode());
                List<BaseRoom> baseRoomList = baseRoomMapper.selectRoomList(innerParams);
                if (baseRoomList == null || baseRoomList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "该房间不存在");
                }
                //查询对应房屋与当前人员是否已经存在关系
                innerParams = new HashMap<>();
                innerParams.put("code", basePerson.getCode());
                innerParams.put("roomCode", basePerson.getRoomCode());
                List<BaseRoom> basePersonListR = basePersonMapper.selectPersonList(innerParams);
                if (basePersonListR != null && !basePersonListR.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "当前人员与指定房屋已经存在关系");
                }
                Iterator<BasePerson> bpi = basePersonList.iterator();
                int f = 0;
                while (bpi.hasNext()) {
                    BasePerson bpt = bpi.next();
                    if (bpt.getRoomCode() == null || StringUtils.isEmpty(bpt.getRoomCode())) {
                        bpt.setRoomCode(basePerson.getRoomCode());
                        bpt.setType(basePerson.getType());
                        bpt.setTypeName(basePerson.getTypeName());
                        bpt.setCheckinTime(basePerson.getCheckinTime());
                        bpt.setRelation(basePerson.getRelation());
                        bpt.setRelationName(basePerson.getRelationName());
                        bpt.setResidencePermit(basePerson.getResidencePermit());
                        basePersonMapper.fillPersonHouseById(bpt);
                        f = 1;
                        break;
                    }
                }
                if (f == 0) {
                    BasePerson insertPerson = basePersonList.get(0);
                    insertPerson.setId(null);
                    insertPerson.setRoomCode(basePerson.getRoomCode());
                    insertPerson.setType(basePerson.getType());
                    insertPerson.setTypeName(basePerson.getTypeName());
                    insertPerson.setCheckinTime(basePerson.getCheckinTime());
                    insertPerson.setRelation(basePerson.getRelation());
                    insertPerson.setRelationName(basePerson.getRelationName());
                    insertPerson.setResidencePermit(basePerson.getResidencePermit());
                    basePersonMapper.insert(insertPerson);
                }
            }
        }
    }

    @Override
    public void updatePersonHouseByCode(BasePerson basePerson) throws OssRenderException {
        if (basePerson.getCode() == null || StringUtils.isEmpty(basePerson.getCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
        }
        if (basePerson.getRoomCode() == null || StringUtils.isEmpty(basePerson.getRoomCode())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "roomCode不能为空");
        }
//        if (basePerson.getType() == null || StringUtils.isEmpty(basePerson.getType().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "type不能为空");
//        }
//        if (basePerson.getRelation() == null || StringUtils.isEmpty(basePerson.getRelation().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "relation不能为空");
//        }
//        if (basePerson.getCheckinTime() == null || StringUtils.isEmpty(basePerson.getCheckinTime().toString())) {
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "checkinTime不能为空");
//        }
        Map innerParams;
        //查询code对应本人
        innerParams = new HashMap<>();
        innerParams.put("code", basePerson.getCode());
        List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParams);
        if (basePersonList == null || basePersonList.isEmpty()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "此人不存在");
        }
        BasePerson basePersonP = basePersonList.get(0);
        String personCode = basePersonP.getCode();
        Integer modifyId = basePersonP.getId();
        //查询房间是否存在
        innerParams = new HashMap<>();
        innerParams.put("code", basePerson.getRoomCode());
        List<BaseRoom> baseRoomList = baseRoomMapper.selectRoomList(innerParams);
        if (baseRoomList == null || baseRoomList.isEmpty()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该房间不存在");
        } else {
            //查询对应房屋与当前人员是否已经存在关系
            BaseRoom baseRoom = baseRoomList.get(0);
            String roomCode = baseRoom.getCode();
            innerParams = new HashMap<>();
            innerParams.put("code", personCode);
            innerParams.put("roomCode", roomCode);
            basePersonList = basePersonMapper.selectPersonList(innerParams);
            if (basePersonList == null || basePersonList.isEmpty()) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "当前人员与指定房屋不存在关系");
            }
        }
//        //字典检索
//        //查询人员类型 type
//        if(basePerson.getType() != null && !StringUtils.isEmpty(basePerson.getType().toString())) {
//            String typeName = DicUtils.getValue("base_person|type", basePerson.getType().toString(), "人员类型值非法");
//            basePerson.setTypeName(typeName);
//        }
//        //与户主关系 relation
//        if(basePerson.getRelation() != null && !StringUtils.isEmpty(basePerson.getRelation().toString())){
//            String relationName = DicUtils.getValue("base_person|relation", basePerson.getRelation().toString(), "与户主关系值非法");
//            basePerson.setRelationName(relationName);
//        }
        basePersonMapper.updatePersonHouseByCode(basePerson);
        //记录操作
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_person", null, basePersonP.getId().toString(), "update", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void deletePersonHouseByCode(BasePerson basePerson) throws OssRenderException {
        Map innerParam = new HashMap();
        innerParam.put("code", basePerson.getCode());
        innerParam.put("roomCode", basePerson.getRoomCode());
        List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParam);
        if (basePersonList == null || basePersonList.isEmpty()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该记录不存在");
        }
        //人员基本信息
        BasePerson oldPerson = basePersonList.get(0);
        //查询其它房间的绑定条数
        innerParam = new HashMap();
        innerParam.put("code", basePerson.getCode());
        innerParam.put("roomCode", basePerson.getRoomCode());
        List<BasePerson> otherPersonHouseList = basePersonMapper.selectOtherHousesInPerson(innerParam);
        int n = otherPersonHouseList.size();
        basePersonMapper.deletePersonHouseByCode(basePerson);
        if (n == 0) {
            oldPerson.setType(null);
            oldPerson.setTypeName(null);
            oldPerson.setCheckinTime(null);
            oldPerson.setRelation(null);
            oldPerson.setRelationName(null);
            oldPerson.setResidencePermit(null);
            oldPerson.setRoomCode(null);
            basePersonMapper.insert(oldPerson);
        }
        //级联删除
//        if(n==0)
//        {
//            //级联删除-车
//            innerParam=new HashMap();
//            innerParam.put("personCode",basePerson.getCode());
//            List<BaseCar> baseCarList=baseCarMapper.selectCarList(innerParam);
//            Iterator<BaseCar> bci=baseCarList.iterator();
//            while (bci.hasNext()){
//                BaseCar bct=bci.next();
//                baseCarService.deleteByNum(bct.getNum());
//            }
//            //级联删除-宠物
//            innerParam=new HashMap();
//            innerParam.put("personCode",basePerson.getCode());
//            List<BasePet> basePetList=basePetMapper.selectPetsList(innerParam);
//            Iterator<BasePet> bpi=basePetList.iterator();
//            while (bpi.hasNext()){
//                BasePet bpt=bpi.next();
//                basePetService.deleteByCode(bpt.getCode());
//            }
//            //级联删除-布控明细 setnull
//            innerParam=new HashMap();
//            innerParam.put("personCode",basePerson.getCode());
//            List<FaceGroupDetail> faceGroupDetailList=faceGroupDetailMapper.selectFaceGroupDetailList(innerParam);
//            Iterator<FaceGroupDetail> fgdi=faceGroupDetailList.iterator();
//            while (fgdi.hasNext()){
//                FaceGroupDetail fgdt=fgdi.next();
//                fgdt.setPersonCode(fgdt.getPersonCode()+"已删除");
//                fgdt.setPersonImage(null);
//                fgdt.setPersonName(null);
//                fgdt.setPersonPaperNumber(null);
//                fgdt.setPersonPhone(null);
//                faceGroupDetailMapper.updateById(fgdt);
//            }
//        }
        BasePerson basePersonP = basePersonList.get(0);
        //记录操作
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_person", null, basePersonP.getId() + "", "delete", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectPersonList(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        List<BasePerson> personList = basePersonMapper.selectPersonList(params);
        map.put("list", personList);
        return map;
    }

    @Override
    public void insertPersonSingle(String communityCode, List persons, List resultMapList) throws OssRenderException, IOException {
        if (persons != null) {
            Iterator i = persons.iterator();
            Iterator ri = resultMapList.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
                Map<String, Object> rt = (Map<String, Object>) ri.next();
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {
                    //String code = StringUtils.(t.get("communityCode").toString());
                    //t.put("code",code);
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "code不能为空");
                }
                Map<String, Object> innerParams;
                //判断该code是否已经存在（一人多房的情况）
                int flag = 0;
                innerParams = new HashMap<>();
                innerParams.put("code", t.get("code"));
                List<BasePerson> basePersonList1 = basePersonMapper.selectPersonList(innerParams);
                if (basePersonList1 == null || basePersonList1.isEmpty()) {
                    //如果没有这个人，那么判断其证件信息是否与之前的业主重复
                    if (t.get("paperNumber") != null && !StringUtils.isEmpty(t.get("paperNumber").toString())
                            && t.get("paperType") != null && !StringUtils.isEmpty(t.get("paperType").toString())) {
                        innerParams = new HashMap<>();
                        innerParams.put("paperType", t.get("paperType"));
                        innerParams.put("paperNumber", t.get("paperNumber"));
                        List<BasePerson> basePersonList10 = basePersonMapper.selectPersonList(innerParams);
                        if (basePersonList10 != null && !basePersonList10.isEmpty())
                            throw new OssRenderException(ReturnConstants.CODE_FAILED, "存在拥有相同证件信息的其它业主信息");
                    }
                } else {
//                    //如果存在这个人，则判断不会随房屋发生变化的其它基本信息是否一致
//                    //name,sex,birthday,country,nationality,paperType,paperNumber,address,marriage,petCount,political,education,occupation,image
//                    //上述信息统一在updatePersonByCode修改
//                    BasePerson basePerson=basePersonList1.get(0);
//                    String exceptionStr="当前输入信息与人员 "+basePerson.getName()+" ("+basePerson.getCode()+") 在房间"+basePerson.getRoomCode()+"中预留的";
//                    if(!t.get("name").toString().equals(basePerson.getName())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"姓名不一致");
//                    }
//                    if(!t.get("phone").toString().equals(basePerson.getPhone())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"电话不一致");
//                    }
//                    if(!t.get("sex").toString().equals(basePerson.getSex())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"性别不一致");
//                    }
//                    if(!t.get("birthday").toString().equals(DateUtil.getStringDate(basePerson.getBirthday()))){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"出生日期不一致");
//                    }
//                    if(!t.get("country").toString().equals(basePerson.getCountry().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"国别不一致");
//                    }
//                    if(!t.get("nationality").toString().equals(basePerson.getNationality().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"民族不一致");
//                    }
//                    if(!t.get("paperType").toString().equals(basePerson.getPaperType().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"证件类型不一致");
//                    }
//                    if(!t.get("paperNumber").toString().equals(basePerson.getPaperNumber())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"证件号不一致");
//                    }
//                    if(!t.get("address").toString().equals(basePerson.getAddress())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"户籍地址不一致");
//                    }
//                    if(!t.get("marriage").toString().equals(basePerson.getMarriage().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"婚姻状况不一致");
//                    }
//                    if(!t.get("petCount").toString().equals(basePerson.getPetCount().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"犬只状况不一致");
//                    }
//                    if(!t.get("political").toString().equals(basePerson.getPolitical().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"政治面貌不一致");
//                    }
//                    if(!t.get("education").toString().equals(basePerson.getEducation().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"教育程度（学历）不一致");
//                    }
//                    if(!t.get("occupation").toString().equals(basePerson.getOccupation())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"职业不一致");
//                    }
//                    if(!t.get("image").toString().equals(basePerson.getImage())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"图片不一致");
//                    }
//                    if(!t.get("residencePermit").toString().equals(basePerson.getResidencePermit().toString())){
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionStr+"居住证状况不一致");
//                    }
                    //如果存在这个人，则默认填入该人员已有的基本信息
                    //name,sex,birthday,country,nationality,paperType,paperNumber,address,marriage,petCount,political,education,occupation,image
                    //上述信息统一在updatePersonByCode修改
//                    flag=1;
//                    BasePerson basePerson=basePersonList1.get(0);
//                    t.put("name",basePerson.getName());
//                    t.put("sex",basePerson.getSex());
//                    t.put("birthday",basePerson.getBirthday());
//                    t.put("country",basePerson.getCountry());
//                    t.put("nationality",basePerson.getNationality());
//                    t.put("paperType",basePerson.getPaperType());
//                    t.put("paperNumber",basePerson.getPaperNumber());
//                    t.put("address",basePerson.getAddress());
//                    t.put("marriage",basePerson.getMarriage());
//                    t.put("political",basePerson.getPolitical());
//                    t.put("education",basePerson.getEducation());
//                    t.put("occupation",basePerson.getOccupation());
//                    t.put("image",basePerson.getImage());
//                    t.put("phone",basePerson.getPhone());
//                    t.put("residencePermit",basePerson.getResidencePermit());
//                    t.put("faceGroupDetailId",basePerson.getFaceGroupDetailId());
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "此人员已存在");
                }
                t.put("communityCode", communityCode);

                if (t.get("communityCode") == null || StringUtils.isEmpty(t.get("communityCode").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
                }
                if (t.get("name") == null || StringUtils.isEmpty(t.get("name").toString())) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "name不能为空");
                }
//                if (t.get("type") == null || StringUtils.isEmpty(t.get("type").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "type不能为空");
//                }
//                if (t.get("sex") == null || StringUtils.isEmpty(t.get("sex").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "sex不能为空");
//                }
//                if (t.get("birthday") == null || StringUtils.isEmpty(t.get("birthday").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "birthday不能为空");
//                }
//                if (t.get("country") == null || StringUtils.isEmpty(t.get("country").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "country不能为空");
//                }
//                if (t.get("nationality") == null || StringUtils.isEmpty(t.get("nationality").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "nationality不能为空");
//                }
//                if (t.get("paperType") == null || StringUtils.isEmpty(t.get("paperType").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "paperType不能为空");
//                }
//                if (t.get("paperNumber") == null || StringUtils.isEmpty(t.get("paperNumber").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "paperNumber不能为空");
//                }
//                if (t.get("relation") == null || StringUtils.isEmpty(t.get("relation").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "relation不能为空");
//                }
//                if (t.get("checkinTime") == null || StringUtils.isEmpty(t.get("checkinTime").toString())) {
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "checkinTime不能为空");
//                }

                //判断常住人口 流动人口
                //根据户籍地 和现居地 判断是否在市内七区(现居地 根据小区所在地判断 ---->则肯定在七区内)
                if (t.get("address") != null && StringUtils.isNotEmpty(t.get("address").toString())) {
                    String address = t.get("address").toString();
                    //返回true or false
                    if (Tools.judgeNatureOfHousehold(address)) {
                        t.put("addressType", 1);
                    } else {
                        t.put("addressType", 2);
                    }
                } else {
                    //户籍地为空 默认为 常住人口
                    t.put("addressType", 1);
                }

                if (t.get("country") != null && StringUtils.isNotEmpty(t.get("country").toString())) {
                    String country = t.get("country").toString();
                    if (!country.equals("1")){
                        //外籍人 不做处理
                        t.put("addressType", "");
                    }
                }


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
                //字典检索
//                //查询人员类型 type
//                t.put("typeName", DicUtils.getValue("base_person|type",t.get("type").toString(),"人员类型值非法"));
//                //查询性别 sex
//                t.put("sexName",DicUtils.getValue("base_person|sex",t.get("sex").toString(),"性别值非法"));
//                //查询国别 country
//                t.put("countryName",DicUtils.getValue("base_person|country",t.get("country").toString(),"国别值非法"));
//                //查询民族 nationality
//                t.put("nationalityName",DicUtils.getValue("base_person|nationality",t.get("nationality").toString(),"民族值非法"));
//                //查询证件类型 paperType
//                t.put("paperTypeName",DicUtils.getValue("base_person|paper_type",t.get("paperType").toString(),"证件类型值非法"));
//                //查询婚姻状况 marriage
//                if (t.get("marriage") != null && !StringUtils.isEmpty(t.get("marriage").toString())) {
//                    t.put("marriageName",DicUtils.getValue("base_person|marriage", t.get("marriage").toString(),"婚姻状况值非法"));
//                }
//                //查询政治面貌 political
//                if (t.get("political") != null && !StringUtils.isEmpty(t.get("political").toString())) {
//                    t.put("politicalName",DicUtils.getValue("base_person|political",t.get("political").toString(),"政治面貌值非法"));
//                }
//                //查询教育状况（学历） education
//                if (t.get("education") != null && !StringUtils.isEmpty(t.get("education").toString())) {
//                    t.put("educationName",DicUtils.getValue("base_person|education",t.get("education").toString(),"教育状况（学历）值非法"));
//                }
                //与户主关系 relation
//                t.put("relationName",DicUtils.getValue("base_person|relation",t.get("relation").toString(),"与户主关系值非法"));
                // 根据最大主键
                Integer nextId = cloudPlatformMapper.getNextId("base_person");
                //图片上传
                if (t.get("image") != null && !StringUtils.isEmpty(t.get("image").toString()) && flag == 0) {

//                    String personImg=t.get("image").toString();
////                    byte[] byteArr= PicUtil.stringToInputStream(personImg.split(",")[1]);
//                    byte[] byteArr= PicUtil.stringToInputStream(personImg);
//                    //rename pic
//                    String fileName = PicUtil.getPicName("base_person", nextId);
//
//                    //封装访问路径：年/月/日
//                    Date date=new Date();
//                    //图片放入打包路径
//                    FileUploadUtil.inputStreamToLocalFile(byteArr,
//                            CommonConstants.LOCAL_IMAGE_FILE_PATH + File.separator +"image"+File.separator+PicUtil.getPicRelativePath(date),fileName);
//
//                    t.put("image", PicUtil.getPicRelativePath(date)+ fileName);
                    //保存图片到fastDFS
                    String base64String = t.get("image").toString();
                    Map<String, Object> imageMap = imageToFdfsService.saveImageToFdfs(t.get("image").toString());
                    t.put("image", imageMap.get("thumbFullPath"));

                    Map resultMap = rt;
                    String type = resultMap.get("type").toString();
                    if (type.equals("0")) {
                        Map faceOwnerInfoMap = new HashMap<>();
                        faceOwnerInfoMap.put("personCode", t.get("code"));
                        faceOwnerInfoMap.put("personFace", resultMap.get("feature"));
                        faceOwnerInfoMap.put("communityCode", communityCode);
                        FaceOwnerInfo faceOwnerInfo = JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceOwnerInfo.class);
                        faceOwnerInfoMapper.insert(faceOwnerInfo);
                    } else if (type.equals("1")) {
                        String delStranger = resultMap.get("face_id").toString();
                        delStranger = delStranger.substring(1, delStranger.length() - 1);
                        String[] delStrangerCodes = delStranger.split(",");
                        int dn = delStrangerCodes.length;
                        for (int j = 0; j < dn; j++) {
                            faceStrangerInfoMapper.deleteByCode(delStrangerCodes[j]);
                        }
                        Map faceOwnerInfoMap = new HashMap<>();
                        faceOwnerInfoMap.put("personCode", t.get("code"));
                        faceOwnerInfoMap.put("personFace", resultMap.get("feature"));
                        faceOwnerInfoMap.put("communityCode", communityCode);
                        FaceOwnerInfo faceOwnerInfo = JSON.parseObject(JSON.toJSONString(faceOwnerInfoMap), FaceOwnerInfo.class);
                        faceOwnerInfoMapper.insert(faceOwnerInfo);
                    }
                }

                BasePerson basePerson = JSON.parseObject(JSON.toJSONString(t), BasePerson.class);
                basePersonMapper.insert(basePerson);
                //记录操作
                LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                OperateRecord operateRecord = new OperateRecord("base_person", basePerson.getCode(), nextId + "", "insert", loginAppUser.getUserId());
                cloudPlatformMapper.insertRecord(operateRecord);
            }
        }
    }
}
