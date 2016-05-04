package com.kaishengit.util;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.io.InputStream;

@Named
public class QiniuUtil {

    @Value("${qiniu.ak}")
    private String ak;
    @Value("${qiniu.sk}")
    private String sk;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.domain}")
    private String domain;

    public String uploadFile(InputStream inputStream) {
        try {
            Auth auth = Auth.create(ak, sk);
            String uploadToken = auth.uploadToken(bucket);
            UploadManager uploadManager = new UploadManager();
            Response resp = uploadManager.put(IOUtils.toByteArray(inputStream), null, uploadToken);
            String fileKey = (String) resp.jsonToMap().get("key");
            return domain+"/"+fileKey;
        } catch (Exception ex) {
            throw new RuntimeException("上传到七牛异常");
        }
    }
}

