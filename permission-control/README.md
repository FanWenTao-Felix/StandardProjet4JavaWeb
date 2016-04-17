### shiro
<http://shiro.apache.org/>
+  项目mshowbackend

+ Apache Shiro 使用手册（一）Shiro架构介绍:<http://kdboy.iteye.com/blog/1154644>
+ SpringMVC整合Shiro:<http://blog.csdn.net/jadyer/article/details/12208847>
+ spring shiro权限注解方式验证:<http://blog.csdn.net/huzheaccp/article/details/24807327>



### spring security

+ 项目mbakcend

+ SpringSecurity3整合CAS实现单点登录 : <http://www.cnblogs.com/AloneSword/p/4097488.html>



### CAS
1、CAS 包含两个部分： CAS Server 和 CAS Client。CAS Server 需要独立部署，主要负责对用户的认证工作；
CAS Client 负责处理对客户端受保护资源的访问请求，需要登录时，重定向到 CAS Server。
2、cas中的ticket：
（1）TGT（Ticket Grangting Ticket）
TGT是CAS为用户签发的登录票据，拥有了TGT，用户就可以证明自己在CAS成功登录过。TGT封装了Cookie值以及此Cookie值对应的用户信息。
用户在CAS认证成功后，CAS生成cookie，写入浏览器，同时生成一个TGT对象，放入自己的缓存，TGT对象的ID就是cookie的值。当HTTP再次请求到来时，如果传过来的有CAS生成的cookie，则CAS以此cookie值为key查询缓存中有无TGT ，如果有的话，则说明用户之前登录过，如果没有，则用户需要重新登录。
（2）ST（Service Ticket）
ST是CAS为用户签发的访问某一service的票据。用户访问service时，service发现用户没有ST，则要求用户去CAS获取ST。
用户向CAS发出获取ST的请求，如果用户的请求中包含cookie，则CAS会以此cookie值为key查询缓存中有无TGT，如果存在TGT，
则用此TGT签发一个ST，返回给用户。用户凭借ST去访问service，service拿ST去CAS验证，验证通过后，允许用户访问资源。