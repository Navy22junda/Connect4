package com.example.connect4app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.Connect4Logic.Board;
import com.example.connect4app.Connect4Logic.Cell;
import com.example.connect4app.Connect4Logic.Game;
import com.example.connect4app.Connect4Logic.Position;
import com.example.connect4app.R;

import java.time.Duration;
import java.time.Instant;

public class GameDevelopment extends AppCompatActivity implements GridView.OnItemClickListener{

    private GridView gridView;
    private Game game;
    private static int sizef = 0;
    static String name = "";
    int temps = 50;
    private TextView textView;
    private Instant start = Instant.now();
    private boolean time;
    private Parcelable mGridview, mAdapterInstanceState;
    private int positionInteractive, currentPlay = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        int sizeGrill;

        Intent intent = getIntent();

        //REBO ALIAS
        name = intent.getStringExtra("name");

        //REBO si el temps activat
        time = intent.getBooleanExtra("actiu", false);
        if(time){
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout10);
            linearLayout.setVisibility(linearLayout.VISIBLE);

            textView = (TextView)findViewById(R.id.time);
            textView.setText("Temps: " + temps);
        }


        //REBO mida grill
        String size = intent.getStringExtra("size");
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
        gridView = (GridView)findViewById(R.id.grid);
        gridView.setNumColumns((Integer.parseInt(size)));
        gridView.setAdapter(new ImageAdapter(this));

        //GridView interactuable
        GridView interactive = (GridView)findViewById(R.id.interactiveGrid);
        interactive.setNumColumns((Integer.parseInt(size)));
        interactive.setAdapter(new ImageAdapterInteractive(this));
        interactive.setY(positionInteractive);

        interactive.setOnItemClickListener(this);


    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if(requestCode == 1){
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Position pos = null;
        Instant end = Instant.now();
        if(currentPlay < (sizef*sizef)){
            currentPlay ++;
            pos = game.drop(position);
        }else{
            Intent intent = new Intent(this, Results.class);
            intent.putExtra("Guanyador", "Empat ningu guanya");
            int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
            intent.putExtra("Temps", finaltime);
            startActivity(intent);
        }
        int flag = 0;
        if(pos.getColumn() == -1 || pos.getRow() == -1){
            Toast.makeText(this, R.string.fullColumn, Toast.LENGTH_LONG).show();

        }else {

            if (game.checkForFinish()) {
                flag = 1;
                Intent intent = new Intent(this, Results.class);
                intent.putExtra("Guanyador", name);
                int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
                intent.putExtra("Temps", finaltime);
                startActivity(intent);
            }
            int index = calculatePos(pos.getRow(), pos.getColumn());
            View curentTile = gridView.getChildAt(index);
            curentTile.setBackgroundResource(R.drawable.fitxaroja);
            game.toggleTurn();

            //JUGADA MAQUINA
            if(currentPlay < (sizef*sizef)){
                currentPlay ++;
                pos = game.playOpponent();
            }else {
                Intent intent = new Intent(this, Results.class);
                intent.putExtra("Guanyador", "Empat ningu guanya");
                int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
                intent.putExtra("Temps", finaltime);
                startActivity(intent);
            }
            if (game.checkForFinish() && flag == 0) {
                Intent intent = new Intent(this, Results.class);
                intent.putExtra("Guanyador", "PLAYER2");
                int finaltime = (int) (temps - Duration.between(start, end).getSeconds());
                intent.putExtra("Temps", finaltime);
                startActivity(intent);
            }
            Log.v("COLUMNA", "COL " + pos.getColumn() + " ROW" + pos.getRow());
            index = calculatePos(pos.getRow(), pos.getColumn());
            curentTile = gridView.getChildAt(index);
            curentTile.setBackgroundResource(R.drawable.fitxagroga);
            game.toggleTurn();

            if (time) {
                end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                textView.setText("Temps: " + (temps - timeElapsed.getSeconds()));
            }
        }

    }

    private int calculatePos(int row, int column){
        int actualrow =  sizef - row;
        int actualcolumn = sizef - column;
        return (actualrow * sizef) - actualcolumn;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putParcelable("gridview", gridView.onSaveInstanceState());
        //outState.putParcelableArray("adapter", ImageAdapter);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        gridView = savedInstanceState.getParcelable("gridview");
        mAdapterInstanceState = savedInstanceState.getParcelable("adapter");
        super.onRestoreInstanceState(savedInstanceState);
    }
}

