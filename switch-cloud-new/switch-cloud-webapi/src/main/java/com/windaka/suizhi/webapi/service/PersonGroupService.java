package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.model.FaceGroup;
import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroup;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroupDetail;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @InterfaceName PersonGroupService
 * @Description 人员布控接口
 * @Author lixianhua
 * @Date 2020/4/8 9:59
 * @Version 1.0
 */
public interface PersonGroupService {

    // 获取人员布控库集合
    List<ExtFaceGroup> selectGroupList(ExtFaceGroup faceGroup, HttpServletRequest request);

    // 添加人员布控库
    int insertRecord(FaceGroup faceGroup) throws OssRenderException;

    // 更新人员布控库
    int updateGroup(FaceGroup faceGroup) throws OssRenderException;

    // 获取人员布控集合
    List<ExtFaceGroupDetail> selectPersonList(ExtFaceGroupDetail groupDetail,HttpServletRequest request);

    // 添加人员布控
    int insertPerson(ExtFaceGroupDetail faceGroupDetail) throws OssRenderException, IOException;

    // 根据ID删除人员库
    int deleteGroupById(String id) throws OssRenderException;

    // 根据ID删除布控人员
    int deletePerson(String id);

    // 根据ID更新人员布控信息
    int updatePersonById(ExtFaceGroupDetail detail) throws OssRenderException, IOException;

    // 批量添加人员布控
    int insertBatchRecord(List<ExtFaceGroupDetail> list) throws OssRenderException;
}
