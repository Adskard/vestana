package cz.cvut.fel.nss.vestana;

import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("init")
public class SampleDataLoader implements ApplicationRunner {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Employee user0 = createUser("user0");

    }

    private Employee createUser(String name) {
        Employee user = new Employee();
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode("password"));
        employeeService.addUser(user);
        System.out.println(employeeService.findById(Long.valueOf(1)).get().getUsername());
        return user;
    }
}
