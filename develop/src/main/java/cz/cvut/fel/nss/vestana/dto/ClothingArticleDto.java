package cz.cvut.fel.nss.vestana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothingArticleDto {
    private Long id;
    private boolean deleted;
    private String name;
    private String description;
    private String imagePath;
    private int size;
    private int price;
}
