package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleCustomerInfo;
import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.repo.ClothingArticleRepo;
import cz.cvut.fel.nss.vestana.service.interfaces.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<ClothingArticle> getAll() {
        List<ClothingArticle> result = new ArrayList<>();
        repo.findAll().forEach(i -> result.add(i));
        return result;
    }

    public ClothingArticle getByName(@NotNull String name) {
        Optional<ClothingArticle> result = repo.findByName(name);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Article with name " + name + "does not exist!");
        }
    }

    public ClothingArticleCustomerInfo getCustomerInfoByName(@NotNull String name) {
        ClothingArticle article = getByName(name);
        if (article.isDeleted()) {
            throw new NotFoundException("Article with name " + name + "does not exist!");
        }
        return article.toClothingArticleCustomerInfo();
    }

    public ClothingArticle save(@NotNull ClothingArticle item) {
        return repo.save(item);
    }

    public void deleteById(@NotNull Long id) throws NotFoundException {
        Optional<ClothingArticle> toDelete = repo.findById(id);
        if (toDelete.isPresent()) {
            ClothingArticle item = toDelete.get();
            item.setDeleted(true);
            repo.save(item);
        } else {
            throw new NotFoundException("Article with id " + id + "does not exist!");
        }
    }

    public void update(@NotNull ClothingArticleDto article) {
        Optional<ClothingArticle> toUpdate = repo.findById(article.getId());
        if (toUpdate.isPresent()) {
            ClothingArticle item = toUpdate.get();
            item.setName(article.getName());
            item.setDescription(article.getDescription());
            item.setImagePath(article.getImagePath());
            item.setPrice(article.getPrice());
            item.setSize(article.getSize());
        } else {
            throw new NotFoundException("Article with id " + article.getId() + "does not exist!");
        }
    }
}