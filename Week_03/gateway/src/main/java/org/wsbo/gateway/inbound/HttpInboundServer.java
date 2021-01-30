package org.wsbo.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;

import java.net.StandardSocketOptions;
import java.util.List;

@Data
public class HttpInboundServer {

    private int port;
    private List<String> proxyServers;

    public HttpInboundServer(int port) {
        this.port=port;
    }

    public HttpInboundServer(int port, List<String> proxyServers) {
        this.port = port;
        this.proxyServers = proxyServers;
    }

    public void run() throws Exception {

        // 主从reactor模型中的mainReactor, 接待员线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 主从reactor模型中的subReactor, 具体工作线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            /*
            A Bootstrap that makes it easy to bootstrap a Channel to use for clients.
            启动线程, 开启socket, 即channel
             */
            ServerBootstrap b = new ServerBootstrap();
            b
                    /*
                    服务器端TCP内核模块维护有2个队列，我们称之为A，B吧
                    客户端向服务端connect的时候，发送带有SYN标志的包（第一次握手）
                    服务端收到客户端发来的SYN时，向客户端发送SYN ACK 确认(第二次握手)
                    此时TCP内核模块把客户端连接加入到A队列中，然后服务器收到客户端发来的ACK时（第三次握手）
                    TCP没和模块把客户端连接从A队列移到B队列，连接完成，应用程序的accept会返回
                    也就是说accept从B队列中取出完成三次握手的连接
                    A队列和B队列的长度之和是backlog,当A，B队列的长之和大于backlog时，新连接将会被TCP内核拒绝
                     */
                    .childOption(ChannelOption.SO_BACKLOG, 128)
                    /*
                    Nagle算法是用来优化网络拥堵的.
                    原理是小包不一定立即发送, 可以先放在缓冲区. 等和其他小包一起填满了缓冲区再进行发送, 减少数据包.
                    如果缓冲区一直不满, 也会有一个超时时间, 达到超时立即发送, 降低延迟.
                    NODELAY为true表示关闭Nagle算法. 即小包也立即发送.
                     */
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    /*
                    KEEPALIVE是检测对端是不是存活.
                    当对端不传送数据了, 会发请求给对端, 对端没有响应, 则直接断开连接.
                    否则如果对端异常崩溃等场景下, 连接已坏, 但还浪费资源维持, 下次直接用会直接报错.
                    .childOption(ChannelOption.SO_KEEPALIVE,true) 存在但是无效
                     */
                    .childOption(NioChannelOption.SO_KEEPALIVE, true)
                    // 接收缓存
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    // 发送缓存
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    /*
                    https://blog.csdn.net/yaokai_assultmaster/article/details/68951150
                    SO_REUSEADDR和SO_REUSEPORT设置为true以后, 只有ip和端口严格一致的socket绑定才会出错
                    如果这俩参数是false, 那么如果有socket的ip设置了一个网段, 或者0.0.0.0, 建其他socket都会报错
                     */
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    // Direct Memory分配方式
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HttpInboundInitializer(proxyServers));

            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
