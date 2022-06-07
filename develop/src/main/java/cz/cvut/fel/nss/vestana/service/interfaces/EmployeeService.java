package cz.cvut.fel.nss.vestana.service.interfaces;

import cz.cvut.fel.nss.vestana.dto.EmployeesDto;
import cz.cvut.fel.nss.vestana.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(Long id);

    Employee findByUsername(String username);

    List<Employee> findAll();

    EmployeesDto mapUsersToEmployeesDto(List<Employee> users);

    Employee register(Employee newUser) throws Exception;
}
