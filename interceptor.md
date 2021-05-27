静态资源处理

使用的是default-servlet

映射形式

<mvc:resources mapping location/>

**mapping 对应的是映射路径，访问路径**

**location对应的是真实路径，文件真实存在的路径，**表示多级路径**

今天任务：

1. interceptor

   Spring mvc拦截器 类似于servlet的开发的过滤器filter，用于对处理器进行**预处理**和**后处理**。

   预处理 -handler-后处理(处理modelAndView)

   常见使用场景： 日志记录，权限检查，性能监控

   和AOP类似。

   1.1  prehandle--如果当前的prehandle返回值为ture，那么一定可以执行到afterCompletion。

   1.2  handler

   1.3  postHandle

   1.4  afterCompletion

   如果prehandle的返回值为true，则可以在套娃的部分下继续执行，如果在范围为false，不会继续执行下去。

   如果prehandle的返回值为true，当前的aftercompletion一定会执行。

   1.5 对部分的url进行拦截

      通过配置拦截器的url来选择部分请求。

   <mvc: interceptor>

   <mvc:mapping path="/user/**"> 对user下面的所有组件进行拦截

   ​	</mvc: interceptor>

2. exception

3. freemarker

**第四天内容: JSON**

Spring mvc支持json

推荐工具: GsonFormat  GeneratorAllSetter

Json 

**1.1 返回json**

依赖: Spring-webmvc    servlet-api lombok 

Jackson -databind(fastxml) 一拖三

在webapp中添加xml dispatcherServlet    <servlet-mapping中添加dispatcherServlet>

<init中添加信息>

在application.xml中添加 <context:componet-scan base-package="com.cskaoyan">

<mvc: annotation-driven>

想要以返回json数据格式,@RespondBody在handler中新增一个方法的前面的注解

@RestController =@Controller+ @ResponseBody 就是在一个类的所有方法都返回json

**1.2 接受json**

接收在参数的的前面加上@RequestBody

**1.3 Hibernate Validator**

验证性的校验,请求参数的合法性验证

1.3.1 注解javabean申明校验规则

1.3.2 添加menssage错误信息源实现国际化I18N的方法

引入依赖 hibernate-validator

```java
组件: <LocalValidatorFactoryBean>

​	<property id="validatorFactoryBean" name="providerclass" value="HibernateValidator">

</LocalValidatorFactoryBean>
```

在<mvc>组件中别忘记添加validator=“id”>



校验中添加@Valid 表示校验注解 @Size表示长度的校验-----声明式校验规则

在形参中添加BindingResult后,可以获取bindingResult的参数信息.

在注册组件的时候,实现国际化

```java
messagesource--ReloadableResourceBoundleMessageSource

<property name="defaultEncoding" value="utf-8">

<property name="fileEncoding" value="utf-8">

<property name="basename" value="classpath:message">

<property name="useCodeAsDefaultMessage" value="true">

<bean id="messageSource" class="LocalValidatorFactoryBean">

​	<property name="providerClass" value="utf-8">

​	<property name="validationMessageSource" value="messgeSource">

​		</bean>

**localResolver,注意这个id是写死了的**.请注意

<bean id="localResolver" class="CookieLocalResolver">

// 默认的语言信息 使用的是en_US

<property name="defaultLocale" value="en_US"> 

// cookie中决定locale信息的字段,就是在cookie信息中添加为lang,可以自定义

<property name="cookieName" value="lang">

</bean>
```





#### JavaConfig

1. Spring是父,Springmvc是子,子类可以使用父类的一切方法,但是父类不能越界,先启动父类,在启动子类,

   所以在web.xml中增添一下代码

   ```java
   <listener>
   	<listener-class>ContextLoaderList的全类名</listener-class>
   	</listener>
   # 先加载Spring的配置文件
   <context-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:application_spring.xml</param-value>
    </context-param>     
   # 再去加载SpringMVC的配置文件
   <init-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:application_springMVC.xml</param-value>
    </init-param>     
   ```

   AACDSI       AbstractAnnotationConfigDispatcherServletInitializer

   ```java
   @Configuration //配置类
   @ComponentScan("com.cs",excludeFilters=@ComponentScan.Filter(type=FilterType.ANNATION,value=Controller.class)) // 不去加载controller类
   @EnableMvc // 允许使用webmvc
   ```

   

