# buession-core 参考手册


## 编码器


目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中。


我们在当前现代应用中，通常会在业务上定义业务错误信息。且我们返回给前端的可能是更模糊或通用的、人为可读性更强的错误信息，而且可能两种不同原因引起的错误，但是我们在返回给前端时的错误信息是一模一样的，这时我们就需要更精确的标识来定义错误，通常为错误码。

此时，我们在应用中，通常会定义错误码和错误信息的映射关系。我们可用的方法大致是，第一代码里面写死；第二，定义错误枚举或者常量。无论哪种方式的都与代码高度耦合，可读性和可维护性都差。

此时此刻，您可以通过 buession framework 的注解 @Message 来简化和解耦您的错误信息配置和代码。在传统 springmvc 应用中，您可以通过 `context:property-placeholder` 或者 `util:properties` 标签将错误信息 properties 配置文件加载到当前应用环境中。

```properties
USER_NOT_FOUND.code = 10404
USER_NOT_FOUND.message = 用户不存在

USER_LOGIN_FAILURE.code = 10405
USER_LOGIN_FAILURE.message = 登录失败
```

```xml
<context:property-placeholder location="classpath:error_message.properties"/>

<util:properties location="classpath:error_message.properties" local-override="true"/>
```


```java
import com.buession.core.codec.Message;
import com.buession.core.codec.MessageObject;

public UserServiceImpl implements UserService {

	@Message("USER_NOT_FOUND")
	private MessageObject userNotFound;

	@Override
	public User update(User user) throws Exception{
		User dbUser = get(user.getId());

		if(dbUser == null){
			throw new Exception(userNotFound.getMessage() + "(code: " + userNotFound.getCode() + ")");
			// 用户不存在(code: 10404)
		}

	}

}
```


您可以自定义错误代码和错误消息的 key，默认分别为：code、message。此时您需要在注解 `@Message` 上显示指定错误代码和错误消息的 key。

```properties
USER_NOT_FOUND.errorCode = 10404
USER_NOT_FOUND.errorMessage = 用户不存在

USER_LOGIN_FAILURE.errorCode = 10405
USER_LOGIN_FAILURE.errorMessage = 登录失败
```

```java
import com.buession.core.codec.Message;
import com.buession.core.codec.MessageObject;

public UserServiceImpl implements UserService {

	@Message(value = "USER_NOT_FOUND", code = "errorCode", message = "errorMessage")
	private MessageObject userNotFound;

	@Override
	public User update(User user) throws Exception{
		User dbUser = get(user.getId());

		if(dbUser == null){
			throw new Exception(userNotFound.getMessage() + "(code: " + userNotFound.getCode() + ")");
			// 用户不存在(code: 10404)
		}

	}

}
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-core/2.3.0/com/buession/core/codec/package-summary.html)