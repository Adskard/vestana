package cz.cvut.fel.nss.vestana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String username;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;


}
