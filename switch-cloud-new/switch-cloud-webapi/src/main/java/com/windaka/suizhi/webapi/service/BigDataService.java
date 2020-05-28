package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;

import java.util.List;
import java.util.Map;

public interface BigDataService {


    /**
     * 频繁夜归人员 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    Map<String,Object> getNightReturnPerson(Map<String, Object> params) throws OssRenderException;

    /**
     * 单个频繁夜归人员 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    Map<String,Object> getNightReturnPersonOne(Map<String, Object> params) throws OssRenderException;

    /**
     * 频繁夜归车辆 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    Map<String,Object> getNightReturnCar(Map<String, Object> params) throws OssRenderException;

    /**
     * 单个频繁夜归车辆 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    Map<String,Object> getNightReturnCarOne(Map<String, Object> params) throws OssRenderException;

    /**
     * 用水异常 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    public Map<String,Object> waterRate(Map<String, Object> params) throws OssRenderException;

    /**
     * 群租房数据 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    public Map<String,Object> groupRentalResearch(Map<String, Object> params) throws OssRenderException;

    /**
     * 日租、短租房数据 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    public Map<String,Object> dayRentalResearch(Map<String, Object> params) throws OssRenderException;

    /**
     * 疑似闲置房屋列表 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    Map<String,Object> queryIdleRoomList(Map<String, Object> params) throws OssRenderException;

    /**
     * 闲置房屋疑似居住列表 hjt
     * @param params
     * @return
     * @throws OssRenderException
     */
    Map<String,Object> queryIdleRoomLivedList(Map<String, Object> params) throws OssRenderException;
}
