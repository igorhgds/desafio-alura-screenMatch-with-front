package igor.henrique.screenMatchAPI.entities;

import igor.henrique.screenMatchAPI.dtos.serie.input.GetDataInputDTO;
import igor.henrique.screenMatchAPI.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.OptionalDouble;

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

    public Serie() {}

    public Serie(GetDataInputDTO dataSerie){
        this.title = dataSerie.title();
        this.totalSeasons = dataSerie.totalSeasons();
        this.rating = OptionalDouble.of(Double.parseDouble(dataSerie.rating())).orElse(0);

        String rawCategory = dataSerie.catogory();
        if (rawCategory != null && !rawCategory.isBlank()) {
            this.category = Category.fromString(rawCategory.split(",")[0].trim());
        } else {
            this.category = null; // ou algum valor padrão
        }

        this.actors = dataSerie.actors();
        this.poster = dataSerie.poster();
        this.plot = dataSerie.plot();
        // this.sinopse = ConsultaChatGPT.obterTraducao(dadosSerie.sinopse()).trim();
    }

    @Override
    public String toString() {
        return
                "genero=" + category +
                        ", titulo='" + title + '\'' +
                        ", totalTemporadas=" + totalSeasons +
                        ", avaliacao=" + rating +
                        ", atores='" + actors + '\'' +
                        ", poster='" + poster + '\'' +
                        ", sinopse='" + plot + '\'';
    }
}
