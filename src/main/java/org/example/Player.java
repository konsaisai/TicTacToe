package org.example;

public interface Player {
    Position getPosition(Board board, Cell cell);
    default void play(Board board, Cell cell) {
        Position position = getPosition(board, cell);
        board.changeBoard(position, cell);
    }
}
