package com.example.minesweeper;

import java.util.Random;

public class GameBoard extends Cell{
    public Cell[][] boardArray;

    public GameBoard(){
        //make 1d array for 10 bombs
        int[] buttons = new int[70];
        Random r = new Random();
        int bombs = 0;
        while (bombs < 10) {
            int loc = r.nextInt(70);
            if (buttons[loc] == 1) {
                continue;
            } else {
                buttons[loc] = 1;
                bombs++;
            }
        }
        //turn 1d array into 2d array
        boardArray = new Cell[7][10];
        int k = 0;
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 10; j++){
                boardArray[i][j] = new Cell();
                boardArray[i][j].setBomb(buttons[k]);
                boardArray[i][j].setRow(i);
                boardArray[i][j].setColumn(j);
                k++;
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                Cell current = boardArray[i][j];
                current.neighborBombs += bombAtLocation(i-1, j-1);
                current.neighborBombs += bombAtLocation(i-1, j);
                current.neighborBombs += bombAtLocation(i-1, j+1);
                current.neighborBombs += bombAtLocation(i, j-1);
                current.neighborBombs += bombAtLocation(i, j+1);
                current.neighborBombs += bombAtLocation(i+1, j+1);
                current.neighborBombs += bombAtLocation(i+1, j);
                current.neighborBombs += bombAtLocation(i+1, j-1);
            }
        }


    }

    private int bombAtLocation(int i, int j) {
        if (i >= 0 && i < 7 && j >= 0 && j < 10 && boardArray[i][j].bomb == true) {
            return 1;
        } else {
            return 0;
        }
    }
}
