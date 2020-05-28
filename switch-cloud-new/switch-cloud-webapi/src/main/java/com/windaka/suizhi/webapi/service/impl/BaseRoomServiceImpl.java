package com.windaka.suizhi.webapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.webapi.dao.BaseRoomDao;
import com.windaka.suizhi.webapi.service.BaseRoomService;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 基础要素实有房产
 * @Author wcl
 * @Date 2020/4/10 0010 下午 4:14
 */
@Service
public class BaseRoomServiceImpl implements BaseRoomService {

    @Autowired
    private BaseRoomDao baseRoomDao;

    @Override
    public List<Map<String, Object>> queryBuildingList(Map<String, Object> params) {
        List<Map<String, Object>> list = baseRoomDao.queryBuildingList(params);
        return list;
    }


    @Override
    public List<Map<String, Object>> queryCellByBuilding(Map<String, Object> params) throws OssRenderException {
        List<Map<String, Object>> list = baseRoomDao.queryXqListInRoom(params);
        for (Map map : list) {
            params.put("xqCode", map.get("communityCode"));
            List<Map<String, Object>> buildings = baseRoomDao.queryBuildingsByXqCodeInRoom(params);
            for (Map map1 : buildings) {
                params.put("buildingCode", map1.get("buildingCode"));
                List<Map<String, Object>> units = baseRoomDao.queryUnitsByXqCodeAndBuildingCodeInRoom(params);
                map1.put("units", units);
            }
            map.put("buildings", buildings);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> queryBuildingCellByXq(Map<String, Object> params) throws OssRenderException {
        if (ObjectUtils.isEmpty(params.get("xqCode"))) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "xqCode为空");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        if (ObjectUtils.isEmpty(params.get("buildingCode"))) {
            list = baseRoomDao.queryBuildingList(params);
        } else {
            list = baseRoomDao.queryUnitsByXqCodeAndBuildingCodeInRoom(params);
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> queryRoomInfoByRoomCode(String roomCode) {
        List<Map<String, Object>> list = baseRoomDao.queryRoomInfoByRoomCode(roomCode);
        return list;
    }

    @Override
    public Map<String, Object> queryPersonInfoByRoomCode(String roomCode) {
        List<Map<String, Object>> list = baseRoomDao.queryPersonInfoByRoomCode(roomCode);
        for (Map map : list) {
            //拼接图片
            if (map.get("image") != null && !StringUtils.isEmpty(map.get("image").toString())) {
                map.put("image", PropertiesUtil.getLocalTomcatImageIp() + map.get("image").toString());
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> queryCarInfoByRoomCode(String roomCode) {
        List<Map<String, Object>> list = baseRoomDao.queryCarInfoByRoomCode(roomCode);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("carNum") != null && !StringUtils.isEmpty(list.get(i).get("carNum").toString())) {
                //拼接图片
                if (list.get(i).get("image") != null && !StringUtils.isEmpty(list.get(i).get("image").toString())) {
                    list.get(i).put("image", PropertiesUtil.getLocalTomcatImageIp() + list.get(i).get("image").toString());
                }
            } else {
                list.remove(list.get(i));
                i = i - 1;
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> queryDogsByRoomCode(String roomCode) {
        List<Map<String, Object>> list = baseRoomDao.queryDogsByRoomCode(roomCode);
        for (Map map : list) {
            //拼接图片
            if (map.get("image") != null && !StringUtils.isEmpty(map.get("image").toString())) {
                map.put("image", PropertiesUtil.getLocalTomcatImageIp() + map.get("image").toString());
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> queryRoomInfoList(Map<String, Object> params) {


        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        Map<String, Object> mapList = new HashMap<>();

        List<Map<String, Object>> list = baseRoomDao.queryRoomInfoList(params);
        if (list.size() > 0) {
            PageInfo paginator = new PageInfo(list);
            list = paginator.getList();
            for (Map map : list) {
                String roomCode = (String) map.get("roomCode");
                //获取房间关联人数
                int liveNum = baseRoomDao.queryLivePersonNumByRoomCode(roomCode);
                //获取房间关联车数
                int roomCarNum = baseRoomDao.queryRoomCarNumByRoomCode(roomCode);

                map.put("liveNum", liveNum);
                map.put("roomCarNum", roomCarNum);
            }

            mapList.put("list", list);
            mapList.put("totalRows", paginator.getTotal());
            mapList.put("currentPage", start);

        } else {
            mapList.put("list", new ArrayList());
            mapList.put("totalRows", 0);
            mapList.put("currentPage", start);
        }

        return mapList;
    }

    @Override
    public Map<String, Object> houseRoomByBuildingList(Map<String, Object> params) throws OssRenderException {

        Map<String, Object> resultMap = new HashMap<>();

        if (params.get("buildingCode") == null || StringUtils.isEmpty(params.get("buildingCode").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "查询buildingCode不能为空");
        }

        //根据楼栋code 得到 单元列表
        List<Map<String, Object>> unitLists = baseRoomDao.queryCellListByBuildingCode(params);

        resultMap.put("cellList", unitLists);
        Map<String, Object> unitList = unitLists.get(0);
        resultMap.put("buildingName", unitList.get("buildingName"));
        resultMap.put("xqName", unitList.get("xqName"));
        resultMap.put("xqCode",unitList.get("xqCode"));

        //如果单元code 为空   则默认填入单元列表中的第一个单元
        if (params.get("cellId") == null || StringUtils.isEmpty(params.get("cellId").toString())) {
            params.put("cellId", unitLists.get(0).get("cellId"));
        }

        //根据楼栋code  单元code 得到 房屋列表
        List<Map<String, Object>> houseLists = baseRoomDao.queryRoomListByCode(params);


        List<Map<String, Object>> floorList = new ArrayList();//存放该单元中 每一层的房间  (Map)floorNum   (List)roomList

		//求出楼层数
		int floorNums=0;
		for (Map<String,Object> houseList:houseLists){
			int floorNum = Integer.parseInt(houseList.get("roomNum").toString()) / 100;
			if (floorNum>floorNums){
				floorNums=floorNum;
			}
		}

        int[] floors = new int[floorNums];//存放楼层
        for (int i = 0; i < floors.length; i++) {
            floors[i] = floorNums-i;
        }

        for (int floorNum : floors) {//遍历层数

            Map<String, Object> floor = new HashMap<>();

//			int floorNum=Integer.parseInt(houseList.get("roomNum").toString())/100;

            floor.put("floorNum", floorNum);//放入该层 第几层

            //存放该层的房间
            List<Map<String, Object>> roomList = new ArrayList<>();

            for (Map<String, Object> houseList : houseLists) {
                int floorNum2 = Integer.parseInt(houseList.get("roomNum").toString()) / 100;
                if (floorNum2 == floorNum) {//是否等于该层数  是 则存放
                    roomList.add(houseList);
                }
            }

			floor.put("roomList", roomList);//放入该层 房间列表

			floorList.add(floor);//存放该单元中 每一层的房间  (Map)floorNum   (List)roomList
        }


        resultMap.put("floorList", floorList);

        return resultMap;
    }
}
