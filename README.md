# 🃏 Pexeso Web Game

A web-based memory card-matching game built with Java and Spring Boot.

## 📌 Description

This is a university project simulating the classic memory game "Pexeso" with features like user login, score tracking, rating system, and comment section.

It uses Spring Boot for backend logic, PostgreSQL for data storage, Thymeleaf and Bootstrap for frontend rendering, and HTMX for partial updates without page reload.

> ⚠️ Note: The app is currently configured to work with my local PostgreSQL database. To run it yourself, you'd need to set up your own database and update the config files.

## ⚙️ Technologies

- Java 17
- Spring Boot
- PostgreSQL
- Thymeleaf
- Bootstrap
- HTMX
- JPA (Hibernate)

## 🚧 Project Status

🛠️ In progress — the backend and game logic are functional, but deployment and external database setup are not yet ready.

## 🔐 Configuration

Create a file called `application.properties` in `src/main/resources/` with your DB credentials:
```properties
screenshots 
spring.datasource.url=jdbc:postgresql://localhost:5432/pexeso
spring.datasource.username=your_username
spring.datasource.password=your_password
![photo_2025-06-27_12-34-50](https://github.com/user-attachments/assets/017484af-25f9-4416-8a35-ce22008d6721)
![photo_2025-06-27_12-34-57](https://github.com/user-attachments/assets/0755e732-5852-42bc-a560-e5c87cfd33b7)
![photo_2025-06-27_12-35-15](https://github.com/user-attachments/assets/ee5eaec3-4f45-48fc-a35c-ff048e22fdae)
![photo_2025-06-27_12-35-26](https://github.com/user-attachments/assets/e7a0c357-3399-4f66-9d66-5b49a30bb452)
![photo_2025-06-27_12-35-35](https://github.com/user-attachments/assets/45f15579-7d17-4660-8932-8eb1cdd74892)

