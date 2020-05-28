package com.windaka.suizhi.mpi.dao.auto;

import com.windaka.suizhi.mpi.model.CapturePerson;
import com.windaka.suizhi.mpi.model.CapturePersonExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CapturePersonMapper {
    int countByExample(CapturePersonExample example);

    int deleteByExample(CapturePersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CapturePerson record);

    int insertSelective(CapturePerson record);

    List<CapturePerson> selectByExample(CapturePersonExample example);

    CapturePerson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CapturePerson record, @Param("example") CapturePersonExample example);

    int updateByExample(@Param("record") CapturePerson record, @Param("example") CapturePersonExample example);

    int updateByPrimaryKeySelective(CapturePerson record);

    int updateByPrimaryKey(CapturePerson record);

    /**
     * @author ：ygy
     * @date   ：2020/3/27 下午7:31
     * @description：  webSocket告警记录列表查询
     */
    List<Map<String, Object>> queryCapPersonList(Integer id);
}