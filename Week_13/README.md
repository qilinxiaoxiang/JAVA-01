# 作业
## 1.1 搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。
1. 创建3个配置文件, 分别修改其中的3个属性
    1. broker.id
    2. listeners
    3. log.dirs
2. 用这3个配置文件启动kafka
    - `bin/kafka-server-start.sh config/server0.properties &`
    - `bin/kafka-server-start.sh config/server1.properties &`
    - `bin/kafka-server-start.sh config/server2.properties &`
3. 代码见kafka-test项目
4. 一开始让kafka集群在wsl2里面运行的时候, 跑在windows上的java程序, 死活连不上broker. 但是在wsl2里面, broker是能正常工作的. 这个wsl2已经坑了我好几次了, 真是不能再用了.
5. 后来看到kafka有.bat文件, 所以干脆运行在windows上, 结果一点问题都没有.

## 1.2（必做）思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。
明天继续
