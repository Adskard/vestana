package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.ArticleAvailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleAvailabilityRepo extends CrudRepository<ArticleAvailability, Long> {
}
