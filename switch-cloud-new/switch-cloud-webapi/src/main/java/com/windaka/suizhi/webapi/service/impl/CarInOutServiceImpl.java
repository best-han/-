package com.windaka.suizhi.webapi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.Page;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.common.utils.PageUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.dao.CarInOutDao;
import com.windaka.suizhi.webapi.service.CarInOutService;
import com.windaka.suizhi.webapi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 车辆出入感知
 * @Author wcl
 * @Date 2020/4/10
 */
@Slf4j
@Service
public class CarInOutServiceImpl implements CarInOutService {
	@Autowired
	private CarInOutDao carInOutDao;

	@Override
	public Page<Map<String, Object>> queryCarAccessList(Map<String, Object> params) {
		String areaId = (String) params.get("areaId");
		if (StringUtils.isBlank(areaId)) {
			params.put("areaId",0);
		}

		//获取3个月之前的时间
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -90);
		String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime());
		params.put("saveTime",endDate);

		int totalRows = carInOutDao.carAccessTotalRows(params);
		List<Map<String,Object>> list = Collections.emptyList();
		if (totalRows > 0) {
			PageUtil.pageParamConver(params, true);
			list = carInOutDao.queryCarAccessList(params);
			for (Map map:list) {
				//拼接图片
				if (map.get("realCapturePicPath") != null && !StringUtils.isEmpty(map.get("realCapturePicPath").toString())) {
					map.put("realCapturePicPath", PropertiesUtil.getLocalTomcatImageIp() + map.get("realCapturePicPath").toString());
				}
			}
		}
		return new Page<>(totalRows, MapUtils.getInteger(params,PageUtil.PAGE), list);
	}

	@Override
	public Map<String, Object> queryCarAccessListElasticSearch(Map<String, Object> params) {
		//建立查询
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		//具体
		//小区查询
		TermsQueryBuilder communityCodeQueryBuilder=null;
		if(params.get("xqCode")!=null && !StringUtils.isEmpty(params.get("xqCode").toString()))
		{
			communityCodeQueryBuilder=new TermsQueryBuilder("communityCode",(List)params.get("xqCode"));
			// communityCode in ${xqCode}
		}
		//房屋使用性质查询
		TermQueryBuilder roomTypeQueryBuilder=null;
		if(params.get("useNature")!=null && !StringUtils.isEmpty(params.get("useNature").toString()))
		{
			roomTypeQueryBuilder=new TermQueryBuilder("roomType",params.get("useNature").toString());
		}
		//时间范围查询
		RangeQueryBuilder captureTimeRangeQueryBuilder=new RangeQueryBuilder("captureTime");
		if(params.get("startTime")==null || StringUtils.isEmpty(params.get("startTime").toString()))
		{
			String startTime=DateUtil.getPastDate(90)+" 00:00:00";
			params.put("startTime",startTime);
		}
		if(params.get("endTime")==null || StringUtils.isEmpty(params.get("endTime").toString()))
		{
			String endTime=DateUtil.getTodayEndTime();
			params.put("endTime",endTime);
		}
		//captureTime>=startTime
		captureTimeRangeQueryBuilder.gte(params.get("startTime").toString());
		// UNIX_TIMESTAMP(access.capture_time) &gt;=UNIX_TIMESTAMP(#{startTime})
		//captureTime<=endTime
		captureTimeRangeQueryBuilder.lte(params.get("endTime").toString());
		// UNIX_TIMESTAMP(access.capture_time) &lt;=UNIX_TIMESTAMP(#{endTime})
		//房屋用途查询 purpose
		TermQueryBuilder roomPurposeQueryBuilder=null;
		if(params.get("purpose")!=null && !StringUtils.isEmpty(params.get("purpose").toString()))
		{
			roomPurposeQueryBuilder=new TermQueryBuilder("roomUseType",params.get("purpose").toString());
		}
		//模糊查询
		BoolQueryBuilder fuzzyQueryBuilder=null;
		if(params.get("likeStr")!=null && !StringUtils.isEmpty(params.get("likeStr").toString()))
		{
			fuzzyQueryBuilder=new BoolQueryBuilder();
			//车牌号
			//MatchPhraseQueryBuilder carNumQueryBuilder=new MatchPhraseQueryBuilder("carNumText",params.get("likeStr").toString());查不到 中间字母+数字分词
			WildcardQueryBuilder carNumQueryBuilder=new WildcardQueryBuilder("carNumKeyword","*"+params.get("likeStr").toString()+"*");
			//车主姓名
			MatchPhraseQueryBuilder personNameQueryBuilder=new MatchPhraseQueryBuilder("personName",params.get("likeStr").toString());
			//设备名称 devChnName
			MatchPhraseQueryBuilder devChnNameQueryBuilder=new MatchPhraseQueryBuilder("devChnName",params.get("likeStr").toString());
			//进/出 8进 9出
			String carIn="进门";
			String carOut="出门";
			TermQueryBuilder carAccessInQueryBuilder=null;
			if(carIn.contains(params.get("likeStr").toString()))
			{
				carAccessInQueryBuilder=new TermQueryBuilder("carDirect","8");
			}
			TermQueryBuilder carAccessOutQueryBuilder=null;
			if(carOut.contains(params.get("likeStr").toString()))
			{
				carAccessOutQueryBuilder=new TermQueryBuilder("carDirect","9");
			}
			//or 关系
			fuzzyQueryBuilder
					.should(carNumQueryBuilder)
					.should(personNameQueryBuilder)
					.should(devChnNameQueryBuilder);
			if(carAccessInQueryBuilder!=null)
				fuzzyQueryBuilder.should(carAccessInQueryBuilder);
			if(carAccessOutQueryBuilder!=null)
				fuzzyQueryBuilder.should(carAccessOutQueryBuilder);
		}
		//以and形式 将查询条件汇总
		BoolQueryBuilder finalQueryBuilder=new BoolQueryBuilder();
		//小区查询
		if(communityCodeQueryBuilder!=null)
			finalQueryBuilder.must(communityCodeQueryBuilder);
		finalQueryBuilder.must(captureTimeRangeQueryBuilder);//时间查询
		//房屋使用性质查询
		if(roomTypeQueryBuilder!=null)
			finalQueryBuilder.must(roomTypeQueryBuilder);
		//房屋用途查询 purpose
		if(roomPurposeQueryBuilder!=null)
			finalQueryBuilder.must(roomPurposeQueryBuilder);
		//模糊查询
		if(fuzzyQueryBuilder!=null)
			finalQueryBuilder.must(fuzzyQueryBuilder);

		searchSourceBuilder.query(finalQueryBuilder);
		//排序
		searchSourceBuilder.sort("captureTime", SortOrder.DESC);
		//分页
		int page=1;
		int limit=10;
		if(params.get("page")!=null && !StringUtils.isEmpty(params.get("page").toString()))
		{
			page=Integer.parseInt(params.get("page").toString());
		}
		if(params.get("limit")!=null && !StringUtils.isEmpty(params.get("limit").toString()))
		{
			limit=Integer.parseInt(params.get("limit").toString());
		}
		int start=(page-1)*limit;
		searchSourceBuilder.from(start);
		searchSourceBuilder.size(limit);
		//返回查询结果
		SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new4","car_access",searchSourceBuilder);
		SearchHits searchHits = searchResponse.getHits();
		//总条数
		List<Map<String, Object>> list=new LinkedList<>();
		long count=searchHits.totalHits;
		SearchHit[] searchHit = searchHits.getHits();
		for (SearchHit hit : searchHit) {
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			Map<String, Object> newMap = new HashMap<>();
			//转！！！
			newMap.put("xqCode",sourceAsMap.get("communityCode"));
			newMap.put("xqName",sourceAsMap.get("communityName"));
			newMap.put("devChnName",sourceAsMap.get("devChnName"));
			newMap.put("devChnId",sourceAsMap.get("devChnId"));
			newMap.put("devChnNum",sourceAsMap.get("devChnNum"));

			if(sourceAsMap.get("carNumKeyword")!=null && !StringUtils.isEmpty(sourceAsMap.get("carNumKeyword").toString()))
				newMap.put("carNum",sourceAsMap.get("carNumKeyword"));
			else
				newMap.put("carNum",sourceAsMap.get("未识别"));
			if(sourceAsMap.get("capImage")!=null && !StringUtils.isEmpty(sourceAsMap.get("capImage").toString()))
				newMap.put("realCapturePicPath",PropertiesUtil.getLocalTomcatImageIp()+sourceAsMap.get("capImage").toString());
			else
				newMap.put("realCapturePicPath","");
			if(sourceAsMap.get("carImage")!=null && !StringUtils.isEmpty(sourceAsMap.get("carImage").toString()))
				newMap.put("originalPicPath",PropertiesUtil.getLocalTomcatImageIp()+sourceAsMap.get("carImage").toString());
			else
				newMap.put("originalPicPath","");
			newMap.put("capTime",sourceAsMap.get("captureTime"));
			if(sourceAsMap.get("carDirect")!=null && !StringUtils.isEmpty(sourceAsMap.get("carDirect").toString()))
			{
				if(sourceAsMap.get("carDirect").toString().equals("8"))
					newMap.put("carDirect","进入");
				else if(sourceAsMap.get("carDirect").toString().equals("9"))
					newMap.put("carDirect","驶出");
				else
					newMap.put("carDirect","未识别");
			}
			else
				newMap.put("carDirect","未识别");
			newMap.put("personCode",sourceAsMap.get("personCode"));
			if(sourceAsMap.get("personCode")!=null && !StringUtils.isEmpty(sourceAsMap.get("personCode").toString()))
			{
				if(sourceAsMap.get("personTypeName")!=null && !StringUtils.isEmpty(sourceAsMap.get("personTypeName").toString()))
					newMap.put("liveTypeName",sourceAsMap.get("personTypeName"));
				else
					newMap.put("liveTypeName","无");
				if(sourceAsMap.get("personName")!=null && !StringUtils.isEmpty(sourceAsMap.get("personName").toString()))
					newMap.put("personName",sourceAsMap.get("personName"));
				else
					newMap.put("personName","无");
				if(sourceAsMap.get("roomTypeName")!=null && !StringUtils.isEmpty(sourceAsMap.get("roomTypeName").toString()))
					newMap.put("useNature",sourceAsMap.get("roomTypeName"));
				else
					newMap.put("useNature","无");
				if(sourceAsMap.get("roomUseTypeName")!=null && !StringUtils.isEmpty(sourceAsMap.get("roomUseTypeName").toString()))
					newMap.put("purpose",sourceAsMap.get("roomUseTypeName"));
				else
					newMap.put("purpose","无");
			}
			else
			{
				newMap.put("liveTypeName","无");
				newMap.put("personName","陌生人");
				newMap.put("useNature","无");
				newMap.put("purpose","无");
			}
			list.add(newMap);
		}

		Map<String, Object> resultMap=new HashMap<>();
		if (count <= 10000) {
			resultMap.put("totalRows", count);
		} else {
			resultMap.put("totalRows", 10000);
		}
		resultMap.put("actualTotalRows", count);
		resultMap.put("list", list);
		resultMap.put("currentPage", page);
		//System.out.println(count);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryCarAccessDetailsByCarNum(Map<String, Object> params) {

		//获取当天开始时间和结束时间
		params.put("startTime", DateUtil.getTodayStartTime());
		params.put("endTime",DateUtil.getTodayEndTime());

		List<Map<String, Object>> list = carInOutDao.queryCarAccessDetailsByCarNum(params);
		if (list.size()>0){
			for (Map map:list) {
				//拼接图片
				if (map.get("capImage") != null && !StringUtils.isEmpty(map.get("capImage").toString())) {
					map.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + map.get("capImage").toString());
				}
			}

		}
		Map<String,Object> mapList = new HashMap<>();
		mapList.put("list",list);

		return mapList;
	}

	@Override
	public Map<String, Object> queryCarAccessDetailsByCarNumByES(Map<String, Object> params) throws OssRenderException {

		//建立查询
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();


		//carNum 查询
		MatchPhraseQueryBuilder carNumQueryBuilder=null;
		if (params.get("carNum")!=null && StringUtils.isNotEmpty(params.get("carNum").toString())){
			carNumQueryBuilder=new MatchPhraseQueryBuilder("carNumText",params.get("carNum"));
		}else {
			throw new OssRenderException(ReturnConstants.CODE_FAILED, "查询carNum不能为空");
		}

		//限制查询今日车辆出入
		//获得今日凌晨时间
		String todayStartTime= TimesUtil.getTodayStartTime();

		RangeQueryBuilder todayQueryBuilder=new RangeQueryBuilder("captureTime").gt(todayStartTime);

		//以and形式 将查询条件汇总
		BoolQueryBuilder finalQueryBuilder = new BoolQueryBuilder();


		//时间范围查询
		finalQueryBuilder.must(todayQueryBuilder);

		if (carNumQueryBuilder!=null){
			finalQueryBuilder.must(carNumQueryBuilder);
		}

		searchSourceBuilder.query(finalQueryBuilder);
		//排序
		searchSourceBuilder.sort("captureTime", SortOrder.DESC);

		//分页
		if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
			params.put("page", 1);
		}
		if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
			params.put("limit", 1000);
		}
		int page = Integer.parseInt(params.get("page").toString());
		int limit = Integer.parseInt(params.get("limit").toString());

		int start = (page - 1) * limit;
		searchSourceBuilder.from(start);
		searchSourceBuilder.size(limit);
		//返回查询结果
		SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new4", "car_access", searchSourceBuilder);
		SearchHits searchHits = searchResponse.getHits();
		//总条数
		List<Map<String, Object>> list = new LinkedList<>();
		long count = searchHits.totalHits;
		SearchHit[] searchHit = searchHits.getHits();

		for (SearchHit hit : searchHit) {
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			Map<String, Object> newMap = new HashMap<>();
			//转！！！
			newMap.put("id", sourceAsMap.get("id"));
			newMap.put("capTime", sourceAsMap.get("captureTime"));
			newMap.put("xqCode", sourceAsMap.get("communityCode"));
			newMap.put("xqName", sourceAsMap.get("communityName"));
			newMap.put("capImage", sourceAsMap.get("capImage"));
			newMap.put("capAddress", sourceAsMap.get("deviceName"));
			newMap.put("car_num", sourceAsMap.get("carNumText"));

			if (sourceAsMap.get("capImage") != null && !StringUtils.isEmpty(sourceAsMap.get("capImage").toString()))
				newMap.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("capImage"));
			else
				newMap.put("capImage", "");

			list.add(newMap);
		}

		Map<String, Object> resultMap = new HashMap<>();

		if (count <= 10000) {
			resultMap.put("totalRows", count);
		} else {
			resultMap.put("totalRows", 10000);
		}

		resultMap.put("actualTotalRows", count);
		resultMap.put("list", list);
		resultMap.put("currentPage", params.get("page"));
		//System.out.println(count);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryCarHighAccessList(Map<String, Object> params) {

		String areaId = (String) params.get("areaId");
		if (StringUtils.isBlank(areaId)) {
			params.put("areaId",0);
		}

		//获取当天开始时间和结束时间
		params.put("startTime", DateUtil.getTodayStartTime());
		params.put("endTime",DateUtil.getTodayEndTime());

		int start = Integer.parseInt(params.get("page").toString());
		int limit = Integer.parseInt(params.get("limit").toString());
		PageHelper.startPage(start, limit);//分页
		Map<String,Object> mapList = new HashMap<>();


		List<Map<String,Object>> list = carInOutDao.queryCarHighAccessList(params);
		PageInfo paginator = new PageInfo(list);
		list = paginator.getList();
		int totalRows = (int) paginator.getTotal();

		if(list.size()>0) {
			for (int i=0;i<list.size();i++){

				Map<String, Object> map = list.get(i);

				String carNum = (String) map.get("carNum");
				String personCode = (String) map.get("personCode");
				String captureTime = (String) map.get("captureTime");

				if (ObjectUtil.isNull(map.get("personName"))){
					map.put("personName","");
				}

				String image = PropertiesUtil.getLocalTomcatImageIp() + carInOutDao.queryLastCarImage(carNum, captureTime);
				map.put("image", image);

				if (StringUtils.equals((CharSequence) map.get("type"),"1")) {
					String purpose = (String) params.get("purpose");
					String useNature = (String) params.get("useNature");
					String phone = carInOutDao.queryPhoneByPersonCode(personCode);
					map.put("phone", phone);

					String roomName = carInOutDao.queryRoomName(personCode, purpose, useNature);
					if (StringUtils.isBlank(params.get("purpose").toString()) && StringUtils.isBlank(params.get("useNature").toString())) {
						if (StringUtils.isBlank(roomName)) {
							map.put("roomName", "");
						}else {
							map.put("roomName", roomName);
						}
					}else {
						if (StringUtils.isBlank(roomName)) {
							list.remove(map);
							totalRows = i;
							i=i-1;
						}else {
							map.put("roomName", roomName);
						}
					}

				} else {
					if (StringUtils.isBlank(params.get("purpose").toString()) && StringUtils.isBlank(params.get("useNature").toString())){
						map.put("roomName", "");
					}else {
						list.remove(map);
						totalRows = i;
						i=i-1;
					}

				}

				mapList.put("list", list);
				mapList.put("totalRows", totalRows);
				mapList.put("currentPage", start);
			}

		} else {
			mapList.put("list",new ArrayList());
			mapList.put("totalRows", 0);
			mapList.put("currentPage", start);
	    }

		return mapList;




	}

    @Override
    public Map<String, Object> queryCarHighAccessListElasticSearch(Map<String, Object> params) {
        //建立查询
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        //personCode出现次数 聚合 （即出入次数）
        TermsAggregationBuilder carNumCountTermsAggregationBuilder= AggregationBuilders.terms("carNumCount").field("carNumKeyword").size(10000);
        carNumCountTermsAggregationBuilder.minDocCount(3);
        //附带条件
        //建立条件
        //小区查询
        TermsQueryBuilder communityCodeQueryBuilder=null;
        if(params.get("xqCode")!=null && !StringUtils.isEmpty(params.get("xqCode").toString()))
        {
            communityCodeQueryBuilder=new TermsQueryBuilder("communityCode",(List)params.get("xqCode"));
            // communityCode in ${xqCode}
        }
        //房屋使用性质查询
        TermQueryBuilder roomTypeQueryBuilder=null;
        if(params.get("useNature")!=null && !StringUtils.isEmpty(params.get("useNature").toString()))
        {
            roomTypeQueryBuilder=new TermQueryBuilder("roomType",params.get("useNature").toString());
        }
        //时间范围查询
        RangeQueryBuilder captureTimeRangeQueryBuilder=new RangeQueryBuilder("captureTime");
        if(params.get("startTime")==null || StringUtils.isEmpty(params.get("startTime").toString()))
        {
            String startTime=DateUtil.getTodayStartTime();
            params.put("startTime",startTime);
        }
        if(params.get("endTime")==null || StringUtils.isEmpty(params.get("endTime").toString()))
        {
            String endTime=DateUtil.getTodayEndTime();
            params.put("endTime",endTime);
        }
        //captureTime>=startTime
        captureTimeRangeQueryBuilder.gte(params.get("startTime").toString());
        // UNIX_TIMESTAMP(access.capture_time) &gt;=UNIX_TIMESTAMP(#{startTime})
        //captureTime<=endTime
        captureTimeRangeQueryBuilder.lte(params.get("endTime").toString());
        // UNIX_TIMESTAMP(access.capture_time) &lt;=UNIX_TIMESTAMP(#{endTime})
        //房屋用途查询 purpose
        TermQueryBuilder roomPurposeQueryBuilder=null;
        if(params.get("purpose")!=null && !StringUtils.isEmpty(params.get("purpose").toString()))
        {
            roomPurposeQueryBuilder=new TermQueryBuilder("roomUseType",params.get("purpose").toString());
        }
        //模糊查询
        BoolQueryBuilder fuzzyQueryBuilder=null;
        if(params.get("likeStr")!=null && !StringUtils.isEmpty(params.get("likeStr").toString()))
        {
            fuzzyQueryBuilder=new BoolQueryBuilder();
			//车牌号
			//MatchPhraseQueryBuilder carNumQueryBuilder=new MatchPhraseQueryBuilder("carNumText",params.get("likeStr").toString());查不到 中间字母+数字分词
			WildcardQueryBuilder carNumQueryBuilder=new WildcardQueryBuilder("carNumKeyword","*"+params.get("likeStr").toString()+"*");
            //车主姓名
            MatchPhraseQueryBuilder personNameQueryBuilder=new MatchPhraseQueryBuilder("personName",params.get("likeStr").toString());
            //or 关系
            fuzzyQueryBuilder
                    .should(carNumQueryBuilder)
                    .should(personNameQueryBuilder);
        }
        //(去除)空车牌号
		TermQueryBuilder carNumNotNullQueryBuilder=new TermQueryBuilder("carNumKeyword","");
        //以and形式 将查询条件汇总
        BoolQueryBuilder finalQueryBuilder=new BoolQueryBuilder();
        //小区查询
        if(communityCodeQueryBuilder!=null)
            finalQueryBuilder.must(communityCodeQueryBuilder);
		finalQueryBuilder.must(captureTimeRangeQueryBuilder);//时间查询
        //房屋使用性质查询
        if(roomTypeQueryBuilder!=null)
            finalQueryBuilder.must(roomTypeQueryBuilder);
        //房屋用途查询 purpose
        if(roomPurposeQueryBuilder!=null)
            finalQueryBuilder.must(roomPurposeQueryBuilder);
        //模糊查询
        if(fuzzyQueryBuilder!=null)
            finalQueryBuilder.must(fuzzyQueryBuilder);
        //车牌号非空
		finalQueryBuilder.mustNot(carNumNotNullQueryBuilder);
        //聚合 过滤器整合
        FilterAggregationBuilder filterAggregationBuilder=new FilterAggregationBuilder("carAccessFilter",finalQueryBuilder);
        //聚合 在条件过滤中加入count聚合
        filterAggregationBuilder.subAggregation(carNumCountTermsAggregationBuilder);
        searchSourceBuilder.aggregation(filterAggregationBuilder);
        //发送同步查询请求
        searchSourceBuilder.size(0);//只返回聚合结果
        SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new4","car_access",searchSourceBuilder);
        //提取过滤器
        Map<String, Aggregation> carAccessFilterAggMap = searchResponse.getAggregations().getAsMap();
        ParsedFilter parsedFilter = (ParsedFilter) carAccessFilterAggMap.get("carAccessFilter");
        //提取聚合
        Map<String, Aggregation> carNumCountAggMap = parsedFilter.getAggregations().getAsMap();
        ParsedStringTerms terms = (ParsedStringTerms) carNumCountAggMap.get("carNumCount");
        //遍历 整理结果
        Iterator<Bucket> termsIterator= (Iterator<Bucket>) terms.getBuckets().iterator();
        ArrayList<Map<String,Object>> carHighAccessList=new ArrayList<>();
        //手动分页
        int page=1;
        int limit=10;
        int totalRows=terms.getBuckets().size();
        if(params.get("page")!=null && !StringUtils.isEmpty(params.get("page").toString()))
        {
            page=Integer.parseInt(params.get("page").toString());
        }
        if(params.get("limit")!=null && !StringUtils.isEmpty(params.get("limit").toString()))
        {
            limit=Integer.parseInt(params.get("limit").toString());
        }
        int currentPage = page;
        int totalPage = totalRows / limit;
        if (totalRows % limit != 0) totalPage += 1;
        if (page > totalPage) currentPage = totalPage;
        if (page < 1) currentPage = 1;

        int start=(currentPage-1)*limit;
        int end=currentPage*limit;
        int pageI=0;
        while (termsIterator.hasNext()) {
            Bucket bucket = termsIterator.next();
            if (pageI >= start && pageI < end) {
                //carNum
                String carNum = (String) bucket.getKey();
                //统计数量
                long docCount = bucket.getDocCount();
                System.out.println(carNum+":"+docCount);
				//子查询 此人对应的最新出入信息
				SearchSourceBuilder singleSearchSourceBuilder=new SearchSourceBuilder();
				//按照carNum查找最新的出入信息
				TermQueryBuilder singleCarNumTermQueryBuilder=new TermQueryBuilder("carNumKeyword",carNum);
				singleSearchSourceBuilder.query(singleCarNumTermQueryBuilder);
				//按照时间排序
				singleSearchSourceBuilder.sort("captureTime",SortOrder.DESC);
				//查询结果整理
				SearchResponse singleSearchResponse = ElasticSearchUtil.select("switch_cloud_new4","car_access",singleSearchSourceBuilder);
				SearchHits singleSearchHits = singleSearchResponse.getHits();
				//取最新的
				SearchHit[] singleSearchHit = singleSearchHits.getHits();
				Map<String, Object> singleSourceAsMap = singleSearchHit[0].getSourceAsMap();
				Map<String, Object> newMap=new HashMap<>();
				newMap.put("type",singleSourceAsMap.get("type"));
				if(singleSourceAsMap.get("type")!=null && !StringUtils.isEmpty(singleSourceAsMap.get("type").toString()))
				{
					String type=singleSourceAsMap.get("type").toString();
					if(type.equals("1"))
					{
						if(singleSourceAsMap.get("personName")!=null && !StringUtils.isEmpty(singleSourceAsMap.get("personName").toString()))
							newMap.put("personName",singleSourceAsMap.get("personName"));
						else
							newMap.put("personName","无");
						newMap.put("personCode",singleSourceAsMap.get("personCode"));
						if(singleSourceAsMap.get("personPhone")!=null && !StringUtils.isEmpty(singleSourceAsMap.get("personPhone").toString()))
							newMap.put("phone",singleSourceAsMap.get("personPhone"));
						else
							newMap.put("phone","无");
						if(singleSourceAsMap.get("roomName")!=null && !StringUtils.isEmpty(singleSourceAsMap.get("roomName").toString()))
							newMap.put("roomName",singleSourceAsMap.get("roomName"));
						else
							newMap.put("roomName","无");
					}
					else if(type.equals("2"))
					{
						newMap.put("personName","陌生人");
						newMap.put("personCode","");
						newMap.put("phone","无");
						newMap.put("roomName","无");
					}
				}
				else
				{
					newMap.put("personName","陌生人");
					newMap.put("personCode","");
					newMap.put("personPhone","无");
					newMap.put("roomName","无");
				}
				if(singleSourceAsMap.get("capImage")!=null && !StringUtils.isEmpty(singleSourceAsMap.get("capImage").toString()))
					newMap.put("image",PropertiesUtil.getLocalTomcatImageIp()+singleSourceAsMap.get("capImage").toString());
				else
					newMap.put("image","");
				newMap.put("captureTime",singleSourceAsMap.get("captureTime"));
				newMap.put("xqName",singleSourceAsMap.get("communityName"));
				newMap.put("xqCode",singleSourceAsMap.get("communityCode"));
				newMap.put("carNum",singleSourceAsMap.get("carNumKeyword"));
				newMap.put("senseNum",docCount);
				carHighAccessList.add(newMap);
            }
            pageI++;
        }
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("list",carHighAccessList);
		resultMap.put("currentPage",currentPage);
		resultMap.put("totalRows",totalRows);
        return resultMap;
    }
}
