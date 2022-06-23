package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.dto.EmployeeDto;
import cz.cvut.fel.nss.vestana.dto.EmployeesDto;
import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.service.interfaces.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final EmployeeService userService;

    @Autowired
    public UserController(EmployeeService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<EmployeesDto> getAllUsers() {
        List<Employee> users = userService.findAll();
        return ResponseEntity.ok(userService.mapUsersToEmployeesDto(users));
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getUserByUsername(@PathVariable String username) {
        Employee user;
        try {
            user = userService.findByUsername(username);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user.toDto());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getUserById(@PathVariable Long id) {
        Employee user;
        try {
            user = userService.findById(id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user.toDto());
    }
}