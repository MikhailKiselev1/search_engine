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

    @Column(columnDefinition = "INT", nullable = false, name = "page_id")
    private String pageId;

    @Column(columnDefinition = "INT", nullable = false, name = "lemma_id")
    private String lemmaId;

    @Column(name = "`rank`", nullable = false, columnDefinition = "FLOAT")
    private float rank;
}
