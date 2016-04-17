
#### 在JDK的bin目彔下,包含了java命令及其他实用工具
<http://www.hollischuang.com/archives/308>
jps:查看本机的Java中进程信息。
jstack:打印线程的栈信息,制作线程Dump。
jmap:打印内存映射,制作堆Dump。
jconsole:简易的可视化控制台。
jvisualvm:功能强大的控制台。
jstat:性能监控工具。
jhat:内存分析工具。

#### Java Dump
Java虚拟机的运行时快照。将Java虚拟机运行时的状态和信息保存到文件。
线程Dump,包含所有线程的运行状态。纯文本格式。
堆Dump,包含线程Dump,幵包含所有堆对象的状态。二进制格式。

#### 制作Java Dump
1. 使用Java虚拟机制作Dump, 指示虚拟机在发生内存不足错误时,自动生成堆Dump `-XX:+HeapDumpOnOutOfMemoryError`
2. 使用图形化工具制作Dump,使用JDK(1.6)自带的工具:Java VisualVM。
3. 使用命令行制作Dump,jstack:打印线程的栈信息,制作线程Dump。jmap:打印内存映射,制作堆Dump。

---
### jPS
<http://www.hollischuang.com/archives/105>
jps(Java Virtual Machine Process Status Tool)是JDK 1.5提供的一个显示当前所有java进程pid的命令

jdk中的jps命令可以显示当前运行的java进程以及相关参数，它的实现机制如下：
java程序在启动以后，会在java.io.tmpdir指定的目录下，就是临时文件夹里，生成一个类似于hsperfdata_User的文件夹，
这个文件夹里（在Linux中为/tmp/hsperfdata_{userName}/），有几个文件，名字就是java进程的pid，因此列出当前运行的java进程，
只是把这个目录里的文件名列一下而已。 至于系统的参数什么，就可以解析这几个文件获得。

-q 只显示pid，不显示class名称,jar文件名和传递给main 方法的参数
-m 输出传递给main 方法的参数，在嵌入式jvm上可能是null
-l 输出应用程序main class的完整package名 或者 应用程序的jar文件完整路径名
-v 输出传递给JVM的参数 在这里，在启动main方法的时候

PS:jps命令有个地方很不好，似乎只能显示当前用户的java进程，要显示其他用户的还是只能用unix/linux的ps命令。

---
### Jstack
<http://www.hollischuang.com/archives/110>
jstack是java虚拟机自带的一种堆栈跟踪工具。
jstack命令主要用来查看Java线程的调用堆栈的，可以用来分析线程问题（如死锁）。

#### 线程状态

想要通过jstack命令来分析线程的情况的话，首先要知道线程都有哪些状态，下面这些状态是我们使用jstack命令查看线程堆栈信息时可能会看到的线程的几种状态：
NEW,未启动的。不会出现在Dump中。
RUNNABLE,在虚拟机内执行的。
BLOCKED,受阻塞并等待监视器锁。
WATING,无限期等待另一个线程执行特定操作。
TIMED_WATING,有时限的等待另一个线程的特定操作。
TERMINATED,已退出的。

---
### Jmap
<http://www.hollischuang.com/archives/303>
+ 查看java 堆（heap）使用情况,执行命令： `jmap -heap 31846`
+ 查看堆内存(histogram)中的对象数量及大小。执行命令： `jmap -histo 3331`
  `jmap -histo:live` 这个命令执行，JVM会先触发gc，然后再统计信息。
+ 将内存使用的详细情况输出到文件，执行命令： `jmap -dump:format=b,file=heapDump 6900`
  然后用jhat命令可以参看 jhat -port 5000 heapDump 在浏览器中访问：http://localhost:5000/ 查看详细信息
  这个命令执行，JVM会将整个heap的信息dump写入到一个文件，heap如果比较大的话，就会导致这个过程比较耗时，并且执行的过程中为了保证dump的信息是可靠的，所以会暂停应用。

#### 总结

1. 如果程序内存不足或者频繁GC，很有可能存在内存泄露情况，这时候就要借助Java堆Dump查看对象的情况。
2. 要制作堆Dump可以直接使用jvm自带的jmap命令
3. 可以先使用jmap -heap命令查看堆的使用情况，看一下各个堆空间的占用情况。
4. 使用jmap -histo:[live]查看堆内存中的对象的情况。如果有大量对象在持续被引用，并没有被释放掉，那就产生了内存泄露，就要结合代码，把不用的对象释放掉。
5. 也可以使用 jmap -dump:format=b,file=<fileName>命令将堆信息保存到一个文件中，再借助jhat命令查看详细内容
6. 在内存出现泄露、溢出或者其它前提条件下，建议多dump几次内存，把内存文件进行编号归档，便于后续内存整理分析。

---
### jstat
<http://www.hollischuang.com/archives/481>
jstat(JVM Statistics Monitoring Tool)是用于监控虚拟机各种运行状态信息的命令行工具。
他可以显示本地或远程虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据，在没有GUI图形的服务器上，它是运行期定位虚拟机性能问题的首选工具。

jstat位于java的bin目录下，主要利用JVM内建的指令对Java应用程序的资源和性能进行实时的命令行的监控，包括了对Heap size和垃圾回收状况的监控。
可见，Jstat是轻量级的、专门针对JVM的工具，非常适用。

选项option代表这用户希望查询的虚拟机信息，主要分为3类：类装载、垃圾收集和运行期编译状况

<pre>
常见术语

1、jstat –class<pid> : 显示加载class的数量，及所占空间等信息。

Loaded 装载的类的数量 Bytes 装载类所占用的字节数 Unloaded 卸载类的数量 Bytes 卸载类的字节数 Time 装载和卸载类所花费的时间

2、jstat -compiler <pid>显示VM实时编译的数量等信息。

Compiled 编译任务执行数量 Failed 编译任务执行失败数量 Invalid 编译任务执行失效数量 Time 编译任务消耗时间 FailedType 最后一个编译失败任务的类型 FailedMethod 最后一个编译失败任务所在的类及方法

3、jstat -gc <pid>: 可以显示gc的信息，查看gc的次数，及时间。

S0C 年轻代中第一个survivor（幸存区）的容量 (字节) S1C 年轻代中第二个survivor（幸存区）的容量 (字节) S0U 年轻代中第一个survivor（幸存区）目前已使用空间 (字节) S1U 年轻代中第二个survivor（幸存区）目前已使用空间 (字节) EC 年轻代中Eden（伊甸园）的容量 (字节) EU 年轻代中Eden（伊甸园）目前已使用空间 (字节) OC Old代的容量 (字节) OU Old代目前已使用空间 (字节) PC Perm(持久代)的容量 (字节) PU Perm(持久代)目前已使用空间 (字节) YGC 从应用程序启动到采样时年轻代中gc次数 YGCT 从应用程序启动到采样时年轻代中gc所用时间(s) FGC 从应用程序启动到采样时old代(全gc)gc次数 FGCT 从应用程序启动到采样时old代(全gc)gc所用时间(s) GCT 从应用程序启动到采样时gc用的总时间(s)

4、jstat -gccapacity <pid>:可以显示，VM内存中三代（young,old,perm）对象的使用和占用大小

NGCMN 年轻代(young)中初始化(最小)的大小(字节) NGCMX 年轻代(young)的最大容量 (字节) NGC 年轻代(young)中当前的容量 (字节) S0C 年轻代中第一个survivor（幸存区）的容量 (字节) S1C 年轻代中第二个survivor（幸存区）的容量 (字节) EC 年轻代中Eden（伊甸园）的容量 (字节) OGCMN old代中初始化(最小)的大小 (字节) OGCMX old代的最大容量(字节) OGC old代当前新生成的容量 (字节) OC Old代的容量 (字节) PGCMN perm代中初始化(最小)的大小 (字节) PGCMX perm代的最大容量 (字节)
PGC perm代当前新生成的容量 (字节) PC Perm(持久代)的容量 (字节) YGC 从应用程序启动到采样时年轻代中gc次数 FGC 从应用程序启动到采样时old代(全gc)gc次数

5、jstat -gcutil <pid>:统计gc信息

S0 年轻代中第一个survivor（幸存区）已使用的占当前容量百分比 S1 年轻代中第二个survivor（幸存区）已使用的占当前容量百分比 E 年轻代中Eden（伊甸园）已使用的占当前容量百分比 O old代已使用的占当前容量百分比 P perm代已使用的占当前容量百分比 YGC 从应用程序启动到采样时年轻代中gc次数 YGCT 从应用程序启动到采样时年轻代中gc所用时间(s) FGC 从应用程序启动到采样时old代(全gc)gc次数 FGCT 从应用程序启动到采样时old代(全gc)gc所用时间(s) GCT 从应用程序启动到采样时gc用的总时间(s)

6、jstat -gcnew <pid>:年轻代对象的信息。

S0C 年轻代中第一个survivor（幸存区）的容量 (字节) S1C 年轻代中第二个survivor（幸存区）的容量 (字节) S0U 年轻代中第一个survivor（幸存区）目前已使用空间 (字节) S1U 年轻代中第二个survivor（幸存区）目前已使用空间 (字节) TT 持有次数限制 MTT 最大持有次数限制 EC 年轻代中Eden（伊甸园）的容量 (字节) EU 年轻代中Eden（伊甸园）目前已使用空间 (字节) YGC 从应用程序启动到采样时年轻代中gc次数 YGCT 从应用程序启动到采样时年轻代中gc所用时间(s)

7、jstat -gcnewcapacity<pid>: 年轻代对象的信息及其占用量。

NGCMN 年轻代(young)中初始化(最小)的大小(字节) NGCMX 年轻代(young)的最大容量 (字节) NGC 年轻代(young)中当前的容量 (字节) S0CMX 年轻代中第一个survivor（幸存区）的最大容量 (字节) S0C 年轻代中第一个survivor（幸存区）的容量 (字节) S1CMX 年轻代中第二个survivor（幸存区）的最大容量 (字节) S1C 年轻代中第二个survivor（幸存区）的容量 (字节) ECMX 年轻代中Eden（伊甸园）的最大容量 (字节) EC 年轻代中Eden（伊甸园）的容量 (字节) YGC 从应用程序启动到采样时年轻代中gc次数 FGC 从应用程序启动到采样时old代(全gc)gc次数

8、jstat -gcold <pid>：old代对象的信息。

PC Perm(持久代)的容量 (字节) PU Perm(持久代)目前已使用空间 (字节) OC Old代的容量 (字节) OU Old代目前已使用空间 (字节) YGC 从应用程序启动到采样时年轻代中gc次数 FGC 从应用程序启动到采样时old代(全gc)gc次数 FGCT 从应用程序启动到采样时old代(全gc)gc所用时间(s) GCT 从应用程序启动到采样时gc用的总时间(s)

9、stat -gcoldcapacity <pid>: old代对象的信息及其占用量。

OGCMN old代中初始化(最小)的大小 (字节) OGCMX old代的最大容量(字节) OGC old代当前新生成的容量 (字节) OC Old代的容量 (字节) YGC 从应用程序启动到采样时年轻代中gc次数 FGC 从应用程序启动到采样时old代(全gc)gc次数 FGCT 从应用程序启动到采样时old代(全gc)gc所用时间(s) GCT 从应用程序启动到采样时gc用的总时间(s)

10、jstat -gcpermcapacity<pid>: perm对象的信息及其占用量。

PGCMN perm代中初始化(最小)的大小 (字节) PGCMX perm代的最大容量 (字节)
PGC perm代当前新生成的容量 (字节) PC Perm(持久代)的容量 (字节) YGC 从应用程序启动到采样时年轻代中gc次数 FGC 从应用程序启动到采样时old代(全gc)gc次数 FGCT 从应用程序启动到采样时old代(全gc)gc所用时间(s) GCT 从应用程序启动到采样时gc用的总时间(s)

11、jstat -printcompilation <pid>：当前VM执行的信息。

Compiled 编译任务的数目 Size 方法生成的字节码的大小 Type 编译类型 Method 类名和方法名用来标识编译的方法。类名使用/做为一个命名空间分隔符。方法名是给定类中的方法。上述格式是由-XX:+PrintComplation选项进行设置的
</pre>


---
### jhat
<http://www.hollischuang.com/archives/1047>
jhat(Java Heap Analysis Tool),是一个用来分析java的堆情况的命令。之前的文章讲到过，使用jmap可以生成Java堆的Dump文件。
生成dump文件之后就可以用jhat命令，将dump文件转成html的形式，然后通过http访问可以查看堆情况。

jhat命令解析会Java堆dump并启动一个web服务器，然后就可以在浏览器中查看堆的dump文件了。

+ 生成dump文件
`jmap -dump:format=b,file=heapDump 62247`

除了使用jmap命令，还可以通过以下方式：
1、使用 jconsole 选项通过 HotSpotDiagnosticMXBean 从运行时获得堆转储（生成dump文件）、
2、虚拟机启动时如果指定了 -XX:+HeapDumpOnOutOfMemoryError 选项, 则在抛出 OutOfMemoryError 时, 会自动执行堆转储。
3、使用 hprof 命令

+ 解析Java堆转储文件,并启动一个 web server
`jhat heapDump`
使用jhat命令，就启动了一个http服务，端口是7000
然后在访问http://localhost:7000/

+ 分析
一般查看堆异常情况主要看这个两个部分：
Show instance counts for all classes (excluding platform)，平台外的所有对象信息。
Show heap histogram 以树状图形式展示堆情况。


---
### jinfo
<http://www.hollischuang.com/archives/1094>
jinfo可以输出java进程、core文件或远程debug服务器的配置信息。这些配置信息包括JAVA系统参数及命令行参数,
如果进程运行在64位虚拟机上，需要指明-J-d64参数，如：jinfo -J-d64 -sysprops pid

另外，Java7的官方文档指出，这一命令在后续的版本中可能不再使用。笔者使用的版本(jdk8)中已经不支持该命令(笔者翻阅了java8中该命令的文档，其中已经明确说明不再支持)。

---
### javap
<http://www.hollischuang.com/archives/1107>

javap是jdk自带的一个工具，可以对代码反编译，也可以查看java编译器生成的字节码。

一般情况下，很少有人使用javap对class文件进行反编译，因为有很多成熟的反编译工具可以使用，比如jad。但是，javap还可以查看java编译器为我们生成的字节码。
通过它，可以对照源代码和字节码，从而了解很多编译器内部的工作。

