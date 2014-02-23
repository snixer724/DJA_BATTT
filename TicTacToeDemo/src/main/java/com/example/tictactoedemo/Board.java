package com.example.tictactoedemo;

/**
 * Created by Drew on 2/16/14.
 */
public class Board {

    private Player[][] playerBoard = new Player[3][3];

    public Board() {
        for(int i = 0; i < playerBoard.length; i++) {
            for(int j = 0; j < playerBoard[i].length; j++) {
                playerBoard[i][j] = Player.E;
            }
        }
    }

    public void makePlay(int x, int y, Player p) {

        if (x < 0 || x > playerBoard.length || y < 0 || y > playerBoard.length) {
            throw new RuntimeException("invaild coor.");
        }

        if (playerBoard[x][y] != Player.E) {
            throw new RuntimeException("non-empty space");
        }

        playerBoard[x][y] = p;

        //Check for win

        if(isGameOver()){
            throw new RuntimeException("Game is over, no more plays");
        }
    }

    private boolean checkWin() {
        // check for rows
        for (int r = 0; r < playerBoard.length; r++) {
            if (playerBoard[r][0] == playerBoard[r][1] && playerBoard[r][1] == playerBoard[r][2] && playerBoard[r][0] != Player.E) {
                return true;
            }
        }

        // check for cols
        for (int c = 0; c < playerBoard.length; c++) {
            if (playerBoard[0][c] == playerBoard[1][c] && playerBoard[1][c] == playerBoard[2][c] && playerBoard[0][c] != Player.E) {
                return true;
            }
        }

        // check for diags
        if (playerBoard[0][0] == playerBoard[1][1] && playerBoard[1][1] == playerBoard[2][2] && playerBoard[0][0] != Player.E) {
            return true;
        }

        if (playerBoard[0][2] == playerBoard[1][1] && playerBoard[1][1] == playerBoard[2][0] && playerBoard[0][2] != Player.E) {
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
        if (checkWin()) {
            return true;
        }

        for(int i = 0; i < playerBoard.length; i++) {
            for(int j = 0; j < playerBoard[i].length; j++) {
                if (playerBoard[i][j] == Player.E) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for(int i = 0; i < playerBoard.length; i++) {
            for(int j = 0; j < playerBoard[i].length; j++) {
                System.out.print(playerBoard[i][j].toString() + " ");
            }
            System.out.println("");
        }
    }
}
