package cz.cvut.fel.nss.vestana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    private String email;

    private int phone;

    private String deliveryAddress;

    public void bookReservation() {
        // TODO
    }
}
