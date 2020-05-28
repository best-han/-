package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.SysDic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDicMapper {
    /*
     * 字典 插入 ygy
     * */
    int insertSysDic(SysDic sysDic);

    /*
     * 字典 删除 by code   ygy
     * */
    int deleteByCode(String dicCode);

    /*
     *字典 更新 by code  ygy
     * */
    int updateByCode(SysDic sysDic);

    /*
     * 字典 查询 by keyword  ygy
     * */
    List<SysDic> selectSysDicList(Map<String, Object> params);

}