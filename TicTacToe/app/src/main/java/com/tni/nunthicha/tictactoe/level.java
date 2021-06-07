package com.tni.nunthicha.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class level extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    public void to_3x3(View view) {
        Intent G3 = new Intent(this, Grid.class);
        G3.putExtra("lv", 3);
        startActivity(G3);
    }

    public void to_4x4(View view) {
        Intent G4 = new Intent(this, Grid.class);
        G4.putExtra("lv", 4);
        startActivity(G4);
    }

    public void to_5x5(View view) {
        Intent G5 = new Intent(this, Grid.class);
        G5.putExtra("lv", 5);
        startActivity(G5);
    }

    public void to_6x6(View view) {
        Intent G6 = new Intent(this, Grid.class);
        G6.putExtra("lv", 6);
        startActivity(G6);
    }

    public void to_7x7(View view) {
        Intent G7 = new Intent(this, Grid.class);
        G7.putExtra("lv", 7);
        startActivity(G7);
    }

    public void to_8x8(View view) {
        Intent G8 = new Intent(this, Grid.class);
        G8.putExtra("lv", 8);
        startActivity(G8);
    }

    public void to_9x9(View view) {
        Intent G9 = new Intent(this, Grid.class);
        G9.putExtra("lv", 9);
        startActivity(G9);
    }

    public void to_10x10(View view) {
        Intent G10 = new Intent(this, Grid.class);
        G10.putExtra("lv", 10);
        startActivity(G10);
    }

}