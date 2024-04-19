# Mastermind Game 🎮

Welcome to the Mastermind Game project! This project is a command-line implementation of the classic Mastermind code-breaking game. 


## Build and Runtime Requirements
+ JDK 17 or later
+ IntelliJ IDEA 
  
## Setup
1. Get a free API at https://www.random.org/clients/http/api/
2. Clone the repo
   ```sh
   git clone https://github.com/your_username_/Project-Name.git
   ```
## Features

- Generate a random secret code consisting of 4 distinct numbers from 0 to 7.
- Players can make guesses and receive feedback on their guesses.
- Feedback includes the number of correct numbers and correct locations.
- The game tracks the player's guess history and provides remaining attempts.
- User can choose to use timer for guess attempts.
  
  ``` sh
   while (logic.hasAttemptsLeft()) {
            System.out.println("\nAttempts remaining: " + logic.getAttemptsLeft());
            System.out.print("Enter your guess (4 different numbers from 0-7, separated by spaces): ");
            List<Integer> guess = Gamelogic.GameUtils.readGuess(scanner);

            if (guess == null) {
                System.out.println("Invalid input. Please enter 4 different numbers from 0-7.");
                continue;
            }

            Gamefeedback feedback = logic.checkGuess(guess);
            logic.recordGuess(guess, feedback);
            System.out.println("Feedback: " + feedback);
 ## Game flow chart: 
   ```
  graph TD;
    Start([Start]) --> |User input: '1'| MakeGuess;
    Start --> |User input: '2'| QuitGame;
    
    MakeGuess --> |User input: guess input| CheckGuess;
    MakeGuess --> |User input: '1'| InitializeTimer;
    MakeGuess --> |User input: '2'| CheckTimer;
    MakeGuess --> |User input: '3'| QuitGame;
    
    InitializeTimer --> PlayWithTimer;
    CheckGuess --> |feedback.correctWinningGuess()| WinGame;
    CheckGuess --> |!logic.hasAttemptsLeft()| LoseGame;
    CheckTimer --> |Timer Choice| TimerStatus;

    PlayWithTimer --> MakeGuess;

    WinGame([Win Game]) --> End([End]);
    LoseGame([Lose Game]) --> End;
    TimerStatus([Check Timer Status]) --> MakeGuess;

    QuitGame([Quit Game]) --> End;
```
## Game thought process
### Initialization:
- The game should start with a welcome message and instructions on how to play.
- The game waits for user input to either start guessing or exit the game.
### Secret Code Generation:
- NumbersAPI class generates a secret code using an API call.
- Consider removing reliance on an external API for secret code generation to keep the game self-contained and avoid potential external network issues. Instead, generate a random code locally.
### Handling User Input:
- Playgame handles user input choices to make a guess or exit.
- Ensure to handle invalid input gracefully by prompting the user to try again.
### Testing and Iteration:
- Test the game thoroughly to ensure it functions as intended and provides a smooth player experience.
- Gather feedback from players to identify areas for improvement and iterate on your design.

## Screenshots
<img src="Commandline Screenshoot.png" alt="Image Alt Text" style="width:400px; height:250px;">

## Resources
- https://www.random.org/clients/http/api/
- Java Utility Timer - Youtube
- YU Rest workbook
- Diagram flow chart : https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/creating-diagrams

 ## Next Steps 
 ### Under construction 👷🏾‍♀️🚧 
 - Add difficulty level
 - Extend multi-player
 - Add hints
