package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.FaceGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaceGroupMapper {

    /*
     * 布控库 新增 ygy
     * */
    int insertFaceGroup(FaceGroup faceGroup);

    /*
     * 布控库 删除 ygy
     * */
    int deleteByCode(String code);

    /*
     * 布控库 更新ygy
     * */
    int updateByCode(FaceGroup code);

    /*
     * 布控库 列表查询 ygy
     * */
    List<FaceGroup> selectFaceGroupList(Map<String, Object> params);


}