package cz.cvut.fel.nss.vestana.model;

import com.sun.istack.NotNull;
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

    public Customer() {
        this.name = name;
    }

    public Customer(@NotNull String name, @NotNull String email, @NotNull int phone, @NotNull String deliveryAddress) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.deliveryAddress = deliveryAddress;
    }

    public CustomerDto toDto() {
        return new CustomerDto(getId(), isDeleted(), name, email, phone, deliveryAddress);
    }
}
