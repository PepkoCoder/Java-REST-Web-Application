package hr.tvz.stambolija.hardwareapp.hardware;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.stambolija.hardwareapp.security.domain.User;
import hr.tvz.stambolija.hardwareapp.security.repository.UserRepository;
import hr.tvz.stambolija.hardwareapp.security.service.JwtService;
import hr.tvz.stambolija.hardwareapp.security.service.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String authenticationToken(String user, String pass) throws Exception {
        Map loginData = new HashMap<>();
        loginData.put("username", user);
        loginData.put("password", pass);
        MvcResult mvcResult = this.mockMvc.perform(
                post("https://localhost:8080/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginData))
        ).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        response = response.replace("{\"jwt\":\"", "");
        String token = response.replace("\"}", "");
        return token;
    }

    @Test
    void getAllReviews() throws Exception {
        String token = authenticationToken("admin", "passadmin");

        this.mockMvc.perform(
                        get("/review")
                                .with(user("admin")
                                        .password("passadmin")
                                        .roles("ADMIN")
                                )
                                .header("authentication", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getReviewByHardwareCode() throws Exception{
        String token = authenticationToken("admin", "passadmin");

        this.mockMvc.perform(
                        get("/review/?jmbag=00000001")
                                .with(user("admin")
                                        .password("passadmin")
                                        .roles("ADMIN")
                                ).with(csrf())
                                .header("authentication", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }
}