# buession-core 参考手册


## 数学函数


定义了实用的数学函数。



|  方法   | 说明  |
|  ----  | ----  |
| continuousSum  | 计算两个数之间连续相加之和 |
| rangeValue  | 获取合法范围内的值，如果 value 小于 min，则返回 min；如果 value 大于 max，则返回 max；否则返回 value 本身 |

```java
import com.buession.core.math.Math;

long result = Math.continuousSum(1, 100);
// 5050
```

```java
import com.buession.core.math.Math;

long value = 3;
long min = 4;
long max = 10;
long result = Math.rangeValue(value, min, max);
// 4
```

```java
import com.buession.core.math.Math;

long value = 5;
long min = 4;
long max = 10;
long result = Math.rangeValue(value, min, max);
// 5
```

```java
import com.buession.core.math.Math;

long value = 11;
long min = 4;
long max = 10;
long result = Math.rangeValue(value, min, max);
// 10
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.3.0/com/buession/core/math/package-summary.html)