package com.example.latihanandroid6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.latihanandroid6.PrefHelper.PrefHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean login = PrefHelper.sharedInstance(getApplicationContext()).isLogin();

        if (!login){
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                PrefHelper.sharedInstance(getApplicationContext()).setLogin(false);
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
            default: {
                //
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
