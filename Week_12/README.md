# 必做作业
## 1. 配置 redis 的主从复制，sentinel 高可用，Cluster 集群。
### 1.1 主从复制
1. 先在/data路径下, 创建两个文件夹, redis6379和redis6380.
2. 放在docker里面玩, 先把项目里面的配置文件拷贝出来, 放在/root目录下.
    1. 修改了bind
        1. 把后面的::1去掉了, 不知道有啥用, 加了报错
        2. 容器间访问需要允许外网访问, 所以bind改成0.0.0.0
    2. 修改了日志路径, 不然报文件夹不存在
    3. 启动主节点
        - `docker run -p 6380:6380 --name redis6380 -v /root/redis6380.conf:/etc/redis/redis.conf -v /data/redis6380:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes`
    4. 查看master容器的ip
        - `docker inspect <containerId>`    
    5. 修改从节点配置文件, 把replicaof后面的ip改成主节点容器的ip
    6. `docker run -p 6379:6379 --name redis6379 -v /root/redis6379.conf:/etc/redis/redis.conf -v /data/redis6379:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes`

### 1.2 sentinel高可用
1. 先把项目里面的配置文件拷贝出来, 放在/root目录下, 把主节点ip改成容器的内网ip
2. 复制一份从节点配置, 再启动一个从节点
    - `docker run -p 6382:6379 --name redis6382 -v /root/redis6382.conf:/etc/redis/redis.conf -v /data/redis6382:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes`
3. 启动3个sentinel
    - `docker run -p 6381:6379 --name sentinel -v /sentinel/sentinel.conf:/etc/redis/redis.conf -v /data/redis6381:/data -d redis redis-sentinel /etc/redis/redis.conf --appendonly yes`
    - `docker run -p 6383:6379 --name sentinel1 -v /sentinel/sentinel1.conf:/etc/redis/redis.conf -v /data/redis6383:/data -d redis redis-sentinel /etc/redis/redis.conf --appendonly yes`
    - `docker run -p 6384:6379 --name sentinel2 -v /sentinel/sentinel2.conf:/etc/redis/redis.conf -v /data/redis6384:/data -d redis redis-sentinel /etc/redis/redis.conf --appendonly yes`
4. 试着停了主库, 没有发生选举
    - 不知道是不是sentinel没有权限写配置文件有关, 虽然我已经把配置文件权限设置成777了
    - 日志如下, 没搞成
```plain
root@DESKTOP-OU5CL4N:/sentinel# docker logs b98359f937aa
1:X 10 Apr 2021 16:49:47.804 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
1:X 10 Apr 2021 16:49:47.804 # Redis version=6.2.1, bits=64, commit=00000000, modified=0, pid=1, just started
1:X 10 Apr 2021 16:49:47.804 # Configuration loaded
1:X 10 Apr 2021 16:49:47.805 * monotonic clock: POSIX clock_gettime
1:X 10 Apr 2021 16:49:47.805 * Running mode=sentinel, port=6379.
1:X 10 Apr 2021 16:49:47.805 # Sentinel ID is 8d992c54df8f8677b0b345825f61fb733c73d14c
1:X 10 Apr 2021 16:49:47.805 # +monitor master mymaster 172.17.0.2 6380 quorum 2
1:X 10 Apr 2021 16:49:47.806 * +slave slave 172.17.0.3:6379 172.17.0.3 6379 @ mymaster 172.17.0.2 6380
1:X 10 Apr 2021 16:49:47.808 # Could not rename tmp config file (Device or resource busy)
1:X 10 Apr 2021 16:49:47.808 # WARNING: Sentinel was not able to save the new configuration on disk!!!: Device or resource busy
1:X 10 Apr 2021 16:49:47.808 * +slave slave 172.17.0.5:6379 172.17.0.5 6379 @ mymaster 172.17.0.2 6380
1:X 10 Apr 2021 16:49:47.810 # Could not rename tmp config file (Device or resource busy)
1:X 10 Apr 2021 16:49:47.810 # WARNING: Sentinel was not able to save the new configuration on disk!!!: Device or resource busy
1:X 10 Apr 2021 16:52:59.253 # +sdown master mymaster 172.17.0.2 6380
```
5. 查看sentinel节点上的info, 发现显示sentinels=1, 可见哨兵间无法相互通信, 查了一下 在主节点上使用`subscribe __sentinel__:hello`
可以查看哨兵节点之间的通信, 一开始执行的时候报没有权限, 我把主节点配置文件里面的`user default on nopass ~* +@all`删掉, 重启了主节点 就能执行了, 然后sentinel之间也能相互感知了, 不知道是重启导致的还是修改配置导致的
6. 之后停掉主库, 重新选主是ok了 但是**哨兵节点配置文件没有被写进信息这个问题, 还不知道啥原因导致的以及怎么解决**
7. 猜测是docker mount文件, 导致文件不可写. 改成直接新建配置文件, 尝试启动sentinel
    - `docker run -p 6381:6379 --name sentinel -v /data/redis6381:/data -d redis sh -c "mkdir /etc/redis && echo 'sentinel monitor mymaster 172.17.0.3 6379 2' > /etc/redis/redis.conf && echo 'sentinel down-after-milliseconds mymaster 10000' >> /etc/redis/redis.conf && echo 'sentinel failover-timeout mymaster 180000' >> /etc/redis/redis.conf && echo 'sentinel parallel-syncs mymaster 1' >> /etc/redis/redis.conf && redis-sentinel /etc/redis/redis.conf --appendonly yes"`
    - `docker run -p 6383:6379 --name sentinel1 -v /data/redis6383:/data -d redis sh -c "mkdir /etc/redis && echo 'sentinel monitor mymaster 172.17.0.3 6379 2' > /etc/redis/redis.conf && echo 'sentinel down-after-milliseconds mymaster 10000' >> /etc/redis/redis.conf && echo 'sentinel failover-timeout mymaster 180000' >> /etc/redis/redis.conf && echo 'sentinel parallel-syncs mymaster 1' >> /etc/redis/redis.conf && redis-sentinel /etc/redis/redis.conf --appendonly yes"`
    - `docker run -p 6384:6379 --name sentinel2 -v /data/redis6384:/data -d redis sh -c "mkdir /etc/redis && echo 'sentinel monitor mymaster 172.17.0.3 6379 2' > /etc/redis/redis.conf && echo 'sentinel down-after-milliseconds mymaster 10000' >> /etc/redis/redis.conf && echo 'sentinel failover-timeout mymaster 180000' >> /etc/redis/redis.conf && echo 'sentinel parallel-syncs mymaster 1' >> /etc/redis/redis.conf && redis-sentinel /etc/redis/redis.conf --appendonly yes"`
8. 问题解决

### 1.3 cluser集群
1. 在配置文件里面加上
```
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
appendonly yes
```
2. 起6个redis, 起docker用`--net=host`
    - `docker run --net=host --name redis6380 -v /root/redis6380.conf:/etc/redis/redis.conf -v /data/redis6380:/data -d redis redis-server /etc/redis/redis.conf`
    - `docker run --net=host --name redis6379 -v /root/redis6379.conf:/etc/redis/redis.conf -v /data/redis6379:/data -d redis redis-server /etc/redis/redis.conf`
    - `docker run --net=host --name redis6381 -v /root/redis6381.conf:/etc/redis/redis.conf -v /data/redis6381:/data -d redis redis-server /etc/redis/redis.conf`
    - `docker run --net=host --name redis6382 -v /root/redis6382.conf:/etc/redis/redis.conf -v /data/redis6382:/data -d redis redis-server /etc/redis/redis.conf`
    - `docker run --net=host --name redis6383 -v /root/redis6383.conf:/etc/redis/redis.conf -v /data/redis6383:/data -d redis redis-server /etc/redis/redis.conf`
    - `docker run --net=host --name redis6384 -v /root/redis6384.conf:/etc/redis/redis.conf -v /data/redis6384:/data -d redis redis-server /etc/redis/redis.conf`
3. 搭建集群
    - `redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384  --cluster-replicas 1`
4. 测试完成
```
root@DESKTOP-OU5CL4N:/data# redis-cli -c
127.0.0.1:6379>
127.0.0.1:6379>
127.0.0.1:6379> set name xiangzheng
-> Redirected to slot [5798] located at 127.0.0.1:6380
OK
```

## 2. 搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。
下午继续

# 选做作业
## 1. 练习 redission 的各种功能。
上节课玩过了.