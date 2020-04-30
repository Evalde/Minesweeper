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
        //TODO: calculate neighbor bombs
        /*for(int x = 0; x < 7; x++) {
            for(int y = 0; y < 10; y++) {
                for(int i = boardArray[x][y].getRow() - 1; i <= boardArray[x][y].getRow() + 1; i++) {
                    for(int j = boardArray[x][y].getColumn() - 1; j <= boardArray[x][y].getColumn() + 1; j++) {
                        if(i >= 0 && i < this.grid.length && j >= 0 && j < this.grid.length) {
                            boardArray[x][y].addNeightborBomb();
                        }
                    }
                }
            }
        }
        */
    }
}
