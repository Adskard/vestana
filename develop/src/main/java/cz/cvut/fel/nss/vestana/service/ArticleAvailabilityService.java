package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.repo.ArticleAvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleAvailabilityService {

    private ArticleAvailabilityRepo repo;

    @Autowired
    public ArticleAvailabilityService(ArticleAvailabilityRepo repo) {
        this.repo = repo;
    }
}
