package com.windaka.suizhi.webapi.dao.ext;

import com.windaka.suizhi.webapi.model.CarGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroupDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName ExtCarGroupDetailMapper
 * @Description 非自动生成mapper
 * @Author lixianhua
 * @Date 2020/4/16 16:53
 * @Version 1.0
 */
@Mapper
public interface ExtCarGroupDetailMapper {


    List<ExtCarGroupDetail> selectList(CarGroupDetail groupDetail);
}
