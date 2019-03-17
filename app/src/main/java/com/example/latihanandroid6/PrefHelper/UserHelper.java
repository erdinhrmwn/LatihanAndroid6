package com.example.latihanandroid6.PrefHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserHelper extends SQLiteOpenHelper {
    final static String DBNAME = "database.db";
    final static int DBVERSION = 1;

    public UserHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String bookCreate = "CREATE TABLE books(_id integer primary key autoincrement," +
                "title text," +
                "author text," +
                "content text);";
        String userCreate = "CREATE TABLE users(_id integer primary key autoincrement," +
                "username text," +
                "password text);";
        db.execSQL(bookCreate);
        db.execSQL(userCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String userDrop = "DROP TABLE IF EXISTS users";
        String bookDrop = "DROP TABLE IF EXISTS books";
        db.execSQL(userDrop);
        db.execSQL(bookDrop);
        onCreate(db);
    }
}
