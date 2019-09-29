package com.hujy.springaopdemo.aspect;

import com.hujy.springaopdemo.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 记录日志切面
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-29 11:17
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 定义切点（注解方式）
     * @author hujy
     * @date 2019-09-29 17:30
     * @param
     * @return void
     */
    @Pointcut("@annotation(com.hujy.springaopdemo.annotation.Log)")
    public void annotationPointCut() {}

    /**
     * 定义切点（正则表达式方式）
     * @author hujy
     * @date 2019-09-29 17:33
     * @param
     * @return void
     */
    @Pointcut("execution(* com.hujy.springaopdemo.service.impl.UserServiceImpl.saveUser(..))")
    public void expPointCut() {}

    /**
     * 前置通知
     * 
     * @author hujy
     * @date 2019-09-29 16:58
     * @param joinPoint
     * @return void
     */
    @Before(value = "annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("【before】方法执行开始时间:" + System.currentTimeMillis());
        // 获取连接点方法的签名对象
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // 获取方法注解对象
        Log log = signature.getMethod().getAnnotation(Log.class);
        System.out.println("【before】记录操作类型:" + log.value());

        // 连接点方法入参
        if (joinPoint.getArgs() != null) {
            for (Object arg : joinPoint.getArgs()) {
                System.out.println("【before】记录方法参数:" + arg);
            }
        }
    }

    /**
     * 后置通知
     *
     * @author hujy
     * @date 2019-09-29 16:58
     * @param joinPoint
     * @return void
     */
    @After(value = "annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("【after】方法执行结束时间:" + System.currentTimeMillis());
    }

    /**
     * 后置通知（带返回值）
     *
     * @author hujy
     * @date 2019-09-29 17:00
     * @param joinPoint
     * @param result
     * @return void
     */
    @AfterReturning(value = "annotationPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("【after returning】记录方法返回值:" + result);
    }

    /**
     * 后置通知（可处理异常）
     *
     * @author hujy
     * @date 2019-09-29 17:00
     * @param joinPoint
     * @param e
     * @return void
     */
    @AfterThrowing(value = "annotationPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("【after throwing】记录异常:" + e.getMessage());
    }

    /**
     * 
     * 环绕通知(Around) 
     * 是所有通知中最为强大的通知，强大也意味着难以控制。 
     * 一般而言，使用它的场景是在你需要大幅度修改原有目标对象的服务逻辑时，否则都尽量使用其他的通知。 
     * 环绕通知是一个取代原有目标对象方法的通知，当然它也提供了回调原有目标对象方法的能力。
     * 
     * @author hujy
     * @date 2019-09-29 16:57
     * @param joinPoint
     * @return void
     */
    @Around(value = "expPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        System.out.println("【around before】方法执行开始时间:" + startTime);

        if (joinPoint.getArgs() != null) {
            for (Object arg : joinPoint.getArgs()) {
                System.out.println("【around before】记录方法参数:" + arg);
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
            System.out.println("【around returning】记录方法返回值:" + result);
        } catch (Throwable throwable) {
            System.out.println("【around throwing】记录方法异常:" + throwable.getMessage());
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("【around after】记录方法耗时:" + (endTime - startTime) + "ms");
        // 这里需要将结果返回，否则调用方拿不到返回值
        return result;
    }
}
