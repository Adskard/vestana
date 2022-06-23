package cz.cvut.fel.nss.vestana.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AVAILABILITY")
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleAvailability extends AbstractEntity {

    @ManyToMany
    @Getter
    private List<TimeEvent> eventList;

    public ArticleAvailability() {
        this.eventList = new ArrayList<>();
    }

    public void addEvent(@NotNull TimeEvent event) {
        if (eventList == null) {
            this.eventList = new ArrayList<>();
        }
        eventList.add(event);
    }
}
