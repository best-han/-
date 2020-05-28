package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.PersonInout;
import com.windaka.suizhi.mpi.model.PersonInoutExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PersonInoutMapper {
    int countByExample(PersonInoutExample example);

    int deleteByExample(PersonInoutExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PersonInout record);

    int insertSelective(PersonInout record);

    List<PersonInout> selectByExample(PersonInoutExample example);

    PersonInout selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PersonInout record, @Param("example") PersonInoutExample example);

    int updateByExample(@Param("record") PersonInout record, @Param("example") PersonInoutExample example);

    int updateByPrimaryKeySelective(PersonInout record);

    int updateByPrimaryKey(PersonInout record);
}