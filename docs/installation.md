### 安装及使用

Maven 中央仓库搜索

- [https://search.maven.org/search?q=g:com.buession](https://search.maven.org/search?q=g:com.buession)
- [https://mvnrepository.com/search?q=com.buession](https://mvnrepository.com/search?q=com.buession)

**Maven**

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-xxx</artifactId>
    <version>x.x.x</version>
</dependency>
```

**Gradle**

```gradle
compile group: 'com.buession', name: 'buession-xxx', version: 'x.x.x'
```

其中，artifactId 中的 xxx 表示对应的子模块；version 中的 x.x.x 代表版本号，根据需要使用特定版本，建议使用最新版本。