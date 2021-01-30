## 作业提交
### 本次作业实现了以下功能
1. [必做]整合你上次作业的httpclient/okhttp
2. [必做]实现过滤器
3. [选座]实现路由器

实际上秦老师已经在nio2/nio2里面把功能都做完了, 我做了工作

1. bootstrap的所有`.option`改成了`.childOption`
2. `childOption(ChannelOption.SO_KEEPALIVE,true)`存在但是无效, 改成了`.childOption(NioChannelOption.SO_KEEPALIVE, true)`
3. 用上周课自己写的MyClient替换了秦老师的httpCLient, 同时也失去了异步的特性, 因为我的MyClient是同步的.
4. filter从增加一个头信息, 换成了在命令行打印日志.
5. 详细了解了netty启动时的socket参数, 并加了注释.
6. 先把router功能拿掉了, 能正常跑, 再看看加上router改动了哪些地方.