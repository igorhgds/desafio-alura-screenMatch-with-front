package igor.henrique.screenMatchAPI.cli;

import igor.henrique.screenMatchAPI.dtos.serie.input.GetDataInputDTO;
import igor.henrique.screenMatchAPI.entities.Serie;
import igor.henrique.screenMatchAPI.repositories.SerieRepository;
import igor.henrique.screenMatchAPI.services.RequestAPI;
import igor.henrique.screenMatchAPI.utils.ConvertInputJsonToObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                    8 - Top 5 Epsodios por Série
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    addNewSerie();
                    break;
            }
        }
    }

    private void addNewSerie() {
        GetDataInputDTO dataSerie = getDataSeries();
        Serie serie = new Serie(dataSerie);
        repository.save(serie);
        System.out.println(dataSerie);
    }

    private GetDataInputDTO getDataSeries() {
        System.out.println("Digite a série que deseja adicionar: ");
        var titleSerie = scanner.nextLine();
        var json = requestAPI.convertToObject(ENDERECO + titleSerie.replace(" ", "+" + API_KEY));
        GetDataInputDTO data = converter.convertToObject(json, GetDataInputDTO.class);
        return data;
    }
}

