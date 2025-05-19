# buession-thesaurus 参考手册


对词库的解析，目前仅支持搜狗词条。


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-thesaurus</artifactId>
    <version>x.x.x</version>
</dependency>
```

您可以通过该库解析搜狗拼音的词条库，包括词条、拼音信息。


```java
import com.buession.thesaurus.SogouParser;
import com.buession.thesaurus.Parser;
import com.buession.thesaurus.core.Word;
import java.util.Set;

Parser parser = new SogouParser();

Set<Word> words parser.parse("搜谱拼音词条文件路径");
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-thesaurus/3.0.0/)