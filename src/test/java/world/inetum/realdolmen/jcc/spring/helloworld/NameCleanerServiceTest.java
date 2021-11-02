package world.inetum.realdolmen.jcc.spring.helloworld;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NameCleanerServiceTest {

    private final NameCleanerService sut = new NameCleanerService();

    @Test
    void nullReturnsNull() {
        String cleaned = sut.cleanName(null);
        Assertions.assertNull(cleaned);
    }

    @Test
    void returnsCapitalized() {
        String cleaned = sut.cleanName("  aBcHH ");
        Assertions.assertEquals("Abchh", cleaned);
    }
}