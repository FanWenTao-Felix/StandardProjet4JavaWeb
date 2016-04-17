### nginx
[root@kingson-wu-kuiup vhosts]# vi mobile-cc.abc.abc.com 
[root@mobile-all-in-one-aeg8r vhosts]# vi mobile-cc.abc.abc.com
upstream mobile-cc.abc.abc.com {
        server 127.0.0.1:8085;
}

server
  {
    listen       80;
    server_name  mobile-cc.abc .abc.com;
    index index.jsp index.html index.htm;

    location / {
          proxy_set_header Host $http_host;
          proxy_pass http://mobile-cc.abc.abc.com;
         }

    access_log  /apps/logs/nginx/mobile-cc.abc.abc.access.log   log_access;
 }



 /apps/svr/nginx/sbin/nginx -c /apps/svr/nginx/conf/nginx.conf

./nginx -c /apps/svr/nginx/conf/nginx.conf

 redis-server /etc/redis/6379.conf
 
###redis
redis-server redis.conf
redis-cli -h 10.199.146.42  -p 6379


###zookeeper

zookeeper-3.4.6/bin/zkServer.sh start

[root@kingson-wu-kuiup bin]# zkServer.sh start
JMX enabled by default
Using config: /apps/svr/zookeeper-3.4.6/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
[root@kingson-wu-kuiup bin]# 1573 QuorumPeerMain
-bash: 1573: command not found
[root@kingson-wu-kuiup bin]# QuorumPeerMain
-bash: QuorumPeerMain: command not found
[root@kingson-wu-kuiup bin]# jps
7547 Jps
7348 QuorumPeerMain
[root@kingson-wu-kuiup bin]# zookeeper-3.4.3/bin/zkServer.sh status
-bash: zookeeper-3.4.3/bin/zkServer.sh: No such file or directory
[root@kingson-wu-kuiup bin]# zkServer.sh status
JMX enabled by default
Using config: /apps/svr/zookeeper-3.4.6/bin/../conf/zoo.cfg
Mode: standalone


### configcenter
+ 配置中心通知没问题,只是通过http拿数据超时,这个没有重试机制,要新增重试机制和数据压缩.
