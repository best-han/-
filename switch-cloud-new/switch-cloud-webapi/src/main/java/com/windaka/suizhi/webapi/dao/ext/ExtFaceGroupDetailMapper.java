package com.windaka.suizhi.webapi.dao.ext;

import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroupDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName ExtFaceGroupDetailMapper
 * @Description 非自动生成
 * @Author lixianhua
 * @Date 2020/4/17 11:49
 * @Version 1.0
 */
@Mapper
public interface ExtFaceGroupDetailMapper {


    /**
     * @author ：ygy
     * @date ：2020/4/3 上午11:52
     * @description： 根据布控明细id 查询对应布控库等级
     */
    String selectGroupLevel(String id);

    //  获取人员布控集合
    List<ExtFaceGroupDetail> selectList(FaceGroupDetail groupDetail);

//    根据personCode 查列表
    List<Map<String,Object>> selectListByCode(Map<String,Object> params);

}
