package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.BasePerson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BasePersonMapper {

    int insert(BasePerson record);

    int updateByCode(BasePerson record);

    int deleteByCode(String code);

    List<BasePerson> selectPersonList(Map<String, Object> params);

    int updatePersonHouseByCode(BasePerson record);

    int deletePersonHouseByCode(BasePerson record);

    int deletePersonByCommunityCode(String communityCode);

    int fillPersonHouseById(BasePerson record);

    List<BasePerson> selectOtherHousesInPerson(Map<String, Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/23 下午2:19
     * @description：  根据roomCode 选出房屋业主信息-
     */
    List<BasePerson> selectRoomOwnerList(Map<String,Object> params);

    /**
     * @author ：zdq
     * @date   ：2020/4/20 下午5:07
     * @description：  人员 根据小区code 修改 小区name
     */
    int updatePersonByCommunityCode(Map<String,Object> params);
}