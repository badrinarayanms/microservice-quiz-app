# Microservices-based Quiz Platform

A scalable and distributed **Quiz Management System** built using **Spring Boot Microservices**. This platform separates core functionalities into independent services to ensure modularity, scalability, and maintainability.

---

## 🍷 Project Structure

```
microservice-quiz-app/
├── eureka-server/        # Service Registry
├── gateway-service/      # API Gateway for routing requests
├── question-service/     # Handles all question CRUD operations
├── quiz-service/         # Quiz management & aggregation
├── README.md
└── .gitignore
```

---

## 🛠️ Services and Endpoints

### 1. Question Service

**Base URL:** `/question`

| Method | Endpoint                    | Description                                                          |
| ------ | --------------------------- | -------------------------------------------------------------------- |
| GET    | `/allquestions`             | Fetch all questions                                                  |
| GET    | `/category/{category}`      | Fetch questions by category                                          |
| POST   | `/add`                      | Add a new question                                                   |
| GET    | `/generate?category=&numQ=` | Generate quiz question IDs based on category and number of questions |
| POST   | `/getquestions`             | Get question details from a list of IDs                              |
| POST   | `/score`                    | Calculate score based on user responses                              |

---

### 2. Quiz Service

**Base URL:** `/quiz`

| Method | Endpoint       | Description                                     |
| ------ | -------------- | ----------------------------------------------- |
| POST   | `/create`      | Create a new quiz (`title`, `category`, `numQ`) |
| GET    | `/get/{id}`    | Fetch quiz details by quiz ID                   |
| POST   | `/submit/{id}` | Submit responses for a quiz and calculate score |

> Note: Quiz Service communicates with Question Service to fetch question data. Each service maintains its **own database**.

---

### 3. Service Registry (Eureka Server)

- **Port:** 8761 (default)
- Enables **service discovery** for all microservices.
- All services register themselves to Eureka for dynamic routing and scaling.

---

## 🧬 Models

### Question

- `id`, `questiontitle`, `option1-4`, `rightanswer`, `difficultylevel`, `category`

### Quiz

- `id`, `title`, `questionIds` (list of question IDs)

### QuizWrapper

- `id`, `questiontitle`, `option1-4`

### Response

- `id`, `response` (user's answer)

---

## 💻 Tech Stack

- **Backend:** Spring Boot, Spring Cloud, Eureka, REST APIs
- **Database:** MySQL (separate DB for Quiz & Question services)
- **API Gateway:** Spring Cloud Gateway
- **Frontend (optional):** React.js

---

## 🔧 Setup Instructions

1. **Clone Repository**

```bash
git clone https://github.com/yourusername/microservice-quiz-app.git
cd microservice-quiz-app
```

2. **Create ****`.env`**** Files**\
   Inside each service folder (`question-service`, `quiz-service`, etc.), create `.env` with DB credentials:

```bash
DB_URL=jdbc:mysql://localhost:3306/dbname
DB_USERNAME=root
DB_PASSWORD=password
```

3. **Run Services**

- Start `eureka-server` first.
- Start `question-service` and `quiz-service`.
- If using `gateway-service`, run it after the services register with Eureka.

4. **Access Services**

- Question Service: `http://localhost:[port number]/question/allquestions`
- Quiz Service: `http://localhost:[port number]/quiz/create`
- Eureka Dashboard: `http://localhost:8761`

---

## ⚡ Key Highlights

- Modular **microservices architecture** for scalability and maintainability.
- Secure configuration with `.env` files (never push real credentials).
- Inter-service communication via **REST APIs**.
- Service discovery and load balancing with **Eureka Server**.
- Ready for **Dockerization / cloud deployment**.

---

## 📌 Notes

- Use `.env` to configure your local DB.
- Ensure **different ports** for each service to avoid conflicts.
- All CRUD operations and quiz functionality can be tested via **Postman** or any REST client.

