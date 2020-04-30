package com.example.minesweeper;

import java.util.Random;

public class Cell {
    boolean bomb;
    boolean revealed;
    int row;
    int column;
    int neighborBombs;


    public Cell(){
        row = 0;
        column = 0;
        revealed = false;
        bomb = false;
    }

    public void setBomb(int bomb){
        if (bomb==1){
            this.bomb = true;
        }
        else this.bomb = false;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setNeighborBombs(int neighborBombs) {
        this.neighborBombs = neighborBombs;
    }

    public void addNeightborBomb(){
        this.neighborBombs++;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
