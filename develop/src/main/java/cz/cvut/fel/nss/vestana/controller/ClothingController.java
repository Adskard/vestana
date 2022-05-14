package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ClothingController {

    private final ClothingService clothingService;

    @Autowired
    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @PostMapping
    public ResponseEntity<ClothingArticleDto> createClothing(@RequestBody ClothingArticleDto itemDto) {
        ClothingArticle item = new ClothingArticle();
        item.setDescription(itemDto.getDescription());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setSize(itemDto.getSize());
        item.setImagePath(itemDto.getImagePath());

        clothingService.createItem(item);

        URI location = URI.create("/rest/v1/issue/" + item.getId());
        return ResponseEntity.created(location).build();
    }
}
