package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;

enum Cell {
    A, B, Nothing
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
        String red    = "\u001b[00;31m";
        String cyan   = "\u001b[00;36m";

        Board board = new Board(3, 3);
        System.out.println("TicTacToe！！" + cyan);
        board.showBoard();

        //入力を取得してboardに反映する
        for (int b =0; b < 9; b++) {
            Cell type;
            if (b % 2 == 0) {
                type = Cell.A;
            } else {
                type = Cell.B;
            }

            Position position;
            //入力チェック
            do {
                System.out.println(type + ":Input number (Row Col)");
                System.out.println(" Ex. 1 1");
                Scanner in = new Scanner(System.in);
                String num = in.nextLine();
                try {
                    position = parsePosition(num);
                } catch (IllegalArgumentException e) {
                    System.out.println("Input Error!!!" + red);
                    System.out.println(e.getMessage());
                    position = null;
                }
            }while (position == null);

            //反映
            if (!board.changeBoard(position, type)) {
                b = b - 1;
                System.out.println("This cell is already selected!!" + red);
            }
            board.showBoard();

            //結果を判定
            if (board.checkBoard() != Cell.Nothing) {
                System.out.println("Congratulations!!");
                System.out.println(type + " Win!!");
                return;
            }
        }
        System.out.println("Draw");
    }


    // 引数で受け取った文字列が数値かどうか正規表現でチェックするメソッド
    public static Position parsePosition(String input) throws IllegalArgumentException {
        String[] numbs = input.split(" ");
        if (numbs.length != 2) {
            throw new IllegalArgumentException ("行と列を半角スペースで区切ってください");
        }

        Pattern pattern = Pattern.compile("([1-3])");
        if (!pattern.matcher(numbs[0]).matches()) {
            System.out.println("Input Error!!!");
            throw new IllegalArgumentException("行は１から３で入力してください");
        }

        if (!pattern.matcher(numbs[1]).matches()) {
            throw new IllegalArgumentException("列は１から３で入力してください");
        }
        return new Position(Integer.parseInt(numbs[0]) - 1, Integer.parseInt(numbs[1]) - 1);
    }
}