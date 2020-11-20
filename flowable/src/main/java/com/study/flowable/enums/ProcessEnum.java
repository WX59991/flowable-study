package com.study.flowable.enums;

public enum ProcessEnum {

    holiday_request("请假");

    private String processName;
    ProcessEnum(String processName){
        this.processName=processName;
    }

    public static String getProcessName(String taskKey){
        switch (taskKey){
            case "holiday-request":
                return ProcessEnum.holiday_request.processName;
        }
        return null;
    }
}
