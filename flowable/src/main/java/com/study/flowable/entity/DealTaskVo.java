package com.study.flowable.entity;

import lombok.Data;

import java.util.Map;
@Data
public class DealTaskVo {

    private String taskId;

    private Map<String,Object> param;

}
