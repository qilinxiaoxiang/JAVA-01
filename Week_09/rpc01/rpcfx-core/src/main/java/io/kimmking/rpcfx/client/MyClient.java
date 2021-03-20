package io.kimmking.rpcfx.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * @author 项峥
 */
public class MyClient {
    public HttpResponse post(String url, String json) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            // 创建POST请求
            HttpPost httpPost = new HttpPost(url);
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true).build();
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new StringEntity(json));
            try (CloseableHttpResponse response = httpClient.execute(httpPost)){
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
