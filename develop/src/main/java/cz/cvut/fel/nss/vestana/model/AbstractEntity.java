package cz.cvut.fel.nss.vestana.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "BIT")
    private boolean deleted = false;
}
