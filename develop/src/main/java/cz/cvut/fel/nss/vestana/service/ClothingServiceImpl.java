package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.repo.ClothingArticleRepo;
import cz.cvut.fel.nss.vestana.service.interfaces.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClothingServiceImpl implements ClothingService {

    private final ClothingArticleRepo repo;

    @Autowired
    public ClothingServiceImpl(ClothingArticleRepo repo) {
        this.repo = repo;
    }

    public ClothingArticle getById(@NotNull Long id) {
        Optional<ClothingArticle> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Article with id " + id + "does not exist!");
        }
    }

    public ClothingArticle getByName(@NotNull String name) {
        Optional<ClothingArticle> result = repo.findByName(name);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Article with name " + name + "does not exist!");
        }
    }

    public ClothingArticle save(@NotNull ClothingArticle item) {
        return repo.save(item);
    }

    public void deleteById(@NotNull Long id) throws NotFoundException {
        Optional<ClothingArticle> toDelete = repo.findById(id);
        if (toDelete.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new NotFoundException("Article with id " + id + "does not exist!");
        }
    }
}