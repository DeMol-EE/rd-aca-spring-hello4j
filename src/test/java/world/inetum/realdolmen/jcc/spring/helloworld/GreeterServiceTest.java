package world.inetum.realdolmen.jcc.spring.helloworld;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreeterServiceTest {

    private GreeterService sut;

    @BeforeEach
    void setUp() {
        sut = new GreeterService(
                new NameCleanerService(),
                new PoliteService()
        );
    }

    @Test
    void generatesHelloXGreeting() {
        String greeting = sut.greet("rObIn");
        Assertions.assertEquals("Hello, Robin!", greeting);
    }

    @Test
    void nullReturnsHelloWorld() {
        String greeting = sut.greet(null);
        Assertions.assertEquals("Hello, world!", greeting);
    }
}