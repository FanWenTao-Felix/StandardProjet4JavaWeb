<http://linux.about.com/od/commands/tp/11-Linux-Terminal-Commands-That-Will-Rock-Your-World.htm>
### shell
1. curl JSON格式输出：curl url/json , curl url/all.json
2. curl http://127.0.0.1/shelf/test/page -H "Host:shelf.com"  <br>
   curl -I 'http://127.0.0.1/shelf/test/page'
3. nginx.exe  -s reload
4. redis-cli -h 10.199.146.42  -p 6379
5. grep access.log | awk '{print $10}'
6. 验证文件的md5 : md5sum.exe  ROOT.war
7. 清空文件： >error.log
8. 最后5个登录的ip ：last -n 5
9. tshark -s 512 -i eth0 -n -f 'tcp dst port 8080' -R 'http.host and http.request.uri' -T fields -e http.host -e http.request.uri -l | tr -d '＼t'  <br/>
   tcpdump -i eth0 src host 192.168.33.174 and dst host 10.199.144.198 and dst port 8080
10. apache : ab.exe –n 10000 –c 100 localhost/index.php //其中-n代表请求数，-c代表并发数
11. lsof -i:2181 ,lsof -i tcp:8000
首先用ps命令查看进程的id：`ps -ef | grep Name `
查看到进程id之后，使用netstat命令查看其占用的端口:`netstat -nap | grep pid `
12. grep
 + grep -i "boo" /etc/passwd 忽略大小写
 + grep -r "192.168.1.5" /etc/ (或-R)指定目录递归地使用 grep 进行搜索(结果每一行都前缀以找到匹配的文件名)
 + grep -h -R "192.168.1.5" /etc/ (或-hR)输出之中包含的文件名可以加 -h 选项来禁止输出
 + grep -w "boo" file 用 -w 选项去强制只输出那些仅仅包含那个整个单词的行
 + egrep -w 'word1|word2' /path/to/file 使用 grep 命令去搜索两个不同的单词
 + grep -c 'word' /path/to/file 通过加 -c 参数显示每个文件中匹配到的次数
 + grep -n 'root' /etc/passwd 传递 -n 选项可以输出的行前加入匹配到的行的行号
 + grep -v bar /path/to/file 使用 -v 选项来输出不包含匹配项的内容
 + grep -l 'main' *.c 仅仅显示匹配到内容的文件名字
 + grep --color vivek /etc/passwd 强制 grep 以彩色输出
 + 
13. sudo tcpdump -i any -Xnpls0 host 192.168.138.236  </br>
    sudo tcpdump -i any -Xnpls0 dst host 192.168.138.236  </br>
    sudo tcpdump -i any -Xnpls0 port 8080
14. ps -ef | grep tomcat | grep -v grep | awk '{print $2}'  <br/>
    ps -ef | grep tomcat | grep -v grep | awk '{print $2}' | xargs kill -9
    find / -name "dd"|xargs rm -rf
15. chmod +x start.sh
16. 用命令行往配置文件里插入多行文本
cat >> path/to/file/to/append-to.txt << "EOF"  <br/>
export PATH=$HOME/jdk1.8.0_31/bin:$PATH  <br/>
export JAVA_HOME=$HOME/jdk1.8.0_31/  <br/>
EOF
17. 从格式化输出里提取一列(我最常使用的awk技巧) git status -s | awk '{print $2}'  <br/>
写个函数，让我们随时都可以用  <br/>
function col {  <br/>
  awk -v col=$1 '{print $col}'  <br/>
}  <br/>
这使得提取列非常容易，比如  <br/>
$ git status -s | col 2
18. 查占用cpu最多的进程 ps H -eo pid,pcpu | sort -nk2 | tail
19. 查看对应的服务名 ps aux | fgrep 30914  <br/>
    ll /proc/pid     ll /proc/30914
20. 查看某个端口的连接情况 netstat -lap | fgrep port  <br/>
                       lsof -i :port  <br/>
                       netstat -tln | grep 8080 查看端口8080的使用情况
                       
21. CTRL + U - 剪切光标前的内容  <br/>
    CTRL + K - 剪切光标至行末的内容  <br/>
    CTRL + Y - 粘贴  <br/>
    CTRL + E - 移动光标到行末  <br/>
    CTRL + A - 移动光标到行首  <br/>
    ALT + F - 跳向下一个空格  <br/>
    ALT + B - 跳回上一个空格  <br/>
    ALT + Backspace - 删除前一个单词  <br/>
    CTRL + W - 剪切光标前一个单词  <br/>
    Shift + Insert - 向终端内粘贴文本  <br/>
22. “permission denied”  <br/>
sudo !!
23. 暂停并在后台运行命令  <br/>
    CTRL + Z - 暂停应用程序  <br/>
    fg - 重新将程序唤到前  <br/>
    (如在用vi编辑过程中，需要使用命令行)
24. 使用nohup在登出SSH会话后仍运行命令 ，一般和&一起使用
25. ‘在’特定的时间运行Linux命令  <br/>
at 10:38 PM Fri  <br/>
at> cowsay 'hello'  <br/>
at> CTRL + D  <br/>
上面的命令能在周五下午10时38分运行程序cowsay。
26. 如何在不使用Cron的情况调度Linux下的任务  <br/>
$ while true; do date >> date.txt ; sleep 5 ; done &  <br/>
$ while true; do /bin/sh script_name.sh ; sleep 100 ; done &
27. ctrl + l <==> clear
28. 在其它目录运行一个命令，然后自动返回当前工作目录  <br/>
$ (cd /home/avi/Downloads/ && ls -l)
29. 删除大文件   <br/>
$ > /path-to-file/huge_file.log  <br/>
rm -rf /path-to-file/huge_file.log  <br/>
写一个空输出到该文件。用更简单的话说它会清空文件而不会导致你的系统产生大的 I/O 消耗。
30. 在多个 Linux 服务器上运行相同命令  <br/>
for in $i(cat list.txt); do ssh user@$i 'bash command'; done  <br/>
list.txt:ip地址
31. tcpdump -i any -Xnpls0 host 10.199.133.80 >> test.log    <br/>
    tcpdump -i any -Xnpls0 src host 10.199.145.162 and dst host 10.199.145.162 and port 8096 查看请求  <br/>
    tcpdump -i any -Xnpls0 src host 10.199.145.182 and src port 8096  查看返回
32. 安装jq<http://stedolan.github.io/jq/> `curl -O url`
`mv jq /usr/local/bin/jq`
`curl 'https://api.github.com/repos/stedolan/jq/commits?per_page=5' | jq '.'`
33. unzip mobile.war -d mobile
34. 查看关键字并指定显示行数  <br/>
    cat apperror.log|grep -n -A10 -B10 --color=auto '2011:00:00'  <br/>
    cat apperror.log|grep -n -A10 -B10 --color=auto 'Read timed out'  <br/>
    cat error.log|grep -n -A10 -B10 --color=auto 'Read timed out'  <br/>
    -n:显示行号  <br/>
    -A10 -B10:前后十行  <br/>
    查看同步数据是否有此平台  <br/>
    vi正则："sale_platform":"(.){0,10}[f]{1}  <br/>
    -m 显示前几行  <br/>
    cat mshowbackend.access.log |grep -m 5 --color=auto '10.199.170.24'  <br/>
    显示多少行  <br/>
    cat error.log|grep -n -A10 -B10 --color=auto 'Read timed out'|wc -l  <br/>
35. `cd -` 来回切换目录
36. traceroute命令 <http://www.cnblogs.com/peida/archive/2013/03/07/2947326.html>
37. 若执行ssh-add /path/to/xxx.pem是出现这个错误:Could not open a connection to your authentication agent，则先执行如下命令即可：
    `ssh-agent bash`
38. Linux cp时询问是否覆盖,怎样让它不询问直接覆盖 
  1、.bashrc里面注释掉 Alias cp＝'cp -i'
  2、使用 \cp 命令（在cp前加一个'\'）
39. find
<http://www.hollischuang.com/archives/800>
  1. `find / -name filename.txt` 根据名称查找/目录下的filename.txt文件。
  2. `find . -name "*.xml"` 递归查找所有的xml文件
  3. `find . -name "*.xml" |xargs grep "hello world"` 递归查找所有文件内容中包含hello world的xml文件
  4. `grep -H 'spring' *.xml` 查找所以有的包含spring的xml文件
  5. `find ./ -size 0 | xargs rm -f &` 删除文件大小为零的文件
  6. `ls -l | grep '.jar'` 查找当前目录中的所有jar文件
  7. `grep 'test' d*` 显示所有以d开头的文件中包含test的行。
  8. `grep 'test' aa bb cc` 显示在aa，bb，cc文件中匹配test的行。
  9. `grep '[a-z]\{5\}' aa` 显示所有包含每个字符串至少有5个连续小写字符的字符串的行。

40. java 常用命令
java javac jps ,jstat ,jmap, jstack
`jstat -gcutil 25063`
`jstack 2625`

41. 查看机器CPU信息:`cat /proc/cpuinfo`
42. Java中如何获取到线程dump文件
    死循环、死锁、阻塞、页面打开慢等问题，打线程dump是最好的解决问题的途径。所谓线程dump也就是线程堆栈，获取到线程堆栈有两步：
    （1）获取到线程的pid，可以通过使用jps命令，在Linux环境下还可以使用ps -ef | grep java
    （2）打印线程堆栈，可以通过使用jstack pid命令，在Linux环境下还可以使用kill -3 pid
    另外提一点，Thread类提供了一个getStackTrace()方法也可以用于获取线程堆栈。这是一个实例方法，因此此方法是和具体线程实例绑定的，每次获取获取到的是具体某个线程当前运行的堆栈，
<http://www.cnblogs.com/xrq730/p/5060921.html>
43. Linux环境下如何查找哪个线程使用CPU最长
    这是一个比较偏实践的问题，这种问题我觉得挺有意义的。可以这么做：
    （1）获取项目的pid，jps或者ps -ef | grep java，这个前面有讲过
    （2）top -H -p pid，顺序不能改变
    这样就可以打印出当前的项目，每条线程占用CPU时间的百分比。注意这里打出的是LWP，也就是操作系统原生线程的线程号，我笔记本山没有部署Linux环境下的Java工程，因此没有办法截图演示，网友朋友们如果公司是使用Linux环境部署项目的话，可以尝试一下。
    使用"top -H -p pid"+"jps pid"可以很容易地找到某条占用CPU高的线程的线程堆栈，从而定位占用CPU高的原因，一般是因为不当的代码操作导致了死循环。
    最后提一点，"top -H -p pid"打出来的LWP是十进制的，"jps pid"打出来的本地线程号是十六进制的，转换一下，就能定位到占用CPU高的线程的当前线程堆栈了。
<http://www.cnblogs.com/xrq730/p/5060921.html>
44.  Linux 中复制文件到多个目录中
`echo /home/aaronkilik/test/ /home/aaronkilik/tmp | xargs -n 1 cp -v /home/aaronkilik/bin/sys_info.sh`
    目录的路径（dir1、dir2、dir3...dirN）被管道作为输入到 xargs 命令中，含义是：
    -n 1 - 告诉 xargs 命令每个命令行最多使用一个参数，并发送到 cp 命令中。
    cp – 用于复制文件。
    -v – 启用详细模式来显示更多复制细节。


---

#### 日志查看
+ 正则表达式匹配统计

`cat *.log | grep ".*Append \(http:\/\/.*\?\) to .*"`
查看*.log中匹配正则表达式 `.*Append (http:\/\/.*\?) to .*`  的行, 为什么括号前要加斜杠呢? 这是shell中正则表达式比较特殊的地方, 括号还有其他个别符号前需要加斜杠.

+ 将匹配正则表达式的内容抽取出来, 排重, 再统计 <br/>
 sed 's/正则表达式/替换的内容/g', 我们在正则表达式里使用了分组(就是那个括号), 替换内容里用到了\1代表第一个分组, 如果是第二个则\2,以此类推   <br/>
`cat spring.log |grep "com.sishuok.Application" |sed 's/.*\(com.sishuok.Application\).*/\1/g'|uniq|wc -l`  <br/>
`cat spring.log |grep "org.springframework.web" |sed 's/.*\(org.springframework.web\).*/\1/g'`  <br/>
`cat spring.log |grep "org.springframework.web" |grep ']'|sed 's/.*\(org.springframework.web.*]\).*/\1/g'`<br/>
如何避开sed贪婪匹配  <br/>
`cat spring.log |grep "org.springframework.web" |grep ']'|sed 's/.*\(org.springframework.web[^]]*\).*/\1/g'`  <br/>
`cat spring.log |grep "org.springframework.web" |grep ']'|sed 's/.*\(org.springframework.web[^]]*]\).*/\1/g'`  <br/>
去掉末尾]  <br/>
`cat spring.log |grep "org.springframework.web" |grep ']'|sed 's/.*\(org.springframework.web[^]]*]\).*/\1/g'|sed 's/]$//'`  <br/>

+ 筛选记录并按时间段分组，统计数目并排序
`less kxw.log.2016-10-10 |grep '邮件发送成功' |awk '{print $2}' | cut -d ":" -f 1,2|sort | uniq -c | sort -k1,1nr | head -20`

+ 统计文件中出现次数最多的前10个单词
`cat words.txt | sort | uniq -c | sort -k1,1nr | head -10`
<pre>
sort:  对单词进行排序
uniq -c:  显示唯一的行，并在每行行首加上本行在文件中出现的次数
sort -k1,1nr:  按照第一个字段，数值排序，且为逆序
head -10:  取前10行数据
</pre>

+ 按邮箱分组统计数目
`less sendtask.mail.log.2016-10-10 |grep '邮件发送成功' |sed 's/.*邮箱=\(.*\)，标题.*/\1/g'`  <br/>
`less sendtask.mail.log.2016-10-10 |grep '邮件发送成功' |sed 's/.*@\(.*\)，标题.*/\1/g'| tr "[:upper:]" "[:lower:]"|sort | uniq -c | sort -k1,1nr | head -20`  <br/>


+ awk 正则匹配提取

+ 截取一段时间内log日志
“2015-05-04 09:25:55,606 后面跟日志内容 ”这样的
目标是需要将05-04的09:25:55 和09:28:08 之间的日志截取出来：
使用sed命令如下：  <br/>
`sed -n ‘/2015-05-04 09:25:55/,/2015-05-04 09:28:55/p’  logfile`
这样可以精确地截取出来某个时间段的日志。

使用正则表达式:  <br/>
`sed -n ‘/2010-11-17 09:[0-9][0-9]:[0-9][0-9]/,/2010-11-17 16:[0-9][0-9]:[0-9][0-9]/p’  logfile`  <br/>
如果没有问题的话，上面就能筛选出指定的时间段的日志。




常用服务器日志分析命令大全
<http://www.cnblogs.com/nixi8/p/4789817.html>




### svn
+ svn --username=yourname co svn_path  local_path
+ svn checkout address
+ svn commit -m "xx"
+ svn update > /dev/null
+ svn info|grep 'Last Changed Rev'
+ 获取svn 的 URL 信息 : `$ svn info | grep URL` 或 `$ cat .svn/entries |grep http`


### Windows
1.
+ 查看所有的端口占用情况  <br/>
`C:\>netstat -ano|findstr "9050"`  <br/>
+ 查看PID对应的进程  <br/>
`C:\>tasklist|findstr "2016"`  <br/>
+ 结束该进程（或通过任务管理器杀死相关的进程  <br/>
`C:\>taskkill /f /t /im tor.exe`  <br/>
2. Cygwin:<https://cygwin.com/install.html>  <br/>
`ln -s /cygdrive/e /data`  <br/>
`cd /data/`  <br/>
`vi ~/.bashrc`  <br/>
`source ~/.bashrc`  <br/>
3. `set  localecho` 打开telnet本地回显功能

### vi
1. 替换  <br/>
　　:s/old/new用new替换行中首次出现的old  <br/>
　　: s/old/new/g 用new替换行中所有出现的old  <br/>
　　:#,# s/old/new/g用new替换从第＃行到第＃行中出现的old  <br/>
　　：% s/old/new/g用new替换整篇中出现的old  <br/>
　　如果替换的范围较大时，在所有的命令尾加一个c命令，强制每个替换需要用户进行确认，例如:s/old/new/c 或s/old/new/gc
2. gg 最上  <br/>
   G 最下  <br/>
   粘贴 crtl+shift + o
3. 保存文件并退出 :x <==> :wq
4. 自动补全   Ctrl+n
5. 把 Vim 变为十六进制编辑器 :%!xxd 恢复 :%!xxd -r
6. 把光标下的文字置于屏幕中央 zz
7. 把当前文件转化为网页 :%TOhtml
8. 行尾 $ ，行首 0
9. /word：向下寻找一个名称为word的字符串。
   ?word：向上寻找一个名称为word的字符串。
10. u：复原前一个操作(undo)。 [Ctrl]+r：重做上一个操作。(redo)
11. yy：复制光标所在的一行。
12. O(pen for Read-Only):打开成只读文件。
    E(dit):用正常方式打开要编辑的文件，并不会
    R(ecover)：加载暂存文件的内容。
    D(elete)：如果你确定这个暂存文件是没有用的，则可以删除。
    Q(uit)：不进行任何操作，回到命令行。
    A(bort)：忽略这个编辑行为，和Q类似。
13. P → 粘贴
    yy → 拷贝当前行当行于 ddP
14. 



<https://linux.cn/article-4669-1.html>

### maven
1. `mvn dependency:tree -Dverbose -Dincludes=vutil` ,`mvn dependency:tree -Dverbose -Dincludes=<groupId>:<artifactId>`
2. `mvn versions:set -DnewVersion=2.6-SNAPSHOT`
3. `maven dependency:sources` 下载项目下所有的源码
4. `exec-maven-plugin` 执行指定程序main方法 或外部程序
5. 查找maven依赖 :`g:"org.aeonbits.owner" AND a:"owner"`

### gradle
1. `gradle dependencies`   ->> find out a dependency tree
2. `compile('com.kxw:hbase-api:1.0.0') {
               exclude group: 'org.slf4j', module: 'slf4j-log4j12'
   }`
3. 打包时任务实现:(1)编写maven插件 (2)编写gradle脚本
4. gradle依赖冲突解决,可以指定某个组的版本号是什么,防止依赖版本混乱,而不是exclude掉

### chrome console
1. 查看中文转码：   <br/>
decodeURIComponent('%E5%9D%87%E7%A0%81');  <br/>
"均码"
encodeURI() 是 Javascript 中真正用来对 URL 编码的函数。它着眼于对整个URL进行编码。
encodeURIComponent() 。它的作用是对 URL 中的参数进行编码，记住是对参数，而不是对整个 URL 进行编码。
正确的用法：encodeURIComponent() 着眼于对单个的参数进行编码：
```javascript
 param = "http://www.b.com?t=123&s=456"; // 要被编码的参数
 URL = "http://www.a.com?foo="+encodeURIComponent(param);
//"http://www.a.com?foo=http%3A%2F%2Fwww.b.com%3Ft%3D123%26s%3D456"
```
2.

### github

`git config user.name "kingson4wu"`  <br/>
`git config user.email "Kingson_Wu@163.com"`



### Knowledge Point
1. JVM使用-XX:PermSize设置非堆内存初始值，默认是物理内存的1/64；  <br/>
那么，如果是物理内存4GB，那么64分之一就是64MB，这就是PermSize默认值，也就是永生代内存初始大小。  <br/>
设置Maven的JVM参数：-Xms512m -Xmx2g -XX:PermSize=64m -XX:MaxPermSize=256m
2. User-Agent: Fiddler  <br/>
   Host: 10.199.146.42:8888  <br/>
   Content-Length: 112  <br/>
   Content-type: application/x-www-form-urlencoded
3. 502 网关错误  <br/>
   301 永久重定向,告诉客户端以后应从新地址访问.  <br/>
   302 作为HTTP1.0的标准,以前叫做Moved Temporarily ,现在叫Found. 现在使用只是为了兼容性的处理,包括PHP的默认Location重定向用的也是302.  <br/>
   但是HTTP 1.1 有303 和307作为详细的补充,其实是对302的细化  <br/>
   303：对于POST请求，它表示请求已经被处理，客户端可以接着使用GET方法去请求Location里的URI。  <br/>
   307：对于POST请求，表示请求还没有被处理，客户端应该向Location里的URI重新发起POST请求
4. POST content-type可指定普通方式,还是文件上传
5. 制作工具:(1)maven插件(2)java  swing jar包


### coding
1. 改了类字段要手动clean build一下，tomcat插件不能热部署  <br/>
   项目编码不一致，json转换会有问题
2. System.getProperties().put("http.proxyHost","127.0.0.1");  <br/>
   System.getProperties().put("http.proxyPort","8888");
3. httpclient 添加代理
   ```
           //创建HttpClientBuilder
           HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
           //HttpClient
           CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

           HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
           RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

           HttpGet httpGet = new HttpGet(url);
           httpGet.setConfig(config);

           //4.1.3
           httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, new HttpHost("100.84.250.97",8888, null))
   ```
3. 枚举转化  <br/>
   `abc.setAppType(AppType.valueOf(((String) Map.get("app_type")).toUpperCase()));`  <br/>
   `CodeConstants.LIST_SWITCH.ordinal();`
4.
```java
do{
break;
}while(false);
```
5. `request.getHeader("HTTP_CDN_SRC_IP");`   <br/>
   `request.getHeader("X-Forwarded-For");`
6. Arrays.asList(object);
7.  response.setHeader("ContentType", "text/html");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(text);/*输出提示信息*/

8. `<? super T>`包括T在内的任何T的父类，`<? extends T>`表示包括T在内的任何T的子类  </br>
请记住PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super。
9. 只要是在方法内定义使用StringBuilder,完全不会有线程安全问题.
10. `@Scope("prototype")`  </br>
    最佳实践：  </br>
    1、不要在controller中定义成员变量。  </br>
    2、万一必须要定义一个非静态成员变量时候，则通过注解@Scope("prototype")，将其设置为多例模式。  
11. 多种条件判断可以在入口处全部判断完，用一个boolean值设置，后面根据这个布尔值判断即可。
12. `request.getRequestURI().substring(request.getContextPath().length())`
13. 怎样处理InterruptedException<http://www.importnew.com/17027.html>  <br/>
<http://www.ibm.com/developerworks/library/j-jtp05236/>  <br/>
```
try {
  Thread.sleep(100);
} catch (InterruptedException ex) {
  Thread.currentThread().interrupt(); // Here!
  throw new RuntimeException(ex);
}
```
14. Comparable一般表示类的自然序，比如定义一个Student类，学号为默认排序  </br>
Comparator一般表示类在某种场合下的特殊分类，需要定制化排序。比如现在想按照Student类的age来排序
15. 没有javadoc，就是当前方法没有自己的注释
    @see就是，用@see后面跟的类的方法的注释作为当前方法的注释
    
    例如可以这么写：
    /* (non-Javadoc)
    * @see com.xiaoxiang.common.util.IPageSupport#hasPreviousPage()
    */
16. Java 7 新的 try-with-resources 语句，自动资源释放,前提是，这些可关闭的资源必须实现 java.lang.AutoCloseable 接口
17. 不可变对象对多线程有什么帮助
    不可变对象保证了对象的内存可见性，对不可变对象的读取不需要进行额外的同步手段，提升了代码执行效率。
18. 由于Java采用抢占式的线程调度算法，因此可能会出现某条线程常常获取到CPU控制权的情况，为了让某些优先级比较低的线程也能获取到CPU控制权，
可以使用Thread.sleep(0)手动触发一次操作系统分配时间片的操作，这也是平衡CPU控制权的一种操作。
19. 高并发、任务执行时间短的业务怎样使用线程池？并发不高、任务执行时间长的业务怎样使用线程池？并发高、业务执行时间长的业务怎样使用线程池？
    这是我在并发编程网上看到的一个问题，把这个问题放在最后一个，希望每个人都能看到并且思考一下，因为这个问题非常好、非常实际、非常专业。关于这个问题，个人看法是：
    （1）高并发、任务执行时间短的业务，线程池线程数可以设置为CPU核数+1，减少线程上下文的切换
    （2）并发不高、任务执行时间长的业务要区分开看：
    　　a）假如是业务时间长集中在IO操作上，也就是IO密集型的任务，因为IO操作并不占用CPU，所以不要让所有的CPU闲下来，可以加大线程池中的线程数目，让CPU处理更多的业务
    　　b）假如是业务时间长集中在计算操作上，也就是计算密集型任务，这个就没办法了，和（1）一样吧，线程池中的线程数设置得少一些，减少线程上下文的切换
    （3）并发高、业务执行时间长，解决这种类型任务的关键不在于线程池而在于整体架构的设计，看看这些业务里面某些数据是否能做缓存是第一步，增加服务器是第二步，至于线程池的设置，设置参考（2）。
    最后，业务执行时间长的问题，也可能需要分析一下，看看能不能使用中间件对任务进行拆分和解耦。
    <http://www.cnblogs.com/xrq730/p/5060921.html>
20. <code>
        tag.setCreateTime(createTime);
        tag.setModifyTime(createTime);//防止应用服务器的时间和数据库服务器的时间不一致
    </code>
    

###cache
1. redis访问超时的情况下，不要回源设置，否则进一步增大redis的压力，引起redis incoming暴增。

### memcached
+  Memcached内存管理的局限性导致尽量不能让KEY永远不过期:<http://blog.csdn.net/tenebaul/article/details/8141673>
+ memcached完全剖析–1. memcached的基础:<http://kb.cnblogs.com/page/42731/>


###serialization
+ com.thoughtworks.xstream.annotations
  java 对象和xml的装换
  <http://www.blogjava.net/DLevin/archive/2012/11/30/392240.html>
+ jackson
  java 对象和json的装换

###java
1. 出现java.lang.UnsupportedClassVersionError 错误的原因，是因为我们使用高版本的JDK编译的Java class文件试图在较低版本的JVM上运行，所报的错误。
   因为，高版本的JDK生成的class文件使用的格式，可能与低版本的JDK的.class文件格式不同。这样，低版本的JVM无法解释执行这个.class文件，会抛出java.lang.UnsupportedClassVersionError不支持的Class版本错误。
   在mvn中可以指定打包的JDK版本。！！！
2. ConnectException ： 指的是服务器请求超时  <br/>
   SocketTimeoutException:指的是服务器响应超时
3. Vector, Hashtable, Properties 和 Stack 都是同步的类，所以它们都线程安全的，可以被使用在多线程环境中  <br/>
   使用Collections.synchronizedList(list)) 方法，可以保证list类是线程安全的  <br/>
   使用java.util.Collections.synchronizedSet()方法可以保证set类是线程安全的  <br/>
   Properties 是Hashtable的子类。它被用于维护值的list，其中它们的键、值都是String类型  <br/>
4. 查看java堆栈信息
首先得找到程序的进程号`jps -lvm`,这个命令可以显示出程序，对应的进程号等
查看对应程序的堆栈信息，就是找到进程号，执行jstack命令
  `jstack -l 5659 ` 这样就可以看堆栈信息了，这个是实时刷的，可以 写入一个文件中进行查看：  jstack -l 5659  > 1.txt
<http://blog.csdn.net/arkblue/article/details/39718947>
5. java不能输出引用的内存地址,用java时不用考虑内存的问题,内存操作是不可见的
6. 
java classloader:<http://www.cnblogs.com/Lawson/archive/2012/07/31/2616623.html>
<http://www.importnew.com/15268.html>
<http://blog.jobbole.com/96145/>

new是在编译时就要检查.class类型和路径
class.forName("xxxx").newInstance() 是在运行时找到改路径的类进行实列化 相当于先检查路径然后再实列化。

new 是新建一个对象
Class.forName(xxx.xx.xx) 返回的是一个类、
Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的 defineClass 方法自动构造的
<http://www.tuicool.com/articles/7vUJ7f>
7. String.format 替代使用 +
    

###前端
1. <div  style="padding-bottom: 20px"> 底部距离
2. web.xml servlet标准。查看spring-web.jar meta文件

###js
1. ` var str ="\"51666:83;48722:973717\": {\"vSkuId\": \"955683827282022402\",\"barcode\": \"02QS95930234\",\"msizeId\": 193553790,\"stock\": 0,\"type\": 1,\"mid\": 75206769,\"price\": {\"marketPrice\": \"1588\",\"salePrice\": \"116\",\"saleSavePrice\": null,\"salePriceType\": \"0\",\"salePriceTips\": null,\"discount\": \"0.7折\"}}";
        /*var arr = str.split("msizeId");
        alert(arr[1]);
        var ele = arr[1];
        alert(ele.substr(2,ele.indexOf(",")-2));*/
        var ele = (str.match("msizeId\":[ ]*[0-9]+").toString()).match("[0-9]+");
        alert(ele);`


###Intellij IDEA
1. `Ctrl+N`   查找类  <br/>
   `Ctrl+Shift+N` 查找文件  <br/>
   `Ctrl+X` 删除行  <br/>
   `Ctrl+Alt+ left/right` 返回至上次浏览的位置  <br/>
   `Ctrl+Q` 方法提示(View-Quick Documentation) mac `control + j` <br/>
   `shift+esc`  <br/>
   `Ctrl+Alt+H` 查看调用链  <br/>
   `Ctrl+Alt+B` `Ctrl + Alt + left click`跳转到实现类  <br/>
   `crtl +shift +f` 全文查找  <br/>
   `ctrl + H` 查看类的继承结构     <br/>
   `Ctrl+Alt+L` 格式化      <br/>
   `Crtl+shift +Z` 反撤销   <br/>
   `Crtl + E` 或 `Command + E` 打开最近访问的文件    <br/>
   `Crtl + shift + E` 或 `Command+ shift + E` 打开最近编辑的文件    <br/>
   查找文件夹 double shift ,然后输入/   <br/>
   `Command + shift + ENTER` 快速补全分号   <br/>
   `Command + shift + V` 粘贴板历史   <br/>
   Language Injection : 把光标置于空字符串中,`Alter Enter`,选择JSON   <br/>
   `Command + shift + A` ,rest client  <br/>
   Step into: `shift F7` 选择要Debug哪一个方法  <br/>
2. 涉及到jar包开发的，不要用社区版，debug很有问题。
3. idea code surround with
4. Intellij IDEA 自动生成 serialVersionUID
Setting->Inspections->Serialization issues->Serializable class without ’serialVersionUID’ 
选上以后，在你的class中：Alt+Enter就会提示自动创建serialVersionUID了。
5. git resolve conflict

### fiddler
1. tomcat 添加代理 ，vm option中添加 `-Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=8888`
2. http 请求添加本地文件替换 ： AutoResponder
3. http 请求添加本地文件mock返回 ： AutoResponder
4. mvnDebug clean install  远程调试
如果是项目中的代码直接在idea的插件debug run install 不用远程调试

### wireshark
<http://www.9upk.com/article/2537.html>

###mobile-dev
1. ReactiveCocoa ios
2. rxjava
使用retrolambda这个gradle插件，可以在android代码中使用lambda
RxAndroid,Retrofit(Restful API)

###mysql
1. which mysql
2. ./mysql -h "192.168.1.244" -u root -p  
3. use databasename;
4. SELECT FROM_UNIXTIME(unix_timestamp(now()), '%Y%m%d%H%i%S');
5. php wordpress 的文章保存用的是longtext类型
6. 插入操作时若有相同的key则改为更新操作 `insert into ... on duplicate key update ....`
7. mysql监控、性能调优及三范式理解<http://www.cnblogs.com/lingfengblogs/p/4165715.html>
101 个 MySQL 的调节和优化的提示<http://www.oschina.net/translate/101-tips-to-mysql-tuning-and-optimization>
MySQL索引背后的数据结构及算法原理<http://outofmemory.cn/mysql/mysql-index-arithmetic>
MySQL性能优化的最佳20+条经验<http://coolshell.cn/articles/1846.html>


#### 异步化DAO的设计和实践
<http://my.oschina.net/ainilife/blog/631366>
<https://github.com/ainilife/zebra-dao>

### redis
+ redis 没有设置过期时间会过期
不会过期。
但是这样说有点绝对。一般情况是这样，当你配置中开启了超出最大内存限制就写磁盘的话，那么这些没有设置过期时间的key可能会被写到磁盘上。
假如没设置。那么REDIS将使用LRU机制，将内存中的老数据删除，并写入新数据。
<http://zhidao.baidu.com/question/1689973548087759188.html>
+ 使用Redis的五个注意事项 :<http://blog.sina.com.cn/s/blog_56c9b55c0100ylbo.html>
+ JedisPool和ShardedJedisPool有什么区别
Redis 默认是单机环境使用的。 数据量较大时需要shard（多机环境），这个时候要用ShardedJedis。
ShardedJedis是基于一致性哈希算法实现的分布式Redis集群客户端


### leveldb
使用磁盘存储数据，对写要求比较苛刻，需要每秒达100000TPS，读的时候需要能10000TPS左右，不能占用太多内存。
单节点满足这个要求的常见有Redis、Memcached等，但是这个东西太费内存了，代价比较高，不太合适。
找来找去，找到Leveldb这个神器了，在写的时候对内存要求不高，读的时候根据性能要求的不同需要对应的内存，如果使用SSD就是完美搭配了。
<http://blog.csdn.net/zhangzhaokun/article/details/8091462>

### Nginx
+ 应用通过nginx获取，ngnix会自动加上x-forwarded-for保存原始ip，request.remoteAddress获取的是最近的访问ip
+ nginx配置文件直接返回, 简单的微服务,性能好

### HTTP
HTTP中的URL长度限制
<http://www.cnblogs.com/lengyuhong/archive/2012/02/04/2330130.html>
nginx 
由于现在项目中主要用到nginx，所以强调下它的设置参数：large_client_header_buffers
该参数对nginx服务器接受客户端请求的头信息时所分配的最大缓冲区的大小做了限制，也就是nginx服务器一次接受一个客户端请求可就收的最大头信息大小。这个头不仅包含 request-line，还包括通用信息头、请求头域、响应头域的长度总和。这也相当程度的限制了url的长度。
nginx服务器默认的限制是4K或者8K，这是根据服务器的硬件配置有关的，一般为内存一页的大小，目前大部分为4K，即4096字节。



###python
1. Jpython
2. python结合shell命令搭建简单http服务


###Stress Testing
Jmeter, abTest,
LoadRunner  </br>


1. wrk::
+ <http://www.oschina.net/p/wrk>
+ <http://www.aikaiyuan.com/6168.html>
`git clone https://github.com/wg/wrk.git`
`cd wrk/`
` make -j8`
`./wrk  -t12 -c40 -d30s http://www.aikaiyuan.com/index.html`
-t12  使用 12 个线程
-c    使用 40连接
-d30s 请求30秒

wrk -t12 -c400 -d30s http://127.0.0.1:8080/index.html
 This runs a benchmark for 30 seconds, using 12 threads, and keeping
  400 HTTP connections open.


<pre>
12 threads and 800 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   529.87ms  557.68ms   2.00s    83.10%
    Req/Sec   159.51    176.76     1.11k    91.37%
  77424 requests in 1.00m, 103.97MB read
  Socket errors: connect 0, read 0, write 0, timeout 6037
Requests/sec:   1288.60
</pre>

latency，也就是延迟时间
STDEV 基于样本估算标准偏差。标准偏差反映数值相对于平均值(mean) 的离散程度。

` ulimit -n 65536`设置linux文件打开的...

2. Weighttp
<http://www.xuebuyuan.com/2085056.html>
<http://redmine.lighttpd.net/projects/weighttp/wiki>


### profier
+ jProfiler,jvisualvm
+  linux下安装使用jprofiler8（tomcat）:<http://blog.csdn.net/ai6740165/article/details/19162969>
把linux整个tomcat拉下来本地，生成startup_jprofiler.sh
然后start本地刚刚建好的jprofiler
+ <http://www.ej-technologies.com/download/jprofiler/files>

### 性能测试工具
1. oprofile
+ <http://blog.chinaunix.net/uid-13746440-id-3152484.html>,<http://oprofile.sourceforge.net/download/>
+ `yum install binutils-devel -y`
+ `sudo groupadd oprofile`
+ `sudo useradd -g oprofile oprofile`
+ <http://19542002.blog.163.com/blog/static/13393238220101032421418/>
+ <http://blog.csdn.net/raintungli/article/details/6909681>


2. perf
<http://blog.chinaunix.net/uid-10540984-id-3854969.html>
+ `perf stat ./t1`
+ `perf top`
+ 使用 perf record, 解读 report
`perf record -e cpu-clock ./t1`
`perf report`
+  `perf stat -e syscalls:sys_enter ls`
   `perf stat -p 17792`

3. Freebencher (java)
<https://github.com/chenjianjx/freebencher>

4. 如何生成每秒百万级别的 HTTP 请求
<http://blog.jobbole.com/87509/>
  1. ‘ab’，Apache Bench
  2. Httperf
  3. Apache Jmeter
  4. Tsung: 重型的（heavy-duty）、分布式的、多协议测试工具
     


### tomcat
+ Tomcat Connector运行模式<http://www.365mini.com/page/tomcat-connector-mode.htm>




### user
1. 请求得不到及时响应时,会导致更多得请求量,这是用户正常得反应.一般前端会挡的,否则出问题不停重刷会引起更严重的雪崩.
2.




-----

### configcenter
1. 打包resouce目录的插件
2. IOutils.tostring(new URL) #网络IO,文件IO
3. XMLUtils
4. DigestUtils.md5Hex
5. AIMDBackoffManager
<https://github.com/AsyncHttpClient/async-http-client>
6. spring整个加载过程
7. beanInfo
8. paxos
9. Atom projectManager

HikariCP：高性能JDBC连接工具。
JDBI：便捷的JDBC抽象。
MapDB：以磁盘或堆内存中并发集合为基础的嵌入式数据库引擎。

处理时间和日期的开发库。
Joda-Time：在Java 8发布前，Joda-Time是实际使用的时间日期库标准。
Time4J：高级时间和日期库。

Disruptor：线程间消息传递开发库

记录应用程序行为日志的开发库。

Apache Log4j 2：使用强大的插件和配置架构进行完全重写。
kibana：分析及可视化日志文件。
Logback：强健的日期开发库，通过Groovy提供很多有趣的选项。
logstash：日志文件管理工具。
Metrics：通过JMX或HTTP发布参数，并且支持存储到数据库。
SLF4J：日志抽象层，需要与具体的实现配合使用。

用来开发响应式应用程序的开发库。

Reactive Streams：异步流处理标准，支持非阻塞式反向压力（backpressure）。
Reactor：构建响应式快速数据（fast-data）应用程序的开发库。
RxJava：通过JVM可观察序列（observable sequence）构建异步和基于事件的程序。(google模仿微软...)

通用工具类函数库。

Apache Commons：提供各种用途的函数，比如配置、验证、集合、文件上传或XML处理等。
args4j：命令行参数解析器。
CRaSH：为运行进行提供CLI。
Gephi：可视化跨平台网络图形化操作程序。
Guava：集合、缓存、支持基本类型、并发函数库、通用注解、字符串处理、I/O等。
JADE：构建、调试多租户系统的框架和环境。
javatuples：正如名字表示的那样，提供tuple支持。尽管目前tuple的概念还有留有争议。
JCommander：命令行参数解析器。官网
Protégé：提供存在论（ontology）编辑器以及构建知识系统的框架。

网络爬虫
用于分析网站内容的函数库。
Apache Nutch：可用于生产环境的高度可扩展、可伸缩的网络爬虫.
Crawler4j：简单的轻量级网络爬虫。
JSoup：刮取、解析、操作和清理HTML。

<https://github.com/jobbole/awesome-java-cn>

<http://www.phpddt.com/demo/metronic/index.html>

C++主要用到栈,变量预编译好.Java主要用堆.线程,管程,协程.堆外内存.一次拷贝,不用两次拷贝.


weka，mahout
kafka一个高吞吐的分布式队列服务
spark一个高速的大数据计算引擎
flume		高可靠的，分布式的海量日志采集、聚合和传输的系统
storm	一个分布式的、容错的实时计算系统
zookeeper	一个分布式的，开放源码的分布式应用程序协调服务
twemproxy	Redis/Memcached代理服务
flume kafka
基于HTML5的应用性能也随之提升的同时，像jQuery Mobile，Angular和Ionic这类JavaScript框架也让HTML5应用的外观看起来很棒。

---
IOS swift
前端H5，js
微信公众平台，php
网站，新浪云
scala，perl，nodejs
php lnmp
---
淘宝开放平台TOP （open.taobao.com）
京东开放平台JOS （jos.jd.com）
微信开放平台 （open.weixin.qq.com）
---
