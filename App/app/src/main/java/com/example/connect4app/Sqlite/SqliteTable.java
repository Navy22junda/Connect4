package com.example.connect4app.Sqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.connect4app.Activities.GameDevelopment;
import com.example.connect4app.Connect4Logic.Game;

import java.time.LocalDate;

public class SqliteTable extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Game4" +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " alias TEXT, " +
            " date INTEGER, " +
            " size INTEGER, " +
            " flag NUMERIC, " +
            " time INTEGER, " +
            " result TEXT)";



    public SqliteTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);

        String alias = GameDevelopment.name;
        int size = GameDevelopment.size;
        Boolean flag = GameDevelopment.time;
        LocalDate date = LocalDate.now();
        int time = GameDevelopment.temps;
        String result = "NONE";


        db.execSQL("INSERT INTO Game (alias, date, size, flag, time , result) " +
                    "VALUES ('" + alias + "','" + date + "','" + size + "','" + flag + "','" + time + "','" + result + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlCreate);
    }
}
