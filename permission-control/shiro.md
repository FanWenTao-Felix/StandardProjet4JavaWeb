Apache Shiro 使用手册（一）Shiro架构介绍

博客分类： 开发
安全框架Shiro 
一、什么是Shiro 
Apache Shiro是一个强大易用的Java安全框架，提供了认证、授权、加密和会话管理等功能： 
认证 - 用户身份识别，常被称为用户“登录”；
授权 - 访问控制；
密码加密 - 保护或隐藏数据防止被偷窥；
会话管理 - 每用户相关的时间敏感的状态。
对于任何一个应用程序，Shiro都可以提供全面的安全管理服务。并且相对于其他安全框架，Shiro要简单的多。 

二、Shiro的架构介绍 
首先，来了解一下Shiro的三个核心组件：Subject, SecurityManager 和 Realms. 如下图： 
 
Subject：即“当前操作用户”。但是，在Shiro中，Subject这一概念并不仅仅指人，也可以是第三方进程、后台帐户（Daemon Account）或其他类似事物。它仅仅意味着“当前跟软件交互的东西”。但考虑到大多数目的和用途，你可以把它认为是Shiro的“用户”概念。 
Subject代表了当前用户的安全操作，SecurityManager则管理所有用户的安全操作。 

SecurityManager：它是Shiro框架的核心，典型的Facade模式，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务。 

Realm： Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。 
从这个意义上讲，Realm实质上是一个安全相关的DAO：它封装了数据源的连接细节，并在需要时将相关数据提供给Shiro。当配置Shiro时，你必须至少指定一个Realm，用于认证和（或）授权。配置多个Realm是可以的，但是至少需要一个。 
Shiro内置了可以连接大量安全数据源（又名目录）的Realm，如LDAP、关系数据库（JDBC）、类似INI的文本配置资源以及属性文件等。如果缺省的Realm不能满足需求，你还可以插入代表自定义数据源的自己的Realm实现。 


Shiro完整架构图： 

 
除前文所讲Subject、SecurityManager 、Realm三个核心组件外，Shiro主要组件还包括： 
Authenticator ：认证就是核实用户身份的过程。这个过程的常见例子是大家都熟悉的“用户/密码”组合。多数用户在登录软件系统时，通常提供自己的用户名（当事人）和支持他们的密码（证书）。如果存储在系统中的密码（或密码表示）与用户提供的匹配，他们就被认为通过认证。 
Authorizer ：授权实质上就是访问控制 - 控制用户能够访问应用中的哪些内容，比如资源、Web页面等等。 
SessionManager ：在安全框架领域，Apache Shiro提供了一些独特的东西：可在任何应用或架构层一致地使用Session API。即，Shiro为任何应用提供了一个会话编程范式 - 从小型后台独立应用到大型集群Web应用。这意味着，那些希望使用会话的应用开发者，不必被迫使用Servlet或EJB容器了。或者，如果正在使用这些容器，开发者现在也可以选择使用在任何层统一一致的会话API，取代Servlet或EJB机制。 
CacheManager :对Shiro的其他组件提供缓存支持。 

来源： <http://kdboy.iteye.com/blog/1154644#bc2358003>
 
 
----
CAS跟Shiro在spring中集成
CAS和Shiro在spring中集成
shiro是权限管理框架，现在已经会利用它如何控制权限。为了能够为多个系统提供统一认证入口，又研究了单点登录框架cas。因为二者都会涉及到对session的管理，所以需要进行集成。

Shiro在1.2.0的时候提供了对cas的集成。因此在项目中添加shiro-cas的依赖
    <dependency>
       <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-cas</artifactId>
       <version>${shiro.version}</version>
    </dependency>

Shiro对cas集成后，cas client的配置更加简单了。原理就是将casFilter添加到到shiroFilter的filterChain中。 shiroFilter是在web.xml中定义的，前文已经讲过。

在Spring项目中集成Shiro和CAS
<?xmlversion="1.0" encoding="UTF-8"?>
<beansxmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beanshttp://www.springframework.org/schema/beans/spring-beans-2.5.xsd"
default-lazy-init="true">

<beanid="shiroFilter"class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
<propertyname="securityManager" ref="securityManager" />

<!--没有单点登录下的配置：没有权限或者失败后跳转的页面 -->
<!--<property name="loginUrl" value="/login/toLoginAction"/> -->

<!--有单点登录的配置：登录 CAS 服务端地址，参数 service 为服务端的返回地址 -->
<propertyname="loginUrl"
value="http://localhost:18080/cas/login?service=http://localhost:8080/gxpt_web_qx_login/shiro-cas"/>
<!--<property name="successUrl" value="/page/index.jsp"/> -->
<propertyname="successUrl" value="/indexAction" />

<propertyname="filters">
<map>
<!--添加casFilter到shiroFilter -->
<entrykey="casFilter" value-ref="casFilter">
</entry>
</map>
</property>

                 <propertyname="filterChainDefinitions">
<value>
/shiro-cas= casFilter
/styles/**= anon
/**= user
</value>
</property>

<!--没有单点登录下的配置： -->
<!--<property name="filterChainDefinitions">
<value>
/styles/**= anon
/login/loginAction= anon
/login/logoutAction= logout
/**= user
</value>
</property>-->
</bean>

<beanid="casFilter" class="org.apache.shiro.cas.CasFilter">
<!--配置验证错误时的失败页面（Ticket 校验不通过时展示的错误页面） -->
<propertyname="failureUrl" value="/page/error.jsp" />
</bean>

<beanid="securityManager"class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
<!--Single realm app. If you have multiple realms, use the 'realms' property
instead.-->
<!--没有单点登录下的配置： -->
<!--<property name="realm" ref="shiroDbRealm" /> -->

<propertyname="realm" ref="casRealm" />
<propertyname="subjectFactory" ref="casSubjectFactory" />

<propertyname="cacheManager" ref="shiroEhcacheManager" />
</bean>

<beanid="casRealm" class="web.qx.login.shiro.MyCasRealm">
<propertyname="defaultRoles" value="ROLE_USER"/>
<propertyname="casServerUrlPrefix"value="http://localhost:18080/cas" />
<!--客户端的回调地址设置，必须和上面的shiro-cas过滤器拦截的地址一致 -->
<propertyname="casService"
value="http://localhost:8080/gxpt_web_qx_login/shiro-cas"/>
</bean>

<!--Define the realm you want to use to connect to your back-end security
datasource:-->
<!--
<beanid="shiroDbRealm"class="web.qx.login.shiro.ShiroDbRealm">
<propertyname="loginService"ref="login-loginBean"></property>
</bean>
 -->

<beanid="casSubjectFactory"class="org.apache.shiro.cas.CasSubjectFactory" />

<!--用户授权/认证信息Cache, 采用EhCache 缓存 -->
<beanid="shiroEhcacheManager"class="org.apache.shiro.cache.ehcache.EhCacheManager">
<propertyname="cacheManagerConfigFile"value="classpath:config/ehcache-shiro.xml" />
</bean>


<!--保证实现了Shiro内部lifecycle函数的bean执行 -->
<beanid="lifecycleBeanPostProcessor"class="org.apache.shiro.spring.LifecycleBeanPostProcessor" />


<!--AOP式方法级权限检查 -->
<!--Enable Shiro Annotations for Spring-configured beans. Only run after -->
<!--the lifecycleBeanProcessor has run: -->
<bean
class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
depends-on="lifecycleBeanPostProcessor">
<propertyname="proxyTargetClass" value="true" />
</bean>
<bean
class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
<propertyname="securityManager" ref="securityManager" />
</bean>

</beans>

没有单点登录情况下的话，登录认证和授权认证默认在AuthorizingRealm的doGetAuthorizationInfo和doGetAuthenticationInfo中进行，所以我这里是通过shiroDbRealm（继承AuthorizingRealm的自定义类）覆写doGetAuthorizationInfo和doGetAuthenticationInfo，实现自定义登录认证和授权认证。

有单点登录情况下，登录认证是在casserver进行的，那么执行流程是这样的：用户从 cas server登录成功后，跳到cas client的CasRealm执行默认的doGetAuthorizationInfo和doGetAuthenticationInfo，此时doGetAuthenticationInfo做的工作是把登录用户信息传递给shiro，保持默认即可，而对于授权的处理，可以通过MyCasRealm（继承CasRealm的自定义类）覆写doGetAuthorizationInfo进行自定义授权认证。

来源： <http://www.myexception.cn/software-architecture-design/1607612.html>
