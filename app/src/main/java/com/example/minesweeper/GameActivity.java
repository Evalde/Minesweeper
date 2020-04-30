package com.example.minesweeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    GameBoard gameBoard = new GameBoard();
    Button[][] buttonGrid;
    Map<View, Integer> states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LinearLayout grid = (LinearLayout) findViewById(R.id.columns);
        int width = 7;
        int height = 10;
        buttonGrid = new Button[width][height];
        states = new HashMap<>();
        for (int i = 0; i < height; i++) {
            LinearLayout line = new LinearLayout(this);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            for (int j = 0; j < width; j++) {
                Button button = new Button(this);
                line.addView(button);
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(125, 125);
                params.setMargins(15, 15, 15, 15);
                button.setLayoutParams(params);
                buttonGrid[j][i] = button;
                states.put(button, 0);
            }
            grid.addView(line);
        }
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                Button button = buttonGrid[i][j];
                final int iindex = i;
                final int jindex = j;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(gameBoard.boardArray[iindex][jindex].bomb==true){
                            view.setBackgroundResource(R.drawable.bomb);
                            for (int i = 0; i < 7; i++) {
                                for (int j = 0; j < 10; j++) {
                                    if (gameBoard.boardArray[i][j].bomb == true) {
                                        buttonGrid[i][j].setBackgroundResource(R.drawable.bomb);
                                    }
                                }
                            }
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GameActivity.this);
                            alertBuilder.setTitle("Gamer Over")
                                    .setMessage("You Lose")
                                    .setPositiveButton("Again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            GameActivity.this.recreate();
                                        }
                                    })
                                    .setNegativeButton("Menu", new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i){
                                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                        }
                        else if(gameBoard.boardArray[iindex][jindex].bomb==false){
                            view.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
                            if(gameBoard.boardArray[iindex][jindex].neighborBombs>0){
                                buttonGrid[iindex][jindex].setText(Integer.toString(gameBoard.boardArray[iindex][jindex].neighborBombs));
                            }
                            else{
                                //TODO: reveal non-bomb spaces around it
                            }
                        }
                    }
                });
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        v.setBackgroundResource(R.drawable.map);
                        return true;
                    }
                });
            }
        }
    }
}
