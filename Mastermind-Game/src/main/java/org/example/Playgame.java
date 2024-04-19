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
        System.out.println("********************************************");

        System.out.println("Welcome to the Code Guessing Game!\uD83D\uDD22");

        System.out.println("********************************************");
        System.out.println("Try to guess the secret code consisting of 4 numbers from 0 to 7.");
        System.out.println("You have 10 attempts to guess the code. Good luck!");
        System.out.println("Type '1' to make a guess or '2' to exit the game.");
        System.out.println();


        int choice = scanner.nextInt();
       // String userInput = scanner.nextLine().trim();
        if (choice == 2) {
            System.out.println("Thank you for playing! Goodbye!");
            return;
        }

        while (logic.hasAttemptsLeft()) {
            System.out.println("\nAttempts remaining: " + logic.getAttemptsLeft());
           // System.out.print("Enter your choice: ");


           // int choice = 0;
//            System.out.print("Enter your choice: ");

//            try {
//                choice = Integer.parseInt(userInput);
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid choice. Please enter a valid option.");
//                continue;
//            }


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
                    System.out.println("Thank you for playing! Goodbye. ");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

        scanner.close();
    }


}
