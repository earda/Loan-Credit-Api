package com.loancredit.api.loan_credit_api.service;

import com.loancredit.api.loan_credit_api.model.Loan;
import com.loancredit.api.loan_credit_api.model.LoanInstallment;
import com.loancredit.api.loan_credit_api.repository.LoanInstallmentRepository;
import com.loancredit.api.loan_credit_api.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("loanInstallmentService")
public class LoanInstallmentService {
    @Autowired
     private   LoanInstallmentRepository loanInstallmentRepository;

    @Autowired
    private LoanRepository loanRepository;

    public LoanInstallment createLoanInstallment(LoanInstallment loanInstallment) {
        return loanInstallmentRepository.save(loanInstallment);
    }

    public List<LoanInstallment> getInstallmentsByLoanId(Long loanId) {
        return loanInstallmentRepository.findByLoanId(loanId);  // Veritabanından ilgili taksitleri getirir
    }



}
