package cz.cvut.fel.nss.vestana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CalendarMap extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    List<DayOfCalendar> calendarDays;
}
