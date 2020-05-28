package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.BasePerson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BasePersonService {

    void insertPerson(String communityCode, List persons, List resultMapList) throws OssRenderException, IOException;

    void updatePersonByCode(Map basePersonMap,Map parseResultMap) throws OssRenderException, IOException;

    void deletePersonByCode(String code) throws OssRenderException;

    void insertPersonHouse(List list) throws OssRenderException;

    void updatePersonHouseByCode(BasePerson basePerson) throws OssRenderException;

    void deletePersonHouseByCode(BasePerson basePerson) throws OssRenderException;

    Map<String,Object> selectPersonList(Map<String, Object> params) throws OssRenderException;

    void insertPersonSingle(String communityCode, List persons, List resultMapList) throws OssRenderException, IOException;

}
