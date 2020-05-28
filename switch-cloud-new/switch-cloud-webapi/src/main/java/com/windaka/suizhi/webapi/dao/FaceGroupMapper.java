package com.windaka.suizhi.webapi.dao;

import com.windaka.suizhi.webapi.model.FaceGroup;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface FaceGroupMapper {

    // 获取人员布控库集合
    List<ExtFaceGroup> selectList(FaceGroup faceGroup);

    // 添加人员布控库
    int insertRecord(FaceGroup faceGroup);

    // 根据主键更新人员布控库
    int updateRecordById(FaceGroup faceGroup);

    int deleteGroupById(Integer id);

    // 根据code人员布控
    FaceGroup getRecordByCode(String groupCode);

    // 根据库名称获取记录
    ExtFaceGroup getRecordByName(String name);

    // 根据ID获取人员布控库
    FaceGroup getRecordById(Integer id);
}