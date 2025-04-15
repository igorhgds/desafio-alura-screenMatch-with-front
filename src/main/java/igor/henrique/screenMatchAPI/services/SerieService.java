package igor.henrique.screenMatchAPI.services;

import igor.henrique.screenMatchAPI.dtos.serie.output.OutputSerieDTO;
import igor.henrique.screenMatchAPI.entities.Serie;
import igor.henrique.screenMatchAPI.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<OutputSerieDTO> obterTodasAsSeries() {
        return converteDados(repository.findAll());
    }

    public List<OutputSerieDTO> obterTop5Series() {
        return converteDados(repository.findTop5ByOrderByRatingDesc());
    }

    public List<OutputSerieDTO> obterLancamentos() {
        return converteDados(repository.lancamentosMaisRecentes());
    }




    private List<OutputSerieDTO> converteDados(List<Serie> series){
        return series.stream()
                .map(s -> new OutputSerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getCategory(), s.getActors(), s.getPoster(), s.getPlot()))
                .collect(Collectors.toList());
    }
}
