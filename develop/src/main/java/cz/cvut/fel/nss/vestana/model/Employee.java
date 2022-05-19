package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.dto.EmployeeDto;
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

    public EmployeeDto toDto() {
        EmployeeDto result = new EmployeeDto();
        result.setId(getId());
        //result.setDeleted(isDeleted());
        result.setUsername(getUsername());
        return result;
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee() {
    }
}