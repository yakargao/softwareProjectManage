package com.gaoyanshan.bysj.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * <pre>类名: MailUtil</pre>
 * <pre>描述: 邮件通知类</pre>
 * <pre>日期: 19-4-3 下午2:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Component
public class MailUtil {


    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    private ExecutorService sendMailThreadPool = Executors.newFixedThreadPool(3);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public void sendSimpleEmail(String subject,String content,String... recipients){

        this.sendMailThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setFrom(sender);
                    mailMessage.setSubject(subject);
                    mailMessage.setText(content);
                    mailMessage.setTo(recipients);
                    javaMailSender.send(mailMessage);
                }catch (Exception e){
                    logger.error("发送邮件出错：{}",e.getMessage());
                }finally {
                   logger.info("邮件发送成功：{}",recipients);
                }
            }
        });
    }

    public void sendHtmlEmail(String subject,String content,String...recipients){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(recipients);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(message);
        }catch (Throwable throwable){
            System.out.println(throwable.getMessage());
            logger.error("邮件发送{}出错{}",recipients,throwable.getMessage());
        }finally {
            logger.info("完成邮件发送：{}",recipients);
        }

    }

}
