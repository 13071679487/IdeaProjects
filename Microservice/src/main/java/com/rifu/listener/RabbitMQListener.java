package com.rifu.listener;

import com.rifu.common.ResponseEntity;
import com.rifu.entity.ZavaMail;
import com.rifu.service.ZavaMailService;
import com.rifu.service.ZavaVerifyCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author Rifu
 * @Date 2018/10/27  17:33
 */
@Service
public class RabbitMQListener {

    Logger logger=LoggerFactory.getLogger(RabbitListener.class);

    @Autowired
    ZavaMailService zavaMailService;

    @Autowired
    ZavaVerifyCodeService zavaVerifyCodeService;

    /**
     * 收到要进行发送邮箱注册验证的消息
     * @param map
     */
    @RabbitListener(queues = "zava.mail")
    public void receiveZavaMailMsg(Map<String,String> map){
//        System.out.println(mail);
       logger.info(map.toString());
        String title =  map.get("title");
        String content = map.get("content");
        String from = map.get("from");
        String to = map.get("to");
        if (title.isEmpty()||content.isEmpty()||from.isEmpty()||to.isEmpty()){
            return;
        }
        ZavaMail mail = new ZavaMail(title, from, to, content);
        logger.info(mail.toString());

     /*   StringBuffer assemContent = new StringBuffer();
        assemContent.append("验证成功,您的初始登录密码是<b>");
        assemContent.append(mail.getContent());
        assemContent.append("</b>,请牢记<br/><a href=''>点击这里去登录</a>");
        mail.setContent(assemContent.toString());*/
        ResponseEntity resp = zavaMailService.sendMail(mail);
        if (resp.success()){
            logger.info("发送邮件成功");
        }else {
            logger.info(resp.getMsg());
        }
    }

    /**
     * 收到要向手机发送验证码的消息
     */
    @RabbitListener(queues = "zava.verifycode")
    public void receiverZavaVerifyCodeMsg(Map<String,String> map){
        logger.info(map.toString());
        String phone =  map.get("phone");
        String verifycode =  map.get("verify");
        Integer type =Integer.parseInt( map.get("type"));
        if (phone.isEmpty()||verifycode.isEmpty()){
            return;
        }
        zavaVerifyCodeService.sendVerifyCode(phone,verifycode,type);
    }
}
