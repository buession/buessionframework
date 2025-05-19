# buession-httpclient 参考手册


## 连接配置


您可以通过连接配置类 `Configuration` 配置 `apache httpcomponents` 和 `okhttp3` 的链接配置属性，`buession-httpclient` 内部会自动将 `Configuration` 的配置信息，转换为 `apache httpcomponents` 或 `okhttp3` 的配置信息。


### 配置属性说明


|  属性名称             | 数据类型  | apache httpcomponents 对应配置    | okhttp3 对应配置       | 默认值      | 说明                    |
|  ----                | ----     | ----                             | ----                  | ----       | ----                   |
|  maxConnections      | int      | maxTotal                         | maxIdleConnections    | 5000       | 最大连接数              |
|  maxPerRoute         | int      | defaultMaxPerRoute               | --                    | 500        | 每个路由的最大连接数      |
|  idleConnectionTime  | int      | closeIdleConnections             | keepAliveDuration     | 60000      | 空闲连接存活时长（单位：毫秒）      |
|  connectTimeout      | int      | connectTimeout                   | connectTimeout        | 3000       | 连接超时时间（单位：毫秒）      |
|  connectionRequestTimeout    | int     | connectionRequestTimeout  | --                    | 5000       | 从连接池获取连接的超时时间（单位：毫秒）     |
|  readTimeout      | int      | socketTimeout                       | readTimeout           | 5000       | 读取超时时间（单位：毫秒）      |
|  allowRedirects      | Boolean      | redirectsEnabled             | followRedirects       | --       | 是否允许重定向      |
|  relativeRedirectsAllowed      | Boolean      | relativeRedirectsAllowed         | --    | --       | 是否应拒绝相对重定向      |
|  circularRedirectsAllowed      | Boolean      | circularRedirectsAllowed         | --    | --       | 是否允许循环重定向      |
|  maxRedirects      | Integer      | maxRedirects         | --    | --       | 最大允许重定向次数      |
|  authenticationEnabled      | boolean      | authenticationEnabled         | --    | --       | 是否开启 Http Basic 认证      |
|  contentCompressionEnabled      | boolean      | contentCompressionEnabled         | --    | --       | 是否启用内容压缩      |
|  normalizeUri      | boolean      | normalizeUri         | --    | --       | 是否标准化 URI      |


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-httpclient/2.0.2/com/buession/httpclient/core/Configuration.html)