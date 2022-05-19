package cz.cvut.fel.nss.vestana.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
