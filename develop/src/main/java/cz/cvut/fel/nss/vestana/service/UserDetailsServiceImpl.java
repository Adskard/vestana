package cz.cvut.fel.nss.vestana.service;

import java.util.Optional;

import cz.cvut.fel.nss.vestana.model.AppUserDetails;
import cz.cvut.fel.nss.vestana.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private EmployeeService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee user = Optional.ofNullable(userService.findByUsername(s))
                .orElseThrow(() -> new UsernameNotFoundException("User " + s + " does not exist"));
        return new AppUserDetails(user);
    }

    @Autowired
    public void setUserService(EmployeeService userService) {
        this.userService = userService;
    }
}
