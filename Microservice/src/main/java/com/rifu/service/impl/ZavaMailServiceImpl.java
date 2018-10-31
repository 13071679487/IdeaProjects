package com.rifu.service.impl;

import com.rifu.common.ResponseEntity;
import com.rifu.entity.ZavaMail;
import com.rifu.mapper.ZavaMailMapper;
import com.rifu.service.ZavaMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author Rifu
 * @Date 2018/10/27  16:52
 */
@Service
public class ZavaMailServiceImpl implements ZavaMailService {

    private static final Logger logger=LoggerFactory.getLogger(ZavaMailServiceImpl.class);

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    ZavaMailMapper zavaMailMapper;

    public ResponseEntity sendMail(ZavaMail zavaMail) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
            messageHelper.setSubject(zavaMail.getTitle());

            messageHelper.setText(zavaMail.getContent(),true);
            /*String to = zavaMail.getTo();
            String[] split = to.split("-");
            List<String> receiver=new ArrayList<>();
            for(String str:split){
                if(!str.isEmpty()){
                    receiver.add(str);
                }
            }*/
            messageHelper.setTo(zavaMail.getTo());
            messageHelper.setFrom(zavaMail.getFrom());
            mailSender.send(mail);
        } catch (MessagingException e) {
            logger.error("",e);
            return ResponseEntity.createByErrorWithMessage("发送邮件失败");
        }
       /* int rowCount = zavaMailMapper.insert(zavaMail);
        if(rowCount<1){
            logger.error("同步信息到数据库失败");
            return ResponseEntity.createBySuccess("同步信息到数据库失败");
        }*/
        return ResponseEntity.createBySuccess();
    }

    public ResponseEntity sendMailSuccess(String to){
        Calendar rightNow=Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MINUTE,-5);
        boolean hasSend = zavaMailMapper.hasSendMail(to,rightNow.getTime())>0?true:false;
        if(hasSend){
            return ResponseEntity.createBySuccess();
        }
        return ResponseEntity.createByError();
    }
}
