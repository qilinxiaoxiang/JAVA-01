# 作业
## 4.（必做）基于 Redis 封装分布式数据操作：
### 4.1 在 Java 中实现一个简单的分布式锁
- 使用了Redisson的RLock, 见 [DistributedLock.java](redis/src/main/java/com/wsbo/redis/lock/DistributedLock.java)
- 最开始试用时, 解锁报"not locked by current thread".
    - 研究了一下报错没看出问题, 在群里问, 秦老师说加锁时间短于业务执行时间, 导致解锁报错.
    - 自动续期需要加上watchDog, 并且不指定leaseTime才行.


### 4.2 在 Java 中实现一个分布式计数器，模拟减库存。
使用了Redisson的RAtomicLong, 见 [DistributedCounter.java](redis/src/main/java/com/wsbo/redis/counter/DistributedCounter.java)

## 5.（必做）基于 Redis 的 PubSub 实现订单异步处理
使用了Redisson的RTopic, 见 [RedisConfiguration.java](redis/src/main/java/com/wsbo/redis/config/RedisConfiguration.java)