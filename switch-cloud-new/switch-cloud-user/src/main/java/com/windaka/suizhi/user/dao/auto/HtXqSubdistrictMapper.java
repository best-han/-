package com.windaka.suizhi.user.dao.auto;

import com.windaka.suizhi.user.model.HtXqSubdistrict;
import com.windaka.suizhi.user.model.HtXqSubdistrictExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface HtXqSubdistrictMapper {
    int countByExample(HtXqSubdistrictExample example);

    int deleteByExample(HtXqSubdistrictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HtXqSubdistrict record);

    int insertSelective(HtXqSubdistrict record);

    List<HtXqSubdistrict> selectByExample(HtXqSubdistrictExample example);

    HtXqSubdistrict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HtXqSubdistrict record, @Param("example") HtXqSubdistrictExample example);

    int updateByExample(@Param("record") HtXqSubdistrict record, @Param("example") HtXqSubdistrictExample example);

    int updateByPrimaryKeySelective(HtXqSubdistrict record);

    int updateByPrimaryKey(HtXqSubdistrict record);
}