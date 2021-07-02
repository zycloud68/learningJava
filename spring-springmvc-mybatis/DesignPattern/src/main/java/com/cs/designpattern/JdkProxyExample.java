package com.cs.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zycloud68@163.com
 * @date 2021/1/7 16:55
 */
public class JdkProxyExample implements InvocationHandler {
    // 真实的对象
    private  Object target = null;

    /**
     * 建立真实对象和代理对象之间的关系，并返回代理对象
     * @param target  真实对象
     * @return
     */
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /**
     * @param proxy   代理对象
     * @param method  当前调度方法
     * @param args    当前方法的参数
     * @return  代理结果返回
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑的方法");
        System.out.println("在调用真实对象之前的服务");
        Object obj = method.invoke(target, args);
        // 相当于调用sayHelloWorld方法
        System.out.println("在调用真实对象之后的服务");
        return obj;
    }
}
