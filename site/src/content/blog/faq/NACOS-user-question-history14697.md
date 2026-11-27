---
id: "question-history-14697"
title: "建议使用Nacos放在公网吗"
date: "2024-09-26"
category: "expertConsultation"
description: "根据我了解的信息中的信息，**不建议将Nacos直接暴露在公网上**。原因在于：- Nacos属于内网核心组件，如果直接暴露在公网环境下且**没有开启鉴权功能**或者使用了默认的密钥（如密码、token.secret.key/server.identity等），那么非常容易遭受黑客攻击，从而导致存储"
tags: ["nacos","opensource","建议","使用","公网"]
keywords: ["nacos","opensource","建议","使用","公网"]
---

## 用户问题 ： 
 建议使用Nacos放在公网吗  

---------------
## 专家官方解答 ：

根据我了解的信息中的信息，**不建议将Nacos直接暴露在公网上**。原因在于：

- Nacos属于内网核心组件，如果直接暴露在公网环境下且**没有开启鉴权功能**或者使用了默认的密钥（如密码、token.secret.key/server.identity等），那么非常容易遭受黑客攻击，从而导致存储于Nacos中的敏感信息被非法获取。
- 从Nacos 2.2.3版本起，默认不再指定默认密钥，以防止因未修改默认密钥而导致的安全风险。

### 建议措施

为避免上述风险，强烈建议采取以下行动之一或组合：

1. **关闭公网访问**：最直接有效的方法是限制Nacos仅能通过内网访问，这通常涉及到配置防火墙规则或其他网络层面的安全设置来阻止外部IP对Nacos服务端口的访问请求。
   
2. **开启鉴权机制**：
   - 如果业务需求确实需要开放部分外网访问权限给Nacos，则必须启用Nacos自带的身份验证与授权机制，并确保所有用户都使用强密码策略，定期更换凭证。
   - 对于较新版本的Nacos (≥2.2.3)，请按照官方文档指示正确配置密钥而非依赖于任何预设值。

3. **考虑使用阿里云MSE提供的Nacos云服务**：该服务不仅简化了安全配置过程，还提供了企业级鉴权能力、数据加密传输等额外安全保障。详情见[这里](https://nacos.io/cloud/?from=wuyi)。

4. **迁移至更安全环境**：对于已经部署但希望增强安全性的现有系统，可以考虑利用阿里云MSE进行平滑迁移，支持应用从自建实例无缝切换到托管微服务注册中心，具体指南可查阅[此处](https://help.aliyun.com/zh/mse/user-guide/migrate-applications-from-self-managed-instances-to-mse-microservices-registry?spm=a2c4g.11186623.0.0.76e17b26TS1Abr)。

### 补充说明
执行上述任一操作时，请务必谨慎处理，特别是在生产环境中做出改动前要充分评估可能带来的影响，并做好相应的备份和测试工作，以减少意外中断的风险。此外，对于步骤较为复杂的操作项，建议选择业务低峰期实施，并逐步灰度上线，以便及时发现并解决问题。


<font color="#949494">---------------</font> 


## 参考链接 ：

* 专家经验：Nacos不开启鉴权&暴露公网会导致风险 
 
 * 专家经验：nacos-opensource Nacos 快速开始 
 
 * 专家经验：Nacos使用的稳定性说明 


 <font color="#949494">---------------</font> 
 


## <font color="#FF0000">答疑服务说明：</font> 

本内容经由技术专家审阅的用户问答的镜像生成，我们提供了<font color="#FF0000">专家智能答疑服务</font>,使用方法：
用法1： 在<font color="#FF0000">页面的右下的浮窗”专家答疑“</font>。
用法2： 点击[专家答疑页](https://answer.opensource.alibaba.com/docs/intro)（针对部分网站不支持插件嵌入的情况）
### 另：


有其他开源产品的使用问题？[点击访问阿里AI专家答疑服务](https://answer.opensource.alibaba.com/docs/intro)。
### 反馈
如问答有错漏，欢迎点：[差评](https://ai.nacos.io/user/feedbackByEnhancerGradePOJOID?enhancerGradePOJOId=17171)给我们反馈。
