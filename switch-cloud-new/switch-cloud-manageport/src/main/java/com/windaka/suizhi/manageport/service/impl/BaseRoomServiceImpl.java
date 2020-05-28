package com.windaka.suizhi.manageport.service.impl;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.manageport.dao.BaseCommunityMapper;
import com.windaka.suizhi.manageport.dao.BasePersonMapper;
import com.windaka.suizhi.manageport.dao.BaseRoomMapper;
import com.windaka.suizhi.manageport.dao.CloudPlatformMapper;
import com.windaka.suizhi.manageport.model.BaseCommunity;
import com.windaka.suizhi.manageport.model.BasePerson;
import com.windaka.suizhi.manageport.model.BaseRoom;
import com.windaka.suizhi.manageport.model.OperateRecord;
import com.windaka.suizhi.manageport.service.BasePersonService;
import com.windaka.suizhi.manageport.service.BaseRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BaseRoomServiceImpl implements BaseRoomService {
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private BaseRoomMapper baseRoomMapper;
    @Autowired
    private CloudPlatformMapper cloudPlatformMapper;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private BasePersonService basePersonService;

    @Override
    public void insertRoom(List baseRoom) throws OssRenderException {
        if (baseRoom != null) {
            Iterator i = baseRoom.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
//                判断是否位空值
                if (t.get("code") == null || StringUtils.isEmpty(t.get("code").toString())) {//房屋代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "房屋代号code不能为空");
                }
//                if (t.get("communityCode") == null || StringUtils.isEmpty(t.get("communityCode").toString())) {//小区代号
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
//                }
//                if (t.get("buildingCode") == null || StringUtils.isEmpty(t.get("buildingCode").toString())) {//楼栋代号
//                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingCode不能为空");
//                }
//                System.out.println("读取数据："+t.get("buildingNum"));
                if (t.get("unitCode") == null || StringUtils.isEmpty(t.get("unitCode").toString())) {//单元代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "unitCode不能为空");
                }
                if (t.get("roomNum") == null || StringUtils.isEmpty(t.get("roomNum").toString())) {//房间代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "roomNum不能为空");
                }
                if (t.get("type") == null || StringUtils.isEmpty(t.get("type").toString())) {//房屋类型
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "type不能为空");
                }

//
                //房屋类型
//                    t.put("typeName", DicUtils.getValue("base_room|type",t.get("type").toString(),"字典无此代码数据"));

                BaseRoom baseRooms = JSON.parseObject(JSON.toJSONString(t), BaseRoom.class);
                int f=0;
                Map innerParam = new HashMap();
                innerParam.put("unitCode", t.get("unitCode"));
                List<BaseRoom> unitList = baseRoomMapper.selectUnitList(innerParam);
                if (unitList == null || unitList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "该单元不存在");
                } else {
                    innerParam = new HashMap();
                    innerParam.put("code", t.get("code"));
                    List<BaseRoom> baseRoomList = baseRoomMapper.selectRoomList(innerParam);
                    if (baseRoomList != null && !baseRoomList.isEmpty()) {
                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "该房间已经存在");
                    }

                    Iterator<BaseRoom> ui = unitList.iterator();
                    while (ui.hasNext()) {
                        BaseRoom ut = ui.next();
                        if (ut.getCode() == null || StringUtils.isEmpty(ut.getCode()))//该单元下 无房间信息
                        {
                            ut.setCode(baseRooms.getCode());
                            ut.setRoomNum(baseRooms.getRoomNum());
                            ut.setRoomName(baseRooms.getRoomName());
                            ut.setType(baseRooms.getType());
                            ut.setTypeName(baseRooms.getTypeName());
                            ut.setBuildingArea(baseRooms.getBuildingArea());
                            ut.setUsableArea(baseRooms.getUsableArea());
                            ut.setOwnership(baseRooms.getOwnership());
                            ut.setUseType(baseRooms.getUseType());
                            ut.setUseTypeName(baseRooms.getUseTypeName());
                            baseRoomMapper.updateUnitById(ut);
                            //记录操作
                            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                            OperateRecord operateRecord = new OperateRecord("base_room", baseRooms.getCode(), null, "insert", loginAppUser.getUserId());
                            cloudPlatformMapper.insertRecord(operateRecord);
                            f=1;
                            break;
                        }
                    }
                }
                if(f==0)
                {
                    BaseRoom baseUnit = unitList.get(0);

                    baseUnit.setCode(baseRooms.getCode());
                    baseUnit.setRoomNum(baseRooms.getRoomNum());
                    baseUnit.setRoomName(baseRooms.getRoomName());
                    baseUnit.setType(baseRooms.getType());
                    baseUnit.setTypeName(baseRooms.getTypeName());
                    baseUnit.setBuildingArea(baseRooms.getBuildingArea());
                    baseUnit.setUsableArea(baseRooms.getUsableArea());
                    baseUnit.setOwnership(baseRooms.getOwnership());
                    baseUnit.setUseType(baseRooms.getUseType());
                    baseUnit.setUseTypeName(baseRooms.getUseTypeName());
                    baseUnit.setId(null);

                    baseRoomMapper.insertRoom(baseUnit);
                    //记录操作
                    LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                    OperateRecord operateRecord = new OperateRecord("base_room", baseRooms.getCode(), null, "insert", loginAppUser.getUserId());
                    cloudPlatformMapper.insertRecord(operateRecord);
                }
            }
        }
    }


    @Override
    public void deleteRoomByCode(String code) throws OssRenderException {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        List<BaseRoom> baseRoomList = baseRoomMapper.selectRoomList(params);
        if (baseRoomList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, code + "房间不存在");
        }

        Map innerParams;

        //查询所属单元信息
        BaseRoom baseUnit=baseRoomList.get(0);
        //查询单元下是否还有其它房屋
        innerParams = new HashMap();
        innerParams.put("code",code);
        innerParams.put("unitCode",baseUnit.getUnitCode());
        List<BaseRoom> otherRooms=baseRoomMapper.selectOtherRoomsInUnit(innerParams);
        int otherTotal=otherRooms.size();

        baseRoomMapper.deleteRoomByCode(code);

        if(otherTotal==0)
        {
            baseRoomMapper.insertUnit(baseUnit);
        }

        //级联删除 -人
        innerParams = new HashMap();
        innerParams.put("roomCode", code);
        List<BasePerson> basePersonList = basePersonMapper.selectPersonList(innerParams);
        Iterator<BasePerson> bpi = basePersonList.iterator();
        while (bpi.hasNext()) {
            BasePerson bpt = bpi.next();
            basePersonService.deletePersonHouseByCode(bpt);//包含 人下 车-宠物
        }

        //记录操作
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_room", code, null, "delete", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateRoomByCode(BaseRoom baseRoom) throws OssRenderException {
        if (baseRoom.getCode() == null || StringUtils.isEmpty(baseRoom.getCode())) {//房屋代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "房屋code不能为空");
        }
//        if (baseRoom.getCommunityCode() == null || StringUtils.isEmpty(baseRoom.getCommunityCode())) {//小区代号
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
//        }
//        if (baseRoom.getBuildingNum() == null || StringUtils.isEmpty(baseRoom.getBuildingNum().toString())) {//楼栋代号
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingNum不能为空");
//        }
//        if (baseRoom.getUnitNum() == null || StringUtils.isEmpty(baseRoom.getUnitNum().toString())) {//单元代号
//            throw new OssRenderException(ReturnConstants.CODE_FAILED, "unitNum不能为空");
//        }
        if (baseRoom.getRoomNum() == null || StringUtils.isEmpty(baseRoom.getRoomNum().toString())) {//房间代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "roomNum不能为空");
        }
        if (baseRoom.getType() == null || StringUtils.isEmpty(baseRoom.getType().toString())) {//房屋类型
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "type不能为空");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("code", baseRoom.getCode());
        List<BaseRoom> baseRoomList = baseRoomMapper.selectRoomList(params);
        if (baseRoomList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, baseRoom.getCode() + "号房间不存在");
        }
        //                查询是否存在该小区
        Map<String, Object> xqParams = new HashMap<>();
        xqParams.put("code", baseRoom.getCommunityCode());
        List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(xqParams);
        if (baseCommunityList.size() > 0) {
            String communityName = baseCommunityList.get(0).getName();
            baseRoom.setCommunityName(communityName);
        } else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, baseRoom.getCommunityCode() + "该小区不存在");
        }
        //房屋类型
//        baseRoom.setTypeName(DicUtils.getValue("base_room|type",baseRoom.getType().toString(),"字典无此代码数据"));

        baseRoomMapper.updateRoomByCode(baseRoom);
        //记录操作
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_room", baseRoom.getCode(), null, "update", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectRoomList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<BaseRoom> list = baseRoomMapper.selectRoomList(params);
        map.put("list", list);
        return map;
    }


    @Override
    public void insertUnit(List units) throws OssRenderException {
        if (units != null) {
            Iterator i = units.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();

                if (t.get("buildingCode") == null || StringUtils.isEmpty(t.get("buildingCode").toString())) {//楼栋代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingCode不能为空");
                }
                if (t.get("unitCode") == null || StringUtils.isEmpty(t.get("unitCode").toString())) {//单元代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "unitCode不能为空");
                }

                BaseRoom baseRoom = JSON.parseObject(JSON.toJSONString(t), BaseRoom.class);

                int f=0;
                Map innerParam = new HashMap();
                innerParam.put("buildingCode", t.get("buildingCode"));
                List<BaseRoom> buildingList = baseRoomMapper.selectBuildingList(innerParam);
                if (buildingList == null || buildingList.isEmpty()) {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "单元绑定楼栋不存在");
                } else {
                    innerParam = new HashMap();
                    innerParam.put("buildingCode", t.get("buildingCode"));
                    innerParam.put("unitCode", t.get("unitCode"));
                    List<BaseRoom> unitList = baseRoomMapper.selectUnitList(innerParam);
                    if (unitList != null && !unitList.isEmpty()) {
                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "小区(" + buildingList.get(0).getCommunityCode() + "):楼栋(" + buildingList.get(0).getBuildingNum() + ")下的单元(" + t.get("unitNum") + ")已经存在");
                    }

                    Iterator<BaseRoom> bi = buildingList.iterator();
                    while (bi.hasNext()) {
                        BaseRoom bt = bi.next();
                        if (bt.getUnitCode() == null || StringUtils.isEmpty(bt.getUnitCode())) {//该楼栋下 无单元信息
                            bt.setUnitNum(baseRoom.getUnitNum());
                            bt.setUnitName(baseRoom.getUnitName());
                            bt.setUnitCode(baseRoom.getUnitCode());

                            baseRoomMapper.updateBuildingById(bt);
                            //记录操作
                            Integer nextId = cloudPlatformMapper.getNextId("base_room");
                            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                            OperateRecord operateRecord = new OperateRecord("base_room", null, nextId + "", "insert", loginAppUser.getUserId());
                            cloudPlatformMapper.insertRecord(operateRecord);
                            f=1;
                            break;
                        }
                    }
                    if(f==0)
                    {
                        BaseRoom baseBuilding = buildingList.get(0);
                        baseBuilding.setUnitCode(baseRoom.getUnitCode());
                        baseBuilding.setId(null);
                        baseBuilding.setUnitName(baseRoom.getUnitName());
                        baseBuilding.setUnitNum(baseRoom.getUnitNum());
                        baseRoomMapper.insertUnit(baseBuilding);
                        //记录操作
                        Integer nextId = cloudPlatformMapper.getNextId("base_room");
                        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                        OperateRecord operateRecord = new OperateRecord("base_room", null, nextId + "", "insert", loginAppUser.getUserId());
                        cloudPlatformMapper.insertRecord(operateRecord);
                    }
                }
            }
        }
    }

    @Override
    public void updateUnit(BaseRoom baseRoom) throws OssRenderException {

        if (baseRoom.getUnitCode() == null || StringUtils.isEmpty(baseRoom.getUnitCode().toString())) {//单元代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "unitCode不能为空");
        }
        Map innerParam = new HashMap();

        innerParam.put("unitCode", baseRoom.getUnitCode());

        List<BaseRoom> unitList = baseRoomMapper.selectUnitList(innerParam);
        if (unitList == null || unitList.isEmpty()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "单元不存在");
        }
        baseRoomMapper.updateUnit(baseRoom);
        //记录操作
        Integer nextId = cloudPlatformMapper.getNextId("base_room");
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_room", null, nextId + "", "update", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void deleteUnit(String unitCode) throws OssRenderException {

        if (unitCode == null || StringUtils.isEmpty(unitCode)) {//单元代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "unitCode不能为空");
        }
        Map innerParam = new HashMap();

        innerParam.put("unitCode", unitCode);
        List<BaseRoom> unitList = baseRoomMapper.selectUnitList(innerParam);
        if (unitList == null || unitList.isEmpty()) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "单元不存在");
        }
        //所属楼栋信息
        BaseRoom baseBuilding=unitList.get(0);
        innerParam=new HashMap();
        innerParam.put("buildingCode",baseBuilding.getBuildingCode());
        innerParam.put("unitCode",unitCode);
        List<BaseRoom> otherUnits = baseRoomMapper.selectOtherUnitsInBuilding(innerParam);
        int otherTotals=otherUnits.size();

        baseRoomMapper.deleteUnit(unitCode);

        if(otherTotals==0)
        {
            baseRoomMapper.insertBuilding(baseBuilding);
        }
        //记录操作
        Integer nextId = cloudPlatformMapper.getNextId("base_room");
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_room", null, nextId + "", "delete", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public Map<String, Object> selectUnitList(Map<String, Object> params) throws OssRenderException {
        Map map = new HashMap();
        List<BaseRoom> list = baseRoomMapper.selectUnitList(params);
        map.put("list", list);
        return map;
    }


    @Override
    public void insertBuilding(List baseRoom) throws OssRenderException {
        if (baseRoom != null) {
            Iterator i = baseRoom.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();
//                判断是否位空值
                if (t.get("communityCode") == null || StringUtils.isEmpty(t.get("communityCode").toString())) {//小区代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "communityCode不能为空");
                }
                if (t.get("buildingNum") == null || StringUtils.isEmpty(t.get("buildingNum").toString())) {//楼栋代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingNum不能为空");
                }
                if (t.get("buildingCode") == null || StringUtils.isEmpty(t.get("buildingCode").toString())) {//楼栋代号
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingCode不能为空");
                }

                Map params;
//                查询是否存在该小区
                params = new HashMap<>();
                params.put("code", t.get("communityCode"));
                List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);
                //                查询是否存在该小区内本楼栋号
                params = new HashMap<>();
                params.put("communityCode", t.get("communityCode"));
                params.put("buildingCode", t.get("buildingCode"));
                List<BaseRoom> baseRoomList = baseRoomMapper.selectBuildingList(params);

                if (baseCommunityList.size() > 0 && baseRoomList.size() <= 0) {
                    //查询添加关联数据
                    //小区名称
                    String communityName = baseCommunityList.get(0).getName();
                    t.put("communityName", communityName);

                    BaseRoom baseRooms = JSON.parseObject(JSON.toJSONString(t), BaseRoom.class);
                    baseRoomMapper.insertBuilding(baseRooms);
                    //记录操作
                    Integer nextId = cloudPlatformMapper.getNextId("base_room");
                    LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
                    OperateRecord operateRecord = new OperateRecord("base_room", null, nextId + "", "insert", loginAppUser.getUserId());
                    cloudPlatformMapper.insertRecord(operateRecord);
                } else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, t.get("communityCode") + "该小区不存在,或者" + t.get("buildingNum") + "该小区本楼栋号已存在。");
                }
            }
        }
    }

    @Override
    public void deleteBuildingByKey(String buildingCode) throws OssRenderException {

        if (buildingCode == null || StringUtils.isEmpty(buildingCode)) {//楼栋代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingCode不能为空");
        }
        Map params;
        params=new HashMap();
        params.put("buildingCode",buildingCode);
        List<BaseRoom> baseRoomList = baseRoomMapper.selectBuildingList(params);
        if (baseRoomList.size() <= 0) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, params.get("buildingCode") + "房间不存在");
        }
        baseRoomMapper.deleteBuildingByKey(buildingCode);
        //记录操作
        Integer nextId = cloudPlatformMapper.getNextId("base_room");
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        OperateRecord operateRecord = new OperateRecord("base_room", null, nextId + "", "delete", loginAppUser.getUserId());
        cloudPlatformMapper.insertRecord(operateRecord);
    }

    @Override
    public void updateBuildingByKey(BaseRoom baseRoom) throws OssRenderException {

        if (baseRoom.getCommunityCode() == null || StringUtils.isEmpty(baseRoom.getCommunityCode().toString())) {//楼栋代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "CommunityCode不能为空");
        }
        if (baseRoom.getBuildingNum() == null || StringUtils.isEmpty(baseRoom.getBuildingNum().toString())) {//楼栋代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingNum不能为空");
        }
        if (baseRoom.getBuildingCode() == null || StringUtils.isEmpty(baseRoom.getBuildingCode().toString())) {//楼栋代号
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "buildingCode不能为空");
        }
        Map params;

//                查询是否存在该小区
        params = new HashMap<>();
        params.put("code", baseRoom.getCommunityCode());
        List<BaseCommunity> baseCommunityList = baseCommunityMapper.selectCommunityList(params);

        //                查询是否存在该小区内本楼栋号
        params = new HashMap<>();
        params.put("communityCode", baseRoom.getCommunityCode());
        params.put("buildingCode", baseRoom.getBuildingCode());
        List<BaseRoom> baseRoomList = baseRoomMapper.selectBuildingList(params);

        if (baseCommunityList.size() > 0 && baseRoomList.size() > 0) {
            //查询添加关联数据
            //小区名称
            String communityName = baseCommunityList.get(0).getName();
            baseRoom.setCommunityName(communityName);

            baseRoomMapper.updateBuildingByKey(baseRoom);
            //记录操作
            Integer nextId = cloudPlatformMapper.getNextId("base_room");
            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
            OperateRecord operateRecord = new OperateRecord("base_room", null, nextId + "", "update", loginAppUser.getUserId());
            cloudPlatformMapper.insertRecord(operateRecord);
        } else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, baseRoom.getCommunityCode() + "该小区不存在,或" + baseRoom.getBuildingCode() + "楼栋不存在");
        }
    }

    @Override
    public Map<String, Object> selectBuildingList(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<BaseRoom> list = baseRoomMapper.selectRoomList(params);
        map.put("list", list);
        return map;
    }
}
