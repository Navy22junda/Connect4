package com.example.connect4app.Activities.SavedGames;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.connect4app.R;
import com.example.connect4app.Sqlite.SqliteTable;

public class DetailFragment extends Fragment {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.regfrag, container, false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void showContent(int position){

        SqliteTable db = SqliteTable.initialize(getContext());
        Cursor cursor = db.getDataFromDB();
        cursor.moveToPosition(position);

        TextView alias = (TextView) getView().findViewById(R.id.aliasPlayer);
        TextView date = (TextView) getView().findViewById(R.id.dateGame);
        TextView size = (TextView) getView().findViewById(R.id.sizeGrill);
        TextView timeFlag = (TextView) getView().findViewById(R.id.flagTime);
        TextView remaining = (TextView) getView().findViewById(R.id.remainingTime);

        alias.setText(cursor.getString(1));
        date.setText(cursor.getString(2));
        size.setText(cursor.getString(3));
        timeFlag.setText(cursor.getString(4));
        remaining.setText("Temps restant: " + cursor.getString(5));



    }
}
