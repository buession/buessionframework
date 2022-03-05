window.ydoc_plugin_search_json = {
  "快速上手": [
    {
      "title": "",
      "content": "",
      "url": "/docs/intro.html",
      "children": [
        {
          "title": "Buession Framework 框架是什么？",
          "url": "/docs/intro.html#buession-framework-框架是什么？",
          "content": "Buession Framework 框架是什么？Buession Framework 框架不是重复造车轮，它不是其它框架的替代品。它是基于作者在日常工作中遇到的常见需求或通用需求二次封装；它是作者其它同类开源框架的一种兼容性的上层封装，简化框架切换带来的成本。常用功能的封装content其它同类开源框架的上层封装content"
        }
      ]
    },
    {
      "title": "",
      "content": "",
      "url": "/docs/license.html",
      "children": []
    },
    {
      "title": "",
      "content": "",
      "url": "/docs/module.html",
      "children": [
        {
          "title": "buession-aop",
          "url": "/docs/module.html#buession-aop",
          "content": "buession-aopSpring Bean 工具类封装、转换\n"
        },
        {
          "title": "buession-core",
          "url": "/docs/module.html#buession-core",
          "content": "buession-core一些继承 apache lang3、apache collections4 的对字符串、集合、List、Map、Class、Object、Enum、Number等工具类封装和扩展\n汉字拼音工具类\n版本对比工具类\n数学计算\nIP 地址工具类\n进制转换\n对象序列化和反序列化，支持二进制、FastJson、Gson、Jackson\n数据合法性验证类\n带 code 和 message 消息消息对象，通过 properties 的形式注入\n日期对象类\nID 生成器\nManager 层注解\n"
        },
        {
          "title": "buession-cron",
          "url": "/docs/module.html#buession-cron",
          "content": "buession-cronS对 org.quartz-scheduler:quartz 的二次封装\n"
        },
        {
          "title": "buession-dao",
          "url": "/docs/module.html#buession-dao",
          "content": "buession-dao对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装\n从代码层面上支持数据库一主多从实现读写分离，insert、update、delete 操作主库，select 操作从库\n"
        },
        {
          "title": "buession-geoip",
          "url": "/docs/module.html#buession-geoip",
          "content": "buession-geoip对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息\n"
        },
        {
          "title": "buession-httpclient",
          "url": "/docs/module.html#buession-httpclient",
          "content": "buession-httpclient对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节\n屏蔽了对 post form、post json 等等的技术细节\n"
        },
        {
          "title": "buession-io",
          "url": "/docs/module.html#buession-io",
          "content": "buession-io封装了对文件的操作\n"
        },
        {
          "title": "buession-jdbc",
          "url": "/docs/module.html#buession-jdbc",
          "content": "buession-jdbcJDBC 通用 POJO 类定义\n对 Hikari、Dbcp2、Druid 等配置和数据源的封装\n"
        },
        {
          "title": "buession-json",
          "url": "/docs/module.html#buession-json",
          "content": "buession-json主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现\n"
        },
        {
          "title": "buession-lang",
          "url": "/docs/module.html#buession-lang",
          "content": "buession-lang常用枚举（如：状态-Status、性别-Gender 等）的定义\n常用 POJO 类（如：地理位置-Geo、Key Value-KeyValue 等）的定义\n"
        },
        {
          "title": "buession-net",
          "url": "/docs/module.html#buession-net",
          "content": "buession-net网络相关工具类\n"
        },
        {
          "title": "buession-oss",
          "url": "/docs/module.html#buession-oss",
          "content": "buession-oss对不同对象存储产品接口的封装\n"
        },
        {
          "title": "buession-redis",
          "url": "/docs/module.html#buession-redis",
          "content": "buession-redisRedis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 命令保持一致\n对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象\n"
        },
        {
          "title": "buession-session",
          "url": "/docs/module.html#buession-session",
          "content": "buession-session...\n"
        },
        {
          "title": "buession-thesaurus",
          "url": "/docs/module.html#buession-thesaurus",
          "content": "buession-thesaurus对词库的解析，目前仅支持搜狗词条\n"
        },
        {
          "title": "buession-velocity",
          "url": "/docs/module.html#buession-velocity",
          "content": "buession-velocityspring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity\n"
        },
        {
          "title": "buession-web",
          "url": "/docs/module.html#buession-web",
          "content": "buession-webweb 相关的功能封装，支持 servlet 和 reactive\n封装了一些常用注解，简化了业务层方面的代码实现\n封装了一些常用 filter\n"
        }
      ]
    },
    {
      "title": "",
      "content": "",
      "url": "/docs/version.html",
      "children": []
    },
    {
      "title": "",
      "content": "",
      "url": "/docs/installation.html",
      "children": [
        {
          "title": "Maven 中央仓库搜索",
          "url": "/docs/installation.html#maven-中央仓库搜索",
          "content": "Maven 中央仓库搜索https://mvnrepository.com/search?q=com.buession\nhttps://search.maven.org/search?q=g:com.buession\n"
        },
        {
          "title": "源码下载",
          "url": "/docs/installation.html#源码下载",
          "content": "源码下载git clone https://github.com/buession/buessionframeworkcd buessionframework/buession-parent && mvn install\n"
        },
        {
          "title": "手动编译",
          "url": "/docs/installation.html#手动编译",
          "content": "手动编译git clone https://github.com/buession/buessionframeworkcd buessionframework/buession-parent && mvn install\n"
        },
        {
          "title": "Maven",
          "url": "/docs/installation.html#maven",
          "content": "Maven    com.buession\n    buession-xxx\n    x.x.x\n\n"
        },
        {
          "title": "Gradle",
          "url": "/docs/installation.html#gradle",
          "content": "Gradlecompile group: 'com.buession', name: 'buession-xxx', version: 'x.x.x'其中，artifactId 中的 xxx 表示对应的子模块；version 中的 x.x.x 代表版本号，根据需要使用特定版本，建议使用最新版本。"
        }
      ]
    },
    {
      "title": "",
      "content": "",
      "url": "/docs/requirement.html",
      "children": [
        {
          "title": "环境要求",
          "url": "/docs/requirement.html#环境要求",
          "content": "环境要求JDKJDK 8+构建工具\n\n构建工具\n版本\n\n\n\n\nMaven\n3.5+\n\n\nGradle\n6.x+，推荐 6.3 及以上版本\n\n\nServlet 容器支持 servlet 3.1+，推荐使用 servlet 4.0 及以上版本。"
        }
      ]
    }
  ]
}