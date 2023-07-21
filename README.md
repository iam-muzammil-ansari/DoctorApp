# DoctorApp

## Table Of Content
- [Frameworks and Language Used](#frameworks-and-language-used)
- [Data Flow](#data-flow)
  - [Controller](#controller)
  - [Services](#services)
  - [Repository](#repository)
  - [BeanFactory](#beanfactory)
  - [Database Design](#database-design)
- [Data Structure Used](#data-structure-used)
- [Project Summary](#project-summary)
- [Getting Started](#getting-started)
- [Testing Endpoints](#testing-endpoints)
- [License](#license)

## Frameworks and Language Used
- Spring Boot
- Java
- Hibernate
- MySQL
- Swagger

## Data Flow

### Controller
- `AdminController`: Handles the admin-related endpoints, such as signing up, signing in, and signing out.
- `AppointmentController`: Manages the endpoints related to appointments, including getting all appointments.
- `DoctorController`: Deals with doctor-related endpoints, like adding doctors and getting all doctors.
- `PatientController`: Manages patient-related endpoints, such as signing up, signing in, signing out, getting all patients, scheduling appointments, and canceling appointments.

### Services
- `AdminService`: Provides services related to admin functionalities, such as sign up, sign in, and sign out.
- `AppointmentService`: Handles services related to appointment functionalities, including getting all appointments, saving appointments, and canceling appointments.
- `DoctorService`: Manages doctor-related services, such as adding doctors and getting all doctors.
- `PatientService`: Provides services related to patient functionalities, such as sign up, sign in, sign out, getting all patients, scheduling appointments, and canceling appointments.

### Repository
- `IAdminRepo`: Interface for Admin entity repository.
- `IAppointmentRepo`: Interface for Appointment entity repository.
- `IAuthTokenRepo`: Interface for AuthenticationToken entity repository.
- `IDoctorRepo`: Interface for Doctor entity repository.
- `IPatientRepo`: Interface for Patient entity repository.

### BeanFactory
- Utility classes for email handling and password encryption.

### Database Design
- Admin: Represents the admin of the system.
- Appointment: Represents an appointment between a patient and a doctor.
- AuthenticationToken: Represents the authentication token for users.
- Doctor: Represents a doctor in the system.
- Patient: Represents a patient in the system.

## Data Structure Used
- Enums:
  - Gender: Represents the gender of a patient.
  - Qualification: Represents the qualification of a doctor.
  - Specialization: Represents the specialization of a doctor.

## Project Summary
The DoctorApp is a web application designed to manage appointments between doctors and patients. It allows admins, doctors, and patients to sign up, sign in, and manage appointments. The application uses Spring Boot as the framework and Java as the primary programming language.

## Getting Started
To run the DoctorApp, follow these steps:
1. Clone the repository to your local machine from [DoctorApp](https://github.com/ayaan097/DoctorApp.git). 
2. Configure the MySQL database connection in the application.properties file.
3. Run the application using your preferred IDE or by executing the main method in the DoctorAppApplication class.

## Testing Endpoints
The application provides RESTful API endpoints that can be tested using Swagger UI.
- Open your web browser and go to: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Use the Swagger UI interface to explore and test the available endpoints.

- Admin Endpoints:
  - Sign up Admin:
    - Endpoint: POST /admin/signup
  - Sign in Admin:
    - Endpoint: POST /admin/signIn
  - Sign out Admin:
    - Endpoint: DELETE /admin/signOut

- Appointment Endpoints:
  - Get All Appointments:
    - Endpoint: GET /appointments

- Doctor Endpoints:
  - Add Doctor:
    - Endpoint: POST /addDoctor
  - Get All Doctors:
    - Endpoint: GET /doctors

- Patient Endpoints:
  - Sign up Patient:
    - Endpoint: POST /patient/signup
  - Sign in Patient:
    - Endpoint: POST /patient/signIn
  - Sign out Patient:
    - Endpoint: DELETE /patient/signOut
  - Get All Patients:
    - Endpoint: GET /patients
  - Schedule Appointment:
    - Endpoint: POST /appointment/schedule
  - Cancel Appointment:
    - Endpoint: DELETE /appointment/cancel

## License
This project is OpenSource
