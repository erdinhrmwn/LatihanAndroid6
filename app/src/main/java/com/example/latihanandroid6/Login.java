package com.example.latihanandroid6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latihanandroid6.PrefHelper.PrefHelper;

public class Login extends AppCompatActivity {
    EditText et_user, et_pass;
    TextView tv_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        et_user = findViewById(R.id.et_user_login);
        et_pass = findViewById(R.id.et_password_login);
        tv_register = findViewById(R.id.tv_register);
        Button btn = findViewById(R.id.btn_login);
        boolean login = PrefHelper.sharedInstance(getApplicationContext()).isLogin();

        if (login){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user = PrefHelper.sharedInstance(getApplicationContext()).getDataByEmail(et_user.getText().toString());
                String password = PrefHelper.sharedInstance(getApplicationContext()).getPasswordByEmail(et_user.getText().toString());
                if (et_user.getText().toString().equals("") || et_pass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Username atau password kosong", Toast.LENGTH_SHORT).show();
                } else {
                    if (user > 0 && et_pass.getText().toString().equals(password)) {
                        PrefHelper.sharedInstance(getApplicationContext()).setUsernameDefault(et_user.getText().toString());
                        PrefHelper.sharedInstance(getApplicationContext()).setLogin(true);
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
