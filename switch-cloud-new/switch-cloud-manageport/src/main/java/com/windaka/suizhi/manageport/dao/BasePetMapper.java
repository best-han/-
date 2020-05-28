package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.BasePet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BasePetMapper {
    /*
     * 宠物新增 ygy
     * */
    int insertPets(BasePet basePet);

    /*
     * 宠物删除 ygy
     * */
    int deleteByCode(String code);


    /*
     * 宠物更新 ygy
     * */
    int updateByCode(BasePet basePet);

    /*
     * 宠物列表查询 ygy
     * */
    List<BasePet> selectPetsList(Map<String, Object> params);

    /*
    zdq 小区级联修改 宠物
     */
    int updatePetByCommunityCode(Map<String,Object> params);
}