# buession-web 参考手册


## 过滤器


我们封装了一些使用的过滤器，简化了您的开发或者应用运行的跟踪。

servlet 包位于 `com.buession.web.servlet.filter`，webflux 包位于 `com.buession.web.reactive.filter`，均有同样类名的过滤器类。


### 过滤器

|  过滤器                | 说明                                                                               |
|  ----                  | ----                                                                             |
| PoweredByFilter        | Powered By 过滤器                                                                  |
| PrintUrlFilter         | 打印当前请求 URL 过滤器                                                             |
| ResponseHeaderFilter   | 响应头过滤器，设置响应头                                                             |
| ResponseHeadersFilter  | 响应头过滤器，批量设置响应头                                                          |
| ServerInfoFilter       | Server 信息过滤器，通过响应头的形式，输出当前服务器名称，可以用于集群环境定位出现问题的节点   |