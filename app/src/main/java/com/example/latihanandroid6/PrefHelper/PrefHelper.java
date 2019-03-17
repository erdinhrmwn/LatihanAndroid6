package com.example.latihanandroid6.PrefHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PrefHelper {
    private static PrefHelper instance;
    SharedPreferences prefs;
    Context ctx;

    private PrefHelper(Context ctx) {
        this.ctx = ctx;
        this.prefs = ctx.getSharedPreferences("SAMPLE", Context.MODE_PRIVATE);
    }

    public static PrefHelper sharedInstance(Context context) {
        if (instance == null) {
            instance = new PrefHelper(context);
        }
        return instance;
    }

    public int getDataByEmail(String email) {
        UserHelper helper = new UserHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] data = {"_id", "username", "password"};
        Cursor c = db.query("users", data, "username=?",
                new String[]{String.valueOf(email)}, null, null, null);

        if (c == null) {
            return 0;
        }

        int user = c.getCount();
        c.close();
        return user;
    }

    public String getPasswordByEmail(String email) {
        UserHelper helper = new UserHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] data = {"_id", "username", "password"};
        Cursor c = db.query("users", data, "username=?",
                new String[]{String.valueOf(email)}, null, null, null);
        c.moveToFirst();

        if (c == null) {
            return null;
        }

        String password = c.getString(c.getColumnIndex("password"));
        c.close();
        return password;
    }

    public String getUsernameDefault() {
        return prefs.getString("username", "Master");
    }

    public void setUsernameDefault(String username) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.apply();
    }

    public boolean isLogin() {
        return prefs.getBoolean("login", false);
    }

    public void setLogin(boolean login) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("login", login);
        editor.apply();
    }
}
