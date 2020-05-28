package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaptureCarMapper {
    //    webSocket告警记录列表查询（车辆）
    List<Map<String,Object>> queryCapCarList(Integer id);

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午9:18
     * @description：  今日抓拍车辆数量
     */
    int selectCarCapNum(Map<String,Object> params);

    /**
     * @description: 车辆实时抓拍查询
     * @author wcl
     * @date 2020/4/10
     */
    List<Map<String,Object>> queryCarCaptureList(@Param("params") Map<String,Object> params);


    /**
     * @description: 车牌模糊查询
     * @author wcl
     * @date 2020/5/7
     */
    List<Map<String,Object>> queryCarNumLikeStr(@Param("carNumLikeStr") String carNumLikeStr,@Param("xqCode") String xqCode);
}