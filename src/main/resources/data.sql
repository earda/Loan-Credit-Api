-- Customer tablosuna veri ekleme
INSERT INTO PUBLIC.customer (id,name, surname, credit_limit, used_credit_limit)
VALUES (1,'John', 'Doe', 10000.00, 5000.00);

-- Loan tablosuna veri ekleme
INSERT INTO PUBLIC.Loan (customer_id, loan_amount, interest_rate, number_of_installments, create_date, is_paid)
VALUES (1, 5000.00, 5.0, 12,  DATE '2024-11-12', FALSE);

-- Loan Installment tablosuna veri ekleme
INSERT INTO PUBLIC.loan_installment (loan_id, amount, paid_amount, due_date, payment_date, is_paid)
VALUES (1, 500.00, 0.00, NULL,DATE '2024-11-12', FALSE);