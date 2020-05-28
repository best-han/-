package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CloudPlatformMapper {
    /*
    * 获取指定表的 下一条记录的id （待插入数据的id） ygy
    * */
    Integer getNextId(String tableName);

}
