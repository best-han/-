package com.windaka.suizhi.manageport.dao;

import com.windaka.suizhi.manageport.model.JRoomRentRecord;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface JRoomRentRecordMapper {



    /**
     * @author ：ygy
     * @date   ：2020/4/23 上午11:40
     * @description：  日租短租 记录表 插入
     */
    int insertRoomRentRecord(JRoomRentRecord jRoomRentRecord);
}
