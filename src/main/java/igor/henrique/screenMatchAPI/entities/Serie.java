package igor.henrique.screenMatchAPI.entities;

import igor.henrique.screenMatchAPI.dtos.serie.input.GetDataInputSerieDTO;
import igor.henrique.screenMatchAPI.enums.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Integer totalSeasons;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String actors;
    private String poster;
    private String plot;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodes = new ArrayList<>();

    public Serie() {}

    public Serie(GetDataInputSerieDTO dataSerie){
        this.title = dataSerie.title();
        this.totalSeasons = dataSerie.totalSeasons();
        this.rating = Optional.ofNullable(dataSerie.rating())
                .map(String::trim)
                .filter(s -> !s.isEmpty() && !s.equalsIgnoreCase("N/A"))
                .map(s -> {
                    try {
                        return Double.parseDouble(s);
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                })
                .orElse(0.0);
        this.category = Optional.ofNullable(dataSerie.category())
                .filter(c -> !c.isBlank())
                .map(c -> c.split(",")[0].trim())
                .map(Category::fromString)
                .orElse(null); // ou Category.DEFAULT

        this.actors = dataSerie.actors();
        this.poster = dataSerie.poster();
        this.plot = dataSerie.plot();
        // this.sinopse = ConsultaChatGPT.obterTraducao(dadosSerie.sinopse()).trim();
    }

    @Override
    public String toString() {
        return
                " Titulo= " + title +
                        ", genero= " + category + '\'' +
                        ", totalTemporadas= " + totalSeasons +
                        ", avaliacao= " + rating +
                        ", atores= " + actors + '\'' +
                        ", poster= " + poster + '\'' +
                        ", sinopse= " + plot + '\'';
    }
}
