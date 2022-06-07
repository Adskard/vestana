package cz.cvut.fel.nss.vestana.service;

import cz.cvut.fel.nss.vestana.repo.ReservationRepo;
import cz.cvut.fel.nss.vestana.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepo repo;

    @Autowired
    public ReservationServiceImpl(ReservationRepo repo) {
        this.repo = repo;
    }
}
