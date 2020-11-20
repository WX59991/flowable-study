package com.study.flowable.aspect;

import com.alibaba.fastjson.JSONObject;
import com.study.flowable.annotation.LoggerAnnotation;
import com.study.flowable.utils.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * 利用切面实现注解，缺点就是只能作用到spring容器内管理的类的方法上
 */
@Slf4j
@Component
@Aspect
public class LoggerAnnotationAspect {

    /**
     * 方法用途（切入点表达式可以用&&,||,!来组合使用）:
     *      execution: 匹配连接点：execution(* com.example.demo.*(..))--com.example.demo包下所有类的方法
     *      within: 某个类里面
     *      this: 指定AOP代理类的类型
     *      target:指定目标对象的类型
     *      args: 指定参数的类型
     *      bean:指定特定的bean名称，可以使用通配符（Spring自带的）
     *      @target： 带有指定注解的类型
     *      @args: 指定运行时传的参数带有指定的注解
     *      @within: 匹配使用指定注解的类
     *      @annotation:指定方法所应用的注解
     * 创建切点
     */
    @Pointcut("@annotation(com.study.flowable.annotation.LoggerAnnotation)")
    public void asAnnotation() {}

    /**
     * 方法用途:
     *      @Around 环绕增强，相当于MethodInterceptor，对带@LoggerAnnotation注解的方法进行切面，并获取到注解的属性值
     *      ProceedingJoinPoint: 环绕通知
     * @param joinPoint
     * @param loggerAnnotation
     * @return
     */
    @Around("asAnnotation() && @annotation(loggerAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, LoggerAnnotation loggerAnnotation) {
        Object obj = null;
        try {
            Object[] args = joinPoint.getArgs();
            Object[] newObj=null;
            Signature signature = joinPoint.getSignature();
            // 方法package路径
            String methodUrl = signature.getDeclaringTypeName();
            // 方法名，不包含package路径
            String method = signature.getName();
            if(loggerAnnotation.isVerfyUtil()){
                VerifyUtil.verifyParam((BindingResult)args[args.length-1]);
                newObj=new Object[args.length-1];
                for(int i=0;i<newObj.length;i++){
                    newObj[i]=args[i];
                }
                log.info("正在执行{}类的{}方法，参数：{}" ,methodUrl,method,JSONObject.toJSONString(newObj));
            }else{
                log.info("正在执行{}类的{}方法，参数：{}" ,methodUrl,method,JSONObject.toJSONString(args));
            }
            obj = joinPoint.proceed(args);
            if(!loggerAnnotation.isVoid()){
                if(loggerAnnotation.isVerfyUtil()){
                    log.info("{}类的{}方法，参数：{},执行结果:{}" ,methodUrl,method,JSONObject.toJSONString(newObj),JSONObject.toJSONString(obj));
                }else{
                    log.info("{}类的{}方法，参数：{},执行结果:{}" ,methodUrl,method,JSONObject.toJSONString(args),JSONObject.toJSONString(obj));
                }

            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
