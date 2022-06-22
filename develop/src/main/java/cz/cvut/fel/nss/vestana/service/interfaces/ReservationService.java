package cz.cvut.fel.nss.vestana.service.interfaces;

import cz.cvut.fel.nss.vestana.dto.ReservationRequest;
import cz.cvut.fel.nss.vestana.dto.ReservationResponse;
import cz.cvut.fel.nss.vestana.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(ReservationRequest reservationRequest);

    Boolean checkArticlesAvailability(List<Long> articles, LocalDateTime startTime, LocalDateTime endTime);

    Reservation getById(Long id);

    List<ReservationResponse> getAll();

    List<ReservationResponse> getAllByCustomerEmail(String email);

    ReservationResponse update(ReservationRequest reservationRequest, Long id);

    void deleteById(Long id);
}
