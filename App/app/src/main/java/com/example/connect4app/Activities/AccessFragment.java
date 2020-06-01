package com.example.connect4app.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.connect4app.R;
import com.example.connect4app.Sqlite.SqliteTable;

import java.util.ArrayList;

public class AccessFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_access, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //BASE DADES SQLite
        SqliteTable sqliteTable = new SqliteTable(getContext(), "Game", null, 1);
        ArrayList<String> arrTblNames = new ArrayList<String>();
        //Cursor c = _db.rawQuery("SELECT name FROM Game WHERE type='table'", null);

        /*if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                arrTblNames.add( c.getString( c.getColumnIndex("name")) );
                c.moveToNext();
            }
        }*/

        Button back = (Button) getView().findViewById(R.id.buttonback2);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}
