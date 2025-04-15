package igor.henrique.screenMatchAPI.repositories;

import igor.henrique.screenMatchAPI.entities.Episodio;
import igor.henrique.screenMatchAPI.entities.Serie;
import igor.henrique.screenMatchAPI.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTitleContainingIgnoreCase(String titleSerie);

    List<Serie> findByCategory(Category category);

    List<Serie> findByRatingGreaterThanEqual(Double rating);

    List<Serie> findTop5ByOrderByRatingDesc();

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie ORDER BY e.rating DESC LIMIT 5")
    List<Episodio> topEpisodesBySerie(Serie serie);

    @Query("SELECT s FROM Serie s JOIN s.episodes e GROUP BY s ORDER BY MAX(e.releaseDate) DESC LIMIT 5")
    List<Serie> recentReleases();
}
