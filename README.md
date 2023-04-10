# NBA 3-Pointers Made (3PM) Average Calculator

This Java program calculates the average number of 3-pointers made (3PM) by each player in the last 5 games for the Dallas Mavericks basketball team.

## Dependencies

* Selenium WebDriver
* WebDriverManager
* JSON-java
* TestNG

## How to run the code

1. Ensure you have Java installed and configured on your machine.
2. Import the project into your favorite Java IDE (e.g., IntelliJ IDEA or Eclipse).
3. Add the required dependencies to the project using Maven or Gradle.
4. Run the test by right-clicking on the NBA_3PM class and selecting Run 'NBA_3PM'.

## Code Overview

The NBA_3PM class contains the following main methods and variables:

* playerURL: A list of URLs for each player on the team.
* playerURLIndex: The current index in the playerURL list.
* DATA_API: The API endpoint for retrieving player data.

The main methods are:

* NBA_3PM(): The constructor that initializes the playerURL list.
* getPlayersURL(): Retrieves the URLs of all players on the team.
* testPlayer3PM(): The TestNG test method that calculates the average 3PM for each player in the last 5 games.

When the test is run, it iterates through the list of player URLs and calculates the average 3PM for each player. The results are displayed in the console.
