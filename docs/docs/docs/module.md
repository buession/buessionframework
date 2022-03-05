### buession-aop
* AOP 封装，方便实现基于 aspectj 的自定义注解

### buession-aop
* Spring Bean 工具类封装、转换

### buession-core
* 一些继承 apache lang3、apache collections4 的对字符串、集合、List、Map、Class、Object、Enum、Number等工具类封装和扩展
* 汉字拼音工具类
* 版本对比工具类
* 数学计算
* IP 地址工具类
* 进制转换
* 对象序列化和反序列化，支持二进制、FastJson、Gson、Jackson
* 数据合法性验证类
* 带 code 和 message 消息消息对象，通过 properties 的形式注入
* 日期对象类
* ID 生成器
* Manager 层注解

### buession-cron
* S对 org.quartz-scheduler:quartz 的二次封装

### buession-dao
* 对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装
* 从代码层面上支持数据库一主多从实现读写分离，insert、update、delete 操作主库，select 操作从库

### buession-geoip
* 对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息

### buession-httpclient
* 对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节
* 屏蔽了对 post form、post json 等等的技术细节

### buession-io
* 封装了对文件的操作

### buession-jdbc
* JDBC 通用 POJO 类定义
* 对 Hikari、Dbcp2、Druid 等配置和数据源的封装

### buession-json
* 主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现

### buession-lang
* 常用枚举（如：状态-Status、性别-Gender 等）的定义
* 常用 POJO 类（如：地理位置-Geo、Key Value-KeyValue 等）的定义

### buession-net
* 网络相关工具类

### buession-oss
* 对不同对象存储产品接口的封装

### buession-redis
* Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 命令保持一致
* 对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象

### buession-session
* ...

### buession-thesaurus
* 对词库的解析，目前仅支持搜狗词条

### buession-velocity
* spring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity

### buession-web
* web 相关的功能封装，支持 servlet 和 reactive
* 封装了一些常用注解，简化了业务层方面的代码实现
* 封装了一些常用 filter
