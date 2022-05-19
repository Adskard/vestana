package cz.cvut.fel.nss.vestana.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegistrationResponse {
    private final String accessToken;
}