package com.windaka.suizhi.api.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息队列实体类
 */
@Data
public class MqObject implements Serializable {

    private static final long serialVersionUID = -8897343869674329934L;

    private String mqStr;

    private Map<String,Object> mqMap;
}
