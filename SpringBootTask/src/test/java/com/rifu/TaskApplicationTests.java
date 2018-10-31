package com.rifu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.lang.reflect.Executable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void sendMail(){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("通知");
        mail.setText("今晚加班哦");

        mail.setTo("13071679487@163.com");
        mail.setFrom("528428122@qq.com");

        mailSender.send(mail);
    }

    @Test
    public void sendMailWithHTMLAndFile() throws Exception {
        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setSubject("通知，这个是可以发送html和文件的");

        helper.setText("<b style='color:blue'> 带颜色的文本 </b>",true);   //第二个参数为true,支持html
        helper.addAttachment("test.png",new File("C:\\Users\\Lifu\\Desktop\\照片\\zava.png"));
        helper.addAttachment("test.png",new File("C:\\Users\\Lifu\\Desktop\\照片\\rifu.png"));


        helper.setTo("13071679487@163.com");
        helper.setFrom("528428122@qq.com");

        mailSender.send(mail);
    }

}
