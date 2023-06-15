package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void getRowLengthTest() {
        Board board = new Board(3, 3);
        assertEquals(3, board.getRowLength());
    }

    @Test
    void getColLengthTest() {
        Board board = new Board(3, 4);
        assertEquals(4, board.getColLength());
    }

    @Test
    void showBoardTest() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        // テスト対象のメソッド呼び出し
        Board board = new Board(3, 3);
        board.changeBoard(Main.parsePosition("1 1", 3), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 3", 3), Cell.Player1);
        board.changeBoard(Main.parsePosition("1 2", 3), Cell.Player2);
        board.changeBoard(Main.parsePosition("2 2", 3), Cell.Player2);

        board.showBoard();
        // 期待する出力を定義
        StringBuilder expectedOutput = new StringBuilder();
        String lineFeedCode;
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            lineFeedCode = "\n";
        } else {
            lineFeedCode = "\r\n";
        }
        expectedOutput.append("----------");
        expectedOutput.append(lineFeedCode);
        expectedOutput.append("|A|B| |");
        expectedOutput.append(lineFeedCode);
        expectedOutput.append("| |B|A|");
        expectedOutput.append(lineFeedCode);
        expectedOutput.append("| | | |");
        expectedOutput.append(lineFeedCode);
        expectedOutput.append("----------");
        expectedOutput.append(lineFeedCode);

        // 期待する出力と実際の出力を比較
        assertEquals(new String(expectedOutput), outputStreamCaptor.toString());
    }

    @Test
    void checkBoardTest() {
        //縦・横・斜めでどこかで３つ揃ったらOK
        //斜め（右下がり）のチェック
        Board board = new Board(3, 3);
        board.changeBoard(Main.parsePosition("1 1", 3), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 2", 3), Cell.Player1);
        assertEquals(Cell.Nothing, board.judgeWinner());
        board.changeBoard(Main.parsePosition("3 3", 3), Cell.Player1);
        assertEquals(Cell.Player1, board.judgeWinner());

        //斜め（右上がり）のチェック
        board = new Board(3, 3);
        assertEquals(Cell.Nothing, board.judgeWinner());
        board.changeBoard(Main.parsePosition("1 3", 3), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 2", 3), Cell.Player1);
        board.changeBoard(Main.parsePosition("3 1", 3), Cell.Player1);
        assertEquals(Cell.Player1, board.judgeWinner());

        //縦のチェック
        board = new Board(4, 4);
        board.changeBoard(Main.parsePosition("1 4", 4), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 4", 4), Cell.Player1);
        assertEquals(Cell.Nothing, board.judgeWinner());
        board.changeBoard(Main.parsePosition("3 4", 4), Cell.Player1);
        assertEquals(Cell.Player1, board.judgeWinner());

        //横のチェック
        board = new Board(4, 4);
        board.changeBoard(Main.parsePosition("2 1", 4), Cell.Player1);
        assertEquals(Cell.Nothing, board.judgeWinner());
        board.changeBoard(Main.parsePosition("2 2", 4), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 3", 4), Cell.Player1);
        assertEquals(Cell.Player1, board.judgeWinner());
    }
}