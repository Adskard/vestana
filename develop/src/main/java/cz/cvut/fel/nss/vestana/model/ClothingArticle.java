package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.dto.LoanDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    public ClothingArticleDto toDto() {
        ClothingArticleDto result = new ClothingArticleDto();
        result.setId(getId());
        result.setDeleted(isDeleted());
        result.setName(getName());
        result.setDescription(getDescription());
        result.setPrice(getPrice());
        result.setSize(getSize());
        result.setImagePath(getImagePath());
        return result;
    }

}
