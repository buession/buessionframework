---
title: "函数计算 ✖️ MSE Nacos : 轻松托管你的MCP Sever"
description: "函数计算 ✖️ MSE Nacos : 轻松托管你的MCP Sever"
date: "2025-10-09"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

作者：濯光

## 一、背景与挑战：MCP Server 落地中的典型问题
随着 AI Agent 生态的发展，Model Context Protocol（MCP）作为连接 Agent 与外部工具的标准协议，正在被越来越多的技术团队采用。但在实际落地过程中，MCP Server 的部署、运维与统一管理成为关键挑战。

+ **部署运维成本高，运行时选择困难**：在生产实践中，MCP Server 往往需要单独部署，许多团队将 MCP Server 部署在自建服务器或容器环境中，需要手动管理进程、配置网络、处理扩缩容。一旦调用量突增，服务容易崩溃；流量低谷时资源又闲置浪费。
+ **MCP 调试迭代必须重启**：MCP 描述对于工具效果起着重要的作用，当需求变化或者服务迭代而需要修改某个 Tool 的描述、参数说明或提示词时，传统方式要求重新打包、发布、重启服务。不仅效率低，还可能影响正在运行的 AI 请求。
+ **服务分散，难以统一管理**：多个 MCP Server 分散在不同机器或环境中，缺乏统一的服务注册与发现机制。AI 网关无法动态感知可用服务，运维人员也难以快速定位问题。
+ **缺乏动态控制能力**：某个 Tool 出现异常，不能即时关闭；新功能上线无法灰度发布；调试阶段也无法临时禁用部分能力——缺乏灵活的运行时治理手段。

这些问题导致 MCP 服务体系虽然功能完整，但**可维护性差、迭代慢、稳定性弱**。

## 二、解决方案：函数计算 + MSE Nacos 企业版
为了解决上述问题，提升 MCP Server 的可维护性与可扩展性，阿里云函数计算（Function Compute）与微服务引擎 MSE Nacos 已完成能力打通，支持将 MCP Server 部署于函数计算环境的同时，自动注册至 MSE Nacos 的 MCP Registry。

函数计算作为事件驱动的 Serverless 计算平台，在 MCP Server 的部署场景中提供以下核心能力：

+ **免运维部署**：用户仅需上传代码包，系统自动完成实例调度、资源分配与服务启停。
+ **弹性伸缩**：根据请求量自动扩缩容，适应 AI 调用流量波动大、突发性强的特点。
+ **协议兼容**：支持 SSE 与 STIDO 协议的 MCP Server 运行，并可通过标准接口对外暴露服务。
+ **低成本运行**：按实际执行时间计费，无调用时不产生费用，适用于低频高价值的工具类服务。

而 MSE Nacos 致力于打造企业级 AI 智能体平台，提供对 MCP Server 的集中化管控能力，并支持社区官方 MCP Registry 协议：

+ **自动服务注册**：MCP Server 在函数计算中启动后，自动注册至指定的 MSE Nacos 实例，无需手动配置。
+ **动态元信息管理**：支持对工具描述、参数定义等元信息进行运行时更新，变更实时生效，无需重启服务。
+ **Tools 动态开关**：可在控制台对特定 Tool 进行启用或禁用操作，实现快速故障隔离与灰度控制。
+ **统一服务发现**：注册的服务信息同步至 Nacos 配置中心与服务发现模块，便于 AI 网关或 Agent 客户端进行动态寻址与调用。
+ **全链路集成**：与阿里云 AI 网关， Nacos MCP Router 等组件无缝对接，构建完整的 AI 服务治理体系。

![](https://img.alicdn.com/imgextra/i2/O1CN01Ir5oue1xtVv1TMXE4_!!6000000006501-2-tps-3332-2284.png)

通过函数计算，开发者可将关注点聚焦于业务逻辑开发，而非基础设施管理。而基于 MSE Nacos，开发者可以实现 MCP Server 的从“散点式部署”走向“平台化治理”，显著提升系统的可观测性与可控性。两者相结合，用户无需修改业务代码，即可完成MCP Server 的注册与动态治理，对 MCP Server 进行全生命周期管理。

接下来本文将通过一个具体案例，演示如何基于 MCP Python SDK 开发一个标准的 MCP Server，并将其部署至函数计算。在不修改任何业务代码的前提下，通过控制台简单配置，即可实现该服务自动注册至 MSE Nacos 企业版，并支持后续的动态更新与统一管理。

## 三、操作教程：从开发到上线的完整流程
### 前提条件
+ [创建MSE Nacos 企业版3.x实例](https://help.aliyun.com/zh/mse/user-guide/create-an-instance)
+ [开通函数计算服务](https://help.aliyun.com/zh/functioncompute/fc-3-0/quickly-create-a-function#b041c69538u79)

### 代码开发
函数计算目前支持 STIDO 和 SSE 协议的 MCP Server的部署， 可以通过 MCP 社区提供的 SDK 或者 Spring Ai Alibaba 等框架进行 MCP Server 开发。 本文中，我们以 MCP Python SDK 进行标准 MCP Server 开发为例：

#### 依赖安装
首先我们需要进行 MCP 相关的依赖安装。在工程目录下执行

```go
pip install "mcp[cli]"
```

#### 代码开发
在工程目录下创建文件 main.py, 使用 FastMCP 快速开发一个 标准 SSE 协议的 MCP Server:

```go
"""
FastMCP Echo Server
"""

from mcp.server.fastmcp import FastMCP

# Create server
mcp = FastMCP("Echo Server",port=8080,host="0.0.0.0")


@mcp.tool()
def echo_tool(text: str) -> str:
    """Echo the input text"""
    return text


@mcp.resource("echo://static")
def echo_resource() -> str:
    return "Echo!"


@mcp.resource("echo://{text}")
def echo_template(text: str) -> str:
    """Echo the input text"""
    return f"Echo: {text}"


@mcp.prompt("echo")
def echo_prompt(text: str) -> str:
    return text

if __name__ == "__main__":
    mcp.run("sse")

```

#### 代码测试
在工程目录下执行命令，启动 MCP Server：

```python
python main.py
```

出现如下输出，代表 MCP Server 成功启动:

```python
INFO:     Started server process [24172]
INFO:     Waiting for application startup.
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:8080 (Press CTRL+C to quit)
```

通过以上的步骤，我们完成了一个标准 MCP Server 开发。接下来我们要做的就是将 MCP Server 部署至函数计算，就可以基于函数计算实现 MCP Server 的自动弹性和扩容，聚焦于业务逻辑；同时在不修改任何代码的情况下，自动将 MCP Server 注册至 MSE Nacos MCP Registry , 实现对于 MCP Server 的统一管理和快速迭代。

#### 代码打包
在工程目录下执行命令, 对代码和相关的依赖进行打包，方便之后在函数计算中部署：

```python
zip -r echo.zip .
```



### 在函数计算中部署 MCP Server
#### **登录**函数计算FuntionAI控制台。
#### 选择已有项目或者创建空白项目
创建空白项目：单击**项目**，并点击加号创建空白项目，选择 MCP 场景，输入**项目名称**，然后单击**确定**。

![](https://img.alicdn.com/imgextra/i2/O1CN018Hmaiw1vQXdJ5pE0J_!!6000000006167-2-tps-4074-2004.png)

完成项目创建后，就可以开始基础的配置了。

#### 基础配置
![](https://img.alicdn.com/imgextra/i3/O1CN01x0Qfgy23c0TSnRvAF_!!6000000007275-2-tps-3250-616.png)

+ **地域**：选择与 MSE Nacos 实例相同地域。
+ **函数名称**：使用自动生成的即可。
+ **函数描述**：对 MCP Server 的功能和使用方式进行简单说明

#### MCP 服务配置
![](https://img.alicdn.com/imgextra/i3/O1CN01EQvny51c6rvB0apW0_!!6000000003552-2-tps-3286-1244.png)

+ **传输类型**：根据实际场景，选择 **SSE 协议或者 STIDO协议**， 函数计算支持将 STDIO 协议转化为 SSE 协议。在本文案例中，我们选择 SSE 协议
+ **SSE 路径**：使用默认值`<font style="background-color:rgba(0, 0, 0, 0.04);">/sse</font>`。
+ **监听端口**：进程实际监听的端口，在本文中我们配置为8080。
+ **开启鉴权**：函数计算支持为 MCP Server 配置 token 鉴权， 可以根据实际需要进行开启。我们选择关闭鉴权。
+ **运行环境**：根据代码实际需要的运行环境进行选择，我们采用 MCP Python SDK 进行的 MCP Server 开发，因此选择 python 3.10 环境。
+ **启动命令**：配置启动命令，为：`<font style="background-color:rgba(0, 0, 0, 0.04);">python3 main.py</font>`。
+ **选择仓库**：函数计算支持多种代码上传方式，我们采用**代码包 方式进行上传**。
+ **代码包**：选择我们在代码开发章节中打包的`echo.zip`文件进行上传。

#### 网络配置
在网络配置一栏，打开**允许访问VPC**开关，选择 MSE Nacos 实例所在的**专有网络**、**交换机**和**安全组**，完成网络配置。

![](https://img.alicdn.com/imgextra/i1/O1CN019CmXFH1O9FM5hOYQ3_!!6000000001662-2-tps-3236-1390.png)

#### 服务注册
在服务注册一栏，选择注册到 MSE Nacos 实例，选择需要注册到的企业版MSE Nacos 实例。如果MSE 实例未开启阿里云访问控制，则需要填写开源控制台用户名和密码。用户名为Nacos，密码为 Nacos 开源控制台密码，如果遗忘或者为新创建实例，可以在 MSE Nacos 实例基础信息页面**开源控制台密码**处进行重置。

![](https://img.alicdn.com/imgextra/i1/O1CN01rURyxi1GBWOk5qCWv_!!6000000000584-2-tps-3278-698.png)

#### 部署 MCP Server
配置完成后，单击确认部署按钮，完成 MCP Server 部署

![](https://img.alicdn.com/imgextra/i2/O1CN01Wj565T26dKStJTRNR_!!6000000007684-2-tps-3908-1874.png)

#### 安装依赖
完成部署后，由于我们采用的是代码包的方式上传的代码，代码包中缺乏本地的依赖，因此第一次启动是不成功的，我们需要在WebIDE中重新把依赖安装一遍：

点击 WebIDE 标签：  
![](https://img.alicdn.com/imgextra/i4/O1CN01yiQaX91oGjqUDoX5m_!!6000000005198-2-tps-3988-1932.png)

在控制台中执行依赖安装命令：

```go
pip install -t . "mcp[cli]"
```



![](https://img.alicdn.com/imgextra/i4/O1CN01Yo4lhL1efnM2PLJVd_!!6000000003899-2-tps-4034-1902.png)

依赖安装完成后点击保存按钮，并重新部署

![](https://img.alicdn.com/imgextra/i1/O1CN01mz94dj29w3VRC341U_!!6000000008131-2-tps-4086-1628.png)



完成以上步骤后， 我们成功在函数计算中部署了 MCP Server 。

#### 调试 MCP Server
部署完成后，可以在函数计算控制台上对 MCP Server 进行简单的调试。单击**服务测试**。并在**连接信息**页签单击**测试连接**。

![](https://img.alicdn.com/imgextra/i4/O1CN01T3vif71WH31sIdQqT_!!6000000002762-2-tps-3372-1718.png)

我们也可通过执行curl命令访问函数的公网访问地址，调试并预热刚才部署的MCP Server。返回类似以下内容，则MCP服务函数部署成功。

```python
id:3d3ba0d9-4d10-4a4e-ae08-a4a92f95d88a
event:endpoint
data:/mcp/message?sessionId=3d3ba0d9-4d10-4a4e-ae08-a4a92f95d88a
```

### 在 MSE Nacos 中查看和管理注册的 MCP Server
在函数计算中完成 MCP Server 的部署并配置相关的服务注册选项后，用户即可在 MSE Nacos 实例中查看和管理对应的 MCP Server 。

#### 登录MSE注册配置中心控制台
单击目标Nacos实例，在左侧导航栏选择**MCP Registry**，检查对应的MCP服务是否注册成功。

![](https://img.alicdn.com/imgextra/i1/O1CN01JKWlxG28TN5LCqIKh_!!6000000007933-2-tps-3978-1214.png)

#### 查看服务详情
单击目标MCP服务，进入MCP服务详情界面，查看**服务详情**。

![](https://img.alicdn.com/imgextra/i1/O1CN016e03IT1qss3JpeMah_!!6000000005552-2-tps-3994-872.png)

![](https://img.alicdn.com/imgextra/i2/O1CN01maMB1B2A0B4Smnm4x_!!6000000008140-2-tps-4072-1630.png)

可以发现函数计算中 MCP 函数的访问地址成功注册到了对应的服务中。

#### 对 MCP Server 进行管理
成功注册到 Nacos 之后， 我们即可在 MSE Nacos MCP Registry 中对 MCP 进行动态管理而无需重新部署 MCP Server ，包括工具描述的修改，开启与禁用某个工具。

在 MCP Server 详情页点击编辑按钮

![](https://img.alicdn.com/imgextra/i1/O1CN01CDf80L1Ivtjb72ehJ_!!6000000000956-2-tps-4058-1224.png)

修改对应的工具描述

![](https://img.alicdn.com/imgextra/i2/O1CN01V44lSp1zxkokAAXCM_!!6000000006781-2-tps-2936-1984.png)

启动官方 MCP Server 调试工具，并执行 List Tools 命令

```python
npx @modelcontextprotocol/inspector 
```

![](https://img.alicdn.com/imgextra/i2/O1CN01TlS5E01bnAtGVKmpP_!!6000000003509-2-tps-4088-2054.png)

发现对应的工具描述变更成了我们动态调整后内容，而在这个过程中我们并没有重新部署 MCP Server。

此外，也可以将对应的 MCP 服务同步至 AI 网关。也可以通过 Nacos MCP Registry 进行统一的 MCP 服务发现。

通过以上步骤，借助函数计算与 MSE Nacos 的深度集成能力，开发者不仅可以快速部署 MCP Server，还能在不重启服务的前提下，实现工具描述更新、Tool 动态开关等运行时治理操作，大大降低了 MCP Server 的部署和管理成本。

## 四、总结
通过函数计算与 MSE Nacos 的集成，MCP Server 在部署和管理中的多个实际问题得到了有效解决。针对部署运维成本高的问题，函数计算提供了免服务器管理的运行环境，自动完成实例调度和弹性伸缩，减少了人工干预和资源浪费。对于配置变更必须重启的情况，借助 MSE Nacos 的动态注册能力，工具描述、参数定义等元信息可以在运行时更新，修改后立即生效，无需重新发布服务。

服务分散、难以统一查看的问题也得到了改善。MCP Server 在启动后会自动注册到 MSE Nacos，所有服务实例集中可见，便于监控和调用。同时，通过控制台对特定 Tool 进行启用或禁用操作，实现了基本的运行时控制能力，提升了应对异常场景的响应速度。

整个流程无需修改业务代码，只需在部署时完成网络和服务注册配置，即可实现从运行到治理的完整链路接入。对于正在构建 AI 工具体系的团队来说，这是一种低侵入、易落地的技术路径。


