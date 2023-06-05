package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        // テスト対象のメソッド呼び出し
        Board board = new Board(3, 3);
        board.changeBoard(Main.parsePosition("1 1", 3), Cell.A);
        board.changeBoard(Main.parsePosition("2 3", 3), Cell.A);
        board.changeBoard(Main.parsePosition("1 2", 3), Cell.B);
        board.changeBoard(Main.parsePosition("2 2", 3), Cell.B);

        board.showBoard();
        // 期待する出力を定義
        String expectedOutput = "----------\r\n" +
                "|A|B| |\r\n" +
                "| |B|A|\r\n" +
                "| | | |\r\n" +
                "----------\r\n";

        // 期待する出力と実際の出力を比較
        assertEquals(expectedOutput, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    void checkBoardTest() {
        //縦・横・斜めでどこかで３つ揃ったらOK
        //斜め（右下がり）のチェック
        Board board = new Board(3, 3);
        board.changeBoard(Main.parsePosition("1 1", 3), Cell.A);
        board.changeBoard(Main.parsePosition("2 2", 3), Cell.A);
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("3 3", 3), Cell.A);
        assertEquals(Cell.A, board.checkBoard());

        //斜め（右上がり）のチェック
        board = new Board(3, 3);
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("1 3", 3), Cell.A);
        board.changeBoard(Main.parsePosition("2 2", 3), Cell.A);
        board.changeBoard(Main.parsePosition("3 1", 3), Cell.A);
        assertEquals(Cell.A, board.checkBoard());

        //縦のチェック
        board = new Board(4, 4);
        board.changeBoard(Main.parsePosition("1 4", 4), Cell.A);
        board.changeBoard(Main.parsePosition("2 4", 4), Cell.A);
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("3 4", 4), Cell.A);
        assertEquals(Cell.A, board.checkBoard());

        //横のチェック
        board = new Board(4, 4);
        board.changeBoard(Main.parsePosition("2 1", 4), Cell.A);
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("2 2", 4), Cell.A);
        board.changeBoard(Main.parsePosition("2 3", 4), Cell.A);
        assertEquals(Cell.A, board.checkBoard());
    }

}