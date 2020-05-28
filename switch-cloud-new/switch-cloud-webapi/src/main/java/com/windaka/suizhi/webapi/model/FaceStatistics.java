package com.windaka.suizhi.webapi.model;

import lombok.Data;

@Data
public class FaceStatistics {
    private Integer id;

    private String faceId;

    private Byte type;

    private String records;

}