# buession-core 参考手册


## 构建器


对象的序列化和反序列化，包括二进制和 JSON。

您可以通过该 API，实现将对象序列化成二进制或 JSON 字符串；或将二进制、JSON 字符串反序列化为对象。


### 序列化、反序列化类


|  类   | 说明  |
|  ----  | ----  |
| DefaultByteArraySerializer  | 将对象序列化为二进制，或将二进制反序列化为对象 |
| FastJsonJsonSerializer  | 基于 FastJSON 的对象与 JSON 之间的序列化和反序列化 |
| GsonJsonSerializer  | 基于 Gson 的对象与 JSON 之间的序列化和反序列化 |
| JacksonJsonSerializer  | 基于 Jackson2 的对象与 JSON 之间的序列化和反序列化 |


1. 通用标准方法是，将对象序列化为字符串，或将字符串反序列化为对象
2. `DefaultByteArraySerializer` 序列化成字符串，逻辑是在对象序列化为 `byte` 数组后，通过 `URLEncoder.encode` 进行编码；反序列化，则先通过 `URLDecoder.decode` 进行解码成 `byte` 数组，在反序列化为对象
3. `DefaultByteArraySerializer` 支持对象与 `byte` 数组数组之间的序列化和反序列化
4. 在将 JSON 字符串反序列化为对象时，默认返回类型依据 fastjson、jackson 等原生逻辑
5. `FastJsonJsonSerializer`、`GsonJsonSerializer`、`JacksonJsonSerializer` 可以通过参数 `Class<T>`、`TypeReference<V>` 指定返回的对象类型
6. `com.buession.core.serializer.type.TypeReference` 是某类型的一个指向或者引用，用于屏蔽 fastjson、gson、jackson 中通过 JDK `Type` 指定反序列化的类型；在 fastjson、gson 中是直接指定 `Type`，在 jackson 中是通过 `com.fasterxml.jackson.core.type.TypeReference` 类返回


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.0.2/com/buession/core/serializer/package-summary.html)