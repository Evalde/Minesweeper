package com.example.minesweeper;

public class GameBoard {
    public Cell[][] grid;
    int columns;
    int rows;

    public GameBoard(){
        columns = 7;
        rows = 10;
        grid = new Cell[columns][rows];
        for (int i = 0; i < columns; i++){
            for (int j = 0; j< rows; j++){
                grid[i][j] = new Cell(i, j);
            }
        }
    }
}
