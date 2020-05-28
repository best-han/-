package com.windaka.suizhi.webapi.service.impl;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.PageUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.webapi.dao.BigDataDao;
import com.windaka.suizhi.webapi.dao.CaptureDeviceDao;
import com.windaka.suizhi.webapi.dao.IdleRoomDao;
import com.windaka.suizhi.webapi.model.CaptureCar;
import com.windaka.suizhi.webapi.model.CapturePerson;
import com.windaka.suizhi.webapi.service.BigDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class BigDataServiceImpl implements BigDataService {

    @Autowired
    BigDataDao bigDataDao;

    @Override
    public Map<String,Object> getNightReturnPerson(Map<String, Object> params) throws OssRenderException {
        List<Map<String, Object>> list = new ArrayList<>();
        if(!ObjectUtils.isEmpty(params.get("searchText"))){
            params.put("searchText",params.get("searchText").toString().trim());
        }

        int totalRows = bigDataDao.totalPersonRows(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = bigDataDao.getNightReturnPerson(params);
            for(Map map:list){
                if(!ObjectUtils.isEmpty(map.get("image")))
                map.put("image",PropertiesUtil.getLocalTomcatImageIp()+map.get("image"));
            }
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }
    @Override
    public Map<String,Object> getNightReturnPersonOne(Map<String, Object> params) throws OssRenderException {
        List<Map<String, Object>> list = new ArrayList<>();
        int totalRows = bigDataDao.totalGetNightReturnPersonByPersonCode(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = bigDataDao.getNightReturnPersonByPersonCode(params);
            for(Map map:list){
                if(!ObjectUtils.isEmpty(map.get("capImage")))
                map.put("capImage",PropertiesUtil.getLocalTomcatImageIp()+map.get("capImage"));
            }
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }

    @Override
    public Map<String,Object> getNightReturnCar(Map<String, Object> params) throws OssRenderException{
        List<Map<String, Object>> list = new ArrayList<>();
        if(!ObjectUtils.isEmpty(params.get("searchText"))){
            params.put("searchText",params.get("searchText").toString().trim());
        }
        int totalRows = bigDataDao.totalCarRows(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = bigDataDao.getNightReturnCar(params);
            for(Map map:list){
                if(!ObjectUtils.isEmpty(map.get("oriImage")))
                map.put("oriImage",PropertiesUtil.getLocalTomcatImageIp()+map.get("oriImage"));
            }
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }
    @Override
    public Map<String,Object> getNightReturnCarOne(Map<String, Object> params) throws OssRenderException{
        List<Map<String, Object>> list = new ArrayList<>();
        int totalRows = bigDataDao.totalGetNightReturnCarByCarNum(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = bigDataDao.getNightReturnCarByCarNum(params);
            for(Map map:list){
                if(!ObjectUtils.isEmpty(map.get("capImage")))
                map.put("capImage",PropertiesUtil.getLocalTomcatImageIp()+map.get("capImage"));
            }
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }

    @Override
   public Map<String,Object> waterRate(Map<String, Object> params) throws OssRenderException{
        List<Map<String,Object>> list = new ArrayList<>();
        if(MapUtils.isNotEmpty(params) && params.get("endTime") != null && !("".equals(params.get("endTime")))){
            String endTime=params.get("endTime").toString();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
            try{
                Date date=sdf.parse(endTime);
                Calendar c=Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.MONTH,1);
                date=c.getTime();
                endTime=sdf.format(date);
                params.put("endTime",endTime);
            }catch(ParseException e){
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "时间格式不正确！");
            }
        }
        int totalRows = bigDataDao.totalWaterRows(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = bigDataDao.waterRate(params);
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }

    @Override
    public Map<String,Object> groupRentalResearch(Map<String, Object> params) throws OssRenderException{
        List<Map<String,Object>> list = new ArrayList<>();
        if(null==params.get("associateNum")||"".endsWith(params.get("associateNum").toString())){
            params.put("associateNum", 5);
        }
        int totalRows = bigDataDao.totalGroupRentalRows(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = bigDataDao.groupRentalResearch(params);
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }

    public Map<String,Object> dayRentalResearch(Map<String, Object> params) throws OssRenderException{
        List<Map<String,Object>> list = new ArrayList<>();

        int totalRows = bigDataDao.totalDayRentalResearch(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = bigDataDao.dayRentalResearch(params);
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }

    @Autowired
    IdleRoomDao idleRoomDao;
    @Override
    public Map<String,Object> queryIdleRoomList(Map<String, Object> params) throws OssRenderException{
        List<Map<String,Object>> list = new ArrayList<>();
        int totalRows = idleRoomDao.totalIdleRoom(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = idleRoomDao.queryIdleRoomList(params);
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }

    @Override
    public Map<String,Object> queryIdleRoomLivedList(Map<String, Object> params) throws OssRenderException{
        List<Map<String,Object>> list = new ArrayList<>();
        int totalRows = idleRoomDao.totalIdleRoomLived(params);
        Map<String,Object> mapResult=new HashMap<>();//返回结果map
        if (totalRows > 0) {
            PageUtil.pageParamConver(params, true);
            list = idleRoomDao.queryIdleRoomLivedList(params);
        }
        mapResult.put("list",list);
        mapResult.put("totalRows",totalRows);
        mapResult.put("currentPage", MapUtils.getInteger(params, PageUtil.PAGE));
        return mapResult;
    }

}
