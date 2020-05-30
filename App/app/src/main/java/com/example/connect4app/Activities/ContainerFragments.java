package com.example.connect4app.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;

public class ContainerFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Boolean check = intent.getBooleanExtra("actiu", false);
        String size = intent.getStringExtra("size");

        GameDevelopment.size = size;
        GameDevelopment.name = name;
        GameDevelopment.time = check;

        
        setContentView(R.layout.activity_containerfrag);
    }
}
