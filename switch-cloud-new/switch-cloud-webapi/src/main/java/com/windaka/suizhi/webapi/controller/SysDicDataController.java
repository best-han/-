package com.windaka.suizhi.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.SysDicData;
import com.windaka.suizhi.webapi.model.ext.ExtFaceAttentionDetail;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroup;
import com.windaka.suizhi.webapi.service.SysDicDataService;
import com.windaka.suizhi.webapi.util.Paginator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysDicDataController
 * @Description 字典控制层
 * @Author lixianhua
 * @Date 2020/4/20 11:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/dictionary")
public class SysDicDataController extends BaseController {

    @Autowired
    private SysDicDataService sysDicDataService;


    /**
     * 功能描述: 获取字典集合
     * @auther: lixianhua
     * @date: 2020/4/20 11:53
     * @param:
     * @return:
     */
    @GetMapping("/dicList")
    public Map<String, Object> personList(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        SysDicData dic = new SysDicData();
        dic.setDicCode(request.getParameter("dicCode"));
        if(StringUtils.isNotBlank(request.getParameter("dicKey"))){
            dic.setDicKey(Short.valueOf(request.getParameter("dicKey")));
        }
        try {
            List<SysDicData> list = this.sysDicDataService.selectDicList(dic);
            return render(list);
        } catch (Exception e) {
            return failRender(e);
        }
    }

}
