package org.example;

public class Main {
    public static void main(String[] args) {

        int[][] board = {
                {1, 2, 1},{0, 1, 0},{2, 0, 0}
        };
        System.out.println("TicTacToe！！");
        showBoard(board);

    }

    public static void showBoard(int[][] board) {

        System.out.println("----------");
        for (int[] row: board) {  //拡張For文
            for (int x: row) {
                String val = "";
                switch (x) {
                    case 0:
                        val = "　";
                        break;
                    case 1:
                        val = "彩";
                        break;
                    case 2:
                        val = "正";
                        break;

                }
                System.out.print("|" + val);
            }
            System.out.println("|");
        }
        System.out.println("----------");

    }

}