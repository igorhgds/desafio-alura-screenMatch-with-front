package igor.henrique.screenMatchAPI.dtos.episodio.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetDataInputEpisodioDTO(@JsonAlias("Title") String title,
                                      @JsonAlias("Episode") Integer episodeNumber,
                                      @JsonAlias("imdbRating") String rating,
                                      @JsonAlias("Released") String releaseDate) {
}
