package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "AVAILABILITY")
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleAvailability extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private CalendarMap calendarMap;


    public ArticleState getStatus(Date date) {
        return calendarMap.getStatus(date);
    }

    public boolean checkAvailabilityPeriod (Date start, Date end) {

        boolean available = true;
        Date date = start;

        while (date != addOneDay(end)) {
            if (getStatus(date) != ArticleState.AVAILABLE) {
                available = false;
                continue;
            }
            date = addOneDay(date);
        }

        return available;
    }

    private Date addOneDay(Date curr) {
        Calendar c = Calendar.getInstance();
        c.setTime(curr);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
