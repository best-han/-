package com.windaka.suizhi.webapi.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.webapi.dao.BaseCarDao;
import com.windaka.suizhi.webapi.dao.BigDataDao;
import com.windaka.suizhi.webapi.service.BaseCarService;
import com.windaka.suizhi.webapi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 实有车辆模块
 * @Author wcl
 * @Date 2020/4/3
 */
@Slf4j
@Service
public class BaseCarServiceImpl implements BaseCarService {

	@Autowired
	private BaseCarDao baseCarDao;
	@Autowired
	private BigDataDao bigDataDao;


	@Override
	public Map<String, Object> queryCarInfoList(Map<String, Object> params) {

		String areaId = (String) params.get("areaId");
		if (StringUtils.isBlank(areaId)) {
			params.put("areaId",0);
		}

		int start = Integer.parseInt(params.get("page").toString());
		int limit = Integer.parseInt(params.get("limit").toString());
		PageHelper.startPage(start, limit);//分页

		Map<String,Object> mapList = new HashMap<>();

		List<Map<String,Object>> list = baseCarDao.queryCarInfoList(params);
		if (list.size() > 0) {
			PageInfo paginator = new PageInfo(list);
			list = paginator.getList();
			for (Map map:list) {
				//拼接图片
				if (map.get("image") != null && !StringUtils.isEmpty(map.get("image").toString())) {
					map.put("image", PropertiesUtil.getLocalTomcatImageIp() + map.get("image").toString());
				}
			}
			mapList.put("list",list);
			mapList.put("totalRows", paginator.getTotal());
			mapList.put("currentPage", start);
		}else {
			mapList.put("list",new ArrayList());
			mapList.put("totalRows", 0);
			mapList.put("currentPage", start);
		}
		return mapList;
	}

	@Override
	public Map<String, Object> carBaseInfo(Map<String, Object> params) {

		String areaId = (String) params.get("areaId");
		if (StringUtils.isBlank(areaId)) {
			params.put("areaId", 0);
		}

		//获取当前系统时间的当月时间
		params.put("MonthStartTime", DateUtil.getMonthStartTime());

		//获取当前系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("currentTime",sdf.format(new Date()));

		Map<String,Object> map = new HashMap<>();
		map.put("carTotalNum",baseCarDao.totalRows(params));
		map.put("permanentPersonCarNum",baseCarDao.permanentPopulationCarNum(params));
		map.put("flowPersonCarNum",baseCarDao.floatingPopulationCarNum(params));
		map.put("monthAddCarNum",baseCarDao.monthAddCarCarNum(params));

		return map;
	}

	@Override
	public List<Map<String,Object>> queryPersonInfoAndOwnerInfoByCarNum(String carNum) {
		List<Map<String,Object>> list= baseCarDao.queryPersonInfoAndOwnerInfoByCarNum(carNum);
		for (Map map:list) {
			//拼接图片
			if (map.get("carImage") != null && !StringUtils.isEmpty(map.get("carImage").toString())) {
				map.put("carImage", PropertiesUtil.getLocalTomcatImageIp() + map.get("carImage").toString());
			}
			//拼接图片
			if (map.get("ownerImage") != null && !StringUtils.isEmpty(map.get("ownerImage").toString())) {
				map.put("ownerImage", PropertiesUtil.getLocalTomcatImageIp() + map.get("ownerImage").toString());
			}
		}
		return list;
	}

	@Override
	public List<Map<String,Object>> queryAbnormalTypeByCarNum(Map<String, Object> params) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		params.put("endDate",format.format(c.getTime()));
		//过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);

		params.put("startDate",mon);
		//获取当天开始时间和结束时间
		params.put("startTime", DateUtil.getTodayStartTime());
		params.put("endTime",DateUtil.getTodayEndTime());

		String str = "";

		//布控标签
		List<Map<String,Object>> listAbnormalType = baseCarDao.queryAbnormalTypeByCarNum(params);
		//高频车辆标签
		List<Map<String,Object>> highAccesslist = baseCarDao.queryHighAccessByCarNum(params);
		//频繁夜归标签
		int nightReturnNum = bigDataDao.totalGetNightReturnCarByCarNum(params);

		if (listAbnormalType.size()>0){
			for (int i=0;i<listAbnormalType.size();i++){
				if (i<listAbnormalType.size()-1) {
					str += listAbnormalType.get(i).get("abnormalType") + ",";
				}else {
					str += listAbnormalType.get(i).get("abnormalType");
				}
			}

			if (highAccesslist.size()>0){
				str += ",高频出入车辆";
			}

			if (nightReturnNum >=5){
				str += ",频繁夜归车辆";
			}
		}else {
			if (highAccesslist.size()>0){
				str += "高频出入车辆";

				if (nightReturnNum >=5){
					str += ",频繁夜归车辆";
				}
			}else {
				if (nightReturnNum >=5){
					str += "频繁夜归车辆";
				}
			}
		}

		List<Map<String,Object>> list = new LinkedList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("abnormalType",str);
		list.add(map);

		return list;
	}

	@Override
	public List<Map<String,Object>> queryRoomInfoByCarNum(String carNum) {
		List<Map<String,Object>> list=new ArrayList<>();
		List<Map<String,Object>> roomCodeList = baseCarDao.queryRoomCodeByCarNum(carNum);

		for (Map map : roomCodeList) {
			Map resultMap=new HashMap();
			resultMap.put("houseId",map.get("roomCode"));
			String houseName=baseCarDao.queryRoomInfoByRoomCode(map.get("roomCode").toString()).get(0).get("roomInfo").toString();

			resultMap.put("roomInfo",houseName);

			list.add(resultMap);
		}

		return list;
	}

	@Override
	public Map<String, Object> queryParkingByCarNum(String carNum) {
		List<Map<String,Object>> list = baseCarDao.queryParkingByCarNum(carNum);
		Map<String,Object> map = new HashMap();
		map.put("parking",list);
		return map;
	}

	@Override
	public Map<String, Object> queryCaptureCountByCarNum(String carNum) {
		List<Map<String,Object>> list = new ArrayList<>();

		//获取过去15天之内的时间
		ArrayList<String> dayList = DateUtil.getDays(15);

		for (String day : dayList) {
			Map<String,Object> map = new HashMap<>();
			map.put("day",day);
			map.put("capCount",baseCarDao.queryCaptureCountByCarNum(carNum,day));
			list.add(map);
		}

		Map<String,Object> mapList = new HashMap<>();
		mapList.put("list",list);
		return mapList;
	}

	@Override
	public Map<String, Object> queryCaptureDetailsByCarNum(Map<String, Object> params) {
		List<Map<String,Object>> list = baseCarDao.queryCaptureDetailsByCarNum(params);
		for (Map map:list) {
			//拼接图片
			if (map.get("capImage") != null && !StringUtils.isEmpty(map.get("capImage").toString())) {
				map.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + map.get("capImage").toString());
			}
		}
		Map<String,Object> map = new HashMap<>();
		map.put("list",list);
		return map;
	}


}
