package com.example.connect4app.Activities.Help;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;

public class Help extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Button buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
