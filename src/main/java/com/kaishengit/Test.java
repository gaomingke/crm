package com.kaishengit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.kaishengit.util.HttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {




        LoadingCache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(7200, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        String result = HttpUtil.getString("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx47b20e6a23545dd1&corpsecret=mhfhcJukxFY9sOB3D2-_-FYUYdGD_JxNZjxAh3w_FvYR3-ewjWDUPRtye0d9qDW0");
                        return result;
                    }
                });


        Map<String,Object> map = new Gson().fromJson(cache.get(""), HashMap.class);
        System.out.println(map.get("access_token"));


    }
}
