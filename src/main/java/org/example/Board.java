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

    public  int getLength(){
        return  myBoard.length;
    }
    public void showBoard(){
        System.out.println("----------");
        for (Cell[] row: myBoard) {  //拡張For文
            for (Cell x: row) {
                switch (x) {
                    case A -> System.out.print("|A");
                    case B -> System.out.print("|B");
                    default -> System.out.print("| ");
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
        for (int i = 0; i < myBoard.length; i ++) {
            for (int j = 0; j < myBoard[0].length; j ++){
                //縦のチェック
                if (checkLine(i, j, 1, 0, 3)) {
                    return myBoard[i][j];
                }
                //横のチェック
                if (checkLine(i, j, 0, 1, 3)) {
                    return myBoard[i][j];
                }
                //斜め（右下がり）のチェック
                if (checkLine(i, j, 1, 1, 3)) {
                    return myBoard[i][j];
                }
                //斜め（左下がり）のチェック
                if (checkLine(i, j, 1, -1, 3)) {
                    return myBoard[i][j];
                }
            }
        }
        return Cell.Nothing;
    }

    private boolean checkLine(int row, int col, int rowInc, int colInc, int count) {
        int ckRow = row;
        int ckCol = col;
        for (int i = 0; i < count - 1; i++) {
            if (validateNumber(row) || validateNumber(col)) {
                return false;
            }
            if (myBoard[row][col] == Cell.Nothing) {
                return false;
            }
            ckRow = ckRow + rowInc;
            ckCol = ckCol + colInc;
            if (validateNumber(ckRow) || validateNumber(ckCol)) {
                return false;
            }
            if (myBoard[row][col] != myBoard[ckRow][ckCol]) {
                return false;
            }
        }
        return true;
    }

    private boolean validateNumber(int num){
        return 0 > num || num >= myBoard.length;
    }
}