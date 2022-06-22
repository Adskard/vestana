package cz.cvut.fel.nss.vestana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class DayOfCalendar extends AbstractEntity {

    @Column(nullable = false)
    @Getter
    private LocalDate date;

    @OneToMany
    @Getter
    @Setter
    private List<TimeEvent> events;

    public void removeEvent(TimeEvent event) {
        Objects.requireNonNull(event);
        if (events == null) {
            return;
        }
        events.removeIf(e -> Objects.equals(e.getId(), event.getId()));
    }

    public void addEvent(TimeEvent event) {
        Objects.requireNonNull(event);
        if (events == null) {
            this.events = new ArrayList<>();
        }
        events.add(event);
    }
}
