package com.example.minesweeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    GameBoard gameBoard = new GameBoard();
    MediaPlayer mp;
    Button[][] buttonGrid;
    Map<View, Integer> states;
    int revealedEmptySpaces = 60;
    int bombs = 10;
    boolean hasPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Handler h = new Handler();
        mp = MediaPlayer.create(this, R.raw.explosion);
        LinearLayout grid = (LinearLayout) findViewById(R.id.columns);
        int width = 7;
        int height = 10;
        buttonGrid = new Button[width][height];
        states = new HashMap<>();
        for (int i = 0; i < height; i++) {
            LinearLayout line = new LinearLayout(this);
            for (int j = 0; j < width; j++) {
                Button button = new Button(this);
                line.addView(button);
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(125, 125);
                params.setMargins(15, 15, 15, 15);
                button.setSoundEffectsEnabled(false);
                button.setLayoutParams(params);
                buttonGrid[j][i] = button;
                states.put(button, 0);
            }
            grid.addView(line);
        }
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        para.setMargins(10,0,0,0);
        final TextView bombCount = new TextView(this);
        bombCount.setText(Integer.toString(bombs));
        bombCount.setTextSize(45);
        bombCount.setLayoutParams(para);
        grid.addView(bombCount);
        final TextView timeCount = new TextView(this);
        timeCount.setText("0");
        timeCount.setTextSize(45);
        timeCount.setLayoutParams(para);
        grid.addView(timeCount);
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                Button button = buttonGrid[i][j];
                final int iindex = i;
                final int jindex = j;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(hasPressed==false){
                            final Runnable r = new Runnable() {
                                int count = 0;
                                @Override
                                public void run() {
                                    count++;
                                    if(count>60){
                                        String str = String.format("%02d", count % 60);
                                        timeCount.setText(Integer.toString(count/60)+":"+ str);
                                    }
                                    else timeCount.setText(Integer.toString(count));
                                    h.postDelayed(this, 1000); //ms
                                }
                            };
                            h.postDelayed(r, 1000);
                            hasPressed=true;
                        }
                        if(gameBoard.boardArray[iindex][jindex].bomb==true && gameBoard.boardArray[iindex][jindex].flag==false){
                            view.setBackgroundResource(R.drawable.bomb);
                            mp.start();
                            h.removeCallbacksAndMessages(null);
                            for (int i = 0; i < 7; i++) {
                                for (int j = 0; j < 10; j++) {
                                    if (gameBoard.boardArray[i][j].bomb == true) {
                                        buttonGrid[i][j].setBackgroundResource(R.drawable.bomb);
                                    }
                                }
                            }
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GameActivity.this);
                            alertBuilder.setTitle("Game Over")
                                    .setMessage("You Lost")
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
                                    })
                                    .setCancelable(false);
                            alertBuilder.show();
                        }
                        else if(gameBoard.boardArray[iindex][jindex].bomb==false && gameBoard.boardArray[iindex][jindex].revealed==false && gameBoard.boardArray[iindex][jindex].flag==false){
                            gameBoard.boardArray[iindex][jindex].revealed=true;
                            view.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
                            if(gameBoard.boardArray[iindex][jindex].neighborBombs>0){
                                buttonGrid[iindex][jindex].setText(Integer.toString(gameBoard.boardArray[iindex][jindex].neighborBombs));
                            }
                            else if(gameBoard.boardArray[iindex][jindex].neighborBombs==0){
                                if(iindex-1>=0 && iindex-1<7 && jindex-1>=0 && jindex-1<10){
                                    buttonGrid[iindex-1][jindex-1].performClick();
                                }
                                if(iindex-1>=0 && iindex-1<7 && jindex>=0 && jindex<10){
                                    buttonGrid[iindex-1][jindex].performClick();
                                }
                                if(iindex-1>=0 && iindex-1<7 && jindex+1>=0 && jindex+1<10){
                                    buttonGrid[iindex-1][jindex+1].performClick();
                                }
                                if(iindex>=0 && iindex<7 && jindex-1>=0 && jindex-1<10){
                                    buttonGrid[iindex][jindex-1].performClick();
                                }
                                if(iindex>=0 && iindex<7 && jindex+1>=0 && jindex+1<10){
                                    buttonGrid[iindex][jindex+1].performClick();
                                }
                                if(iindex+1>=0 && iindex+1<7 && jindex+1>=0 && jindex+1<10){
                                    buttonGrid[iindex+1][jindex+1].performClick();
                                }
                                if(iindex+1>=0 && iindex+1<7 && jindex>=0 && jindex<10){
                                    buttonGrid[iindex+1][jindex].performClick();
                                }
                                if(iindex+1>=0 && iindex+1<7 && jindex-1>=0 && jindex-1<10){
                                    buttonGrid[iindex+1][jindex-1].performClick();
                                }
                            }
                            revealedEmptySpaces--;
                            if(revealedEmptySpaces==0){
                                h.removeCallbacksAndMessages(null);
                                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GameActivity.this);
                                alertBuilder.setTitle("Victory")
                                        .setMessage("Your time: "  + timeCount.getText())
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
                                        })
                                        .setCancelable(false);
                                alertBuilder.show();
                            }
                        }
                    }
                });
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(gameBoard.boardArray[iindex][jindex].revealed==false && gameBoard.boardArray[iindex][jindex].flag==false){
                            v.setBackgroundResource(R.drawable.map);
                            gameBoard.boardArray[iindex][jindex].flag=true;
                            bombs--;
                            bombCount.setText(Integer.toString(bombs));
                        }
                        else if(gameBoard.boardArray[iindex][jindex].revealed==false && gameBoard.boardArray[iindex][jindex].flag==true){
                            v.setBackgroundResource(R.color.colorPrimary);
                            gameBoard.boardArray[iindex][jindex].flag=false;
                            if(bombs<10){
                                bombs++;
                                bombCount.setText(Integer.toString(bombs));
                            }
                        }
                        return true;
                    }
                });
            }
        }
    }
}
