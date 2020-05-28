package com.windaka.suizhi.manageport.util;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.manageport.dao.SysDicDataMapper;
import com.windaka.suizhi.manageport.model.SysDicData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DicUtils {

    @Autowired
    private SysDicDataMapper sysDicDataMapper;

    public static DicUtils dicUtils;

    @PostConstruct
    public void init() {
        dicUtils = this;
    }

    public static String getValue(String dicCode,String key,String exceptionMsg) throws OssRenderException {
        Map innerParams=new HashMap<>();
        innerParams.put("dicCode",dicCode);
        innerParams.put("dicKey",key);
        List<SysDicData> sysDicDataList=dicUtils.sysDicDataMapper.selectSysDicDataList(innerParams);
        if(sysDicDataList==null||sysDicDataList.isEmpty()){
            throw new OssRenderException(ReturnConstants.CODE_FAILED, exceptionMsg);
        }
        else {
            SysDicData sysDicData=(SysDicData) sysDicDataList.get(0);
            return sysDicData.getDicValue();
        }
    }
}
