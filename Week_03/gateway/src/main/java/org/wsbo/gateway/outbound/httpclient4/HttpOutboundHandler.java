package org.wsbo.gateway.outbound.httpclient4;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.wsbo.gateway.client.MyClient;
import org.wsbo.gateway.filter.HttpRequestFilter;
import org.wsbo.gateway.filter.HttpResponseFilter;
import org.wsbo.gateway.filter.PrintInfoHttpResponseFilter;
import org.wsbo.gateway.router.HttpRouter;
import org.wsbo.gateway.router.RandomHttpRouter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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


            HttpEntity entity = originalResponse.getEntity();
            originalResponse.setEntity(new HttpEntity() {
                @Override
                public boolean isRepeatable() {
                    return false;
                }

                @Override
                public boolean isChunked() {
                    return false;
                }

                @Override
                public long getContentLength() {
                    return entity.getContentLength();
                }

                @Override
                public Header getContentType() {
                    return null;
                }

                @Override
                public Header getContentEncoding() {
                    return null;
                }

                @Override
                public InputStream getContent() throws IOException, UnsupportedOperationException {
                    // 这里加内容
                    return entity.getContent();
                }

                @Override
                public void writeTo(OutputStream outputStream) throws IOException {

                }

                @Override
                public boolean isStreaming() {
                    return false;
                }

                @Override
                public void consumeContent() throws IOException {

                }
            });
            byte[] body = new byte[0];
            try {
                body = EntityUtils.toByteArray(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            entity
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
