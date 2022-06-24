package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleCustomerInfo;
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
import java.util.List;
import java.util.stream.Collectors;

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
        ClothingArticle item = new ClothingArticle(itemDto);
        clothingService.save(item);

        URI location = URI.create("/item/" + item.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClothingArticleDto>> getAllArticles() {
        List<ClothingArticleDto> result;
        try {
            result = clothingService.getAll().stream().map(ClothingArticle::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    // Anonymous user
    @GetMapping(value = "info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClothingArticleCustomerInfo>> getAllArticlesCustomerInfo() {
        List<ClothingArticleCustomerInfo> result;
        try {
            result = clothingService.getAll().stream().map(ClothingArticle::toClothingArticleCustomerInfo).collect(Collectors.toList());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClothingArticleDto> getClothingByName(@PathVariable String name) {
        ClothingArticle item;
        try {
            item = clothingService.getByName(name);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(item.toDto());
    }

    // Anonymous user
    @GetMapping(value = "/info/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClothingArticleCustomerInfo> getCustomerInfo(@PathVariable String name) {
        ClothingArticleCustomerInfo item;
        try {
            item = clothingService.getCustomerInfoByName(name);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(item);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateClothing(@RequestBody ClothingArticleDto item) {
        try {
            clothingService.update(item);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> deleteClothing(@PathVariable Long id) {
        try {
            clothingService.deleteById(id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}