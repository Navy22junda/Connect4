package com.example.connect4app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4app.R;

public class Configuration extends AppCompatActivity implements View.OnClickListener {

    String selectedtext;
    RadioGroup radioGroup;
    int indexOfChildx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //Agafar el botó premut i mirar-li el text que conte
        radioGroup = (RadioGroup)findViewById(R.id.radio);

        Button btnStart = (Button)findViewById(R.id.buttonStart);
        btnStart.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton premut = radioGroup.findViewById(radioButtonId);
        indexOfChildx = radioGroup.indexOfChild(premut);
        RadioButton r = (RadioButton) radioGroup.getChildAt(indexOfChildx);
        selectedtext = r.getText().toString();

        Intent intent = new Intent(this, GameDevelopment.class);
        intent.putExtra("size",selectedtext);
        startActivity(intent);
    }
}
