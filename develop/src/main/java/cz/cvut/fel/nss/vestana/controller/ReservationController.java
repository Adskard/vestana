package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.dto.ReservationRequest;
import cz.cvut.fel.nss.vestana.dto.ReservationResponse;
import cz.cvut.fel.nss.vestana.model.Reservation;
import cz.cvut.fel.nss.vestana.service.interfaces.ReservationService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Anonymous user
    @PostMapping("/new")
    public ResponseEntity<Void> makeReservation(@RequestBody ReservationRequest reservation) {
        try {
            reservationService.createReservation(reservation);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Anonymous user
    @PostMapping("/availabilityCheck")
    public ResponseEntity<Boolean> isAllClothingArticleAvailable(@RequestBody ReservationRequest reservation) {
        try {
            val result = reservationService.checkArticlesAvailability(reservation.getBookedItems(), reservation.getStartTime(), reservation.getEndTime());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long id) {
        Reservation reservation;
        try {
            reservation = reservationService.getById(id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reservation.toDto());
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("/customer/{email}")
    public ResponseEntity<List<ReservationResponse>> getAllByCustomerEmail(@PathVariable String email) {
        List<ReservationResponse> result;
        try {
            result = reservationService.getAllByCustomerEmail(email);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<ReservationResponse>> getAll() {
        List<ReservationResponse> result;
        try {
            result = reservationService.getAll();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(ReservationRequest reservation, @PathVariable Long id) {
        ReservationResponse result;
        try {
            result = reservationService.update(reservation, id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeReservation(@PathVariable Long id) {
        try {
            reservationService.deleteById(id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
