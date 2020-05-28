package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IdleRoomDao {

    /**
     * 查询某个月的疑似闲置房屋  hjt
     * @param params
     * @return
     */
    List<Map<String,Object>> queryIdleRoomByWaterUse(Map<String, Object> params);

    /**
     * 查询疑似闲置房屋结果表(单个查) hjt
     * @param params
     * @return
     */
    List<Map<String,Object>> queryIdleRoom(Map<String, Object> params);
    /**
     * 增加疑似闲置房屋  hjt
     * @param params
     * @return
     */
    int addIdleRoom(Map<String, Object> params);

    /**
     * 更改疑似闲置房屋的闲置时长  hjt
     * @param params
     * @return
     */
    int updateIdleRoomByRoomCode(Map<String, Object> params);
    /**
     * 查询疑似闲置房屋列表查 hjt
     * @param params
     * @return
     */
    List<Map<String,Object>> queryIdleRoomList(Map<String, Object> params);

    /**
     * 查询疑似闲置房屋总数  hjt
     * @param params
     * @return
     */
    int totalIdleRoom(Map<String, Object> params);

    /**
     * 查询某个月的闲置房屋疑似居住  hjt
     * @param params
     * @return
     */
    List<Map<String,Object>> queryIdleRoomLivedByWaterUse(Map<String, Object> params);

    /**
     * 查询闲置房屋疑似居住列表查 hjt
     * @param params
     * @return
     */
    List<Map<String,Object>> queryIdleRoomLivedList(Map<String, Object> params);

    /**
     * 查询闲置房屋疑似居住总数  hjt
     * @param params
     * @return
     */
    int totalIdleRoomLived(Map<String, Object> params);
}
