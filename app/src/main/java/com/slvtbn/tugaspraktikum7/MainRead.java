package com.slvtbn.tugaspraktikum7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Skincare> ListSkincare = new ArrayList<Skincare>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);

        adapter_off = new CustomListAdapter(this, ListSkincare);

        mListView = (ListView) findViewById(R.id.list_skincare);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListSkincare.clear();

        List<Skincare> skincare = db.ReadSkincare();
        for (Skincare skr : skincare) {
            Skincare daftar = new Skincare();
            daftar.set_id(skr.get_id());
            daftar.set_nama(skr.get_nama());
            daftar.set_harga(skr.get_harga());
            ListSkincare.add(daftar);

            if((ListSkincare.isEmpty())) {
                Toast.makeText(MainRead.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
            }else{

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Skincare detailSkr = (Skincare) o;

        String Sid = detailSkr.get_id();
        String Snama = detailSkr.get_nama();
        String Sharga = detailSkr.get_harga();

        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        startActivity(goUpdel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListSkincare.clear();
        mListView.setAdapter(adapter_off);

        List<Skincare> skincare = db.ReadSkincare();
        for(Skincare skr : skincare) {
            Skincare daftar = new Skincare();
            daftar.set_id(skr.get_id());
            daftar.set_nama(skr.get_nama());
            daftar.set_harga(skr.get_harga());
            ListSkincare.add(daftar);

            if((ListSkincare.isEmpty())) {
                Toast.makeText(MainRead.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
            }else{

            }
        }
    }
}
