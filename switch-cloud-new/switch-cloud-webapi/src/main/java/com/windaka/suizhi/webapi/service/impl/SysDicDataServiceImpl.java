package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.webapi.dao.auto.SysDicDataAutoMapper;
import com.windaka.suizhi.webapi.model.SysDicData;
import com.windaka.suizhi.webapi.model.SysDicDataExample;
import com.windaka.suizhi.webapi.service.SysDicDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysDicDataServiceImpl
 * @Description 字典实现类
 * @Author lixianhua
 * @Date 2020/4/20 11:20
 * @Version 1.0
 */
@Service
public class SysDicDataServiceImpl implements SysDicDataService {

    @Autowired
    private SysDicDataAutoMapper sysDicDataAutoMapper;

    /**
     * 功能描述: 获取数据集合
     * @auther: lixianhua
     * @date: 2020/4/20 11:25
     * @param:
     * @return:
     */
    @Override
    public List<SysDicData> selectDicList(SysDicData dic) {
        SysDicDataExample example = new SysDicDataExample();
        SysDicDataExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dic.getDicCode())) {
            criteria.andDicCodeEqualTo(dic.getDicCode());
        }
        List<SysDicData>  list = this.sysDicDataAutoMapper.selectByExample(example);
        return list;
    }
}
