package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.service.interfaces.ClothingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/item")
@Slf4j
public class ClothingController {

    private final ClothingService clothingService;

    @Autowired
    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClothingArticleDto> createClothing(@RequestBody ClothingArticleDto itemDto) {
        ClothingArticle item = new ClothingArticle();
        item.setDescription(itemDto.getDescription());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setSize(itemDto.getSize());
        item.setImagePath(itemDto.getImagePath());

        clothingService.save(item);

        URI location = URI.create("/item/" + item.getId());
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ClothingArticleDto> getClothing(@PathVariable Long id) {
        ClothingArticle item;
        try {
            item = clothingService.getById(id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(item.toDto());
    }
}