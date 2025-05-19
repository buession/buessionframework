# buession-core 参考手册


## 收集器

数组、Map、集合的工具类


### 数组

数组工具类 `Arrays` 继承自 `org.apache.commons.lang3.ArrayUtils` 封装了元素是否存在检测、元素索引位置获取、拼接成字符串、转换为 `List`、`Set` 以及字符串类型的数组、数组合并、数组元素操作等方法。


检测数组 array 中是否存在元素 element：

```java
import com.buession.core.collect.Arrays;

boolean result = Arrays.contains(array, element);
```


返回元素 element 在数组 array 中第一次出现的位置的索引，不存在返回 -1：

```java
import com.buession.core.collect.Arrays;

int result = Arrays.indexOf(array, element);
```


返回元素 element 在数组 array 中最后一次出现的位置的索引，不存在返回 -1：

```java
import com.buession.core.collect.Arrays;

int result = Arrays.lastIndexOf(array, element);
```


将字符串拼接会字符串：

```java
import com.buession.core.collect.Arrays;

int[] array = {1, 2, 3};
String result = Arrays.toString(array);
// 1, 2, 3
```

可以通过参数 `glue` 指定连接符，默认为：`", "`

```java
import com.buession.core.collect.Arrays;

int[] array = {1, 2, 3};
String glue = "-";
String result = Arrays.toString(array, glue);
// 1-2-3
```


可以通过方法 toList、toSet 转换为 List 和 Set：

```java
import com.buession.core.collect.Arrays;
import java.util.List;
import java.util.Set;

int[] array = {1, 2, 3};
List<Integer> list = Arrays.toList(array);
Set<Integer> set = Arrays.toSet(array);
```


将数组转换为字符串类型的数组：

```java
import com.buession.core.collect.Arrays;

int[] array = {1, 2, 3};
String[] result = Arrays.toStringArray(array);
```


将数组进行合并：

```java
import com.buession.core.collect.Arrays;

String[] result = Arrays.toStringArray(array1, array2, array3);
```


对数组元素进行操作：

```java
import com.buession.core.collect.Arrays;

String[] array = {"A", "B", "C"};
String[] result = Arrays.map(array, String.class, fn);
```

第二个参数为数组元素类型，第三个参数为 `java.util.function.Function` 的实现


### Lists

List 工具类 `Lists` 实现了将元素拼接成字符串、转换为 Set 操作。


将字符串拼接会字符串：

```java
import com.buession.core.collect.Lists;
import com.buession.core.builder.ListBuilder;

List<Integer> list = ListBuilder.<Integer>create().add(1).add(2).add(3).build();
String result = Lists.toString(list);
// 1, 2, 3
```

可以通过参数 `glue` 指定连接符，默认为：`", "`

```java
import com.buession.core.collect.Lists;
import com.buession.core.builder.ListBuilder;

List<Integer> list = ListBuilder.<Integer>create().add(1).add(2).add(3).build();
String glue = "-";
String result = Lists.toString(list);
// 1-2-3
```


可以通过方法 toSet 将 List 转换为 Set：

```java
import com.buession.core.collect.Lists;
import com.buession.core.builder.ListBuilder;
import java.util.List;
import java.util.Set;

List<Integer> list = ListBuilder.<Integer>create().add(1).add(2).add(3).build();
Set<Integer> set = Lists.toSet(list);
```


### Sets

Sett 工具类 `Sets` 实现了将元素拼接成字符串、转换为 List 操作。


将字符串拼接会字符串：

```java
import com.buession.core.collect.Sets;
import com.buession.core.builder.SetBuilder;

Set<Integer> set = SetBuilder.<Integer>create().add(1).add(2).add(3).build();
String result = Sets.toString(set);
// 1, 2, 3
```

可以通过参数 `glue` 指定连接符，默认为：`", "`

```java
import com.buession.core.collect.Sets;
import com.buession.core.builder.SetBuilder;

Set<Integer> set = SetBuilder.<Integer>create().add(1).add(2).add(3).build();
String glue = "-";
String result = Sets.toString(list);
// 1-2-3
```


可以通过方法 toList 将 Set 转换为 List：

```java
import com.buession.core.collect.Lists;
import com.buession.core.builder.ListBuilder;
import java.util.List;
import java.util.Set;

Set<Integer> set = SetBuilder.<Integer>create().add(1).add(2).add(3).build();
List<Integer> list = Sets.toList(set);
```


### Maps

Map 工具类 `Maps` 实现了对 key 和 value 进行操作，实现了将 value 转换为 List 和 Set。


对 Map 进行操作：

```java
import com.buession.core.collect.Maps;
import java.util.Map;
import java.util.HashMap;

Map<String, Object> maps = new HashMap<>();
Map<String, String> result = Maps.map(maps, (key)->key, (value)->value == null ? null : value.toString());
```

第二个、第三参数为 `java.util.function.Function` 的实现


可以通过方法 toList 将 Map 的 value 转换为 List：

```java
import com.buession.core.collect.Maps;
import com.buession.core.builder.ListBuilder;
import java.util.List;

List<T> list = Maps.toList(maps);
```


可以通过方法 toSet 将 Map 的 value 转换为 Set：

```java
import com.buession.core.collect.Maps;
import com.buession.core.builder.ListBuilder;
import java.util.Set;

Set<T> set = Maps.toSet(maps);
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.2.0/com/buession/core/collect/package-summary.html)