package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepo repo;

    @Autowired
    public ReservationService(ReservationRepo repo) {
        this.repo = repo;
    }
}
