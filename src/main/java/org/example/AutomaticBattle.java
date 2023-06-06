package org.example;

public class AutomaticBattle implements Opponent{
    @Override
    public void play(Board board, Cell cell) {
        getNextStep(board, cell);
    }

    private void getNextStep(Board board, Cell cell) {
        //最初に見つかった配置できる場所に配置する
        for (int i = 0; i < board.getRowLength(); i ++) {
            for (int j = 0; j < board.getColLength(); j++) {
                if (board.myBoard[i][j] == Cell.Nothing) {
                    board.changeBoard(new Position(i, j), cell);
                    return;
                }
            }
        }

    }
}
