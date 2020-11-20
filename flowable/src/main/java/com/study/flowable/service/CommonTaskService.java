package com.study.flowable.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface CommonTaskService {
    /**
     * 查询流程图
     *
     * @param httpServletResponse
     * @param processId act_ru_task表PROC_INST_ID_
     * @throws Exception
     */
    void getProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception;

    /**
     * 创建并启动任务
     * @param startTaskParam  任务参数
     * @param taskId  任务id
     * @return 启动结果
     */
    String startLeaveProcess(Map<String,Object> startTaskParam,String taskId);

    /**
     * 删除流程
     * @param processId processId
     */
    void deleteProcess(String processId);

}
