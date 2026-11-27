---
title: "Nacos 3.0 架构全景解读，AI 时代服务注册中心的演进"
description: "Nacos 3.0 架构全景解读，AI 时代服务注册中心的演进"
date: "2025-06-30"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

作者：杨翊（席翁），柳遵飞（翼严），罗鑫（子葵）

**Nacos** `/nɑ:kəʊs/`是 Dynamic **Na**ming and **Co**nfiguration **S**ervice的首字母简称，随着Nacos3.0的发布，定位由`“更易于构建云原生应用的动态服务发现、配置管理和服务管理平台”`升级至`“ 一个易于构建 AI Agent 应用的动态服务发现、配置管理和AI智能体管理平台 ”`。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750140682677-86732331-4802-41a2-a903-ebf4bb017da6.png)

Nacos 从 2018 年 7 月开始宣布开源以来，已经走过了第六个年头，在这六年里，备受广大开源用户欢迎，收获许多社区大奖。Nacos 在社区共同的建设下不断成长，逐步的开始帮助用户解决实际问题，助力企业数字化转型，目前已经广泛的使用在国内的公司中，根据微服务领域调查问卷，Nacos 在注册配置中心领域已经成为**国内首选**，占有**50%+国内市场**份额，被**各行各业的头部企业**广泛使用！在此期间，Nacos的部署包下载量突破300w次，官网每年访问用户数超过90w人，被国内各主流云厂商托管服务！

随着AI时代到来以及Nacos3.0版本的正式发布，Nacos未来的演进目标以及架构也会随之升级。本文会对比Nacos3.0与Nacos2.0的架构异同，同时对Nacos3.0的主要功能原理进行介绍。

# Nacos 2.0 架构回顾
Nacos2.0的架构主要聚焦对`性能`和`可扩展性`进行优化和提升。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750063777046-f772df41-b9f7-40a3-a71b-6f7dadb010c5.png)

对于`性能`升级，Nacos2.0通过将通信模型从HTTP升级至gRPC，从短连接模型升级到长连接模型，使得Nacos的通信吞吐量中极大提升；同时配合数据存储和数据结构模型的升级，进一步减少核心操作所涉及的步骤和链路，最终实现性能的**10倍提升**。

关于`可扩展性`升级，Nacos2.0通过将一些具有个性化需求的通用能力进行抽象，进行插件化改造的方式，允许Nacos用户和运维人员能够开发自定义插件，适配个人或企业的个性化需求。



虽然Nacos 2.0 在`性能`和`可扩展性`实现了一些突破，但仍然还存在一些挑战。![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750150796215-ba5ef42d-48d5-4624-9de9-e2b409cbf08f.png)

其中一个主要的挑战就是Nacos的安全风险。比如：Nacos2.0中所有的HTTP API均使用8848端口， 这其中及包含了1.X客户端使用的API，也包含了运维人员以及控制台的API， 对于不同类型的API， 对于权限的需求其实是不同的，对于网络访问的连通性要求也是不同的。使用单端口并且使用唯一的鉴权开关，导致了网络的访问控制，以及鉴权控制都不是很灵活方便。许多用户为了方便使用，将此端口暴露在办公网甚至公网环境，同时未开启鉴权，这就造成了安全风险。

另一个问题就是默认命名空间的使用问题，Nacos最初的版本中定义了命名空间作为数据资源的强隔离属性，不同的命名空间之间的服务和配置不能互相的发现和获取；但在最初版本中因为历史原因， 注册中心和配置中心对于默认命名空间的处理方式有一定的不统一，这导致了许多用户在使用默认命名空间时的配置经常配置错误或者出现疑惑；并且在Nacos2.0提供各种插件能力之后， 许多插件实现时也对不同的默认命名空间的不同有很多疑惑或者需要额外工作进行适配，严重阻碍了插件的开发以及插件的稳定性。

同时随着随着AI时代来临， AI Agent的应用，其部署的形态在之前云原生 可弹性可伸缩的基础上，要求应用更加轻量，更加弹性，例如FC场景；在这种要求下，Nacos之前的服务发现和配置管理的能力是否还能承载AIAgent的应用的部署？同时，AI Agent的应用广泛使用已是大势所趋， 随着越来越多的AI Agent的应用贯穿业务全线，Nacos能否帮助更好地管理AIAgent的应用，也是Nacos在当前的挑战，同时也是新的机遇。



为了应对这些挑战以及机遇，Nacos3.0架构也做了对应的升级。目标是希望在AI时代作为更安全的Registry。设计理念也由之前的`一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台`升级为`一个易于构建 <font style="color:#117CEE;">AI Agent</font>应用的动态服务发现、配置管理和<font style="color:#117CEE;">AI智能体管理</font>平台`。

# Nacos 3.0 架构
##  Nacos 3.0 整体架构解析
Nacos3.0升级后的整体架构仍然以一致性协议，通信模块，其他模块等通用功能模块为基座，承载出注册中心、配置中心、AI Registry、协议增强等功能；同时通过各类多语言SDK，桥接各个生态组件。架构的左右两侧，分别是Nacos的插件以及Nacos的一些拓展组件，他们一起构成了Nacos3.0的整体架构。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750150731448-980a8bde-f5b0-47ab-a9f6-75691e006ccb.png)

对于Nacos3.0的架构，我们主要关注的是新增的能力，即图中绿色和棕色的部分。

这其中既包括对原本注册配置中心的增强功能：模糊订阅，也包括了对AI相关能力的实现和规划，如MCP和管理，MCP Router，动态Prompt及A2A协议支持；同时也通过支持xDS协议及Nacos Controller继续加强和探索Mesh生态。

## Nacos 3.0 AI Registry 架构
了解完Nacos3.0的整体架构，接下来我们Nacos AI 中心（AI Registry）的架构设计。作为Nacos3.0最重要的规划能力，Nacos AI Registry的架构被分为3个层次，分别是`模型层`,`工具层`和`应用层`。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750150906580-56db1ed8-f93d-462a-953c-30ca54f90aa5.png)

在`模型层`中， 主要通过对AI模型中一些常用的动态参数，比如Prompt，学习率，联网参数等进行管理，复用在云原生应用中的配置动态管理和分发能力，帮助AI智能体在模型层进行快速调整及试错。

模型层上是`工具层`， 工具层主要帮助LLM模型和提供数据的MCP工具之间进行 自动的发现、注册以及检索等能力，复用在云原生应用中服务的动态注册、管理、发现的能力，帮助AI智能体应用快速及便捷地发现MCP工具，同时快速过滤无关工具，减少Token损耗。

最顶层是Agent的`应用层`，也即AI应用与AI应用之间的发现与协作。目前规划是通过支持A2A等社区标准协议，同时配合SAA等AIAgent应用框架，帮助AIAgent应用便捷的自动注册自身AI应用，同时发现其他AI应用，并能够像云原生应用一样，进行任务的分发以及结论的构成。



如果从功能视角出发，Nacos AI Registry又可被分为针对大模型LLM的`模型动态配置调优`,针对AI应用平台的`应用开发管理`以及针对AI Agent应用的`运行时能力增强`。Nacos希望通过不同的功能点，帮助AI应用像微服务云原生应用一样，能动态的调整Prompt，学习率等参数，无需重新发布，从而帮助AI应用简化开发，调试过程中的繁琐操作，提高AI应用的开发和运行效率。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750150998321-2c35d000-51ed-486d-9a03-6c056bd78d01.png)

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750151010613-3617d58c-4499-4137-a409-856527eb6538.png)

## Nacos 3.0 安全架构
Nacos 2.0中面临的一个主要的风险就是Nacos所有的HTTP OpenAPI均通过统一的端口进行暴露，同时使用了统一的鉴权开关，这使得使用者必须在便捷性和安全性中作出取舍，导致在许多部署的环境中可能存在安全风险。

Nacos 3.0为了解决这个问题，从Nacos的部署架构上作出演进，`独立控制台部署`，`拆分鉴权开关`，`分类API`并`默认开启控制台及管控类API的鉴权`

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750142042626-9027ba08-dcec-46f9-b49e-6da7293a2f16.png)

同时配合`配置加密插件`,`TLS传输`，来实现Nacos 3.0的零信任安全架构

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750143443333-807fa265-b701-4515-b022-cd76cb20cbff.png)

除了针对Nacos自身的安全零信任架构外， Nacos3.0还将与Druid，Spring AI Alibaba/Spring Cloud Alibaba等开源社区、与KMS等安全云产品合作， 提供面向应用侧的数据源运行期动态轮转方案。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750145846757-577b22cc-927d-4cee-9a28-c5badd8cb044.png)

在这套解决方案中，数据源的凭据始终由KMS等凭据托管平台和系统保存，全程无人工传递和配置的过程。用户可以设置定期进行凭据的自动轮转，或在怀疑密钥泄漏时手动触发凭据轮转；触发后会通过Nacos动态无损的将新的加密凭据通知到Druid或Spring AI Alibaba/Spring Cloud Alibaba，进行凭据的动态刷新和无损替换。极大降低了凭据泄漏的可能性，同时极大提高了安全性及出现安全风险时的收敛恢复速度。

# Nacos MCP Registry
## Nacos MCP Registry架构
Nacos 3.0 最主要的能力升级就是作为MCP Registry，支持了MCP服务的管理能力。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750129707197-a478c4aa-f554-4d4a-b048-86d7a69680f9.png)

Nacos MCP Registry支持三类MCP 服务的注册方式，

第一类是将存量HTTP或RPC的服务，通过声明自动转化为MCP服务，配合Higress的协议转换能力， 实现0代码改造成MCP服务协议，如何将存量API转化为MCP服务，详情可参见[文档](https://nacos.io/docs/latest/manual/user/ai/api-to-mcp/?spm=5238cd80.2ef5001f.0.0.3f613b7ciRMNL5)。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750129505952-5cf3e58d-6ecd-4621-9157-12487d29db6e.png)

第二类就是新构建的MCP服务注册， 配合Spring AI等AI Agent应用框架和Nacos-MCP的sdk，能够做到像微服务一样自动注册到Nacos中进行统一的管理和维护，如何通过Spring AI或Nacos-MCP的sdk进行MCP服务的自动注册与发现，请参见[文档](https://nacos.io/docs/latest/manual/user/ai/mcp-auto-register/?spm=5238cd80.2ef5001f.0.0.3f613b7ciRMNL5)。

第三类就是已经构建好的或其他供应商提供的MCP服务，可以导入到Nacos中，进行其描述、工具列表、工具Schema等内容的动态修改和维护，让调试MCP服务变得更加简单。

## Nacos MCP Router
Nacos 3.0 支持用户通过3种方式发布MCP服务，并对MCP服务的元数据和版本进行管理，但如果最终不能使用这些元数据和版本信息进行实际的使用，这些信息就没有意义。

因此Nacos 3.0 提供Nacos MCP Router 帮助终端使用者无需实际感知MCP服务列表，即可自动发现和使用需要的MCP服务。

Nacos MCP Router 提供两种工作模式，`动态路由`和`动态代理`。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750129512744-5082e5af-478e-4b4c-80d9-8cccd29b3b4e.png)

动态路由模式将会根据LLM所提供的关键字信息，对注册在Nacos中的MCP服务进行相关性过滤和筛选，选择出与关键字相关的MCP服务进行实际的使用，从而减少对LLM 上下文的消耗，实现`路由`MCP 服务的能力。

而代理模式能够进行MCP协议的转换，将`stdio`和`sse`类型的MCP服务，代理成`streamable`类型的MCP服务。代理模式下的Nacos MCP Router不根据关键字进行筛选，仅是将注册在Nacos中的`stdio`和`sse`类型的MCP服务，转化成`streamable`类型，同时应用用户在Nacos上修改和编辑的Tool描述信息，将转化后的MCP服务列表，返回给LLM供其使用。

# Nacos 3.0 RoadMap
Nacos 3.0的目标是成为`全面拥抱AI时代的服务、配置、AI Registry平台`，因此Nacos3.0的 RoadMap将会逐步实现AI Registry的能力，从当前的MCP管理，拓展到Prompt管理，Agent的自动注册发现，再到LLM模型的参数管理和托管；同时进一步加强注册配置中心的能力和更多相关领域协议的支持（如DNS，Mesh）。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750131723377-df1695b0-c2c3-4a73-a44a-edf28703b130.png)

Nacos也希望有更多的社区贡献者加入进Nacos社区，帮助Nacos更快更好的完善和实现Nacos3.0。

# Nacos MeetUp上海站
6月6日，Nacos在上海<font style="color:rgb(0, 0, 0);">举办了开源开发者沙龙MeetUp活动， 此次是 Nacos 社区成员今年首次线下分享最新的能力和实践，并邀请了 Spring AI Alibaba 和 Higress 一起分享一站式的开源解决方案。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1750754614053-88da137b-e328-4ff6-8e5e-905013bde659.png)

有需要MeetUp的PPT或希望回看MeetUp活动视频的同学，欢迎加入本文末尾的群中获取。

同时如果对Nacos3.0的架构，运行原理，最佳实践等内容感兴趣的同学，欢迎阅读Nacos3.0更多相关文章：

《0 代码改造实现应用运行时数据库密码无损轮转》[https://mp.weixin.qq.com/s/LkCYj_IOktGG7bXl0yBiSg](https://mp.weixin.qq.com/s/LkCYj_IOktGG7bXl0yBiSg)

《Nacos MCP Router 新版发布：支持 Docker 远程部署，MCP的多协议stido、SSE、Streamable互相转换》[https://mp.weixin.qq.com/s/80FW8VysOxJ3TUGXcEfL5A](https://mp.weixin.qq.com/s/80FW8VysOxJ3TUGXcEfL5A)

《企业生产环境中，实现 MCP 服务的统一管理和智能路由的实践》[https://mp.weixin.qq.com/s/BbOsO7u3nDLRDWnUwC90TA](https://mp.weixin.qq.com/s/BbOsO7u3nDLRDWnUwC90TA)

《Nacos 3.0 正式发布：MCP Registry、安全零信任、链接更多生态》[https://mp.weixin.qq.com/s/0j9R7cMw7opuRZrx8k2L6A](https://mp.weixin.qq.com/s/0j9R7cMw7opuRZrx8k2L6A)

# 欢迎加入Nacos社区
Nacos 致力于帮助您发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及AI管理。

Nacos 帮助用户更敏捷和容易地构建、交付和管理云原生AI应用的平台。 Nacos 是构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式、AI原生范式) 的服务基础设施。

Nacos 3.0 还有很多待完成的功能及大量待探索和开发的领域，欢迎大家扫码加入 Nacos 社区群及 Nacos MCP社区讨论群，参与 Nacos 社区的贡献和讨论，在 Nacos 社区一起搭把手，让你的代码和能力有机会能在各行各业领域内进行释放能量，期待认识你和你一起共建 Nacos 社区；



_<font style="color:#585a5a;">“Nacos 相信一切都是服务，每个服务节点被构想为一个星球，每个服务都是一个星系；Nacos 致力于帮助这些服务建立连接赋予智能，助力每个有面向星辰的梦想能够透过云层，飞在云上，更好的链接整片星空。”</font>_



Nacos 官网：[https://nacos.io/](https://nacos.io/)

Nacos 仓库地址：[https://github.com/alibaba/nacos](https://github.com/alibaba/nacos)

“Nacos社区群5”群的钉钉群号： 120960003144

“Nacos MCP 社区讨论群”群的钉钉群号： 97760026913

| ![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1745409709892-b3a5252e-eb16-41ea-a25c-91d9acc75353.png) | ![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/297800/1745409712778-599a0535-4433-43b8-b413-bff9dc1ab1e8.png) |
| --- | --- |



