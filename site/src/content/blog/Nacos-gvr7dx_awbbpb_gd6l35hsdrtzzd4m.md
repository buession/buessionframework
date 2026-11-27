---
title: "Dify开发者必看：如何破解MCP集成与Prompt迭代难题？"
description: "Dify开发者必看：如何破解MCP集成与Prompt迭代难题？"
date: "2025-07-01"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

作者：濯光

Dify 是一个面向AI时代的开源大语言模型（LLM）应用开发平台，致力于让复杂的人工智能应用构建变得简单高效，目前已在全球范围内形成显著影响力，其 GitHub 仓库 Star 数截至 2025 年 6 月已突破 **100,000+**，目前，Dify 已经成为 LLMOps 领域增长最快的开源项目之一。

然而，AI领域正处于蓬勃发展,快速变化的阶段，每天都有新技术，新的应用场景在出现，Dify也有其力所不逮的地方：

+ **MCP集成困难**。最近，MCP (Model Context Protocol，模型上下文协议)的风潮正在刮遍整个领域。MCP是一个开放协议，旨在标准化大型语言模型（LLM）与外部数据源及工具之间的集成。其最大的优点是使LLM应用能够灵活、安全地访问上下文信息和功能，显著降低了AI应用开发的复杂度，加速了AI应用的部署，吸引了无数开发者的目光。但是，截止目前，Dify 平台仍然缺乏原生对于 MCP 协议的支持，虽然有社区的插件将 MCP Server 转化为 Dify 应用的工具调用，但这些插件也存在配置麻烦，使用场景受限，难以快速变更等问题。
+ **Prompt不够敏捷**。Prompt 是生成式 AI 的核心，生产中往往需要根据应用场景不断的进行调整和优化，Dify已经大大降低了应用开发的成本，但是Prompt变化依然要重新发布。
+ **启动配置复杂，运维困难**。Dify 平台拥有强大的功能，但也提升了其应用架构的复杂度，各个组件所用到的配置项数量繁多，难以管理。

Nacos 是阿里巴巴开源的一款注册配置中心产品，被广泛应用于各种云原生应用的构建中。面对AI浪潮带来的技术革命，全新的 Nacos 3.0版本将定位进行了升级，致力于打造**一个易于构建 AI Agent 应用的动态服务发现、配置管理和 AI 智能体管理平台**，为 模型 / MCP Server / Agent 等新型业务智能场景架构提供更高效的运行支撑。

对于Dify面临的这些问题，我们可以在 Nacos 中找到对应的答案：

+ **MCP 动态管理和接入**。Nacos 3.0 支持多种类型的 MCP Server 自动注册和动态发现，和 MCP 服务相关描述，工具，Prompt 等内容的动态管理。并支持快速的将存量的微服务 API 实现 0 代码改动转化成 MCP 服务。同时，Nacos 3.0 支持了标准 MCP Registry API，轻松实现 MCP Registry 私有化部署。
+ **Prompt的运行时动态变更**。Nacos 提供的配置管理能力，天然契合 Prompt 快速调整优化的场景。通过将Prompt 托管至 Nacos，无需重新编辑部署Dify工作流，即可进行
+ **Dify部署环境变量托管**。Nacos 支持将 Dify 部署使用的配置托管至 Nacos, 支持控制台白屏化变更和配置历史版本，提高运维效率。

## 灵活集成，Dify 轻松发现 MCP 服务
目前，Dify 应用通过社区插件对接 MCP 服务主要存在以下两个痛点

+ **接入琐碎繁杂**：每个需要接入 MCP 服务的 Dify应用都要单独配置 MCP Server 服务的连接信息，缺乏一个统一的集中式管理的平台。
+ **难以动态变更**：需要添加新的 MCP Server 或者修改 MCP Server 信息时，都要重新编辑和部署Dify应用，变更成本高。

而在 3.0 版本中，Nacos 在 MCP 生态中迈进了一大步，支持了 MCP Registry 能力，多种类型、不同版本的MCP Server 可以轻松注册至 Nacos 中，消费端只需要连接 Nacos, 就可以获取所有可用的 MCP Server 和 访问方式，免去了手动配置的痛苦。而除了动态发现 MCP Server 之外，Nacos 3.0 MCP Registry 还支持以下 MCP 相关企业级特性:

+ 支持将存量的 HTTP 接口实现 0 代码改动转化成 MCP 服务
+ MCP 服务支持多实例注册分布式部署，大大提高容灾能力
+ 支持动态变更 MCP tools 开关和描述

目前，Nacos 官方 MCP 插件 [Nacos MCP](https://marketplace.dify.ai/plugins/nacos/nacos_mcp?language=zh-Hans&theme=system) 已经登录 Dify 官方市场。Dify 应用通过接入 Nacos MCP 插件，即可实时感知 Nacos 中配置的所有 MCP Server，享受以上 MCP 企业级特性，大大降低了 Dify 应用中 MCP Server 的接入和管理成本。同时，Nacos MCP 插件还能帮助模型按需挑选并路由 MCP 服务，有效降低模型调用的 Token 消耗。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750777870698-ba365c0a-2e80-479e-a4bf-712330fe78bb.png)

接下来，就让我们通过一个简单的实践教程，来实际体验下吧

### 实践: Dify 应用轻松路由 Nacos MCP 服务
假设我们正在开发一个AI导购，在功能刚上线的时候，我们希望AI导购能够调用`goods-mcp-server`的工具来根据用户提供的关键字返回相关的商品列表，后面根据用户的反馈再接入新的 MCP Server 进行能力的增强。

通过接入 Nacos MCP 插件，Dify 应用即可以动态感知新增的 MCP 服务，实现能力的快速迭代。同时，在实际的调用过程中，Nacos MCP 会首先返回所有 MCP 的简要信息供模型进行挑选，模型根据实际任务需要再查询对应 MCP 的 Tools 供模型进行调用，通过这种方式，能够降低模型Token消耗，特别是在 MCP 服务数量和工具数量过多的场景下，优势明显。

#### 注册 MCP 服务 至 Nacos
首先，我们要将所需的 MCP Server 信息注册到Nacos中，目前Nacos MCP Registry 支持多种注册方式：

+ 控制台上手动注册
+ 通过框架自动注册，参考[MCP Server自动注册手册](https://nacos.io/docs/latest/manual/user/ai/mcp-auto-register/?spm=5238cd80.6f0ee98.0.0.3bbf480cbJHznr&source=blog)
+ 存量的微服务API实现0代码改动转化成MCP服务，参考[存量API转换MCP手册](https://nacos.io/docs/latest/manual/user/ai/api-to-mcp/?spm=5238cd80.2ef5001f.0.0.3f613b7c3VlKIO&source=blog)

注册完成后，我们可以在Nacos 控制台上看到对应的MCP Server信息：

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750656874785-e1847119-ede0-40e7-83dc-f41a813e445a.png)

  
![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750656912225-635f2a97-e7af-4b96-815a-d8f3a3cfc66a.png)

#### Dify 应用 Nacos MCP 插件配置
完成 MCP Server 在 Nacos 上的注册之后，为了让 Dify 应用能够访问到Nacos中注册的 MCP Server，我们需要安装并配置 Nacos MCP 插件。目前，Nacos 官方 MCP 插件 [Nacos MCP](https://marketplace.dify.ai/plugins/nacos/nacos_mcp?language=zh-Hans&theme=system) 已经登录 Dify 官方市场。在 Dify 官方插件市场中搜索[ Nacos MCP 插件](https://marketplace.dify.ai/plugins/nacos/nacos_mcp?language=zh-Hans&theme=system)，安装后配置 Nacos Server 的访问地址和访问凭证:

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750657412139-7aa5737a-83a7-4e9c-942c-cf4fa2739360.png)

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750657330873-4d45b08e-6643-4d3d-b8a4-9985fc0721e1.png)

现在，让我们在 Dify 中构建一个简单的AI导购助手。在 Dify 中创建一个 Agent 应用并配置 Nacos MCP 以下的工具：

+ **查询 MCP Server 列表** (`list_mcp_servers`) 
+ **查找特定的 MCP Server 的工具列表** (`list_mcp_server_tools`) 
+ **列出用户预配置的 MCP Server 的工具列表** (`list_mcp_server_tools_by_user`)

模型会根据实际的任务，首先查询可用的 MCP Server 列表，并根据自己的需要，智能挑选依次调用Nacos MCP 的三个工具来完成任务的执行。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750663324548-a4abc87c-49b9-48e6-92f9-69f5df6c37f4.png)

点击部署，让我们在对话中体验下：

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750664673635-965e361f-fde1-4476-8553-45316fe774c0.png)

查看调用历史，大模型通过调用Nacos MCP 插件的三个工具，顺利完成了对于`goods-mcp-server`的调用。

#### 实时发现并路由 MCP 服务
而完成配置之后，在随后的运行时中，Dify可以实时的感知 Nacos 中注册的 MCP Server服务，而无需对 Dify 应用进行重新编辑和部署，并根据任务实现对于 MCP 的路由。

以AI导购为例，在上线之后，我们觉得单纯推荐物品的逻辑有些单薄，我们希望他除了推荐物品之外，还可以进一步的给出每类物品在各个平台上的最低价格以及对应的商品链接，相关的价格查询逻辑已经由`price-mcp-server`进行了实现。在不依赖Nacos的情况下，添加一个全新的MCP Server我们需要重新对Dify应用进行部署，而现在，我们只需要在Nacos中注册对应的`price-mcp-server`即可。

通过自动注册或者手动配置的方式在 Nacos 注册`price-mcp-server`MCP 服务：

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750667388955-f4884880-3da1-4f07-b2ed-19cb587f12ca.png)



![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750667376307-f030def7-5566-46cc-a96a-fed003b9a972.png)

现在，再让我们调用 AI 导购：  
![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750669215765-671d66b3-8a6f-41e4-8b81-067275921ba6.png)

可以发现，在给出推荐的商品列表之外，AI 导购进一步的利用了`price-mcp-server`来进行了价格的查询和比较，而无需重新部署。

此外，查看模型的工作链路，我们看到模型首先查询了所有可用的 MCP 服务列表，根据任务判断需要调用 `goods-mcp-server` 以及 `price-mcp-server`, 再去查找了具体的 Tools 信息。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750779334628-c70c91b7-fa64-4fce-8991-25ebdc000ad7.png)

如果我们只希望获取物品的列表，则模型只会查询 `goods-mcp-server`的工具列表。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750779548146-9c3e7210-a013-4671-a67a-0f6a2cc909a2.png)

当 MCP 数量和 Tools 数量较多时，可以显著减少模型 Token 消耗。

而在实践中，为了实现更好的路由效果，MCP 本身以及工具的描述是否精准也是重要因素。通过 Nacos MCP 插件接入的 MCP 服务，支持在 MCP 运行时对 MCP 的工具描述和工具开关进行动态调整，根据实际需要向 Dify 应用暴露可用工具进行调优。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750754460137-24505b7c-a108-4612-82d8-db735f037034.png)

## 快速迭代，Nacos 托管 Dify Prompts
Prompt 是生成式 AI 的核心，生产中往往需要根据应用场景不断的进行调整和优化，Dify 已经大大降低了应用开发的成本，但是 Prompt 变化依然要重新发布。而 Nacos 解决的一大痛点就是就是配置“动态”，其提供的动态配置管理能力，天然契合 Prompt 快速调整优化的场景。通过将 Prompt 托管至 Nacos，无需重新编辑部署 Dify 工作流，只需要在 Nacos 中进行简单的配置内容变更，即可实现 Prompt 的动态调优。

依然是以 AI 导购为例，让我们看看如何实现 Prompt 的快速进化吧。

### 实践：AI导购Prompt迭代
#### Prompts 托管至 Nacos
首先，我们要将 Prompt 迁移到 Nacos 上。在 Nacos 控制台上创建一个配置文件，DataId 为 Prompt.template，Group 为默认的 DEFAULT_GROUP，内容为 AI 导购 Prompt 模版对应的内容：

```powershell
你是一个AI导购助手，请查看所有可用的工具，调用工具根据用户的需求给出相关的商品推荐列表，并查询在各个平台上对应的价格，给出性价比最高的选择
```

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750677561460-67ea4e9b-43b2-4143-8ef7-4dbc1225a149.png)

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750678011350-1f7edc11-86ec-4538-b8b4-c222713a009f.png)

#### Nacos 插件安装和配置
为了从 Nacos 中读取 Prompt 动态配置，我们需要在 Dify 中安装 Nacos 插件，并配置插件的连接信息：  
![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750743916118-7a665e0e-5989-4574-92eb-aa305d1e2c30.png)

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750743886184-7693076f-f6c9-44f6-a406-e6fa2be85a28.png)



然后，让我们创建对应的工作流应用，在工作流中创建对应的`读取nacos`工具节点，配置 Prompt 配置的命名空间、配置 ID 和分组的信息

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750743698794-4a4b6648-b1f4-447b-aa05-88817d6a3b0e.png)

创建 Agent 节点，配置 Nacos MCP 工具列表，在指令一栏配置`读取nacos`工具节点的输出作为输入，查询一栏配置`sys.query`作为输入。

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750743834804-b226bf29-b5bf-438e-b211-69e08026ccde.png)

#### 动态变更 Prompt 实现快速迭代
通过以上两步，我们实现了 Nacos 对于 Dify 应用 Prompt 的托管，接下来即可实现在Dify应用运行时动态调整和迭代 Prompt。

首先，我们调用 AI 导购，希望他给我们一些露营的装备建议：

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750679599532-e2209f19-f096-4fa1-a76a-73501dce0cc4.png)  
可以发现，Agent 根据 Prompt 的内容给我们返回了预期之内的结果。

但是，在应用实际上线之后，我们发现这些结果都是以文字格式展示的，客户反馈这种方式不够直观，希望调整为表格的形式展示。因此我们要对 Prompt 进行修改。没有 Nacos 时，我们需要修改Dify工作流并进行重新部署，而将 Prompt 托管到 Nacos上之后，我们只需要在 Nacos 控制台上修改对应的Prompt内容为：

```powershell
你是一个AI导购助手，首先查看所有可用的工具，调用工具根据用户的需求给出相关的商品推荐列表，并查询在各个平台上对应的价格，给出性价比最高的选择，以表格的形式进行展示
```

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750680550442-7df48c2f-ad31-4228-b5f4-2a32b8b844c0.png)

再次询问 AI 导购，发现结果根据我们 Prompt 的要求做了相应的调整，Prompt 变更成功生效了。

#### ![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750680472533-e8c137e6-a324-4449-a255-b84e6df686f1.png)
Nacos 还支持变更历史历史，在历史版本界面，可以查看所有使用过的历史 Prompt 版本，并进行回滚，这样即使 Prompt 效果不够满意，也可以一键复原：

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1743046297502-9819a1ab-b8d3-44c1-89bb-c72caca95481.png)

此外, Nacos 插件中还包含了配置修改的工具，用户也可以在 专门的 Dify 应用中结合大模型的能力实现Prompt的自动优化。

## 高效运维，Nacos 托管 Dify .ENV 启动配置
Dify 平台拥有强大的功能，但也提升了其应用架构的复杂度。Dify平台在生产环境下按照高可用架构需要进行多节点部署，运行时会依赖PostgreSQL，Redis，OSS等第三方中间件组件，访问第三方组件的参数通过环境变量注入的方式，有一定的维护成本，同时也存在泄漏风险。而目前，Dify 支持了将启动参数托管至 Nacos 中，通过读取 Nacos 中的配置获取启动参数，降低运维成本及安全风险”

1. 采取[源代码方式启动Dify服务](https://docs.dify.ai/zh-hans/getting-started/install-self-hosted/local-source-code)
2. 将.env中的参数转移至 Nacos 配置中

![](https://intranetproxy.alipay.com/skylark/lark/0/2025/png/101856292/1750736461495-49c4dfc2-2e03-49c1-b6da-400bf722074c.png)

3. 在启动API服务时将以下环境变量进行注入

```properties
REMOTE_SETTINGS_SOURCE_NAME=nacos
DIFY_ENV_NACOS_SERVER_ADDR=<nacos_addr>
DIFY_ENV_NACOS_DATA_ID=<启动参数配置文件的data_id>
DIFY_ENV_NACOS_GROUP=<启动参数配置文件的分组>
DIFY_ENV_NACOS_NAMESPACE=<启动参数配置文件所在的命名空间>
DIFY_ENV_NACOS_USERNAME=
DIFY_ENV_NACOS_PASSWORD=
DIFY_ENV_NACOS_ACCESS_KEY=
DIFY_ENV_NACOS_SECRET_KEY=
```

在启动时，Dify 应用会从 Nacos 读取相应的配置。通过这种方式，基于 Nacos, 即可实现启动参数白屏化变更和历史版本管理，提高运维效率。

## 结语
Nacos 与 Dify 的结合，为生成式 AI 应用的开发效率与架构灵活性提供了新的解决方案。通过动态发现 Nacos MCP 服务，Dify 能快速集成标准化的模型工具接口，简化服务调用流程；借助 Nacos 托管 Prompt 配置，开发者无需重新部署即可实现提示词的实时优化，真正释放敏捷开发潜力；而 Nacos 对环境变量的集中管理，则进一步简化了 Dify 应用的运维复杂度，让 Dify 的部署配置更易维护和复用。这一整合不仅解决了 Dify 在 MCP 协议适配和动态配置管理上的痛点，也体现了云原生技术与 AI 开发工具的务实协作。

未来，随着 AI 应用场景的不断扩展，Nacos 将进一步的拥抱AI生态，从基础的配置管理和服务发现，到 MCP 生态的持续拓展和集成，再到多 Agent的协同，Nacos 将持续演进，帮助开发者提升效率，降低复杂环境下的开发与运维成本，让 AI 应用的构建更加高效、稳定。

## 相关链接和推荐阅读
<font style="color:rgb(53, 56, 65);">[1] Nacos 官网</font>

[_<u>https://nacos.io</u>_](https://nacos.io/)

<font style="color:rgb(53, 56, 65);">[2] Nacos Github 主仓库</font>

[_<u>https://github.com/alibaba/nacos</u>_](https://github.com/alibaba/nacos)

<font style="color:rgb(53, 56, 65);">[3] 生态组仓库</font>

[_<u>https://github.com/nacos-group</u>_](https://github.com/nacos-group)

<font style="color:rgb(53, 56, 65);">[4] 《Nacos 3.0 正式发布：MCP Registry、安全零信任、链接更多生态》</font>

[https://mp.weixin.qq.com/s/0j9R7cMw7opuRZrx8k2L6A](https://mp.weixin.qq.com/s/0j9R7cMw7opuRZrx8k2L6A)

<font style="color:rgb(53, 56, 65);">[5] 《Nacos MCP Router 新版发布：支持 Docker 远程部署，MCP的多协议stido、SSE、Streamable互相转换》</font>

[https://mp.weixin.qq.com/s/80FW8VysOxJ3TUGXcEfL5A](https://mp.weixin.qq.com/s/80FW8VysOxJ3TUGXcEfL5A)

[6] MCP Server 自动注册手册

[https://nacos.io/docs/latest/manual/user/ai/mcp-auto-register/?spm=5238cd80.2ef5001f.0.0.3f613b7crgeNaW](https://nacos.io/docs/latest/manual/user/ai/mcp-auto-register/?spm=5238cd80.2ef5001f.0.0.3f613b7crgeNaW)

[7] 存量API转换MCP手册

[https://nacos.io/docs/latest/manual/user/ai/api-to-mcp/?spm=5238cd80.30a1e062.0.0.5d0b22f0djfN01](https://nacos.io/docs/latest/manual/user/ai/api-to-mcp/?spm=5238cd80.30a1e062.0.0.5d0b22f0djfN01)

[8] 源代码方式启动Dify服务

[https://docs.dify.ai/zh-hans/getting-started/install-self-hosted/local-source-code](https://docs.dify.ai/zh-hans/getting-started/install-self-hosted/local-source-code)
