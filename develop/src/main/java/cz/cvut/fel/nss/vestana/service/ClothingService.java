package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.repo.ClothingArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClothingService {

    private final ClothingArticleRepo repo;

    @Autowired
    public ClothingService(ClothingArticleRepo repo) {
        this.repo = repo;
    }

    public void createItem(ClothingArticle item) {
        repo.save(item);
    }
}
