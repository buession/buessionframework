# buession-core 参考手册


## 日期时间


日期、时间工具。目前，仅有少量的 API，参考 PHP 中的日期时间函数而来。

获取当前 Unix 时间戳（秒）：

```java
import com.buession.core.datetime.DateTime;

DateTime.unixtime();
```

该方法我们在实际业务中经常用到。

以 "msec sec" 的格式返回当前 Unix 时间戳和微秒数：

```java
import com.buession.core.datetime.DateTime;

DateTime.microtime();
// 1657171717 948000
```

该方法参考 PHP 的 microtime 函数而来。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.1.0/com/buession/core/datetime/package-summary.html)