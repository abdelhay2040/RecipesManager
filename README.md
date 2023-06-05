# Recipe Manager

Recipe Manager is a Java application used to manage recipes and ingredients. It consists of two main services: `IngredientService` and `RecipeService`.

## IngredientService

`IngredientService` is used to manage ingredients in our application. Here are its primary responsibilities:

- **Create Ingredient**: Adds a new ingredient to the database.
- **Get Ingredient By ID**: Retrieves an ingredient from the database based on its ID.
- **Update Ingredient**: Updates details of an existing ingredient in the database.
- **Delete Ingredient**: Deletes an ingredient from the database based on its ID.

## RecipeService

`RecipeService` is used to manage recipes in our application. Here are its main functionalities:

- **Create Recipe**: Adds a new recipe to the database.
- **Get All Recipes**: Retrieves all recipes from the database.
- **Get Recipe By ID**: Retrieves a specific recipe from the database based on its ID.
- **Update Recipe**: Updates details of an existing recipe in the database.
- **Delete Recipe**: Deletes a recipe from the database based on its ID.
- **Filter All Recipes**: Filters recipes based on various parameters such as vegetarian status, number of servings, specific ingredient, and instruction text.

Both services use Spring Boot for dependency injection, and Hibernate to interact with the database.

For unit testing, we use JUnit 4. All service methods are covered with tests to ensure correct behavior.

## Dependencies

- Spring Boot
- Hibernate
- JUnit 4

## Getting Started

You need to have Java and Maven installed on your machine to run the application. After cloning the repository, navigate to the project directory and use the command `mvn spring-boot:run` to start the application.

you can use http://localhost:8080/swagger-ui/index.html to call the endpoints 

## Future Work

In the upcoming versions of Recipe Manager, we are planning to improve and expand the functionalities as follows:

- **Integration Testing**: Along with unit tests, I will also be incorporating integration tests using frameworks like Spring Boot Test or Testcontainers. This will allow us to verify the interaction of our services with each other and with the database.

- **Increase Test Coverage**: I aim to increase our test coverage to ensure all paths in the code are tested. This will help us catch and resolve potential issues earlier in the development process. This might involve using tools like JaCoCo to assess and improve our test coverage.

