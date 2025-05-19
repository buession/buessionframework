# buession-net 参考手册


网络相关工具类。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-net</artifactId>
    <version>x.x.x</version>
</dependency>
```


### IP 地址工具类

IP 地址工具类 `com.buession.net.utils.InetAddressUtis` 实现了，IPV4 地址和数字型 IP 相互转换。

```java
import com.buession.net.utils.InetAddressUtis;

long result = InetAddressUtis.ip2long("127.0.0.1"); // 2130706433
String ip = InetAddressUtis.long2ip(2130706433L); // 127.0.0.1
```

URI 类或 URIBuilder 类，实现了 url 字符串的构建，详细查看 API 手册。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-net/2.0.2/)