package com.windaka.suizhi.mpi.task;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.mpi.dao.auto.CaptureCarAutoMapper;
import com.windaka.suizhi.mpi.dao.auto.CapturePersonMapper;
import com.windaka.suizhi.mpi.dao.auto.CarAccessMapper;
import com.windaka.suizhi.mpi.dao.auto.PersonInoutMapper;
import com.windaka.suizhi.mpi.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CaptureDeleteTask
 * @Description 定时删除抓拍图片（保留三个月的）
 * @Author lixianhua
 * @Date 2020/5/28 8:27
 * @Version 1.0
 */
@Component
@Slf4j
public class CaptureDeleteTask {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private CaptureCarAutoMapper captureCarAutoMapper;

    @Autowired
    private CapturePersonMapper capturePersonMapper;

    @Autowired
    private CarAccessMapper carAccessMapper;

    @Autowired
    private PersonInoutMapper personInoutMapper;

    public void run() {
        System.out.println("*********************删除抓拍任务开始，开始时间为:" + TimesUtil.getServerDateTime(9, new Date()) + "**********************");
        int personNum = 0;
        int carNum = 0;
        int inoutNum = 0;
        int accessNum = 0;
        // 获取超时的抓拍人图片
        CapturePersonExample personExample = new CapturePersonExample();
        personExample.createCriteria().andCaptureTimeLessThan(TimesUtil.addMonth(new Date(), -3));
        List<CapturePerson> personList = this.capturePersonMapper.selectByExample(personExample);
        if (null != personList && personList.size() > 0) {
            for (CapturePerson person : personList) {
                if (StringUtils.isNotBlank(person.getCapImage())) {
                    this.fastFileStorageClient.deleteFile(person.getCapImage());
                }
                if (StringUtils.isNotBlank(person.getCapPersonImage())) {
                    this.fastFileStorageClient.deleteFile(person.getCapPersonImage());
                }

            }
            // 删除抓拍人记录
            personNum = this.capturePersonMapper.deleteByExample(personExample);
        }
        // 获取超时的抓拍车图片
        CaptureCarExample carExample = new CaptureCarExample();
        carExample.createCriteria().andCaptureTimeLessThan(TimesUtil.addMonth(new Date(), -3));
        List<CaptureCar> carList = this.captureCarAutoMapper.selectByExample(carExample);
        if (null != carList && carList.size() > 0) {
            for (CaptureCar car : carList) {
                if (StringUtils.isNotBlank(car.getCapImage())) {
                    this.fastFileStorageClient.deleteFile(car.getCapImage());
                }
                if (StringUtils.isNotBlank(car.getFullImage())) {
                    this.fastFileStorageClient.deleteFile(car.getFullImage());
                }
            }
            // 删除车抓拍记录
            carNum = this.captureCarAutoMapper.deleteByExample(carExample);
        }
        // 获取超时的人进出图片
        PersonInoutExample inoutExample = new PersonInoutExample();
        inoutExample.createCriteria().andCaptureTimeLessThan(TimesUtil.addMonth(new Date(), -3));
        List<PersonInout> inoutList = this.personInoutMapper.selectByExample(inoutExample);
        if (inoutList.size() > 0) {
            for (PersonInout inout : inoutList) {
                if (StringUtils.isNotBlank(inout.getCapImage())) {
                    this.fastFileStorageClient.deleteFile(inout.getCapImage());
                }
            }
            // 删除人出入记录
            inoutNum = this.personInoutMapper.deleteByExample(inoutExample);
        }
        // 获取超时的车进出图片
        CarAccessExample carAccessExample = new CarAccessExample();
        carAccessExample.createCriteria().andCaptureTimeLessThan(TimesUtil.addMonth(new Date(), -3));
        List<CarAccess> accessList = this.carAccessMapper.selectByExample(carAccessExample);
        if (null != accessList && accessList.size() > 0) {
            for (CarAccess access : accessList) {
                if (StringUtils.isNotBlank(access.getCapImage())) {
                    this.fastFileStorageClient.deleteFile(access.getCapImage());
                }
            }
            accessNum = this.carAccessMapper.deleteByExample(carAccessExample);
        }
        System.out.println("*********************删除抓拍任务结束，结束时间为:" + TimesUtil.getServerDateTime(9, new Date()) +
                "删除抓拍人记录" + personNum + "条，车抓拍记录" + carNum + "条，人进出记录" + inoutNum + "条，车进出记录" + accessNum + "条**********************");

    }
}
