package igor.henrique.screenMatchAPI.repositories;

import igor.henrique.screenMatchAPI.entities.Serie;
import igor.henrique.screenMatchAPI.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTitleContainingIgnoreCase(String titleSerie);

    List<Serie> findByCategory(Category category);
}
