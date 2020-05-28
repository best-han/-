package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.webapi.dao.BaseCommunityMapper;
import com.windaka.suizhi.webapi.dao.auto.BaseCommunityAutoMapper;
import com.windaka.suizhi.webapi.dao.auto.HtUserMapper;
import com.windaka.suizhi.webapi.dao.auto.HtUserXqMapper;
import com.windaka.suizhi.webapi.model.*;
import com.windaka.suizhi.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户实现类
 * @Author lixianhua
 * @Date 2020/4/26 8:26
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HtUserMapper htUserMapper;

    @Autowired
    private HtUserXqMapper htUserXqMapper;

    @Autowired
    private BaseCommunityAutoMapper baseCommunityAutoMapper;

    /**
     * 功能描述: 获取下级所有用户
     * @auther: lixianhua
     * @date: 2020/4/26 9:02
     * @param:
     * @return:
     */
    @Override
    public List<HtUser> getUserListByUserId(String userId) {
        List<HtUser> list = new ArrayList<>();
        HtUserExample example = new HtUserExample();
        HtUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        HtUser user = this.htUserMapper.selectByExample(example).get(0);
        list.add(user);
        HtUserExample subExample = new HtUserExample();
        HtUserExample.Criteria subCriteria = subExample.createCriteria();
        subCriteria.andEnabledEqualTo(true);
        subCriteria.andDelFlagEqualTo(true);
        if(!"3".equals(user.getSysLevel())){
            if("2".equals(user.getSysLevel())){// 区级用户
                subCriteria.andAreaIdEqualTo(user.getAreaId()).andSysLevelIn(Arrays.asList(new String[]{"3","4"}));
                List<HtUser> subList = this.htUserMapper.selectByExample(subExample);
                list.addAll(subList);
            }else if("4".equals(user.getSysLevel())){// 街道用户
                subCriteria.andSubdistrictIdEqualTo(user.getSubdistrictId()).andSysLevelEqualTo("3");
                List<HtUser> subList = this.htUserMapper.selectByExample(subExample);
                list.addAll(subList);
            }else if("1".equals(user.getSysLevel())){// 超管
                List<HtUser> subList = this.htUserMapper.selectByExample(subExample);
                return subList;
            }
        }
        return list;
    }
    /**
     * 功能描述: 根据用户ID获取小区集合
     * @auther: lixianhua
     * @date: 2020/4/28 14:02
     * @param:
     * @return:
     */
    @Override
    public List<HtUserXq> getUserXqByUserId(String userId) {
        HtUserExample example = new HtUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        HtUser htUser = this.htUserMapper.selectByExample(example).get(0);
        List<HtUserXq> list = new ArrayList<>();
        if("3".equals(htUser.getSysLevel())){// 普通用户
            HtUserXqExample xqExample = new HtUserXqExample();
            xqExample.createCriteria().andUserIdEqualTo(userId);
            list = this.htUserXqMapper.selectByExample(xqExample);
        }else if("2".equals(htUser.getSysLevel())){// 区用户
            BaseCommunityExample areaExample = new BaseCommunityExample();
            areaExample.createCriteria().andAreaEqualTo(htUser.getAreaId());
            List<BaseCommunity> commList = this.baseCommunityAutoMapper.selectByExample(areaExample);
            if(null!=commList && commList.size()>0){
                for(BaseCommunity comm : commList){
                    HtUserXq userXq = new HtUserXq();
                    userXq.setUserId(htUser.getUserId());
                    userXq.setXqCode(comm.getCode());
                    list.add(userXq);
                }
            }
        }else if("4".equals(htUser.getSysLevel())){// 街道用户
            BaseCommunityExample subExample = new BaseCommunityExample();
            subExample.createCriteria().andAreaEqualTo(htUser.getAreaId()).andSubdistrictCodeEqualTo(htUser.getSubdistrictId().toString());
            List<BaseCommunity> subList = this.baseCommunityAutoMapper.selectByExample(subExample);
            if(null!=subList&& subList.size()>0){
                for(BaseCommunity comm:subList){
                    HtUserXq userXq = new HtUserXq();
                    userXq.setUserId(htUser.getUserId());
                    userXq.setXqCode(comm.getCode());
                    list.add(userXq);
                }
            }
        }else if("1".equals(htUser.getSysLevel())){// 超管
            BaseCommunityExample adminExample = new BaseCommunityExample();
            List<BaseCommunity> adminList = this.baseCommunityAutoMapper.selectByExample(adminExample);
            if(null!=adminList&& adminList.size()>0){
                for(BaseCommunity comm:adminList){
                    HtUserXq userXq = new HtUserXq();
                    userXq.setUserId(htUser.getUserId());
                    userXq.setXqCode(comm.getCode());
                    list.add(userXq);
                }
            }
        }
        return list;
    }

}
