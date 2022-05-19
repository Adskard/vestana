package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.model.AppUserDetails;
import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private final EmployeeRepo employeeRepo;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee user = employeeRepo.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User " + s + " does not exist"));
        return new AppUserDetails(user);
    }
}