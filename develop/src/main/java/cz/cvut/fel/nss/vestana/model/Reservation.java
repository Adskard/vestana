package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.dto.ReservationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "RESERVATION")
@Data
@EqualsAndHashCode(callSuper = true)
public class Reservation extends AbstractEntity {

    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP")
    @Getter
    private LocalDateTime startTime;

    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP")
    @Getter
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Getter
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @Getter
    @Setter
    private Employee employee;

    @OneToOne
    @Setter
    @Getter
    private TimeEvent event;

    @ManyToMany
    @Setter
    @Getter
    private List<ClothingArticle> bookedItems;

    public Reservation() {
    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Customer customer, List<ClothingArticle> bookedItems) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.customer = customer;
        this.bookedItems = bookedItems;
    }

    public ReservationResponse toDto() {
        return new ReservationResponse(getId(), startTime, endTime, customer.toDto(), event.getId(), bookedItems.stream().map(i -> i.getId()).collect(Collectors.toList()));
    }
}
