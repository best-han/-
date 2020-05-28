package com.windaka.suizhi.webapi.service.impl;

import ch.qos.logback.core.util.TimeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.ElasticSearchUtil;
import com.windaka.suizhi.common.utils.PicUtil;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.dao.BasePersonMapper;
import com.windaka.suizhi.webapi.dao.CapturePersonMapper;
import com.windaka.suizhi.webapi.dao.MsgSocketIdMapper;
import com.windaka.suizhi.webapi.dao.SysDicDataMapper;
import com.windaka.suizhi.webapi.service.CapturePersonService;
import com.windaka.suizhi.webapi.util.DateUtil;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
@Slf4j
public class CapturePersonServiceImpl implements CapturePersonService {
    @Autowired
    private CapturePersonMapper capturePersonMapper;
    @Autowired
    private MsgSocketIdMapper msgSocketIdMapper;
    @Autowired
    private QueryImgByImgImpl queryImgByImg;
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private SysDicDataMapper sysDicDataMapper;

    @Override
    public List<Map<String, Object>> queryCapPersonList(Map<String, Object> params) throws OssRenderException {
        //        根据记录表查找 上一次查询的最大id
        int lastId = msgSocketIdMapper.queryLastIdByRecordName("capture_person");

        //未发送的 抓拍人员列表
        List<Map<String, Object>> lists = capturePersonMapper.queryCapPersonList(lastId);
        if (lists.size() > 0) {

            Iterator i = lists.iterator();
            while (i.hasNext()) {
                Map<String, Object> t = (Map<String, Object>) i.next();


                //处理图片 拼接地址
                if (t.get("faceImage") != null && !StringUtils.isEmpty(t.get("faceImage").toString())) {

                    t.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + t.get("faceImage"));

                }
                if (t.get("bodyImage") != null && !StringUtils.isEmpty(t.get("bodyImage").toString())) {

                    t.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + t.get("bodyImage"));

                }
                if (null == t.get("name") || StringUtils.isEmpty(t.get("name").toString())) {

                    t.put("name", "陌生人");

                }
                t.put("webStatus", "r");//推送标志
                t.put("msgType", "1");
            }

//            更新id记录表
            Map<String, Object> lastRecord = lists.get(lists.size() - 1);//获取最后一条id (max)
            lastId = (Integer) lastRecord.get("id");
            Map<String, Object> innerParam = new HashMap<>();
            innerParam.put("recordName", "capture_person");
            innerParam.put("maxId", lastId);
            msgSocketIdMapper.updateLastIdByRecordName(innerParam);
        }

        return lists;
    }

    @Override
    public Map<String, Object> personStrangerList(Map<String, Object> params) throws OssRenderException {

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

        List<Map<String, Object>> lists = capturePersonMapper.personStrangerList(params);

        if (lists.size() > 0 && null != lists) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {
                String id = list.get("id").toString();
                Map person = capturePersonMapper.selectCapInfoById(id).get(0);
                list.put("xqCode", person.get("community_code"));
                list.put("xqName", person.get("community_name"));
                list.put("sex", person.get("cap_sex"));
                list.put("age", person.get("cap_age"));
                if (person.get("cap_image") != null && !StringUtils.isEmpty(person.get("cap_image").toString())) {
                    String img = person.get("cap_image").toString();
                    String ip = PropertiesUtil.getLocalTomcatImageIp();
                    img = ip + img;
                    list.put("image", img);
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
    public Map<String, Object> singleCapPersonList(Map<String, Object> params) throws OssRenderException {

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

        List<Map<String, Object>> lists = capturePersonMapper.selectCapPersonByCode(params);
        if (lists.size() > 0 && null != lists) {
            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {
                if (list.get("faceImage") != null && !StringUtils.isEmpty(list.get("faceImage").toString())) {
                    list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("faceImage").toString());
                }
                if (list.get("bodyImage") != null && !StringUtils.isEmpty(list.get("bodyImage").toString())) {
                    list.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("bodyImage").toString());
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
    public Map<String, Object> singleCapPersonListByES(Map<String, Object> params) throws OssRenderException {
        //建立查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //小区查询
        TermsQueryBuilder communityCodeQueryBuilder = null;
        if (params.get("xqCode") != null && !StringUtils.isEmpty(params.get("xqCode").toString())) {
            communityCodeQueryBuilder = new TermsQueryBuilder("communityCode", (List) params.get("xqCode"));
        }

        //personId 查询
        MatchPhraseQueryBuilder personIdQueryBuilder = null;
        if (params.get("personId") != null && StringUtils.isNotEmpty(params.get("personId").toString())) {
            personIdQueryBuilder = new MatchPhraseQueryBuilder("personCode", params.get("personId"));
        } else {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "查询personId不能为空");
        }

        //某人某天抓拍记录
        RangeQueryBuilder capTimeQueryBuilder = new RangeQueryBuilder("captureTime");
        if (params.get("capTime") != null && StringUtils.isNotEmpty(params.get("capTime").toString())) {
            String capTime = params.get("capTime").toString();
            String capTimeStart = capTime + " 00:00:00";
            String capTimeEnd = capTime + " 23:59:59";
            capTimeQueryBuilder.gte(capTimeStart);
            capTimeQueryBuilder.lte(capTimeEnd);
        }

        //疑似新增人员抓拍记录 15天内的抓拍记录
        RangeQueryBuilder suspectAddQueryBuilder = new RangeQueryBuilder("captureTime");
        if (params.get("suspectAdd") != null && StringUtils.isNotEmpty(params.get("suspectAdd").toString())) {
            String dayTime15 = TimesUtil.getPastDate(15);
            suspectAddQueryBuilder.gt(dayTime15);
        }


        //以and形式 将查询条件汇总
        BoolQueryBuilder finalQueryBuilder = new BoolQueryBuilder();

        //小区查询
        if (communityCodeQueryBuilder != null)
            finalQueryBuilder.must(communityCodeQueryBuilder);


        if (personIdQueryBuilder != null) {
            finalQueryBuilder.must(personIdQueryBuilder);
        }
        //疑似新增人员
        finalQueryBuilder.must(suspectAddQueryBuilder);

        //某天抓拍记录
        finalQueryBuilder.must(capTimeQueryBuilder);

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
        SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new2", "capture_person", searchSourceBuilder);
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
            newMap.put("faceImage", sourceAsMap.get("capPersonImage"));
            newMap.put("bodyImage", sourceAsMap.get("capImage"));
            newMap.put("capAddress", sourceAsMap.get("deviceName"));

            if (sourceAsMap.get("capImage") != null && !StringUtils.isEmpty(sourceAsMap.get("capImage").toString()))
                newMap.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("capImage"));
            else
                newMap.put("bodyImage", "");
            if (sourceAsMap.get("capPersonImage") != null && !StringUtils.isEmpty(sourceAsMap.get("capPersonImage").toString()))
                newMap.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("capPersonImage"));
            else
                newMap.put("faceImage", "");

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
    public Map<String, Object> personAddedList(Map<String, Object> params) throws OssRenderException {

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

        List<Map<String, Object>> lists = capturePersonMapper.selectPersonAddList(params);

        if (lists.size() > 0 && null != lists) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {

                if (list.get("sex") != null && !StringUtils.isEmpty(list.get("sex").toString())) {
                    String sex = list.get("sex").toString();
                    if (sex.equals("1")) {
                        list.put("sex", "男");
                    }
                    if (sex.equals("2")) {
                        list.put("sex", "女");
                    }
                }


                if (list.get("faceImage") != null && !StringUtils.isEmpty(list.get("faceImage").toString())) {
                    list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("faceImage").toString());
                }
                if (list.get("bodyImage") != null && !StringUtils.isEmpty(list.get("bodyImage").toString())) {
                    list.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("bodyImage").toString());
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
    public Map<String, Object> personCaptureList(Map<String, Object> params) throws OssRenderException {
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }


        //以图搜图
        if (params.get("image") != null && StringUtils.isNotEmpty(params.get("image").toString())) {

            Map<String, Object> innerParams;

            //访问算法服务 传入图像base64 返回 personCode （算法端personCode为List--->返回String）
            List personCode = new ArrayList();

            innerParams = new HashMap<>();
            innerParams.put("face_image", params.get("image"));//传入图像base64

            try {
                Map<String, Object> personInfo = queryImgByImg.queryPersonCodeByImg(innerParams);//返回 personCode,type(0 未识别  1正常  2异常)

                if (personInfo.get("type").equals("1")) {
                    String personCodeStr = personInfo.get("person_code").toString();
                    String[] personCodes = personCodeStr.split(",");
                    personCode = Arrays.asList(personCodes);//string -->list
                    params.put("personCode", personCode);
                } else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "图像未识别或算法未能识别图像特征");
                }
            } catch (Exception e) {
                log.error("[CapturePersonServiceImpl .personCaptureListByImg,链接算法服务异常,异常信息{}]");
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "图像未识别或算法未能识别图像特征");
            }
        }


        Map resultMap = new HashMap();
        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        List<Map<String, Object>> lists = capturePersonMapper.personCaptureList(params);

        if (lists.size() > 0 && null != lists) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            Iterator<Map<String, Object>> li = lists.iterator();
            while (li.hasNext()) {
                Map<String, Object> lt = li.next();

                //处理图片 拼接地址
                if (lt.get("faceImage") != null && !StringUtils.isEmpty(lt.get("faceImage").toString())) {

                    lt.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + lt.get("faceImage"));

                }
                if (lt.get("bodyImage") != null && !StringUtils.isEmpty(lt.get("bodyImage").toString())) {

                    lt.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + lt.get("bodyImage"));

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
    public Map<String, Object> personCaptureListByES(Map<String, Object> params) throws OssRenderException {

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

        //姓名查询
        MatchPhraseQueryBuilder personNameQueryBuilder = null;
        if (params.get("name") != null && StringUtils.isNotEmpty(params.get("name").toString())) {
            personNameQueryBuilder = new MatchPhraseQueryBuilder("personName", params.get("name"));
        }

        //性别查询
        TermQueryBuilder personSexQueryBuilder = null;
        if (params.get("sex") != null && StringUtils.isNotEmpty(params.get("sex").toString())) {
            personSexQueryBuilder = new TermQueryBuilder("capSex", params.get("sex"));
        }

        //bmask
        TermQueryBuilder bmaskQueryBuilder = null;
        if (params.get("bmask") != null && StringUtils.isNotEmpty(params.get("bmask").toString())) {
            bmaskQueryBuilder = new TermQueryBuilder("capBmask", params.get("bmask"));
        }

        //eyeglass
        TermQueryBuilder eyeglassQueryBuilder = null;
        if (params.get("eyeglass") != null && StringUtils.isNotEmpty(params.get("eyeglass").toString())) {
            eyeglassQueryBuilder = new TermQueryBuilder("capEyeglass", params.get("eyeglass"));
        }

        //age
        MatchPhraseQueryBuilder ageQueryBuilder = null;
        if (params.get("age") != null && StringUtils.isNotEmpty(params.get("age").toString())) {
            String age = params.get("age").toString();
            String capAge = null;
            if (age.equals("0")) {
                capAge = "未成年";
            } else if (age.equals("1")) {
                capAge = "青年人";

            } else if (age.equals("2")) {
                capAge = "中年人";

            } else if (age.equals("3")) {
                capAge = "老年人";

            }
            ageQueryBuilder = new MatchPhraseQueryBuilder("capAge", capAge);
        }

        //knapsack
        TermQueryBuilder knapsackQueryBuilder = null;
        if (params.get("knapsack") != null && StringUtils.isNotEmpty(params.get("knapsack").toString())) {
            knapsackQueryBuilder = new TermQueryBuilder("capKnapsack", params.get("knapsack"));
        }

        //hat
        TermQueryBuilder hatQueryBuilder = null;
        if (params.get("hat") != null && StringUtils.isNotEmpty(params.get("hat").toString())) {
            hatQueryBuilder = new TermQueryBuilder("capHat", params.get("hat"));
        }

        //handbag
        TermQueryBuilder handbagQueryBuilder = null;
        if (params.get("handbag") != null && StringUtils.isNotEmpty(params.get("handbag").toString())) {
            handbagQueryBuilder = new TermQueryBuilder("capHandbag", params.get("handbag"));
        }

        //coatType
        TermQueryBuilder coatTypeQueryBuilder = null;
        if (params.get("coatType") != null && StringUtils.isNotEmpty(params.get("coatType").toString())) {
            coatTypeQueryBuilder = new TermQueryBuilder("capCoatType", params.get("coatType"));
        }

        //coatColor
        TermQueryBuilder coatColorQueryBuilder = null;
        if (params.get("coatColor") != null && StringUtils.isNotEmpty(params.get("coatColor").toString())) {
            coatColorQueryBuilder = new TermQueryBuilder("capCoatColor", params.get("coatColor"));
        }

        //trousersType
        TermQueryBuilder trousersTypeQueryBuilder = null;
        if (params.get("trousersType") != null && StringUtils.isNotEmpty(params.get("trousersType").toString())) {
            trousersTypeQueryBuilder = new TermQueryBuilder("capTrousersType", params.get("trousersType"));
        }

        //trousersColor
        TermQueryBuilder trousersColorQueryBuilder = null;
        if (params.get("trousersColor") != null && StringUtils.isNotEmpty(params.get("trousersColor").toString())) {
            trousersColorQueryBuilder = new TermQueryBuilder("capTrousersColor", params.get("trousersColor"));
        }

        //以and形式 将查询条件汇总
        BoolQueryBuilder finalQueryBuilder = new BoolQueryBuilder();

        //小区查询
        if (communityCodeQueryBuilder != null)
            finalQueryBuilder.must(communityCodeQueryBuilder);

        //时间范围查询
        finalQueryBuilder.must(captureTimeRangeQueryBuilder);

        if (personNameQueryBuilder != null)
            finalQueryBuilder.must(personNameQueryBuilder);

        if (handbagQueryBuilder != null)
            finalQueryBuilder.must(handbagQueryBuilder);

        if (coatTypeQueryBuilder != null)
            finalQueryBuilder.must(coatTypeQueryBuilder);

        if (personSexQueryBuilder != null)
            finalQueryBuilder.must(personSexQueryBuilder);

        if (bmaskQueryBuilder != null)
            finalQueryBuilder.must(bmaskQueryBuilder);

        if (eyeglassQueryBuilder != null)
            finalQueryBuilder.must(eyeglassQueryBuilder);

        if (ageQueryBuilder != null)
            finalQueryBuilder.must(ageQueryBuilder);

        if (knapsackQueryBuilder != null)
            finalQueryBuilder.must(knapsackQueryBuilder);

        if (knapsackQueryBuilder != null)
            finalQueryBuilder.must(knapsackQueryBuilder);
        if (hatQueryBuilder != null)
            finalQueryBuilder.must(hatQueryBuilder);

        if (coatColorQueryBuilder != null)
            finalQueryBuilder.must(coatColorQueryBuilder);

        if (trousersTypeQueryBuilder != null)
            finalQueryBuilder.must(trousersTypeQueryBuilder);

        if (trousersColorQueryBuilder != null)
            finalQueryBuilder.must(trousersColorQueryBuilder);

        //以图搜图
        //访问算法服务 传入图像base64 返回 personCode （算法端personCode为List--->返回String）
        if (params.get("image") != null && StringUtils.isNotEmpty(params.get("image").toString())) {

            Map<String, Object> innerParams;

            innerParams = new HashMap<>();
            innerParams.put("face_image", params.get("image"));//传入图像base64

            try {
                Map<String, Object> personInfo = queryImgByImg.queryPersonCodeByImg(innerParams);//返回 personCode,type(0 未识别  1正常  2异常)
                List personCode = new ArrayList();
                if (personInfo.get("type").equals("1")) {
                    String personCodeStr = personInfo.get("person_code").toString();
                    String[] personCodes = personCodeStr.split(",");
                    personCode = Arrays.asList(personCodes);//string -->list
                    params.put("personCode", personCode);
                } else {
                    throw new OssRenderException(ReturnConstants.CODE_FAILED, "图像未识别或算法未能识别图像特征");
                }
            } catch (Exception e) {
                log.error("[CapturePersonServiceImpl .personCaptureListByImg,链接算法服务异常,异常信息{}]");
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "图像未识别或算法未能识别图像特征");
            }
        }

        //点击查询
        if (params.get("id") != null && StringUtils.isNotEmpty(params.get("id").toString())) {
            String id = params.get("id").toString();
            List personCode = capturePersonMapper.selectPersonCodeById(id);
            params.put("personCode", personCode);
        }

        //根据personCode 查询抓拍人员
        TermsQueryBuilder personCodeQueryBuilder = null;
        if (params.get("personCode") != null && !StringUtils.isEmpty(params.get("personCode").toString())) {
            personCodeQueryBuilder = new TermsQueryBuilder("personCode", (List) params.get("personCode"));
        }
        if (personCodeQueryBuilder != null)
            finalQueryBuilder.must(personCodeQueryBuilder);


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
        SearchResponse searchResponse = ElasticSearchUtil.select("switch_cloud_new2", "capture_person", searchSourceBuilder);
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
            newMap.put("faceImage", sourceAsMap.get("capPersonImage"));
            newMap.put("bodyImage", sourceAsMap.get("capImage"));
            newMap.put("capAddress", sourceAsMap.get("deviceName"));
            newMap.put("xqCode", sourceAsMap.get("communityCode"));
            newMap.put("xqName", sourceAsMap.get("communityName"));
            newMap.put("deviceLocation", sourceAsMap.get("deviceLocation"));
            newMap.put("personId", sourceAsMap.get("personCode"));
            newMap.put("name", sourceAsMap.get("personName"));

            if (sourceAsMap.get("capPersonImage") != null && !StringUtils.isEmpty(sourceAsMap.get("capPersonImage").toString()))
                newMap.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("capPersonImage"));
            else
                newMap.put("faceImage", "");
            if (sourceAsMap.get("capImage") != null && !StringUtils.isEmpty(sourceAsMap.get("capImage").toString()))
                newMap.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + sourceAsMap.get("capImage"));
            else
                newMap.put("bodyImage", "");

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
    public Map<String, Object> personCaptureListById(Map<String, Object> params) throws OssRenderException {

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

        List<Map<String, Object>> lists = capturePersonMapper.selectCapPersonById(params);
        if (lists.size() > 0 && null != lists) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {

                //处理图片 拼接地址
                if (list.get("faceImage") != null && !StringUtils.isEmpty(list.get("faceImage").toString())) {
                    list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("faceImage").toString());
                }
                if (list.get("bodyImage") != null && !StringUtils.isEmpty(list.get("bodyImage").toString())) {
                    list.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("bodyImage").toString());
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
    public Map<String, Object> personCaptureListByImg(Map<String, Object> params) throws OssRenderException {


        //图像不能为空
        if (null == params.get("image") || StringUtils.isEmpty(params.get("image").toString())) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "图像不能为空");
        }
        Map<String, Object> innerParams;

        //访问算法服务 传入图像base64 返回 personCode （算法端personCode为List--->返回String）
        List personCode = new ArrayList();
//        personCode.add("99_zdq629");

        innerParams = new HashMap<>();
        innerParams.put("face_image", params.get("image"));//传入图像base64

        try {
            Map<String, Object> personInfo = queryImgByImg.queryPersonCodeByImg(innerParams);//返回 personCode,type(0 未识别  1正常  2异常)
//            Map<String, Object> personInfo = new HashMap<>();//test
//           personInfo.put("type","1");
//            personInfo.put("person_code","[99_zdq629,99_80530]");
            if (personInfo.get("type").equals("1")) {
                String personCodeStr = personInfo.get("person_code").toString();
//                personCodeStr = personCodeStr.substring(1, personCodeStr.length() - 1);
                String[] personCodes = personCodeStr.split(",");
                personCode = Arrays.asList(personCodes);//string -->list
            } else {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "图像未识别或算法未能识别图像特征");
            }
        } catch (Exception e) {
            log.error("[CapturePersonServiceImpl .personCaptureListByImg,链接算法服务异常,异常信息{}]");
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "图像未识别或算法未能识别图像特征");

        }

        //根据personCode （list） \xqCode(list) 查询相关人员列表
        innerParams = new HashMap<>();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("personCode", personCode);
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

        List<Map<String, Object>> lists = capturePersonMapper.selectCapPersonByCodes(innerParams);
        if (null != lists && lists.size() > 0) {

            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();

            for (Map<String, Object> list : lists) {

                //处理图片 拼接地址
                if (list.get("faceImage") != null && !StringUtils.isEmpty(list.get("faceImage").toString())) {
                    list.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("faceImage").toString());
                }
                if (list.get("bodyImage") != null && !StringUtils.isEmpty(list.get("bodyImage").toString())) {
                    list.put("bodyImage", PropertiesUtil.getLocalTomcatImageIp() + list.get("bodyImage").toString());
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
    public Map<String, Object> capPerson15Day(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> resultMap = new HashMap<>();
        List capDays = new LinkedList();//存放近15天日期

        List capNum = new LinkedList();//返回 存放每天抓拍次数
        List capDay = new LinkedList();//返回  存放15天日期

        capDays = DateUtil.getDays(15);//近15天日期
        for (int i = 0; i < capDays.size(); i++) {
            params.put("monthDay", capDays.get(i));
            int num = basePersonMapper.queryCaptureTimesInOneDayByPersonCode(params);
            capNum.add(num);//近15天每天抓拍次数
        }

//        for (int i=0;i<capDays.size();i++){//格式化日期 年-月-日  ---》 月-日
//            String[] date = capDays.get(i).toString().split("-");
//            capDay.add(date[1] + "-" + date[2]);
//        }


        resultMap.put("capDay", capDays);
        resultMap.put("capNum", capNum);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryQuitPersonList(Map<String, Object> params) throws OssRenderException {
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        if (params.get("liveType") != null && !StringUtils.isEmpty(params.get("liveType").toString())) {
            String liveType = params.get("liveType").toString();
            params.remove("liveType");
            Map<String, Object> innerParam = new HashMap<>();
            innerParam.put("dicCode", "base_person|type");
            innerParam.put("dicKey", liveType);
            String liveTypeName = sysDicDataMapper.getDictValue(innerParam);
            params.put("liveTypeName", liveTypeName);
        }

        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        List<Map<String, Object>> lists = capturePersonMapper.quitPersonList(params);

        Map<String, Object> resultMap = new HashMap<>();
        if (null != lists && lists.size() > 0) {
            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();
            Iterator<Map<String, Object>> li = lists.iterator();
            while (li.hasNext()) {
                Map<String, Object> lt = li.next();
                if (lt.get("image") != null && !StringUtils.isEmpty(lt.get("image").toString())) {
                    lt.put("image", PropertiesUtil.getLocalTomcatImageIp() + lt.get("image").toString());
                }
                if (lt.get("fullImage") != null && !StringUtils.isEmpty(lt.get("fullImage").toString())) {
                    lt.put("fullImage", PropertiesUtil.getLocalTomcatImageIp() + lt.get("fullImage").toString());
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
    public void ignoreQuitPerson(Map<String, Object> params, String username) throws OssRenderException {
        if(params.get("ownerId")!=null && !StringUtils.isEmpty(params.get("ownerId").toString()))
        {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "ownerId不能为空");
        }
        if(params.get("xqCode")!=null && !StringUtils.isEmpty(params.get("xqCode").toString()))
        {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "xqCode不能为空");
        }
        //获取待删除的迁出人员信息
        List<Map<String, Object>> quitPersonList = capturePersonMapper.quitPersonListSingle(params);
        if (quitPersonList.size() > 0) {
            Map<String, Object> toDelQuitPerson = quitPersonList.get(0);
            capturePersonMapper.deletePersonQuitList(params);
            Map<String, Object> innerParams;
            //是否已经存在过忽略定时记录
            int ignoreNum = capturePersonMapper.queryPersonQuitIgnoreNum(params);
            if (ignoreNum == 1) {
                innerParams = new HashMap<>();
                innerParams.put("ownerId", toDelQuitPerson.get("ownerId"));
                innerParams.put("xqCode", toDelQuitPerson.get("xqCode"));
                innerParams.put("ignoreTime", 15);
                capturePersonMapper.updatePersonQuitIgnoreList(innerParams);
            } else {
                innerParams = new HashMap<>();
                innerParams.put("ownerId", toDelQuitPerson.get("ownerId"));
                innerParams.put("xqCode", toDelQuitPerson.get("xqCode"));
                innerParams.put("ignoreTime", 15);
                capturePersonMapper.insertPersonQuitIgnoreList(innerParams);
            }
            //插入处理记录
            innerParams = new HashMap<>();
            innerParams.putAll(toDelQuitPerson);
            innerParams.put("handleContent", "已忽略");
            innerParams.put("handleUser", username);
            capturePersonMapper.insertPersonQuitHandleList(innerParams);
            //删除短信发送记录
            capturePersonMapper.deletePersonQuitMsg(innerParams);
        }
    }

    @Override
    public void deleteQuitPerson(Map<String, Object> params, String username) throws OssRenderException {
        if(params.get("ownerId")!=null && !StringUtils.isEmpty(params.get("ownerId").toString()))
        {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "ownerId不能为空");
        }
        if(params.get("xqCode")!=null && !StringUtils.isEmpty(params.get("xqCode").toString()))
        {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "xqCode不能为空");
        }
        //获取待删除的迁出人员信息
        List<Map<String, Object>> quitPersonList = capturePersonMapper.quitPersonListSingle(params);
        if (quitPersonList.size() > 0) {
            Map<String, Object> toDelQuitPerson = quitPersonList.get(0);
            capturePersonMapper.deletePersonQuitList(params);
            Map<String, Object> innerParams;
            //插入迁出列表
            innerParams = new HashMap<>();
            innerParams.putAll(toDelQuitPerson);
            innerParams.put("handleUser", username);
            capturePersonMapper.insertPersonQuitRecordList(innerParams);
            //插入处理记录
            innerParams = new HashMap<>();
            innerParams.putAll(toDelQuitPerson);
            innerParams.put("handleContent", "已迁出");
            innerParams.put("handleUser", username);
            capturePersonMapper.insertPersonQuitHandleList(innerParams);
            //删除短信发送记录
            capturePersonMapper.deletePersonQuitMsg(innerParams);
        }
    }

    @Override
    public Map<String, Object> queryQuitPersonHandleList(Map<String, Object> params) throws OssRenderException {
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        if (params.get("liveType") != null && !StringUtils.isEmpty(params.get("liveType").toString())) {
            String liveType = params.get("liveType").toString();
            params.remove("liveType");
            Map<String, Object> innerParam = new HashMap<>();
            innerParam.put("dicCode", "base_person|type");
            innerParam.put("dicKey", liveType);
            String liveTypeName = sysDicDataMapper.getDictValue(innerParam);
            params.put("liveTypeName", liveTypeName);
        }

        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        List<Map<String, Object>> lists = capturePersonMapper.quitPersonHandleList(params);

        Map<String, Object> resultMap = new HashMap<>();
        if (null != lists && lists.size() > 0) {
            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();
            Iterator<Map<String, Object>> li = lists.iterator();
            while (li.hasNext()) {
                Map<String, Object> lt = li.next();
                if (lt.get("image") != null && !StringUtils.isEmpty(lt.get("image").toString())) {
                    lt.put("image", PropertiesUtil.getLocalTomcatImageIp() + lt.get("image").toString());
                }
                if (lt.get("fullImage") != null && !StringUtils.isEmpty(lt.get("fullImage").toString())) {
                    lt.put("fullImage", PropertiesUtil.getLocalTomcatImageIp() + lt.get("fullImage").toString());
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
    public Map<String, Object> queryQuitPersonDeleteList(Map<String, Object> params) throws OssRenderException {
        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }
        if (params.get("liveType") != null && !StringUtils.isEmpty(params.get("liveType").toString())) {
            String liveType = params.get("liveType").toString();
            params.remove("liveType");
            Map<String, Object> innerParam = new HashMap<>();
            innerParam.put("dicCode", "base_person|type");
            innerParam.put("dicKey", liveType);
            String liveTypeName = sysDicDataMapper.getDictValue(innerParam);
            params.put("liveTypeName", liveTypeName);
        }

        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        List<Map<String, Object>> lists = capturePersonMapper.quitPersonDeleteList(params);

        Map<String, Object> resultMap = new HashMap<>();
        if (null != lists && lists.size() > 0) {
            PageInfo paginator = new PageInfo(lists);
            lists = paginator.getList();
            Iterator<Map<String, Object>> li = lists.iterator();
            while (li.hasNext()) {
                Map<String, Object> lt = li.next();
                if (lt.get("image") != null && !StringUtils.isEmpty(lt.get("image").toString())) {
                    lt.put("image", PropertiesUtil.getLocalTomcatImageIp() + lt.get("image").toString());
                }
                if (lt.get("fullImage") != null && !StringUtils.isEmpty(lt.get("fullImage").toString())) {
                    lt.put("fullImage", PropertiesUtil.getLocalTomcatImageIp() + lt.get("fullImage").toString());
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
}
