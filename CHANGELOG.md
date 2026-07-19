Buession Framework Changelog
===========================


## [5.0.0](https://github.com/buession/buessionframework/releases/tag/v5.0.0) (2026-07-xx)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v5.0.0)

### 🔔 变化

- javax.validation 更换为 jakarta.validation
- maxmind 数据库从代码中移除
- geoip model 类型从 class 变更未 record

### 🐞 Bug 修复

- 修复 File 文件扩展名为大写时，无法获取 MimeType 的 BUG

### 📔 文档

- 完善代码注释


---


## [4.0.0](https://github.com/buession/buessionframework/releases/tag/v4.0.0) (2026-07-15)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v4.0.0)
- [geoip2](https://dev.maxmind.com/geoip?lang=en) 版本升级至 4.4.0

### ⭐ 新特性

- 新增 com.buession.core.serializer.FastJson2JsonSerializer 和 com.buession.core.deserializer.FastJson2JsonDeserializer
- redis 序列化和反序列化新增 fastjson2 支持
- 新增 JDK HttpClient 支持

### 🔔 变化

- 删除 java.io.FileAlreadyExistedException，迁移至 com.buession.lang.FileAlreadyExisteException
- 删除 org.apache.ibatis.type.DefaultEnumTypeHandler
- 删除 com.buession.httpclient.IBaseHttpClient
- GEO IP Model 字段发生变化
- 删除 com.buession.beans.BeanUtils#toMap()
- 删除 buession-velocity 模块
- 删除 com.buession.redis.client.connection.datasource.DataSource#getConnection()
- MyBatisDao 使用构造方式注入 SqlSessionTemplate 和 MongoDBDao 使用构造方法注入 MongoTemplate
- 删除 RedisTemplate 已废弃的方法
- 优化 RedisTemplate 内部实现
- 模块 buession-beans 不再推荐使用

### 🐞 Bug 修复

- 修复 Converter 中链式操作的问题
- 修复 com.buession.httpclient.AbstractHttpAsyncClient#put(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback) 无限递归的 BUG
- 修复 com.buession.httpclient.AbstractHttpAsyncClient#report(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback) 无限递归的 BUG
- 修复 RedisTemplate 中无限递归调用的 BUG
- 修复 RedisTemplate 中 lMove(final String key, final String destKey, final Direction from, final Direction to) 中原 key 设置成目标 key 了的 BUG；
- 修复 RedisTemplate 中 blMove(final String key, final String destKey, final Direction from, final Direction to, final int timeout) 中原 key 设置成目标 key 了的 BUG
- 修复 jackson 序列化、反序列化不支持 LocalDate、LocalTime、LocalDateTime 的 BUG

### 📔 文档

- 完善代码注释


---


## [3.0.1](https://github.com/buession/buessionframework/releases/tag/v3.0.1) (2025-05-19)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v3.0.1)

### ⭐ 新特性

- 新增对象工具类 com.buession.core.utils.Objects
- 新增 webflux 异常处理器 ErrorHandler
- Dao getAll 支持排序

### 🔔 变化

- 废弃 com.buession.core.Rawable，迁移至 com.buession.lang.Rawable
- 废弃 RedisConnection 方法 isConnect()，新增方法 isConnected()
- PropertiesGitParser、JsonGitParser 字符串构造函数，使用 FileSystemResource 解析文件路径
- RandomDigitIdGenerator 最小值允许为 Long.MIN_VALUE
- RandomIdGenerator 允许指定随机字符串词典
- RandomUtils nextInt、nextLong、nextFloat、nextDouble 起始值允许为负数

### 🐞 Bug 修复

- 修复 redis HashOperations hMSet 方法参数类型
- 修复 ApacheHttpClient、ApacheHttpAsyncClient 指定为 apache client 4 ConnectionManager 且包含 apache client 5 的包时，初始化
  ApacheClient 时 ConnectionManager 类型转换异常的 BUG
- 修复 Redis Jedis 模式下多线程环境中出现 Scoket closed 异常
- 修复 GitParser 无法正常解析 git 信息的 BUG

### ⏪ 优化

- 优化 redis 命令参数日志
- 优化 redis RedisTemplate 对象转换器
- 优化 redis 命令运行器数据类型优化
- 优化 redis 稳定性优化
- 优化雪花算法稳定性
- 优化和规范化 httpclient 连接池参数设置

### 📔 文档

- 完善 redis 注释

---

## [3.0.0](https://github.com/buession/buessionframework/releases/tag/v3.0.0) (2024-11-07)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v3.0.0)

### ⭐ 新特性

- ClassUtils 新增检测类是否存在方法 isPresent
- 新增 list 到 set 转换器 ListSetConverter
- 新增 set 到 list 转换器 SetListConverter
- 新增断言转换器 PredicateConverter
- 新增 集合转换器 CollectionConverter
- 新增 Map.Entry 到 Map 转换器 MapEntryMapConverter
- httpClient Configuration 新增
  expectContinueEnabled、hardCancellationEnabled、targetPreferredAuthSchemes、cookieSpec、proxy 配置
- httpClient 新增代理支持
- ApacheClient 新增支持 apache httpcomponents 5，根据依赖的 apache httpcomponents 5 和 apache httpcomponents 4 自动判断，优先使用
  apache httpcomponents 5
- 新增支持 Lettuce（stream 命令不稳定，不推荐使用）
- jedis 模式下，集群支持管道
- 新增代理方法调用者 ProxyMethodInvoker
- 新增代理调用处理器 InvocationHandler 抽象类 AbstractInvocationHandler
- PropertyMapper 增加正数断言 alwaysApplyingWhenPositiveNumber
- com.buession.io.file.File 新增支持将 InputStream 写入文件
- JSON 序列化和反序列化支持对原生对象进行配置
- 增加范围类 Range 及其衍生类
- 新增日期时间工具类 DateTimeUtils

### 🔔 变化

- Maps.toList 参数为 LinkedHashMap 时返回 LinkedList
- Lists.toSet 参数为 LinkedList 返回 LinkedHashSet
- Sets.toList 参数为 LinkedHashSet 返回 LinkedList
- core 包中删除依赖 commons-beanutils
- 删除 ArrayUtils、StatusUtils、ObjectUtils、MapUtils、BuesssionFrameworkVersion
- 删除 AbstractAspectAnnotationsMethodInterceptor
- geoip 删除依赖 apache httpcomponents
- 废弃 ApacheRequest、ApacheRequestBuilder、ApacheHttpClientBuilder
- httpClient 优化 ConnectionManager 创建默认原生 ConnectionManager，以解决在创建原生 ConnectionManager 之前，更新了
  Configuration 不生效的问题
- ApacheHttpClient 删除 apache httpcomponents 4 中的特有方法
- httpClient 参数为 URL 时，不再验证是否为 null
- apache httpcomponents 异步请求时 HttpAsyncResponseConsumer 采用 ZeroCopyConsumer
- okhttp maxRequestsPerHost 大于 0 时，才会更新 okhttp 原生 maxRequestsPerHost 的值
- 删除 OkHttpClient
- 重构 JDBC 连接池和数据源
- MyBatisDao 废弃 masterTemplate、slaveSqlSessionTemplate，通过其它数据库本身或其它中间件实现读写分离
- redis 删除参数为字符串数组或 byte[] 数组的方法 bitField
- redis 废弃字符串型 / byte[]型 min、max 参数的方法
- redis role 命令直接返回 Role，不再返回 List&lt;Role&gt;
- redis 重构管道、事务内部实现
- redis 哨兵连接，如果指定了连接超时、读取超时，但未指定哨兵节点连接超时、读取超时，哨兵节点连接超时、读取超时也使用该值
- redis 连接池调整在连接对象中创建，不再在 DataSource 中创建
- redis 不再推荐从 DataSource 中创建 RedisConnection 实例
- IO MimeType 忽略大小写比较
- 删除 com.buession.web.mvc.Response 中参数为 com.buession.core.Pagination 的构造函数和 setter 函数
- 删除 buession-cron 和 buession-session
- 删除 MobileFilter、PoweredByHeaderFilter
- 序列化类删除反序列化方法

### 🐞 Bug 修复

- httpClient 修复 ConnectionManager 未设置 connectionManagerShared 时，无法从 Configuration 中获取
  connectionManagerShared 值的 BUG
- 修复 okhttp 设置 followRedirects 时，followSslRedirects 未生效的 BUG
- 修复 jedis 模式下 role 命令未返回数据的 BUG
- 修复 jedis 模式下 API 中，“命令”参数设置错误的 BUG
- 修复 jedis GeoRadiusResponseConverter coordinate 为 null 时，空指针异常
- 修复 jedis 模式下连接中的错误和参数丢失等问题
- 修复 Validate IPV4 验证，第一段为 10 ~ 99 时，验证失败的 BUG

### ⏪ 优化

- 优化 redis 转换器，不使用静态属性，以减少内存占用
- jedis 模式命令运算优化
- 优化 JDBC 数据源连接池设置
- 优化 httpclient 配置、连接管理器以及连接客户端
- 优化默认线程池执行器 DefaultThreadPoolExecutor 初始化
- com.buession.io.file.File 读取文件优化

### 📔 文档

- 完善 redis 注释
- 修正 httpclient 错误注释
- 完善 httpclient 注释

---

## [2.3.3](https://github.com/buession/buessionframework/releases/tag/v2.3.3) (2024-05-06)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.3.3)

### ⭐ 新特性

- **buession-core：** 新增默认线程工厂 DefaultThreadFactory
- **buession-core：** 新增默认线程池执行器 DefaultThreadPoolExecutor
- **buession-core：** File 新增创建软链接方法
- **buession-core：** PropertyMapper 新增 alwaysApplyingWhenNull 条件为 null 时，alwaysApplyingWhenNonText 条件为 null
  或无字符串（不含空格、换行符、制表位）时

### 🔔 变化

- **buession-core：** 废弃 ThreadPoolConfiguration name 属性
- **buession-core：** 废弃 ThreadPoolConfiguration timeUnit 属性，使用 keepAliveTimeUnit 替代
- **buession-core：** 废弃 ObjectUtils 使用 java 原生 API Optional
- **buession-core：** 废弃 StatusUtils ，迁移至枚举 Status 本身

### 🐞 Bug 修复

- **buession-core：** 修复 StringUtils 类忽略大小写判断是否以字符开头或结尾，错误判断的 BUG
- **buession-dao：** 修复 MongoDBDao 处理 in、nin 条件值错误传递问题

### ⏪ 优化

- **buession-core：** 优化数据验证，减少内存占用
- **buession-json：** 数据脱敏，性能优化

---

## [2.3.2](https://github.com/buession/buessionframework/releases/tag/v2.3.2) (2023-12-27)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.3.2)

### ⭐ 新特性

- **buession-core：** ClassUtils、MethodUtils、FieldUtils 增加检测是否含义任意注解的方法
- **buession-core：** AnnotationUtils 增加检测属性是否含义任意注解的方法
- **buession-core：** 断言 Assert 可指定异常
- **buession-core：** 新增对象工具类 ObjectUtils
- **buession-core：** AbstractBeanConverter 新增支持 String 转枚举
- **buession-core：** 新增反射工具类 ReflectionUtils
- **buession-aop：** 新增注解处理器支持解析注解属性中的变量
- **buession-json：** 注解 @Sensitive 增加支持邮箱脱敏
- **buession-httpclient：** OKHTTP client 增加可设置 maxRequests
- **buession-velocity：** 新增验证工具 ValidateTool
- **buession-web：** 新增注解处理器支持解析注解属性中的变量

### 🔔 变化

- **buession-core：** 废弃 JsonSerializer 和 JsonDeserializer
- **buession-core：** ListConverter、SetConverter、MapConverter 默认返回原始类型
- **buession-core：** 移除 spring-beans 依赖
- **buession-json：** 注解 @Sensitive 内容替换策略，替换符可通过属性 replacement 设置

### 🐞 Bug 修复

- **buession-core：** 修改错误的类名 BuesssionFrameworkVersion 为 BuessionFrameworkVersion
- **buession-beans：** 修复 NumberPropertyConverter 字符串转换为数字时异常的 BUG
- **buession-beans：** 修复 Map 转换为 bean 时字段为 null 值的 BUG
- **buession-beans：** 修复 Map 转换为 bean 或 bean 转换为 map 时缓存导致的异常
- **buession-dao：** 修复分页插件 PaginationInterceptor 当页码大于等于 2 时，无法返回数据的 BUG
- **buession-dao：** 修复分页对象下一页值错误的 BUG
- **buession-git：** 修复无法解析 git 信息时，空指针异常
- **buession-web：** 修复分页对象下一页值错误的 BUG

### ⏪ 优化

- **buession-beans：** 代码优化
- **buession-httpclient：** 代码优化
- **buession-redis：** 代码优化
- **buession-redis：** 优化 RedisAccessor 多次调用 afterPropertiesSet 时，重复初始化 RedisConnectionFactory
- **buession-geoip：** 优化 GeoIPResolverFactoryBean 多次调用 afterPropertiesSet 时，重复初始化 DatabaseResolver
- **buession-thesaurus：** 优化 ThesaurusFactoryBean 多次调用 afterPropertiesSet 时，重复初始化 Parser
- **buession-velocity：** 优化 VelocityEngineFactoryBean 多次调用 afterPropertiesSet 时，重复初始化 VelocityEngine

### 📔 文档

- **buession-aop：** 完善注释
- **buession-httpclient：** 完善注释
- **buession-redis：** 完善注释
- **buession-velocity：** 完善注释
- **buession-web：** 完善注释

---

## [2.3.1](https://github.com/buession/buessionframework/releases/tag/v2.3.1) (2023-11-17)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.3.1)

### ⭐ 新特性

- **buession-beans：** 新增 bean 转换器 BeanConverter , 支持 map、bean 互转
- **buession-core：** 新增日期、时间格式化工具类 DateFormatUtils
- **buession-core：** DateTime 新增日期时间对象、日历对象、时钟对象转换为时间戳方法
- **buession-core：** 新增线程池饱和策略 ThreadPolicy
- **buession-dao：** 新增实验性的 MyBatis 分页插件
- **buession-json：** 注解 Sensitive 增加 strategyType 用于定义脱敏策略类，优先级高于 strategy

### 🐞 Bug 修复

- **buession-core：** 修复 ClassUtils instantiate 方法初始化类时，参数长度错误判断异常
- **buession-core：** 修复 MapBuilder 增加 putIfPresent 方法，值不为 null 时添加到 Map 中
- **buession-dao：** 修复修改数据 BUG
- **buession-web：** 修复获取客户端真实 IP 注解判断错误 BUG
- **buession-web：** 修复 velocity JsonTool 为设置日期时间格式时，无法设置时区的 BUG

### ⏪ 优化

- **buession-httpclient：** okhttp3 连接池关闭时驱逐连接资源

---

## [2.3.0](https://github.com/buession/buessionframework/releases/tag/v2.3.0) (2023-08-15)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.3.0)

### 🔔 变化

- **buession-core：** 废弃 com.buession.core.serializer.type.TypeReference 使用
  com.buession.core.type.TypeReference【3.0.0 版本删除】
- **buession-core：** 将序列化类，拆分成序列化和反序列化
- **buession-dao：** 废弃 DefaultEnumTypeHandler 使用 mybatis 原生 EnumTypeHandler【3.0.0 版本删除】
- **buession-httpclient：** 废弃 Request.setUrl(String url) 使用 Request.setUri(URI uri) 替代【3.0.0 版本删除】
- **buession-web：** AbstractRestController 添加主键类型、数据传输对象类型、数据输出对象类型
- **buession-web：** Response 的 Pagination 类型由 com.buession.core.Pagination 更改为 com.buession.web.mvc.Pagination
  不再返回数据
- **buession-redis：** 废弃 bitfield 通过可变参数传参

### ⭐ 新特性

- **buession-lang：** 新增浏览器类型 BrowserType、设备类型 DeviceType、渲染引擎 RenderingEngine、版本 Version、浏览器
  Browser、操作系统 OperatingSystem 定义
- **buession-lang：** 新增重试配置 Retry
- **buession-aop：** 增加
  AfterReturningAdviceMethodInvocationAdapter、AfterThrowingAdviceMethodInvocationAdapter、AroundAdviceMethodInvocationAdapter
- **buession-core：** 新增类型引用类 com.buession.core.type.TypeReference
- **buession-core：** 新增配置器接口 Configurer
- **buession-core：** 新增定制器接口 Customizer
- **buession-core：** 新增线程池配置类 ThreadPoolConfiguration
- **buession-core：** Arrays 新增元素重复填充方法
- **buession-core：** StringUtils 新增截取左边指定个字符串
- **buession-core：** DateTime 新增如果获取原生日期、时间对象方法
- **buession-dao：** 删除数据时，支持指定删除条数
- **buession-httpclient：** 新增实验性 HTTP 异步请求客户端
- **buession-httpclient：** 请求方法支持传 URI
- **buession-httpclient：** 支持为每次请求单独配置 readTimeout
- **buession-jdbc：** DataSource 可设置驱动、JDBC URL、数据库用户名、数据库密码信息
- **buession-jdbc：** 新增 javax.sql.DataSource 初始化回调接口 Callback
- **buession-redis：** bitfield API 支持通过 BitFieldArgument 传参
- **buession-net：** SslConfiguration 增加 sslContext 属性
- **buession-net：** 新增 SSL 配置 SslConfigure

### 🐞 Bug 修复

- **buession-redis：** 修复 Jedis StringCommands.SetArgument 设置过期时间戳，处理成过期时间的 BUG
- **buession-redis：** 修复 Client 对象返回的 cmd 类型错误的 BUG
- **buession-jdbc：** 修复 DataSource 未设置 PoolConfiguration 创建原生 DataSource 空指针 BUG
- **buession-httpclient：** 修复 HttpClient request 方法，无法发送 report、proppatch 请求 BUG
- **buession-httpclient：** 修复 OkHttpClientConnectionManager 中错误设置 IdleConnectionTime 的 BUG
- **buession-web：** 修复 AbstractBasicRestController 无法调用重写 pageNotFound(final String uri) 方法 BUG
- **buession-web：** 修复 ServerInfoFilter 通过 setHeaderName 方法设置响应头名称无效的 BUG
- **buession-web：** 修复 ServerInfoFilter 通过构造函数设置响应头名称未进行有效性验证的 BUG
- **buession-web：** 修复 ServletContentTypeAnnotationHandler、ServletResponseHeadersAnnotationHandler 空指针 BUG
- **buession-web：** 修复 servlet 下 ExceptionResolver 处理异常时，响应流异常关闭的 BUG

### ⏪ 优化

- **buession-httpclient：** 内部优化
- 其它优化

---

## [2.2.1](https://github.com/buession/buessionframework/releases/tag/v2.2.1) (2023-03-31)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.2.1)
- **buession-geoip：** 升级 IP 库

### 🔔 变化

- **buession-web：** Servlet AbstractHandlerExceptionResolver doResolve 方法支持接收 handler

### ⭐ 新特性

- **buession-web：** 新增实验性 User-Agent 解析工具

---

## [2.2.0](https://github.com/buession/buessionframework/releases/tag/v2.2.0) (2023-03-10)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.2.0)
- **buession-geoip：** 升级 IP 库

### ⭐ 新特性

- **buession-core：** ListBuilder、MapBuilder、QueueBuilder、SetBuilder 增加可以直接传递 List、Map、Queue、Set 实例的 create 方法
- **buession-core：** Assert isEmpty 和 notEmpty 增加基础类型数组判断
- **buession-core：** StringUtils 增加 random 增加支持字符串
- **buession-geoip：** 增加返回 autonomous system number、autonomous system organization
- **buession-geoip：** 增加可支持设置 asn 库地址或流
- **buession-httpclient：** 支持原生 RequestBody 子类转换
- **buession-web：** 增加 web binder 转换器工厂 IgnoreCaseEnumConverterFactory 忽略大小写将字符串转换为枚举值
- **buession-web：** 获取客户端真实 IP，增加标头 Client-IP 的支持

### 🔔 变化

- **buession-web：** 废弃 web binder 转换器
  CaseTypeConverter、DomainTLDConverter、DomainTLDTypeConverter、GenderConverter、IpTypeConverter、ISBNTypeConverter、OrderConverter、StatusConverter，统一使用
  web binder 转换器工厂 IgnoreCaseEnumConverterFactory
- **buession-core：** Executor、Resolve 增加异常支持

### 🐞 Bug 修复

- **buession-core：** 修复 RandomDigitIdGenerator 最大值大于最小值时报错的问题
- **buession-httpclient：** 修复 Response 返回错误的 statusCode 和 statusText
- **buession-dao：** 修复 DefaultJsonTypeHandler 为 null 或空字符串反序列化出错的 BUG

### ⏪ 优化

- 其它性能优化
- 其它代码优化

---

## [2.1.2](https://github.com/buession/buessionframework/releases/tag/v2.1.2) (2022-11-13)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.1.2)

### ⭐ 新特性

- **buession-core：** ListBuilder、MapBuilder、QueueBuilder、SetBuilder 增加可以指定 initialCapacity 和 Collection / Map 的
  create 方法

### ⏪ 优化

- **buession-http：** 优化 Response，使 statusCode、statusText 与 statusLine 的值始终保持一致
- **buession-geoip：** 优化国家和地区词典解析，GeoIPResolverFactory 支持从默认词典返回文件对象或流对象，以及支持从
  classpath 家长词典文件
- 其它优化
- **buession-velocity：** 配置属性 resource.loader 替换 resource.loaders

### 🐞 Bug 修复

- **buession-redis：** 修复 jedis 单例模式、哨兵模式下，不使用连接池的情况下，未创建 jedis 实例的 BUG

### 📔 文档

- **buession-redis：** 修正错误的注释

---

## [2.1.1](https://github.com/buession/buessionframework/releases/tag/v2.1.1) (2022-08-18)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.1.1)

### ⭐ 新特性

- **buession-core：** DateTime 增加统计年、月天数

---

## [2.1.0](https://github.com/buession/buessionframework/releases/tag/v2.1.0) (2022-08-07)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.1.0)

### ⭐ 新特性

- **buession-core：** 新增对象解析接口 Resolve
- **buession-core：** PropertyMapper 增加 alwaysApplyingWhenHasText() 方法，用于判断源属性是否含有内容
- **buession-web：** 注解 @RequestClientIp 增加支持返回 InetAddress
- **buession-web：** 注解 @RequestClientIp 支持指定获取真实 IP 的请求头名称

### 🔔 变化

- **buession-aop：** 调整 AnnotationResolver 及其实现泛型参数，由类泛型参数，调整为 getAnnotation 泛型
- **buession-aop：** AnnotationHandler execute 方法不再返回值
- **buession-web：** 优化注解 @RequestClientIp HandlerMethodArgumentResolver，继承 spring 原生
  HandlerMethodArgumentResolver 实现抽象类
- **buession-web：** 废弃 MobileFilter，根据需要直接使用 RequestUtils.isMobile(request) 判断
- **buession-web：** 删除 AopUtils、MethodUtils，删除 servlet HttpServlet、webflux ServerHttp
- **buession-web：** MethodInterceptor 的抽象类移至 buession-aop
- **buession-velocity：** 配置属性 springMacro.resource.loader.class 替换为
  resource.loader.springMacro.class，resource.loader 替换 resource.loaders

### ⏪ 优化

- **buession-aop：** 优化注解处理程序
- **buession-web：** 优化 servlet 注解处理

### 🐞 Bug 修复

- **buession-aop：** 修复注解在 cglib 代理模式下，连接点重复执行的 BUG
- **buession-web：** 修复 ReactiveContentTypeAnnotationMethodInterceptor 使用成了 servlet 模式下的
  ContentTypeAnnotationMethodInterceptor 的 BUG

---

## [2.0.2](https://github.com/buession/buessionframework/releases/tag/v2.0.2) (2022-07-28)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.0.2)

---

## [2.0.1](https://github.com/buession/buessionframework/releases/tag/v2.0.1) (2022-07-17)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.0.1)
- [maxmind geoip2](https://dev.maxmind.com/geoip?lang=en) 版本升级至 3.0.1

### ⭐ 新特性

- **buession-core：** 新增 PropertyMapper 用于将值从提供的源映射到目标

### 🔔 变化

- **buession-jdbc：** 连接池属性废弃 JDBC url、用户名、密码属性配置
- **buession-jdbc：** 连接池基础类型的原始类型改为包装类型，为 null 使用原生数据源的默认配置值

### 🐞 Bug 修复

- **buession-core：** Math 连续两个数之间连续相加之和计算错误的 BUG
- **buession-core：** @Isbn、@MimeType 的使用了错误的校验器的问题

---

## [2.0.0](https://github.com/buession/buessionframework/releases/tag/v2.0.0) (2022-07-06)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.0.0)

### ⭐ 新特性

- **buession-core：** 新增 ListBuilder 增加 addIfPresent 方法，值为 null，不添加到 List 中
- **buession-core：** 新增属性工具类 com.buession.core.utils.PropertiesUtils
- **buession-core：** 新增 System Property 工具类 com.buession.core.utils.SystemPropertyUtils
- **buession-core：** 新增数组转换器 com.buession.core.converter.ArrayConverter
- **buession-core：** 新增字节数组转枚举转换器 com.buession.core.converter.BinaryEnumConverter
- **buession-core：** 新增 com.buession.core.collect.Arrays 增加数组合并方法
- **buession-core：** 新增 Character、char 工具类 CharacterUtils
- **buession-core：** 新增 Byte、byte 工具类 ByteUtils
- **buession-core：** 新增 IP、端口、MimeType 验证注解 @Ip、@Port、@MimeType
- **buession-httpclient：** 新增 Header 对象，value 值增加对数字类型、char 类型、byte[] 类型的支持
- **buession-redis：** 新增支持哨兵模式（Sentine）和集群模式（Cluster）
- **buession-redis：** 新增支持 xtream 命令
- **buession-redis：** 新增支持 redis 6.0.x

### 🔔 变化

- **buession-aop：** 注解 AOP 重构
- **buession-core：** 移除众多 Bean 操作类，请使用 common-beans 或 spring 操作
- **buession-core：** 移除布尔值转换 Status 转换器 BooleanStatusConvert ，使用 BooleanStatusConverter
- **buession-core：** 移除 Status 转换布尔值换器 StatusBooleanConvert ，使用 StatusBooleanConverter
- **buession-core：** 移除反射工具类 com.buession.core.utils.ReflectUtils
- **buession-core：** ListBuilder、MapBuilder、SetBuilder、QueueBuilder 从包 com.buession.core.utils 迁移至包
  com.buession.core.builder
- **buession-dao：** 优化部分底层逻辑
- **buession-httpclient：** 重构底层代码，内部包结构做重大调整
- **buession-httpclient：** 移除 ObjectRequestBodyConverter
- **buession-httpclient：** okhttp 连接管理器 com.buession.httpclient.okhttp.OkHttpClientConnectionManager 类，使用
  okhttp3.HttpClientConnectionManager，API okhttp 保持一致
- **buession-httpclient：** okhttp 连接管理器 com.buession.httpclient.okhttp.RequestBuilder 类，使用
  okhttp3.RequestBuilder，API okhttp 保持一致
- **buession-httpclient：** 废弃 OkHttpClient ，使用统一风格的命名 OkHttpHttpClient
- **buession-io：** 移除 FilePermission 字符串值字段
- **buession-jdbc：** 时间配置由 int/long ，变更为 java.time.Duration
- **buession-json：** 脱敏注解 @Sensitive replacement 默认值由空字符串替换为 ***
- **buession-redis：** 新增 RedisTemplate 通过 DataSource 初始化，不再通过 RedisConnection 初始化
- **buession-redis：** 移除 jedis shared redis API
- **buession-redis：** 优化内部 API
- **buession-web：** 移除 com.buession.web.reponse 包中的 response 注解，改由 com.buession.web.reponse.annotation 包中
  response 注解替代
- **buession-web：** AOP 注解重构
- **buession-web：** @Cors 注解及 CorsFilter

### 🐞 Bug 修复

- **buession-core：** 修复 validate 注解不生效 BUG
- **buession-dao：** 修复 MyBatis Dao updatePrimary 类型转换错误
- **buession-httpclient：** 修复 post 请求中，部分 api 循环调用的问题
- **buession-redis：** 修复多线程下异常
- **buession-web：** 修复 AbstractController、AbstractRestController 支持 servlet 和 reactive 的兼容性问题
- **buession-web：** 修复 @Configuration 在 servlet 和 webflux 冲突的问题