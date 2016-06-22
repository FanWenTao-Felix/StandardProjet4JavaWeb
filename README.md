# project standard
standard structure for Java Web Application.  <br/>

####制定编码规范
####日志按业务划分
####README文件：注明系统间代码耦合的地方，具体类
####开发不仅仅只是应付需求，考虑代码扩展性和安全隐患等方面也是工作量，也很有必要
#### 留意那些包路径或方法事务切面了
#### 写可测试可监控的代码

1. 添加依赖包需要用eclipse查看Resolved Dependencies,查看最终依赖有没有变（若依赖有问题，字节码是在加载使用到的时候才会报错，classload机制）
2. debug log 需要加 `if(logger.isDebugEnabled())`,隔离不必要的操作，比如预编译之类的问题，slf4j也一样。
3. 参数绝对不能传中文，避免以后埋坑
4. 代码文档规范：<http://www.importnew.com/16459.html>
5. `@Deprecated`标识废弃
6. 新建类时，查看该类所属的包是否被AOP，查看xml文件确认  <br/>
``` xml
<aop:config>
		<aop:pointcut id="daoMethodsExp"
			expression="execution(* com.kxw.dao.service.impl.*.*(..))" />
		<aop:advisor advice-ref="txAdvice" pointcut-ref="daoMethodsExp" />
</aop:config>
```
7.  `<c:if>` 是服务端语言，不能写在js中，若需要传参，可用`<input> hidden`的方式
8. js: 条件语句：`var str = "abc + "false ? "" : "kxw";`
9. 定义不抛异常不代表不会隐性给你抛异常，要加try catch！！！
10. remove web.xml : WebApplicationInitializer   <http://www.javabeat.net/webapplicationinitializer-spring-mvc/>
<http://www.javacodegeeks.com/2014/10/spring-webapplicationinitializer-and-applicationcontextinitializer-confusion.html>
config-api project
11. 提交数据不能用get, 1.不安全 2.容易在浏览器地址栏造成历史纪录,造成误提交
12. 解决maven依赖,如果ide中对某一个类有多个jar包,应该排除依赖保持一个版本,使用`<Execludes>`,有些隐形依赖,在项目中搜索不出来,可使用mvn dependency:tree来查看.
13. 工具类封装，如HttpUtils，JsonUtils。方便以后替换或升级底层的实现，如究竟是使用jackson还是fastjson。
14. Controller只能写一些不能复用的代码。
15.

***

## cache
1. 缓存为空就去DB取是不合理的，应该是缓存为空，且请求的key合法（参数在可选的范围内），才去数据库中拿，否则数据库会受到攻击！！！
2. 从数据库一次性拿出所有数据再分类(维度，即不同key)放在缓存中，减少数据库访问次数
3. select * from dept, emp where dept.deptno=emp.deptno; [简单处理方式]  <br/>
   select * from dept left join emp on dept.deptno=emp.deptno;  [左外连接，更ok!]  
4. 后台从数据库拿（需要经常用到的数据可以放到后台的本地缓存）
   api从缓存（memcached）中拿
   ----------
   缓存为空就去DB取是不合理的
   应该是缓存为空，且请求的key合法（参数在可选的范围内），才去数据库中拿，否则数据库会受到攻击！！！！！！


   
+ 本地缓存:guava,oscache
+ 分布式缓存: memcached,redis,mongodb
+ 数据库:mysql

+ 5个强大的Java分布式缓存框架推荐:<http://www.codeceo.com/article/5-java-distribute-cache.html>

### memcached
+  Memcached内存管理的局限性导致尽量不能让KEY永远不过期:<http://blog.csdn.net/tenebaul/article/details/8141673>
+ memcached完全剖析–1. memcached的基础:<http://kb.cnblogs.com/page/42731/>



### Ehcache – Java分布式缓存框架
Ehcache是一个Java实现的开源分布式缓存框架，EhCache 可以有效地减轻数据库的负载，可以让数据保存在不同服务器的内存中，
在需要数据的时候可以快速存取。同时EhCache 扩展非常简单，官方提供的Cache配置方式有好几种。
你可以通过声明配置、在xml中配置、在程序里配置或者调用构造方法时传入不同的参数。
EhCache 分布式缓存/缓存集群:<http://www.cnblogs.com/hoojo/archive/2012/07/19/2599534.html>


### 图数据库
图数据库的扩展性，灵活性非常好，适合用于复杂关系管理和关系查询推理，社交关系应用就是一个可选的应用场景。
neo4j 

## logger
+ log4j,slf4j,logback
+ log4j、 slf4j 、logback:<http://blog.csdn.net/maohaiyan/article/details/7593996>
+ Logback浅析: <http://www.cnblogs.com/yongze103/archive/2012/05/05/2484753.html>
+ logback 配置详解:<http://blog.csdn.net/haidage/article/details/6794509>
+ log4j2 使用详解:<http://blog.csdn.net/lrenjun/article/details/8178875>
+ log4j PropertyConfigurator,logger.getRootLogger.set()
+ log4j2.0:<http://blog.csdn.net/xmtblog/article/details/38982473>


## Tomcat
+ 提升tomcat服务器性能的经验<http://my.oschina.net/u/1995545/blog/360316>
+ 对tomcat来说，每一个进来的请求(request)都需要一个线程，直到该请求结束。如果同时进来的请求多于当前可用的请求处理线程数，
额外的线程就会被创建，直到到达配置的最大线程数(maxThreads属性值)。如果仍就同时接收到更多请求，
这些来不及处理的请求就会在Connector创建的Server Socket中堆积起来，直到到达最大的配置值(acceptCount属性值)。
至此，任何再来的请求将会收到connection refused错误，直到有可用的资源来处理它们。
然而，不管是可立即处理请求还是需要放入等待区，都需要tomcat先接受该请求(即接受client的连接请求，建立socket channel)，
那么tomcat同时可建立的连接数(maxConnections属性值)也会影响可同时处理的请求数。
<http://www.cnblogs.com/tyb1222/p/4583983.html>



***

1. log4j.jsp
2. version.jsp  jsp中写java代码读取文件
3. gradle
4. scala
5. 在页面中读取文件（1）.采用js（读本地文件）（2）.采用jsp（写java代码）

---

#### 


SonarQube idea plugin

---

Tools 
<http://tongji.baidu.com/web/welcome/products>
<http://tongji.baidu.com/web/welcome/login?qq-pf-to=pcqq.c2c>
