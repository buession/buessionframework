# buession-web 参考手册


## Response


我们定义了 restful 返回数据类型。

```java
public class Response<E> {

	/**
	 * 状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 */
	private boolean state;

	/**
	 * 错误状态码
	 */
	private int code;

	/**
	 * 提示或错误消息
	 */
	private String message;

	/**
	 * 数据
	 */
	private E data;

	/**
	 * 分页对象
	 */
	private Pagination pagination;

	/**
	 * 构造函数
	 */
	public Response();

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 */
	public Response(boolean state);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 */
	public Response(boolean state, int code);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 * @param message
	 * 		提示或错误消息
	 */
	public Response(boolean state, int code, String message);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param message
	 * 		提示或错误消息
	 */
	public Response(boolean state, String message);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 */
	public Response(boolean state, int code, String message, E data);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 */
	public Response(boolean state, String message, E data);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 * @param pagination
	 * 		分页对象
	 */
	public Response(boolean state, int code, String message, E data, Pagination pagination);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 * @param pagination
	 * 		分页对象
	 */
	@Deprecated
	public Response(boolean state, int code, String message, E data, com.buession.core.Pagination<E> pagination) ;

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 * @param pagination
	 * 		分页对象
	 */
	public Response(boolean state, String message, E data, Pagination pagination);

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 * @param pagination
	 * 		分页对象
	 */
	@Deprecated
	public Response(boolean state, String message, E data, com.buession.core.Pagination<E> pagination);

	/**
	 * 返回状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 *
	 * @return 状态
	 */
	public boolean isState();

	/**
	 * 返回状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 *
	 * @return 状态
	 */
	public boolean getState();

	/**
	 * 设置状态
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 */
	public void setState(boolean state);

	/**
	 * 返回错误码
	 *
	 * @return 错误码
	 */
	public int getCode();

	/**
	 * 设置错误码
	 *
	 * @param code
	 * 		错误码
	 */
	public void setCode(int code);

	/**
	 * 返回提示或错误消息
	 *
	 * @return 提示或错误消息
	 */
	public String getMessage();

	/**
	 * 设置提示或错误消息
	 *
	 * @param message
	 * 		提示或错误消息
	 */
	public void setMessage(String message);

	/**
	 * 返回数据
	 *
	 * @return 数据
	 */
	public E getData();

	/**
	 * 设置数据
	 *
	 * @param data
	 * 		数据
	 */
	public void setData(E data);

	/**
	 * 返回分页对象
	 *
	 * @return 分页对象
	 */
	public Pagination getPagination();

	/**
	 * 设置分页对象
	 *
	 * @param pagination
	 * 		分页对象
	 */
	public void setPagination(Pagination pagination);

}
```