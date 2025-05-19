# buession-web 参考手册


web 相关的功能封装，支持 servlet 和 reactive；封装了一些常用注解，简化了业务层方面的代码实现；封装了一些常用 filter。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-web</artifactId>
    <version>x.x.x</version>
</dependency>
```

`buession-web` 扩展了 `spring-webmvc`、`spring-webflux`；在此基础上，提供了大量的实用的 filter 和注解，以及工具类。该模块无论封装的 filter、注解、工具类，均适用于 spring mvc，也适用于 spring webflux。当时，filter、工具类均为 servlet 和 webflux 各自独立的类，不是说同一个类，即能用于 servlet，也能用于 webflux，当然注解是通用的。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-web/3.0.0/)