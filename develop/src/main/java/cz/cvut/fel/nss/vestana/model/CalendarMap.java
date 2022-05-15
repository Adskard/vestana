package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CalendarMap extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    List<DayOfCalendar> calendarDays;

    public ArticleState getStatus(Date date) {
        ArticleState state = ArticleState.AVAILABLE;
        for (DayOfCalendar day : calendarDays) {
            if (day.getDate() == date) {
                state = day.getArticleState();
            }
        }
        return state;
    }
}
