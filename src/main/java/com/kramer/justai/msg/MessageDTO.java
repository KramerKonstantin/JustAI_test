package com.kramer.justai.msg;

public class MessageDTO {
    private String type;
    private String fromId;
    private String text;

    /**
     * A constructor that combines the type of incoming request, the user ID and the incoming message.
     *
     * @param type the type of incoming request.
     * @param fromId to identify the user to whom the response will be sent.
     * @param text the text of the incoming message.
     */
    public MessageDTO(String type, String fromId, String text) {
        this.type = type;
        this.fromId = fromId;
        this.text = text;
    }

    /**
     * A constructor that has only the type of incoming request.
     *
     * @param type the type of incoming request.
     */
    public MessageDTO(String type) {
        this.type = type;
    }

    /**
     *
     * @return the type of incoming request
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return returns the user id.
     */
    public String getFromId() {
        return fromId;
    }

    /**
     *
     * @return the text of the incoming message.
     */
    public String getText() {
        return text;
    }

    /**
     * Generates a response message.
     *
     * @return a message like: "You said: 'incoming message'"
     */
    public String generateResponse() {
        return "Вы сказали: " + text;
    }
}
