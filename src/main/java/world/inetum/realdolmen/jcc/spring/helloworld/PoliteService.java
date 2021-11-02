package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.stereotype.Component;

@Component
public class PoliteService {

    public String greetPolitely(String name) {
        return "Hello, " + (name == null ? "world" : name) + "!";
    }
}
