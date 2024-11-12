package com.loancredit.api.loan_credit_api.service;

import com.loancredit.api.loan_credit_api.model.Customer;
import com.loancredit.api.loan_credit_api.model.Loan;
import com.loancredit.api.loan_credit_api.model.LoanInstallment;
import com.loancredit.api.loan_credit_api.repository.CustomerRepository;
import com.loancredit.api.loan_credit_api.repository.LoanInstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loancredit.api.loan_credit_api.repository.LoanRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LoanInstallmentRepository loanInstallmentRepository;
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // Müşteri limit kontrolü
    public boolean hasEnoughLimit(Long customerId, Double amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Müşterinin kredi limitini al
        Double creditLimit = customer.getCreditLimit();

        // Kredi limitinin yeterli olup olmadığını kontrol et
        return creditLimit != null && creditLimit >= amount;
    }
    public Loan createLoan(Long customerId, Double amount, Double interestRate, Integer numberOfInstallments) {
        // Kontroller
        if (!hasEnoughLimit(customerId, amount)) {
            throw new IllegalArgumentException("Customer does not have enough limit");
        }
        if (numberOfInstallments != 6 && numberOfInstallments != 9 && numberOfInstallments != 12 && numberOfInstallments != 24) {
            throw new IllegalArgumentException("Invalid number of installments. Allowed values: 6, 9, 12, 24");
        }
        if (interestRate < 0.1 || interestRate > 0.5) {
            throw new IllegalArgumentException("Interest rate must be between 0.1 and 0.5");
        }

        // Toplam kredi tutarını hesapla
        Loan loan = new Loan(customerId, amount, interestRate, numberOfInstallments);

        // Taksitleri oluştur
        List<LoanInstallment> installments = new ArrayList<>();
        Double installmentAmount = loan.getTotalAmount() / numberOfInstallments;

        LocalDate dueDate = LocalDate.now().plusMonths(1).withDayOfMonth(1); // İlk taksit için gelecek ayın 1. günü

        for (int i = 0; i < numberOfInstallments; i++) {
            LoanInstallment installment = new LoanInstallment(loan.getId(), installmentAmount, dueDate, false);
            installments.add(installment);
            dueDate = dueDate.plusMonths(1); // Sonraki taksitin ödeme tarihi
        }

        loan.setInstallments(installments);

        // Loan ve taksitleri kaydet
        loanRepository.save(loan);
        loanInstallmentRepository.saveAll(installments);

        return loan;
    }
}