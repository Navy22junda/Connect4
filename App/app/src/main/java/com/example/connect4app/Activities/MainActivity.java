package com.example.connect4app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.connect4app.Activities.Game.ContainerFragments;
import com.example.connect4app.Activities.Help.Help;
import com.example.connect4app.Activities.SavedGames.GamesPlayed;
import com.example.connect4app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.buttonStart);
        Button help = findViewById(R.id.buttonHelp);
        Button game = findViewById(R.id.buttonGames);
        Button exit = findViewById(R.id.buttonExit);

        start.setOnClickListener(this);
        help.setOnClickListener(this);
        game.setOnClickListener(this);
        exit.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){

            case R.id.buttonStart:
                intent = new Intent(this, ContainerFragments.class);
                startActivity(intent);
                break;

            case R.id.buttonHelp:
                intent = new Intent(this, Help.class);
                startActivity(intent);
                break;

            case R.id.buttonGames:
                intent = new Intent(this, GamesPlayed.class);
                startActivity(intent);
                break;

            case R.id.buttonExit:
                this.finish();
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.configuration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.config) {
            Intent intent = new Intent(this, Preferences.class);
            startActivity(intent);
        }
        return true;
    }
}
