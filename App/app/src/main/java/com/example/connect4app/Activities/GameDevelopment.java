package com.example.connect4app.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.connect4app.Connect4Logic.Board;
import com.example.connect4app.Connect4Logic.Cell;
import com.example.connect4app.Connect4Logic.Game;
import com.example.connect4app.Connect4Logic.Position;
import com.example.connect4app.R;

import java.time.Duration;
import java.time.Instant;

public class GameDevelopment extends Fragment implements GridView.OnItemClickListener{

    private GridView gridView;
    private Game game;
    private String size;
    private static int sizef = 0;
    private String name = "";
    static int temps = 50;
    private TextView textView;
    private Instant start = Instant.now();
    private boolean time;
    private Parcelable mGridview, mAdapterInstanceState;
    private int positionInteractive, currentPlay = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_game, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        int sizeGrill;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        size = preferences.getString("size", "5");
        name = preferences.getString("alias", "None");
        time = preferences.getBoolean("temps",false);


        if(time){
            LinearLayout linearLayout = (LinearLayout)getView().findViewById(R.id.linearLayout10);
            linearLayout.setVisibility(linearLayout.VISIBLE);

            textView = (TextView)getView().findViewById(R.id.time);
            textView.setText("Temps: " + temps);
        }

        if(size.equals("5")){
            sizeGrill = 25;
            positionInteractive = 150;
        }else if(size.equals("6")){
            sizeGrill = 36;
            positionInteractive = 50;
        }else {
            sizeGrill = 49;
            positionInteractive = -20;
        }
        //CAMBIO LA MIDA DE FITXES SI ES TABLET
        LogFrag frag = (LogFrag)getFragmentManager().findFragmentById(R.id.fragmentLog);
        if(frag != null && frag.isInLayout()){
            ImageAdapter.fitxaSize = 72;
            ImageAdapterInteractive.fitxaSize = 72;
            ImageAdapter.width = 60;

            //Passo dades al fragment
            frag.setAlias(name);
            frag.setMida(Integer.parseInt(size));
            frag.setTime(time);

            if(sizeGrill == 25) {
                ImageAdapterInteractive.width = 60;
            }else{
                ImageAdapterInteractive.width = 10;
            }
        }


        ImageAdapter.setData(sizeGrill);
        ImageAdapterInteractive.setData((Integer.parseInt(size)));
        sizef = (Integer.parseInt(size));


        if (savedInstanceState != null) {
            mGridview = savedInstanceState.getParcelable("gridview");
            gridView = (GridView)mGridview;

        }else{

            //Inicialitzar celles
            Cell[][] cell = new Cell[Integer.parseInt(size)][Integer.parseInt(size)];
            for (int i = 0; i < (Integer.parseInt(size)); i++){
                for (int j = 0; j < (Integer.parseInt(size)); j++){
                    cell[i][j] = new Cell(false, 0);
                }
            }

            game = new Game(new Board(Integer.parseInt(size), cell), (Integer.parseInt(size)), 100000, 0);

        }

        //GridView de la parrilla
        gridView = (GridView)getView().findViewById(R.id.grid);
        gridView.setNumColumns((Integer.parseInt(size)));
        gridView.setAdapter(new ImageAdapter(getActivity()));

        //GridView interactuable
        GridView interactive = (GridView)getView().findViewById(R.id.interactiveGrid);
        interactive.setNumColumns((Integer.parseInt(size)));
        interactive.setAdapter(new ImageAdapterInteractive(getActivity()));
        interactive.setY(positionInteractive);

        interactive.setOnItemClickListener(this);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if(requestCode == 1){
            getActivity().finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Position posPerson = null;
        Position posMachine = null;
        Instant end = Instant.now();
        if(currentPlay < (sizef*sizef)){
            currentPlay ++;
            posPerson = game.drop(position);
        }else{
            Intent intent = new Intent(getActivity(), Results.class);
            intent.putExtra("Guanyador", "Empat ningu guanya");
            int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
            intent.putExtra("Temps", finaltime);
            view.getContext().startActivity(intent);
        }
        int flag = 0;
        if(posPerson.getColumn() == -1 || posPerson.getRow() == -1){
            Toast.makeText(getActivity(), R.string.fullColumn, Toast.LENGTH_LONG).show();

        }else {

            if (game.checkForFinish()) {
                flag = 1;
                Intent intent = new Intent(getActivity(), Results.class);
                intent.putExtra("Guanyador", name);
                int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
                intent.putExtra("Temps", finaltime);
                view.getContext().startActivity(intent);
            }
            int index = calculatePos(posPerson.getRow(), posPerson.getColumn());
            View curentTile = gridView.getChildAt(index);
            curentTile.setBackgroundResource(R.drawable.fitxaroja);
            game.toggleTurn();

            //JUGADA MAQUINA
            if(currentPlay < (sizef*sizef)){
                currentPlay ++;
                posMachine = game.playOpponent();
            }else {
                Intent intent = new Intent(getActivity(), Results.class);
                intent.putExtra("Guanyador", "Empat ningu guanya");
                int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
                intent.putExtra("Temps", finaltime);
                view.getContext().startActivity(intent);
            }
            if (game.checkForFinish() && flag == 0) {
                Intent intent = new Intent(getActivity(), Results.class);
                intent.putExtra("Guanyador", "PLAYER2");
                int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
                intent.putExtra("Temps", finaltime);
                view.getContext().startActivity(intent);
            }
            Log.v("COLUMNA", "COL " + posMachine.getColumn() + " ROW" + posMachine.getRow());
            index = calculatePos(posMachine.getRow(), posMachine.getColumn());
            curentTile = gridView.getChildAt(index);
            curentTile.setBackgroundResource(R.drawable.fitxagroga);
            game.toggleTurn();

            if (time) {
                end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                textView.setText("Temps: " + (temps - timeElapsed.getSeconds()));
            }
        }
        LogFrag frag = (LogFrag)getFragmentManager().findFragmentById(R.id.fragmentLog);
        if(frag != null && frag.isInLayout()){
            Duration timeElapsed = Duration.between(start, end);
            frag.showLog(posPerson.getRow(), posPerson.getColumn(), start, end, temps - timeElapsed.getSeconds());
        }

    }

    private int calculatePos(int row, int column){
        int actualrow =  sizef - row;
        int actualcolumn = sizef - column;
        return (actualrow * sizef) - actualcolumn;
    }

}

