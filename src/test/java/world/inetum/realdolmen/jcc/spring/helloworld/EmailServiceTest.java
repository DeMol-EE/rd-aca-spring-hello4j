package world.inetum.realdolmen.jcc.spring.helloworld;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "email.from=mock-from",
        "spring.security.user.name=John",
        "spring.security.user.password=Doe"
})
@AutoConfigureMockMvc
class EmailServiceIT {
    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private MockMvc mockMvc;

    @Test
//    @WithMockUser // in case you don't want to skip testing basic auth
    void send() throws Exception {
        mockMvc.perform(post("/email")
                        .queryParam("to", "robin.demol@realdolmen.com")
                        .with(httpBasic("John", "Doe"))
                        .content("This is the body")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is(200));
        // capture mail and assert to/subject/from/body?
        Mockito.verify(javaMailSender)
                .send(ArgumentMatchers.any(SimpleMailMessage.class));
    }
}