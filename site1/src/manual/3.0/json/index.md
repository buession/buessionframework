# buession-json 参考手册


主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-json</artifactId>
    <version>x.x.x</version>
</dependency>
```


封装了大量基于 `jackson` 的注解。


### 注解


|  注解   | 说明  |
|  ----  | ----  |
| CalendarUnixTimestamp | java.util.Calendar 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Calendar 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Calendar |
| DateUnixTimestamp  | java.util.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Date |
| SqlDateUnixTimestamp  | java.sql.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Date |
| TimestampUnixTimestamp  | java.sql.Timestamp 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Timestamp 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Timestamp |
| JsonEnum2Map  | 枚举和 java.util.Map 序列化和反序列化；通过该注解，可以将枚举序列化为 java.util.Map；将 java.util.Map 反序列化为枚举 |
| Sensitive  | 通过该注解可以实现数据的脱敏 |


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-json/3.0.0/)