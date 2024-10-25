package de.neuefische.java.cgnjava245api;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterApiService characterApiService;

    public CharacterService(CharacterApiService characterApiService) {
        this.characterApiService = characterApiService;
    }

    public List<ApiCharacter> getAllCharacters() {
        return characterApiService.loadAllCharacters();
    }
}
