package sda.projekt.rezerwacje.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service("emailService")
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final String MY_MAIL = "damian.rezerwacje@gmail.com";

    @Async
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom(MY_MAIL);
        msg.setSubject(subject);
        msg.setText(content);
        javaMailSender.send(msg);
    }

}

