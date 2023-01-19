package searchengine.model.entity;

import lombok.Data;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Lemma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @Column(nullable = false)
    private String lemma;

    @Column(nullable = false)
    private int frequency;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "index",
            joinColumns = {@JoinColumn(name = "lemma_id")},
            inverseJoinColumns = {@JoinColumn(name = "page_id")}
    )
    private Set<Page> pages;

}
