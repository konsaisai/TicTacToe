package org.example;

public class CPUPlayer implements Player {
    @Override
    public Position getPosition(Board board, Cell cell) {
        return getNextStep(board, cell);
    }

    private Position getNextStep(Board board, Cell cell) {
        //最初に見つかった配置できる場所に配置する
        for (int i = 0; i < board.getRowLength(); i ++) {
            for (int j = 0; j < board.getColLength(); j++) {
                if (board.myBoard[i][j] == Cell.Nothing) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }
}
