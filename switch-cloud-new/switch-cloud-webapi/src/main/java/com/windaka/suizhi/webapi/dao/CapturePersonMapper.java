package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;


@Mapper
public interface CapturePersonMapper {

    /**
     * @author ：ygy
     * @date   ：2020/3/27 下午7:31
     * @description：  webSocket告警记录列表查询
     */
    List<Map<String, Object>> queryCapPersonList(Integer id);

    /**
     * @author ：ygy
     * @date   ：2020/3/27 下午7:31
     * @description：   查询感知人员数量
     */
    int SensePersonNum(Map<String, Object> params);

//     <!--    根据id 查询抓拍人员信息将信息插入告警人员表-->
    List<Map<String,Object>> selectCapturePersonInfo(String id);

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午8:22
     * @description：  陌生人员列表查询
     */
    List<Map<String,Object>> personStrangerList(Map<String,Object> params);
    List<Map<String,Object>> selectCapInfoById(String id);

    /**
     *
     *@description: 人员抓拍列表查询
     *@author: zdq
     *@time: 4/7/20 9:35 AM
     *
     */
    List<Map<String,Object>> personCaptureList(Map<String,Object> params);
    List selectPersonCodeById(String id);

    /**
     * @author ：ygy
     * @date   ：2020/4/7 上午9:17
     * @description：  陌生人员感知-单人记录列表
     */
    List<Map<String,Object>> selectCapPersonByCode(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/7 下午8:18
     * @description：  定时疑似新增人员列表
     */
    List<Map<String,Object>> personAddedList();

    List<Map<String, Object>> selectPersonInfoByCode(String personCode);
    /**
     * @author ：ygy
     * @date   ：2020/4/18 下午5:51
     * @description：  业务查询疑似新增人员列表
     */
    List<Map<String,Object>> selectPersonAddList(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/18 下午4:55
     * @description：  疑似新增人员表 更新
     *
     */
    //若存在此人 则部分信息更新
    int updatePersonAddTable(Map<String,Object> params);

    //若存在此人 若已发短信 则在15天后将信息发送状态改为 未发送 若超过15天
    int updatePersonAddTable15(String personCode);

    // 根据personCode 判断是否存在此人
    List<Map<String,Object>> selectPersonAddByCode(String personCode);

    //清理 之前感知到  16天后 并未感知到到人员
    int deletePersonAdd();

    /**
     * @author ：ygy
     * @date   ：2020/4/18 下午4:57
     * @description：  疑似新增人 插入
     */
    int insertPersonAddTable(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午9:38
     * @description：  今日抓拍人数
     */
    int selectPersonCapNum(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/11 下午6:02
     * @description：  抓拍人员【以图搜图】点击查询
     */
    List<Map<String,Object>> selectCapPersonById(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/11 下午8:22
     * @description：  抓拍人员【以图搜图】图像查询
     */
    List<Map<String,Object>> selectCapPersonByCodes(Map<String,Object> params);

    /**
     *
     *@description: 疑似迁入人员 总数
     *@author: zdq
     *@time: 5/14/20 5:49 PM
     *
     */
    int selectPersonAddListCount(Map<String,Object> params);

    /**
     *
     *@description: 疑似新增人员 警务通推送表 清空
     *@author: zdq
     *@time: 5/18/20 5:53 PM
     *
     */
    int emptyPersonAddApp();

    /**
     *
     *@description: 疑似新增人员 警务通推送项 增加
     *@author: zdq
     *@time: 5/18/20 5:54 PM
     *
     */
    int insertPersonAddApp(Map<String,Object> params);

    /**
     *
     *@description: 疑似迁出人员-定时任务专用
     *@author: zdq
     *@time: 4/10/20 9:08 AM
     *
     */
    List<Map<String,Object>> personQuitListTask();

    /**
     *
     *@description: 疑似迁出人员(车辆)-定时任务专用
     *@author: zdq
     *@time: 4/10/20 9:08 AM
     *
     */
    List<Map<String,Object>> personCarQuitListTask(Map<String,Object> params);

    /**
     *
     *@description: 疑似迁出人员(每日更新)
     *@author: zdq
     *@time: 4/11/20 6:11 PM
     *
     */
    int insertPersonQuitList(Map<String,Object> params);

    /**
     *
     *@description: 疑似迁出人员(清空)
     *@author: zdq
     *@time: 4/11/20 6:11 PM
     *
     */
    int emptyPersonQuitList();

    /**
     *
     *@description: 疑似迁出人员-删除
     *@author: zdq
     *@time: 4/12/20 10:13 AM
     *
     */
    int deletePersonQuitList(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-忽略某小区的某人
     *@author: zdq
     *@time: 4/11/20 10:01 PM
     *
     */
    int insertPersonQuitIgnoreList(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-查询忽略项是否存在
     *@author: zdq
     *@time: 4/12/20 10:57 AM
     *
     */
    int queryPersonQuitIgnoreNum(Map<String, Object> params);

    List<Map<String,Object>> queryPersonQuitIgnoreCondition(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-更新忽略项
     *@author: zdq
     *@time: 4/12/20 10:58 AM
     *
     */
    int updatePersonQuitIgnoreList(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-迁出某小区的某人
     *@author: zdq
     *@time: 4/11/20 10:04 PM
     *
     */
    int insertPersonQuitRecordList(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-添加处理记录
     *@author: zdq
     *@time: 4/12/20 10:13 AM
     *
     */
    int insertPersonQuitHandleList(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-单独查询
     *@author: zdq
     *@time: 4/12/20 11:42 AM
     *
     */
    List<Map<String,Object>> quitPersonListSingle(Map<String, Object> params);
    List<Map<String,Object>> quitPersonListSingleByCode(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-处理记录tab
     *@author: zdq
     *@time: 4/12/20 3:57 PM
     *
     */
    List<Map<String,Object>> quitPersonHandleList(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-迁出人员tab
     *@author: zdq
     *@time: 4/12/20 4:01 PM
     *
     */
    List<Map<String,Object>> quitPersonDeleteList(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-查询迁出项是否存在
     *@author: zdq
     *@time: 4/12/20 5:52 PM
     *
     */
    int queryPersonQuitRecordNum(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-总数
     *@author: zdq
     *@time: 5/14/20 5:38 PM
     *
     */
    int queryPersonQuitTotal(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-查询发送短信的记录
     *@author: zdq
     *@time: 5/18/20 9:07 AM
     *
     */
    List<Map<String,Object>> queryPersonQuitMsgCondition(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-更新短信发送状态
     *@author: zdq
     *@time: 5/18/20 9:08 AM
     *
     */
    int updatePersonQuitMsgStatus(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-插入至短信发送表中
     *@author: zdq
     *@time: 5/18/20 9:10 AM
     *
     */
    int insertPersonQuitMsg(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-从短信发送表中删除
     *@author: zdq
     *@time: 5/18/20 10:25 AM
     *
     */
    int deletePersonQuitMsg(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-警务通清空推送列表
     *@author: zdq
     *@time: 5/18/20 9:21 AM
     *
     */
    int emptySuspectedPersonQuitApp();

    /**
     *
     *@description: 疑似迁出人员-警务通增加推送项
     *@author: zdq
     *@time: 5/18/20 11:58 AM
     *
     */
    int insertPersonQuitApp(Map<String, Object> params);

    /**
     *
     *@description: 疑似迁出人员-人员列表tab
     *@author: zdq
     *@time: 4/8/20 8:31 AM
     *
     */
    List<Map<String,Object>> quitPersonList(Map<String,Object> params);
}