package cz.cvut.fel.nss.vestana.service;

import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.dto.EmployeeDto;
import cz.cvut.fel.nss.vestana.dto.EmployeesDto;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.repo.EmployeeRepo;
import cz.cvut.fel.nss.vestana.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee findById(@NotNull Long id) {
        Optional<Employee> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Employee with id " + id + " not exists.");
        }
    }

    @Override
    public Employee findByUsername(@NotNull String username) {
        Optional<Employee> result = repo.findByUsername(username);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Employee with username " + username + " not exists.");
        }
    }

    public List<Employee> findAll() {
        List<Employee> users = new ArrayList<>();
        for (Employee employee : repo.findAll()) {
            users.add(employee);
        }
        return users;
    }

    @Override
    public EmployeesDto mapUsersToEmployeesDto(List<Employee> users) {
        List<EmployeeDto> usersDto = users.stream()
                .map(Employee::toDto)
                .collect(Collectors.toList());
        return new EmployeesDto(usersDto);
    }

    @Override
    public Employee register(Employee newUser) throws Exception {
        if (newUser.getUsername().length() < 3) {
            throw new Exception("Selected username is too short. (At least 3 characters.)");
        }

        if (newUser.getPassword().length() < 8) {
            throw new Exception("Selected password is too short. (At least 8 characters.)");
        }
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
                newUser.getPassword())) {
            throw new Exception("Password has to contain at least one digit [0-9], " +
                    "at least one lowercase character [a-z] and " +
                    "at least one uppercase character [A-Z].");
        }

        if (repo.findByUsername(newUser.getUsername()).isPresent()) {
            throw new Exception("Username is already in use.");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return repo.save(newUser);
    }
}