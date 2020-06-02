package com.example.connect4app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;
import com.example.connect4app.Sqlite.SqliteTable;

import javax.xml.datatype.Duration;

public class Results extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText logMessage;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button btnEnviar = (Button)findViewById(R.id.buttonEnviar);
        Button btnNovaPartida = (Button) findViewById(R.id.buttonNovaPartida);
        Button btnSortir = (Button)findViewById(R.id.buttonSortir);

        btnEnviar.setOnClickListener(this);
        btnNovaPartida.setOnClickListener(this);
        btnSortir.setOnClickListener(this);

        email = (EditText)findViewById(R.id.emailEdit);
        logMessage = (EditText)findViewById(R.id.logEdit);

        Intent intent = getIntent();
        String player = intent.getStringExtra("Winner");
        int temps = intent.getIntExtra("Time",0);
        logMessage.setText("Alias: "+ player +" Time left "+ temps + " seconds");
        email.setText("example@email.com");

        SqliteTable usdbh =
                new SqliteTable(this, "Game", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        // Com fer un create i insertar al sqlite usdbh.onCreate(db);

    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){
            case R.id.buttonEnviar:

                email.setFocusable(false);
                logMessage.setFocusable(false);
                Editable address = email.getText();
                Editable log = logMessage.getText();

                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { address.toString() });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Game Results");
                intent.putExtra(Intent.EXTRA_TEXT, log);
                startActivity(intent);
                break;

            case R.id.buttonSortir:
                //Borrar el backstack i sortir de l'app
                finishAffinity();
                System.exit(0);

            case R.id.buttonNovaPartida:
                //Borro el backstack i vaig a l'activity Configuration
                intent = new Intent(this, ContainerFragments.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Results.this.finish();

        }
    }
}
