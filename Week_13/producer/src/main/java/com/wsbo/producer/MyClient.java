package com.wsbo.producer;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * @author 项峥
 */
public class MyClient {
    public String get(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true).build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String post(String url, String json) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpPost httpPost = new HttpPost(url);
        if (json != null) {
            httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        }
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true).build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MyClient client = new MyClient();
        System.out.println(client.post("http://localhost:8080/kmq/topic/testTopic", null));
        System.out.println(client.post("http://localhost:8080/kmq/topic/testTopic/message", "{\"message\":\"first message\"}"));
        System.out.println(client.post("http://localhost:8080/kmq/topic/testTopic/message", "{\"message\":\"second message\"}"));
        System.out.println(client.post("http://localhost:8080/kmq/topic/testTopic/message", "{\"message\":\"third message\"}"));
    }
}
