package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.FaceGroupDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaceGroupDetailMapper {
    /*
     * 布控库人员明细  新增 ygy
     * */
    int insertFaceGroupDetail(FaceGroupDetail faceGroupDetail);

    /*
     * 布控库人员明细 删除 ygy
     * */
    int deleteById(String id);

    /*
     * 布控库人员明细 更新 ygy
     * */
    int updateById(FaceGroupDetail faceGroupDetail);

    /*
     * 布控库 人员明细 列表查询 ygy
     * */
    List<FaceGroupDetail> selectFaceGroupDetailList(Map<String, Object> params);


}