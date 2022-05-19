package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothingArticleRepo extends CrudRepository<ClothingArticle, Long> {

    Optional<ClothingArticle> findByName(String name);
}