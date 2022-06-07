package cz.cvut.fel.nss.vestana.service;

import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.model.Loan;
import cz.cvut.fel.nss.vestana.repo.LoanRepo;
import cz.cvut.fel.nss.vestana.service.interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    
    private final LoanRepo repo;

    @Autowired
    public LoanServiceImpl(LoanRepo repo) {
        this.repo = repo;
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

    public void removeItem(@NotNull Loan loan, @NotNull ClothingArticle item) {
        if (!isItemInLoan(loan, item)) {
            throw new NotFoundException("Item " + item.getName() + " is not in loan " + loan.getId() + "!");
        }
        loan.removeItem(item);
        repo.save(loan);
    }

    public void addItem(Loan loan, ClothingArticle item) {
        Objects.requireNonNull(loan);
        Objects.requireNonNull(item);
        loan.addItem(item);
        repo.save(loan);
    }

    public boolean isItemInLoan(Loan loan, ClothingArticle item) {
        Objects.requireNonNull(loan);
        Objects.requireNonNull(item);
        List<ClothingArticle> items = loan.getLoanedItems();
        return items.stream().anyMatch((i) -> i.getName().equals(item.getName()));
    }
}
