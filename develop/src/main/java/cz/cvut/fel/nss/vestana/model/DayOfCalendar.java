package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class DayOfCalendar extends AbstractEntity {

    private LocalDate localDate;

    private ArticleState articleState;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public ArticleState getArticleState() {
        return articleState;
    }
}
