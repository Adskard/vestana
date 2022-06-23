package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.dto.LoanDto;
import cz.cvut.fel.nss.vestana.model.enums.DeliveryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "LOAN")
@Data
@EqualsAndHashCode(callSuper = true)
public class Loan extends AbstractEntity {

    @Basic(optional = false)
    @Column(columnDefinition = "DATE")
    @Getter
    private LocalDate startDate;

    @Basic(optional = false)
    @Column(columnDefinition = "DATE")
    @Getter
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @ManyToOne
    private Customer customerToLoan;

    @ManyToMany(cascade = CascadeType.MERGE)
    @Getter
    private List<ClothingArticle> loanedItems;

    public LoanDto toDto() {
        List<LoanDto.ItemDto> items = loanedItems.stream()
                .map(i -> new LoanDto.ItemDto(
                        i.getId(),
                        i.isDeleted(),
                        i.getName(),
                        i.getDescription(),
                        i.getImagePath(),
                        i.getSize(),
                        i.getPrice()))
                .collect(Collectors.toList());

        LoanDto result = new LoanDto();
        result.setId(getId());
        result.setDeleted(isDeleted());
        result.setStart(startDate);
        result.setEnd(endDate);
        result.setDeliveryType(deliveryType);
        result.setCustomer(customerToLoan.toDto());
        result.setLoanedItems(items);
        return result;
    }

    public void removeItem(ClothingArticle item) {
        Objects.requireNonNull(item);
        if (loanedItems == null) {
            return;
        }
        loanedItems.removeIf(i -> Objects.equals(i.getId(), item.getId()));
    }

    public void addItem(ClothingArticle item) {
        Objects.requireNonNull(item);
        if (loanedItems == null) {
            loanedItems = new ArrayList<>();
        }
        loanedItems.add(item);
    }
}
