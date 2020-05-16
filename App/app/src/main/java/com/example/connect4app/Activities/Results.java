package com.example.connect4app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;

public class Results extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText logMessage;

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
        String player = intent.getStringExtra("Guanyador");
        int temps = intent.getIntExtra("Temps",0);
        logMessage.setText("Alias: "+ player +" Han sobrat "+ temps + " segons");
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){
            case R.id.buttonEnviar:

                Editable address = email.getText();
                Editable log = logMessage.getText();

                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, address);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Game Results");
                intent.putExtra(Intent.EXTRA_TEXT, log);
                startActivity(intent);
                break;

            case R.id.buttonExit:
                finish();
                break;

            case R.id.buttonNovaPartida:
                finishActivity(1);
                finish();

        }
    }
}
