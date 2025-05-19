# buession-core 参考手册


## 定制器


使用源对象对目标对象进行定制。


接口规范。

```java
@FunctionalInterface
public interface Customizer<S, T> {

	/**
	 * 定制
	 *
	 * @param source
	 * 		源实例
	 * @param target
	 * 		待定制实例
	 */
	void customize(S source, T target);

}
```

示例：

```java
public class DefaultCustomizer implements Customizer<UserModel, UserVo> {

	@Override
	public void customize(final UserModel userModel, final UserVo userVo) {
		userVo.setUsername(userModel.getUsername());
	}

}
```

### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.3.0/com/buession/core/Customizer.html)