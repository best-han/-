package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

public interface BasePersonService {

    /**
     * @author ：ygy
     * @date   ：2020/3/28 上午8:15
     * @description：  人员基础信息
     */
    Map<String, Object> personBaseInfo(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/2 下午4:08
     * @description：  人员分布信息
     */
    List<Map<String,Object>> personDistribute(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/2 下午5:11
     * @description：  现有人员列表查询
     */
    Map<String,Object> personList(Map<String,Object> params) throws OssRenderException;
    /**
     *
     *@description: 查询个人信息
     *@author: zdq
     *@time: 4/3/20 10:50 AM
     *
     */
    Map<String, Object> querySinglePerson(Map<String,Object> params) throws OssRenderException;

    /**
     *
     *@description: 查询个人信息-布控标签
     *@author: zdq
     *@time: 4/3/20 3:29 PM
     *
     */
    List queryFaceGroupLabel(Map<String,Object> params) throws OssRenderException;

    /**
     *
     *@description: 查询个人信息-异常行为标签
     *@author: zdq
     *@time: 4/3/20 3:55 PM
     *
     */
    List queryAbnormalLabel(Map<String,Object> params) throws OssRenderException;

    /**
     *
     *@description: 根据personCode 查询本人拥有的房屋信息列表
     *@author: zdq
     *@time: 4/3/20 4:21 PM
     *
     */
    Map<String, Object> queryHouseListByPersonCode(Map<String, Object> params) throws OssRenderException;

    /**
     *
     *@description: 根据personCode 查询本人拥有的车辆信息列表
     *@author: zdq
     *@time: 4/3/20 7:04 PM
     *
     */
    List<Map<String, Object>> queryCarListByPersonCode(Map<String, Object> params) throws OssRenderException;

    /**
     *
     *@description: 根据personCode 查询本人近30天的抓拍次数
     *@author: zdq
     *@time: 4/3/20 7:31 PM
     *
     */
    Map<String, Object> queryCaptureTimesByPersonCode(Map<String, Object> params) throws OssRenderException;

    /**
     *
     *@description: 根据
     *@author: zdq
     *@time: 4/3/20 8:15 PM
     *
     */
    Map<String, Object> queryRelationShipByPersonCode(Map<String, Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午10:03
     * @description：  国家列表
     */
    List<Map<String,Object>> countryList(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午8:45
     * @description：  基础要素管理
     */
    Map<String,Object> baseData(Map<String,Object> params) throws OssRenderException;
}
