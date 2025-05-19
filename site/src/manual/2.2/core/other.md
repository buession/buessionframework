# buession-core 参考手册


## 其它


通用的接口定义，框架自身类，以及其它杂项。


### 框架自身工具

获取 Buession Framework 版本：

```java
import com.buession.core.Framework;
import com.buession.core.BuesssionFrameworkVersion;

BuesssionFrameworkVersion.getVersion(); // 2.2.0
Framework.VERSION; // 2.2.0
```

获取 Buession Framework 框架名称：

```java
import com.buession.core.Framework;

Framework.NAME; // "Buession"
```


### 命令执行器

命令执行器接口：

```java
/**
 * 命令执行器
 *
 * @param <C>
 * 		命令上下文
 * @param <R>
 * 		命令执行返回值
 */
@FunctionalInterface
public interface Executor<C, R> {

	/**
	 * 命令执行
	 *
	 * @param context
	 * 		命令执行器上下文
	 *
	 * @return 命令执行返回值，R 类型的实例
	 */
	R execute(C context);

}
```

您可以通过，该接口执行一个命令上下文的方法，并返回一个值。该方法有些类似代理模式的代理类。


### 销毁接口

功能类似 `java.io.Closeable`。

```java
public interface Destroyable {

	/**
	 * 销毁相关资源
	 *
	 * @throws IOException
	 * 		IO 错误时抛出
	 */
	void destroy() throws IOException;

}
```

### Rawable

原始的，约定实现该接口的类，必须返回原始字节数组。

```java
public interface Rawable {

	/**
	 * 返回原始的字节数组
	 *
	 * @return 原始的字节数组
	 */
	byte[] getRaw();

}
```

### 名称节点

名称节点，约定实现该接口的类应该返回一个名称

```java
public interface NamedNode {

	/**
	 * 返回节点名称
	 *
	 * @return 节点名称
	 */
	@Nullable
	String getName();

}
```

### 分页

`com.buession.core.Pagination` 为一个分页 POJO 类，包括了当前页码、每页大小、上一页、下一页、总页数、总记录数、数据列表。能够根据设定的其中一个值，计算另外的值。