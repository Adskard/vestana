package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingArticleRepo extends CrudRepository<ClothingArticle, Integer> {
}
