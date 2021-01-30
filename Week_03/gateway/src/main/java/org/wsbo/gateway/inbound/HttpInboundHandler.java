package org.wsbo.gateway.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.wsbo.gateway.filter.HttpRequestFilter;
import org.wsbo.gateway.filter.PrintInfoHttpRequestFilter;
import org.wsbo.gateway.outbound.httpclient4.HttpOutboundHandler;

import java.util.List;


public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final HttpOutboundHandler handler;
    private final HttpRequestFilter filter;
    private final List<String> proxyServers;
    
    public HttpInboundHandler(List<String> proxyServers) {
        this.handler = new HttpOutboundHandler(proxyServers);
        this.filter = new PrintInfoHttpRequestFilter();
        this.proxyServers = proxyServers;
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            handler.handle(fullRequest, ctx, filter);
    
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
