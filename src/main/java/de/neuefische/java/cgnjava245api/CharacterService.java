package de.neuefische.java.cgnjava245api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterApiService characterApiService;
    private final ChatGptApiService chatGptApiService;

    public CharacterService(CharacterApiService characterApiService, ChatGptApiService chatGptApiService) {
        this.characterApiService = characterApiService;
        this.chatGptApiService = chatGptApiService;
    }

    public List<ApiCharacter> getAllCharacters() {
        return characterApiService.loadAllCharacters();
    }

    public ApiCharacter addCharacter(String name) throws JsonProcessingException {
        return chatGptApiService.generateNewCharacter(name);
    }
}
