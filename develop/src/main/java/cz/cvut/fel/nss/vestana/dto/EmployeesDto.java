package cz.cvut.fel.nss.vestana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesDto {
    private List<EmployeeDto> users;
}
