package igor.henrique.screenMatchAPI.rest.controllers;

import igor.henrique.screenMatchAPI.dtos.episodio.output.OutputEpisodioDTO;
import igor.henrique.screenMatchAPI.dtos.serie.output.OutputSerieDTO;
import igor.henrique.screenMatchAPI.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<OutputSerieDTO> getSeries() {
        return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<OutputSerieDTO> getTop5Series() {
        return service.getTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<OutputSerieDTO> getReleases() {
        return service.getReleases();
    }

    @GetMapping("/{id}")
    public OutputSerieDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<OutputEpisodioDTO> getAllSeasons(@PathVariable Long id){
        return service.getAllSeasons(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<OutputEpisodioDTO> getSeasonsByNumber(@PathVariable Long id, @PathVariable Long numero){
        return service.getSeasonsByNumber(id, numero);
    }

    @GetMapping("/categoria/{genero}")
    public List<OutputSerieDTO> getSeriesByCategory(@PathVariable String genero){
        return service.getSeriesByCategory(genero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<OutputEpisodioDTO> getTopEpisodes(@PathVariable Long id){
        return service.getTopEpisodes(id);
    }
}
