package com.kramer.justai;

import com.kramer.justai.Application;
import com.kramer.justai.controller.MainController;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTest {
    private final Random random = new Random();
    @MockBean
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void serverAddressConfirmationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/callback")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\":\"confirmation\",\"group_id\":" + random.nextInt() + "}")
                .characterEncoding("utf-8")
        ).andExpect(status().isOk());
    }

    @Test
    public void messageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/callback")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"type\":\"message_new\"," +
                        "\"object\":{" +
                            "\"message\":{" +
                                "\"date\":" + random.nextInt() + "," +
                                "\"from_id\":" + random.nextInt() + "," +
                                "\"id\":" + random.nextInt() + "," +
                                "\"out\":" + random.nextInt() + "," +
                                "\"peer_id\":" + random.nextInt() + "," +
                                "\"text\":\"Kramer Konstantin\"," +
                                "\"conversation_message_id\":" + random.nextInt() + "," +
                                "\"fwd_messages\":[]," +
                                "\"important\":false," +
                                "\"random_id\":" + random.nextInt() + "," +
                                "\"attachments\":[]," +
                                "\"is_hidden\":false" +
                            "}," +
                            "\"client_info\":{" +
                                "\"button_actions\":[\"text\",\"vkpay\",\"open_app\",\"location\",\"open_link\"]," +
                                "\"keyboard\":true," +
                                "\"inline_keyboard\":true," +
                                "\"carousel\":false," +
                                "\"lang_id\":0" +
                            "}" +
                        "}," +
                        "\"group_id\":" + random.nextInt() + "," +
                        "\"event_id\":\"e69626e7a1d86d7ta5f4938c76fc4479f8b071ab\"" +
                        "}")
                .characterEncoding("utf-8")
        ).andExpect(status().isOk());
    }
}
