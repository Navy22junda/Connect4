package com.example.connect4app.Activities.SavedGames;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;

import static com.example.connect4app.R.id.regFrag;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(regFrag);

        Intent intent = getIntent();
        int position = intent.getIntExtra("pos",0);

        detailFragment.showContent(position);



    }
}