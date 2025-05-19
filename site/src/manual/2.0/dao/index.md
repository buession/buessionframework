# buession-dao 参考手册


对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装。从代码层面实现了数据库的读写分离，insert、update、delete 操作主库，select 操作从库。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-dao</artifactId>
    <version>x.x.x</version>
</dependency>
```

我们咋众多项目中，基本有常见的重复的对数据库的 CURD 操作，比如：根据主键查询数据、根据主键删除数据、获取一条记录。MyBatis 是一款优秀的持久层框架，应用广泛。MongoDB 是一款优秀的文档数据库。我自己根据从业多年的经验，从实际场合出发，将在业务层对数据库的常用操作进行了封装。对关系型数据库基于 MyBatis 二次封装，对 MongoDB 基于 spring-data-mongodb；在未来也许会考虑，增加 jpa 和 JdbcTemplate 对关系型数据库的二次封装。

同时，我们在代码层面实现了数据库的读写分离。

我们没有改变 MyBatis 和 spring-data-mongodb 的任何底层逻辑，本质就是 MyBaits 和 spring-data-mongodb；我们唯一做了的就是，定义和是了大家在应用程序中常用的方法，让您不在重复去编写该部分代码；以及在代码层面实现了数据的读写分离。


### Dao 接口

接口定义，可见：[https://javadoc.io/static/com.buession/buession-dao/2.0.2/com/buession/dao/Dao.html](https://javadoc.io/static/com.buession/buession-dao/2.0.2/com/buession/dao/Dao.html)

```java
public interface Dao<P, E> {
}
```

* `P`：主键类型
* `E`：实体类


分页对象 `com.buession.dao.Pagination` 继承自 `com.buession.core.Pagination`，增加了偏移量属性 `offset`。

条件为 `Map<String, Object>` 类型，允许为 null。

排序为 `Map<String, com.buession.lang.Order>` 类型，允许为 null。


#### [MyBatis](mybatis.md)

Buession Framework 扩展 MyBatis 的文档。


#### [MongoDB](mongodb.md)

Buession Framework 扩展 spring-data-mongodb 的文档。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-dao/2.0.2/)