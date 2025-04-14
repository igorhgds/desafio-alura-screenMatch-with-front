package igor.henrique.screenMatchAPI.dtos.episodio.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetDataInputDTO(@JsonAlias("Title") String title,
                              @JsonAlias("Episode") Integer episodeNumber,
                              @JsonAlias("imdbRating") String rating,
                              @JsonAlias("Released") String releaseDate) {
}
