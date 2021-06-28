#  Java 集合框架复习

Java 集合框架主要包括两种类型的容器，一种是集合（Collection），存储一个元素集合，另一种是图（Map），存储键/值对映射。Collection 接口又有 3 种子类型，List、Set 和 Queue，再下面是一些抽象类，最后是具体实现类，常用的有 [ArrayList](https://www.runoob.com/java/java-arraylist.html)、[LinkedList](https://www.runoob.com/java/java-linkedlist.html)、[HashSet](https://www.runoob.com/java/java-hashset.html)、LinkedHashSet、[HashMap](https://www.runoob.com/java/java-hashmap.html)、LinkedHashMap 等等。

![java-collection](https://github.com/zycloud68/learningJava/blob/45263091734cdb1587880eabc37287bf373453a6/Collection/Picture/java-collection.png)

###  集合接口

集合框架定义了一些接口,下面提供每个接口的概述:

| 序号 | 接口描述                                                     |
| ---- | ------------------------------------------------------------ |
| 1    | **collection** 接口:  Collection 是最基本的集合接口，一个 Collection 代表一组 Object，即 Collection 的元素, Java不提供直接继承自Collection的类，**只提供继承于的子接口**(如List和set)。**Collection 接口存储一组不唯一，无序的对象。** |
| 2    | **List接口**:  List接口是一个**有序**的 Collection，使用此接口能够精确的控制每个元素插入的位置，能够通过索引(元素在List中位置，类似于数组的下标)来访问List中的元素，**第一个元素的索引为 0，而且允许有相同的元素。****List 接口存储一组不唯一，有序（插入顺序）的对象**。 |
| 3    | **Set**:  Set 具有与 Collection 完全一样的接口，只是行为上不同，**Set 不保存重复的元素**,**Set 接口存储一组唯一，无序的对象**。 |
| 4    | **SortedSet**:  继承于Set保存有序的集合。                    |
| 5    | **Map**:  Map 接口存储一组键值对象，提供key（键）到value（值）的映射。--> k-v |
| 6    | **Map.Entry**: 描述在一个Map中的一个元素（键/值对）。是一个 Map 的内部接口。 |
| 7    | **SortedMap**:  继承于 Map，使 Key 保持在升序排列。          |

### Set和List的区别

1. Set 接口实例存储的是**无序的，不重复的数据**。List 接口实例存储的是**有序的，可以重复的元素**。
2. Set检索效率低下，删除和插入效率高，插入和删除不会引起元素位置改变 **<实现类有HashSet,TreeSet>**。
3. List和数组类似，可以**动态增长**，根据实际存储的数据的长度自动增长List的长度。**查找元素效率高**，插入删除效率低，因为会引起其他元素位置改变 **<实现类有ArrayList,LinkedList,Vector>** 。

