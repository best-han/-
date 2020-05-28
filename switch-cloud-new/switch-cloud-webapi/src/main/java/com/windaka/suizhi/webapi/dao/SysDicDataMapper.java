package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface SysDicDataMapper {

/**
 * @author ：ygy
 * @date   ：2020/4/3 上午11:21
 * @description：  根据字典标签和key值  得到value
 */
    String getDictValue(Map<String,Object> params);
/**
 * @author ：ygy
 * @date   ：2020/4/3 下午3:13
 * @description：  根据字典标签和value值 得到Key
 */
    String getDictKey(Map<String,Object> params);
}