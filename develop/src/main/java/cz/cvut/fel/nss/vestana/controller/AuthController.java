package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.controller.util.SecurityUtils;
import cz.cvut.fel.nss.vestana.dto.LoginRequest;
import cz.cvut.fel.nss.vestana.dto.LoginResponse;
import cz.cvut.fel.nss.vestana.dto.RegisterUserDto;
import cz.cvut.fel.nss.vestana.dto.RegistrationResponse;
import cz.cvut.fel.nss.vestana.model.AppUserDetails;
import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final EmployeeService employeeService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        val user = (AppUserDetails) authenticate.getPrincipal();
        val accessToken = SecurityUtils.jwtGenerateAccessToken(user.getUser());

        return ResponseEntity.ok(new LoginResponse(accessToken));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody RegisterUserDto user) {
        Employee newUser = new Employee();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        try {
            employeeService.register(newUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        val accessToken = SecurityUtils.jwtGenerateAccessToken(newUser);
        return ResponseEntity.ok(new RegistrationResponse(accessToken));
    }
}