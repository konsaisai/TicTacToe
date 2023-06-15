package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void parsePositionTest() {
        Position position = parsePosition("1 2", 8);
        assertEquals(0, position.myRow);
        assertEquals(1, position.myCol);

        assertThrows(IllegalArgumentException.class, ()-> parsePosition("5 8", 3));
        assertThrows(IllegalArgumentException.class, ()-> parsePosition("8", 1));
        assertThrows(IllegalArgumentException.class, ()-> parsePosition("あ", 3));
    }
}