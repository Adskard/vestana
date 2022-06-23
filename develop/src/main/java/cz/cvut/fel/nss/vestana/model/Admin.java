package cz.cvut.fel.nss.vestana.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Admin extends Employee {
    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin() {

    }
}
