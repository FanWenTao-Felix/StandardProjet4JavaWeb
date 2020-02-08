# project standard
standard structure for Java Web Application.  <br/>
Java web 项目标准，规范

#### 制定编码规范
+ 错误码定义：BusinessException, RequestException, ValidationException

#### 业务要点记录，业务名，范围，对接人
#### 日志按业务划分
#### README文件：注明系统间代码耦合的地方，具体类
#### 开发不仅仅只是应付需求，考虑代码扩展性和安全隐患等方面也是工作量，也很有必要
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

### wiki
1. 记录接口是否废弃,调用方等信息


***


1. log4j.jsp
2. version.jsp  jsp中写java代码读取文件
3. gradle
4. scala
5. 在页面中读取文件（1）.采用js（读本地文件）（2）.采用jsp（写java代码）

---


SonarQube idea plugin

---

Tools 
<http://tongji.baidu.com/web/welcome/products>
<http://tongji.baidu.com/web/welcome/login?qq-pf-to=pcqq.c2c>
