package cz.cvut.fel.nss.vestana.service.interfaces;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleCustomerInfo;
import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;

import java.util.List;

public interface ClothingService {

    ClothingArticle getById(Long id);

    ClothingArticle getByName(String name);

    List<ClothingArticle> getAll();

    ClothingArticle save(ClothingArticle item);

    void update(ClothingArticleDto articleDto);

    void deleteById(Long id);

    ClothingArticleCustomerInfo getCustomerInfoByName(String name);
}
