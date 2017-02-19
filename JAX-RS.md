JAX-RS是JAVA EE6 引入的一个新技术。 JAX-RS即Java API for RESTful Web Services，
是一个Java 编程语言的应用程序接口，支持按照表述性状态转移（REST）架构风格创建Web服务。
JAX-RS使用了Java SE5引入的Java标注来简化Web服务的客户端和服务端的开发和部署。

JAX-RS提供了一些标注将一个资源类，一个POJO Java类，封装为Web资源。

标注
@Path，标注资源类或者方法的相对路径
@GET，@PUT，@POST，@DELETE，标注方法是HTTP请求的类型。
@Produces，标注返回的MIME媒体类型
@Consumes，标注可接受请求的MIME媒体类型
@PathParam，@QueryParam，@HeaderParam，@CookieParam，@MatrixParam，@FormParam,分别标注方法的参数来自于HTTP请求的不同位置，例如@PathParam来自于URL的路径，@QueryParam来自于URL的查询参数，@HeaderParam来自于HTTP请求的头信息，@CookieParam来自于HTTP请求的Cookie。
基于JAX-RS实现的框架有Jersey，RESTEasy等。这两个框架创建的应用可以很方便地部署到Servlet 容器中，比如Tomcat，JBoss等。值得一提的是RESTEasy是由JBoss公司开发的，所以将用RESTEasy框架实现的应用部署到JBoss服务器上，可以实现很多额外的功能。

from 百度百科
---

java 利用JAX-RS快速开发RESTful 服务
<http://www.cnblogs.com/yjmyzz/p/javaee-jax-rs-tutorial.html>

---
一、JAX-WS：全称是JavaTM API forXML-Based Web Services 
JAX-RS :全称是 JavaTM API forRESTful Web Services
关于JAX-WS与JAX-RS两者是不同风格的SOA架构。前者以动词为中心，指定的是每次执行函数。而后者以名词为中心，每次执行的时候指的是资源。
二、JAX-RS是JAVA EE6 引入的一个新技术。 JAX-RS即Java API for RESTful Web Services，是一个Java 编程语言的应用程序接口，支持按照表述性状态转移(REST)架构风格创建Web服务。JAX-RS使用了Java SE5引入的Java标注来简化Web服务的客户端和服务端的开发和部署。
JAX-WS规范是一组XML web services的JAVA API，JAX-WS允许开发者可以选择RPC-oriented或者message-oriented 来实现自己的web services。

---
jsr 311 && 339 是 JAX-RS,我用的是Jersey
jsr 330 是 Dependency Injection for Java SE
jsr 299 是 Contexts and Dependency Injection for the Java EE platform,我用的是jboss 的weld
jsr 346 是 Contexts and Dependency Injection for JavaTM EE (CDI)

---
jsr是Java Specification Requests的缩写，意思是Java 规范提案。

是指向JCP(Java Community Process)提出新增一个标准化技术规范的正式请求。任何人都可以提交JSR，以向Java平台增添新的API和服务。
JSR已成为Java界的一个重要标准。

---

JAX-RS实现貌似也碰到了类似的问题。目前我们有：
CXF——XFire和Celtix的合并（一个由IONA赞助的开源ESB，最初寄存在ObjectWeb上）。
Jersey——Sun公司的JAX-RS参考实现。
RESTEasy——JBoss的JAX-RS项目。
Restlet——也许是最早的REST框架了，它JAX-RS之前就有了。


<http://www.infoq.com/cn/news/2008/10/jaxrs-comparison/>

---
Spring MVC与JAX-RS比较与分析
<http://www.infoq.com/cn/articles/springmvc_jsx-rs/>
JAX-RS的目标是Web Services开发（这与HTML Web应用不同）而Spring MVC的目标则是Web应用开发。
Spring 3为Web应用与Web Services增加了广泛的REST支持

REST特性是Spring Framework的一部分，也是现有的Spring MVC编程模型的延续，因此，并没有所谓的“Spring REST framework”这种概念，
有的只是Spring和Spring MVC。这意味着如果你有一个Spring应用的话，你既可以使用Spring MVC创建HTML Web层，也可以创建RESTful Web Services层。

在Jersey中创建Spring管理的JAX-RS资源
Jersey支持在REST层中使用Spring，两个简单的步骤就能搞定（事实上有3步，还需要将构建依赖加到maven artifact com.sun.jersey.contribs:jersey-spring中）。
---
Spring MVC 是以 Servlet 为http容器，并自己构建了一套Api，没有遵循 jax-rs 规范。
更直白一点说吧。你写的 Servlet 程序，可以不经过任何修改，放到任何实现 Servlet 容器中运行。
你写的 jax-rs 程序，可以不经任何修改，和任何 jax-rs 框架配合使用。


---
REST 在 Java 中的使用
<http://blog.jobbole.com/109031/>