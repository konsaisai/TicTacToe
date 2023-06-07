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

    public int getRowLength(){
        return  myBoard.length;
    }

    public int getColLength(){
        return  myBoard[0].length;
    }

    public void showBoard(){
        System.out.println("----------");
        for (Cell[] row: myBoard) {  //拡張For文
            for (Cell x: row) {
                switch (x) {
                    case Player1 -> System.out.print("|A");
                    case Player2 -> System.out.print("|B");
                    default -> System.out.print("| ");
                }
            }
            System.out.println("|");
        }
        System.out.println("----------");
    }

    public boolean changeBoard(Position position, Cell cell) {
        if (positionCheck(position)) {
            myBoard[position.getRow()][position.getCol()] = cell;
            return true;
        }
        return false;
    }

    public boolean positionCheck(Position position) {
        return (myBoard[position.getRow()][position.getCol()] == Cell.Nothing);
    }

    public boolean isGameOver() {
        Cell winner = judgeWinner();
        if (winner != Cell.Nothing) {
            System.out.println("Congratulations!!");
            System.out.println(winner + " Win!!");
            return true;
        }
        return false;
    }

    public Cell judgeWinner() {
        for (int i = 0; i < myBoard.length; i ++) {
            for (int j = 0; j < myBoard[0].length; j ++){
                //縦のチェック
                if (winnerCheck(i, j, 1, 0, 3)) {
                    return myBoard[i][j];
                }
                //横のチェック
                if (winnerCheck(i, j, 0, 1, 3)) {
                    return myBoard[i][j];
                }
                //斜め（右下がり）のチェック
                if (winnerCheck(i, j, 1, 1, 3)) {
                    return myBoard[i][j];
                }
                //斜め（左下がり）のチェック
                if (winnerCheck(i, j, 1, -1, 3)) {
                    return myBoard[i][j];
                }
            }
        }
        return Cell.Nothing;
    }


    private boolean winnerCheck(int row, int col, int rowInc, int colInc, int count) {
        int ckRow = row;
        int ckCol = col;
        for (int i = 0; i < count - 1; i++) {
            if (!isValidPosition(row, col)) {
                return false;
            }
            if (myBoard[row][col] == Cell.Nothing) {
                return false;
            }
            ckRow = ckRow + rowInc;
            ckCol = ckCol + colInc;
            if (!isValidPosition(ckRow, ckCol)) {
                return false;
            }
            if (myBoard[row][col] != myBoard[ckRow][ckCol]) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPosition(int row, int col){
        return 0 <= row && row < myBoard.length && 0 <= col && col < myBoard.length;
    }
}