package com.windaka.suizhi.mpi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SuspectedAppDao {

    List<Map<String,Object>> querySuspectedAddPersonAppGroupByXqCode();

    List<Map<String,Object>> querySuspectedQuitPersonAppGroupByXqCode();

}
