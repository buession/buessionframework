# buession-web 参考手册


## 工具


我们封装了一些 web 相关的工具类，用于处理 request、response。

servlet 包位于 `com.buession.web.servlet.http`，webflux 包位于 `com.buession.web.reactive.http`，均有同样类名的过滤器类。


获取客户端真实 IP 地址：

```java
RequestUtils.getClientIp(request);
```

我们兼容了，通过微信和一些 CDN 厂商获取用户真实 IP 的头信息，我们优先获取从微信透传过来的用户的真实 IP，然后再是各 CDN 厂商的用户真实 IP 头，最后才是标准的真实 IP 头。当然，我们不能保证是否是伪造的。

优先顺序：X-Forwarded-For-Pound（微信） > Ali-Cdn-Real-Ip（阿里云 CDN） > Cdn-Src-Ip（网宿） > X-Cdn-Src-Ip（网宿） > X-Original-Forwarded-For（天翼云） > X-Forwarded-For > X-Real-Ip > Proxy-Client-IP > WL-Proxy-Client-IP > Real-ClientIP > remote addr


是否是 Ajax 请求：

```java
RequestUtils.isAjaxRequest(request);
```


是否是移动设备请求：

```java
RequestUtils.isMobile(request);
```


设置缓存：

```java
ResponseUtils.httpCache(response, 5); // 缓存 5 秒
ResponseUtils.httpCache(response, new Date()); // 缓存到指定的时间点
```