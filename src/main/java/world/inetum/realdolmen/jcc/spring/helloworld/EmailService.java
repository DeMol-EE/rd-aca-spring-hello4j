package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailSender mailSender;

    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String to, String body) {
        var email = new SimpleMailMessage();
        email.setFrom("robin.demol@realdolmen.com");
        email.setTo(to);
        email.setSubject("[JCC - Spring]");
        email.setText(body);
        mailSender.send(email);
    }

}
