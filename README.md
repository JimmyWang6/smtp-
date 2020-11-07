

这是我们2020年大三下学期计算机网络课程设计做的项目，后期准备开源到github上，这里是我们的课程设文档
# 1绪论
## 背景与目的
随着电子邮件作为人们沟通交流的主要工具，在网络中有着广泛的应用。邮件系统的架构可分为邮件传输代理 MTA、邮件投递代理 MDA 和邮件用户代理 MUA 。邮件用户代理是一个发信和收信的程序，负责将电子邮件发送到 SMTP 服务器或者从邮件服务器取回收到的邮件。常用的邮件用户代理有微软的 OUTLOOK、腾讯的 FOXMAIL 等，其可以从遵循 POP3 协议的邮件服务器中收取邮件。 UDP、TCP/IP 等相关网络协议，以及应用程序网络协议的设计。 
本设计以计算机网络课程为背景， 掌握 SOCKET 网络编程以及应用层网络协议的设计方法，训练 Java 和 Android 移动操作系统 APK 的开发能力。本报告介绍基于 POP3 的邮件服务端和移动客户端（安卓系统）的详细设计和界面展示
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# 2系统相关技术应用
## 相关开发技术
### Android Studio
Android Studio 是谷歌推出的一个Android集成开发工具，基于IntelliJ IDEA. 类似 Eclipse ADT，Android Studio 提供了集成的 Android 开发工具用于开发和调试。
Android Studio 可以很方便的同时编辑Java逻辑代码和页面样式, 简化了开发。
![图 2.1 Android Studio工作台](https://img-blog.csdnimg.cn/20201106133555932.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)


###  PowerDesigner数据库设计工具
PowerDesigner是一款数据库设计软件。本系统在软件设计前期，使用PowerDesigner来对项目进行数据库设计。PowerDesigner可以构建物理数据模型(Physical Data Model)和概念数据模型(Conceptual Data Model) [8]，并可以与许多流行的软件开发工具进行交互。在使用时可以进行数据库范式以及关系之间的验证，同时可以直接导入数据库来快速生成。
###  IDEA
IDEA(IntelliJ IDEA)是JetBrains公司的产品，其中IDEA是java编程语言开发的集成环境。本系统使用了IDEA环境大大加快了工作效率，从不同方面为代码的开发和编写以及项目的部署发布，到依赖的下载等等提供了诸多便利。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106133804935.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
## 应用技术框架
### Netty
Netty 是一个利用 Java 的高级网络的能力，隐藏其背后的复杂性而提供一个易于使用的 API 的客户端/服务器框架。
   	Netty 是一个广泛使用的 Java 网络编程框架（Netty 在 2011 年获得了Duke's Choice Award，见https://www.java.net/dukeschoice/2011）。它活跃和成长于用户社区，像大型公司 Facebook 和 Instagram 以及流行 开源项目如 Infinispan, HornetQ, Vert.x, Apache Cassandra 和 Elasticsearch 等，都利用其强大的对于网络抽象的核心代码。
使用Netty能让我们的程序从一开始就具备并发能力和异步处理。本系统采用Netty实现了SMTP和POP3的服务器, Netty在其中的作用是处理用户并发访问。
### 基于Spring系列提供的RestFul服务
管理员模块不属于SMTP和POP3协议的范围, 要么是通过自定义协议实现, 要么是通过Http接口实现。本系统没有选择基于TCP的socket编程实现管理员模块, 而是采用了基于Http的, 由Spring框架提供的RestFul接口来暴露管理员功能接口, 安卓端请求接口来完成管理员的功能
### MyBatis
MyBatis 通常作为持久层框架而被广泛应用，除了支持普通SQL事物之外，还具有自定义SQL语言的强大拓展能力，它作为一个优秀的框架而言，提供的最大方便之处即在于无需编写传统的JDBC代码和众多的参数配置，通过简单的XML（eXtensible Markup Language）或注解即可完成DAO层数据处理[12]。本系统通过使用Mybatis与Entity实体进行一一对应，以XML文件配置实现了数据库操作的封装，并具有良好的扩展性能，以应对多种多样的数据库操作。
本系统通过使用Mybatis加快对Mysql数据库的增删查改(主要是邮件和用户的数据表), 减少不必要的错误。
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

## 客户端核心模块
### SMTP和POP模块
客户端(移动端)需要实现邮件用户代理的功能, 这就离不开SMTP和POP协议的使用。本系统基于学习网络编程的目的，没有直接使用开源且维护良好的框架JavaMail，而是自行通过Socket编程和服务器交互。客户端对两个协议的理解和服务器不太一样，我们在客户端只需要做两个事情：①发指令（数据） ②收应答或者数据
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106225453596.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
### MIME编解码
功能网络邮件传输协议(MIME)也是一个比较规范的协议，通过对它进行编解码能够有效提升算法设计和开发水平。 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106225526461.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
首先是按行读取邮件头部，然后是邮件体呈现一个muiltpart的分层格式，需要定义一个MultiPart类，通过递归的方法层层向下读取到纯文本、html、嵌入式图片、附件，最后将嵌入式资源嵌入到html中，得以显示。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020110622554124.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106225547561.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
### SMTP服务器模块
采用Netty事件驱动模型，首先要处理和用户的连接，将连接状态保存起来，维持一个状态机模型来表示用户和服务器的交互进行到哪一步（SMTP协议分为连接、登录、事务状态），登录之后就能够使用rcpt to 和 mail from 和data 指令进行发邮件，data指令执行完后接收到结束符时，将邮件存储在mysql数据库中。
![数据流动图](https://img-blog.csdnimg.cn/20201106225718375.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
	需要注意的是，从输入流拿到的是字节序列，而且是有粘包的可能，所以要对序列进行解包，根据 \n做分隔符将其分行，这是LineDecoder做的工作。然后因为我们对字符串行感兴趣，所以要将字符序列编码成字符串行。之后将这一行指令（也可能是数据）传给ServerHandler，ServerHandler会根据状态机情况和当前指令将该行数据交给对应CommandHandler处理。
	![指令行（数据）处理](https://img-blog.csdnimg.cn/2020110622575856.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
###  POP3服务器模块
POP3邮件服务模块从实现角度看，和SMTP是基本一样的。常用的指令是user和pass指令、uidl指令、retr指令，所以POP3服务模块需要能够响应这些指令请求，维护状态机，维护用户登录信息，根据用户发过来的指令做相应的应答。
user指令，接收到该指令时，保存user的值，并计数+1，等待下一条pass指令，接收到pass指令后，进行数据库查询，若用户密码匹配则返回正确应答-OK，否则返回错误应答-ERR xxx错误信息。
uidl指令，在用户登录之后才能调用，否则返回错误信息。接收到uidl指令后，查询数据库中该用户的邮件列表，返回邮件uid 唯一标识符列表。
retr uid指令，确认用户已登录，将返回用户邮箱中标识符为uid的邮件数据。
###  管理员服务器模块
实际操作是采用RestFul接口来定义管理员功能，包括新增用户、删除用户、服务器启停（通过依赖注入的方式，SMTP和POP3服务器的实例对象被Spring容器所管理，所以简单这两个实例对象的关闭socket的方法即可） ，日志系统在此次设计中将重要的事件操作存到数据库，群发邮件也是通过暴露RestFul接口给安卓客户端，在验证管理员身份后，给每位普通用户的数据库邮箱表写入该群发邮件。起初是想用RCPT TO指令给每位成员转发的，但是我们设计的时候限制了RCPT TO的转发人数，所以还是采用直接写入数据库的方法来实现群发。
同时，在启停服务器时，考虑到可能会接收到多个请求的情况，可能会有竞态的产生，这里使用了一些同步与锁操作。
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# 3系统需求分析与详细设计
## 系统需求分析
项目是基于 POP3 和 SMTP 的邮件服务端和移动客户端（安卓系统）的设计，设计一个邮件服务器和一个移动端（安卓系统）的邮件客户端
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020110623024352.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
服务器端除了提供最基本的收发邮件功能之外，还应具有注册新用户、管理用户、群发邮件以及修改服务器相关参数、修改管理员密码等功能。
客户端分为普通用户端和管理员端。普通用户端可实现基 本的注册、收发邮件，修改个人资料等功能。
管理员端主要实现群发邮件功能，除此之外， 它还可以实现浏览用户信息以及删除用户等操作。
## 系统详细设计
### 系统模块
整体模块设计分为两大块——移动端和服务器端。移动端充邮件用户代理角色，移动端子模块有收发邮件、邮件管理、信息管理、管理员功能模块；服务器端子模块有SMTP服务器、POP3服务器、管理员服务器。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106230331522.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
总的来说，除了管理员功能以外，其它的功能都能够符合现有的邮件系统的基本要求，即通过适当配置，完全可以让移动端收发主流邮件系统的邮件；也可以用比较有名的邮件代理软件收发我们的邮件服务器的邮件。
	收发邮件子模块使用了SMTP和POP3协议，基于socket和服务器进行通信。还通过对MIME实现编解码，从而具备复杂邮件（HTML、嵌入资源）收发的功能。
### 子模块设计
1)身份验证（登录注册）模块
用户第一次进入系统，可以选择注册用户，输入用户名、密码后点击注册，注册成功之后自动登录。用户登录，需要输入用户名、密码后点击登录，登录操作会连接SMTP和POP服务器分别进行auth login指令进行确认，若两个服务器都返回正确应答，则同意用户登录，将其登录信息存入安卓SQLite数据库以便之后自动登录。
2)发邮件模块
用户填写好收件人和发件人（这主要是应对多用户情况，所以需要选），填写邮件标题和内容，我们提供了体验较好的编辑器模块，方便用户书写邮件。然后点击发送，发送邮件操作会调用SMTP模块进行整个SMTP协议的发信流程。
3)收邮件模块
用户登录后，维持其socket连接，可在主页面下拉刷新进行邮件获取。因为安卓端本地存储模块的实现，使得我们可以和POP3服务器请求邮件列表，比对已经收到了的邮件，从而针对性的向服务器请求新邮件，而不是每次都重复下载所有邮件。
4)邮件管理模块
用户登录后，提供了收件箱、草稿邮件、已发送、星标邮件、已删除五个邮箱分类，对于草稿邮件可以进行编辑，对于收到的邮件可以直接回复（回复会加载原邮件）。关于删除邮件，此系统设计思路是不向服务器发送dele删除指令，而是在本地标记该邮件已删除，之后不显示也不加载该邮件。
5)个人资料管理模块
用户登录后，可以修改密码、发件人名称（默认为邮箱账号）。
6)管理员模块
管理员账号登录后，客户端会区别对待管理员，在普通用户基础功能上，展示其特有的功能页面，该页面包括对服务器的启停控制、用户列表管理（增、删）、日志查看、群发邮件。
### 数据库设计
数据库原型设计采用的是Powerdesigner原型设计工具，系统设计阶段设计的数据库原型E-R图如下所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106230445716.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
上述数据库主要包含了三大模块：用户表模块、邮件模块、日志模块，在实际系统开发中有所出入，数据库各个表展示如下：
1)用户注册后的基本信息，字段结构如下表3.1所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106230513947.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
2)邮件表，字段结构如下表3.2所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106230531485.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)

实际开发过程中，有所差异，安卓端在此次系统设计中也展示出本地存储，用于加快应用运行，通过一次POP3邮件接收，将已有的邮件从服务器拉取后存储于本地，之后的接收都会将服务器返回的邮件列表和本地比对，只收取新的邮件，大大提高了收件效率和节省带宽。
## 各模块具体设计
### 身份验证（登录注册）模块设计
身份验证模块包含用户登录、用户注册。当用户第一次打开系统时，会进入到系统的登录页面。在登录页面上包含有用户登录、管理员登录（用户可进行不同登录身份切换）、注册账号的功能。
1)邮箱注册设计
注册账户当前采用的是http注册，即携带用户名密码进行http请求，注册成功后会直接自动登录。如果邮箱地址已存在则提示邮箱已存在，邮箱地址不符合格式要求也会提示。
2)邮箱登录设计
邮箱登录采用的是和SMTP和POP3服务器进行认证，发送auth login指令给SMTP服务器，发送user 、pass指令给POP3服务器。两者都返回正确应答后，将用户登录信息存储到本地，完成登录。

### 客户端SMTP模块设计
主要是实现socket连接、指令实现、应答处理。这三个功能联合在一起就能把邮件发出去。
然后是MIME编码，从编辑器调用getHtml方法，因为编辑器是在网页的，所以从网页把内容通过JS脚本回调到安卓。先拼接邮件头（包含发件人、收件人、时间、邮件主题等内容），然后拼接邮件体，在拼接过程中需要将嵌入资源拆开，放到独立的一个multipart中。邮件最后以 . 号结尾。
发送成功后，将邮件本地存储，在发件箱处可以看到这封邮件。
图中显示的是客户端如何通过socket和SMTP服务器交互，达到发送邮件的目的
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106230923686.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
图中省略了应答等待（不然图片太长了），实际上每次发完指令，都要等待服务器应答，写了一个函数getReply()就是等待应答的，这是阻塞式的等待，因为socket读阻塞。需要根据应答码来确定当前指令能否成功，任何一步的失败都导致整个邮件发送的失败，需要调用UI告诉用户出现错误，过程中断。

### 客户端POP模块设计
主要也是socket连接、指令实现、应答等待三个部分。
然后是收到邮件后，需要进行MIME解码。解码要比编码难得多，因为不同邮件体格式不太一样，有些有附件、有些有图片嵌入等。采取的方案是递归分析，抓住MultiPart的边界Boundary，每一个Multipart都分为头部和消息体，从它的头部可以看出它有没有子part，如果没有子part则递归终止，否则向下递归。最终解析整个邮件。这个过程在前面的核心模块的MIME编解码部分比较详细提到了。
### 服务器端POP模块设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231017387.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231028787.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
### 开发环境介绍
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231105415.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)<hr>

#	实现效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231239158.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231229106.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231252431.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231303523.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020110623131828.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231334403.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201106231345129.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE4MDQ4NA==,size_16,color_FFFFFF,t_70#pic_center)
<hr>

# 个人心得
本次主要负责的是服务器端的架构以及SMTP协议的编写。从一开始学习的java socket编程到后来的NIO，再到后来项目最终使用的netty框架，对于网络编程有了更加深入的理解。在项目中实际应用了一些线程同步的知识，特别是服务器的启停以及socket的并发处理等，收获很多。在实现协议等过程中，阅读了smtp协议文档，深入了解了如何定义并且应用层协议，同时也使用了一些设计模式对代码进行编写，是一次非常有意义的课程设计。
<hr>
# 这部分代码主要是后端代码，前端代码详见https://github.com/Shihuan1014/Android-Mail
