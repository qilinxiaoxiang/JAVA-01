package org.wsbo.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class PrintInfoHttpResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse response) {
        System.out.println("response out");
    }
}
