# 作业
## 1. 按自己设计的表结构, 插入100万订单模拟数据, 测试不同方式的插入效率
见项目batchinsert, 表结构如下

```sql
create table if not exists `trade`(
    `id` int auto_increment comment '主键',
    `trade_id` varchar(30) not null comment '订单唯一id',
    `uid` varchar(20) not null comment '用户唯一id',
    `status` varchar(20) not null default 'init' comment '订单状态',
    `goods_info` text comment '购买商品信息',
    `pay_time` datetime default null comment '支付时间',
    `total_amount` decimal(10,2) default null comment '原价',
    `received_amount` decimal(10,2) default null comment '实收金额',
    `fail_msg` text comment '订单失败原因',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `modify_time` datetime default current_timestamp on update current_timestamp not null comment '修改时间',
    primary key (`id`),
	key `idx_uid` (`trade_id`),
	key `idx_trade_id` (`trade_id`),
	key `idx_create_time` (`create_time`)
) default charset=utf8mb4 comment '订单表';
```

### 1.1 简单循环单条插入
Week_07/batchinsert/src/main/java/com/wsbo/batchinsert/inserter/SimpleInserter.java

插入1万条, 耗时44803ms

### 1.2 线程池并发单条插入
Week_07/batchinsert/src/main/java/com/wsbo/batchinsert/inserter/ThreadPoolSimpleInserter.java

10线程并发插入1万条, 耗时9270ms

### 1.3 xml中sql拼接批量插入
Week_07/batchinsert/src/main/java/com/wsbo/batchinsert/inserter/ThreadPoolSqlBatchInserter.java

10线程并发, 每条sql语句插入200行, 总共插入100万条, 耗时45819ms.

### 1.4 sqlSessionFactory批量插入
Week_07/batchinsert/src/main/java/com/wsbo/batchinsert/inserter/ThreadPoolSqlSessionBatchInserter.java

10线程并发, 每条sql语句插入200行, 每批次100条sql, 耗时38032ms.

## 2. 读写分离-动态切换数据源版本1.0
见项目read-write-separation1

```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.3)

2021-03-07 05:09:32.720  INFO 15832 --- [           main] c.w.r.ReadWriteSeparate1Application      : Starting ReadWriteSeparate1Application using Java 11.0.6 on DESKTOP-OU5CL4N with PID 15832 (C:\Users\wq551\code\JAVA-01\Week_07\read-write-separate1\target\classes started by wq551 in C:\Users\wq551\code\JAVA-01\Week_07\read-write-separate1)
2021-03-07 05:09:32.728  INFO 15832 --- [           main] c.w.r.ReadWriteSeparate1Application      : No active profile set, falling back to default profiles: default
2021-03-07 05:09:33.630  INFO 15832 --- [           main] c.w.r.ReadWriteSeparate1Application      : Started ReadWriteSeparate1Application in 1.18 seconds (JVM running for 2.007)
2021-03-07 05:09:33.661  INFO 15832 --- [           main] c.w.r.config.MyRoutingDataSource         : 使用了写库
2021-03-07 05:09:33.661  INFO 15832 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-03-07 05:09:33.889  INFO 15832 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
writeDataSource
2021-03-07 05:09:33.919  INFO 15832 --- [           main] c.w.r.config.DbContextHolder             : 设置dbType为：read
2021-03-07 05:09:33.919  INFO 15832 --- [           main] c.w.r.config.MyRoutingDataSource         : 使用了读库1
2021-03-07 05:09:33.919  INFO 15832 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Starting...
2021-03-07 05:09:33.929  INFO 15832 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Start completed.
2021-03-07 05:09:33.940  INFO 15832 --- [           main] c.w.r.annotation.ReadOnlyAspect          : 清除threadLocal
readDataSource1
2021-03-07 05:09:33.940  INFO 15832 --- [           main] c.w.r.config.DbContextHolder             : 设置dbType为：read
2021-03-07 05:09:33.940  INFO 15832 --- [           main] c.w.r.config.MyRoutingDataSource         : 使用了读库2
2021-03-07 05:09:33.940  INFO 15832 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Starting...
2021-03-07 05:09:33.971  INFO 15832 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Start completed.
2021-03-07 05:09:33.971  INFO 15832 --- [           main] c.w.r.annotation.ReadOnlyAspect          : 清除threadLocal
readDataSource2
2021-03-07 05:09:33.971  INFO 15832 --- [           main] c.w.r.config.DbContextHolder             : 设置dbType为：read
2021-03-07 05:09:33.971  INFO 15832 --- [           main] c.w.r.config.MyRoutingDataSource         : 使用了读库1
2021-03-07 05:09:33.979  INFO 15832 --- [           main] c.w.r.annotation.ReadOnlyAspect          : 清除threadLocal
readDataSource1
2021-03-07 05:09:33.979  INFO 15832 --- [           main] c.w.r.config.DbContextHolder             : 设置dbType为：read
2021-03-07 05:09:33.980  INFO 15832 --- [           main] c.w.r.config.MyRoutingDataSource         : 使用了读库2
2021-03-07 05:09:33.980  INFO 15832 --- [           main] c.w.r.annotation.ReadOnlyAspect          : 清除threadLocal
readDataSource2
2021-03-07 05:09:33.980  INFO 15832 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown initiated...
2021-03-07 05:09:33.999  INFO 15832 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown completed.
2021-03-07 05:09:33.999  INFO 15832 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown initiated...
2021-03-07 05:09:34.001  INFO 15832 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown completed.
2021-03-07 05:09:34.001  INFO 15832 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2021-03-07 05:09:34.013  INFO 15832 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 0
```

## 3. 读写分离-数据库框架版本2.0
见项目readwriteseparation1

```
"C:\Program Files\Java\jdk-11.0.6\bin\java.exe" -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=6413:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -Dfile.encoding=UTF-8 -classpath C:\Users\wq551\code\JAVA-01\Week_07\read-write-separate2\target\classes;C:\Users\wq551\.m2\repository\org\mybatis\spring\boot\mybatis-spring-boot-starter\2.1.4\mybatis-spring-boot-starter-2.1.4.jar;C:\Users\wq551\.m2\repository\org\springframework\boot\spring-boot-starter\2.4.3\spring-boot-starter-2.4.3.jar;C:\Users\wq551\.m2\repository\org\springframework\boot\spring-boot\2.4.3\spring-boot-2.4.3.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-context\5.3.4\spring-context-5.3.4.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-aop\5.3.4\spring-aop-5.3.4.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-expression\5.3.4\spring-expression-5.3.4.jar;C:\Users\wq551\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\2.4.3\spring-boot-autoconfigure-2.4.3.jar;C:\Users\wq551\.m2\repository\org\springframework\boot\spring-boot-starter-logging\2.4.3\spring-boot-starter-logging-2.4.3.jar;C:\Users\wq551\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;C:\Users\wq551\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;C:\Users\wq551\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.13.3\log4j-to-slf4j-2.13.3.jar;C:\Users\wq551\.m2\repository\org\apache\logging\log4j\log4j-api\2.13.3\log4j-api-2.13.3.jar;C:\Users\wq551\.m2\repository\org\slf4j\jul-to-slf4j\1.7.30\jul-to-slf4j-1.7.30.jar;C:\Users\wq551\.m2\repository\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;C:\Users\wq551\.m2\repository\org\yaml\snakeyaml\1.27\snakeyaml-1.27.jar;C:\Users\wq551\.m2\repository\org\springframework\boot\spring-boot-starter-jdbc\2.4.3\spring-boot-starter-jdbc-2.4.3.jar;C:\Users\wq551\.m2\repository\com\zaxxer\HikariCP\3.4.5\HikariCP-3.4.5.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-jdbc\5.3.4\spring-jdbc-5.3.4.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-beans\5.3.4\spring-beans-5.3.4.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-tx\5.3.4\spring-tx-5.3.4.jar;C:\Users\wq551\.m2\repository\org\mybatis\spring\boot\mybatis-spring-boot-autoconfigure\2.1.4\mybatis-spring-boot-autoconfigure-2.1.4.jar;C:\Users\wq551\.m2\repository\org\mybatis\mybatis\3.5.6\mybatis-3.5.6.jar;C:\Users\wq551\.m2\repository\org\mybatis\mybatis-spring\2.0.6\mybatis-spring-2.0.6.jar;C:\Users\wq551\.m2\repository\mysql\mysql-connector-java\8.0.23\mysql-connector-java-8.0.23.jar;C:\Users\wq551\.m2\repository\org\projectlombok\lombok\1.18.18\lombok-1.18.18.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-core\5.3.4\spring-core-5.3.4.jar;C:\Users\wq551\.m2\repository\org\springframework\spring-jcl\5.3.4\spring-jcl-5.3.4.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-jdbc-spring-boot-starter\4.1.1\sharding-jdbc-spring-boot-starter-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-spring-boot-util\4.1.1\sharding-spring-boot-util-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-core-common\4.1.1\sharding-core-common-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-common\4.1.1\shardingsphere-common-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-spi\4.1.1\shardingsphere-spi-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-binder\4.1.1\shardingsphere-sql-parser-binder-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-core-api\4.1.1\sharding-core-api-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\encrypt-core-api\4.1.1\encrypt-core-api-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\encrypt-core-common\4.1.1\encrypt-core-common-4.1.1.jar;C:\Users\wq551\.m2\repository\org\codehaus\groovy\groovy\2.4.5\groovy-2.4.5-indy.jar;C:\Users\wq551\.m2\repository\commons-codec\commons-codec\1.15\commons-codec-1.15.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-transaction-spring\4.1.1\sharding-transaction-spring-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-transaction-core\4.1.1\sharding-transaction-core-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-jdbc-core\4.1.1\sharding-jdbc-core-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-pluggable\4.1.1\shardingsphere-pluggable-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-route\4.1.1\shardingsphere-route-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-rewrite-engine\4.1.1\shardingsphere-rewrite-engine-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-executor\4.1.1\shardingsphere-executor-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-merge\4.1.1\shardingsphere-merge-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-sql92\4.1.1\shardingsphere-sql-parser-sql92-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-engine\4.1.1\shardingsphere-sql-parser-engine-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-spi\4.1.1\shardingsphere-sql-parser-spi-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-statement\4.1.1\shardingsphere-sql-parser-statement-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\commons\commons-collections4\4.2\commons-collections4-4.2.jar;C:\Users\wq551\.m2\repository\org\antlr\antlr4-runtime\4.7.2\antlr4-runtime-4.7.2.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-mysql\4.1.1\shardingsphere-sql-parser-mysql-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-postgresql\4.1.1\shardingsphere-sql-parser-postgresql-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-oracle\4.1.1\shardingsphere-sql-parser-oracle-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shardingsphere-sql-parser-sqlserver\4.1.1\shardingsphere-sql-parser-sqlserver-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-core-route\4.1.1\sharding-core-route-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\master-slave-core-route\4.1.1\master-slave-core-route-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-core-rewrite\4.1.1\sharding-core-rewrite-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\encrypt-core-rewrite\4.1.1\encrypt-core-rewrite-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\shadow-core-rewrite\4.1.1\shadow-core-rewrite-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-core-execute\4.1.1\sharding-core-execute-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\sharding-core-merge\4.1.1\sharding-core-merge-4.1.1.jar;C:\Users\wq551\.m2\repository\org\apache\shardingsphere\encrypt-core-merge\4.1.1\encrypt-core-merge-4.1.1.jar;C:\Users\wq551\.m2\repository\com\google\guava\guava\18.0\guava-18.0.jar;C:\Users\wq551\.m2\repository\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;C:\Users\wq551\.m2\repository\org\slf4j\jcl-over-slf4j\1.7.30\jcl-over-slf4j-1.7.30.jar com.wsbo.readwriteseparate2.ReadWriteSeparate2Application

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.3)

2021-03-07 05:51:35.876  INFO 16480 --- [           main] c.w.r.ReadWriteSeparate2Application      : Starting ReadWriteSeparate2Application using Java 11.0.6 on DESKTOP-OU5CL4N with PID 16480 (C:\Users\wq551\code\JAVA-01\Week_07\read-write-separate2\target\classes started by wq551 in C:\Users\wq551\code\JAVA-01\Week_07\read-write-separate2)
2021-03-07 05:51:35.880  INFO 16480 --- [           main] c.w.r.ReadWriteSeparate2Application      : No active profile set, falling back to default profiles: default
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass$3$1 (file:/C:/Users/wq551/.m2/repository/org/codehaus/groovy/groovy/2.4.5/groovy-2.4.5-indy.jar) to method java.lang.Object.finalize()
WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass$3$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
2021-03-07 05:51:36.627  INFO 16480 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'stringToNoneShardingStrategyConfigurationConverter' of type [org.apache.shardingsphere.spring.boot.converter.StringToNoneShardingStrategyConfigurationConverter] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-07 05:51:36.640  INFO 16480 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.shardingsphere.sharding-org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties' of type [org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-07 05:51:36.644  INFO 16480 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.shardingsphere.masterslave-org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties' of type [org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-07 05:51:36.646  INFO 16480 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.shardingsphere.encrypt-org.apache.shardingsphere.shardingjdbc.spring.boot.encrypt.SpringBootEncryptRuleConfigurationProperties' of type [org.apache.shardingsphere.shardingjdbc.spring.boot.encrypt.SpringBootEncryptRuleConfigurationProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-07 05:51:36.649  INFO 16480 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.shardingsphere.shadow-org.apache.shardingsphere.shardingjdbc.spring.boot.shadow.SpringBootShadowRuleConfigurationProperties' of type [org.apache.shardingsphere.shardingjdbc.spring.boot.shadow.SpringBootShadowRuleConfigurationProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-07 05:51:36.652  INFO 16480 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.shardingsphere-org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties' of type [org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-07 05:51:36.807  INFO 16480 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration' of type [org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration$$EnhancerBySpringCGLIB$$e46505ba] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-07 05:51:36.896  INFO 16480 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-03-07 05:51:37.184  INFO 16480 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-03-07 05:51:37.198  INFO 16480 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Starting...
2021-03-07 05:51:37.229  INFO 16480 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Start completed.
2021-03-07 05:51:37.229  INFO 16480 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Starting...
2021-03-07 05:51:37.252  INFO 16480 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Start completed.
2021-03-07 05:51:37.289  INFO 16480 --- [           main] o.a.s.core.log.ConfigurationLogger       : MasterSlaveRuleConfiguration:
loadBalanceAlgorithmType: round_robin
masterDataSourceName: master
name: ms
slaveDataSourceNames:
- s1
- s2

2021-03-07 05:51:37.290  INFO 16480 --- [           main] o.a.s.core.log.ConfigurationLogger       : Properties:
sql.show: 'false'

2021-03-07 05:51:37.336  INFO 16480 --- [           main] ShardingSphere-metadata                  : Loading 3 tables' meta data.
2021-03-07 05:51:37.559  INFO 16480 --- [           main] ShardingSphere-metadata                  : Meta data load finished, cost 269 milliseconds.
2021-03-07 05:51:37.884  INFO 16480 --- [           main] c.w.r.ReadWriteSeparate2Application      : Started ReadWriteSeparate2Application in 2.49 seconds (JVM running for 3.551)
Trade(id=1, tradeId=readDataSource1, uid=user2, status=init, payTime=null, totalAmount=null, receivedAmount=null, createTime=Sun Mar 07 04:35:15 CST 2021, modifyTime=Sun Mar 07 04:35:15 CST 2021, goodsInfo=null, failMsg=null)
Trade(id=1, tradeId=readDataSource2, uid=user3, status=init, payTime=null, totalAmount=null, receivedAmount=null, createTime=Sun Mar 07 04:36:07 CST 2021, modifyTime=Sun Mar 07 04:36:07 CST 2021, goodsInfo=null, failMsg=null)
Trade(id=1, tradeId=readDataSource1, uid=user2, status=init, payTime=null, totalAmount=null, receivedAmount=null, createTime=Sun Mar 07 04:35:15 CST 2021, modifyTime=Sun Mar 07 04:35:15 CST 2021, goodsInfo=null, failMsg=null)
Trade(id=1, tradeId=readDataSource2, uid=user3, status=init, payTime=null, totalAmount=null, receivedAmount=null, createTime=Sun Mar 07 04:36:07 CST 2021, modifyTime=Sun Mar 07 04:36:07 CST 2021, goodsInfo=null, failMsg=null)
2021-03-07 05:51:38.306  INFO 16480 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2021-03-07 05:51:38.319  INFO 16480 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2021-03-07 05:51:38.320  INFO 16480 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown initiated...
2021-03-07 05:51:38.330  INFO 16480 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown completed.
2021-03-07 05:51:38.330  INFO 16480 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown initiated...
2021-03-07 05:51:38.335  INFO 16480 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown completed.

Process finished with exit code 0

```