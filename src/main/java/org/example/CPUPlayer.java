package org.example;

import java.util.Optional;

public class CPUPlayer implements Player {

    CPUPlayer() {
        System.out.println("Start AutomaticBattle.");
    }
    @Override
    public Optional<Position> getPosition(Board board, Cell cell) {
        return getNextStep(board, cell);
    }

    private Optional<Position> getNextStep(Board board, Cell cell) {
        //最初に見つかった配置できる場所に配置する
        for (int i = 0; i < board.getRowLength(); i ++) {
            for (int j = 0; j < board.getColLength(); j++) {
                if (board.myBoard[i][j] == Cell.Nothing) {
                    return Optional.ofNullable(new Position(i, j));
                }
            }
        }
        return null;
    }
}
