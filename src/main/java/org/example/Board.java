package org.example;

class Board {
    Cell[][] myBoard;
    public Board(int row, int col){
        Cell[][] board = new Cell[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Cell.Nothing;
            }
        }
        myBoard = board;
    }

    public void showBoard(){
        System.out.println("----------");
        for (Cell[] row: myBoard) {  //拡張For文
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

    public boolean changeBoard(Position position, Cell cell) {
        if (myBoard[position.getRow()][position.getCol()] == Cell.Nothing) {
            myBoard[position.getRow()][position.getCol()] = cell;
            return true;
        }
        return false;
    }

    public Cell checkBoard() {
        boolean bEnd;
        Cell val;
        //縦のチェック
        for (Cell[] cells : myBoard) {
            val = cells[0];
            bEnd = true;
            for (Cell type : cells) {
                if (val != type) {
                    bEnd = false;
                    break;
                }
            }
            if (bEnd) {
                return val;
            }
        }


        //横のチェック
        for (int i = 0; i < myBoard[0].length; i++) {
            val = myBoard[0][i];
            bEnd = true;
            for (Cell[] cells : myBoard) {
                if (val != cells[i]) {
                    bEnd = false;
                    break;
                }
            }
            if (bEnd) {
                return val;
            }
        }

        //斜めのチェック
        if (((myBoard[0][0] == myBoard[1][1]) && (myBoard[0][0] == myBoard[2][2])) ||
                ((myBoard[0][2] == myBoard[1][1]) && (myBoard[0][2] == myBoard[2][0]))) {
            return myBoard[0][0];
        }
        return Cell.Nothing;
    }

}