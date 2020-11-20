package com.study.flowable.entity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class HolidayEntity {
    private Long holidayId;

    @NotEmpty  //注解主要用来结合VerifyUtil进行校验
    private String holidayName;
    @NotEmpty
    private Integer holidayCount;
    private String holidayReson;

    private String taskKey;

    private String processId;

}
