# buession-core 参考手册


## 构建器


Map、集合的便捷式构建，减少您的代码行数。


您需要往 Map、List 中添加元素的传统写法是：

```java
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

List<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

Map<String, Object> map = new HashMap<>();
map.put("a", "A");
map.put("b", "B");
map.put("c", "C");
```

而当您使用 buession framework 可以这么写：

```java
import com.buession.core.builder.ListBuilder;
import com.buession.core.builder.MapBuilder;
import java.util.List;
import java.util.Map;

List<String> list = ListBuilder.<String>create().add("A").add("B").add("C").build();

Map<String, Object> map = MapBuilder.<String, Object>create().put("a", "A").put("b", "B").put("c", "C");
```

此时的 List、Map 的实现类是 java.util.ArrayList、java.util.HashMap；您可以通过 create 指定其实现类。

```java
import com.buession.core.builder.ListBuilder;
import com.buession.core.builder.MapBuilder;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;

List<String> list = ListBuilder.<String>create(LinkedList.class).add("A").add("B").add("C").build();

Map<String, Object> map = MapBuilder.<String, Object>create(LinkedHashMap.class).put("a", "A").put("b", "B").put("c", "C");
```

* 注：实现类必须为 public，且必须有无参数的访问权限为 public 的构造函数


当您有 value 为 null 时，不添加到 List 时，传统写法：

```java
import java.util.ArrayList;
import java.util.List;

String value = null;
List<String> list = new ArrayList<>();

if(value != null){
	list.add(value);
}
```

而当您使用 buession framework 可以这么写：

```java
import com.buession.core.builder.ListBuilder;
import java.util.List;

String value = null;
List<String> list = ListBuilder.<String>create().addIfPresent(value).build();
```

Map、Set、Queue 同理。


### 便捷方法


|  方法   | 说明  |
|  ----  | ----  |
| <V> List<V> ListBuilder.epmty()  | 创建空的 V 类型的 List 对象 |
| <V> List<V> ListBuilder.of()  | 创建空的 V 类型的 List 对象 |
| <V> List<V> ListBuilder.of(V value)  | 创建仅有一个元素的 V 类型的 List 对象 |
| <V> Queue<V> QueueBuilder.epmty()  | 创建空的 V 类型的 Queue 对象 |
| <V> Queue<V> QueueBuilder.of()  | 创建空的 V 类型的 Queue 对象 |
| <V> Queue<V> QueueBuilder.of(V value)  | 创建仅有一个元素的 V 类型的 Queue 对象 |
| <V> Set<V> SetBuilder.epmty()  | 创建空的 V 类型的 Set 对象 |
| <V> Set<V> SetBuilder.of()  | 创建空的 V 类型的 Set 对象 |
| <V> Set<V> SetBuilder.of(V value)  | 创建仅有一个元素的 V 类型的 Set 对象 |
| <K, V> Map<K, V> MapBuilder.epmty()  | 创建空的 Key 为 K 类型，值为 V 类型的 Map 对象 |
| <K, V> Map<K, V> MapBuilder.of()  | 创建空的 Key 为 K 类型，值为 V 类型的 Map 对象 |
| <K, V> Map<K, V> MapBuilder.of(V value)  | 创建仅有一个元素的 Key 为 K 类型，值为 V 类型的 Map 对象 |


empty 与 jdk Collections.emptyList()、Collections.emptySet()、Collections.emptyMap() 的本质区别是返回的 List 实例不同，该方法创建的 List、Set、Map 实例是允许对 List、Set、Map 做操作，而 jdk Collections 创建的 List、Set、Map 则是不允许的。两个 of 方法均同理。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.2.0/com/buession/core/builder/package-summary.html)