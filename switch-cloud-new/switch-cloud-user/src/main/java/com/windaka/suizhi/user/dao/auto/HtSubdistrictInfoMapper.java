package com.windaka.suizhi.user.dao.auto;

import com.windaka.suizhi.user.model.HtSubdistrictInfo;
import com.windaka.suizhi.user.model.HtSubdistrictInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface HtSubdistrictInfoMapper {
    int countByExample(HtSubdistrictInfoExample example);

    int deleteByExample(HtSubdistrictInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HtSubdistrictInfo record);

    int insertSelective(HtSubdistrictInfo record);

    List<HtSubdistrictInfo> selectByExample(HtSubdistrictInfoExample example);

    HtSubdistrictInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HtSubdistrictInfo record, @Param("example") HtSubdistrictInfoExample example);

    int updateByExample(@Param("record") HtSubdistrictInfo record, @Param("example") HtSubdistrictInfoExample example);

    int updateByPrimaryKeySelective(HtSubdistrictInfo record);

    int updateByPrimaryKey(HtSubdistrictInfo record);
}