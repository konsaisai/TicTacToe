package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;

enum Cell {
    Player1, Player2, Nothing
}

class Position {
    int myRow;
    int myCol;

    public Position(int row, int col) {
        myRow = row;
        myCol = col;
    }
    public int getRow() {
        return myRow;
    }
    public int getCol() {
        return myCol;
    }
}


public class Main {
    public static void main(String[] args) {
        String cyan   = "\u001b[00;36m";
        int row = 5;
        int col = 5;

        Scanner in = new Scanner(System.in);
        Opponent op;
        PlayerBattle player = new PlayerBattle();

        Board board = new Board(row, col);
        System.out.println("TicTacToe！！" + cyan);

        //対人戦もしくはコンピュータ戦を選択
        System.out.println("Do you play computer games?(y/n)");
        String answer = in.nextLine();
        if (answer.contains("y")) {
            op = new AutomaticBattle();
            System.out.println("Start AutomaticBattle.");
        } else {
            op = new PlayerBattle();
            System.out.println("Start PlayerBattle.");
        }

        board.showBoard();

        //入力を取得してboardに反映する
        for (int b =0; b < (row * col); b++) {
            if (b % 2 == 0) {
                player.play(board, Cell.Player1);
            } else {
                op.play(board, Cell.Player2);
            }

            board.showBoard();

            //結果を判定
            Cell winner = board.checkBoard();
            if (winner != Cell.Nothing) {
                System.out.println("Congratulations!!");
                System.out.println(winner + " Win!!");
                return;
            }
        }
        System.out.println("Draw");
    }


    // 引数で受け取った文字列が数値かどうか正規表現でチェックするメソッド
    public static Position parsePosition(String input, int len) throws IllegalArgumentException {
        String[] numbs = input.split(" ");
        if (numbs.length != 2) {
            throw new IllegalArgumentException ("行と列を半角スペースで区切ってください");
        }

        String regex = "([1-" + len + "])";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(numbs[0]).matches()) {
            System.out.println("Input Error!!!");
            throw new IllegalArgumentException("行は1から" + len + "で入力してください");
        }

        if (!pattern.matcher(numbs[1]).matches()) {
            throw new IllegalArgumentException("行は1から" + len + "で入力してください");
        }
        return new Position(Integer.parseInt(numbs[0]) - 1, Integer.parseInt(numbs[1]) - 1);
    }
}