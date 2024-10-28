package de.neuefische.java.cgnjava245api.character;

import java.util.List;

public record ApiResponse(
        List<ApiCharacter>  results
) {
}
