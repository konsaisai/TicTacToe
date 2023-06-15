package org.example;

import java.util.Optional;
import java.util.Scanner;

public interface Player {
    Optional<Position> getPosition(Board board, Cell cell);
    default Player decideOpponent(String opponentType) {
        //対人戦もしくはコンピュータ戦を選択
        if (opponentType.contains("C")) {
            return new CPUPlayer();
        } else if (opponentType.contains("H")) {
            return new HumanPlayer();
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Do you play computer games?(y/n)");
            String answer = in.nextLine();
            if (answer.contains("y")) {
                return new CPUPlayer();
            } else {
                return new HumanPlayer();
            }
        }
    }
    default void play(Board board, Cell cell) {
        Optional<Position> position = getPosition(board, cell);
        if (position.isPresent()) {
            board.changeBoard(position.get(), cell);
        } else {
            System.out.println(cell + " is skip");
        }
    }
}
