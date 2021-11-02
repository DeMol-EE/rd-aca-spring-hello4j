package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class GreeterController {

    private final GreeterService greeterService;

    @Value("${spring.mail.username}")
    String hello;

    public GreeterController(GreeterService greeterService) {
        this.greeterService = greeterService;
    }

    @GetMapping
    @ResponseBody
    public String greet(@RequestParam(required = false) String name) {
        System.out.println("greeting controller init with email: " + hello);
        return greeterService.greet(name);
    }

}
