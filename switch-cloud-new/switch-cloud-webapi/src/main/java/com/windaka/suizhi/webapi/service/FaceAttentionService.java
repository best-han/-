package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.webapi.model.FaceAttentionDetail;
import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtFaceAttentionDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @InterfaceName FaceAttentionService
 * @Description 重点人员接口
 * @Author lixianhua
 * @Date 2020/4/18 11:17
 * @Version 1.0
 */
public interface FaceAttentionService {

    // 查询重点关注人员列表
    List<ExtFaceAttentionDetail> selectPersonList(ExtFaceAttentionDetail attentionDetail, HttpServletRequest request);

    // 添加重点关注人员
    int insertPerson(ExtFaceAttentionDetail attentionDetail) throws OssRenderException;

    // 根据ID删除重点关注人员
    int deletePerson(String id);

    // 根据ID更新重点关注人员
    int updatePersonById(ExtFaceAttentionDetail detail);

    // 批量添加重点关注人员
    int insertBatchPerson(ExtFaceAttentionDetail attentionDetail) throws OssRenderException;
}
