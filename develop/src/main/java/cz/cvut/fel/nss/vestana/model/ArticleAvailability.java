package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "AVAILABILITY")
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleAvailability extends AbstractEntity {

    // TODO
    private Map<LocalDate, ArticleState> calendar;


    public ArticleState getStatus(LocalDate date) {
        return calendar.getOrDefault(date, ArticleState.AVAILABLE);
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
