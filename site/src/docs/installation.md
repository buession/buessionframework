# 安装及使用


### Maven 中央仓库搜索
* [https://mvnrepository.com/search?q=com.buession](https://mvnrepository.com/search?q=com.buession)
* [https://central.sonatype.com/search?q=g:com.buession](https://central.sonatype.com/search?q=g:com.buession)

### 手动编译
```shell
git clone https://github.com/buession/buessionframework
cd buessionframework/buession-parent && mvn clean install
```

### Maven
```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-xxx</artifactId>
    <version>x.x.x</version>
</dependency>
```

### Gradle
```gradle
compile group: 'com.buession', name: 'buession-xxx', version: 'x.x.x'
```

其中，artifactId 中的 xxx 表示对应的子模块；version 中的 x.x.x 代表版本号，根据需要使用特定版本，建议使用 maven 仓库中已构建好的最新版本[![Maven Central](https://img.shields.io/maven-central/v/com.buession/buession-core.svg)](https://search.maven.org/search?q=g:com.buession)的包。