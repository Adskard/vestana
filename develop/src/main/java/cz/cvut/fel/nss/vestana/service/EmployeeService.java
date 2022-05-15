package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.model.Loan;
import cz.cvut.fel.nss.vestana.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepo repo;

    @Autowired
    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    public Optional<Employee> findById(Long id) {
        return repo.findById(id);
    }

    public Employee findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public List<Employee> findAll() {
        List<Employee> users = null;
        for (Employee employee : repo.findAll()) {
            users.add(employee);
        }
        return users;
    }

    public void addUser(Employee user) {
        repo.save(user);
    }
}
