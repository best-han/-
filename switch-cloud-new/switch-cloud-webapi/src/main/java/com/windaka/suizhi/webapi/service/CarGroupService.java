package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.model.CarGroup;
import com.windaka.suizhi.webapi.model.CarGroupDetail;
import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroup;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroupDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @InterfaceName CarGroupService
 * @Description 车辆布控接口
 * @Author lixianhua
 * @Date 2020/4/8 10:00
 * @Version 1.0
 */
public interface CarGroupService {

    // 获取车辆列表
    List<ExtCarGroup> selectGroupList(ExtCarGroup carGroup, HttpServletRequest request);

    // 添加车辆库
    int insertRecord(CarGroup carGroup) throws OssRenderException;

    // 根据ID更新车辆库
    int updateGroup(CarGroup carGroup) throws OssRenderException;

    // 根据ID删除车辆布控库
    int deleteGroupById(String id);

    // 获取车辆布控列表
    List<ExtCarGroupDetail> selectCarList(ExtCarGroupDetail groupDetail,HttpServletRequest request);

    // 添加车辆布控
    int insertCar(ExtCarGroupDetail carGroupDetail) throws OssRenderException;

    // 删除车辆布控
    int deleteCar(String id);

    // 根据ID更新布控车辆信息
    int updateCarById(ExtCarGroupDetail detail) throws OssRenderException;

    // 批量添加车辆布控
    int insertBatchRecord(List<CarGroupDetail> list);
}
