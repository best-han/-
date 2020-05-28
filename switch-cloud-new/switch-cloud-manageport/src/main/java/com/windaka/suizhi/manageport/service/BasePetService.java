package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.BasePet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BasePetService {
    /*
     * 宠物新增 ygy
     * */
    void insertPets(String communityCode, List pets) throws OssRenderException, IOException;

    /*
     * 宠物删除 ygy
     * */
    void deleteByCode(String code) throws OssRenderException;

    /*
     * 宠物更新 ygy
     * */
    void updateByCode(BasePet basePet) throws OssRenderException, IOException;

    /*
     * 宠物列表查询 ygy
     * */
    Map<String, Object> selectPetsList(Map<String, Object> params) throws OssRenderException;
}
