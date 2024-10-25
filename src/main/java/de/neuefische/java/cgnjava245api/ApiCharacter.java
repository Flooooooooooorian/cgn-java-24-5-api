package de.neuefische.java.cgnjava245api;

import java.time.Instant;
import java.util.List;

public record ApiCharacter(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        ApiOrigin origin,
        ApiLocation location,
        String image,
        List<String> episode,
        String url,
        Instant created
) {
}
