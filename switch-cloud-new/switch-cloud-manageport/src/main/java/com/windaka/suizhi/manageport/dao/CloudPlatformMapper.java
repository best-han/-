package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.OperateRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CloudPlatformMapper {
    /*
    * 获取指定表的 下一条记录的id （待插入数据的id） ygy
    * */
    Integer getNextId(String tableName);

    int insertRecord(OperateRecord operateRecord);
}
