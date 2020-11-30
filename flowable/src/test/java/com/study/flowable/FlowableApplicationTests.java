package com.study.flowable;

import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class FlowableApplicationTests {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Test
    void contextLoads() {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId("15008").singleResult();
        List<Task> list = taskService.createTaskQuery().taskAssignee("admin").list();
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //通过参数查询
        List<Task> beginTaskID1 = taskService.createTaskQuery().processVariableValueEquals("beginTaskID", "admin").list();
//        Object beginTaskID = taskService.getVariable(beginTaskID1.getId(), "beginTaskID");
        System.out.println(list.size());
    }


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Test
    void createTask() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("beginTaskID", "张三");
        runtimeService.startProcessInstanceByKey("holiday-request", map);
    }

    @Test
    void selectTask() {
        List<Task> beginTaskID1 = taskService.createTaskQuery().processVariableValueEquals("beginTaskID", "张三").list();
        System.out.println(beginTaskID1.get(0).getId());
    }

    @Autowired
    HistoryService historyService;

    @Test
    void selectHistory() {
        List result=historyService.createHistoricProcessInstanceQuery().variableValueEquals("beginTaskID", "admin").list();
        System.out.println(result.size());
    }

    @Test
    void completeTask() {
        List<Task> beginTaskID1 = taskService.createTaskQuery().processVariableValueEquals("beginTaskID", "张三").list();
        String taskId = beginTaskID1.get(0).getId();
        HashMap<String, Object> param = new HashMap<>();
        param.put("checkResult", "通过");
        taskService.complete(taskId, param);
    }

    @Test
    void deleteTask() {
        List<Task> beginTaskID1 = taskService.createTaskQuery().processVariableValueEquals("beginTaskID", "张三").list();
        String processId = beginTaskID1.get(0).getProcessInstanceId();
        runtimeService.deleteProcessInstance(processId, "删除的原因");
    }


}
