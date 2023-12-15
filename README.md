### Implementation description

Project consists of 3 modules: 
- common - contains common classes for calculations
- console - contains console application
- web - contains web application

NOTE: You will need Java 17 (or newer) set up and in use for this to work.

### Console application
To run console application, run from the root folder of the project:
```bash
./mvnw clean install
java -jar console/target/mortgage-plan-jar-with-dependencies.jar  prospects.txt
```

### Web application
To run web application, run from the root folder of the project:
```bash
cd web
export DB_USERNAME=your_username
export DB_PASSWORD=your_password

../mvnw spring-boot:run
```
* If this fails, ensure it is actually executable and retry: `chmod +x ./mvnw`.


### REST API endpoints

#### Mortgage plans
- **GET /mortgage/plan** : Returns list of plans which were added before (of course, it is not right behavior to show list of all customers to any user, but it is because it is just rest api for test task without any authorization)
- **GET /mortgage/plan/{id}** : Returns plan with id={id}
- **POST /mortgage/plan** : Add plan with requested params and calculated monthly payment.
Has request body in json format:
```json
{
  "customer" : "Julia",
  "total_loan" : 100000,
  "interest" : 3.5,
  "years" : 2
}
```