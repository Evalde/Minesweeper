package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

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
                button.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                line.addView(button);
                button.setBackgroundColor(Color.RED);
                buttonGrid[j][i] = button;
                states.put(button, 0);
            }
            grid.addView(line);
        }

        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                Button button = buttonGrid[i][j];
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int state = states.get(view);
                        state++;
                        states.put(view, state);
                        if (state % 3 == 0) {
                            view.setBackgroundColor(Color.RED);
                        } else if (state % 3 == 1) {
                            view.setBackgroundResource(R.drawable.bomb);
                        } else {
                            view.setBackgroundResource(R.drawable.map);
                        }
                    }
                });
            }
        }
    }
}
