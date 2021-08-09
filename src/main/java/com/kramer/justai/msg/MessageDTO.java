package com.kramer.justai.msg;

public class MessageDTO {
    private String type;
    private String fromId;
    private String text;

    public MessageDTO(String type, String fromId, String text) {
        this.type = type;
        this.fromId = fromId;
        this.text = text;
    }

    public MessageDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getFromId() {
        return fromId;
    }

    public String getText() {
        return text;
    }

    public String generateResponse() {
        return "Вы сказали: " + text;
    }
}
