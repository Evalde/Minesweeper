package com.example.minesweeper;

import java.util.Random;

public class Cell {
    boolean bomb;
    boolean revealed;
    int row;
    int column;
    public Cell(int x, int y){
        row = x;
        column = y;
        revealed = false;
        Random generator = new Random();
        int rand = generator.nextInt(10-1+1) + 1;
        rand = rand/10;
        if (rand>.75){
            bomb = true;
        }
    }
}
