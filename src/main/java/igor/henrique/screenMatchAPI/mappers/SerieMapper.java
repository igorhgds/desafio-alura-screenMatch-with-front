package igor.henrique.screenMatchAPI.mappers;

import igor.henrique.screenMatchAPI.dtos.serie.output.OutputSerieDTO;
import igor.henrique.screenMatchAPI.entities.Serie;

import java.util.List;
import java.util.stream.Collectors;

public class SerieMapper {

    public static OutputSerieDTO toDTO(Serie s) {
        return new OutputSerieDTO(
                s.getId(),
                s.getTitle(),
                s.getTotalSeasons(),
                s.getRating(),
                s.getCategory(),
                s.getActors(),
                s.getPoster(),
                s.getPlot()
        );
    }

    public static List<OutputSerieDTO> toDTOList(List<Serie> series) {
        return series.stream()
                .map(SerieMapper::toDTO)
                .collect(Collectors.toList());
    }
}
