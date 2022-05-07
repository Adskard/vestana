package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.dto.CustomerDto;
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

    @Basic(optional = false)
    @Column(nullable = false)
    private String email;

    @Basic(optional = false)
    @Column(nullable = false)
    private int phone;

    @Basic(optional = false)
    @Column(nullable = false)
    private String deliveryAddress;

    public void bookReservation() {
        // TODO
    }

    public Customer() {
        this.name = name;
    }

    public CustomerDto toDto() {
        return new CustomerDto(getId(), isDeleted(), name, email, phone, deliveryAddress);
    }
}
