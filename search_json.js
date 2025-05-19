window.ydoc_plugin_search_json = {
  "文档": [
    {
      "title": "快速入门",
      "content": "TIP\n\n官方指南假设您已了解\"JAVA\"方面的相关知识。\n\nBuession Framework 它是日常工作中常见的通用技术需求二次封装，提供了众多常用的类库、方法、注解；同时基于 springfrawork、jsckson、jedis、apache httpcomponents、okhttp3 等等众多的优秀的三方工具的标准化的、统一的类库的上层封装，简化框架切换带来的成本。更多介绍开源查阅框架介绍。Buession Framework 还在引用三方类库时，确保了版本的一致性，避免在不用三方类库引用的同一个三方类库版本不一致的情况。您可以根据本文档中的示例，快速熟悉 Buession Framework 的使用方法。",
      "url": "/docs/quickstart.html",
      "children": [
        {
          "title": "下一步可做什么？",
          "url": "/docs/quickstart.html#下一步可做什么？",
          "content": "下一步可做什么？您对 Buession Framework 大致了解后，您接下来可以做以下事情：了解兼容性：了解 Buession Framework 的兼容性\n安装：安装/引用 Buession Framework\n使用：开始使用 Buession Framework 功能\n"
        }
      ]
    },
    {
      "title": "框架介绍",
      "content": "",
      "url": "/docs/intro.html",
      "children": [
        {
          "title": "Buession Framework 框架是什么？",
          "url": "/docs/intro.html#buession-framework-框架是什么？",
          "content": "Buession Framework 框架是什么？Buession Framework 框架不是重复造车轮，它不是其它框架的替代品。它是基于各开源框架的日常工作中常见的通用技术需求二次封装本地化的数据验证，如：QQ、电话号码、身份证号码、邮政编码\n常用 DAO 层操作，如：插入、替换、根据主键获取记录、获取单条记录、获取多条记录\n应用层实现数据库读写分离\nredis 操作兼容原生 API 的前提下，同时实现了 redis 中的值反序列化成对象\n词库解析（目前仅支持搜狗词库）\n使用 WEB 功能，如：响应头注解、缓存头注解、兼容性获取用户端真实 IP、获取用户真实 IP 注解\n替代 springfamework 5，支持 apache velocity\n基于 maxmind geoip 的 IP 信息解析\n基于标准的 HTTP 请求方法的 HttpClient\n文件操作，如：写文件、设置文件所属用户或组、文件 MimeType 解析\n... ...它是同类开源框架的一种兼容性的上层封装，简化框架切换带来的成本摒弃直接使用原生类库，带来的大量的代码修改，如：HttpClient 支持 apache httpcomponents 和 okhttp3，只需要修改 HttpClient 初始化类，即可实现 HTTP 库的切换\n... ..."
        }
      ]
    },
    {
      "title": "开源协议",
      "content": "                             Apache License                       Version 2.0, January 2004\n                    http://www.apache.org/licenses/\nTERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\nDefinitions.\n\"License\" shall mean the terms and conditions for use, reproduction,\nand distribution as defined by Sections 1 through 9 of this document.\n\"Licensor\" shall mean the copyright owner or entity authorized by\nthe copyright owner that is granting the License.\n\"Legal Entity\" shall mean the union of the acting entity and all\nother entities that control, are controlled by, or are under common\ncontrol with that entity. For the purposes of this definition,\n\"control\" means (i) the power, direct or indirect, to cause the\ndirection or management of such entity, whether by contract or\notherwise, or (ii) ownership of fifty percent (50%) or more of the\noutstanding shares, or (iii) beneficial ownership of such entity.\n\"You\" (or \"Your\") shall mean an individual or Legal Entity\nexercising permissions granted by this License.\n\"Source\" form shall mean the preferred form for making modifications,\nincluding but not limited to software source code, documentation\nsource, and configuration files.\n\"Object\" form shall mean any form resulting from mechanical\ntransformation or translation of a Source form, including but\nnot limited to compiled object code, generated documentation,\nand conversions to other media types.\n\"Work\" shall mean the work of authorship, whether in Source or\nObject form, made available under the License, as indicated by a\ncopyright notice that is included in or attached to the work\n(an example is provided in the Appendix below).\n\"Derivative Works\" shall mean any work, whether in Source or Object\nform, that is based on (or derived from) the Work and for which the\neditorial revisions, annotations, elaborations, or other modifications\nrepresent, as a whole, an original work of authorship. For the purposes\nof this License, Derivative Works shall not include works that remain\nseparable from, or merely link (or bind by name) to the interfaces of,\nthe Work and Derivative Works thereof.\n\"Contribution\" shall mean any work of authorship, including\nthe original version of the Work and any modifications or additions\nto that Work or Derivative Works thereof, that is intentionally\nsubmitted to Licensor for inclusion in the Work by the copyright owner\nor by an individual or Legal Entity authorized to submit on behalf of\nthe copyright owner. For the purposes of this definition, \"submitted\"\nmeans any form of electronic, verbal, or written communication sent\nto the Licensor or its representatives, including but not limited to\ncommunication on electronic mailing lists, source code control systems,\nand issue tracking systems that are managed by, or on behalf of, the\nLicensor for the purpose of discussing and improving the Work, but\nexcluding communication that is conspicuously marked or otherwise\ndesignated in writing by the copyright owner as \"Not a Contribution.\"\n\"Contributor\" shall mean Licensor and any individual or Legal Entity\non behalf of whom a Contribution has been received by Licensor and\nsubsequently incorporated within the Work.\n\n\nGrant of Copyright License. Subject to the terms and conditions of\nthis License, each Contributor hereby grants to You a perpetual,\nworldwide, non-exclusive, no-charge, royalty-free, irrevocable\ncopyright license to reproduce, prepare Derivative Works of,\npublicly display, publicly perform, sublicense, and distribute the\nWork and such Derivative Works in Source or Object form.\n\n\nGrant of Patent License. Subject to the terms and conditions of\nthis License, each Contributor hereby grants to You a perpetual,\nworldwide, non-exclusive, no-charge, royalty-free, irrevocable\n(except as stated in this section) patent license to make, have made,\nuse, offer to sell, sell, import, and otherwise transfer the Work,\nwhere such license applies only to those patent claims licensable\nby such Contributor that are necessarily infringed by their\nContribution(s) alone or by combination of their Contribution(s)\nwith the Work to which such Contribution(s) was submitted. If You\ninstitute patent litigation against any entity (including a\ncross-claim or counterclaim in a lawsuit) alleging that the Work\nor a Contribution incorporated within the Work constitutes direct\nor contributory patent infringement, then any patent licenses\ngranted to You under this License for that Work shall terminate\nas of the date such litigation is filed.\n\n\nRedistribution. You may reproduce and distribute copies of the\nWork or Derivative Works thereof in any medium, with or without\nmodifications, and in Source or Object form, provided that You\nmeet the following conditions:\n(a) You must give any other recipients of the Work or\nDerivative Works a copy of this License; and\n(b) You must cause any modified files to carry prominent notices\nstating that You changed the files; and\n(c) You must retain, in the Source form of any Derivative Works\nthat You distribute, all copyright, patent, trademark, and\nattribution notices from the Source form of the Work,\nexcluding those notices that do not pertain to any part of\nthe Derivative Works; and\n(d) If the Work includes a \"NOTICE\" text file as part of its\ndistribution, then any Derivative Works that You distribute must\ninclude a readable copy of the attribution notices contained\nwithin such NOTICE file, excluding those notices that do not\npertain to any part of the Derivative Works, in at least one\nof the following places: within a NOTICE text file distributed\nas part of the Derivative Works; within the Source form or\ndocumentation, if provided along with the Derivative Works; or,\nwithin a display generated by the Derivative Works, if and\nwherever such third-party notices normally appear. The contents\nof the NOTICE file are for informational purposes only and\ndo not modify the License. You may add Your own attribution\nnotices within Derivative Works that You distribute, alongside\nor as an addendum to the NOTICE text from the Work, provided\nthat such additional attribution notices cannot be construed\nas modifying the License.\nYou may add Your own copyright statement to Your modifications and\nmay provide additional or different license terms and conditions\nfor use, reproduction, or distribution of Your modifications, or\nfor any such Derivative Works as a whole, provided Your use,\nreproduction, and distribution of the Work otherwise complies with\nthe conditions stated in this License.\n\n\nSubmission of Contributions. Unless You explicitly state otherwise,\nany Contribution intentionally submitted for inclusion in the Work\nby You to the Licensor shall be under the terms and conditions of\nthis License, without any additional terms or conditions.\nNotwithstanding the above, nothing herein shall supersede or modify\nthe terms of any separate license agreement you may have executed\nwith Licensor regarding such Contributions.\n\n\nTrademarks. This License does not grant permission to use the trade\nnames, trademarks, service marks, or product names of the Licensor,\nexcept as required for reasonable and customary use in describing the\norigin of the Work and reproducing the content of the NOTICE file.\n\n\nDisclaimer of Warranty. Unless required by applicable law or\nagreed to in writing, Licensor provides the Work (and each\nContributor provides its Contributions) on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or\nimplied, including, without limitation, any warranties or conditions\nof TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A\nPARTICULAR PURPOSE. You are solely responsible for determining the\nappropriateness of using or redistributing the Work and assume any\nrisks associated with Your exercise of permissions under this License.\n\n\nLimitation of Liability. In no event and under no legal theory,\nwhether in tort (including negligence), contract, or otherwise,\nunless required by applicable law (such as deliberate and grossly\nnegligent acts) or agreed to in writing, shall any Contributor be\nliable to You for damages, including any direct, indirect, special,\nincidental, or consequential damages of any character arising as a\nresult of this License or out of the use or inability to use the\nWork (including but not limited to damages for loss of goodwill,\nwork stoppage, computer failure or malfunction, or any and all\nother commercial damages or losses), even if such Contributor\nhas been advised of the possibility of such damages.\n\n\nAccepting Warranty or Additional Liability. While redistributing\nthe Work or Derivative Works thereof, You may choose to offer,\nand charge a fee for, acceptance of support, warranty, indemnity,\nor other liability obligations and/or rights consistent with this\nLicense. However, in accepting such obligations, You may act only\non Your own behalf and on Your sole responsibility, not on behalf\nof any other Contributor, and only if You agree to indemnify,\ndefend, and hold each Contributor harmless for any liability\nincurred by, or claims asserted against, such Contributor by reason\nof your accepting any such warranty or additional liability.\n\nEND OF TERMS AND CONDITIONSAPPENDIX: How to apply the Apache License to your work.  To apply the Apache License to your work, attach the following  boilerplate notice, with the fields enclosed by brackets \"[]\"\n  replaced with your own identifying information. (Don't include\n  the brackets!)  The text should be enclosed in the appropriate\n  comment syntax for the file format. We also recommend that a\n  file or class name and description of purpose be included on the\n  same \"printed page\" as the copyright notice for easier\n  identification within third-party archives.\nCopyright [yyyy] [name of copyright owner]Licensed under the Apache License, Version 2.0 (the \"License\");you may not use this file except in compliance with the License.\nYou may obtain a copy of the License at   http://www.apache.org/licenses/LICENSE-2.0Unless required by applicable law or agreed to in writing, softwaredistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.",
      "url": "/docs/license.html",
      "children": []
    },
    {
      "title": "模块说明",
      "content": "",
      "url": "/docs/module.html",
      "children": [
        {
          "title": "buession-aop",
          "url": "/docs/module.html#buession-aop",
          "content": "buession-aopAOP 封装，方便实现自定义注解\n"
        },
        {
          "title": "buession-beans",
          "url": "/docs/module.html#buession-beans",
          "content": "buession-beansBean 工具类封\n"
        },
        {
          "title": "buession-core",
          "url": "/docs/module.html#buession-core",
          "content": "buession-core一些继承 apache lang3、apache collections4 的对字符串、集合、List、Map、Class、Object、Enum、Number等工具类封装和扩展\n汉字拼音工具类\n版本对比工具类\n数学计算\nIP 地址工具类\n进制转换\n对象序列化和反序列化，支持二进制、FastJson、Gson、Jackson\n数据合法性验证类\n带 code 和 message 消息消息对象，通过 properties 的形式注入\n日期对象类\nID 生成器\nManager 层注解\n"
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
          "title": "buession-git",
          "url": "/docs/module.html#buession-git",
          "content": "buession-git项目中 Git 信息解析\n"
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
      "title": "版本说明",
      "content": "该项目基于 GNU 版风格定义项目版本，即：主版本号.子版本号.修正版本号。管理策略主版本号，发生变更时，不保证所有的 API 对上一个版本兼容，但保障大部分能兼容；主版本变更，可能涉及类、接口、枚举、方法的删除，或者包名的变更\n子版本号，发生变更时，完全兼容上一个版本，主要会增加一些小的功能或API，底层逻辑的调整调优\n修正版本号，主要用于修复 BUG、优化性能、安全漏洞修复，不会新增、变更、删除已有 API\n三方包兼容性说明当引用的三方包，我们保证尽大可能兼容。但对于 springframework、springboot、springcloud、springsecurity、springdata 等 spring 家族组件，以及 servlet 兼容对应的主版本。",
      "url": "/docs/version.html",
      "children": []
    },
    {
      "title": "安装及使用",
      "content": "",
      "url": "/docs/installation.html",
      "children": [
        {
          "title": "Maven 中央仓库搜索",
          "url": "/docs/installation.html#maven-中央仓库搜索",
          "content": "Maven 中央仓库搜索https://mvnrepository.com/search?q=com.buession\nhttps://central.sonatype.com/search?q=g:com.buession\n"
        },
        {
          "title": "手动编译",
          "url": "/docs/installation.html#手动编译",
          "content": "手动编译git clone https://github.com/buession/buessionframeworkcd buessionframework/buession-parent && mvn clean install\n"
        },
        {
          "title": "Maven",
          "url": "/docs/installation.html#maven",
          "content": "Maven    com.buession\n    buession-xxx\n    x.x.x\n\n"
        },
        {
          "title": "Gradle",
          "url": "/docs/installation.html#gradle",
          "content": "Gradlecompile group: 'com.buession', name: 'buession-xxx', version: 'x.x.x'其中，artifactId 中的 xxx 表示对应的子模块；version 中的 x.x.x 代表版本号，根据需要使用特定版本，建议使用 maven 仓库中已构建好的最新版本的包。"
        }
      ]
    },
    {
      "title": "环境要求",
      "content": "JDKJDK 8+构建工具\n\n构建工具\n版本\n\n\n\n\nMaven\n3.5+\n\n\nGradle\n6.x+，推荐 6.3 及以上版本\n\n\nServlet 容器支持 servlet 3.1+，推荐使用 servlet 4.0 及以上版本。",
      "url": "/docs/requirement.html",
      "children": []
    },
    {
      "title": "下一步计划",
      "content": "",
      "url": "/docs/plan.html",
      "children": []
    }
  ],
  "参考手册": [
    {
      "title": "参考手册简介",
      "content": "Buession Framework 它是日常工作中常见的通用技术需求二次封装，提供了众多常用的类库、方法、注解；同时基于 springfrawork、jsckson、jedis、apache httpcomponents、okhttp3 等等众多的优秀的三方工具的标准化的、统一的类库的上层封装，简化框架切换带来的成本。更多介绍开源查阅框架介绍。本章节将想您讲解，如何使用 Buession Framework，为您提供 Java 应用的最佳实践。",
      "url": "/manual/index.html",
      "children": []
    },
    {
      "title": "参考指南",
      "content": "本文档包含了完整的 Buession Framework 的参考文档。\n\n版本\n手册\n\n\n\n\n3.0.x\nAPI 手册\n\n\n2.3.x\nAPI 手册\n\n\n2.2.x\nAPI 手册\n\n\n2.1.x\nAPI 手册\n\n\n2.0.x\nAPI 手册\n\n\n",
      "url": "/manual/overview.html",
      "children": []
    },
    {
      "title": "API 参考手册",
      "content": "Buession Framework API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-aop\n使用帮助\nAPI 手册\n\n\nbuession-beans\n使用帮助\nAPI 手册\n\n\nbuession-core\n使用帮助\nAPI 手册\n\n\nbuession-cron\n使用帮助\nAPI 手册\n\n\nbuession-dao\n使用帮助\nAPI 手册\n\n\nbuession-geoip\n使用帮助\nAPI 手册\n\n\nbuession-httpclient\n使用帮助\nAPI 手册\n\n\nbuession-io\n使用帮助\nAPI 手册\n\n\nbuession-jdbc\n使用帮助\nAPI 手册\n\n\nbuession-json\n使用帮助\nAPI 手册\n\n\nbuession-lang\n使用帮助\nAPI 手册\n\n\nbuession-net\n使用帮助\nAPI 手册\n\n\nbuession-redis\n使用帮助\nAPI 手册\n\n\nbuession-session\n使用帮助\nAPI 手册\n\n\nbuession-thesaurus\n使用帮助\nAPI 手册\n\n\nbuession-velocity\n使用帮助\nAPI 手册\n\n\nbuession-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/3.0/index.html",
      "children": []
    },
    {
      "title": "buession-aop 参考手册",
      "content": "AOP 封装，方便实现自定义注解",
      "url": "/manual/3.0/aop/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/aop/index.html#安装",
          "content": "安装    com.buession\n    buession-aop\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/aop/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-beans 参考手册",
      "content": "该类库提供了基于 commons-beanutils 和 cglib（spring-core 包中，名称空间：org.springframework.cglib.beans） 的 bean 工具的二次封装。包括了属性拷贝和属性映射，以及对象属性转换为 Map。",
      "url": "/manual/3.0/beans/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/beans/index.html#安装",
          "content": "安装    com.buession\n    buession-beans\n    x.x.x\n\n"
        },
        {
          "title": "属性拷贝",
          "url": "/manual/3.0/beans/index.html#属性拷贝",
          "content": "属性拷贝使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 和 target 的属性做映射，实现同名拷贝。import com.buession.beans.BeanUtils;\nBeanUtils.copyProperties(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.copyProperties(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "属性映射",
          "url": "/manual/3.0/beans/index.html#属性映射",
          "content": "属性映射使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 转换为驼峰格式后和 target 的属性做映射，实现拷贝，这是 populate 和 copyProperties 的唯一区别。import com.buession.beans.BeanUtils;\nBeanUtils.populate(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.populate(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "Bean 转换为 Map",
          "url": "/manual/3.0/beans/index.html#bean-转换为-map",
          "content": "Bean 转换为 Map使用此方法，可以实现将一个 bean 对象转换为 Map，bean 的属性作为 Map 的 Keyimport com.buession.beans.BeanUtils;\nMap result = BeanUtils.toMap(bean)\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/beans/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "该类库为核心包，包括构建器、工具类、数据验证、ID 生成器、转换器、序列化、数学方法、消息注入、Manager 层注解、日期时间类和各通用接口定义。",
      "url": "/manual/3.0/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/core/index.html#安装",
          "content": "安装    com.buession\n    buession-core\n    x.x.x\n\n构建器Map、集合的便捷式构建，减少您的代码行数编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中收集器数组、Map、集合的工具类上下文定义应用上下文的类库、注解转换器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。日期时间日期、时间工具ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。数学函数定义了实用的数学函数序列化和反序列化对象的序列化和反序列化，包括二进制和 JSON。验证器数据验证器及其注解工具类常用通用性工具类其它通用的接口定义，框架自身类异常通用异常的定义"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/builder.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/3.0/core/builder.html#构建器",
          "content": "构建器Map、集合的便捷式构建，减少您的代码行数。您需要往 Map、List 中添加元素的传统写法是：import java.util.ArrayList;import java.util.List;\nimport java.util.HashMap;\nimport java.util.Map;\n\nList list = new ArrayList();\nlist.add(\"A\");\nlist.add(\"B\");\nlist.add(\"C\");\n\nMap map = new HashMap();\nmap.put(\"a\", \"A\");\nmap.put(\"b\", \"B\");\nmap.put(\"c\", \"C\");\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.Map;\n\nList list = ListBuilder.create().add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create().put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n此时的 List、Map 的实现类是 java.util.ArrayList、java.util.HashMap；您可以通过 create 指定其实现类。import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.LinkedList;\nimport java.util.Map;\nimport java.util.LinkedHashMap;\n\nList list = ListBuilder.create(LinkedList.class).add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create(LinkedHashMap.class).put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n注：实现类必须为 public，且必须有无参数的访问权限为 public 的构造函数\n当您有 value 为 null 时，不添加到 List 时，传统写法：import java.util.ArrayList;import java.util.List;\n\nString value = null;\nList list = new ArrayList();\n\nif(value != null){\n\tlist.add(value);\n}\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import java.util.List;\n\nString value = null;\nList list = ListBuilder.create().addIfPresent(value).build();\nMap、Set、Queue 同理。"
        },
        {
          "title": "便捷方法",
          "url": "/manual/3.0/core/builder.html#构建器-便捷方法",
          "content": "便捷方法\n\n方法\n说明\n\n\n\n\n List ListBuilder.epmty()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of(V value)\n创建仅有一个元素的 V 类型的 List 对象\n\n\n Queue QueueBuilder.epmty()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Queue 对象\n\n\n Set SetBuilder.epmty()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Set 对象\n\n\n Map MapBuilder.epmty()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of(V value)\n创建仅有一个元素的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\nempty 与 jdk Collections.emptyList()、Collections.emptySet()、Collections.emptyMap() 的本质区别是返回的 List 实例不同，该方法创建的 List、Set、Map 实例是允许对 List、Set、Map 做操作，而 jdk Collections 创建的 List、Set、Map 则是不允许的。两个 of 方法均同理。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/builder.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/codec.html",
      "children": [
        {
          "title": "编码器",
          "url": "/manual/3.0/core/codec.html#编码器",
          "content": "编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中。我们在当前现代应用中，通常会在业务上定义业务错误信息。且我们返回给前端的可能是更模糊或通用的、人为可读性更强的错误信息，而且可能两种不同原因引起的错误，但是我们在返回给前端时的错误信息是一模一样的，这时我们就需要更精确的标识来定义错误，通常为错误码。此时，我们在应用中，通常会定义错误码和错误信息的映射关系。我们可用的方法大致是，第一代码里面写死；第二，定义错误枚举或者常量。无论哪种方式的都与代码高度耦合，可读性和可维护性都差。此时此刻，您可以通过 buession framework 的注解 @Message 来简化和解耦您的错误信息配置和代码。在传统 springmvc 应用中，您可以通过 context:property-placeholder 或者 util:properties 标签将错误信息 properties 配置文件加载到当前应用环境中。USER_NOT_FOUND.code = 10404USER_NOT_FOUND.message = 用户不存在\n\nUSER_LOGIN_FAILURE.code = 10405\nUSER_LOGIN_FAILURE.message = 登录失败\n\n\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(\"USER_NOT_FOUND\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n您可以自定义错误代码和错误消息的 key，默认分别为：code、message。此时您需要在注解 @Message 上显示指定错误代码和错误消息的 key。USER_NOT_FOUND.errorCode = 10404USER_NOT_FOUND.errorMessage = 用户不存在\n\nUSER_LOGIN_FAILURE.errorCode = 10405\nUSER_LOGIN_FAILURE.errorMessage = 登录失败\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(value = \"USER_NOT_FOUND\", code = \"errorCode\", message = \"errorMessage\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/codec.html#编码器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/collect.html",
      "children": [
        {
          "title": "收集器",
          "url": "/manual/3.0/core/collect.html#收集器",
          "content": "收集器数组、Map、集合的工具类"
        },
        {
          "title": "数组",
          "url": "/manual/3.0/core/collect.html#收集器-数组",
          "content": "数组数组工具类 Arrays 继承自 org.apache.commons.lang3.ArrayUtils 封装了元素是否存在检测、元素索引位置获取、拼接成字符串、转换为 List、Set 以及字符串类型的数组、数组合并、数组元素操作等方法。检测数组 array 中是否存在元素 element：import com.buession.core.collect.Arrays;\nboolean result = Arrays.contains(array, element);\n返回元素 element 在数组 array 中第一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.indexOf(array, element);\n返回元素 element 在数组 array 中最后一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.lastIndexOf(array, element);\n将字符串拼接会字符串：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString result = Arrays.toString(array);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString glue = \"-\";\nString result = Arrays.toString(array, glue);\n// 1-2-3\n可以通过方法 toList、toSet 转换为 List 和 Set：import com.buession.core.collect.Arrays;import java.util.List;\nimport java.util.Set;\n\nint[] array = {1, 2, 3};\nList list = Arrays.toList(array);\nSet set = Arrays.toSet(array);\n将数组转换为字符串类型的数组：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString[] result = Arrays.toStringArray(array);\n将数组进行合并：import com.buession.core.collect.Arrays;\nString[] result = Arrays.toStringArray(array1, array2, array3);\n对数组元素进行操作：import com.buession.core.collect.Arrays;\nString[] array = {\"A\", \"B\", \"C\"};\nString[] result = Arrays.map(array, String.class, fn);\n第二个参数为数组元素类型，第三个参数为 java.util.function.Function 的实现"
        },
        {
          "title": "Lists",
          "url": "/manual/3.0/core/collect.html#收集器-lists",
          "content": "ListsList 工具类 Lists 实现了将元素拼接成字符串、转换为 Set 操作。将字符串拼接会字符串：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString result = Lists.toString(list);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Lists.toString(list);\n// 1-2-3\n可以通过方法 toSet 将 List 转换为 Set：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nSet set = Lists.toSet(list);\n"
        },
        {
          "title": "Sets",
          "url": "/manual/3.0/core/collect.html#收集器-sets",
          "content": "SetsSett 工具类 Sets 实现了将元素拼接成字符串、转换为 List 操作。将字符串拼接会字符串：import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString result = Sets.toString(set);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Sets.toString(list);\n// 1-2-3\n可以通过方法 toList 将 Set 转换为 List：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nList list = Sets.toList(set);\n"
        },
        {
          "title": "Maps",
          "url": "/manual/3.0/core/collect.html#收集器-maps",
          "content": "MapsMap 工具类 Maps 实现了对 key 和 value 进行操作，实现了将 value 转换为 List 和 Set。对 Map 进行操作：import com.buession.core.collect.Maps;import java.util.Map;\nimport java.util.HashMap;\n\nMap maps = new HashMap();\nMap result = Maps.map(maps, (key)->key, (value)->value == null ? null : value.toString());\n第二个、第三参数为 java.util.function.Function 的实现可以通过方法 toList 将 Map 的 value 转换为 List：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\n\nList list = Maps.toList(maps);\n可以通过方法 toSet 将 Map 的 value 转换为 Set：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.Set;\n\nSet set = Maps.toSet(maps);\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/collect.html#收集器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/context.html",
      "children": [
        {
          "title": "上下文",
          "url": "/manual/3.0/core/context.html#上下文",
          "content": "上下文注解 com.buession.core.context.stereotype.Manager 用于在分层应用中，标记当前类是一个 manager 类。类似于 org.springframework.stereotype.Service 加上该注解会将当前类自动注入到 spring 容器中，用法和 @Service 一样。在多层应用架构中 Manager 层通常为：通用业务处理层，处于 Dao 层之上，Service 层之下。主要特征如下：逻辑少\n与 Dao 层进行交互，多个 Dao 层的复用\nService 通用底层逻辑的下沉，如：缓存方案、中间件通用处理、以及对第三方平台封装的层\nimport com.buession.core.context.stereotype.Manager;import org.springframework.stereotype.Service;\n\npublic interface UserManager {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Manager\npublic class UserManagerImpl implements UserManager {\n\n\t@Autowired\n\tprivate UserDao userDao;\n\n\t@Autowired\n\tprivate UserProfileDao userProfileDao;\n\n\t@Autowired\n\tprivate RedisTemplate redisTemplate;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = redisTemplate.hGetObject(\"user\", Integer.toString(id), User.class);\n\n\t\tif(user == null){\n\t\t\tuser = userDao.getByPrimary(id);\n\t\t\tif(user != null){\n\t\t\t\tuser.setProfile(userProfileDao.getByUserId(id));\n\t\t\t\tredisTemplate.hSet(\"user\", Integer.toString(id), user);\n\t\t\t}else{\n\t\t\t\tthrow new RuntimeException(\"用户不存在\");\n\t\t\t}\n\t\t}\n\n\t\treturn user;\n\t}\n\n}\n\npublic interface UserService {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Service\npublic class UserServiceImpl implements UserService {\n\n\t@Autowired\n\tprivate UserManager userManager;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = userManager.getByPrimary(id);\n\n\t\t...\n\n\t\treturn user;\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/context.html#上下文-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/converter.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/3.0/core/converter.html#构建器",
          "content": "构建器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。接口定义：@FunctionalInterfacepublic interface Converter {\n\n\tT convert(final S source);\n\n}\n将类似为 S 的对象转换为类型为 T 的对象。"
        },
        {
          "title": "内置转换器",
          "url": "/manual/3.0/core/converter.html#构建器-内置转换器",
          "content": "内置转换器\n\n转换器\n说明\n\n\n\n\nArrayConverter\n将 S 类型的数组转换为 T 类型的数组\n\n\nEnumConverter\n枚举转换器，将字符串转换为枚举 E\n\n\nBinaryEnumConverter\n枚举转换器，将 byte 数组转换为枚举 E\n\n\nBooleanStatusConverter\n将布尔值转换为 com.buession.lang.Status\n\n\nStatusBooleanConverter\n将 com.buession.lang.Status 转换为布尔值\n\n\nPredicateStatusConverter\n通过 java.util.function.Predicate 对某种数据类型的数据进行判断结果转换为 com.buession.lang.Status\n\n\nListConverter\n将 S 类型的 List 对象转换为 T 类型的 List 对象\n\n\nSetConverter\n将 S 类型的 Set 对象转换为 T 类型的 Set 对象\n\n\nMapConverter\n将 Key 为 SK 类型、值为 SV 类型的 Map 对象转换为 Key 为 TK 类型、值为 TV 类型的 Map\n\n\n将 key 为 Integer 类型，value 为 Object 类型的 Map 转换成 key 为 String 类型，value 为 String 类型的 Map 对象import com.buession.core.converter.MapConverter;import java.util.Map;\n\nMap source;\nMap target;\nMapConverter converter = new MapConverter();\n\ntarget = converter.convert(source);\n将字符串转换为枚举import com.buession.core.converter.EnumConverter;import com.buession.lang.Gender;\n\nGender target;\nEnumConverter converter = new EnumConverter(Gender.class);\n\ntarget = converter.convert(\"FEMALE\");\n// Gender.FEMALE\n\ntarget = converter.convert(\"F\");\n// null\n"
        },
        {
          "title": "POJO 类映射",
          "url": "/manual/3.0/core/converter.html#构建器-pojo-类映射",
          "content": "POJO 类映射我们通过 com.buession.core.converter.mapper.Mapper 接口约定了，基于 mapstruct POJO 类的映射接口规范。关于 mapstruct 的更多文章，可通过搜索引擎自行搜索。public interface Mapper {\n\t/**\n\t * 将源对象映射到目标对象\n\t *\n\t * @param object\n\t * \t\t源对象\n\t *\n\t * @return 目标对象实例\n\t */\n\tT mapping(S object);\n\n\t/**\n\t * 将源对象数组映射到目标对象数组\n\t *\n\t * @param object\n\t * \t\t源对象数组\n\t *\n\t * @return 目标对象实例数组\n\t */\n\tT[] mapping(S[] object);\n\n\t/**\n\t * 将源 list 对象映射到目标 list 对象\n\t *\n\t * @param object\n\t * \t\t源 list 对象\n\t *\n\t * @return 目标对象 list 实例\n\t */\n\tList mapping(List object);\n\n\t/**\n\t * 将源 set 对象映射到目标 set 对象\n\t *\n\t * @param object\n\t * \t\t源 set 对象\n\t *\n\t * @return 目标对象 set 实例\n\t */\n\tSet mapping(Set object);\n\n}\n我们还可以使用工具类 com.buession.core.converter.mapper.PropertyMapper 将值从提供的源映射到目标，此方法来拷贝并简单修改于：springboot 中的 org.springframework.boot.context.properties.PropertyMapper，和原生 springboot 中的用法一样。import com.buession.core.converter.mapper.PropertyMapper;\nUser source = new User();\nUser target = new User();\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();\npropertyMapper.form(source::getId).to(target:setId)\n// null\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/converter.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/datetime.html",
      "children": [
        {
          "title": "日期时间",
          "url": "/manual/3.0/core/datetime.html#日期时间",
          "content": "日期时间日期、时间工具。目前，仅有少量的 API，参考 PHP 中的日期时间函数而来。获取当前 Unix 时间戳（秒）：import com.buession.core.datetime.DateTime;\nDateTime.unixtime();\n该方法我们在实际业务中经常用到。以 \"msec sec\" 的格式返回当前 Unix 时间戳和微秒数：import com.buession.core.datetime.DateTime;\nDateTime.microtime();\n// 1657171717 948000\n该方法参考 PHP 的 microtime 函数而来。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/datetime.html#日期时间-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/id.html",
      "children": [
        {
          "title": "ID 生成器",
          "url": "/manual/3.0/core/id.html#id-生成器",
          "content": "ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。您可以通过 buession framework 提供的 ID 生成器，生成唯一 ID。接口规范。public interface IdGenerator {\n\t/**\n\t * 获取下一个 ID\n\t *\n\t * @return ID\n\t */\n\tT nextId();\n\n}\n"
        },
        {
          "title": "ID 生成器",
          "url": "/manual/3.0/core/id.html#id-生成器-id-生成器",
          "content": "ID 生成器\n\n生成器\n说明\n\n\n\n\nAtomicSimpleIdGenerator\n基于 AtomicLong 递增的，简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nAtomicUUIDIdGenerator\n基于 AtomicLong 递增的，UUID ID 生成器\n\n\nNanoIDIdGenerator\njnanoid ID 生成器，可通过构造函数指定字符范围、长度\n\n\nRandomDigitIdGenerator\n随机数 ID 生成器，返回指定范围内的随机数，默认为 1L ~ Long.MAX_VALUE，可通过构造函数指定\n\n\nRandomIdGenerator\n随机字符 ID 生成器，可通过构造函数指定生成长度，默认 16 位\n\n\nSimpleIdGenerator\n简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nSnowflakeIdGenerator\n雪花算法 ID 生成器，此处不解决时钟回拨的问题，您可以通过构造函数指定开始时间、数据中心 ID、数据中心 ID 所占的位数等等值\n\n\nUUIDIdGenerator\nUUID ID 生成器\n\n\nimport com.buession.core.id.AtomicUUIDIdGenerator;import com.buession.core.id.NanoIDIdGenerator;\nimport com.buession.core.id.SnowflakeIdGenerator;\nimport com.buession.core.id.UUIDIdGenerator;\nimport com.buession.core.id.SimpleIdGenerator;\n\nAtomicUUIDIdGenerator idGenerator = new AtomicUUIDIdGenerator();\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000001\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000002\n\nNanoIDIdGenerator idGenerator = new NanoIDIdGenerator();\nidGenerator.nextId(); // omRdTPCug5z_Uk1E_x3ozu3Avyyl3XSK\n\nSnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();\nidGenerator.nextId(); // 170602258864545792\n\nUUIDIdGenerator idGenerator = new UUIDIdGenerator();\nidGenerator.nextId(); // 8634a166-e7d6-4160-85eb-3433107de5a4\n\nSimpleIdGenerator idGenerator = new SimpleIdGenerator();\nidGenerator.nextId(); // 26d9ed57fdad443894b988eeabc69c05\n注：关于雪花算法、jnanoid 算法的可自行搜索。\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/id.html#id-生成器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/math.html",
      "children": [
        {
          "title": "数学函数",
          "url": "/manual/3.0/core/math.html#数学函数",
          "content": "数学函数定义了实用的数学函数。\n\n方法\n说明\n\n\n\n\ncontinuousSum\n计算两个数之间连续相加之和\n\n\nrangeValue\n获取合法范围内的值，如果 value 小于 min，则返回 min；如果 value 大于 max，则返回 max；否则返回 value 本身\n\n\nimport com.buession.core.math.Math;\nlong result = Math.continuousSum(1, 100);\n// 5050\nimport com.buession.core.math.Math;\nlong value = 3;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 4\nimport com.buession.core.math.Math;\nlong value = 5;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 5\nimport com.buession.core.math.Math;\nlong value = 11;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 10\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/math.html#数学函数-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/serializer.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/3.0/core/serializer.html#构建器",
          "content": "构建器对象的序列化和反序列化，包括二进制和 JSON。您可以通过该 API，实现将对象序列化成二进制或 JSON 字符串；或将二进制、JSON 字符串反序列化为对象。"
        },
        {
          "title": "序列化、反序列化类",
          "url": "/manual/3.0/core/serializer.html#构建器-序列化、反序列化类",
          "content": "序列化、反序列化类\n\n类\n说明\n\n\n\n\nDefaultByteArraySerializer\n将对象序列化为二进制，或将二进制反序列化为对象\n\n\nFastJsonJsonSerializer\n基于 FastJSON 的对象与 JSON 之间的序列化和反序列化\n\n\nGsonJsonSerializer\n基于 Gson 的对象与 JSON 之间的序列化和反序列化\n\n\nJacksonJsonSerializer\n基于 Jackson2 的对象与 JSON 之间的序列化和反序列化\n\n\n通用标准方法是，将对象序列化为字符串，或将字符串反序列化为对象\nDefaultByteArraySerializer 序列化成字符串，逻辑是在对象序列化为 byte 数组后，通过 URLEncoder.encode 进行编码；反序列化，则先通过 URLDecoder.decode 进行解码成 byte 数组，在反序列化为对象\nDefaultByteArraySerializer 支持对象与 byte 数组数组之间的序列化和反序列化\n在将 JSON 字符串反序列化为对象时，默认返回类型依据 fastjson、jackson 等原生逻辑\nFastJsonJsonSerializer、GsonJsonSerializer、JacksonJsonSerializer 可以通过参数 Class、TypeReference 指定返回的对象类型\ncom.buession.core.serializer.type.TypeReference 是某类型的一个指向或者引用，用于屏蔽 fastjson、gson、jackson 中通过 JDK Type 指定反序列化的类型；在 fastjson、gson 中是直接指定 Type，在 jackson 中是通过 com.fasterxml.jackson.core.type.TypeReference 类返回\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/serializer.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/validator.html",
      "children": [
        {
          "title": "验证器",
          "url": "/manual/3.0/core/validator.html#验证器",
          "content": "验证器数据验证器及其注解。该 API 提供了大量通用的适用于本土化的常用验证方法，如：判断是否为 null、判断是否不为 null、判断（字符串、数组、集合、Map等）是否为空、判断（字符串、数组、集合、Map等）是否不为空、IP 地址合法性验证、ISBN 合法性验证、身份证号码验证、QQ 验证。并提供对应的基于 javax.validation 的校验注解。验证是否为 null判断任意对象是否为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNull(obj);\n验证是否不为 null判断任意对象是否不为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNotNull(obj);\n判断字符串是否为空白字符串判断字符串是否为空白字符串，为 null 或长度为 0 时也返回 true；其余情况，字符串中含有任意可打印字符串则为 falseimport com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\n\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\na\";\nboolean result = Validate.isBlank(str); // false\n注：isNotBlank 与之相反\n判断是否为空isEmpty 可判断字符串、数组、集合、Map 是否为空，即：为 null 或长度/大小为 0 时，表示为空import com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isEmpty(str); // true\n\nString str = \" \";\nboolean result = Validate.isEmpty(str); // false\n\nboolean result = Validate.isEmpty(new String[]{}); // true\n\nboolean result = Validate.isEmpty(new Integer[]{1}); // false\n注：isNotEmpty 与之相反\n判断是否在两个数之间isBetween 可判断一个整型或浮点型，是否在两个整型或者浮点型数字之间import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3); // true\n\nboolean result = Validate.isBetween(2, 2, 3); // false\n可通过参数设置是否包含边界值import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3, true); // true\n\nboolean result = Validate.isBetween(2, 2, 3, true); // true\n判断是否为电话号码isTel 可判断一个字符串是否为电话号码，仅支持普通座机号码，不支持 110、400、800等特殊号码。格式，需符合：86-区号-电话号码、区号-电话号码、（区号）电话号码、(区号)电话号码、电话号码。可通过参数 com.buession.core.validator.routines.TelValidator.AreaCodeType 指定区号的控制策略。仅支持中国的电话号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isTel(\"028-12345678\"); // true\n\nboolean result = Validate.isTel(\"028-02345678\"); // false\n判断是否为手机号码isMobile 可判断一个字符串是否为手机号码。仅支持中国的手机号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isMobile(\"028-12345678\"); // false\n\nboolean result = Validate.isMobile(\"13800138000\"); // true\n判断是否为邮政编码isPostCode 可判断一个字符串是否为邮政编码。仅支持中国的邮政编码。import com.buession.core.validator.Validate;\nboolean result = Validate.isPostCode(\"043104\"); // false\n\nboolean result = Validate.isPostCode(\"643104\"); // true\n判断是否为 QQ 号码isQQ 可判断一个字符串是否为 QQ 号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isQQ(\"043104\"); // false\n\nboolean result = Validate.isQQ(\"251329041\"); // true\n判断是否为身份证号码isIDCard 可判断一个字符串是否合法的身份证号码。仅支持 18 位的身份证号码。身份证号码需符合其身份证号码编排规律。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n可以和身份证号码进行比对，其实该方法的实际作用不是很大，只是在业务系统里面有需要填写身份证号码和出生日期时，简化验证出生日期和身份证号码中的出生日期是否一致。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\", true, \"2000-01-01\");\n其它，更多方法可以参考手册。"
        },
        {
          "title": "注解",
          "url": "/manual/3.0/core/validator.html#验证器-注解",
          "content": "注解javax 的 validation 是 Java 定义的一套基于注解的数据校验规范，buession framework 基于 javax.validation 实现了 Validate 中所有验证方法的校验注解。\n\n注解\n验证的数据类型\n说明\n\n\n\n\n@Alnum\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Alpha\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Numeric\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Between\nshort、int、double 等任何 Number 的子类型\n验证注解的元素值是否为在两个数之间\n\n\n@Empty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否为空\n\n\n@NotEmpty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否不为空\n\n\n@HasText\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@IDCard\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Ip\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Isbn\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@MimeType\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Mobile\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Null\n任意类型\n验证注解的元素值是否为 null\n\n\n@NotNull\n任意类型\n验证注解的元素值是否为 null\n\n\n@Port\nInteger\n验证注解的元素值是否为 null\n\n\n@PostCode\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@QQ\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Tel\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Xdigit\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/validator.html#验证器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/utils.html",
      "children": [
        {
          "title": "工具类",
          "url": "/manual/3.0/core/utils.html#工具类",
          "content": "工具类常用通用性工具类。"
        },
        {
          "title": "Byte 数组比较",
          "url": "/manual/3.0/core/utils.html#工具类-byte-数组比较",
          "content": "Byte 数组比较ByteArrayComparable 类为 java Comparable 的实现，实现了 byte 数组的比较。"
        },
        {
          "title": "注解工具",
          "url": "/manual/3.0/core/utils.html#工具类-注解工具",
          "content": "注解工具AnnotationUtils 继承 org.springframework.core.annotation.AnnotationUtils ，在此基础上新增了方法 hasClassAnnotationPresent(final Class clazz, final Class[] annotations)、hasMethodAnnotationPresent(Method method, final Class[] annotations) 判断类或者方法上是否有待检测的注解中的其中一个注解。"
        },
        {
          "title": "断言",
          "url": "/manual/3.0/core/utils.html#工具类-断言",
          "content": "断言Assert 和 springframework 中的注解类似，不过逻辑有些相反。"
        },
        {
          "title": "Byte 工具",
          "url": "/manual/3.0/core/utils.html#工具类-byte-工具",
          "content": "Byte 工具ByteUtils 可以将 byte 数组转换为 char 或者 char 数组。import com.buession.core.utils.ByteUtils;\nbyte[] bytes;\nchar c = ByteUtils.toChar(bytes);\n\nchar[] chars = ByteUtils.toChar(bytes);\nbyte 数组连接。import com.buession.core.utils.ByteUtils;\nbyte[] dest;\nbyte[] source\nbyte[] result = ByteUtils.concat(dest, source);\n"
        },
        {
          "title": "Character 工具",
          "url": "/manual/3.0/core/utils.html#工具类-character-工具",
          "content": "Character 工具CharacterUtils 继承 org.apache.commons.lang3.CharUtils，可以将 char、char 数组转换为 byte 数组。import com.buession.core.utils.CharacterUtils;\nchar c;\nbyte[] bytes = ByteUtils.toBytes(c);\n\nchar[] chars;\nbyte[] bytes = ByteUtils.toBytes(chars);\n"
        },
        {
          "title": "数字工具",
          "url": "/manual/3.0/core/utils.html#工具类-数字工具",
          "content": "数字工具NumberUtils 提供了对数字相关的操作。\n\n方法\n说明\n\n\n\n\nint2bytes\n将 int 转换为 byte[]\n\n\nbytes2int\n将 byte[] 转换为 int\n\n\nlong2bytes\n将 long 转换为 byte[]\n\n\nbytes2long\n将 byte[] 转换为 long\n\n\nfloat2bytes\n将 float 转换为 byte[]\n\n\nbytes2float\n将 byte[] 转换为 float\n\n\ndouble2bytes\n将 double 转换为 byte[]\n\n\nbytes2double\n将 byte[] 转换为 double\n\n\n"
        },
        {
          "title": "字符串工具",
          "url": "/manual/3.0/core/utils.html#工具类-字符串工具",
          "content": "字符串工具StringUtils 继承了 org.apache.commons.lang3.StringUtils，封装了多字符串更多的操作。截取字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.substr(\"abcde\", 1); // bcde\nString result = StringUtils.substr(\"abcde\", 1, 2); // bc\n生成随机字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.random(length);\n比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 3); // true\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 4); // false\n忽略大小写比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"Abce\", 3); // true\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"aBce\", 4); // false\n"
        },
        {
          "title": "拼音工具",
          "url": "/manual/3.0/core/utils.html#工具类-拼音工具",
          "content": "拼音工具PinyinUtils 封装了获取中文拼音、拼音首字母的方法。import com.buession.core.utils.PinyinUtils;\nString result = PinyinUtils.getPinyin(\"中国\"); // zhongguo\nString result = PinyinUtils.getPinYinFirstChar(\"中国\"); // zg\n"
        },
        {
          "title": "随机数工具",
          "url": "/manual/3.0/core/utils.html#工具类-随机数工具",
          "content": "随机数工具RandomUtils 封装了随机数的生成。\n\n方法\n说明\n\n\n\n\nnextBoolean\n随机布尔值\n\n\nnextBytes\n随机字节数组\n\n\nnextInt\n生成指定范围内的 int 值，默认：0 到 Integer.MAX_VALUE\n\n\nnextLong\n生成指定范围内的 long 值，默认：0 到 Long.MAX_VALUE\n\n\nnextFloat\n生成指定范围内的 float 值，默认：0 到 Float.MAX_VALUE\n\n\nnextDouble\n生成指定范围内的 double 值，默认：0 到 Double.MAX_VALUE\n\n\n"
        },
        {
          "title": "Properties 工具",
          "url": "/manual/3.0/core/utils.html#工具类-properties-工具",
          "content": "Properties 工具PropertiesUtils 封装了对 Properties 的操作，主要是 Properties.getProperty 的值为字符串，而我们在实际场景中，很多时候其值可能为 int、double。传统方法是，我们在代码中通过 Properties.getProperty 获取其值后，对其进行转换；而 PropertiesUtils 简化了操作。import com.buession.core.utils.SystemPropertyUtils;\nInteger result = PropertiesUtils.getInteger(properties, key);\nBoolean result = PropertiesUtils.getBoolean(properties, key);\n"
        },
        {
          "title": "System Property 工具",
          "url": "/manual/3.0/core/utils.html#工具类-system-property-工具",
          "content": "System Property 工具SystemPropertyUtils 封装了系统属性或系统环境变量的操作。设置属性方法 setProperty 对 System.setProperty 的封装，唯一区别是：SystemPropertyUtils.setProperty 的值可以为非字符串，该方法类会将非字符串值转换为字符串再调用 System.setProperty。import com.buession.core.utils.SystemPropertyUtils;\nSystemPropertyUtils.setProperty(\"http.port\", 8080);\nSystemPropertyUtils.setProperty(\"http.ssl.enable\", false);\n获取属性方法 getProperty 会先通过 System.getProperty 进行获取，若未获取到值，再调用 System.getenv 进行获取。String value = System.getProperty(name);\nif(Validate.hasText(value) == false){\n  value = System.getenv(name);\n}\n"
        },
        {
          "title": "版本工具",
          "url": "/manual/3.0/core/utils.html#工具类-版本工具",
          "content": "版本工具VersionUtils 提供了对两个版本值的比较方法和获取类、jar 对应的版本。import com.buession.core.utils.VersionUtils;\nVersionUtils.compare(\"1.0.0\", \"1.0.1-beta\"); // -1\nVersionUtils.compare(\"1.0.0\", \"1.0.0r\"); // -1\n规则：dev < alpha = a < beta = b < rc < release < pl = p < 纯数字版本获取类的版本值import com.buession.core.utils.VersionUtils;\nByteUtils.determineClassVersion(com.buession.core.utils.VersionUtils.class); // 3.0.0\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/utils.html#工具类-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/other.html",
      "children": [
        {
          "title": "其它",
          "url": "/manual/3.0/core/other.html#其它",
          "content": "其它通用的接口定义，框架自身类，以及其它杂项。"
        },
        {
          "title": "框架自身工具",
          "url": "/manual/3.0/core/other.html#其它-框架自身工具",
          "content": "框架自身工具获取 Buession Framework 版本：import com.buession.core.Framework;import com.buession.core.BuesssionFrameworkVersion;\n\nBuesssionFrameworkVersion.getVersion(); // 3.0.0\nFramework.VERSION; // 3.0.0\n获取 Buession Framework 框架名称：import com.buession.core.Framework;\nFramework.NAME; // \"Buession\"\n"
        },
        {
          "title": "命令执行器",
          "url": "/manual/3.0/core/other.html#其它-命令执行器",
          "content": "命令执行器命令执行器接口：/** * 命令执行器\n *\n * @param \n * \t\t命令上下文\n * @param \n * \t\t命令执行返回值\n */\n@FunctionalInterface\npublic interface Executor {\n\n\t/**\n\t * 命令执行\n\t *\n\t * @param context\n\t * \t\t命令执行器上下文\n\t *\n\t * @return 命令执行返回值，R 类型的实例\n\t */\n\tR execute(C context);\n\n}\n您可以通过，该接口执行一个命令上下文的方法，并返回一个值。该方法有些类似代理模式的代理类。"
        },
        {
          "title": "销毁接口",
          "url": "/manual/3.0/core/other.html#其它-销毁接口",
          "content": "销毁接口功能类似 java.io.Closeable。public interface Destroyable {\n\t/**\n\t * 销毁相关资源\n\t *\n\t * @throws IOException\n\t * \t\tIO 错误时抛出\n\t */\n\tvoid destroy() throws IOException;\n\n}\n"
        },
        {
          "title": "Rawable",
          "url": "/manual/3.0/core/other.html#其它-rawable",
          "content": "Rawable原始的，约定实现该接口的类，必须返回原始字节数组。public interface Rawable {\n\t/**\n\t * 返回原始的字节数组\n\t *\n\t * @return 原始的字节数组\n\t */\n\tbyte[] getRaw();\n\n}\n"
        },
        {
          "title": "名称节点",
          "url": "/manual/3.0/core/other.html#其它-名称节点",
          "content": "名称节点名称节点，约定实现该接口的类应该返回一个名称public interface NamedNode {\n\t/**\n\t * 返回节点名称\n\t *\n\t * @return 节点名称\n\t */\n\t@Nullable\n\tString getName();\n\n}\n"
        },
        {
          "title": "分页",
          "url": "/manual/3.0/core/other.html#其它-分页",
          "content": "分页com.buession.core.Pagination 为一个分页 POJO 类，包括了当前页码、每页大小、上一页、下一页、总页数、总记录数、数据列表。能够根据设定的其中一个值，计算另外的值。"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/3.0/core/exception.html",
      "children": [
        {
          "title": "异常",
          "url": "/manual/3.0/core/exception.html#异常",
          "content": "异常通用异常的定义。\n\n异常\n说明\n\n\n\n\nAccessException\n拒绝访问异常\n\n\nClassInstantiationException\n类实例化异常\n\n\nConversionException\n数据类型转换异常\n\n\nDataAlreadyExistException\n数据已存在异常\n\n\nDataNotFoundException\n数据不存在或未找到异常\n\n\nInsteadException\n类方法废弃后，需要使用其它类库方法来替代\n\n\nNestedRuntimeException\n嵌套运行时异常\n\n\nOperationException\n运算异常\n\n\nPresentException\n--\n\n\nSerializationException\n序列化异常\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/exception.html#异常-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装。从代码层面实现了数据库的读写分离，insert、update、delete 操作主库，select 操作从库。",
      "url": "/manual/3.0/dao/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/dao/index.html#安装",
          "content": "安装    com.buession\n    buession-dao\n    x.x.x\n\n我们咋众多项目中，基本有常见的重复的对数据库的 CURD 操作，比如：根据主键查询数据、根据主键删除数据、获取一条记录。MyBatis 是一款优秀的持久层框架，应用广泛。MongoDB 是一款优秀的文档数据库。我自己根据从业多年的经验，从实际场合出发，将在业务层对数据库的常用操作进行了封装。对关系型数据库基于 MyBatis 二次封装，对 MongoDB 基于 spring-data-mongodb；在未来也许会考虑，增加 jpa 和 JdbcTemplate 对关系型数据库的二次封装。同时，我们在代码层面实现了数据库的读写分离。我们没有改变 MyBatis 和 spring-data-mongodb 的任何底层逻辑，本质就是 MyBaits 和 spring-data-mongodb；我们唯一做了的就是，定义和是了大家在应用程序中常用的方法，让您不在重复去编写该部分代码；以及在代码层面实现了数据的读写分离。"
        },
        {
          "title": "Dao 接口",
          "url": "/manual/3.0/dao/index.html#dao-接口",
          "content": "Dao 接口接口定义，可见：https://javadoc.io/static/com.buession/buession-dao/2.0.2/com/buession/dao/Dao.htmlpublic interface Dao {}\nP：主键类型\nE：实体类\n分页对象 com.buession.dao.Pagination 继承自 com.buession.core.Pagination，增加了偏移量属性 offset。条件为 Map 类型，允许为 null。排序为 Map 类型，允许为 null。MyBatisBuession Framework 扩展 MyBatis 的文档。MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/dao/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/3.0/dao/mybatis.html",
      "children": [
        {
          "title": "MyBatis",
          "url": "/manual/3.0/dao/mybatis.html#mybatis",
          "content": "MyBatisBuession Framework 扩展 MyBatis 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/3.0/dao/mybatis.html#mybatis-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMyBatisDao；且存在 bean 名为 masterSqlSessionTemplate、slaveSqlSessionTemplates 的 bean 实例。masterSqlSessionTemplate 操作主库，实现插入、更新、删除操作；slaveSqlSessionTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveSqlSessionTemplate() 在所有的 slave templates 中随机返回一个 slave SqlSessionTemplate bean 实例。当然，您也可以通过 getSlaveSqlSessionTemplate(final int index) 指定索引的 slave SqlSessionTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave SqlSessionTemplate bean 实例列表，将会返回 master SqlSessionTemplate bean 实例，buession framework 屏蔽了这些技术细节。"
        },
        {
          "title": "Mybatis 约定",
          "url": "/manual/3.0/dao/mybatis.html#mybatis-mybatis-约定",
          "content": "Mybatis 约定如果集成 AbstractMyBatisDao 类，必须重写方法 getStatement()，通过此方法返回每个 Mapper namespace\nnamespace com.buession.dao.test.dao;\npublic class UserDaoImpl extends AbstractMyBatisDao {\n\n\t@Override\n\tprotected String getStatement(){\n\t\treturn \"com.buession.dao.test.dao.UserMapper\";\n\t}\n\n}\n\n\nMapper 的 SQL ID 和方法名保持一致\n\n\nSQL ID\n说明\n返回值\n\n\n\n\ninsert\n插入数据\n影响的行数\n\n\nbatchInsert\n批量插入数据，默认循环插入；您可以重写该方法实现 SQL 批量插入\n每次插入影响的行数列表\n\n\nreplace\n替换数据，即：REPLACE 语句\n影响的行数\n\n\nbatchReplace\n批量替换数据，即：REPLACE 语句\n每次替换数据影响的行数列表\n\n\nupdate\n更新数据\n更新条数\n\n\nupdateByPrimary\n根据主键更新数据，注：主键参数值是会覆盖实体类主键参数对应的类属性的值\n更新条数\n\n\ngetByPrimary\n根据主键查询数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselectOne\n（根据条件）获取一条数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselect\n查询数据\n数据结果列表\n\n\ngetAll\n查询所有数据\n数据结果列表\n\n\ncount\n获取记录数\n记录数\n\n\ndeleteByPrimary\n根据主键删除数据\n影响条数\n\n\ndelete\n删除数据\n影响条数\n\n\nclear\n清除数据\n影响条数\n\n\ntruncate\n截断数据\n影响条数\n\n\n注：要实现分页，必须实现 count，且和 select 的查询条件必须一致。因为，在分页方法中，首先会执行 count ，查询指定条件的记录数；如果记录数大于 0 时，才会执行 select 查询数据。在后续的开发中，我们将会使用拦截器实现。\n以上 SQL ID，只是一种约定，具体会呈现一种什么样的效果，还是完全屈居于您的 SQL 语句。\n"
        },
        {
          "title": "Mybatis 类型处理器",
          "url": "/manual/3.0/dao/mybatis.html#mybatis-mybatis-类型处理器",
          "content": "Mybatis 类型处理器MyBatis 自身提供大量优秀的类型处理器 TypeHandler，但任然不足。我们在此基础上扩展了一些 TypeHandler。名称空间为 org.apache.ibatis.type，不是 com.buession.dao。\n\nTypeHandler\n说明\n\n\n\n\nDefaultEnumTypeHandler\n默认 Enum 类型处理器，将值直接转换为枚举字段\n\n\nIgnoreCaseEnumTypeHandler\n忽略大小写 Enum 类型处理器，将值忽略大小写转换为枚举字段\n\n\nDefaultJsonTypeHandler\nJSON 处理器，将 JSON 格式的字符串值和类型  进行转换\n\n\nDefaultSetEnumTypeHandler\n默认 Enum 型 Set 类型处理器，将值直接转换为枚举字段作为 Set 的元素\n\n\nIgnoreCaseSetEnumTypeHandler\n忽略大小写 Enum 型 Set 类型处理器，将值忽略大小写转换为枚举字段作为 Set 的元素\n\n\nDefaultSetTypeHandler\n默认 Set 类型处理器，将值以 \",\" 拆分转换为 Set\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/dao/mybatis.html#mybatis-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/3.0/dao/mongodb.html",
      "children": [
        {
          "title": "MongoDB",
          "url": "/manual/3.0/dao/mongodb.html#mongodb",
          "content": "MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/3.0/dao/mongodb.html#mongodb-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMongoDBDao；且存在 bean 名为 masterMongoTemplate、slaveMongoTemplates 的 bean 实例。masterMongoTemplate 操作主库，实现插入、更新、删除操作；slaveMongoTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveMongoTemplate() 在所有的 slave templates 中随机返回一个 MongoTemplate bean 实例。当然，您也可以通过 getSlaveMongoTemplate(final int index) 指定索引的 slave MongoTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave MongoTemplate bean 实例列表，将会返回 master MongoTemplate bean 实例，buession framework 屏蔽了这些技术细节。AbstractMongoDBDao 的 replace 执行的也是 insert。在对 MongoDB 的操作条件中 value 可以为 com.buession.dao.MongoOperation，可以通过该类的方法控制条件等于值、大于值、小于值、LIKE值 等等运算。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/dao/mongodb.html#mongodb-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-geoip 参考手册",
      "content": "对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息。",
      "url": "/manual/3.0/geoip/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/geoip/index.html#安装",
          "content": "安装    com.buession\n    buession-geoip\n    x.x.x\n\n通常我们在应用中对用户注册、登录、以及其它操作记录 IP，我们更希望知道用户在什么城市进行的操作，如：微信公众号的内容发表于、微博的发布于等等，对于用户行为的安全审计等等有着极高的作用。geoip 在基于 maxmind geoip2 的基础上进行了二次封装，可以根据 IP（字符串形式的 IP，如：114.114.114.114、2001:0DB8:0000:0023:0008:0800:200C:417A ，IPV4 的数字表示：3739974408，java InetAddress）获取其 IP 地址的国家信息、城市信息、位置信息。"
        },
        {
          "title": "获取国家信息",
          "url": "/manual/3.0/geoip/index.html#获取国家信息",
          "content": "获取国家信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nCountry country = resolver.country(\"114.114.114.114\");\n// Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}\n\nCountry country = resolver.country(3739974408L); // 3739974408L => 222.235.123.8\n// Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}\n"
        },
        {
          "title": "获取城市信息",
          "url": "/manual/3.0/geoip/index.html#获取城市信息",
          "content": "获取城市信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nDistrict district = resolver.district(\"114.114.114.114\");\n// District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}\n\nDistrict district = resolver.district(3739974408L); // 3739974408L => 222.235.123.8\n// District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}\n"
        },
        {
          "title": "获取位置信息",
          "url": "/manual/3.0/geoip/index.html#获取位置信息",
          "content": "获取位置信息位置信息中包括了该 IP 比较全面的信息，包括：城市信息、国家信息、洲信息、经纬度、机构信息、时区等。import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nLocation location = resolver.location(\"114.114.114.114\");\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}, district=District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}, traits=Traits{ipAddress='114.114.114.114', domain='null', isp='null', network=114.114.0.0/16, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=32.0617, longitude=118.7778, accuracyRadius=50}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Shanghai\",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null]}\n\nLocation location = resolver.location(3739974408L); // 3739974408L => 222.235.123.8\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}, district=District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}, traits=Traits{ipAddress='222.235.123.8', domain='null', isp='null', network=222.235.120.0/21, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=37.5111, longitude=126.9743, accuracyRadius=200}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Seoul\",offset=32400000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null]}\n"
        },
        {
          "title": "缓存",
          "url": "/manual/3.0/geoip/index.html#缓存",
          "content": "缓存为了提高数据的处理能力，可以将获取过的数据缓存起来，下次获取同一 IP 信息时，可以直接从缓存中返回。您可以通过 DatabaseResolver 构造函数中的参数 cache 设置为 com.maxmind.db.NodeCache 的实现类即可，或直接使用类 CacheDatabaseResolver解析。我们默认使用 maxmind 内置的 CHMCache 来实现缓存，它是基于 ConcurrentHashMap 的内存缓存。"
        },
        {
          "title": "Resolver 的 Spring Factory Bean",
          "url": "/manual/3.0/geoip/index.html#resolver-的-spring-factory-bean",
          "content": "Resolver 的 Spring Factory Bean我们内置了 geoip 的 Resolver spring factory bean 类 GeoIPResolverFactoryBean，您可以通过它在您的 spring 项目中，初始化 Resolver 的实现类为 spring bean 对象。dbPath 和 stream 二选一即可，一个是指定 IP 的文件路径，一个是指定已加载的 IP 库的文件流。都不设置的默认以流的形式加载 buession-geoip 中的 IP 库文件。\nenableCache 可以控制是否缓存。\n"
        },
        {
          "title": "关于 IP 库",
          "url": "/manual/3.0/geoip/index.html#关于-ip-库",
          "content": "关于 IP 库buession-geoip 中包含了 maxmind 免费的 IP 所属城市和国家的库。由于在 jar 包中该数据库无法做到及时更新，在实际应用中，我们建议您从 maxmind 官网下载 IP 方法您的应用中，通过 DatabaseResolver 的构造函数指定 IP 库路径，这么做的好处是：在您的应用程序中，可以去保证 IP 库是更新的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/geoip/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-git 参考手册",
      "content": "",
      "url": "/manual/3.0/git/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/git/index.html#安装",
          "content": "安装    com.buession\n    buession-git\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/git/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节，屏蔽了对 post form、post json 等等的技术细节。",
      "url": "/manual/3.0/httpclient/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/httpclient/index.html#安装",
          "content": "安装    com.buession\n    buession-httpclient\n    x.x.x\n\n我们在应用中使用 Http Client 功能时，经常因为从 apache httpcomponents 切换为 okhttp3，或者从 okhttp3 切换为 apache httpcomponents，需要改动大量的代码而烦恼。而当您使用了 buession-httpclient 时，该类库为您解决了这些烦恼，通过顶层设计，屏蔽了 apache httpcomponents 和 okhttp3 的细节，当您需要从一个 http 库更换为另外一个 http 库时，您只需要在 pom.xml 中引用不同的包，修改一下 httpclient 的初始化类和连接管理器即可。传统的方式：    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\nimport org.apache.http.HttpResponse;import org.apache.http.conn.HttpClientConnectionManager;\nimport org.apache.http.client.HttpClient;\nimport org.apache.http.impl.client.HttpClientBuilder;\nimport org.apache.http.client.methods.HttpPost;\n\nHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(new HttpClientConnectionManager()).build();\n\nHttpResponse response = httpClient.execute(new HttpPost(\"https://www.buession.com/\"));\n或者    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport okhttp3.HttpClientConnectionManager;import okhttp3.OkHttpClient;\nimport okhttp3.ConnectionPool;\nimport okhttp3.Request;\nimport okhttp3.Request.Builder;\nimport okhttp3.Response;\n\nOkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(new ConnectionPool());\nHttpClient httpClient = builder.build();\n\nBuilder requestBuilder = new Builder().post();\nrequestBuilder.url(\"https://www.buession.com/\");\nRequest okHttpRequest = requestBuilder.build();\n\nResponse httpResponse = httpClient.newCall(okHttpRequest).execute();\n现在，您只需要通过 buession-httpclient，即可屏蔽其中的细节。    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\n或者    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.OkHttpHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.conn.OkHttpClientConnectionManager;\nimport com.buession.httpclient.core.Response;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager()); // 或者 new OkHttpHttpClient(new OkHttpClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/httpclient/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/3.0/httpclient/configuration.html",
      "children": [
        {
          "title": "连接配置",
          "url": "/manual/3.0/httpclient/configuration.html#连接配置",
          "content": "连接配置您可以通过连接配置类 Configuration 配置 apache httpcomponents 和 okhttp3 的链接配置属性，buession-httpclient 内部会自动将 Configuration 的配置信息，转换为 apache httpcomponents 或 okhttp3 的配置信息。"
        },
        {
          "title": "配置属性说明",
          "url": "/manual/3.0/httpclient/configuration.html#连接配置-配置属性说明",
          "content": "配置属性说明\n\n属性名称\n数据类型\napache httpcomponents 对应配置\nokhttp3 对应配置\n默认值\n说明\n\n\n\n\nmaxConnections\nint\nmaxTotal\nmaxIdleConnections\n5000\n最大连接数\n\n\nmaxPerRoute\nint\ndefaultMaxPerRoute\n--\n500\n每个路由的最大连接数\n\n\nidleConnectionTime\nint\ncloseIdleConnections\nkeepAliveDuration\n60000\n空闲连接存活时长（单位：毫秒）\n\n\nconnectTimeout\nint\nconnectTimeout\nconnectTimeout\n3000\n连接超时时间（单位：毫秒）\n\n\nconnectionRequestTimeout\nint\nconnectionRequestTimeout\n--\n5000\n从连接池获取连接的超时时间（单位：毫秒）\n\n\nreadTimeout\nint\nsocketTimeout\nreadTimeout\n5000\n读取超时时间（单位：毫秒）\n\n\nallowRedirects\nBoolean\nredirectsEnabled\nfollowRedirects\n--\n是否允许重定向\n\n\nrelativeRedirectsAllowed\nBoolean\nrelativeRedirectsAllowed\n--\n--\n是否应拒绝相对重定向\n\n\ncircularRedirectsAllowed\nBoolean\ncircularRedirectsAllowed\n--\n--\n是否允许循环重定向\n\n\nmaxRedirects\nInteger\nmaxRedirects\n--\n--\n最大允许重定向次数\n\n\nauthenticationEnabled\nboolean\nauthenticationEnabled\n--\n--\n是否开启 Http Basic 认证\n\n\ncontentCompressionEnabled\nboolean\ncontentCompressionEnabled\n--\n--\n是否启用内容压缩\n\n\nnormalizeUri\nboolean\nnormalizeUri\n--\n--\n是否标准化 URI\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/httpclient/configuration.html#连接配置-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/3.0/httpclient/connectionmanager.html",
      "children": [
        {
          "title": "连接管理器",
          "url": "/manual/3.0/httpclient/connectionmanager.html#连接管理器",
          "content": "连接管理器连接管理器是用来管理 HTTP 连接的，包括设置连接配置、控制连接池。关于更多的连接池，可以参考 apache httpcomponents 和 okhttp3 的文档。您可以通过无参数的构造函数来创建连接管理器，也可以通过构造函数参数仅为 com.buession.httpclient.core.Configuration 来创建连接管理器，也可以构造函数通过 apache httpcomponents 或 okhttp3 原生的连接管理器类创建（此时，Configuration 的配置不任何意义，他不会修改您通过原生连接管理器实例中的参数配置）。"
        },
        {
          "title": "关于 okhttp 连接管理器",
          "url": "/manual/3.0/httpclient/connectionmanager.html#连接管理器-关于-okhttp-连接管理器",
          "content": "关于 okhttp 连接管理器okhttp3 本身是没有类似 apache httpcomponents 的链接管理器 org.apache.http.conn.HttpClientConnectionManager 的，我们为了在 buession-httpclient 的链接管理器实现 com.buession.httpclient.conn.OkHttpClientConnectionManager 保持一致，人为的加了一层 okhttp3 的链接管理器 okhttp3.HttpClientConnectionManager（注意：命名空间为 okhttp3），主要用于初始化连接池类 okhttp3.ConnectionPool。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/httpclient/connectionmanager.html#连接管理器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/3.0/httpclient/response.html",
      "children": [
        {
          "title": "响应",
          "url": "/manual/3.0/httpclient/response.html#响应",
          "content": "响应当通过 HttpClient 发起任意请求后，将得到一个 Response。此结果，包括了：协议及其版本、状态码及其信息、响应头列表、响应体、以及响应体长度。buession-httpclient 会将 apache httpcomponents 或 okhttp3 的响应对象，转换为 Response。需要注意的是，原生 apache httpcomponents 或 okhttp3 响应体流，一旦被使用过一次之后，将不能再使用；同时您只能以流或者字符串二选一的形式获取响应体。但是，在 buession-httpclient 中您将可以二者兼顾，当然这也同时会带来一些性能消耗和内存占用。import com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.core.Response;\nimport java.io.InputStream;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\nInputStream stream = response.getInputStream(); // 以流的形式获取响应体\nString body = response.getBody(); // 以字符串的形式获取响应体\n\nstream.close();\ngetInputStream、getBody 二者可以重复调用，当时您需要始终手动关闭一下流，因为这将是拷贝的原生 apache httpcomponents 或 okhttp3 返回的流。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/httpclient/response.html#响应-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/3.0/httpclient/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/3.0/httpclient/method.html#方法",
          "content": "方法buession-httpclient 提供了和 HTTP 请求方式同名的方法 API，您可以很方便的通过提供的方法发起 HTTP 请求。示例：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\");\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\");\n您可以自定义请求头：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.List;\nimport java.util.ArrayList;\n\nList headers = new ArrayList();\n\nheaders.add(new Header(\"X-SDK-NAME\", \"Buession\"));\nheaders.add(new Header(\"X-Timestamp\", System.currentTimeMillis()));\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", headers);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", headers);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", headers);\n您可以设置请求参数：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.Map;\nimport java.util.HashMap;\n\nMap parameters = new HashMap();\n\nparameters.put(\"action\", \"edit\");\nparameters.put(\"id\", 1);\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", parameters);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", parameters);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", parameters);\n您可以设置请求体：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport jcom.buession.httpclient.core.RequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\n\nEncodedFormRequestBody requestBody = new EncodedFormRequestBody();\n\nrequestBody.addRequestBodyElement(\"username\", \"buession\");\nrequestBody.addRequestBodyElement(\"password\", \"buession\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", requestBody);\n\nJsonRawRequestBody requestBody = new JsonRawRequestBody(new User());\n// PUT 请求\nResponse response = httpClient.put(\"https://www.buession.com/\", requestBody);\n不同的 RequestBody，决定了我们以什么样的 Content-Type 提交数据，buession-httpclient 中提供了大量的内置 RequestBody。"
        },
        {
          "title": "RequestBody",
          "url": "/manual/3.0/httpclient/method.html#方法-requestbody",
          "content": "RequestBody\n\nRequestBody\nContent-Type\n说明\n\n\n\n\nInputStreamRequestBody\napplication/octet-stream\n二进制请求体\n\n\nChunkedInputStreamRequestBody\napplication/octet-stream\nChunked 二进制请求体\n\n\nRepeatableInputStreamRequestBody\napplication/octet-stream\nRepeatable 二进制请求体\n\n\nEncodedFormRequestBody\napplication/x-www-form-urlencoded\n普通表单请求体\n\n\nMultipartFormRequestBody\nmultipart/form-data\n文件上传表单请求体\n\n\nHtmlRawRequestBody\ntext/html\nHTML 请求体\n\n\nJavaScriptRawRequestBody\napplication/javascript\nJavaScript 请求体\n\n\nJsonRawRequestBody\napplication/json\nJSON 请求体\n\n\nTextRawRequestBody\ntext/plain\nTEXT 请求体\n\n\nXmlRawRequestBody\ntext/xml\nXML 请求体\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/httpclient/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-io 参考手册",
      "content": "封装了对文件的操作",
      "url": "/manual/3.0/io/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/io/index.html#安装",
          "content": "安装    com.buession\n    buession-io\n    x.x.x\n\n该模块二次封装了 java java.io.File 和 java.nio.file.Files 类，在此基础上扩展了大量的实用方法，如：文件读写、获取文件 MD5 和 SHA1 值，获取文件 MIME，设置文件所属用户和用户组，简化了我们在应用开发过程中对文件的操作。"
        },
        {
          "title": "读取文件",
          "url": "/manual/3.0/io/index.html#读取文件",
          "content": "读取文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nbyte[] result = file.read();\n"
        },
        {
          "title": "写文件",
          "url": "/manual/3.0/io/index.html#写文件",
          "content": "写文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nfile.write(\"Buession\");\nfile.write(\"Buession\".getBytes());\nfile.write(\"Buession\", true); // 追加写\n"
        },
        {
          "title": "获取文件 MD5、SHA-1值",
          "url": "/manual/3.0/io/index.html#获取文件-md5、sha-1值",
          "content": "获取文件 MD5、SHA-1值import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nString md5 = file.getMd5(); // 获取文件 MD5\nString sha1 = file.getSha1(); // 获取文件 SHA-1\n"
        },
        {
          "title": "获取文件 MD5、SHA-1 值",
          "url": "/manual/3.0/io/index.html#获取文件-md5、sha-1-值",
          "content": "获取文件 MD5、SHA-1 值import com.buession.io.file.File;import com.buession.io.MimeType;\n\nFile file = new File(\"/tmp/debug.txt\");\n\nMimeType result = file.getMimeType();\n"
        },
        {
          "title": "设置文件权限",
          "url": "/manual/3.0/io/index.html#设置文件权限",
          "content": "设置文件权限import com.buession.io.file.Files;\nFiles.chmod(\"/tmp/debug.txt\", 0777);\n"
        },
        {
          "title": "设置文件用户组",
          "url": "/manual/3.0/io/index.html#设置文件用户组",
          "content": "设置文件用户组import com.buession.io.file.Files;\nFiles.chgrp(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "设置文件用户",
          "url": "/manual/3.0/io/index.html#设置文件用户",
          "content": "设置文件用户import com.buession.io.file.Files;\nFiles.chown(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "注解",
          "url": "/manual/3.0/io/index.html#注解",
          "content": "注解注解 com.buession.io.json.annotation.MimeTypeString 可以将类型为 com.buession.io.MimeType 的字段序列化为字符串和将字符串反序列化为 com.buession.io.MimeType，该功能是基于 jackson 实现的。import com.buession.io.json.annotation.MimeTypeString;\nclass File {\n\n    @MimeTypeString\n    private MimeType mime;\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/io/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-jdbc 参考手册",
      "content": "JDBC 通用 POJO 类定义，对 Hikari、Dbcp2、Druid 等配置和数据源的封装。",
      "url": "/manual/3.0/jdbc/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/jdbc/index.html#安装",
          "content": "安装    com.buession\n    buession-jdbc\n    x.x.x\n\n通过提供的 API，您可以简化对 DBCP2、Druid、Hikari、Tomcat 数据源的初始化，该类库基本不单独使用。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/jdbc/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-json 参考手册",
      "content": "主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现。",
      "url": "/manual/3.0/json/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/json/index.html#安装",
          "content": "安装    com.buession\n    buession-json\n    x.x.x\n\n封装了大量基于 jackson 的注解。"
        },
        {
          "title": "注解",
          "url": "/manual/3.0/json/index.html#注解",
          "content": "注解\n\n注解\n说明\n\n\n\n\nCalendarUnixTimestamp\njava.util.Calendar 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Calendar 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Calendar\n\n\nDateUnixTimestamp\njava.util.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Date\n\n\nSqlDateUnixTimestamp\njava.sql.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Date\n\n\nTimestampUnixTimestamp\njava.sql.Timestamp 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Timestamp 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Timestamp\n\n\nJsonEnum2Map\n枚举和 java.util.Map 序列化和反序列化；通过该注解，可以将枚举序列化为 java.util.Map；将 java.util.Map 反序列化为枚举\n\n\nSensitive\n通过该注解可以实现数据的脱敏\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/json/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-lang 参考手册",
      "content": "常用 POJO 类和枚举的定义，详细查看 API 参考手册。",
      "url": "/manual/3.0/lang/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/lang/index.html#安装",
          "content": "安装    com.buession\n    buession-lang\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/lang/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-net 参考手册",
      "content": "网络相关工具类。",
      "url": "/manual/3.0/net/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/net/index.html#安装",
          "content": "安装    com.buession\n    buession-net\n    x.x.x\n\n"
        },
        {
          "title": "IP 地址工具类",
          "url": "/manual/3.0/net/index.html#ip-地址工具类",
          "content": "IP 地址工具类IP 地址工具类 com.buession.net.utils.InetAddressUtis 实现了，IPV4 地址和数字型 IP 相互转换。import com.buession.net.utils.InetAddressUtis;\nlong result = InetAddressUtis.ip2long(\"127.0.0.1\"); // 2130706433\nString ip = InetAddressUtis.long2ip(2130706433L); // 127.0.0.1\nURI 类或 URIBuilder 类，实现了 url 字符串的构建，详细查看 API 手册。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/net/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 原生命令保持一致。同时，对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Object.class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象。",
      "url": "/manual/3.0/redis/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/redis/index.html#安装",
          "content": "安装    com.buession\n    buession-redis\n    x.x.x\n\n"
        },
        {
          "title": "介绍",
          "url": "/manual/3.0/redis/index.html#介绍",
          "content": "介绍buession-redis 是一款基于 jedis 的 redis 操作库，最大的优势就是封装了与 redis 同名、最大程度与 redis 原生参数顺序一致的 API。同时，我们在现代应用中，经常需要读写一个 pojo 对象，buession-redis 封装了 xxxObject 读写取 redis 中的二进制或 JSON 数据，并反序列化为 POJO 类。大大简化了，我们在代码中对象存取到 redis 中，让我们更专注业务功能的开。能够通过 com.buession.redis.core.Options 设置全局选项，如：统一的 Key 前缀，对象基于什么方式序列化和反序列化。import com.buession.redis.RedisTemplate;import com.buession.redis.core.Options;\nimport com.buession.core.serializer.type.TypeReference;\nimport java.utils.Map;\nimport java.utils.HashMap;\n\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.setOptions(new Options());\nredisTemplate.afterPropertiesSet();\n\n// 将 User 对象写进 key 为 user hash 中\nredisTemplate.hSet(\"user\", \"1\", new User());\n\n// 获取 key 为 user ，field 为 1 的 hash 中的数据，并转换为 User\nUser user = redisTemplate.hGetObject(\"user\", \"1\", User.class);\n\n// 获取 key 为 user 的 hash 的所有数据，并将值转换为 User\nMap data = redisTemplate.hGetAllObject(\"user\", \"1\", new TypeReference>{});\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/redis/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/3.0/redis/datasource.html",
      "children": [
        {
          "title": "数据源",
          "url": "/manual/3.0/redis/datasource.html#数据源",
          "content": "数据源buession-redis 基于数据源 DataSource 连接 redis，其机制类似 JDBC 的 DataSource。通过，数据源可以配置，redis 的用户名、密码、连接超时、读取超时、连接池、SSL等等。数据源 DataSource 包括三个子接口：StandaloneDataSource：单机模式数据源\nSentinelDataSource：哨兵模式数据源\nClusterDataSource：集群模式数据源\njedis 和后续的 lettuce 分别实现这三个接口，用于创建不通模式的数据源，数据源中实现了连接池的创建。在原始的 jedis 或者 spring-data-redis 中，密码为空字符串时，会以空字符串密码进行登录 redis；我们修改了这一逻辑，不管您在程序中密码是设置的 null 还是空字符串，我们都会跳过认证。这样的好处就是，假设您的开发环境 redis 没有设置密码，生产环境设置了密码，我们可以通过一个 bean 初始化即可，不用写成两个 bean。测试环境 properties：redis.host=127.0.0.1redis.port=6379\nredis.password=\n生产环境 properties：redis.host=192.168.100.131redis.port=6379\nredis.password=passwd\n"
        },
        {
          "title": "连接池",
          "url": "/manual/3.0/redis/datasource.html#数据源-连接池",
          "content": "连接池通过连接池管理 redis 连接，能够大大的提高效率和节约资源的使用。jedis 和 lettuce 均使用 apache commons-pool2 来创建和维护连接池。但是，在 jedis 中，以 JedisPoolConfig 和 ConnectionPoolConfig 来管理单例模式连接池、哨兵模式连接池和集群模式连接池；为了简化配置，我们定义了 com.buession.redis.core.PoolConfig 来统一维护各种模式的连接池配置，然后在各 DataSource 中转换为原生的连接池配置，极大的简化了学习和替换成本。连接池配置\n\n配置项\n数据类型\n-- 默认值\n说明\n\n\n\n\nlifo\nboolean\nGenericObjectPoolConfig.DEFAULT_LIFO\n池模式，为 true 时，后进先出；为 false 时，先进先出\n\n\nfairness\nboolean\nGenericObjectPoolConfig.DEFAULT_FAIRNESS\n当从池中获取资源或者将资源还回池中时，是否使用 java.util.concurrent.locks.ReentrantLock 的公平锁机制\n\n\nmaxWait\nDuration\nGenericObjectPoolConfig.DEFAULT_MAX_WAIT\n当连接池资源用尽后，调用者获取连接时的最大等待时间\n\n\nminEvictableIdleTime\nDuration\n60000\n连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除\n\n\nsoftMinEvictableIdleTime\nDuration\nGenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION\n连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数\n\n\nevictionPolicyClassName\nString\nGenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME\n驱逐策略的类名\n\n\nevictorShutdownTimeout\nDuration\nGenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT\n关闭驱逐线程的超时时间\n\n\nnumTestsPerEvictionRun\nint\n-1\n检测空闲对象线程每次运行时检测的空闲对象的数量\n\n\ntestOnCreate\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE\n在创建对象时检测对象是否有效，配置 true 会降低性能\n\n\ntestOnBorrow\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW\n在从对象池获取对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestOnReturn\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN\n在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestWhileIdle\nboolean\ntrue\n在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\nblockWhenExhausted\nboolean\nGenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED\n当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效；false 连接池没有资源立马抛异常）\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\njmxEnabled\nboolean\nGenericObjectPoolConfig.DEFAULT_JMX_ENABLE\n是否注册 JMX\n\n\njmxNamePrefix\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX\nJMX 前缀\n\n\njmxNameBase\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_BASE\n使用 base + jmxNamePrefix + i 来生成 ObjectName\n\n\nmaxTotal\nint\nGenericObjectPoolConfig.DEFAULT_MAX_TOTAL\n最大连接数\n\n\nminIdle\nint\nGenericObjectPoolConfig.DEFAULT_MIN_IDLE\n最小空闲连接数\n\n\nmaxIdle\nint\nGenericObjectPoolConfig.DEFAULT_MAX_IDLE\n最大空闲连接数\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/redis/datasource.html#数据源-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/3.0/redis/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/3.0/redis/method.html#方法",
          "content": "方法buession-redis BaseRedisTemplate 的方法以及参数计划与原生 redis 命名保持一致。复杂的参数会通过 Builder 进行参数构建，在多个值中进行选择的将定义成枚举，规避出错的几率。import com.buession.redis.BaseRedisTemplate;\nBaseRedisTemplate redisTemplate = new BaseRedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 删除哈希表 key 中的一个或多个指定域\nredisTemplate.hDel(\"user\", \"1\", \"2\", \"3\");\n\n// 检查给定 key 是否存在\nredisTemplate.exists(\"user\");\n\n// 获取列表 key 中，下标为 index 的元素\nredisTemplate.lIndex(\"user\", 1);\n\n// 如果键 key 已经存在并且它的值是一个字符串，将 value 追加到键 key 现有值的末尾\nredisTemplate.append(\"key\", \"value 1\");\nBaseRedisTemplate 实现了 redis 的原生操作，RedisTemplate 继承了 BaseRedisTemplate ，在此基础上实现了将 redis 中的二进制或者 JSON 格式的值，反序列化为一个类。import com.buession.redis.RedisTemplate;\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 获取列表 key 中，下标为 index 的元素，并反序列化为 User 类\nUser user = redisTemplate.lIndexObject(\"user\", 1, User.class);\n序列化和反序列化，基于 buession-core 序列化和反序列化 扩展而来，序列化或反序列化出错时会直接返回 null，而忽略异常，默认使用 com.buession.redis.serializer.JacksonJsonSerializer 序列化为 JSON。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/redis/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-thesaurus 参考手册",
      "content": "对词库的解析，目前仅支持搜狗词条。",
      "url": "/manual/3.0/thesaurus/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/thesaurus/index.html#安装",
          "content": "安装    com.buession\n    buession-thesaurus\n    x.x.x\n\n您可以通过该库解析搜狗拼音的词条库，包括词条、拼音信息。import com.buession.thesaurus.SogouParser;import com.buession.thesaurus.Parser;\nimport com.buession.thesaurus.core.Word;\nimport java.util.Set;\n\nParser parser = new SogouParser();\n\nSet words parser.parse(\"搜谱拼音词条文件路径\");\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/thesaurus/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-velocity 参考手册",
      "content": "spring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity。",
      "url": "/manual/3.0/velocity/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/velocity/index.html#安装",
          "content": "安装    com.buession\n    buession-velocity\n    x.x.x\n\n该类库，基本照搬了 springframework 集成 velocity 的代码和逻辑。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/velocity/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "web 相关的功能封装，支持 servlet 和 reactive；封装了一些常用注解，简化了业务层方面的代码实现；封装了一些常用 filter。",
      "url": "/manual/3.0/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/web/index.html#安装",
          "content": "安装    com.buession\n    buession-web\n    x.x.x\n\nbuession-web 扩展了 spring-webmvc、spring-webflux；在此基础上，提供了大量的实用的 filter 和注解，以及工具类。该模块无论封装的 filter、注解、工具类，均适用于 spring mvc，也适用于 spring webflux。当时，filter、工具类均为 servlet 和 webflux 各自独立的类，不是说同一个类，即能用于 servlet，也能用于 webflux，当然注解是通用的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/3.0/web/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/3.0/web/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了一些我们在日常应用开发过程中能用到的实用性功能，简化了您在代码开发中的便捷度，让您能够更专注业务的开发。"
        },
        {
          "title": "注解",
          "url": "/manual/3.0/web/annotation.html#注解-注解",
          "content": "注解\n\n注解\nRequest / Response\n作用域\n说明\n\n\n\n\n@RequestClientIp\nrequest\n方法参数\n获取当前请求的客户端 IP 地址\n\n\n@ContentType\nresponse\n类、方法\n设置响应 Content-Type\n\n\n@HttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值\n\n\n@DisableHttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值，禁止缓存\n\n\n@ResponseHeader\nresponse\n类、方法\n设置响应头\n\n\n@ResponseHeaders\nresponse\n类、方法\n批量设置响应头\n\n\n@DocumentMetaData\nresponse\n类、方法\n设置页面标题、页面编码、关键字、描述、版权等等元信息\n\n\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/3.0/web/filter.html",
      "children": [
        {
          "title": "过滤器",
          "url": "/manual/3.0/web/filter.html#过滤器",
          "content": "过滤器我们封装了一些使用的过滤器，简化了您的开发或者应用运行的跟踪。servlet 包位于 com.buession.web.servlet.filter，webflux 包位于 com.buession.web.reactive.filter，均有同样类名的过滤器类。"
        },
        {
          "title": "过滤器",
          "url": "/manual/3.0/web/filter.html#过滤器-过滤器",
          "content": "过滤器\n\n过滤器\n说明\n\n\n\n\nMobileFilter\n当前请求是否为移动设备\n\n\nPoweredByFilter\nPowered By 过滤器\n\n\nPrintUrlFilter\n打印当前请求 URL 过滤器\n\n\nResponseHeaderFilter\n响应头过滤器，设置响应头\n\n\nResponseHeadersFilter\n响应头过滤器，批量设置响应头\n\n\nServerInfoFilter\nServer 信息过滤器，通过响应头的形式，输出当前服务器名称，可以用于集群环境定位出现问题的节点\n\n\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/3.0/web/restful.html",
      "children": [
        {
          "title": "RESTFUL",
          "url": "/manual/3.0/web/restful.html#restful",
          "content": "RESTFULRestful 是当今比较流行的一种架构的规范与约束、原则，基于这个风格设计的软件可以更简洁、更有层次。我们遵循 REST 规范，在代码层面规范好了新增、修改、详情、删除等基本的路由，您的控制器层只需要继承 com.buession.web.servlet.mvc.controller.AbstractBasicRestController 或者 com.buession.web.reactive.mvc.controller.AbstractBasicRestController 即可在 servlet 或 webflux 模式下，实现标准的 REST 风格的代码。简化了您的代码（主要是不用再写 @RequestMapping）和标准化了。@RestController@RequestMapping(path = \"/example\")\npublic class ExampleController extends AbstractRestController {\n\n\t@Override\n\tpublic Response add(HttpServletRequest request, HttpServletResponse response, @RequestBody ExampleDto example){\n\t\t\n\t}\n\n\t@Override\n\tpublic Response edit(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id, @RequestBody ExampleDto example){\n\n\t}\n\n\t@Override\n\tpublic Response detail(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\n\t}\n\n\t@Override\n\tpublic Response delete(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/3.0/web/utils.html",
      "children": [
        {
          "title": "工具",
          "url": "/manual/3.0/web/utils.html#工具",
          "content": "工具我们封装了一些 web 相关的工具类，用于处理 request、response。servlet 包位于 com.buession.web.servlet.http，webflux 包位于 com.buession.web.reactive.http，均有同样类名的过滤器类。获取客户端真实 IP 地址：RequestUtils.getClientIp(request);我们兼容了，通过微信和一些 CDN 厂商获取用户真实 IP 的头信息，我们优先获取从微信透传过来的用户的真实 IP，然后再是各 CDN 厂商的用户真实 IP 头，最后才是标准的真实 IP 头。当然，我们不能保证是否是伪造的。优先顺序：X-Forwarded-For-Pound（微信） > Ali-Cdn-Real-Ip（阿里云 CDN） > Cdn-Src-Ip（网宿） > X-Cdn-Src-Ip（网宿） > X-Original-Forwarded-For（天翼云） > X-Forwarded-For > X-Real-Ip > Proxy-Client-IP > WL-Proxy-Client-IP > Real-ClientIP > remote addr是否是 Ajax 请求：RequestUtils.isAjaxRequest(request);是否是移动设备请求：RequestUtils.isMobile(request);设置缓存：ResponseUtils.httpCache(response, 5); // 缓存 5 秒ResponseUtils.httpCache(response, new Date()); // 缓存到指定的时间点\n"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Framework API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-aop\n使用帮助\nAPI 手册\n\n\nbuession-beans\n使用帮助\nAPI 手册\n\n\nbuession-core\n使用帮助\nAPI 手册\n\n\nbuession-cron\n使用帮助\nAPI 手册\n\n\nbuession-dao\n使用帮助\nAPI 手册\n\n\nbuession-geoip\n使用帮助\nAPI 手册\n\n\nbuession-httpclient\n使用帮助\nAPI 手册\n\n\nbuession-io\n使用帮助\nAPI 手册\n\n\nbuession-jdbc\n使用帮助\nAPI 手册\n\n\nbuession-json\n使用帮助\nAPI 手册\n\n\nbuession-lang\n使用帮助\nAPI 手册\n\n\nbuession-net\n使用帮助\nAPI 手册\n\n\nbuession-redis\n使用帮助\nAPI 手册\n\n\nbuession-session\n使用帮助\nAPI 手册\n\n\nbuession-thesaurus\n使用帮助\nAPI 手册\n\n\nbuession-velocity\n使用帮助\nAPI 手册\n\n\nbuession-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.3/index.html",
      "children": []
    },
    {
      "title": "buession-aop 参考手册",
      "content": "AOP 封装，方便实现自定义注解",
      "url": "/manual/2.3/aop/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/aop/index.html#安装",
          "content": "安装    com.buession\n    buession-aop\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/aop/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-beans 参考手册",
      "content": "该类库提供了基于 commons-beanutils 和 cglib（spring-core 包中，名称空间：org.springframework.cglib.beans） 的 bean 工具的二次封装。包括了属性拷贝和属性映射，以及对象属性转换为 Map。",
      "url": "/manual/2.3/beans/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/beans/index.html#安装",
          "content": "安装    com.buession\n    buession-beans\n    x.x.x\n\n"
        },
        {
          "title": "属性拷贝",
          "url": "/manual/2.3/beans/index.html#属性拷贝",
          "content": "属性拷贝使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 和 target 的属性做映射，实现同名拷贝。import com.buession.beans.BeanUtils;\nBeanUtils.copyProperties(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.copyProperties(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "属性映射",
          "url": "/manual/2.3/beans/index.html#属性映射",
          "content": "属性映射使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 转换为驼峰格式后和 target 的属性做映射，实现拷贝，这是 populate 和 copyProperties 的唯一区别。import com.buession.beans.BeanUtils;\nBeanUtils.populate(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.populate(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "Bean 转换为 Map",
          "url": "/manual/2.3/beans/index.html#bean-转换为-map",
          "content": "Bean 转换为 Map使用此方法，可以实现将一个 bean 对象转换为 Map，bean 的属性作为 Map 的 Keyimport com.buession.beans.BeanUtils;\nMap result = BeanUtils.toMap(bean)\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/beans/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "该类库为核心包，包括构建器、工具类、数据验证、ID 生成器、转换器、序列化、数学方法、消息注入、Manager 层注解、日期时间类和各通用接口定义。",
      "url": "/manual/2.3/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/core/index.html#安装",
          "content": "安装    com.buession\n    buession-core\n    x.x.x\n\n构建器Map、集合的便捷式构建，减少您的代码行数编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中收集器数组、Map、集合的工具类上下文定义应用上下文的类库、注解配置器接使用配置参数对对象进行配置定制器使用源对象对目标对象进行定制转换器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。日期时间日期、时间工具ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。数学函数定义了实用的数学函数序列化将对象序列化为二进制或 JSON。反序列化将二进制或 JSON 反序列化为对象。验证器数据验证器及其注解工具类常用通用性工具类其它通用的接口定义，框架自身类异常通用异常的定义"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/builder.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.3/core/builder.html#构建器",
          "content": "构建器Map、集合的便捷式构建，减少您的代码行数。您需要往 Map、List 中添加元素的传统写法是：import java.util.ArrayList;import java.util.List;\nimport java.util.HashMap;\nimport java.util.Map;\n\nList list = new ArrayList();\nlist.add(\"A\");\nlist.add(\"B\");\nlist.add(\"C\");\n\nMap map = new HashMap();\nmap.put(\"a\", \"A\");\nmap.put(\"b\", \"B\");\nmap.put(\"c\", \"C\");\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.Map;\n\nList list = ListBuilder.create().add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create().put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n此时的 List、Map 的实现类是 java.util.ArrayList、java.util.HashMap；您可以通过 create 指定其实现类。import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.LinkedList;\nimport java.util.Map;\nimport java.util.LinkedHashMap;\n\nList list = ListBuilder.create(LinkedList.class).add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create(LinkedHashMap.class).put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n注：实现类必须为 public，且必须有无参数的访问权限为 public 的构造函数\n当您有 value 为 null 时，不添加到 List 时，传统写法：import java.util.ArrayList;import java.util.List;\n\nString value = null;\nList list = new ArrayList();\n\nif(value != null){\n\tlist.add(value);\n}\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import java.util.List;\n\nString value = null;\nList list = ListBuilder.create().addIfPresent(value).build();\nMap、Set、Queue 同理。"
        },
        {
          "title": "便捷方法",
          "url": "/manual/2.3/core/builder.html#构建器-便捷方法",
          "content": "便捷方法\n\n方法\n说明\n\n\n\n\n List ListBuilder.epmty()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of(V value)\n创建仅有一个元素的 V 类型的 List 对象\n\n\n Queue QueueBuilder.epmty()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Queue 对象\n\n\n Set SetBuilder.epmty()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Set 对象\n\n\n Map MapBuilder.epmty()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of(V value)\n创建仅有一个元素的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\nempty 与 jdk Collections.emptyList()、Collections.emptySet()、Collections.emptyMap() 的本质区别是返回的 List 实例不同，该方法创建的 List、Set、Map 实例是允许对 List、Set、Map 做操作，而 jdk Collections 创建的 List、Set、Map 则是不允许的。两个 of 方法均同理。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/builder.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/codec.html",
      "children": [
        {
          "title": "编码器",
          "url": "/manual/2.3/core/codec.html#编码器",
          "content": "编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中。我们在当前现代应用中，通常会在业务上定义业务错误信息。且我们返回给前端的可能是更模糊或通用的、人为可读性更强的错误信息，而且可能两种不同原因引起的错误，但是我们在返回给前端时的错误信息是一模一样的，这时我们就需要更精确的标识来定义错误，通常为错误码。此时，我们在应用中，通常会定义错误码和错误信息的映射关系。我们可用的方法大致是，第一代码里面写死；第二，定义错误枚举或者常量。无论哪种方式的都与代码高度耦合，可读性和可维护性都差。此时此刻，您可以通过 buession framework 的注解 @Message 来简化和解耦您的错误信息配置和代码。在传统 springmvc 应用中，您可以通过 context:property-placeholder 或者 util:properties 标签将错误信息 properties 配置文件加载到当前应用环境中。USER_NOT_FOUND.code = 10404USER_NOT_FOUND.message = 用户不存在\n\nUSER_LOGIN_FAILURE.code = 10405\nUSER_LOGIN_FAILURE.message = 登录失败\n\n\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(\"USER_NOT_FOUND\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n您可以自定义错误代码和错误消息的 key，默认分别为：code、message。此时您需要在注解 @Message 上显示指定错误代码和错误消息的 key。USER_NOT_FOUND.errorCode = 10404USER_NOT_FOUND.errorMessage = 用户不存在\n\nUSER_LOGIN_FAILURE.errorCode = 10405\nUSER_LOGIN_FAILURE.errorMessage = 登录失败\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(value = \"USER_NOT_FOUND\", code = \"errorCode\", message = \"errorMessage\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/codec.html#编码器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/collect.html",
      "children": [
        {
          "title": "收集器",
          "url": "/manual/2.3/core/collect.html#收集器",
          "content": "收集器数组、Map、集合的工具类"
        },
        {
          "title": "数组",
          "url": "/manual/2.3/core/collect.html#收集器-数组",
          "content": "数组数组工具类 Arrays 继承自 org.apache.commons.lang3.ArrayUtils 封装了元素是否存在检测、元素索引位置获取、拼接成字符串、转换为 List、Set 以及字符串类型的数组、数组合并、数组元素操作等方法。检测数组 array 中是否存在元素 element：import com.buession.core.collect.Arrays;\nboolean result = Arrays.contains(array, element);\n返回元素 element 在数组 array 中第一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.indexOf(array, element);\n返回元素 element 在数组 array 中最后一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.lastIndexOf(array, element);\n将字符串拼接会字符串：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString result = Arrays.toString(array);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString glue = \"-\";\nString result = Arrays.toString(array, glue);\n// 1-2-3\n可以通过方法 toList、toSet 转换为 List 和 Set：import com.buession.core.collect.Arrays;import java.util.List;\nimport java.util.Set;\n\nint[] array = {1, 2, 3};\nList list = Arrays.toList(array);\nSet set = Arrays.toSet(array);\n将数组转换为字符串类型的数组：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString[] result = Arrays.toStringArray(array);\n将数组进行合并：import com.buession.core.collect.Arrays;\nString[] result = Arrays.toStringArray(array1, array2, array3);\n对数组元素进行操作：import com.buession.core.collect.Arrays;\nString[] array = {\"A\", \"B\", \"C\"};\nString[] result = Arrays.map(array, String.class, fn);\n第二个参数为数组元素类型，第三个参数为 java.util.function.Function 的实现"
        },
        {
          "title": "Lists",
          "url": "/manual/2.3/core/collect.html#收集器-lists",
          "content": "ListsList 工具类 Lists 实现了将元素拼接成字符串、转换为 Set 操作。将字符串拼接会字符串：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString result = Lists.toString(list);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Lists.toString(list);\n// 1-2-3\n可以通过方法 toSet 将 List 转换为 Set：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nSet set = Lists.toSet(list);\n"
        },
        {
          "title": "Sets",
          "url": "/manual/2.3/core/collect.html#收集器-sets",
          "content": "SetsSett 工具类 Sets 实现了将元素拼接成字符串、转换为 List 操作。将字符串拼接会字符串：import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString result = Sets.toString(set);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Sets.toString(list);\n// 1-2-3\n可以通过方法 toList 将 Set 转换为 List：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nList list = Sets.toList(set);\n"
        },
        {
          "title": "Maps",
          "url": "/manual/2.3/core/collect.html#收集器-maps",
          "content": "MapsMap 工具类 Maps 实现了对 key 和 value 进行操作，实现了将 value 转换为 List 和 Set。对 Map 进行操作：import com.buession.core.collect.Maps;import java.util.Map;\nimport java.util.HashMap;\n\nMap maps = new HashMap();\nMap result = Maps.map(maps, (key)->key, (value)->value == null ? null : value.toString());\n第二个、第三参数为 java.util.function.Function 的实现可以通过方法 toList 将 Map 的 value 转换为 List：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\n\nList list = Maps.toList(maps);\n可以通过方法 toSet 将 Map 的 value 转换为 Set：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.Set;\n\nSet set = Maps.toSet(maps);\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/collect.html#收集器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/context.html",
      "children": [
        {
          "title": "上下文",
          "url": "/manual/2.3/core/context.html#上下文",
          "content": "上下文注解 com.buession.core.context.stereotype.Manager 用于在分层应用中，标记当前类是一个 manager 类。类似于 org.springframework.stereotype.Service 加上该注解会将当前类自动注入到 spring 容器中，用法和 @Service 一样。在多层应用架构中 Manager 层通常为：通用业务处理层，处于 Dao 层之上，Service 层之下。主要特征如下：逻辑少\n与 Dao 层进行交互，多个 Dao 层的复用\nService 通用底层逻辑的下沉，如：缓存方案、中间件通用处理、以及对第三方平台封装的层\nimport com.buession.core.context.stereotype.Manager;import org.springframework.stereotype.Service;\n\npublic interface UserManager {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Manager\npublic class UserManagerImpl implements UserManager {\n\n\t@Autowired\n\tprivate UserDao userDao;\n\n\t@Autowired\n\tprivate UserProfileDao userProfileDao;\n\n\t@Autowired\n\tprivate RedisTemplate redisTemplate;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = redisTemplate.hGetObject(\"user\", Integer.toString(id), User.class);\n\n\t\tif(user == null){\n\t\t\tuser = userDao.getByPrimary(id);\n\t\t\tif(user != null){\n\t\t\t\tuser.setProfile(userProfileDao.getByUserId(id));\n\t\t\t\tredisTemplate.hSet(\"user\", Integer.toString(id), user);\n\t\t\t}else{\n\t\t\t\tthrow new RuntimeException(\"用户不存在\");\n\t\t\t}\n\t\t}\n\n\t\treturn user;\n\t}\n\n}\n\npublic interface UserService {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Service\npublic class UserServiceImpl implements UserService {\n\n\t@Autowired\n\tprivate UserManager userManager;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = userManager.getByPrimary(id);\n\n\t\t...\n\n\t\treturn user;\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/context.html#上下文-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "",
      "content": "",
      "url": "/manual/2.3/core/configurer.html",
      "children": [
        {
          "title": "配置器",
          "url": "/manual/2.3/core/configurer.html#配置器",
          "content": "配置器使用配置参数对对象进行配置。接口规范。@FunctionalInterfacepublic interface Configurer {\n\n\t/**\n\t * 使用配置参数 config 对对象 object 进行配置\n\t *\n\t * @param object\n\t * \t\t配置对象\n\t * @param config\n\t * \t\t配置参数\n\t */\n\tvoid configure(T object, C config);\n\n}\n示例：public class DefaultConfigurer implements Configurer> {\n\t@Override\n\tpublic void configure(final User user, final Map configs) {\n\t\tuser.setUsername(configs.get(\"name\"));\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/configurer.html#配置器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/customizer.html",
      "children": [
        {
          "title": "定制器",
          "url": "/manual/2.3/core/customizer.html#定制器",
          "content": "定制器使用源对象对目标对象进行定制。接口规范。@FunctionalInterfacepublic interface Customizer {\n\n\t/**\n\t * 定制\n\t *\n\t * @param source\n\t * \t\t源实例\n\t * @param target\n\t * \t\t待定制实例\n\t */\n\tvoid customize(S source, T target);\n\n}\n示例：public class DefaultCustomizer implements Customizer {\n\t@Override\n\tpublic void customize(final UserModel userModel, final UserVo userVo) {\n\t\tuserVo.setUsername(userModel.getUsername());\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/customizer.html#定制器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/converter.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.3/core/converter.html#构建器",
          "content": "构建器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。接口定义：@FunctionalInterfacepublic interface Converter {\n\n\tT convert(final S source);\n\n}\n将类似为 S 的对象转换为类型为 T 的对象。"
        },
        {
          "title": "内置转换器",
          "url": "/manual/2.3/core/converter.html#构建器-内置转换器",
          "content": "内置转换器\n\n转换器\n说明\n\n\n\n\nArrayConverter\n将 S 类型的数组转换为 T 类型的数组\n\n\nEnumConverter\n枚举转换器，将字符串转换为枚举 E\n\n\nBinaryEnumConverter\n枚举转换器，将 byte 数组转换为枚举 E\n\n\nBooleanStatusConverter\n将布尔值转换为 com.buession.lang.Status\n\n\nStatusBooleanConverter\n将 com.buession.lang.Status 转换为布尔值\n\n\nPredicateStatusConverter\n通过 java.util.function.Predicate 对某种数据类型的数据进行判断结果转换为 com.buession.lang.Status\n\n\nListConverter\n将 S 类型的 List 对象转换为 T 类型的 List 对象\n\n\nSetConverter\n将 S 类型的 Set 对象转换为 T 类型的 Set 对象\n\n\nMapConverter\n将 Key 为 SK 类型、值为 SV 类型的 Map 对象转换为 Key 为 TK 类型、值为 TV 类型的 Map\n\n\n将 key 为 Integer 类型，value 为 Object 类型的 Map 转换成 key 为 String 类型，value 为 String 类型的 Map 对象import com.buession.core.converter.MapConverter;import java.util.Map;\n\nMap source;\nMap target;\nMapConverter converter = new MapConverter();\n\ntarget = converter.convert(source);\n将字符串转换为枚举import com.buession.core.converter.EnumConverter;import com.buession.lang.Gender;\n\nGender target;\nEnumConverter converter = new EnumConverter(Gender.class);\n\ntarget = converter.convert(\"FEMALE\");\n// Gender.FEMALE\n\ntarget = converter.convert(\"F\");\n// null\n"
        },
        {
          "title": "POJO 类映射",
          "url": "/manual/2.3/core/converter.html#构建器-pojo-类映射",
          "content": "POJO 类映射我们通过 com.buession.core.converter.mapper.Mapper 接口约定了，基于 mapstruct POJO 类的映射接口规范。关于 mapstruct 的更多文章，可通过搜索引擎自行搜索。public interface Mapper {\n\t/**\n\t * 将源对象映射到目标对象\n\t *\n\t * @param object\n\t * \t\t源对象\n\t *\n\t * @return 目标对象实例\n\t */\n\tT mapping(S object);\n\n\t/**\n\t * 将源对象数组映射到目标对象数组\n\t *\n\t * @param object\n\t * \t\t源对象数组\n\t *\n\t * @return 目标对象实例数组\n\t */\n\tT[] mapping(S[] object);\n\n\t/**\n\t * 将源 list 对象映射到目标 list 对象\n\t *\n\t * @param object\n\t * \t\t源 list 对象\n\t *\n\t * @return 目标对象 list 实例\n\t */\n\tList mapping(List object);\n\n\t/**\n\t * 将源 set 对象映射到目标 set 对象\n\t *\n\t * @param object\n\t * \t\t源 set 对象\n\t *\n\t * @return 目标对象 set 实例\n\t */\n\tSet mapping(Set object);\n\n}\n我们还可以使用工具类 com.buession.core.converter.mapper.PropertyMapper 将值从提供的源映射到目标，此方法来拷贝并简单修改于：springboot 中的 org.springframework.boot.context.properties.PropertyMapper，和原生 springboot 中的用法一样；并在此基础上增加了方法 alwaysApplyingWhenHasText()，用于判断映射源是否为 null 或者是否含有字符串。import com.buession.core.converter.mapper.PropertyMapper;\nUser source = new User();\nUser target = new User();\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();\npropertyMapper.form(source::getId).to(target:setId)\n// null\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();\npropertyMapper.form(source::getUsername).to(target:setUsername)\n// null\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/converter.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/datetime.html",
      "children": [
        {
          "title": "日期时间",
          "url": "/manual/2.3/core/datetime.html#日期时间",
          "content": "日期时间日期、时间工具。目前，仅有少量的 API，参考 PHP 中的日期时间函数而来。获取当前 Unix 时间戳（秒）：import com.buession.core.datetime.DateTime;\nDateTime.unixtime();\n该方法我们在实际业务中经常用到。以 \"msec sec\" 的格式返回当前 Unix 时间戳和微秒数：import com.buession.core.datetime.DateTime;\nDateTime.microtime();\n// 1657171717 948000\n该方法参考 PHP 的 microtime 函数而来。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/datetime.html#日期时间-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/id.html",
      "children": [
        {
          "title": "ID 生成器",
          "url": "/manual/2.3/core/id.html#id-生成器",
          "content": "ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。您可以通过 buession framework 提供的 ID 生成器，生成唯一 ID。接口规范。public interface IdGenerator {\n\t/**\n\t * 获取下一个 ID\n\t *\n\t * @return ID\n\t */\n\tT nextId();\n\n}\n"
        },
        {
          "title": "ID 生成器",
          "url": "/manual/2.3/core/id.html#id-生成器-id-生成器",
          "content": "ID 生成器\n\n生成器\n说明\n\n\n\n\nAtomicSimpleIdGenerator\n基于 AtomicLong 递增的，简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nAtomicUUIDIdGenerator\n基于 AtomicLong 递增的，UUID ID 生成器\n\n\nNanoIDIdGenerator\njnanoid ID 生成器，可通过构造函数指定字符范围、长度\n\n\nRandomDigitIdGenerator\n随机数 ID 生成器，返回指定范围内的随机数，默认为 1L ~ Long.MAX_VALUE，可通过构造函数指定\n\n\nRandomIdGenerator\n随机字符 ID 生成器，可通过构造函数指定生成长度，默认 16 位\n\n\nSimpleIdGenerator\n简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nSnowflakeIdGenerator\n雪花算法 ID 生成器，此处不解决时钟回拨的问题，您可以通过构造函数指定开始时间、数据中心 ID、数据中心 ID 所占的位数等等值\n\n\nUUIDIdGenerator\nUUID ID 生成器\n\n\nimport com.buession.core.id.AtomicUUIDIdGenerator;import com.buession.core.id.NanoIDIdGenerator;\nimport com.buession.core.id.SnowflakeIdGenerator;\nimport com.buession.core.id.UUIDIdGenerator;\nimport com.buession.core.id.SimpleIdGenerator;\n\nAtomicUUIDIdGenerator idGenerator = new AtomicUUIDIdGenerator();\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000001\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000002\n\nNanoIDIdGenerator idGenerator = new NanoIDIdGenerator();\nidGenerator.nextId(); // omRdTPCug5z_Uk1E_x3ozu3Avyyl3XSK\n\nSnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();\nidGenerator.nextId(); // 170602258864545792\n\nUUIDIdGenerator idGenerator = new UUIDIdGenerator();\nidGenerator.nextId(); // 8634a166-e7d6-4160-85eb-3433107de5a4\n\nSimpleIdGenerator idGenerator = new SimpleIdGenerator();\nidGenerator.nextId(); // 26d9ed57fdad443894b988eeabc69c05\n注：关于雪花算法、jnanoid 算法的可自行搜索。\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/id.html#id-生成器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/math.html",
      "children": [
        {
          "title": "数学函数",
          "url": "/manual/2.3/core/math.html#数学函数",
          "content": "数学函数定义了实用的数学函数。\n\n方法\n说明\n\n\n\n\ncontinuousSum\n计算两个数之间连续相加之和\n\n\nrangeValue\n获取合法范围内的值，如果 value 小于 min，则返回 min；如果 value 大于 max，则返回 max；否则返回 value 本身\n\n\nimport com.buession.core.math.Math;\nlong result = Math.continuousSum(1, 100);\n// 5050\nimport com.buession.core.math.Math;\nlong value = 3;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 4\nimport com.buession.core.math.Math;\nlong value = 5;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 5\nimport com.buession.core.math.Math;\nlong value = 11;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 10\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/math.html#数学函数-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/serializer.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.3/core/serializer.html#构建器",
          "content": "构建器将对象序列化为二进制或 JSON。您可以通过该 API，实现将对象序列化成二进制或 JSON 字符串。"
        },
        {
          "title": "序列化、反序列化类",
          "url": "/manual/2.3/core/serializer.html#构建器-序列化、反序列化类",
          "content": "序列化、反序列化类\n\n类\n说明\n\n\n\n\nDefaultByteArraySerializer\n将对象序列化为二进制\n\n\nFastJsonJsonSerializer\n基于 FastJSON 的对象与 JSON 之间的序列化\n\n\nGsonJsonSerializer\n基于 Gson 的对象与 JSON 之间的序列化\n\n\nJacksonJsonSerializer\n基于 Jackson2 的对象与 JSON 之间的序列化\n\n\nDefaultByteArraySerializer 序列化成字符串，逻辑是在对象序列化为 byte 数组后，通过 URLEncoder.encode 进行编码\nFastJsonJsonSerializer、GsonJsonSerializer、JacksonJsonSerializer 可以通过参数 Class、TypeReference 指定返回的对象类型\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/serializer.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/deserializer.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.3/core/deserializer.html#构建器",
          "content": "构建器将二进制或 JSON 反序列化为对象。您可以通过该 API，实现将将二进制、JSON 字符串反序列化为对象。"
        },
        {
          "title": "序列化、反序列化类",
          "url": "/manual/2.3/core/deserializer.html#构建器-序列化、反序列化类",
          "content": "序列化、反序列化类\n\n类\n说明\n\n\n\n\nDefaultByteArrayDeserializer\n将对象序列化为二进制，或将二进制反序列化为对象\n\n\nFastJsonJsonDeserializer\n基于 FastJSON 的对象与 JSON 之间的序列化和反序列化\n\n\nGsonJsonDeserializer\n基于 Gson 的对象与 JSON 之间的序列化和反序列化\n\n\nJacksonJsonDeserializer\n基于 Jackson2 的对象与 JSON 之间的序列化和反序列化\n\n\nDefaultByteArrayDeserializer 通过 URLDecoder.decode 进行解码成 byte 数组，再反序列化为对象\n在将 JSON 字符串反序列化为对象时，默认返回类型依据 fastjson、jackson 等原生逻辑\nFastJsonJsonDeserializer、GsonJsonDeserializer、JacksonJsonDeserializer 可以通过参数 Class、TypeReference 指定返回的对象类型\ncom.buession.core.type.TypeReference 是某类型的一个指向或者引用，用于屏蔽 fastjson、gson、jackson 中通过 JDK Type 指定反序列化的类型；在 fastjson、gson 中是直接指定 Type，在 jackson 中是通过 com.fasterxml.jackson.core.type.TypeReference 类返回\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/deserializer.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/validator.html",
      "children": [
        {
          "title": "验证器",
          "url": "/manual/2.3/core/validator.html#验证器",
          "content": "验证器数据验证器及其注解。该 API 提供了大量通用的适用于本土化的常用验证方法，如：判断是否为 null、判断是否不为 null、判断（字符串、数组、集合、Map等）是否为空、判断（字符串、数组、集合、Map等）是否不为空、IP 地址合法性验证、ISBN 合法性验证、身份证号码验证、QQ 验证。并提供对应的基于 javax.validation 的校验注解。验证是否为 null判断任意对象是否为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNull(obj);\n验证是否不为 null判断任意对象是否不为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNotNull(obj);\n判断字符串是否为空白字符串判断字符串是否为空白字符串，为 null 或长度为 0 时也返回 true；其余情况，字符串中含有任意可打印字符串则为 falseimport com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\n\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\na\";\nboolean result = Validate.isBlank(str); // false\n注：isNotBlank 与之相反\n判断是否为空isEmpty 可判断字符串、数组、集合、Map 是否为空，即：为 null 或长度/大小为 0 时，表示为空import com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isEmpty(str); // true\n\nString str = \" \";\nboolean result = Validate.isEmpty(str); // false\n\nboolean result = Validate.isEmpty(new String[]{}); // true\n\nboolean result = Validate.isEmpty(new Integer[]{1}); // false\n注：isNotEmpty 与之相反\n判断是否在两个数之间isBetween 可判断一个整型或浮点型，是否在两个整型或者浮点型数字之间import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3); // true\n\nboolean result = Validate.isBetween(2, 2, 3); // false\n可通过参数设置是否包含边界值import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3, true); // true\n\nboolean result = Validate.isBetween(2, 2, 3, true); // true\n判断是否为电话号码isTel 可判断一个字符串是否为电话号码，仅支持普通座机号码，不支持 110、400、800等特殊号码。格式，需符合：86-区号-电话号码、区号-电话号码、（区号）电话号码、(区号)电话号码、电话号码。可通过参数 com.buession.core.validator.routines.TelValidator.AreaCodeType 指定区号的控制策略。仅支持中国的电话号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isTel(\"028-12345678\"); // true\n\nboolean result = Validate.isTel(\"028-02345678\"); // false\n判断是否为手机号码isMobile 可判断一个字符串是否为手机号码。仅支持中国的手机号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isMobile(\"028-12345678\"); // false\n\nboolean result = Validate.isMobile(\"13800138000\"); // true\n判断是否为邮政编码isPostCode 可判断一个字符串是否为邮政编码。仅支持中国的邮政编码。import com.buession.core.validator.Validate;\nboolean result = Validate.isPostCode(\"043104\"); // false\n\nboolean result = Validate.isPostCode(\"643104\"); // true\n判断是否为 QQ 号码isQQ 可判断一个字符串是否为 QQ 号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isQQ(\"043104\"); // false\n\nboolean result = Validate.isQQ(\"25132.141\"); // true\n判断是否为身份证号码isIDCard 可判断一个字符串是否合法的身份证号码。仅支持 18 位的身份证号码。身份证号码需符合其身份证号码编排规律。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n可以和身份证号码进行比对，其实该方法的实际作用不是很大，只是在业务系统里面有需要填写身份证号码和出生日期时，简化验证出生日期和身份证号码中的出生日期是否一致。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\", true, \"2.10-01-01\");\n其它，更多方法可以参考手册。"
        },
        {
          "title": "注解",
          "url": "/manual/2.3/core/validator.html#验证器-注解",
          "content": "注解javax 的 validation 是 Java 定义的一套基于注解的数据校验规范，buession framework 基于 javax.validation 实现了 Validate 中所有验证方法的校验注解。\n\n注解\n验证的数据类型\n说明\n\n\n\n\n@Alnum\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Alpha\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Numeric\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Between\nshort、int、double 等任何 Number 的子类型\n验证注解的元素值是否为在两个数之间\n\n\n@Empty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否为空\n\n\n@NotEmpty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否不为空\n\n\n@HasText\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@IDCard\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Ip\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Isbn\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@MimeType\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Mobile\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Null\n任意类型\n验证注解的元素值是否为 null\n\n\n@NotNull\n任意类型\n验证注解的元素值是否为 null\n\n\n@Port\nInteger\n验证注解的元素值是否为 null\n\n\n@PostCode\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@QQ\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Tel\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Xdigit\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/validator.html#验证器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/utils.html",
      "children": [
        {
          "title": "工具类",
          "url": "/manual/2.3/core/utils.html#工具类",
          "content": "工具类常用通用性工具类。"
        },
        {
          "title": "Byte 数组比较",
          "url": "/manual/2.3/core/utils.html#工具类-byte-数组比较",
          "content": "Byte 数组比较ByteArrayComparable 类为 java Comparable 的实现，实现了 byte 数组的比较。"
        },
        {
          "title": "注解工具",
          "url": "/manual/2.3/core/utils.html#工具类-注解工具",
          "content": "注解工具AnnotationUtils 继承 org.springframework.core.annotation.AnnotationUtils ，在此基础上新增了方法 hasClassAnnotationPresent(final Class clazz, final Class[] annotations)、hasMethodAnnotationPresent(Method method, final Class[] annotations) 判断类或者方法上是否有待检测的注解中的其中一个注解。"
        },
        {
          "title": "断言",
          "url": "/manual/2.3/core/utils.html#工具类-断言",
          "content": "断言Assert 和 springframework 中的注解类似，不过逻辑有些相反。"
        },
        {
          "title": "Byte 工具",
          "url": "/manual/2.3/core/utils.html#工具类-byte-工具",
          "content": "Byte 工具ByteUtils 可以将 byte 数组转换为 char 或者 char 数组。import com.buession.core.utils.ByteUtils;\nbyte[] bytes;\nchar c = ByteUtils.toChar(bytes);\n\nchar[] chars = ByteUtils.toChar(bytes);\nbyte 数组连接。import com.buession.core.utils.ByteUtils;\nbyte[] dest;\nbyte[] source\nbyte[] result = ByteUtils.concat(dest, source);\n"
        },
        {
          "title": "Character 工具",
          "url": "/manual/2.3/core/utils.html#工具类-character-工具",
          "content": "Character 工具CharacterUtils 继承 org.apache.commons.lang3.CharUtils，可以将 char、char 数组转换为 byte 数组。import com.buession.core.utils.CharacterUtils;\nchar c;\nbyte[] bytes = ByteUtils.toBytes(c);\n\nchar[] chars;\nbyte[] bytes = ByteUtils.toBytes(chars);\n"
        },
        {
          "title": "数字工具",
          "url": "/manual/2.3/core/utils.html#工具类-数字工具",
          "content": "数字工具NumberUtils 提供了对数字相关的操作。\n\n方法\n说明\n\n\n\n\nint2bytes\n将 int 转换为 byte[]\n\n\nbytes2int\n将 byte[] 转换为 int\n\n\nlong2bytes\n将 long 转换为 byte[]\n\n\nbytes2long\n将 byte[] 转换为 long\n\n\nfloat2bytes\n将 float 转换为 byte[]\n\n\nbytes2float\n将 byte[] 转换为 float\n\n\ndouble2bytes\n将 double 转换为 byte[]\n\n\nbytes2double\n将 byte[] 转换为 double\n\n\n"
        },
        {
          "title": "字符串工具",
          "url": "/manual/2.3/core/utils.html#工具类-字符串工具",
          "content": "字符串工具StringUtils 继承了 org.apache.commons.lang3.StringUtils，封装了多字符串更多的操作。截取字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.substr(\"abcde\", 1); // bcde\nString result = StringUtils.substr(\"abcde\", 1, 2); // bc\n生成随机字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.random(length);\n比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 3); // true\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 4); // false\n忽略大小写比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"Abce\", 3); // true\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"aBce\", 4); // false\n"
        },
        {
          "title": "拼音工具",
          "url": "/manual/2.3/core/utils.html#工具类-拼音工具",
          "content": "拼音工具PinyinUtils 封装了获取中文拼音、拼音首字母的方法。import com.buession.core.utils.PinyinUtils;\nString result = PinyinUtils.getPinyin(\"中国\"); // zhongguo\nString result = PinyinUtils.getPinYinFirstChar(\"中国\"); // zg\n"
        },
        {
          "title": "随机数工具",
          "url": "/manual/2.3/core/utils.html#工具类-随机数工具",
          "content": "随机数工具RandomUtils 封装了随机数的生成。\n\n方法\n说明\n\n\n\n\nnextBoolean\n随机布尔值\n\n\nnextBytes\n随机字节数组\n\n\nnextInt\n生成指定范围内的 int 值，默认：0 到 Integer.MAX_VALUE\n\n\nnextLong\n生成指定范围内的 long 值，默认：0 到 Long.MAX_VALUE\n\n\nnextFloat\n生成指定范围内的 float 值，默认：0 到 Float.MAX_VALUE\n\n\nnextDouble\n生成指定范围内的 double 值，默认：0 到 Double.MAX_VALUE\n\n\n"
        },
        {
          "title": "Properties 工具",
          "url": "/manual/2.3/core/utils.html#工具类-properties-工具",
          "content": "Properties 工具PropertiesUtils 封装了对 Properties 的操作，主要是 Properties.getProperty 的值为字符串，而我们在实际场景中，很多时候其值可能为 int、double。传统方法是，我们在代码中通过 Properties.getProperty 获取其值后，对其进行转换；而 PropertiesUtils 简化了操作。import com.buession.core.utils.SystemPropertyUtils;\nInteger result = PropertiesUtils.getInteger(properties, key);\nBoolean result = PropertiesUtils.getBoolean(properties, key);\n"
        },
        {
          "title": "System Property 工具",
          "url": "/manual/2.3/core/utils.html#工具类-system-property-工具",
          "content": "System Property 工具SystemPropertyUtils 封装了系统属性或系统环境变量的操作。设置属性方法 setProperty 对 System.setProperty 的封装，唯一区别是：SystemPropertyUtils.setProperty 的值可以为非字符串，该方法类会将非字符串值转换为字符串再调用 System.setProperty。import com.buession.core.utils.SystemPropertyUtils;\nSystemPropertyUtils.setProperty(\"http.port\", 8080);\nSystemPropertyUtils.setProperty(\"http.ssl.enable\", false);\n获取属性方法 getProperty 会先通过 System.getProperty 进行获取，若未获取到值，再调用 System.getenv 进行获取。String value = System.getProperty(name);\nif(Validate.hasText(value) == false){\n  value = System.getenv(name);\n}\n"
        },
        {
          "title": "版本工具",
          "url": "/manual/2.3/core/utils.html#工具类-版本工具",
          "content": "版本工具VersionUtils 提供了对两个版本值的比较方法和获取类、jar 对应的版本。import com.buession.core.utils.VersionUtils;\nVersionUtils.compare(\"1.0.0\", \"1.0.1-beta\"); // -1\nVersionUtils.compare(\"1.0.0\", \"1.0.0r\"); // -1\n规则：dev < alpha = a < beta = b < rc < release < pl = p < 纯数字版本获取类的版本值import com.buession.core.utils.VersionUtils;\nByteUtils.determineClassVersion(com.buession.core.utils.VersionUtils.class); // 2.3.0\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/utils.html#工具类-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/other.html",
      "children": [
        {
          "title": "其它",
          "url": "/manual/2.3/core/other.html#其它",
          "content": "其它通用的接口定义，框架自身类，以及其它杂项。"
        },
        {
          "title": "框架自身工具",
          "url": "/manual/2.3/core/other.html#其它-框架自身工具",
          "content": "框架自身工具获取 Buession Framework 版本：import com.buession.core.Framework;import com.buession.core.BuesssionFrameworkVersion;\n\nBuesssionFrameworkVersion.getVersion(); // 2.3.0\nFramework.VERSION; // 2.3.0\n获取 Buession Framework 框架名称：import com.buession.core.Framework;\nFramework.NAME; // \"Buession\"\n"
        },
        {
          "title": "命令执行器",
          "url": "/manual/2.3/core/other.html#其它-命令执行器",
          "content": "命令执行器命令执行器接口：/** * 命令执行器\n *\n * @param \n * \t\t命令上下文\n * @param \n * \t\t命令执行返回值\n */\n@FunctionalInterface\npublic interface Executor {\n\n\t/**\n\t * 命令执行\n\t *\n\t * @param context\n\t * \t\t命令执行器上下文\n\t *\n\t * @return 命令执行返回值，R 类型的实例\n\t */\n\tR execute(C context);\n\n}\n您可以通过，该接口执行一个命令上下文的方法，并返回一个值。该方法有些类似代理模式的代理类。"
        },
        {
          "title": "销毁接口",
          "url": "/manual/2.3/core/other.html#其它-销毁接口",
          "content": "销毁接口功能类似 java.io.Closeable。public interface Destroyable {\n\t/**\n\t * 销毁相关资源\n\t *\n\t * @throws IOException\n\t * \t\tIO 错误时抛出\n\t */\n\tvoid destroy() throws IOException;\n\n}\n"
        },
        {
          "title": "Rawable",
          "url": "/manual/2.3/core/other.html#其它-rawable",
          "content": "Rawable原始的，约定实现该接口的类，必须返回原始字节数组。public interface Rawable {\n\t/**\n\t * 返回原始的字节数组\n\t *\n\t * @return 原始的字节数组\n\t */\n\tbyte[] getRaw();\n\n}\n"
        },
        {
          "title": "名称节点",
          "url": "/manual/2.3/core/other.html#其它-名称节点",
          "content": "名称节点名称节点，约定实现该接口的类应该返回一个名称public interface NamedNode {\n\t/**\n\t * 返回节点名称\n\t *\n\t * @return 节点名称\n\t */\n\t@Nullable\n\tString getName();\n\n}\n"
        },
        {
          "title": "分页",
          "url": "/manual/2.3/core/other.html#其它-分页",
          "content": "分页com.buession.core.Pagination 为一个分页 POJO 类，包括了当前页码、每页大小、上一页、下一页、总页数、总记录数、数据列表。能够根据设定的其中一个值，计算另外的值。"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.3/core/exception.html",
      "children": [
        {
          "title": "异常",
          "url": "/manual/2.3/core/exception.html#异常",
          "content": "异常通用异常的定义。\n\n异常\n说明\n\n\n\n\nAccessException\n拒绝访问异常\n\n\nClassInstantiationException\n类实例化异常\n\n\nConversionException\n数据类型转换异常\n\n\nDataAlreadyExistException\n数据已存在异常\n\n\nDataNotFoundException\n数据不存在或未找到异常\n\n\nInsteadException\n类方法废弃后，需要使用其它类库方法来替代\n\n\nNestedRuntimeException\n嵌套运行时异常\n\n\nOperationException\n运算异常\n\n\nPresentException\n--\n\n\nSerializationException\n序列化异常\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/exception.html#异常-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-cron 参考手册",
      "content": "对 quartz 的二次封装",
      "url": "/manual/2.3/cron/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/cron/index.html#安装",
          "content": "安装    com.buession\n    buession-cron\n    x.x.x\n\n由于在过去的工作中，定时任务基本使用 quartz 来实现；但是在初始化定时任务项目时，大量基本相同的代码，因此对此部分做了二次封装，简化了 quartz 项目的初始化。由于在现在有众多优秀的分布式定时任务，如：elastic-job、xxl-job 等等，因此直接使用 quartz 应该会越来越少（个人主观猜测），即使使用 quartz 初始化也简单，故该模块将不做说明。且在今后的版本中，该模块可能会被废弃。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/cron/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装。从代码层面实现了数据库的读写分离，insert、update、delete 操作主库，select 操作从库。",
      "url": "/manual/2.3/dao/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/dao/index.html#安装",
          "content": "安装    com.buession\n    buession-dao\n    x.x.x\n\n我们咋众多项目中，基本有常见的重复的对数据库的 CURD 操作，比如：根据主键查询数据、根据主键删除数据、获取一条记录。MyBatis 是一款优秀的持久层框架，应用广泛。MongoDB 是一款优秀的文档数据库。我自己根据从业多年的经验，从实际场合出发，将在业务层对数据库的常用操作进行了封装。对关系型数据库基于 MyBatis 二次封装，对 MongoDB 基于 spring-data-mongodb；在未来也许会考虑，增加 jpa 和 JdbcTemplate 对关系型数据库的二次封装。同时，我们在代码层面实现了数据库的读写分离。我们没有改变 MyBatis 和 spring-data-mongodb 的任何底层逻辑，本质就是 MyBaits 和 spring-data-mongodb；我们唯一做了的就是，定义和是了大家在应用程序中常用的方法，让您不在重复去编写该部分代码；以及在代码层面实现了数据的读写分离。"
        },
        {
          "title": "Dao 接口",
          "url": "/manual/2.3/dao/index.html#dao-接口",
          "content": "Dao 接口接口定义，可见：https://javadoc.io/static/com.buession/buession-dao/2.3.0/com/buession/dao/Dao.htmlpublic interface Dao {}\nP：主键类型\nE：实体类\n分页对象 com.buession.dao.Pagination 继承自 com.buession.core.Pagination，增加了偏移量属性 offset。条件为 Map 类型，允许为 null。排序为 Map 类型，允许为 null。MyBatisBuession Framework 扩展 MyBatis 的文档。MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/dao/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.3/dao/mybatis.html",
      "children": [
        {
          "title": "MyBatis",
          "url": "/manual/2.3/dao/mybatis.html#mybatis",
          "content": "MyBatisBuession Framework 扩展 MyBatis 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.3/dao/mybatis.html#mybatis-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMyBatisDao；且存在 bean 名为 masterSqlSessionTemplate、slaveSqlSessionTemplates 的 bean 实例。masterSqlSessionTemplate 操作主库，实现插入、更新、删除操作；slaveSqlSessionTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveSqlSessionTemplate() 在所有的 slave templates 中随机返回一个 slave SqlSessionTemplate bean 实例。当然，您也可以通过 getSlaveSqlSessionTemplate(final int index) 指定索引的 slave SqlSessionTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave SqlSessionTemplate bean 实例列表，将会返回 master SqlSessionTemplate bean 实例，buession framework 屏蔽了这些技术细节。"
        },
        {
          "title": "Mybatis 约定",
          "url": "/manual/2.3/dao/mybatis.html#mybatis-mybatis-约定",
          "content": "Mybatis 约定如果集成 AbstractMyBatisDao 类，必须重写方法 getStatement()，通过此方法返回每个 Mapper namespace\nnamespace com.buession.dao.test.dao;\npublic class UserDaoImpl extends AbstractMyBatisDao {\n\n\t@Override\n\tprotected String getStatement(){\n\t\treturn \"com.buession.dao.test.dao.UserMapper\";\n\t}\n\n}\n\n\nMapper 的 SQL ID 和方法名保持一致\n\n\nSQL ID\n说明\n返回值\n\n\n\n\ninsert\n插入数据\n影响的行数\n\n\nbatchInsert\n批量插入数据，默认循环插入；您可以重写该方法实现 SQL 批量插入\n每次插入影响的行数列表\n\n\nreplace\n替换数据，即：REPLACE 语句\n影响的行数\n\n\nbatchReplace\n批量替换数据，即：REPLACE 语句\n每次替换数据影响的行数列表\n\n\nupdate\n更新数据\n更新条数\n\n\nupdateByPrimary\n根据主键更新数据，注：主键参数值是会覆盖实体类主键参数对应的类属性的值\n更新条数\n\n\ngetByPrimary\n根据主键查询数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselectOne\n（根据条件）获取一条数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselect\n查询数据\n数据结果列表\n\n\ngetAll\n查询所有数据\n数据结果列表\n\n\ncount\n获取记录数\n记录数\n\n\ndeleteByPrimary\n根据主键删除数据\n影响条数\n\n\ndelete\n删除数据\n影响条数\n\n\nclear\n清除数据\n影响条数\n\n\ntruncate\n截断数据\n影响条数\n\n\n注：要实现分页，必须实现 count，且和 select 的查询条件必须一致。因为，在分页方法中，首先会执行 count ，查询指定条件的记录数；如果记录数大于 0 时，才会执行 select 查询数据。在后续的开发中，我们将会使用拦截器实现。\n以上 SQL ID，只是一种约定，具体会呈现一种什么样的效果，还是完全屈居于您的 SQL 语句。\n"
        },
        {
          "title": "Mybatis 类型处理器",
          "url": "/manual/2.3/dao/mybatis.html#mybatis-mybatis-类型处理器",
          "content": "Mybatis 类型处理器MyBatis 自身提供大量优秀的类型处理器 TypeHandler，但任然不足。我们在此基础上扩展了一些 TypeHandler。名称空间为 org.apache.ibatis.type，不是 com.buession.dao。\n\nTypeHandler\n说明\n\n\n\n\nDefaultEnumTypeHandler\n默认 Enum 类型处理器，将值直接转换为枚举字段\n\n\nIgnoreCaseEnumTypeHandler\n忽略大小写 Enum 类型处理器，将值忽略大小写转换为枚举字段\n\n\nDefaultJsonTypeHandler\nJSON 处理器，将 JSON 格式的字符串值和类型  进行转换\n\n\nDefaultSetEnumTypeHandler\n默认 Enum 型 Set 类型处理器，将值直接转换为枚举字段作为 Set 的元素\n\n\nIgnoreCaseSetEnumTypeHandler\n忽略大小写 Enum 型 Set 类型处理器，将值忽略大小写转换为枚举字段作为 Set 的元素\n\n\nDefaultSetTypeHandler\n默认 Set 类型处理器，将值以 \",\" 拆分转换为 Set\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/dao/mybatis.html#mybatis-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.3/dao/mongodb.html",
      "children": [
        {
          "title": "MongoDB",
          "url": "/manual/2.3/dao/mongodb.html#mongodb",
          "content": "MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.3/dao/mongodb.html#mongodb-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMongoDBDao；且存在 bean 名为 masterMongoTemplate、slaveMongoTemplates 的 bean 实例。masterMongoTemplate 操作主库，实现插入、更新、删除操作；slaveMongoTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveMongoTemplate() 在所有的 slave templates 中随机返回一个 MongoTemplate bean 实例。当然，您也可以通过 getSlaveMongoTemplate(final int index) 指定索引的 slave MongoTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave MongoTemplate bean 实例列表，将会返回 master MongoTemplate bean 实例，buession framework 屏蔽了这些技术细节。AbstractMongoDBDao 的 replace 执行的也是 insert。在对 MongoDB 的操作条件中 value 可以为 com.buession.dao.MongoOperation，可以通过该类的方法控制条件等于值、大于值、小于值、LIKE值 等等运算。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/dao/mongodb.html#mongodb-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-geoip 参考手册",
      "content": "对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息。",
      "url": "/manual/2.3/geoip/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/geoip/index.html#安装",
          "content": "安装    com.buession\n    buession-geoip\n    x.x.x\n\n通常我们在应用中对用户注册、登录、以及其它操作记录 IP，我们更希望知道用户在什么城市进行的操作，如：微信公众号的内容发表于、微博的发布于等等，对于用户行为的安全审计等等有着极高的作用。geoip 在基于 maxmind geoip2 的基础上进行了二次封装，可以根据 IP（字符串形式的 IP，如：114.114.114.114、2.11:0DB8:0000:0023:0008:0800:2.1C:417A ，IPV4 的数字表示：3739974408，java InetAddress）获取其 IP 地址的国家信息、城市信息、位置信息。"
        },
        {
          "title": "获取国家信息",
          "url": "/manual/2.3/geoip/index.html#获取国家信息",
          "content": "获取国家信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nCountry country = resolver.country(\"114.114.114.114\");\n// Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}\n\nCountry country = resolver.country(3739974408L); // 3739974408L => 222.235.123.8\n// Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}\n"
        },
        {
          "title": "获取城市信息",
          "url": "/manual/2.3/geoip/index.html#获取城市信息",
          "content": "获取城市信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nDistrict district = resolver.district(\"114.114.114.114\");\n// District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}\n\nDistrict district = resolver.district(3739974408L); // 3739974408L => 222.235.123.8\n// District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}\n"
        },
        {
          "title": "获取位置信息",
          "url": "/manual/2.3/geoip/index.html#获取位置信息",
          "content": "获取位置信息位置信息中包括了该 IP 比较全面的信息，包括：城市信息、国家信息、洲信息、经纬度、机构信息、时区等。import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nLocation location = resolver.location(\"114.114.114.114\");\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}, district=District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}, traits=Traits{ipAddress='114.114.114.114', domain='null', isp='null', network=114.114.0.0/16, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=32.1617, longitude=118.7778, accuracyRadius=50}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Shanghai\",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null]}\n\nLocation location = resolver.location(3739974408L); // 3739974408L => 222.235.123.8\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}, district=District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}, traits=Traits{ipAddress='222.235.123.8', domain='null', isp='null', network=222.235.120.0/21, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=37.5111, longitude=126.9743, accuracyRadius=2.1}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Seoul\",offset=32.10000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null]}\n"
        },
        {
          "title": "缓存",
          "url": "/manual/2.3/geoip/index.html#缓存",
          "content": "缓存为了提高数据的处理能力，可以将获取过的数据缓存起来，下次获取同一 IP 信息时，可以直接从缓存中返回。您可以通过 DatabaseResolver 构造函数中的参数 cache 设置为 com.maxmind.db.NodeCache 的实现类即可，或直接使用类 CacheDatabaseResolver解析。我们默认使用 maxmind 内置的 CHMCache 来实现缓存，它是基于 ConcurrentHashMap 的内存缓存。"
        },
        {
          "title": "Resolver 的 Spring Factory Bean",
          "url": "/manual/2.3/geoip/index.html#resolver-的-spring-factory-bean",
          "content": "Resolver 的 Spring Factory Bean我们内置了 geoip 的 Resolver spring factory bean 类 GeoIPResolverFactoryBean，您可以通过它在您的 spring 项目中，初始化 Resolver 的实现类为 spring bean 对象。dbPath 和 stream 二选一即可，一个是指定 IP 的文件路径，一个是指定已加载的 IP 库的文件流。都不设置的默认以流的形式加载 buession-geoip 中的 IP 库文件。\nenableCache 可以控制是否缓存。\n"
        },
        {
          "title": "关于 IP 库",
          "url": "/manual/2.3/geoip/index.html#关于-ip-库",
          "content": "关于 IP 库buession-geoip 中包含了 maxmind 免费的 IP 所属城市和国家的库。由于在 jar 包中该数据库无法做到及时更新，在实际应用中，我们建议您从 maxmind 官网下载 IP 方法您的应用中，通过 DatabaseResolver 的构造函数指定 IP 库路径，这么做的好处是：在您的应用程序中，可以去保证 IP 库是更新的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/geoip/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-git 参考手册",
      "content": "获取项目 GIT 信息。",
      "url": "/manual/2.3/git/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/git/index.html#安装",
          "content": "安装    com.buession\n    buession-git\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/git/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节，屏蔽了对 post form、post json 等等的技术细节。",
      "url": "/manual/2.3/httpclient/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/httpclient/index.html#安装",
          "content": "安装    com.buession\n    buession-httpclient\n    x.x.x\n\n我们在应用中使用 Http Client 功能时，经常因为从 apache httpcomponents 切换为 okhttp3，或者从 okhttp3 切换为 apache httpcomponents，需要改动大量的代码而烦恼。而当您使用了 buession-httpclient 时，该类库为您解决了这些烦恼，通过顶层设计，屏蔽了 apache httpcomponents 和 okhttp3 的细节，当您需要从一个 http 库更换为另外一个 http 库时，您只需要在 pom.xml 中引用不同的包，修改一下 httpclient 的初始化类和连接管理器即可。传统的方式：    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\nimport org.apache.http.HttpResponse;import org.apache.http.conn.HttpClientConnectionManager;\nimport org.apache.http.client.HttpClient;\nimport org.apache.http.impl.client.HttpClientBuilder;\nimport org.apache.http.client.methods.HttpPost;\n\nHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(new HttpClientConnectionManager()).build();\n\nHttpResponse response = httpClient.execute(new HttpPost(\"https://www.buession.com/\"));\n或者    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport okhttp3.HttpClientConnectionManager;import okhttp3.OkHttpClient;\nimport okhttp3.ConnectionPool;\nimport okhttp3.Request;\nimport okhttp3.Request.Builder;\nimport okhttp3.Response;\n\nOkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(new ConnectionPool());\nHttpClient httpClient = builder.build();\n\nBuilder requestBuilder = new Builder().post();\nrequestBuilder.url(\"https://www.buession.com/\");\nRequest okHttpRequest = requestBuilder.build();\n\nResponse httpResponse = httpClient.newCall(okHttpRequest).execute();\n现在，您只需要通过 buession-httpclient，即可屏蔽其中的细节。    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\n或者    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.OkHttpHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.conn.OkHttpClientConnectionManager;\nimport com.buession.httpclient.core.Response;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager()); // 或者 new OkHttpHttpClient(new OkHttpClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/httpclient/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.3/httpclient/configuration.html",
      "children": [
        {
          "title": "连接配置",
          "url": "/manual/2.3/httpclient/configuration.html#连接配置",
          "content": "连接配置您可以通过连接配置类 Configuration 配置 apache httpcomponents 和 okhttp3 的链接配置属性，buession-httpclient 内部会自动将 Configuration 的配置信息，转换为 apache httpcomponents 或 okhttp3 的配置信息。"
        },
        {
          "title": "配置属性说明",
          "url": "/manual/2.3/httpclient/configuration.html#连接配置-配置属性说明",
          "content": "配置属性说明\n\n属性名称\n数据类型\napache httpcomponents 对应配置\nokhttp3 对应配置\n默认值\n说明\n\n\n\n\nmaxConnections\nint\nmaxTotal\nmaxIdleConnections\n5000\n最大连接数\n\n\nmaxPerRoute\nint\ndefaultMaxPerRoute\n--\n500\n每个路由的最大连接数\n\n\nidleConnectionTime\nint\ncloseIdleConnections\nkeepAliveDuration\n60000\n空闲连接存活时长（单位：毫秒）\n\n\nconnectTimeout\nint\nconnectTimeout\nconnectTimeout\n3000\n连接超时时间（单位：毫秒）\n\n\nconnectionRequestTimeout\nint\nconnectionRequestTimeout\n--\n5000\n从连接池获取连接的超时时间（单位：毫秒）\n\n\nreadTimeout\nint\nsocketTimeout\nreadTimeout\n5000\n读取超时时间（单位：毫秒）\n\n\nallowRedirects\nBoolean\nredirectsEnabled\nfollowRedirects\n--\n是否允许重定向\n\n\nrelativeRedirectsAllowed\nBoolean\nrelativeRedirectsAllowed\n--\n--\n是否应拒绝相对重定向\n\n\ncircularRedirectsAllowed\nBoolean\ncircularRedirectsAllowed\n--\n--\n是否允许循环重定向\n\n\nmaxRedirects\nInteger\nmaxRedirects\n--\n--\n最大允许重定向次数\n\n\nauthenticationEnabled\nboolean\nauthenticationEnabled\n--\n--\n是否开启 Http Basic 认证\n\n\ncontentCompressionEnabled\nboolean\ncontentCompressionEnabled\n--\n--\n是否启用内容压缩\n\n\nnormalizeUri\nboolean\nnormalizeUri\n--\n--\n是否标准化 URI\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/httpclient/configuration.html#连接配置-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.3/httpclient/connectionmanager.html",
      "children": [
        {
          "title": "连接管理器",
          "url": "/manual/2.3/httpclient/connectionmanager.html#连接管理器",
          "content": "连接管理器连接管理器是用来管理 HTTP 连接的，包括设置连接配置、控制连接池。关于更多的连接池，可以参考 apache httpcomponents 和 okhttp3 的文档。您可以通过无参数的构造函数来创建连接管理器，也可以通过构造函数参数仅为 com.buession.httpclient.core.Configuration 来创建连接管理器，也可以构造函数通过 apache httpcomponents 或 okhttp3 原生的连接管理器类创建（此时，Configuration 的配置不任何意义，他不会修改您通过原生连接管理器实例中的参数配置）。"
        },
        {
          "title": "关于 okhttp 连接管理器",
          "url": "/manual/2.3/httpclient/connectionmanager.html#连接管理器-关于-okhttp-连接管理器",
          "content": "关于 okhttp 连接管理器okhttp3 本身是没有类似 apache httpcomponents 的链接管理器 org.apache.http.conn.HttpClientConnectionManager 的，我们为了在 buession-httpclient 的链接管理器实现 com.buession.httpclient.conn.OkHttpClientConnectionManager 保持一致，人为的加了一层 okhttp3 的链接管理器 okhttp3.HttpClientConnectionManager（注意：命名空间为 okhttp3），主要用于初始化连接池类 okhttp3.ConnectionPool。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/httpclient/connectionmanager.html#连接管理器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.3/httpclient/response.html",
      "children": [
        {
          "title": "响应",
          "url": "/manual/2.3/httpclient/response.html#响应",
          "content": "响应当通过 HttpClient 发起任意请求后，将得到一个 Response。此结果，包括了：协议及其版本、状态码及其信息、响应头列表、响应体、以及响应体长度。buession-httpclient 会将 apache httpcomponents 或 okhttp3 的响应对象，转换为 Response。需要注意的是，原生 apache httpcomponents 或 okhttp3 响应体流，一旦被使用过一次之后，将不能再使用；同时您只能以流或者字符串二选一的形式获取响应体。但是，在 buession-httpclient 中您将可以二者兼顾，当然这也同时会带来一些性能消耗和内存占用。import com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.core.Response;\nimport java.io.InputStream;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\nInputStream stream = response.getInputStream(); // 以流的形式获取响应体\nString body = response.getBody(); // 以字符串的形式获取响应体\n\nstream.close();\ngetInputStream、getBody 二者可以重复调用，当时您需要始终手动关闭一下流，因为这将是拷贝的原生 apache httpcomponents 或 okhttp3 返回的流。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/httpclient/response.html#响应-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.3/httpclient/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.3/httpclient/method.html#方法",
          "content": "方法buession-httpclient 提供了和 HTTP 请求方式同名的方法 API，您可以很方便的通过提供的方法发起 HTTP 请求。示例：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\");\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\");\n您可以自定义请求头：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.List;\nimport java.util.ArrayList;\n\nList headers = new ArrayList();\n\nheaders.add(new Header(\"X-SDK-NAME\", \"Buession\"));\nheaders.add(new Header(\"X-Timestamp\", System.currentTimeMillis()));\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", headers);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", headers);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", headers);\n您可以设置请求参数：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.Map;\nimport java.util.HashMap;\n\nMap parameters = new HashMap();\n\nparameters.put(\"action\", \"edit\");\nparameters.put(\"id\", 1);\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", parameters);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", parameters);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", parameters);\n您可以设置请求体：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport jcom.buession.httpclient.core.RequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\n\nEncodedFormRequestBody requestBody = new EncodedFormRequestBody();\n\nrequestBody.addRequestBodyElement(\"username\", \"buession\");\nrequestBody.addRequestBodyElement(\"password\", \"buession\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", requestBody);\n\nJsonRawRequestBody requestBody = new JsonRawRequestBody(new User());\n// PUT 请求\nResponse response = httpClient.put(\"https://www.buession.com/\", requestBody);\n不同的 RequestBody，决定了我们以什么样的 Content-Type 提交数据，buession-httpclient 中提供了大量的内置 RequestBody。"
        },
        {
          "title": "RequestBody",
          "url": "/manual/2.3/httpclient/method.html#方法-requestbody",
          "content": "RequestBody\n\nRequestBody\nContent-Type\n说明\n\n\n\n\nInputStreamRequestBody\napplication/octet-stream\n二进制请求体\n\n\nChunkedInputStreamRequestBody\napplication/octet-stream\nChunked 二进制请求体\n\n\nRepeatableInputStreamRequestBody\napplication/octet-stream\nRepeatable 二进制请求体\n\n\nEncodedFormRequestBody\napplication/x-www-form-urlencoded\n普通表单请求体\n\n\nMultipartFormRequestBody\nmultipart/form-data\n文件上传表单请求体\n\n\nHtmlRawRequestBody\ntext/html\nHTML 请求体\n\n\nJavaScriptRawRequestBody\napplication/javascript\nJavaScript 请求体\n\n\nJsonRawRequestBody\napplication/json\nJSON 请求体\n\n\nTextRawRequestBody\ntext/plain\nTEXT 请求体\n\n\nXmlRawRequestBody\ntext/xml\nXML 请求体\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/httpclient/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.3/httpclient/async.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.3/httpclient/async.html#方法",
          "content": "方法buession-httpclient 实现了异步 HTTP 请求。示例：import com.buession.httpclient.HttpAsyncClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.concurrent.Callback;\nimport com.buession.httpclient.exception.RequestException;\n\nApacheHttpAsyncClient httpClient = new ApacheHttpAsyncClient();\n\nhttpClient.get(\"https://www.baidu.com\", new Callback() {\n\n\t/**\n\t * 当 HTTP 请求成功完成并且服务器返回响应时调用此方法\n\t *\n\t * @param response\n\t * \t\tHTTP 响应\n\t */\n\t@Override\n\tpublic void completed(Response response){\n\t\tfor(Header header : response.getHeaders()){\n\t\t\tSystem.out.println(header.toString());\n\t\t}\n\t}\n\n\t/**\n\t * 当 HTTP 请求由于异常而失败时调用此方法\n\t *\n\t * @param ex\n\t * \t\t导致失败的异常。\n\t */\n\t@Override\n\tpublic void failed(Exception ex){\n\t\tSystem.out.println(\"failed: \" + ex.getMessage());\n\t}\n\n\t/**\n\t * 当 HTTP 请求在完成之前被取消时调用此方法\n\t */\n\t@Override\n\tpublic void cancelled(){\n\t\tSystem.out.println(\"cancelled\");\n\t}\n\n});\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/httpclient/async.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-io 参考手册",
      "content": "封装了对文件的操作",
      "url": "/manual/2.3/io/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/io/index.html#安装",
          "content": "安装    com.buession\n    buession-io\n    x.x.x\n\n该模块二次封装了 java java.io.File 和 java.nio.file.Files 类，在此基础上扩展了大量的实用方法，如：文件读写、获取文件 MD5 和 SHA1 值，获取文件 MIME，设置文件所属用户和用户组，简化了我们在应用开发过程中对文件的操作。"
        },
        {
          "title": "读取文件",
          "url": "/manual/2.3/io/index.html#读取文件",
          "content": "读取文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nbyte[] result = file.read();\n"
        },
        {
          "title": "写文件",
          "url": "/manual/2.3/io/index.html#写文件",
          "content": "写文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nfile.write(\"Buession\");\nfile.write(\"Buession\".getBytes());\nfile.write(\"Buession\", true); // 追加写\n"
        },
        {
          "title": "获取文件 MD5、SHA-1值",
          "url": "/manual/2.3/io/index.html#获取文件-md5、sha-1值",
          "content": "获取文件 MD5、SHA-1值import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nString md5 = file.getMd5(); // 获取文件 MD5\nString sha1 = file.getSha1(); // 获取文件 SHA-1\n"
        },
        {
          "title": "获取文件 MD5、SHA-1 值",
          "url": "/manual/2.3/io/index.html#获取文件-md5、sha-1-值",
          "content": "获取文件 MD5、SHA-1 值import com.buession.io.file.File;import com.buession.io.MimeType;\n\nFile file = new File(\"/tmp/debug.txt\");\n\nMimeType result = file.getMimeType();\n"
        },
        {
          "title": "设置文件权限",
          "url": "/manual/2.3/io/index.html#设置文件权限",
          "content": "设置文件权限import com.buession.io.file.Files;\nFiles.chmod(\"/tmp/debug.txt\", 0777);\n"
        },
        {
          "title": "设置文件用户组",
          "url": "/manual/2.3/io/index.html#设置文件用户组",
          "content": "设置文件用户组import com.buession.io.file.Files;\nFiles.chgrp(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "设置文件用户",
          "url": "/manual/2.3/io/index.html#设置文件用户",
          "content": "设置文件用户import com.buession.io.file.Files;\nFiles.chown(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "注解",
          "url": "/manual/2.3/io/index.html#注解",
          "content": "注解注解 com.buession.io.json.annotation.MimeTypeString 可以将类型为 com.buession.io.MimeType 的字段序列化为字符串和将字符串反序列化为 com.buession.io.MimeType，该功能是基于 jackson 实现的。import com.buession.io.json.annotation.MimeTypeString;\nclass File {\n\n    @MimeTypeString\n    private MimeType mime;\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/io/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-jdbc 参考手册",
      "content": "JDBC 通用 POJO 类定义，对 Hikari、Dbcp2、Druid 等配置和数据源的封装。",
      "url": "/manual/2.3/jdbc/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/jdbc/index.html#安装",
          "content": "安装    com.buession\n    buession-jdbc\n    x.x.x\n\n通过提供的 API，您可以简化对 DBCP2、Druid、Hikari、Tomcat 数据源的初始化，该类库基本不单独使用。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/jdbc/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-json 参考手册",
      "content": "主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现。",
      "url": "/manual/2.3/json/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/json/index.html#安装",
          "content": "安装    com.buession\n    buession-json\n    x.x.x\n\n封装了大量基于 jackson 的注解。"
        },
        {
          "title": "注解",
          "url": "/manual/2.3/json/index.html#注解",
          "content": "注解\n\n注解\n说明\n\n\n\n\nCalendarUnixTimestamp\njava.util.Calendar 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Calendar 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Calendar\n\n\nDateUnixTimestamp\njava.util.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Date\n\n\nSqlDateUnixTimestamp\njava.sql.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Date\n\n\nTimestampUnixTimestamp\njava.sql.Timestamp 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Timestamp 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Timestamp\n\n\nJsonEnum2Map\n枚举和 java.util.Map 序列化和反序列化；通过该注解，可以将枚举序列化为 java.util.Map；将 java.util.Map 反序列化为枚举\n\n\nSensitive\n通过该注解可以实现数据的脱敏\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/json/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-lang 参考手册",
      "content": "常用 POJO 类和枚举的定义，详细查看 API 参考手册。",
      "url": "/manual/2.3/lang/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/lang/index.html#安装",
          "content": "安装    com.buession\n    buession-lang\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/lang/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-net 参考手册",
      "content": "网络相关工具类。",
      "url": "/manual/2.3/net/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/net/index.html#安装",
          "content": "安装    com.buession\n    buession-net\n    x.x.x\n\n"
        },
        {
          "title": "IP 地址工具类",
          "url": "/manual/2.3/net/index.html#ip-地址工具类",
          "content": "IP 地址工具类IP 地址工具类 com.buession.net.utils.InetAddressUtis 实现了，IPV4 地址和数字型 IP 相互转换。import com.buession.net.utils.InetAddressUtis;\nlong result = InetAddressUtis.ip2long(\"127.0.0.1\"); // 2130706433\nString ip = InetAddressUtis.long2ip(2130706433L); // 127.0.0.1\nURI 类或 URIBuilder 类，实现了 url 字符串的构建，详细查看 API 手册。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/net/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 原生命令保持一致。同时，对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Object.class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象。",
      "url": "/manual/2.3/redis/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/redis/index.html#安装",
          "content": "安装    com.buession\n    buession-redis\n    x.x.x\n\n"
        },
        {
          "title": "介绍",
          "url": "/manual/2.3/redis/index.html#介绍",
          "content": "介绍buession-redis 是一款基于 jedis 的 redis 操作库，最大的优势就是封装了与 redis 同名、最大程度与 redis 原生参数顺序一致的 API。同时，我们在现代应用中，经常需要读写一个 pojo 对象，buession-redis 封装了 xxxObject 读写取 redis 中的二进制或 JSON 数据，并反序列化为 POJO 类。大大简化了，我们在代码中对象存取到 redis 中，让我们更专注业务功能的开。能够通过 com.buession.redis.core.Options 设置全局选项，如：统一的 Key 前缀，对象基于什么方式序列化和反序列化。import com.buession.redis.RedisTemplate;import com.buession.redis.core.Options;\nimport com.buession.core.serializer.type.TypeReference;\nimport java.utils.Map;\nimport java.utils.HashMap;\n\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.setOptions(new Options());\nredisTemplate.afterPropertiesSet();\n\n// 将 User 对象写进 key 为 user hash 中\nredisTemplate.hSet(\"user\", \"1\", new User());\n\n// 获取 key 为 user ，field 为 1 的 hash 中的数据，并转换为 User\nUser user = redisTemplate.hGetObject(\"user\", \"1\", User.class);\n\n// 获取 key 为 user 的 hash 的所有数据，并将值转换为 User\nMap data = redisTemplate.hGetAllObject(\"user\", \"1\", new TypeReference>{});\n"
        },
        {
          "title": "展望",
          "url": "/manual/2.3/redis/index.html#展望",
          "content": "展望目前，buession-redis 仅支持 jedis，不支持 lettuce，我们计划在 2.3 ~ 2.5 的版本中计划加入。其实，之前尝试过，但由于两者 API 差异性和使用方式太大，没法很好的做到统一化，就暂时放弃了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/redis/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.3/redis/datasource.html",
      "children": [
        {
          "title": "数据源",
          "url": "/manual/2.3/redis/datasource.html#数据源",
          "content": "数据源buession-redis 基于数据源 DataSource 连接 redis，其机制类似 JDBC 的 DataSource。通过，数据源可以配置，redis 的用户名、密码、连接超时、读取超时、连接池、SSL等等。数据源 DataSource 包括三个子接口：StandaloneDataSource：单机模式数据源\nSentinelDataSource：哨兵模式数据源\nClusterDataSource：集群模式数据源\njedis 和后续的 lettuce 分别实现这三个接口，用于创建不通模式的数据源，数据源中实现了连接池的创建。在原始的 jedis 或者 spring-data-redis 中，密码为空字符串时，会以空字符串密码进行登录 redis；我们修改了这一逻辑，不管您在程序中密码是设置的 null 还是空字符串，我们都会跳过认证。这样的好处就是，假设您的开发环境 redis 没有设置密码，生产环境设置了密码，我们可以通过一个 bean 初始化即可，不用写成两个 bean。测试环境 properties：redis.host=127.0.0.1redis.port=6379\nredis.password=\n生产环境 properties：redis.host=192.168.100.131redis.port=6379\nredis.password=passwd\n"
        },
        {
          "title": "连接池",
          "url": "/manual/2.3/redis/datasource.html#数据源-连接池",
          "content": "连接池通过连接池管理 redis 连接，能够大大的提高效率和节约资源的使用。jedis 和 lettuce 均使用 apache commons-pool2 来创建和维护连接池。但是，在 jedis 中，以 JedisPoolConfig 和 ConnectionPoolConfig 来管理单例模式连接池、哨兵模式连接池和集群模式连接池；为了简化配置，我们定义了 com.buession.redis.core.PoolConfig 来统一维护各种模式的连接池配置，然后在各 DataSource 中转换为原生的连接池配置，极大的简化了学习和替换成本。连接池配置\n\n配置项\n数据类型\n-- 默认值\n说明\n\n\n\n\nlifo\nboolean\nGenericObjectPoolConfig.DEFAULT_LIFO\n池模式，为 true 时，后进先出；为 false 时，先进先出\n\n\nfairness\nboolean\nGenericObjectPoolConfig.DEFAULT_FAIRNESS\n当从池中获取资源或者将资源还回池中时，是否使用 java.util.concurrent.locks.ReentrantLock 的公平锁机制\n\n\nmaxWait\nDuration\nGenericObjectPoolConfig.DEFAULT_MAX_WAIT\n当连接池资源用尽后，调用者获取连接时的最大等待时间\n\n\nminEvictableIdleTime\nDuration\n60000\n连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除\n\n\nsoftMinEvictableIdleTime\nDuration\nGenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION\n连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数\n\n\nevictionPolicyClassName\nString\nGenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME\n驱逐策略的类名\n\n\nevictorShutdownTimeout\nDuration\nGenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT\n关闭驱逐线程的超时时间\n\n\nnumTestsPerEvictionRun\nint\n-1\n检测空闲对象线程每次运行时检测的空闲对象的数量\n\n\ntestOnCreate\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE\n在创建对象时检测对象是否有效，配置 true 会降低性能\n\n\ntestOnBorrow\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW\n在从对象池获取对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestOnReturn\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN\n在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestWhileIdle\nboolean\ntrue\n在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\nblockWhenExhausted\nboolean\nGenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED\n当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效；false 连接池没有资源立马抛异常）\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\njmxEnabled\nboolean\nGenericObjectPoolConfig.DEFAULT_JMX_ENABLE\n是否注册 JMX\n\n\njmxNamePrefix\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX\nJMX 前缀\n\n\njmxNameBase\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_BASE\n使用 base + jmxNamePrefix + i 来生成 ObjectName\n\n\nmaxTotal\nint\nGenericObjectPoolConfig.DEFAULT_MAX_TOTAL\n最大连接数\n\n\nminIdle\nint\nGenericObjectPoolConfig.DEFAULT_MIN_IDLE\n最小空闲连接数\n\n\nmaxIdle\nint\nGenericObjectPoolConfig.DEFAULT_MAX_IDLE\n最大空闲连接数\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/redis/datasource.html#数据源-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.3/redis/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.3/redis/method.html#方法",
          "content": "方法buession-redis BaseRedisTemplate 的方法以及参数计划与原生 redis 命名保持一致。复杂的参数会通过 Builder 进行参数构建，在多个值中进行选择的将定义成枚举，规避出错的几率。import com.buession.redis.BaseRedisTemplate;\nBaseRedisTemplate redisTemplate = new BaseRedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 删除哈希表 key 中的一个或多个指定域\nredisTemplate.hDel(\"user\", \"1\", \"2\", \"3\");\n\n// 检查给定 key 是否存在\nredisTemplate.exists(\"user\");\n\n// 获取列表 key 中，下标为 index 的元素\nredisTemplate.lIndex(\"user\", 1);\n\n// 如果键 key 已经存在并且它的值是一个字符串，将 value 追加到键 key 现有值的末尾\nredisTemplate.append(\"key\", \"value 1\");\nBaseRedisTemplate 实现了 redis 的原生操作，RedisTemplate 继承了 BaseRedisTemplate ，在此基础上实现了将 redis 中的二进制或者 JSON 格式的值，反序列化为一个类。import com.buession.redis.RedisTemplate;\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 获取列表 key 中，下标为 index 的元素，并反序列化为 User 类\nUser user = redisTemplate.lIndexObject(\"user\", 1, User.class);\n序列化和反序列化，基于 buession-core 序列化和反序列化 扩展而来，序列化或反序列化出错时会直接返回 null，而忽略异常，默认使用 com.buession.redis.serializer.JacksonJsonSerializer 序列化为 JSON。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/redis/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-session 参考手册",
      "content": "无文档",
      "url": "/manual/2.3/session/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/session/index.html#安装",
          "content": "安装    com.buession\n    buession-session\n    x.x.x\n\n该模块无实际意义，将在今后的版本中会删除掉。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/session/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-thesaurus 参考手册",
      "content": "对词库的解析，目前仅支持搜狗词条。",
      "url": "/manual/2.3/thesaurus/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/thesaurus/index.html#安装",
          "content": "安装    com.buession\n    buession-thesaurus\n    x.x.x\n\n您可以通过该库解析搜狗拼音的词条库，包括词条、拼音信息。import com.buession.thesaurus.SogouParser;import com.buession.thesaurus.Parser;\nimport com.buession.thesaurus.core.Word;\nimport java.util.Set;\n\nParser parser = new SogouParser();\n\nSet words parser.parse(\"搜谱拼音词条文件路径\");\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/thesaurus/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-velocity 参考手册",
      "content": "spring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity。",
      "url": "/manual/2.3/velocity/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/velocity/index.html#安装",
          "content": "安装    com.buession\n    buession-velocity\n    x.x.x\n\n该类库，基本照搬了 springframework 集成 velocity 的代码和逻辑。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/velocity/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "web 相关的功能封装，支持 servlet 和 reactive；封装了一些常用注解，简化了业务层方面的代码实现；封装了一些常用 filter。",
      "url": "/manual/2.3/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/web/index.html#安装",
          "content": "安装    com.buession\n    buession-web\n    x.x.x\n\nbuession-web 扩展了 spring-webmvc、spring-webflux；在此基础上，提供了大量的实用的 filter 和注解，以及工具类。该模块无论封装的 filter、注解、工具类，均适用于 spring mvc，也适用于 spring webflux。当时，filter、工具类均为 servlet 和 webflux 各自独立的类，不是说同一个类，即能用于 servlet，也能用于 webflux，当然注解是通用的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.3/web/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/2.3/web/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了一些我们在日常应用开发过程中能用到的实用性功能，简化了您在代码开发中的便捷度，让您能够更专注业务的开发。"
        },
        {
          "title": "注解",
          "url": "/manual/2.3/web/annotation.html#注解-注解",
          "content": "注解\n\n注解\nRequest / Response\n作用域\n说明\n\n\n\n\n@RequestClientIp\nrequest\n方法参数\n获取当前请求的客户端 IP 地址，支持返回字符串、long 类型的 IP 地址，以及 InetAddress\n\n\n@ContentType\nresponse\n类、方法\n设置响应 Content-Type\n\n\n@HttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值\n\n\n@DisableHttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值，禁止缓存\n\n\n@ResponseHeader\nresponse\n类、方法\n设置响应头\n\n\n@ResponseHeaders\nresponse\n类、方法\n批量设置响应头\n\n\n@DocumentMetaData\nresponse\n类、方法\n设置页面标题、页面编码、关键字、描述、版权等等元信息\n\n\n获取用户端真实 IP@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/ip1\")\n\t@ResponseBody\n\tpublic String ip1(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip2\")\n\t@ResponseBody\n\tpublic Long ip2(@RequestClientIp long ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip3\")\n\t@ResponseBody\n\tpublic InetAddress ip3(@RequestClientIp InetAddress ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n您也可以指定获取用户真实 IP 的请求头列表，若未指定则使用 RequestUtils.getClientIp(request) 方法获取，获取顺序参考：RequestUtils.CLIENT_IP_HEADERS@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/ip1\")\n\t@ResponseBody\n\tpublic String ip1(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip2\")\n\t@ResponseBody\n\tpublic Long ip2(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) long ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip3\")\n\t@ResponseBody\n\tpublic InetAddress ip3(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) InetAddress ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n设置页面缓存@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/cache\")\n\t@HttpCache(expires = \"5\")\n\t@ResponseBody\n\tpublic String cache(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n以上，会自动计算 Cache-Control 和 pragma 的值。当然，您也可以手动指定。@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/cache\")\n\t@HttpCache(expires = \"5\", cacheControl=\"public, max-age=5\")\n\t@ResponseBody\n\tpublic String cache(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.3/web/filter.html",
      "children": [
        {
          "title": "过滤器",
          "url": "/manual/2.3/web/filter.html#过滤器",
          "content": "过滤器我们封装了一些使用的过滤器，简化了您的开发或者应用运行的跟踪。servlet 包位于 com.buession.web.servlet.filter，webflux 包位于 com.buession.web.reactive.filter，均有同样类名的过滤器类。"
        },
        {
          "title": "过滤器",
          "url": "/manual/2.3/web/filter.html#过滤器-过滤器",
          "content": "过滤器\n\n过滤器\n说明\n\n\n\n\nPoweredByFilter\nPowered By 过滤器\n\n\nPrintUrlFilter\n打印当前请求 URL 过滤器\n\n\nResponseHeaderFilter\n响应头过滤器，设置响应头\n\n\nResponseHeadersFilter\n响应头过滤器，批量设置响应头\n\n\nServerInfoFilter\nServer 信息过滤器，通过响应头的形式，输出当前服务器名称，可以用于集群环境定位出现问题的节点\n\n\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.3/web/response.html",
      "children": [
        {
          "title": "Response",
          "url": "/manual/2.3/web/response.html#response",
          "content": "Response我们定义了 restful 返回数据类型。public class Response {\n\t/**\n\t * 状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t */\n\tprivate boolean state;\n\n\t/**\n\t * 错误状态码\n\t */\n\tprivate int code;\n\n\t/**\n\t * 提示或错误消息\n\t */\n\tprivate String message;\n\n\t/**\n\t * 数据\n\t */\n\tprivate E data;\n\n\t/**\n\t * 分页对象\n\t */\n\tprivate Pagination pagination;\n\n\t/**\n\t * 构造函数\n\t */\n\tpublic Response();\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t */\n\tpublic Response(boolean state);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param code\n\t * \t\t错误码\n\t */\n\tpublic Response(boolean state, int code);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param code\n\t * \t\t错误码\n\t * @param message\n\t * \t\t提示或错误消息\n\t */\n\tpublic Response(boolean state, int code, String message);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param message\n\t * \t\t提示或错误消息\n\t */\n\tpublic Response(boolean state, String message);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param code\n\t * \t\t错误码\n\t * @param message\n\t * \t\t提示或错误消息\n\t * @param data\n\t * \t\t数据\n\t */\n\tpublic Response(boolean state, int code, String message, E data);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param message\n\t * \t\t提示或错误消息\n\t * @param data\n\t * \t\t数据\n\t */\n\tpublic Response(boolean state, String message, E data);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param code\n\t * \t\t错误码\n\t * @param message\n\t * \t\t提示或错误消息\n\t * @param data\n\t * \t\t数据\n\t * @param pagination\n\t * \t\t分页对象\n\t */\n\tpublic Response(boolean state, int code, String message, E data, Pagination pagination);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param code\n\t * \t\t错误码\n\t * @param message\n\t * \t\t提示或错误消息\n\t * @param data\n\t * \t\t数据\n\t * @param pagination\n\t * \t\t分页对象\n\t */\n\t@Deprecated\n\tpublic Response(boolean state, int code, String message, E data, com.buession.core.Pagination pagination) ;\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param message\n\t * \t\t提示或错误消息\n\t * @param data\n\t * \t\t数据\n\t * @param pagination\n\t * \t\t分页对象\n\t */\n\tpublic Response(boolean state, String message, E data, Pagination pagination);\n\n\t/**\n\t * 构造函数\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t * @param message\n\t * \t\t提示或错误消息\n\t * @param data\n\t * \t\t数据\n\t * @param pagination\n\t * \t\t分页对象\n\t */\n\t@Deprecated\n\tpublic Response(boolean state, String message, E data, com.buession.core.Pagination pagination);\n\n\t/**\n\t * 返回状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t *\n\t * @return 状态\n\t */\n\tpublic boolean isState();\n\n\t/**\n\t * 返回状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t *\n\t * @return 状态\n\t */\n\tpublic boolean getState();\n\n\t/**\n\t * 设置状态\n\t *\n\t * @param state\n\t * \t\t状态，true 为逻辑成功；false 为逻辑失败或出现异常\n\t */\n\tpublic void setState(boolean state);\n\n\t/**\n\t * 返回错误码\n\t *\n\t * @return 错误码\n\t */\n\tpublic int getCode();\n\n\t/**\n\t * 设置错误码\n\t *\n\t * @param code\n\t * \t\t错误码\n\t */\n\tpublic void setCode(int code);\n\n\t/**\n\t * 返回提示或错误消息\n\t *\n\t * @return 提示或错误消息\n\t */\n\tpublic String getMessage();\n\n\t/**\n\t * 设置提示或错误消息\n\t *\n\t * @param message\n\t * \t\t提示或错误消息\n\t */\n\tpublic void setMessage(String message);\n\n\t/**\n\t * 返回数据\n\t *\n\t * @return 数据\n\t */\n\tpublic E getData();\n\n\t/**\n\t * 设置数据\n\t *\n\t * @param data\n\t * \t\t数据\n\t */\n\tpublic void setData(E data);\n\n\t/**\n\t * 返回分页对象\n\t *\n\t * @return 分页对象\n\t */\n\tpublic Pagination getPagination();\n\n\t/**\n\t * 设置分页对象\n\t *\n\t * @param pagination\n\t * \t\t分页对象\n\t */\n\tpublic void setPagination(Pagination pagination);\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.3/web/pagination.html",
      "children": [
        {
          "title": "Response",
          "url": "/manual/2.3/web/pagination.html#response",
          "content": "Response我们定义了 restful 返回数据类型中的分页对象。public class Pagination {\n\t/**\n\t * 默认每页大小\n\t */\n\tpublic final static int PAGESIZE = 15;\n\n\t/**\n\t * 当前页码\n\t */\n\tprivate int page = 1;\n\n\t/**\n\t * 每页大小\n\t */\n\tprivate int pagesize = PAGESIZE;\n\n\t/**\n\t * 前一页页码\n\t */\n\tprivate int previousPage = 1;\n\n\t/**\n\t * 下一页页码\n\t */\n\tprivate int nextPage = 1;\n\n\t/**\n\t * 总页码\n\t */\n\tprivate int totalPages = 1;\n\n\t/**\n\t * 总记录数\n\t */\n\tprivate long totalRecords = 0;\n\n\t/**\n\t * Constructs with default configuration.\n\t */\n\tpublic Pagination();\n\n\t/**\n\t * Constructs with page and pagesize.\n\t *\n\t * @param page\n\t * \t\t当前页码\n\t * @param pagesize\n\t * \t\t每页大小\n\t */\n\tpublic Pagination(int page, int pagesize);\n\n\t/**\n\t * Constructs with page, pagesize and totalRecords.\n\t *\n\t * @param page\n\t * \t\t当前页码\n\t * @param pagesize\n\t * \t\t每页大小\n\t * @param totalRecords\n\t * \t\t总记录数\n\t */\n\tpublic Pagination(int page, int pagesize, long totalRecords);\n\n\t/**\n\t * 返回当前页码\n\t *\n\t * @return 当前页码\n\t */\n\tpublic int getPage();\n\n\t/**\n\t * 设置当前页码\n\t *\n\t * @param page\n\t * \t\t当前页码\n\t */\n\tpublic void setPage(int page);\n\n\t/**\n\t * 返回每页大小\n\t *\n\t * @return 每页大小\n\t */\n\tpublic int getPagesize();\n\n\t/**\n\t * 设置每页大小\n\t *\n\t * @param pagesize\n\t * \t\t每页大小\n\t */\n\tpublic void setPagesize(int pagesize);\n\n\t/**\n\t * 返回前一页页码\n\t *\n\t * @return 前一页页码\n\t */\n\tpublic int getPreviousPage();\n\n\t/**\n\t * 设置前一页页码\n\t *\n\t * @param previousPage\n\t * \t\t前一页页码\n\t */\n\tpublic void setPreviousPage(int previousPage);\n\n\t/**\n\t * 返回下一页页码\n\t *\n\t * @return 下一页页码\n\t */\n\tpublic int getNextPage(){\n\t\treturn nextPage;\n\t}\n\n\t/**\n\t * 设置下一页页码\n\t *\n\t * @param nextPage\n\t * \t\t下一页页码\n\t */\n\tpublic void setNextPage(int nextPage);\n\n\t/**\n\t * 返回总页码\n\t *\n\t * @return 总页码\n\t */\n\tpublic int getTotalPages();\n\n\t/**\n\t * 设置总页码\n\t *\n\t * @param totalPages\n\t * \t\t总页码\n\t */\n\tpublic void setTotalPages(int totalPages);\n\n\t/**\n\t * 返回总记录数\n\t *\n\t * @return 总记录数\n\t */\n\tpublic long getTotalRecords();\n\n\t/**\n\t * 设置总记录数\n\t *\n\t * @param totalRecords\n\t * \t\t总记录数\n\t */\n\tpublic void setTotalRecords(long totalRecords);\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.3/web/restful.html",
      "children": [
        {
          "title": "RESTFUL",
          "url": "/manual/2.3/web/restful.html#restful",
          "content": "RESTFULRestful 是当今比较流行的一种架构的规范与约束、原则，基于这个风格设计的软件可以更简洁、更有层次。我们遵循 REST 规范，在代码层面规范好了新增、修改、详情、删除等基本的路由，您的控制器层只需要继承 com.buession.web.servlet.mvc.controller.AbstractBasicRestController 或者 com.buession.web.reactive.mvc.controller.AbstractBasicRestController 即可在 servlet 或 webflux 模式下，实现标准的 REST 风格的代码。简化了您的代码（主要是不用再写 @RequestMapping）和标准化了。@RestController@RequestMapping(path = \"/example\")\npublic class ExampleController extends AbstractRestController {\n\n\t@Override\n\tpublic Response add(HttpServletRequest request, HttpServletResponse response, @RequestBody ExampleDto example){\n\t\t\n\t}\n\n\t@Override\n\tpublic Response edit(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id, @RequestBody ExampleDto example){\n\n\t}\n\n\t@Override\n\tpublic Response detail(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\n\t}\n\n\t@Override\n\tpublic Response delete(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.3/web/utils.html",
      "children": [
        {
          "title": "工具",
          "url": "/manual/2.3/web/utils.html#工具",
          "content": "工具我们封装了一些 web 相关的工具类，用于处理 request、response。servlet 包位于 com.buession.web.servlet.http，webflux 包位于 com.buession.web.reactive.http，均有同样类名的过滤器类。获取客户端真实 IP 地址：RequestUtils.getClientIp(request);我们兼容了，通过微信和一些 CDN 厂商获取用户真实 IP 的头信息，我们优先获取从微信透传过来的用户的真实 IP，然后再是各 CDN 厂商的用户真实 IP 头，最后才是标准的真实 IP 头。当然，我们不能保证是否是伪造的。优先顺序：X-Forwarded-For-Pound（微信） > Ali-Cdn-Real-Ip（阿里云 CDN） > Cdn-Src-Ip（网宿） > X-Cdn-Src-Ip（网宿） > X-Original-Forwarded-For（天翼云） > X-Forwarded-For > X-Real-Ip > Proxy-Client-IP > WL-Proxy-Client-IP > Real-ClientIP > remote addr是否是 Ajax 请求：RequestUtils.isAjaxRequest(request);是否是移动设备请求：RequestUtils.isMobile(request);设置缓存：ResponseUtils.httpCache(response, 5); // 缓存 5 秒ResponseUtils.httpCache(response, new Date()); // 缓存到指定的时间点\n"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Framework API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-aop\n使用帮助\nAPI 手册\n\n\nbuession-beans\n使用帮助\nAPI 手册\n\n\nbuession-core\n使用帮助\nAPI 手册\n\n\nbuession-cron\n使用帮助\nAPI 手册\n\n\nbuession-dao\n使用帮助\nAPI 手册\n\n\nbuession-geoip\n使用帮助\nAPI 手册\n\n\nbuession-httpclient\n使用帮助\nAPI 手册\n\n\nbuession-io\n使用帮助\nAPI 手册\n\n\nbuession-jdbc\n使用帮助\nAPI 手册\n\n\nbuession-json\n使用帮助\nAPI 手册\n\n\nbuession-lang\n使用帮助\nAPI 手册\n\n\nbuession-net\n使用帮助\nAPI 手册\n\n\nbuession-redis\n使用帮助\nAPI 手册\n\n\nbuession-session\n使用帮助\nAPI 手册\n\n\nbuession-thesaurus\n使用帮助\nAPI 手册\n\n\nbuession-velocity\n使用帮助\nAPI 手册\n\n\nbuession-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.2/index.html",
      "children": []
    },
    {
      "title": "buession-aop 参考手册",
      "content": "AOP 封装，方便实现自定义注解",
      "url": "/manual/2.2/aop/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/aop/index.html#安装",
          "content": "安装    com.buession\n    buession-aop\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/aop/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-beans 参考手册",
      "content": "该类库提供了基于 commons-beanutils 和 cglib（spring-core 包中，名称空间：org.springframework.cglib.beans） 的 bean 工具的二次封装。包括了属性拷贝和属性映射，以及对象属性转换为 Map。",
      "url": "/manual/2.2/beans/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/beans/index.html#安装",
          "content": "安装    com.buession\n    buession-beans\n    x.x.x\n\n"
        },
        {
          "title": "属性拷贝",
          "url": "/manual/2.2/beans/index.html#属性拷贝",
          "content": "属性拷贝使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 和 target 的属性做映射，实现同名拷贝。import com.buession.beans.BeanUtils;\nBeanUtils.copyProperties(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.copyProperties(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "属性映射",
          "url": "/manual/2.2/beans/index.html#属性映射",
          "content": "属性映射使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 转换为驼峰格式后和 target 的属性做映射，实现拷贝，这是 populate 和 copyProperties 的唯一区别。import com.buession.beans.BeanUtils;\nBeanUtils.populate(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.populate(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "Bean 转换为 Map",
          "url": "/manual/2.2/beans/index.html#bean-转换为-map",
          "content": "Bean 转换为 Map使用此方法，可以实现将一个 bean 对象转换为 Map，bean 的属性作为 Map 的 Keyimport com.buession.beans.BeanUtils;\nMap result = BeanUtils.toMap(bean)\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/beans/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "该类库为核心包，包括构建器、工具类、数据验证、ID 生成器、转换器、序列化、数学方法、消息注入、Manager 层注解、日期时间类和各通用接口定义。",
      "url": "/manual/2.2/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/core/index.html#安装",
          "content": "安装    com.buession\n    buession-core\n    x.x.x\n\n构建器Map、集合的便捷式构建，减少您的代码行数编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中收集器数组、Map、集合的工具类上下文定义应用上下文的类库、注解转换器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。日期时间日期、时间工具ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。数学函数定义了实用的数学函数序列化和反序列化对象的序列化和反序列化，包括二进制和 JSON。验证器数据验证器及其注解工具类常用通用性工具类其它通用的接口定义，框架自身类异常通用异常的定义"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/builder.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.2/core/builder.html#构建器",
          "content": "构建器Map、集合的便捷式构建，减少您的代码行数。您需要往 Map、List 中添加元素的传统写法是：import java.util.ArrayList;import java.util.List;\nimport java.util.HashMap;\nimport java.util.Map;\n\nList list = new ArrayList();\nlist.add(\"A\");\nlist.add(\"B\");\nlist.add(\"C\");\n\nMap map = new HashMap();\nmap.put(\"a\", \"A\");\nmap.put(\"b\", \"B\");\nmap.put(\"c\", \"C\");\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.Map;\n\nList list = ListBuilder.create().add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create().put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n此时的 List、Map 的实现类是 java.util.ArrayList、java.util.HashMap；您可以通过 create 指定其实现类。import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.LinkedList;\nimport java.util.Map;\nimport java.util.LinkedHashMap;\n\nList list = ListBuilder.create(LinkedList.class).add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create(LinkedHashMap.class).put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n注：实现类必须为 public，且必须有无参数的访问权限为 public 的构造函数\n当您有 value 为 null 时，不添加到 List 时，传统写法：import java.util.ArrayList;import java.util.List;\n\nString value = null;\nList list = new ArrayList();\n\nif(value != null){\n\tlist.add(value);\n}\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import java.util.List;\n\nString value = null;\nList list = ListBuilder.create().addIfPresent(value).build();\nMap、Set、Queue 同理。"
        },
        {
          "title": "便捷方法",
          "url": "/manual/2.2/core/builder.html#构建器-便捷方法",
          "content": "便捷方法\n\n方法\n说明\n\n\n\n\n List ListBuilder.epmty()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of(V value)\n创建仅有一个元素的 V 类型的 List 对象\n\n\n Queue QueueBuilder.epmty()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Queue 对象\n\n\n Set SetBuilder.epmty()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Set 对象\n\n\n Map MapBuilder.epmty()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of(V value)\n创建仅有一个元素的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\nempty 与 jdk Collections.emptyList()、Collections.emptySet()、Collections.emptyMap() 的本质区别是返回的 List 实例不同，该方法创建的 List、Set、Map 实例是允许对 List、Set、Map 做操作，而 jdk Collections 创建的 List、Set、Map 则是不允许的。两个 of 方法均同理。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/builder.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/codec.html",
      "children": [
        {
          "title": "编码器",
          "url": "/manual/2.2/core/codec.html#编码器",
          "content": "编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中。我们在当前现代应用中，通常会在业务上定义业务错误信息。且我们返回给前端的可能是更模糊或通用的、人为可读性更强的错误信息，而且可能两种不同原因引起的错误，但是我们在返回给前端时的错误信息是一模一样的，这时我们就需要更精确的标识来定义错误，通常为错误码。此时，我们在应用中，通常会定义错误码和错误信息的映射关系。我们可用的方法大致是，第一代码里面写死；第二，定义错误枚举或者常量。无论哪种方式的都与代码高度耦合，可读性和可维护性都差。此时此刻，您可以通过 buession framework 的注解 @Message 来简化和解耦您的错误信息配置和代码。在传统 springmvc 应用中，您可以通过 context:property-placeholder 或者 util:properties 标签将错误信息 properties 配置文件加载到当前应用环境中。USER_NOT_FOUND.code = 10404USER_NOT_FOUND.message = 用户不存在\n\nUSER_LOGIN_FAILURE.code = 10405\nUSER_LOGIN_FAILURE.message = 登录失败\n\n\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(\"USER_NOT_FOUND\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n您可以自定义错误代码和错误消息的 key，默认分别为：code、message。此时您需要在注解 @Message 上显示指定错误代码和错误消息的 key。USER_NOT_FOUND.errorCode = 10404USER_NOT_FOUND.errorMessage = 用户不存在\n\nUSER_LOGIN_FAILURE.errorCode = 10405\nUSER_LOGIN_FAILURE.errorMessage = 登录失败\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(value = \"USER_NOT_FOUND\", code = \"errorCode\", message = \"errorMessage\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/codec.html#编码器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/collect.html",
      "children": [
        {
          "title": "收集器",
          "url": "/manual/2.2/core/collect.html#收集器",
          "content": "收集器数组、Map、集合的工具类"
        },
        {
          "title": "数组",
          "url": "/manual/2.2/core/collect.html#收集器-数组",
          "content": "数组数组工具类 Arrays 继承自 org.apache.commons.lang3.ArrayUtils 封装了元素是否存在检测、元素索引位置获取、拼接成字符串、转换为 List、Set 以及字符串类型的数组、数组合并、数组元素操作等方法。检测数组 array 中是否存在元素 element：import com.buession.core.collect.Arrays;\nboolean result = Arrays.contains(array, element);\n返回元素 element 在数组 array 中第一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.indexOf(array, element);\n返回元素 element 在数组 array 中最后一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.lastIndexOf(array, element);\n将字符串拼接会字符串：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString result = Arrays.toString(array);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString glue = \"-\";\nString result = Arrays.toString(array, glue);\n// 1-2-3\n可以通过方法 toList、toSet 转换为 List 和 Set：import com.buession.core.collect.Arrays;import java.util.List;\nimport java.util.Set;\n\nint[] array = {1, 2, 3};\nList list = Arrays.toList(array);\nSet set = Arrays.toSet(array);\n将数组转换为字符串类型的数组：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString[] result = Arrays.toStringArray(array);\n将数组进行合并：import com.buession.core.collect.Arrays;\nString[] result = Arrays.toStringArray(array1, array2, array3);\n对数组元素进行操作：import com.buession.core.collect.Arrays;\nString[] array = {\"A\", \"B\", \"C\"};\nString[] result = Arrays.map(array, String.class, fn);\n第二个参数为数组元素类型，第三个参数为 java.util.function.Function 的实现"
        },
        {
          "title": "Lists",
          "url": "/manual/2.2/core/collect.html#收集器-lists",
          "content": "ListsList 工具类 Lists 实现了将元素拼接成字符串、转换为 Set 操作。将字符串拼接会字符串：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString result = Lists.toString(list);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Lists.toString(list);\n// 1-2-3\n可以通过方法 toSet 将 List 转换为 Set：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nSet set = Lists.toSet(list);\n"
        },
        {
          "title": "Sets",
          "url": "/manual/2.2/core/collect.html#收集器-sets",
          "content": "SetsSett 工具类 Sets 实现了将元素拼接成字符串、转换为 List 操作。将字符串拼接会字符串：import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString result = Sets.toString(set);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Sets.toString(list);\n// 1-2-3\n可以通过方法 toList 将 Set 转换为 List：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nList list = Sets.toList(set);\n"
        },
        {
          "title": "Maps",
          "url": "/manual/2.2/core/collect.html#收集器-maps",
          "content": "MapsMap 工具类 Maps 实现了对 key 和 value 进行操作，实现了将 value 转换为 List 和 Set。对 Map 进行操作：import com.buession.core.collect.Maps;import java.util.Map;\nimport java.util.HashMap;\n\nMap maps = new HashMap();\nMap result = Maps.map(maps, (key)->key, (value)->value == null ? null : value.toString());\n第二个、第三参数为 java.util.function.Function 的实现可以通过方法 toList 将 Map 的 value 转换为 List：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\n\nList list = Maps.toList(maps);\n可以通过方法 toSet 将 Map 的 value 转换为 Set：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.Set;\n\nSet set = Maps.toSet(maps);\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/collect.html#收集器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/context.html",
      "children": [
        {
          "title": "上下文",
          "url": "/manual/2.2/core/context.html#上下文",
          "content": "上下文注解 com.buession.core.context.stereotype.Manager 用于在分层应用中，标记当前类是一个 manager 类。类似于 org.springframework.stereotype.Service 加上该注解会将当前类自动注入到 spring 容器中，用法和 @Service 一样。在多层应用架构中 Manager 层通常为：通用业务处理层，处于 Dao 层之上，Service 层之下。主要特征如下：逻辑少\n与 Dao 层进行交互，多个 Dao 层的复用\nService 通用底层逻辑的下沉，如：缓存方案、中间件通用处理、以及对第三方平台封装的层\nimport com.buession.core.context.stereotype.Manager;import org.springframework.stereotype.Service;\n\npublic interface UserManager {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Manager\npublic class UserManagerImpl implements UserManager {\n\n\t@Autowired\n\tprivate UserDao userDao;\n\n\t@Autowired\n\tprivate UserProfileDao userProfileDao;\n\n\t@Autowired\n\tprivate RedisTemplate redisTemplate;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = redisTemplate.hGetObject(\"user\", Integer.toString(id), User.class);\n\n\t\tif(user == null){\n\t\t\tuser = userDao.getByPrimary(id);\n\t\t\tif(user != null){\n\t\t\t\tuser.setProfile(userProfileDao.getByUserId(id));\n\t\t\t\tredisTemplate.hSet(\"user\", Integer.toString(id), user);\n\t\t\t}else{\n\t\t\t\tthrow new RuntimeException(\"用户不存在\");\n\t\t\t}\n\t\t}\n\n\t\treturn user;\n\t}\n\n}\n\npublic interface UserService {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Service\npublic class UserServiceImpl implements UserService {\n\n\t@Autowired\n\tprivate UserManager userManager;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = userManager.getByPrimary(id);\n\n\t\t...\n\n\t\treturn user;\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/context.html#上下文-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/converter.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.2/core/converter.html#构建器",
          "content": "构建器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。接口定义：@FunctionalInterfacepublic interface Converter {\n\n\tT convert(final S source);\n\n}\n将类似为 S 的对象转换为类型为 T 的对象。"
        },
        {
          "title": "内置转换器",
          "url": "/manual/2.2/core/converter.html#构建器-内置转换器",
          "content": "内置转换器\n\n转换器\n说明\n\n\n\n\nArrayConverter\n将 S 类型的数组转换为 T 类型的数组\n\n\nEnumConverter\n枚举转换器，将字符串转换为枚举 E\n\n\nBinaryEnumConverter\n枚举转换器，将 byte 数组转换为枚举 E\n\n\nBooleanStatusConverter\n将布尔值转换为 com.buession.lang.Status\n\n\nStatusBooleanConverter\n将 com.buession.lang.Status 转换为布尔值\n\n\nPredicateStatusConverter\n通过 java.util.function.Predicate 对某种数据类型的数据进行判断结果转换为 com.buession.lang.Status\n\n\nListConverter\n将 S 类型的 List 对象转换为 T 类型的 List 对象\n\n\nSetConverter\n将 S 类型的 Set 对象转换为 T 类型的 Set 对象\n\n\nMapConverter\n将 Key 为 SK 类型、值为 SV 类型的 Map 对象转换为 Key 为 TK 类型、值为 TV 类型的 Map\n\n\n将 key 为 Integer 类型，value 为 Object 类型的 Map 转换成 key 为 String 类型，value 为 String 类型的 Map 对象import com.buession.core.converter.MapConverter;import java.util.Map;\n\nMap source;\nMap target;\nMapConverter converter = new MapConverter();\n\ntarget = converter.convert(source);\n将字符串转换为枚举import com.buession.core.converter.EnumConverter;import com.buession.lang.Gender;\n\nGender target;\nEnumConverter converter = new EnumConverter(Gender.class);\n\ntarget = converter.convert(\"FEMALE\");\n// Gender.FEMALE\n\ntarget = converter.convert(\"F\");\n// null\n"
        },
        {
          "title": "POJO 类映射",
          "url": "/manual/2.2/core/converter.html#构建器-pojo-类映射",
          "content": "POJO 类映射我们通过 com.buession.core.converter.mapper.Mapper 接口约定了，基于 mapstruct POJO 类的映射接口规范。关于 mapstruct 的更多文章，可通过搜索引擎自行搜索。public interface Mapper {\n\t/**\n\t * 将源对象映射到目标对象\n\t *\n\t * @param object\n\t * \t\t源对象\n\t *\n\t * @return 目标对象实例\n\t */\n\tT mapping(S object);\n\n\t/**\n\t * 将源对象数组映射到目标对象数组\n\t *\n\t * @param object\n\t * \t\t源对象数组\n\t *\n\t * @return 目标对象实例数组\n\t */\n\tT[] mapping(S[] object);\n\n\t/**\n\t * 将源 list 对象映射到目标 list 对象\n\t *\n\t * @param object\n\t * \t\t源 list 对象\n\t *\n\t * @return 目标对象 list 实例\n\t */\n\tList mapping(List object);\n\n\t/**\n\t * 将源 set 对象映射到目标 set 对象\n\t *\n\t * @param object\n\t * \t\t源 set 对象\n\t *\n\t * @return 目标对象 set 实例\n\t */\n\tSet mapping(Set object);\n\n}\n我们还可以使用工具类 com.buession.core.converter.mapper.PropertyMapper 将值从提供的源映射到目标，此方法来拷贝并简单修改于：springboot 中的 org.springframework.boot.context.properties.PropertyMapper，和原生 springboot 中的用法一样；并在此基础上增加了方法 alwaysApplyingWhenHasText()，用于判断映射源是否为 null 或者是否含有字符串。import com.buession.core.converter.mapper.PropertyMapper;\nUser source = new User();\nUser target = new User();\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();\npropertyMapper.form(source::getId).to(target:setId)\n// null\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();\npropertyMapper.form(source::getUsername).to(target:setUsername)\n// null\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/converter.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/datetime.html",
      "children": [
        {
          "title": "日期时间",
          "url": "/manual/2.2/core/datetime.html#日期时间",
          "content": "日期时间日期、时间工具。目前，仅有少量的 API，参考 PHP 中的日期时间函数而来。获取当前 Unix 时间戳（秒）：import com.buession.core.datetime.DateTime;\nDateTime.unixtime();\n该方法我们在实际业务中经常用到。以 \"msec sec\" 的格式返回当前 Unix 时间戳和微秒数：import com.buession.core.datetime.DateTime;\nDateTime.microtime();\n// 1657171717 948000\n该方法参考 PHP 的 microtime 函数而来。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/datetime.html#日期时间-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/id.html",
      "children": [
        {
          "title": "ID 生成器",
          "url": "/manual/2.2/core/id.html#id-生成器",
          "content": "ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。您可以通过 buession framework 提供的 ID 生成器，生成唯一 ID。接口规范。public interface IdGenerator {\n\t/**\n\t * 获取下一个 ID\n\t *\n\t * @return ID\n\t */\n\tT nextId();\n\n}\n"
        },
        {
          "title": "ID 生成器",
          "url": "/manual/2.2/core/id.html#id-生成器-id-生成器",
          "content": "ID 生成器\n\n生成器\n说明\n\n\n\n\nAtomicSimpleIdGenerator\n基于 AtomicLong 递增的，简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nAtomicUUIDIdGenerator\n基于 AtomicLong 递增的，UUID ID 生成器\n\n\nNanoIDIdGenerator\njnanoid ID 生成器，可通过构造函数指定字符范围、长度\n\n\nRandomDigitIdGenerator\n随机数 ID 生成器，返回指定范围内的随机数，默认为 1L ~ Long.MAX_VALUE，可通过构造函数指定\n\n\nRandomIdGenerator\n随机字符 ID 生成器，可通过构造函数指定生成长度，默认 16 位\n\n\nSimpleIdGenerator\n简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nSnowflakeIdGenerator\n雪花算法 ID 生成器，此处不解决时钟回拨的问题，您可以通过构造函数指定开始时间、数据中心 ID、数据中心 ID 所占的位数等等值\n\n\nUUIDIdGenerator\nUUID ID 生成器\n\n\nimport com.buession.core.id.AtomicUUIDIdGenerator;import com.buession.core.id.NanoIDIdGenerator;\nimport com.buession.core.id.SnowflakeIdGenerator;\nimport com.buession.core.id.UUIDIdGenerator;\nimport com.buession.core.id.SimpleIdGenerator;\n\nAtomicUUIDIdGenerator idGenerator = new AtomicUUIDIdGenerator();\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000001\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000002\n\nNanoIDIdGenerator idGenerator = new NanoIDIdGenerator();\nidGenerator.nextId(); // omRdTPCug5z_Uk1E_x3ozu3Avyyl3XSK\n\nSnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();\nidGenerator.nextId(); // 170602258864545792\n\nUUIDIdGenerator idGenerator = new UUIDIdGenerator();\nidGenerator.nextId(); // 8634a166-e7d6-4160-85eb-3433107de5a4\n\nSimpleIdGenerator idGenerator = new SimpleIdGenerator();\nidGenerator.nextId(); // 26d9ed57fdad443894b988eeabc69c05\n注：关于雪花算法、jnanoid 算法的可自行搜索。\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/id.html#id-生成器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/math.html",
      "children": [
        {
          "title": "数学函数",
          "url": "/manual/2.2/core/math.html#数学函数",
          "content": "数学函数定义了实用的数学函数。\n\n方法\n说明\n\n\n\n\ncontinuousSum\n计算两个数之间连续相加之和\n\n\nrangeValue\n获取合法范围内的值，如果 value 小于 min，则返回 min；如果 value 大于 max，则返回 max；否则返回 value 本身\n\n\nimport com.buession.core.math.Math;\nlong result = Math.continuousSum(1, 100);\n// 5050\nimport com.buession.core.math.Math;\nlong value = 3;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 4\nimport com.buession.core.math.Math;\nlong value = 5;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 5\nimport com.buession.core.math.Math;\nlong value = 11;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 10\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/math.html#数学函数-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/serializer.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.2/core/serializer.html#构建器",
          "content": "构建器对象的序列化和反序列化，包括二进制和 JSON。您可以通过该 API，实现将对象序列化成二进制或 JSON 字符串；或将二进制、JSON 字符串反序列化为对象。"
        },
        {
          "title": "序列化、反序列化类",
          "url": "/manual/2.2/core/serializer.html#构建器-序列化、反序列化类",
          "content": "序列化、反序列化类\n\n类\n说明\n\n\n\n\nDefaultByteArraySerializer\n将对象序列化为二进制，或将二进制反序列化为对象\n\n\nFastJsonJsonSerializer\n基于 FastJSON 的对象与 JSON 之间的序列化和反序列化\n\n\nGsonJsonSerializer\n基于 Gson 的对象与 JSON 之间的序列化和反序列化\n\n\nJacksonJsonSerializer\n基于 Jackson2 的对象与 JSON 之间的序列化和反序列化\n\n\n通用标准方法是，将对象序列化为字符串，或将字符串反序列化为对象\nDefaultByteArraySerializer 序列化成字符串，逻辑是在对象序列化为 byte 数组后，通过 URLEncoder.encode 进行编码；反序列化，则先通过 URLDecoder.decode 进行解码成 byte 数组，在反序列化为对象\nDefaultByteArraySerializer 支持对象与 byte 数组数组之间的序列化和反序列化\n在将 JSON 字符串反序列化为对象时，默认返回类型依据 fastjson、jackson 等原生逻辑\nFastJsonJsonSerializer、GsonJsonSerializer、JacksonJsonSerializer 可以通过参数 Class、TypeReference 指定返回的对象类型\ncom.buession.core.serializer.type.TypeReference 是某类型的一个指向或者引用，用于屏蔽 fastjson、gson、jackson 中通过 JDK Type 指定反序列化的类型；在 fastjson、gson 中是直接指定 Type，在 jackson 中是通过 com.fasterxml.jackson.core.type.TypeReference 类返回\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/serializer.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/validator.html",
      "children": [
        {
          "title": "验证器",
          "url": "/manual/2.2/core/validator.html#验证器",
          "content": "验证器数据验证器及其注解。该 API 提供了大量通用的适用于本土化的常用验证方法，如：判断是否为 null、判断是否不为 null、判断（字符串、数组、集合、Map等）是否为空、判断（字符串、数组、集合、Map等）是否不为空、IP 地址合法性验证、ISBN 合法性验证、身份证号码验证、QQ 验证。并提供对应的基于 javax.validation 的校验注解。验证是否为 null判断任意对象是否为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNull(obj);\n验证是否不为 null判断任意对象是否不为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNotNull(obj);\n判断字符串是否为空白字符串判断字符串是否为空白字符串，为 null 或长度为 0 时也返回 true；其余情况，字符串中含有任意可打印字符串则为 falseimport com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\n\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\na\";\nboolean result = Validate.isBlank(str); // false\n注：isNotBlank 与之相反\n判断是否为空isEmpty 可判断字符串、数组、集合、Map 是否为空，即：为 null 或长度/大小为 0 时，表示为空import com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isEmpty(str); // true\n\nString str = \" \";\nboolean result = Validate.isEmpty(str); // false\n\nboolean result = Validate.isEmpty(new String[]{}); // true\n\nboolean result = Validate.isEmpty(new Integer[]{1}); // false\n注：isNotEmpty 与之相反\n判断是否在两个数之间isBetween 可判断一个整型或浮点型，是否在两个整型或者浮点型数字之间import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3); // true\n\nboolean result = Validate.isBetween(2, 2, 3); // false\n可通过参数设置是否包含边界值import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3, true); // true\n\nboolean result = Validate.isBetween(2, 2, 3, true); // true\n判断是否为电话号码isTel 可判断一个字符串是否为电话号码，仅支持普通座机号码，不支持 110、400、800等特殊号码。格式，需符合：86-区号-电话号码、区号-电话号码、（区号）电话号码、(区号)电话号码、电话号码。可通过参数 com.buession.core.validator.routines.TelValidator.AreaCodeType 指定区号的控制策略。仅支持中国的电话号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isTel(\"028-12345678\"); // true\n\nboolean result = Validate.isTel(\"028-02345678\"); // false\n判断是否为手机号码isMobile 可判断一个字符串是否为手机号码。仅支持中国的手机号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isMobile(\"028-12345678\"); // false\n\nboolean result = Validate.isMobile(\"13800138000\"); // true\n判断是否为邮政编码isPostCode 可判断一个字符串是否为邮政编码。仅支持中国的邮政编码。import com.buession.core.validator.Validate;\nboolean result = Validate.isPostCode(\"043104\"); // false\n\nboolean result = Validate.isPostCode(\"643104\"); // true\n判断是否为 QQ 号码isQQ 可判断一个字符串是否为 QQ 号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isQQ(\"043104\"); // false\n\nboolean result = Validate.isQQ(\"25132.141\"); // true\n判断是否为身份证号码isIDCard 可判断一个字符串是否合法的身份证号码。仅支持 18 位的身份证号码。身份证号码需符合其身份证号码编排规律。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n可以和身份证号码进行比对，其实该方法的实际作用不是很大，只是在业务系统里面有需要填写身份证号码和出生日期时，简化验证出生日期和身份证号码中的出生日期是否一致。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\", true, \"2.10-01-01\");\n其它，更多方法可以参考手册。"
        },
        {
          "title": "注解",
          "url": "/manual/2.2/core/validator.html#验证器-注解",
          "content": "注解javax 的 validation 是 Java 定义的一套基于注解的数据校验规范，buession framework 基于 javax.validation 实现了 Validate 中所有验证方法的校验注解。\n\n注解\n验证的数据类型\n说明\n\n\n\n\n@Alnum\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Alpha\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Numeric\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Between\nshort、int、double 等任何 Number 的子类型\n验证注解的元素值是否为在两个数之间\n\n\n@Empty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否为空\n\n\n@NotEmpty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否不为空\n\n\n@HasText\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@IDCard\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Ip\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Isbn\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@MimeType\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Mobile\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Null\n任意类型\n验证注解的元素值是否为 null\n\n\n@NotNull\n任意类型\n验证注解的元素值是否为 null\n\n\n@Port\nInteger\n验证注解的元素值是否为 null\n\n\n@PostCode\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@QQ\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Tel\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Xdigit\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/validator.html#验证器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/utils.html",
      "children": [
        {
          "title": "工具类",
          "url": "/manual/2.2/core/utils.html#工具类",
          "content": "工具类常用通用性工具类。"
        },
        {
          "title": "Byte 数组比较",
          "url": "/manual/2.2/core/utils.html#工具类-byte-数组比较",
          "content": "Byte 数组比较ByteArrayComparable 类为 java Comparable 的实现，实现了 byte 数组的比较。"
        },
        {
          "title": "注解工具",
          "url": "/manual/2.2/core/utils.html#工具类-注解工具",
          "content": "注解工具AnnotationUtils 继承 org.springframework.core.annotation.AnnotationUtils ，在此基础上新增了方法 hasClassAnnotationPresent(final Class clazz, final Class[] annotations)、hasMethodAnnotationPresent(Method method, final Class[] annotations) 判断类或者方法上是否有待检测的注解中的其中一个注解。"
        },
        {
          "title": "断言",
          "url": "/manual/2.2/core/utils.html#工具类-断言",
          "content": "断言Assert 和 springframework 中的注解类似，不过逻辑有些相反。"
        },
        {
          "title": "Byte 工具",
          "url": "/manual/2.2/core/utils.html#工具类-byte-工具",
          "content": "Byte 工具ByteUtils 可以将 byte 数组转换为 char 或者 char 数组。import com.buession.core.utils.ByteUtils;\nbyte[] bytes;\nchar c = ByteUtils.toChar(bytes);\n\nchar[] chars = ByteUtils.toChar(bytes);\nbyte 数组连接。import com.buession.core.utils.ByteUtils;\nbyte[] dest;\nbyte[] source\nbyte[] result = ByteUtils.concat(dest, source);\n"
        },
        {
          "title": "Character 工具",
          "url": "/manual/2.2/core/utils.html#工具类-character-工具",
          "content": "Character 工具CharacterUtils 继承 org.apache.commons.lang3.CharUtils，可以将 char、char 数组转换为 byte 数组。import com.buession.core.utils.CharacterUtils;\nchar c;\nbyte[] bytes = ByteUtils.toBytes(c);\n\nchar[] chars;\nbyte[] bytes = ByteUtils.toBytes(chars);\n"
        },
        {
          "title": "数字工具",
          "url": "/manual/2.2/core/utils.html#工具类-数字工具",
          "content": "数字工具NumberUtils 提供了对数字相关的操作。\n\n方法\n说明\n\n\n\n\nint2bytes\n将 int 转换为 byte[]\n\n\nbytes2int\n将 byte[] 转换为 int\n\n\nlong2bytes\n将 long 转换为 byte[]\n\n\nbytes2long\n将 byte[] 转换为 long\n\n\nfloat2bytes\n将 float 转换为 byte[]\n\n\nbytes2float\n将 byte[] 转换为 float\n\n\ndouble2bytes\n将 double 转换为 byte[]\n\n\nbytes2double\n将 byte[] 转换为 double\n\n\n"
        },
        {
          "title": "字符串工具",
          "url": "/manual/2.2/core/utils.html#工具类-字符串工具",
          "content": "字符串工具StringUtils 继承了 org.apache.commons.lang3.StringUtils，封装了多字符串更多的操作。截取字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.substr(\"abcde\", 1); // bcde\nString result = StringUtils.substr(\"abcde\", 1, 2); // bc\n生成随机字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.random(length);\n比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 3); // true\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 4); // false\n忽略大小写比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"Abce\", 3); // true\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"aBce\", 4); // false\n"
        },
        {
          "title": "拼音工具",
          "url": "/manual/2.2/core/utils.html#工具类-拼音工具",
          "content": "拼音工具PinyinUtils 封装了获取中文拼音、拼音首字母的方法。import com.buession.core.utils.PinyinUtils;\nString result = PinyinUtils.getPinyin(\"中国\"); // zhongguo\nString result = PinyinUtils.getPinYinFirstChar(\"中国\"); // zg\n"
        },
        {
          "title": "随机数工具",
          "url": "/manual/2.2/core/utils.html#工具类-随机数工具",
          "content": "随机数工具RandomUtils 封装了随机数的生成。\n\n方法\n说明\n\n\n\n\nnextBoolean\n随机布尔值\n\n\nnextBytes\n随机字节数组\n\n\nnextInt\n生成指定范围内的 int 值，默认：0 到 Integer.MAX_VALUE\n\n\nnextLong\n生成指定范围内的 long 值，默认：0 到 Long.MAX_VALUE\n\n\nnextFloat\n生成指定范围内的 float 值，默认：0 到 Float.MAX_VALUE\n\n\nnextDouble\n生成指定范围内的 double 值，默认：0 到 Double.MAX_VALUE\n\n\n"
        },
        {
          "title": "Properties 工具",
          "url": "/manual/2.2/core/utils.html#工具类-properties-工具",
          "content": "Properties 工具PropertiesUtils 封装了对 Properties 的操作，主要是 Properties.getProperty 的值为字符串，而我们在实际场景中，很多时候其值可能为 int、double。传统方法是，我们在代码中通过 Properties.getProperty 获取其值后，对其进行转换；而 PropertiesUtils 简化了操作。import com.buession.core.utils.SystemPropertyUtils;\nInteger result = PropertiesUtils.getInteger(properties, key);\nBoolean result = PropertiesUtils.getBoolean(properties, key);\n"
        },
        {
          "title": "System Property 工具",
          "url": "/manual/2.2/core/utils.html#工具类-system-property-工具",
          "content": "System Property 工具SystemPropertyUtils 封装了系统属性或系统环境变量的操作。设置属性方法 setProperty 对 System.setProperty 的封装，唯一区别是：SystemPropertyUtils.setProperty 的值可以为非字符串，该方法类会将非字符串值转换为字符串再调用 System.setProperty。import com.buession.core.utils.SystemPropertyUtils;\nSystemPropertyUtils.setProperty(\"http.port\", 8080);\nSystemPropertyUtils.setProperty(\"http.ssl.enable\", false);\n获取属性方法 getProperty 会先通过 System.getProperty 进行获取，若未获取到值，再调用 System.getenv 进行获取。String value = System.getProperty(name);\nif(Validate.hasText(value) == false){\n  value = System.getenv(name);\n}\n"
        },
        {
          "title": "版本工具",
          "url": "/manual/2.2/core/utils.html#工具类-版本工具",
          "content": "版本工具VersionUtils 提供了对两个版本值的比较方法和获取类、jar 对应的版本。import com.buession.core.utils.VersionUtils;\nVersionUtils.compare(\"1.0.0\", \"1.0.1-beta\"); // -1\nVersionUtils.compare(\"1.0.0\", \"1.0.0r\"); // -1\n规则：dev < alpha = a < beta = b < rc < release < pl = p < 纯数字版本获取类的版本值import com.buession.core.utils.VersionUtils;\nByteUtils.determineClassVersion(com.buession.core.utils.VersionUtils.class); // 2.2.0\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/utils.html#工具类-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/other.html",
      "children": [
        {
          "title": "其它",
          "url": "/manual/2.2/core/other.html#其它",
          "content": "其它通用的接口定义，框架自身类，以及其它杂项。"
        },
        {
          "title": "框架自身工具",
          "url": "/manual/2.2/core/other.html#其它-框架自身工具",
          "content": "框架自身工具获取 Buession Framework 版本：import com.buession.core.Framework;import com.buession.core.BuesssionFrameworkVersion;\n\nBuesssionFrameworkVersion.getVersion(); // 2.2.0\nFramework.VERSION; // 2.2.0\n获取 Buession Framework 框架名称：import com.buession.core.Framework;\nFramework.NAME; // \"Buession\"\n"
        },
        {
          "title": "命令执行器",
          "url": "/manual/2.2/core/other.html#其它-命令执行器",
          "content": "命令执行器命令执行器接口：/** * 命令执行器\n *\n * @param \n * \t\t命令上下文\n * @param \n * \t\t命令执行返回值\n */\n@FunctionalInterface\npublic interface Executor {\n\n\t/**\n\t * 命令执行\n\t *\n\t * @param context\n\t * \t\t命令执行器上下文\n\t *\n\t * @return 命令执行返回值，R 类型的实例\n\t */\n\tR execute(C context);\n\n}\n您可以通过，该接口执行一个命令上下文的方法，并返回一个值。该方法有些类似代理模式的代理类。"
        },
        {
          "title": "销毁接口",
          "url": "/manual/2.2/core/other.html#其它-销毁接口",
          "content": "销毁接口功能类似 java.io.Closeable。public interface Destroyable {\n\t/**\n\t * 销毁相关资源\n\t *\n\t * @throws IOException\n\t * \t\tIO 错误时抛出\n\t */\n\tvoid destroy() throws IOException;\n\n}\n"
        },
        {
          "title": "Rawable",
          "url": "/manual/2.2/core/other.html#其它-rawable",
          "content": "Rawable原始的，约定实现该接口的类，必须返回原始字节数组。public interface Rawable {\n\t/**\n\t * 返回原始的字节数组\n\t *\n\t * @return 原始的字节数组\n\t */\n\tbyte[] getRaw();\n\n}\n"
        },
        {
          "title": "名称节点",
          "url": "/manual/2.2/core/other.html#其它-名称节点",
          "content": "名称节点名称节点，约定实现该接口的类应该返回一个名称public interface NamedNode {\n\t/**\n\t * 返回节点名称\n\t *\n\t * @return 节点名称\n\t */\n\t@Nullable\n\tString getName();\n\n}\n"
        },
        {
          "title": "分页",
          "url": "/manual/2.2/core/other.html#其它-分页",
          "content": "分页com.buession.core.Pagination 为一个分页 POJO 类，包括了当前页码、每页大小、上一页、下一页、总页数、总记录数、数据列表。能够根据设定的其中一个值，计算另外的值。"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.2/core/exception.html",
      "children": [
        {
          "title": "异常",
          "url": "/manual/2.2/core/exception.html#异常",
          "content": "异常通用异常的定义。\n\n异常\n说明\n\n\n\n\nAccessException\n拒绝访问异常\n\n\nClassInstantiationException\n类实例化异常\n\n\nConversionException\n数据类型转换异常\n\n\nDataAlreadyExistException\n数据已存在异常\n\n\nDataNotFoundException\n数据不存在或未找到异常\n\n\nInsteadException\n类方法废弃后，需要使用其它类库方法来替代\n\n\nNestedRuntimeException\n嵌套运行时异常\n\n\nOperationException\n运算异常\n\n\nPresentException\n--\n\n\nSerializationException\n序列化异常\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/exception.html#异常-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-cron 参考手册",
      "content": "对 quartz 的二次封装",
      "url": "/manual/2.2/cron/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/cron/index.html#安装",
          "content": "安装    com.buession\n    buession-cron\n    x.x.x\n\n由于在过去的工作中，定时任务基本使用 quartz 来实现；但是在初始化定时任务项目时，大量基本相同的代码，因此对此部分做了二次封装，简化了 quartz 项目的初始化。由于在现在有众多优秀的分布式定时任务，如：elastic-job、xxl-job 等等，因此直接使用 quartz 应该会越来越少（个人主观猜测），即使使用 quartz 初始化也简单，故该模块将不做说明。且在今后的版本中，该模块可能会被废弃。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/cron/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装。从代码层面实现了数据库的读写分离，insert、update、delete 操作主库，select 操作从库。",
      "url": "/manual/2.2/dao/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/dao/index.html#安装",
          "content": "安装    com.buession\n    buession-dao\n    x.x.x\n\n我们咋众多项目中，基本有常见的重复的对数据库的 CURD 操作，比如：根据主键查询数据、根据主键删除数据、获取一条记录。MyBatis 是一款优秀的持久层框架，应用广泛。MongoDB 是一款优秀的文档数据库。我自己根据从业多年的经验，从实际场合出发，将在业务层对数据库的常用操作进行了封装。对关系型数据库基于 MyBatis 二次封装，对 MongoDB 基于 spring-data-mongodb；在未来也许会考虑，增加 jpa 和 JdbcTemplate 对关系型数据库的二次封装。同时，我们在代码层面实现了数据库的读写分离。我们没有改变 MyBatis 和 spring-data-mongodb 的任何底层逻辑，本质就是 MyBaits 和 spring-data-mongodb；我们唯一做了的就是，定义和是了大家在应用程序中常用的方法，让您不在重复去编写该部分代码；以及在代码层面实现了数据的读写分离。"
        },
        {
          "title": "Dao 接口",
          "url": "/manual/2.2/dao/index.html#dao-接口",
          "content": "Dao 接口接口定义，可见：https://javadoc.io/static/com.buession/buession-dao/2.2.0/com/buession/dao/Dao.htmlpublic interface Dao {}\nP：主键类型\nE：实体类\n分页对象 com.buession.dao.Pagination 继承自 com.buession.core.Pagination，增加了偏移量属性 offset。条件为 Map 类型，允许为 null。排序为 Map 类型，允许为 null。MyBatisBuession Framework 扩展 MyBatis 的文档。MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/dao/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.2/dao/mybatis.html",
      "children": [
        {
          "title": "MyBatis",
          "url": "/manual/2.2/dao/mybatis.html#mybatis",
          "content": "MyBatisBuession Framework 扩展 MyBatis 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.2/dao/mybatis.html#mybatis-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMyBatisDao；且存在 bean 名为 masterSqlSessionTemplate、slaveSqlSessionTemplates 的 bean 实例。masterSqlSessionTemplate 操作主库，实现插入、更新、删除操作；slaveSqlSessionTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveSqlSessionTemplate() 在所有的 slave templates 中随机返回一个 slave SqlSessionTemplate bean 实例。当然，您也可以通过 getSlaveSqlSessionTemplate(final int index) 指定索引的 slave SqlSessionTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave SqlSessionTemplate bean 实例列表，将会返回 master SqlSessionTemplate bean 实例，buession framework 屏蔽了这些技术细节。"
        },
        {
          "title": "Mybatis 约定",
          "url": "/manual/2.2/dao/mybatis.html#mybatis-mybatis-约定",
          "content": "Mybatis 约定如果集成 AbstractMyBatisDao 类，必须重写方法 getStatement()，通过此方法返回每个 Mapper namespace\nnamespace com.buession.dao.test.dao;\npublic class UserDaoImpl extends AbstractMyBatisDao {\n\n\t@Override\n\tprotected String getStatement(){\n\t\treturn \"com.buession.dao.test.dao.UserMapper\";\n\t}\n\n}\n\n\nMapper 的 SQL ID 和方法名保持一致\n\n\nSQL ID\n说明\n返回值\n\n\n\n\ninsert\n插入数据\n影响的行数\n\n\nbatchInsert\n批量插入数据，默认循环插入；您可以重写该方法实现 SQL 批量插入\n每次插入影响的行数列表\n\n\nreplace\n替换数据，即：REPLACE 语句\n影响的行数\n\n\nbatchReplace\n批量替换数据，即：REPLACE 语句\n每次替换数据影响的行数列表\n\n\nupdate\n更新数据\n更新条数\n\n\nupdateByPrimary\n根据主键更新数据，注：主键参数值是会覆盖实体类主键参数对应的类属性的值\n更新条数\n\n\ngetByPrimary\n根据主键查询数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselectOne\n（根据条件）获取一条数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselect\n查询数据\n数据结果列表\n\n\ngetAll\n查询所有数据\n数据结果列表\n\n\ncount\n获取记录数\n记录数\n\n\ndeleteByPrimary\n根据主键删除数据\n影响条数\n\n\ndelete\n删除数据\n影响条数\n\n\nclear\n清除数据\n影响条数\n\n\ntruncate\n截断数据\n影响条数\n\n\n注：要实现分页，必须实现 count，且和 select 的查询条件必须一致。因为，在分页方法中，首先会执行 count ，查询指定条件的记录数；如果记录数大于 0 时，才会执行 select 查询数据。在后续的开发中，我们将会使用拦截器实现。\n以上 SQL ID，只是一种约定，具体会呈现一种什么样的效果，还是完全屈居于您的 SQL 语句。\n"
        },
        {
          "title": "Mybatis 类型处理器",
          "url": "/manual/2.2/dao/mybatis.html#mybatis-mybatis-类型处理器",
          "content": "Mybatis 类型处理器MyBatis 自身提供大量优秀的类型处理器 TypeHandler，但任然不足。我们在此基础上扩展了一些 TypeHandler。名称空间为 org.apache.ibatis.type，不是 com.buession.dao。\n\nTypeHandler\n说明\n\n\n\n\nDefaultEnumTypeHandler\n默认 Enum 类型处理器，将值直接转换为枚举字段\n\n\nIgnoreCaseEnumTypeHandler\n忽略大小写 Enum 类型处理器，将值忽略大小写转换为枚举字段\n\n\nDefaultJsonTypeHandler\nJSON 处理器，将 JSON 格式的字符串值和类型  进行转换\n\n\nDefaultSetEnumTypeHandler\n默认 Enum 型 Set 类型处理器，将值直接转换为枚举字段作为 Set 的元素\n\n\nIgnoreCaseSetEnumTypeHandler\n忽略大小写 Enum 型 Set 类型处理器，将值忽略大小写转换为枚举字段作为 Set 的元素\n\n\nDefaultSetTypeHandler\n默认 Set 类型处理器，将值以 \",\" 拆分转换为 Set\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/dao/mybatis.html#mybatis-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.2/dao/mongodb.html",
      "children": [
        {
          "title": "MongoDB",
          "url": "/manual/2.2/dao/mongodb.html#mongodb",
          "content": "MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.2/dao/mongodb.html#mongodb-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMongoDBDao；且存在 bean 名为 masterMongoTemplate、slaveMongoTemplates 的 bean 实例。masterMongoTemplate 操作主库，实现插入、更新、删除操作；slaveMongoTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveMongoTemplate() 在所有的 slave templates 中随机返回一个 MongoTemplate bean 实例。当然，您也可以通过 getSlaveMongoTemplate(final int index) 指定索引的 slave MongoTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave MongoTemplate bean 实例列表，将会返回 master MongoTemplate bean 实例，buession framework 屏蔽了这些技术细节。AbstractMongoDBDao 的 replace 执行的也是 insert。在对 MongoDB 的操作条件中 value 可以为 com.buession.dao.MongoOperation，可以通过该类的方法控制条件等于值、大于值、小于值、LIKE值 等等运算。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/dao/mongodb.html#mongodb-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-geoip 参考手册",
      "content": "对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息。",
      "url": "/manual/2.2/geoip/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/geoip/index.html#安装",
          "content": "安装    com.buession\n    buession-geoip\n    x.x.x\n\n通常我们在应用中对用户注册、登录、以及其它操作记录 IP，我们更希望知道用户在什么城市进行的操作，如：微信公众号的内容发表于、微博的发布于等等，对于用户行为的安全审计等等有着极高的作用。geoip 在基于 maxmind geoip2 的基础上进行了二次封装，可以根据 IP（字符串形式的 IP，如：114.114.114.114、2.11:0DB8:0000:0023:0008:0800:2.1C:417A ，IPV4 的数字表示：3739974408，java InetAddress）获取其 IP 地址的国家信息、城市信息、位置信息。"
        },
        {
          "title": "获取国家信息",
          "url": "/manual/2.2/geoip/index.html#获取国家信息",
          "content": "获取国家信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nCountry country = resolver.country(\"114.114.114.114\");\n// Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}\n\nCountry country = resolver.country(3739974408L); // 3739974408L => 222.235.123.8\n// Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}\n"
        },
        {
          "title": "获取城市信息",
          "url": "/manual/2.2/geoip/index.html#获取城市信息",
          "content": "获取城市信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nDistrict district = resolver.district(\"114.114.114.114\");\n// District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}\n\nDistrict district = resolver.district(3739974408L); // 3739974408L => 222.235.123.8\n// District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}\n"
        },
        {
          "title": "获取位置信息",
          "url": "/manual/2.2/geoip/index.html#获取位置信息",
          "content": "获取位置信息位置信息中包括了该 IP 比较全面的信息，包括：城市信息、国家信息、洲信息、经纬度、机构信息、时区等。import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nLocation location = resolver.location(\"114.114.114.114\");\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}, district=District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}, traits=Traits{ipAddress='114.114.114.114', domain='null', isp='null', network=114.114.0.0/16, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=32.1617, longitude=118.7778, accuracyRadius=50}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Shanghai\",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null]}\n\nLocation location = resolver.location(3739974408L); // 3739974408L => 222.235.123.8\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}, district=District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}, traits=Traits{ipAddress='222.235.123.8', domain='null', isp='null', network=222.235.120.0/21, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=37.5111, longitude=126.9743, accuracyRadius=2.1}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Seoul\",offset=32.10000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null]}\n"
        },
        {
          "title": "缓存",
          "url": "/manual/2.2/geoip/index.html#缓存",
          "content": "缓存为了提高数据的处理能力，可以将获取过的数据缓存起来，下次获取同一 IP 信息时，可以直接从缓存中返回。您可以通过 DatabaseResolver 构造函数中的参数 cache 设置为 com.maxmind.db.NodeCache 的实现类即可，或直接使用类 CacheDatabaseResolver解析。我们默认使用 maxmind 内置的 CHMCache 来实现缓存，它是基于 ConcurrentHashMap 的内存缓存。"
        },
        {
          "title": "Resolver 的 Spring Factory Bean",
          "url": "/manual/2.2/geoip/index.html#resolver-的-spring-factory-bean",
          "content": "Resolver 的 Spring Factory Bean我们内置了 geoip 的 Resolver spring factory bean 类 GeoIPResolverFactoryBean，您可以通过它在您的 spring 项目中，初始化 Resolver 的实现类为 spring bean 对象。dbPath 和 stream 二选一即可，一个是指定 IP 的文件路径，一个是指定已加载的 IP 库的文件流。都不设置的默认以流的形式加载 buession-geoip 中的 IP 库文件。\nenableCache 可以控制是否缓存。\n"
        },
        {
          "title": "关于 IP 库",
          "url": "/manual/2.2/geoip/index.html#关于-ip-库",
          "content": "关于 IP 库buession-geoip 中包含了 maxmind 免费的 IP 所属城市和国家的库。由于在 jar 包中该数据库无法做到及时更新，在实际应用中，我们建议您从 maxmind 官网下载 IP 方法您的应用中，通过 DatabaseResolver 的构造函数指定 IP 库路径，这么做的好处是：在您的应用程序中，可以去保证 IP 库是更新的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/geoip/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-git 参考手册",
      "content": "获取项目 GIT 信息。",
      "url": "/manual/2.2/git/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/git/index.html#安装",
          "content": "安装    com.buession\n    buession-git\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/git/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节，屏蔽了对 post form、post json 等等的技术细节。",
      "url": "/manual/2.2/httpclient/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/httpclient/index.html#安装",
          "content": "安装    com.buession\n    buession-httpclient\n    x.x.x\n\n我们在应用中使用 Http Client 功能时，经常因为从 apache httpcomponents 切换为 okhttp3，或者从 okhttp3 切换为 apache httpcomponents，需要改动大量的代码而烦恼。而当您使用了 buession-httpclient 时，该类库为您解决了这些烦恼，通过顶层设计，屏蔽了 apache httpcomponents 和 okhttp3 的细节，当您需要从一个 http 库更换为另外一个 http 库时，您只需要在 pom.xml 中引用不同的包，修改一下 httpclient 的初始化类和连接管理器即可。传统的方式：    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\nimport org.apache.http.HttpResponse;import org.apache.http.conn.HttpClientConnectionManager;\nimport org.apache.http.client.HttpClient;\nimport org.apache.http.impl.client.HttpClientBuilder;\nimport org.apache.http.client.methods.HttpPost;\n\nHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(new HttpClientConnectionManager()).build();\n\nHttpResponse response = httpClient.execute(new HttpPost(\"https://www.buession.com/\"));\n或者    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport okhttp3.HttpClientConnectionManager;import okhttp3.OkHttpClient;\nimport okhttp3.ConnectionPool;\nimport okhttp3.Request;\nimport okhttp3.Request.Builder;\nimport okhttp3.Response;\n\nOkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(new ConnectionPool());\nHttpClient httpClient = builder.build();\n\nBuilder requestBuilder = new Builder().post();\nrequestBuilder.url(\"https://www.buession.com/\");\nRequest okHttpRequest = requestBuilder.build();\n\nResponse httpResponse = httpClient.newCall(okHttpRequest).execute();\n现在，您只需要通过 buession-httpclient，即可屏蔽其中的细节。    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\n或者    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.OkHttpHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.conn.OkHttpClientConnectionManager;\nimport com.buession.httpclient.core.Response;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager()); // 或者 new OkHttpHttpClient(new OkHttpClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\n"
        },
        {
          "title": "展望",
          "url": "/manual/2.2/httpclient/index.html#展望",
          "content": "展望目前，buession-httpclient 仅支持同步，不支持异步。我们会在下一个小版本（即：2.2） 中，集成 apache httpcomponents 切换为 okhttp3 的异步 http 请求。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/httpclient/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.2/httpclient/configuration.html",
      "children": [
        {
          "title": "连接配置",
          "url": "/manual/2.2/httpclient/configuration.html#连接配置",
          "content": "连接配置您可以通过连接配置类 Configuration 配置 apache httpcomponents 和 okhttp3 的链接配置属性，buession-httpclient 内部会自动将 Configuration 的配置信息，转换为 apache httpcomponents 或 okhttp3 的配置信息。"
        },
        {
          "title": "配置属性说明",
          "url": "/manual/2.2/httpclient/configuration.html#连接配置-配置属性说明",
          "content": "配置属性说明\n\n属性名称\n数据类型\napache httpcomponents 对应配置\nokhttp3 对应配置\n默认值\n说明\n\n\n\n\nmaxConnections\nint\nmaxTotal\nmaxIdleConnections\n5000\n最大连接数\n\n\nmaxPerRoute\nint\ndefaultMaxPerRoute\n--\n500\n每个路由的最大连接数\n\n\nidleConnectionTime\nint\ncloseIdleConnections\nkeepAliveDuration\n60000\n空闲连接存活时长（单位：毫秒）\n\n\nconnectTimeout\nint\nconnectTimeout\nconnectTimeout\n3000\n连接超时时间（单位：毫秒）\n\n\nconnectionRequestTimeout\nint\nconnectionRequestTimeout\n--\n5000\n从连接池获取连接的超时时间（单位：毫秒）\n\n\nreadTimeout\nint\nsocketTimeout\nreadTimeout\n5000\n读取超时时间（单位：毫秒）\n\n\nallowRedirects\nBoolean\nredirectsEnabled\nfollowRedirects\n--\n是否允许重定向\n\n\nrelativeRedirectsAllowed\nBoolean\nrelativeRedirectsAllowed\n--\n--\n是否应拒绝相对重定向\n\n\ncircularRedirectsAllowed\nBoolean\ncircularRedirectsAllowed\n--\n--\n是否允许循环重定向\n\n\nmaxRedirects\nInteger\nmaxRedirects\n--\n--\n最大允许重定向次数\n\n\nauthenticationEnabled\nboolean\nauthenticationEnabled\n--\n--\n是否开启 Http Basic 认证\n\n\ncontentCompressionEnabled\nboolean\ncontentCompressionEnabled\n--\n--\n是否启用内容压缩\n\n\nnormalizeUri\nboolean\nnormalizeUri\n--\n--\n是否标准化 URI\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/httpclient/configuration.html#连接配置-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.2/httpclient/connectionmanager.html",
      "children": [
        {
          "title": "连接管理器",
          "url": "/manual/2.2/httpclient/connectionmanager.html#连接管理器",
          "content": "连接管理器连接管理器是用来管理 HTTP 连接的，包括设置连接配置、控制连接池。关于更多的连接池，可以参考 apache httpcomponents 和 okhttp3 的文档。您可以通过无参数的构造函数来创建连接管理器，也可以通过构造函数参数仅为 com.buession.httpclient.core.Configuration 来创建连接管理器，也可以构造函数通过 apache httpcomponents 或 okhttp3 原生的连接管理器类创建（此时，Configuration 的配置不任何意义，他不会修改您通过原生连接管理器实例中的参数配置）。"
        },
        {
          "title": "关于 okhttp 连接管理器",
          "url": "/manual/2.2/httpclient/connectionmanager.html#连接管理器-关于-okhttp-连接管理器",
          "content": "关于 okhttp 连接管理器okhttp3 本身是没有类似 apache httpcomponents 的链接管理器 org.apache.http.conn.HttpClientConnectionManager 的，我们为了在 buession-httpclient 的链接管理器实现 com.buession.httpclient.conn.OkHttpClientConnectionManager 保持一致，人为的加了一层 okhttp3 的链接管理器 okhttp3.HttpClientConnectionManager（注意：命名空间为 okhttp3），主要用于初始化连接池类 okhttp3.ConnectionPool。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/httpclient/connectionmanager.html#连接管理器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.2/httpclient/response.html",
      "children": [
        {
          "title": "响应",
          "url": "/manual/2.2/httpclient/response.html#响应",
          "content": "响应当通过 HttpClient 发起任意请求后，将得到一个 Response。此结果，包括了：协议及其版本、状态码及其信息、响应头列表、响应体、以及响应体长度。buession-httpclient 会将 apache httpcomponents 或 okhttp3 的响应对象，转换为 Response。需要注意的是，原生 apache httpcomponents 或 okhttp3 响应体流，一旦被使用过一次之后，将不能再使用；同时您只能以流或者字符串二选一的形式获取响应体。但是，在 buession-httpclient 中您将可以二者兼顾，当然这也同时会带来一些性能消耗和内存占用。import com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.core.Response;\nimport java.io.InputStream;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\nInputStream stream = response.getInputStream(); // 以流的形式获取响应体\nString body = response.getBody(); // 以字符串的形式获取响应体\n\nstream.close();\ngetInputStream、getBody 二者可以重复调用，当时您需要始终手动关闭一下流，因为这将是拷贝的原生 apache httpcomponents 或 okhttp3 返回的流。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/httpclient/response.html#响应-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.2/httpclient/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.2/httpclient/method.html#方法",
          "content": "方法buession-httpclient 提供了和 HTTP 请求方式同名的方法 API，您可以很方便的通过提供的方法发起 HTTP 请求。示例：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\");\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\");\n您可以自定义请求头：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.List;\nimport java.util.ArrayList;\n\nList headers = new ArrayList();\n\nheaders.add(new Header(\"X-SDK-NAME\", \"Buession\"));\nheaders.add(new Header(\"X-Timestamp\", System.currentTimeMillis()));\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", headers);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", headers);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", headers);\n您可以设置请求参数：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.Map;\nimport java.util.HashMap;\n\nMap parameters = new HashMap();\n\nparameters.put(\"action\", \"edit\");\nparameters.put(\"id\", 1);\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", parameters);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", parameters);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", parameters);\n您可以设置请求体：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport jcom.buession.httpclient.core.RequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\n\nEncodedFormRequestBody requestBody = new EncodedFormRequestBody();\n\nrequestBody.addRequestBodyElement(\"username\", \"buession\");\nrequestBody.addRequestBodyElement(\"password\", \"buession\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", requestBody);\n\nJsonRawRequestBody requestBody = new JsonRawRequestBody(new User());\n// PUT 请求\nResponse response = httpClient.put(\"https://www.buession.com/\", requestBody);\n不同的 RequestBody，决定了我们以什么样的 Content-Type 提交数据，buession-httpclient 中提供了大量的内置 RequestBody。"
        },
        {
          "title": "RequestBody",
          "url": "/manual/2.2/httpclient/method.html#方法-requestbody",
          "content": "RequestBody\n\nRequestBody\nContent-Type\n说明\n\n\n\n\nInputStreamRequestBody\napplication/octet-stream\n二进制请求体\n\n\nChunkedInputStreamRequestBody\napplication/octet-stream\nChunked 二进制请求体\n\n\nRepeatableInputStreamRequestBody\napplication/octet-stream\nRepeatable 二进制请求体\n\n\nEncodedFormRequestBody\napplication/x-www-form-urlencoded\n普通表单请求体\n\n\nMultipartFormRequestBody\nmultipart/form-data\n文件上传表单请求体\n\n\nHtmlRawRequestBody\ntext/html\nHTML 请求体\n\n\nJavaScriptRawRequestBody\napplication/javascript\nJavaScript 请求体\n\n\nJsonRawRequestBody\napplication/json\nJSON 请求体\n\n\nTextRawRequestBody\ntext/plain\nTEXT 请求体\n\n\nXmlRawRequestBody\ntext/xml\nXML 请求体\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/httpclient/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-io 参考手册",
      "content": "封装了对文件的操作",
      "url": "/manual/2.2/io/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/io/index.html#安装",
          "content": "安装    com.buession\n    buession-io\n    x.x.x\n\n该模块二次封装了 java java.io.File 和 java.nio.file.Files 类，在此基础上扩展了大量的实用方法，如：文件读写、获取文件 MD5 和 SHA1 值，获取文件 MIME，设置文件所属用户和用户组，简化了我们在应用开发过程中对文件的操作。"
        },
        {
          "title": "读取文件",
          "url": "/manual/2.2/io/index.html#读取文件",
          "content": "读取文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nbyte[] result = file.read();\n"
        },
        {
          "title": "写文件",
          "url": "/manual/2.2/io/index.html#写文件",
          "content": "写文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nfile.write(\"Buession\");\nfile.write(\"Buession\".getBytes());\nfile.write(\"Buession\", true); // 追加写\n"
        },
        {
          "title": "获取文件 MD5、SHA-1值",
          "url": "/manual/2.2/io/index.html#获取文件-md5、sha-1值",
          "content": "获取文件 MD5、SHA-1值import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nString md5 = file.getMd5(); // 获取文件 MD5\nString sha1 = file.getSha1(); // 获取文件 SHA-1\n"
        },
        {
          "title": "获取文件 MD5、SHA-1 值",
          "url": "/manual/2.2/io/index.html#获取文件-md5、sha-1-值",
          "content": "获取文件 MD5、SHA-1 值import com.buession.io.file.File;import com.buession.io.MimeType;\n\nFile file = new File(\"/tmp/debug.txt\");\n\nMimeType result = file.getMimeType();\n"
        },
        {
          "title": "设置文件权限",
          "url": "/manual/2.2/io/index.html#设置文件权限",
          "content": "设置文件权限import com.buession.io.file.Files;\nFiles.chmod(\"/tmp/debug.txt\", 0777);\n"
        },
        {
          "title": "设置文件用户组",
          "url": "/manual/2.2/io/index.html#设置文件用户组",
          "content": "设置文件用户组import com.buession.io.file.Files;\nFiles.chgrp(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "设置文件用户",
          "url": "/manual/2.2/io/index.html#设置文件用户",
          "content": "设置文件用户import com.buession.io.file.Files;\nFiles.chown(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "注解",
          "url": "/manual/2.2/io/index.html#注解",
          "content": "注解注解 com.buession.io.json.annotation.MimeTypeString 可以将类型为 com.buession.io.MimeType 的字段序列化为字符串和将字符串反序列化为 com.buession.io.MimeType，该功能是基于 jackson 实现的。import com.buession.io.json.annotation.MimeTypeString;\nclass File {\n\n    @MimeTypeString\n    private MimeType mime;\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/io/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-jdbc 参考手册",
      "content": "JDBC 通用 POJO 类定义，对 Hikari、Dbcp2、Druid 等配置和数据源的封装。",
      "url": "/manual/2.2/jdbc/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/jdbc/index.html#安装",
          "content": "安装    com.buession\n    buession-jdbc\n    x.x.x\n\n通过提供的 API，您可以简化对 DBCP2、Druid、Hikari、Tomcat 数据源的初始化，该类库基本不单独使用。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/jdbc/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-json 参考手册",
      "content": "主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现。",
      "url": "/manual/2.2/json/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/json/index.html#安装",
          "content": "安装    com.buession\n    buession-json\n    x.x.x\n\n封装了大量基于 jackson 的注解。"
        },
        {
          "title": "注解",
          "url": "/manual/2.2/json/index.html#注解",
          "content": "注解\n\n注解\n说明\n\n\n\n\nCalendarUnixTimestamp\njava.util.Calendar 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Calendar 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Calendar\n\n\nDateUnixTimestamp\njava.util.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Date\n\n\nSqlDateUnixTimestamp\njava.sql.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Date\n\n\nTimestampUnixTimestamp\njava.sql.Timestamp 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Timestamp 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Timestamp\n\n\nJsonEnum2Map\n枚举和 java.util.Map 序列化和反序列化；通过该注解，可以将枚举序列化为 java.util.Map；将 java.util.Map 反序列化为枚举\n\n\nSensitive\n通过该注解可以实现数据的脱敏\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/json/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-lang 参考手册",
      "content": "常用 POJO 类和枚举的定义，详细查看 API 参考手册。",
      "url": "/manual/2.2/lang/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/lang/index.html#安装",
          "content": "安装    com.buession\n    buession-lang\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/lang/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-net 参考手册",
      "content": "网络相关工具类。",
      "url": "/manual/2.2/net/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/net/index.html#安装",
          "content": "安装    com.buession\n    buession-net\n    x.x.x\n\n"
        },
        {
          "title": "IP 地址工具类",
          "url": "/manual/2.2/net/index.html#ip-地址工具类",
          "content": "IP 地址工具类IP 地址工具类 com.buession.net.utils.InetAddressUtis 实现了，IPV4 地址和数字型 IP 相互转换。import com.buession.net.utils.InetAddressUtis;\nlong result = InetAddressUtis.ip2long(\"127.0.0.1\"); // 2130706433\nString ip = InetAddressUtis.long2ip(2130706433L); // 127.0.0.1\nURI 类或 URIBuilder 类，实现了 url 字符串的构建，详细查看 API 手册。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/net/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 原生命令保持一致。同时，对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Object.class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象。",
      "url": "/manual/2.2/redis/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/redis/index.html#安装",
          "content": "安装    com.buession\n    buession-redis\n    x.x.x\n\n"
        },
        {
          "title": "介绍",
          "url": "/manual/2.2/redis/index.html#介绍",
          "content": "介绍buession-redis 是一款基于 jedis 的 redis 操作库，最大的优势就是封装了与 redis 同名、最大程度与 redis 原生参数顺序一致的 API。同时，我们在现代应用中，经常需要读写一个 pojo 对象，buession-redis 封装了 xxxObject 读写取 redis 中的二进制或 JSON 数据，并反序列化为 POJO 类。大大简化了，我们在代码中对象存取到 redis 中，让我们更专注业务功能的开。能够通过 com.buession.redis.core.Options 设置全局选项，如：统一的 Key 前缀，对象基于什么方式序列化和反序列化。import com.buession.redis.RedisTemplate;import com.buession.redis.core.Options;\nimport com.buession.core.serializer.type.TypeReference;\nimport java.utils.Map;\nimport java.utils.HashMap;\n\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.setOptions(new Options());\nredisTemplate.afterPropertiesSet();\n\n// 将 User 对象写进 key 为 user hash 中\nredisTemplate.hSet(\"user\", \"1\", new User());\n\n// 获取 key 为 user ，field 为 1 的 hash 中的数据，并转换为 User\nUser user = redisTemplate.hGetObject(\"user\", \"1\", User.class);\n\n// 获取 key 为 user 的 hash 的所有数据，并将值转换为 User\nMap data = redisTemplate.hGetAllObject(\"user\", \"1\", new TypeReference>{});\n"
        },
        {
          "title": "展望",
          "url": "/manual/2.2/redis/index.html#展望",
          "content": "展望目前，buession-redis 仅支持 jedis，不支持 lettuce，我们计划在 2.3 ~ 2.5 的版本中计划加入。其实，之前尝试过，但由于两者 API 差异性和使用方式太大，没法很好的做到统一化，就暂时放弃了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/redis/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.2/redis/datasource.html",
      "children": [
        {
          "title": "数据源",
          "url": "/manual/2.2/redis/datasource.html#数据源",
          "content": "数据源buession-redis 基于数据源 DataSource 连接 redis，其机制类似 JDBC 的 DataSource。通过，数据源可以配置，redis 的用户名、密码、连接超时、读取超时、连接池、SSL等等。数据源 DataSource 包括三个子接口：StandaloneDataSource：单机模式数据源\nSentinelDataSource：哨兵模式数据源\nClusterDataSource：集群模式数据源\njedis 和后续的 lettuce 分别实现这三个接口，用于创建不通模式的数据源，数据源中实现了连接池的创建。在原始的 jedis 或者 spring-data-redis 中，密码为空字符串时，会以空字符串密码进行登录 redis；我们修改了这一逻辑，不管您在程序中密码是设置的 null 还是空字符串，我们都会跳过认证。这样的好处就是，假设您的开发环境 redis 没有设置密码，生产环境设置了密码，我们可以通过一个 bean 初始化即可，不用写成两个 bean。测试环境 properties：redis.host=127.0.0.1redis.port=6379\nredis.password=\n生产环境 properties：redis.host=192.168.100.131redis.port=6379\nredis.password=passwd\n"
        },
        {
          "title": "连接池",
          "url": "/manual/2.2/redis/datasource.html#数据源-连接池",
          "content": "连接池通过连接池管理 redis 连接，能够大大的提高效率和节约资源的使用。jedis 和 lettuce 均使用 apache commons-pool2 来创建和维护连接池。但是，在 jedis 中，以 JedisPoolConfig 和 ConnectionPoolConfig 来管理单例模式连接池、哨兵模式连接池和集群模式连接池；为了简化配置，我们定义了 com.buession.redis.core.PoolConfig 来统一维护各种模式的连接池配置，然后在各 DataSource 中转换为原生的连接池配置，极大的简化了学习和替换成本。连接池配置\n\n配置项\n数据类型\n-- 默认值\n说明\n\n\n\n\nlifo\nboolean\nGenericObjectPoolConfig.DEFAULT_LIFO\n池模式，为 true 时，后进先出；为 false 时，先进先出\n\n\nfairness\nboolean\nGenericObjectPoolConfig.DEFAULT_FAIRNESS\n当从池中获取资源或者将资源还回池中时，是否使用 java.util.concurrent.locks.ReentrantLock 的公平锁机制\n\n\nmaxWait\nDuration\nGenericObjectPoolConfig.DEFAULT_MAX_WAIT\n当连接池资源用尽后，调用者获取连接时的最大等待时间\n\n\nminEvictableIdleTime\nDuration\n60000\n连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除\n\n\nsoftMinEvictableIdleTime\nDuration\nGenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION\n连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数\n\n\nevictionPolicyClassName\nString\nGenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME\n驱逐策略的类名\n\n\nevictorShutdownTimeout\nDuration\nGenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT\n关闭驱逐线程的超时时间\n\n\nnumTestsPerEvictionRun\nint\n-1\n检测空闲对象线程每次运行时检测的空闲对象的数量\n\n\ntestOnCreate\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE\n在创建对象时检测对象是否有效，配置 true 会降低性能\n\n\ntestOnBorrow\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW\n在从对象池获取对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestOnReturn\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN\n在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestWhileIdle\nboolean\ntrue\n在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\nblockWhenExhausted\nboolean\nGenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED\n当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效；false 连接池没有资源立马抛异常）\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\njmxEnabled\nboolean\nGenericObjectPoolConfig.DEFAULT_JMX_ENABLE\n是否注册 JMX\n\n\njmxNamePrefix\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX\nJMX 前缀\n\n\njmxNameBase\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_BASE\n使用 base + jmxNamePrefix + i 来生成 ObjectName\n\n\nmaxTotal\nint\nGenericObjectPoolConfig.DEFAULT_MAX_TOTAL\n最大连接数\n\n\nminIdle\nint\nGenericObjectPoolConfig.DEFAULT_MIN_IDLE\n最小空闲连接数\n\n\nmaxIdle\nint\nGenericObjectPoolConfig.DEFAULT_MAX_IDLE\n最大空闲连接数\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/redis/datasource.html#数据源-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.2/redis/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.2/redis/method.html#方法",
          "content": "方法buession-redis BaseRedisTemplate 的方法以及参数计划与原生 redis 命名保持一致。复杂的参数会通过 Builder 进行参数构建，在多个值中进行选择的将定义成枚举，规避出错的几率。import com.buession.redis.BaseRedisTemplate;\nBaseRedisTemplate redisTemplate = new BaseRedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 删除哈希表 key 中的一个或多个指定域\nredisTemplate.hDel(\"user\", \"1\", \"2\", \"3\");\n\n// 检查给定 key 是否存在\nredisTemplate.exists(\"user\");\n\n// 获取列表 key 中，下标为 index 的元素\nredisTemplate.lIndex(\"user\", 1);\n\n// 如果键 key 已经存在并且它的值是一个字符串，将 value 追加到键 key 现有值的末尾\nredisTemplate.append(\"key\", \"value 1\");\nBaseRedisTemplate 实现了 redis 的原生操作，RedisTemplate 继承了 BaseRedisTemplate ，在此基础上实现了将 redis 中的二进制或者 JSON 格式的值，反序列化为一个类。import com.buession.redis.RedisTemplate;\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 获取列表 key 中，下标为 index 的元素，并反序列化为 User 类\nUser user = redisTemplate.lIndexObject(\"user\", 1, User.class);\n序列化和反序列化，基于 buession-core 序列化和反序列化 扩展而来，序列化或反序列化出错时会直接返回 null，而忽略异常，默认使用 com.buession.redis.serializer.JacksonJsonSerializer 序列化为 JSON。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/redis/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-session 参考手册",
      "content": "无文档",
      "url": "/manual/2.2/session/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/session/index.html#安装",
          "content": "安装    com.buession\n    buession-session\n    x.x.x\n\n该模块无实际意义，将在今后的版本中会删除掉。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/session/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-thesaurus 参考手册",
      "content": "对词库的解析，目前仅支持搜狗词条。",
      "url": "/manual/2.2/thesaurus/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/thesaurus/index.html#安装",
          "content": "安装    com.buession\n    buession-thesaurus\n    x.x.x\n\n您可以通过该库解析搜狗拼音的词条库，包括词条、拼音信息。import com.buession.thesaurus.SogouParser;import com.buession.thesaurus.Parser;\nimport com.buession.thesaurus.core.Word;\nimport java.util.Set;\n\nParser parser = new SogouParser();\n\nSet words parser.parse(\"搜谱拼音词条文件路径\");\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/thesaurus/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-velocity 参考手册",
      "content": "spring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity。",
      "url": "/manual/2.2/velocity/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/velocity/index.html#安装",
          "content": "安装    com.buession\n    buession-velocity\n    x.x.x\n\n该类库，基本照搬了 springframework 集成 velocity 的代码和逻辑。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/velocity/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "web 相关的功能封装，支持 servlet 和 reactive；封装了一些常用注解，简化了业务层方面的代码实现；封装了一些常用 filter。",
      "url": "/manual/2.2/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/web/index.html#安装",
          "content": "安装    com.buession\n    buession-web\n    x.x.x\n\nbuession-web 扩展了 spring-webmvc、spring-webflux；在此基础上，提供了大量的实用的 filter 和注解，以及工具类。该模块无论封装的 filter、注解、工具类，均适用于 spring mvc，也适用于 spring webflux。当时，filter、工具类均为 servlet 和 webflux 各自独立的类，不是说同一个类，即能用于 servlet，也能用于 webflux，当然注解是通用的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.2/web/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/2.2/web/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了一些我们在日常应用开发过程中能用到的实用性功能，简化了您在代码开发中的便捷度，让您能够更专注业务的开发。"
        },
        {
          "title": "注解",
          "url": "/manual/2.2/web/annotation.html#注解-注解",
          "content": "注解\n\n注解\nRequest / Response\n作用域\n说明\n\n\n\n\n@RequestClientIp\nrequest\n方法参数\n获取当前请求的客户端 IP 地址，支持返回字符串、long 类型的 IP 地址，以及 InetAddress\n\n\n@ContentType\nresponse\n类、方法\n设置响应 Content-Type\n\n\n@HttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值\n\n\n@DisableHttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值，禁止缓存\n\n\n@ResponseHeader\nresponse\n类、方法\n设置响应头\n\n\n@ResponseHeaders\nresponse\n类、方法\n批量设置响应头\n\n\n@DocumentMetaData\nresponse\n类、方法\n设置页面标题、页面编码、关键字、描述、版权等等元信息\n\n\n获取用户端真实 IP@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/ip1\")\n\t@ResponseBody\n\tpublic String ip1(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip2\")\n\t@ResponseBody\n\tpublic Long ip2(@RequestClientIp long ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip3\")\n\t@ResponseBody\n\tpublic InetAddress ip3(@RequestClientIp InetAddress ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n您也可以指定获取用户真实 IP 的请求头列表，若未指定则使用 RequestUtils.getClientIp(request) 方法获取，获取顺序参考：RequestUtils.CLIENT_IP_HEADERS@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/ip1\")\n\t@ResponseBody\n\tpublic String ip1(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip2\")\n\t@ResponseBody\n\tpublic Long ip2(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) long ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip3\")\n\t@ResponseBody\n\tpublic InetAddress ip3(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) InetAddress ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n设置页面缓存@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/cache\")\n\t@HttpCache(expires = \"5\")\n\t@ResponseBody\n\tpublic String cache(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n以上，会自动计算 Cache-Control 和 pragma 的值。当然，您也可以手动指定。@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/cache\")\n\t@HttpCache(expires = \"5\", cacheControl=\"public, max-age=5\")\n\t@ResponseBody\n\tpublic String cache(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.2/web/filter.html",
      "children": [
        {
          "title": "过滤器",
          "url": "/manual/2.2/web/filter.html#过滤器",
          "content": "过滤器我们封装了一些使用的过滤器，简化了您的开发或者应用运行的跟踪。servlet 包位于 com.buession.web.servlet.filter，webflux 包位于 com.buession.web.reactive.filter，均有同样类名的过滤器类。"
        },
        {
          "title": "过滤器",
          "url": "/manual/2.2/web/filter.html#过滤器-过滤器",
          "content": "过滤器\n\n过滤器\n说明\n\n\n\n\nPoweredByFilter\nPowered By 过滤器\n\n\nPrintUrlFilter\n打印当前请求 URL 过滤器\n\n\nResponseHeaderFilter\n响应头过滤器，设置响应头\n\n\nResponseHeadersFilter\n响应头过滤器，批量设置响应头\n\n\nServerInfoFilter\nServer 信息过滤器，通过响应头的形式，输出当前服务器名称，可以用于集群环境定位出现问题的节点\n\n\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.2/web/restful.html",
      "children": [
        {
          "title": "RESTFUL",
          "url": "/manual/2.2/web/restful.html#restful",
          "content": "RESTFULRestful 是当今比较流行的一种架构的规范与约束、原则，基于这个风格设计的软件可以更简洁、更有层次。我们遵循 REST 规范，在代码层面规范好了新增、修改、详情、删除等基本的路由，您的控制器层只需要继承 com.buession.web.servlet.mvc.controller.AbstractBasicRestController 或者 com.buession.web.reactive.mvc.controller.AbstractBasicRestController 即可在 servlet 或 webflux 模式下，实现标准的 REST 风格的代码。简化了您的代码（主要是不用再写 @RequestMapping）和标准化了。@RestController@RequestMapping(path = \"/example\")\npublic class ExampleController extends AbstractRestController {\n\n\t@Override\n\tpublic Response add(HttpServletRequest request, HttpServletResponse response, @RequestBody ExampleDto example){\n\t\t\n\t}\n\n\t@Override\n\tpublic Response edit(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id, @RequestBody ExampleDto example){\n\n\t}\n\n\t@Override\n\tpublic Response detail(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\n\t}\n\n\t@Override\n\tpublic Response delete(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.2/web/utils.html",
      "children": [
        {
          "title": "工具",
          "url": "/manual/2.2/web/utils.html#工具",
          "content": "工具我们封装了一些 web 相关的工具类，用于处理 request、response。servlet 包位于 com.buession.web.servlet.http，webflux 包位于 com.buession.web.reactive.http，均有同样类名的过滤器类。获取客户端真实 IP 地址：RequestUtils.getClientIp(request);我们兼容了，通过微信和一些 CDN 厂商获取用户真实 IP 的头信息，我们优先获取从微信透传过来的用户的真实 IP，然后再是各 CDN 厂商的用户真实 IP 头，最后才是标准的真实 IP 头。当然，我们不能保证是否是伪造的。优先顺序：X-Forwarded-For-Pound（微信） > Ali-Cdn-Real-Ip（阿里云 CDN） > Cdn-Src-Ip（网宿） > X-Cdn-Src-Ip（网宿） > X-Original-Forwarded-For（天翼云） > X-Forwarded-For > X-Real-Ip > Proxy-Client-IP > WL-Proxy-Client-IP > Real-ClientIP > remote addr是否是 Ajax 请求：RequestUtils.isAjaxRequest(request);是否是移动设备请求：RequestUtils.isMobile(request);设置缓存：ResponseUtils.httpCache(response, 5); // 缓存 5 秒ResponseUtils.httpCache(response, new Date()); // 缓存到指定的时间点\n"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Framework API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-aop\n使用帮助\nAPI 手册\n\n\nbuession-beans\n使用帮助\nAPI 手册\n\n\nbuession-core\n使用帮助\nAPI 手册\n\n\nbuession-cron\n使用帮助\nAPI 手册\n\n\nbuession-dao\n使用帮助\nAPI 手册\n\n\nbuession-geoip\n使用帮助\nAPI 手册\n\n\nbuession-git\n使用帮助\nAPI 手册\n\n\nbuession-httpclient\n使用帮助\nAPI 手册\n\n\nbuession-io\n使用帮助\nAPI 手册\n\n\nbuession-jdbc\n使用帮助\nAPI 手册\n\n\nbuession-json\n使用帮助\nAPI 手册\n\n\nbuession-lang\n使用帮助\nAPI 手册\n\n\nbuession-net\n使用帮助\nAPI 手册\n\n\nbuession-redis\n使用帮助\nAPI 手册\n\n\nbuession-session\n使用帮助\nAPI 手册\n\n\nbuession-thesaurus\n使用帮助\nAPI 手册\n\n\nbuession-velocity\n使用帮助\nAPI 手册\n\n\nbuession-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.1/index.html",
      "children": []
    },
    {
      "title": "buession-aop 参考手册",
      "content": "AOP 封装，方便实现自定义注解",
      "url": "/manual/2.1/aop/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/aop/index.html#安装",
          "content": "安装    com.buession\n    buession-aop\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/aop/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-beans 参考手册",
      "content": "该类库提供了基于 commons-beanutils 和 cglib（spring-core 包中，名称空间：org.springframework.cglib.beans） 的 bean 工具的二次封装。包括了属性拷贝和属性映射，以及对象属性转换为 Map。",
      "url": "/manual/2.1/beans/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/beans/index.html#安装",
          "content": "安装    com.buession\n    buession-beans\n    x.x.x\n\n"
        },
        {
          "title": "属性拷贝",
          "url": "/manual/2.1/beans/index.html#属性拷贝",
          "content": "属性拷贝使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 和 target 的属性做映射，实现同名拷贝。import com.buession.beans.BeanUtils;\nBeanUtils.copyProperties(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.copyProperties(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "属性映射",
          "url": "/manual/2.1/beans/index.html#属性映射",
          "content": "属性映射使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 转换为驼峰格式后和 target 的属性做映射，实现拷贝，这是 populate 和 copyProperties 的唯一区别。import com.buession.beans.BeanUtils;\nBeanUtils.populate(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.populate(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "Bean 转换为 Map",
          "url": "/manual/2.1/beans/index.html#bean-转换为-map",
          "content": "Bean 转换为 Map使用此方法，可以实现将一个 bean 对象转换为 Map，bean 的属性作为 Map 的 Keyimport com.buession.beans.BeanUtils;\nMap result = BeanUtils.toMap(bean)\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/beans/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "该类库为核心包，包括构建器、工具类、数据验证、ID 生成器、转换器、序列化、数学方法、消息注入、Manager 层注解、日期时间类和各通用接口定义。",
      "url": "/manual/2.1/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/core/index.html#安装",
          "content": "安装    com.buession\n    buession-core\n    x.x.x\n\n构建器Map、集合的便捷式构建，减少您的代码行数编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中收集器数组、Map、集合的工具类上下文定义应用上下文的类库、注解转换器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。日期时间日期、时间工具ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。数学函数定义了实用的数学函数序列化和反序列化对象的序列化和反序列化，包括二进制和 JSON。验证器数据验证器及其注解工具类常用通用性工具类其它通用的接口定义，框架自身类异常通用异常的定义"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/builder.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.1/core/builder.html#构建器",
          "content": "构建器Map、集合的便捷式构建，减少您的代码行数。您需要往 Map、List 中添加元素的传统写法是：import java.util.ArrayList;import java.util.List;\nimport java.util.HashMap;\nimport java.util.Map;\n\nList list = new ArrayList();\nlist.add(\"A\");\nlist.add(\"B\");\nlist.add(\"C\");\n\nMap map = new HashMap();\nmap.put(\"a\", \"A\");\nmap.put(\"b\", \"B\");\nmap.put(\"c\", \"C\");\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.Map;\n\nList list = ListBuilder.create().add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create().put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n此时的 List、Map 的实现类是 java.util.ArrayList、java.util.HashMap；您可以通过 create 指定其实现类。import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.LinkedList;\nimport java.util.Map;\nimport java.util.LinkedHashMap;\n\nList list = ListBuilder.create(LinkedList.class).add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create(LinkedHashMap.class).put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n注：实现类必须为 public，且必须有无参数的访问权限为 public 的构造函数\n当您有 value 为 null 时，不添加到 List 时，传统写法：import java.util.ArrayList;import java.util.List;\n\nString value = null;\nList list = new ArrayList();\n\nif(value != null){\n\tlist.add(value);\n}\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import java.util.List;\n\nString value = null;\nList list = ListBuilder.create().addIfPresent(value).build();\nMap、Set、Queue 同理。"
        },
        {
          "title": "便捷方法",
          "url": "/manual/2.1/core/builder.html#构建器-便捷方法",
          "content": "便捷方法\n\n方法\n说明\n\n\n\n\n List ListBuilder.epmty()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of(V value)\n创建仅有一个元素的 V 类型的 List 对象\n\n\n Queue QueueBuilder.epmty()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Queue 对象\n\n\n Set SetBuilder.epmty()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Set 对象\n\n\n Map MapBuilder.epmty()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of(V value)\n创建仅有一个元素的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\nempty 与 jdk Collections.emptyList()、Collections.emptySet()、Collections.emptyMap() 的本质区别是返回的 List 实例不同，该方法创建的 List、Set、Map 实例是允许对 List、Set、Map 做操作，而 jdk Collections 创建的 List、Set、Map 则是不允许的。两个 of 方法均同理。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/builder.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/codec.html",
      "children": [
        {
          "title": "编码器",
          "url": "/manual/2.1/core/codec.html#编码器",
          "content": "编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中。我们在当前现代应用中，通常会在业务上定义业务错误信息。且我们返回给前端的可能是更模糊或通用的、人为可读性更强的错误信息，而且可能两种不同原因引起的错误，但是我们在返回给前端时的错误信息是一模一样的，这时我们就需要更精确的标识来定义错误，通常为错误码。此时，我们在应用中，通常会定义错误码和错误信息的映射关系。我们可用的方法大致是，第一代码里面写死；第二，定义错误枚举或者常量。无论哪种方式的都与代码高度耦合，可读性和可维护性都差。此时此刻，您可以通过 buession framework 的注解 @Message 来简化和解耦您的错误信息配置和代码。在传统 springmvc 应用中，您可以通过 context:property-placeholder 或者 util:properties 标签将错误信息 properties 配置文件加载到当前应用环境中。USER_NOT_FOUND.code = 10404USER_NOT_FOUND.message = 用户不存在\n\nUSER_LOGIN_FAILURE.code = 10405\nUSER_LOGIN_FAILURE.message = 登录失败\n\n\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(\"USER_NOT_FOUND\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n您可以自定义错误代码和错误消息的 key，默认分别为：code、message。此时您需要在注解 @Message 上显示指定错误代码和错误消息的 key。USER_NOT_FOUND.errorCode = 10404USER_NOT_FOUND.errorMessage = 用户不存在\n\nUSER_LOGIN_FAILURE.errorCode = 10405\nUSER_LOGIN_FAILURE.errorMessage = 登录失败\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(value = \"USER_NOT_FOUND\", code = \"errorCode\", message = \"errorMessage\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/codec.html#编码器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/collect.html",
      "children": [
        {
          "title": "收集器",
          "url": "/manual/2.1/core/collect.html#收集器",
          "content": "收集器数组、Map、集合的工具类"
        },
        {
          "title": "数组",
          "url": "/manual/2.1/core/collect.html#收集器-数组",
          "content": "数组数组工具类 Arrays 继承自 org.apache.commons.lang3.ArrayUtils 封装了元素是否存在检测、元素索引位置获取、拼接成字符串、转换为 List、Set 以及字符串类型的数组、数组合并、数组元素操作等方法。检测数组 array 中是否存在元素 element：import com.buession.core.collect.Arrays;\nboolean result = Arrays.contains(array, element);\n返回元素 element 在数组 array 中第一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.indexOf(array, element);\n返回元素 element 在数组 array 中最后一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.lastIndexOf(array, element);\n将字符串拼接会字符串：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString result = Arrays.toString(array);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString glue = \"-\";\nString result = Arrays.toString(array, glue);\n// 1-2-3\n可以通过方法 toList、toSet 转换为 List 和 Set：import com.buession.core.collect.Arrays;import java.util.List;\nimport java.util.Set;\n\nint[] array = {1, 2, 3};\nList list = Arrays.toList(array);\nSet set = Arrays.toSet(array);\n将数组转换为字符串类型的数组：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString[] result = Arrays.toStringArray(array);\n将数组进行合并：import com.buession.core.collect.Arrays;\nString[] result = Arrays.toStringArray(array1, array2, array3);\n对数组元素进行操作：import com.buession.core.collect.Arrays;\nString[] array = {\"A\", \"B\", \"C\"};\nString[] result = Arrays.map(array, String.class, fn);\n第二个参数为数组元素类型，第三个参数为 java.util.function.Function 的实现"
        },
        {
          "title": "Lists",
          "url": "/manual/2.1/core/collect.html#收集器-lists",
          "content": "ListsList 工具类 Lists 实现了将元素拼接成字符串、转换为 Set 操作。将字符串拼接会字符串：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString result = Lists.toString(list);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Lists.toString(list);\n// 1-2-3\n可以通过方法 toSet 将 List 转换为 Set：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nSet set = Lists.toSet(list);\n"
        },
        {
          "title": "Sets",
          "url": "/manual/2.1/core/collect.html#收集器-sets",
          "content": "SetsSett 工具类 Sets 实现了将元素拼接成字符串、转换为 List 操作。将字符串拼接会字符串：import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString result = Sets.toString(set);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Sets.toString(list);\n// 1-2-3\n可以通过方法 toList 将 Set 转换为 List：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nList list = Sets.toList(set);\n"
        },
        {
          "title": "Maps",
          "url": "/manual/2.1/core/collect.html#收集器-maps",
          "content": "MapsMap 工具类 Maps 实现了对 key 和 value 进行操作，实现了将 value 转换为 List 和 Set。对 Map 进行操作：import com.buession.core.collect.Maps;import java.util.Map;\nimport java.util.HashMap;\n\nMap maps = new HashMap();\nMap result = Maps.map(maps, (key)->key, (value)->value == null ? null : value.toString());\n第二个、第三参数为 java.util.function.Function 的实现可以通过方法 toList 将 Map 的 value 转换为 List：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\n\nList list = Maps.toList(maps);\n可以通过方法 toSet 将 Map 的 value 转换为 Set：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.Set;\n\nSet set = Maps.toSet(maps);\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/collect.html#收集器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/context.html",
      "children": [
        {
          "title": "上下文",
          "url": "/manual/2.1/core/context.html#上下文",
          "content": "上下文注解 com.buession.core.context.stereotype.Manager 用于在分层应用中，标记当前类是一个 manager 类。类似于 org.springframework.stereotype.Service 加上该注解会将当前类自动注入到 spring 容器中，用法和 @Service 一样。在多层应用架构中 Manager 层通常为：通用业务处理层，处于 Dao 层之上，Service 层之下。主要特征如下：逻辑少\n与 Dao 层进行交互，多个 Dao 层的复用\nService 通用底层逻辑的下沉，如：缓存方案、中间件通用处理、以及对第三方平台封装的层\nimport com.buession.core.context.stereotype.Manager;import org.springframework.stereotype.Service;\n\npublic interface UserManager {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Manager\npublic class UserManagerImpl implements UserManager {\n\n\t@Autowired\n\tprivate UserDao userDao;\n\n\t@Autowired\n\tprivate UserProfileDao userProfileDao;\n\n\t@Autowired\n\tprivate RedisTemplate redisTemplate;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = redisTemplate.hGetObject(\"user\", Integer.toString(id), User.class);\n\n\t\tif(user == null){\n\t\t\tuser = userDao.getByPrimary(id);\n\t\t\tif(user != null){\n\t\t\t\tuser.setProfile(userProfileDao.getByUserId(id));\n\t\t\t\tredisTemplate.hSet(\"user\", Integer.toString(id), user);\n\t\t\t}else{\n\t\t\t\tthrow new RuntimeException(\"用户不存在\");\n\t\t\t}\n\t\t}\n\n\t\treturn user;\n\t}\n\n}\n\npublic interface UserService {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Service\npublic class UserServiceImpl implements UserService {\n\n\t@Autowired\n\tprivate UserManager userManager;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = userManager.getByPrimary(id);\n\n\t\t...\n\n\t\treturn user;\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/context.html#上下文-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/converter.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.1/core/converter.html#构建器",
          "content": "构建器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。接口定义：@FunctionalInterfacepublic interface Converter {\n\n\tT convert(final S source);\n\n}\n将类似为 S 的对象转换为类型为 T 的对象。"
        },
        {
          "title": "内置转换器",
          "url": "/manual/2.1/core/converter.html#构建器-内置转换器",
          "content": "内置转换器\n\n转换器\n说明\n\n\n\n\nArrayConverter\n将 S 类型的数组转换为 T 类型的数组\n\n\nEnumConverter\n枚举转换器，将字符串转换为枚举 E\n\n\nBinaryEnumConverter\n枚举转换器，将 byte 数组转换为枚举 E\n\n\nBooleanStatusConverter\n将布尔值转换为 com.buession.lang.Status\n\n\nStatusBooleanConverter\n将 com.buession.lang.Status 转换为布尔值\n\n\nPredicateStatusConverter\n通过 java.util.function.Predicate 对某种数据类型的数据进行判断结果转换为 com.buession.lang.Status\n\n\nListConverter\n将 S 类型的 List 对象转换为 T 类型的 List 对象\n\n\nSetConverter\n将 S 类型的 Set 对象转换为 T 类型的 Set 对象\n\n\nMapConverter\n将 Key 为 SK 类型、值为 SV 类型的 Map 对象转换为 Key 为 TK 类型、值为 TV 类型的 Map\n\n\n将 key 为 Integer 类型，value 为 Object 类型的 Map 转换成 key 为 String 类型，value 为 String 类型的 Map 对象import com.buession.core.converter.MapConverter;import java.util.Map;\n\nMap source;\nMap target;\nMapConverter converter = new MapConverter();\n\ntarget = converter.convert(source);\n将字符串转换为枚举import com.buession.core.converter.EnumConverter;import com.buession.lang.Gender;\n\nGender target;\nEnumConverter converter = new EnumConverter(Gender.class);\n\ntarget = converter.convert(\"FEMALE\");\n// Gender.FEMALE\n\ntarget = converter.convert(\"F\");\n// null\n"
        },
        {
          "title": "POJO 类映射",
          "url": "/manual/2.1/core/converter.html#构建器-pojo-类映射",
          "content": "POJO 类映射我们通过 com.buession.core.converter.mapper.Mapper 接口约定了，基于 mapstruct POJO 类的映射接口规范。关于 mapstruct 的更多文章，可通过搜索引擎自行搜索。public interface Mapper {\n\t/**\n\t * 将源对象映射到目标对象\n\t *\n\t * @param object\n\t * \t\t源对象\n\t *\n\t * @return 目标对象实例\n\t */\n\tT mapping(S object);\n\n\t/**\n\t * 将源对象数组映射到目标对象数组\n\t *\n\t * @param object\n\t * \t\t源对象数组\n\t *\n\t * @return 目标对象实例数组\n\t */\n\tT[] mapping(S[] object);\n\n\t/**\n\t * 将源 list 对象映射到目标 list 对象\n\t *\n\t * @param object\n\t * \t\t源 list 对象\n\t *\n\t * @return 目标对象 list 实例\n\t */\n\tList mapping(List object);\n\n\t/**\n\t * 将源 set 对象映射到目标 set 对象\n\t *\n\t * @param object\n\t * \t\t源 set 对象\n\t *\n\t * @return 目标对象 set 实例\n\t */\n\tSet mapping(Set object);\n\n}\n我们还可以使用工具类 com.buession.core.converter.mapper.PropertyMapper 将值从提供的源映射到目标，此方法来拷贝并简单修改于：springboot 中的 org.springframework.boot.context.properties.PropertyMapper，和原生 springboot 中的用法一样；并在此基础上增加了方法 alwaysApplyingWhenHasText()，用于判断映射源是否为 null 或者是否含有字符串。import com.buession.core.converter.mapper.PropertyMapper;\nUser source = new User();\nUser target = new User();\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();\npropertyMapper.form(source::getId).to(target:setId)\n// null\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();\npropertyMapper.form(source::getUsername).to(target:setUsername)\n// null\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/converter.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/datetime.html",
      "children": [
        {
          "title": "日期时间",
          "url": "/manual/2.1/core/datetime.html#日期时间",
          "content": "日期时间日期、时间工具。目前，仅有少量的 API，参考 PHP 中的日期时间函数而来。获取当前 Unix 时间戳（秒）：import com.buession.core.datetime.DateTime;\nDateTime.unixtime();\n该方法我们在实际业务中经常用到。以 \"msec sec\" 的格式返回当前 Unix 时间戳和微秒数：import com.buession.core.datetime.DateTime;\nDateTime.microtime();\n// 1657171717 948000\n该方法参考 PHP 的 microtime 函数而来。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/datetime.html#日期时间-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/id.html",
      "children": [
        {
          "title": "ID 生成器",
          "url": "/manual/2.1/core/id.html#id-生成器",
          "content": "ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。您可以通过 buession framework 提供的 ID 生成器，生成唯一 ID。接口规范。public interface IdGenerator {\n\t/**\n\t * 获取下一个 ID\n\t *\n\t * @return ID\n\t */\n\tT nextId();\n\n}\n"
        },
        {
          "title": "ID 生成器",
          "url": "/manual/2.1/core/id.html#id-生成器-id-生成器",
          "content": "ID 生成器\n\n生成器\n说明\n\n\n\n\nAtomicSimpleIdGenerator\n基于 AtomicLong 递增的，简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nAtomicUUIDIdGenerator\n基于 AtomicLong 递增的，UUID ID 生成器\n\n\nNanoIDIdGenerator\njnanoid ID 生成器，可通过构造函数指定字符范围、长度\n\n\nRandomDigitIdGenerator\n随机数 ID 生成器，返回指定范围内的随机数，默认为 1L ~ Long.MAX_VALUE，可通过构造函数指定\n\n\nRandomIdGenerator\n随机字符 ID 生成器，可通过构造函数指定生成长度，默认 16 位\n\n\nSimpleIdGenerator\n简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nSnowflakeIdGenerator\n雪花算法 ID 生成器，此处不解决时钟回拨的问题，您可以通过构造函数指定开始时间、数据中心 ID、数据中心 ID 所占的位数等等值\n\n\nUUIDIdGenerator\nUUID ID 生成器\n\n\nimport com.buession.core.id.AtomicUUIDIdGenerator;import com.buession.core.id.NanoIDIdGenerator;\nimport com.buession.core.id.SnowflakeIdGenerator;\nimport com.buession.core.id.UUIDIdGenerator;\nimport com.buession.core.id.SimpleIdGenerator;\n\nAtomicUUIDIdGenerator idGenerator = new AtomicUUIDIdGenerator();\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000001\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000002\n\nNanoIDIdGenerator idGenerator = new NanoIDIdGenerator();\nidGenerator.nextId(); // omRdTPCug5z_Uk1E_x3ozu3Avyyl3XSK\n\nSnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();\nidGenerator.nextId(); // 170602258864545792\n\nUUIDIdGenerator idGenerator = new UUIDIdGenerator();\nidGenerator.nextId(); // 8634a166-e7d6-4160-85eb-3433107de5a4\n\nSimpleIdGenerator idGenerator = new SimpleIdGenerator();\nidGenerator.nextId(); // 26d9ed57fdad443894b988eeabc69c05\n注：关于雪花算法、jnanoid 算法的可自行搜索。\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/id.html#id-生成器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/math.html",
      "children": [
        {
          "title": "数学函数",
          "url": "/manual/2.1/core/math.html#数学函数",
          "content": "数学函数定义了实用的数学函数。\n\n方法\n说明\n\n\n\n\ncontinuousSum\n计算两个数之间连续相加之和\n\n\nrangeValue\n获取合法范围内的值，如果 value 小于 min，则返回 min；如果 value 大于 max，则返回 max；否则返回 value 本身\n\n\nimport com.buession.core.math.Math;\nlong result = Math.continuousSum(1, 100);\n// 5050\nimport com.buession.core.math.Math;\nlong value = 3;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 4\nimport com.buession.core.math.Math;\nlong value = 5;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 5\nimport com.buession.core.math.Math;\nlong value = 11;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 10\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/math.html#数学函数-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/serializer.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.1/core/serializer.html#构建器",
          "content": "构建器对象的序列化和反序列化，包括二进制和 JSON。您可以通过该 API，实现将对象序列化成二进制或 JSON 字符串；或将二进制、JSON 字符串反序列化为对象。"
        },
        {
          "title": "序列化、反序列化类",
          "url": "/manual/2.1/core/serializer.html#构建器-序列化、反序列化类",
          "content": "序列化、反序列化类\n\n类\n说明\n\n\n\n\nDefaultByteArraySerializer\n将对象序列化为二进制，或将二进制反序列化为对象\n\n\nFastJsonJsonSerializer\n基于 FastJSON 的对象与 JSON 之间的序列化和反序列化\n\n\nGsonJsonSerializer\n基于 Gson 的对象与 JSON 之间的序列化和反序列化\n\n\nJacksonJsonSerializer\n基于 Jackson2 的对象与 JSON 之间的序列化和反序列化\n\n\n通用标准方法是，将对象序列化为字符串，或将字符串反序列化为对象\nDefaultByteArraySerializer 序列化成字符串，逻辑是在对象序列化为 byte 数组后，通过 URLEncoder.encode 进行编码；反序列化，则先通过 URLDecoder.decode 进行解码成 byte 数组，在反序列化为对象\nDefaultByteArraySerializer 支持对象与 byte 数组数组之间的序列化和反序列化\n在将 JSON 字符串反序列化为对象时，默认返回类型依据 fastjson、jackson 等原生逻辑\nFastJsonJsonSerializer、GsonJsonSerializer、JacksonJsonSerializer 可以通过参数 Class、TypeReference 指定返回的对象类型\ncom.buession.core.serializer.type.TypeReference 是某类型的一个指向或者引用，用于屏蔽 fastjson、gson、jackson 中通过 JDK Type 指定反序列化的类型；在 fastjson、gson 中是直接指定 Type，在 jackson 中是通过 com.fasterxml.jackson.core.type.TypeReference 类返回\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/serializer.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/validator.html",
      "children": [
        {
          "title": "验证器",
          "url": "/manual/2.1/core/validator.html#验证器",
          "content": "验证器数据验证器及其注解。该 API 提供了大量通用的适用于本土化的常用验证方法，如：判断是否为 null、判断是否不为 null、判断（字符串、数组、集合、Map等）是否为空、判断（字符串、数组、集合、Map等）是否不为空、IP 地址合法性验证、ISBN 合法性验证、身份证号码验证、QQ 验证。并提供对应的基于 javax.validation 的校验注解。验证是否为 null判断任意对象是否为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNull(obj);\n验证是否不为 null判断任意对象是否不为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNotNull(obj);\n判断字符串是否为空白字符串判断字符串是否为空白字符串，为 null 或长度为 0 时也返回 true；其余情况，字符串中含有任意可打印字符串则为 falseimport com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\n\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\na\";\nboolean result = Validate.isBlank(str); // false\n注：isNotBlank 与之相反\n判断是否为空isEmpty 可判断字符串、数组、集合、Map 是否为空，即：为 null 或长度/大小为 0 时，表示为空import com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isEmpty(str); // true\n\nString str = \" \";\nboolean result = Validate.isEmpty(str); // false\n\nboolean result = Validate.isEmpty(new String[]{}); // true\n\nboolean result = Validate.isEmpty(new Integer[]{1}); // false\n注：isNotEmpty 与之相反\n判断是否在两个数之间isBetween 可判断一个整型或浮点型，是否在两个整型或者浮点型数字之间import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3); // true\n\nboolean result = Validate.isBetween(2, 2, 3); // false\n可通过参数设置是否包含边界值import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3, true); // true\n\nboolean result = Validate.isBetween(2, 2, 3, true); // true\n判断是否为电话号码isTel 可判断一个字符串是否为电话号码，仅支持普通座机号码，不支持 110、400、800等特殊号码。格式，需符合：86-区号-电话号码、区号-电话号码、（区号）电话号码、(区号)电话号码、电话号码。可通过参数 com.buession.core.validator.routines.TelValidator.AreaCodeType 指定区号的控制策略。仅支持中国的电话号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isTel(\"028-12345678\"); // true\n\nboolean result = Validate.isTel(\"028-02345678\"); // false\n判断是否为手机号码isMobile 可判断一个字符串是否为手机号码。仅支持中国的手机号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isMobile(\"028-12345678\"); // false\n\nboolean result = Validate.isMobile(\"13800138000\"); // true\n判断是否为邮政编码isPostCode 可判断一个字符串是否为邮政编码。仅支持中国的邮政编码。import com.buession.core.validator.Validate;\nboolean result = Validate.isPostCode(\"043104\"); // false\n\nboolean result = Validate.isPostCode(\"643104\"); // true\n判断是否为 QQ 号码isQQ 可判断一个字符串是否为 QQ 号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isQQ(\"043104\"); // false\n\nboolean result = Validate.isQQ(\"25132.141\"); // true\n判断是否为身份证号码isIDCard 可判断一个字符串是否合法的身份证号码。仅支持 18 位的身份证号码。身份证号码需符合其身份证号码编排规律。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n可以和身份证号码进行比对，其实该方法的实际作用不是很大，只是在业务系统里面有需要填写身份证号码和出生日期时，简化验证出生日期和身份证号码中的出生日期是否一致。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\", true, \"2.10-01-01\");\n其它，更多方法可以参考手册。"
        },
        {
          "title": "注解",
          "url": "/manual/2.1/core/validator.html#验证器-注解",
          "content": "注解javax 的 validation 是 Java 定义的一套基于注解的数据校验规范，buession framework 基于 javax.validation 实现了 Validate 中所有验证方法的校验注解。\n\n注解\n验证的数据类型\n说明\n\n\n\n\n@Alnum\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Alpha\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Numeric\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Between\nshort、int、double 等任何 Number 的子类型\n验证注解的元素值是否为在两个数之间\n\n\n@Empty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否为空\n\n\n@NotEmpty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否不为空\n\n\n@HasText\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@IDCard\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Ip\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Isbn\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@MimeType\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Mobile\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Null\n任意类型\n验证注解的元素值是否为 null\n\n\n@NotNull\n任意类型\n验证注解的元素值是否为 null\n\n\n@Port\nInteger\n验证注解的元素值是否为 null\n\n\n@PostCode\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@QQ\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Tel\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Xdigit\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/validator.html#验证器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/utils.html",
      "children": [
        {
          "title": "工具类",
          "url": "/manual/2.1/core/utils.html#工具类",
          "content": "工具类常用通用性工具类。"
        },
        {
          "title": "Byte 数组比较",
          "url": "/manual/2.1/core/utils.html#工具类-byte-数组比较",
          "content": "Byte 数组比较ByteArrayComparable 类为 java Comparable 的实现，实现了 byte 数组的比较。"
        },
        {
          "title": "注解工具",
          "url": "/manual/2.1/core/utils.html#工具类-注解工具",
          "content": "注解工具AnnotationUtils 继承 org.springframework.core.annotation.AnnotationUtils ，在此基础上新增了方法 hasClassAnnotationPresent(final Class clazz, final Class[] annotations)、hasMethodAnnotationPresent(Method method, final Class[] annotations) 判断类或者方法上是否有待检测的注解中的其中一个注解。"
        },
        {
          "title": "断言",
          "url": "/manual/2.1/core/utils.html#工具类-断言",
          "content": "断言Assert 和 springframework 中的注解类似，不过逻辑有些相反。"
        },
        {
          "title": "Byte 工具",
          "url": "/manual/2.1/core/utils.html#工具类-byte-工具",
          "content": "Byte 工具ByteUtils 可以将 byte 数组转换为 char 或者 char 数组。import com.buession.core.utils.ByteUtils;\nbyte[] bytes;\nchar c = ByteUtils.toChar(bytes);\n\nchar[] chars = ByteUtils.toChar(bytes);\nbyte 数组连接。import com.buession.core.utils.ByteUtils;\nbyte[] dest;\nbyte[] source\nbyte[] result = ByteUtils.concat(dest, source);\n"
        },
        {
          "title": "Character 工具",
          "url": "/manual/2.1/core/utils.html#工具类-character-工具",
          "content": "Character 工具CharacterUtils 继承 org.apache.commons.lang3.CharUtils，可以将 char、char 数组转换为 byte 数组。import com.buession.core.utils.CharacterUtils;\nchar c;\nbyte[] bytes = ByteUtils.toBytes(c);\n\nchar[] chars;\nbyte[] bytes = ByteUtils.toBytes(chars);\n"
        },
        {
          "title": "数字工具",
          "url": "/manual/2.1/core/utils.html#工具类-数字工具",
          "content": "数字工具NumberUtils 提供了对数字相关的操作。\n\n方法\n说明\n\n\n\n\nint2bytes\n将 int 转换为 byte[]\n\n\nbytes2int\n将 byte[] 转换为 int\n\n\nlong2bytes\n将 long 转换为 byte[]\n\n\nbytes2long\n将 byte[] 转换为 long\n\n\nfloat2bytes\n将 float 转换为 byte[]\n\n\nbytes2float\n将 byte[] 转换为 float\n\n\ndouble2bytes\n将 double 转换为 byte[]\n\n\nbytes2double\n将 byte[] 转换为 double\n\n\n"
        },
        {
          "title": "字符串工具",
          "url": "/manual/2.1/core/utils.html#工具类-字符串工具",
          "content": "字符串工具StringUtils 继承了 org.apache.commons.lang3.StringUtils，封装了多字符串更多的操作。截取字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.substr(\"abcde\", 1); // bcde\nString result = StringUtils.substr(\"abcde\", 1, 2); // bc\n生成随机字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.random(length);\n比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 3); // true\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 4); // false\n忽略大小写比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"Abce\", 3); // true\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"aBce\", 4); // false\n"
        },
        {
          "title": "拼音工具",
          "url": "/manual/2.1/core/utils.html#工具类-拼音工具",
          "content": "拼音工具PinyinUtils 封装了获取中文拼音、拼音首字母的方法。import com.buession.core.utils.PinyinUtils;\nString result = PinyinUtils.getPinyin(\"中国\"); // zhongguo\nString result = PinyinUtils.getPinYinFirstChar(\"中国\"); // zg\n"
        },
        {
          "title": "随机数工具",
          "url": "/manual/2.1/core/utils.html#工具类-随机数工具",
          "content": "随机数工具RandomUtils 封装了随机数的生成。\n\n方法\n说明\n\n\n\n\nnextBoolean\n随机布尔值\n\n\nnextBytes\n随机字节数组\n\n\nnextInt\n生成指定范围内的 int 值，默认：0 到 Integer.MAX_VALUE\n\n\nnextLong\n生成指定范围内的 long 值，默认：0 到 Long.MAX_VALUE\n\n\nnextFloat\n生成指定范围内的 float 值，默认：0 到 Float.MAX_VALUE\n\n\nnextDouble\n生成指定范围内的 double 值，默认：0 到 Double.MAX_VALUE\n\n\n"
        },
        {
          "title": "Properties 工具",
          "url": "/manual/2.1/core/utils.html#工具类-properties-工具",
          "content": "Properties 工具PropertiesUtils 封装了对 Properties 的操作，主要是 Properties.getProperty 的值为字符串，而我们在实际场景中，很多时候其值可能为 int、double。传统方法是，我们在代码中通过 Properties.getProperty 获取其值后，对其进行转换；而 PropertiesUtils 简化了操作。import com.buession.core.utils.SystemPropertyUtils;\nInteger result = PropertiesUtils.getInteger(properties, key);\nBoolean result = PropertiesUtils.getBoolean(properties, key);\n"
        },
        {
          "title": "System Property 工具",
          "url": "/manual/2.1/core/utils.html#工具类-system-property-工具",
          "content": "System Property 工具SystemPropertyUtils 封装了系统属性或系统环境变量的操作。设置属性方法 setProperty 对 System.setProperty 的封装，唯一区别是：SystemPropertyUtils.setProperty 的值可以为非字符串，该方法类会将非字符串值转换为字符串再调用 System.setProperty。import com.buession.core.utils.SystemPropertyUtils;\nSystemPropertyUtils.setProperty(\"http.port\", 8080);\nSystemPropertyUtils.setProperty(\"http.ssl.enable\", false);\n获取属性方法 getProperty 会先通过 System.getProperty 进行获取，若未获取到值，再调用 System.getenv 进行获取。String value = System.getProperty(name);\nif(Validate.hasText(value) == false){\n  value = System.getenv(name);\n}\n"
        },
        {
          "title": "版本工具",
          "url": "/manual/2.1/core/utils.html#工具类-版本工具",
          "content": "版本工具VersionUtils 提供了对两个版本值的比较方法和获取类、jar 对应的版本。import com.buession.core.utils.VersionUtils;\nVersionUtils.compare(\"1.0.0\", \"1.0.1-beta\"); // -1\nVersionUtils.compare(\"1.0.0\", \"1.0.0r\"); // -1\n规则：dev < alpha = a < beta = b < rc < release < pl = p < 纯数字版本获取类的版本值import com.buession.core.utils.VersionUtils;\nByteUtils.determineClassVersion(com.buession.core.utils.VersionUtils.class); // 2.1.0\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/utils.html#工具类-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/other.html",
      "children": [
        {
          "title": "其它",
          "url": "/manual/2.1/core/other.html#其它",
          "content": "其它通用的接口定义，框架自身类，以及其它杂项。"
        },
        {
          "title": "框架自身工具",
          "url": "/manual/2.1/core/other.html#其它-框架自身工具",
          "content": "框架自身工具获取 Buession Framework 版本：import com.buession.core.Framework;import com.buession.core.BuesssionFrameworkVersion;\n\nBuesssionFrameworkVersion.getVersion(); // 2.1.0\nFramework.VERSION; // 2.1.0\n获取 Buession Framework 框架名称：import com.buession.core.Framework;\nFramework.NAME; // \"Buession\"\n"
        },
        {
          "title": "命令执行器",
          "url": "/manual/2.1/core/other.html#其它-命令执行器",
          "content": "命令执行器命令执行器接口：/** * 命令执行器\n *\n * @param \n * \t\t命令上下文\n * @param \n * \t\t命令执行返回值\n */\n@FunctionalInterface\npublic interface Executor {\n\n\t/**\n\t * 命令执行\n\t *\n\t * @param context\n\t * \t\t命令执行器上下文\n\t *\n\t * @return 命令执行返回值，R 类型的实例\n\t */\n\tR execute(C context);\n\n}\n您可以通过，该接口执行一个命令上下文的方法，并返回一个值。该方法有些类似代理模式的代理类。"
        },
        {
          "title": "销毁接口",
          "url": "/manual/2.1/core/other.html#其它-销毁接口",
          "content": "销毁接口功能类似 java.io.Closeable。public interface Destroyable {\n\t/**\n\t * 销毁相关资源\n\t *\n\t * @throws IOException\n\t * \t\tIO 错误时抛出\n\t */\n\tvoid destroy() throws IOException;\n\n}\n"
        },
        {
          "title": "Rawable",
          "url": "/manual/2.1/core/other.html#其它-rawable",
          "content": "Rawable原始的，约定实现该接口的类，必须返回原始字节数组。public interface Rawable {\n\t/**\n\t * 返回原始的字节数组\n\t *\n\t * @return 原始的字节数组\n\t */\n\tbyte[] getRaw();\n\n}\n"
        },
        {
          "title": "名称节点",
          "url": "/manual/2.1/core/other.html#其它-名称节点",
          "content": "名称节点名称节点，约定实现该接口的类应该返回一个名称public interface NamedNode {\n\t/**\n\t * 返回节点名称\n\t *\n\t * @return 节点名称\n\t */\n\t@Nullable\n\tString getName();\n\n}\n"
        },
        {
          "title": "分页",
          "url": "/manual/2.1/core/other.html#其它-分页",
          "content": "分页com.buession.core.Pagination 为一个分页 POJO 类，包括了当前页码、每页大小、上一页、下一页、总页数、总记录数、数据列表。能够根据设定的其中一个值，计算另外的值。"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.1/core/exception.html",
      "children": [
        {
          "title": "异常",
          "url": "/manual/2.1/core/exception.html#异常",
          "content": "异常通用异常的定义。\n\n异常\n说明\n\n\n\n\nAccessException\n拒绝访问异常\n\n\nClassInstantiationException\n类实例化异常\n\n\nConversionException\n数据类型转换异常\n\n\nDataAlreadyExistException\n数据已存在异常\n\n\nDataNotFoundException\n数据不存在或未找到异常\n\n\nInsteadException\n类方法废弃后，需要使用其它类库方法来替代\n\n\nNestedRuntimeException\n嵌套运行时异常\n\n\nOperationException\n运算异常\n\n\nPresentException\n--\n\n\nSerializationException\n序列化异常\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/exception.html#异常-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-cron 参考手册",
      "content": "对 quartz 的二次封装",
      "url": "/manual/2.1/cron/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/cron/index.html#安装",
          "content": "安装    com.buession\n    buession-cron\n    x.x.x\n\n由于在过去的工作中，定时任务基本使用 quartz 来实现；但是在初始化定时任务项目时，大量基本相同的代码，因此对此部分做了二次封装，简化了 quartz 项目的初始化。由于在现在有众多优秀的分布式定时任务，如：elastic-job、xxl-job 等等，因此直接使用 quartz 应该会越来越少（个人主观猜测），即使使用 quartz 初始化也简单，故该模块将不做说明。且在今后的版本中，该模块可能会被废弃。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/cron/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装。从代码层面实现了数据库的读写分离，insert、update、delete 操作主库，select 操作从库。",
      "url": "/manual/2.1/dao/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/dao/index.html#安装",
          "content": "安装    com.buession\n    buession-dao\n    x.x.x\n\n我们咋众多项目中，基本有常见的重复的对数据库的 CURD 操作，比如：根据主键查询数据、根据主键删除数据、获取一条记录。MyBatis 是一款优秀的持久层框架，应用广泛。MongoDB 是一款优秀的文档数据库。我自己根据从业多年的经验，从实际场合出发，将在业务层对数据库的常用操作进行了封装。对关系型数据库基于 MyBatis 二次封装，对 MongoDB 基于 spring-data-mongodb；在未来也许会考虑，增加 jpa 和 JdbcTemplate 对关系型数据库的二次封装。同时，我们在代码层面实现了数据库的读写分离。我们没有改变 MyBatis 和 spring-data-mongodb 的任何底层逻辑，本质就是 MyBaits 和 spring-data-mongodb；我们唯一做了的就是，定义和是了大家在应用程序中常用的方法，让您不在重复去编写该部分代码；以及在代码层面实现了数据的读写分离。"
        },
        {
          "title": "Dao 接口",
          "url": "/manual/2.1/dao/index.html#dao-接口",
          "content": "Dao 接口接口定义，可见：https://javadoc.io/static/com.buession/buession-dao/2.1.0/com/buession/dao/Dao.htmlpublic interface Dao {}\nP：主键类型\nE：实体类\n分页对象 com.buession.dao.Pagination 继承自 com.buession.core.Pagination，增加了偏移量属性 offset。条件为 Map 类型，允许为 null。排序为 Map 类型，允许为 null。MyBatisBuession Framework 扩展 MyBatis 的文档。MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/dao/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.1/dao/mybatis.html",
      "children": [
        {
          "title": "MyBatis",
          "url": "/manual/2.1/dao/mybatis.html#mybatis",
          "content": "MyBatisBuession Framework 扩展 MyBatis 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.1/dao/mybatis.html#mybatis-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMyBatisDao；且存在 bean 名为 masterSqlSessionTemplate、slaveSqlSessionTemplates 的 bean 实例。masterSqlSessionTemplate 操作主库，实现插入、更新、删除操作；slaveSqlSessionTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveSqlSessionTemplate() 在所有的 slave templates 中随机返回一个 slave SqlSessionTemplate bean 实例。当然，您也可以通过 getSlaveSqlSessionTemplate(final int index) 指定索引的 slave SqlSessionTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave SqlSessionTemplate bean 实例列表，将会返回 master SqlSessionTemplate bean 实例，buession framework 屏蔽了这些技术细节。"
        },
        {
          "title": "Mybatis 约定",
          "url": "/manual/2.1/dao/mybatis.html#mybatis-mybatis-约定",
          "content": "Mybatis 约定如果集成 AbstractMyBatisDao 类，必须重写方法 getStatement()，通过此方法返回每个 Mapper namespace\nnamespace com.buession.dao.test.dao;\npublic class UserDaoImpl extends AbstractMyBatisDao {\n\n\t@Override\n\tprotected String getStatement(){\n\t\treturn \"com.buession.dao.test.dao.UserMapper\";\n\t}\n\n}\n\n\nMapper 的 SQL ID 和方法名保持一致\n\n\nSQL ID\n说明\n返回值\n\n\n\n\ninsert\n插入数据\n影响的行数\n\n\nbatchInsert\n批量插入数据，默认循环插入；您可以重写该方法实现 SQL 批量插入\n每次插入影响的行数列表\n\n\nreplace\n替换数据，即：REPLACE 语句\n影响的行数\n\n\nbatchReplace\n批量替换数据，即：REPLACE 语句\n每次替换数据影响的行数列表\n\n\nupdate\n更新数据\n更新条数\n\n\nupdateByPrimary\n根据主键更新数据，注：主键参数值是会覆盖实体类主键参数对应的类属性的值\n更新条数\n\n\ngetByPrimary\n根据主键查询数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselectOne\n（根据条件）获取一条数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselect\n查询数据\n数据结果列表\n\n\ngetAll\n查询所有数据\n数据结果列表\n\n\ncount\n获取记录数\n记录数\n\n\ndeleteByPrimary\n根据主键删除数据\n影响条数\n\n\ndelete\n删除数据\n影响条数\n\n\nclear\n清除数据\n影响条数\n\n\ntruncate\n截断数据\n影响条数\n\n\n注：要实现分页，必须实现 count，且和 select 的查询条件必须一致。因为，在分页方法中，首先会执行 count ，查询指定条件的记录数；如果记录数大于 0 时，才会执行 select 查询数据。在后续的开发中，我们将会使用拦截器实现。\n以上 SQL ID，只是一种约定，具体会呈现一种什么样的效果，还是完全屈居于您的 SQL 语句。\n"
        },
        {
          "title": "Mybatis 类型处理器",
          "url": "/manual/2.1/dao/mybatis.html#mybatis-mybatis-类型处理器",
          "content": "Mybatis 类型处理器MyBatis 自身提供大量优秀的类型处理器 TypeHandler，但任然不足。我们在此基础上扩展了一些 TypeHandler。名称空间为 org.apache.ibatis.type，不是 com.buession.dao。\n\nTypeHandler\n说明\n\n\n\n\nDefaultEnumTypeHandler\n默认 Enum 类型处理器，将值直接转换为枚举字段\n\n\nIgnoreCaseEnumTypeHandler\n忽略大小写 Enum 类型处理器，将值忽略大小写转换为枚举字段\n\n\nDefaultJsonTypeHandler\nJSON 处理器，将 JSON 格式的字符串值和类型  进行转换\n\n\nDefaultSetEnumTypeHandler\n默认 Enum 型 Set 类型处理器，将值直接转换为枚举字段作为 Set 的元素\n\n\nIgnoreCaseSetEnumTypeHandler\n忽略大小写 Enum 型 Set 类型处理器，将值忽略大小写转换为枚举字段作为 Set 的元素\n\n\nDefaultSetTypeHandler\n默认 Set 类型处理器，将值以 \",\" 拆分转换为 Set\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/dao/mybatis.html#mybatis-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.1/dao/mongodb.html",
      "children": [
        {
          "title": "MongoDB",
          "url": "/manual/2.1/dao/mongodb.html#mongodb",
          "content": "MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.1/dao/mongodb.html#mongodb-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMongoDBDao；且存在 bean 名为 masterMongoTemplate、slaveMongoTemplates 的 bean 实例。masterMongoTemplate 操作主库，实现插入、更新、删除操作；slaveMongoTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveMongoTemplate() 在所有的 slave templates 中随机返回一个 MongoTemplate bean 实例。当然，您也可以通过 getSlaveMongoTemplate(final int index) 指定索引的 slave MongoTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave MongoTemplate bean 实例列表，将会返回 master MongoTemplate bean 实例，buession framework 屏蔽了这些技术细节。AbstractMongoDBDao 的 replace 执行的也是 insert。在对 MongoDB 的操作条件中 value 可以为 com.buession.dao.MongoOperation，可以通过该类的方法控制条件等于值、大于值、小于值、LIKE值 等等运算。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/dao/mongodb.html#mongodb-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-geoip 参考手册",
      "content": "对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息。",
      "url": "/manual/2.1/geoip/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/geoip/index.html#安装",
          "content": "安装    com.buession\n    buession-geoip\n    x.x.x\n\n通常我们在应用中对用户注册、登录、以及其它操作记录 IP，我们更希望知道用户在什么城市进行的操作，如：微信公众号的内容发表于、微博的发布于等等，对于用户行为的安全审计等等有着极高的作用。geoip 在基于 maxmind geoip2 的基础上进行了二次封装，可以根据 IP（字符串形式的 IP，如：114.114.114.114、2.11:0DB8:0000:0023:0008:0800:2.1C:417A ，IPV4 的数字表示：3739974408，java InetAddress）获取其 IP 地址的国家信息、城市信息、位置信息。"
        },
        {
          "title": "获取国家信息",
          "url": "/manual/2.1/geoip/index.html#获取国家信息",
          "content": "获取国家信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nCountry country = resolver.country(\"114.114.114.114\");\n// Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}\n\nCountry country = resolver.country(3739974408L); // 3739974408L => 222.235.123.8\n// Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}\n"
        },
        {
          "title": "获取城市信息",
          "url": "/manual/2.1/geoip/index.html#获取城市信息",
          "content": "获取城市信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nDistrict district = resolver.district(\"114.114.114.114\");\n// District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}\n\nDistrict district = resolver.district(3739974408L); // 3739974408L => 222.235.123.8\n// District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}\n"
        },
        {
          "title": "获取位置信息",
          "url": "/manual/2.1/geoip/index.html#获取位置信息",
          "content": "获取位置信息位置信息中包括了该 IP 比较全面的信息，包括：城市信息、国家信息、洲信息、经纬度、机构信息、时区等。import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nLocation location = resolver.location(\"114.114.114.114\");\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}, district=District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=18062.1, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}, traits=Traits{ipAddress='114.114.114.114', domain='null', isp='null', network=114.114.0.0/16, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=32.1617, longitude=118.7778, accuracyRadius=50}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Shanghai\",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null]}\n\nLocation location = resolver.location(3739974408L); // 3739974408L => 222.235.123.8\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}, district=District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}, traits=Traits{ipAddress='222.235.123.8', domain='null', isp='null', network=222.235.120.0/21, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=37.5111, longitude=126.9743, accuracyRadius=2.1}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Seoul\",offset=32.10000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null]}\n"
        },
        {
          "title": "缓存",
          "url": "/manual/2.1/geoip/index.html#缓存",
          "content": "缓存为了提高数据的处理能力，可以将获取过的数据缓存起来，下次获取同一 IP 信息时，可以直接从缓存中返回。您可以通过 DatabaseResolver 构造函数中的参数 cache 设置为 com.maxmind.db.NodeCache 的实现类即可，或直接使用类 CacheDatabaseResolver解析。我们默认使用 maxmind 内置的 CHMCache 来实现缓存，它是基于 ConcurrentHashMap 的内存缓存。"
        },
        {
          "title": "Resolver 的 Spring Factory Bean",
          "url": "/manual/2.1/geoip/index.html#resolver-的-spring-factory-bean",
          "content": "Resolver 的 Spring Factory Bean我们内置了 geoip 的 Resolver spring factory bean 类 GeoIPResolverFactoryBean，您可以通过它在您的 spring 项目中，初始化 Resolver 的实现类为 spring bean 对象。dbPath 和 stream 二选一即可，一个是指定 IP 的文件路径，一个是指定已加载的 IP 库的文件流。都不设置的默认以流的形式加载 buession-geoip 中的 IP 库文件。\nenableCache 可以控制是否缓存。\n"
        },
        {
          "title": "关于 IP 库",
          "url": "/manual/2.1/geoip/index.html#关于-ip-库",
          "content": "关于 IP 库buession-geoip 中包含了 maxmind 免费的 IP 所属城市和国家的库。由于在 jar 包中该数据库无法做到及时更新，在实际应用中，我们建议您从 maxmind 官网下载 IP 方法您的应用中，通过 DatabaseResolver 的构造函数指定 IP 库路径，这么做的好处是：在您的应用程序中，可以去保证 IP 库是更新的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/geoip/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节，屏蔽了对 post form、post json 等等的技术细节。",
      "url": "/manual/2.1/httpclient/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/httpclient/index.html#安装",
          "content": "安装    com.buession\n    buession-httpclient\n    x.x.x\n\n我们在应用中使用 Http Client 功能时，经常因为从 apache httpcomponents 切换为 okhttp3，或者从 okhttp3 切换为 apache httpcomponents，需要改动大量的代码而烦恼。而当您使用了 buession-httpclient 时，该类库为您解决了这些烦恼，通过顶层设计，屏蔽了 apache httpcomponents 和 okhttp3 的细节，当您需要从一个 http 库更换为另外一个 http 库时，您只需要在 pom.xml 中引用不同的包，修改一下 httpclient 的初始化类和连接管理器即可。传统的方式：    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\nimport org.apache.http.HttpResponse;import org.apache.http.conn.HttpClientConnectionManager;\nimport org.apache.http.client.HttpClient;\nimport org.apache.http.impl.client.HttpClientBuilder;\nimport org.apache.http.client.methods.HttpPost;\n\nHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(new HttpClientConnectionManager()).build();\n\nHttpResponse response = httpClient.execute(new HttpPost(\"https://www.buession.com/\"));\n或者    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport okhttp3.HttpClientConnectionManager;import okhttp3.OkHttpClient;\nimport okhttp3.ConnectionPool;\nimport okhttp3.Request;\nimport okhttp3.Request.Builder;\nimport okhttp3.Response;\n\nOkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(new ConnectionPool());\nHttpClient httpClient = builder.build();\n\nBuilder requestBuilder = new Builder().post();\nrequestBuilder.url(\"https://www.buession.com/\");\nRequest okHttpRequest = requestBuilder.build();\n\nResponse httpResponse = httpClient.newCall(okHttpRequest).execute();\n现在，您只需要通过 buession-httpclient，即可屏蔽其中的细节。    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\n或者    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.OkHttpHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.conn.OkHttpClientConnectionManager;\nimport com.buession.httpclient.core.Response;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager()); // 或者 new OkHttpHttpClient(new OkHttpClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\n"
        },
        {
          "title": "展望",
          "url": "/manual/2.1/httpclient/index.html#展望",
          "content": "展望目前，buession-httpclient 仅支持同步，不支持异步。我们会在下一个小版本（即：2.2） 中，集成 apache httpcomponents 切换为 okhttp3 的异步 http 请求。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/httpclient/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.1/httpclient/configuration.html",
      "children": [
        {
          "title": "连接配置",
          "url": "/manual/2.1/httpclient/configuration.html#连接配置",
          "content": "连接配置您可以通过连接配置类 Configuration 配置 apache httpcomponents 和 okhttp3 的链接配置属性，buession-httpclient 内部会自动将 Configuration 的配置信息，转换为 apache httpcomponents 或 okhttp3 的配置信息。"
        },
        {
          "title": "配置属性说明",
          "url": "/manual/2.1/httpclient/configuration.html#连接配置-配置属性说明",
          "content": "配置属性说明\n\n属性名称\n数据类型\napache httpcomponents 对应配置\nokhttp3 对应配置\n默认值\n说明\n\n\n\n\nmaxConnections\nint\nmaxTotal\nmaxIdleConnections\n5000\n最大连接数\n\n\nmaxPerRoute\nint\ndefaultMaxPerRoute\n--\n500\n每个路由的最大连接数\n\n\nidleConnectionTime\nint\ncloseIdleConnections\nkeepAliveDuration\n60000\n空闲连接存活时长（单位：毫秒）\n\n\nconnectTimeout\nint\nconnectTimeout\nconnectTimeout\n3000\n连接超时时间（单位：毫秒）\n\n\nconnectionRequestTimeout\nint\nconnectionRequestTimeout\n--\n5000\n从连接池获取连接的超时时间（单位：毫秒）\n\n\nreadTimeout\nint\nsocketTimeout\nreadTimeout\n5000\n读取超时时间（单位：毫秒）\n\n\nallowRedirects\nBoolean\nredirectsEnabled\nfollowRedirects\n--\n是否允许重定向\n\n\nrelativeRedirectsAllowed\nBoolean\nrelativeRedirectsAllowed\n--\n--\n是否应拒绝相对重定向\n\n\ncircularRedirectsAllowed\nBoolean\ncircularRedirectsAllowed\n--\n--\n是否允许循环重定向\n\n\nmaxRedirects\nInteger\nmaxRedirects\n--\n--\n最大允许重定向次数\n\n\nauthenticationEnabled\nboolean\nauthenticationEnabled\n--\n--\n是否开启 Http Basic 认证\n\n\ncontentCompressionEnabled\nboolean\ncontentCompressionEnabled\n--\n--\n是否启用内容压缩\n\n\nnormalizeUri\nboolean\nnormalizeUri\n--\n--\n是否标准化 URI\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/httpclient/configuration.html#连接配置-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.1/httpclient/connectionmanager.html",
      "children": [
        {
          "title": "连接管理器",
          "url": "/manual/2.1/httpclient/connectionmanager.html#连接管理器",
          "content": "连接管理器连接管理器是用来管理 HTTP 连接的，包括设置连接配置、控制连接池。关于更多的连接池，可以参考 apache httpcomponents 和 okhttp3 的文档。您可以通过无参数的构造函数来创建连接管理器，也可以通过构造函数参数仅为 com.buession.httpclient.core.Configuration 来创建连接管理器，也可以构造函数通过 apache httpcomponents 或 okhttp3 原生的连接管理器类创建（此时，Configuration 的配置不任何意义，他不会修改您通过原生连接管理器实例中的参数配置）。"
        },
        {
          "title": "关于 okhttp 连接管理器",
          "url": "/manual/2.1/httpclient/connectionmanager.html#连接管理器-关于-okhttp-连接管理器",
          "content": "关于 okhttp 连接管理器okhttp3 本身是没有类似 apache httpcomponents 的链接管理器 org.apache.http.conn.HttpClientConnectionManager 的，我们为了在 buession-httpclient 的链接管理器实现 com.buession.httpclient.conn.OkHttpClientConnectionManager 保持一致，人为的加了一层 okhttp3 的链接管理器 okhttp3.HttpClientConnectionManager（注意：命名空间为 okhttp3），主要用于初始化连接池类 okhttp3.ConnectionPool。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/httpclient/connectionmanager.html#连接管理器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.1/httpclient/response.html",
      "children": [
        {
          "title": "响应",
          "url": "/manual/2.1/httpclient/response.html#响应",
          "content": "响应当通过 HttpClient 发起任意请求后，将得到一个 Response。此结果，包括了：协议及其版本、状态码及其信息、响应头列表、响应体、以及响应体长度。buession-httpclient 会将 apache httpcomponents 或 okhttp3 的响应对象，转换为 Response。需要注意的是，原生 apache httpcomponents 或 okhttp3 响应体流，一旦被使用过一次之后，将不能再使用；同时您只能以流或者字符串二选一的形式获取响应体。但是，在 buession-httpclient 中您将可以二者兼顾，当然这也同时会带来一些性能消耗和内存占用。import com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.core.Response;\nimport java.io.InputStream;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\nInputStream stream = response.getInputStream(); // 以流的形式获取响应体\nString body = response.getBody(); // 以字符串的形式获取响应体\n\nstream.close();\ngetInputStream、getBody 二者可以重复调用，当时您需要始终手动关闭一下流，因为这将是拷贝的原生 apache httpcomponents 或 okhttp3 返回的流。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/httpclient/response.html#响应-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.1/httpclient/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.1/httpclient/method.html#方法",
          "content": "方法buession-httpclient 提供了和 HTTP 请求方式同名的方法 API，您可以很方便的通过提供的方法发起 HTTP 请求。示例：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\");\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\");\n您可以自定义请求头：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.List;\nimport java.util.ArrayList;\n\nList headers = new ArrayList();\n\nheaders.add(new Header(\"X-SDK-NAME\", \"Buession\"));\nheaders.add(new Header(\"X-Timestamp\", System.currentTimeMillis()));\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", headers);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", headers);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", headers);\n您可以设置请求参数：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.Map;\nimport java.util.HashMap;\n\nMap parameters = new HashMap();\n\nparameters.put(\"action\", \"edit\");\nparameters.put(\"id\", 1);\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", parameters);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", parameters);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", parameters);\n您可以设置请求体：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport jcom.buession.httpclient.core.RequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\n\nEncodedFormRequestBody requestBody = new EncodedFormRequestBody();\n\nrequestBody.addRequestBodyElement(\"username\", \"buession\");\nrequestBody.addRequestBodyElement(\"password\", \"buession\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", requestBody);\n\nJsonRawRequestBody requestBody = new JsonRawRequestBody(new User());\n// PUT 请求\nResponse response = httpClient.put(\"https://www.buession.com/\", requestBody);\n不同的 RequestBody，决定了我们以什么样的 Content-Type 提交数据，buession-httpclient 中提供了大量的内置 RequestBody。"
        },
        {
          "title": "RequestBody",
          "url": "/manual/2.1/httpclient/method.html#方法-requestbody",
          "content": "RequestBody\n\nRequestBody\nContent-Type\n说明\n\n\n\n\nInputStreamRequestBody\napplication/octet-stream\n二进制请求体\n\n\nChunkedInputStreamRequestBody\napplication/octet-stream\nChunked 二进制请求体\n\n\nRepeatableInputStreamRequestBody\napplication/octet-stream\nRepeatable 二进制请求体\n\n\nEncodedFormRequestBody\napplication/x-www-form-urlencoded\n普通表单请求体\n\n\nMultipartFormRequestBody\nmultipart/form-data\n文件上传表单请求体\n\n\nHtmlRawRequestBody\ntext/html\nHTML 请求体\n\n\nJavaScriptRawRequestBody\napplication/javascript\nJavaScript 请求体\n\n\nJsonRawRequestBody\napplication/json\nJSON 请求体\n\n\nTextRawRequestBody\ntext/plain\nTEXT 请求体\n\n\nXmlRawRequestBody\ntext/xml\nXML 请求体\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/httpclient/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-io 参考手册",
      "content": "封装了对文件的操作",
      "url": "/manual/2.1/io/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/io/index.html#安装",
          "content": "安装    com.buession\n    buession-io\n    x.x.x\n\n该模块二次封装了 java java.io.File 和 java.nio.file.Files 类，在此基础上扩展了大量的实用方法，如：文件读写、获取文件 MD5 和 SHA1 值，获取文件 MIME，设置文件所属用户和用户组，简化了我们在应用开发过程中对文件的操作。"
        },
        {
          "title": "读取文件",
          "url": "/manual/2.1/io/index.html#读取文件",
          "content": "读取文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nbyte[] result = file.read();\n"
        },
        {
          "title": "写文件",
          "url": "/manual/2.1/io/index.html#写文件",
          "content": "写文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nfile.write(\"Buession\");\nfile.write(\"Buession\".getBytes());\nfile.write(\"Buession\", true); // 追加写\n"
        },
        {
          "title": "获取文件 MD5、SHA-1值",
          "url": "/manual/2.1/io/index.html#获取文件-md5、sha-1值",
          "content": "获取文件 MD5、SHA-1值import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nString md5 = file.getMd5(); // 获取文件 MD5\nString sha1 = file.getSha1(); // 获取文件 SHA-1\n"
        },
        {
          "title": "获取文件 MD5、SHA-1 值",
          "url": "/manual/2.1/io/index.html#获取文件-md5、sha-1-值",
          "content": "获取文件 MD5、SHA-1 值import com.buession.io.file.File;import com.buession.io.MimeType;\n\nFile file = new File(\"/tmp/debug.txt\");\n\nMimeType result = file.getMimeType();\n"
        },
        {
          "title": "设置文件权限",
          "url": "/manual/2.1/io/index.html#设置文件权限",
          "content": "设置文件权限import com.buession.io.file.Files;\nFiles.chmod(\"/tmp/debug.txt\", 0777);\n"
        },
        {
          "title": "设置文件用户组",
          "url": "/manual/2.1/io/index.html#设置文件用户组",
          "content": "设置文件用户组import com.buession.io.file.Files;\nFiles.chgrp(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "设置文件用户",
          "url": "/manual/2.1/io/index.html#设置文件用户",
          "content": "设置文件用户import com.buession.io.file.Files;\nFiles.chown(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "注解",
          "url": "/manual/2.1/io/index.html#注解",
          "content": "注解注解 com.buession.io.json.annotation.MimeTypeString 可以将类型为 com.buession.io.MimeType 的字段序列化为字符串和将字符串反序列化为 com.buession.io.MimeType，该功能是基于 jackson 实现的。import com.buession.io.json.annotation.MimeTypeString;\nclass File {\n\n    @MimeTypeString\n    private MimeType mime;\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/io/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-jdbc 参考手册",
      "content": "JDBC 通用 POJO 类定义，对 Hikari、Dbcp2、Druid 等配置和数据源的封装。",
      "url": "/manual/2.1/jdbc/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/jdbc/index.html#安装",
          "content": "安装    com.buession\n    buession-jdbc\n    x.x.x\n\n通过提供的 API，您可以简化对 DBCP2、Druid、Hikari、Tomcat 数据源的初始化，该类库基本不单独使用。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/jdbc/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-json 参考手册",
      "content": "主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现。",
      "url": "/manual/2.1/json/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/json/index.html#安装",
          "content": "安装    com.buession\n    buession-json\n    x.x.x\n\n封装了大量基于 jackson 的注解。"
        },
        {
          "title": "注解",
          "url": "/manual/2.1/json/index.html#注解",
          "content": "注解\n\n注解\n说明\n\n\n\n\nCalendarUnixTimestamp\njava.util.Calendar 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Calendar 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Calendar\n\n\nDateUnixTimestamp\njava.util.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Date\n\n\nSqlDateUnixTimestamp\njava.sql.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Date\n\n\nTimestampUnixTimestamp\njava.sql.Timestamp 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Timestamp 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Timestamp\n\n\nJsonEnum2Map\n枚举和 java.util.Map 序列化和反序列化；通过该注解，可以将枚举序列化为 java.util.Map；将 java.util.Map 反序列化为枚举\n\n\nSensitive\n通过该注解可以实现数据的脱敏\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/json/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-lang 参考手册",
      "content": "常用 POJO 类和枚举的定义，详细查看 API 参考手册。",
      "url": "/manual/2.1/lang/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/lang/index.html#安装",
          "content": "安装    com.buession\n    buession-lang\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/lang/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-net 参考手册",
      "content": "网络相关工具类。",
      "url": "/manual/2.1/net/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/net/index.html#安装",
          "content": "安装    com.buession\n    buession-net\n    x.x.x\n\n"
        },
        {
          "title": "IP 地址工具类",
          "url": "/manual/2.1/net/index.html#ip-地址工具类",
          "content": "IP 地址工具类IP 地址工具类 com.buession.net.utils.InetAddressUtis 实现了，IPV4 地址和数字型 IP 相互转换。import com.buession.net.utils.InetAddressUtis;\nlong result = InetAddressUtis.ip2long(\"127.0.0.1\"); // 2130706433\nString ip = InetAddressUtis.long2ip(2130706433L); // 127.0.0.1\nURI 类或 URIBuilder 类，实现了 url 字符串的构建，详细查看 API 手册。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/net/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 原生命令保持一致。同时，对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Object.class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象。",
      "url": "/manual/2.1/redis/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/redis/index.html#安装",
          "content": "安装    com.buession\n    buession-redis\n    x.x.x\n\n"
        },
        {
          "title": "介绍",
          "url": "/manual/2.1/redis/index.html#介绍",
          "content": "介绍buession-redis 是一款基于 jedis 的 redis 操作库，最大的优势就是封装了与 redis 同名、最大程度与 redis 原生参数顺序一致的 API。同时，我们在现代应用中，经常需要读写一个 pojo 对象，buession-redis 封装了 xxxObject 读写取 redis 中的二进制或 JSON 数据，并反序列化为 POJO 类。大大简化了，我们在代码中对象存取到 redis 中，让我们更专注业务功能的开。能够通过 com.buession.redis.core.Options 设置全局选项，如：统一的 Key 前缀，对象基于什么方式序列化和反序列化。import com.buession.redis.RedisTemplate;import com.buession.redis.core.Options;\nimport com.buession.core.serializer.type.TypeReference;\nimport java.utils.Map;\nimport java.utils.HashMap;\n\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.setOptions(new Options());\nredisTemplate.afterPropertiesSet();\n\n// 将 User 对象写进 key 为 user hash 中\nredisTemplate.hSet(\"user\", \"1\", new User());\n\n// 获取 key 为 user ，field 为 1 的 hash 中的数据，并转换为 User\nUser user = redisTemplate.hGetObject(\"user\", \"1\", User.class);\n\n// 获取 key 为 user 的 hash 的所有数据，并将值转换为 User\nMap data = redisTemplate.hGetAllObject(\"user\", \"1\", new TypeReference>{});\n"
        },
        {
          "title": "展望",
          "url": "/manual/2.1/redis/index.html#展望",
          "content": "展望目前，buession-redis 仅支持 jedis，不支持 lettuce，我们预计会在下个版本或者下下个版本（即：2.1 或者 2.2）中计划加入。其实，之前尝试过，但由于两者 API 差异性和使用方式太大，没法很好的做到统一化，就暂时放弃了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/redis/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.1/redis/datasource.html",
      "children": [
        {
          "title": "数据源",
          "url": "/manual/2.1/redis/datasource.html#数据源",
          "content": "数据源buession-redis 基于数据源 DataSource 连接 redis，其机制类似 JDBC 的 DataSource。通过，数据源可以配置，redis 的用户名、密码、连接超时、读取超时、连接池、SSL等等。数据源 DataSource 包括三个子接口：StandaloneDataSource：单机模式数据源\nSentinelDataSource：哨兵模式数据源\nClusterDataSource：集群模式数据源\njedis 和后续的 lettuce 分别实现这三个接口，用于创建不通模式的数据源，数据源中实现了连接池的创建。在原始的 jedis 或者 spring-data-redis 中，密码为空字符串时，会以空字符串密码进行登录 redis；我们修改了这一逻辑，不管您在程序中密码是设置的 null 还是空字符串，我们都会跳过认证。这样的好处就是，假设您的开发环境 redis 没有设置密码，生产环境设置了密码，我们可以通过一个 bean 初始化即可，不用写成两个 bean。测试环境 properties：redis.host=127.0.0.1redis.port=6379\nredis.password=\n生产环境 properties：redis.host=192.168.100.131redis.port=6379\nredis.password=passwd\n"
        },
        {
          "title": "连接池",
          "url": "/manual/2.1/redis/datasource.html#数据源-连接池",
          "content": "连接池通过连接池管理 redis 连接，能够大大的提高效率和节约资源的使用。jedis 和 lettuce 均使用 apache commons-pool2 来创建和维护连接池。但是，在 jedis 中，以 JedisPoolConfig 和 ConnectionPoolConfig 来管理单例模式连接池、哨兵模式连接池和集群模式连接池；为了简化配置，我们定义了 com.buession.redis.core.PoolConfig 来统一维护各种模式的连接池配置，然后在各 DataSource 中转换为原生的连接池配置，极大的简化了学习和替换成本。连接池配置\n\n配置项\n数据类型\n-- 默认值\n说明\n\n\n\n\nlifo\nboolean\nGenericObjectPoolConfig.DEFAULT_LIFO\n池模式，为 true 时，后进先出；为 false 时，先进先出\n\n\nfairness\nboolean\nGenericObjectPoolConfig.DEFAULT_FAIRNESS\n当从池中获取资源或者将资源还回池中时，是否使用 java.util.concurrent.locks.ReentrantLock 的公平锁机制\n\n\nmaxWait\nDuration\nGenericObjectPoolConfig.DEFAULT_MAX_WAIT\n当连接池资源用尽后，调用者获取连接时的最大等待时间\n\n\nminEvictableIdleTime\nDuration\n60000\n连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除\n\n\nsoftMinEvictableIdleTime\nDuration\nGenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION\n连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数\n\n\nevictionPolicyClassName\nString\nGenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME\n驱逐策略的类名\n\n\nevictorShutdownTimeout\nDuration\nGenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT\n关闭驱逐线程的超时时间\n\n\nnumTestsPerEvictionRun\nint\n-1\n检测空闲对象线程每次运行时检测的空闲对象的数量\n\n\ntestOnCreate\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE\n在创建对象时检测对象是否有效，配置 true 会降低性能\n\n\ntestOnBorrow\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW\n在从对象池获取对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestOnReturn\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN\n在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestWhileIdle\nboolean\ntrue\n在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\nblockWhenExhausted\nboolean\nGenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED\n当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效；false 连接池没有资源立马抛异常）\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\njmxEnabled\nboolean\nGenericObjectPoolConfig.DEFAULT_JMX_ENABLE\n是否注册 JMX\n\n\njmxNamePrefix\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX\nJMX 前缀\n\n\njmxNameBase\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_BASE\n使用 base + jmxNamePrefix + i 来生成 ObjectName\n\n\nmaxTotal\nint\nGenericObjectPoolConfig.DEFAULT_MAX_TOTAL\n最大连接数\n\n\nminIdle\nint\nGenericObjectPoolConfig.DEFAULT_MIN_IDLE\n最小空闲连接数\n\n\nmaxIdle\nint\nGenericObjectPoolConfig.DEFAULT_MAX_IDLE\n最大空闲连接数\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/redis/datasource.html#数据源-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.1/redis/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.1/redis/method.html#方法",
          "content": "方法buession-redis BaseRedisTemplate 的方法以及参数计划与原生 redis 命名保持一致。复杂的参数会通过 Builder 进行参数构建，在多个值中进行选择的将定义成枚举，规避出错的几率。import com.buession.redis.BaseRedisTemplate;\nBaseRedisTemplate redisTemplate = new BaseRedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 删除哈希表 key 中的一个或多个指定域\nredisTemplate.hDel(\"user\", \"1\", \"2\", \"3\");\n\n// 检查给定 key 是否存在\nredisTemplate.exists(\"user\");\n\n// 获取列表 key 中，下标为 index 的元素\nredisTemplate.lIndex(\"user\", 1);\n\n// 如果键 key 已经存在并且它的值是一个字符串，将 value 追加到键 key 现有值的末尾\nredisTemplate.append(\"key\", \"value 1\");\nBaseRedisTemplate 实现了 redis 的原生操作，RedisTemplate 继承了 BaseRedisTemplate ，在此基础上实现了将 redis 中的二进制或者 JSON 格式的值，反序列化为一个类。import com.buession.redis.RedisTemplate;\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 获取列表 key 中，下标为 index 的元素，并反序列化为 User 类\nUser user = redisTemplate.lIndexObject(\"user\", 1, User.class);\n序列化和反序列化，基于 buession-core 序列化和反序列化 扩展而来，序列化或反序列化出错时会直接返回 null，而忽略异常，默认使用 com.buession.redis.serializer.JacksonJsonSerializer 序列化为 JSON。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/redis/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-session 参考手册",
      "content": "无文档",
      "url": "/manual/2.1/session/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/session/index.html#安装",
          "content": "安装    com.buession\n    buession-session\n    x.x.x\n\n该模块无实际意义，将在今后的版本中会删除掉。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/session/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-thesaurus 参考手册",
      "content": "对词库的解析，目前仅支持搜狗词条。",
      "url": "/manual/2.1/thesaurus/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/thesaurus/index.html#安装",
          "content": "安装    com.buession\n    buession-thesaurus\n    x.x.x\n\n您可以通过该库解析搜狗拼音的词条库，包括词条、拼音信息。import com.buession.thesaurus.SogouParser;import com.buession.thesaurus.Parser;\nimport com.buession.thesaurus.core.Word;\nimport java.util.Set;\n\nParser parser = new SogouParser();\n\nSet words parser.parse(\"搜谱拼音词条文件路径\");\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/thesaurus/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-velocity 参考手册",
      "content": "spring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity。",
      "url": "/manual/2.1/velocity/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/velocity/index.html#安装",
          "content": "安装    com.buession\n    buession-velocity\n    x.x.x\n\n该类库，基本照搬了 springframework 集成 velocity 的代码和逻辑。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/velocity/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "web 相关的功能封装，支持 servlet 和 reactive；封装了一些常用注解，简化了业务层方面的代码实现；封装了一些常用 filter。",
      "url": "/manual/2.1/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/web/index.html#安装",
          "content": "安装    com.buession\n    buession-web\n    x.x.x\n\nbuession-web 扩展了 spring-webmvc、spring-webflux；在此基础上，提供了大量的实用的 filter 和注解，以及工具类。该模块无论封装的 filter、注解、工具类，均适用于 spring mvc，也适用于 spring webflux。当时，filter、工具类均为 servlet 和 webflux 各自独立的类，不是说同一个类，即能用于 servlet，也能用于 webflux，当然注解是通用的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.1/web/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/2.1/web/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了一些我们在日常应用开发过程中能用到的实用性功能，简化了您在代码开发中的便捷度，让您能够更专注业务的开发。"
        },
        {
          "title": "注解",
          "url": "/manual/2.1/web/annotation.html#注解-注解",
          "content": "注解\n\n注解\nRequest / Response\n作用域\n说明\n\n\n\n\n@RequestClientIp\nrequest\n方法参数\n获取当前请求的客户端 IP 地址，支持返回字符串、long 类型的 IP 地址，以及 InetAddress\n\n\n@ContentType\nresponse\n类、方法\n设置响应 Content-Type\n\n\n@HttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值\n\n\n@DisableHttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值，禁止缓存\n\n\n@ResponseHeader\nresponse\n类、方法\n设置响应头\n\n\n@ResponseHeaders\nresponse\n类、方法\n批量设置响应头\n\n\n@DocumentMetaData\nresponse\n类、方法\n设置页面标题、页面编码、关键字、描述、版权等等元信息\n\n\n获取用户端真实 IP@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/ip1\")\n\t@ResponseBody\n\tpublic String ip1(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip2\")\n\t@ResponseBody\n\tpublic Long ip2(@RequestClientIp long ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip3\")\n\t@ResponseBody\n\tpublic InetAddress ip3(@RequestClientIp InetAddress ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n您也可以指定获取用户真实 IP 的请求头列表，若未指定则使用 RequestUtils.getClientIp(request) 方法获取，获取顺序参考：RequestUtils.CLIENT_IP_HEADERS@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/ip1\")\n\t@ResponseBody\n\tpublic String ip1(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip2\")\n\t@ResponseBody\n\tpublic Long ip2(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) long ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n\t@RequestMapping(path = \"/ip3\")\n\t@ResponseBody\n\tpublic InetAddress ip3(@RequestClientIp(headerName = {\"X-Real-Ip\", \"X-User-Real-Ip\"}) InetAddress ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n设置页面缓存@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/cache\")\n\t@HttpCache(expires = \"5\")\n\t@ResponseBody\n\tpublic String cache(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n以上，会自动计算 Cache-Control 和 pragma 的值。当然，您也可以手动指定。@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n\t@RequestMapping(path = \"/cache\")\n\t@HttpCache(expires = \"5\", cacheControl=\"public, max-age=5\")\n\t@ResponseBody\n\tpublic String cache(@RequestClientIp String ip, ServerHttpResponse response){\n\t\treturn ip;\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.1/web/filter.html",
      "children": [
        {
          "title": "过滤器",
          "url": "/manual/2.1/web/filter.html#过滤器",
          "content": "过滤器我们封装了一些使用的过滤器，简化了您的开发或者应用运行的跟踪。servlet 包位于 com.buession.web.servlet.filter，webflux 包位于 com.buession.web.reactive.filter，均有同样类名的过滤器类。"
        },
        {
          "title": "过滤器",
          "url": "/manual/2.1/web/filter.html#过滤器-过滤器",
          "content": "过滤器\n\n过滤器\n说明\n\n\n\n\nPoweredByFilter\nPowered By 过滤器\n\n\nPrintUrlFilter\n打印当前请求 URL 过滤器\n\n\nResponseHeaderFilter\n响应头过滤器，设置响应头\n\n\nResponseHeadersFilter\n响应头过滤器，批量设置响应头\n\n\nServerInfoFilter\nServer 信息过滤器，通过响应头的形式，输出当前服务器名称，可以用于集群环境定位出现问题的节点\n\n\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.1/web/restful.html",
      "children": [
        {
          "title": "RESTFUL",
          "url": "/manual/2.1/web/restful.html#restful",
          "content": "RESTFULRestful 是当今比较流行的一种架构的规范与约束、原则，基于这个风格设计的软件可以更简洁、更有层次。我们遵循 REST 规范，在代码层面规范好了新增、修改、详情、删除等基本的路由，您的控制器层只需要继承 com.buession.web.servlet.mvc.controller.AbstractBasicRestController 或者 com.buession.web.reactive.mvc.controller.AbstractBasicRestController 即可在 servlet 或 webflux 模式下，实现标准的 REST 风格的代码。简化了您的代码（主要是不用再写 @RequestMapping）和标准化了。@RestController@RequestMapping(path = \"/example\")\npublic class ExampleController extends AbstractRestController {\n\n\t@Override\n\tpublic Response add(HttpServletRequest request, HttpServletResponse response, @RequestBody ExampleDto example){\n\t\t\n\t}\n\n\t@Override\n\tpublic Response edit(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id, @RequestBody ExampleDto example){\n\n\t}\n\n\t@Override\n\tpublic Response detail(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\n\t}\n\n\t@Override\n\tpublic Response delete(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.1/web/utils.html",
      "children": [
        {
          "title": "工具",
          "url": "/manual/2.1/web/utils.html#工具",
          "content": "工具我们封装了一些 web 相关的工具类，用于处理 request、response。servlet 包位于 com.buession.web.servlet.http，webflux 包位于 com.buession.web.reactive.http，均有同样类名的过滤器类。获取客户端真实 IP 地址：RequestUtils.getClientIp(request);我们兼容了，通过微信和一些 CDN 厂商获取用户真实 IP 的头信息，我们优先获取从微信透传过来的用户的真实 IP，然后再是各 CDN 厂商的用户真实 IP 头，最后才是标准的真实 IP 头。当然，我们不能保证是否是伪造的。优先顺序：X-Forwarded-For-Pound（微信） > Ali-Cdn-Real-Ip（阿里云 CDN） > Cdn-Src-Ip（网宿） > X-Cdn-Src-Ip（网宿） > X-Original-Forwarded-For（天翼云） > X-Forwarded-For > X-Real-Ip > Proxy-Client-IP > WL-Proxy-Client-IP > Real-ClientIP > remote addr是否是 Ajax 请求：RequestUtils.isAjaxRequest(request);是否是移动设备请求：RequestUtils.isMobile(request);设置缓存：ResponseUtils.httpCache(response, 5); // 缓存 5 秒ResponseUtils.httpCache(response, new Date()); // 缓存到指定的时间点\n"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Framework API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-aop\n使用帮助\nAPI 手册\n\n\nbuession-beans\n使用帮助\nAPI 手册\n\n\nbuession-core\n使用帮助\nAPI 手册\n\n\nbuession-cron\n使用帮助\nAPI 手册\n\n\nbuession-dao\n使用帮助\nAPI 手册\n\n\nbuession-geoip\n使用帮助\nAPI 手册\n\n\nbuession-httpclient\n使用帮助\nAPI 手册\n\n\nbuession-io\n使用帮助\nAPI 手册\n\n\nbuession-jdbc\n使用帮助\nAPI 手册\n\n\nbuession-json\n使用帮助\nAPI 手册\n\n\nbuession-lang\n使用帮助\nAPI 手册\n\n\nbuession-net\n使用帮助\nAPI 手册\n\n\nbuession-redis\n使用帮助\nAPI 手册\n\n\nbuession-session\n使用帮助\nAPI 手册\n\n\nbuession-thesaurus\n使用帮助\nAPI 手册\n\n\nbuession-velocity\n使用帮助\nAPI 手册\n\n\nbuession-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.0/index.html",
      "children": []
    },
    {
      "title": "buession-aop 参考手册",
      "content": "AOP 封装，方便实现自定义注解",
      "url": "/manual/2.0/aop/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/aop/index.html#安装",
          "content": "安装    com.buession\n    buession-aop\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/aop/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-beans 参考手册",
      "content": "该类库提供了基于 commons-beanutils 和 cglib（spring-core 包中，名称空间：org.springframework.cglib.beans） 的 bean 工具的二次封装。包括了属性拷贝和属性映射，以及对象属性转换为 Map。",
      "url": "/manual/2.0/beans/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/beans/index.html#安装",
          "content": "安装    com.buession\n    buession-beans\n    x.x.x\n\n"
        },
        {
          "title": "属性拷贝",
          "url": "/manual/2.0/beans/index.html#属性拷贝",
          "content": "属性拷贝使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 和 target 的属性做映射，实现同名拷贝。import com.buession.beans.BeanUtils;\nBeanUtils.copyProperties(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.copyProperties(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "属性映射",
          "url": "/manual/2.0/beans/index.html#属性映射",
          "content": "属性映射使用此方法，可以实现两个对象之间的属性拷贝，该方式基于 BeanCopier 实现属性的拷贝。如果 source 对象是 Map 接口的实现，则会将 Map 的 key 转换为驼峰格式后和 target 的属性做映射，实现拷贝，这是 populate 和 copyProperties 的唯一区别。import com.buession.beans.BeanUtils;\nBeanUtils.populate(target, source)\n注：该方式只会对同类型的属性进行拷贝。即假设，source 中有数据类型为 int 名称为 id 的属性，target 中有数据类型为 long 名称为 id 的属性，是不会将 source 属性 id 的值拷贝到 target 的 id 属性上。\n我们可以指定 Converter 实现自定义规则进行属性拷贝。该方式的缺点是：BeanCopier 只使用 Converter 定义的规则去拷贝属性，所以在 convert 方法中要考虑所有的属性import com.buession.beans.BeanUtils;import org.springframework.cglib.core.Converter;\n\nBeanUtils.populate(target, source, new Converter() {\n\n\t@Override\n\tpublic Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter){\n\t\tif(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){\n\t\t\tif(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){\n\t\t\t\treturn sourceFieldValue;\n\t\t\t}\n\t\t}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){\n\t\t\treturn sourceFieldValue;\n\t\t}\n\n\t\treturn null;\n\t}\n\n});\n"
        },
        {
          "title": "Bean 转换为 Map",
          "url": "/manual/2.0/beans/index.html#bean-转换为-map",
          "content": "Bean 转换为 Map使用此方法，可以实现将一个 bean 对象转换为 Map，bean 的属性作为 Map 的 Keyimport com.buession.beans.BeanUtils;\nMap result = BeanUtils.toMap(bean)\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/beans/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "该类库为核心包，包括构建器、工具类、数据验证、ID 生成器、转换器、序列化、数学方法、消息注入、Manager 层注解、日期时间类和各通用接口定义。",
      "url": "/manual/2.0/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/core/index.html#安装",
          "content": "安装    com.buession\n    buession-core\n    x.x.x\n\n构建器Map、集合的便捷式构建，减少您的代码行数编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中收集器数组、Map、集合的工具类上下文定义应用上下文的类库、注解转换器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。日期时间日期、时间工具ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。数学函数定义了实用的数学函数序列化和反序列化对象的序列化和反序列化，包括二进制和 JSON。验证器数据验证器及其注解工具类常用通用性工具类其它通用的接口定义，框架自身类异常通用异常的定义"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/builder.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.0/core/builder.html#构建器",
          "content": "构建器Map、集合的便捷式构建，减少您的代码行数。您需要往 Map、List 中添加元素的传统写法是：import java.util.ArrayList;import java.util.List;\nimport java.util.HashMap;\nimport java.util.Map;\n\nList list = new ArrayList();\nlist.add(\"A\");\nlist.add(\"B\");\nlist.add(\"C\");\n\nMap map = new HashMap();\nmap.put(\"a\", \"A\");\nmap.put(\"b\", \"B\");\nmap.put(\"c\", \"C\");\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.Map;\n\nList list = ListBuilder.create().add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create().put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n此时的 List、Map 的实现类是 java.util.ArrayList、java.util.HashMap；您可以通过 create 指定其实现类。import com.buession.core.builder.ListBuilder;import com.buession.core.builder.MapBuilder;\nimport java.util.List;\nimport java.util.LinkedList;\nimport java.util.Map;\nimport java.util.LinkedHashMap;\n\nList list = ListBuilder.create(LinkedList.class).add(\"A\").add(\"B\").add(\"C\").build();\n\nMap map = MapBuilder.create(LinkedHashMap.class).put(\"a\", \"A\").put(\"b\", \"B\").put(\"c\", \"C\");\n注：实现类必须为 public，且必须有无参数的访问权限为 public 的构造函数\n当您有 value 为 null 时，不添加到 List 时，传统写法：import java.util.ArrayList;import java.util.List;\n\nString value = null;\nList list = new ArrayList();\n\nif(value != null){\n\tlist.add(value);\n}\n而当您使用 buession framework 可以这么写：import com.buession.core.builder.ListBuilder;import java.util.List;\n\nString value = null;\nList list = ListBuilder.create().addIfPresent(value).build();\nMap、Set、Queue 同理。"
        },
        {
          "title": "便捷方法",
          "url": "/manual/2.0/core/builder.html#构建器-便捷方法",
          "content": "便捷方法\n\n方法\n说明\n\n\n\n\n List ListBuilder.epmty()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of()\n创建空的 V 类型的 List 对象\n\n\n List ListBuilder.of(V value)\n创建仅有一个元素的 V 类型的 List 对象\n\n\n Queue QueueBuilder.epmty()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of()\n创建空的 V 类型的 Queue 对象\n\n\n Queue QueueBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Queue 对象\n\n\n Set SetBuilder.epmty()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of()\n创建空的 V 类型的 Set 对象\n\n\n Set SetBuilder.of(V value)\n创建仅有一个元素的 V 类型的 Set 对象\n\n\n Map MapBuilder.epmty()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of()\n创建空的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\n Map MapBuilder.of(V value)\n创建仅有一个元素的 Key 为 K 类型，值为 V 类型的 Map 对象\n\n\nempty 与 jdk Collections.emptyList()、Collections.emptySet()、Collections.emptyMap() 的本质区别是返回的 List 实例不同，该方法创建的 List、Set、Map 实例是允许对 List、Set、Map 做操作，而 jdk Collections 创建的 List、Set、Map 则是不允许的。两个 of 方法均同理。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/builder.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/codec.html",
      "children": [
        {
          "title": "编码器",
          "url": "/manual/2.0/core/codec.html#编码器",
          "content": "编码器目前，仅包含消息构建器，您可以通过注解 @Message 将您环境中的错误码、错误消息的配置注入到 MessageObject 类型的属性中。我们在当前现代应用中，通常会在业务上定义业务错误信息。且我们返回给前端的可能是更模糊或通用的、人为可读性更强的错误信息，而且可能两种不同原因引起的错误，但是我们在返回给前端时的错误信息是一模一样的，这时我们就需要更精确的标识来定义错误，通常为错误码。此时，我们在应用中，通常会定义错误码和错误信息的映射关系。我们可用的方法大致是，第一代码里面写死；第二，定义错误枚举或者常量。无论哪种方式的都与代码高度耦合，可读性和可维护性都差。此时此刻，您可以通过 buession framework 的注解 @Message 来简化和解耦您的错误信息配置和代码。在传统 springmvc 应用中，您可以通过 context:property-placeholder 或者 util:properties 标签将错误信息 properties 配置文件加载到当前应用环境中。USER_NOT_FOUND.code = 10404USER_NOT_FOUND.message = 用户不存在\n\nUSER_LOGIN_FAILURE.code = 10405\nUSER_LOGIN_FAILURE.message = 登录失败\n\n\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(\"USER_NOT_FOUND\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n您可以自定义错误代码和错误消息的 key，默认分别为：code、message。此时您需要在注解 @Message 上显示指定错误代码和错误消息的 key。USER_NOT_FOUND.errorCode = 10404USER_NOT_FOUND.errorMessage = 用户不存在\n\nUSER_LOGIN_FAILURE.errorCode = 10405\nUSER_LOGIN_FAILURE.errorMessage = 登录失败\nimport com.buession.core.codec.Message;import com.buession.core.codec.MessageObject;\n\npublic UserServiceImpl implements UserService {\n\n\t@Message(value = \"USER_NOT_FOUND\", code = \"errorCode\", message = \"errorMessage\")\n\tprivate MessageObject userNotFound;\n\n\t@Override\n\tpublic User update(User user) throws Exception{\n\t\tUser dbUser = get(user.getId());\n\n\t\tif(dbUser == null){\n\t\t\tthrow new Exception(userNotFound.getMessage() + \"(code: \" + userNotFound.getCode() + \")\");\n\t\t\t// 用户不存在(code: 10404)\n\t\t}\n\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/codec.html#编码器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/collect.html",
      "children": [
        {
          "title": "收集器",
          "url": "/manual/2.0/core/collect.html#收集器",
          "content": "收集器数组、Map、集合的工具类"
        },
        {
          "title": "数组",
          "url": "/manual/2.0/core/collect.html#收集器-数组",
          "content": "数组数组工具类 Arrays 继承自 org.apache.commons.lang3.ArrayUtils 封装了元素是否存在检测、元素索引位置获取、拼接成字符串、转换为 List、Set 以及字符串类型的数组、数组合并、数组元素操作等方法。检测数组 array 中是否存在元素 element：import com.buession.core.collect.Arrays;\nboolean result = Arrays.contains(array, element);\n返回元素 element 在数组 array 中第一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.indexOf(array, element);\n返回元素 element 在数组 array 中最后一次出现的位置的索引，不存在返回 -1：import com.buession.core.collect.Arrays;\nint result = Arrays.lastIndexOf(array, element);\n将字符串拼接会字符串：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString result = Arrays.toString(array);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString glue = \"-\";\nString result = Arrays.toString(array, glue);\n// 1-2-3\n可以通过方法 toList、toSet 转换为 List 和 Set：import com.buession.core.collect.Arrays;import java.util.List;\nimport java.util.Set;\n\nint[] array = {1, 2, 3};\nList list = Arrays.toList(array);\nSet set = Arrays.toSet(array);\n将数组转换为字符串类型的数组：import com.buession.core.collect.Arrays;\nint[] array = {1, 2, 3};\nString[] result = Arrays.toStringArray(array);\n将数组进行合并：import com.buession.core.collect.Arrays;\nString[] result = Arrays.toStringArray(array1, array2, array3);\n对数组元素进行操作：import com.buession.core.collect.Arrays;\nString[] array = {\"A\", \"B\", \"C\"};\nString[] result = Arrays.map(array, String.class, fn);\n第二个参数为数组元素类型，第三个参数为 java.util.function.Function 的实现"
        },
        {
          "title": "Lists",
          "url": "/manual/2.0/core/collect.html#收集器-lists",
          "content": "ListsList 工具类 Lists 实现了将元素拼接成字符串、转换为 Set 操作。将字符串拼接会字符串：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString result = Lists.toString(list);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Lists.toString(list);\n// 1-2-3\n可以通过方法 toSet 将 List 转换为 Set：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nList list = ListBuilder.create().add(1).add(2).add(3).build();\nSet set = Lists.toSet(list);\n"
        },
        {
          "title": "Sets",
          "url": "/manual/2.0/core/collect.html#收集器-sets",
          "content": "SetsSett 工具类 Sets 实现了将元素拼接成字符串、转换为 List 操作。将字符串拼接会字符串：import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString result = Sets.toString(set);\n// 1, 2, 3\n可以通过参数 glue 指定连接符，默认为：\", \"import com.buession.core.collect.Sets;import com.buession.core.builder.SetBuilder;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nString glue = \"-\";\nString result = Sets.toString(list);\n// 1-2-3\n可以通过方法 toList 将 Set 转换为 List：import com.buession.core.collect.Lists;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\nimport java.util.Set;\n\nSet set = SetBuilder.create().add(1).add(2).add(3).build();\nList list = Sets.toList(set);\n"
        },
        {
          "title": "Maps",
          "url": "/manual/2.0/core/collect.html#收集器-maps",
          "content": "MapsMap 工具类 Maps 实现了对 key 和 value 进行操作，实现了将 value 转换为 List 和 Set。对 Map 进行操作：import com.buession.core.collect.Maps;import java.util.Map;\nimport java.util.HashMap;\n\nMap maps = new HashMap();\nMap result = Maps.map(maps, (key)->key, (value)->value == null ? null : value.toString());\n第二个、第三参数为 java.util.function.Function 的实现可以通过方法 toList 将 Map 的 value 转换为 List：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.List;\n\nList list = Maps.toList(maps);\n可以通过方法 toSet 将 Map 的 value 转换为 Set：import com.buession.core.collect.Maps;import com.buession.core.builder.ListBuilder;\nimport java.util.Set;\n\nSet set = Maps.toSet(maps);\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/collect.html#收集器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/context.html",
      "children": [
        {
          "title": "上下文",
          "url": "/manual/2.0/core/context.html#上下文",
          "content": "上下文注解 com.buession.core.context.stereotype.Manager 用于在分层应用中，标记当前类是一个 manager 类。类似于 org.springframework.stereotype.Service 加上该注解会将当前类自动注入到 spring 容器中，用法和 @Service 一样。在多层应用架构中 Manager 层通常为：通用业务处理层，处于 Dao 层之上，Service 层之下。主要特征如下：逻辑少\n与 Dao 层进行交互，多个 Dao 层的复用\nService 通用底层逻辑的下沉，如：缓存方案、中间件通用处理、以及对第三方平台封装的层\nimport com.buession.core.context.stereotype.Manager;import org.springframework.stereotype.Service;\n\npublic interface UserManager {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Manager\npublic class UserManagerImpl implements UserManager {\n\n\t@Autowired\n\tprivate UserDao userDao;\n\n\t@Autowired\n\tprivate UserProfileDao userProfileDao;\n\n\t@Autowired\n\tprivate RedisTemplate redisTemplate;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = redisTemplate.hGetObject(\"user\", Integer.toString(id), User.class);\n\n\t\tif(user == null){\n\t\t\tuser = userDao.getByPrimary(id);\n\t\t\tif(user != null){\n\t\t\t\tuser.setProfile(userProfileDao.getByUserId(id));\n\t\t\t\tredisTemplate.hSet(\"user\", Integer.toString(id), user);\n\t\t\t}else{\n\t\t\t\tthrow new RuntimeException(\"用户不存在\");\n\t\t\t}\n\t\t}\n\n\t\treturn user;\n\t}\n\n}\n\npublic interface UserService {\n\n\tUser getByPrimary(int id);\n\n}\n\n@Service\npublic class UserServiceImpl implements UserService {\n\n\t@Autowired\n\tprivate UserManager userManager;\n\n\n\t@Override\n\tpublic User getByPrimary(int id){\n\t\tUser user = userManager.getByPrimary(id);\n\n\t\t...\n\n\t\treturn user;\n\t}\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/context.html#上下文-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/converter.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.0/core/converter.html#构建器",
          "content": "构建器数据转化器，基于 mapstruct 的 POJO 类映射接口定义。定义了常用的数据转化器。接口定义：@FunctionalInterfacepublic interface Converter {\n\n\tT convert(final S source);\n\n}\n将类似为 S 的对象转换为类型为 T 的对象。"
        },
        {
          "title": "内置转换器",
          "url": "/manual/2.0/core/converter.html#构建器-内置转换器",
          "content": "内置转换器\n\n转换器\n说明\n\n\n\n\nArrayConverter\n将 S 类型的数组转换为 T 类型的数组\n\n\nEnumConverter\n枚举转换器，将字符串转换为枚举 E\n\n\nBinaryEnumConverter\n枚举转换器，将 byte 数组转换为枚举 E\n\n\nBooleanStatusConverter\n将布尔值转换为 com.buession.lang.Status\n\n\nStatusBooleanConverter\n将 com.buession.lang.Status 转换为布尔值\n\n\nPredicateStatusConverter\n通过 java.util.function.Predicate 对某种数据类型的数据进行判断结果转换为 com.buession.lang.Status\n\n\nListConverter\n将 S 类型的 List 对象转换为 T 类型的 List 对象\n\n\nSetConverter\n将 S 类型的 Set 对象转换为 T 类型的 Set 对象\n\n\nMapConverter\n将 Key 为 SK 类型、值为 SV 类型的 Map 对象转换为 Key 为 TK 类型、值为 TV 类型的 Map\n\n\n将 key 为 Integer 类型，value 为 Object 类型的 Map 转换成 key 为 String 类型，value 为 String 类型的 Map 对象import com.buession.core.converter.MapConverter;import java.util.Map;\n\nMap source;\nMap target;\nMapConverter converter = new MapConverter();\n\ntarget = converter.convert(source);\n将字符串转换为枚举import com.buession.core.converter.EnumConverter;import com.buession.lang.Gender;\n\nGender target;\nEnumConverter converter = new EnumConverter(Gender.class);\n\ntarget = converter.convert(\"FEMALE\");\n// Gender.FEMALE\n\ntarget = converter.convert(\"F\");\n// null\n"
        },
        {
          "title": "POJO 类映射",
          "url": "/manual/2.0/core/converter.html#构建器-pojo-类映射",
          "content": "POJO 类映射我们通过 com.buession.core.converter.mapper.Mapper 接口约定了，基于 mapstruct POJO 类的映射接口规范。关于 mapstruct 的更多文章，可通过搜索引擎自行搜索。public interface Mapper {\n\t/**\n\t * 将源对象映射到目标对象\n\t *\n\t * @param object\n\t * \t\t源对象\n\t *\n\t * @return 目标对象实例\n\t */\n\tT mapping(S object);\n\n\t/**\n\t * 将源对象数组映射到目标对象数组\n\t *\n\t * @param object\n\t * \t\t源对象数组\n\t *\n\t * @return 目标对象实例数组\n\t */\n\tT[] mapping(S[] object);\n\n\t/**\n\t * 将源 list 对象映射到目标 list 对象\n\t *\n\t * @param object\n\t * \t\t源 list 对象\n\t *\n\t * @return 目标对象 list 实例\n\t */\n\tList mapping(List object);\n\n\t/**\n\t * 将源 set 对象映射到目标 set 对象\n\t *\n\t * @param object\n\t * \t\t源 set 对象\n\t *\n\t * @return 目标对象 set 实例\n\t */\n\tSet mapping(Set object);\n\n}\n我们还可以使用工具类 com.buession.core.converter.mapper.PropertyMapper 将值从提供的源映射到目标，此方法来拷贝并简单修改于：springboot 中的 org.springframework.boot.context.properties.PropertyMapper，和原生 springboot 中的用法一样。import com.buession.core.converter.mapper.PropertyMapper;\nUser source = new User();\nUser target = new User();\n\nPropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();\npropertyMapper.form(source::getId).to(target:setId)\n// null\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/converter.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/datetime.html",
      "children": [
        {
          "title": "日期时间",
          "url": "/manual/2.0/core/datetime.html#日期时间",
          "content": "日期时间日期、时间工具。目前，仅有少量的 API，参考 PHP 中的日期时间函数而来。获取当前 Unix 时间戳（秒）：import com.buession.core.datetime.DateTime;\nDateTime.unixtime();\n该方法我们在实际业务中经常用到。以 \"msec sec\" 的格式返回当前 Unix 时间戳和微秒数：import com.buession.core.datetime.DateTime;\nDateTime.microtime();\n// 1657171717 948000\n该方法参考 PHP 的 microtime 函数而来。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/datetime.html#日期时间-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/id.html",
      "children": [
        {
          "title": "ID 生成器",
          "url": "/manual/2.0/core/id.html#id-生成器",
          "content": "ID 生成器基于 UUID、jnanoid ID、雪花算法等等的 ID 生成器。您可以通过 buession framework 提供的 ID 生成器，生成唯一 ID。接口规范。public interface IdGenerator {\n\t/**\n\t * 获取下一个 ID\n\t *\n\t * @return ID\n\t */\n\tT nextId();\n\n}\n"
        },
        {
          "title": "ID 生成器",
          "url": "/manual/2.0/core/id.html#id-生成器-id-生成器",
          "content": "ID 生成器\n\n生成器\n说明\n\n\n\n\nAtomicSimpleIdGenerator\n基于 AtomicLong 递增的，简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nAtomicUUIDIdGenerator\n基于 AtomicLong 递增的，UUID ID 生成器\n\n\nNanoIDIdGenerator\njnanoid ID 生成器，可通过构造函数指定字符范围、长度\n\n\nRandomDigitIdGenerator\n随机数 ID 生成器，返回指定范围内的随机数，默认为 1L ~ Long.MAX_VALUE，可通过构造函数指定\n\n\nRandomIdGenerator\n随机字符 ID 生成器，可通过构造函数指定生成长度，默认 16 位\n\n\nSimpleIdGenerator\n简单 ID 生成器，基于  UUID 生成，将 UUID 结果中的 \"-\" 过滤掉\n\n\nSnowflakeIdGenerator\n雪花算法 ID 生成器，此处不解决时钟回拨的问题，您可以通过构造函数指定开始时间、数据中心 ID、数据中心 ID 所占的位数等等值\n\n\nUUIDIdGenerator\nUUID ID 生成器\n\n\nimport com.buession.core.id.AtomicUUIDIdGenerator;import com.buession.core.id.NanoIDIdGenerator;\nimport com.buession.core.id.SnowflakeIdGenerator;\nimport com.buession.core.id.UUIDIdGenerator;\nimport com.buession.core.id.SimpleIdGenerator;\n\nAtomicUUIDIdGenerator idGenerator = new AtomicUUIDIdGenerator();\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000001\nidGenerator.nextId(); // 00000000-0000-0000-0000-000000000002\n\nNanoIDIdGenerator idGenerator = new NanoIDIdGenerator();\nidGenerator.nextId(); // omRdTPCug5z_Uk1E_x3ozu3Avyyl3XSK\n\nSnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();\nidGenerator.nextId(); // 170602258864545792\n\nUUIDIdGenerator idGenerator = new UUIDIdGenerator();\nidGenerator.nextId(); // 8634a166-e7d6-4160-85eb-3433107de5a4\n\nSimpleIdGenerator idGenerator = new SimpleIdGenerator();\nidGenerator.nextId(); // 26d9ed57fdad443894b988eeabc69c05\n注：关于雪花算法、jnanoid 算法的可自行搜索。\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/id.html#id-生成器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/math.html",
      "children": [
        {
          "title": "数学函数",
          "url": "/manual/2.0/core/math.html#数学函数",
          "content": "数学函数定义了实用的数学函数。\n\n方法\n说明\n\n\n\n\ncontinuousSum\n计算两个数之间连续相加之和\n\n\nrangeValue\n获取合法范围内的值，如果 value 小于 min，则返回 min；如果 value 大于 max，则返回 max；否则返回 value 本身\n\n\nimport com.buession.core.math.Math;\nlong result = Math.continuousSum(1, 100);\n// 5050\nimport com.buession.core.math.Math;\nlong value = 3;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 4\nimport com.buession.core.math.Math;\nlong value = 5;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 5\nimport com.buession.core.math.Math;\nlong value = 11;\nlong min = 4;\nlong max = 10;\nlong result = Math.rangeValue(value, min, max);\n// 10\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/math.html#数学函数-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/serializer.html",
      "children": [
        {
          "title": "构建器",
          "url": "/manual/2.0/core/serializer.html#构建器",
          "content": "构建器对象的序列化和反序列化，包括二进制和 JSON。您可以通过该 API，实现将对象序列化成二进制或 JSON 字符串；或将二进制、JSON 字符串反序列化为对象。"
        },
        {
          "title": "序列化、反序列化类",
          "url": "/manual/2.0/core/serializer.html#构建器-序列化、反序列化类",
          "content": "序列化、反序列化类\n\n类\n说明\n\n\n\n\nDefaultByteArraySerializer\n将对象序列化为二进制，或将二进制反序列化为对象\n\n\nFastJsonJsonSerializer\n基于 FastJSON 的对象与 JSON 之间的序列化和反序列化\n\n\nGsonJsonSerializer\n基于 Gson 的对象与 JSON 之间的序列化和反序列化\n\n\nJacksonJsonSerializer\n基于 Jackson2 的对象与 JSON 之间的序列化和反序列化\n\n\n通用标准方法是，将对象序列化为字符串，或将字符串反序列化为对象\nDefaultByteArraySerializer 序列化成字符串，逻辑是在对象序列化为 byte 数组后，通过 URLEncoder.encode 进行编码；反序列化，则先通过 URLDecoder.decode 进行解码成 byte 数组，在反序列化为对象\nDefaultByteArraySerializer 支持对象与 byte 数组数组之间的序列化和反序列化\n在将 JSON 字符串反序列化为对象时，默认返回类型依据 fastjson、jackson 等原生逻辑\nFastJsonJsonSerializer、GsonJsonSerializer、JacksonJsonSerializer 可以通过参数 Class、TypeReference 指定返回的对象类型\ncom.buession.core.serializer.type.TypeReference 是某类型的一个指向或者引用，用于屏蔽 fastjson、gson、jackson 中通过 JDK Type 指定反序列化的类型；在 fastjson、gson 中是直接指定 Type，在 jackson 中是通过 com.fasterxml.jackson.core.type.TypeReference 类返回\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/serializer.html#构建器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/validator.html",
      "children": [
        {
          "title": "验证器",
          "url": "/manual/2.0/core/validator.html#验证器",
          "content": "验证器数据验证器及其注解。该 API 提供了大量通用的适用于本土化的常用验证方法，如：判断是否为 null、判断是否不为 null、判断（字符串、数组、集合、Map等）是否为空、判断（字符串、数组、集合、Map等）是否不为空、IP 地址合法性验证、ISBN 合法性验证、身份证号码验证、QQ 验证。并提供对应的基于 javax.validation 的校验注解。验证是否为 null判断任意对象是否为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNull(obj);\n验证是否不为 null判断任意对象是否不为 nullimport com.buession.core.validator.Validate;\nboolean result = Validate.isNotNull(obj);\n判断字符串是否为空白字符串判断字符串是否为空白字符串，为 null 或长度为 0 时也返回 true；其余情况，字符串中含有任意可打印字符串则为 falseimport com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\n\";\nboolean result = Validate.isBlank(str); // true\n\nString str = \"\\\\r\\\\na\";\nboolean result = Validate.isBlank(str); // false\n注：isNotBlank 与之相反\n判断是否为空isEmpty 可判断字符串、数组、集合、Map 是否为空，即：为 null 或长度/大小为 0 时，表示为空import com.buession.core.validator.Validate;\nString str = null;\nboolean result = Validate.isEmpty(str); // true\n\nString str = \" \";\nboolean result = Validate.isEmpty(str); // false\n\nboolean result = Validate.isEmpty(new String[]{}); // true\n\nboolean result = Validate.isEmpty(new Integer[]{1}); // false\n注：isNotEmpty 与之相反\n判断是否在两个数之间isBetween 可判断一个整型或浮点型，是否在两个整型或者浮点型数字之间import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3); // true\n\nboolean result = Validate.isBetween(2, 2, 3); // false\n可通过参数设置是否包含边界值import com.buession.core.validator.Validate;\nboolean result = Validate.isBetween(2, 1, 3, true); // true\n\nboolean result = Validate.isBetween(2, 2, 3, true); // true\n判断是否为电话号码isTel 可判断一个字符串是否为电话号码，仅支持普通座机号码，不支持 110、400、800等特殊号码。格式，需符合：86-区号-电话号码、区号-电话号码、（区号）电话号码、(区号)电话号码、电话号码。可通过参数 com.buession.core.validator.routines.TelValidator.AreaCodeType 指定区号的控制策略。仅支持中国的电话号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isTel(\"028-12345678\"); // true\n\nboolean result = Validate.isTel(\"028-02345678\"); // false\n判断是否为手机号码isMobile 可判断一个字符串是否为手机号码。仅支持中国的手机号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isMobile(\"028-12345678\"); // false\n\nboolean result = Validate.isMobile(\"13800138000\"); // true\n判断是否为邮政编码isPostCode 可判断一个字符串是否为邮政编码。仅支持中国的邮政编码。import com.buession.core.validator.Validate;\nboolean result = Validate.isPostCode(\"043104\"); // false\n\nboolean result = Validate.isPostCode(\"643104\"); // true\n判断是否为 QQ 号码isQQ 可判断一个字符串是否为 QQ 号码。import com.buession.core.validator.Validate;\nboolean result = Validate.isQQ(\"043104\"); // false\n\nboolean result = Validate.isQQ(\"251329041\"); // true\n判断是否为身份证号码isIDCard 可判断一个字符串是否合法的身份证号码。仅支持 18 位的身份证号码。身份证号码需符合其身份证号码编排规律。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\");\n可以和身份证号码进行比对，其实该方法的实际作用不是很大，只是在业务系统里面有需要填写身份证号码和出生日期时，简化验证出生日期和身份证号码中的出生日期是否一致。import com.buession.core.validator.Validate;\nboolean result = Validate.isIDCard(\"xxxxxxxxxxxxxxxxx\", true, \"2000-01-01\");\n其它，更多方法可以参考手册。"
        },
        {
          "title": "注解",
          "url": "/manual/2.0/core/validator.html#验证器-注解",
          "content": "注解javax 的 validation 是 Java 定义的一套基于注解的数据校验规范，buession framework 基于 javax.validation 实现了 Validate 中所有验证方法的校验注解。\n\n注解\n验证的数据类型\n说明\n\n\n\n\n@Alnum\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Alpha\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Numeric\nCharSequence 的子类型，Character\n验证注解的元素值是否为数字\n\n\n@Between\nshort、int、double 等任何 Number 的子类型\n验证注解的元素值是否为在两个数之间\n\n\n@Empty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否为空\n\n\n@NotEmpty\nCharSequence、Map、Collection、Iterator、Enumeration 的子类型和数组\n验证注解的元素值是否不为空\n\n\n@HasText\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@IDCard\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Ip\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Isbn\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@MimeType\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Mobile\nCharSequence 的子类型\n验证注解的元素值是否有非空字符\n\n\n@Null\n任意类型\n验证注解的元素值是否为 null\n\n\n@NotNull\n任意类型\n验证注解的元素值是否为 null\n\n\n@Port\nInteger\n验证注解的元素值是否为 null\n\n\n@PostCode\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@QQ\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Tel\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n@Xdigit\nCharSequence 的子类型\n验证注解的元素值是否为 null\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/validator.html#验证器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/utils.html",
      "children": [
        {
          "title": "工具类",
          "url": "/manual/2.0/core/utils.html#工具类",
          "content": "工具类常用通用性工具类。"
        },
        {
          "title": "Byte 数组比较",
          "url": "/manual/2.0/core/utils.html#工具类-byte-数组比较",
          "content": "Byte 数组比较ByteArrayComparable 类为 java Comparable 的实现，实现了 byte 数组的比较。"
        },
        {
          "title": "注解工具",
          "url": "/manual/2.0/core/utils.html#工具类-注解工具",
          "content": "注解工具AnnotationUtils 继承 org.springframework.core.annotation.AnnotationUtils ，在此基础上新增了方法 hasClassAnnotationPresent(final Class clazz, final Class[] annotations)、hasMethodAnnotationPresent(Method method, final Class[] annotations) 判断类或者方法上是否有待检测的注解中的其中一个注解。"
        },
        {
          "title": "断言",
          "url": "/manual/2.0/core/utils.html#工具类-断言",
          "content": "断言Assert 和 springframework 中的注解类似，不过逻辑有些相反。"
        },
        {
          "title": "Byte 工具",
          "url": "/manual/2.0/core/utils.html#工具类-byte-工具",
          "content": "Byte 工具ByteUtils 可以将 byte 数组转换为 char 或者 char 数组。import com.buession.core.utils.ByteUtils;\nbyte[] bytes;\nchar c = ByteUtils.toChar(bytes);\n\nchar[] chars = ByteUtils.toChar(bytes);\nbyte 数组连接。import com.buession.core.utils.ByteUtils;\nbyte[] dest;\nbyte[] source\nbyte[] result = ByteUtils.concat(dest, source);\n"
        },
        {
          "title": "Character 工具",
          "url": "/manual/2.0/core/utils.html#工具类-character-工具",
          "content": "Character 工具CharacterUtils 继承 org.apache.commons.lang3.CharUtils，可以将 char、char 数组转换为 byte 数组。import com.buession.core.utils.CharacterUtils;\nchar c;\nbyte[] bytes = ByteUtils.toBytes(c);\n\nchar[] chars;\nbyte[] bytes = ByteUtils.toBytes(chars);\n"
        },
        {
          "title": "数字工具",
          "url": "/manual/2.0/core/utils.html#工具类-数字工具",
          "content": "数字工具NumberUtils 提供了对数字相关的操作。\n\n方法\n说明\n\n\n\n\nint2bytes\n将 int 转换为 byte[]\n\n\nbytes2int\n将 byte[] 转换为 int\n\n\nlong2bytes\n将 long 转换为 byte[]\n\n\nbytes2long\n将 byte[] 转换为 long\n\n\nfloat2bytes\n将 float 转换为 byte[]\n\n\nbytes2float\n将 byte[] 转换为 float\n\n\ndouble2bytes\n将 double 转换为 byte[]\n\n\nbytes2double\n将 byte[] 转换为 double\n\n\n"
        },
        {
          "title": "字符串工具",
          "url": "/manual/2.0/core/utils.html#工具类-字符串工具",
          "content": "字符串工具StringUtils 继承了 org.apache.commons.lang3.StringUtils，封装了多字符串更多的操作。截取字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.substr(\"abcde\", 1); // bcde\nString result = StringUtils.substr(\"abcde\", 1, 2); // bc\n生成随机字符串import com.buession.core.utils.StringUtils;\nString result = StringUtils.random(length);\n比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 3); // true\nboolean result = StringUtils.equals(\"abcd\", \"abce\", 4); // false\n忽略大小写比较两个 CharSequence 前 length 位是否相等import com.buession.core.utils.StringUtils;\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"Abce\", 3); // true\nboolean result = StringUtils.equalsIgnoreCase(\"abcd\", \"aBce\", 4); // false\n"
        },
        {
          "title": "拼音工具",
          "url": "/manual/2.0/core/utils.html#工具类-拼音工具",
          "content": "拼音工具PinyinUtils 封装了获取中文拼音、拼音首字母的方法。import com.buession.core.utils.PinyinUtils;\nString result = PinyinUtils.getPinyin(\"中国\"); // zhongguo\nString result = PinyinUtils.getPinYinFirstChar(\"中国\"); // zg\n"
        },
        {
          "title": "随机数工具",
          "url": "/manual/2.0/core/utils.html#工具类-随机数工具",
          "content": "随机数工具RandomUtils 封装了随机数的生成。\n\n方法\n说明\n\n\n\n\nnextBoolean\n随机布尔值\n\n\nnextBytes\n随机字节数组\n\n\nnextInt\n生成指定范围内的 int 值，默认：0 到 Integer.MAX_VALUE\n\n\nnextLong\n生成指定范围内的 long 值，默认：0 到 Long.MAX_VALUE\n\n\nnextFloat\n生成指定范围内的 float 值，默认：0 到 Float.MAX_VALUE\n\n\nnextDouble\n生成指定范围内的 double 值，默认：0 到 Double.MAX_VALUE\n\n\n"
        },
        {
          "title": "Properties 工具",
          "url": "/manual/2.0/core/utils.html#工具类-properties-工具",
          "content": "Properties 工具PropertiesUtils 封装了对 Properties 的操作，主要是 Properties.getProperty 的值为字符串，而我们在实际场景中，很多时候其值可能为 int、double。传统方法是，我们在代码中通过 Properties.getProperty 获取其值后，对其进行转换；而 PropertiesUtils 简化了操作。import com.buession.core.utils.SystemPropertyUtils;\nInteger result = PropertiesUtils.getInteger(properties, key);\nBoolean result = PropertiesUtils.getBoolean(properties, key);\n"
        },
        {
          "title": "System Property 工具",
          "url": "/manual/2.0/core/utils.html#工具类-system-property-工具",
          "content": "System Property 工具SystemPropertyUtils 封装了系统属性或系统环境变量的操作。设置属性方法 setProperty 对 System.setProperty 的封装，唯一区别是：SystemPropertyUtils.setProperty 的值可以为非字符串，该方法类会将非字符串值转换为字符串再调用 System.setProperty。import com.buession.core.utils.SystemPropertyUtils;\nSystemPropertyUtils.setProperty(\"http.port\", 8080);\nSystemPropertyUtils.setProperty(\"http.ssl.enable\", false);\n获取属性方法 getProperty 会先通过 System.getProperty 进行获取，若未获取到值，再调用 System.getenv 进行获取。String value = System.getProperty(name);\nif(Validate.hasText(value) == false){\n  value = System.getenv(name);\n}\n"
        },
        {
          "title": "版本工具",
          "url": "/manual/2.0/core/utils.html#工具类-版本工具",
          "content": "版本工具VersionUtils 提供了对两个版本值的比较方法和获取类、jar 对应的版本。import com.buession.core.utils.VersionUtils;\nVersionUtils.compare(\"1.0.0\", \"1.0.1-beta\"); // -1\nVersionUtils.compare(\"1.0.0\", \"1.0.0r\"); // -1\n规则：dev < alpha = a < beta = b < rc < release < pl = p < 纯数字版本获取类的版本值import com.buession.core.utils.VersionUtils;\nByteUtils.determineClassVersion(com.buession.core.utils.VersionUtils.class); // 2.0.0\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/utils.html#工具类-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/other.html",
      "children": [
        {
          "title": "其它",
          "url": "/manual/2.0/core/other.html#其它",
          "content": "其它通用的接口定义，框架自身类，以及其它杂项。"
        },
        {
          "title": "框架自身工具",
          "url": "/manual/2.0/core/other.html#其它-框架自身工具",
          "content": "框架自身工具获取 Buession Framework 版本：import com.buession.core.Framework;import com.buession.core.BuesssionFrameworkVersion;\n\nBuesssionFrameworkVersion.getVersion(); // 2.0.0\nFramework.VERSION; // 2.0.0\n获取 Buession Framework 框架名称：import com.buession.core.Framework;\nFramework.NAME; // \"Buession\"\n"
        },
        {
          "title": "命令执行器",
          "url": "/manual/2.0/core/other.html#其它-命令执行器",
          "content": "命令执行器命令执行器接口：/** * 命令执行器\n *\n * @param \n * \t\t命令上下文\n * @param \n * \t\t命令执行返回值\n */\n@FunctionalInterface\npublic interface Executor {\n\n\t/**\n\t * 命令执行\n\t *\n\t * @param context\n\t * \t\t命令执行器上下文\n\t *\n\t * @return 命令执行返回值，R 类型的实例\n\t */\n\tR execute(C context);\n\n}\n您可以通过，该接口执行一个命令上下文的方法，并返回一个值。该方法有些类似代理模式的代理类。"
        },
        {
          "title": "销毁接口",
          "url": "/manual/2.0/core/other.html#其它-销毁接口",
          "content": "销毁接口功能类似 java.io.Closeable。public interface Destroyable {\n\t/**\n\t * 销毁相关资源\n\t *\n\t * @throws IOException\n\t * \t\tIO 错误时抛出\n\t */\n\tvoid destroy() throws IOException;\n\n}\n"
        },
        {
          "title": "Rawable",
          "url": "/manual/2.0/core/other.html#其它-rawable",
          "content": "Rawable原始的，约定实现该接口的类，必须返回原始字节数组。public interface Rawable {\n\t/**\n\t * 返回原始的字节数组\n\t *\n\t * @return 原始的字节数组\n\t */\n\tbyte[] getRaw();\n\n}\n"
        },
        {
          "title": "名称节点",
          "url": "/manual/2.0/core/other.html#其它-名称节点",
          "content": "名称节点名称节点，约定实现该接口的类应该返回一个名称public interface NamedNode {\n\t/**\n\t * 返回节点名称\n\t *\n\t * @return 节点名称\n\t */\n\t@Nullable\n\tString getName();\n\n}\n"
        },
        {
          "title": "分页",
          "url": "/manual/2.0/core/other.html#其它-分页",
          "content": "分页com.buession.core.Pagination 为一个分页 POJO 类，包括了当前页码、每页大小、上一页、下一页、总页数、总记录数、数据列表。能够根据设定的其中一个值，计算另外的值。"
        }
      ]
    },
    {
      "title": "buession-core 参考手册",
      "content": "",
      "url": "/manual/2.0/core/exception.html",
      "children": [
        {
          "title": "异常",
          "url": "/manual/2.0/core/exception.html#异常",
          "content": "异常通用异常的定义。\n\n异常\n说明\n\n\n\n\nAccessException\n拒绝访问异常\n\n\nClassInstantiationException\n类实例化异常\n\n\nConversionException\n数据类型转换异常\n\n\nDataAlreadyExistException\n数据已存在异常\n\n\nDataNotFoundException\n数据不存在或未找到异常\n\n\nInsteadException\n类方法废弃后，需要使用其它类库方法来替代\n\n\nNestedRuntimeException\n嵌套运行时异常\n\n\nOperationException\n运算异常\n\n\nPresentException\n--\n\n\nSerializationException\n序列化异常\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/exception.html#异常-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-cron 参考手册",
      "content": "对 quartz 的二次封装",
      "url": "/manual/2.0/cron/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/cron/index.html#安装",
          "content": "安装    com.buession\n    buession-cron\n    x.x.x\n\n由于在过去的工作中，定时任务基本使用 quartz 来实现；但是在初始化定时任务项目时，大量基本相同的代码，因此对此部分做了二次封装，简化了 quartz 项目的初始化。由于在现在有众多优秀的分布式定时任务，如：elastic-job、xxl-job 等等，因此直接使用 quartz 应该会越来越少（个人主观猜测），即使使用 quartz 初始化也简单，故该模块将不做说明。且在今后的版本中，该模块可能会被废弃。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/cron/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "对 mybatis、spring-data-mongo 常用方法（如：根据条件获取单条记录、根据主键获取单条记录、分页、根据条件删除数据、根据主键删除数据）进行了二次封装。从代码层面实现了数据库的读写分离，insert、update、delete 操作主库，select 操作从库。",
      "url": "/manual/2.0/dao/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/dao/index.html#安装",
          "content": "安装    com.buession\n    buession-dao\n    x.x.x\n\n我们咋众多项目中，基本有常见的重复的对数据库的 CURD 操作，比如：根据主键查询数据、根据主键删除数据、获取一条记录。MyBatis 是一款优秀的持久层框架，应用广泛。MongoDB 是一款优秀的文档数据库。我自己根据从业多年的经验，从实际场合出发，将在业务层对数据库的常用操作进行了封装。对关系型数据库基于 MyBatis 二次封装，对 MongoDB 基于 spring-data-mongodb；在未来也许会考虑，增加 jpa 和 JdbcTemplate 对关系型数据库的二次封装。同时，我们在代码层面实现了数据库的读写分离。我们没有改变 MyBatis 和 spring-data-mongodb 的任何底层逻辑，本质就是 MyBaits 和 spring-data-mongodb；我们唯一做了的就是，定义和是了大家在应用程序中常用的方法，让您不在重复去编写该部分代码；以及在代码层面实现了数据的读写分离。"
        },
        {
          "title": "Dao 接口",
          "url": "/manual/2.0/dao/index.html#dao-接口",
          "content": "Dao 接口接口定义，可见：https://javadoc.io/static/com.buession/buession-dao/2.0.2/com/buession/dao/Dao.htmlpublic interface Dao {}\nP：主键类型\nE：实体类\n分页对象 com.buession.dao.Pagination 继承自 com.buession.core.Pagination，增加了偏移量属性 offset。条件为 Map 类型，允许为 null。排序为 Map 类型，允许为 null。MyBatisBuession Framework 扩展 MyBatis 的文档。MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/dao/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.0/dao/mybatis.html",
      "children": [
        {
          "title": "MyBatis",
          "url": "/manual/2.0/dao/mybatis.html#mybatis",
          "content": "MyBatisBuession Framework 扩展 MyBatis 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.0/dao/mybatis.html#mybatis-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMyBatisDao；且存在 bean 名为 masterSqlSessionTemplate、slaveSqlSessionTemplates 的 bean 实例。masterSqlSessionTemplate 操作主库，实现插入、更新、删除操作；slaveSqlSessionTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveSqlSessionTemplate() 在所有的 slave templates 中随机返回一个 slave SqlSessionTemplate bean 实例。当然，您也可以通过 getSlaveSqlSessionTemplate(final int index) 指定索引的 slave SqlSessionTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave SqlSessionTemplate bean 实例列表，将会返回 master SqlSessionTemplate bean 实例，buession framework 屏蔽了这些技术细节。"
        },
        {
          "title": "Mybatis 约定",
          "url": "/manual/2.0/dao/mybatis.html#mybatis-mybatis-约定",
          "content": "Mybatis 约定如果集成 AbstractMyBatisDao 类，必须重写方法 getStatement()，通过此方法返回每个 Mapper namespace\nnamespace com.buession.dao.test.dao;\npublic class UserDaoImpl extends AbstractMyBatisDao {\n\n\t@Override\n\tprotected String getStatement(){\n\t\treturn \"com.buession.dao.test.dao.UserMapper\";\n\t}\n\n}\n\n\nMapper 的 SQL ID 和方法名保持一致\n\n\nSQL ID\n说明\n返回值\n\n\n\n\ninsert\n插入数据\n影响的行数\n\n\nbatchInsert\n批量插入数据，默认循环插入；您可以重写该方法实现 SQL 批量插入\n每次插入影响的行数列表\n\n\nreplace\n替换数据，即：REPLACE 语句\n影响的行数\n\n\nbatchReplace\n批量替换数据，即：REPLACE 语句\n每次替换数据影响的行数列表\n\n\nupdate\n更新数据\n更新条数\n\n\nupdateByPrimary\n根据主键更新数据，注：主键参数值是会覆盖实体类主键参数对应的类属性的值\n更新条数\n\n\ngetByPrimary\n根据主键查询数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselectOne\n（根据条件）获取一条数据，SQL 层面需要保证最多只会返回一条数据\n一条数据结果\n\n\nselect\n查询数据\n数据结果列表\n\n\ngetAll\n查询所有数据\n数据结果列表\n\n\ncount\n获取记录数\n记录数\n\n\ndeleteByPrimary\n根据主键删除数据\n影响条数\n\n\ndelete\n删除数据\n影响条数\n\n\nclear\n清除数据\n影响条数\n\n\ntruncate\n截断数据\n影响条数\n\n\n注：要实现分页，必须实现 count，且和 select 的查询条件必须一致。因为，在分页方法中，首先会执行 count ，查询指定条件的记录数；如果记录数大于 0 时，才会执行 select 查询数据。在后续的开发中，我们将会使用拦截器实现。\n以上 SQL ID，只是一种约定，具体会呈现一种什么样的效果，还是完全屈居于您的 SQL 语句。\n"
        },
        {
          "title": "Mybatis 类型处理器",
          "url": "/manual/2.0/dao/mybatis.html#mybatis-mybatis-类型处理器",
          "content": "Mybatis 类型处理器MyBatis 自身提供大量优秀的类型处理器 TypeHandler，但任然不足。我们在此基础上扩展了一些 TypeHandler。名称空间为 org.apache.ibatis.type，不是 com.buession.dao。\n\nTypeHandler\n说明\n\n\n\n\nDefaultEnumTypeHandler\n默认 Enum 类型处理器，将值直接转换为枚举字段\n\n\nIgnoreCaseEnumTypeHandler\n忽略大小写 Enum 类型处理器，将值忽略大小写转换为枚举字段\n\n\nDefaultJsonTypeHandler\nJSON 处理器，将 JSON 格式的字符串值和类型  进行转换\n\n\nDefaultSetEnumTypeHandler\n默认 Enum 型 Set 类型处理器，将值直接转换为枚举字段作为 Set 的元素\n\n\nIgnoreCaseSetEnumTypeHandler\n忽略大小写 Enum 型 Set 类型处理器，将值忽略大小写转换为枚举字段作为 Set 的元素\n\n\nDefaultSetTypeHandler\n默认 Set 类型处理器，将值以 \",\" 拆分转换为 Set\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/dao/mybatis.html#mybatis-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-dao 参考手册",
      "content": "",
      "url": "/manual/2.0/dao/mongodb.html",
      "children": [
        {
          "title": "MongoDB",
          "url": "/manual/2.0/dao/mongodb.html#mongodb",
          "content": "MongoDBBuession Framework 扩展 spring-data-mongodb 的文档。"
        },
        {
          "title": "读写分离",
          "url": "/manual/2.0/dao/mongodb.html#mongodb-读写分离",
          "content": "读写分离要从代码层面实现读写分离，必须继承 AbstractMongoDBDao；且存在 bean 名为 masterMongoTemplate、slaveMongoTemplates 的 bean 实例。masterMongoTemplate 操作主库，实现插入、更新、删除操作；slaveMongoTemplates 操作从库，实现查询操作。默认查询操作，会通过方法 getSlaveMongoTemplate() 在所有的 slave templates 中随机返回一个 MongoTemplate bean 实例。当然，您也可以通过 getSlaveMongoTemplate(final int index) 指定索引的 slave MongoTemplate bean 实例（当然，我们不建议您这么做）。如果没有指定 slave MongoTemplate bean 实例列表，将会返回 master MongoTemplate bean 实例，buession framework 屏蔽了这些技术细节。AbstractMongoDBDao 的 replace 执行的也是 insert。在对 MongoDB 的操作条件中 value 可以为 com.buession.dao.MongoOperation，可以通过该类的方法控制条件等于值、大于值、小于值、LIKE值 等等运算。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/dao/mongodb.html#mongodb-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-geoip 参考手册",
      "content": "对 com.maxmind.geoip2:geoip2 进行二次封装，实现支持根据 IP 地址获取所属 ISP、所属国家、所属城市等等信息。",
      "url": "/manual/2.0/geoip/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/geoip/index.html#安装",
          "content": "安装    com.buession\n    buession-geoip\n    x.x.x\n\n通常我们在应用中对用户注册、登录、以及其它操作记录 IP，我们更希望知道用户在什么城市进行的操作，如：微信公众号的内容发表于、微博的发布于等等，对于用户行为的安全审计等等有着极高的作用。geoip 在基于 maxmind geoip2 的基础上进行了二次封装，可以根据 IP（字符串形式的 IP，如：114.114.114.114、2001:0DB8:0000:0023:0008:0800:200C:417A ，IPV4 的数字表示：3739974408，java InetAddress）获取其 IP 地址的国家信息、城市信息、位置信息。"
        },
        {
          "title": "获取国家信息",
          "url": "/manual/2.0/geoip/index.html#获取国家信息",
          "content": "获取国家信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nCountry country = resolver.country(\"114.114.114.114\");\n// Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}\n\nCountry country = resolver.country(3739974408L); // 3739974408L => 222.235.123.8\n// Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}\n"
        },
        {
          "title": "获取城市信息",
          "url": "/manual/2.0/geoip/index.html#获取城市信息",
          "content": "获取城市信息import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nDistrict district = resolver.district(\"114.114.114.114\");\n// District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}\n\nDistrict district = resolver.district(3739974408L); // 3739974408L => 222.235.123.8\n// District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}\n"
        },
        {
          "title": "获取位置信息",
          "url": "/manual/2.0/geoip/index.html#获取位置信息",
          "content": "获取位置信息位置信息中包括了该 IP 比较全面的信息，包括：城市信息、国家信息、洲信息、经纬度、机构信息、时区等。import com.buession.geoip.model.Country;import com.buession.geoip.model.DatabaseResolver;\n\nDatabaseResolver resolver = new DatabaseResolver(DatabaseResolver.class.getResourceAsStream(\"/maxmind/City.mmdb\"));\nLocation location = resolver.location(\"114.114.114.114\");\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1814991, confidence=null, code='CN', originalName='China', name='中国', fullName='中华人民共和国', isInEuropeanUnion=false}, district=District{geoNameId=1799962, code=null, originalName='Nanjing', name='南京', fullName='江苏省南京', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省江苏省', postal=null, parent=District{geoNameId=1806260, code=null, originalName='Jiangsu', name='江苏省', fullName='江苏省', postal=null, parent=null}}}, traits=Traits{ipAddress='114.114.114.114', domain='null', isp='null', network=114.114.0.0/16, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=32.0617, longitude=118.7778, accuracyRadius=50}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Shanghai\",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null]}\n\nLocation location = resolver.location(3739974408L); // 3739974408L => 222.235.123.8\n// Location{continent=Continent{geoNameId=6255147, code='AS', originalName='Asia', name='亚洲'}, country=Country{geoNameId=1835841, confidence=null, code='KR', originalName='Republic of Korea', name='大韩民国', fullName='大韩民国', isInEuropeanUnion=false}, district=District{geoNameId=1835848, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=Postal{code='null', confidence=null}, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市首尔特别市', postal=null, parent=District{geoNameId=1835847, code=null, originalName='Seoul', name='首尔特别市', fullName='首尔特别市', postal=null, parent=null}}}, traits=Traits{ipAddress='222.235.123.8', domain='null', isp='null', network=222.235.120.0/21, connectionType=null, organization=null, autonomousSystemOrganization=null, autonomousSystemNumber=null, isAnonymous=false, isAnonymousProxy=false, isAnonymousVpn=false, isHostingProvider=false, isLegitimateProxy=false, isPublicProxy=false, isSatelliteProvider=false, isTorExitNode=false, userType='false', userCount=null, staticIpScore=null}, geo=Geo{latitude=37.5111, longitude=126.9743, accuracyRadius=200}, timeZone=sun.util.calendar.ZoneInfo[id=\"Asia/Seoul\",offset=32400000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null]}\n"
        },
        {
          "title": "缓存",
          "url": "/manual/2.0/geoip/index.html#缓存",
          "content": "缓存为了提高数据的处理能力，可以将获取过的数据缓存起来，下次获取同一 IP 信息时，可以直接从缓存中返回。您可以通过 DatabaseResolver 构造函数中的参数 cache 设置为 com.maxmind.db.NodeCache 的实现类即可，或直接使用类 CacheDatabaseResolver解析。我们默认使用 maxmind 内置的 CHMCache 来实现缓存，它是基于 ConcurrentHashMap 的内存缓存。"
        },
        {
          "title": "Resolver 的 Spring Factory Bean",
          "url": "/manual/2.0/geoip/index.html#resolver-的-spring-factory-bean",
          "content": "Resolver 的 Spring Factory Bean我们内置了 geoip 的 Resolver spring factory bean 类 GeoIPResolverFactoryBean，您可以通过它在您的 spring 项目中，初始化 Resolver 的实现类为 spring bean 对象。dbPath 和 stream 二选一即可，一个是指定 IP 的文件路径，一个是指定已加载的 IP 库的文件流。都不设置的默认以流的形式加载 buession-geoip 中的 IP 库文件。\nenableCache 可以控制是否缓存。\n"
        },
        {
          "title": "关于 IP 库",
          "url": "/manual/2.0/geoip/index.html#关于-ip-库",
          "content": "关于 IP 库buession-geoip 中包含了 maxmind 免费的 IP 所属城市和国家的库。由于在 jar 包中该数据库无法做到及时更新，在实际应用中，我们建议您从 maxmind 官网下载 IP 方法您的应用中，通过 DatabaseResolver 的构造函数指定 IP 库路径，这么做的好处是：在您的应用程序中，可以去保证 IP 库是更新的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/geoip/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "对 apache httpcomponents、okhttp3 进行封装，屏蔽了 apache httpcomponents 和 okhttp3 的不同技术细节，屏蔽了对 post form、post json 等等的技术细节。",
      "url": "/manual/2.0/httpclient/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/httpclient/index.html#安装",
          "content": "安装    com.buession\n    buession-httpclient\n    x.x.x\n\n我们在应用中使用 Http Client 功能时，经常因为从 apache httpcomponents 切换为 okhttp3，或者从 okhttp3 切换为 apache httpcomponents，需要改动大量的代码而烦恼。而当您使用了 buession-httpclient 时，该类库为您解决了这些烦恼，通过顶层设计，屏蔽了 apache httpcomponents 和 okhttp3 的细节，当您需要从一个 http 库更换为另外一个 http 库时，您只需要在 pom.xml 中引用不同的包，修改一下 httpclient 的初始化类和连接管理器即可。传统的方式：    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\nimport org.apache.http.HttpResponse;import org.apache.http.conn.HttpClientConnectionManager;\nimport org.apache.http.client.HttpClient;\nimport org.apache.http.impl.client.HttpClientBuilder;\nimport org.apache.http.client.methods.HttpPost;\n\nHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(new HttpClientConnectionManager()).build();\n\nHttpResponse response = httpClient.execute(new HttpPost(\"https://www.buession.com/\"));\n或者    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport okhttp3.HttpClientConnectionManager;import okhttp3.OkHttpClient;\nimport okhttp3.ConnectionPool;\nimport okhttp3.Request;\nimport okhttp3.Request.Builder;\nimport okhttp3.Response;\n\nOkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(new ConnectionPool());\nHttpClient httpClient = builder.build();\n\nBuilder requestBuilder = new Builder().post();\nrequestBuilder.url(\"https://www.buession.com/\");\nRequest okHttpRequest = requestBuilder.build();\n\nResponse httpResponse = httpClient.newCall(okHttpRequest).execute();\n现在，您只需要通过 buession-httpclient，即可屏蔽其中的细节。    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpcore\n    x.x.x\n\n\n    org.apache.httpcomponents\n    httpclient\n    x.x.x\n\n或者    com.buession\n    buession-httpclient\n    x.x.x\n\n\n    com.squareup.okhttp3\n    okhttp\n    x.x.x\n\nimport com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.OkHttpHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.conn.OkHttpClientConnectionManager;\nimport com.buession.httpclient.core.Response;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager()); // 或者 new OkHttpHttpClient(new OkHttpClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\n"
        },
        {
          "title": "展望",
          "url": "/manual/2.0/httpclient/index.html#展望",
          "content": "展望目前，buession-httpclient 仅支持同步，不支持异步。我们会在下一个小版本（即：2.1） 中，集成 apache httpcomponents 切换为 okhttp3 的异步 http 请求。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/httpclient/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.0/httpclient/configuration.html",
      "children": [
        {
          "title": "连接配置",
          "url": "/manual/2.0/httpclient/configuration.html#连接配置",
          "content": "连接配置您可以通过连接配置类 Configuration 配置 apache httpcomponents 和 okhttp3 的链接配置属性，buession-httpclient 内部会自动将 Configuration 的配置信息，转换为 apache httpcomponents 或 okhttp3 的配置信息。"
        },
        {
          "title": "配置属性说明",
          "url": "/manual/2.0/httpclient/configuration.html#连接配置-配置属性说明",
          "content": "配置属性说明\n\n属性名称\n数据类型\napache httpcomponents 对应配置\nokhttp3 对应配置\n默认值\n说明\n\n\n\n\nmaxConnections\nint\nmaxTotal\nmaxIdleConnections\n5000\n最大连接数\n\n\nmaxPerRoute\nint\ndefaultMaxPerRoute\n--\n500\n每个路由的最大连接数\n\n\nidleConnectionTime\nint\ncloseIdleConnections\nkeepAliveDuration\n60000\n空闲连接存活时长（单位：毫秒）\n\n\nconnectTimeout\nint\nconnectTimeout\nconnectTimeout\n3000\n连接超时时间（单位：毫秒）\n\n\nconnectionRequestTimeout\nint\nconnectionRequestTimeout\n--\n5000\n从连接池获取连接的超时时间（单位：毫秒）\n\n\nreadTimeout\nint\nsocketTimeout\nreadTimeout\n5000\n读取超时时间（单位：毫秒）\n\n\nallowRedirects\nBoolean\nredirectsEnabled\nfollowRedirects\n--\n是否允许重定向\n\n\nrelativeRedirectsAllowed\nBoolean\nrelativeRedirectsAllowed\n--\n--\n是否应拒绝相对重定向\n\n\ncircularRedirectsAllowed\nBoolean\ncircularRedirectsAllowed\n--\n--\n是否允许循环重定向\n\n\nmaxRedirects\nInteger\nmaxRedirects\n--\n--\n最大允许重定向次数\n\n\nauthenticationEnabled\nboolean\nauthenticationEnabled\n--\n--\n是否开启 Http Basic 认证\n\n\ncontentCompressionEnabled\nboolean\ncontentCompressionEnabled\n--\n--\n是否启用内容压缩\n\n\nnormalizeUri\nboolean\nnormalizeUri\n--\n--\n是否标准化 URI\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/httpclient/configuration.html#连接配置-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.0/httpclient/connectionmanager.html",
      "children": [
        {
          "title": "连接管理器",
          "url": "/manual/2.0/httpclient/connectionmanager.html#连接管理器",
          "content": "连接管理器连接管理器是用来管理 HTTP 连接的，包括设置连接配置、控制连接池。关于更多的连接池，可以参考 apache httpcomponents 和 okhttp3 的文档。您可以通过无参数的构造函数来创建连接管理器，也可以通过构造函数参数仅为 com.buession.httpclient.core.Configuration 来创建连接管理器，也可以构造函数通过 apache httpcomponents 或 okhttp3 原生的连接管理器类创建（此时，Configuration 的配置不任何意义，他不会修改您通过原生连接管理器实例中的参数配置）。"
        },
        {
          "title": "关于 okhttp 连接管理器",
          "url": "/manual/2.0/httpclient/connectionmanager.html#连接管理器-关于-okhttp-连接管理器",
          "content": "关于 okhttp 连接管理器okhttp3 本身是没有类似 apache httpcomponents 的链接管理器 org.apache.http.conn.HttpClientConnectionManager 的，我们为了在 buession-httpclient 的链接管理器实现 com.buession.httpclient.conn.OkHttpClientConnectionManager 保持一致，人为的加了一层 okhttp3 的链接管理器 okhttp3.HttpClientConnectionManager（注意：命名空间为 okhttp3），主要用于初始化连接池类 okhttp3.ConnectionPool。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/httpclient/connectionmanager.html#连接管理器-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.0/httpclient/response.html",
      "children": [
        {
          "title": "响应",
          "url": "/manual/2.0/httpclient/response.html#响应",
          "content": "响应当通过 HttpClient 发起任意请求后，将得到一个 Response。此结果，包括了：协议及其版本、状态码及其信息、响应头列表、响应体、以及响应体长度。buession-httpclient 会将 apache httpcomponents 或 okhttp3 的响应对象，转换为 Response。需要注意的是，原生 apache httpcomponents 或 okhttp3 响应体流，一旦被使用过一次之后，将不能再使用；同时您只能以流或者字符串二选一的形式获取响应体。但是，在 buession-httpclient 中您将可以二者兼顾，当然这也同时会带来一些性能消耗和内存占用。import com.buession.httpclient.HttpClient;import com.buession.httpclient.ApacheHttpClient;\nimport com.buession.httpclient.conn.ApacheClientConnectionManager;\nimport com.buession.httpclient.core.Response;\nimport java.io.InputStream;\n\nHttpClient httpClient = new ApacheHttpClient(new ApacheClientConnectionManager());\n\nResponse response = httpClient.post(\"https://www.buession.com/\");\nInputStream stream = response.getInputStream(); // 以流的形式获取响应体\nString body = response.getBody(); // 以字符串的形式获取响应体\n\nstream.close();\ngetInputStream、getBody 二者可以重复调用，当时您需要始终手动关闭一下流，因为这将是拷贝的原生 apache httpcomponents 或 okhttp3 返回的流。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/httpclient/response.html#响应-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-httpclient 参考手册",
      "content": "",
      "url": "/manual/2.0/httpclient/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.0/httpclient/method.html#方法",
          "content": "方法buession-httpclient 提供了和 HTTP 请求方式同名的方法 API，您可以很方便的通过提供的方法发起 HTTP 请求。示例：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\");\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\");\n您可以自定义请求头：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.List;\nimport java.util.ArrayList;\n\nList headers = new ArrayList();\n\nheaders.add(new Header(\"X-SDK-NAME\", \"Buession\"));\nheaders.add(new Header(\"X-Timestamp\", System.currentTimeMillis()));\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", headers);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", headers);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", headers);\n您可以设置请求参数：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport java.util.Map;\nimport java.util.HashMap;\n\nMap parameters = new HashMap();\n\nparameters.put(\"action\", \"edit\");\nparameters.put(\"id\", 1);\n\n// GET 请求\nResponse response = httpClient.get(\"https://www.buession.com/\", parameters);\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", parameters);\n\n// HEAD 请求\nResponse response = httpClient.head(\"https://www.buession.com/\", parameters);\n您可以设置请求体：import com.buession.httpclient.HttpClient;import com.buession.httpclient.core.Response;\nimport com.buession.httpclient.core.Header;\nimport jcom.buession.httpclient.core.RequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\nimport jcom.buession.httpclient.core.EncodedFormRequestBody;\n\nEncodedFormRequestBody requestBody = new EncodedFormRequestBody();\n\nrequestBody.addRequestBodyElement(\"username\", \"buession\");\nrequestBody.addRequestBodyElement(\"password\", \"buession\");\n\n// POST 请求\nResponse response = httpClient.post(\"https://www.buession.com/\", requestBody);\n\nJsonRawRequestBody requestBody = new JsonRawRequestBody(new User());\n// PUT 请求\nResponse response = httpClient.put(\"https://www.buession.com/\", requestBody);\n不同的 RequestBody，决定了我们以什么样的 Content-Type 提交数据，buession-httpclient 中提供了大量的内置 RequestBody。"
        },
        {
          "title": "RequestBody",
          "url": "/manual/2.0/httpclient/method.html#方法-requestbody",
          "content": "RequestBody\n\nRequestBody\nContent-Type\n说明\n\n\n\n\nInputStreamRequestBody\napplication/octet-stream\n二进制请求体\n\n\nChunkedInputStreamRequestBody\napplication/octet-stream\nChunked 二进制请求体\n\n\nRepeatableInputStreamRequestBody\napplication/octet-stream\nRepeatable 二进制请求体\n\n\nEncodedFormRequestBody\napplication/x-www-form-urlencoded\n普通表单请求体\n\n\nMultipartFormRequestBody\nmultipart/form-data\n文件上传表单请求体\n\n\nHtmlRawRequestBody\ntext/html\nHTML 请求体\n\n\nJavaScriptRawRequestBody\napplication/javascript\nJavaScript 请求体\n\n\nJsonRawRequestBody\napplication/json\nJSON 请求体\n\n\nTextRawRequestBody\ntext/plain\nTEXT 请求体\n\n\nXmlRawRequestBody\ntext/xml\nXML 请求体\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/httpclient/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-io 参考手册",
      "content": "封装了对文件的操作",
      "url": "/manual/2.0/io/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/io/index.html#安装",
          "content": "安装    com.buession\n    buession-io\n    x.x.x\n\n该模块二次封装了 java java.io.File 和 java.nio.file.Files 类，在此基础上扩展了大量的实用方法，如：文件读写、获取文件 MD5 和 SHA1 值，获取文件 MIME，设置文件所属用户和用户组，简化了我们在应用开发过程中对文件的操作。"
        },
        {
          "title": "读取文件",
          "url": "/manual/2.0/io/index.html#读取文件",
          "content": "读取文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nbyte[] result = file.read();\n"
        },
        {
          "title": "写文件",
          "url": "/manual/2.0/io/index.html#写文件",
          "content": "写文件import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nfile.write(\"Buession\");\nfile.write(\"Buession\".getBytes());\nfile.write(\"Buession\", true); // 追加写\n"
        },
        {
          "title": "获取文件 MD5、SHA-1值",
          "url": "/manual/2.0/io/index.html#获取文件-md5、sha-1值",
          "content": "获取文件 MD5、SHA-1值import com.buession.io.file.File;\nFile file = new File(\"/tmp/debug.txt\");\n\nString md5 = file.getMd5(); // 获取文件 MD5\nString sha1 = file.getSha1(); // 获取文件 SHA-1\n"
        },
        {
          "title": "获取文件 MD5、SHA-1 值",
          "url": "/manual/2.0/io/index.html#获取文件-md5、sha-1-值",
          "content": "获取文件 MD5、SHA-1 值import com.buession.io.file.File;import com.buession.io.MimeType;\n\nFile file = new File(\"/tmp/debug.txt\");\n\nMimeType result = file.getMimeType();\n"
        },
        {
          "title": "设置文件权限",
          "url": "/manual/2.0/io/index.html#设置文件权限",
          "content": "设置文件权限import com.buession.io.file.Files;\nFiles.chmod(\"/tmp/debug.txt\", 0777);\n"
        },
        {
          "title": "设置文件用户组",
          "url": "/manual/2.0/io/index.html#设置文件用户组",
          "content": "设置文件用户组import com.buession.io.file.Files;\nFiles.chgrp(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "设置文件用户",
          "url": "/manual/2.0/io/index.html#设置文件用户",
          "content": "设置文件用户import com.buession.io.file.Files;\nFiles.chown(\"/tmp/debug.txt\", \"root\");\n"
        },
        {
          "title": "注解",
          "url": "/manual/2.0/io/index.html#注解",
          "content": "注解注解 com.buession.io.json.annotation.MimeTypeString 可以将类型为 com.buession.io.MimeType 的字段序列化为字符串和将字符串反序列化为 com.buession.io.MimeType，该功能是基于 jackson 实现的。import com.buession.io.json.annotation.MimeTypeString;\nclass File {\n\n    @MimeTypeString\n    private MimeType mime;\n\n}\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/io/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-jdbc 参考手册",
      "content": "JDBC 通用 POJO 类定义，对 Hikari、Dbcp2、Druid 等配置和数据源的封装。",
      "url": "/manual/2.0/jdbc/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/jdbc/index.html#安装",
          "content": "安装    com.buession\n    buession-jdbc\n    x.x.x\n\n通过提供的 API，您可以简化对 DBCP2、Druid、Hikari、Tomcat 数据源的初始化，该类库基本不单独使用。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/jdbc/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-json 参考手册",
      "content": "主要实现了一些 jackson 的自定义注解及序列化、反序列化的实现。",
      "url": "/manual/2.0/json/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/json/index.html#安装",
          "content": "安装    com.buession\n    buession-json\n    x.x.x\n\n封装了大量基于 jackson 的注解。"
        },
        {
          "title": "注解",
          "url": "/manual/2.0/json/index.html#注解",
          "content": "注解\n\n注解\n说明\n\n\n\n\nCalendarUnixTimestamp\njava.util.Calendar 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Calendar 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Calendar\n\n\nDateUnixTimestamp\njava.util.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.util.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.util.Date\n\n\nSqlDateUnixTimestamp\njava.sql.Date 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Date 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Date\n\n\nTimestampUnixTimestamp\njava.sql.Timestamp 和 Unix 时间戳序列化、反序列化；通过该注解，可以将 java.sql.Timestamp 序列化为 Unix 时间戳；将 Unix 时间戳反序列化为 java.sql.Timestamp\n\n\nJsonEnum2Map\n枚举和 java.util.Map 序列化和反序列化；通过该注解，可以将枚举序列化为 java.util.Map；将 java.util.Map 反序列化为枚举\n\n\nSensitive\n通过该注解可以实现数据的脱敏\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/json/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-lang 参考手册",
      "content": "常用 POJO 类和枚举的定义，详细查看 API 参考手册。",
      "url": "/manual/2.0/lang/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/lang/index.html#安装",
          "content": "安装    com.buession\n    buession-lang\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/lang/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-net 参考手册",
      "content": "网络相关工具类。",
      "url": "/manual/2.0/net/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/net/index.html#安装",
          "content": "安装    com.buession\n    buession-net\n    x.x.x\n\n"
        },
        {
          "title": "IP 地址工具类",
          "url": "/manual/2.0/net/index.html#ip-地址工具类",
          "content": "IP 地址工具类IP 地址工具类 com.buession.net.utils.InetAddressUtis 实现了，IPV4 地址和数字型 IP 相互转换。import com.buession.net.utils.InetAddressUtis;\nlong result = InetAddressUtis.ip2long(\"127.0.0.1\"); // 2130706433\nString ip = InetAddressUtis.long2ip(2130706433L); // 127.0.0.1\nURI 类或 URIBuilder 类，实现了 url 字符串的构建，详细查看 API 手册。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/net/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "Redis 操作类，基于 jedis 实现，RedisTemplate 方法名、参数几乎同 redis 原生命令保持一致。同时，对对象读写 redis 进行了扩展，支持二进制、json方式序列化和反序列化，例如：通过 RedisTemplate.getObject(“key”, Object.class) 可以将 redis 中的数据读取出来后，直接反序列化成一个对象。",
      "url": "/manual/2.0/redis/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/redis/index.html#安装",
          "content": "安装    com.buession\n    buession-redis\n    x.x.x\n\n"
        },
        {
          "title": "介绍",
          "url": "/manual/2.0/redis/index.html#介绍",
          "content": "介绍buession-redis 是一款基于 jedis 的 redis 操作库，最大的优势就是封装了与 redis 同名、最大程度与 redis 原生参数顺序一致的 API。同时，我们在现代应用中，经常需要读写一个 pojo 对象，buession-redis 封装了 xxxObject 读写取 redis 中的二进制或 JSON 数据，并反序列化为 POJO 类。大大简化了，我们在代码中对象存取到 redis 中，让我们更专注业务功能的开。能够通过 com.buession.redis.core.Options 设置全局选项，如：统一的 Key 前缀，对象基于什么方式序列化和反序列化。import com.buession.redis.RedisTemplate;import com.buession.redis.core.Options;\nimport com.buession.core.serializer.type.TypeReference;\nimport java.utils.Map;\nimport java.utils.HashMap;\n\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.setOptions(new Options());\nredisTemplate.afterPropertiesSet();\n\n// 将 User 对象写进 key 为 user hash 中\nredisTemplate.hSet(\"user\", \"1\", new User());\n\n// 获取 key 为 user ，field 为 1 的 hash 中的数据，并转换为 User\nUser user = redisTemplate.hGetObject(\"user\", \"1\", User.class);\n\n// 获取 key 为 user 的 hash 的所有数据，并将值转换为 User\nMap data = redisTemplate.hGetAllObject(\"user\", \"1\", new TypeReference>{});\n"
        },
        {
          "title": "展望",
          "url": "/manual/2.0/redis/index.html#展望",
          "content": "展望目前，buession-redis 仅支持 jedis，不支持 lettuce，我们预计会在下个版本或者下下个版本（即：2.1 或者 2.2）中计划加入。其实，之前尝试过，但由于两者 API 差异性和使用方式太大，没法很好的做到统一化，就暂时放弃了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/redis/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.0/redis/datasource.html",
      "children": [
        {
          "title": "数据源",
          "url": "/manual/2.0/redis/datasource.html#数据源",
          "content": "数据源buession-redis 基于数据源 DataSource 连接 redis，其机制类似 JDBC 的 DataSource。通过，数据源可以配置，redis 的用户名、密码、连接超时、读取超时、连接池、SSL等等。数据源 DataSource 包括三个子接口：StandaloneDataSource：单机模式数据源\nSentinelDataSource：哨兵模式数据源\nClusterDataSource：集群模式数据源\njedis 和后续的 lettuce 分别实现这三个接口，用于创建不通模式的数据源，数据源中实现了连接池的创建。在原始的 jedis 或者 spring-data-redis 中，密码为空字符串时，会以空字符串密码进行登录 redis；我们修改了这一逻辑，不管您在程序中密码是设置的 null 还是空字符串，我们都会跳过认证。这样的好处就是，假设您的开发环境 redis 没有设置密码，生产环境设置了密码，我们可以通过一个 bean 初始化即可，不用写成两个 bean。测试环境 properties：redis.host=127.0.0.1redis.port=6379\nredis.password=\n生产环境 properties：redis.host=192.168.100.131redis.port=6379\nredis.password=passwd\n"
        },
        {
          "title": "连接池",
          "url": "/manual/2.0/redis/datasource.html#数据源-连接池",
          "content": "连接池通过连接池管理 redis 连接，能够大大的提高效率和节约资源的使用。jedis 和 lettuce 均使用 apache commons-pool2 来创建和维护连接池。但是，在 jedis 中，以 JedisPoolConfig 和 ConnectionPoolConfig 来管理单例模式连接池、哨兵模式连接池和集群模式连接池；为了简化配置，我们定义了 com.buession.redis.core.PoolConfig 来统一维护各种模式的连接池配置，然后在各 DataSource 中转换为原生的连接池配置，极大的简化了学习和替换成本。连接池配置\n\n配置项\n数据类型\n-- 默认值\n说明\n\n\n\n\nlifo\nboolean\nGenericObjectPoolConfig.DEFAULT_LIFO\n池模式，为 true 时，后进先出；为 false 时，先进先出\n\n\nfairness\nboolean\nGenericObjectPoolConfig.DEFAULT_FAIRNESS\n当从池中获取资源或者将资源还回池中时，是否使用 java.util.concurrent.locks.ReentrantLock 的公平锁机制\n\n\nmaxWait\nDuration\nGenericObjectPoolConfig.DEFAULT_MAX_WAIT\n当连接池资源用尽后，调用者获取连接时的最大等待时间\n\n\nminEvictableIdleTime\nDuration\n60000\n连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除\n\n\nsoftMinEvictableIdleTime\nDuration\nGenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION\n连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数\n\n\nevictionPolicyClassName\nString\nGenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME\n驱逐策略的类名\n\n\nevictorShutdownTimeout\nDuration\nGenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT\n关闭驱逐线程的超时时间\n\n\nnumTestsPerEvictionRun\nint\n-1\n检测空闲对象线程每次运行时检测的空闲对象的数量\n\n\ntestOnCreate\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE\n在创建对象时检测对象是否有效，配置 true 会降低性能\n\n\ntestOnBorrow\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW\n在从对象池获取对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestOnReturn\nboolean\nGenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN\n在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能\n\n\ntestWhileIdle\nboolean\ntrue\n在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\nblockWhenExhausted\nboolean\nGenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED\n当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效；false 连接池没有资源立马抛异常）\n\n\ntimeBetweenEvictionRuns\nint\n30000\n空闲连接检测的周期，如果为负值，表示不运行检测线程\n\n\njmxEnabled\nboolean\nGenericObjectPoolConfig.DEFAULT_JMX_ENABLE\n是否注册 JMX\n\n\njmxNamePrefix\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX\nJMX 前缀\n\n\njmxNameBase\nString\nGenericObjectPoolConfig.DEFAULT_JMX_NAME_BASE\n使用 base + jmxNamePrefix + i 来生成 ObjectName\n\n\nmaxTotal\nint\nGenericObjectPoolConfig.DEFAULT_MAX_TOTAL\n最大连接数\n\n\nminIdle\nint\nGenericObjectPoolConfig.DEFAULT_MIN_IDLE\n最小空闲连接数\n\n\nmaxIdle\nint\nGenericObjectPoolConfig.DEFAULT_MAX_IDLE\n最大空闲连接数\n\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/redis/datasource.html#数据源-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-redis 参考手册",
      "content": "",
      "url": "/manual/2.0/redis/method.html",
      "children": [
        {
          "title": "方法",
          "url": "/manual/2.0/redis/method.html#方法",
          "content": "方法buession-redis BaseRedisTemplate 的方法以及参数计划与原生 redis 命名保持一致。复杂的参数会通过 Builder 进行参数构建，在多个值中进行选择的将定义成枚举，规避出错的几率。import com.buession.redis.BaseRedisTemplate;\nBaseRedisTemplate redisTemplate = new BaseRedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 删除哈希表 key 中的一个或多个指定域\nredisTemplate.hDel(\"user\", \"1\", \"2\", \"3\");\n\n// 检查给定 key 是否存在\nredisTemplate.exists(\"user\");\n\n// 获取列表 key 中，下标为 index 的元素\nredisTemplate.lIndex(\"user\", 1);\n\n// 如果键 key 已经存在并且它的值是一个字符串，将 value 追加到键 key 现有值的末尾\nredisTemplate.append(\"key\", \"value 1\");\nBaseRedisTemplate 实现了 redis 的原生操作，RedisTemplate 继承了 BaseRedisTemplate ，在此基础上实现了将 redis 中的二进制或者 JSON 格式的值，反序列化为一个类。import com.buession.redis.RedisTemplate;\nRedisTemplate redisTemplate = new RedisTemplate(dataSource);\n\nredisTemplate.afterPropertiesSet();\n\n// 获取列表 key 中，下标为 index 的元素，并反序列化为 User 类\nUser user = redisTemplate.lIndexObject(\"user\", 1, User.class);\n序列化和反序列化，基于 buession-core 序列化和反序列化 扩展而来，序列化或反序列化出错时会直接返回 null，而忽略异常，默认使用 com.buession.redis.serializer.JacksonJsonSerializer 序列化为 JSON。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/redis/method.html#方法-api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-session 参考手册",
      "content": "无文档",
      "url": "/manual/2.0/session/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/session/index.html#安装",
          "content": "安装    com.buession\n    buession-session\n    x.x.x\n\n该模块无实际意义，将在今后的版本中会删除掉。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/session/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-thesaurus 参考手册",
      "content": "对词库的解析，目前仅支持搜狗词条。",
      "url": "/manual/2.0/thesaurus/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/thesaurus/index.html#安装",
          "content": "安装    com.buession\n    buession-thesaurus\n    x.x.x\n\n您可以通过该库解析搜狗拼音的词条库，包括词条、拼音信息。import com.buession.thesaurus.SogouParser;import com.buession.thesaurus.Parser;\nimport com.buession.thesaurus.core.Word;\nimport java.util.Set;\n\nParser parser = new SogouParser();\n\nSet words parser.parse(\"搜谱拼音词条文件路径\");\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/thesaurus/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-velocity 参考手册",
      "content": "spring mvc 不再支持 velocity，这里主要是把原来 spring mvc 中关于 velocity 的实现迁移过来，让喜欢 velocity 的 coder 继续在高版本的 springframework 中继续使用 velocity。",
      "url": "/manual/2.0/velocity/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/velocity/index.html#安装",
          "content": "安装    com.buession\n    buession-velocity\n    x.x.x\n\n该类库，基本照搬了 springframework 集成 velocity 的代码和逻辑。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/velocity/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "web 相关的功能封装，支持 servlet 和 reactive；封装了一些常用注解，简化了业务层方面的代码实现；封装了一些常用 filter。",
      "url": "/manual/2.0/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/web/index.html#安装",
          "content": "安装    com.buession\n    buession-web\n    x.x.x\n\nbuession-web 扩展了 spring-webmvc、spring-webflux；在此基础上，提供了大量的实用的 filter 和注解，以及工具类。该模块无论封装的 filter、注解、工具类，均适用于 spring mvc，也适用于 spring webflux。当时，filter、工具类均为 servlet 和 webflux 各自独立的类，不是说同一个类，即能用于 servlet，也能用于 webflux，当然注解是通用的。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.0/web/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/2.0/web/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了一些我们在日常应用开发过程中能用到的实用性功能，简化了您在代码开发中的便捷度，让您能够更专注业务的开发。"
        },
        {
          "title": "注解",
          "url": "/manual/2.0/web/annotation.html#注解-注解",
          "content": "注解\n\n注解\nRequest / Response\n作用域\n说明\n\n\n\n\n@RequestClientIp\nrequest\n方法参数\n获取当前请求的客户端 IP 地址\n\n\n@ContentType\nresponse\n类、方法\n设置响应 Content-Type\n\n\n@HttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值\n\n\n@DisableHttpCache\nresponse\n类、方法\n设置响应缓存头 Cache-Control、Expires、Pragma 值，禁止缓存\n\n\n@ResponseHeader\nresponse\n类、方法\n设置响应头\n\n\n@ResponseHeaders\nresponse\n类、方法\n批量设置响应头\n\n\n@DocumentMetaData\nresponse\n类、方法\n设置页面标题、页面编码、关键字、描述、版权等等元信息\n\n\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.0/web/filter.html",
      "children": [
        {
          "title": "过滤器",
          "url": "/manual/2.0/web/filter.html#过滤器",
          "content": "过滤器我们封装了一些使用的过滤器，简化了您的开发或者应用运行的跟踪。servlet 包位于 com.buession.web.servlet.filter，webflux 包位于 com.buession.web.reactive.filter，均有同样类名的过滤器类。"
        },
        {
          "title": "过滤器",
          "url": "/manual/2.0/web/filter.html#过滤器-过滤器",
          "content": "过滤器\n\n过滤器\n说明\n\n\n\n\nMobileFilter\n当前请求是否为移动设备\n\n\nPoweredByFilter\nPowered By 过滤器\n\n\nPrintUrlFilter\n打印当前请求 URL 过滤器\n\n\nResponseHeaderFilter\n响应头过滤器，设置响应头\n\n\nResponseHeadersFilter\n响应头过滤器，批量设置响应头\n\n\nServerInfoFilter\nServer 信息过滤器，通过响应头的形式，输出当前服务器名称，可以用于集群环境定位出现问题的节点\n\n\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.0/web/restful.html",
      "children": [
        {
          "title": "RESTFUL",
          "url": "/manual/2.0/web/restful.html#restful",
          "content": "RESTFULRestful 是当今比较流行的一种架构的规范与约束、原则，基于这个风格设计的软件可以更简洁、更有层次。我们遵循 REST 规范，在代码层面规范好了新增、修改、详情、删除等基本的路由，您的控制器层只需要继承 com.buession.web.servlet.mvc.controller.AbstractBasicRestController 或者 com.buession.web.reactive.mvc.controller.AbstractBasicRestController 即可在 servlet 或 webflux 模式下，实现标准的 REST 风格的代码。简化了您的代码（主要是不用再写 @RequestMapping）和标准化了。@RestController@RequestMapping(path = \"/example\")\npublic class ExampleController extends AbstractRestController {\n\n\t@Override\n\tpublic Response add(HttpServletRequest request, HttpServletResponse response, @RequestBody ExampleDto example){\n\t\t\n\t}\n\n\t@Override\n\tpublic Response edit(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id, @RequestBody ExampleDto example){\n\n\t}\n\n\t@Override\n\tpublic Response detail(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\n\t}\n\n\t@Override\n\tpublic Response delete(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = \"id\") Integer id){\n\t\t\n\t}\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-web 参考手册",
      "content": "",
      "url": "/manual/2.0/web/utils.html",
      "children": [
        {
          "title": "工具",
          "url": "/manual/2.0/web/utils.html#工具",
          "content": "工具我们封装了一些 web 相关的工具类，用于处理 request、response。servlet 包位于 com.buession.web.servlet.http，webflux 包位于 com.buession.web.reactive.http，均有同样类名的过滤器类。获取客户端真实 IP 地址：RequestUtils.getClientIp(request);我们兼容了，通过微信和一些 CDN 厂商获取用户真实 IP 的头信息，我们优先获取从微信透传过来的用户的真实 IP，然后再是各 CDN 厂商的用户真实 IP 头，最后才是标准的真实 IP 头。当然，我们不能保证是否是伪造的。优先顺序：X-Forwarded-For-Pound（微信） > Ali-Cdn-Real-Ip（阿里云 CDN） > Cdn-Src-Ip（网宿） > X-Cdn-Src-Ip（网宿） > X-Original-Forwarded-For（天翼云） > X-Forwarded-For > X-Real-Ip > Proxy-Client-IP > WL-Proxy-Client-IP > Real-ClientIP > remote addr是否是 Ajax 请求：RequestUtils.isAjaxRequest(request);是否是移动设备请求：RequestUtils.isMobile(request);设置缓存：ResponseUtils.httpCache(response, 5); // 缓存 5 秒ResponseUtils.httpCache(response, new Date()); // 缓存到指定的时间点\n"
        }
      ]
    }
  ]
}