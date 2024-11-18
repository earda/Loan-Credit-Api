# Loan-Credit-Api

Loan-Credit-Api, müşteriler için kredi ve kredi taksit yönetimi sağlayan bir API'dir. Bu proje, bir müşteri yönetimi, kredi oluşturma ve kredi taksitlerinin ödeme işlemlerini içerir. Proje, Spring Boot kullanılarak geliştirilmiş ve H2 veritabanı kullanılarak veri depolama sağlanmıştır.

## Özellikler

- **Müşteri Yönetimi**: Müşterilerin bilgilerini (id, isim, soyisim, kredi limiti, kullanılan kredi limiti) tutar.
- **Kredi Yönetimi**: Kredi başvuru ve takip işlemleri; kredi miktarı, taksit sayısı, oluşturulma tarihi ve ödeme durumu gibi bilgileri içerir.
- **Kredi Taksit Yönetimi**: Her kredi için oluşturulmuş taksitlerin durumu, ödeme miktarı ve son ödeme tarihi gibi bilgilerle taksit yönetimi sağlar.
- **Yetkilendirme**: Yalnızca ADMIN rolüne sahip kullanıcılar tüm müşteriler üzerinde işlem yapabilir, CUSTOMER rolüne sahip kullanıcılar yalnızca kendi hesaplarıyla ilgili işlemler yapabilir.

## Kullanılan Teknolojiler

- **Java**: Programlama dili
- **Spring Boot**: Uygulama geliştirme framework'ü
- **Spring Data JPA**: ORM aracı
- **H2 Database**: Hafif, bellekte çalışan bir veritabanı
- **Maven**: Proje yönetimi ve bağımlılık yönetim aracı

## API Endpointleri

###  Loan Controller

- `GET creditApi/loans/{loanId}`: Belirtilen ID'ye sahip kredi bilgisini getirir.

 Örnek; http://localhost:8080/creditApi/loans/1
 
- `POST creditApi/create/{customerId}/{amount}/{interestRate}/{numberOfInstallments}`: Yeni bir kredi oluşturur.

Örnek; http://localhost:8080/creditApi/create/1/10000/0.15/6
 
- `GET creditApi/loans`: Bütün kredi bilgilerini getirir.

Örnek; http://localhost:8080/creditApi/loans
 
- `GET creditApi/installments/loan/{loanId}`: Belirtilen ID'ye sahip taksit tutarını getirir.

 Örnek; http://localhost:8080/creditApi/installments/loan/1

## Proje Yapısı
 ![projecttree](https://github.com/user-attachments/assets/39993910-8e15-4387-a10e-0b6e68efe4e0)

    • controller: API endpoint'lerini tanımlayan sınıfları içerir.
    • model: Customer, Loan, ve LoanInstallment gibi veri modellerini içerir.
    • repository: Veritabanı işlemleri için kullanılan repository arayüzlerini içerir.
    • service: İş mantığı katmanındaki işlemler için kullanılan sınıfları içerir.
    • resources: Uygulamanın yapılandırma dosyalarını (application.properties), veritabanı başlangıç dosyalarını (data.sql ve schema.sql), ve statik/şablon dosyalarını içerir. 


## Projenin Kurulumu 
1. Projeyi "git clone https://github.com/earda/Loan-Credit-Api.git" ile klonlayın.
2. Veritabanı bağlantılarını oluşturun; Projemizdeki #resources# klasörünün altına data.sql ve scheme.sql adında iki sql dosyası oluşturuyoruz, içlerini dolduracağımız kodlar aşağıda DATABASE kısmındadır.
3. Projeyi çalıştırın.
4. Endpointleri Postman üzerinden test edin



## H2 DATABASE 

##### scheme.sql
```sql
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
```
##### Bu scheme.sql dosyası bir H2 veritabanıdır ve in-memory çalışır. Bu sayede veriler RAM'de tutulur uygulama kapatıldığında hepsi bellekten silinir.

##### Şemayı oluşturduktan sonra içine veri ekleyelim.

##### data.sql dosyamız;

```sql
-- Customer tablosuna veri ekleme
INSERT INTO PUBLIC.customer (id,name, surname, credit_limit, used_credit_limit)
VALUES (1,'John', 'Doe', 10000.00, 5000.00);
INSERT INTO PUBLIC.customer (id,name, surname, credit_limit, used_credit_limit)
VALUES (2,'Dohn', 'Joe', 10000.00, 5000.00);
INSERT INTO PUBLIC.customer (id,name, surname, credit_limit, used_credit_limit)
VALUES (3,'Ron', 'Yue', 10000.00, 5000.00);

-- Loan tablosuna veri ekleme
INSERT INTO PUBLIC.Loan (customer_id, loan_amount, interest_rate, number_of_installments, create_date, is_paid)
VALUES (1, 5000.00, 5.0, 12,  DATE '2024-11-12', FALSE);
INSERT INTO PUBLIC.Loan (customer_id, loan_amount, interest_rate, number_of_installments, create_date, is_paid)
VALUES (2, 5000.00, 3.0, 9,  DATE '2024-11-10', FALSE);
INSERT INTO PUBLIC.Loan (customer_id, loan_amount, interest_rate, number_of_installments, create_date, is_paid)
VALUES (3, 5000.00, 2.0, 6,  DATE '2024-11-02', FALSE);

-- Loan Installment tablosuna veri ekleme
INSERT INTO PUBLIC.loan_installment (loan_id, amount, paid_amount, due_date, payment_date, is_paid)
VALUES (1, 500.00, 0.00, NULL,DATE '2024-11-12', FALSE);
INSERT INTO PUBLIC.loan_installment (loan_id, amount, paid_amount, due_date, payment_date, is_paid)
VALUES (2, 500.00, 0.00, NULL,DATE '2024-11-10', FALSE);
INSERT INTO PUBLIC.loan_installment (loan_id, amount, paid_amount, due_date, payment_date, is_paid)
VALUES (3, 500.00, 0.00, NULL,DATE '2024-11-05', FALSE);
```
##### localhost:8080/h2-console/login.jsp url ile veritabanımıza bir arayüz ile erişim sağlayabiliriz.

![image](https://github.com/user-attachments/assets/b005b1eb-f856-466a-83cc-c57e8f2db70a)


