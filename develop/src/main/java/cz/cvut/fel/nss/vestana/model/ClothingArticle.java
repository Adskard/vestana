package cz.cvut.fel.nss.vestana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClothingArticle extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    private String description;

    private String imagePath;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    private ArticleAvailability availability;

}
