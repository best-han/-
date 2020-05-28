package com.windaka.suizhi.webapi.service;

import com.windaka.suizhi.webapi.model.SysDicData;

import java.util.List;

/**
 * @InterfaceName SysDicDataService
 * @Description 字典接口
 * @Author lixianhua
 * @Date 2020/4/20 11:20
 * @Version 1.0
 */
public interface SysDicDataService {

    // 获取字典数据集合
    List<SysDicData> selectDicList(SysDicData dic);
}
