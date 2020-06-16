package com.example.connect4app.Activities.Game;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;

public class ContainerFragments extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            getSupportFragmentManager().getFragment(savedInstanceState, "GameDevelopment");
        }
        
        setContentView(R.layout.activity_containerfrag);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
