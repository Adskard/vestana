package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepo repo;

    @Autowired
    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }
}
