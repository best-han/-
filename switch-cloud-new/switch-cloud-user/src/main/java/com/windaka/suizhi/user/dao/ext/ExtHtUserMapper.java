package com.windaka.suizhi.user.dao.ext;

import com.windaka.suizhi.user.model.ext.ExtHtUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName ExtHtUserMapper
 * @Description 用户dao层
 * @Author lixianhua
 * @Date 2020/4/22 15:25
 * @Version 1.0
 */
@Mapper
public interface ExtHtUserMapper {
    /**
     * 功能描述: 获取用户集合
     * @auther: lixianhua
     * @date: 2020/4/22 15:34
     * @param:
     * @return:
     */
    List<ExtHtUser> getUserList(ExtHtUser extHtUser);
}
