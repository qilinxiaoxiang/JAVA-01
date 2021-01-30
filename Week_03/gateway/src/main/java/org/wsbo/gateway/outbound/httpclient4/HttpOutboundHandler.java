package org.wsbo.gateway.outbound.httpclient4;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.wsbo.gateway.client.MyClient;
import org.wsbo.gateway.filter.HttpRequestFilter;
import org.wsbo.gateway.filter.HttpResponseFilter;
import org.wsbo.gateway.filter.PrintInfoHttpResponseFilter;
import org.wsbo.gateway.router.HttpRouter;
import org.wsbo.gateway.router.RandomHttpRouter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandler {

    private final MyClient httpclient;
    private final ExecutorService proxyService;
    private final HttpResponseFilter responseFilter;
    private final List<String> backendUrls;
    private final HttpRouter httpRouter;

    public HttpOutboundHandler(List<String> proxyServers) {
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        this.proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
        this.httpclient = new MyClient();
        this.backendUrls = proxyServers;
        this.responseFilter = new PrintInfoHttpResponseFilter();
        this.httpRouter = new RandomHttpRouter();
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter requestFilter) {
        requestFilter.filter(fullRequest, ctx);
        proxyService.submit(()-> {
            String url = httpRouter.route(this.backendUrls) + fullRequest.uri();
            HttpResponse originalResponse = httpclient.visit(url);

            byte[] body = new byte[0];
            try {
                body = EntityUtils.toByteArray(originalResponse.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(originalResponse.getFirstHeader("Content-Length").getValue()));
            this.responseFilter.filter(response);
            if (!HttpUtil.isKeepAlive(fullRequest)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                ctx.write(response);
            }
            ctx.flush();
        });
    }

}
