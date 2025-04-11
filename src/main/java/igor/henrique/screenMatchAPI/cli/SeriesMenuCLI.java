package igor.henrique.screenMatchAPI.cli;

import igor.henrique.screenMatchAPI.services.RequestAPI;
import igor.henrique.screenMatchAPI.utils.ConvertInputJsonToObject;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SeriesMenuCLI {
    private Scanner scanner = new Scanner(System.in);

    private RequestAPI requestAPI = new RequestAPI();
    private ConvertInputJsonToObject converter = new ConvertInputJsonToObject();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

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
    }
}

