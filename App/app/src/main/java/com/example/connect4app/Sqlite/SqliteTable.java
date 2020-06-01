package com.example.connect4app.Sqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

public class SqliteTable extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Game" +
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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

       db.execSQL("INSERT INTO Game (alias, date, size, flag, time , result) " +
                    "VALUES ('" + alias + "','" + date + "','" + size + "','" + flag + "','" + time + "','" + result + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlCreate);
    }
}
