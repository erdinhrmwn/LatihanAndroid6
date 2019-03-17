package com.example.latihanandroid6.Halaman;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.latihanandroid6.AddBook;
import com.example.latihanandroid6.PrefHelper.PrefHelper;
import com.example.latihanandroid6.PrefHelper.UserHelper;
import com.example.latihanandroid6.R;

public class Halaman2 extends Fragment {

    /**
     * method untuk membuat/inisialisasi fragmet
     *
     * @param idx
     * @return
     */

    public static Halaman2 newInstance(int idx) {
        Halaman2 fragment = new Halaman2();
        Bundle b = new Bundle();
        b.putInt("index", idx);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_list, container, false);
        FloatingActionButton fab = v.findViewById(R.id.fab);
        ListView lv = v.findViewById(R.id.book_list);

        UserHelper helper = new UserHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] datax = {"_id", "title", "author"};
        Cursor c = db.query("books", datax,
                null,
                null,
                null,
                null,
                null);
        SimpleCursorAdapter sca = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, c,
                new String[]{"title", "author"},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(sca);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddBook.class));
            }
        });
        registerForContextMenu(v);
        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = new MenuInflater(getContext());
        menuInflater.inflate(R.menu.book_menu, menu);
    }

    // membuat method delete buku
    public void DeleteBooks(long id) {
        SimpleCursorAdapter sca = null;
        UserHelper helper = new UserHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("books", "_id=?",
                new String[]{String.valueOf(id)});
        Toast.makeText(getContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

        Cursor x = db.query("books",
                new String[]{"_id", "title", "author"},
                null,
                null,
                null,
                null,
                null);
        sca.changeCursor(x);
        sca.notifyDataSetChanged();
    }

    public void UpdateBooks(long id) {
        UserHelper helper = new UserHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor e = db.query("books",
                new String[]{"title", "author"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        e.moveToFirst();
        Intent i = new Intent(getContext(), AddBook.class);
        i.putExtra("_id", id);
        i.putExtra("title", e.getString(e.getColumnIndex("title")));
        i.putExtra("author", e.getString(e.getColumnIndex("author")));
        startActivity(i);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete: {
                DeleteBooks(info.id);
            }
            break;
            case R.id.update: {
                UpdateBooks(info.id);
            }
            break;
            default: {
                //
            }
        }
        return true;
    }
}
