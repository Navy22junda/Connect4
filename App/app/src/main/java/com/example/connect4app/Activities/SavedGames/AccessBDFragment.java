package com.example.connect4app.Activities.SavedGames;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.connect4app.Activities.MainActivity;
import com.example.connect4app.R;
import com.example.connect4app.Sqlite.SqliteTable;

public class AccessBDFragment extends Fragment implements View.OnClickListener{


    private SQLiteDatabase db;
    private selectInfoListener listener;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.queryfrag, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //BASE DADES SQLite
        SqliteTable usdbh = SqliteTable.initialize(getContext());
        final DetailFragment frag = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFrag);

        ListView listView = (ListView)getView().findViewById(R.id.games);
        listView.setAdapter(new infoGames(this, usdbh));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                DetailFragment frag = (DetailFragment) getFragmentManager().findFragmentById(R.id.regFrag);
                if (frag != null && frag.isInLayout()) {
                    Log.v("BEEEEEEEEE", "fins aqui OK");
                    frag.showContent(pos);
                }else{
                    Log.v("MAAAAAAAAL", "entra else");
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("pos", pos);
                    startActivity(intent);
                }
            }
        });

        Button back = (Button) getView().findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    public interface selectInfoListener {
        void onGameSelected(int pos);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }


    //ADAPTER DEL LIST VIEW
    private class infoGames extends BaseAdapter{

        Activity context;
        SqliteTable db;

        private infoGames(AccessBDFragment listFragment, SqliteTable database) {
            this.context = listFragment.getActivity();
            this.db = database;
        }

        @Override
        public int getCount() {
            return db.getDataFromDB().getCount();
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
            Cursor cursor = db.getDataFromDB();
            cursor.moveToPosition(position);

            TextView alias = (TextView) item.findViewById(R.id.Alias);
            alias.setText(cursor.getString(1));

            TextView date = (TextView) item.findViewById(R.id.Date);
            date.setText(cursor.getString(2));

            TextView result = (TextView) item.findViewById(R.id.Result);
            result.setText(cursor.getString(6));

            return (item);
        }
    }
}
