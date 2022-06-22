package cz.cvut.fel.nss.vestana.service;

import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.dto.ReservationRequest;
import cz.cvut.fel.nss.vestana.dto.ReservationResponse;
import cz.cvut.fel.nss.vestana.exception.InvalidStateException;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.model.*;
import cz.cvut.fel.nss.vestana.model.enums.ArticleState;
import cz.cvut.fel.nss.vestana.repo.*;
import cz.cvut.fel.nss.vestana.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepo repo;
    private final CustomerRepo customerRepo;
    private final TimeEventRepo eventRepo;
    private final ClothingArticleRepo articleRepo;
    private final ArticleAvailabilityRepo availabilityRepo;

    @Autowired
    public ReservationServiceImpl(ReservationRepo repo, CustomerRepo customerRepo, TimeEventRepo eventRepo, ClothingArticleRepo articleRepo, ArticleAvailabilityRepo availabilityRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
        this.eventRepo = eventRepo;
        this.articleRepo = articleRepo;
        this.availabilityRepo = availabilityRepo;
    }

    public Reservation createReservation(@NotNull ReservationRequest dto) {
        if (!checkArticlesAvailability(dto.getBookedItems(), dto.getStartTime(), dto.getEndTime())) {
            throw new InvalidStateException("Some articles are not available!");
        }
        Customer customer = customerRepo.save(new Customer(dto.getCustomer().getName(), dto.getCustomer().getEmail(), dto.getCustomer().getPhone(), dto.getCustomer().getDeliveryAddress()));
        List<ClothingArticle> bookedItems = findBookedItems(dto.getBookedItems());
        Reservation reservation = new Reservation(dto.getStartTime(), dto.getEndTime(), customer, bookedItems);
        reservation = repo.save(reservation);
        reservation = setTimeEvent(reservation, bookedItems);
        return repo.save(reservation);
    }

    private Reservation setTimeEvent(Reservation reservation, List<ClothingArticle> items) {
        TimeEvent event = eventRepo.save(new TimeEvent(reservation.getStartTime(), reservation.getEndTime(), ArticleState.BOOKED, reservation.getId()));
        reservation.setEvent(event);
        setArticleAvailability(items, event);
        return reservation;
    }

    private List<ClothingArticle> findBookedItems(List<Long> itemsIdList) {
        return itemsIdList.stream().map(i -> articleRepo.findById(i).orElse(null)).filter(Objects::nonNull).collect(toList());
    }

    private void setArticleAvailability(List<ClothingArticle> items, TimeEvent event) {
        items.forEach(i -> {
            ArticleAvailability availability = i.getAvailability();
            availability.addEvent(event);
            availabilityRepo.save(availability);
        });
    }

    public Boolean checkArticlesAvailability(List<Long> articles, LocalDateTime startTime, LocalDateTime endTime) {
        for (Long id : articles) {
            Optional<ClothingArticle> article = articleRepo.findById(id);
            if (article.isPresent()) {
                List<TimeEvent> eventList = article.get().getAvailability().getEventList();
                for (TimeEvent event : eventList) {
                    if (!event.getEndTime().isBefore(startTime) && !event.getStartTime().isAfter(endTime)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Reservation getById(Long id) {
        Optional<Reservation> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Article with id " + id + " does not exist!");
        }
    }

    @Override
    public List<ReservationResponse> getAll() {
        List<Reservation> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list.stream().map(Reservation::toDto).collect(toList());
    }

    @Override
    public List<ReservationResponse> getAllByCustomerEmail(String email) {
        List<Reservation> list = new ArrayList<>();
        repo.findAll().forEach(i -> {
            if (i.getCustomer().getEmail().equals(email)) {
                list.add(i);
            }
        });
        return list.stream().map(Reservation::toDto).collect(toList());
    }

    @Override
    public ReservationResponse update(ReservationRequest reservationRequest, Long id) {
        // TODO availability check
        if (!checkArticlesAvailability(reservationRequest.getBookedItems(), reservationRequest.getStartTime(), reservationRequest.getEndTime())) {
            throw new InvalidStateException("Some articles are not available!");
        }
        Reservation reservation = getById(id);
        reservation.setStartTime(reservationRequest.getStartTime());
        reservation.setEndTime(reservationRequest.getEndTime());
        Customer customer = customerRepo.save(new Customer(reservationRequest.getCustomer().getName(), reservationRequest.getCustomer().getEmail(), reservationRequest.getCustomer().getPhone(), reservationRequest.getCustomer().getDeliveryAddress()));
        reservation.setCustomer(customer);
        List<ClothingArticle> bookedItems = findBookedItems(reservationRequest.getBookedItems());
        reservation.setBookedItems(bookedItems);
        // TODO time event update
        return repo.save(reservation).toDto();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Reservation> toDelete = repo.findById(id);
        if (toDelete.isPresent()) {
            Reservation reservation = toDelete.get();
            reservation.setDeleted(true);
            // TODO time event delete, availability update
            repo.save(reservation);
        } else {
            throw new NotFoundException("Reservation with id " + id + "does not exist!");
        }
    }
}
