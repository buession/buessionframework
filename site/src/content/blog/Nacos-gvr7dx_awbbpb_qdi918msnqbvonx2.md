---
title: "Nacos MCP Router 新版发布：支持 Docker 远程部署，MCP的多协议stido、SSE、Streamable互相转换"
description: "Nacos MCP Router 新版发布：支持 Docker 远程部署，MCP的多协议stido、SSE、Streamable互相转换"
date: "2025-06-06"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

## Nacos MCP Router简介
Nacos MCP Router是一个基于MCP官方SDK开发的标准MCP Server，为MCP Client提供MCP Server的智能搜索、安装、代理等功能， 极大地简化了MCP服务的使用流程。同时，Nacos MCP Router跟Nacos MCP Registry结合，可以实现MCP Server治理，如MCP Server及工具可见性、版本管理等。

今天，我们很高兴地宣布Nacos MCP Router推出全新版本，带来了多项重要更新，包括对SSE和StreamableHTTP协议的全面支持、Docker容器化部署方案以及革命性的MCP Server协议一键转换功能。这些新特性将为开发者提供更加灵活、高效的MCP服务使用体验，推动MCP生态系统的繁荣发展。

## 应用场景
新版Nacos MCP Router的特性组合为多种应用场景提供了强大支持：

1. **异构系统集成**：通过协议转换功能，可以轻松实现不同协议MCP服务之间的互通，打破技术壁垒，促进系统集成。
2. **微服务架构**：作为服务发现和配置中心的补充，Nacos MCP Router可以帮助微服务架构中的各个服务高效地发现和使用MCP服务。
3. **AI助手能力扩展**：通过与Cline、Cursor等AI助手的集成，可以为这些平台提供MCP搜索、安装及代理等工具和服务，扩展其能力边界。
4. **云原生应用**：Docker部署支持使Nacos MCP Router成为真正的云原生应用，可以轻松部署在Kubernetes等容器编排平台上。

## 多协议支持：支持stdio、SSE、StreamableHTTP协议
在现代分布式系统中，不同的通信协议适用于不同的应用场景。新版Nacos MCP Router深刻理解这一点，因此全面扩展了协议支持范围，除了传统的stdio协议外，现在还支持SSE（Server-Sent Events）和StreamableHTTP协议。

### 支持SSE协议
SSE协议是MCP官方提供的第一种remote MCP server通信协议。sse协议支持事件推送，提升了实时性和交互体验。Nacos MCP Router目前支持暴露SSE协议，以docker部署为例：

```plain
docker run -i --rm --network host -e NACOS_ADDR=$NACOS_ADDR -e NACOS_USERNAME=$NACOS_USERNAME -e NACOS_PASSWORD=$NACOS_PASSWORD -e TRANSPORT_TYPE=sse nacos-mcp-router:latest
```

注意：需设置环境变量TRANSPORT_TYPE=sse

Nacos MCP Router启动完成后，完成Cline、Cursor、Claude等MCP设置即可使用，示例如下：

```plain
{
  "mcpServers": {
    "nacos-mcp-router": {
      "url":"http://$nacos_mcp_router_addr/sse"
    }
  }
}
```



### 支持streamableHTTP协议
由于sse协议需要维持链接状态，存在一些缺陷：

+ 连接不可恢复（断线需重连），SSE 连接一旦中断（如网络波动），客户端无法恢复之前的会话状态，必须重新建立连接并初始化上下文，导致长任务（如文件处理、多轮对话）被迫重启
+ 服务器需维持高可用长连接，每个客户端均需独立的 SSE 长连接，高并发场景下服务器资源（如 TCP 连接数、内存）消耗剧增，且水平扩展困难。
+ 仅支持**服务器→客户端**的单向推送，客户端消息仍需通过独立 HTTP 请求发送。这种割裂设计增加了实现复杂度，且无法支持双向按需通信<font style="background-color:rgb(229, 229, 229);">18</font>。
+ 基础设施兼容性差，长连接易被企业防火墙、CDN 或负载均衡器强制中断，且难以部署在 Serverless 架构等不支持长连接的云平台

为解决上述问题，MCP官方推出了StreamableHTTP协议，StreamableHTTP协议在保留 SSE 流式能力的同时，也具备灵活性、轻量化、兼容性等特性。

设置环境变量TRANSPORT_TYPE=streamable_http, Nacos MCP Server开启StreamableHTTP协议。以docker部署为例，命令如下：

```plain
docker run -i --rm --network host -e NACOS_ADDR=$NACOS_ADDR -e NACOS_USERNAME=$NACOS_USERNAME -e NACOS_PASSWORD=$NACOS_PASSWORD -e TRANSPORT_TYPE=streamable_http nacos-mcp-router:latest
```

Cline、Cursor、Claude等MCP设置示例如下：

```plain
{
  "mcpServers": {
    "nacos-mcp-router": {
      "url":"http://$nacos_mcp_router_addr/mcp"
    }
  }
}
```



Nacos MCP Router在SSE、StreamableHTTP协议下工作原理如下图所示：



![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/169256735/1749174547596-91019757-110f-46dd-8e92-ce91327c8901.png)

_图1：Nacos MCP Router StreamableHTTP、sse协议架构_ 

## 一键转换stdio、sse为streamableHTTP协议
当前，AI生态已经有了大量的MCP Server，其中大部分暴露的是stdio协议或sse协议。MCP Server本地部署需占用本地资源，因此MCP Server有远程部署的需求。Nacos MCP Router新版本引入了协议转换功能，支持实sse、stdio协议MCP Server到streamableHTTP协议的一键转换，其工作原理示意图如下：

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/7601/1748952080966-c87a1154-3cec-4c75-ac50-ae041d79dfdc.png)

_图2：Nacos MCP Router 协议转换架构_ 

### stdio转为streamableHTTP协议
如前所述，MCP Server本地部署会占用本地计算资源，尤其是部署多个MCP Server时，资源占用会大幅增加。为解决这个问题，Nacos MCP Router提供了proxy模式。在proxy模式下，用户只需简单配置几个环境变量，无需修改一行代码，即可把stdio协议的MCP Server一键转换为streamableHTTP协议MCP Server。

本节以stdio协议转为streamableHTTP为例，简单演示使用过程，sse转换为streamableHTTP与此类似。启用proxy模式需要设置`MODE=proxy`环境变量，指定`PROXIED_MCP_NAME`后，Nacos MCP Router会自动从Nacos获取目标MCP服务器的配置，并建立代理连接，将不同协议的请求无缝转换。整个过程对原始MCP服务器完全透明，无需修改任何代码。

启动示例如下：

```plain
docker run -i --rm --network host -e NACOS_ADDR=$NACOS_ADDR -e NACOS_USERNAME=$NACOS_USERNAME -e NACOS_PASSWORD=$NACOS_PASSWORD -e TRANSPORT_TYPE=streamable_http -e MODE=proxy -e PROXIED_MCP_NAME=$PROXIED_MCP_NAME  nacos-mcp-router:latest
```

下面以高德地图为例，展示如何把stdio协议MCP Server转为streamableHTTP协议MCP Server。

1. 启动Nacos Server最新版，简单起见，以单机模式启动

```plain
git clone 
sh $NACOS_DIR/bin/startup.sh -m standalone
```

2. 在Nacos注册高德MCP

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/7601/1748951010946-c3fcb93b-65b3-4c8f-b52d-f98c616a6375.png)

3. 以proxy模式启动Nacos MCP Router

```plain
docker run -i --rm --network host -e NACOS_ADDR=127.0.0.1:8848 -e NACOS_USERNAME=nacos -e NACOS_PASSWORD=nacos -e TRANSPORT_TYPE=streamable_http -e MODE=proxy -e PROXIED_MCP_NAME=amap-mcp-server  nacos/nacos-mcp-router:latest
```

4. CherryStudio使用streamableHTTP
    1. 设置MCP，指定为streamableHTTP，保存后就能看到工具列表![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/7601/1748951761141-2215cb41-755a-44cf-8fde-3dc02c97e007.png)![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/7601/1748951888475-12f91633-0577-49f6-b008-d147e3ac4e36.png)
    2. 使用MCP![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/7601/1748952032035-fbbc3a4f-b0d4-437f-9387-d9c482f7a5a2.png)

## 支持Docker部署：更安全、无需关心依赖
容器化部署已成为现代应用交付的标准方式。新版Nacos MCP Router支持Docker镜像部署，可与K8s等部署平台结合，进一步简化运维复杂度。Docker镜像中自带了MCP常见的依赖如python、node等，用户无需关注依赖问题。同时，MCP Server运行在容器内提升了系统的安全性。通过官方Docker镜像，开发者只需一行命令即可启动MCP服务：

```bash
docker run -i --rm --network host -e NACOS_ADDR=$NACOS_ADDR -e NACOS_USERNAME=$NACOS_USERNAME -e NACOS_PASSWORD=$NACOS_PASSWORD -e TRANSPORT_TYPE=$TRANSPORT_TYPE nacos-mcp-router:latest

```

对于需要协议转换的场景，同样可以通过Docker轻松实现：

```bash
docker run -i --rm --network host -e NACOS_ADDR=$NACOS_ADDR -e NACOS_USERNAME=$NACOS_USERNAME -e NACOS_PASSWORD=$NACOS_PASSWORD -e TRANSPORT_TYPE=streamable_http -e MODE=proxy -e PROXIED_MCP_NAME=$PROXIED_MCP_NAME nacos-mcp-router:latest

```

## 结语：拥抱变化，共建生态
Nacos MCP Router新版本的发布，标志着MCP服务生态向更加开放、灵活和高效的方向迈进了一大步。多协议支持、协议转换、Docker部署等特性，不仅提升了开发者的使用体验，也为MCP服务的广泛应用和生态繁荣奠定了基础。

我们相信，随着这些新特性的应用，将会有更多创新的MCP服务涌现，为云原生应用开发带来更多可能性。我们也期待社区的反馈和贡献，共同推动Nacos MCP Router和整个MCP生态的发展。未来，我们希望Nacos MCP Router能够成为MCP Server的分发与调度平台，为AI Agent提供强大的智能化MCP调度能力。


