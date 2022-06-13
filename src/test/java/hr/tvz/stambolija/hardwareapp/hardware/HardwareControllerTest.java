package hr.tvz.stambolija.hardwareapp.hardware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.
        SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HardwareService hardwareService;

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
    void getAllHardware() throws Exception {
        String token = authenticationToken("admin", "passadmin");

        this.mockMvc.perform(
                        get("/hardware")
                                .with(user("admin")
                                        .password("passadmin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header("authentication", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void getHardwareByCode() throws Exception {
        String token = authenticationToken("admin", "passadmin");

        this.mockMvc.perform(
                        get("/hardware/00000002")
                                .with(user("admin")
                                        .password("passadmin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header("authentication", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    @Transactional
    void save() throws Exception {
        String token = authenticationToken("admin", "passadmin");

        String TEST_CODE = "00000020";
        String TEST_NAME = "RandomName";
        float TEST_PRICE = 100;
        int TEST_STOCK = 3;
        HardwareType TEST_TYPE = HardwareType.CPU;

        final HardwareCommand hardwareCommand = new HardwareCommand(TEST_CODE, TEST_NAME, TEST_PRICE, TEST_TYPE, TEST_STOCK);
        objectMapper = new ObjectMapper();

        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("passadmin")
                                        .roles("ADMIN")
                                )
                                .header("authentication", "Bearer " + token)
                                .with(csrf())
                                .contentType("application/json; charset=utf-8")
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=utf-8"));
    }

    @Test
    @Transactional
    void save_bad() throws Exception {
        String token = authenticationToken("admin", "passadmin");

        String TEST_CODE = "0000020";
        String TEST_NAME = "";
        float TEST_PRICE = -100;
        int TEST_STOCK = 3;
        HardwareType TEST_TYPE = HardwareType.CPU;

        final HardwareCommand hardwareCommand = new HardwareCommand(TEST_CODE, TEST_NAME, TEST_PRICE, TEST_TYPE, TEST_STOCK);
        objectMapper = new ObjectMapper();

        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("passadmin")
                                        .roles("ADMIN")
                                )
                                .header("authentication", "Bearer " + token)
                                .with(csrf())
                                .contentType("application/json; charset=utf-8")
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void delete() throws Exception {
        String token = authenticationToken("admin", "passadmin");

        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/hardware/00000001")
                                .with(user("admin")
                                        .password("passadmin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header("authentication", "Bearer " + token)
                )
                .andExpect(status().isNoContent());
    }
}