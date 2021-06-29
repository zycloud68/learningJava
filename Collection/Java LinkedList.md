# Java LinkedList

链表（Linked list）是一种常见的基础数据结构，是一种线性表，但是并不会按线性的顺序存储数据，而是在每一个节点里存到下一个节点的地址,链表可分为单向链表和双向链表。

一个单向链表包含两个值: 当前节点的值和一个指向下一个节点的链接。

![image-20210629144748393](/Users/zhang/Library/Application Support/typora-user-images/image-20210629144748393.png)

### LinkedList与ArrayList的区别  (推荐使用ArrayList)

与ArrayList相比,LinkedList对元素的增加和删除的操作效率更高,但是对元素的查找和修改的效率更低。

| ArrayList                          | LinkedList                                               |
| ---------------------------------- | -------------------------------------------------------- |
| 对元素的查找和修改的效率更高       | 对元素的增加和删除的操作效率更高                         |
| 频繁的访问列表的某一个元素         | 循环迭代来访问列表中的某些元素                           |
| 只需要在列表末尾进行添加和删除元素 | 频繁的在列表开头、中间、末尾等位置进行添加和删除元素操作 |
| 对列表中的随机元素更加高效         |                                                          |

### 以下情况推荐使用LinkedList

- 在列表开头添加元素  **addFirst()**
- 在列表结尾添加元素  **addLast()**
- 在列表删除开头元素  **removeFirst()**
- 在列表删除结尾元素  **removeLast()**
- 获取列表头部元素  **getFirst()**

### 实例

```java
// 引入 LinkedList 类
import java.util.LinkedList;

public class RunoobTest {
    public static void main(String[] args) {
        LinkedList<String> sites = new LinkedList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        for (String i : sites) {
            System.out.println(i);
        }
    }
}
// 输入的结果为:
Google
Runoob
Taobao
Weibo
```

