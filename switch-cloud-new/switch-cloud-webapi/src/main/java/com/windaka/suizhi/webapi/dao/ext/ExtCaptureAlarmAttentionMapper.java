package com.windaka.suizhi.webapi.dao.ext;

import com.windaka.suizhi.webapi.model.CaptureAbnormal;
import com.windaka.suizhi.webapi.model.CaptureAlarmAttention;
import com.windaka.suizhi.webapi.model.HtUser;
import com.windaka.suizhi.webapi.model.ext.ExtAlarmModel;
import com.windaka.suizhi.webapi.model.ext.ExtCaptureAlarmAttention;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName ExtCaptureAlarmAttentionMapper
 * @Description 重点关注预警接口
 * @Author lixianhua
 * @Date 2020/4/28 10:17
 * @Version 1.0
 */
@Mapper
public interface ExtCaptureAlarmAttentionMapper {

    // 获取重点关注警告集合
    List<ExtCaptureAlarmAttention> selectAttentionList(ExtCaptureAlarmAttention attention);
    // 根据ID更新
    int updateAttentionById(CaptureAlarmAttention attention);
    // 根据类型获取用户集合(人，车，重点关注)
    List<HtUser> getUserListByType(ExtAlarmModel model);
    // 根据小区获取用户集合（异常行为）
    List<HtUser> getUserListByXq(ExtAlarmModel model);
    // 获取重点关注总数
    Integer countAttentionList(ExtCaptureAlarmAttention attention);
    // 警务通 获取重点关注预警集合
    List<ExtCaptureAlarmAttention> selectAPPAttentionList(ExtCaptureAlarmAttention attention);
}
