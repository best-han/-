package com.windaka.suizhi.mpi.model.ext;

import com.windaka.suizhi.mpi.model.CapturePerson;
import lombok.Data;

/**
 * @ClassName ExtCapturePerson
 * @Description TODO
 * @Author lixianhua
 * @Date 2020/4/10 8:30
 * @Version 1.0
 */
@Data
public class ExtCapturePerson extends CapturePerson {

    // 抓拍时间
    private String captureTimeStr;
}
