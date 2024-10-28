package de.neuefische.java.cgnjava245api.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.java.cgnjava245api.character.ApiCharacter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ChatGptApiService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${app.chatgpt.api.token}")
    private String token;

    public ChatGptApiService(RestClient.Builder builder, ObjectMapper objectMapper) {
        restClient = builder
                .build();
        this.objectMapper = objectMapper;
    }

    public ApiCharacter generateNewCharacter(String name) throws JsonProcessingException {
        ChatGptResponse body = restClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(new ChatGptRequest("gpt-4o-mini",
                        List.of(new ChatGptMessage("Erstelle einen Rick and Morty Character mit dem Namen: " + name + ". Erstelle eine JSON Objekt mit dem folgenden Schema: Key\tType\tDescription\n" +
                                                   "id\tint\tThe id of the character.\n" +
                                                   "name\tstring\tThe name of the character.\n" +
                                                   "status\tstring\tThe status of the character ('Alive', 'Dead' or 'unknown').\n" +
                                                   "species\tstring\tThe species of the character.\n" +
                                                   "type\tstring\tThe type or subspecies of the character.\n" +
                                                   "gender\tstring\tThe gender of the character ('Female', 'Male', 'Genderless' or 'unknown').\n" +
                                                   "origin\tobject\tName and link to the character's origin location.\n" +
                                                   "location\tobject\tName and link to the character's last known location endpoint.\n" +
                                                   "image\tstring (url)\tLink to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.\n" +
                                                   "episode\tarray (urls)\tList of episodes in which this character appeared.\n" +
                                                   "url\tstring (url)\tLink to the character's own URL endpoint.\n" +
                                                   "created\tstring\tTime at which the character was created in the database.", "user")),
                        new ChatGptFormat("json_object")))
                .retrieve()
                .body(ChatGptResponse.class);

//        System.out.println(body.choices().get(0).message().content());

        ApiCharacter apiCharacter = objectMapper.readValue(body.choices().get(0).message().content(), ApiCharacter.class);

        System.out.println(apiCharacter);

        return apiCharacter;
    }
}
