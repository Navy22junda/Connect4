package com.example.connect4app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.connect4app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.buttonStart);
        Button help = findViewById(R.id.buttonHelp);
        Button exit = findViewById(R.id.buttonExit);

        start.setOnClickListener(this);
        help.setOnClickListener(this);
        exit.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){

            case R.id.buttonStart:
                intent = new Intent(this, Configuration.class);
                startActivity(intent);
                break;

            case R.id.buttonHelp:
                intent = new Intent(this, Help.class);
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
            //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            //SharedPreferences.Editor editor = sharedPreferences.edit();
            //editor.putString("name", name);
            //editor.putBoolean("actiu", checked);
            //editor.putString("size", selectedtext);
            //editor.apply();
            startActivity(intent);
        }
        return true;
    }
}
