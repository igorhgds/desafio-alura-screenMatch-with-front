package igor.henrique.screenMatchAPI.dtos.serie.output;

import igor.henrique.screenMatchAPI.enums.Category;

public record OutputSerieDTO(
        Long id,
        String title,
        Integer totalSeasons,
        Double rating,
        Category category,
        String actors,
        String poster,
        String plot) {
}
