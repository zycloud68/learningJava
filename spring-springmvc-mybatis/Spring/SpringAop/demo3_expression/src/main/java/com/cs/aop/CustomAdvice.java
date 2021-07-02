package com.cs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author zycloud68@163.com
 * @date 2021/1/9 01:22
 */
@Component
public class CustomAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("before");
        String name = methodInvocation.getMethod().getName();
        System.out.println("name method:" + name);
        Object proceed = methodInvocation.proceed();
        System.out.println("after");
        return proceed;
    }
}
