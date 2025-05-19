# buession-httpclient 参考手册


## 连接管理器


连接管理器是用来管理 HTTP 连接的，包括设置连接配置、控制连接池。关于更多的连接池，可以参考 `apache httpcomponents` 和 `okhttp3` 的文档。

您可以通过无参数的构造函数来创建连接管理器，也可以通过构造函数参数仅为 `com.buession.httpclient.core.Configuration` 来创建连接管理器，也可以构造函数通过 `apache httpcomponents` 或 `okhttp3` 原生的连接管理器类创建（此时，`Configuration` 的配置不任何意义，他不会修改您通过原生连接管理器实例中的参数配置）。


### 关于 okhttp 连接管理器

okhttp3 本身是没有类似 apache httpcomponents 的链接管理器 `org.apache.http.conn.HttpClientConnectionManager` 的，我们为了在 `buession-httpclient` 的链接管理器实现 `com.buession.httpclient.conn.OkHttpClientConnectionManager` 保持一致，人为的加了一层 okhttp3 的链接管理器 `okhttp3.HttpClientConnectionManager`（注意：命名空间为 okhttp3），主要用于初始化连接池类 `okhttp3.ConnectionPool`。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-httpclient/2.3.0/com/buession/httpclient/conn/ConnectionManager.html)