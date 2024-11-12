package com.loancredit.api.loan_credit_api.controller;

import com.loancredit.api.loan_credit_api.model.Loan;
import com.loancredit.api.loan_credit_api.service.LoanInstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        // InstallmentService'i kullanarak taksitleri getir
        return "Installments for loan ID: " + loanId;
    }

    @GetMapping("/loans/{loanId}")
    public String getLoan(@PathVariable Long loanId) {
        // LoanService'i kullanarak krediyi getir
        return "Loan details for loan ID: " + loanId;
    }

    @PostMapping("/create")
    public Loan createLoan(@RequestParam Long customerId,
                           @RequestParam Double amount,
                           @RequestParam Double interestRate,
                           @RequestParam Integer numberOfInstallments) {
        return loanService.createLoan(customerId, amount, interestRate, numberOfInstallments);
    }
}