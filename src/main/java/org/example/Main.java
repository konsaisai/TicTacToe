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

            Position rowCol;
            //入力チェック
            do {
                System.out.println(type + ":Input number (Row Col)");
                System.out.println(" Ex. 1 1");
                Scanner in = new Scanner(System.in);
                String num = in.nextLine();
                rowCol = checkString(num);
                if (rowCol == null){
                    System.out.println("Input Error!!!" + red);
                }
            }while (rowCol == null);

            //反映
            if (!changeBoard(board, rowCol, type)) {
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
        Cell val = Cell.Nothing;
        Boolean bFound = false;
        //縦のチェック
        for (int i = 0; i < board.length; i++){
            if ((board[i][0] == board[i][1]) && (board[i][0] == board[i][2])) {
                val = board[i][0];
                return val;
            }
        }

        //横のチェック
        for (int i = 0; i < board[0].length; i++){
            if ((board[0][i] == board[1][i]) && (board[0][i] == board[2][i])) {
                val = board[0][i];
                return val;
            }
        }

        //斜めのチェック
        if (((board[0][0] == board[1][1]) && (board[0][0] == board[2][2])) ||
                ((board[0][2] == board[1][1]) && (board[0][2] == board[2][0]))) {
            val = board[0][0];
            return val;
        }
        return val;
    }

    public static void showBoard(Cell[][] board) {
        System.out.println("----------");
        for (Cell[] row: board) {  //拡張For文
            for (Cell x: row) {
                switch (x) {
                    case A:
                        System.out.print("|彩");
                        break;
                    case B:
                        System.out.print("|正");
                        break;
                    default:
                        System.out.print("|　");
                        break;
                }
            }
            System.out.println("|");
        }
        System.out.println("----------");
    }

    // 引数で受け取った文字列が数値かどうか正規表現でチェックするメソッド
    public static Position checkString(String input) {
        String red    = "\u001b[00;31m";
        String nums[] = input.split(" ");
        if (nums.length != 2) {
            return null;
        }

        Pattern pattern = Pattern.compile("(1|2|3)");
        boolean res = pattern.matcher(nums[0]).matches();
        if (!res) {
            System.out.println("Input Error!!!");
            return null;
        }

        res = pattern.matcher(nums[1]).matches();
        if (res) {
            return new Position(Integer.parseInt(nums[0]) - 1, Integer.parseInt(nums[1]) - 1);
        } else {
            return null;
        }
    }
}