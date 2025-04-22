package igor.henrique.screenMatchAPI.services;

import igor.henrique.screenMatchAPI.dtos.episodio.output.OutputEpisodioDTO;
import igor.henrique.screenMatchAPI.dtos.serie.output.OutputSerieDTO;
import igor.henrique.screenMatchAPI.entities.Serie;
import igor.henrique.screenMatchAPI.enums.Category;
import igor.henrique.screenMatchAPI.mappers.SerieMapper;
import igor.henrique.screenMatchAPI.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<OutputSerieDTO> getAllSeries() {
        return SerieMapper.toDTOList(repository.findAll());
    }

    public List<OutputSerieDTO> getTop5Series() {
        return SerieMapper.toDTOList(repository.findTop5ByOrderByRatingDesc());
    }

    public List<OutputSerieDTO> getReleases() {
        return SerieMapper.toDTOList(repository.recentReleases());
    }

    public OutputSerieDTO getById(Long id) {
        Optional<Serie> serie = repository.findById(id);
        return serie.map(SerieMapper::toDTO).orElse(null);
    }

    public List<OutputEpisodioDTO> getAllSeasons(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodes().stream()
                    .map(e -> new OutputEpisodioDTO(e.getSeason(), e.getEpisodeNumber(), e.getTitle()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<OutputEpisodioDTO> getSeasonsByNumber(Long id, Long number) {
        return repository.getEpisodeBySeason(id, number)
                .stream()
                .map(e -> new OutputEpisodioDTO(e.getSeason(), e.getEpisodeNumber(), e.getTitle()))
                .collect(Collectors.toList());
    }

    public List<OutputSerieDTO> getSeriesByCategory(String genero) {
        Category category = Category.fromPortugues(genero);
        return SerieMapper.toDTOList(repository.findByCategory(category));
    }

    public List<OutputEpisodioDTO> getTopEpisodes(Long id) {
        var serie = repository.findById(id).get();
        return repository.topEpisodesBySerie(serie)
                .stream()
                .map(e -> new OutputEpisodioDTO(e.getSeason(), e.getEpisodeNumber(), e.getTitle()))
                .collect(Collectors.toList());
    }
}
