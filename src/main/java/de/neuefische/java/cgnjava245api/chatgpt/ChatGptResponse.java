package de.neuefische.java.cgnjava245api.chatgpt;

import java.util.List;

public record ChatGptResponse(
        List<ChatGptChoice> choices
) {
}
