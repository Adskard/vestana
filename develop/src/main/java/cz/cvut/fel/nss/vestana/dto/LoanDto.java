package cz.cvut.fel.nss.vestana.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fel.nss.vestana.model.enums.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    private Long id;
    private boolean deleted;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    private DeliveryType deliveryType;
    private CustomerDto customer;
    private List<ItemDto> loanedItems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemDto {
        private Long id;
        private boolean deleted;
        private String name;
        private String description;
        private String imagePath;
        private int size;
        private double price;
    }
}
