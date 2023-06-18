package com.pccw.backend.modules.mail;

import com.pccw.backend.modules.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MailService {

    private static final String FROM = "pcww@email.com";
    private static final String SUBJECT = "Successful Registration";

    private final JavaMailSender mailSender;
    private final MailMapper mailMapper;

    public void send(User user) {
        Mail mail = null;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom(FROM);
            helper.setTo(user.getEmail());
            helper.setSubject("Successful Registration");
            String content = String.format("Welcome %s", user.getName().getEffectiveName());
            mimeMessage.setContent(content, "text/html");
            mail = new Mail(user.getEmail(), SUBJECT, content);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Unable to send email");
        } finally {
            mailMapper.insert(mail);
        }
    }

    @Async
    public void sendAsync(User user) {
        send(user);
    }

}
