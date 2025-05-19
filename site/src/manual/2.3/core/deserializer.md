# buession-core 参考手册


## 构建器


将二进制或 JSON 反序列化为对象。

您可以通过该 API，实现将将二进制、JSON 字符串反序列化为对象。


### 序列化、反序列化类


|  类   | 说明  |
|  ----  | ----  |
| DefaultByteArrayDeserializer  | 将对象序列化为二进制，或将二进制反序列化为对象 |
| FastJsonJsonDeserializer  | 基于 FastJSON 的对象与 JSON 之间的序列化和反序列化 |
| GsonJsonDeserializer  | 基于 Gson 的对象与 JSON 之间的序列化和反序列化 |
| JacksonJsonDeserializer  | 基于 Jackson2 的对象与 JSON 之间的序列化和反序列化 |


1. `DefaultByteArrayDeserializer` 通过 `URLDecoder.decode` 进行解码成 `byte` 数组，再反序列化为对象
2. 在将 JSON 字符串反序列化为对象时，默认返回类型依据 fastjson、jackson 等原生逻辑
3. `FastJsonJsonDeserializer`、`GsonJsonDeserializer`、`JacksonJsonDeserializer` 可以通过参数 `Class<T>`、`TypeReference<V>` 指定返回的对象类型
4. `com.buession.core.type.TypeReference` 是某类型的一个指向或者引用，用于屏蔽 fastjson、gson、jackson 中通过 JDK `Type` 指定反序列化的类型；在 fastjson、gson 中是直接指定 `Type`，在 jackson 中是通过 `com.fasterxml.jackson.core.type.TypeReference` 类返回


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.3.0/com/buession/core/serializer/package-summary.html)