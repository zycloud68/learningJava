# Java HashSet

HashSet 基于 HashMap 来实现的，是一个**不允许有重复元素**的集合。

HashSet **允许有 null 值**。

HashSet 是**无序**的，即不会记录插入的顺序。

HashSet **不是线程安全的**， 如果多个线程尝试同时修改 HashSet，则最终结果是不确定的。 您必须在多线程访问时**显式同步**对 HashSet 的并发访问。

HashSet 实现了 Set 接口。

HashSet 中的元素实际上是**对象**，一些常见的**基本类型**可以使用它的包装类。

基本类型： boolean，byte，short，int，long，float，double，char。

引用类型： Boolean，Byte，Short，Integer，Long，Float，Character。

### 添加元素   add()

```java
import java.util.HashSet;

public class RunoobTest {
    public static void main(String[] args) {
    HashSet<String> sites = new HashSet<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Zhihu");
        sites.add("Runoob");  // 重复的元素不会被添加
        System.out.println(sites);
    }
}
// 输出的结果为：
[Google, Runoob, Zhihu, Taobao]
```

### 判断元素是否存在   contains()

```java
// 引入 HashSet 类      
import java.util.HashSet;

public class RunoobTest {
    public static void main(String[] args) {
    HashSet<String> sites = new HashSet<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Zhihu");
        sites.add("Runoob");  // 重复的元素不会被添加
        System.out.println(sites.contains("Taobao"));
    }
}
// 输出的结果为：
true
```

### 删除元素    remove()

```java
import java.util.HashSet;

public class RunoobTest {
    public static void main(String[] args) {
    HashSet<String> sites = new HashSet<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Zhihu");
        sites.add("Runoob");     // 重复的元素不会被添加
        sites.remove("Taobao");  // 删除元素，删除成功返回 true，否则为 false
        System.out.println(sites);
    }
}
// 输出的结果为：
[Google, Runoob, Zhihu]
```

### 计算元素大小   size()

```java
import java.util.HashSet;

public class RunoobTest {
    public static void main(String[] args) {
    HashSet<String> sites = new HashSet<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Zhihu");
        sites.add("Runoob");     // 重复的元素不会被添加
        sites.remove("Taobao");  // 删除元素，删除成功返回 true，否则为 false
        System.out.println(sites);
        System.out.println(sites.size());
    }
}
// 输出的结果为：
[Google, Runoob, Zhihu]
4
```

### 迭代HashSet  for-each
