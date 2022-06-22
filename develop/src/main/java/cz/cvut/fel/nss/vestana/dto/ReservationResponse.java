package cz.cvut.fel.nss.vestana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private CustomerDto customer;
    private Long eventId;
    private List<Long> bookedItems;
}
