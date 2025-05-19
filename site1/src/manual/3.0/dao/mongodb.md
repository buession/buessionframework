# buession-dao 参考手册


## MongoDB

Buession Framework 扩展 spring-data-mongodb 的文档。


### 读写分离

要从代码层面实现读写分离，必须继承 `AbstractMongoDBDao`；且存在 bean 名为 `masterMongoTemplate`、`slaveMongoTemplates` 的 bean 实例。masterMongoTemplate 操作主库，实现插入、更新、删除操作；slaveMongoTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 `getSlaveMongoTemplate()` 在所有的 slave templates 中随机返回一个 MongoTemplate bean 实例。当然，您也可以通过 `getSlaveMongoTemplate(final int index)` 指定索引的 slave MongoTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave MongoTemplate bean 实例列表，将会返回 master MongoTemplate bean 实例，buession framework 屏蔽了这些技术细节。

`AbstractMongoDBDao` 的 `replace` 执行的也是 `insert`。
在对 MongoDB 的操作条件中 value 可以为 `com.buession.dao.MongoOperation`，可以通过该类的方法控制条件等于值、大于值、小于值、LIKE值 等等运算。


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-dao/3.0.0/com/buession/dao/AbstractMongoDBDao.html)