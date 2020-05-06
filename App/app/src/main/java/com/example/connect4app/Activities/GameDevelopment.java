package com.example.connect4app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;

public class GameDevelopment extends AppCompatActivity implements View.OnClickListener, GridView.OnItemClickListener{
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

        Button button = (Button)findViewById(R.id.temporalButton);
        button.setOnClickListener(this);

        GridView gridView = (GridView)findViewById(R.id.grid);
        gridView.setNumColumns((Integer.parseInt(size)));
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(this, Results.class);
        startActivity(in);
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
        Intent in = new Intent(this, Results.class);
        startActivity(in);
    }
}

