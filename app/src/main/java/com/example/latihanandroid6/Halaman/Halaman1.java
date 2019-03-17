package com.example.latihanandroid6.Halaman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.latihanandroid6.R;

public class Halaman1 extends Fragment {

    /**
     * method untuk membuat/inisialisasi fragmet
     *
     * @param idx
     * @return
     */

    public static Halaman1 newInstance(int idx) {
        Halaman1 fragment = new Halaman1();
        Bundle b = new Bundle();
        b.putInt("index", idx);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.city_list, container, false);

        return v;
    }
}
