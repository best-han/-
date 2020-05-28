package com.windaka.suizhi.webapi.task;

import com.windaka.suizhi.webapi.dao.BasePersonMapper;
import com.windaka.suizhi.webapi.dao.CapturePersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class PersonQuitTask {

    @Autowired
    private CapturePersonMapper capturePersonMapper;

    @Autowired
    private BasePersonMapper basePersonMapper;

    public void executeInternal()
    {
        //旧记录 写入内存待对比
        //basePersonMapper.emptyPersonQuitList();
        List<Map<String,Object>> oldQuitPersonList= capturePersonMapper.quitPersonList(new HashMap<>());
        //查询疑似迁出人列表并插入展示表
        List<Map<String,Object>> quitPersonList= capturePersonMapper.personQuitListTask();
        //新记录 写入内存待对比
        List<Map<String,Object>> newQuitPersonList=new ArrayList<>();
        if(quitPersonList.size()>0){
            int i=0;
            for (Map<String, Object> bplt : quitPersonList) {
                Map<String, Object> innerParams;
                //ownerId,name,phone,image,lastCapTime,noSenceTime,xqCode,xqName
                int finalNoSenceTime = Integer.parseInt(bplt.get("noSenceTime").toString());
                if (bplt.get("ownerId") != null && !StringUtils.isEmpty(bplt.get("ownerId").toString())) {
                    String personCode = bplt.get("ownerId").toString();
                    String personXqCode = bplt.get("xqCode").toString();
                    //是否已经存在过忽略定时记录
                    int ifignore = 0;//初始为未忽略
                    innerParams = new HashMap<>();
                    innerParams.put("ownerId", bplt.get("ownerId"));
                    innerParams.put("xqCode", bplt.get("xqCode"));
                    List<Map<String, Object>> ignoreDetails = capturePersonMapper.queryPersonQuitIgnoreCondition(innerParams);
                    if (ignoreDetails.size() > 0) {
                        //存在忽略记录
                        Map<String, Object> ignoreDetail = ignoreDetails.get(0);
                        int lastDays = Integer.parseInt(ignoreDetail.get("lastDays").toString());
                        int ignoreTime = Integer.parseInt(ignoreDetail.get("ignoreTime").toString());
                        //如果自忽略处理时间起至今的时长 小于忽略时间 则不放开此记录
                        if (lastDays <= ignoreTime) {
                            ifignore = 1;
                        } else {
                            //否则删掉这个忽略定时记录
                            //basePersonMapper.deletePersonQuitIgnoreList();
                        }
                    }

                    //如果未忽略
                    if (ifignore == 0) {
                        //则判断是否已迁出
                        int ifquit = capturePersonMapper.queryPersonQuitRecordNum(innerParams);
                        if (ifquit == 0) {
                            //没有找到任何迁出处理记录，即未迁出
                            innerParams = new HashMap<>();
                            innerParams.put("personId", personCode);
                            //此人拥有的车辆 对于多辆车 离开天数取最小
                            List<Map<String, Object>> carList = basePersonMapper.queryCarListByPersonCode(innerParams);
                            int minNoSenceTimeCar = Integer.MAX_VALUE;
                            if (carList.size() > 0) {
                                //每辆车的离开天数
                                for (Map<String, Object> clt : carList) {
                                    String carNum = clt.get("carNum").toString();
                                    String carXqCode = clt.get("xqCode").toString();
                                    System.out.println(carNum);
                                    if (carXqCode.equals(personXqCode)) {
                                        innerParams = new HashMap<>();
                                        innerParams.put("carNum", carNum);
                                        innerParams.put("xqCode", carXqCode);
                                        List<Map<String, Object>> quitCarList = capturePersonMapper.personCarQuitListTask(innerParams);
                                        if (quitCarList.size() > 0) {
                                            Map<String, Object> qct = quitCarList.get(0);
                                            System.out.println(qct);
                                            int noSenceTimeCar = Integer.parseInt(qct.get("noSenceTimeCar").toString());
                                            if (noSenceTimeCar < minNoSenceTimeCar) {
                                                minNoSenceTimeCar = noSenceTimeCar;
                                            }
                                        }
                                    }
                                }
                                if (minNoSenceTimeCar < Integer.MAX_VALUE) {
                                    if (minNoSenceTimeCar < finalNoSenceTime) {
                                        finalNoSenceTime = minNoSenceTimeCar;
                                    }
                                }
                            }
                            i++;
                            if (finalNoSenceTime >= 15) {
                                Map<String, Object> insertParams = new HashMap<>();
                                //疑似迁出人查询记录->展示表
                                insertParams.put("id", i);
                                //xqCode->xqCode
                                insertParams.put("xqCode", bplt.get("xqCode"));
                                //xqName->xqName
                                insertParams.put("xqName", bplt.get("xqName"));
                                //ownerId->ownerId
                                insertParams.put("ownerId", bplt.get("ownerId"));
                                //name->name
                                insertParams.put("name", bplt.get("name"));
                                //phone->phone
                                insertParams.put("phone", bplt.get("phone"));
                                //finalNoSenceTime->noSenceTime
                                insertParams.put("noSenceTime", finalNoSenceTime);
                                //lastCapTime->lastCapTime
                                insertParams.put("lastCapTime", bplt.get("lastCapTime"));
                                //image->image
                                insertParams.put("image", bplt.get("image"));
                                //fullImage->fullImage
                                insertParams.put("fullImage", bplt.get("fullImage"));
                                //checkinDate->checkinDate
                                insertParams.put("checkinDate", bplt.get("checkinDate"));
                                //liveTypeName->liveTypeName
                                insertParams.put("liveTypeName", bplt.get("liveTypeName"));
                                //houseName->houseName
                                insertParams.put("houseName", bplt.get("houseName"));
                                //paperNumber->paperNumber
                                insertParams.put("paperNumber", bplt.get("paperNumber"));
                                //插入
                                newQuitPersonList.add(insertParams);
                            }
                        }
                    }
                }
            }
        }
        //判断是否有旧记录(内存)存在 但新记录(内存)不存在的人 即未抓拍超过15天又回来的人
        for (Map<String, Object> oldT : oldQuitPersonList) {
            String ownerIdOld = oldT.get("ownerId").toString();
            String xqCodeOld = oldT.get("xqCode").toString();
            int f = 0;
            for (Map<String, Object> newT : newQuitPersonList) {
                String ownerIdNew = newT.get("ownerId").toString();
                String xqCodeNew = newT.get("xqCode").toString();
                if (ownerIdNew.equals(ownerIdOld) && xqCodeNew.equals(xqCodeOld)) {
                    f = 1;
                    break;
                }
            }
            if (f == 0) {
                //若新记录没有这个人 则删除这个人的短信发送状态
                Map<String, Object> msgParams = new HashMap<>();
                msgParams.put("xqCode", xqCodeOld);
                msgParams.put("ownerId", ownerIdOld);
                capturePersonMapper.deletePersonQuitMsg(msgParams);
            }
        }
        //清空数据库旧记录
        capturePersonMapper.emptyPersonQuitList();
        //清空警务通推送记录
        capturePersonMapper.emptySuspectedPersonQuitApp();
        //更新警务通推送记录 短信发送记录 与数据库表中的记录
        for (Map<String, Object> newT : newQuitPersonList) {
            String ownerIdNew = newT.get("ownerId").toString();
            String xqCodeNew = newT.get("xqCode").toString();
            capturePersonMapper.insertPersonQuitList(newT);
            //判断是否为旧记录存在 且新记录没有的人
            int f = 0;
            for (Map<String, Object> oldT : oldQuitPersonList) {
                String ownerIdOld = oldT.get("ownerId").toString();
                String xqCodeOld = oldT.get("xqCode").toString();
                if(ownerIdOld.equals(ownerIdNew)&&xqCodeOld.equals(xqCodeNew))
                {
                    f = 1;
                    break;
                }
            }
            if(f == 0)
            {
                //若旧记录没有这个人 则将其加入警务通推送需求中
                capturePersonMapper.insertPersonQuitApp(newT);
            }
            //判断是否有发送短信的记录
            Map<String,Object> msgParams=new HashMap<>();
            msgParams.put("xqCode",xqCodeNew);
            msgParams.put("ownerId",ownerIdNew);
            //List<Map<String, Object>> msgList=session.selectList(suspectQuitDaoRoot+".queryPersonQuitMsgCondition",msgParams);
            List<Map<String, Object>> msgList= capturePersonMapper.queryPersonQuitMsgCondition(msgParams);
            if(msgList.size()>0)
            {
                Map<String, Object> msgDetail=msgList.get(0);
                int lastDays=Integer.parseInt(msgDetail.get("lastDays").toString());
                int gapTime=Integer.parseInt(msgDetail.get("gapTime").toString());
                //判断自从短信发起之时是否超过（默认 15）天
                if(lastDays>=gapTime)
                {
                    //已超过
                    //重置发送状态
                    //session.update(suspectQuitDaoRoot+".updatePersonQuitMsgStatus",msgParams);
                    capturePersonMapper.updatePersonQuitMsgStatus(msgParams);
                }
            }
            else
            {
                //发送短信--
                //send...待填写
                //session.insert(suspectQuitDaoRoot+".insertPersonQuitMsg",insertParams);
                capturePersonMapper.insertPersonQuitMsg(newT);
            }
        }
    }
}
