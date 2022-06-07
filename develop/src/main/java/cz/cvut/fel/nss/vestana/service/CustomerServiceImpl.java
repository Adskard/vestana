package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.repo.CustomerRepo;
import cz.cvut.fel.nss.vestana.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo repo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo repo) {
        this.repo = repo;
    }
}
