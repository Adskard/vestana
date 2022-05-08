package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
}
