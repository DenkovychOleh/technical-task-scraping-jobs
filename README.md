# DataOx Test Task: Job Scraping API

## Description:

This API, developed using Spring Boot 3.2.1 and Java 17, leverages Maven 4.0.0 to collect information about job vacancies and tags from https://jobs.techstars.com/jobs. Following SOLID principles ensures that the project's functionality can be easily extended.

**Technical Requirements:**

- Spring Boot 3.2.1
-  Java 17
-  Maven 4.0.0
-  PostgreSQL database for storing retrieved information
-  Liquibase for database migration

## Endpoints for Interaction with Techstars
1. Retrieve information about current tags and their usage count.
```http request
GET /api/v1/techstarts/industry-tags
```
2. Accepts job_function as a request param, where job_function is the ID from our database. The API checks for vacancies not yet saved in the database and stores them for quick access.
```http request
GET /api/v1/techstarts/jobs
```

## Endpoints for Interaction with Our Database
1. Returns a list of names and IDs for all job functions for use in subsequent requests.
```http request
GET /api/v1/scraping-jobs/job-functions
```
2. Can accept 1 or multiple jobFunctionsId to view vacancies based on their job_function. Sorting is available using orderBy (CREATED) and sortBy (ASC or DESC). If no parameters are provided, return the entire list of vacancies.
```http request
GET /api/v1/scraping-jobs/jobs
```

## Installation and Running
1. Clone the repository:
```bash
   git clone https://github.com/your-username/scraping-jobs-app.git
   cd scraping-jobs-app
```
2. For a seamless setup and launch, Dockerfile and docker-compose.yaml have been provided. Execute the following command after cloning the project from the GitHub repository:
```bash
docker-compose up --build
```
The application will be accessible at http://localhost:8081.

3. To stop the application, use the following command:
```bash
docker-compose down
```


## Swagger Documentation
**Explore the API effortlessly through Swagger, accessible at**
```http request
http://localhost:8081/swagger/swagger-ui/index.html
```

## Additional Files in the Project Root
**In the project's root directory, the following files have been added with preserved information:**
 
- **jobs.csv**
- **job_functions.csv**
  
These files contain additional data related to job vacancies and job functions.
