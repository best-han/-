package com.windaka.suizhi.webapi.service.impl;


import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.webapi.dao.HTUserXQDao;
import com.windaka.suizhi.webapi.dao.auto.BaseCommunityAutoMapper;
import com.windaka.suizhi.webapi.dao.auto.HtUserXqMapper;
import com.windaka.suizhi.webapi.model.BaseCommunity;
import com.windaka.suizhi.webapi.model.BaseCommunityExample;
import com.windaka.suizhi.webapi.model.HtUserXq;
import com.windaka.suizhi.webapi.model.HtUserXqExample;
import com.windaka.suizhi.webapi.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private HTUserXQDao htUserXQDao;

    @Autowired
    private HtUserXqMapper htUserXqMapper;

    @Autowired
    private BaseCommunityAutoMapper baseCommunityAutoMapper;

    /**
     * 根据当前用户、传参，进行权限验证
     *
     * @param userId
     * @throws OssRenderException
     */
    public String checkAuth(String userId) throws OssRenderException {
        String xqCodes = "";
        //验证当前用户查询权限
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        String sysLevel = loginAppUser.getSysLevel();   //用户级别（1：超管，2：区级管理员，3：小区普通用户，4：街道管理员）
        if ("3".equals(sysLevel)) {//普通用户
//			xqCodes = htUserXQDao.queryXQCodeByUserId(userId);
            HtUserXqExample example = new HtUserXqExample();
            example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo("0");
            List<HtUserXq> list = this.htUserXqMapper.selectByExample(example);
            if (null != list && list.size() > 0) {
                for (HtUserXq userXq : list) {
                    xqCodes += (userXq.getXqCode() + ",");
                }
            }
        }
        if ("1".equals(sysLevel)) {//超管
//			xqCodes = htUserXQDao.queryAllXQCode();
            BaseCommunityExample communityExample = new BaseCommunityExample();
            List<BaseCommunity> baseList = this.baseCommunityAutoMapper.selectByExample(communityExample);
            if (null != baseList && baseList.size() > 0) {
                for (BaseCommunity base : baseList) {
                    xqCodes += (base.getCode() + ",");
                }
            }
        }
        if ("2".equals(sysLevel)) {//区级管理员
//            xqCodes = htUserXQDao.queryAllAreaXqCodeByUserId(loginAppUser.getUserId());
            BaseCommunityExample areaExample = new BaseCommunityExample();
            areaExample.createCriteria().andAreaEqualTo(loginAppUser.getAreaId().toString());
            List<BaseCommunity> areaList = this.baseCommunityAutoMapper.selectByExample(areaExample);
            if (null != areaList && areaList.size() > 0) {
                for (BaseCommunity base : areaList) {
                    xqCodes += (base.getCode() + ",");
                }
            }

        }
        if ("4".equals(sysLevel)) {//街道级管理员
//            xqCodes = htUserXQDao.queryAllSubdistrictXqCodeByUserId(loginAppUser.getUserId());
            BaseCommunityExample subExample = new BaseCommunityExample();
            subExample.createCriteria().andSubdistrictCodeEqualTo(loginAppUser.getSubdistrictId().toString());
            List<BaseCommunity> subList = this.baseCommunityAutoMapper.selectByExample(subExample);
            if (null != subList && subList.size() > 0) {
                for (BaseCommunity base : subList) {
                    xqCodes += (base.getCode() + ",");
                }
            }
        }
        if (StringUtils.isBlank(xqCodes)) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "该用户未关联查看小区");
        }
        xqCodes = xqCodes.substring(0, xqCodes.length() - 1);
        return xqCodes;

    }


}
