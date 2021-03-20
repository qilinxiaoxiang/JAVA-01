# 作业
## 1. 改造自定义RPC的程序，提交到github
见项目rpc01

### 1.1 尝试将服务端写死查找接口实现类变成泛型和反射
最开始没太看懂题目, 不知道为啥要引入泛型. 感觉有反射就足够了. 后来看了同学的作业, 发现Class.forName的返回值本身就是自带泛型. 行吧.

### 1.2 尝试将客户端动态代理改成AOP，添加异常处理
最开始也没太看懂, 动态代理本身就是一种AOP, 啥叫改成AOP. 后来看了秦老师在知识星球的回复说字节码, 所以用cglib玩了一下.

### 1.3 尝试使用Netty+HTTP作为client端传输方式

1. 连接复用可以用连接池, PoolingHttpClientConnectionManager, 这次没玩.
2. 这次先用了HttpClient, 没用Netty. 后续补上.

## 2. 结合dubbo+hmily，实现一个TCC外汇交易处理，代码提交到github

- 上周玩过spring cloud+hmily了, 这周换成dubbo大同小异, 所以将hmily项目中的demo直接copy过来了, 没有玩