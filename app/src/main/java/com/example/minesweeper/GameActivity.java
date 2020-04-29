package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
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
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int state = states.get(view);
                        state++;
                        states.put(view, state);
                        if (state % 3 == 0) {
                            view.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
                        } else if (state % 3 == 1) {
                            view.setBackgroundResource(R.drawable.bomb);
                        } else {
                            view.setBackgroundResource(R.drawable.map);
                        }
                    }
                });
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int state = states.get(v);
                        
                        v.setBackgroundResource(R.drawable.map);
                        return true;
                    }
                });
            }
        }
    }
}
