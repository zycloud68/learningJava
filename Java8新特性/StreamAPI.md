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
### sorted()   --自然排序

1. sorted()    --自然排序(Comparable)

2. sorted(Comparator com)   --定制排序(Comparator)

   

##### 实例

```java
/*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	 */
	@Test
	public void test2(){
		emps.stream()
			.map(Employee::getName)
			.sorted()
			.forEach(System.out::println);
		
		System.out.println("------------------------------------");
		
		emps.stream()
			.sorted((x, y) -> {
				if(x.getAge() == y.getAge()){
					return x.getName().compareTo(y.getName());
				}else{
					return Integer.compare(x.getAge(), y.getAge());
				}
			}).forEach(System.out::println);
	}
```

### 终止操作

```java
public class TestStreamAPI2 {
	
	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66, Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);
	
	//3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
	@Test
	public void test1(){
			boolean bl = emps.stream()
				.allMatch((e) -> e.getStatus().equals(Status.BUSY));
			
			System.out.println(bl);
			
			boolean bl1 = emps.stream()
				.anyMatch((e) -> e.getStatus().equals(Status.BUSY));
			
			System.out.println(bl1);
			
			boolean bl2 = emps.stream()
				.noneMatch((e) -> e.getStatus().equals(Status.BUSY));
			
			System.out.println(bl2);
	}
	
	@Test
	public void test2(){
		Optional<Employee> op = emps.stream()
			.sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
			.findFirst();
		
		System.out.println(op.get());
		
		System.out.println("--------------------------------");
		
		Optional<Employee> op2 = emps.parallelStream()
			.filter((e) -> e.getStatus().equals(Status.FREE))
			.findAny();
		
		System.out.println(op2.get());
	}
	
	@Test
	public void test3(){
		long count = emps.stream()
						 .filter((e) -> e.getStatus().equals(Status.FREE))
						 .count();
		
		System.out.println(count);
		
		Optional<Double> op = emps.stream()
			.map(Employee::getSalary)
			.max(Double::compare);
		
		System.out.println(op.get());
		
		Optional<Employee> op2 = emps.stream()
			.min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		
		System.out.println(op2.get());
	}
	
	//注意：流进行了终止操作后，不能再次使用
	@Test
	public void test4(){
		Stream<Employee> stream = emps.stream()
		 .filter((e) -> e.getStatus().equals(Status.FREE));
		
		long count = stream.count();
		
		stream.map(Employee::getSalary)
			.max(Double::compare);
	}
}

```


