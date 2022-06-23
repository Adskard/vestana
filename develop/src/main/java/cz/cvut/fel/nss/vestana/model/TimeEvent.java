package cz.cvut.fel.nss.vestana.model;

import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TIME_EVENT")
@EqualsAndHashCode(callSuper = true)
public class TimeEvent extends AbstractEntity {

    @Column(columnDefinition = "TIMESTAMP")
    @Getter
    @Setter
    private LocalDateTime startTime;

    @Column(columnDefinition = "TIMESTAMP")
    @Getter
    @Setter
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ArticleState articleState;

    @Getter
    @Setter
    private Long reservationOrLoanId;

    public TimeEvent() {
    }

    public TimeEvent(LocalDateTime startTime, LocalDateTime endTime, ArticleState articleState, Long reservationOrLoanId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.articleState = articleState;
        this.reservationOrLoanId = reservationOrLoanId;
    }
}
