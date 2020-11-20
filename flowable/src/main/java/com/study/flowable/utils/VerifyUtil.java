package com.study.flowable.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

@Slf4j
public class VerifyUtil {

    /**
     * 查看参数的校验结果，如果有问题，则打日志，走异常阻断
     *
     * @param bindingResult 参数校验的结果
     */
    public static void verifyParam(BindingResult bindingResult) {

        //参数校验,错误则走异常处理机制
        if (bindingResult.hasErrors()) {
            log.info("异常参数:{}", bindingResult.getFieldError().getDefaultMessage());
            throw new RuntimeException(ResponseUtils.FAIL.msg(bindingResult.getFieldError().getDefaultMessage()).getResult().toJSONString());
        }
    }

}
