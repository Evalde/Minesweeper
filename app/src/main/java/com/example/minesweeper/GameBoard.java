package com.example.minesweeper;

import java.util.Random;

public class GameBoard extends Cell{
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
        Cell[][] boardArray = new Cell[7][10];
        int k = 0;
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 9; j++){
                boardArray[i][j].setBomb(buttons[k]);
                boardArray[i][j].setRow(i);
                boardArray[i][j].setColumn(j);
            }
        }
    }
}
