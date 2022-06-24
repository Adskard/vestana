package cz.cvut.fel.nss.vestana.service.interfaces;

import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.dto.LoanDto;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.model.Loan;

import java.util.List;

public interface LoanService {

    Loan createLoan(LoanDto loan);

    List<LoanDto> findAll();

    Loan findLoan(Long id);

    void removeItem(@NotNull Loan loan, @NotNull ClothingArticle item);

    void addItem(Loan loan, ClothingArticle item);

    boolean isItemInLoan(Loan loan, ClothingArticle item);
}
