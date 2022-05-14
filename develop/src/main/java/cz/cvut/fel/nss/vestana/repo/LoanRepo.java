package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends CrudRepository<Loan, Long> {
}
