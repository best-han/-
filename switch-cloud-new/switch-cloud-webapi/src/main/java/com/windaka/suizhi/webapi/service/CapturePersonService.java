package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

public interface CapturePersonService {

    //   socket 告警记录列表查询 推送
    List<Map<String,Object>> queryCapPersonList(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午8:25
     * @description：  陌生人员列表查询
     */
    Map<String ,Object> personStrangerList(Map<String,Object> params) throws OssRenderException;

    /**
     *
     *@description: 人员抓拍列表查询
     *@author: zdq
     *@time: 4/7/20 10:03 AM
     *
     */
    Map<String ,Object> personCaptureList(Map<String,Object> params) throws OssRenderException;
    //人员抓拍列表查询 通过ES查询
    Map<String, Object> personCaptureListByES(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午9:19
     * @description：  单人记录列表
     */
    Map<String,Object> singleCapPersonList(Map<String,Object> params) throws OssRenderException;
    Map<String,Object> singleCapPersonListByES(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/7 下午8:19
     * @description：  疑似新增人员列表
     */
    Map<String,Object> personAddedList(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/11 下午6:03
     * @description：  抓拍人员【以图搜图】点击查询
     */
    Map<String,Object> personCaptureListById(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/12 上午11:09
     * @description：  抓拍人员【以图搜图】图像查询
     */
    Map<String,Object> personCaptureListByImg(Map<String,Object> params) throws OssRenderException;

    /**
     * @author ：ygy
     * @date   ：2020/4/15 上午11:03
     * @description：  抓拍人员 15天 每天次数
     */
    Map<String,Object> capPerson15Day(Map<String,Object> params) throws OssRenderException;

    /**
     *
     *@description: 疑似迁出人员-忽略某小区的某人
     *@author: zdq
     *@time: 4/12/20 10:45 AM
     *
     */
    void ignoreQuitPerson(Map<String,Object> params,String username) throws OssRenderException;

    /**
     *
     *@description: 疑似迁出人员-迁出某小区的某人
     *@author: zdq
     *@time: 4/12/20 12:00 PM
     *
     */
    void deleteQuitPerson(Map<String,Object> params,String username) throws OssRenderException;

    /**
     *
     *@description: 疑似迁出人员-处理记录tab
     *@author: zdq
     *@time: 4/12/20 4:29 PM
     *
     */
    Map<String,Object> queryQuitPersonHandleList(Map<String,Object> params) throws OssRenderException;

    /**
     *
     *@description: 疑似迁出人员-迁出人员tab
     *@author: zdq
     *@time: 4/12/20 4:29 PM
     *
     */
    Map<String,Object> queryQuitPersonDeleteList(Map<String,Object> params) throws OssRenderException;

    /**
     *
     *@description: 疑似迁出人员-人员列表tab
     *@author: zdq
     *@time: 4/8/20 10:02 AM
     *
     */
    Map<String,Object> queryQuitPersonList(Map<String,Object> params) throws OssRenderException;
}
