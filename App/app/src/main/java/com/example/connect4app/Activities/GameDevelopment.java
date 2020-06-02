package com.example.connect4app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.connect4app.Connect4Logic.Board;
import com.example.connect4app.Connect4Logic.Cell;
import com.example.connect4app.Connect4Logic.Game;
import com.example.connect4app.Connect4Logic.Position;
import com.example.connect4app.R;

import java.time.Duration;
import java.time.Instant;

@RequiresApi(api = Build.VERSION_CODES.O)
public class GameDevelopment extends Fragment implements GridView.OnItemClickListener {

    static GridView gridView;
    private Game game;
    private TextView textView;
    private Instant start = Instant.now();
    private int currentPlay = 0;

    public static int size;
    public static String name = "";
    public static int temps = 50;
    public static boolean time;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_game, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        int sizeGrill;

        //Trobar elements de la layout
        LinearLayout layoutTemps = getView().findViewById(R.id.linearLayout10);
        gridView = getView().findViewById(R.id.grid);
        GridView interactive = getView().findViewById(R.id.interactiveGrid);

        //Agafo valors de sharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        size = Integer.parseInt(preferences.getString("size", "5"));
        name = preferences.getString("alias", "None");
        time = preferences.getBoolean("temps", false);

        //Creació de TextView del Temps
        if (time) {
            layoutTemps.setVisibility(View.VISIBLE);
            textView = getView().findViewById(R.id.time);
            textView.setText("Temps: " + temps);
        }

        //Decidir mida
        int positionInteractive;
        switch (size) {
            case 5:
                sizeGrill = 25;
                positionInteractive = 150;
                break;
            case 6:
                sizeGrill = 36;
                positionInteractive = 50;
                break;
            case 7:
                sizeGrill = 49;
                positionInteractive = -20;
                break;
            default:
                throw new NullPointerException();
        }

        //Ajustament de mides de les fitxes (tablet)
        LogFrag frag = (LogFrag) getFragmentManager().findFragmentById(R.id.fragmentLog);
        if (frag != null && frag.isInLayout()) {
            ImageAdapter.fitxaSize = 72;
            ImageAdapterInteractive.fitxaSize = 72;
            ImageAdapter.width = 60;

            //Passo dades al fragment
            frag.setAlias(name);
            frag.setMida(size);
            frag.setTime(time);

            if (sizeGrill == 25) {
                ImageAdapterInteractive.width = 60;
            } else {
                ImageAdapterInteractive.width = 10;
            }
        }


        ImageAdapter.setData(sizeGrill);
        ImageAdapterInteractive.setData(size);


        if (savedInstanceState != null) {
            Parcelable mGridview = savedInstanceState.getParcelable("gridview");
            gridView = (GridView) mGridview;

        } else {

            //Inicialitzar celles
            Cell[][] cell = new Cell[size][size];
            for (int i = 0; i < (size); i++) {
                for (int j = 0; j < (size); j++) {
                    cell[i][j] = new Cell(false, 0);
                }
            }

            game = new Game(new Board(size, cell), size, 100000, 0);

        }

        //GridView de la parrilla
        gridView.setNumColumns(size);
        gridView.setAdapter(new ImageAdapter(getActivity()));

        //GridView interactuable
        interactive.setNumColumns(size);
        interactive.setAdapter(new ImageAdapterInteractive(getActivity()));
        interactive.setY(positionInteractive);
        interactive.setOnItemClickListener(this);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if (requestCode == 1) {
            getActivity().finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Position posPerson = null, posMachine = null;
        int flag = 0;
        int sizeGrill = (size * size);

        if (currentPlay < sizeGrill) {
            currentPlay++;
            posPerson = game.drop(position);

        } else {

            Intent intent = new Intent(getActivity(), Results.class);
            intent.putExtra("Winner", "Draw nobody wins");
            int finaltime = (int) (temps - Duration.between(start, Instant.now()).getSeconds());
            intent.putExtra("Time", finaltime);
            view.getContext().startActivity(intent);
        }

        if (posPerson.getColumn() != -1 && posPerson.getRow() != -1) {

            if (game.checkForFinish()) {
                flag = 1;
                gameFinished();
            }

            paintTile(posPerson, true);
            game.toggleTurn();

            //Jugada màquina
            if (currentPlay < sizeGrill) {
                currentPlay++;
                posMachine = game.playOpponent();
            } else {
                Intent intent = new Intent(getActivity(), Results.class);
                intent.putExtra("Winner", "Draw nobody wins");
                int finaltime = (int) (temps - Duration.between(start, Instant.now()).getSeconds());
                intent.putExtra("Time", finaltime);
                view.getContext().startActivity(intent);
            }
            if (game.checkForFinish() && flag == 0) {
                name = "Player2";
                gameFinished();
            }

            paintTile(posMachine, false);
            game.toggleTurn();

            if (time) {
                Duration timeElapsed = Duration.between(start, Instant.now());
                textView.setText("Temps: " + (temps - timeElapsed.getSeconds()));
            }

        }else {
            Toast.makeText(getActivity(), R.string.fullColumn, Toast.LENGTH_LONG).show();
        }

        sendLogToFragment(posPerson);

    }

    private int calculatePos(int row, int column) {
        int actualrow = size - row;
        int actualcolumn = size - column;
        return (actualrow * size) - actualcolumn;
    }

    private void gameFinished(){
        Intent intent = new Intent(getActivity(), Results.class);
        intent.putExtra("Winner", name);
        int finaltime = (int) (temps - Duration.between(start, Instant.now()).getSeconds());
        intent.putExtra("Time", finaltime);
        getContext().startActivity(intent);
    }

    private void sendLogToFragment(Position position){
        LogFrag frag = (LogFrag) getFragmentManager().findFragmentById(R.id.fragmentLog);
        if (frag != null && frag.isInLayout()) {
            Duration timeElapsed = Duration.between(start, Instant.now());
            frag.showLog(position.getRow(), position.getColumn(), start, Instant.now(), temps - timeElapsed.getSeconds());
        }
    }

    private void paintTile(Position position, boolean flag){

        int index = calculatePos(position.getRow(), position.getColumn());
        View curentTile = gridView.getChildAt(index);
        if(flag){
            curentTile.setBackgroundResource(R.drawable.fitxaroja);
        }else {
            curentTile.setBackgroundResource(R.drawable.fitxagroga);
        }
    }

}

