package com.kaishengit.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpUtil {

    /**
     * 执行HTTP的GET请求，并返回字符串
     * @param url
     * @return
     */
    public static String getString(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return IOUtils.toString(response.getEntity().getContent());
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("执行"+url+"异常:" + e.getMessage());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭HTTP请求异常");
            }
        }
    }

    /**
     * 使用Post请求发送Raw数据
     * @param url
     * @param raw
     * @return
     */
    public static String postStringWithRaw(String url,String raw) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            post.setEntity(new StringEntity(raw, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return IOUtils.toString(response.getEntity().getContent());
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("执行"+url+"异常:" + e.getMessage());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭HTTP请求异常");
            }
        }

    }

}
