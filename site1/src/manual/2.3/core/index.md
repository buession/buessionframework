# buession-core 参考手册


该类库为核心包，包括构建器、工具类、数据验证、ID 生成器、转换器、序列化、数学方法、消息注入、Manager 层注解、日期时间类和各通用接口定义。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-core</artifactId>
    <version>x.x.x</version>
</dependency>
```


#### [构建器](builder.md)

Map、集合的便捷式构建，减少您的代码行数


#### [编码器](codec.md)

目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中


#### [收集器](collect.md)

数组、Map、集合的工具类


#### [上下文](context.md)

定义应用上下文的类库、注解


#### [配置器接](configurer.md)

使用配置参数对对象进行配置



#### [定制器](customizer.md)

使用源对象对目标对象进行定制



#### [转换器](converter.md)

数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。


#### [日期时间](datetime.md)

日期、时间工具


#### [ID 生成器](id.md)

基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。


#### [数学函数](math.md)

定义了实用的数学函数


#### [序列化](serializer.md)

将对象序列化为二进制或 JSON。


#### [反序列化](deserializer.md)

将二进制或 JSON 反序列化为对象。


#### [验证器](validator.md)

数据验证器及其注解


#### [工具类](utils.md)

常用通用性工具类


#### [其它](other.md)

通用的接口定义，框架自身类


#### [异常](exception.md)

通用异常的定义


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.3.0/)