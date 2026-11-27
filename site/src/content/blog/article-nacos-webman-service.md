---
title: Webman高性能框架实现Nacos微服务动态配置服务的全面突破
keywords: [Nacos, 注册]
description: Webman高性能框架实现Nacos微服务动态配置服务的全面突破
date: "2024-08-08"
category: article
---

# Nacos 简介
## Nacos是什么

[Nacos](https://nacos.io/) 属于阿里巴巴的一个开源的项目，通过一组简单的特性集，Nacos能够帮助用户实现服务动态发现、服务配置、服务元数据及流量管理。nacos主要提供三种功能：`服务注册与发现`、`动态配置服务`、`动态DNS服务`。

## 特性

### 动态配置服务

* 动态配置服务可以让您以中心化、外部化和动态化的方式管理所有环境的应用配置和服务配置。
* 动态配置消除了配置变更时重新部署应用和服务的需要，让配置管理变得更加高效和敏捷。
* 配置中心化管理让实现无状态服务变得更简单，让服务按需弹性扩展变得更容易。

> 可以通过管理系统去更新配置项，并且在更新完配置项之后，可以主动推送到订阅了这个配置的客户端。动态配置服务可以跳过重启直接实现配置的实时生效，可以使我们的服务拥有更多的灵活性。

### 服务注册与发现

Nacos 支持基于 DNS 和基于 RPC 的服务发现。服务提供者使用 原生SDK、OpenAPI、或一个独立的Agent TODO注册 Service 后，服务消费者可以使用DNS TODO 或HTTP&API查找和发现服务。

Nacos 提供对服务的实时的健康检查，阻止向不健康的主机或服务实例发送请求。Nacos 支持传输层 (PING 或 TCP)和应用层 (如 HTTP、MySQL、用户自定义）的健康检查。 

> Nacos提供多种服务注册与发现，包括基于 DNS 和基于 RPC 的服务发现和RPC 如 dubbo 的服务注册与发现。

### 动态DNS服务

动态 DNS 服务支持权重路由，让您更容易地实现中间层负载均衡、更灵活的路由策略、流量控制以及数据中心内网的简单DNS解析服务。

动态DNS服务还能让您更容易地实现以 DNS 协议为基础的服务发现，以帮助您消除耦合到厂商私有服务发现 API 上的风险。

# 动态配置服务

配置中心的作用是将本地配置文件云端话，所谓的云端也就是 Nacos 的服务器端，这样既能保证配置文件中的敏感数据不会暴露，同时又提供了实时的修改、查看、回滚和动态刷新配置文件的功能，非常实用。

## 为什么需要配置中心

> **在没有配置中心之前，传统应用配置的存在以下痛点：**

1. 采用本地静态配置，无法保证实时性：修改配置不灵活且需要经过较长的测试发布周期，无法尽快通知到客户端，还有些配置对实时性要求很高，比方说主备切换配置或者碰上故障需要修改配置，这时通过传统的静态配置或者重新发布的方式去配置，那么响应速度是非常慢的，业务风险非常大。
2. 易引发生产事故：比如在发布的时候，容易将测试环境的配置带到生产上，引发生产事故。
3. 配置散乱且格式不标准：有的用properties格式，有的用xml格式，还有的存DB，团队倾向自造轮子，做法五花八门。
4. 配置缺乏安全审计、版本控制、配置权限控制功能：谁？在什么时间？修改了什么配置？无从追溯，出了问题也无法及时回滚到上一个版本；无法对配置的变更发布进行认证授权，所有人都能修改和发布配置。

## 配置中心好处

1. 通过配置中心，可以使得配置标准化、格式统一化
2. 当配置信息发生变动时，修改实时生效，无需要重新重启服务器，就能够自动感知相应的变化，并将新的变化统一发送到相应程序上，快速响应变化。比方说某个功能只是针对某个地区用户，还有某个功能只在大促的时段开放，使用配置中心后只需要相关人员在配置中心动态去调整参数，就基本上可以实时或准实时去调整相关对应的业务。
3. 通过审计功能还可以追溯问题

# 配置中心功能实现

## 安装插件

```php
composer require workbunny/webman-nacos
```
## 配置 Nacos

插件配置文件路径：`plugin/workbunny/webman-nacos/app.php`

### 1、服务端配置
```php
/** nacos 服务端地址 */
'host' => '192.168.1.2', 

/** nacos 服务端端口 */
'port' => 8848,

/** nacos 认证用户名 */
'username' => 'nacos',

/** nacos 认证用户密码 */
'password' => 'nacos',
```
> 以上主要是配置Nacos Server的服务端连接、认证用户名、认证用户密码配置。

### 2、配置中心配置
```php
'config_listeners' => [
    [
        /** DataID */
        'payment.php',
        /** groupName */
        'DEFAULT_GROUP',
        /** namespaceId */
        '',
        /** filePath @desc 配置文件本地保存的地址 */
        config_path() . '/nacos/payment.php',
    ],
    [
        /** DataID */
        'application-dev.yml',
        /** groupName */
        'DEFAULT_GROUP',
        /** namespaceId */
        'b34ea59f-e240-413b-ba3d-bb040981d773',
        /** filePath @desc 配置文件本地保存的地址 */
        config_path() . '/nacos/application-dev.yml',
    ],
],
```
> 以上配置含义和规则

1. `config_listeners` 配置数组支持配置多个配置文件，每个配置文件为一个单独的数组
2. `payment.php`配置说明
    1. Data ID：`payment.php`
    2. 配置格式：`text`
    3. 用户组：`DEFAULT_GROUP`
    4. 命名空间：`public` （默认命名空间）
    5. 命名空间ID：`''`
    6. 配置文件本地保存位置：`config_path() . '/nacos/payment.php'`
3. `application-dev.yml`配置说明
    1. Data ID：`application-dev.yml`
    2. 配置格式：`YAML`
    3. 用户组：`DEFAULT_GROUP`
    4. 命名空间：`java`
    5. 命名空间ID：`b34ea59f-e240-413b-ba3d-bb040981d773`
    6. 配置文件本地保存位置：`config_path() . '/nacos/application-dev.yml'`

> 注意：`config_path()` 助手函数为`webman`框架自带函数，用于获取当前配置目录路径地址，该函数指定路径为`项目/config`目录

### 3、编写代码读取配置文件


创建一个测试控制器文件，用于手动读取配置文件
```php
<?php
declare(strict_types=1);

namespace app\controller;

class Test
{
    /**
     * @desc nacos 配置中心
     * @author Tinywan(ShaoBo Wan)
     */
    public function nacos()
    {
        $client = \Workbunny\WebmanNacos\Client::channel();

        /** 读取命名空间为 public 的配置文件 payment.php */
        $payment = $client->config->get('payment.php', 'DEFAULT_GROUP');
        var_dump($payment);

        echo '读取命名空间配置文件' . PHP_EOL;

        /** 读取命名空间为 java 的配置文件 application-dev.yml */
        $application = $client->config->get('application-dev.yml', 'DEFAULT_GROUP', 'b34ea59f-e240-413b-ba3d-bb040981d773');
        var_dump($application);
    }
}
```

### 4、Nacos 控制台添加配置

在 Nacos 控制台创建并设置配置文件

#### 命名空间为`public`配置文件截图

![img](/img/blog/webman/article-nacos-webman-service-01.png)

>配置详情

![img](/img/blog/webman/article-nacos-webman-service-02.png)

> 配置文件`payment.php`内容


```php
<?php
/**
 * @desc 支付配置文件
 * @author Tinywan(ShaoBo Wan)
 * @date 2022/03/11 20:15
 */
 
return [
    'alipay' => [
        'default' => [
            // 必填-支付宝分配的 app_id
            'app_id' => '2016090900470841',
            // 必填-应用私钥 字符串或路径
            'app_secret_cert' => 'MIIEpAIBAAKCAQE4OUmD/+XDgCg==',
            // 必填-应用公钥证书 路径
            'app_public_cert_path' => public_path().'/appCertPublicKey_2016090900470841.crt',
            // 必填-支付宝公钥证书 路径
            'alipay_public_cert_path' => public_path().'/alipayCertPublicKey_RSA2.crt',
            // 必填-支付宝根证书 路径
            'alipay_root_cert_path' => public_path().'/alipayRootCert.crt',
            'return_url' => 'http://micro.train.tinywan.cn/gateway/payment/alipay-return',
            'notify_url' => 'http://micro.train.tinywan.cn/gateway/payment/alipay-notify',
            // 选填-服务商模式下的服务商 id，当 mode 为 Pay::MODE_SERVICE 时使用该参数
            'service_provider_id' => '',
            // 选填-默认为正常模式。可选为： MODE_NORMAL, MODE_SANDBOX, MODE_SERVICE
            'mode' => \Yansongda\Pay\Pay::MODE_SANDBOX,
        ]
    ]
];
```

#### 命名空间为`java`配置文件截图

可以在nacos控制台新建一个命名空间。这里命名一个`java`，主要是为了配置`yml`配置文件。

![img](/img/blog/webman/article-nacos-webman-service-03.png)

> 命名空间配置

![img](/img/blog/webman/article-nacos-webman-service-04.png)

> 配置详情

![img](/img/blog/webman/article-nacos-webman-service-05.png)

> 配置文件`application-dev.yml`内容

```yaml
server:
  port: 8501
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/train_main_service?characterEncoding=utf-8&serverTimezone=GMT%2B8
    username: tinywan
    password: 123456
  jpa:
    properties:
      hibernate:
        show_sql: true
        format_sql: true
```

### 5、控制器读取配置文件

配置好没问题了，启动webman项目
```php
php start.php start
```

> 启动成功终端截图如下

![img](/img/blog/webman/article-nacos-webman-service-06.png)

通过地址`http://127.0.0.1:8888/test/nacos` 读取配置文件，该路由地址由自己自定义即可，只要能访问通控制器即可。

>注意：由于上面测试控制器代码没有响应到前端页面，所以所有的打印都会在控制台输出

![img](/img/blog/webman/article-nacos-webman-service-07.png)

> 可以看到，以上终端完美的读取到了两个配置文件。同时配置文件也被写入到了本地配置文件对应设置的目录。

![img](/img/blog/webman/article-nacos-webman-service-08.png)

### 6、动态读取配置

> 动态读取配置：在 `Nacos` 配置中心修改的配置内容，在不重启项目的前提下可以实时的读取到。

[webman-nacos](https://github.com/workbunny/webman-nacos)组件默认会启动一个名为 `config-listener` 的进程，用于监听在配置文件 `plugin/workbunny/webman-nacos/app.php` 中 `config_listeners` 下的配置内容。

> 如果想自行掌控调用，可以使用如下服务

```
$client = \Workbunny\WebmanNacos\Client::channel();

// 异步非阻塞监听
// 注：在webman中是异步非阻塞的，不会阻塞当前进程
$response = $client->config->listenerAsyncUseEventLoop();

// 异步阻塞监听
// 注：在webman中是异步阻塞的，返回的是Guzzle/PromiseInterface，配合wait()可以多条请求并行执行；
//     请求会阻塞在 **wait()** 直到执行完毕；详见 **ConfigListernerProcess.php** 
$response = $client->config->listenerAsync();

# 同步阻塞监听
$response = $client->config->listener();
```

> 动态读取实现演示

这里动态修改一下`application-dev.yml` 配置文件内容

![img](/img/blog/webman/article-nacos-webman-service-09.png)

### 7、历史版本一键回滚

> Nacos 通过提供配置版本管理及其一键回滚能力，帮助用户改错配置的时候能够快速恢复，降低微服务系统在配置管理上的一定会遇到的可用性风险。让您能够轻松的实现溯源和回滚配置文件

![img](/img/blog/webman/article-nacos-webman-service-10.png)


