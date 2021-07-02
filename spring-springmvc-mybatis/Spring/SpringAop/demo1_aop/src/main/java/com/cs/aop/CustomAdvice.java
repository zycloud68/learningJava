package com.cs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zycloud68@163.com
 * @date 2021/1/8 22:29
 */
@Component
public class CustomAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("before");
        Object[] arguments = methodInvocation.getArguments();
        System.out.println("argument:"+ Arrays.toString(arguments));
        String name = methodInvocation.getMethod().getName();
        System.out.println("name:"+ name);
        Object proceed = methodInvocation.proceed();
        System.out.println("after");
        return proceed;
    }
}
