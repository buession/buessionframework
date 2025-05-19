# buession-redis 参考手册


Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 原生命令保持一致。同时，对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Object.class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-redis</artifactId>
    <version>x.x.x</version>
</dependency>
```


### 介绍

`buession-redis` 是一款基于 jedis 的 redis 操作库，最大的优势就是封装了与 redis 同名、最大程度与 redis 原生参数顺序一致的 API。同时，我们在现代应用中，经常需要读写一个 pojo 对象，`buession-redis` 封装了 `xxxObject` 读写取 redis 中的二进制或 JSON 数据，并反序列化为 POJO 类。大大简化了，我们在代码中对象存取到 redis 中，让我们更专注业务功能的开。能够通过 `com.buession.redis.core.Options` 设置全局选项，如：统一的 Key 前缀，对象基于什么方式序列化和反序列化。


```java
import com.buession.redis.RedisTemplate;
import com.buession.redis.core.Options;
import com.buession.core.serializer.type.TypeReference;
import java.utils.Map;
import java.utils.HashMap;

RedisTemplate redisTemplate = new RedisTemplate(dataSource);

redisTemplate.setOptions(new Options());
redisTemplate.afterPropertiesSet();

// 将 User 对象写进 key 为 user hash 中
redisTemplate.hSet("user", "1", new User());

// 获取 key 为 user ，field 为 1 的 hash 中的数据，并转换为 User
User user = redisTemplate.hGetObject("user", "1", User.class);

// 获取 key 为 user 的 hash 的所有数据，并将值转换为 User
Map<String, User> data = redisTemplate.hGetAllObject("user", "1", new TypeReference<HashMap<String, User>>{});
```


### 展望

目前，`buession-redis` 仅支持 jedis，不支持 lettuce，我们计划在 2.3 ~ 2.5 的版本中计划加入。其实，之前尝试过，但由于两者 API 差异性和使用方式太大，没法很好的做到统一化，就暂时放弃了。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-redis/2.2.0/)