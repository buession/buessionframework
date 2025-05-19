# buession-dao 参考手册


## MyBatis

Buession Framework 扩展 MyBatis 的文档。


### 读写分离

要从代码层面实现读写分离，必须继承 `AbstractMyBatisDao`；且存在 bean 名为 `masterSqlSessionTemplate`、`slaveSqlSessionTemplates` 的 bean 实例。masterSqlSessionTemplate 操作主库，实现插入、更新、删除操作；slaveSqlSessionTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 `getSlaveSqlSessionTemplate()` 在所有的 slave templates 中随机返回一个 slave SqlSessionTemplate bean 实例。当然，您也可以通过 `getSlaveSqlSessionTemplate(final int index)` 指定索引的 slave SqlSessionTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave SqlSessionTemplate bean 实例列表，将会返回 master SqlSessionTemplate bean 实例，buession framework 屏蔽了这些技术细节。


### Mybatis 约定

1. 如果集成 `AbstractMyBatisDao` 类，必须重写方法 `getStatement()`，通过此方法返回每个 Mapper namespace


```java
namespace com.buession.dao.test.dao;

public class UserDaoImpl extends AbstractMyBatisDao<Integer, User> {

	@Override
	protected String getStatement(){
		return "com.buession.dao.test.dao.UserMapper";
	}

}
```

```xml
<!DOCTYPE mapper PUBLIC "-//mybatis.org/DTD Mapper 3.0" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.buession.dao.test.dao.UserMapper">
</mapper>
```

2. Mapper 的 SQL ID 和方法名保持一致


|  SQL ID            | 说明                                                             | 返回值                   |
|  ----              | ----                                                            | ----                    |
| insert             | 插入数据                                                          | 影响的行数               |
| batchInsert        | 批量插入数据，默认循环插入；您可以重写该方法实现 SQL 批量插入            | 每次插入影响的行数列表    |
| replace            | 替换数据，即：REPLACE 语句                                         | 影响的行数               |
| batchReplace       | 批量替换数据，即：REPLACE 语句                                      | 每次替换数据影响的行数列表 |
| update             | 更新数据                                                          | 更新条数                 |
| updateByPrimary    | 根据主键更新数据，注：主键参数值是会覆盖实体类主键参数对应的类属性的值     | 更新条数                 |
| getByPrimary       | 根据主键查询数据，SQL 层面需要保证最多只会返回一条数据                  | 一条数据结果              |
| selectOne          | （根据条件）获取一条数据，SQL 层面需要保证最多只会返回一条数据           | 一条数据结果              |
| select             | 查询数据                                                          | 数据结果列表              |
| getAll             | 查询所有数据                                                       | 数据结果列表              |
| count              | 获取记录数                                                        | 记录数                    |
| deleteByPrimary    | 根据主键删除数据                                                   | 影响条数                  |
| delete             | 删除数据                                                          | 影响条数                  |
| clear              | 清除数据                                                          | 影响条数                  |
| truncate           | 截断数据                                                          | 影响条数                  |

* 注：要实现分页，必须实现 count，且和 select 的查询条件必须一致。因为，在分页方法中，首先会执行 count ，查询指定条件的记录数；如果记录数大于 0 时，才会执行 select 查询数据。在后续的开发中，我们将会使用拦截器实现。
  以上 SQL ID，只是一种约定，具体会呈现一种什么样的效果，还是完全屈居于您的 SQL 语句。


### Mybatis 类型处理器

MyBatis 自身提供大量优秀的类型处理器 `TypeHandler`，但任然不足。我们在此基础上扩展了一些 `TypeHandler`。名称空间为 `org.apache.ibatis.type`，不是 `com.buession.dao`。


|  TypeHandler   | 说明  |
|  ----  | ----  |
| DefaultEnumTypeHandler  | 默认 Enum 类型处理器，将值直接转换为枚举字段 |
| IgnoreCaseEnumTypeHandler  | 忽略大小写 Enum 类型处理器，将值忽略大小写转换为枚举字段 |
| DefaultJsonTypeHandler  | JSON 处理器，将 JSON 格式的字符串值和类型 &lt;E&gt; 进行转换 |
| DefaultSetEnumTypeHandler  | 默认 Enum 型 Set 类型处理器，将值直接转换为枚举字段作为 Set 的元素 |
| IgnoreCaseSetEnumTypeHandler  | 忽略大小写 Enum 型 Set 类型处理器，将值忽略大小写转换为枚举字段作为 Set 的元素 |
| DefaultSetTypeHandler  | 默认 Set 类型处理器，将值以 "," 拆分转换为 Set&lt;String&gt; |


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-dao/2.1.0/com/buession/dao/AbstractMyBatisDao.html)