package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Employee> findById(Long id) {
        return repo.findById(id);
    }

    public Employee findByUsername(String username) {
        return repo.findByUsername(username).get();
    }

    public List<Employee> findAll() {
        List<Employee> users = null;
        for (Employee employee : repo.findAll()) {
            users.add(employee);
        }
        return users;
    }

    @Deprecated
    public void addUser(Employee user) {
        repo.save(user);
    }

    public void register(Employee user) {
        repo.save(new Employee(user.getUsername(), passwordEncoder.encode(user.getPassword())));
    }
}