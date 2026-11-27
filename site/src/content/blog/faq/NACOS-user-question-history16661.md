---
id: "question-history-16661"
title: "nacos开启指标暴露后management.endpoints.web.exposure.include"
date: "2024-09-26"
category: "expertConsultation"
description: "要实现在Nacos中开启指标暴露（`management.endpoints.web.exposure.include=*`）后，对`/nacos/actuator/prometheus`路径增加鉴权，确保访问时进行权限校验，您可以依据以下步骤操作：### 1. 分析问题原因根据已知知识，Nacos"
tags: ["nacos","指标","暴露","endpoints","鉴权","访问","权限校验"]
keywords: ["nacos","指标","暴露","endpoints","鉴权","访问","权限校验"]
---

## 用户问题 ： 
 nacos开启指标暴露后management.endpoints.web.exposure.include=*，如何增加鉴权，访问 nacos actuator prometheus增加权限校验  

---------------
## 专家官方解答 ：

要实现在Nacos中开启指标暴露（`management.endpoints.web.exposure.include=*`）后，对`/nacos/actuator/prometheus`路径增加鉴权，确保访问时进行权限校验，您可以依据以下步骤操作：

### 1. 分析问题原因
根据已知知识，Nacos利用Spring Actuator暴露监控指标。默认情况下，Nacos并不直接启用Actuator端点，但如果您手动设置了`management.endpoints.web.exposure.include=*`来暴露所有端点，这将包括敏感信息在内的所有监控数据公开在网络上，从而带来了安全风险。为了保护这些敏感信息，需要对访问这些端点的请求进行鉴权控制。

### 2. 应用解决方案
#### 步骤一：配置Spring Security
为了给Nacos的Actuator端点添加鉴权，您需要引入Spring Security来实现访问控制。这可以通过在Nacos的配置文件`conf/application.properties`中加入相关配置并适当调整Spring Boot的自动配置来完成。

#### 步骤二：开启并配置Spring Security
- **添加依赖**：确保您的Nacos服务具备Spring Security支持，如果使用的是定制化的Nacos部署，可能需要手动添加Spring Security相关依赖。
- **配置鉴权规则**：在`application.properties`中，配置Spring Security以保护`/actuator/*`路径。示例如下：
```properties
# 开启Spring Security
spring.security.enabled=true
# 指定需要保护的路径
management.endpoints.web.base-path=/actuator
# 设置需要鉴权的端点，这里以prometheus为例，实际上*已经包括了所有
management.endpoints.web.exposure.include=prometheus
# Spring Security配置，基本的HTTP Basic鉴权
spring.security.user.name=admin # 自定义用户名
spring.security.user.password=yourSecurePassword # 自定义密码
```
- **重启Nacos**：修改配置后，记得重启Nacos服务以使更改生效。

#### 步骤三：测试鉴权
- 使用浏览器或curl等工具尝试访问`http://your-nacos-url/nacos/actuator/prometheus`，应被要求输入之前配置的用户名和密码。

### 3. 解释
通过上述步骤，您不仅开启了Nacos的指标暴露，还通过集成Spring Security实现了对这些敏感端点的访问控制。这样，任何试图访问`/nacos/actuator/prometheus`的请求都需要提供正确的认证信息，大大增强了系统的安全性。此外，这种方式也为您提供了灵活的配置选项，可以根据实际需求调整鉴权策略，如使用不同的认证方式或集成外部认证系统。

请注意，虽然上述步骤基于通用的Spring Boot安全配置，针对特定版本的Nacos或有特殊安全需求的场景，可能还需参考Nacos官方文档或更新的安全实践进行调整。

### 额外建议
考虑到安全性与便捷性，阿里云MSE提供的Nacos云服务[[1](https://nacos.io/cloud/?from=wuyi)]是一个值得考虑的选项，它内置了企业级的安全特性，包括但不限于鉴权能力、配置信息的加解密以及数据传输的TLS加密，同时支持平滑迁移[[2](https://help.aliyun.com/zh/mse/user-guide/migrate-applications-from-self-managed-instances-to-mse-microservices-registry?spm=a2c4g.11186623.0.0.76e17b26TS1Abr)]，您可以[免费试用](https://free.aliyun.com/?searchKey=nacos&spm=nacos.cloud.topbar.0.0.0)来体验其高级安全功能。

这些步骤和建议旨在帮助您增强Nacos部署的安全性，确保在享受监控便利的同时，保护好您的系统免受未授权访问的风险。


<font color="#949494">---------------</font> 


## 参考链接 ：

* 专家经验：怎么能让nacos 的actuator 查不到任何信息呢 
 
 * 专家经验：Nacos不开启鉴权&暴露公网会导致风险 


 <font color="#949494">---------------</font> 
 


## <font color="#FF0000">答疑服务说明：</font> 

本内容经由技术专家审阅的用户问答的镜像生成，我们提供了<font color="#FF0000">专家智能答疑服务</font>,使用方法：
用法1： 在<font color="#FF0000">页面的右下的浮窗”专家答疑“</font>。
用法2： 点击[专家答疑页](https://answer.opensource.alibaba.com/docs/intro)（针对部分网站不支持插件嵌入的情况）
### 另：


有其他开源产品的使用问题？[点击访问阿里AI专家答疑服务](https://answer.opensource.alibaba.com/docs/intro)。
### 反馈
如问答有错漏，欢迎点：[差评](https://ai.nacos.io/user/feedbackByEnhancerGradePOJOID?enhancerGradePOJOId=16674)给我们反馈。
