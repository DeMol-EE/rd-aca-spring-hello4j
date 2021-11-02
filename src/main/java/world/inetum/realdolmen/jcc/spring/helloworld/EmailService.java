package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${email.from:no-reply@realdolmen.com}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String to, String body) {
        var email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject("[JCC - Spring]");
        email.setText(body);
        mailSender.send(email);
    }

}
