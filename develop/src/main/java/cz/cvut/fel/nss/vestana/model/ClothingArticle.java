package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleCustomerInfo;
import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClothingArticle extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String imagePath;

    @Column(nullable = false)
    @Getter
    @Setter
    private int size;

    @Column(nullable = false)
    @Getter
    @Setter
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private ArticleAvailability availability;

    public ClothingArticle() {
    }

    public ClothingArticle(ClothingArticleDto article) {
        this.name = article.getName();
        this.description = article.getDescription();
        this.imagePath = article.getImagePath();
        this.price = article.getPrice();
        this.size = article.getSize();
        this.availability = new ArticleAvailability();
    }

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

    public ClothingArticleCustomerInfo toClothingArticleCustomerInfo() {
        return new ClothingArticleCustomerInfo(getName(), getDescription(), getPrice(), getSize());
    }
}