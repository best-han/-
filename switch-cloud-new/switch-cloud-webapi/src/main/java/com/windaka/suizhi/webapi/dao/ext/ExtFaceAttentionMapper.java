package com.windaka.suizhi.webapi.dao.ext;

import com.windaka.suizhi.webapi.model.ext.ExtFaceAttentionDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName ExtFaceAttentionMapper
 * @Description 重点关注dao层
 * @Author lixianhua
 * @Date 2020/4/20 16:14
 * @Version 1.0
 */
@Mapper
public interface ExtFaceAttentionMapper {

    // 获取重点关注人员
    List<ExtFaceAttentionDetail> selectPersonList(ExtFaceAttentionDetail attentionDetail);

//    根据code 获取重点人员
    List<Map<String,Object>>  selectListByCode(Map<String,Object> params);
}
