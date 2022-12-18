package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String[][] board = {
                {"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"}
        };

        System.out.println("TicTacToe！！");
        showBoard(board);

        //入力を取得してboardに反映する
        for (int b =0 ; b < 9 ; b++) {
            String type;
            if (b % 2 == 0) {
                type = "彩";
            } else {
                type = "正";
            }
            System.out.println(type + ":Input number ");
            Scanner in = new Scanner(System.in);
            String num = in.nextLine();
            board = changeBoard(board, num,type);
            showBoard(board);

            //結果を判定
            if (checkBoard(board) != "") {
                System.out.println("Congratulations!!");
                System.out.println(type + " Win!!");
                return;
            }
        }

        System.out.println("Draw");
    }

    public static String[][] changeBoard(String[][] board ,String num ,String type) {
        //配列を書き換えられないので拡張For文は使用しない
        Boolean bFound;
        bFound = false;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (checkString(board[i][j])) {
                    if (Integer.parseInt(board[i][j]) == Integer.parseInt(num)) {
                        board[i][j] = type;
                        bFound = true;
                        break;
                    }
                }
            }

            if (bFound) {
                break;
            }
        }
        return  board;
    }

    public static String checkBoard(String[][] board) {
        String val = "";
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

    public static void showBoard(String[][] board) {
        System.out.println("----------");
        for (String[] row: board) {  //拡張For文
            for (String x: row) {
                String val = "";
                switch (x) {
                    case "彩":
                        val = "彩";
                        break;
                    case "正":
                        val = "正";
                        break;
                    default:
                        val = " " + x;
                        break;
                }
                System.out.print("|" + val);
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