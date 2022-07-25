 Buession Framework Changelog
===========================

## [2.0.2](https://github.com/buession/buessionframework/releases/tag/v2.0.2) (2022-07-28)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.0.2)


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
- **buession-core：** ListBuilder、MapBuilder、SetBuilder、QueueBuilder 从包 com.buession.core.utils 迁移至包 com.buession.core.builder
- **buession-dao：** 优化部分底层逻辑
- **buession-httpclient：** 重构底层代码，内部包结构做重大调整
- **buession-httpclient：** 移除 ObjectRequestBodyConverter
- **buession-httpclient：** okhttp 连接管理器 com.buession.httpclient.okhttp.OkHttpClientConnectionManager 类，使用 okhttp3.HttpClientConnectionManager，API okhttp 保持一致
- **buession-httpclient：** okhttp 连接管理器 com.buession.httpclient.okhttp.RequestBuilder 类，使用 okhttp3.RequestBuilder，API okhttp 保持一致
- **buession-httpclient：** 废弃 OkHttpClient ，使用统一风格的命名 OkHttpHttpClient
- **buession-io：** 移除 FilePermission 字符串值字段
- **buession-jdbc：** 时间配置由 int/long ，变更为 java.time.Duration
- **buession-json：** 脱敏注解 @Sensitive replacement 默认值由空字符串替换为 ***
- **buession-redis：** 新增 RedisTemplate 通过 DataSource 初始化，不再通过 RedisConnection 初始化
- **buession-redis：** 移除 jedis shared redis API
- **buession-redis：** 优化内部 API
- **buession-web：** 移除 com.buession.web.reponse 包中的 response 注解，改由 com.buession.web.reponse.annotation 包中 response 注解替代
- **buession-web：** AOP 注解重构
- **buession-web：** @Cors 注解及 CorsFilter


### 🐞 Bug 修复

- **buession-core：** 修复 validate 注解不生效 BUG
- **buession-dao：** 修复 MyBatis Dao updatePrimary 类型转换错误
- **buession-httpclient：** 修复 post 请求中，部分 api 循环调用的问题
- **buession-redis：** 修复多线程下异常
- **buession-web：** 修复 AbstractController、AbstractRestController 支持 servlet 和 reactive 的兼容性问题
- **buession-web：** 修复 @Configuration 在 servlet 和 webflux 冲突的问题