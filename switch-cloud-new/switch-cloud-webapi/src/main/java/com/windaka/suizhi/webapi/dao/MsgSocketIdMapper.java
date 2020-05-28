package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MsgSocketIdMapper {


//    根据记录表查找 上一次查询的最大id-
    int queryLastIdByRecordName(String recordName);


//    更新id记录表
    int updateLastIdByRecordName(Map<String,Object> params);
}
