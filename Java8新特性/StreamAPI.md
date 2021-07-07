# Stream API

对数据进行操作! 跟IO流是不一样的,是数据渠道,用于数据源(集合,数组等)所生成的元素序列.**"集合讲的是数据,流讲的是计算".**

##### 注意:

1. Stream自己不会存储元素.
2. Stream**不会改变源对象**,相反,它们**会返回一个持有结果的新Stream**.
3. Stream操作是**延迟执行**的,这意味着它们会等到需要结果的时候才执行.
### 创建Stream
```java
// 一. 创建Stream
1. 可以通过Collection系列提供的Stream()或者paralleleStream()
List<String> list = new ArrayList<>();
Stream<String> stream = list.stream();
2. 通过Arrays中的静态方法Stream()获取数组流
XX xx = new XX();
Stream<XX>Arrays.stream(xx);
3. 通过Stream类中的静态方法of()
Stream<String> stream = Stream.of("aa","bb","cc");
4. 创建无限流(没有穷尽)
Stream.literator()
```
### 中间操作

```java
// 二. 中间操作
/**
筛选与切片
filter-接受Lambda,从流中排除某些元素
limit()-截断流,使其元素不超过给定的数量.
skip(n) - 跳过元素,返回一个扔掉的前n个元素的流,若扔掉的不足n个,则返回一个空流,与limit(n)互补.
distinct-筛选,通过流所生成的元素的hashCode()和equals()去除重复元素.
*/
```

##### 实例操作

```java
@Test 
public void test(){
   employees.stream().filter((e) -> e.getAge()>35)
     .forEach(System.out::println);
 }
```

多个中间操作可以连接起来形成一个流水线工作,**除非流水线触发终止操作**,否则**中间操作不会执行任何处理**,而在**终止操作事一次性全部处理,称为"惰性求值"**.

内部迭代: 迭代操作由StreamAPI完成.

```java
@Test 
public void test(){
   employees.stream().filter((e) -> e.getAge()>35)
     .forEach(System.out::println);
 }
```

外部迭代:

```java
@Test 
public void test(){
  Iterator<Student> it= student.iterator();
  while(it.hasNext()){
    System.out.println(it.next());
  }
}
```

