Configurer# buession-core 参考手册


## 配置器


使用配置参数对对象进行配置。


接口规范。

```java
@FunctionalInterface
public interface Configurer<T, C> {

	/**
	 * 使用配置参数 config 对对象 object 进行配置
	 *
	 * @param object
	 * 		配置对象
	 * @param config
	 * 		配置参数
	 */
	void configure(T object, C config);

}
```

示例：

```java
public class DefaultConfigurer implements Configurer<User, Map<String, Object>> {

	@Override
	public void configure(final User user, final Map<String, Object> configs) {
		user.setUsername(configs.get("name"));
	}

}
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.3.0/com/buession/core/Configurer.html)