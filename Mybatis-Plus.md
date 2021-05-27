#### 主键策略:

自动增长 auto increment

#### MP实现修改操作

```java
根据ID操作 updateById
  userMapper.updateById(user);
修改什么数值,就添加什么数值
```

#### 自动填充

```java
// 在表里面添加两个字段,create_time,update_time; 类型是datetime
创建时间,修改时间
2在实体类中添加 Date createTime,表中的字段变为驼峰字段
							Date updateTime
在MP中自动添加,不需要set到对象的值.
3. 具体实现方法:
	3.1.1 在实体类中添加注解 @TableField(fill=FieldFill.INSERT)
	3.1.2 @TableField(fill=FieldFill.INSERT_UPDATE)
  3.2 创建类,实现接口MetaObjectHandler实现接口里面的方法 @Component
    // 为了方便起见,创建一个handler包名.
    下面是增加时间,需要两个一起添加
    this.setFieldValByName(“createField”,new Date(),metaObject); // 根据名称设置属性值
		this.setFieldValByName(“updateField”,new Date(),metaObject);
		// 当修改数据的时候,update,只需要进行修改时间即可.
    this.setFieldValByName(“updateField”,new Date(),metaObject);
```

#### 乐观锁

针对特定问题的解决方案.主要解决**丢失更新**

```java
多人同时修改同一条记录,最后提交的把之前的提交数据覆盖掉.
解决方案:
1. 悲观锁: 当一人操作的时候,其他人不能操作,类似于串行.效率很低. 基本抛弃.
2. 乐观锁: 在数据库中添加版本号 version,最后一个人提交,会将前一个的覆盖掉,并且version+1;
	        2.1 在数据库中添加version字段 version int
	        2.2 在实体类中添加version字段,并且添加@Version
	        		@Version
	        		private Integer version;
	        2.3 配置乐观锁的插件
	        	@Bean
	        	public OptimisticLockerInterceptor optimisticLockerInterceptor(){
	        	 return new OptimisticLockerInterceptor();
	        	} 
	        在实际操作中,我们将这些写入配置类中.@Configuration
         
```

#### 分页查询 PageHelper

1. 配置分页插件

   ```java
   // 分页插件
   @Bean 
   public PaginationInterceptor  paginationInterceptor(){
    return new PaginationInterceptor();
   }
   ```

2. 编写分页代码

   ```java
   // 分页查询
   public void testPage(){
     // 1. 创建分页对象
     // 2. 传入两个参数,当前页,和每页显示的记录数
     Page<User> page = new Page<>(1,3);
     // 2. 调用
     // 把分页所有数据封装在page对象里面
     userMapper.selectPage(page,XX);
     // 通过page对象获取分页数据.
     sout.(page.getCurrent());  // page.getRecods(),page.getSize(),page.getTotal();
     
   }
   ```

####  分页条件查询

```java
// 多条件组合查询
组合:就是查询某一些部分
  1. 第一步:
		1.1 把条件值传到接口里面,把条件值封装在对象中,把对象传递到接口里面
  2. 第二步:
		2.1 根据条件值进行判断,拼接条件
      2.1.1 @RequestBody 使用json传递数据,把json数据封装到对应对象里面 
         注意: 使用RequestBody提交方式,需要使用post提交方式,但是一般在@RequstBody(required= false),参数值可以为空
      2.1.2 @ResponseBody 返回数据,返回json数据
```

```java
//4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false)  TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
       // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
```



#### 逻辑删除

添加deleted =0 的时候,在表中存在,但是不能查询出来.

物理删除就是在表中彻底删除.

```java
1. 在表中添加逻辑删除字段deleted,对应实体类上添加属性
2. 实体类添加一下注解
  @TableLogic
  @TableField(fill=FieldFill.INSERT) 
  private Integer deleted;
3. 在配置文件中添加插件
   @Bean
   pulic ISqlInjector sqlInjector(){
    return new ISqlInjector();
}
4. 在元对象处理器接口添加deleted的insert默认值
  public void insertFill(MetaObject metaObject){
   this.setFieldValByName("deleted",metaObject);   
}
5. 在application.properties中添加配置
  // 一下配置可以不用写,默认就是这些配置
  mybatis-plus.global-config.db-config.logic-delete-value=1  // 1是删除
  mybatis-plus.global-config.db-config.logic-not-delete-value=0 // 0不删除
```

####  性能插件

性能分析拦截器,用于输出每条sql语句及其执行时间,当超过开发时间,有帮助我们发现问题.

1. 配置插件

   参数: maxTime : 执行sql最大时长,超过自动停止运行,有助于发现问题.

   ​         format: sql是否格式化,默认为false.

2. 在config中配置,如果项目上线的话,我们最好不推荐使用.

   ```java
   @Bean
   @Profile("dev","test") // 设置dev test开发环境
   public PerformanInterceptor performanInterceptor(){
     PerformanInterceptor performanInterceptor = new PerformanInterceptor();
     performanInterceptor.setMaxTime(100); //默认是ms.超过100ms语句不执行
     performanInterceptor.setFormat(true); //sql语言格式化
     return performanInterceptor;
   }
   ```

   #### MP实现复杂条件查询

   ```java
   // 1. 创建QuerryWrapper对象
   QueryWrapper<User> queryWrapper = new QueryWrapper<>();
   // 2. 通过QueryWrapper设置条件
   // ge gt le lt 分别对应为大于等于,大于,小于等于,小于
   // ge 第一个参数是名称,第二个参数是内容
   
   ```

   #### 修改功能

   这里面包含两部分方法:

   1. 先根据id来查询

   ```java
   @GetMapping("getTeacher/{id}")
   public ResultResponse getTeacher(@PathVariable String id){
     EduTeacher eduTeacher = eduTeacherService.getById(id).var
       return ResultResponse.ok().data("teacher",eduTeacher);
   }
   ```

   2. 再来进行修改的查询

      ```java
      @PostMapping("updateTeacher")
      // 注意要使用post
      public ResultResponse updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(id);
        if(flag){
          return ResultResponse.ok();
        }
          return ResultResponse.error();
      }
      ```

####  异常处理

建立统一异常处理类GlobalExceptionHandler.java

注意: 千万别忘记把ResultResponse这个包引入到其他包中

1. 全局异常处理

```java
// 全局异常处理类
@ControllerAdvice
public class GlobalExceptionHandler{
// 当出现什么异常的时候才会执行到这个方法
	@ExceptionHandler(Exception.class)
  // 因为这不是在controller层中,不能使用@RestController
	@ResponseBody
	public ResultResponse error(Exception e){
		e.printStackTrace();
		return ResultResponse.message("执行了异常处理");
	}
}
```

2. 特殊异常处理

```java
@ControllerAdvice
public class GlobalExceptionHandler{
// 当出现什么异常的时候才会执行到这个方法
	@ExceptionHandler(AirthmeticException.class)
  // 因为这不是在controller层中,不能使用@RestController
	@ResponseBody
	public ResultResponse error(AirthmeticException e){
		e.printStackTrace();
		return ResultResponse.message("执行了Airthmetic处理");
	}
}
```

3. 自定义异常处理

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizeException extends RuntimeException{
  @ApiModelProperty(value="状态码")
  private Integer code;
  private String msg;
}
```

```java
@ControllerAdvice
public class CustomizeExceptionHandler{
// 当出现什么异常的时候才会执行到这个方法
	@ExceptionHandler(CustomizeException.class)
  // 因为这不是在controller层中,不能使用@RestController
	@ResponseBody
	public ResultResponse error(CustomizeException e){
		e.printStackTrace();
		return ResultResponse.error().code(e.getCode()).message(e.getMsg())("执行了Airthmetic处理");
	}
}

// 主要是系统不会自动操作,需要自己抛出新的异常
```

####  统一日志处理

配置日志级别: 默认级别是info,基本信息

error,warn, info, debug, all

```java
设置日志级别
  logging.level.root=warn // 自己改成其他的
```

```java
使用logback日志工具
  1. 将之前的日志配置删除 logging mybatis
  2. 在resources中创建logback-spring.xml 注意名字不能改变
<?xml version="1.0" encoding="UTF-8"?>
<configuration  scan="true" scanPeriod="10 seconds">
    <!-- 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出 -->
    <!-- scan:当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true -->
    <!-- scanPeriod:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。 -->
    <!-- debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->

    <contextName>logback</contextName>
    <!-- name的值是变量的名称，value的值时变量定义的值。通过定义的值会被插入到logger上下文中。定义变量后，可以使“${}”来使用变量。 -->
    <property name="log.path" value="自己想增加的内容" />

    <!-- 彩色日志 -->
    <!-- 配置格式变量：CONSOLE_LOG_PATTERN 彩色日志格式 -->
    <!-- magenta:洋红 -->
    <!-- boldMagenta:粗红-->
    <!-- cyan:青色 -->
    <!-- white:白色 -->
    <!-- magenta:洋红 -->
    <property name="CONSOLE_LOG_PATTERN"
              value="%yellow(%date{yyyy-MM-dd HH:mm:ss}) |%highlight(%-5level) |%blue(%thread) |%blue(%file:%line) |%green(%logger) |%cyan(%msg%n)"/>


    <!--输出到控制台-->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->
        <!-- 例如：如果此处配置了INFO级别，则后面其他位置即使配置了DEBUG级别的日志，也不会被输出 -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>INFO</level>
        </filter>
        <encoder>
            <Pattern>${CONSOLE_LOG_PATTERN}</Pattern>
            <!-- 设置字符集 -->
            <charset>UTF-8</charset>
        </encoder>
    </appender>


    <!--输出到文件-->

    <!-- 时间滚动输出 level为 INFO 日志 -->
    <appender name="INFO_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 正在记录的日志文件的路径及文件名 -->
        <file>${log.path}/log_info.log</file>
        <!--日志文件输出格式-->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 每天日志归档路径以及格式 -->
            <fileNamePattern>${log.path}/info/log-info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <!--日志文件保留天数-->
            <maxHistory>15</maxHistory>
        </rollingPolicy>
        <!-- 此日志文件只记录info级别的 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!-- 时间滚动输出 level为 WARN 日志 -->
    <appender name="WARN_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 正在记录的日志文件的路径及文件名 -->
        <file>${log.path}/log_warn.log</file>
        <!--日志文件输出格式-->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
            <charset>UTF-8</charset> <!-- 此处设置字符集 -->
        </encoder>
        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${log.path}/warn/log-warn-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <!--日志文件保留天数-->
            <maxHistory>15</maxHistory>
        </rollingPolicy>
        <!-- 此日志文件只记录warn级别的 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>warn</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>


    <!-- 时间滚动输出 level为 ERROR 日志 -->
    <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 正在记录的日志文件的路径及文件名 -->
        <file>${log.path}/log_error.log</file>
        <!--日志文件输出格式-->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
            <charset>UTF-8</charset> <!-- 此处设置字符集 -->
        </encoder>
        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${log.path}/error/log-error-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <!--日志文件保留天数-->
            <maxHistory>15</maxHistory>
        </rollingPolicy>
        <!-- 此日志文件只记录ERROR级别的 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!--
        <logger>用来设置某一个包或者具体的某一个类的日志打印级别、以及指定<appender>。
        <logger>仅有一个name属性，
        一个可选的level和一个可选的addtivity属性。
        name:用来指定受此logger约束的某一个包或者具体的某一个类。
        level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
              如果未设置此属性，那么当前logger将会继承上级的级别。
    -->
    <!--
        使用mybatis的时候，sql语句是debug下才会打印，而这里我们只配置了info，所以想要查看sql语句的话，有以下两种操作：
        第一种把<root level="INFO">改成<root level="DEBUG">这样就会打印sql，不过这样日志那边会出现很多其他消息
        第二种就是单独给mapper下目录配置DEBUG模式，代码如下，这样配置sql语句会打印，其他还是正常DEBUG级别：
     -->
    <!--开发环境:打印控制台-->
      // 这里要注意一下 看看自己是什么配置 dev test prod
    <springProfile name="dev">
        <!--可以输出项目中的debug日志，包括mybatis的sql日志-->
        <logger name="com.guli" level="INFO" />

        <!--
            root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性
            level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，默认是DEBUG
            可以包含零个或多个appender元素。
        -->
        <root level="INFO">
            <appender-ref ref="CONSOLE" />
            <appender-ref ref="INFO_FILE" />
            <appender-ref ref="WARN_FILE" />
            <appender-ref ref="ERROR_FILE" />
        </root>
    </springProfile>


    <!--生产环境:输出到文件-->
    <springProfile name="pro">

        <root level="INFO">
            <appender-ref ref="CONSOLE" />
            <appender-ref ref="DEBUG_FILE" />
            <appender-ref ref="INFO_FILE" />
            <appender-ref ref="ERROR_FILE" />
            <appender-ref ref="WARN_FILE" />
        </root>
    </springProfile>

</configuration>
```

```java
如果程序运行出现异常,把异常信息添加到输出文档中
 // 在异常类中添加@SLf4j
  并且在异常代码快中添加一段代码,log.error(e.getMessage())
```

