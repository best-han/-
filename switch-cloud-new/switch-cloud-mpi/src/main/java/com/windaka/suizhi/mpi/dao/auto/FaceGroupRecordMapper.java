package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.FaceGroupRecord;
import com.windaka.suizhi.mpi.model.FaceGroupRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FaceGroupRecordMapper {
    int countByExample(FaceGroupRecordExample example);

    int deleteByExample(FaceGroupRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceGroupRecord record);

    int insertSelective(FaceGroupRecord record);

    List<FaceGroupRecord> selectByExample(FaceGroupRecordExample example);

    FaceGroupRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceGroupRecord record, @Param("example") FaceGroupRecordExample example);

    int updateByExample(@Param("record") FaceGroupRecord record, @Param("example") FaceGroupRecordExample example);

    int updateByPrimaryKeySelective(FaceGroupRecord record);

    int updateByPrimaryKey(FaceGroupRecord record);
}