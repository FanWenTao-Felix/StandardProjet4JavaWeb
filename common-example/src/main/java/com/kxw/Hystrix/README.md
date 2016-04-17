<http://hot66hot.iteye.com/blog/2155036>
在复杂的分布式架构的应用程序有很多的依赖，都会不可避免地在某些时候失败。高并发的依赖失败时如果没有隔离措施，当前应用服务就有被拖垮的风险。

Java代码  收藏代码
例如:一个依赖30个SOA服务的系统,每个服务99.99%可用。
99.99%的30次方 ≈ 99.7%
0.3% 意味着一亿次请求 会有 3,000,00次失败
换算成时间大约每月有2个小时服务不稳定.
随着服务依赖数量的变多，服务不稳定的概率会成指数性提高.
解决问题方案:对依赖做隔离,Hystrix就是处理依赖隔离的框架,同时也是可以帮我们做依赖服务的治理和监控.


<http://hot66hot.iteye.com/blog/2155036>

<https://github.com/Netflix/Hystrix/wiki>
<https://github.com/Netflix/Hystrix/wiki/How-To-Use>

