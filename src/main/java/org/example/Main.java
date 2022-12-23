package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;

enum Cell {
    A, B, Nothing;
}

public class Main {
    public static void main(String[] args) {
        Cell[][] board = getBoard(3,3);

        System.out.println("TicTacToe！！");
        showBoard(board);

        //入力を取得してboardに反映する
        for (int b =0 ; b < 9 ; b++) {
            Cell type;
            if (b % 2 == 0) {
                type = Cell.A;
            } else {
                type = Cell.B;
            }
            System.out.println(type + ":Input number (Row Col)");
            System.out.println(" Ex. 1 1");
            Scanner in = new Scanner(System.in);
            String num = in.nextLine();

            String[] nums = num.split(" ");
            board = changeBoard(board,Integer.parseInt(nums[0]) - 1,Integer.parseInt(nums[1]) - 1, type);
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

//    public  static  String getRowCol(String nums){
//        InNum inNum = new InNum();
//        String[] num = nums.split(" ");
//        System.out.println(num[0] + " " + num[1]);
//        if (checkString(num[0]) && checkString(num[1])) {
//            InNum.row = Integer.parseInt(num[0]);
//            inNum.col = Integer.parseInt(num[1]);
//        } else {
//            inNum.row = 0;
//        }
//        return  inNum;
//    }

    public static Cell[][] getBoard(int row,int col) {
        Cell[][] board = new Cell[row][col];
        for (int i = 0 ; i < row ; i++) {
            for (int j = 0 ; j < col ; j++) {
                board[i][j] = Cell.Nothing;
            }
        }
        return board;
    }
    public static Cell[][] changeBoard(Cell[][] board ,int row ,int col ,Cell cell) {
        if (board[row][col] == Cell.Nothing) {
            board[row][col] = cell;
        }
        return  board;
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
    public static boolean checkString(String text) {
        boolean res = true;
        Pattern pattern = Pattern.compile("^[0-9]+$|-[0-9]+$");
        res = pattern.matcher(text).matches();
        return res;
    }

}