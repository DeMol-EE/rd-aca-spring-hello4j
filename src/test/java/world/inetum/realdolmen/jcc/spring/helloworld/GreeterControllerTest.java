package world.inetum.realdolmen.jcc.spring.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreeterControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void greetingAnonymous() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().is(200))
                .andExpect(content().string("Hello, world!"));
    }

    @Test
    void greetingNamed() throws Exception {
        mockMvc.perform(get("/hello").queryParam("name", "john"))
                .andExpect(status().is(200))
                .andExpect(content().string("Hello, John!"));
    }
}