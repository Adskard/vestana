package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.TimeEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeEventRepo extends CrudRepository<TimeEvent, Long> {
}
