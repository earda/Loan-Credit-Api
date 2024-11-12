package com.loancredit.api.loan_credit_api.repository;

import com.loancredit.api.loan_credit_api.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  LoanRepository extends JpaRepository<Loan, Long> {

}
