package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(consumes = "text/plain")
    public void sendEmail(@RequestBody String body) {
        emailService.sendEmail("robin.demol@realdolmen.com", body);
    }
}
