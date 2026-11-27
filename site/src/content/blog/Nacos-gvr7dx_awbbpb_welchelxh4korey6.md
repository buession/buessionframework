---
title: "OpenAI 宕机思考｜Kubernetes 复杂度带来的服务发现系统的风险和应对措施"
description: "OpenAI 宕机思考｜Kubernetes 复杂度带来的服务发现系统的风险和应对措施"
date: "2024-12-18"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

12月11日，OpenAI 旗下 AI 聊天机器人平台 ChatGPT、视频生成工具 Sora 及其面向开发人员的 API 自太平洋时间下午 3 点左右起发生严重中断，耗费约三个小时才顺利恢复所有服务。

OpenAI 在事后报告中写道，“该问题源自新部署的遥测服务，此项服务无意间压垮了 Kubernetes 控制平面，导致关键系统发生连锁故障。 引发事故的**根本原因就是新的遥测服务配置意外在大规模集群中产生了大量 Kubernetes API 负载，导致控制平面不堪重负并破坏了基于 DNS 的服务发现能力。**”

可见，即使如实力强大的OpenAI，面对复杂Kubernetes架构，也不能很好处理Kubernetes服务发现和控制面解耦的问题。**造成这个问题的关键原因在于容器调度和业务关键服务发现链路耦合在一起，互相干扰，Kubernetes控制面故障影响了业务服务发现链路**。那么，Kubernetes体系下应如何选择服务发现系统，进一步提升业务稳定性呢？笔者认为，大型业务的服务发现系统应该具备高可靠性，高可伸缩性，高性能及高可维护性等特点，**采用独立服务发现系统**是一种相对较好的方案。本文以社区主流服务发现系统Nacos为例，从可靠性、可伸缩性、高性能、可维护性等4个方面探讨如何提升Kubernetes中微服务应用的稳定性。

# 一、如何提升系统可靠性
> 产品、系统在规定的条件下，规定的时间内，完成规定功能的能力称为可靠性。
>

## 解耦控制面与运行时
众所周知，Kubernetes主要工作资源调度，更偏向运维系统一些。从架构合理性上讲，运行时与控制面的系统不应耦合在一起， 甚至即使运维系统挂了也不会影响运行时。服务发现是运行时需求，而Kubernetes服务发现与运维系统绑定，一旦Kubernetes故障，上层运行态应用会受到致命影响。

Kubernetes服务发现依赖API Server，而API Server被非常多的组件调用，任何组件异常调用都有可能把 API Server打挂，进而影响服务发现。以OpenAI这次故障为例，本来是要增强系统的可观测性， 但因可观测组件的大量调用把API Server打挂了，导致系统DNS解析发生故障。如果服务发现体系相对独立，不依赖或弱依赖Kubernetes控制面，本次故障是可以避免的。

Nacos作为独立注册配置中心，可以不依赖Kubernetes独立部署，面向运行时设计，且有遵循多项面向失败原则，帮助业务服务发现与底层运维系统结构隔离，做到运维态系统故障不影响运行态系统，大大提高系统可靠性。

## 提升系统容灾能力
首先，Nacos可以帮助提升部署在Kubernetes上微服务体系的容灾能力。先讲一个实际案例，2023年11月，国内某头部出行服务公司的app突然出现大面积报错，导致长达几十个小时的服务不可用，影响大量用户正常出行。据官方发布通过，此次故障原因是底层软件异常导致（据推测为Kubernetes升级出现异常）。

对于大规模微服务系统，Kubernetes集群容灾是系统稳定性非常重要的一环。通常，为了实现Kubernetes集群级别容灾，我们通常会把应用部署在多个Kubernetes上。这样，即使一个Kubernetes集群出现问题，还有另一个集群可以提供服务。但因Kubernetes服务发现是面向本集群内的，多Kubernetes部署之后，应用间的服务发现，尤其是跨Kubernetes集群服务发现会变得比较困难。

Nacos作为独立的注册配置中心，可以不依赖Kubernetes独立部署。因此，如果在多Kubernetes引入Nacos作为注册配置中心，跨Kubernetes的服务发现问题就迎刃而解了。下图给出了Nacos作为独立注册配置中心的一个架构示意图。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734436926865-47d9b2a2-6ccb-407c-9b89-179374ae7e0b.png)



● Nacos独立于业务应用部署，可以部署在独立Kubernetes中也可以部署在其他平台上；

● 业务应用部署在两个Kubernetes集群上，服务提供者向Nacos注册服务；

● 服务订阅者从Nacos订阅服务，发起服务调用；

从上图可知，任何一个Kubernetes集群出现故障都不会影响整个系统的服务。因此，Nacos能大幅提升K8s体系微服务系统的可靠性。

## 面向失败设计
针对微服务系统常见的问题，Nacos做了多项面向失败的设计，帮助提升系统可靠性。本节重点介绍其中两个：客户端缓存和推空保护。

### 客户端缓存提高极端情况下系统可靠性
在Kubernetes系统中，CoreDNS是服务发现的核心组件，所有DNS请求都经过CoreDNS。一旦CoreDNS故障，所有服务调用都会受到影响。跟Kubernetes服务发现不同，Nacos客户端保存了缓存数据，在服务端故障无法更新服务IP列表的情况下，可以继续使用缓存提供服务，不会影响运行时服务调用。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734436943353-74f412d2-fb24-4d8d-a6be-9e22f39d5a42.png)



### 推空保护防止服务实例被误下线
当Nacos服务端发现某个服务下的实例全部下线时，可以自动触发保护逻辑，不会给客户端推送空IP列表。推空保护策略能预防因网络抖动或运维失误等导致的服务实例全部异常下线问题，保障业务运行可靠性。

# 二、如何提升系统可伸缩性
> 信息系统不需要对本身进行大量修改，只需要通过增加软硬件资源使服务容量产生线性(理想情况下)增长的特性称为可伸缩性。
>



在微服务架构下，传统单体应用被切分为多个小应用提供某些独立功能。随着业务发展，服务数量可能会出现爆发式增长。以淘宝为例，仅仅3年时间微服务实例规模就从十万级别暴涨到了百万级别。微服务数量爆发式增长对注册配置中心的可伸缩性提出了很高的要求。

Kubernetes服务发现的核心组件之一是etcd，系统所有微服务相关数据均存储于其中。但etcd是基于raft一致性协议开发的系统，Raft协议本身的特性决定所有写操作必须由Leader执行。因此，etcd可伸缩性较差，无法通过水平扩容解决微服务大规模增长带来的压力。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734436958350-2a196724-91aa-4317-a175-acf49068e6f6.png)

那如何提升系统的可伸缩性呢？一种方案是业务拆分，即把业务按照一定的逻辑拆分到多个Kubernetes集群，每个Kubernetes内部业务封闭， 但成本很高，可能会改变整个业务架构；另一种方案是引入可伸缩性好的注册配置中心。

与etcd不同，Nacos服务发现能力基于自研的Distro协议构建，每个服务端均可提供写服务，其性能随着系统的水平扩展而提高。因此，Nacos作为Kubernetes集群的注册配置中心，可以大幅提高整个系统的可伸缩能力。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734436967713-f64dc93d-6559-4261-ab2d-09ba7d977c17.png)

# 三、如何提升系统性能
Kubernetes系统内服务发现主要有两种方式：环境变量和DNS。默认情况下，Kubernetes会为每个服务自动生成一个环境变量，并注入到Pod中。如果业务规模很大，环境变量过多的问题就不可避免。环境变量过多会导致Pod启动过程很慢，笔者就多次遇到环境变量过多导致Pod无法启动的问题。

DNS服务发现方式允许开发者像调用普通域名一样调用Kubernetes内的服务，为多语言技术栈开发带来了很大便利。但频繁的DNS解析一方面会导致请求响应时间变慢，另一方面也会有一定的资源消耗。笔者曾做过一个简单的实验，对比直接以DNS域名方式调用和以IP直连方式调用的响应时间。结果显示，平均每次调用DNS方式的RT比直连慢3%-5%。3%-5%的延迟看起来微不足道，复杂业务可能都会有多次甚至几十次的调用，累积起来的延迟对终端用户的体验影响还是比较大的。

而Nacos服务发现方式，通常和微服务框架（SpringCloud/Dubbo等）结合，推送IP列表给框架，然后框架用IP直连的方式发起调用，省去了DNS解析的消耗。

下图简要画出了DNS解析和IP直连方式的区别。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734436984054-33df90a9-f5fb-4ca8-aa1b-dc46ecf524ee.png)



因此，对于技术栈统一的微服务架构，使用Nacos作为注册配置中心，可以进一步缩短响应时间提升系统吞吐量。

# 四、如何提升系统可维护性
> 系统发生故障后能够排除(或抑制)故障予以修复，并返回到原来正常运行状态的可能性称之为可维护性。
>

## 简化服务发现链路，降低维护成本
K8s服务发现依赖组件众多，下图给出了其典型架构。可以看到整个链路很复杂，涉及到api-server、etcd、kube-dns/coredns、kubelet、kube-proxy、iptables、ipvs等组件。一个Pod从扩容到最终接收到请求大概需要10步。

1. 创建Pod

2. 创建Service

3. kubelet检测Pod健康状态，并上报给api-server

4. api-server更新数据到etcd；

5. kube-proxy从api-server收到service变更

6. kube-proxy调用iptables/ipvs设置转发规则；

7. kube-dns/coredns从api-server监听到服务变化，更新dns解析记录；

8. 调用方pod从kube-dns/coredns解析service，得到cluster ip

9. 调用方pod用拿到的cluster ip发起调用；

10. 请求经过iptables/ipvs转发链到达服务提供方pod；

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734437005139-b537c167-c6e3-4940-a590-d92345d6705c.png)



而Nacos的服务发现工作原理，如下图所示， 涉及组件更少，只有Nacos Sdk和Nacos Server；一个Pod从创建到最终接收到请求大概只需要5步

1. 创建Pod

2. 服务提供方Pod注册服务到Nacos，并自动持续汇报心跳

3. 服务调用方Pod从Nacos订阅服务，拿到服务 IP列表；

4. 服务调用方使用Pod IP发起调用；

5. 请求打到服务提供方Pod；

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734437022210-3c12d850-2e6c-412d-b29c-c9ca520382c6.png)

可以看出，相比Kubernetes，Nacos注册中心服务发现链路更短，涉及组件少。对于大规模微服务系统，采用Nacos作为注册配置中心，可以大幅提升系统的可维护性。

## Kubernetes与非Kubernetes服务互相发现，提升架构兼容性
随着Kubernetes的普及，新增系统通常选择直接部署在Kubernetes中。但仍有很多存量应用部署在传统虚拟机或物理机上。这两类应用经常互相调用，因此它们能互相发现变得十分必要。然而Kubernetes服务发现与K8s运维系统绑定，Kubernetes服务要发现外部服务或被外部服务发现比较困难。如前所述，Nacos是独立系统，对接入应用的部署平台没有限制，支持Kubernetes应用与非Kubernetes应用互相发现，下图是使用Nacos后Kubernetes与非Kubernetes应用互相发现的示意图。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/299576/1734437045287-8fd1ca76-7a1c-463a-b7fa-3c595970c67b.png)

# 五、Nacos服务发现与Kubernetes服务发现特性对比
最后，下面表格中给出了**Nacos**服务发现与**Kubernetes**服务发现特性对比，方便大家选出更适合自己业务的微服务注册配置中心。

| <font style="color:black;"> 特性</font> | <font style="color:black;">Nacos</font> | <font style="color:black;"> Kubernetes</font> |
| :--- | :--- | :--- |
| <font style="color:black;">多K8s或非K8s服务互相发现</font> | + <font style="color:black;">支持</font> | + <font style="color:black;">不支持</font> |
| <font style="color:black;">控制面与运行时耦合</font> | + <font style="color:black;">否</font> | + <font style="color:black;">是</font> |
| <font style="color:black;">注册方式</font> | + <font style="color:black;">SDK</font><br/>+ <font style="color:black;">HTTP API</font><br/>+ <font style="color:black;">控制台</font> | + <font style="color:black;">HTTP API</font> |
| <font style="color:black;">发现方式</font> | + <font style="color:black;">SDK</font><br/>+ <font style="color:black;">HTTP API</font><br/>+ <font style="color:black;">DNS</font> | + <font style="color:black;">环境变量</font><br/>+ <font style="color:black;">DNS </font> |
| <font style="color:black;">健康检查</font> | + <font style="color:black;">心跳上报</font><br/>+ <font style="color:black;">HTTP</font><br/>+ <font style="color:black;">TCP</font> | + <font style="color:black;">HTTP</font><br/>+ <font style="color:black;">TCP</font><br/>+ <font style="color:black;">脚本</font> |
| <font style="color:black;">负载均衡</font><br/> | + <font style="color:black;">基于权重随机</font><br/>+ <font style="color:black;">支持自定义负载均衡插件</font> | + <font style="color:black;">依赖IPVS负载均衡能力，负载均衡方式更多</font> |
| <font style="color:black;">性能与可伸缩性</font> | + <font style="color:black;">自研distro一致性协议，水平扩展能力强</font><br/>+ <font style="color:black;">IP直连调用，性能高</font> | + <font style="color:black;">服务调用经过DNS解析、iptables和ipvs转发，有一定性能损耗</font> |
| <font style="color:black;">可维护性</font> | + <font style="color:black;">架构原理简单，运维及故障恢复成本低</font> | + <font style="color:black;">架构原理复杂，运维及故障恢复成本高</font> |
| <font style="color:black;">服务治理</font> | + <font style="color:black;">控制台功能丰富</font><br/><font style="color:#585A5A;">支持服务增删查改等功能；</font><br/><font style="color:#585A5A;">支持服务调用关系查询；</font><br/><font style="color:#585A5A;">支持权重及上下线操作；</font> | + <font style="color:black;">控制台功能较弱，主要依靠kubectl命令行</font> |
| <font style="color:black;">生态集成</font> | + <font style="color:black;">支持主流rpc框架（dubbo、SpringCloud等）服务发现</font><br/>+ <font style="color:black;">提供主流开发语言SDK</font> | + <font style="color:black;">mesh生态支持较好</font><br/>+ <font style="color:black;">基于DNS服务发现，多语言支持好</font> |


# 六、总结
Kubernetes体系基于DNS的服务发现为开发者提供了很大的便利，但其高度复杂的架构往往带来更高的稳定性风险。以Nacos为代表的独立服务发现系统架构简单，在Kubernetes中选择独立服务发现系统可以帮助增强业务可靠性、可伸缩性、性能及可维护性， 对于规模大、增长快、稳定性要求高的业务来说是一个较理想的服务发现方案。希望大家都能找到适合自己业务的服务发现系统。


