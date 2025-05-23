package igor.henrique.screenMatchAPI.dtos.serie.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetDataInputSerieDTO(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer totalSeasons,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Genre") String category,
        @JsonAlias("Actors") String actors,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Plot") String plot
) {
}
