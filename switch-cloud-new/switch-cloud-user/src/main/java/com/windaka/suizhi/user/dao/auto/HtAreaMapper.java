package com.windaka.suizhi.user.dao.auto;

import com.windaka.suizhi.user.model.HtArea;
import com.windaka.suizhi.user.model.HtAreaExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface HtAreaMapper {
    int countByExample(HtAreaExample example);

    int deleteByExample(HtAreaExample example);

    int deleteByPrimaryKey(String areaId);

    int insert(HtArea record);

    int insertSelective(HtArea record);

    List<HtArea> selectByExample(HtAreaExample example);

    HtArea selectByPrimaryKey(String areaId);

    int updateByExampleSelective(@Param("record") HtArea record, @Param("example") HtAreaExample example);

    int updateByExample(@Param("record") HtArea record, @Param("example") HtAreaExample example);

    int updateByPrimaryKeySelective(HtArea record);

    int updateByPrimaryKey(HtArea record);
}