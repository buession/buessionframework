# buession-io 参考手册


封装了对文件的操作


---


### 安装

```xml
<dependency>
    <groupId>com.buession</groupId>
    <artifactId>buession-io</artifactId>
    <version>x.x.x</version>
</dependency>
```


该模块二次封装了 java `java.io.File` 和 `java.nio.file.Files` 类，在此基础上扩展了大量的实用方法，如：文件读写、获取文件 MD5 和 SHA1 值，获取文件 MIME，设置文件所属用户和用户组，简化了我们在应用开发过程中对文件的操作。


### 读取文件

```java
import com.buession.io.file.File;

File file = new File("/tmp/debug.txt");

byte[] result = file.read();
```


### 写文件

```java
import com.buession.io.file.File;

File file = new File("/tmp/debug.txt");

file.write("Buession");
file.write("Buession".getBytes());
file.write("Buession", true); // 追加写
```


### 获取文件 MD5、SHA-1值

```java
import com.buession.io.file.File;

File file = new File("/tmp/debug.txt");

String md5 = file.getMd5(); // 获取文件 MD5
String sha1 = file.getSha1(); // 获取文件 SHA-1
```


### 获取文件 MD5、SHA-1 值

```java
import com.buession.io.file.File;
import com.buession.io.MimeType;

File file = new File("/tmp/debug.txt");

MimeType result = file.getMimeType();
```


### 设置文件权限

```java
import com.buession.io.file.Files;

Files.chmod("/tmp/debug.txt", 0777);
```


### 设置文件用户组

```java
import com.buession.io.file.Files;

Files.chgrp("/tmp/debug.txt", "root");
```


### 设置文件用户

```java
import com.buession.io.file.Files;

Files.chown("/tmp/debug.txt", "root");
```


### 注解

注解 `com.buession.io.json.annotation.MimeTypeString` 可以将类型为 `com.buession.io.MimeType` 的字段序列化为字符串和将字符串反序列化为 `com.buession.io.MimeType`，该功能是基于 jackson 实现的。

```java
import com.buession.io.json.annotation.MimeTypeString;

class File {

    @MimeTypeString
    private MimeType mime;

}
```


### [API 参考手册>>](https://javadoc.io/static/com.buession/buession-io/2.2.0/)