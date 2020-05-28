package com.windaka.suizhi.mpi.service.impl;

import com.windaka.suizhi.mpi.dao.auto.BaseCommunityMapper;
import com.windaka.suizhi.mpi.dao.auto.HtUserMapper;
import com.windaka.suizhi.mpi.dao.auto.HtUserXqMapper;
import com.windaka.suizhi.mpi.model.*;
import com.windaka.suizhi.mpi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户实现类
 * @Author lixianhua
 * @Date 2020/5/6 15:42
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HtUserMapper htUserMapper;

    @Autowired
    private HtUserXqMapper htUserXqMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    /**
     * 功能描述: 根据userId获取上级用户集合
     * @auther: lixianhua
     * @date: 2020/5/6 15:43
     * @param:
     * @return:
     */
    @Override
    public List<HtUser> getUserListById(String userId) {
        HtUserExample example = new HtUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        // 获取用户信息
        List<HtUser> userList = this.htUserMapper.selectByExample(example);
        if(null==userList || userList.size()==0){
            return null;
        }
        HtUser htUser = userList.get(0);
        List<HtUser> list = new ArrayList<>();
        list.add(htUser);
        if(!"1".equals(htUser.getSysLevel())){// 超管
            HtUserExample adminExample = new HtUserExample();
            adminExample.createCriteria().andSysLevelEqualTo("1");
            List<HtUser> adminList = this.htUserMapper.selectByExample(adminExample);
            if(null!=adminList && adminList.size()>0){
                list.addAll(adminList);
            }
        }
        if("3".equals(htUser.getSysLevel())){// 小区普通用户
            HtUserExample subExample = new HtUserExample();
            subExample.createCriteria().andSubdistrictIdEqualTo(htUser.getSubdistrictId()).andSysLevelEqualTo("4").andDelFlagEqualTo(true);
            // 获取街道用户
            List<HtUser> subList = this.htUserMapper.selectByExample(subExample);
            if(null!=subList && subList.size()>0){
                list.addAll(subList);
            }
            HtUserExample areaExample = new HtUserExample();
            areaExample.createCriteria().andAreaIdEqualTo(htUser.getAreaId()).andSysLevelEqualTo("2").andDelFlagEqualTo(true);
            // 获取区级用户
            List<HtUser> areaList = this.htUserMapper.selectByExample(areaExample);
            if(null!=areaList && areaList.size()>0){
                list.addAll(areaList);
            }
        }else if("4".equals(htUser.getSysLevel())){// 街道管理员
            HtUserExample areaExample = new HtUserExample();
            areaExample.createCriteria().andAreaIdEqualTo(htUser.getAreaId()).andSysLevelEqualTo("2").andDelFlagEqualTo(true);
            // 获取区级用户
            List<HtUser> areaList = this.htUserMapper.selectByExample(areaExample);
            if(null!=areaList && areaList.size()>0){
                list.addAll(areaList);
            }
        }
        return list;
    }
    /**
     * 功能描述: 根据小区code获取所属人员集合
     * @auther: lixianhua
     * @date: 2020/5/7 14:32
     * @param:
     * @return:
     */
    @Override
    public List<HtUser> getUserListByXqCode(String xqCode) {
        List<HtUser> userList = new ArrayList<>();
        HtUserXqExample example = new HtUserXqExample();
        example.createCriteria().andXqCodeEqualTo(xqCode);
        // 获取小区用户
        List<HtUserXq> list = this.htUserXqMapper.selectByExample(example);
        if(null!=list&& list.size()>0){
           for(HtUserXq userXq:list){
               HtUser htUser = new HtUser();
               htUser.setUserId(userXq.getUserId());
               userList.add(htUser);
           }
       }
        BaseCommunityExample communityExample = new BaseCommunityExample();
        communityExample.createCriteria().andCodeEqualTo(xqCode);
        BaseCommunity baseCommunity = this.baseCommunityMapper.selectByExample(communityExample).get(0);
        // 获取街道用户
        HtUserExample subExample = new HtUserExample();
        subExample.createCriteria().andDelFlagEqualTo(true).andSysLevelEqualTo("4").andSubdistrictIdEqualTo(Long.valueOf(baseCommunity.getSubdistrictCode()));
        List<HtUser> subList = this.htUserMapper.selectByExample(subExample);
        userList.addAll(subList);
        // 获取区用户
        HtUserExample areaExample = new HtUserExample();
        areaExample.createCriteria().andDelFlagEqualTo(true).andSysLevelEqualTo("2").andAreaIdEqualTo(baseCommunity.getArea());
        List<HtUser> areaList = this.htUserMapper.selectByExample(areaExample);
        userList.addAll(areaList);
        return userList;
    }
    /**
     * 功能描述: 获取用户集合
     * @auther: lixianhua
     * @date: 2020/5/9 10:22
     * @param:
     * @return:
     */
    @Override
    public List<HtUser> getUserList(HtUser condition) {
        HtUserExample example = new HtUserExample();
        HtUserExample.Criteria criteria = example.createCriteria();
        // 添加可添加
        criteria.andDelFlagEqualTo(true);
        if(StringUtils.isNotBlank(condition.getUserId())){
            criteria.andUserIdEqualTo(condition.getUserId());
        }
        List<HtUser> list = this.htUserMapper.selectByExample(example);
        return list;
    }
    /**
     * 功能描述: 根据userId获取用户下的小区信息
     * @auther: lixianhua
     * @date: 2020/5/11 11:13
     * @param:
     * @return:
     */
    @Override
    public List<BaseCommunity> getXqByUserId(String userId) {
        List<BaseCommunity> list = null;
        HtUserExample example = new HtUserExample();
        example.createCriteria().andUserIdEqualTo(userId).andDelFlagEqualTo(true);
        HtUser htUser = this.htUserMapper.selectByExample(example).get(0);
        if("1".equals(htUser.getSysLevel())){// 超管
           list = this.baseCommunityMapper.selectByExample(new BaseCommunityExample());
        }else if("2".equals(htUser.getSysLevel())){// 区用户
            BaseCommunityExample areaExample = new BaseCommunityExample();
            areaExample.createCriteria().andAreaEqualTo(htUser.getAreaId());
            list = this.baseCommunityMapper.selectByExample(areaExample);
        }else if("3".equals(htUser.getSysLevel())){// 普通用户
            HtUserXqExample xqExample = new HtUserXqExample();
            xqExample.createCriteria().andStatusEqualTo("0").andUserIdEqualTo(userId);
            List<HtUserXq> userXqList = this.htUserXqMapper.selectByExample(xqExample);
            if(null!=userXqList && userXqList.size()>0){
                list = new ArrayList<>();
                for(HtUserXq userXq:userXqList){
                    BaseCommunity community = new BaseCommunity();
                    community.setCode(userXq.getXqCode());
                    list.add(community);
                }
            }
        }else if("4".equals(htUser.getSysLevel())){// 街道用户
            BaseCommunityExample subExample = new BaseCommunityExample();
            subExample.createCriteria().andAreaEqualTo(htUser.getAreaId()).andSubdistrictCodeEqualTo(htUser.getSubdistrictId().toString());
            list = this.baseCommunityMapper.selectByExample(subExample);
        }
        return list;
    }
}
