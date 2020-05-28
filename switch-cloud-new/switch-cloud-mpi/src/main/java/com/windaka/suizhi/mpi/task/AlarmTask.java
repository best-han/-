package com.windaka.suizhi.mpi.task;

import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.mpi.dao.auto.*;
import com.windaka.suizhi.mpi.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AlarmTask
 * @Description 将布控结束的记录改为撤控
 * @Author lixianhua
 * @Date 2020/5/15 18:44
 * @Version 1.0
 */
@Service
@Slf4j
public class AlarmTask {

    @Autowired
    private FaceGroupDetailMapper faceGroupDetailMapper;

    @Autowired
    private CarGroupDetailMapper carGroupDetailMapper;

    @Autowired
    private FaceAttentionDetailMapper faceAttentionDetailMapper;

    @Autowired
    private FaceGroupRecordMapper faceGroupRecordMapper;

    @Autowired
    private CarGroupRecordMapper carGroupRecordMapper;

    @Autowired
    private FaceAttentionRecordMapper faceAttentionRecordMapper;

    public void run(){
        log.info("*********************检测布控任务开始，开始时间为:" + TimesUtil.getServerDateTime(9, new Date()) + "**********************");
        int personNum = 0;
        int carNum = 0;
        int attentionNum = 0;
        FaceGroupDetailExample example = new FaceGroupDetailExample();
        example.createCriteria().andStatusEqualTo(false);
        // 获取布控中的人员布控集合
        List<FaceGroupDetail> faceList = this.faceGroupDetailMapper.selectByExample(example);
        if (null != faceList && faceList.size() > 0) {
            for (FaceGroupDetail faceDetail : faceList) {
                if (new Date().getTime() > faceDetail.getEndTime().getTime()) {// 当前时间大于布控结束时间
                    faceDetail.setStatus(true);
                    faceDetail.setWithdrawTime(new Date());
                    faceDetail.setUpdateTime(new Date());
                    int person = this.faceGroupDetailMapper.updateByPrimaryKeySelective(faceDetail);
                    if (person > 0) {
                        personNum++;
                        // 添加人员撤控记录
                        FaceGroupRecord faceGroupRecord = new FaceGroupRecord();
                        faceGroupRecord.setCreateTime(new Date());
                        faceGroupRecord.setType(true);
                        faceGroupRecord.setDetailId(faceDetail.getId());
                        faceGroupRecord.setWithdrawTime(new Date());
                        this.faceGroupRecordMapper.insertSelective(faceGroupRecord);

                    }
                }
            }
        }
        CarGroupDetailExample carExample = new CarGroupDetailExample();
        carExample.createCriteria().andStatusEqualTo(false);
        // 获取布控中的车辆布控集合
        List<CarGroupDetail> carList = this.carGroupDetailMapper.selectByExample(carExample);
        if (null != carList && carList.size() > 0) {
            for (CarGroupDetail carDetail : carList) {
                if (new Date().getTime() > carDetail.getEndTime().getTime()) {// 前时间大于布控结束时间
                    carDetail.setStatus(true);
                    carDetail.setWithdrawTime(new Date());
                    carDetail.setUpdateTime(new Date());
                    int car = this.carGroupDetailMapper.updateByPrimaryKeySelective(carDetail);
                    if (car > 0) {
                        carNum++;
                        // 添加车辆撤控记录
                        CarGroupRecord carGroupRecord = new CarGroupRecord();
                        carGroupRecord.setCreateTime(new Date());
                        carGroupRecord.setDetailId(carDetail.getId());
                        carGroupRecord.setWithdrawTime(new Date());
                        carGroupRecord.setType(true);
                        this.carGroupRecordMapper.insertSelective(carGroupRecord);
                    }
                }
            }
        }
        FaceAttentionDetailExample attentionExample = new FaceAttentionDetailExample();
        attentionExample.createCriteria().andStatusEqualTo(false);
        // 获取布控中的重点关注集合
        List<FaceAttentionDetail> attentionList = this.faceAttentionDetailMapper.selectByExample(attentionExample);
        if (null != attentionList && attentionList.size() > 0) {
            for (FaceAttentionDetail attentionDetail : attentionList) {
                if (new Date().getTime() > attentionDetail.getEndTime().getTime()) {
                    attentionDetail.setStatus(true);
                    attentionDetail.setWithdrawTime(new Date());
                    attentionDetail.setUpdateTime(new Date());
                    int attention = this.faceAttentionDetailMapper.updateByPrimaryKeySelective(attentionDetail);
                    if (attention > 0) {
                        attentionNum++;
                        // 添加重点关注人员撤控记录
                        FaceAttentionRecord record = new FaceAttentionRecord();
                        record.setCreateTime(new Date());
                        record.setDetailId(attentionDetail.getId());
                        record.setWithdrawTime(new Date());
                        record.setType(true);
                        this.faceAttentionRecordMapper.insertSelective(record);
                    }
                }
            }
        }
        log.info("*********************检测布控任务结束，结束时间为:" + TimesUtil.getServerDateTime(9, new Date()) + "撤控人员记录" + personNum + "条，车辆记录" + carNum + "条，重点关注记录" + attentionNum + "条**********************");
    }
}
