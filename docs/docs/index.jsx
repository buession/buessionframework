---
banner:
  name: '基于各开源框架的二次开发和封装。'
  desc: '基于各开源框架的二次开发和封装。'
  btns: 
    - { name: '开 始', href: './documents/index.html', primary: true }
    - { name: 'Github >', href: 'https://github.com/buession/buessionframework' }
  caption: '当前版本: v2.0.0'
features: 
  - { name: '优雅', desc: '经过精雕细琢，我们带给大家一个精心设计的、拥有卓越的视觉与交互体验的文档构建工具' }
  - { name: '灵动', desc: '我们拥有非常灵活的 插件机制 与 主题定制 功能，正在努力构建活跃的插件社区。也许初次使用未见其惊艳，但当你灵活使用插件后便会发现她的强大' }
  - { name: '简洁', desc: '以 ‘无形’ 代替 ‘有形’，从开发体验到用户界面的呈现，不断去除冗余的设计，使用户专注于写作与阅读' }
  - { name: '开源', desc: '作为开源项目，我们拥有高质量的代码、完善的自动化测试流程，对社区的需求能够作出积极快速响应' }

footer:
  copyright:
    name: 'Buession Team'
    href: 'https://www.buession.com/'
  links:
    前端开源库:
      - { name: 'Buession Prototype', href: 'https://prototype.buession.com/' }
      - { name: 'Buession Shirojs', href: 'https://shirojs.buession.com/' }
    后端开源库:
      - { name: 'Buession Framework', href: 'https://www.buession.com/' }
      - { name: 'Buession Security', href: 'https://security.buession.com/' }
      - { name: 'Buession SpringBoot', href: 'https://springboot.buession.com/' }
      - { name: 'Buession SpringCloud', href: 'https://springcloud.buession.com/' }
      - { name: 'Buession Cas', href: 'https://cas.buession.com/' }

---

<Homepage banner={banner} features={features} />
<Footer distPath={props.page.distPath} copyRight={props.footer.copyright} links={props.footer.links} />