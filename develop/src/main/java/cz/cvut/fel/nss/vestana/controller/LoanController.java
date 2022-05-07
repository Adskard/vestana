package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.controller.util.RestUtils;
import cz.cvut.fel.nss.vestana.dto.LoanDto;
import cz.cvut.fel.nss.vestana.exception.InvalidStateException;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    //private final LoanService loanService;

    //private final UserService userService;

    //private final ClothingService clothingService;

    @Autowired
    public LoanController() {
        //this.loanService = loanService;
        //this.userService = userService;
        //this.clothingService = clothingService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> finishLoan(@RequestBody() Loan loan) {
        final Loan newLoan = loan;
        boolean checkLoanAttributes = true;
        // loanService.loanCheck(loan) - so that CUSTOMER, STARTDATE and ENDDATE are filled and/or make sense
        if (!checkLoanAttributes) {
            throw new InvalidStateException("Attributes of the loan are missing or don't make sense.");
        }

        // for every item in the loan mark the dates for ArticleAvailability as BOOKED
        // need loanService
        boolean availabilityCheck = true;
        for (ClothingArticle item : loan.getLoanedItems()) {
            // checkAvailiability
            /*
            if (!clothingService.checkAvailability(item, loan.getStart(), loan.getEnd())) {
                availabilityCheck = false;
                break;
            }
             */
        }
        if (availabilityCheck) {
            // set Clothing availability as BOOKED in the period
            for (ClothingArticle item : loan.getLoanedItems()) {
            /*
            if (!clothingService.bookClothing (item, loan.getStart(), loan.getEnd())) {
                availabilityCheck = false;
                break;
            }
             */
            }
        }
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", loan.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Loan getCurrentLoan() {
        // get the loan that the employee is putting together
        // return userService.getCurrentLoan();
        return null;
    }

    @PutMapping(value = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItem(@RequestBody ClothingArticle item) {
        final Loan loan = getCurrentLoan();
        // add clothingArticle to the loan
        // loanService.addItem(loan, item)
    }

    @DeleteMapping(value = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeItem(@RequestBody ClothingArticle item) {
        final Loan loan = getCurrentLoan();
        //loanService.removeItem(loan, item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> getLoan(@PathVariable Long id) {
       // Loan loan = Optional.ofNullable(loanService.findLoan(id))
       //         .orElseThrow(() -> new NotFoundException("Loan id " + id + " not found"));
       // return ResponseEntity.ok(loan.toDto());
        return null;
    }

}
