package com.study.flowable.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.flowable.annotation.LoggerAnnotation;
import com.study.flowable.dao.HolidayDao;
import com.study.flowable.entity.DealTaskVo;
import com.study.flowable.entity.HolidayEntity;
import com.study.flowable.enums.ProcessEnum;
import com.study.flowable.service.CommonTaskService;
import com.study.flowable.utils.ResponseUtils;
import com.study.flowable.vo.UserTaskVo;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/userTask")
@RestController
public class UserTaskController {

    @Autowired
    private CommonTaskService commonTaskService;

    @Autowired
    private TaskService taskService;

    @Resource
    private HolidayDao holidayDao;

    @LoggerAnnotation
    @PostMapping("/list")
    public JSONObject list(String userName){
        if(StringUtils.isEmpty(userName)){
            return ResponseUtils.FAIL.msg("用户名不能为空").getResult();
        }
        //通过用户名获取发起的所有流程
        List<Task> beginTaskID1 = taskService.createTaskQuery().processVariableValueEquals("beginTaskID", userName).list();
        List<UserTaskVo> result=new ArrayList<>();
        for(Task task:beginTaskID1){
            UserTaskVo userTaskVo=new UserTaskVo();
            userTaskVo.setTaskId(task.getId());
            userTaskVo.setProcessId(task.getProcessInstanceId());
            userTaskVo.setBeginName(userName);
            userTaskVo.setCurrState(task.getName());
            userTaskVo.setProcessName(ProcessEnum.getProcessName(task.getProcessDefinitionId().split(":")[0]));
            result.add(userTaskVo);
        }
        return ResponseUtils.SUCCESS.msg(result).getResult();
    }


    /**
     * 创建请假任务
     * 如果失败就回退所有
     * @param bindingResult
     * @param holidayEntity
     */
    @LoggerAnnotation(isVerfyUtil = true)
    @PostMapping("/createHolidayTask")
    @Transactional
    public JSONObject createHolidayTask(@RequestBody  HolidayEntity holidayEntity,BindingResult bindingResult){
        HashMap<String,Object> map=new HashMap<>();
        map.put("beginTaskID",holidayEntity.getHolidayName());
        String processId = commonTaskService.startLeaveProcess(map, holidayEntity.getTaskKey());
        holidayEntity.setProcessId(processId);
        holidayDao.insert(holidayEntity);//把请假流程存入数据库
        return ResponseUtils.SUCCESS.msg("创建任务实例成功").getResult();
    }

    /**
     * 每次修改之后taksId都会改变
     * 经理处理流程
     * @param dealTaskVo  任务id  act_ru_task表id
     * @author xinzhifu
     * @description 驳回
     * @date 2020/8/27 14:30
     */
    @LoggerAnnotation
    @PostMapping("dealTask")
    public JSONObject dealTask(@RequestBody  DealTaskVo dealTaskVo) {
        taskService.complete(dealTaskVo.getTaskId(), dealTaskVo.getParam());
        return ResponseUtils.SUCCESS.msg("处理成功").getResult();
    }

    /**
     * 查询流程图
     *
     * @param httpServletResponse
     * @param processId act_ru_task表execution_ID_
     * @throws Exception
     */
    @GetMapping("processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
        commonTaskService.getProcessDiagram(httpServletResponse, processId);
    }

    /**
     * 删除任务流程
     * @param processId
     */
    @LoggerAnnotation(isVoid = true)
    @GetMapping("deleteProcess")
    public void deleteProcess(String processId){
        commonTaskService.deleteProcess(processId);
    }
}
