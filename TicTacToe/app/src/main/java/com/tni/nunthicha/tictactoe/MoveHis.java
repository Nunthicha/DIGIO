package com.tni.nunthicha.tictactoe;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class MoveHis extends AsyncTask<Void, Integer, String> {
    private WeakReference<Grid> main;
    MoveHis(Grid tv) { main = new WeakReference<>(tv);}

    @Override
    protected String doInBackground(Void... voids) {

        for (int i = 0 ; i < 100 ; i++) {
            try {
                Thread.sleep(350);
                if ( main.get().MH[i] == 0)
                    return "";
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    @Override
    protected void onProgressUpdate(Integer... i) {
        super.onProgressUpdate(i);
        main.get().Playgame(main.get().MH[i[0]]);
    }
}

