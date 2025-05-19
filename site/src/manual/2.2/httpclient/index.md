# buession-httpclient 参考手册


对 `apache httpcomponents`、`okhttp3` 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节，屏蔽了对 post form、post json 等等的技术细节。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-httpclient</artifactId>
    <version>x.x.x</version>
</dependency>
```

我们在应用中使用 Http Client 功能时，经常因为从 `apache httpcomponents` 切换为 `okhttp3`，或者从 `okhttp3` 切换为 `apache httpcomponents`，需要改动大量的代码而烦恼。而当您使用了 `buession-httpclient` 时，该类库为您解决了这些烦恼，通过顶层设计，屏蔽了 `apache httpcomponents` 和 `okhttp3` 的细节，当您需要从一个 http 库更换为另外一个 http 库时，您只需要在 pom.xml 中引用不同的包，修改一下 httpclient 的初始化类和连接管理器即可。

传统的方式：

```xml
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpcore</artifactId>
    <version>x.x.x</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>x.x.x</version>
</dependency>
```

```java
import org.apache.http.HttpResponse;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpPost;

HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(new HttpClientConnectionManager()).build();

HttpResponse response = httpClient.execute(new HttpPost("https://www.buession.com/"));
```

或者

```xml
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>x.x.x</version>
</dependency>
```

```java
import okhttp3.HttpClientConnectionManager;
import okhttp3.OkHttpClient;
import okhttp3.ConnectionPool;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

OkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(new ConnectionPool());
HttpClient httpClient = builder.build();

Builder requestBuilder = new Builder().post();
requestBuilder.url("https://www.buession.com/");
Request okHttpRequest = requestBuilder.build();

Response httpResponse = httpClient.newCall(okHttpRequest).execute();
```

现在，您只需要通过 `buession-httpclient`，即可屏蔽其中的细节。

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-httpclient</artifactId>
    <version>x.x.x</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpcore</artifactId>
    <version>x.x.x</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>x.x.x</version>
</dependency>
```

或者

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-httpclient</artifactId>
    <version>x.x.x</version>
</dependency>
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>x.x.x</version>
</dependency>
```

```java
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.ApacheHttpClient;
import com.buession.httpclient.OkHttpHttpClient;
import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.conn.OkHttpClientConnectionManager;
import com.buession.httpclient.core.Response;

HttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager()); // 或者 new OkHttpHttpClient(new OkHttpClientConnectionManager());

Response response = httpClient.post("https://www.buession.com/");
```

### 展望

目前，`buession-httpclient` 仅支持同步，不支持异步。我们会在下一个小版本（即：2.2） 中，集成 `apache httpcomponents` 切换为 `okhttp3` 的异步 http 请求。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-httpclient/2.2.0/)