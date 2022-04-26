package cz.cvut.fel.nss.vestana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
@Data
@EqualsAndHashCode(callSuper = true)
public class Reservation extends AbstractEntity {

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
