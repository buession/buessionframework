### 模块说明


#### buession-aop

* AOP 封装，方便实现基于 aspectj 的自定义注解


#### buession-common

* - - -


#### buession-core

* 一些继承 apache lang3、apache collections4 的对字符串、集合、Map、Class、Object、Enum、Number等工具类封装和扩展
* 汉字拼音工具类
* 版本对比工具类
* 数学计算
* IP 地址工具类
* 进制转换
* 对象序列化和反序列化，支持二进制、FastJson、Gson、Jackson
* 数据验证
* 带 code 和 message 消息消息对象，通过 properties 的形式注入


**示例**


计算两个数之间连续相加之和
```java
import com.buession.core.math.Math

Math.continuousAddition(start, end)
```

将长整型转化为字符串形式带点的 IPV4 地址
```java
import com.buession.core.net.InetAddressUtils

InetAddressUtils.long2ip(long)
```

将长整型转化为字符串形式带点的 IPV4 地址的 InetAddress 对象
```java
import com.buession.core.net.InetAddressUtils

InetAddressUtils.long2InetAddress(long)
```

验证是否为身份证号码（仅支持 18 位身份证号码）
```java
import com.buession.core.validator.Validate

Validate.isIDCard(str, strict, birthday)
```

验证字符串是否为 IP
```java
import com.buession.core.validator.Validate

Validate.isIp(str)
```

验证字符串是否为 ISBN
```java
import com.buession.core.validator.Validate

Validate.isIsbn(str, separator)
```

验证是否为手机号码
```java
import com.buession.core.validator.Validate

Validate.isMobile(str, separator)
```


#### buession-cron

* 对 org.quartz-scheduler:quartz 的二次封装


#### buession-dao

* 对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装
* 从代码层面上支持数据库一主多从实现读写分离，insert、update、delete 操作主库，select 操作从库


**示例**


```java
/**
 * 插入数据
 */
insert(entity)

/**
 * 批量插入数据
 */
batchInsert(entities)

/**
 * 更新数据
 */
update(entity, conditions)

/**
 * 根据主键更新数据
 */
updateByPrimary(primary, entity)

/**
 * 根据主键查询数据
 */
getByPrimary(primary)

/**
 * 数据分页查询
 */
paging(conditions, page, pagesize, orders)
```


#### buession-geoip

* 对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息
* 支持内存实现建议缓存，避免多次扫描 maxmind db


**示例**


```java
/**
 * 获取国家信息
 */
Resolver.country(ipAddress)

/**
 * 获取地区信息
 */
Resolver.district(ipAddress)

/**
 * 获取位置信息
 */
Resolver.location(ipAddress)
```


#### buession-httpclient

* 对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节
* 屏蔽了对 post form、post json 等等的技术细节


#### buession-io

* 封装了对文件的操作


**示例**


```java
import com.buession.io.file.File;
import com.buession.io.file.Files;

/**
 * 读取文件
 */
File.read()

/**
 * 向文件里面写内容
 */
File.write(content)

/**
 * 修改用户组
 */
Files.changeGroup(path, group)

/**
 * 修改用户组
 */
Files.chgrp(path, group)

/**
 * 修改文件权限
 */
Files.chmod(path, mode)
```


#### buession-json

* 主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现
* 目前支持 Unix Timestamp (10 位时间戳)、Enum 和 Map 的相互转换


**示例**


枚举和 Map 相互转换
```java
import com.buession.json.annotation.JsonEnum2Map;
import com.buession.io.file.Files;

public class User {

	@JsonEnum2Map
	private Enum enum;

}
```


#### buession-lang

* 定义了一些通用的 pojo 类


#### buession-oss

* 封装对不同对象存储产品的实现
* 目前功能很弱，需逐步完善，形成对阿里云、天翼云、AWS、百度云、华为云、腾讯云等等不同云厂商对象存储产品的封装


#### buession-redis

* Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 命令保持一致
* 对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject("key", Class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象


**示例**


枚举和 Map 相互转换
```java
import com.buession.redis.RedisTemplate;
import com.buession.core.serializer.type.TypeReference;

/**
 * 获取键 key 相关联的字符串值，并将值反序列化为对象
 */
redisTemplate.getObject(key)

/**
 * 获取键 key 相关联的字符串值，并将值反序列化为 clazz 指定的对象
 */
redisTemplate.getObject(key, clazz)

/**
 * 获取键 key 相关联的字符串值，并将值反序列化为 type 指定的对象
 */
redisTemplate.getObject(key, type)
```


#### buession-session

* - - -


#### buession-thesaurus

* 对词库的解析，目前仅支持搜索词条


#### buession-velocity

* spring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity


#### buession-web

* web 相关的功能封装，支持 servlet 和 reactive
* 封装了一些常用注解，简化了业务层方面的代码实现，如：com.buession.web.http.request.annotation.RequestClientIp: 获取客户端的真实 IP、com.buession.web.http.response.annotation.ContentType: 设置响应头中的 Content-Type、com.buession.web.http.response.annotation.ResponseHeader: 设置响应头、com.buession.web.http.response.annotation.ResponseHeaders: 批量设置响应头、com.buession.web.http.response.annotation.HttpCache: 设置页面缓存、com.buession.web.http.response.annotation.DisableHttpCache: 禁止页面缓存
* 封装了一些常用 filter，如：com.buession.web.servlet.filter.MobileFilter / com.buession.web.reactive.filter.MobileFilter: 判断是否为移动设备请求、com.buession.web.servlet.filter.ServerInfoFilter / com.buession.web.reactive.filter.ServerInfoFilter: 在响应头中返回主机名称（可用于在排查问题时，定位主机）