---
title: "Nacos 多语言体系再添新员：首个 Rust 语言版本正式发布！"
description: "Nacos 多语言体系再添新员：首个 Rust 语言版本正式发布！"
date: "2024-08-22"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

<font style="color:rgba(0, 0, 0, 0.9);">距离 2.1.1 版本发布2个月后，Nacos 社区又迎来一波大更新。本次发布包含了 2 个 server 版本，1 个 go-sdk 版本以及新语言 SDK 的预告。</font>

### Nacos 2.1.2
<font style="color:rgba(0, 0, 0, 0.9);">2.1.2 主要增强了控制台的 UI 效果，变更了控制台的样式，使得内容更加紧凑美观；</font>

<font style="color:rgba(0, 0, 0, 0.9);">另外 2.1.2 对客户端大小进行了优化，大幅降低了客户端的 jar 包大小，同时还提供了纯净版 java-client，方便没有依赖 gRPC 或希望使用非 shaded 版本客户端用户使用，可以到 Java SDK</font><sup>**<font style="color:rgb(255, 106, 0);">[1</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);"> </font><font style="color:rgba(0, 0, 0, 0.9);">中查看纯净版使用方式。</font>

<font style="color:rgba(0, 0, 0, 0.9);">最后 2.1.2 修复了许多旧版本的问题，提高了稳定性。具体变更内容可参考变更日志：</font>

```plain
## Enhancement[#6112] Unified derby-data variables.[#7929] Reduce nacos-client jar size by minijar.[#8941] Support Fuzzy Query in Authority Control--for api change.[#8956] Internationalize product description content in nacos console.[#8976] Create new namespace with duplicate namespace show name.[#9091] build pure nacos-client when release.[#9210] Naming Distro sync support revision.
## Refactor&dependency[#8611] Close old datasource connection.[#8650] Make cluster/report both receive and send metadata.[#9013] refactor rpcClient and grpcClient to support set configuration.[#9014] refactor TpsMonitorPoint.[#9177] Upgrade org.yaml.snakeyaml version from 1.30 to 1.32[#9325] Add switch for naming async query.
## BugFix[#8882] Fix nacos-client 2.1.0 start error when using endpoint configuration.[#8910] Fix calculate instance count error when using batch register.[#8925] Fix the value of hasQueryString is always false.[#8928] Fix the replaceAll operation is invalid for server list.[#8931] Fix BatchInstanceData can't serialize problem.[#8934] Fix header lost when request retrying.[#8947] Fix the authentication/encryption plugin are not loaded on the nacos server.[#9023] Fix corner case config dataId 'cipher-' can't be create.[#9047] Fix ServerListMgr is not shutdown in nacos-client.[#9060] Fix print logs for NamingTraceEvent continuously.[#9062] Fix unsubscribe service failed problem.[#9101] Fix the ConnectionTimeout property in the datasource connection is overwritten problem.[#9227] Fix instance change event subscribe failed in 2.1.1 when no setting scope.[#9230] Fix error event order for snapshot loading.[#9269] Fix RpcClient parse ipv6 address error problem.[#9271][#6876] Fix 'JraftServer' NPE after server exceptionally shutdown.[#9277] Fix ClientServiceIndex not clean when service removed.[#9305] Fix build resource with error dataId.[#9311] Fix cache not removed when listener adding delay.[#9323] Fix service checking problem in 1.x http openAPI.
```

### 2.2.0-BETA
<font style="color:rgba(0, 0, 0, 0.9);">2.2.0 版本是 2.X 中一个较为重要的版本，它包含了一些较为重大的改动：</font><font style="color:rgba(0, 0, 0, 0.9);">  
</font>

<font style="color:rgba(0, 0, 0, 0.9);">首先，2.2.0 将会删除旧的冗余代码，即 1.X 模式服务发现和双写相关代码。删除后，2.2.0 版本将无法从 Nacos 1.X 服务器升级，只能从至少 2.0.0 版本升级。此更改不会影响对 1.X 客户端请求的适配，用户仍然可以使用 1.X 客户端链接 2.2.0 版本服务端。</font>

<font style="color:rgba(0, 0, 0, 0.9);">其次，2.2.0 将会合并部分阿里巴巴编程之夏 2022 和开源之夏 2022 的课题结果，例如V2 版本的 openAPI</font><sup>**<font style="color:rgb(255, 106, 0);">[2</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);">和数据源插件</font><sup>**<font style="color:rgb(255, 106, 0);">[3</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);">。其他课题也将在未来版本中发布。</font>

<font style="color:rgba(0, 0, 0, 0.9);">最后，2.2.0 增强了在 2.1.1 版本被列为 beta 功能的轨迹追踪插件和批量注册，这使它们更易于使用。关于如何开发和使用轨迹追踪插件，可以参考文末插件文档</font><sup>**<font style="color:rgb(255, 106, 0);">[4</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);">进行开发。</font>

<font style="color:rgba(0, 0, 0, 0.9);">由于这个版本中有许多重要的变化，所以社区计划做一个预发布的 BETA 版本。根据 BETA 测试的结果，下一个版本计划是 BETA2 或 GA 版本，欢迎广大用户积极下载</font><sup>**<font style="color:rgb(255, 106, 0);">[5</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);">试用测试，帮助社区尽早发现问题。</font>

注意：2.2.0-BETA 是一个预发布的 beta 版本，可能存在一些问题，请尽量避免在生产环境中使用。

<font style="color:rgba(0, 0, 0, 0.9);">2.2.0-BETA 版本具体变更内容可参考变更日志：</font>

```plain
## feature[#5863][#9331] Support batch register and batch deregister service.[#8308] Add v2 openAPI for nacos 2.0.[#8312] Support datasource plugins.[#8481] Support track tracing plugins.[#9366] Support Ldaps authentication.
## Enhancement[#7930] Reomve old redundant codes about 1.x naming.
## BugFix[#9334] Fix group_id data length different in many tables.[#9341] Fix can not create bean ldapAuthenticationProvider.[#9351] Fix instance count error in prometheus metrics.
```

_**<font style="color:rgb(242, 98, 46);">  
</font>**_

### 多语言 SDK
#### <font style="color:rgb(255, 106, 0);">Go</font>
<font style="color:rgba(0, 0, 0, 0.9);">Nacos Go SDK v2.1.1</font><sup>**<font style="color:rgb(255, 106, 0);">[6</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);">版本也在近期发布了正式版本，在 v2.1.0 带来大量新特性和改进的基础上，进一步加强了使用的稳定性，欢迎大家升级使用。</font>

#### <font style="color:rgb(255, 106, 0);">Rust</font>
<font style="color:rgba(0, 0, 0, 0.9);">Rust 语言是最近非常如火如荼</font><font style="color:rgba(0, 0, 0, 0.9);">的新编程语言生态，Nacos 社区的小伙伴第一时间加入了对 rust 生态的建设，目前 nacos-rust-sdk</font><sup>**<font style="color:rgb(255, 106, 0);">[7</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);">已完成基础的功能建设工作，同时实现了配置中心的核心功能，已发布 v0.1.1 版本供社区试用。</font><font style="color:rgba(0, 0, 0, 0.9);">  
</font>

<font style="color:rgba(0, 0, 0, 0.9);">随着社区小伙伴的逐渐完善和更多愿意贡献的贡献者加入，nacos-rust-sdk 很快也能够支持注册中心的功能，发布 1.0 的正式版本，这里也欢迎更多对 rust 有兴趣，希望找个项目练手的小伙伴加入一起建设 nacos-rust-sdk。</font>

#### <font style="color:rgb(255, 106, 0);">PHP</font>
<font style="color:rgba(0, 0, 0, 0.9);">PHP 语言作为老牌服务端编程语言，以往有不少用户询问关于 PHP 客户端的问题；</font><font style="color:rgba(0, 0, 0, 0.9);">虽然社区中有很多根据 openAPI 自行开发的 PHP 客户端，但一直没有功能较全的版本和愿意持续维护捐献的 PHP 客户端实现，导致社区中一直没有属于 nacos-group 的 PHP 客户端。</font><font style="color:rgba(0, 0, 0, 0.9);">  
</font>

<font style="color:rgba(0, 0, 0, 0.9);">今年由 huangwh2014 贡献到社区的 PHP 客户端终于能够让 PHP 的项目能够接入 Nacos，享受 Nacos 所带来的各种功能。</font>

<font style="color:rgba(0, 0, 0, 0.9);">由于该版本的 PHP 客户端</font><sup>**<font style="color:rgb(255, 106, 0);">[8</font>**</sup><sup>**<font style="color:rgb(255, 106, 0);">]</font>**</sup><font style="color:rgba(0, 0, 0, 0.9);">仍然是基于 openAPI 进行开发的，因此不具备 gRPC 的能力，希望社区的各位小伙伴积极参与项目，早日让 PHP 客户端进入 2.X 的时代。</font>

### 社区
#### <font style="color:rgb(255, 106, 0);">编程之夏&开源之夏</font>
<font style="color:rgba(0, 0, 0, 0.9);">经历了 6 月-9 月的夏日，Nacos 的编程之夏和开源之夏活动也圆满结束。</font><font style="color:rgba(0, 0, 0, 0.9);">参与课题的9位同学也完成了他们的开源社区体验。</font><font style="color:rgba(0, 0, 0, 0.9);">在此期间，有些同学完成了 Nacos 新 openAPI，新插件的开发；</font><font style="color:rgba(0, 0, 0, 0.9);">有些同学完成了 Nacos 对 K8s，Mesh 化的探索；</font><font style="color:rgba(0, 0, 0, 0.9);">也有些同学深耕于 Nacos 客户端与服务端的协商机制。</font><font style="color:rgba(0, 0, 0, 0.9);">根据当前功能的完成程度和 Nacos 社区的版本的规划，这些重要的改动会在后续版本中逐渐与大家见面。</font><font style="color:rgba(0, 0, 0, 0.9);">在这里感谢参加编程之夏与开源之夏的同学和导师们的热情付出，同时也感谢阿里巴巴和中科院举办的优秀的开源活动。</font><font style="color:rgba(0, 0, 0, 0.9);">期待明年再见。</font>

#### <font style="color:rgb(255, 106, 0);">Committer</font>
<font style="color:rgba(0, 0, 0, 0.9);">Nacos 社区新晋级了两位 Committer 同学。</font><font style="color:rgba(0, 0, 0, 0.9);">  
</font>

<font style="color:rgba(0, 0, 0, 0.9);">自由开发者 onewe 同学主要优化统一了 Nacos 客户端的配置参数的加载逻辑，同时优化了 LDAP 鉴权插件和大量控制台使用内容，找到并修复了不少 Nacos 的问题。经社区 PMC 及 Committer 的投票表决，提名为 Nacos 社区的 Committer。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/webp/299576/1724292846271-90dfc6c5-cfcd-4244-a763-247ccf295359.webp)![](https://intranetproxy.alipay.com/skylark/lark/0/2024/webp/299576/1724292844303-8fe3d9c7-1d12-407b-a30b-764d3c161cb8.webp)

<font style="color:rgba(0, 0, 0, 0.9);">另外来自小米科技的 chenhao26-nineteen 同学主要完成了 Nacos 批量注册的新功能，并且使用该新功能优化了 Nacos-Sync 的同步逻辑，极大提升了 Nacos-Sync 的性能，同时也修复了不少 Nacos 的问题。经社区 PMC 及 Committer 的投票表决，提名为 Nacos 社区的 Committer。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/webp/299576/1724292844328-66f0223b-1289-47cb-9809-bc12c6a5aa2f.webp)![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1724292844251-264b9fef-5a65-4d97-abe1-c0b52cd3cf1d.png)

<font style="color:rgba(0, 0, 0, 0.9);">期望更多小伙伴一起参与贡献～～</font>

_**<font style="color:rgb(242, 98, 46);">  
</font>**_

### About Nacos
<font style="color:rgba(0, 0, 0, 0.9);">Nacos 致力于帮助您发现、配置和管理微服务。</font><font style="color:rgba(0, 0, 0, 0.9);">Nacos 提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。</font><font style="color:rgba(0, 0, 0, 0.9);">  
</font>

<font style="color:rgba(0, 0, 0, 0.9);">Nacos 帮助您更敏捷和容易地构建、交付和管理微服务平台。Nacos 是构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施。</font>

<font style="color:rgba(0, 0, 0, 0.9);">Nacos 社区同时也开启了关于 </font>[<font style="color:rgba(0, 0, 0, 0.9);">Nacos 3.0</font>](https://mp.weixin.qq.com/s?__biz=MzU4NzU0MDIzOQ==&mid=2247511397&idx=3&sn=3ed3ef95e5ce1e396554ba4f370a0254&scene=21#wechat_redirect)<font style="color:rgba(0, 0, 0, 0.9);"> </font><font style="color:rgba(0, 0, 0, 0.9);">的畅想和规划，欢迎社区积极参与到新版本的建设中。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/webp/299576/1724292846641-ca44d47d-2bee-481b-897f-37d7e751ece6.webp)

<font style="color:rgba(0, 0, 0, 0.9);">最后欢迎大家钉钉扫码加入 Nacos 社区群</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/webp/299576/1724292844732-3198d21e-6965-4629-b1d9-242176ddaf6c.webp)

_**<font style="color:rgb(34, 34, 34);">相关文档链接</font>**_

_<font style="color:rgb(34, 34, 34);">[1] </font>__<font style="color:rgb(34, 34, 34);">Java SDK</font>_

_<u><font style="color:rgb(0, 122, 170);">https://nacos.io/zh-cn/docs/v2/guide/user/sdk.html</font></u>_

_<font style="color:rgb(34, 34, 34);">[2] </font>__<font style="color:rgb(34, 34, 34);">V2 版本的 openAPI</font>_

_<u><font style="color:rgb(0, 122, 170);">https://nacos.io/zh-cn/docs/v2/guide/user/open-api.html</font></u>_

_<font style="color:rgb(34, 34, 34);">[3] </font>__<font style="color:rgb(34, 34, 34);">数据源插件</font>_

_<u><font style="color:rgb(0, 122, 170);">https://nacos.io/zh-cn/docs/v2/plugin/datasource-plugin.html</font></u>_

_<font style="color:rgb(34, 34, 34);">[4] </font>__<font style="color:rgb(34, 34, 34);">插件文档</font>_

_<u><font style="color:rgb(0, 122, 170);">https://nacos.io/zh-cn/docs/v2/plugin/trace-plugin.html</font></u>_

_<font style="color:rgb(34, 34, 34);">[5] </font>__<font style="color:rgb(34, 34, 34);">下载链接</font>_

_<u><font style="color:rgb(0, 122, 170);">https://github.com/alibaba/nacos/releases/tag/2.2.0-BETA</font></u>_

_<font style="color:rgb(34, 34, 34);">[6] </font>__<font style="color:rgb(34, 34, 34);">Go SDK v2.1.1</font>_

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-go/releases/tag/v2.1.1</font></u>_

_<font style="color:rgb(34, 34, 34);">[7] </font>__<font style="color:rgb(34, 34, 34);">nacos-rust-sdk</font>_

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-rust</font></u>_

_<font style="color:rgb(34, 34, 34);">[8] </font>__<font style="color:rgb(34, 34, 34);">PHP 客户端</font>_

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-php</font></u>_


