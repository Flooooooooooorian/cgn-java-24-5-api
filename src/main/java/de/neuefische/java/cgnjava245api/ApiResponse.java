package de.neuefische.java.cgnjava245api;

import java.util.List;

public record ApiResponse(
        List<ApiCharacter>  results
) {
}
