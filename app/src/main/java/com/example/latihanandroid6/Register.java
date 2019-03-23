package com.example.latihanandroid6;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.latihanandroid6.PrefHelper.UserHelper;

public class Register extends AppCompatActivity {
    EditText et_user, et_pass, et_repass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        et_user = findViewById(R.id.et_user_register);
        et_pass = findViewById(R.id.et_password_register);
        et_repass = findViewById(R.id.et_repassword_register);
        Button btn = findViewById(R.id.btn_register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_user.getText().toString().equals("") || et_pass.getText().toString().equals("") || et_repass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    String retype_password = et_pass.getText().toString();
                    if (!et_repass.getText().toString().equals(retype_password)) {
                        Toast.makeText(getApplicationContext(), "Please check your password.", Toast.LENGTH_SHORT).show();
                    } else {
                        UserHelper helper = new UserHelper(getApplicationContext());
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues cv = new ContentValues();

                        cv.put("username", et_user.getText().toString().trim());
                        cv.put("password", et_pass.getText().toString().trim());
                        db.insert("users", null, cv);
                        startActivity(new Intent(Register.this, Login.class));
                        finish();
                    }
                }
            }
        });
    }
}
