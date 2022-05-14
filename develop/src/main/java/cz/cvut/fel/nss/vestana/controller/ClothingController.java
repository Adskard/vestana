package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.dto.LoanDto;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.model.Loan;
import cz.cvut.fel.nss.vestana.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        URI location = URI.create("/item/" + item.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingArticleDto> getClothing(@PathVariable Long id) {
        Optional<ClothingArticle> item;
        try {
            item = Optional.ofNullable(clothingService.findItem(id))
                    .orElseThrow(() -> new NotFoundException("Loan id " + id + " not found"));
        } catch (Exception e) {
            //LOG.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(item.get().toDto());
    }
}
