---
title: "Spring Cloud+Nacos+KMS动态配置最佳实践"
description: "Spring Cloud+Nacos+KMS动态配置最佳实践"
date: "2024-11-19"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

# 前言
Spring Cloud框架在微服务领域被广大开发者所使用，@Value是每位开发者都会接触到的注解，在SpringBean中可以通过Value注解引用application.properties属性，实现配置代码分离，提升应用代码部署的灵活性，但无法在运行期动态更新配置。Nacos是一款集服务发现和配置管理功能的中间件产品，其中配置中心可以实现运行期配置实时生效，将工程本地的属性文件配置在Nacos中，在应用中做一些配置上的改动就可以轻易集成Nacos实现配置的动态刷新，工程依赖的属性多种多样，其中把有一些敏感数据配置在中心化的Nacos中可能会存在一些安全性层面的顾虑，Nacos也有方法来应对这个问题，本次我们就对以上问题进行介绍。

本文将以如下步骤展开：

        * 集成Nacos实现动态配置属性
        * Spring Cloud+Nacos工作原理介绍
        * 集成KMS零代码改造实现敏感配置加密

# SpringCloud应用配置常规用法
在一个Spring Cloud应用中，可以在Bean中通过@Value注解来引用Spring上下文中的属性值，可以引用环境变量，JVM参数以及我们常见的application.properties配置文件中的属性。

以下是该种用法简易实例：

application.properties

```plain
app.switch=true
app.threadhold=0.8
```

一个简单的Spring Bean

```plain

@Component
public class AppConfig{

  @Value("${app.switch:false}")
  boolean switch;
  
  @Value("${app.threadhold}")
  double threadhold;

}
```

AppConfig可以被其他的SpringBean引用，可以正常获取到配置在application.properties中的app.switch和app.threadhold属性。

当我们需要修改app.switch和app.threadhold的值时，我们需要修改配置文件中的内容并对应用进行重启，当我们需要频繁修改某些业务参数时，重启应用的效率较低。

# 集成Nacos实现配置动态刷新
以下我们将介绍如何在Spring Cloud应用中结合Nacos实现配置动态更新。

Spring在2.4.x版本开始，引入了spring.config.import参数，可以自定义外部的属性源，通过Spring Cloud Alibaba组件可以实现将nacos中的配置添加为Spring的属性源之一，因此在一个Spring Bean中也可以通过Value注解读取到nacos中的配置。

*以下我们将Spring Cloud Alibaba简称为SCA .

1.pom中添加SCA nacos config依赖

```plain
<dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
      <version>${spring.cloud.alibaba.version}</version>
</dependency>
```

*组件的版本名称和spring boot版本相关，可以根据sca官网的版本说明选择对应的版本，[https://sca.aliyun.com/docs/2021/overview/version-explain/](https://sca.aliyun.com/docs/2021/overview/version-explain/)

2.初始化Nacos配置

* 可以选择开源nacos或者购买商业化MSE Nacos版本，以下的图示为商业化Nacos。

  在nacos实例中创建dataId=application.properties,group=config的配置

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1729068466720-caec8528-d414-460a-8ccf-f5ef5808cbfc.png)

3.修改应用工程中的application.properties

```plain
spring.config.import=nacos:application.properties?group=config&refreshEnabled=true
spring.cloud.nacos.config.server-addr={server addr}

app.switch=true
app.threadhold=0.8
```

通过sping.config.import参数将nacos中dataId=application.properties，group=config的配置添加为属性源，refreshEnabled=true表示当nacos中的配置变更时，需要同步刷新Spring中的属性源。

spring.cloud.nacos.config.server-addr指定了连接的nacos地址。

4.Spring Bean中添加RefreshScope注解

```sql
@Component
@RefreshScope
public class AppConfig{

  @Value("${app.switch:false}")
  boolean switch;
  
  @Value("${app.threadhold}")
  double threadhold;

}
```

在业务代码中仍然使用Value注解来引用Spring上下文中的配置，但需要在 Bean上添加RefreshScope注解，只有添加该注解,Spring才会在属性源更新时将属性刷新到对应的 Bean中。





# 配置动态刷新工作原理介绍
通过以上改造，我们就完成了Spring Cloud+Nacos实现配置动态刷新的功能，下面我们将介绍Spring Cloud + Nacos实现动态配置刷新的工作原理。

## 属性源的优先级
上面我们了解到Value注解可以读取环境变量，JVM，application.properties中的配置，示例中我们在Nacos和工程本地的application.properties文件中都配置了app.switch和app.threadhold参数，这种情况下，Spring读取配置有一个优先级，如下图所示，优先级为JVM参数>环境变量>Nacos配置(spring.config.import参数引入属性源)>工程本地application.properties。

Nacos中设置的属性值会覆盖工程本地的属性文件，但是其优先级低于JVM和环境变量，如果在环境变量和JVM参数配置了相同的参数，Nacos中的配置将不会生效。SCA在实现配置动态加载遵循了Spring Boot官方推荐的属性源优先级顺序，参考[https://docs.spring.io/spring-boot/reference/features/external-config.html](https://docs.spring.io/spring-boot/reference/features/external-config.html)。





![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1731392014297-814a26ae-47cc-4236-8400-e83bd787a5ea.png)

此外，spring.config.import参数可以指定多个属性源，不同的属性源之间通过逗号","分隔，多个不同属性源之间，引入顺序靠前，优先级更低。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1731464467097-c4880c0c-a10a-45a5-81ac-e36c6ad78d0f.png)

*在spring boot 2.4之前的版本中，Spring不支持通过spring.config.import指定外部属性源，SCA内部提供了spring.cloud.nacos.config.shared-configs和spring.cloud.nacos.config.extension-configs参数来指定多个nacos配置属性源，在最新的SCA版本2023.0.1.3中已经废弃这两个参数，统一到标准的spring.config.import参数。此外，在低版本的Spring中，支持在bootstrap.yml文件中配置参数，该种用法也在新版本Spring中废弃，统一将参数配置application.properties，我们建议对依赖低版本的应用代码进行升级，统一改造为标准的方法进行配置。

## 启动加载机制
Spring Bean的初始化需要读取Nacos中的配置，因此Nacos初始化的过程是在所有Spring Bean初始化之前进行。Spring Clound Aliababa组件会根据当前的application.properties参数对Nacos进行初始化，从Nacos Server加载配置，并构建为NacosPropertySource。在此阶段中，Spring也可以从JVM或者环境变量中读取参数，因此Nacos初始化所需的参数也可以通过JVM参数和环境变量进行设置,比如nacos server的地址，命名空间namespace，鉴权相关的accessKey及secretKey等。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1731392070332-75a91778-b608-4ff9-9ac4-a856b9ac2d63.png)

在构建好完整属性源之后，Spring会进入Bean的初始化流程中，只有在该阶段正常完成了Nacos的初始化以及Nacos配置的加载，Bean才可以正常读取到Nacos中的配置。

## 动态更新机制
在上一章节中，我们在设置spring.config.import参数时，指定了refreshEnabled=true参数，该参数表示是否需要动态监听远端NacosServer中该配置的变化，如果不指定该参数，SCA只会在启动时加载一次配置，并不会在运行期监听配置变化以及更新NacosPropertySource中的内容，SpringBean中的属性值也就无法运行期更新。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1731392161298-bcc19132-886b-4ee1-9070-18889294df71.png)

可以按照上图图示中的数字顺序了解Nacos配置动态更新的机制，当spring.config.import配置中添加了refreshEnabled=true参数，SCA就会在Spring容器初始化完成后对Nacos配置进行监听，时间点上和配置启动加载的时间点并不一致，配置初始化的时间点是在所有Bean初始化之前，而监听配置变更的时间点是在所有Bean完成初始化之后。

成功监听后，当我们在Nacos控制台对配置进行更新时，应用程序中的NacosClient会通知SCA配置发生变化，SCA在接受到底层Nacos回调后会向Spring发布RefreshEvent事件，Spring中的ContextRefresher会接受该事件，将最新的配置更新到NacosPropertySource中，更新Enviroment对象，并且发布RefreshScopeRefreshedEvent事件，对所有添加了RefreshScope注解以及ConfigurationProperties注解的SpringBean进行重新初始化，从未获取到最新的属性值。

以上流程中spring.config.import配置中的refreshEnabled=true参数决定了SCA是否会监听配置并在Nacos中配置的变化时更新Enviroment，而在Bean中添加RefreshScope注解以及ConfigurationProperties直接决定了当Enviroment对象中的属性发生变化时刷新Bean中的属性值。

## Nacos日志
<font style="color:rgba(0, 0, 0, 0.9);">Nacos扮演了配置动态推送的核心功能，通过查看nacos的启动及运行时日志，可以帮助大家更好地理解两者整合的内部原理，并且有助于大家自主排查配置中心的常见问题，nacos客户端的日志目录默认在{user.home}/logs/nacos/目录下，其中{user.home}是应用进程运行所属用户的主目录，在Linux系统中，如果进程以root启动，日志默认在/root/logs/nacos/下，如果以admin用户启动，日志默认在/home/admin/logs/nacos/下。 在nacos目录下，我们可以看到remote.log,config.log,naming.log三个日志文件，其中remote.log记录nacos客户端和服务端的长连接相关的日志，naming.log是服务管理相关日志，config.log是我们需要核心关注的配置相关日志，其中记录着nacos客户端和nacos服务端交互的详细日志。以下是几个关键的日志：</font>

        * <font style="color:rgba(0, 0, 0, 0.9);">add-listener: 表示应用程序监听了配置，包括namespace，dataId，group三元组,只有正常监听了配置，才能在配置变更时收到推送</font>
        * <font style="color:rgba(0, 0, 0, 0.9);">server-push: 表示应用程序收到了服务端的配置变更推送事件。</font>
        * <font style="color:rgba(0, 0, 0, 0.9);">data-received：表示应用程序收到推送事件后向服务端查询到了配置内容，包括namespace，dataId，group三元组以及接受到的配置md5，可以和nacos控制台比对md5值来判断是否接受到正确的版本</font>
        * <font style="color:rgba(0, 0, 0, 0.9);">notify-listener：表示应用程序收到了更新后的配置内容，并且尝试将最新的配置内容回调给对应的监听器，比如通知SCA重新加载Nacos的配置并且更新Spring上下文。</font>
        * <font style="color:rgba(0, 0, 0, 0.9);">notify-ok：表示nacos已经成功回调了监听器，监听器中的回调已经正常执行。</font>
        * <font style="color:rgba(0, 0, 0, 0.9);">notify-error ：表示nacos回调了监听器，但是监听器执行是抛出了异常，从业务视角，该种情况会表现配置更新没有效果，需要根据具体异常进行处理。</font>
        * <font style="color:rgba(0, 0, 0, 0.9);">notify-block-monitor：表示nacos回调了监听器，但监听器执行超时，默认监听器执行超过60s时会打印该日志。</font>

通过阅读nacos的日志，可以排查在使用nacos配置中心过程遇到的问题，比如通过日志判断应用程序是否连接到了正确的nacos服务端地址，是否监听了正确的<font style="color:rgba(0, 0, 0, 0.9);">namespace</font>，dataId以及group，是否正常收到了变更推送以及监听器回调时是否存在异常报错以及阻塞超时的情况。

# 集成KMS实现配置无感加密 	
在以上章节中，我们对Spring Cloud集成Nacos实现配置动态刷新的用法及原理进行了介绍，接下来我们将进阶介绍如何在应用中通过MSE Nacos和KMS提升应用内配置的安全性。

应用中的配置类型多种多样，其中某些配置具有较高的敏感性，比如数据库的连接地址，用户名密码，一些第三方组件的秘钥，Token以及其他业务功能中敏感配置等等，这些配置的安全性非常重要，如果泄漏可能会对业务造成不可估量的影响。以往我们会将这些配置放在application.properties，或者环境变量，JVM参数中。以下是一些常见的用法:

<font style="color:#C75C00;">*以下示例中的敏感参数均为模拟数据</font>

application.properties

```plain
# 数据库配置
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/myapp
spring.datasource.username=user001
spring.datasource.password=pass!@#$%

# 秘钥Token等 secret and token
app.secret=GFYIdryujixxx
key.token=eedsjpp56hko8h

# 业务参数
app.switch=false
app.threadhold=0.8

```

SpringBean

```plain
@Component
public class SecretConfig{

  @Value("${app.secret}")
  String secret;
  
  @Value("${app.secret}")
  String token;

  @PostConstruct
  public void init(){
    //init client use token and secret
  }
}
```

```plain
@Component
public class AppConfig{

  @Value("${app.switch:false}")
  boolean switch;
  
  @Value("${app.threadhold}")
  double threadhold;

}
```

	这些参数我们都可以通过前两个章节介绍的内容将配置迁移至Nacos中实现配置的动态更新，但对于其中数据库密码Token等数据，通常会有更多安全性层面的考虑，比如这些敏感配置存储在Nacos中安全性是否可以保证，应用访问Nacos传输过程中数据是否存在泄漏可能性，敏感配置和普通的业务配置能否设置不同的读写权限，要实现以上安全性的要求，业务的代码是否可以尽量低成本改造等等。

要实现以上的安全性诉求，我们要做到以下几点： 

1. 敏感配置在Nacos需要加密存储，不能直接明文存储 

2. 敏感配置在传输过程中需要加密传输，防止中间网络设备通过抓包方式窃取数据。

3. 应用中的业务代码不能感知配置是否加密，仍需要按照之前的方式读取属性值，降低改造成本。

以下我们将介绍如何通过MSE Nacos及KMS实现零代码改造实现上述诉求。

## 加密配置迁移
我们可以将原本配置在application.properties中的属性按照第二章节中介绍到配置迁移至Nacos中，不同的是，我们将在Nacos中创建两个属性文件，分别为application.properties ，用于存放普通的业务配置，以及cipher-kms-aes-256-application.properties，用于存放数据源相关的敏感配置。为了防止解密配置和普通配置属性文件中的属性值重复，我们可以在加密配置中的属性值统一加上encrypted前缀。

**Nacos中的配置**

1.dataId=cipher-kms-aes-256-application.properties，group=secret

```plain
# 数据库配置
encrypted.spring.datasource.driver-class-name=com.mysql.jdbc.Driver
encrypted.spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
encrypted.spring.datasource.username=user001
encrypted.spring.datasource.password=pass!@#$%

# 秘钥Token等 secret and token
encrypted.app.secret=test_GFYIdryujixxx
encrypted.key.token=test_eedsjpp56hko8h
```

2.application.properties

原先的application.properties中的属性暂时保持不动，等应用程序侧的所有节点引入新配置cipher-kms-aes-256-application.properties之后，再对其做变更。

## 工程属性文件改造
调整项目工程下的application.properties ，在spring.config.import添加新加配置cipher-kms-aes-256-application.properties，以及设置KMS初始化相关参数。

```plain
spring.config.import=nacos:cipher-kms-aes-256-application.properties?group=secret&refreshEnabled=true,nacos:application.properties?refreshEnabled=true
spring.cloud.nacos.config.server-addr={server addr}

# 设置客户端NacosClient访问KMS所需参数
spring.cloud.nacos.config.kms_region_id=cn-hangzhou
spring.cloud.nacos.config.kmsEndpoint=kst-xxx.cryptoservice.kms.aliyuncs.com
spring.cloud.nacos.config.kmsVersion=v3.0
spring.cloud.nacos.config.kmsClientKeyFilePath=clientKey_hangzhou.json
spring.cloud.nacos.config.kmsPasswordKey=10xxxd1d
spring_cloud_nacos_config_kmsPasswordKey=10xxxd1d
spring.cloud.nacos.config.kmsCaFilePath=clientKey_hangzhou.json

```

*客户端NacosClient访问KMS所需参数和kms版本相关，具体步骤及后续更新见MSE官方文档 [https://help.aliyun.com/zh/mse/user-guide/create-and-use-encrypted-configurations](https://help.aliyun.com/zh/mse/user-guide/create-and-use-encrypted-configurations)

修改配置重启业务应用完成后，此时应用程序读取的还是Nacos中application.properties的属性值，但是此时encrypted.前缀的相关属性已经存在于Spring的上下文中。

## 配置拆分
当前应用程序重启完成之后，我们对Nacos的配置做如下修改：

applicaition.properties

```plain
# 数据库配置
spring.datasource.driver-class-name=${encrypted.spring.datasource.driver-class-name}
spring.datasource.url={encrypted.spring.datasource.url}
spring.datasource.username=${encrypted.spring.datasource.username}
spring.datasource.password=${encrypted.spring.datasource.password}

# 秘钥Token等 secret and token
app.secret=${encrypted.app.secret}
key.token=${encrypted.key.token}

# 业务参数
app.switch=false
app.threadhold=0.8
```

其中cipher-kms-aes-256-application.properties中的属性并不会被应用程序代码直接读取，而是在application.properties通过配置嵌套的模式间接引用,程序代码中本质上还是读取的application.properties中的属性。

过程中，我们只对工程中的配置做了改造，而业务代码层面没有做任何改动。改造完成后，cipher-kms-aes-256-application.properties配置中的内容在Nacos服务端，传输过程中以及应用本地的缓存中都是密文形式，在业务应用进程中，NacosClient内部会和KMS交互完成密文解密成明文，并且在启动和运行时我们可以在nacos的config.log日志中观察到nacos和kms交互的日志，以便于更好地排查遇到的问题。

# <font style="color:rgb(242, 98, 46);">结语</font>
<font style="color:rgba(0, 0, 0, 0.9);">以上我们在Spring Cloud应用中结合Nacos实现了运行期配置动态更新的功能，并对两者结合的工作原理做了简单介绍，以及在此基础上结合KMS在不改动代码的情况下对应用使用的敏感配置进行保护，解决将配置迁移到Nacos中可能存在的数据安全顾虑。Nacos作为广泛使用的配置中心，除了基础的配置实时动态更新的核心功能外，还支持配置监听查询(配置订阅节点查询)，推送轨迹，标签灰度等进阶功能，提升易用性，安全性方面除了本文中介绍的配置加密外，也支持访问控制，传输加密等多维护保障数据安全。</font>

<font style="color:rgba(0, 0, 0, 0.9);"></font>

**相关链接：**

[1] Nacos 官网

_<u><font style="color:rgb(0, 122, 170);">https://nacos.io</font></u>_

[2] Nacos Github 主仓库

_<u><font style="color:rgb(0, 122, 170);">https://github.com/alibaba/nacos</font></u>_

[3] 生态组仓库

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group</font></u>_

[4] Spring Cloud Alibaba

[https://sca.aliyun.com/docs/2023/user-guide/nacos/quick-start/](https://sca.aliyun.com/docs/2023/user-guide/nacos/quick-start/)

**Nacos 多语言生态仓库：**

[1] Nacos-GO-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-go</font></u>_

[2] Nacos-Python-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-python</font></u>_

[3] Nacos-Rust-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-rust</font></u>_

[4] Nacos C# SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-csharp</font></u>_

[5] Nacos C++ SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-cpp</font></u>_

[6] Nacos PHP-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-php</font></u>_

[7] Rust Nacos Server

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/r-nacos</font></u>_

****

**推荐阅读：**

《[Nacos 安全零信任实践](http://mp.weixin.qq.com/s?__biz=MzUzNzYxNjAzMg==&mid=2247563843&idx=1&sn=c115a13b7a91ef66e001de6cfc4bf0ed&chksm=fae7f18ccd90789aed1ba919775facc1775b3eedcda2991c998eee8eaa291d5b193fd5c353a3&scene=21#wechat_redirect)》

《[MSE Nacos：解决敏感配置的安全隐患](http://mp.weixin.qq.com/s?__biz=MzUzNzYxNjAzMg==&mid=2247560684&idx=1&sn=edad3ba00700c7a7848631c1571771ba&chksm=fae7e623cd906f35dbf754daef77f5b60e6963f7f15f14ce337cadbb5e3c70d69a898e82846e&scene=21#wechat_redirect)》

《[Nacos 配置中心变更利器：自定义标签灰度](https://mp.weixin.qq.com/s/si_7MY_9MRAhatnfX-em4A)》


