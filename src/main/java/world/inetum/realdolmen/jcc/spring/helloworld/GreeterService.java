package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.stereotype.Service;

@Service
public class GreeterService {

    private final NameCleanerService nameCleanerService;
    private final PoliteService politeService;

    public GreeterService(
            NameCleanerService nameCleanerService,
            PoliteService politeService
    ) {
        this.nameCleanerService = nameCleanerService;
        this.politeService = politeService;
    }

    public String greet(String name) {
        String cleaned = nameCleanerService.cleanName(name);
        return politeService.greetPolitely(cleaned);
    }
}
