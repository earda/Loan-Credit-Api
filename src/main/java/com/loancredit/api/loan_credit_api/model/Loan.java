package com.loancredit.api.loan_credit_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Double loanAmount;
    private Integer numberOfInstallment;
    private Date createDate;
    private Boolean isPaid;
    private Double totalAmount;
    @OneToMany(mappedBy = "loan", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<LoanInstallment> installments;


    public Loan(Long customerId, Double amount, Double interestRate, Integer numberOfInstallments) {
    }
}