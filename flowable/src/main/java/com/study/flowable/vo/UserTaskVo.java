package com.study.flowable.vo;

public class UserTaskVo {

    //任务id
    private String taskId;

    //流程id
    private String processId;

    //发起人姓名
    private String beginName;
    //任务类型
    private String processName;
    //当前状态
    private String currState;

    public UserTaskVo() {
    }

    public String getBeginName() {
        return beginName;
    }

    public void setBeginName(String beginName) {
        this.beginName = beginName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getCurrState() {
        return currState;
    }

    public void setCurrState(String currState) {
        this.currState = currState;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
