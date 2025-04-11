package igor.henrique.screenMatchAPI.repositories;

import igor.henrique.screenMatchAPI.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

}
