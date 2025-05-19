# buession-core 参考手册


## 构建器


将对象序列化为二进制或 JSON。

您可以通过该 API，实现将对象序列化成二进制或 JSON 字符串。


### 序列化、反序列化类


|  类   | 说明  |
|  ----  | ----  |
| DefaultByteArraySerializer  | 将对象序列化为二进制 |
| FastJsonJsonSerializer  | 基于 FastJSON 的对象与 JSON 之间的序列化 |
| GsonJsonSerializer  | 基于 Gson 的对象与 JSON 之间的序列化 |
| JacksonJsonSerializer  | 基于 Jackson2 的对象与 JSON 之间的序列化 |


1. `DefaultByteArraySerializer` 序列化成字符串，逻辑是在对象序列化为 `byte` 数组后，通过 `URLEncoder.encode` 进行编码
2. `FastJsonJsonSerializer`、`GsonJsonSerializer`、`JacksonJsonSerializer` 可以通过参数 `Class<T>`、`TypeReference<V>` 指定返回的对象类型


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.3.0/com/buession/core/serializer/package-summary.html)