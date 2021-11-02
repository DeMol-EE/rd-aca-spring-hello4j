package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(consumes = "text/plain")
    public void sendEmail(
            @RequestParam("to") String to,
            @RequestBody String body
    ) {
        emailService.sendEmail(to, body);
    }
}
