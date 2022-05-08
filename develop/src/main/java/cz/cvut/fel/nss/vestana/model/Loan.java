package cz.cvut.fel.nss.vestana.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.dto.CustomerDto;
import cz.cvut.fel.nss.vestana.dto.LoanDto;
import cz.cvut.fel.nss.vestana.model.enums.DeliveryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "LOAN")
@Data
@EqualsAndHashCode(callSuper = true)
public class Loan extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @Getter
    private LocalDate start;

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @Getter
    private LocalDate end;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @ManyToOne
    private Customer customerToLoan;

    @ManyToMany(cascade = CascadeType.MERGE)
    @Getter
    private List<ClothingArticle> loanedItems;

    public LoanDto toDto() {
        List<LoanDto.ItemDto> items = this.loanedItems.stream()
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
        result.setStart(getStart());
        result.setEnd(getEnd());
        result.setDeliveryType(getDeliveryType());
        result.setCustomer(getCustomerToLoan().toDto());
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
            this.loanedItems = new ArrayList<>();
        }
        loanedItems.add(item);
    }
}
