package searchengine.model.entity;

import lombok.Builder;
import lombok.Data;
import searchengine.model.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT", nullable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED', 'FAILED')", nullable = false)
    private Status status;

    @Column(name = "status_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime statusTime;

    @Column(name = "last_error", columnDefinition = "TEXT")
    private String lastError;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String url;


    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private Set<Lemma> lemmaSet;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private Set<Page> pages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return id == site.id && status == site.status && statusTime.equals(site.statusTime) && lastError.equals(site.lastError) && url.equals(site.url) && name.equals(site.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, statusTime, lastError, url, name);
    }
}
