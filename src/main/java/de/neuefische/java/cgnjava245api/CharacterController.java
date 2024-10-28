package de.neuefische.java.cgnjava245api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<ApiCharacter> getAllRickAndMortyCharacters() {
        return characterService.getAllCharacters();
    }

    @PostMapping
    public ApiCharacter addRickAndMortyCharacter(@RequestBody String name) throws JsonProcessingException {
        return characterService.addCharacter(name);
    }
}
