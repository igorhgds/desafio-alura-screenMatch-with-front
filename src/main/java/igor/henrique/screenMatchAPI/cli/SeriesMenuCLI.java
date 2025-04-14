package igor.henrique.screenMatchAPI.cli;

import igor.henrique.screenMatchAPI.dtos.serie.input.GetDataInputSerieDTO;
import igor.henrique.screenMatchAPI.dtos.temporada.input.GetDataInputTemporadaDTO;
import igor.henrique.screenMatchAPI.entities.Episodio;
import igor.henrique.screenMatchAPI.entities.Serie;
import igor.henrique.screenMatchAPI.enums.Category;
import igor.henrique.screenMatchAPI.repositories.SerieRepository;
import igor.henrique.screenMatchAPI.services.RequestAPI;
import igor.henrique.screenMatchAPI.utils.ConvertInputJsonToObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SeriesMenuCLI {
    private Scanner scanner = new Scanner(System.in);

    private RequestAPI requestAPI = new RequestAPI();
    private ConvertInputJsonToObject converter = new ConvertInputJsonToObject();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private SerieRepository repository;
    private List<Serie> series = new ArrayList<>();
    private Optional<Serie> searchSerie;

    public SeriesMenuCLI(SerieRepository repository) {
        this.repository = repository;
    }

    public void showMenu(){
        var option = -1;
        while(option != 0){
            var menu = """
                    1 - Adicionar Nova Série
                    2 - Adicionar Episódios
                    3 - Listar Séries Salvas
                    4 - Listar Séries Salvas por Categoria
                    5 - Buscar Série Salva por Título
                    6 - Filtrar Séries por Avaliação
                    7 - Top 5 Séries
                    8 - Top 5 Episodios por Série
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    addNewSerie();
                    break;
                case 2:
                    addEpisodes();
                    break;
                case 3:
                    listSeriesSearched();
                    break;
                case 4:
                    listSeriesSearchedCategory();
                    break;
                case 5:
                    searchSavedSeriesByTitle();
                    break;
                case 6:
                    filterSeriesByRating();
                    break;
                case 7:
                    top5Series();
                    break;
                case 8:
                    top5EpisodesBySerie();
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    private void addNewSerie() {
        GetDataInputSerieDTO dataSerie = getDataSeries();
        Serie serie = new Serie(dataSerie);
        repository.save(serie);
        System.out.println(dataSerie);
    }

    private GetDataInputSerieDTO getDataSeries() {
        System.out.println("Digite a série que deseja adicionar: ");
        var titleSerie = scanner.nextLine();
        var json = requestAPI.convertToObject(ENDERECO + titleSerie.replace(" ", "+") + API_KEY);
        GetDataInputSerieDTO data = converter.convertToObject(json, GetDataInputSerieDTO.class);
        return data;
    }

    private void addEpisodes(){
        listSeriesSearched();
        System.out.println("Escolha uma série para buscar os episódios:");
        var titleSerie = scanner.nextLine();

        Optional<Serie> serie = repository.findByTitleContainingIgnoreCase(titleSerie);

        if(serie.isPresent()){
            var serieSearched = serie.get();
            List<GetDataInputTemporadaDTO> seasons = new ArrayList<>();

            for (int i = 1; i <= serieSearched.getTotalSeasons(); i++){
                var json = requestAPI.convertToObject(ENDERECO + serieSearched.getTitle().replace(" ", "+") + "&season=" + i + API_KEY);
                GetDataInputTemporadaDTO data = converter.convertToObject(json, GetDataInputTemporadaDTO.class);
                seasons.add(data);
            }
            seasons.forEach(System.out::println);

            List<Episodio> episodes = seasons.stream()
                    .flatMap(d -> d.episodes().stream()
                            .map(e -> new Episodio(d.number(), e)))
                    .collect(Collectors.toList());

            serieSearched.setEpisodes(episodes);
            repository.save(serieSearched);
        }else {
            System.out.println("Série não encontrada! ");
        }

    }

    private void listSeriesSearched() {
        series = repository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getTitle))
                .forEach(System.out::println);
    }

    private void listSeriesSearchedCategory() {
        System.out.println("Deseja buscar séries de que categoria/gênero? ");
        var categoryName = scanner.nextLine();
        Category category = Category.fromPortugues(categoryName);
        List<Serie> seriesByCategory = repository.findByCategory(category);
        System.out.println("Séries da categoria: " + category);
        seriesByCategory.forEach(System.out::println);
    }

    private void searchSavedSeriesByTitle() {
        System.out.println("Escolha uma série pelo nome: ");
        var titleSerie = scanner.nextLine();
        searchSerie = repository.findByTitleContainingIgnoreCase(titleSerie);

        if(searchSerie.isPresent()){
            System.out.println("Dados da série: " + searchSerie.get());
        }else {
            System.out.println("Série não encontrada!");
        }
    }

    private void filterSeriesByRating() {
    }

    private void top5Series() {
    }

    private void top5EpisodesBySerie() {
    }
}

