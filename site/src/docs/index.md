# 框架介绍


### Buession Framework 框架是什么？
Buession Framework 框架不是重复造车轮，它不是其它框架的替代品。

#### 它是基于各开源框架的日常工作中常见的通用技术需求二次封装
1. 本地化的数据验证，如：QQ、电话号码、身份证号码、邮政编码
2. 常用 DAO 层操作，如：插入、替换、根据主键获取记录、获取单条记录、获取多条记录
3. 应用层实现数据库读写分离
4. redis 操作兼容原生 API 的前提下，同时实现了 redis 中的值反序列化成对象
5. 词库解析（目前仅支持搜狗词库）
6. 使用 WEB 功能，如：响应头注解、缓存头注解、兼容性获取用户端真实 IP、获取用户真实 IP 注解
7. 替代 springfamework 5，支持 apache velocity
8. 基于 maxmind geoip 的 IP 信息解析
9. 基于标准的 HTTP 请求方法的 HttpClient
10. 文件操作，如：写文件、设置文件所属用户或组、文件 MimeType 解析

... ...

#### 它是同类开源框架的一种兼容性的上层封装，简化框架切换带来的成本
1. 摒弃直接使用原生类库，带来的大量的代码修改，如：HttpClient 支持 apache httpcomponents 和 okhttp3，只需要修改 HttpClient 初始化类，即可实现 HTTP 库的切换

... ...