package com.windaka.suizhi.manageport.service;


import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.model.FaceGroup;

import java.util.List;
import java.util.Map;

public interface FaceGroupService {
    /*
     * 布控库 新增 ygy
     * */
    void insertFaceGroup(List faceGroups) throws OssRenderException;

    /*
     * 布控库 删除 ygy
     * */
    void deleteByCode(String code) throws OssRenderException;

    /*
     * 布控库 更新ygy
     * */
    void updateByCode(FaceGroup faceGroup) throws OssRenderException;

    /*
     * 布控库 列表查询 ygy
     * */
    Map<String, Object> selectFaceGroupList(Map<String, Object> params);

}
