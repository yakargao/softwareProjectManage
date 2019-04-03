package com.gaoyanshan.bysj.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



/**
 * <pre>类名: MailUtil</pre>
 * <pre>描述: 邮件通知类</pre>
 * <pre>日期: 19-4-3 下午2:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Component
public class MailUtil {


    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.test.username}")
    private String testMail;

    public void sendSimpleEmail(String[] recipients,String subject,String content){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            mailMessage.setTo(recipients);
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            logger.error("发送邮件出错：",e.getMessage());
        }finally {
            for(int i=0;i < recipients.length ; i++)
            logger.info("发送邮件给"+recipients[i]+"成功");
        }
    }

}
