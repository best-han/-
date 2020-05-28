package com.windaka.suizhi.mpi.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.mpi.dao.HTUserXQDao;
import com.windaka.suizhi.mpi.dao.WebsocketUserDao;
import com.windaka.suizhi.mpi.dao.WebsocketXqDao;
import com.windaka.suizhi.mpi.dao.auto.BaseCommunityMapper;
import com.windaka.suizhi.mpi.dao.auto.HtUserMapper;
import com.windaka.suizhi.mpi.model.BaseCommunity;
import com.windaka.suizhi.mpi.model.HtUser;
import com.windaka.suizhi.mpi.model.HtUserExample;
import com.windaka.suizhi.mpi.service.UserService;
import com.windaka.suizhi.mpi.service.WebsocketXqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WebsocketXqServiceImpl implements WebsocketXqService {

    @Autowired
    private WebsocketXqDao xqDao;
    @Autowired
    private WebsocketUserDao userDao;
    @Autowired
    private HTUserXQDao htUserXQDao;
    @Autowired
    private HtUserMapper htUserMapper;
    @Autowired
    private UserService userService;


    /**
     * 内部调用  hjt
     *
     * @param
     * @return
     */
    @Transactional
    @Override
    public List<Map<String, Object>> queryListXqInfo(String areaId, String xqCode, String userId) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        // 获取用户信息
        HtUserExample userExample = new HtUserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        HtUser htUser = this.htUserMapper.selectByExample(userExample).get(0);
        Map<String, Object> loginAppUser = htUserXQDao.queryByUserId(userId);
        String sysLevel = loginAppUser.get("sysLevel").toString();//用户级别（1：超管，2：区级管理员，3：小区普通用户，4：街道管理员）
        String isAdmin = userDao.queryIsSuperByUserId(userId);
        if (isAdmin.equalsIgnoreCase("1")) {//是超管
            if (StringUtils.isNotBlank(xqCode)) {
                map = queryByCode(xqCode);
                list.add(map);
            } else {
                // 查询小区集合
                List<BaseCommunity> commList = this.userService.getXqByUserId(userId);
                if (null != commList && commList.size() > 0) {
                    listToMap(commList, list);
                }
//				list=queryListXqByAreaId(areaId);
            }
        } else {//不是超管
            if (StringUtils.isNotBlank(xqCode)) {
                map = queryByxqCodeUserId(xqCode, userId);
                list.add(map);
            } else {
                // 查询小区集合
                List<BaseCommunity> commList = this.userService.getXqByUserId(userId);
                if (null != commList && commList.size() > 0) {
                    listToMap(commList, list);
                }
//				if("3".equals(sysLevel)){
//					list=queryListXqByAreaIdUserId(areaId,userId);
//				}else if("2".equals(sysLevel)){// 区用户
//					list=xqDao.queryListXqByAreaUser(userId);
//				}else if("4".equals(sysLevel)){
//					list=xqDao.queryListXqBySubdistrictUser(userId);
//				}
            }
        }
        return list;

    }

    /**
     * 功能描述: 小区list转Map
     *
     * @auther: lixianhua
     * @date: 2020/5/11 10:45
     * @param:
     * @return:
     */
    public void listToMap(List<BaseCommunity> list, List<Map<String, Object>> mapList) {
        for (BaseCommunity base : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("xqCode", base.getCode());
            map.put("xqName", base.getName());
            mapList.add(map);
        }
    }


    public Map<String, Object> queryByCode(String xqCode) throws OssRenderException {
        Map map = xqDao.queryByxqCode(xqCode);
        if (map == null) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "查询的小区不存在");
        }
        return map;
    }

    public Map<String, Object> queryByxqCodeUserId(String xqCode, String userId) throws OssRenderException {
        Map map = xqDao.queryByxqCodeUserId(xqCode, userId);
        if (map == null) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "查询的小区不存在");
        }
        return map;
    }

    public List<Map<String, Object>> queryListXqByAreaId(String areaId) throws OssRenderException {
        List<Map<String, Object>> list = xqDao.queryListXqByAreaId(areaId);
		/*if (list.size() < 1) {
			throw new OssRenderException(ReturnConstants.CODE_FAILED,"该区域无小区");
		}*/
        return list;
    }

    public List<Map<String, Object>> queryListXqByAreaIdUserId(String areaId, String userId) throws OssRenderException {
        List<Map<String, Object>> list = xqDao.queryListXqByAreaIdUserId(areaId, userId);
		/*if (list.size() < 1) {
			throw new OssRenderException(ReturnConstants.CODE_FAILED,"该区域无小区");
		}*/
        return list;
    }


}
