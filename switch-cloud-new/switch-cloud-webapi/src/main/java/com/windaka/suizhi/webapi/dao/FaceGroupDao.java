package com.windaka.suizhi.webapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaceGroupDao {
    /**
     * 新增
     * @param params
     * @return
     */
    int saveFaceGroup(Map<String, Object> params);

    /**
     * 修改
     * @param params
     * @return
     */
    int updateFaceGroup(Map<String, Object> params);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteFaceGroupByCode(String id);

    /**
     * 查询人脸库
     * @param params
     * @return
     */
    List<Map<String,Object>> queryFaceGroups(Map<String, Object> params);
    
    /**
     * 查人脸库总数
     * @param params
     * @return
     */
    int queryTotalFaceGroup(Map<String, Object> params);

}
