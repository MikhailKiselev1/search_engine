package searchengine.model.entity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "`index`")
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT", nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "page_id", referencedColumnName = "id", nullable = false, columnDefinition = "INT")
    private Page page;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lemma_id", referencedColumnName = "id", nullable = false, columnDefinition = "INT")
    private Lemma lemma;

    @Column(name = "`rank`", nullable = false, columnDefinition = "FLOAT")
    private float rank;
}
