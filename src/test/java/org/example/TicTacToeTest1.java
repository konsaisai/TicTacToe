package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest1 {

    @Test
    void parsePositionTest() {
        Position position = parsePosition("1 2", 8);
        assertEquals(0, position.myRow);
        assertEquals(1, position.myCol);

        assertThrows(IllegalArgumentException.class, ()-> parsePosition("5 8", 3));
        assertThrows(IllegalArgumentException.class, ()-> parsePosition("8", 1));
        assertThrows(IllegalArgumentException.class, ()-> parsePosition("あ", 3));
    }

    @Test
    void getLengthTest() {
        Board board = new Board(3, 3);
        assertEquals(3, board.getLength());
    }

    @Test
    void showBoardTest() {

    }

}