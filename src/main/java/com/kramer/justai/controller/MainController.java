package com.kramer.justai.controller;

import com.kramer.justai.msg.MessageDTO;
import com.kramer.justai.msg.MessageParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
public class MainController {
    private static final String CALLBACK_API_EVENT_CONFIRMATION = "confirmation";
    private static final String CALLBACK_API_EVENT_MESSAGE_NEW = "message_new";
    private static final String VK_API_MESSAGE_SEND_METHOD = "https://api.vk.com/method/messages.send";

    @Value("${ACCESS_TOKEN}")
    private String ACCESS_TOKEN;
    @Value("${CALLBACK_API_CONFIRMATION_TOKEN}")
    private String CALLBACK_API_CONFIRMATION_TOKEN;
    @Value("${VERSION_API}")
    private String VERSION_API;

    private final Random random = new Random();

    private final RestTemplate restTemplate;

    public MainController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Processes incoming requests. If it is a confirmation request, it sends a confirmation token, else if it is a new
     * message, it responds to it, otherwise it does nothing.
     *
     * @param inputMessage incoming request in the form of JSON.
     * @return callback api confirmation token or message that the method has completed - OK
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public String botResponse(@RequestBody String inputMessage) {
        if (inputMessage != null) {
            MessageParser messageParser = new MessageParser();
            MessageDTO messageDTO = messageParser.parseJSON(inputMessage);
            String type = messageDTO.getType();

            if (type.equals(CALLBACK_API_EVENT_CONFIRMATION)) {
                return CALLBACK_API_CONFIRMATION_TOKEN;
            } else if (type.equals(CALLBACK_API_EVENT_MESSAGE_NEW)) {
                String fromId = messageDTO.getFromId();
                String response = generateBotResponse(fromId, messageDTO.generateResponse());

                try {
                    restTemplate.getForObject(response, String.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "OK";
    }

    private String generateBotResponse(String fromId, String text) {
        return VK_API_MESSAGE_SEND_METHOD +
                "?user_id=" + fromId +
                "&message=" + text +
                "&random_id=" + random.nextInt() +
                "&access_token=" + ACCESS_TOKEN +
                "&v=" + VERSION_API;
    }
}
