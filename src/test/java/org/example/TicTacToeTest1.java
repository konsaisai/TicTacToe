package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
        //String lineFeedCode = "\r\n";
        String lineFeedCode = "\n";
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
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("3 3", 3), Cell.Player1);
        assertEquals(Cell.Player1, board.checkBoard());

        //斜め（右上がり）のチェック
        board = new Board(3, 3);
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("1 3", 3), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 2", 3), Cell.Player1);
        board.changeBoard(Main.parsePosition("3 1", 3), Cell.Player1);
        assertEquals(Cell.Player1, board.checkBoard());

        //縦のチェック
        board = new Board(4, 4);
        board.changeBoard(Main.parsePosition("1 4", 4), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 4", 4), Cell.Player1);
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("3 4", 4), Cell.Player1);
        assertEquals(Cell.Player1, board.checkBoard());

        //横のチェック
        board = new Board(4, 4);
        board.changeBoard(Main.parsePosition("2 1", 4), Cell.Player1);
        assertEquals(Cell.Nothing, board.checkBoard());
        board.changeBoard(Main.parsePosition("2 2", 4), Cell.Player1);
        board.changeBoard(Main.parsePosition("2 3", 4), Cell.Player1);
        assertEquals(Cell.Player1, board.checkBoard());
    }

    @Test
    void automaticPlayTest() {
        Board board = new Board(3, 3);
        board.changeBoard(Main.parsePosition("2 1", 3), Cell.Player1);

        Opponent op = new AutomaticBattle();
        op.play(board, Cell.Player2);
        assertEquals(Cell.Player2, board.myBoard[0][0]);
        board.changeBoard(Main.parsePosition("1 2", 3), Cell.Player1);
        op.play(board, Cell.Player2);
        assertEquals(Cell.Player2, board.myBoard[0][2]);
    }

    @Test
    void playerBattleTest() {
        Board board = new Board(3, 3);
        //インプットを設定
        String input = "3 1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //メソッドを呼び出す
        Opponent op = new PlayerBattle();
        op.play(board, Cell.Player1);
        assertEquals(Cell.Player1, board.myBoard[2][0]);
    }
}