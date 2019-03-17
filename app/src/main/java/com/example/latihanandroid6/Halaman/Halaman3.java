package com.example.latihanandroid6.Halaman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.latihanandroid6.Login;
import com.example.latihanandroid6.MainActivity;
import com.example.latihanandroid6.PrefHelper.PrefHelper;
import com.example.latihanandroid6.R;

public class Halaman3 extends Fragment {

    /**
     * method untuk membuat/inisialisasi fragmet
     *
     * @param idx
     * @return
     */

    public static Halaman3 newInstance(int idx) {
        Halaman3 fragment = new Halaman3();
        Bundle b = new Bundle();
        b.putInt("index", idx);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile, container, false);
        TextView username = v.findViewById(R.id.user_profile);
        TextView logout = v.findViewById(R.id.logout);

        username.setText(PrefHelper.sharedInstance(getContext()).getUsernameDefault());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefHelper.sharedInstance(getContext()).setLogin(true);
                startActivity(new Intent(getContext(), Login.class));
            }
        });
        return v;
    }
}
