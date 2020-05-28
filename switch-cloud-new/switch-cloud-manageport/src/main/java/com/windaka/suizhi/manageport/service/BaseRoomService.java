package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.BaseRoom;

import java.util.List;
import java.util.Map;

public interface BaseRoomService {
    /*
     *新增房屋信息 ygy
     * */
    void insertRoom(List baseRoom) throws OssRenderException;

    /*
     *删除房屋信息 by code  ygy
     * */
    void deleteRoomByCode(String code) throws OssRenderException;

    /*
     *更新房屋信息 by code ygy
     * */
    void updateRoomByCode(BaseRoom baseRoom) throws OssRenderException;

    /*
     *查询房屋列表 ygy
     * */
    Map<String, Object> selectRoomList(Map<String, Object> params) throws OssRenderException;

    /**
     * 新增单元信息 dee
     * @param units
     * @throws OssRenderException
     */
    void insertUnit(List units) throws OssRenderException;

    void updateUnit(BaseRoom baseRoom) throws OssRenderException;

    void deleteUnit(String unitCode) throws OssRenderException;

    Map<String, Object> selectUnitList(Map<String, Object> params) throws OssRenderException;

    /*
     *新增房屋信息 ygy
     * */
    void insertBuilding(List baseRoom) throws OssRenderException;

    /*
     *删除房屋信息 by code  ygy
     * */
    void deleteBuildingByKey(String buildingCode) throws OssRenderException;

    /*
     *更新房屋信息 by code ygy
     * */
    void updateBuildingByKey(BaseRoom baseRoom) throws OssRenderException;

    /*
     *查询房屋列表 ygy
     * */
    Map<String, Object> selectBuildingList(Map<String, Object> params) throws OssRenderException;
}
