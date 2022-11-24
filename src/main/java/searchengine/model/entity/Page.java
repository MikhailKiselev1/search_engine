package searchengine.model.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "site_id", columnDefinition = "INT", nullable = false)
    private Site site;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String path;

    @Column(columnDefinition = "INT", nullable = false)
    private int code;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String context;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "index",
            joinColumns = {@JoinColumn(name = "page_id")},
            inverseJoinColumns = {@JoinColumn(name = "lemma_id")}
    )
    private Set<Lemma> lemmaSet;


    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private Set<Index> indices;
}
