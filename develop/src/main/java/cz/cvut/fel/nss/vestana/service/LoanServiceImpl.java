package cz.cvut.fel.nss.vestana.service;

import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.dto.LoanDto;
import cz.cvut.fel.nss.vestana.dto.ReservationRequest;
import cz.cvut.fel.nss.vestana.exception.InvalidStateException;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.model.*;
import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import cz.cvut.fel.nss.vestana.repo.*;
import cz.cvut.fel.nss.vestana.service.interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    
    private final LoanRepo repo;
    private final ClothingArticleRepo articleRepo;
    private final CustomerRepo customerRepo;
    private final TimeEventRepo eventRepo;
    private final ArticleAvailabilityRepo availabilityRepo;

    @Autowired
    public LoanServiceImpl(LoanRepo repo, ClothingArticleRepo articleRepo, CustomerRepo customerRepo, TimeEventRepo eventRepo, ArticleAvailabilityRepo availabilityRepo) {
        this.repo = repo;
        this.articleRepo = articleRepo;
        this.customerRepo = customerRepo;
        this.eventRepo = eventRepo;
        this.availabilityRepo = availabilityRepo;
    }

    public Loan createLoan(@NotNull LoanDto dto) {
        LocalDate startDate = dto.getStartDate();
        LocalDateTime startTime = startDate.atTime(LocalTime.MIN);
        LocalDate endDate = dto.getEndDate();
        LocalDateTime endTime = endDate.atTime(LocalTime.MAX);
        if (!checkArticlesAvailability(dto.getLoanedItems(), startTime, endTime)) {
            throw new InvalidStateException("Some articles are not available!");
        }
        Customer customer = customerRepo.save(new Customer(dto.getCustomer().getName(), dto.getCustomer().getEmail(), dto.getCustomer().getPhone(), dto.getCustomer().getDeliveryAddress()));
        List<ClothingArticle> loanedItems = dto.getLoanedItems().stream().map(i -> articleRepo.findById(i.getId()).get()).collect(toList());
        Loan loan = new Loan(dto.getStartDate(), dto.getEndDate(), dto.getDeliveryType(), customer, loanedItems);
        loan = repo.save(loan);
        loan = setTimeEvent(loan, loanedItems);
        return repo.save(loan);
    }

    private Loan setTimeEvent(Loan loan, List<ClothingArticle> items) {
        TimeEvent event = eventRepo.save(new TimeEvent(LocalDateTime.of(loan.getStartDate(), LocalTime.MIN), LocalDateTime.of(loan.getEndDate(), LocalTime.MAX), ArticleState.LOANED, loan.getId()));
        loan.setEvent(event);
        setArticleAvailability(items, event);
        return loan;
    }

    private void setArticleAvailability(List<ClothingArticle> items, TimeEvent event) {
        items.forEach(i -> {
            ArticleAvailability availability = i.getAvailability();
            availability.addEvent(event);
            availabilityRepo.save(availability);
        });
    }

    public Boolean checkArticlesAvailability(List<LoanDto.ItemDto> articles, LocalDateTime startTime, LocalDateTime endTime) {
        for (LoanDto.ItemDto dto : articles) {
            Optional<ClothingArticle> article = articleRepo.findById(dto.getId());
            if (article.isPresent()) {
                List<TimeEvent> eventList = article.get().getAvailability().getEventList();
                for (TimeEvent event : eventList) {
                    if (!event.getEndTime().isBefore(startTime) && !event.getStartTime().isAfter(endTime) && !event.isDeleted()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Loan findLoan(@NotNull Long id) {
        Optional<Loan> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Loan with id " + id + "not found.");
        }
    }

    public List<LoanDto> findAll() {
        List<Loan> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list.stream().map(Loan::toDto).collect(toList());
    }

    @Deprecated
    public void removeItem(@NotNull Loan loan, @NotNull ClothingArticle item) {
        if (!isItemInLoan(loan, item)) {
            throw new NotFoundException("Item " + item.getName() + " is not in loan " + loan.getId() + "!");
        }
        loan.removeItem(item);
        repo.save(loan);
    }

    @Deprecated
    public void addItem(Loan loan, ClothingArticle item) {
        Objects.requireNonNull(loan);
        Objects.requireNonNull(item);
        loan.addItem(item);
        repo.save(loan);
    }

    @Deprecated
    public boolean isItemInLoan(Loan loan, ClothingArticle item) {
        Objects.requireNonNull(loan);
        Objects.requireNonNull(item);
        List<ClothingArticle> items = loan.getLoanedItems();
        return items.stream().anyMatch((i) -> i.getName().equals(item.getName()));
    }
}
