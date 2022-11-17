# REST API (service)
Sample of REST service in Java.

Technologies:
- Spring Boot
- JPA, Spring Data, Pagination
- Flyway DB migrations
- Spring Security (API key auth)
- Custom Exception handler
- DTO, Validation

## Setup
Clone repository
```cmd
git clone https://github.com/dawidgorecki/rest-service-java.git
```

Run MySQL database
```cmd
docker run -p 3307:3306 --name rest_service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=rest_service -d mysql
```

Go inside folder and run application
```cmd
cd rest-service-java

# on linux with maven wrapper
./mvnw clean install spring-boot:run
```

## Authorization
API key passed in HTTP header.
```properties
Key: AUTH_API_KEY
Value: secret_key
```

## API methods

### Create task  
```
POST http://localhost:8080/api/v1/tasks
```
```json
{
    "name": "My task",
    "description": "Task description",
    "deadline": "2022-12-12"
}
```
### Get all tasks  
```
GET http://localhost:8080/api/v1/tasks
```

### Get all with paging and sorting  
```
GET http://localhost:8080/api/v1/tasks?page=0&size=2&sort=id,desc
```

### Get one task with specified ID  
```
GET http://localhost:8080/api/v1/tasks/5
```

### Update task  
```
PUT http://localhost:8080/api/v1/tasks/1
```
```json
{
    "name": "My updated task",
    "description": "New description",
    "deadline": "2022-12-15"
}
```

### Delete task  
```
DELETE http://localhost:8080/api/v1/tasks/1
```

### Mark as done  
```
PATCH http://localhost:8080/api/v1/tasks/1
```
