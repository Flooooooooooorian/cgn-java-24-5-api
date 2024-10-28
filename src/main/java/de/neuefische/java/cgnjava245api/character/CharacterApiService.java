package de.neuefische.java.cgnjava245api.character;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CharacterApiService {

    private final RestClient restClient;

    public CharacterApiService(RestClient.Builder builder) {
        restClient = builder
                .build();
    }

    public List<ApiCharacter> loadAllCharacters() {
        ApiResponse body = restClient.get()
                .uri("https://rickandmortyapi.com/api/character")
                .retrieve()
                .body(ApiResponse.class);

        return body.results();
    }
}
