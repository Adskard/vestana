package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "AVAILABILITY")
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleAvailability extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private CalendarMap calendarMap;


    public ArticleState getStatus(LocalDate date) {
        return calendarMap.getStatus(date);
    }

    public boolean checkAvailabilityPeriod (LocalDate start, LocalDate end) {

        boolean available = true;
        LocalDate date = start;

        while (date != end.plusDays(1)) {
            if (getStatus(date) != ArticleState.AVAILABLE) {
                available = false;
                continue;
            }
            date = date.plusDays(1);
        }

        return available;
    }
}
