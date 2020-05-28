package com.windaka.suizhi.webapi.dao.auto;

import com.windaka.suizhi.webapi.model.HtUser;
import com.windaka.suizhi.webapi.model.HtUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface HtUserMapper {
    int countByExample(HtUserExample example);

    int deleteByExample(HtUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HtUser record);

    int insertSelective(HtUser record);

    List<HtUser> selectByExample(HtUserExample example);

    HtUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HtUser record, @Param("example") HtUserExample example);

    int updateByExample(@Param("record") HtUser record, @Param("example") HtUserExample example);

    int updateByPrimaryKeySelective(HtUser record);

    int updateByPrimaryKey(HtUser record);
}