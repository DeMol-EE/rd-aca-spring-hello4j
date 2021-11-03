package world.inetum.realdolmen.jcc.spring.helloworld;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "email.from=mock-from",
        "email.subject=mock-subject",
})
@AutoConfigureMockMvc
class EmailControllerIT {
    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private MockMvc mockMvc;

    @Value("${email.subject}")
    private String subject;

    @Value("${email.from}")
    private String from;

    @Test
    @WithMockUser(roles = "EMAIL")
    void send() throws Exception {
        var to = "robin.demol@realdolmen.com";
        var body = "This is the body";
        mockMvc.perform(post("/email")
                        .queryParam("to", to)
                        .content(body)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is(200));
        var mailCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        Mockito.verify(javaMailSender)
                .send(mailCaptor.capture());
        var simpleMailMessage = mailCaptor.getValue();
        Assertions.assertNotNull(simpleMailMessage);
        Assertions.assertEquals(subject, simpleMailMessage.getSubject());
        Assertions.assertEquals(from, simpleMailMessage.getFrom());
        Assertions.assertNotNull(simpleMailMessage.getTo());
        Assertions.assertEquals(1, simpleMailMessage.getTo().length);
        Assertions.assertEquals(to, simpleMailMessage.getTo()[0]);
        Assertions.assertEquals(body, simpleMailMessage.getText());
    }

    // Ensure that @Async methods are actually executed synchronously
    @TestConfiguration
    static class SyncTaskExecutorConfiguration {
        @Bean
        TaskExecutor taskExecutor() {
            return new SyncTaskExecutor();
        }
    }
}