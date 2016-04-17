### project
#### servicexx-trac--kerxx
1. jersey （org.glassfish.jersey）<https://jersey.java.net/>
2. mongodb
3. LinkedBlockingQueue Logger  threadPoolExecutor
4. GZIP
5. HttpServletRequestWrapper + Filter
HttpServletRequestWrapper 用法<http://fishhappy365.iteye.com/blog/484185>
Servlet规范中所引入的filter令人心动不已，因为它引入了一个功能强大的拦截模式。Filter是这样一种Java对象，它能在request到达servlet的服务方法之前拦截HttpServletRequest对象，
而在服务方法转移控制后又能拦截HttpServletResponse对象。你可以使用filter来实现特定的任务，比如验证用户输入，以及压缩web内容。
但你拟富有成效地使用过滤器的念头却被你不能改变HttpServletRequest对象的参数的现实扫了兴，因为java.util.Map所包装的HttpServletRequest对象的参数是不可改变的。
这极大地缩减了filter的应用范围。至少在一半的时间里，你希望可以改变准备传送给filter的对象。如果在HttpServletRequest对象到达Struts的action  servlet之前，
我们可以通过一个filter将用户输入的多余空格去掉，难道不是更美妙吗？这样的话，你就不必等到在Struts的action表单验证方法中才进行这项工作了。 
幸运的是，尽管你不能改变不变对象本身，但你却可以通过使用装饰模式来改变其状态。 
6. 重写log4j appender
7. 分表,数据迁移过程中保持接口兼容使用

#### cxonfi--g-api
1. spring拦截器和注解实现Controller拦截做校验等操作
2. URLConnection readTimeout and connectionTimeout

#### mxbilexx-br-an--d
1. shiro 权限控制
2. 分布式定时调度
3. 实时刷新模块(面试 vip notes 1)(注册机制,定时刷新注解,)版本对比时使用==而不是使用equals,Integer类型对[-128,127]的数据有缓存,属于同一个对象
4. 插入和更新并行导致锁表问题(面试 vip notes 1 + csdn blog)
5. 使用json字符串放缓存还是java序列化之后放缓存,前者可以在后台操作,比较方便(访问量不大,配置性的数据可以考虑用json字符串)
6. 全量查数据再分不同的key做缓存,而不是多次查数据库,因为数据库是性能的瓶颈
7. 有时改变类的结构要以新key进行存储,同时要兼容旧key,因为后台上了之后,前台的api还没上,避免读取脏数据
8. 修改js,要改参数的版本号,这样客户端辉自动拉去最新的js文件,而且前端js检验,后端也要检验
9. cas对接
10. tomcat是否开启gzip,开启gzip最好是直接面对用户的'客户端,如果是内部系统之间的调用,对方开启的gzip,而这边需要解压,在高并发的情况下非常消耗CPU的性能
11. 对于后台系统,如果是不依赖于本系统,而仅仅是一个工具,比如批量检验图片是否有效,可以在客户端的js实现功能,而不是在服务端实现,消耗服务端的CPU性能
12. 性能分析,用jProfiler分析代码,用jMeter压测,linux命令查看服务器CPU,IO等
13. mysql取对象,SQL list 加 limit 1,防止转化报错
14. 有些包路径或方法事务切面了,导致新写的类方法被切面执行其他操作而没及时发觉
15. 更多面试总结看 vip notes 1
16. 业务:同步数据,插入数据库,进行排序
17. 定时同步和运营排序时数据库死锁问题,后台定时任务有插入操作,而运营有更新操作,
insert操作去主键索引,再取唯一索引,而update操作先取唯一索引,再去主键索引,
解决:update操作先取查处符合条件的id(即主键),再根据id进行更新操作
18. 项目依赖,后台和api同时依赖一个service的纠结,service中使用annotation还是xml的选择问题
19. promotion failed

#### maxp--i
1. 为了避免mc hotkey，每个key值在后台生成10份不同的key，存相同的值，这样就可以把需要的数据保存在多台mc，取得时候随机取一个，
   这样避免相同数据老是访问同一台机的hotkey问题
2. 平常两百多万,高峰期间一千多万,响应5毫秒,最高25毫秒
日均20亿访问量,每秒500万,380台机,平均217个每秒,
tomcat配置连接数为3000,因为高峰是10倍流量,而且是同步阻塞,所以要开很多个线程接收请求,同时由于业务原因,
一个请求还要另外请求其他的系统(也要连接数??),(另外的请求应该不用连接数,同步模型还是同一个线程,只是交给了网络IO去处理)
所以需要的线程很多,相应的线程切换也很多
网络IO的数量设置`ulimit -n 65536`
<http://www.2cto.com/os/201309/245224.html>

ulimit 并没有哪个选项直接说是用来限制 socket 的数量的。但是，我们有 -n 这个选项，它是用于限制一个进程所能打开的文件描述符的最大值。
在 Linux 下一切资源皆文件，普通文件是文件，磁盘打印机是文件，socket 当然也是文件。在 Linux 下创建一个新的 socket 连接，
实际上就是创建一个新的文件描述符。
查进程打开的文件描述符:`ls /proc/pid/fd -l`
`ulimit -n` 查看程序所能打开的最大文件描述符数量
通过 ulimit 改善系统性能:<http://www.ibm.com/developerworks/cn/linux/l-cn-ulimit/>


#### mxobilexx-back--end
1. spring security 权限控制
2. shelf nashorn
3. WebApplicationInitializer in Spring MVC
4. log4j.jsp

#### mxobilexx-shelf
1. digester 解析 xml文件

#### configcontrol-ce--nter
1. zookeeper
2. IOUtils

#### mxonito--r
1. highchart
2. spring session 报错,,no operations allow after connection close.(定时清数据的任务)
因为被配置拦截成为事务,一个事务有太多的提交,提交事务时报错.
3. 适应事务的advice做读写分离
4. 各个接入应用服务器的xxx.log,使用flume收集,配置发送到kafka,Storm连接kafka 服务器消费数据并进行流式计算,再把结果查到存储层中(mysql,redis,hbase,hdfs)
5. 规则引擎drools

#### erxlanx--gshxen
+ flume 安装再mapi各个服务器,收集日志 ,发送到kafka
+ kafka消息队列
+ storm 流式计算,消费kafka 的消息,并存储到mongodb
+ mongodb 数据存储

#### drxagoxn--fy 日志系统
+ logstash 安装在各个应用服务器如mapi ,与flume功能类似,用于收集日志数据,并发送到redis
+ redis 之所以发送到redis,是因为收集到的日志要做日志处理,logstash是可以做的,但是在应用服务器做会占用机器的系统资源
+ 部署redis机器上的logstash消费redis上的数据,并作处理,然后发送到kafka
+ 消费kafka上的数据并发送到elesticsearch存储

flume只能一行一行收集,所以只能用来收集nginx日志
logstash可以按照规则收集,所以可以用来收集应用日志,如tomcat中的日志

大数据部门收集nginx日志到hive


#### moco
1. netty
2. 使用了jmx,相当于一个简单的后台(某些基础数据,不涉及运营人员操作,只是做简单的开关或调度管理,可以使用jmx,提供给技术人员使用)
(考虑使用jmx做测试环境的后门,不用提供接口做测试,在测试环境中开启)
3. tcpdump收集请求数据,转换格式,存文件,把数据导入到hbase,再提供接口sdk给moco server调用,使用jMeter对moco server进行压测

#### cxommon-cxompon----ent
1. http-client
2. http-client-async
3. AuthxFilterx
4. 自定义maven插件

#### kaxtaxna
1. freemarker ,javadoc 产生md文件
使用FreeMarker替换JSP的10个理由<http://dwz.cn/30q6cl>
与 JSP 不同的是FreeMarker 模板可以在 servlet 容器之外使用。可以使用它们来生成电子邮件、 配置文件、 XML 映射等。
你甚至可以使用它们来生成 web 页 并将它们保存在服务器端的缓存中
2. nashorn
3. WebApplicationInitializer 实现了servlet规范的
4. NettyServer ClassLoader (三个)
5. maven-api使用
6. 开源库org.apache.commons.cli.* 使用 ,可参考项目或Utils项目TestCommandLineParser
7. akka 使用,actor 管程
8. javaassist(BeanUtils是使用反射实现的)
9. 查找怎么创建固定的actor数目?
10. 扫描接口所有实现类的那段代码）

#### evil脚本 
自动编译并上传到svn

#### teamcity
shell , expect

#### middle层work
+ 业务适配
+ 服务聚合
+ 数据展示
+ 安全隔离

#### vexnu--s
spring mvc ＋ thrift ＋ netty＋zookeeper

#### nginx和HAProxy
+ 每台服务器都有一个nginx和一个tomcat,通过服务器本地的niginx转发到本地的各个不同端口的tomcat服务器(逻辑集群,充分利用硬件资源),现在本地只开启了一台tomcat
+ 使用nginx的除了可以转发请求到不同的tomcat之外,还可以汇集单台机器的所有访问日志,由大数据收集保存,供查阅和分析
+ 而外部的方向代理为什么是使用HAProxy而不是使用nginx,HAProxy用来根据不同url规则转发到不同的服务器,nginx也可以做到,但是
nginx应用层反向代理服务器,所有请求和响应都会经过nginx,容易形成性能瓶颈,而HAProxy可以向LVS,使用直接路由方式??
数据链路层的负载均衡(指在通信协议的数据链路层修改mac地址进行负载均衡)
三角传输模式,请求分发过程中不修改ip地址,直接修改目的的mac地址,通过配置真实物理服务器集群所有机器虚拟ip和负载均衡器ip地址一直从而达到不修改数据包的源地址就可以进行数据分发的目的
+ 七层(基于URL等应用层的负载均衡),四层(ip+端口),三层(ip),二层(mac地址)
+ nginx(反向代理,负载均衡,页面缓存,URL重写,读写分离)
+ request-->F5-->HAProxy-->(各个ip server)
+  Nginx 教程:<https://openresty.org/download/agentzh-nginx-tutorials-zhcn.html>

#### 设计模式
1. 配置center观察者模式
2. Adapter InputStream
3. 生成器模式 java.lang.StringBuilder

logstash lucene搜索语法：<https://lucene.apache.org/core/2_9_4/queryparsersyntax.html>