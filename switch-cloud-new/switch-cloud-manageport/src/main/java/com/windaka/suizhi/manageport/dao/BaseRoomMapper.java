package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.BaseRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseRoomMapper {

    /*
    查询房屋名称 zdq
     */
    String queryHouseNameByRoomCode(String roomCode);
    String queryHouseNameByRoomCode2(String roomCode);
    String queryHouseNameByRoomCode3(Map<String,Object> params);

    /*
    * 新增房屋信息 ygy
    * */
    int insertRoom(BaseRoom baseRoom);

    /*
    * 删除房屋信息 by code  ygy
    * */
    int deleteRoomByCode(String code);

    /*
    * 更新小区信息 by code ygy
    * */
    int updateRoomByCode(BaseRoom baseRoom);

    /**
     * 更新房屋信息 by id dee
     * @param baseRoom
     * @return
     *
     *  单元信息更新  ps  某单条记录只存在楼栋单元信息 不存在房间  更新单元 插入房间信息
     */
    int updateUnitById(BaseRoom baseRoom);

    /*
    * 查询房屋列表 ygy
    * */
    List<BaseRoom> selectRoomList(Map<String, Object> params);

    /*
     * 新增楼栋信息 ygy
     * */
    int insertBuilding(BaseRoom baseRoom);

    /*
     * 删除楼栋信息 by code  ygy
     * */
    int deleteBuildingByKey(String buildingCode);

    /*
     * 更新楼栋信息 by code ygy
     * */
    int updateBuildingByKey(BaseRoom baseRoom);

    /*
     * 查询楼栋信息列表 ygy
     * */
    List<BaseRoom>  selectBuildingList(Map<String, Object> params);

    /**
     * 新增单元信息 dee
     * @param baseRoom
     * @return
     */
    int insertUnit(BaseRoom baseRoom);

    /**
     * 修改单元信息 dee
     * @param baseRoom
     * @return
     */
    int updateUnit(BaseRoom baseRoom);

    /**
     * 按id修改 dee
     * @param baseRoom
     * @return
     * 楼栋信息更新  ps  某单条记录只存在楼栋 不存在单元  更新楼栋 插入单元信息
     */
    int updateBuildingById(BaseRoom baseRoom);

    /**
     * 删除单元信息 dee
     * @param unitCode
     * @return
     */
    int deleteUnit(String unitCode);

    /**
     * 查询单元信息 dee
     * @param params
     * @return
     */
    List<BaseRoom> selectUnitList(Map<String, Object> params);

    /**
     * 清空小区下所有楼 单元 房间 dee
     * @param communityCode
     * @return
     */
    int deleteAnyByCommunityCode(String communityCode);

    /**
     * 查询单元下的其它房间
     * @param params
     * @return
     */
    List<BaseRoom> selectOtherRoomsInUnit(Map<String, Object> params);

    /**
     * 查询楼栋下的其它单元
     * @param params
     * @return
     */
    List<BaseRoom> selectOtherUnitsInBuilding(Map<String, Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/20 下午5:07
     * @description：  根据房屋小区code 小区name
     */
    int updateRoomByCommunityCode(Map<String,Object> params);
}