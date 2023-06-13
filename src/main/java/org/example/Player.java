package org.example;

import java.util.Optional;

public interface Player {
    Optional<Position> getPosition(Board board, Cell cell);
    default void play(Board board, Cell cell) {
        Optional<Position> position = getPosition(board, cell);
        if (position.isPresent()) {
            board.changeBoard(position.get(), cell);
        } else {
            System.out.println(cell + " is skip");
        }
    }
}
