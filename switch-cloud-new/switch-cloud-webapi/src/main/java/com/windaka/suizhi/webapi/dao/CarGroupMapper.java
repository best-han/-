package com.windaka.suizhi.webapi.dao;

import com.windaka.suizhi.webapi.model.CarGroup;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarGroupMapper {

    // 获取车辆库列表
    List<ExtCarGroup> selectList(CarGroup carGroup);

    // 添加车辆库
    int insertRecord(CarGroup carGroup);

    // 根据ID更新车辆库
    int updateRecordById(CarGroup carGroup);

    // 根据ID删除车辆库
    int deleteGroupById(int parseInt);

    // 根据code获取车辆库
    CarGroup getRecordByCode(String groupCode);

    // 根据车辆库名称获取记录
    ExtCarGroup getRecordByName(String name);

    // 根据ID获取车辆布控库
    CarGroup getRecordById(Integer id);
}