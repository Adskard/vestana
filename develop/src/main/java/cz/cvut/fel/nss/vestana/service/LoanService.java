package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanService {
    
    private LoanRepo repo;

    @Autowired
    public LoanService(LoanRepo repo) {
        this.repo = repo;
    }
}
