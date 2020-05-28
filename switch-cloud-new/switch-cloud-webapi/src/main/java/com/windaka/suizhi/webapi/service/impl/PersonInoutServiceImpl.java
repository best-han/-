package com.windaka.suizhi.webapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.dao.BasePersonMapper;
import com.windaka.suizhi.webapi.dao.BaseRoomDao;
import com.windaka.suizhi.webapi.dao.PersonInoutMapper;
import com.windaka.suizhi.webapi.service.PersonInoutService;
import com.windaka.suizhi.webapi.util.DateUtil;
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

import java.util.*;

@Service
public class PersonInoutServiceImpl implements PersonInoutService {
    @Autowired
    private PersonInoutMapper personInoutMapper;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private BaseRoomDao baseRoomMapper;

    @Override
    public Map<String, Object> personAccessList(Map<String, Object> params) throws OssRenderException {
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        Map resultMap = new HashMap();
        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        List<Map<String, Object>> lists = personInoutMapper.personAccessList(params);
        if (lists.size() > 0 && null != lists) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {

                //处理图片 拼接地址
                if (list.get("capImage") != null && !StringUtils.isEmpty(list.get("capImage").toString())) {
                    list.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("capImage"));
                }
                if (list.get("faceImage") != null && !StringUtils.isEmpty(list.get("faceImage").toString())) {
                    list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("faceImage"));
                }

                //处理陌生人情况
                if (list.get("capType") != null && !StringUtils.isEmpty(list.get("capType").toString())) {
                    String capType = list.get("capType").toString();
                    if (capType.equals("2")) {
                        list.put("personName", "陌生人");
                        list.put("phone", "无");
                        list.put("paperNum", "无");
                        list.put("roomName", "无");
                        list.put("liveTypeName", "无");
                    }
                    if (capType.equals("1")) {
                        if (list.get("liveType") != null && !StringUtils.isEmpty(list.get("liveType").toString())) {
                            String liveType = list.get("liveType").toString();
                            if (liveType.equals("1") ) {
                                list.put("liveTypeName", "业主");
                            }
                            if (liveType.equals("2")) {
                                list.put("liveTypeName", "家庭成员");
                            }
                            if (liveType.equals("3")) {
                                list.put("liveTypeName", "租户");
                            }
                        }
                    }
                }
                //出入类型 转汉子 1：进门  2 出门
                if (list.get("access") != null && !StringUtils.isEmpty(list.get("access").toString())) {
                    String access = list.get("access").toString();
                    if (access.equals("1")) {
                        list.put("access", "进门");
                    }
                    if (access.equals("2")) {
                        list.put("access", "出门");
                    }
                }
            }

            resultMap.put("list", lists);
            resultMap.put("totalRows", paginator.getTotal());
            resultMap.put("currentPage", start);

        } else {
            resultMap.put("totalRows", 0);
            resultMap.put("list", new ArrayList());
            resultMap.put("currentPage", start);

        }
        return resultMap;
    }

    @Override
    public Map<String, Object> personAccessListOptimize(Map<String, Object> params) throws OssRenderException {
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        else
        {
            params.put("page",Integer.parseInt(params.get("page").toString()));
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        else
        {
            params.put("limit",Integer.parseInt(params.get("limit").toString()));
        }
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        int start = (page-1)*limit;
        params.put("start",start);
        //是否有必要链接人/房表
        if (params.get("likeStr") != null && !StringUtils.isEmpty(params.get("likeStr").toString())){
            params.put("personLink",1);
            params.put("houseLink",1);
        }
        //是否有必要链接人员列表
        if (params.get("personName") != null && !StringUtils.isEmpty(params.get("personName").toString())){
            params.put("personLink",1);
        }
        if (params.get("liveType") != null && !StringUtils.isEmpty(params.get("liveType").toString())){
            params.put("personLink",1);
        }

        int totalRows = personInoutMapper.personAccessListOptimizeCount(params);
        List<Map<String, Object>> lists = personInoutMapper.personAccessListOptimize(params);
        Map resultMap = new HashMap();
        Map innerMap;
        if (lists.size() > 0 && null != lists) {
            for (Map<String, Object> list : lists) {

                //处理图片 拼接地址
                if (list.get("capImage") != null && !StringUtils.isEmpty(list.get("capImage").toString())) {
                    list.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("capImage"));
                }
                //是否链接人员表
                if (params.get("personLink")!=null && params.get("personLink").toString().equals("1")){
                    if (list.get("faceImage") != null && !StringUtils.isEmpty(list.get("faceImage").toString())) {
                        list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("faceImage"));
                    }
                }
                else {
                    //未链接人员表 则链接查询此人的信息
                    innerMap=new HashMap();
                    innerMap.put("personId",list.get("personId"));
                    List<Map<String,Object>> singlePersonL=basePersonMapper.querySinglePerson(innerMap);
                    if(singlePersonL.size()>0)
                    {
                        Map singlePerson=singlePersonL.get(0);
                        list.put("personName", singlePerson.get("name"));
                        if (singlePerson.get("image") != null && !StringUtils.isEmpty(singlePerson.get("image").toString())) {
                            list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + singlePerson.get("image"));
                        }
                        list.put("phone", singlePerson.get("phone"));
                        list.put("paperNum", singlePerson.get("paperNumber"));
                        list.put("liveType", singlePerson.get("type"));
                    }
                    else
                    {
                        list.put("personName", "陌生人");
                        list.put("faceImage", "");
                        list.put("phone", "无");
                        list.put("paperNum", "无");
                        list.put("liveType", null);
                    }
                }

                //是否链接房间表
                if (params.get("houseLink")!=null && params.get("houseLink").toString().equals("1")){

                }
                else {
                    //未链接人员表 则链接查询此人的房间名
                    innerMap=new HashMap();
                    innerMap.put("personId",list.get("personId"));
                    List<Map<String,Object>> singlePersonL=basePersonMapper.querySinglePerson(innerMap);
                    String roomCode="";
                    if(singlePersonL.size()>0) {
                        Map singlePerson = singlePersonL.get(0);
                        if(singlePerson.get("roomCode")!=null && !StringUtils.isEmpty(singlePerson.get("roomCode").toString()))
                        {
                            roomCode=baseRoomMapper.queryHouseNameByRoomCode(singlePerson.get("roomCode").toString());
                        }
                    }
                    list.put("roomName",roomCode);
                }

                //处理陌生人情况
                if (list.get("capType") != null && !StringUtils.isEmpty(list.get("capType").toString())) {
                    String capType = list.get("capType").toString();
                    if (capType.equals("2")) {
                        list.put("personName", "陌生人");
                        list.put("phone", "无");
                        list.put("paperNum", "无");
                        list.put("roomName", "无");
                        list.put("liveTypeName", "无");
                    }
                    if (capType.equals("1")) {
                        if (list.get("liveType") != null && !StringUtils.isEmpty(list.get("liveType").toString())) {
                            String liveType = list.get("liveType").toString();
                            if (liveType.equals("1") ) {
                                list.put("liveTypeName", "业主");
                            }
                            if (liveType.equals("2")) {
                                list.put("liveTypeName", "家庭成员");
                            }
                            if (liveType.equals("3")) {
                                list.put("liveTypeName", "租户");
                            }
                        }
                    }
                }
                //出入类型 转汉子 1：进门  2 出门
                if (list.get("access") != null && !StringUtils.isEmpty(list.get("access").toString())) {
                    String access = list.get("access").toString();
                    if (access.equals("1")) {
                        list.put("access", "进门");
                    }
                    if (access.equals("2")) {
                        list.put("access", "出门");
                    }
                }
            }

            resultMap.put("list", lists);
            resultMap.put("totalRows", totalRows);
            resultMap.put("currentPage", params.get("page"));

        } else {
            resultMap.put("totalRows", 0);
            resultMap.put("list", new ArrayList());
            resultMap.put("currentPage", params.get("page"));

        }
        return resultMap;
    }

    @Override
    public Map<String, Object> personAccessListElasticSearch(Map<String, Object> params) throws OssRenderException {
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
        //姓名查询
        TermQueryBuilder personNameQueryBuilder=null;
        if(params.get("personName")!=null && !StringUtils.isEmpty(params.get("personName").toString()))
        {
            //personName=#{personName}
            personNameQueryBuilder=new TermQueryBuilder("personName",params.get("personName").toString());
        }
        //居住类型
        TermQueryBuilder personTypeQueryBuilder=null;
        if(params.get("liveType")!=null && !StringUtils.isEmpty(params.get("liveType").toString()))
        {
            //personType=#{liveType}
            personTypeQueryBuilder=new TermQueryBuilder("personType",params.get("liveType").toString());
        }
        //模糊查询
        BoolQueryBuilder fuzzyQueryBuilder=null;
        if(params.get("likeStr")!=null && !StringUtils.isEmpty(params.get("likeStr").toString()))
        {
            //模糊查询房间名称
            MatchPhraseQueryBuilder roomNameFuzzyQueryBuilder=new MatchPhraseQueryBuilder("roomName",params.get("likeStr").toString());
            //模糊查询人员姓名
            MatchPhraseQueryBuilder personNameFuzzyQueryBuilder=new MatchPhraseQueryBuilder("personName",params.get("likeStr").toString());
            //模糊查询电话
            WildcardQueryBuilder personPhoneFuzzyQueryBuilder=new WildcardQueryBuilder("personPhone","*"+params.get("likeStr").toString()+"*");
            //模糊查询证件号码
            WildcardQueryBuilder personPaperNumberQueryBuilder=new WildcardQueryBuilder("personPaperNumber","*"+params.get("likeStr").toString()+"*");
            //模糊查询设备名称
            MatchPhraseQueryBuilder deviceNameFuzzyQueryBuilder=new MatchPhraseQueryBuilder("deviceName",params.get("likeStr").toString());
            //查询的关系之间为 或(or)
            fuzzyQueryBuilder=new BoolQueryBuilder();
            fuzzyQueryBuilder
                .should(roomNameFuzzyQueryBuilder)
                .should(personNameFuzzyQueryBuilder)
                .should(personPhoneFuzzyQueryBuilder)
                .should(personPaperNumberQueryBuilder)
                .should(deviceNameFuzzyQueryBuilder);
            //相当于 roomName like '%likeStr%' or personName like '%likeStr%' or ....
            //证件号码和电话不支持自动分词 只能通配符/手动分词
        }

        //以and形式 将查询条件汇总
        BoolQueryBuilder finalQueryBuilder=new BoolQueryBuilder();
        //小区查询
        if(communityCodeQueryBuilder!=null)
            finalQueryBuilder.must(communityCodeQueryBuilder);
        //时间范围查询
        finalQueryBuilder.must(captureTimeRangeQueryBuilder);
        //姓名查询
        if(personNameQueryBuilder!=null)
            finalQueryBuilder.must(personNameQueryBuilder);
        //居住类型
        if(personTypeQueryBuilder!=null)
            finalQueryBuilder.must(personTypeQueryBuilder);
        //模糊查询
        if(fuzzyQueryBuilder!=null)
            finalQueryBuilder.must(fuzzyQueryBuilder);

        searchSourceBuilder.query(finalQueryBuilder);
        //排序
        searchSourceBuilder.sort("captureTime",SortOrder.DESC);
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
        SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new","person_inout",searchSourceBuilder);
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
            newMap.put("deviceId",sourceAsMap.get("deviceCode"));
            newMap.put("deviceName",sourceAsMap.get("deviceName"));
            newMap.put("openTime",sourceAsMap.get("captureTime"));
            newMap.put("personCode",sourceAsMap.get("personCode"));
            newMap.put("personName",sourceAsMap.get("personName"));
            if (sourceAsMap.get("access") != null && !StringUtils.isEmpty(sourceAsMap.get("access").toString())) {
                String access = sourceAsMap.get("access").toString();
                if (access.equals("1")) {
                    newMap.put("access", "进门");
                }
                if (access.equals("2")) {
                    newMap.put("access", "出门");
                }
            }
            if(sourceAsMap.get("capImage")!=null && !StringUtils.isEmpty(sourceAsMap.get("capImage").toString()))
                newMap.put("capImage",PropertiesUtil.getLocalTomcatImageIp()+sourceAsMap.get("capImage"));
            else
                newMap.put("capImage","");
            if(sourceAsMap.get("personImagePath")!=null && !StringUtils.isEmpty(sourceAsMap.get("personImagePath").toString()))
                newMap.put("faceImage",PropertiesUtil.getLocalTomcatImageIp()+sourceAsMap.get("personImagePath"));
            else
                newMap.put("faceImage","");
            if(sourceAsMap.get("roomName")!=null && !StringUtils.isEmpty(sourceAsMap.get("roomName").toString()))
                newMap.put("roomName",sourceAsMap.get("roomName"));
            else
                newMap.put("roomName","无");
            if(sourceAsMap.get("personPhone")!=null && !StringUtils.isEmpty(sourceAsMap.get("personPhone").toString()))
                newMap.put("phone",sourceAsMap.get("personPhone"));
            else
                newMap.put("phone","无");
            if(sourceAsMap.get("personPaperNumber")!=null && !StringUtils.isEmpty(sourceAsMap.get("personPaperNumber").toString()))
                newMap.put("paperNum",sourceAsMap.get("personPaperNumber"));
            else
                newMap.put("paperNum","无");
            newMap.put("liveType",sourceAsMap.get("personType"));
            if(sourceAsMap.get("personTypeName")!=null && !StringUtils.isEmpty(sourceAsMap.get("personTypeName").toString()))
                newMap.put("liveTypeName",sourceAsMap.get("personTypeName"));
            else
                newMap.put("liveTypeName","无");
            newMap.put("capType",sourceAsMap.get("type"));
            newMap.put("personId",sourceAsMap.get("personCode"));

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
    public Map<String, Object> personSenseHighAccess(Map<String, Object> params) throws OssRenderException {
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        Map resultMap = new HashMap();
        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        List<Map<String, Object>> lists = personInoutMapper.personSenseHighAccess(params);
        if (lists.size() > 0 && null != lists) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {

                //处理图片 拼接地址
                if (list.get("capImage") != null && !StringUtils.isEmpty(list.get("capImage").toString())) {
                    list.put("capImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("capImage"));
                }
                if (list.get("faceImage") != null && !StringUtils.isEmpty(list.get("faceImage").toString())) {
                    list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("faceImage"));
                }
                //处理人员类型情况
                if (list.get("personType") != null && !StringUtils.isEmpty(list.get("personType").toString())) {
                    String personType = list.get("personType").toString();
                    if (personType.equals("2")) {
                        list.put("personName", "陌生人");
                        list.put("phone", "无");
                        list.put("liveTypeName", "陌生人");
                    }
                    if (personType.equals("1")) {
                        if (list.get("liveType") != null && !StringUtils.isEmpty(list.get("liveType").toString())) {
                            String liveType = list.get("liveType").toString();
                            if (liveType.equals("1") ) {
                                list.put("liveTypeName", "业主");
                            }
                            if (liveType.equals("2")){
                                list.put("liveTypeName", "家庭成员");
                            }
                            if (liveType.equals("3")) {
                                list.put("liveTypeName", "租户");
                            }
                        }
                    }
                }
            }

            resultMap.put("list", lists);
            resultMap.put("totalRows", paginator.getTotal());
            resultMap.put("currentPage", start);
        } else {
            resultMap.put("totalRows", 0);
            resultMap.put("list", new ArrayList());
            resultMap.put("currentPage", start);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> personSenseHighAccessElasticSearch(Map<String, Object> params) throws OssRenderException {
        //建立查询
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        //personCode出现次数 聚合 （即出入次数）
        TermsAggregationBuilder personCodeCountTermsAggregationBuilder=AggregationBuilders.terms("personCodeCount").field("personCode").size(10000);
        personCodeCountTermsAggregationBuilder.minDocCount(3);
        if(params.get("accessNum")!=null && !StringUtils.isEmpty(params.get("accessNum").toString()))
        {
            int accessNum=Integer.parseInt(params.get("accessNum").toString());
            if(accessNum>3)
                personCodeCountTermsAggregationBuilder.minDocCount(accessNum);
        }
        //附带条件
        //建立条件
        //小区查询
        TermsQueryBuilder communityCodeQueryBuilder=null;
        if(params.get("xqCode")!=null && !StringUtils.isEmpty(params.get("xqCode").toString()))
        {
            communityCodeQueryBuilder=new TermsQueryBuilder("communityCode",(List)params.get("xqCode"));
            // communityCode in ${xqCode}
        }
        //人员类型查询 1业主  2家庭成员  3租户  4 陌生人
        TermQueryBuilder liveTypeQueryBuilder=null;
        if(params.get("liveType")!=null && !StringUtils.isEmpty(params.get("liveType").toString()))
        {
            String liveType=params.get("liveType").toString();
            if(liveType.equals("4"))
            {
                liveTypeQueryBuilder=new TermQueryBuilder("type","2");//云端自己分析的type（正常人/陌生人）
            }
            else
            {
                liveTypeQueryBuilder=new TermQueryBuilder("personType",params.get("liveType").toString());
            }
        }
        //时间范围查询
        String startTime = DateUtil.getTodayStartTime();
        String endTime = DateUtil.getTodayEndTime();
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        RangeQueryBuilder captureTimeRangeQueryBuilder=new RangeQueryBuilder("captureTime");
        if(params.get("startTime")!=null && !StringUtils.isEmpty(params.get("startTime").toString()))
        {
            //captureTime>=startTime
            captureTimeRangeQueryBuilder.gte(params.get("startTime").toString());
            // UNIX_TIMESTAMP(access.capture_time) &gt;=UNIX_TIMESTAMP(#{startTime})
        }
        if(params.get("endTime")!=null && !StringUtils.isEmpty(params.get("endTime").toString()))
        {
            //captureTime<=endTime
            captureTimeRangeQueryBuilder.lte(params.get("endTime").toString());
            // UNIX_TIMESTAMP(access.capture_time) &lt;=UNIX_TIMESTAMP(#{endTime})
        }
        //模糊查询
        BoolQueryBuilder fuzzyQueryBuilder=null;
        if(params.get("likeStr")!=null && !StringUtils.isEmpty(params.get("likeStr").toString()))
        {
            //模糊查询人员姓名
            MatchPhraseQueryBuilder personNameFuzzyQueryBuilder=new MatchPhraseQueryBuilder("personName",params.get("likeStr").toString());
            //模糊查询电话
            WildcardQueryBuilder personPhoneFuzzyQueryBuilder=new WildcardQueryBuilder("personPhone","*"+params.get("likeStr").toString()+"*");
            //模糊查询证件号码
            WildcardQueryBuilder personPaperNumberQueryBuilder=new WildcardQueryBuilder("personPaperNumber","*"+params.get("likeStr").toString()+"*");
            //查询的关系之间为 或(or)
            fuzzyQueryBuilder=new BoolQueryBuilder();
            fuzzyQueryBuilder
                    .should(personNameFuzzyQueryBuilder)
                    .should(personPhoneFuzzyQueryBuilder)
                    .should(personPaperNumberQueryBuilder);
            //相当于 personName like '%likeStr%' or ....
            //证件号码和电话不支持自动分词 只能通配符/手动分词
        }
        //最终过滤条件
        BoolQueryBuilder finalQueryBuilder=new BoolQueryBuilder();
        if(communityCodeQueryBuilder!=null)
            finalQueryBuilder.must(communityCodeQueryBuilder);//小区查询
        if(liveTypeQueryBuilder!=null)
            finalQueryBuilder.must(liveTypeQueryBuilder);//人员类型查询
        finalQueryBuilder.must(captureTimeRangeQueryBuilder);//时间查询
        if(fuzzyQueryBuilder!=null)
            finalQueryBuilder.must(fuzzyQueryBuilder);//模糊查询
        //聚合 过滤器整合
        FilterAggregationBuilder filterAggregationBuilder=new FilterAggregationBuilder("personInoutFilter",finalQueryBuilder);
        //聚合 在条件过滤中加入count聚合
        filterAggregationBuilder.subAggregation(personCodeCountTermsAggregationBuilder);
        searchSourceBuilder.aggregation(filterAggregationBuilder);
        //发送同步查询请求
        searchSourceBuilder.size(0);//只返回聚合结果
        SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new","person_inout",searchSourceBuilder);
        //提取过滤器
        Map<String, Aggregation> personInoutFilterAggMap = searchResponse.getAggregations().getAsMap();
        ParsedFilter parsedFilter = (ParsedFilter) personInoutFilterAggMap.get("personInoutFilter");
        //提取聚合
        Map<String, Aggregation> personCodeCountAggMap = parsedFilter.getAggregations().getAsMap();
        ParsedStringTerms terms = (ParsedStringTerms) personCodeCountAggMap.get("personCodeCount");
        //遍历 整理结果
        Iterator<Bucket> termsIterator= (Iterator<Bucket>) terms.getBuckets().iterator();
        ArrayList<Map<String,Object>> personHighAccessList=new ArrayList<>();
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
        while (termsIterator.hasNext()){
            Bucket bucket = termsIterator.next();
            if(pageI>=start&&pageI<end)
            {
                //personCode
                String personCode = (String) bucket.getKey();
                //统计数量
                long docCount = bucket.getDocCount();
                //打印
                System.out.println(personCode + " " + docCount);
                //子查询 此人对应的最新出入信息
                SearchSourceBuilder singleSearchSourceBuilder=new SearchSourceBuilder();
                //按照personCode查询
                TermQueryBuilder singlePersonCodeTermQueryBuilder=new TermQueryBuilder("personCode",personCode);
                singleSearchSourceBuilder.query(singlePersonCodeTermQueryBuilder);
                //按照时间排序
                singleSearchSourceBuilder.sort("captureTime",SortOrder.DESC);
                //查询结果整理
                SearchResponse singleSearchResponse = ElasticSearchUtil.select("switch_cloud_new","person_inout",singleSearchSourceBuilder);
                SearchHits singleSearchHits = singleSearchResponse.getHits();
                //取最新的
                SearchHit[] singleSearchHit = singleSearchHits.getHits();
                Map<String, Object> singleSourceAsMap = singleSearchHit[0].getSourceAsMap();
                Map<String, Object> newMap=new HashMap<>();
//                select access.community_code xqCode,access.person_code personId,access.community_name xqName,access.person_name
//                personName,access.cap_image capImage,person.image faceImage,
//                    person.type liveType,person.phone phone,access.cap_type personType ,count(*) senseNum
                newMap.put("xqCode",singleSourceAsMap.get("communityCode"));
                newMap.put("personId",singleSourceAsMap.get("personCode"));
                newMap.put("xqName",singleSourceAsMap.get("communityName"));
                newMap.put("liveType",singleSourceAsMap.get("personType"));
                newMap.put("personType",singleSourceAsMap.get("type"));
                if(singleSourceAsMap.get("capImage")!=null && !StringUtils.isEmpty(singleSourceAsMap.get("capImage").toString()))
                {
                    newMap.put("capImage",PropertiesUtil.getLocalTomcatImageIp()+singleSourceAsMap.get("capImage"));
                }
                else
                {
                    newMap.put("capImage","");
                }
                if(singleSourceAsMap.get("personImagePath")!=null && !StringUtils.isEmpty(singleSourceAsMap.get("personImagePath").toString()))
                {
                    newMap.put("faceImage",PropertiesUtil.getLocalTomcatImageIp()+singleSourceAsMap.get("personImagePath"));
                }
                else
                {
                    newMap.put("faceImage","");
                }
                //处理人员类型情况
                if (singleSourceAsMap.get("type") != null && !StringUtils.isEmpty(singleSourceAsMap.get("type").toString())) {
                    String type = singleSourceAsMap.get("type").toString();
                    if (type.equals("2")) {
                        newMap.put("personName", "陌生人");
                        newMap.put("phone", "无");
                        newMap.put("liveTypeName", "陌生人");
                    }
                    if (type.equals("1")) {
                        if (singleSourceAsMap.get("personType") != null && !StringUtils.isEmpty(singleSourceAsMap.get("personType").toString())) {
                            newMap.put("personName", singleSourceAsMap.get("personName"));
                            newMap.put("phone", singleSourceAsMap.get("personPhone"));
                            newMap.put("liveTypeName", singleSourceAsMap.get("personTypeName"));
                        }
                    }
                }
                newMap.put("senseNum",docCount);
                personHighAccessList.add(newMap);
            }
            pageI++;
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",personHighAccessList);
        resultMap.put("currentPage",currentPage);
        resultMap.put("totalRows",totalRows);
        return resultMap;
    }

    @Override
    public Map<String, Object> singlePersonAccess(Map<String, Object> params) throws OssRenderException {

        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        if (params.get("personId") == null || StringUtils.isEmpty(params.get("personId").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "查询personId不能为空");
        }

        Map resultMap = new HashMap();
        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        String personCode = params.get("personId").toString();
        List<Map<String, Object>> lists = personInoutMapper.singlePersonAccessByCode(personCode);

        if (lists.size() > 0 && null != lists) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {

                //处理图片 拼接地址
                if (list.get("image") != null && !StringUtils.isEmpty(list.get("image").toString())) {
                    list.put("image", PropertiesUtil.getLocalTomcatImageIp() + list.get("image"));
                }
            }

            resultMap.put("list", lists);
            resultMap.put("totalRows", paginator.getTotal());
            resultMap.put("currentPage", start);
        } else {
            resultMap.put("totalRows", 0);
            resultMap.put("list", new ArrayList());
            resultMap.put("currentPage", start);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> singlePersonAccessByES(Map<String, Object> params) throws OssRenderException {
        //建立查询
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();

        //小区查询
        TermsQueryBuilder communityCodeQueryBuilder=null;
        if(params.get("xqCode")!=null && !StringUtils.isEmpty(params.get("xqCode").toString()))
        {
            communityCodeQueryBuilder=new TermsQueryBuilder("communityCode",(List)params.get("xqCode"));
        }

        //personId 查询
        MatchPhraseQueryBuilder personIdQueryBuilder=null;
        if (params.get("personId")!=null && StringUtils.isNotEmpty(params.get("personId").toString())){
            personIdQueryBuilder=new MatchPhraseQueryBuilder("personCode",params.get("personId"));
        }else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "查询personId不能为空");
        }

        //限制查询今日人员出入
        //获得今日凌晨时间
       String todayStartTime= TimesUtil.getTodayStartTime();

        RangeQueryBuilder todayQueryBuilder=new RangeQueryBuilder("captureTime").gt(todayStartTime);

        //以and形式 将查询条件汇总
        BoolQueryBuilder finalQueryBuilder = new BoolQueryBuilder();

        //小区查询
        if (communityCodeQueryBuilder != null)
            finalQueryBuilder.must(communityCodeQueryBuilder);

        //时间范围查询
        finalQueryBuilder.must(todayQueryBuilder);

        if (personIdQueryBuilder!=null){
            finalQueryBuilder.must(personIdQueryBuilder);
        }

        searchSourceBuilder.query(finalQueryBuilder);
        //排序
        searchSourceBuilder.sort("captureTime", SortOrder.DESC);

        //分页
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        int start = (page - 1) * limit;
        searchSourceBuilder.from(start);
        searchSourceBuilder.size(limit);
        //返回查询结果
        SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new", "person_inout", searchSourceBuilder);
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
            newMap.put("image", sourceAsMap.get("capImage"));
            newMap.put("capAddress", sourceAsMap.get("deviceName"));

            if (sourceAsMap.get("capImage") != null && !StringUtils.isEmpty(sourceAsMap.get("capImage").toString()))
                newMap.put("image", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("capImage"));
            else
                newMap.put("image", "");

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
