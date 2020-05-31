package com.example.connect4app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("name", name);
        //editor.putBoolean("actiu", checked);
        editor.putString("size", selectedtext);
        editor.apply();
        //Agafar el botó premut i mirar-li el text que conte
        radioGroup = (RadioGroup)findViewById(R.id.radio);

        Button btnStart = (Button)findViewById(R.id.buttonStart);
        btnStart.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        EditText alias = (EditText)findViewById(R.id.alias);
        alias.setFocusable(false);
        String name = alias.getText().toString();




        if(!name.matches("")){

            int radioButtonId = radioGroup.getCheckedRadioButtonId();
            RadioButton premut = radioGroup.findViewById(radioButtonId);
            indexOfChildx = radioGroup.indexOfChild(premut);
            RadioButton r = (RadioButton) radioGroup.getChildAt(indexOfChildx);
            selectedtext = r.getText().toString();

            Intent intent = new Intent(this, ContainerFragments.class);
            intent.putExtra("size",selectedtext);

            //Passo el alias
            intent.putExtra("name", name);

            //Passo si el temps esta activat  o no
            CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
            boolean checked = checkBox.isChecked();
            intent.putExtra("actiu", checked);

            startActivity(intent);



        }else {
            Toast.makeText(this, R.string.emptyAlias, Toast.LENGTH_LONG).show();
            alias.setFocusable(true);

        }
    }
}
