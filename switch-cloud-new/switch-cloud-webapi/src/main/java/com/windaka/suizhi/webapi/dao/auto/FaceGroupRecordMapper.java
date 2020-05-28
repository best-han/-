package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.FaceGroupRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaceGroupRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FaceGroupRecord record);

    int insertSelective(FaceGroupRecord record);

    FaceGroupRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FaceGroupRecord record);

    int updateByPrimaryKey(FaceGroupRecord record);
}