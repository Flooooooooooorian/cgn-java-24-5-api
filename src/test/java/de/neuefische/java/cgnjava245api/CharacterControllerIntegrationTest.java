package de.neuefische.java.cgnjava245api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class CharacterControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MockRestServiceServer mockRestServiceServer;

    @Test
    void getAllRickAndMortyCharacters() throws Exception {
        //GIVEN
        mockRestServiceServer.expect(requestTo("https://rickandmortyapi.com/api/character"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                        {
                            "info": {
                                "count": 826,
                                "pages": 42,
                                "next": "https://rickandmortyapi.com/api/character?page=2",
                                "prev": null
                            },
                            "results": [
                                {
                                    "id": 1,
                                    "name": "Florian",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "Earth (C-137)",
                                        "url": "https://rickandmortyapi.com/api/location/1"
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    },
                                    "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                    "episode": [
                                        "https://rickandmortyapi.com/api/episode/1"
                                    ],
                                    "url": "https://rickandmortyapi.com/api/character/1",
                                    "created": "2017-11-04T18:48:46.250Z"
                                }
                            ]
                        }
                        """, MediaType.APPLICATION_JSON));

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         [{
                          "id": 1,
                          "name": "Florian",
                          "status": "Alive",
                          "species": "Human",
                          "type": "",
                          "gender": "Male",
                          "origin": {
                              "name": "Earth (C-137)",
                              "url": "https://rickandmortyapi.com/api/location/1"
                          },
                          "location": {
                              "name": "Citadel of Ricks",
                              "url": "https://rickandmortyapi.com/api/location/3"
                          },
                          "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                          "episode": [
                              "https://rickandmortyapi.com/api/episode/1"
                          ],
                          "url": "https://rickandmortyapi.com/api/character/1",
                          "created": "2017-11-04T18:48:46.250Z"
                        }]
                        """));

        //THEN
    }
}
