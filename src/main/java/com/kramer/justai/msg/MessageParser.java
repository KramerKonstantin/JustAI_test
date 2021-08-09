package com.kramer.justai.msg;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MessageParser {
    public MessageDTO parseJSON(String inputJSON) {
        MessageDTO messageDTO;

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(inputJSON);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String type = jsonObject.get("type").getAsString();
        if (type.equals("message_new")) {
            JsonObject messageJSONObject = jsonObject.getAsJsonObject("object").getAsJsonObject("message");

            String fromId = messageJSONObject.get("from_id").getAsString();
            String text = messageJSONObject.get("text").getAsString();

            messageDTO = new MessageDTO(type, fromId, text);
        } else {
            messageDTO = new MessageDTO(type);
        }

        return messageDTO;
    }
}
