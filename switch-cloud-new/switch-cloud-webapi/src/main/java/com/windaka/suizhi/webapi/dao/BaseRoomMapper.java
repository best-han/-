package com.windaka.suizhi.webapi.dao;

import cn.hutool.core.util.ObjectUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


@Mapper
public interface BaseRoomMapper {

    /**
     * @author ：ygy
     * @date   ：2020/4/3 上午9:12
     * @description：  根据roomCode 查找房间名
     */
    String queryHouseNameByRoomCode(String roomCode);

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午8:58
     * @description：  实有房屋数量
     */
    int selectRoomNum(Map<String,Object> params);


}