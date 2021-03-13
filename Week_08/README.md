# 作业
## 1. 设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

- 用shardingsphere-jdbc做的, 并没有用proxy, 引入中间件会让复杂度变高
- 代码和配置文件见项目sharding
- sql见sharing.sql


## 2. 模拟1000万的订单单表数据，迁移到上面作业2的分库分表中

- 直接批量从原表中读取, 分批用shardingsphere-jdbc数据源插入新表中
- 并没有尝试使用scailing, 后续可以试试

## 3. 基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github

- 见项目eureka-server、distributed-transaction-hmily1和distributed-transaction-hmily2
- 最开始看的是atomikos, 网上的示例代码都是单应用多数据源的, 并不是常见的场景. 只有一篇讲到分布式 https://www.jianshu.com/p/0ad70057dda3, 不过完全看不懂.
- 然后转向hmily. 首先想到试试spring cloud alibaba搭建微服务. 结果一个nacos搞半天才起来. 然后发现nacos-client要求spring boot版本低于2.3.0, 这不行. 转向标准的spring cloud体系.
- hmily的TCC模式和此前接触的XA不太一样, 花了点时间理解和摆弄.
    1. distributed-transaction-hmily1作为单纯的服务提供方, 不仅需要HmilyTCC, 而且需要做好以下两件事
        1. 幂等(代码里偷懒没有做, 导致有重复插入)
        2. @Transactional本地事务回滚.
    2. distributed-transaction-hmily2作为服务的调用方, 是需要引入hmily的.
        1. 入口方法、confirm方法和cancel方法需要有相同的参数, 否则会报找不到方法.
        2. 其他照到文档操作就可以了.