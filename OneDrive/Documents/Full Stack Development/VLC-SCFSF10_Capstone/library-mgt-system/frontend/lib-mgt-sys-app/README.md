Business Requirements 

Project Goals:
The goal of this project is to create a new Library Management System (LMS), which is a web application designed to streamline library operations for staff and enhance user experience. 

Target audience:
Librarians: To efficiently manage books, members, and borrowing/returning processes.
Members: To easily search for books and view their current loans as well as any outstanding fines.

Objectives: 
Develop a fully functional web application using Spring Boot, React and MySQL.
Enable librarians to efficiently and easily manage books and members.
Provide a user-friendly interface for members to search for books and view their current loans and outstanding fines.
Create a robust and scalable system that can handle a moderate number of books and members. In this project, a moderate number is defined as up to 15,000 books and 1,000 members.

Functional Requirements


There will be 3 user roles: appointed System Administrator for the setup and maintenance of the full stack web application, Staff Admin (Librarian) and Member.
 
Features vs role access: Refer to Table 1 in Capstone Documentation that shows the functions or features that can be performed by the respective user roles.


Business rules:

Membership rules:
Members can borrow a maximum of 3 books at any time.
Members cannot borrow if active loan items = 3 books
Membership is valid for 1 year from registration
Start of membership = Date of registration
End of membership = 1 year from date of registration
Members must have valid membership to borrow books

Lending rules:
Loan duration: 14 days
Maximum of 2 renewals per book
Member cannot borrow if having overdue books or accumulated fines exceeding $10

Fine calculation:
$0.50 per day for overdue books
Fine starts accumulating from day after due date
Maximum fine per book: $20

Software Requirements Specification

Technology stack used:
Backend: Spring Boot (Java)
Database: MySQL
Frontend: React
Version Control: Git

Backend
MySQL database
Spring Boot framework
RESTful API architecture
Spring Security for authentication
JPA/Hibernate for database operations

Frontend (React)
React router for navigation
React hooks for state management
Bootstrap or Material-UI for styling
Axios for API communication

Database requirements
Proper relationship mapping
Referential integrity
Appropriate indexing
Data validation rules

Security requirements
Password encryption
Role-based access control
JWT token-based authentication
Input validation and sanitization

System Architecture
Architecture type: Model-View-Controller (MVC) 

MVC (Model–View–Controller) helped in system implementation by providing a clear separation of concerns, which made the development, testing, and maintenance of the system more structured, modular and efficient.
Model handled the data and business logic (e.g., database queries, loan rules in a library system)
View managed how information was presented to users (e.g., React components)
Controller acted as the bridge, processing user inputs, updating models, and returning the correct views

Benefits:
Flexibility and scalability
UI (View) could be redesigned without touching business logic
Business rules in the Model could be updated without changing the UI
Controller ensured smooth communication, so scaling or adding new features became easier
Code reusability
Improved debugging and testing


Deliverable Acceptance Criteria

Acceptance criteria
All core features implemented and functional
Code follows provided coding standards (refer to coding document)
Basic test coverage
Proper error handling
Completed documentation
No critical bugs
