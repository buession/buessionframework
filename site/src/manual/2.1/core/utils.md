# buession-core 参考手册


## 工具类


常用通用性工具类。


### Byte 数组比较

`ByteArrayComparable` 类为 java `Comparable` 的实现，实现了 byte 数组的比较。


### 注解工具

`AnnotationUtils` 继承 `org.springframework.core.annotation.AnnotationUtils` ，在此基础上新增了方法 `hasClassAnnotationPresent(final Class<?> clazz, final Class<? extends Annotation>[] annotations)`、`hasMethodAnnotationPresent(Method method, final Class<? extends Annotation>[] annotations)` 判断类或者方法上是否有待检测的注解中的其中一个注解。


### 断言

`Assert` 和 springframework 中的注解类似，不过逻辑有些相反。


### Byte 工具

`ByteUtils` 可以将 byte 数组转换为 char 或者 char 数组。

```java
import com.buession.core.utils.ByteUtils;

byte[] bytes;
char c = ByteUtils.toChar(bytes);

char[] chars = ByteUtils.toChar(bytes);
```

byte 数组连接。

```java
import com.buession.core.utils.ByteUtils;

byte[] dest;
byte[] source
byte[] result = ByteUtils.concat(dest, source);
```


### Character 工具

`CharacterUtils` 继承 `org.apache.commons.lang3.CharUtils`，可以将 char、char 数组转换为 byte 数组。

```java
import com.buession.core.utils.CharacterUtils;

char c;
byte[] bytes = ByteUtils.toBytes(c);

char[] chars;
byte[] bytes = ByteUtils.toBytes(chars);
```


### 数字工具

`NumberUtils` 提供了对数字相关的操作。

|  方法   | 说明  |
|  ----  | ----  |
| int2bytes  | 将 int 转换为 byte[] |
| bytes2int  | 将 byte[] 转换为 int |
| long2bytes  | 将 long 转换为 byte[] |
| bytes2long  | 将 byte[] 转换为 long |
| float2bytes  | 将 float 转换为 byte[] |
| bytes2float  | 将 byte[] 转换为 float |
| double2bytes  | 将 double 转换为 byte[] |
| bytes2double  | 将 byte[] 转换为 double |

### 字符串工具

`StringUtils` 继承了 `org.apache.commons.lang3.StringUtils`，封装了多字符串更多的操作。


截取字符串

```java
import com.buession.core.utils.StringUtils;

String result = StringUtils.substr("abcde", 1); // bcde
String result = StringUtils.substr("abcde", 1, 2); // bc
```

生成随机字符串

```java
import com.buession.core.utils.StringUtils;

String result = StringUtils.random(length);
```

比较两个 CharSequence 前 length 位是否相等

```java
import com.buession.core.utils.StringUtils;

boolean result = StringUtils.equals("abcd", "abce", 3); // true
boolean result = StringUtils.equals("abcd", "abce", 4); // false
```

忽略大小写比较两个 CharSequence 前 length 位是否相等

```java
import com.buession.core.utils.StringUtils;

boolean result = StringUtils.equalsIgnoreCase("abcd", "Abce", 3); // true
boolean result = StringUtils.equalsIgnoreCase("abcd", "aBce", 4); // false
```


### 拼音工具

`PinyinUtils` 封装了获取中文拼音、拼音首字母的方法。

```java
import com.buession.core.utils.PinyinUtils;

String result = PinyinUtils.getPinyin("中国"); // zhongguo
String result = PinyinUtils.getPinYinFirstChar("中国"); // zg
```

### 随机数工具

`RandomUtils` 封装了随机数的生成。

|  方法        | 说明                                                |
|  ----       | ----                                                |
| nextBoolean | 随机布尔值                                           |
| nextBytes   | 随机字节数组                                          |
| nextInt     | 生成指定范围内的 int 值，默认：0 到 Integer.MAX_VALUE   |
| nextLong    | 生成指定范围内的 long 值，默认：0 到 Long.MAX_VALUE     |
| nextFloat   | 生成指定范围内的 float 值，默认：0 到 Float.MAX_VALUE   |
| nextDouble  | 生成指定范围内的 double 值，默认：0 到 Double.MAX_VALUE |


### Properties 工具

`PropertiesUtils` 封装了对 `Properties` 的操作，主要是 `Properties.getProperty` 的值为字符串，而我们在实际场景中，很多时候其值可能为 int、double。传统方法是，我们在代码中通过 `Properties.getProperty` 获取其值后，对其进行转换；而 `PropertiesUtils` 简化了操作。

```java
import com.buession.core.utils.SystemPropertyUtils;

Integer result = PropertiesUtils.getInteger(properties, key);
Boolean result = PropertiesUtils.getBoolean(properties, key);
```


### System Property 工具

`SystemPropertyUtils` 封装了系统属性或系统环境变量的操作。

设置属性方法 `setProperty` 对 `System.setProperty` 的封装，唯一区别是：`SystemPropertyUtils.setProperty` 的值可以为非字符串，该方法类会将非字符串值转换为字符串再调用 `System.setProperty`。

```java
import com.buession.core.utils.SystemPropertyUtils;

SystemPropertyUtils.setProperty("http.port", 8080);
SystemPropertyUtils.setProperty("http.ssl.enable", false);
```

获取属性方法 `getProperty` 会先通过 `System.getProperty` 进行获取，若未获取到值，再调用 `System.getenv` 进行获取。

```java
String value = System.getProperty(name);

if(Validate.hasText(value) == false){
  value = System.getenv(name);
}
```


### 版本工具

`VersionUtils` 提供了对两个版本值的比较方法和获取类、jar 对应的版本。

```java
import com.buession.core.utils.VersionUtils;

VersionUtils.compare("1.0.0", "1.0.1-beta"); // -1
VersionUtils.compare("1.0.0", "1.0.0r"); // -1
```

规则：dev < alpha = a < beta = b < rc < release < pl = p < 纯数字版本

获取类的版本值

```java
import com.buession.core.utils.VersionUtils;

ByteUtils.determineClassVersion(com.buession.core.utils.VersionUtils.class); // 2.1.0
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.1.0/com/buession/core/utils/package-summary.html)