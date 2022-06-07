package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.repo.ArticleAvailabilityRepo;
import cz.cvut.fel.nss.vestana.service.interfaces.ArticleAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleAvailabilityServiceImpl implements ArticleAvailabilityService {

    private ArticleAvailabilityRepo repo;

    @Autowired
    public ArticleAvailabilityServiceImpl(ArticleAvailabilityRepo repo) {
        this.repo = repo;
    }
}