package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.dto.EmployeeDto;
import cz.cvut.fel.nss.vestana.dto.EmployeesDto;
import cz.cvut.fel.nss.vestana.dto.RegisterUserDto;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/v1/user")
public class UserController {

    private EmployeeService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<EmployeesDto> getAllUsers() {
        List<Employee> users = userService.findAll();
        List<EmployeeDto> userDtos = users.stream()
                .map(Employee::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new EmployeesDto(userDtos));
    }

    @GetMapping("(/username/{username}")
    public ResponseEntity<EmployeeDto> getUserByUsername(@PathVariable String username) {
        Employee user = userService.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User with email " + username + " not found");
        }
        return ResponseEntity.ok(user.toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getUserById(@PathVariable Long id) {
        Optional<Employee> user = userService.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("User " + id + " not found");
        } else {
            return ResponseEntity.ok(user.get().toDto());
        }
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterUserDto userDto) {
        System.out.println("ahoj");
        Employee user = new Employee();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println("ahoj2");
        userService.addUser(user);
        System.out.println("ahoj3");
        System.out.println(user.getId());
        URI location = URI.create("/user/" + user.getId());
        return ResponseEntity.created(location).build();
    }

    @Autowired
    public void setUserService(EmployeeService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
