package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.SysDicData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDicDataMapper {

    /*
     * 字典数据 插入 ygy
     * */
    int insertSysDicData(SysDicData sysDicData);

    /*
     * 字典数据 删除 by id   ygy
     * */
    int deleteById(String id);

    /*
     *字典数据 更新 by id  ygy
     * */
    int updateById(SysDicData sysDicData);

    /*
     * 字典数据 查询 by keyword  ygy
     * */
    List<SysDicData> selectSysDicDataList(Map<String, Object> params);


}