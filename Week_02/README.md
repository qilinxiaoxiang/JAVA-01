# Week02 作业题目（周三）
## 1. GCLogAnalysis演练
![1秒吞吐量](./1秒吞吐量.png)
![3秒吞吐量](./3秒吞吐量.png)

1. 1秒吞吐量测试观察
	1. -XX:-UseAdaptiveSizePolicy参数未见什么影响
	2. Xms设置和Xmx一样大, 可以显著减少young GC次数, 因为少了逐渐扩容的过程
	3. 小内存时(256M), 只有CMS没有OOM
	4. 大内存时(>8G), G1有更好的吞吐量
2. 3秒吞吐量测试观察
	1. 吞吐量为 SerialGC > ParallelGC = G1 > CMS
	2. 内存增大后, 不同gc的垃圾回收次数变化不同.
		1. SerialGC和ParallelGC的gc次数明显减少, 12G内存时, 3秒总共gc了3-4次. 可能逻辑是内存满了才会清理.
		2. CMS和G1的gc次数降到5-6次/秒以后就不再下降了. 可能逻辑是内存不满也会定时清理.
	3. CMS的gc日志行数比SerialGC和ParallelGC高出1个数量级, G1的gc日志行数比SerialGC和ParallelGC高出2个数量级

## 2. 网关压测
1. 操作系统: windows
2. 物理机内存: 16G
3. 压测命令: `sb -u http://localhost:8088/api/hello -N 300 -c 4`

### 2.1 SerialGC
#### 2.1.1 2G内存
1. 服务启动命令: `java -Xmx2g -Xms2g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseSerialGC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2248103
	- RPS: 7463.3
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0.1ms
	- Min: 0ms
	- Max: 166ms
3. gc
	1. jmc
		1. copy
			1. 总GC耗时: 416ms
			2. GC次数: 104
		2. MarkSweepCompact
			1. 总gc耗时: 161ms
			2. GC次数: 2
	2. 飞行记录器(1分钟)
		1. 22次
		2. 原因: Allocation Failure
		3. 收集器名称: DefNew
		4. 耗时: 2.661ms-8.297ms

#### 2.1.2 1G内存
1. 服务启动命令: `java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseSerialGC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2363561
	- RPS: 7846.3
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0ms
	- Min: 0ms
	- Max: 295ms
3. gc
	1. jmc
		1. copy
			1. 总GC耗时: 674ms
			2. GC次数: 222
		2. MarkSweepCompact
			1. 总gc耗时: 112ms
			2. GC次数: 2
4. 额外一分钟压测
	- Requests: 581006
	- RPS: 9507.2
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0ms
	- Min: 0ms
	- Max: 29ms

### 2.2 ParallelGC
#### 2.2.1 2G内存
1. 服务启动命令: `java -Xmx2g -Xms2g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2327340
	- RPS: 7724.4
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0.1ms
	- Min: 0ms
	- Max: 127ms
3. gc
	1. jmc
		1. PS Scavenge
			1. 总GC耗时: 270ms
			2. GC次数: 118
		2. PS MarkSweep
			1. 总GC耗时: 72ms
			2. GC次数: 2
	2. 飞行记录器(1分钟)
		1. 24次
		2. 原因: Allocation Failure
		3. 收集器名称: ParallelScavenge
		4. 耗时: 2.108ms-16.936ms

#### 2.2.2 1G内存
1. 服务启动命令: `java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2412039
	- RPS: 8008.4
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0ms
	- Min: 0ms
	- Max: 365ms
3. gc
	1. jmc
		1. PS Scavenge
			1. 总GC耗时: 474ms
			2. GC次数: 244
		2. PS MarkSweep
			1. 总GC耗时: 69ms
			2. GC次数: 2
4. 额外一分钟压测
	- Requests: 587594
	- RPS: 9617.6
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0ms
	- Min: 0ms
	- Max: 30ms

### 2.3 CMS
#### 2.3.1 2G内存
1. 服务启动命令: `java -Xmx2g -Xms2g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2232448
	- RPS: 7410.3
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0.1ms
	- Min: 0ms
	- Max: 124ms
3. gc
	1. jmc
		1. ParNew
			1. 总GC耗时: 432ms
			2. GC次数: 108
		2. ConCurrentMarkSeep
			1. 总GC耗时: 104ms
			2. GC次数: 1
	2. 飞行记录器(1分钟)
		1. 22次
		2. 原因: Allocation Failure
		3. 收集器名称: ParNew
		4. 耗时: 3.130ms-5.408ms

#### 2.3.2 1G内存
1. 服务启动命令: `java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2299892
	- RPS: 7634.3
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0.1ms
	- Min: 0ms
	- Max: 514ms
3. gc
	1. jmc
		1. ParNew
			1. 总GC耗时: 456ms
			2. GC次数: 219
		2. ConCurrentMarkSeep
			1. 总GC耗时: 16ms
			2. GC次数: 1
4. 额外一分钟压测
	- Requests: 568973
	- RPS: 9308.5
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0ms
	- Min: 0ms
	- Max: 26ms

### 2.4 CMS
#### 2.4.1 2G内存
1. 服务启动命令: `java -Xmx2g -Xms2g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2198203
	- RPS: 7297.6
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0.1ms
	- Min: 0ms
	- Max: 117ms
3. gc
	1. jmc
		1. G1 Young Generation
			1. 总GC耗时: 420ms
			2. GC次数: 49
		2. G1 Old Generation
			1. 总GC耗时: 0
			2. GC次数: 0
	2. 飞行记录器(1分钟)
		1. 9次
		2. 原因: G1 Evacuation Pause
		3. 收集器名称: G1New
		4. 耗时: 8.424ms-26.471ms

#### 2.4.2 1G内存
1. 服务启动命令: `java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -jar gateway-server-0.0.1-SNAPSHOT.jar`
2. 压测结果
	- Requests: 2263801
	- RPS: 7515.2
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0.1ms
	- Min: 0ms
	- Max: 447ms
3. gc
	1. jmc
		1. G1 Young Generation
			1. 总GC耗时: 465ms
			2. GC次数: 96
		2. G1 Old Generation
			1. 总GC耗时: 0
			2. GC次数: 0
4. 额外一分钟压测
	- Requests: 507836
	- RPS: 8301.3
	- 90th Percentile: 0ms
	- 95th Percentile: 0ms
	- 99th Percentile: 1ms
	- Average: 0ms
	- Min: 0ms
	- Max: 34ms

## 4. 对于不同GC和堆内存的总结
1. **G1并不一定比SerialGC好, 不管是大内存还是小内存.**
2. **ParallelGC也并不一定吞吐量达, 不管是大内存还是小内存.**
3. **内存大性能未必好.**
4. **比来比去, 发现不同GC的差别根本就不大, 如果不是有特殊要求或者遇到特殊问题, 确实没有必要优化GC.**


# Week02 作业题目（周日）
## 2. 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801.
见MyClient.java.