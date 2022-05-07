package cz.cvut.fel.nss.vestana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private boolean deleted;
    private String name;
    private String email;
    private int phone;
    private String deliveryAddress;

}
