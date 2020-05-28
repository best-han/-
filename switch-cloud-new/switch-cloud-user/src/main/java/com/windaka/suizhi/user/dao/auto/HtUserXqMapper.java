package com.windaka.suizhi.user.dao.auto;

import com.windaka.suizhi.user.model.HtUserXq;
import com.windaka.suizhi.user.model.HtUserXqExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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