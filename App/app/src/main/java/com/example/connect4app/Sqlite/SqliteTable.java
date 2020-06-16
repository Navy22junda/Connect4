package com.example.connect4app.Sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;


import androidx.annotation.RequiresApi;


public class SqliteTable extends SQLiteOpenHelper {

    private static String sqlCreate = "CREATE TABLE Game" +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " alias TEXT, " +
            " date INTEGER, " +
            " size INTEGER, " +
            " flag NUMERIC, " +
            " time INTEGER, " +
            " result TEXT)";

    private static SqliteTable sqLiteRecord;


    public SqliteTable(Context context) {
        super(context, sqlCreate, null, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {


        /*db.execSQL(sqlCreate);

        String alias = GameDevelopment.name;
        int size = GameDevelopment.size;
        Boolean flag = GameDevelopment.time;
        LocalDate date = LocalDate.now();
        int time = GameDevelopment.temps;
        String result = "NONE";


        db.execSQL("INSERT INTO Game (alias, date, size, flag, time , result) " +
                    "VALUES ('" + alias + "','" + date + "','" + size + "','" + flag + "','" + time + "','" + result + "')");

         */
        db.execSQL(sqlCreate);
    }


    public Cursor getDataFromDB() {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(("SELECT * from Game ORDER BY _id DESC"), null);
    }

    public static SqliteTable initialize(Context context) {
        if (sqLiteRecord == null) {
            sqLiteRecord = new SqliteTable(context);
        }
        return sqLiteRecord;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlCreate);
    }

    public class InfoGame {
        public static final String Alias = "alias";
        public static final String Date = "date";
        public static final String Size = "size";
        public static final String Flag = "flag";
        public static final String Time = "time";
        public static final String Result = "result";
    }
}
