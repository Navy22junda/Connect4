package com.example.connect4app.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.connect4app.R;

import java.time.Duration;
import java.time.Instant;

public class LogFrag extends Fragment {

    private TextView config;
    private TextView jugades;
    private LinearLayout linearLayout;
    private String alias;
    private int mida;
    private boolean time;
    private int beginTime = 50;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        config = (TextView) getView().findViewById(R.id.config);
        jugades = (TextView) getView().findViewById(R.id.jugades);
        linearLayout = (LinearLayout)getView().findViewById(R.id.linearLayout11);

        String temps = "Desactivat";
        if (time) {
            temps = "Activat";
        }

        config.setText("Alias: " + alias + " Mida Graella: " + mida + "\nControl temps: " + temps+"\n");

    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setMida(int mida) {
        this.mida = mida;
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    public void showLog(int posx, int posy, Instant start, Instant end, long elapsed) {
        TextView textView = new TextView(getContext());
        textView.setText("Casella ocupada:("+ posx +"," + posy +")\nTemps inici Tirada: "+ beginTime +";\n" +
                "Temps finalitzar tirada:" + Duration.between(start, end).getSeconds() + ";\nTemps restant:"+elapsed);
        textView.setTextSize(20);
        linearLayout.addView(textView);
        beginTime = (int) (elapsed);
    }
}
