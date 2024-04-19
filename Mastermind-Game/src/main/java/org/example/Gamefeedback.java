package org.example;

public class Gamefeedback {
    final int correctNumbers;
    final int correctLocation;

    public Gamefeedback(int correct, int correctLocation) {
        this.correctNumbers = correct;
        this.correctLocation = correctLocation;
    }
    public boolean correctWinningGuess () {
        return correctNumbers == Gamelogic.CODE_LENGTH && correctLocation == Gamelogic.CODE_LENGTH;
    }
    @Override
//    public String toString() {
//        return "Gamefeedback(" +
//                "correct Number =" + correctNumbers +
//                ", correctLocation =" + correctLocation +
//                ')';
//    }
    public String toString() {
        if (correctLocation == 0 && correctNumbers == 0) {
            return "all incorrect";
        } else {
            return correctNumbers + " correct number(s) and " + correctLocation + " correct location(s)";
        }


    }
}