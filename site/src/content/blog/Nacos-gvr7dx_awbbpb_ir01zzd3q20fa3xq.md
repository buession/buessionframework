---
title: "Nacos 配置中心变更利器：自定义标签灰度"
description: "Nacos 配置中心变更利器：自定义标签灰度"
date: "2024-11-12"
category: "article"
keywords: ["Nacos"]
authors: "CH3CHO"
---

# 配置中心被广泛使用
配置中心是Nacos的核心功能之一，接入配置中心，可以实现不重启线上应用的情况下动态改变程序的运行期行为，在整个软件生命周期中，可以极大降低了软件构建及部署的成本，提升效率，为业务发展提速。配置中心在各个领域都有着非常广泛的应用。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1713424054235-44a1c6e6-b907-40d6-a626-fdeee073133c.png?x-oss-process=image%2Fformat%2Cwebp%2Fresize%2Cw_1650%2Climit_0)

上图列举了Nacos的一些非常常见的使用场景，覆盖了微服务领域，高可用领域，前端生态，数据库领域等等，可以看出其中有一些非常关键的场景都是靠配置中心来完成的，比如流量调度，路由规则，应急预案，一些业务的关键开关，数据库的一些数据源配置等等，这些数据的正确性直接影响线上业务运行的稳定性。

# 配置变更的风险
动态变更配置带来软件迭代效率的提升，同时也会带来一定的稳定性风险，对线上配置的误操作，可能导致线上程序执行出错，进而对业务产生影响，在对变更没有进行完善的流程管控的情况下，动态配置变更从某种角度上来说会扩大线上稳定性问题的敞口，业界由于配置变更导致的故障时有发生，因此对于配置的变更管控是非常必要的。

出于稳定性考虑，变更都需具有可灰度，可监控，可回滚三个基本要求。

可灰度：要求任何变更都可以控制其生效范围，在变更过程中可逐步扩大生效范围。

可监控：要求对业务的各项指标都有可视化展示，可正确清晰地反应业务的运行状态。

可回滚：在变更期间，如观测到业务异常，可以快速使业务回退至变更前的状态。

灰度发布是配置中心用于控制配置变更风险的一个非常重要且基础的功能。

一个规范的配置变更流程如下所示：

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1728984473438-f69243fe-5b31-45f9-80f6-df0ebe37df28.png?x-oss-process=image%2Fformat%2Cwebp%2Fresize%2Cw_932%2Climit_0)

先选取一部分低风险应用服务器作为第一个版本的灰度，下发新配置，观察服务器的系统及业务指标，确认业务正常后，逐步扩大灰度范围，直至发布正式版本完成整体的配置变更流程，如果期间业务出现异常，选择终止灰度，先前已灰度的服务器也同时回滚至变更前的配置，以快速恢复业务。

# 基于IP灰度的局限
在之前的版本中，Nacos提供了基于IP地址的灰度方式，用户在发布正式版本配置前，可以选择一些IP地址下发新配置进行验证，确认业务正常，观察期过后，选择全量发布。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730274834265-a61e2c5f-18ac-4c4d-a8b2-db3e4804619e.png)

对于一些小型应用，基于IP灰度的方式已经可以满足诉求，而对于一些中大型应用，一个配置可能会被大量的应用节点订阅，这些应用节点可能归属于不同的业务应用，这些应用在应用系统中扮演不同的角色，对线上业务的影响面也截然不同。当应用节点IP数量较大时，易用性会大大降低，IP地址是底层基础网络设施决定，无法和业务应用做直接的关联，在扩大灰度范围时，需要自行筛选不同业务特点的节点IP列表，此外在k8s容器化部署下，节点重启之后IP可能会发生变化而导致灰度失效。

# 自定义标签灰度
为了解决基于IP灰度的局限性，MSE Nacos对灰度发布进行了功能升级，支持了自定义标签灰度的能力，结合原先的IP灰度，可以给业务提供更加灵活更加易用的灰度发布能力，为业务稳定性保驾护航。

自定义标签灰度允许应用侧根据其实际场景给不同的节点设置不同的标签，比如给节点设置应用，机房，环境等标签，比如在阿里集团内部，同一个应用在线下环境中有不同的项目开发分支，就给对应的开发分支部署的节点打上项目分支的标签，借助标签灰度的能力实现不同的项目环境下发不同的配置内容。

以下我们将介绍应用如何接入自定义标签灰度的能力。![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730371901059-05b08246-ebc0-45b4-b305-5bd553201ee3.png)

## 版本要求
自定义标签灰度是新上线的功能，因此对客户端和服务端版本均有一定的要求。

+ <font style="color:rgb(88, 88, 88);background-color:rgb(255, 249, 243);">开源Nacos-Client版本在2.3.2及以上支持自定义应用标签灰度，您需要升级客户端版本至2.3.2及以上。</font>
+ <font style="color:rgb(88, 88, 88);background-color:rgb(255, 249, 243);">MSE Nacos版本在2.2.3.3及以上支持应用标签灰度，您需要升级引擎版本至2.2.3.3及以上。</font>

## <font style="color:rgb(24, 24, 24);">设置应用标签</font>
在完成客户端和服务端版本升级之后，<font style="color:rgb(24, 24, 24);">您可以通过以下方式设置应用的标签，应用标签为key-value格式。可以通过properties，JVM参数，环境变量三种方式指定。</font>

`<font style="color:rgb(24, 24, 24);">nacos.config.gray.label</font>`<font style="color:rgb(24, 24, 24);">是Nacos内置的默认配置灰度标签。你可以通过设置nacos.config.gray.label={value}参数设置标签值。</font>

```plain
//1.properties属性设置标签
Properties properties = new Properties();
properties.put(PropertyKeyConst.SERVER_ADDR, "{server addr}");
//other properties
properties.put("nacos.config.gray.label","yourgrayname");

ConfigFactory.createConfigService(properties);

// 2.通过JVM参数设置标签
-Dnacos.config.gray.label={value}

// 3.通过环境变量设置标签
nacos_config_gray_label={value}
```

+ properties属性传入的标签只对当前ConfigService对象生效，作用范围最小。
+ 通过JVM参数设置标签，对当前进程内的所有ConfigService生效
+ 通过环境变量设置标签，对当前节点上的所有ConfigService生效
+ 当三种方式设置的标签key存在重复时，读取的优先级为 properties>JVM参数>环境变量

## 发布标签灰度
成功设置应用标签，对应用进行重启后，可以通过MSE Nacos控制台查看配置的监听查询界面，在其中的应用节点标签一列中看到客户端设置的标签key-value。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730276386963-9eea7bfd-15c9-4658-81bd-4088b8a787dd.png)

在客户端成功设置标签之后，在MSE控制台打开配置编辑界面，在发布方式中选择"**<font style="color:rgb(17, 17, 17);">基于标签灰度发布</font>**",设置灰度版本名称，选择标签键及标签值，可以看到预期匹配到的应用节点数量以及节点列表，在界面右侧配置内容框中编辑配置内容，点击”发布灰度“完成配置发布。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730276268931-9bfc98ad-f8e8-4e13-811f-61917c6c6e2c.png)

发布成功后，打开配置详情页，点击配置内容Tab，可以看到有"正式"和"Beta"两个版本。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730276706367-24889b06-a9f8-4e1f-a56a-ae657f322e2b.png)

切换至监听查询界面，可以看到对应的业务节点当前的匹配的版本, 展示的版本为Beta({灰度版本名称}), md5为对应版本的md5，推送状态为推送成功。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730276732325-185fcd9d-50e2-4e48-8a37-412de4611dcf.png)

此外，在推送轨迹界面也可以看到标签发布以及客户端查询灰度版本的轨迹，在节点的配置推送轨迹详情中可以看到具体推送的配置版本。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730276813123-d71d993d-9a53-4caa-811b-778a514dc3ac.png)

在发布标签灰度时，我们可以选择编辑灰度配置选择多个标签值来实现逐步扩大灰度范围的目的。以下我们简单介绍几种常见的用法：

1.将应用A下的机器都设置<font style="color:rgb(24, 24, 24);">nacos.config.gray.label</font>=A的标签，将应用B下的机器设置<font style="color:rgb(24, 24, 24);">nacos.config.gray.label</font>=B的标签，在进行灰度发布时，我们可以先发布规则为<font style="color:rgb(24, 24, 24);">nacos.config.gray.label</font>=A的灰度版本对应用A的节点进行灰度，而后编辑灰度规则为<font style="color:rgb(24, 24, 24);">nacos.config.gray.label</font>=A,B 将应用B的节点也纳入灰度范围。

2.通过环境来设置标签，比如将线上环境的机器分为不同的分组，设置<font style="color:rgb(24, 24, 24);">nacos.config.gray.label=gray1，nacos.config.gray.label=gray2，nacos.config.gray.label=gray3来区分不同的灰度环境，将线上正式的机器</font>设置<font style="color:rgb(24, 24, 24);">nacos.config.gray.label=online，在对nacos.config.gray.label进行标签发布来实现不同环境逐步扩大灰度的能力。</font>

<font style="color:rgb(24, 24, 24);">完成灰度版本发布后，观察业务侧的监控指标，度过观察期后，我们可以在灰度版本编辑界面中的应用节点标签值编辑匹配的值，扩大灰度规则覆盖的节点范围，确保所有灰度节点指标正常后，可以点击全量发布将当前灰度的配置内容发布为正式版本，成功发布正式版本后，当前的灰度版本将会终止。</font>

## 多标签灰度
以上，我们使用nacos内置的标签nacos.config.gray.label作为示例，对于一些中小型应用来说已经可以满足自定义灰度的需求，但对于相对复杂的中大型应用，单个标签可能无法应对业务侧的复杂性。实际上，MSE并没有对标签key做强制的约束，业务应用可以自行选择其他的标签key作为灰度，并且也支持给应用设置多个标签，以下我们将介绍如何给业务节点设置多个标签键值对。

<font style="color:rgb(24, 24, 24);">和设置单个标签类似，客户端可以在properties及JVM参数，环境变量中设置多个标签。在properties和JVM参数中通过nacos.app.conn.labels来设置多个标签，（示例：</font>`<font style="color:rgb(24, 24, 24);">nacos.app.conn.labels=k1=v1,k2=v2,k3=v3</font>`<font style="color:rgb(24, 24, 24);">），在环境变量中指定</font>**<font style="color:rgb(24, 24, 24);">nacos_app_conn_labels</font>**<font style="color:rgb(24, 24, 24);">参数 (</font>**<font style="color:rgb(24, 24, 24);">nacos_app_conn_labels</font>**<font style="color:rgb(24, 24, 24);">=k1=v1,k2=v2,k3=v3)注入应用的多个标签。</font>

```plain
//1.properties形式传入
Properties properties = new Properties();
properties.put(PropertyKeyConst.SERVER_ADDR, "your endpoint");
properties.put("project.name", "your app name");
properties.put("nacos.app.conn.labels","app=demo,site=hangzhou-c,otherkey=othervaue");

//2.JVM参数设置
设置启动参数-Dnacos.app.conn.labels=app=demo,site=hangzhou-c,otherkey=othervaue

//3.env环境变量指定
设置环境变量 nacos_app_conn_labels=app=demo,site=hangzhou-c,otherkey=othervaue

NacosConfigService configService = new NacosConfigService(properties);

String dataId = "gray_test_dataid";
String group = "test-group";
        
configService.addListener(dataId, group, new Listener() {
        @Override
        public Executor getExecutor() {
            return null;
        }
            
        @Override
        public void receiveConfigInfo(String configInfo) {
            System.out.println("receiveConfig:" + configInfo);
        }
});
```

除了通过参数设置之外，如果标签的读取逻辑比较复杂，也可以选择通过实现自定义SPI接口来设置标签。<font style="color:rgb(24, 24, 24);">Nacos-Client定义了自定义应用标签的SPI，实现</font>`<font style="color:rgb(24, 24, 24);">com.alibaba.nacos.common.labels.LabelsCollector</font>`<font style="color:rgb(24, 24, 24);">接口，并将具体实现类在META-INF/services发布为SPI。</font>

```plain
package your.demo.test;

import com.alibaba.nacos.common.labels.LabelsCollector;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * TestLabelsCollector.
 *
 * @author yourname
 */
public class TestLabelsCollector implements LabelsCollector {
    
    @Override
    public String getName() {
        return "testlables";
    }
    
    @Override
    public Map<String, String> collectLabels(Properties properties) {
        Map<String, String> labels = new HashMap<>();
        labels.put("test", "implements your lables logic");
        return labels;
    }

  
    @Override
    public int getOrder() {
        return 1;
    }
}
```

应用可以自行设置标签的键值对，但为了尽量避免应用使用标签灰度时遇到异常，Nacos对标签的键值也对做了一些约束。

    - <font style="color:rgb(24, 24, 24);">标签键值key-value允许使用的字符包括英文字母大小写、数字、下划线“_”、横杠“-”和点“.”。如果传入其他格式的标签将被忽略处理。如果通过参数</font>`<font style="color:rgb(24, 24, 24);">nacos.app.conn.labels</font>`<font style="color:rgb(24, 24, 24);">指定多个键值对时，格式必须遵循</font>`<font style="color:rgb(24, 24, 24);"> k1=v1,k2=v2,k3=v3 </font>`<font style="color:rgb(24, 24, 24);">格式。如果传入</font>`<font style="color:rgb(24, 24, 24);">k1=v1,k2</font>`<font style="color:rgb(24, 24, 24);">，那么k2参数将会被忽略，最终解析成</font>`<font style="color:rgb(24, 24, 24);">k1=v1</font>`<font style="color:rgb(24, 24, 24);">。</font>
    - <font style="color:rgb(24, 24, 24);">MSE对单个应用节点的标签键值对数量上限也有一定限制，最大支持10个标签键值对，超过上限时，会随机剔除多余的键值对，请合理规划应用标签数量。</font>



    当前在标签灰度界面设置匹配规则时，可以选择多个标签值匹配多种类型的节点列表，但为了降低功能的复杂度，暂未开放在一个灰度规则中匹配多个标签键，比如通过设置k1=v1&&k2=v2表达式来进一步细化灰度范围，也不支持在一个灰度版本中设置规则为k1=v1||k2=v2来扩大灰度范围。

# 多灰度版本并行
MSE Nacos支持在一个配置下发布多个灰度版本，比如对匹配 k1=v1 的节点下发graycontent1的配置内容，同时对k1=v2的节点下发graycontent2的配置内容。我们可以在配置编辑界面Tab页点击+来创建新的灰度版本。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730278154962-818386d8-cb74-45ad-98c2-367e1f1899f0.png)

成功创建多个灰度版本后，我们在配置的编辑界面和详情界面可以看到多个灰度版本并排展示。

![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1730369791662-8df916ee-c73d-4b8f-a633-f7e1d6d390c5.png)

一个配置发布了多个灰度版本时，灰度的标签键值对可能匹配到完全不重合的节点列表，但也可能匹配到同一个应用节点，此时需要关注一个匹配的优先级问题。

对于一个配置来说，可能存在多个配置的版本，包括正式版本，基于IP的灰度版本，基于标签的灰度版本，其中基于标签的灰度可以发布多个版本。当一个配置存在多个版本，并且应用侧的标签被多个灰度版本的规则匹配到时，Nacos服务端将以如下的优先级规则进行匹配。 基于IP的灰度版本> 基于标签的灰度版本> 正式版本，多个基于标签的灰度版本之间将以优先级<font style="color:rgb(51, 51, 51);">priority</font>字段排序，优先级<font style="color:rgb(51, 51, 51);">priority</font>值越大，优先级越高，优先级<font style="color:rgb(51, 51, 51);">priority</font>值相同时，将灰度名称的字符顺序匹配，建议将不同的规则设置为不同优先级。



![](https://intranetproxy.alipay.com/skylark/lark/0/2024/png/2483/1729580902847-a8faf967-a2d4-4929-9f6a-c99fdd973abe.png)

    - <font style="color:rgb(24, 24, 24);">MSE对一个配置所发布的最大灰度版本数量也有一定限制，默认最大支持5个标签灰度的版本，超过上限时，灰度发布将被拦截。</font>

在配置编辑界面和详情界面，各个灰度版本的展示顺序是按照以上的优先级逻辑进行展示，可以通过界面直观地看到各个版本的优先级。如果我们想提高某个灰度版本的优先级，可以在编辑页面修改优先级的数值，点击发布即可。

多灰度版本并行时，选择其中一个灰度版本进行全量发布，<font style="color:rgb(24, 24, 24);">可以将当前灰度的配置内容发布为正式版本，成功发布正式版本后，当前的灰度版本将会终止，但此时其他并行的灰度版本不会被终止，它们的规则所覆盖的节点仍将接收对应灰度版本的配置内容。</font>

# 结语
以上我们完成了对MSE Nacos应用自定义标签灰度的功能介绍，欢迎大家升级版本进行试用，如果使用期间遇到问题或者对配置的灰度有更多的使用场景，欢迎联系我们。

**相关链接：**

[1] Nacos 官网

_<u><font style="color:rgb(0, 122, 170);">https://nacos.io</font></u>_

[2] Nacos Github 主仓库

_<u><font style="color:rgb(0, 122, 170);">https://github.com/alibaba/nacos</font></u>_

[3] 生态组仓库

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group</font></u>_

[4] MSE 配置灰度发布

_<u><font style="color:rgb(0, 122, 170);">https://help.aliyun.com/zh/mse/user-guide/configure-canary-release</font></u>_

**Nacos 多语言生态仓库：**

[1] Nacos-GO-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-go</font></u>_

[2] Nacos-Python-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-python</font></u>_

[3] Nacos-Rust-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-rust</font></u>_

[4] Nacos C# SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-csharp</font></u>_

[5] Nacos C++ SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-cpp</font></u>_

[6] Nacos PHP-SDK

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/nacos-sdk-php</font></u>_

[7] Rust Nacos Server

_<u><font style="color:rgb(0, 122, 170);">https://github.com/nacos-group/r-nacos</font></u>_

**推荐阅读：**

《[Nacos 安全零信任实践](http://mp.weixin.qq.com/s?__biz=MzUzNzYxNjAzMg==&mid=2247563843&idx=1&sn=c115a13b7a91ef66e001de6cfc4bf0ed&chksm=fae7f18ccd90789aed1ba919775facc1775b3eedcda2991c998eee8eaa291d5b193fd5c353a3&scene=21#wechat_redirect)》

《[MSE Nacos：解决敏感配置的安全隐患](http://mp.weixin.qq.com/s?__biz=MzUzNzYxNjAzMg==&mid=2247560684&idx=1&sn=edad3ba00700c7a7848631c1571771ba&chksm=fae7e623cd906f35dbf754daef77f5b60e6963f7f15f14ce337cadbb5e3c70d69a898e82846e&scene=21#wechat_redirect)》




