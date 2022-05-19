package cz.cvut.fel.nss.vestana.service;

import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.exception.NotFoundException;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.model.Loan;
import cz.cvut.fel.nss.vestana.repo.ClothingArticleRepo;
import cz.cvut.fel.nss.vestana.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class LoanService {
    
    private LoanRepo repo;
    private ClothingArticleRepo clothingArticleRepo;

    @Autowired
    public LoanService(LoanRepo repo, ClothingArticleRepo clothingArticleRepo) {
        this.repo = repo;
        this.clothingArticleRepo = clothingArticleRepo;
    }

    public Optional<Loan> findLoan(Long id) {
        return repo.findById(id);
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
        return items.stream().anyMatch((i) -> i.getName() == item.getName());
    }
}
