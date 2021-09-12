# The Retail Store Discounts

## Description

On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the basket, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a basket.

Write a Spring Boot Java application with test cases, which exposes an API such that given a basket, it finds the 
net payable amount. Please note the stress is on object oriented approach and test coverage. User interface is 
not required, only an API exposed. What we care about:

• object oriented programming approach, provide a high level UML class diagram of all the key classes in your solution. 
  This can either be on paper or using a CASE tool
• unit test and code coverage
• code to be generic and simple
• leverage today's best coding practices
• clear README.md that explains how the code and the test can be run and how the coverage reports can be generated
• state what you will use to document your API, with an example sample request and response

Create a GitHub repository, ensure the name is generic and doesn’t have any company
names. Commit your code to the GitHub repository and share the link with us.


## Installing

After checking out the git repo run the following maven command

```bash
mvn clean install
```

This should install all packages, run all unit tests

## Starting

To start the server please run the following maven command

```bash
mvn spring-boot:run
```

## Testing

To execute the unit tests against the business logic service classes please run the following maven command

```bash
mvn test
```

## Code coverage

I have used Jacoco for code coverage. Jacoco code coverage report upon running "mvn clean install" command.
To access the report go to "target\site\jacoco" folder and open index.html file in your preferred browser to see the 
results.

## Using

### API Endpoint

* Http Method - **POST**
* Endpoint - **localhost:8080/api/v1/discounts**

Example request

```json
{
    "user": {
        "type": "EMPLOYEE",
        "registerDate": "2021-06-11"
    },
    "basket": {
        "items": [
            {
                "type": "GROCERY",
                "price": 30.0
            },
            {
                "type": "TECHNOLOGY",
                "price": 60.00
            },
            {
                "type": "GROCERY",
                "price": 50.30
            }
        ]
    }
}

```

The response is net payable amount.

```json
117.30
```

## API Documentation

I have used Swagger to document my API 

* Swagger url - **http://localhost:8080/swagger-ui.html**

