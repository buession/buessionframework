# buession-core 参考手册


## ID 生成器


基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。

您可以通过 buession framework 提供的 ID 生成器，生成唯一 ID。


接口规范。

```java
public interface IdGenerator<T> {

	/**
	 * 获取下一个 ID
	 *
	 * @return ID
	 */
	T nextId();

}
```


### ID 生成器


|  生成器   | 说明  |
|  ----  | ----  |
| AtomicSimpleIdGenerator  | 基于 AtomicLong 递增的，简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 "-" 过滤掉 |
| AtomicUUIDIdGenerator  | 基于 AtomicLong 递增的，UUID ID 生成器 |
| NanoIDIdGenerator | jnanoid ID 生成器，可通过构造函数指定字符范围、长度 |
| RandomDigitIdGenerator  | 随机数 ID 生成器，返回指定范围内的随机数，默认为 1L ~ Long.MAX_VALUE，可通过构造函数指定 |
| RandomIdGenerator  | 随机字符 ID 生成器，可通过构造函数指定生成长度，默认 16 位 |
| SimpleIdGenerator  | 简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 "-" 过滤掉 |
| SnowflakeIdGenerator  | 雪花算法 ID 生成器，此处不解决时钟回拨的问题，您可以通过构造函数指定开始时间、数据中心 ID、数据中心 ID 所占的位数等等值 |
| UUIDIdGenerator  | UUID ID 生成器 |

```java
import com.buession.core.id.AtomicUUIDIdGenerator;
import com.buession.core.id.NanoIDIdGenerator;
import com.buession.core.id.SnowflakeIdGenerator;
import com.buession.core.id.UUIDIdGenerator;
import com.buession.core.id.SimpleIdGenerator;

AtomicUUIDIdGenerator idGenerator = new AtomicUUIDIdGenerator();
idGenerator.nextId(); // 00000000-0000-0000-0000-000000000001
idGenerator.nextId(); // 00000000-0000-0000-0000-000000000002

NanoIDIdGenerator idGenerator = new NanoIDIdGenerator();
idGenerator.nextId(); // omRdTPCug5z_Uk1E_x3ozu3Avyyl3XSK

SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();
idGenerator.nextId(); // 170602258864545792

UUIDIdGenerator idGenerator = new UUIDIdGenerator();
idGenerator.nextId(); // 8634a166-e7d6-4160-85eb-3433107de5a4

SimpleIdGenerator idGenerator = new SimpleIdGenerator();
idGenerator.nextId(); // 26d9ed57fdad443894b988eeabc69c05
```

* 注：关于雪花算法、jnanoid 算法的可自行搜索。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.1.0/com/buession/core/id/package-summary.html)