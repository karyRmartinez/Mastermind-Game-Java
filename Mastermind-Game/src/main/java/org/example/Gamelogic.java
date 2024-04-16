package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gamelogic {
    private static final int CODE_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 10;
    private int attemptsLeft;

    public class GameUtils {
        public static List<Integer> readGuess(Scanner scanner) {
            String input = scanner.nextLine();
            String[] parts = input.trim().split("\\s+");

            if (parts.length != Gamelogic.CODE_LENGTH) {
                return null;
            }

            List<Integer> guess = new ArrayList<>();
            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part);
                    if (num < 0 || num >= Gamelogic.MAX_ATTEMPTS || guess.contains(num)) {
                        return null;
                    }
                    guess.add(num);
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            return guess;
        }
    }
}
