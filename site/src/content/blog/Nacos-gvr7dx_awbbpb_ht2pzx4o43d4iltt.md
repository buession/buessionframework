---
title: "0代码改动实现应用运行时数据库密码无损轮转"
description: "0代码改动实现应用运行时数据库密码无损轮转"
date: "2025-06-30"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

作者：柳遵飞

# 一.敏感数据的安全风险
在应用程序中，访问数据库几乎是必须的，是实现业务功能的基础普遍场景，应用程序访问数据库，需要设置数据库的地址，端口，账号及密码。密码的安全性非常重要，业界密码泄漏导致资损的事件时有发生，根据相关统计，单次泄漏事件的发生平均导致<font style="color:#DF2A3F;">488万</font>**<font style="color:#000000;">美元（约合人民币</font>**<font style="color:#DF2A3F;">3542万</font>**<font style="color:#000000;">元），每条泄漏的数据记录平均导致</font>**<font style="color:#DF2A3F;">169美元</font>**<font style="color:#000000;">（约合人民币</font>**<font style="color:#DF2A3F;">1226元</font><font style="color:#000000;">）</font>，除了直观的资金损失外，对企业的形象和舆论也会造成不良影响。

国家在2019年颁布了《**国家安全二级等保等保2.0标准（GB/T 22239-2019）**》，明确了对于不同类型的企业所需要实现的安全防护等级，特别是涉及银行，金融类的企业IT系统中存储的业务数据涉及大量的个人敏感数据，这些数据泄漏往往直接造成经济损失，高级别的合规性要求，深刻影响着企业运营和社会稳定。



# 二.如何降低账密泄漏风险
Nacos是国内被广泛使用的IT系统应用的配置中心，对于线上的IT系统应用，我们可以从多个方面来提升应用访问数据库帐密的安全性，比如增加密码的强度，帐密统一管理，设置访问权限，帐密传输加密等等，可以参考 《[Nacos安全零信任](about:blank)》以及《[Spring Cloud+Nacos+KMS 动态配置最佳实践](https://mp.weixin.qq.com/s/SdMAGMXb3RUf8TlGMYr_oA)》。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/2483/1749449507920-39d21fc1-0ee7-43e8-92ed-7f0652517f96.png)



Nacos可以统一托管应用程序中的配置参数，并且从访问控制，传输安全，存储安全三个方面的措施有效降低帐密泄漏的风险，但是没有解决以下两个方面的问题：

+ **帐密人工维护**：运维人员需要将帐密手动设置在nacos加密配置中，过程中人为的参与带来了泄漏的隐患。
+ **运行期轮转成本高**：当帐密泄漏时替换的成本较大，需要创建新帐密，并且在应用程序中重启替换，时间和人力消耗巨大，具体的修复时间和应用集群规模相关，通常需要<font style="color:#DF2A3F;">数小时</font>才能完成，当集群规模达到100+时，修复时间更长，另外大批量的应用重启可能会带来稳定性风险。

# 三.不重启应用实现密码无损轮转
在应用侧访问数据库通常会结合各类应用侧数据库连接池框架，比如HikariCP，Apache Druid，  C3P0等，除了连接数据库的地址及帐密之外，还可以设置应用侧连接池大小，超时时间等参数，以实现业务系统可用性和性能的最大化。

为了解决上一节中提到的两个问题，MSE Nacos联合阿里云密钥管理服务KMS，开源数据库连接池框架Druid以及开源Spring Cloud Alibaba社区推出了面向应用侧的数据源运行期动态轮转方案。



![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/2483/1749449347204-1bbc3d86-f7c3-4af8-8630-0263139b324c.png)

可以根据上图中的数字代表的步骤顺序了解整体的工作流程。

其中各个组件的职责如下：

1. MSE Nacos-动态配置中心

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/2483/1749448248684-1a89b856-4776-492a-a8ca-547051404f8c.png)

    1. 提供应用侧数据源配置的统一管理平台
    2. 整合KMS实现帐密->应用侧配置的转化
    3. 提供数据源配置的运行期推送的基础能力
2. Spring Cloud Alibaba-开源应用侧框架
    1. 整合nacos-client和druid组件协作，屏蔽接入复杂性
    2. 配置化接入数据源druid以及配置变更时触发 druid 数据源运行时轮转
3. Apache Druid-开源应用侧数据库连接池
    1. 应用程序内数据库连接池统一管理，支持运行时连接池大小，超时等参数调整
    2. 运行时动态刷新，实现旧帐密连接->新帐密连接的优雅刷新，保证业务无损。
    3. 运行期异常保护，比如错误帐密，错误地址的预检，保证存量连接稳定。
4. KMS-阿里云密钥管理服务![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/2483/1749448328847-42a47cb5-3839-4dbf-8eee-ddfcaadbaee6.png)
    1. 提供数据源底层加密配置的加密和解密服务
    2. 提供云上数据库的账号密码托管和定时轮转功能
    3. 帐密泄漏时可进行帐密立即轮转，实现一键快速止损

以上方案实现了：

+ 加密配置统一托管 ：应用程序侧访问数据库的配置统一加密存储在Nacos中 
+ 帐密全托管：KMS实现了对数据库实例账号密码的全托管
+ 双层权限管控：应用程序侧对加密配置的查询及加密进行双层权限认证。
+ 帐密秘文存储及传输：在全链路中明文只存在于应用内存中，存储和传输中均为加密配置。
+ 运行时无损轮转：当数据库帐密变更时，应用侧实时感知并且连接优雅切换。



当帐密泄露后，线上应用帐密的切换时间由之前的<font style="color:#DF2A3F;">数小时</font>优化到只需<font style="color:#DF2A3F;">一秒</font>！相比之前重启替换小时级别，大大提升安全性和效率.

<font style="color:rgba(0, 0, 0, 0.9);">动态数据源接入方案无代码侵入性，全程 0 代码改造，</font>详细接入步骤请参照官方文档：《[MSE Nacos数据源管理](https://help.aliyun.com/zh/mse/user-guide/data-source-management)》



除了支持运行期更新帐密功能外，同时也支持数据库连接池大小，超时参数，数据库地址及数据库名的动态更新。可以实现运行期调整连接池性能以及切库等高阶功能。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/2483/1750055368754-9cd84b1a-6218-43df-8709-5b6cb134ee74.png)

# 四.Nacos+KMS+X数据源类通用解决方案
 	MSE Nacos + KMS +Druid的方案实现了数据库帐密的运行期动态轮转，未来MSE Nacos和KMS会对接更多的数据源类的组件，比如NoSql (Redis/Tair)，MQ(RocketMQ,Kafka),ScheduleX, OSS等，以下是将数据库druid泛化为通用组件X的架构图，除了进行帐密的托管及动态轮转之外，面向应用侧会进行组件初始化及轮转的逻辑封装，实现0代码改造、配置化接入，降低应用侧的复杂性。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/2483/1749434754701-9798202a-0e66-4d8b-8893-1a2ca760b176.png)

Nacos作为国内被广泛使用的配置中心，已经成为应用侧的基础设施产品，近年来安全问题被更多关注，这是中国国内软件行业逐渐迈向成熟的体现，也是必经之路，Nacos提供配置加密存储-运行时轮转的核心安全能力，将在应用安全领域承担更多职责。当前正在加速迈向AI时代，AI领域的安全问题也同样重要，比如Agent访问大模型LLM，MCP Server的配置也同样面临传统微服务应用中类似的安全性和易用性问题，Nacos会全面拥抱AI时代，面向应用侧提供一站式安全-易用-稳定的服务，配置，AI Registry平台。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/2483/1750139970225-21b5e4da-4722-40da-ace1-ef2888e99319.png)



**<font style="color:rgba(0, 0, 0, 0.9);">相关链接：</font>**

<font style="color:rgba(0, 0, 0, 0.9);">[1] Nacos 官网</font>

_<u><font style="color:rgb(0, 122, 170);">https://nacos.io</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[2] Nacos Github 主仓库</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/alibaba/nacos</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[3] 生态组仓库</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[4] Spring Cloud Alibaba</font>

_<u><font style="color:rgb(0, 122, 170);">https://sca.aliyun.com/docs/2023/user-guide/nacos/quick-start/</font></u>_

**<font style="color:rgba(0, 0, 0, 0.9);">Nacos 多语言生态仓库：</font>**

<font style="color:rgba(0, 0, 0, 0.9);">[1] Nacos-GO-SDK</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-go</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[2] Nacos-Python-SDK</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-python</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[3] Nacos-Rust-SDK</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-rust</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[4] Nacos C# SDK</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-csharp</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[5] Nacos C++ SDK</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-cpp</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[6] Nacos PHP-SDK</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-php</font></u>_

<font style="color:rgba(0, 0, 0, 0.9);">[7] Rust Nacos Server</font>

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/r-nacos</font></u>_

**<font style="color:rgba(0, 0, 0, 0.9);">推荐阅读：</font>**

<font style="color:rgba(0, 0, 0, 0.9);">《</font>[<font style="color:rgb(87, 107, 149);">MSE Nacos：解决敏感配置的安全隐患</font>](http://mp.weixin.qq.com/s?__biz=MzUzNzYxNjAzMg==&mid=2247560684&idx=1&sn=edad3ba00700c7a7848631c1571771ba&chksm=fae7e623cd906f35dbf754daef77f5b60e6963f7f15f14ce337cadbb5e3c70d69a898e82846e&scene=21#wechat_redirect)<font style="color:rgba(0, 0, 0, 0.9);">》</font>

<font style="color:rgba(0, 0, 0, 0.9);">《</font>[<font style="color:rgb(87, 107, 149);">Nacos 配置中心变更利器：自定义标签灰度</font>](https://mp.weixin.qq.com/s?__biz=MzUzNzYxNjAzMg==&mid=2247569459&idx=1&sn=4ea86a4aaae62d470f806580e609f85c&scene=21#wechat_redirect)<font style="color:rgba(0, 0, 0, 0.9);">》</font>

<font style="color:rgba(0, 0, 0, 0.9);">《</font>[<font style="color:rgba(0, 0, 0, 0.9);">Nacos+Langchain大模型参数及promot托管</font>](https://developer.aliyun.com/article/1660070)<font style="color:rgba(0, 0, 0, 0.9);">》</font>

<font style="color:rgba(0, 0, 0, 0.9);">《</font>[<font style="color:rgba(0, 0, 0, 0.9);">Nacos 开源 MCP Router，加速 MCP 私有化部署</font>](https://mp.weixin.qq.com/s/80FW8VysOxJ3TUGXcEfL5A)<font style="color:rgba(0, 0, 0, 0.9);">》</font>


