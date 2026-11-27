# buession-httpclient 参考手册


## 响应


当通过 `HttpClient` 发起任意请求后，将得到一个 `Response`。此结果，包括了：协议及其版本、状态码及其信息、响应头列表、响应体、以及响应体长度。
`buession-httpclient` 会将 `apache httpcomponents` 或 `okhttp3` 的响应对象，转换为 `Response`。

需要注意的是，原生 `apache httpcomponents` 或 `okhttp3` 响应体流，一旦被使用过一次之后，将不能再使用；同时您只能以流或者字符串二选一的形式获取响应体。但是，在 `buession-httpclient` 中您将可以二者兼顾，当然这也同时会带来一些性能消耗和内存占用。

```java
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.ApacheHttpClient;
import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.core.Response;
import java.io.InputStream;

HttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager());

Response response = httpClient.post("https://www.buession.com/");
InputStream stream = response.getInputStream(); // 以流的形式获取响应体
String body = response.getBody(); // 以字符串的形式获取响应体

stream.close();
```

`getInputStream`、`getBody` 二者可以重复调用，当时您需要始终手动关闭一下流，因为这将是拷贝的原生 `apache httpcomponents` 或 `okhttp3` 返回的流。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-httpclient/3.0.0/com/buession/httpclient/core/Response.html)