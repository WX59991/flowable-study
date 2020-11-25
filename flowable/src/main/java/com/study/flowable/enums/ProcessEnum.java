package com.study.flowable.enums;

public enum ProcessEnum {

    holiday_request("请假"),
    LeaveProcess("请假新流程");

    private String processName;
    ProcessEnum(String processName){
        this.processName=processName;
    }

    public static String getProcessName(String taskKey){
        switch (taskKey){
            case "holiday-request":
                return ProcessEnum.holiday_request.processName;
            case "LeaveProcess":
                return ProcessEnum.LeaveProcess.processName;
        }
        return null;
    }
}
