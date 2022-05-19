package cz.cvut.fel.nss.vestana.repo;

import cz.cvut.fel.nss.vestana.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Long> {
}
