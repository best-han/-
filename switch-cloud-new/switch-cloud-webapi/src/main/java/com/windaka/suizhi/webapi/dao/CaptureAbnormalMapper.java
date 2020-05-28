package com.windaka.suizhi.webapi.dao;

import com.windaka.suizhi.webapi.model.CaptureAbnormal;
import com.windaka.suizhi.webapi.model.ext.ExtCaptureAbnormal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CaptureAbnormalMapper {
    // 获取异常预警信息
    List<ExtCaptureAbnormal> selectAbnormalList(ExtCaptureAbnormal abnormal);
    // 根据ID获取异常预警
    CaptureAbnormal getAbnormalById(Integer id);
    // 根据ID更新异常信息
    int updateAbnormalById(CaptureAbnormal abnormal);
    // 获取异常预警总数
    Integer countAbnormal(ExtCaptureAbnormal abnormal);
    // 警务通 获取异常集合
    List<ExtCaptureAbnormal> selectAppAbnormalList(ExtCaptureAbnormal abnormal);
}