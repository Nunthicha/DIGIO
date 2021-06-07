package com.tni.nunthicha.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Grid extends AppCompatActivity implements View.OnClickListener {
    private TextView textScore;
    private boolean P1turn = true;
    private int AllTurn;
    private int LV = 0;
    private int sum = 0;
    private int move = 0;
    private int CW = 0;
    public  int MH[] = new int[100];
    private Button[][] BT = new Button[10][10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        LV = bundle.getInt("lv");

        //move to grid
        super.onCreate(savedInstanceState);
        if (LV==3) {
            setContentView(R.layout.activity_g3x3);
        }
        else if (LV==4){
            setContentView(R.layout.activity_g4x4);
        }
        else if (LV==5){
            setContentView(R.layout.activity_g5x5);
        }
        else if (LV==6){
            setContentView(R.layout.activity_g6x6);
        }
        else if (LV==7){
            setContentView(R.layout.activity_g7x7);
        }
        else if (LV==8){
            setContentView(R.layout.activity_g8x8);
        }
        else if (LV==9){
            setContentView(R.layout.activity_g9x9);
        }
        else if (LV==10){
            setContentView(R.layout.activity_g10x10);
        }

        textScore = findViewById(R.id.Score);

        //buttons on grid
        for (int i = 0; i < LV; i++) {
            for (int j = 0; j < LV; j++) {
                String buttonID = "button" + i + j;
                int ID = getResources().getIdentifier(buttonID, "id", getPackageName());
                BT[i][j] = findViewById(ID);
                BT[i][j].setOnClickListener(this);
            }
        }

        //play again
        Button buttonPA = findViewById(R.id.button_PA);
        buttonPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < (LV*LV); i++){
                    MH[i] = 0;
                }
                reset();
                EnableBut();
            }
        });

        //move history
        Button buttonMH = findViewById(R.id.button_MH);
        buttonMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                MoveHistory();
            }
        });
    }


    @Override
    public void onClick(View v) {
        MH[move++] = v.getId();
        Playgame(v.getId());
    }

    public void Playgame(int id) {
        View v = findViewById(id);

        if (!((Button)v).getText().toString().equals("")) {
            return;
        }
        if (P1turn) {
            ((Button)v).setText("O");
            ((Button)v).setEnabled(false);
            ((Button)v).setTextColor(getResources().getColor(R.color.veryred));

        } else {
            ((Button)v).setText("X");
            ((Button)v).setEnabled(false);
            ((Button)v).setTextColor(getResources().getColor(R.color.verydarkblue));
        }
        AllTurn++;

        if (checkWin()) {
            if (P1turn) {
                P1Win();
            } else {
                P2Win();
            }
        } else if (AllTurn == (LV*LV)) {
            Draw();
        } else {
            P1turn = !P1turn;
        }
    }

    private boolean checkWin() {
        String[][] field = new String[LV][LV];
        for (int i = 0; i < LV; i++){
            for (int j = 0; j < LV; j++){
                field[i][j] = BT[i][j].getText().toString();
            }
        }

        //grid 3*3 use 3 for win, grid 4*4 use 4 for win, grid 5*5 - 10*10 use 5 for win
        if(LV == 3){
            CW = 3;
        }
        else if(LV == 4){
            CW = 4;
        }
        else {
            CW = 5;
        }

        //check row
        for(int i = 0; i < LV; i++) {
            sum = 0;
            for (int j = 0; j < LV; j++) {
                if (field[i][j].equals("O")) {
                    sum += 1;
                }
                if (field[i][j].equals("X")) {
                    sum += -1;
                }
                if (field[i][j].equals("")){
                    sum = 0;
                }
                if (sum == CW || sum == -CW) {
                    return true;
                }
            }
        }

        //check column
        for(int j = 0; j < LV; j++) {
            sum = 0;
            for (int i = 0; i < LV; i++) {
                if (field[i][j].equals("O")) {
                    sum += 1;
                }
                if (field[i][j].equals("X")) {
                    sum += -1;
                }
                if (field[i][j].equals("")){
                    sum = 0;
                }
                if (sum == CW || sum == -CW) {
                    return true;
                }
            }
        }

        //check diagonal L to R
        for(int i = 0; i < LV; i++) {
            for (int j = 0; j < LV; j++) {
                sum = 0;
                for (int k = 0; k < LV; k++) {
                    if(i+k > LV-1 || j+k > LV-1){
                        break;
                    }
                    if (field[i+k][j+k].equals("O")) {
                        sum += 1;
                    }
                    if (field[i+k][j+k].equals("X")) {
                        sum += -1;
                    }
                    if (field[i+k][j+k].equals("")){
                        sum = 0;
                    }
                    if (sum == CW || sum == -CW) {
                        return true;
                    }
                }
            }
        }

        //check diagonal R to L
        for(int i = LV-1; i >= 0; i--) {
            for (int j = 0; j < LV; j++) {
                sum = 0;
                for (int k = 0; k < LV; k++) {
                    if(i-k < 0 || j+k > LV-1){
                        break;
                    }
                    if (field[i-k][j+k].equals("O")) {
                        sum += 1;
                    }
                    if (field[i-k][j+k].equals("X")) {
                        sum += -1;
                    }
                    if (field[i-k][j+k].equals("")){
                        sum = 0;
                    }
                    if (sum == CW || sum == -CW) {
                        return true;
                    }
                }
            }
        }
        return  false;
    }

    private void P1Win() {
        showP1Win();
        disableBut();
    }

    private void P2Win() {
        showP2Win();
        disableBut();
    }

    @SuppressLint("SetTextI18n")
    private void showP1Win() {
        textScore.setText("!! PLAYER 1 (O) : WIN !!");
    }

    @SuppressLint("SetTextI18n")
    private void showP2Win() {
        textScore.setText("!! PLAYER 2 (X) : WIN !!");
    }

    @SuppressLint("SetTextI18n")
    private void Draw() {
        textScore.setText("-- DRAW --");
        disableBut();
    }

    private  void EnableBut(){
        for (int i = 0; i < LV; i++){
            for(int j = 0; j < LV; j++){
                String buttonID = "button" + i + j ;
                int ID = getResources().getIdentifier(buttonID,"id",getPackageName());
                BT[i][j]= findViewById(ID);
                BT[i][j].setEnabled(true);
            }
        }
    }

    private void disableBut() {
        for (int i = 0; i < LV; i++) {
            for (int j = 0; j < LV; j++) {
                String buttonID = "button" + i + j;
                int ID = getResources().getIdentifier(buttonID, "id", getPackageName());
                BT[i][j] = findViewById(ID);
                BT[i][j].setEnabled(false);
            }
        }
    }

    private void  reset(){
        for (int i = 0; i < LV ; i++) {
            for (int j = 0; j < LV; j++){
                BT[i][j].setText("");
            }
        }
        textScore.setText("");
        move = 0;
        AllTurn = 0;
        P1turn = true;
    }

    private  void MoveHistory(){
        new MoveHis(this).execute();
    }
}