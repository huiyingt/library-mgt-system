use library_mgt_sys;
show databases;
drop table Roles;
drop table Books;
drop table Loans;
drop table BookCopies;
drop table Users;
create table Roles (
role_id int PRIMARY KEY AUTO_INCREMENT,
role_type ENUM('SystemAdmin', 'Librarian', 'Member') NOT NULL
);

create table Users (
user_id int PRIMARY KEY AUTO_INCREMENT,
role_id int NOT NULL,
user_name varchar(255) NOT NULL,
password_hash varchar(255) NOT NULL,
email varchar(255) NOT NULL UNIQUE,
contact_no varchar(50) NOT NULL,
address varchar(255),
membership_start_date DATE NOT NULL,
membership_end_date DATE NOT NULL,
FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

insert into Roles VALUES
(1, 'SystemAdmin'),
(2, 'Librarian'),
(3, 'Member')

insert into Users VALUES
(0001, 02, 'alice', SHA2('alicepw', 256), 'alice@example.com', '12345678', '1 Springville Rd, Singapore 111111', '2025-08-21', '2026-08-20'),
(0002, 03, 'benjamin', SHA2('benpw', 256), 'benjamin@example.com', '23456789', '2 Springville Rd, Singapore 111112', '2025-08-20', '2026-08-19')

select * from Roles
select * from Users
select * from Books

create table Books (
book_id int PRIMARY KEY AUTO_INCREMENT,
isbn varchar(50) NOT NULL UNIQUE,
book_title varchar(255) NOT NULL,
author varchar(255) NOT NULL,
category ENUM('FICTION', 'NON_FICTION') NOT NULL,
publication_year YEAR NOT NULL
);

insert into Books VALUES
(00001, '978-1-4721-3995-5', 'Mindset', 'Carol S. Dweck', 'NON_FICTION', 2017),
(00002, '978-0-241-95822-3', 'Start with Why', 'Simon Sinek', 'NON_FICTION', 2019),
(00003, '978-93-90213-10-8', 'Five Weeks in a Balloon', 'Jules Verne', 'FICTION', 2023)

create table Loans (
loan_id int PRIMARY KEY AUTO_INCREMENT,
user_id int NOT NULL,
borrow_date DATE NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

create table BookCopies (
copy_id int PRIMARY KEY AUTO_INCREMENT,
book_id int NOT NULL,
copy_number int NOT NULL,
book_status ENUM('AVAILABLE', 'BORROWED', 'RESERVED', 'OVERDUE') NOT NULL,
FOREIGN KEY (book_id) REFERENCES Books(book_id)
);
select * from BookCopies;
insert into BookCopies VALUES
(01, 00001, 1, 'AVAILABLE'),
(02, 00001, 2, 'AVAILABLE'),
(03, 00001, 3, 'BORROWED'),
(04, 00002, 1, 'AVAILABLE'),
(05, 00002, 2, 'RESERVED'),
(06, 00002, 3, 'OVERDUE'),
(07, 00002, 4, 'AVAILABLE');

create table LoanItems (
loanitem_id int PRIMARY KEY AUTO_INCREMENT,
loan_id int NOT NULL,
copy_id int NOT NULL,
due_date DATE NOT NULL,
return_date DATE,
renewal_count ENUM('0','1','2') NOT NULL,
fine_amount DECIMAL(2,2),
FOREIGN KEY (loan_id) REFERENCES Loans(loan_id),
FOREIGN KEY (copy_id) REFERENCES BookCopies(copy_id)
);

