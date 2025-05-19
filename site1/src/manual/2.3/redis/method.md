# buession-redis 参考手册


## 方法

`buession-redis` `BaseRedisTemplate` 的方法以及参数计划与原生 redis 命名保持一致。复杂的参数会通过 Builder 进行参数构建，在多个值中进行选择的将定义成枚举，规避出错的几率。

```java
import com.buession.redis.BaseRedisTemplate;

BaseRedisTemplate redisTemplate = new BaseRedisTemplate(dataSource);

redisTemplate.afterPropertiesSet();

// 删除哈希表 key 中的一个或多个指定域
redisTemplate.hDel("user", "1", "2", "3");

// 检查给定 key 是否存在
redisTemplate.exists("user");

// 获取列表 key 中，下标为 index 的元素
redisTemplate.lIndex("user", 1);

// 如果键 key 已经存在并且它的值是一个字符串，将 value 追加到键 key 现有值的末尾
redisTemplate.append("key", "value 1");
```

`BaseRedisTemplate` 实现了 redis 的原生操作，`RedisTemplate` 继承了 `BaseRedisTemplate` ，在此基础上实现了将 redis 中的二进制或者 JSON 格式的值，反序列化为一个类。

```java
import com.buession.redis.RedisTemplate;

RedisTemplate redisTemplate = new RedisTemplate(dataSource);

redisTemplate.afterPropertiesSet();

// 获取列表 key 中，下标为 index 的元素，并反序列化为 User 类
User user = redisTemplate.lIndexObject("user", 1, User.class);
```

序列化和反序列化，基于 [`buession-core` 序列化和反序列化](/manual/2.3/core/serializer.html) 扩展而来，序列化或反序列化出错时会直接返回 null，而忽略异常，默认使用 `com.buession.redis.serializer.JacksonJsonSerializer` 序列化为 JSON。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-redis/2.3.0/com/buession/redis/core/command/package-summary.html)