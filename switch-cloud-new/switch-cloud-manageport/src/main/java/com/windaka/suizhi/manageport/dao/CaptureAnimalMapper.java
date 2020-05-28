package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.CaptureAnimal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaptureAnimalMapper {
    /*
     * 宠物抓拍 新增 ygy
     * */
    int insertCapturePets(CaptureAnimal captureAnimal);

    /*
     * 宠物抓拍删除 ygy
     * */
    int deleteById(String id);

    /*
     * 宠物抓拍列表查询 ygy
     * */
    List<CaptureAnimal> selectCapturePetList(Map<String, Object> params);
}