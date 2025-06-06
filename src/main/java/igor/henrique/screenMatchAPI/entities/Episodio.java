package igor.henrique.screenMatchAPI.entities;

import igor.henrique.screenMatchAPI.dtos.episodio.input.GetDataInputEpisodioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releaseDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;

    public Episodio(Integer seasonNumber, GetDataInputEpisodioDTO dataEpisodes){
        this.season = seasonNumber;
        this.title = dataEpisodes.title();
        this.episodeNumber = dataEpisodes.episodeNumber();

        try {
            this.rating = Double.valueOf(dataEpisodes.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(dataEpisodes.releaseDate());
        } catch (DateTimeParseException ex) {
            this.releaseDate = null;
        }
    }

    @Override
    public String toString() {
        return
                " Temporada= " + season +
                ", titulo= " + title + '\'' +
                ", numeroEpisodio= " + episodeNumber +
                ", avaliação= " + rating +
                ", dataLançamento= " + releaseDate;
    }
}
