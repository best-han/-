package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PersonInoutService {

    Map<String,Object> selectPersonInoutList(Map<String, Object> params) throws OssRenderException;

    void insertPersonInout(String communityCode, List lists, List resultMapList) throws OssRenderException, IOException;

    void deletePersonInoutById(int id) throws OssRenderException;
}
