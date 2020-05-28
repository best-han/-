package com.windaka.suizhi.webapi.dao;

import com.windaka.suizhi.webapi.model.BasePerson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface BasePersonMapper {


 /**
  * @author ：ygy
  * @date   ：2020/3/27 下午7:31
  * @description：
  */
    int queryPersonNum(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/2 下午6:49
     * @description：  现有人员列表
     */
    List<Map<String,Object>> personList(Map<String,Object> params);

    /**
     *
     *@description: 查询个人信息
     *@author: zdq
     *@time: 4/3/20 9:46 AM
     *
     */

    List<Map<String,Object>> querySinglePerson(Map<String,Object> params);
    List<Map<String,Object>> querySinglePerson2(Map<String,Object> params);

    /**
     *
     *@description: 查询个人信息-布控库标签
     *@author: zdq
     *@time: 4/3/20 3:00 PM
     *
     */
    List<Map<String,Object>> queryFaceGroupLabel(Map<String,Object> params);

    /**
     *
     *@description: 查询个人信息-异常行为标签
     *@author: zdq
     *@time: 4/3/20 3:40 PM
     *
     */
   List<Map<String,Object>> queryAbnormalLabel(Map<String,Object> params);

   /**
    *
    *@description: 查询个人信息-某天抓拍次数
    *@author: zdq
    *@time: 4/3/20 7:22 PM
    *
    */
   int queryCaptureTimesInOneDayByPersonCode(Map<String,Object> params);

   /**
    * @author ：ygy
    * @date   ：2020/4/7 上午10:02
    * @description：  国家列表
    */
   List<Map<String,Object>> countryList(Map<String,Object> params);

    /**
     * @author ：ygy
     * @date   ：2020/4/9 上午9:49
     * @description：  实有人口数量
     */
    int selectPersonNum(Map<String,Object> params);

    /**
     *
     *@description: 查询个人信息-所拥有的车辆
     *@author: zdq
     *@time: 4/3/20 6:57 PM
     *
     */
    List<Map<String, Object>> queryCarListByPersonCode(Map<String, Object> params);

}