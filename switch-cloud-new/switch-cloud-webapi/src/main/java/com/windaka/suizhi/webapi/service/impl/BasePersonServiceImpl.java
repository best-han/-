package com.windaka.suizhi.webapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.PropertiesUtil;
import com.windaka.suizhi.webapi.dao.*;
import com.windaka.suizhi.webapi.dao.ext.ExtFaceAttentionMapper;
import com.windaka.suizhi.webapi.dao.ext.ExtFaceGroupDetailMapper;
import com.windaka.suizhi.webapi.service.BasePersonService;
import com.windaka.suizhi.webapi.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BasePersonServiceImpl implements BasePersonService {
    @Autowired
    private BasePersonMapper basePersonMapper;
    @Autowired
    private CapturePersonMapper capturePersonMapper;
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private BaseRoomDao baseRoomDao;
    @Autowired
    private ExtFaceGroupDetailMapper faceGroupDetailMapper;
    @Autowired
    private SysDicDataMapper sysDicDataMapper;
    @Autowired
    private BaseCarDao baseCarDao;
    @Autowired
    private PersonInoutMapper personInoutMapper;
    @Autowired
    private CarAccessMapper carAccessMapper;
    @Autowired
    private CaptureCarMapper captureCarMapper;
    @Autowired
    private ExtFaceGroupDetailMapper extFaceGroupDetailMapper;
    @Autowired
    private BigDataDao bigDataDao;
    @Autowired
    private ExtFaceAttentionMapper extFaceAttentionMapper;

    @Override
    public Map<String, Object> personBaseInfo(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> map = new HashMap<>();
        Map innerParams;

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        map.put("personTotalNum", basePersonMapper.queryPersonNum(innerParams));//人口总数

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("timeType", "month");
        map.put("monthSenseNum", capturePersonMapper.SensePersonNum(innerParams));//本月感知人数

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("timeType", "day");
        map.put("todaySenseNum", capturePersonMapper.SensePersonNum(innerParams));//今日感知人数

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("timeType", "day");
        innerParams.put("personType", "1");//0正常人 1陌生人
        map.put("todaySenseStrangerNum", capturePersonMapper.SensePersonNum(innerParams));//今日感知陌生人数量

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("liveType", "permanent");
        map.put("permanentPersonNum", basePersonMapper.queryPersonNum(innerParams));//常住人口数量

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("liveType", "float");
        map.put("permanentPersonNum", basePersonMapper.queryPersonNum(innerParams));//流动人口数量

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("timeType", "month");//本月
        innerParams.put("liveType", "float");//流动人口
        map.put("monthAddFloatPersonNum", basePersonMapper.queryPersonNum(innerParams));//本月新增流动人口数量

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));
        innerParams.put("foreigner", "true");
        map.put("foreignerPersonNum", basePersonMapper.queryPersonNum(innerParams));//涉外人员数量

        return map;
    }

    @Override
    public List<Map<String, Object>> personDistribute(Map<String, Object> params) throws OssRenderException {

        List lists = new ArrayList();

        Map innerParams;
        innerParams = new HashMap();

        //各小区人数总和
        innerParams.put("xqCode", params.get("xqCode"));
        int personTotalNum = basePersonMapper.queryPersonNum(innerParams);

        //小区列表
        List<Map<String, Object>> xqLists = baseCommunityMapper.communityList(innerParams);

        //循环查询各小区信息、人数、占比
        for (Map<String, Object> xq : xqLists) {

            Map<String, Object> map = new HashMap<>();

            String xqCode = xq.get("code").toString();
            String xqName = xq.get("name").toString();
            innerParams.put("xqCode", xqCode);
            int personNum = basePersonMapper.queryPersonNum(innerParams);
            if (personTotalNum == 0) {
                personTotalNum = 1;
            }
            double percent = 100 * (personNum * 1.0 / personTotalNum);

            map.put("xqCode", xqCode);//小区编码
            map.put("xqName", xqName);//小区名
            map.put("personNum", personNum);//人员数
            map.put("percent", percent);//占比

            lists.add(map);

        }
        return lists;
    }

    @Override
    public Map<String, Object> personList(Map<String, Object> params) throws OssRenderException {

        if (params.get("page") == null || StringUtils.isEmpty(params.get("page").toString())) {
            params.put("page", 1);
        }
        if (params.get("limit") == null || StringUtils.isEmpty(params.get("limit").toString())) {
            params.put("limit", 10);
        }

        if (params.get("color") != null && !StringUtils.isEmpty(params.get("color").toString())) {
            Map map = new HashMap();
            String color = params.get("color").toString();
            if (!color.equals("green")) {
                map.put("dicCode", "face_group_detail|level");
                map.put("dicValue", color);
                String level = sysDicDataMapper.getDictKey(map);//根据dicCode 和颜色 查询布控库等级
                params.put("level", level);
            }
        }

        int start = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(start, limit);//分页

        List<Map<String, Object>> lists = basePersonMapper.personList(params);

        Map<String, Object> resultMap = new HashMap<>();
        if (null != lists && lists.size() > 0) {

            PageInfo paginator = new PageInfo(lists);

            lists = paginator.getList();
            for (Map<String, Object> list : lists) {

                //添加房屋名称
                String houseName = "";
                if (list.get("roomCode") != null && !StringUtils.isEmpty(list.get("roomCode").toString())) {
                    String roomCode = list.get("roomCode").toString();
                    houseName = baseRoomDao.queryHouseNameByRoomCode(roomCode);
                    list.put("houseName", houseName);
                } else {
                    list.put("houseName", "/");
                }

                //是否申请居住证
                if (list.get("type") != null && !StringUtils.isEmpty(list.get("type").toString())) {
                    int type = Integer.parseInt(list.get("type").toString());
                    if (type == 1 || type == 2) {
                        list.put("residence", "/");
                    }
                    if (type == 3) {
                        if (list.get("residence") != null && !StringUtils.isEmpty(list.get("residence").toString())) {
                            int re = Integer.parseInt(list.get("residence").toString());
                            if (re == 0) {
                                list.put("residence", "未申请");
                            } else {
                                list.put("residence", "已申请");
                            }
                        } else {
                            list.put("residence", "未申请");//residence 为空  默认未申请
                        }
                    }
                }

                //拼接图片地址
                if (list.get("image") != null && !StringUtils.isEmpty(list.get("image").toString())) {
                    String img = list.get("image").toString();
                    String ip = PropertiesUtil.getLocalTomcatImageIp();
                    img = ip + img;
                    list.put("image", img);
                }

                //默认中国人民共和国
                if (list.get("countryName") == null || StringUtils.isEmpty(list.get("countryName").toString())) {
                    list.put("countryName", "中华人民共和国");
                }

                //找到实有人口的重点关注对象，对其打上相应标签
                String dicValue = "green";//默认为绿色
                list.put("color", dicValue);
                if (list.get("faceGroupDetailId") != null && !StringUtils.isEmpty(list.get("faceGroupDetailId").toString())) {
                    String id = list.get("faceGroupDetailId").toString();
                    String level = faceGroupDetailMapper.selectGroupLevel(id);//查找布控明细表中 此人的布控等级

                    Map innerParams = new HashMap();
                    innerParams.put("dicCode", "face_group_detail|level");//根据布控标签和key 查询value
                    innerParams.put("dicKey", level);
                    dicValue = sysDicDataMapper.getDictValue(innerParams);
                    list.put("color", dicValue);
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
    public Map<String, Object> querySinglePerson(Map<String, Object> params) throws OssRenderException {
        List<Map<String, Object>> personList = basePersonMapper.querySinglePerson2(params);
        if (personList.size() > 0) {
            Map<String, Object> singlePerson = personList.get(0);
            singlePerson.remove("roomCode");
            singlePerson.remove("relation");
            singlePerson.remove("checkinTime");
            singlePerson.remove("typeName");
            if (singlePerson.get("image") != null && !StringUtils.isEmpty(singlePerson.get("image").toString())) {
                singlePerson.put("image", PropertiesUtil.getLocalTomcatImageIp() + singlePerson.get("image").toString());
            }

            //是否申请居住证
            if (singlePerson.get("type") != null && !StringUtils.isEmpty(singlePerson.get("type").toString())) {
                int type = Integer.parseInt(singlePerson.get("type").toString());
                if (type == 1 || type == 2) {
                    singlePerson.put("residence", "/");
                }
                if (type == 3) {
                    if (singlePerson.get("residence") != null && !StringUtils.isEmpty(singlePerson.get("residence").toString())) {
                        int re = Integer.parseInt(singlePerson.get("residence").toString());
                        if (re == 0) {
                            singlePerson.put("residence", "是");
                        } else {
                            singlePerson.put("residence", "否");
                        }
                    } else {
                        singlePerson.put("residence", "否");//residence 为空  默认未申请
                    }
                }
            }

            return singlePerson;
        }
        return new HashMap<>();
    }

    @Override
    public List queryFaceGroupLabel(Map<String, Object> params) throws OssRenderException {
        List<Map<String, Object>> faceGroupLabels = basePersonMapper.queryFaceGroupLabel(params);
        return faceGroupLabels;
    }

    @Override
    public List queryAbnormalLabel(Map<String, Object> params) throws OssRenderException {
//        List<Map<String, Object>> abnormalLabels = basePersonMapper.queryAbnormalLabel(params);

        params.put("xqCode", new ArrayList());

        List abnormalLabels = new ArrayList();

        //判断是否为布控人员
        List<Map<String, Object>> faceGroupDetails = faceGroupDetailMapper.selectListByCode(params);
        if (null != faceGroupDetails && faceGroupDetails.size() > 0) {
            for (Map<String, Object> faceGroupDetail : faceGroupDetails) {
                abnormalLabels.add(faceGroupDetail.get("group_name"));
            }
        }

        //判断是否为疑似迁出人
        params.put("ownerId", params.get("personId"));
        List suspectQuit = capturePersonMapper.quitPersonListSingleByCode(params);
        if (null != suspectQuit && suspectQuit.size() > 0) {
            abnormalLabels.add("疑似迁出人");
        }


        //判断是否为频繁夜归人员
        int total = bigDataDao.totalGetNightReturnPersonByCode(params);
        if (total > 0) {
            abnormalLabels.add("频繁夜归人员");
        }
        //判断是否为高频出入人员
        List<Map<String, Object>> personAccessHigh = personInoutMapper.personSenseHighAccess(params);
        if (null != personAccessHigh && personAccessHigh.size() > 0) {
            abnormalLabels.add("高频出入人员");
        }

        //判断是否为重点关注人员
        List<Map<String, Object>> attentionLists =extFaceAttentionMapper.selectListByCode(params);
        if (null != attentionLists && attentionLists.size() > 0) {
            for (Map<String, Object> attentionList : attentionLists) {
                abnormalLabels.add(attentionList.get("attention_name"));
            }
        }


//        List resultList=new LinkedList();
//        Map map;
//        for (int i=0;i<abnormalLabels.size();i++){
//            map=new HashMap();
//            map.put("abnormalTypeName",abnormalLabels.get(i));
//            resultList.add(map);
//        }

//        return resultList;

        return abnormalLabels;
    }

    @Override
    public Map<String, Object> queryHouseListByPersonCode(Map<String, Object> params) {
        List<Map<String, Object>> personList = basePersonMapper.querySinglePerson(params);
        Map<String, Object> resultMap = new HashMap<>();
        if (personList.size() > 0) {
            Map<String, Object> rootPerson = personList.get(0);
            //本人名称&身份证号
            resultMap.put("houseOwnerName", rootPerson.get("name"));
            resultMap.put("houseOwnerPaperNum", rootPerson.get("paperNumber"));
            Iterator<Map<String, Object>> pli = personList.iterator();
            List<Map<String, Object>> houseList = new LinkedList<>();
            while (pli.hasNext()) {
                //遍历有关系的房屋
                Map<String, Object> plt = pli.next();
                Map<String, Object> newPlt = new HashMap<>();
                newPlt.put("personType", plt.get("personIdentityName"));
                newPlt.put("houseRelation", plt.get("relation"));
                newPlt.put("houseName", plt.get("address"));
                newPlt.put("personType", plt.get("personIdentityName"));
                newPlt.put("houseId", plt.get("roomCode"));
                houseList.add(newPlt);
            }
            resultMap.put("houses", houseList);
        }
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> queryCarListByPersonCode(Map<String, Object> params) {
        List<Map<String, Object>> carList = basePersonMapper.queryCarListByPersonCode(params);
        Iterator<Map<String, Object>> cli = carList.iterator();
        while (cli.hasNext()) {
            Map<String, Object> clt = cli.next();
            clt.remove("xqCode");
        }
        return carList;
    }

    @Override
    public Map<String, Object> queryCaptureTimesByPersonCode(Map<String, Object> params) throws OssRenderException {
        String todayYMDH = DateUtil.getTodayStartTime();
        String[] ts = todayYMDH.split("-");
        int y = Integer.parseInt(ts[0]);
        int m = Integer.parseInt(ts[1]);
        if (params.get("month") != null && !params.get("month").toString().trim().equals(""))
            m = Integer.parseInt(params.get("month").toString());
        if (params.get("year") != null && !params.get("year").toString().trim().equals(""))
            y = Integer.parseInt(params.get("year").toString());

        String personId = params.get("personId").toString();

        if (m < 1 || m > 12) {
            throw new OssRenderException("月份非法！");
        }
        int days = DateUtil.getDaysByYearMonth(y, m);
        System.out.println(days);

        int[] capMonth = new int[days];//存放本月的抓拍记录
        String[] monthListParam = new String[days];//存放本月日期
        String[] monthWeek = new String[days];//存放本月日期对应的星期

        String timeStart = DateUtil.getMonthStartTime2(y, m);
        System.out.println(timeStart);
        for (int i = 0; i < days; i++) {
            String si = (i + 1) + "";
            if (i + 1 < 10) {
                si = "0" + si;
            }
            monthListParam[i] = timeStart + "-" + si;
        }

        Map<String, Object> mapMonth = new HashMap<>();//查询本月出门记录
        for (int i = 0; i < days; i++) {
//            System.out.println(monthListParam[i]);
            mapMonth.put("monthDay", monthListParam[i]); // ...14 15 16 17 18
            mapMonth.put("personId", personId);
            capMonth[i] = basePersonMapper.queryCaptureTimesInOneDayByPersonCode(mapMonth);//本月天每天出门量
        }
        for (int i = 0; i < days; i++) {//得到一个月的 星期日历
            monthWeek[i] = DateUtil.getWeek(monthListParam[i]);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

        String[] monthList = new String[days];//日期格式化为 几月几日
        for (int i = 0; i < days; i++) {
            String[] date = monthListParam[i].split("-");
            monthList[i] = date[1] + "-" + date[2];
        }
        String[] weekList = new String[days]; // 星期日   -----》日
        for (int i = 0; i < days; i++) {
            String[] date = monthWeek[i].split("");
            weekList[i] = date[2];
        }


        Map<String, Object> map = new HashMap<>();
        Map<String, Object> record = new HashMap<>();
        record.put("monthDay", monthList);
        record.put("monthWeek", weekList);
        record.put("num", capMonth);


        map.put("year", y);
        map.put("month", m);
        map.put("record", record);
        return map;
    }

    @Override
    public Map<String, Object> queryRelationShipByPersonCode(Map<String, Object> params) throws OssRenderException {
        List<Map<String, Object>> personList = basePersonMapper.querySinglePerson(params);
        Map<String, Object> resultMap = new HashMap<>();
        if (personList.size() > 0) {
            Map<String, Object> rootPerson = personList.get(0);
            //查询此人关联的房屋（默认第一个）
            String roomCode = rootPerson.get("roomCode").toString();
            if (roomCode != null && !roomCode.trim().equals("")) {
                //找到房子
                String roomName = baseRoomDao.queryHouseNameByRoomCode(roomCode);
                if (roomName == null || roomName.trim().equals("")) {
                    //没有房子 则本人为关系中心
                    resultMap.put("personName", rootPerson.get("name"));
                    if (rootPerson.get("image") != null && !StringUtils.isEmpty(rootPerson.get("image").toString())) {
                        resultMap.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + rootPerson.get("image").toString());
                    } else {
                        resultMap.put("faceImage", "");
                    }
                    resultMap.put("friend", new LinkedList<>());
                } else {
                    //有房子 查找房主及成员
                    Map<String, Object> innerParam = new HashMap<>();
                    innerParam.put("roomCode", roomCode);
                    List<Map<String, Object>> memberList = basePersonMapper.querySinglePerson(innerParam);
                    int ownerFlag = 0;
                    Iterator<Map<String, Object>> mli = memberList.iterator();
                    List<Map<String, Object>> relationList = new LinkedList<>();
                    while (mli.hasNext()) {
                        Map<String, Object> mlt = mli.next();
                        if (mlt.get("personIdentityId") != null && !mlt.get("personIdentityId").toString().trim().equals("")) {
                            String type = mlt.get("personIdentityId").toString();
                            //1为业主 2为家庭成员 3为租户
                            if (type.equals("1")) {
                                //以业主为中心
                                resultMap.put("personName", mlt.get("name"));
                                if (mlt.get("image") != null && !StringUtils.isEmpty(mlt.get("image").toString())) {
                                    resultMap.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + mlt.get("image").toString());
                                } else {
                                    resultMap.put("faceImage", "");
                                }
                                ownerFlag = 1;
                                System.out.println(ownerFlag);
                            } else {
                                Map<String, Object> mltChild = new HashMap<>();
                                mltChild.put("personName", mlt.get("name"));
                                mltChild.put("relation", mlt.get("relation"));
                                if (mlt.get("image") != null && !StringUtils.isEmpty(mlt.get("image").toString())) {
                                    mltChild.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + mlt.get("image").toString());
                                } else {
                                    mltChild.put("faceImage", "");
                                }
                                mltChild.put("personId", mlt.get("personCode"));
                                Map<String, Object> innerParams = new HashMap<>();
                                innerParams.put("personId", mlt.get("personCode"));
                                innerParams.put("xqCode", mlt.get("xqCode"));
                                List<Map<String, Object>> faceGroupLabels = basePersonMapper.queryFaceGroupLabel(innerParams);
                                if (faceGroupLabels.size() > 0) {
                                    Map<String, Object> faceGroupLabel = faceGroupLabels.get(0);
                                    mltChild.put("faceTypeName", faceGroupLabel.get("faceTypeName"));
                                } else {
                                    mltChild.put("faceTypeName", "");
                                }
                                //mltChild.put("faceTypeName",mlt.get("image"));
                                relationList.add(mltChild);
                            }
                        }
                    }
                    if (ownerFlag == 0) {
                        //没有找到业主 则本人为关系中心
                        resultMap.put("personName", rootPerson.get("name"));
                        if (rootPerson.get("image") != null && !StringUtils.isEmpty(rootPerson.get("image").toString())) {
                            resultMap.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + rootPerson.get("image").toString());
                        } else {
                            resultMap.put("faceImage", "");
                        }
                        resultMap.put("friend", new LinkedList<>());
                    } else {
                        resultMap.put("friend", relationList);
                    }
                }
            } else {
                //没有房子 则本人为关系中心
                resultMap.put("personName", rootPerson.get("name"));
                if (rootPerson.get("image") != null && !StringUtils.isEmpty(rootPerson.get("image").toString())) {
                    resultMap.put("faceImage", PropertiesUtil.getLocalTomcatImageIp() + rootPerson.get("image").toString());
                } else {
                    resultMap.put("faceImage", "");
                }
                resultMap.put("friend", new LinkedList<>());
            }
        }
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> countryList(Map<String, Object> params) throws OssRenderException {
        List lists = basePersonMapper.countryList(params);
        if (lists.size() > 0 && null != lists) {
            return lists;
        } else {
            return new LinkedList<>();
        }
    }

    @Override
    public Map<String, Object> baseData(Map<String, Object> params) throws OssRenderException {
        Map<String, Object> resultMap = new HashMap();
        Map innerParams;

        innerParams = new HashMap();
        innerParams.put("xqCode", params.get("xqCode"));

        resultMap.put("houseNum", baseRoomDao.selectRoomNum(innerParams));//实有房屋

        resultMap.put("personNum", basePersonMapper.selectPersonNum(innerParams));//实有人口

        resultMap.put("carNum", baseCarDao.selectCarNum(innerParams));//实有车辆

        resultMap.put("personAccessNum", personInoutMapper.selectPersonAccessNum(innerParams));//今日人员出入

        resultMap.put("carAccessNum", carAccessMapper.selectCarAccessNum(innerParams));//今日车辆出入

        resultMap.put("personCapNum", capturePersonMapper.selectPersonCapNum(innerParams));//今日人脸抓拍

        resultMap.put("carCapNum", captureCarMapper.selectCarCapNum(innerParams));//今日车辆抓拍

        return resultMap;
    }


}
