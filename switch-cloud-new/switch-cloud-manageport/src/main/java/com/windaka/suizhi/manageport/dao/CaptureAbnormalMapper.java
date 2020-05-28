package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CaptureAbnormal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface CaptureAbnormalMapper {
    /*
     * 异常行为 新增 ygy
     * */
    int insertCaptureAbnormal(CaptureAbnormal captureAbnormal);

    /*
     * 异常行为 删除 ygy
     * */
    int deleteById(String id);

    /*
     * 异常行为 更新 ygy
     * */
    int updateById(CaptureAbnormal captureAbnormal);

    /*
     * 异常行为 列表查询 ygy
     * */
    List<CaptureAbnormal> selectCaptureAbnormalList(Map<String, Object> params);
}