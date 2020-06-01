package com.example.connect4app.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.connect4app.R;
import com.example.connect4app.Sqlite.SqliteTable;

import java.util.ArrayList;

public class AccessFragment extends Fragment implements View.OnClickListener{


    private SQLiteDatabase db;
    SqliteTable usdbh =
            new SqliteTable(getContext(), "Game", null, 1);

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

        DetailFragment frag = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFrag);
        if(frag != null && !frag.isInLayout()){
            Intent intent = new Intent(getContext(), DetailFragment.class);
            startActivity(intent);
        }

        ListView listView = (ListView)getView().findViewById(R.id.games);
        listView.setAdapter(new infoGames());


        //BASE DADES SQLite
        SqliteTable usdbh =
                new SqliteTable(getContext(), "Game", null, 1);

        db = usdbh.getWritableDatabase();

        Button back = (Button) getView().findViewById(R.id.buttonback2);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }


    //ADAPTER DEL LIST VIEW
    private class infoGames extends BaseAdapter{

        @Override
        public int getCount() {
            return db.rawQuery("SELECT * from Game", null).getCount();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View item = inflater.inflate(R.layout.fragment_particulargame, null);

            //Cursor que fa iterar la taula de db
            Cursor cursor = db.rawQuery("SELECT * from Game", null);
            cursor.moveToPosition(position);

            TextView alias = (TextView) item.findViewById(R.id.Alias);
            alias.setText(cursor.getString(1));

            TextView date = (TextView) item.findViewById(R.id.Date);
            date.setText(cursor.getString(2));

            TextView size = (TextView) item.findViewById(R.id.Size);
            size.setText(cursor.getString(3));

            TextView flag = (TextView) item.findViewById(R.id.Flag);
            flag.setText(cursor.getString(4));

            TextView time = (TextView) item.findViewById(R.id.Time);
            time.setText(cursor.getString(5));

            TextView result = (TextView) item.findViewById(R.id.Result);
            result.setText(cursor.getString(6));

            return (item);
        }
    }
}
