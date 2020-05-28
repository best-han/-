package com.windaka.suizhi.webapi.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Building {

    private String buildingCode;

    private String buildingName;

    private List<Map<String,Object>> units;
}