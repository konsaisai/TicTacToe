package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void automaticPlayTest() {
        Board board = new Board(3, 3);
        board.changeBoard(Main.parsePosition("2 1", 3), Cell.Player1);

        Player op = new CPUPlayer();
        Optional<Position> position = op.getPosition(board, Cell.Player2);
        board.changeBoard(position.get(), Cell.Player2);
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
        Player op = new HumanPlayer();
        op.play(board, Cell.Player1);
        assertEquals(Cell.Player1, board.myBoard[2][0]);
    }

    @Test
    public void decideOpponentTest() {
        Player player = new HumanPlayer();
        assertEquals(CPUPlayer.class, player.decideOpponent("C").getClass());
        assertEquals(HumanPlayer.class, player.decideOpponent("H").getClass());

    }
}