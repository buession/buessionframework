---
title: "如何实现 AI Agent 自主发现和使用 MCP 服务——Nacos MCP Router 部署最佳实践"
description: "如何实现 AI Agent 自主发现和使用 MCP 服务——Nacos MCP Router 部署最佳实践"
date: "2025-08-11"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

<font style="color:rgba(23, 26, 29, 0.94);">作者：正己</font>

## **背景**
随着人工智能的迅猛发展，AI Agent 正日益深入人们的生活与工作场景。特别是自去年11月 MCP 协议发布以来，AI Agent 对外部数据和工具的调用能力得到了显著提升，展现出更强的自主性与实用性。然而，在享受 MCP 工具带来便利的同时，用户也面临一系列现实挑战：

**服务选择与配置复杂**：目前市面上存在成千上万的 MCP 服务器，用户需根据具体任务自行筛选合适的服务器，并手动配置至 AI Agent。这一过程不仅繁琐，还可能显著降低使用效率，影响整体体验。

**Token 消耗过高**：若为 AI Agent 配置过多 MCP 服务器，会导致与大模型交互时需将所有工具的完整描述信息一并发送，从而引发 token 消耗激增的问题，增加推理成本并降低响应速度。

**安全与信任隐患**：对于开源社区提供的、需本地部署的 MCP 服务器，用户常面临安全性质疑：该服务器是否含有漏洞？是否存在数据泄露风险？缺乏权威的安全评估机制，使得用户在使用时难有充分信任保障。

这些问题在一定程度上制约了 MCP 生态的普及与 AI Agent 的广泛应用，亟需系统性的解决方案来提升可用性、效率与安全性。

## **Nacos社区的解决方案：Nacos MCP Router + Nacos MCP Registry**
<font style="color:rgba(23, 26, 29, 0.94);">针对当前 AI Agent 在使用 MCP 协议过程中面临的挑战，Nacos 社区推出了一款重磅开源产品 —— Nacos MCP Router（以下简称 Router）。Router 是一个遵循 MCP 协议的标准 MCP Server，其核心能力是根据用户任务的语义描述和关键词，智能地从 MCP 注册中心中筛选出最匹配的 MCP Server，并将这些候选工具提供给大模型进行决策。</font>

<font style="color:rgba(23, 26, 29, 0.94);">通过引入 Router，用户不再需要手动为不同任务配置不同的 MCP Server，也无需在海量的 MCP 服务中费力筛选。AI Agent 只需统一接入 Router 这一个 MCP Server，极大简化了集成与管理复杂度。</font>

<font style="color:rgba(23, 26, 29, 0.94);">更重要的是，Router 显著优化了 token 使用效率。初始阶段，AI Agent 仅需向大模型传递 Router 自身的轻量级工具描述，避免了全量工具信息的冗余传输。在实际执行过程中，Router 会动态匹配并仅返回与当前任务相关的 MCP 工具描述，从而大幅减少上下文长度，有效缓解因工具描述过多导致的 token 消耗问题，降低推理成本，提升响应速度。</font>

<font style="color:rgba(23, 26, 29, 0.94);">此外，MCP Server 的安全性问题也日益凸显。目前大多数开源 MCP Server 依赖本地部署，并通过标准输入输出（stdio）协议暴露功能，存在潜在的数据泄露、进程污染等安全风险。为此，Router 提供了关键的代理与协议转换能力：支持将基于 stdio 的 MCP Server 一键转换为更安全、更可控的 SSE 或 streamable HTTP 协议，并结合 Docker 容器化部署，实现服务的隔离运行。通过这一机制，用户可将原本不安全的 stdio 服务部署在独立的容器环境中，有效阻断敏感数据外泄路径，提升整体系统的安全性和可维护性。</font>

## **Nacos MCP Router架构及原理**
<font style="color:rgba(23, 26, 29, 0.94);">Router作为Nacos MCP解决方案的一部分，其整体架构图如下图所示：</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889601472-838293b9-36f5-4fe3-93d1-ad969a3b968e.png)

1. <font style="color:rgba(23, 26, 29, 0.94);">Router作为标准MCP Server，通过MCP协议于上层MCP Client通信，支持Local模式（stdio协议）和Remote模式（SSE或streamable HTTP协议）</font>
2. <font style="color:rgba(23, 26, 29, 0.94);">Router主要有两种工作模式：智能路由模式和代理模式。</font>
    1. <font style="color:rgba(23, 26, 29, 0.94);">智能路由是Router的全功能模式，在此模式下，Router提供MCP Server自动发现、智能筛选、工具代理、协议转换、安全鉴权、工具及描述动态调试等功能。</font>
    2. <font style="color:rgba(23, 26, 29, 0.94);">代理模式是Router的子功能合集，主要提供协议转换、工具代理、安全鉴权、工具及描述动态调试等功能</font>
3. <font style="color:rgba(23, 26, 29, 0.94);">Router以Nacos MCP Registry为数据源，从中获取MCP Server列表。用户在Nacos上配置MCP Server或依赖SDK自动注册MCP后，可以通过Router消费MCP Server。</font>

### **工作原理之智能路由模式**
<font style="color:rgba(23, 26, 29, 0.94);">智能路由模式下，Router是一个标准MCP Server，提供3个MCP工具：</font>

+ <font style="color:rgba(23, 26, 29, 0.94);">search_mcp_server：根据任务描述及关键字智能筛选MCP Server；</font>
+ <font style="color:rgba(23, 26, 29, 0.94);">add_mcp_server：初始化指定的MCP Server；如果是stdio协议，执行安装及初始化逻辑；如果是 SSE或streamable HTTP协议，执行建连及初始化逻辑；</font>
+ <font style="color:rgba(23, 26, 29, 0.94);">use_mcp_tool：使用目标MCP Server的某个工具，Router会工具代理请求至目标MCP Server。</font>

<font style="color:rgba(23, 26, 29, 0.94);">架构示意图如下：</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889601541-98b878f4-3917-4da5-be9a-6351b1170463.png)

<font style="color:rgba(23, 26, 29, 0.94);">智能路由模式的工作流程如下：</font>

1. <font style="color:rgba(23, 26, 29, 0.94);">Router启动，从Nacos MCP Registry获取MCP Server列表，并把相关描述存入向量数据库中；</font>
2. <font style="color:rgba(23, 26, 29, 0.94);">Agent初始化和大模型建立会话时，告诉大模型Router的工具列表（前文3个）；</font>
3. <font style="color:rgba(23, 26, 29, 0.94);">用户通过Agent发起任务，大模型根据用户任务描述，选择使用search_mcp_server，构造参数发起工具调用，查找可用的MCP Server；</font>
4. <font style="color:rgba(23, 26, 29, 0.94);">Router根据工具调用参数，查询可用的MCP Server列表，返回相关对最高的5个；</font>
5. <font style="color:rgba(23, 26, 29, 0.94);">大模型选择合适的MCP Server调用add_mcp_server工具，初始化目标MCP Server；</font>
6. <font style="color:rgba(23, 26, 29, 0.94);">Router初始化目标MCP Server，并返回目标MCP Server的工具列表；</font>
7. <font style="color:rgba(23, 26, 29, 0.94);">大模型选择目标MCP Server工具，构造参数，调用Router的use_mcp_tool工具，发起工具调用；</font>
8. <font style="color:rgba(23, 26, 29, 0.94);">Router代理MCP 工具请求至目标MCP Server，并将结果返回给Agent；</font>
9. <font style="color:rgba(23, 26, 29, 0.94);">大模型拿到查询数据，整理最终结果。</font>

### **工作原理之代理模式**
<font style="color:rgba(23, 26, 29, 0.94);">代理模式相对简单一些，代理模式产生的初衷是把stdio协议的MCP Server一键转为SSE或streamableHTTP协议的MCP Server，同时也能通过容器技术为用户提供隔离的MCP运行环境。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889601810-137b0d9e-66cc-4fc2-ad95-ac8b167b72ac.png)

## **Nacos MCP Router部署实践**
### **总体原则**
![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889601561-779efedd-17be-4bfb-bee4-e44527fb9082.png)

+ Nacos、Router均为容器化部署，隔离计算资源
+ Nacos、Router多副本部署，提升稳定性
+ Router采用SLB提供负载均衡能力并对外暴露服务
+ 推荐采用streamableHTTP协议，提供无状态MCP Server

### **架构拓扑**
![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889602152-bc8e5286-4ca5-4e72-9b67-5c19796ca490.png)

为减少复杂性，本次部署基于阿里云容器平台ACS，因为ACS的Serverless形态交付模式帮用户免去了K8s底层资源管理的复杂性。

### **创建Nacos及Nacos MCP Router集群**
<font style="color:rgba(23, 26, 29, 0.94);">目前Nacos MCP Router已经支持helm一键部署，包括Router依赖的Nacos集群，并且能自动创建SLB对外暴露服务，首先要确保已正确安装并配置helm和kubectl。 方便起见我们在阿里云ACS上部署Nacos及Router，部署脚本如下：</font>

```plain
git clone https://github.com/nacos-group/nacos-mcp-router.git
cd nacos-mcp-router/helm
bash randomize_values.sh
helm install nacos-mcp-router . -n nacos-mcp --create-namespace
```

<font style="color:rgba(23, 26, 29, 0.94);">命令执行成功后，等待集群初始化完成。部署完成后如下图所示，包括3节点Nacos、Mysql及Router。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889608773-e0862601-8d85-444b-94a9-25a8a5ae2726.png)

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889608666-be494cac-c694-48cd-948b-8f3829d94d26.png)

### **注册MCP Server到Nacos**
1. <font style="color:rgba(23, 26, 29, 0.94);">登陆Nacos控制台：集群部署完成后，我们需要在Nacos中注册相关的MCP Server。在ACS控制台上找到Nacos的外网SLB，即可访问Nacos控制台，注册MCP Server。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889608669-5d5d444c-e21c-4154-a0e0-cb2d03f4faa3.png)

1. <font style="color:rgba(23, 26, 29, 0.94);">初始化控制台密码：首次登陆Nacos控制台需要初始化密码，</font>**这里要把密码初始化为values.yaml文件里的NACOS_PASSWORD的值**

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889609897-1e91f732-582d-4ada-ba89-11125b69d7ab.png)

1. <font style="color:rgba(23, 26, 29, 0.94);">注册Local MCP Server</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889620612-8e216bf5-d3d6-416a-afab-a2a4f971524c.png)

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889620969-1004bcc1-acbe-4a3f-8d17-9e922f11d1fb.png)

### **通过Router的代理模式把 stdio协议MCP Server转为streamableHTTP协议**
<font style="color:rgba(23, 26, 29, 0.94);">前面讲过Router支持MCP协议转换，本节讲述以代理模式部署Router，并代理stdio协议MCP Server，对外暴露streamableHTTP协议，以 MCP Server fetch为例：</font>

+ <font style="color:rgba(23, 26, 29, 0.94);">部署fetch</font>

```plain
apiVersion: apps/v1
kind: Deployment
metadata:
  name: fetch-mcp
spec:
  replicas: 1
  selector:
    matchLabels:
      app: fetch-mcp
  template:
    metadata:
      labels:
        app: fetch-mcp
    spec:
      containers:
        - name: fetch-mcp
          image: "nacos/nacos-mcp-router:latest"
          env:
            - name: TRANSPORT_TYPE
              value: streamable_http
            - name: MODE
              value: "proxy"
            - name: PROXIED_MCP_NAME
              value: "fetch"
            - name: PROXIED_MCP_SERVER_CONFIG
              value: '{"mcpServers":{"fetch":{"command":"uvx","args":["mcp-server-fetch"]}}}'
            - name: AUTO_REGISTER_TOOLS
              value: "false"

---
apiVersion: v1
kind: Service
metadata:
  name: fetch-mcp
spec:
  type: ClusterIP
  selector:
    app: fetch-mcp
  ports:
    - name: http
      port: 8000
      targetPort: 8000
```

<font style="color:rgba(23, 26, 29, 0.94);">使用kubectl部署：</font>

```plain
kubectl apply -f nacos-mcp-router-proxy-deployment.yaml -n nacos-mcp
```

+ <font style="color:rgba(23, 26, 29, 0.94);">部署完成后可以看到fetch-mcp的接入信息：</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889625843-53d7adde-3465-4415-93d4-2d37e3f90c07.png)

+ <font style="color:rgba(23, 26, 29, 0.94);">在Nacos控制台中把fetch注册为streamable协议的MCP Server即可。</font>

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889626127-5cc11059-201a-46fb-ad50-bb5448fe8743.png)

### **通过cherry studio使用Router**
+ <font style="color:rgba(23, 26, 29, 0.94);">配置cherry studio MCP服务器，选择streamable协议，地址为Router的公网地址</font>![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889627535-042e6918-6616-4bfb-9d0b-2a459c836de0.png)![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889629056-219b03cd-5b5e-4b75-9cfe-3ec450e6a266.png)
+ 使用Router自动发现MCP![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1754889631360-5f4d66f5-4d5a-490d-8495-1746c4be94f5.png)

## **未来规划**
<font style="color:rgba(23, 26, 29, 0.94);">Nacos MCP Router未来要做的事情还有很多，近期重点集中在安全性、稳定性、智能化三个方面：</font>

+ <font style="color:rgba(23, 26, 29, 0.94);">安全性：MCP统一鉴权管理</font>
+ <font style="color:rgba(23, 26, 29, 0.94);">稳定性：工具调用限流、可观测</font>
+ <font style="color:rgba(23, 26, 29, 0.94);">智能化：虚拟MCP构建、MCP工具筛选、检索准确性优化</font>

[**欢迎大家一起参与共建**](https://github.com/nacos-group/nacos-mcp-router)


