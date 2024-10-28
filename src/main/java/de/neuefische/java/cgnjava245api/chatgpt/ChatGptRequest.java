package de.neuefische.java.cgnjava245api.chatgpt;

import java.util.List;

public record ChatGptRequest(
        String model,
        List<ChatGptMessage> messages,
        ChatGptFormat response_format
) {
}
