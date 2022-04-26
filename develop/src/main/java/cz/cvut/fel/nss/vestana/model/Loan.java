package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.model.enums.DeliveryType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LOAN")
@Data
@EqualsAndHashCode(callSuper = true)
public class Loan extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate start;

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate end;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
}
