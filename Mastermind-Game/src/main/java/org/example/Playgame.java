package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Playgame {
    private final Gamelogic logic;
    private final Scanner scanner;


    private Timer timer;
    private TimerTask timerTask;
    private final int timeLimitInSeconds = 300;
    private int remainTime;
    private boolean isTimerEnabled = false;

    public Playgame() {
        logic = new Gamelogic();
        scanner = new Scanner(System.in);
    }
    public void startGame() {
        System.out.println("********************************************");
        System.out.println("Welcome to the Code Guessing Game! 🔑");
        System.out.println("********************************************");
        System.out.println("Try to guess the secret code consisting of 4 numbers from 0 to 7.");
        System.out.println("You have 10 attempts to guess the code. Good luck!");
        System.out.println("Type '1' to make a guess or '2' to exit the game.");
        System.out.println();

        // Ginitial choice
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 2) {
            System.out.println("Thank you for playing! Goodbye!");
            return;
        }

        // Ask the user if they want to play with a timer
        System.out.println("Would you like to play with a timer? Type '1' for Yes or '2' for No.");
        int timerChoice = scanner.nextInt();
        scanner.nextLine();

        // Enable timer
        if (timerChoice == 1) {
            isTimerEnabled = true;
            initializeTimer();
        }

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

            if (feedback.correctWinningGuess()) {
                System.out.println("Congratulations! You guessed the secret code!");
                if (isTimerEnabled && timer != null) {
                    timer.cancel();
                }
                return;
            }

            if (!logic.hasAttemptsLeft()) {
                System.out.println("\nYou've used all your attempts! The secret code was: " + logic.getSecretCode());
                if (isTimerEnabled && timer != null) {
                    timer.cancel();
                }
                return;
            }
        }

        scanner.close();
    }

    private void initializeTimer() {
        remainTime = timeLimitInSeconds;
        timer = new Timer();
            timerTask = new TimerTask() {
            @Override
            public void run() {
                // Update the remaining time
                remainTime -= 30;

                System.out.println("\nRemaining time: " + remainTime + " seconds.");

                if (remainTime <= 0) {
                    System.out.println("\nTime's up! You didn't guess the secret code in time.");
                    System.out.println("The secret code was: " + logic.getSecretCode());
                    timer.cancel();
                    System.exit(0);
                }
            }
        };

        // Schedule the timer task to execute every 30 seconds
        timer.scheduleAtFixedRate(timerTask, 0, 30 * 1000);
    }

    }



