package cz.cvut.fel.nss.vestana.controller;

import cz.cvut.fel.nss.vestana.controller.util.RestUtils;
import cz.cvut.fel.nss.vestana.dto.LoanDto;
import cz.cvut.fel.nss.vestana.dto.ReservationRequest;
import cz.cvut.fel.nss.vestana.dto.ReservationResponse;
import cz.cvut.fel.nss.vestana.exception.InvalidStateException;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.model.Loan;
import cz.cvut.fel.nss.vestana.service.interfaces.ClothingService;
import cz.cvut.fel.nss.vestana.service.interfaces.EmployeeService;
import cz.cvut.fel.nss.vestana.service.interfaces.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@Slf4j
public class LoanController {

    private final LoanService loanService;
    private final EmployeeService employeeService;
    private final ClothingService clothingService;

    @Autowired
    public LoanController(LoanService loanService, EmployeeService employeeService, ClothingService clothingService) {
        this.loanService = loanService;
        this.employeeService = employeeService;
        this.clothingService = clothingService;
    }

    /*
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
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
             *//*
        }
        if (availabilityCheck) {
            // set Clothing availability as BOOKED in the period
            for (ClothingArticle item : loan.getLoanedItems()) {
            /*
            if (!clothingService.bookClothing (item, loan.getStart(), loan.getEnd())) {
                availabilityCheck = false;
                break;
            }
             *//*
            }
        }
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", loan.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }*/

    @Deprecated
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Loan getCurrentLoan() {
        // get the loan that the employee is putting together
        // return employeeService.getCurrentLoan();
        return null;
    }

    @Deprecated
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @PutMapping(value = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addItem(@RequestBody ClothingArticle item) {
        final Loan loan = getCurrentLoan();
        try {
            loanService.addItem(loan, item);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Deprecated
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @DeleteMapping(value = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> removeItem(@RequestBody ClothingArticle item) {
        final Loan loan = getCurrentLoan();
        try {
            loanService.removeItem(loan, item);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createLoan(@RequestBody LoanDto loan) {
        try {
            loanService.createLoan(loan);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoanDto>> getAll() {
        List<LoanDto> result;
        try {
            result = loanService.findAll();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanDto> getLoan(@PathVariable Long id) {
        Loan loan;
        try {
            loan = loanService.findLoan(id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(loan.toDto());
    }
}