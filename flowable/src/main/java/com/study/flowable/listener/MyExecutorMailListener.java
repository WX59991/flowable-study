package com.study.flowable.listener;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 主要是针对流程的监听
 */
@Component("myExecutorMailListener")
public class MyExecutorMailListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("发送邮件");
    }
}
