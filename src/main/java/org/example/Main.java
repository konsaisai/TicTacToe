package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;

enum Cell {
    A, B, Nothing;
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
        String green  = "\u001b[00;32m";
        String yellow = "\u001b[00;33m";
        String purple = "\u001b[00;34m";
        String pink   = "\u001b[00;35m";
        String cyan   = "\u001b[00;36m";
        String end    = "\u001b[00m";

        Cell[][] board = createBoard(3,3);
        System.out.println("TicTacToe！！"+cyan);
        showBoard(board);

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
            if (!changeBoard(board, position, type)) {
                b = b - 1;
                System.out.println("This cell is already selected!!" + red);
            }
            showBoard(board);

            //結果を判定
            if (checkBoard(board) != Cell.Nothing) {
                System.out.println("Congratulations!!");
                System.out.println(type + " Win!!");
                return;
            }
        }
        System.out.println("Draw");
    }

    public static Cell[][] createBoard(int row, int col) {
        Cell[][] board = new Cell[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Cell.Nothing;
            }
        }
        return board;
    }
    public static boolean changeBoard(Cell[][] board, Position position, Cell cell) {
        if (board[position.getRow()][position.getCol()] == Cell.Nothing) {
            board[position.getRow()][position.getCol()] = cell;
            return true;
        } {
            return false;
        }
    }

    public static Cell checkBoard(Cell[][] board) {
        //縦のチェック
        for (Cell[] cells : board) {
            if ((cells[0] == cells[1]) && (cells[0] == cells[2])) {
                return cells[0];
            }
        }

        //横のチェック
        for (int i = 0; i < board[0].length; i++){
            if ((board[0][i] == board[1][i]) && (board[0][i] == board[2][i])) {
                return board[0][i];
            }
        }

        //斜めのチェック
        if (((board[0][0] == board[1][1]) && (board[0][0] == board[2][2])) ||
                ((board[0][2] == board[1][1]) && (board[0][2] == board[2][0]))) {
            return board[0][0];
        }
        return Cell.Nothing;
    }

    public static void showBoard(Cell[][] board) {
        System.out.println("----------");
        for (Cell[] row: board) {  //拡張For文
            for (Cell x: row) {
                switch (x) {
                    case A -> System.out.print("|A");
                    case B -> System.out.print("|B");
                    default -> System.out.print("|　");
                }
            }
            System.out.println("|");
        }
        System.out.println("----------");
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