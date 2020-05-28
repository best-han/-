package com.windaka.suizhi.user.dao.auto;

import com.windaka.suizhi.user.model.HtUserSubdistrict;
import com.windaka.suizhi.user.model.HtUserSubdistrictExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HtUserSubdistrictMapper {
    int countByExample(HtUserSubdistrictExample example);

    int deleteByExample(HtUserSubdistrictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HtUserSubdistrict record);

    int insertSelective(HtUserSubdistrict record);

    List<HtUserSubdistrict> selectByExample(HtUserSubdistrictExample example);

    HtUserSubdistrict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HtUserSubdistrict record, @Param("example") HtUserSubdistrictExample example);

    int updateByExample(@Param("record") HtUserSubdistrict record, @Param("example") HtUserSubdistrictExample example);

    int updateByPrimaryKeySelective(HtUserSubdistrict record);

    int updateByPrimaryKey(HtUserSubdistrict record);
}