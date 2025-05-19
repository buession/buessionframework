# buession-core 参考手册


## 验证器


数据验证器及其注解。

该 API 提供了大量通用的适用于本土化的常用验证方法，如：判断是否为 null、判断是否不为 null、判断（字符串、数组、集合、Map等）是否为空、判断（字符串、数组、集合、Map等）是否不为空、IP 地址合法性验证、ISBN 合法性验证、身份证号码验证、QQ 验证。

并提供对应的基于 `javax.validation` 的校验注解。


#### 验证是否为 null

判断任意对象是否为 null

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isNull(obj);
```

#### 验证是否不为 null

判断任意对象是否不为 null

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isNotNull(obj);
```

#### 判断字符串是否为空白字符串

判断字符串是否为空白字符串，为 null 或长度为 0 时也返回 true；其余情况，字符串中含有任意可打印字符串则为 false

```java
import com.buession.core.validator.Validate;

String str = null;
boolean result = Validate.isBlank(str); // true

String str = "";
boolean result = Validate.isBlank(str); // true

String str = "\\r\\n";
boolean result = Validate.isBlank(str); // true

String str = "\\r\\na";
boolean result = Validate.isBlank(str); // false
```

* 注：`isNotBlank` 与之相反


#### 判断是否为空

`isEmpty` 可判断字符串、数组、集合、Map 是否为空，即：为 null 或长度/大小为 0 时，表示为空

```java
import com.buession.core.validator.Validate;

String str = null;
boolean result = Validate.isEmpty(str); // true

String str = " ";
boolean result = Validate.isEmpty(str); // false

boolean result = Validate.isEmpty(new String[]{}); // true

boolean result = Validate.isEmpty(new Integer[]{1}); // false
```

* 注：`isNotEmpty` 与之相反


#### 判断是否在两个数之间

`isBetween` 可判断一个整型或浮点型，是否在两个整型或者浮点型数字之间

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isBetween(2, 1, 3); // true

boolean result = Validate.isBetween(2, 2, 3); // false
```

可通过参数设置是否包含边界值

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isBetween(2, 1, 3, true); // true

boolean result = Validate.isBetween(2, 2, 3, true); // true
```


#### 判断是否为电话号码

`isTel` 可判断一个字符串是否为电话号码，仅支持普通座机号码，不支持 110、400、800等特殊号码。格式，需符合：86-区号-电话号码、区号-电话号码、（区号）电话号码、(区号)电话号码、电话号码。可通过参数 `com.buession.core.validator.routines.TelValidator.AreaCodeType` 指定区号的控制策略。仅支持中国的电话号码。

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isTel("028-12345678"); // true

boolean result = Validate.isTel("028-02345678"); // false
```


#### 判断是否为手机号码

`isMobile` 可判断一个字符串是否为手机号码。仅支持中国的手机号码。

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isMobile("028-12345678"); // false

boolean result = Validate.isMobile("13800138000"); // true
```


#### 判断是否为邮政编码

`isPostCode` 可判断一个字符串是否为邮政编码。仅支持中国的邮政编码。

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isPostCode("043104"); // false

boolean result = Validate.isPostCode("643104"); // true
```


#### 判断是否为 QQ 号码

`isQQ` 可判断一个字符串是否为 QQ 号码。

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isQQ("043104"); // false

boolean result = Validate.isQQ("251329041"); // true
```


#### 判断是否为身份证号码

`isIDCard` 可判断一个字符串是否合法的身份证号码。仅支持 18 位的身份证号码。身份证号码需符合其[身份证号码编排规律](https://baijiahao.baidu.com/s?id=1553065522587070&wfr=spider&for=pc)。

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isIDCard("xxxxxxxxxxxxxxxxx");

boolean result = Validate.isIDCard("xxxxxxxxxxxxxxxxx");
```

可以和身份证号码进行比对，其实该方法的实际作用不是很大，只是在业务系统里面有需要填写身份证号码和出生日期时，简化验证出生日期和身份证号码中的出生日期是否一致。

```java
import com.buession.core.validator.Validate;

boolean result = Validate.isIDCard("xxxxxxxxxxxxxxxxx", true, "2000-01-01");
```

其它，更多方法可以[参考手册](https://javadoc.io/static/com.buession/buession-core/2.0.2/com/buession/core/validator/Validate.html)。


### 注解

javax 的 validation 是 Java 定义的一套基于注解的数据校验规范，buession framework 基于 javax.validation 实现了 `Validate` 中所有验证方法的校验注解。


|  注解       | 验证的数据类型                                                    | 说明                           |
|  ----      | ----                                                             | ----                          |
| @Alnum     | CharSequence 的子类型，Character                                  | 验证注解的元素值是否为数字        |
| @Alpha     | CharSequence 的子类型，Character                                  | 验证注解的元素值是否为数字        |
| @Numeric   | CharSequence 的子类型，Character                                  | 验证注解的元素值是否为数字        |
| @Between   | short、int、double 等任何 Number 的子类型                          | 验证注解的元素值是否为在两个数之间 |
| @Empty     | CharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组 | 验证注解的元素值是否为空         |
| @NotEmpty  | CharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组 | 验证注解的元素值是否不为空        |
| @HasText   | CharSequence 的子类型                                             | 验证注解的元素值是否有非空字符    |
| @IDCard    | CharSequence 的子类型                                             | 验证注解的元素值是否有非空字符    |
| @Ip        | CharSequence 的子类型                                             | 验证注解的元素值是否有非空字符    |
| @Isbn      | CharSequence 的子类型                                             | 验证注解的元素值是否有非空字符    |
| @MimeType  | CharSequence 的子类型                                             | 验证注解的元素值是否有非空字符    |
| @Mobile    | CharSequence 的子类型                                             | 验证注解的元素值是否有非空字符    |
| @Null      | 任意类型                                                          | 验证注解的元素值是否为 null      |
| @NotNull   | 任意类型                                                          | 验证注解的元素值是否为 null      |
| @Port      | Integer                                                          | 验证注解的元素值是否为 null      |
| @PostCode  | CharSequence 的子类型                                             | 验证注解的元素值是否为 null      |
| @QQ        | CharSequence 的子类型                                             | 验证注解的元素值是否为 null      |
| @Tel       | CharSequence 的子类型                                             | 验证注解的元素值是否为 null      |
| @Xdigit    | CharSequence 的子类型                                             | 验证注解的元素值是否为 null      |


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.0.2/com/buession/core/validator/package-summary.html)