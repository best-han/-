package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.HtUserXq;
import com.windaka.suizhi.mpi.model.HtUserXqExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface HtUserXqMapper {
    int countByExample(HtUserXqExample example);

    int deleteByExample(HtUserXqExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HtUserXq record);

    int insertSelective(HtUserXq record);

    List<HtUserXq> selectByExample(HtUserXqExample example);

    HtUserXq selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HtUserXq record, @Param("example") HtUserXqExample example);

    int updateByExample(@Param("record") HtUserXq record, @Param("example") HtUserXqExample example);

    int updateByPrimaryKeySelective(HtUserXq record);

    int updateByPrimaryKey(HtUserXq record);
}