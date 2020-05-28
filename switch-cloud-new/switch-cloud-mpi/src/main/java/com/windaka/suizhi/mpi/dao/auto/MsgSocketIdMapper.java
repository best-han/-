package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.MsgSocketId;
import com.windaka.suizhi.mpi.model.MsgSocketIdExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MsgSocketIdMapper {
    int countByExample(MsgSocketIdExample example);

    int deleteByExample(MsgSocketIdExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MsgSocketId record);

    int insertSelective(MsgSocketId record);

    List<MsgSocketId> selectByExample(MsgSocketIdExample example);

    MsgSocketId selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MsgSocketId record, @Param("example") MsgSocketIdExample example);

    int updateByExample(@Param("record") MsgSocketId record, @Param("example") MsgSocketIdExample example);

    int updateByPrimaryKeySelective(MsgSocketId record);

    int updateByPrimaryKey(MsgSocketId record);
}