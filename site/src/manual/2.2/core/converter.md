# buession-core 参考手册


## 构建器


数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。

接口定义：

```java
@FunctionalInterface
public interface Converter<S, T> {

	T convert(final S source);

}
```

将类似为 S 的对象转换为类型为 T 的对象。


### 内置转换器


|  转换器   | 说明  |
|  ----  | ----  |
| ArrayConverter<S, T>  | 将 S 类型的数组转换为 T 类型的数组 |
| EnumConverter<E extends Enum<E>>  | 枚举转换器，将字符串转换为枚举 E |
| BinaryEnumConverter<E extends Enum<E>>  | 枚举转换器，将 byte 数组转换为枚举 E |
| BooleanStatusConverter  | 将布尔值转换为 `com.buession.lang.Status` |
| StatusBooleanConverter  | 将 `com.buession.lang.Status` 转换为布尔值 |
| PredicateStatusConverter<T>  | 通过 `java.util.function.Predicate` 对某种数据类型的数据进行判断结果转换为 `com.buession.lang.Status` |
| ListConverter<S, T>  | 将 S 类型的 List 对象转换为 T 类型的 List 对象 |
| SetConverter<S, T>  | 将 S 类型的 Set 对象转换为 T 类型的 Set 对象 |
| MapConverter<SK, SV, TK, TV>  | 将 Key 为 SK 类型、值为 SV 类型的 Map 对象转换为 Key 为 TK 类型、值为 TV 类型的 Map |

将 key 为 Integer 类型，value 为 Object 类型的 Map 转换成 key 为 String 类型，value 为 String 类型的 Map 对象

```java
import com.buession.core.converter.MapConverter;
import java.util.Map;

Map<Integer, Object> source;
Map<String, String> target;
MapConverter<Integer, Object, String, String> converter = new MapConverter<>();

target = converter.convert(source);
```

将字符串转换为枚举

```java
import com.buession.core.converter.EnumConverter;
import com.buession.lang.Gender;

Gender target;
EnumConverter<Gender> converter = new EnumConverter<>(Gender.class);

target = converter.convert("FEMALE");
// Gender.FEMALE

target = converter.convert("F");
// null
```


### POJO 类映射

我们通过 `com.buession.core.converter.mapper.Mapper` 接口约定了，基于 [mapstruct](https://mapstruct.org/) POJO 类的映射接口规范。关于 mapstruct 的更多文章，可通过搜索引擎自行搜索。

```java
public interface Mapper<S, T> {

	/**
	 * 将源对象映射到目标对象
	 *
	 * @param object
	 * 		源对象
	 *
	 * @return 目标对象实例
	 */
	T mapping(S object);

	/**
	 * 将源对象数组映射到目标对象数组
	 *
	 * @param object
	 * 		源对象数组
	 *
	 * @return 目标对象实例数组
	 */
	T[] mapping(S[] object);

	/**
	 * 将源 list 对象映射到目标 list 对象
	 *
	 * @param object
	 * 		源 list 对象
	 *
	 * @return 目标对象 list 实例
	 */
	List<T> mapping(List<S> object);

	/**
	 * 将源 set 对象映射到目标 set 对象
	 *
	 * @param object
	 * 		源 set 对象
	 *
	 * @return 目标对象 set 实例
	 */
	Set<T> mapping(Set<S> object);

}
```

我们还可以使用工具类 `com.buession.core.converter.mapper.PropertyMapper` 将值从提供的源映射到目标，此方法来拷贝并简单修改于：`springboot` 中的 `org.springframework.boot.context.properties.PropertyMapper`，和原生 `springboot` 中的用法一样；并在此基础上增加了方法 `alwaysApplyingWhenHasText()`，用于判断映射源是否为 `null` 或者是否含有字符串。

```java
import com.buession.core.converter.mapper.PropertyMapper;

User source = new User();
User target = new User();

PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
propertyMapper.form(source::getId).to(target:setId)
// null

PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
propertyMapper.form(source::getUsername).to(target:setUsername)
// null
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.2.0/com/buession/core/converter/package-summary.html)