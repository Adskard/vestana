package cz.cvut.fel.nss.vestana.service.interfaces;

import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;

public interface ClothingService {

    ClothingArticle getById(Long id);

    ClothingArticle getByName(@NotNull String name);

    ClothingArticle save(@NotNull ClothingArticle item);

    void deleteById(@NotNull Long id);
}
