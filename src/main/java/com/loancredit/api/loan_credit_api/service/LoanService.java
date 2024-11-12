package com.loancredit.api.loan_credit_api.service;

import com.loancredit.api.loan_credit_api.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loancredit.api.loan_credit_api.repository.LoanRepository;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}