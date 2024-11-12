package com.loancredit.api.loan_credit_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class LoanInstallment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String dueDate;
    private Boolean isPaid;
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public LoanInstallment(Long id, Double installmentAmount, LocalDate dueDate, boolean b,Loan loan) {
    }

    public LoanInstallment() {

    }
}
