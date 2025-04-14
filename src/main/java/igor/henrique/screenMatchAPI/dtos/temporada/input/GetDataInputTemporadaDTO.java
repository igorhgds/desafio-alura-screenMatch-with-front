package igor.henrique.screenMatchAPI.dtos.temporada.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import igor.henrique.screenMatchAPI.dtos.episodio.input.GetDataInputEpisodioDTO;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetDataInputTemporadaDTO(@JsonAlias("Season") Integer number,
                                       @JsonAlias("Episodes") List<GetDataInputEpisodioDTO> episodes) {
}
