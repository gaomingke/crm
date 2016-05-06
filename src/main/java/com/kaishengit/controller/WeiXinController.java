package com.kaishengit.controller;

import com.kaishengit.service.WeiXinService;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/wx")
public class WeiXinController {

    private Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    @Value("${wx.token}")
    private String sToken;
    @Value("${wx.aeskey}")
    private String sEncodingAESKey;
    @Value("${wx.corpid}")
    private String sCorpID;

    @Inject
    private WeiXinService weiXinService;

    /**
     * 微信回调模式的验证方法
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String callback(String msg_signature,String timestamp,String nonce,String echostr) {
        logger.debug("{} - {} - {} - {}",msg_signature,timestamp,nonce,echostr);
        String sEchoStr = null;
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            sEchoStr = wxcpt.VerifyURL(msg_signature,timestamp,nonce,echostr);
        } catch (AesException e) {
            e.printStackTrace();
        }
        return sEchoStr;
    }

}
