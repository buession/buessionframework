# buession-cron 参考手册


对 quartz 的二次封装


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-cron</artifactId>
    <version>x.x.x</version>
</dependency>
```

由于在过去的工作中，定时任务基本使用 quartz 来实现；但是在初始化定时任务项目时，大量基本相同的代码，因此对此部分做了二次封装，简化了 quartz 项目的初始化。

由于在现在有众多优秀的分布式定时任务，如：elastic-job、xxl-job 等等，因此直接使用 quartz 应该会越来越少（个人主观猜测），即使使用 quartz 初始化也简单，故该模块将不做说明。

且在今后的版本中，该模块可能会被废弃。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-cron/2.2.0/)