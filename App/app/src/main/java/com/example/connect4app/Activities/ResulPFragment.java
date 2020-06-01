package com.example.connect4app.Activities;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.connect4app.R;

public class ResulPFragment extends Fragment {

    private SQLiteDatabase database;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resulp, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView imageView = (ImageView) getView().findViewById(R.id.resultImage);

        Cursor cursor = database.rawQuery("Select * from Game", null);
        String result = cursor.getString( cursor.getColumnIndex("result") );


        switch (result){

            case "Winner":
                imageView.setImageResource(R.drawable.victoria);
                break;
            case "Looser":
                imageView.setImageResource(R.drawable.derrota);
                break;
            case "Draw":
                imageView.setImageResource(R.drawable.empate);
                break;
            case "OutTime":
                imageView.setImageResource(R.drawable.tiempoagotado);
                break;

        }



    }
}
