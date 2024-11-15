 CREATE TABLE IF NOT EXISTS customer (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255),
     surname VARCHAR(255),
     credit_limit DOUBLE,
     used_credit_limit DOUBLE
 );

 CREATE TABLE IF NOT EXISTS loan (
     id INT AUTO_INCREMENT PRIMARY KEY,
     customer_id INT,
     loan_amount DOUBLE,
     interest_rate DOUBLE,
     number_of_installments INT,
     create_date DATE,
     is_paid BOOLEAN,
     FOREIGN KEY (customer_id) REFERENCES customer(id)
 );

 CREATE TABLE IF NOT EXISTS loan_installment (
     id INT AUTO_INCREMENT PRIMARY KEY,
     loan_id INT,
     amount DOUBLE,
     paid_amount DOUBLE,
     due_date INT,
     payment_date DATE,
     is_paid BOOLEAN,
     FOREIGN KEY (loan_id) REFERENCES loan(id)
 );