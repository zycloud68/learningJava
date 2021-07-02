package com.cs.designpattern;

import org.junit.Test;

/**
 * @author zycloud68@163.com
 * @date 2021/1/7 17:07
 */
public class TestProxy {
    @Test
    public void  testProxy(){
        JdkProxyExample jdkProxyExample = new JdkProxyExample();
        // 绑定关系，因为挂在接口下面，所以申明了代理对象HelloWorld proxy
        HelloWorld  proxy = (HelloWorld) jdkProxyExample.bind(new HelloWorldImpl());
        proxy.sayHelloWorld();

    }
}
