package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.entity.Site;

import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

    Optional<Site> findByUrl(String url);
    long deleteByUrl(String url);
}
