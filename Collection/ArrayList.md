# Java ArrayList

ArrayList<E> 类是一个可以动态修改的数组，与普通数组的区别就是它是没有固定大小的限制，我们可以添加或删除元素。ArrayList 继承了 AbstractList ，并实现了 List 接口。**<E>** 只能为引用数据类型(Boolean,Byte,Short,Integer,Long,Float,Double,Character),并且List从**0**开始计数

![ArrayList-1-768x406-1](https://github.com/zycloud68/learningJava/blob/a505ffee59d71b6222c9932a86ffbc96c5125bc5/Collection/Picture/ArrayList-1-768x406-1.png)

### 添加元素 add()

```java
import java.util.ArrayList;

public class listTest {
    public static void main(String[] args) {
        ArrayList<String> lists = new ArrayList<String>(); //后面的String类型可以不写
        lists.add("Google");
        lists.add("Yahoo");
        lists.add("Taobao");
        lists.add("Jingdong");
        System.out.println(lists);
    }
}
// 输入结果为:
[Google,Yahoo,Taobao,Jingdong]
```

### 获取元素-访问 get()

```java
import java.util.ArrayList;

public class listTest {
    public static void main(String[] args) {
        ArrayList<String> lists = new ArrayList<String>(); //后面的String类型可以不写
        lists.add("Google");
        lists.add("Yahoo");
        lists.add("Taobao");
        lists.add("Jingdong");
        System.out.println(lists.get(1));
    }
}
// 输出的结果为:
Yahoo
```

### 修改元素

```java
import java.util.ArrayList;

public class listTest {
    public static void main(String[] args) {
        ArrayList<String> lists = new ArrayList<String>(); //后面的String类型可以不写
        lists.add("Google");
        lists.add("Yahoo");
        lists.add("Taobao");
        lists.add("Jingdong");
        System.out.println(lists);
        lists.set(2,"baidu");
    }
}
// 输出的结果为:
[Google,Yahoo,Taobao,Jingdong]
[Google,Yahoo,baidu,Jingdong]
```

### 删除元素 remove()

```java
import java.util.ArrayList;

public class listTest {
    public static void main(String[] args) {
        ArrayList<String> lists = new ArrayList<String>(); //后面的String类型可以不写
        lists.add("Google");
        lists.add("Yahoo");
        lists.add("Taobao");
        lists.add("Jingdong");
      	lists.remove(3) // 删除第四个元素
        System.out.println(lists);
        
    }
}
// 输出的结果为:
[Google,Yahoo,Taobao]
```

###  迭代数组列表

```java
import java.util.ArrayList;

public class RunoobTest {
    public static void main(String[] args) {
        ArrayList<String> sites = new ArrayList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        for (int i = 0; i < sites.size(); i++) {
            System.out.println(sites.get(i));
        }
    }
}
// 输入结果为:
Google
Runoob
Taobao
Weibo
```

###  for-each来迭代元素

```java
import java.util.ArrayList;

public class RunoobTest {
    public static void main(String[] args) {
        ArrayList<String> sites = new ArrayList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        for (String i : sites) {   //idea中快捷键 sites.for
            System.out.println(i);
        }
    }
}
// 输入结果为:
Google
Runoob
Taobao
Weibo
```

### ArrayList 排序

Collections 类也是一个非常有用的类，位于 java.util 包中，提供的 sort() 方法可以对**字符或数字**列表进行排序。

```java
import java.util.ArrayList;
import java.util.Collections;  // 引入 Collections 类

public class RunoobTest {
    public static void main(String[] args) {
        ArrayList<String> sites = new ArrayList<String>();
        sites.add("Taobao");
        sites.add("Wiki");
        sites.add("Runoob");
        sites.add("Weibo");
        sites.add("Google");
        Collections.sort(sites);  // 字母排序
        for (String i : sites) {
            System.out.println(i);
        }
    }
}
// 输出的结果为:   //按照字母顺序来排列
Google
Runoob
Taobao
Weibo 
```

 
