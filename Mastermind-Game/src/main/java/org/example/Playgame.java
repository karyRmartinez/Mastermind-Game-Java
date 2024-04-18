package org.example;

import java.util.List;
import java.util.Scanner;


public class Playgame {
    private final Gamelogic logic;
    private final Scanner scanner;

    public Playgame() {
     logic = new Gamelogic();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Mastermind!");

        while (logic.hasAttemptsLeft()) {
            System.out.println("\nAttempts remaining: " + logic.getAttemptsLeft());

            System.out.println("Please choose an option:");
            System.out.println("1. Make a guess");
            System.out.println("2. Quit game");
            System.out.print("Enter your choice (1, 2): ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a valid option.");

            }


            switch (choice) {
                case 1:
                    // Make a guess
                    System.out.print("Enter your guess (4 different numbers from 0-7, separated by spaces): ");
                    List<Integer> guess = Gamelogic.GameUtils.readGuess(scanner);
                    if (guess == null) {
                        System.out.println("Invalid input. Please enter 4 different numbers 0-7");
                        continue;
                    }
                    Gamefeedback feedback = logic.checkGuess(guess);
                    logic.recordGuess(guess, feedback);
                    System.out.println("Feedback: " + feedback);

                    if (feedback.correctWinningGuess()) {
                        System.out.println("Congratulations! You guessed the secret code!");
                        return;
                    }

                    if (!logic.hasAttemptsLeft()) {
                        System.out.println("\nYou've used all your attempts! The secret code was: " + logic.getSecretCode());
                        return;
                    }
                    break;


                case 2:
                    // Quit the game
                    System.out.println("Thank you for playing! Goodbye.");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
