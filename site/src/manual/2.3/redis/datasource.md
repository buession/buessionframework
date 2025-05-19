# buession-redis 参考手册


## 数据源

`buession-redis` 基于数据源 `DataSource` 连接 redis，其机制类似 JDBC 的 DataSource。
通过，数据源可以配置，redis 的用户名、密码、连接超时、读取超时、连接池、SSL等等。

数据源 `DataSource` 包括三个子接口：

* StandaloneDataSource：单机模式数据源
* SentinelDataSource：哨兵模式数据源
* ClusterDataSource：集群模式数据源

jedis 和后续的 lettuce 分别实现这三个接口，用于创建不通模式的数据源，数据源中实现了连接池的创建。

在原始的 jedis 或者 spring-data-redis 中，密码为空字符串时，会以空字符串密码进行登录 redis；我们修改了这一逻辑，不管您在程序中密码是设置的 null 还是空字符串，我们都会跳过认证。这样的好处就是，假设您的开发环境 redis 没有设置密码，生产环境设置了密码，我们可以通过一个 bean 初始化即可，不用写成两个 bean。

```xml
<bean id="redisDataSource" class="com.buession.redis.client.connection.datasource.jedis.UserMapper"
	p:host="${redis.host}"
	p:port="${redis.port}"
	p:password="${redis.password}" />
```

测试环境 properties：

```properties
redis.host=127.0.0.1
redis.port=6379
redis.password=
```

生产环境 properties：

```properties
redis.host=192.168.100.131
redis.port=6379
redis.password=passwd
```


### 连接池

通过连接池管理 redis 连接，能够大大的提高效率和节约资源的使用。jedis 和 lettuce 均使用 `apache commons-pool2` 来创建和维护连接池。但是，在 jedis 中，以 `JedisPoolConfig` 和 `ConnectionPoolConfig` 来管理单例模式连接池、哨兵模式连接池和集群模式连接池；为了简化配置，我们定义了 `com.buession.redis.core.PoolConfig` 来统一维护各种模式的连接池配置，然后在各 `DataSource` 中转换为原生的连接池配置，极大的简化了学习和替换成本。


连接池配置

|  配置项    | 数据类型    | -- 默认值   | 说明        |
|  ----     | ----       | --          | ----       |
| lifo      | boolean    | GenericObjectPoolConfig.DEFAULT_LIFO  | 池模式，为 true 时，后进先出；为 false 时，先进先出 |
| fairness      | boolean    | GenericObjectPoolConfig.DEFAULT_FAIRNESS  | 当从池中获取资源或者将资源还回池中时，是否使用 java.util.concurrent.locks.ReentrantLock 的公平锁机制 |
| maxWait      | Duration    | GenericObjectPoolConfig.DEFAULT_MAX_WAIT  | 当连接池资源用尽后，调用者获取连接时的最大等待时间 |
| minEvictableIdleTime      | Duration    | 60000  | 连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除 |
| softMinEvictableIdleTime      | Duration    | GenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION  | 连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数 |
| evictionPolicyClassName      | String    | GenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME  | 驱逐策略的类名 |
| evictorShutdownTimeout      | Duration    | GenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT  | 关闭驱逐线程的超时时间 |
| numTestsPerEvictionRun      | int    | -1  | 检测空闲对象线程每次运行时检测的空闲对象的数量 |
| testOnCreate      | boolean    | GenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE  | 在创建对象时检测对象是否有效，配置 true 会降低性能 |
| testOnBorrow      | boolean    | GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW  | 在从对象池获取对象时是否检测对象有效，配置 true 会降低性能 |
| testOnReturn      | boolean    | GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN  | 在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能 |
| testWhileIdle      | boolean    | true | 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性 |
| timeBetweenEvictionRuns      | int    | 30000  | 空闲连接检测的周期，如果为负值，表示不运行检测线程 |
| blockWhenExhausted      | boolean    | GenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED  | 当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效；false 连接池没有资源立马抛异常） |
| timeBetweenEvictionRuns      | int    | 30000  | 空闲连接检测的周期，如果为负值，表示不运行检测线程 |
| jmxEnabled      | boolean    | GenericObjectPoolConfig.DEFAULT_JMX_ENABLE  | 是否注册 JMX |
| jmxNamePrefix      | String    | GenericObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX  | JMX 前缀 |
| jmxNameBase      | String    | GenericObjectPoolConfig.DEFAULT_JMX_NAME_BASE  | 使用 base + jmxNamePrefix + i 来生成 ObjectName |
| maxTotal      | int    | GenericObjectPoolConfig.DEFAULT_MAX_TOTAL  | 最大连接数 |
| minIdle      | int    | GenericObjectPoolConfig.DEFAULT_MIN_IDLE  | 最小空闲连接数 |
| maxIdle      | int    | GenericObjectPoolConfig.DEFAULT_MAX_IDLE  | 最大空闲连接数 |


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-redis/2.3.0/com/buession/redis/client/connection/datasource/package-summary.html)