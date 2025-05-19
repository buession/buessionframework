# buession-httpclient 参考手册


## 方法


`buession-httpclient` 实现了异步 HTTP 请求。


#### 示例：

```java
import com.buession.httpclient.HttpAsyncClient;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;

ApacheHttpAsyncClient httpClient = new ApacheHttpAsyncClient();

httpClient.get("https://www.baidu.com", new Callback() {

	/**
	 * 当 HTTP 请求成功完成并且服务器返回响应时调用此方法
	 *
	 * @param response
	 * 		HTTP 响应
	 */
	@Override
	public void completed(Response response){
		for(Header header : response.getHeaders()){
			System.out.println(header.toString());
		}
	}

	/**
	 * 当 HTTP 请求由于异常而失败时调用此方法
	 *
	 * @param ex
	 * 		导致失败的异常。
	 */
	@Override
	public void failed(Exception ex){
		System.out.println("failed: " + ex.getMessage());
	}

	/**
	 * 当 HTTP 请求在完成之前被取消时调用此方法
	 */
	@Override
	public void cancelled(){
		System.out.println("cancelled");
	}

});
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-httpclient/2.3.0/com/buession/httpclient/HttpAsyncClient.html)