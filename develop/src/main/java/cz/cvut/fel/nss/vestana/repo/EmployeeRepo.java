package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    @Query("select e from Employee e where e.username = 'username'")
    Employee findByUsername(String username);
}
