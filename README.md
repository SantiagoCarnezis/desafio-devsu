# DevSu Challenge

In this challenge, two microservices were developed according to the requested specifications. Below are the implemented features and some additional details:

## Basic Features

- **CRUD for Microservices:** Controllers were created to perform basic Create, Read, Update, and Delete (CRUD) operations in both microservices.  
Some operations, such as modifying and deleting historical transactions, were not included as they did not align with the business model. I inquired about this but did not receive a response.

## Additional Features

- **Close Account:** Implemented to change the status of an account to "closed." A closed account cannot be used to make or receive payments. Since the requirements specified that asynchronous communication was required, I decided to implement this functionality using Kafka. However, in a real-world scenario, synchronous communication might be a better choice.

- **Make Payment:** Allows transferring money from one account to another, ensuring that both accounts exist, are open, and have sufficient funds. It creates transactions in both accounts within a single transaction.

- **Get Account Data:** Sample endpoint demonstrating how to implement synchronous communication between microservices using `FeignClient`. Supports various exception scenarios.

## Testing

- **Unit Tests:** Used to test different use cases when processing a payment.  
- **Integration Tests:** Used to validate various reporting use cases.  
- **Karate Tests:** Used to test the application in a running Docker environment. It's important to note that Docker must be running to execute these tests.

## Dockerization

The application is containerized using Docker Compose, which includes:

- **Microservices:** `Movements` and `Users`.  
- **Databases:** One for each service.  
- **Zookeeper and Kafka:** For message management and asynchronous communication.  
- **Eureka:** Service registry for managing services.  
- **API Gateway:** Centralizes requests, although, for testing purposes, direct access to services is also allowed.

## Attachments

- **Postman Collection:** Includes request examples for testing the endpoints, along with two environments to test via the API Gateway or by directly querying the service. [Postman Collection](https://drive.google.com/drive/folders/19O9A0YNCQCmbz6uiX9HZO5hw1xyk0N-m?usp=sharing)  
- **SQL File:** Included in the Docker Compose configuration to facilitate database initialization.

## Deployment

To start the application using Docker, ensure that none of the ports used by Docker to expose the services are occupied (they can be found in the `dev-su.env` file).  
Once the ports are confirmed to be available, navigate to the directory containing the `docker-compose.yml` file and run the following command:

```bash
docker-compose up -d
```
or  
```bash
docker-compose up
```

For any questions or additional clarifications, feel free to contact me at my email: **scarnezis@gmail.com**


