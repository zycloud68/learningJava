package com.cs.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * @author zycloud68@163.com
 * @date 2021/1/11 15:18
 */

/**
 * 谁在什么样的时间坐什么样的事情
 * before  around  after AfterReturning AfterThrowing
 */
@Component
public class CustomAdvice {
    /**
     * JoinPoint类，用来获取代理类和被代理类的信息
     * @param joinPoint
     */
    public void myBefore(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println( "name = " + name);
        // thisName= com.sun.proxy.$Proxy18
        String thisName = joinPoint.getThis().getClass().getName();
        System.out.println("thisName=" + thisName);
        // 获取被代理类的传入参数
        Object[] args = joinPoint.getArgs();
        System.out.println("args: " + Arrays.toString(args));
        String s = joinPoint.getSignature().getDeclaringType().getName();
        System.out.println(s);
        System.out.println("before通知");
    }

    /**
     * after
     */
    public void after(JoinPoint joinPoint){
        System.out.println("after的通知");
    }
    /**
     * around
     */
   public Object around(ProceedingJoinPoint joinPoint){
       System.out.println("around的前置");
       Object proceed = null;
       try {
           proceed = joinPoint.proceed();
       } catch (Throwable throwable) {
           throwable.printStackTrace();
       }
       System.out.println("around的后置");
       return proceed;
   }
   public void afterReturning(Object obj){
       System.out.println("after-returning获得的值：" + obj);
   }
    /**
     * 当出现问题的时候，会走到这一步
     * afterThrowing
     */
   public void afterThrowing(Exception exception){
       System.out.println("after-Throwing"+ exception.getMessage());
   }
}
