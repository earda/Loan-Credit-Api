package com.loancredit.api.loan_credit_api.controller;

import com.loancredit.api.loan_credit_api.model.Loan;
import com.loancredit.api.loan_credit_api.service.LoanInstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.loancredit.api.loan_credit_api.service.LoanService;
import java.util.List;

@RestController
@RequestMapping("/creditApi")
public class LoanController {

    @Autowired
    private LoanService loanService;

    private LoanInstallmentService loanInstallmentService;

    public LoanController(LoanService loanService, LoanInstallmentService loanInstallmentService) {
        this.loanService = loanService;
        this.loanInstallmentService = loanInstallmentService;
    }



    @Autowired
    public void LoanInstallmentController(
            @Qualifier("loanInstallmentService") LoanInstallmentService installmentService,
            @Qualifier("loanService") LoanService loanService) {
        this.loanInstallmentService = installmentService;
        this.loanService = loanService;
    }


    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/installments/loan/{loanId}")
    public String getInstallmentsByLoanId(@PathVariable Long loanId) {
        return "Installments for loan ID: " + loanId;
    }

    @GetMapping("/loans/{loanId}")
    public ResponseEntity<Loan> getLoan(@PathVariable Long loanId) {
        Loan loan = loanService.getLoanById(loanId); // LoanService'i kullanarak krediyi getir

        if (loan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(loan);
    }

    @PostMapping("/create/{customerId}/{amount}/{interestRate}/{numberOfInstallments}")
    public Loan createLoan(@PathVariable Long customerId,
                           @PathVariable Double amount,
                           @PathVariable Double interestRate,
                           @PathVariable Integer numberOfInstallments) {
        return loanService.createLoan(customerId, amount, interestRate, numberOfInstallments);
    }
}