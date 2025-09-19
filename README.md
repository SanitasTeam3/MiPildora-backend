# MiPildora - Recordatorio de Medicación

Functional web application designed to facilitate medication management by users.

## Project Structure

```text
src
└── com.hackathon.mipildora
    ├── config                    # Configuration
    │   └── CorsConfig            # CORS configuration
    ├── controllers               # REST controllers
    │   └── MedicationController  
    ├── dtos                      # DTOs for category operations
    │   ├── MedicationMapper      
    │   ├── MedicationRequest    
    │   ├── MedicationTakenRequest 
    │   └── MedicationResponse   
    ├── models                    
    │   └── Medication            # JPA Entity
    ├── repositories              
    │   └── MedicationRepository  # JPA repository
    ├── services                  # Business logic
    │   ├── MedicationService     
    │   └── MedicationServiceImpl 
    └── MipildoraApplication      # Spring Boot application entry point
```

## Technologies Used

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![draw.io](https://img.shields.io/badge/draw.io-F08705?style=for-the-badge&logo=diagramsdotnet&logoColor=white)

## Clone the Repository

```bash
git clone https://github.com/SanitasTeam3/MiPildora-backend.git
cd MiPildora-backend
```
### Run

```bash
./mvnw spring-boot:run
```
or
```bash
mvn spring-boot:run
```
Alternative Way to Run the Application
If you are using an IDE such as IntelliJ IDEA,VS Code etc, you can simply click the “Run” button or run the main application class directly (the one annotated with @SpringBootApplication).
For example, in IntelliJ IDEA, right-click the main class and choose "Run 'MipildoraApplication...main()'".

## API Endpoints

### Medication

- `GET /medications` — Get all medications
- `POST /medications` — Add new medication
- `PUT /medications` — Update medication
- `PUT /medications/{id}/taken` — Set medication as taken by id
- `DELETE /medications/{id}` — Delete medication
