package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import searchengine.model.entity.Index;

public interface IndexRepository extends JpaRepository<Index, Long> {
}
