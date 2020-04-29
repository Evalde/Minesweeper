package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout grid = (LinearLayout) findViewById(R.id.columns);
        int width = 7;
        int height = 10;
        for (int i = 0; i < height; i++) {
            LinearLayout line = new LinearLayout(this);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            for (int j = 0; j < width; j++) {
                Button button = new Button(this);
                //button.setBackground(R.);
                button.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                line.addView(button);
            }
            grid.addView(line);
        }
    }
}
