package com.rifu.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.rifu.common.ResponseEntity;
import com.rifu.service.ZavaVerifyCodeService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author Rifu
 * @Date 2018/10/30  13:07
 */
@Service
public class ZavaVerifyCodeServiceImpl implements ZavaVerifyCodeService {

    private static final Logger logger=LoggerFactory.getLogger(ZavaVerifyCodeServiceImpl.class);

    private static final int VERIFY_CODE_LENGTH=6;

    // 短信应用SDK AppID
    int appid = 1400155142; // 1400开头

    // 短信应用SDK AppKey
    String appkey = "fa68dd25ce4e50c792691da838b5b983";


    // 短信模板ID，需要在短信应用中申请
    int templateId = 219619; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    //templateId7839对应的内容是"您的验证码是: {1}"
// 签名
    String smsSign = "邑人邑语"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`


    /**
     *
     * @param phone
     * @param type  1为需要发送注册验证码，2为登录验证码，3为找回密码验证码
     */
    public ResponseEntity sendVerifyCode(String phone,String verifycode, Integer type){
        try {
            String[] params = {verifycode};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            logger.info("",result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            logger.error("HTTP响应码错误",e);
            return ResponseEntity.createByErrorWithMessage("HTTP响应码错误");
        } catch (JSONException e) {
            // json解析错误
            logger.error(" json解析错误",e);
            return ResponseEntity.createByErrorWithMessage("json解析错误");
        } catch (IOException e) {
            // 网络IO错误
            logger.error(" 网络IO错误",e);
            return ResponseEntity.createByErrorWithMessage("网络IO错误");
        }
        return ResponseEntity.createBySuccess();
    }

}
