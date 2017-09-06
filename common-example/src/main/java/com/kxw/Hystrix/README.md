<http://hot66hot.iteye.com/blog/2155036>
在复杂的分布式架构的应用程序有很多的依赖，都会不可避免地在某些时候失败。高并发的依赖失败时如果没有隔离措施，当前应用服务就有被拖垮的风险。

例如:一个依赖30个SOA服务的系统,每个服务99.99%可用。
99.99%的30次方 ≈ 99.7%
0.3% 意味着一亿次请求 会有 3,000,00次失败
换算成时间大约每月有2个小时服务不稳定.
随着服务依赖数量的变多，服务不稳定的概率会成指数性提高.
解决问题方案:对依赖做隔离,Hystrix就是处理依赖隔离的框架,同时也是可以帮我们做依赖服务的治理和监控.


<http://hot66hot.iteye.com/blog/2155036>

<https://github.com/Netflix/Hystrix/wiki>
<https://github.com/Netflix/Hystrix/wiki/How-To-Use>

隔离术之使用Hystrix实现隔离:<https://mp.weixin.qq.com/s/o8RGc7FjXtzgXxsmRmDeVg>
降级特技之使用Hystrix实现降级和熔断:<https://mp.weixin.qq.com/s/uF09oVzf_1Tz_ZnHXQjyzA>


---


Hystrix基于命令模式

<www.jianshu.com/p/138f92aa83dc>

####  Hystrix如何解决依赖隔离
+ Hystrix使用命令模式HystrixCommand(Command)包装依赖调用逻辑，每个命令在单独线程中/信号授权下执行。
+ 可配置依赖调用超时时间,超时时间一般设为比99.5%平均时间略高即可.当调用超时时，直接返回或执行fallback逻辑。
+ 为每个依赖提供一个小的线程池（或信号），如果线程池已满调用将被立即拒绝，默认不采用排队.加速失败判定时间。
+ 依赖调用结果分:成功，失败（抛出异常），超时，线程拒绝，短路。 请求失败(异常，拒绝，超时，短路)时执行fallback(降级)逻辑。
+ 提供熔断器组件,可以自动运行或手动调用,停止当前依赖一段时间(10秒)，熔断器默认错误率阈值为50%,超过将自动运行。
+ 提供近实时依赖的统计和监控。


#### Hystrix流程结构解析
1:每次调用创建一个新的HystrixCommand,把依赖调用封装在run()方法中.
2:执行execute()/queue做同步或异步调用.
3:判断熔断器(circuit-breaker)是否打开,如果打开跳到步骤8,进行降级策略,如果关闭进入步骤.
4:判断线程池/队列/信号量是否跑满，如果跑满进入降级步骤8,否则继续后续步骤.
5:调用HystrixCommand的run方法.运行依赖逻辑
    5a:依赖逻辑调用超时,进入步骤8.
6:判断逻辑是否调用成功
    6a:返回成功调用结果
    6b:调用出错，进入步骤8.
7:计算熔断器状态,所有的运行状态(成功, 失败, 拒绝,超时)上报给熔断器，用于统计从而判断熔断器状态.
8:getFallback()降级逻辑.
    以下四种情况将触发getFallback调用：
         (1):run()方法抛出非HystrixBadRequestException异常。
         (2):run()方法调用超时
         (3):熔断器开启拦截调用
         (4):线程池/队列/信号量是否跑满
    8a:没有实现getFallback的Command将直接抛出异常
    8b:fallback降级逻辑调用成功直接返回
    8c:降级逻辑调用失败抛出异常
9:返回执行成功结果

#### 熔断器:Circuit Breaker

每个熔断器默认维护10个bucket,每秒一个bucket,每个bucket记录成功,失败,超时,拒绝的状态。
默认错误超过50%且10秒内超过20个请求进行中断拦截。