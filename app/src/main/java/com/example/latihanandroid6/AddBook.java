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

public class AddBook extends AppCompatActivity {

    EditText et_title, et_author, et_review, et_content;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);
        et_title = findViewById(R.id.et_book_title);
        et_author = findViewById(R.id.et_book_author);
        et_review = findViewById(R.id.et_book_review);
        et_content = findViewById(R.id.et_book_content);
        btn = findViewById(R.id.btn_add_book);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHelper helper = new UserHelper(getApplicationContext());
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                String title = et_title.getText().toString().trim();
                String author = et_author.getText().toString().trim();
                String review = et_review.getText().toString().trim();
                String content = et_content.getText().toString().trim();

                if (title == null || title.equals("")
                        || review == null || review.equals("")
                        || author == null || author.equals("")
                        || content == null || content.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all data", Toast.LENGTH_SHORT).show();
                } else {
                    cv.put("title", title);
                    cv.put("author", author);
                    cv.put("review", review);
                    cv.put("content", content);
                    db.insert("books", null, cv);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }
}
