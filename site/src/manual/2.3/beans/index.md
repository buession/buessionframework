# buession-beans 参考手册


该类库提供了基于 commons-beanutils 和 cglib（spring-core 包中，名称空间：org.springframework.cglib.beans） 的 bean 工具的二次封装。包括了属性拷贝和属性映射，以及对象属性转换为 Map。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-beans</artifactId>
    <version>x.x.x</version>
</dependency>
```


### 属性拷贝

使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 和 target 的属性做映射，实现同名拷贝。

```java
import com.buession.beans.BeanUtils;

BeanUtils.copyProperties(target, source)
```

* 注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。


我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性

```java
import com.buession.beans.BeanUtils;
import org.springframework.cglib.core.Converter;

BeanUtils.copyProperties(target, source, new Converter() {

	@Override
	public Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){
		if(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){
			if(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){
				return sourceFieldValue;
			}
		}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){
			return sourceFieldValue;
		}

		return null;
	}

});
```

### 属性映射

使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 转换为驼峰格式后和 target 的属性做映射，实现拷贝，这是 populate 和 copyProperties 的唯一区别。

```java
import com.buession.beans.BeanUtils;

BeanUtils.populate(target, source)
```

* 注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。


我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性

```java
import com.buession.beans.BeanUtils;
import org.springframework.cglib.core.Converter;

BeanUtils.populate(target, source, new Converter() {

	@Override
	public Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){
		if(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){
			if(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){
				return sourceFieldValue;
			}
		}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){
			return sourceFieldValue;
		}

		return null;
	}

});
```

### Bean 转换为 Map

使用此方法，可以实现将一个 bean 对象转换为 Map，bean 的属性作为 Map 的 Key

```java
import com.buession.beans.BeanUtils;

Map<String, Object> result = BeanUtils.toMap(bean)
```


### [API 参考手册>>](https://javadoc.io/doc/com.buession/buession-beans/2.3.0/)