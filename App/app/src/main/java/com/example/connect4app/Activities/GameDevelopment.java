package com.example.connect4app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.Connect4Logic.Board;
import com.example.connect4app.Connect4Logic.Cell;
import com.example.connect4app.Connect4Logic.Game;
import com.example.connect4app.Connect4Logic.Position;
import com.example.connect4app.R;

import java.util.concurrent.TimeUnit;

public class GameDevelopment extends AppCompatActivity implements GridView.OnItemClickListener{

    GridView gridView;
    Game game;
    static int sizef = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int sizeGrill;

        Intent intent = getIntent();
        String size = intent.getStringExtra("size");
        if(size.equals("5")){
            sizeGrill = 25;
        }else if(size.equals("6")){
            sizeGrill = 36;
        }else {
            sizeGrill = 49;
        }
        ImageAdapter.setData(sizeGrill);
        ImageAdapterInteractive.setData((Integer.parseInt(size)));
        sizef = (Integer.parseInt(size));

        //GridView de la parrilla
        gridView = (GridView)findViewById(R.id.grid);
        gridView.setNumColumns((Integer.parseInt(size)));
        gridView.setAdapter(new ImageAdapter(this));

        //GridView interactuable
        GridView interactive = (GridView)findViewById(R.id.interactiveGrid);
        interactive.setNumColumns((Integer.parseInt(size)));
        interactive.setAdapter(new ImageAdapterInteractive(this));

        //Inicialitzar celles
        Cell[][] cell = new Cell[Integer.parseInt(size)][Integer.parseInt(size)];
        for (int i = 0; i < (Integer.parseInt(size)); i++){
            for (int j = 0; j < (Integer.parseInt(size)); j++){
                cell[i][j] = new Cell(false, 0);
            }
        }

        game = new Game(new Board(Integer.parseInt(size), cell), (Integer.parseInt(size)), 100000, 0);
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
        Position pos = game.drop(position);
        if(game.checkForFinish()){
            Intent intent = new Intent(this, Results.class);
            startActivity(intent);
        }
        int index = calculatePos(pos.getRow(), pos.getColumn());
        View curentTile = gridView.getChildAt(index);
        curentTile.setBackgroundResource(R.drawable.fitxaroja);
        game.toggleTurn();

        //JUGADA MAQUINA
        pos = game.playOpponent();
        if(game.checkForFinish()){
            Intent intent = new Intent(this, Results.class);
            startActivity(intent);
        }
        index = calculatePos(pos.getRow(), pos.getColumn());
        curentTile = gridView.getChildAt(index);
        curentTile.setBackgroundResource(R.drawable.fitxagroga);
        game.toggleTurn();



    }

    private int calculatePos(int row, int column){
        int actualrow =  sizef - row;
        int actualcolumn = sizef - column;
        return (actualrow * sizef) - actualcolumn;
    }
}

