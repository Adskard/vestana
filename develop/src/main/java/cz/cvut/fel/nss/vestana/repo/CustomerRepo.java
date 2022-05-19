package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {
}