package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.stereotype.Service;

@Service
public class NameCleanerService {

    public String cleanName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        String trimmed = name.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }
}
