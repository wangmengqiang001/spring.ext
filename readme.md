1. 自定义namespace handler 
参考: https://blog.csdn.net/weixin_33735077/article/details/87978434

2. 自定义__XMLApplicationContext__ 
CustomizeWebApplicationContext 注册新schema 和namespace resolver 
在这些resolver中实现了不再仅限于在spring.handlers, spring.schemas中增加对namespace的扩展

* 调整了工程的目录结构，改为标准的maven结构

3. 增加对BeanPostProcessor的自定义扩展

4. 自定义annotation, 并增加自定义的注解解析
通过标签设置要扫描的base-package, 不是对全包扫描，且是通过annotationscanner的处理方式，为扫描到的bean增加了annotation 类型的holder.

