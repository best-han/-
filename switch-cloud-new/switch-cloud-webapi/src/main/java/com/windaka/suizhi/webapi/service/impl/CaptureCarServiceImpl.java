package com.windaka.suizhi.webapi.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.webapi.dao.CaptureCarMapper;
import com.windaka.suizhi.webapi.dao.MsgSocketIdMapper;
import com.windaka.suizhi.webapi.service.CaptureCarService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaptureCarServiceImpl implements CaptureCarService {
    @Autowired
    private CaptureCarMapper captureCarMapper;
    @Autowired
    private MsgSocketIdMapper msgSocketIdMapper;



    @Override
    public List<Map<String, Object>> queryCapCarList(Map<String, Object> params) throws OssRenderException {
        //        根据记录表查找 上一次查询的最大id
        int lastId = msgSocketIdMapper.queryLastIdByRecordName("capture_car");

        //未发送的 告警人员列表
        List<Map<String, Object>> lists =captureCarMapper.queryCapCarList(lastId);
        if (lists.size() > 0) {

            Iterator i=lists.iterator();
            while (i.hasNext()){
                Map<String,Object> t=(Map<String,Object>)i.next();


                //处理图片 拼接地址
                if (t.get("capImage")!=null &&! StringUtils.isEmpty(t.get("capImage").toString())){

                    t.put("capImage", PropertiesUtil.getLocalTomcatImageIp()+t.get("capImage"));

                }
                if(t.get("fullImage")!=null &&! StringUtils.isEmpty(t.get("fullImage").toString())){
                    t.put("fullImage", PropertiesUtil.getLocalTomcatImageIp()+t.get("fullImage"));
                }
                t.put("webStatus","r");//推送标志
                t.put("msgType","2");
            }

//            更新id记录表
            Map<String, Object> lastRecord = lists.get(lists.size() - 1);//获取最后一条id (max)
            lastId = (Integer) lastRecord.get("id");
            Map<String, Object> innerParam = new HashMap<>();
            innerParam.put("recordName", "capture_car");
            innerParam.put("maxId", lastId);
            msgSocketIdMapper.updateLastIdByRecordName(innerParam);
        }

        return lists;
    }

    @Override
    public Map<String, Object> queryCarCaptureList(Map<String, Object> params) {
        String areaId = (String) params.get("areaId");
        if (StringUtils.isBlank(areaId)) {
            params.put("areaId",0);
        }

        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页
        Map<String,Object> mapList = new HashMap<>();

        List<Map<String, Object>> list = captureCarMapper.queryCarCaptureList(params);
        if(list.size()>0) {
            for (Map map : list) {
                PageInfo paginator = new PageInfo(list);
                list = paginator.getList();
                //拼接图片
                if (map.get("capImage") != null && !StringUtils.isEmpty(map.get("capImage").toString())) {
                    map.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + map.get("capImage").toString());
                }
                if (map.get("fullImage") != null && !StringUtils.isEmpty(map.get("fullImage").toString())) {
                    map.put("fullImage", PropertiesUtil.getLocalTomcatImageIp() + map.get("fullImage").toString());
                }
                mapList.put("list", list);
                mapList.put("totalRows", paginator.getTotal());
                mapList.put("currentPage", start);
            }
        }else {
            mapList.put("list",new ArrayList());
            mapList.put("totalRows", 0);
            mapList.put("currentPage", start);
        }

        return mapList;
    }

    @Override
    public Map<String, Object> queryCarCaptureListByES(Map<String, Object> params) {
        //建立查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //具体

        //小区查询
        TermsQueryBuilder communityCodeQueryBuilder = null;
        if (params.get("xqCode") != null && !StringUtils.isEmpty(params.get("xqCode").toString())) {
            communityCodeQueryBuilder = new TermsQueryBuilder("communityCode", (List) params.get("xqCode"));
            // communityCode in ${xqCode}
        }

        //时间范围查询
        RangeQueryBuilder captureTimeRangeQueryBuilder = new RangeQueryBuilder("captureTime");
        if (params.get("startTime") != null && !StringUtils.isEmpty(params.get("startTime").toString())) {
            //captureTime>=startTime
            captureTimeRangeQueryBuilder.gte(params.get("startTime").toString());
            // UNIX_TIMESTAMP(access.capture_time) &gt;=UNIX_TIMESTAMP(#{startTime})
        }
        if (params.get("endTime") != null && !StringUtils.isEmpty(params.get("endTime").toString())) {
            //captureTime<=endTime
            captureTimeRangeQueryBuilder.lte(params.get("endTime").toString());
            // UNIX_TIMESTAMP(access.capture_time) &lt;=UNIX_TIMESTAMP(#{endTime})
        }

        //车牌
        MatchPhraseQueryBuilder carNumQueryBuilder=null;
        if (params.get("carNum")!=null && !StringUtils.isEmpty(params.get("carNum").toString())){
            carNumQueryBuilder=new MatchPhraseQueryBuilder("carNumText",params.get("carNum"));
        }

        //车辆颜色
        MatchPhraseQueryBuilder carColorNameQueryBuilder=null;
        if (params.get("carColorName")!=null && !StringUtils.isEmpty(params.get("carColorName").toString())){
            carColorNameQueryBuilder=new MatchPhraseQueryBuilder("carColorName",params.get("carColorName"));
        }

        //车辆类型
        MatchPhraseQueryBuilder carTypeNameQueryBuilder=null;
        if (params.get("carTypeName")!=null && !StringUtils.isEmpty(params.get("carTypeName").toString())){
            carTypeNameQueryBuilder=new MatchPhraseQueryBuilder("carTypeName",params.get("carTypeName"));
        }

        //车辆品牌
        MatchPhraseQueryBuilder carBrandNameQueryBuilder=null;
        if (params.get("carBrandName")!=null && !StringUtils.isEmpty(params.get("carBrandName").toString())){
            carBrandNameQueryBuilder=new MatchPhraseQueryBuilder("carBrandName",params.get("carBrandName"));
        }

        //以and形式 将查询条件汇总
        BoolQueryBuilder finalQueryBuilder = new BoolQueryBuilder();

        //小区查询
        if (communityCodeQueryBuilder != null)
            finalQueryBuilder.must(communityCodeQueryBuilder);

        //时间范围查询
        finalQueryBuilder.must(captureTimeRangeQueryBuilder);

        if (carNumQueryBuilder!=null){
            finalQueryBuilder.must(carNumQueryBuilder);
        }
        if (carColorNameQueryBuilder!=null){
            finalQueryBuilder.must(carColorNameQueryBuilder);
        }
        if (carTypeNameQueryBuilder!=null){
            finalQueryBuilder.must(carTypeNameQueryBuilder);
        }
        if (carBrandNameQueryBuilder!=null){
            finalQueryBuilder.must(carBrandNameQueryBuilder);
        }

        searchSourceBuilder.query(finalQueryBuilder);

        //排序
        searchSourceBuilder.sort("captureTime", SortOrder.DESC);

        //分页
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page",1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit",10);
        }
        int page=Integer.parseInt(params.get("page").toString());
        int limit=Integer.parseInt(params.get("limit").toString());

        int start = (page - 1) * limit;


        searchSourceBuilder.from(start);
        searchSourceBuilder.size(limit);
        //返回查询结果
        SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new3", "capture_car", searchSourceBuilder);
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
            newMap.put("capImage", sourceAsMap.get("capImage"));
            newMap.put("capAddress", sourceAsMap.get("deviceName"));
            newMap.put("capTime", sourceAsMap.get("captureTime"));
            newMap.put("capDeviceLocation", sourceAsMap.get("deviceLocation"));
            newMap.put("fullImage", sourceAsMap.get("fullImage"));

            if (sourceAsMap.get("capImage") != null && !StringUtils.isEmpty(sourceAsMap.get("capImage").toString()))
                newMap.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("capImage"));
            else
                newMap.put("capImage", "");
            if (sourceAsMap.get("fullImage") != null && !StringUtils.isEmpty(sourceAsMap.get("fullImage").toString()))
                newMap.put("fullImage", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("fullImage"));
            else
                newMap.put("fullImage", "");

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
}
