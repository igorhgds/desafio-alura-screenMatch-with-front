package igor.henrique.screenMatchAPI.rest.controllers;

import igor.henrique.screenMatchAPI.dtos.serie.output.OutputSerieDTO;
import igor.henrique.screenMatchAPI.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
