package com.example.connect4app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}
