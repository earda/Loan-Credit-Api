package com.loancredit.api.loan_credit_api.controller;

import com.loancredit.api.loan_credit_api.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.loancredit.api.loan_credit_api.service.LoanService;
import java.util.List;

@RestController
@RequestMapping("/creditApi/")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
}