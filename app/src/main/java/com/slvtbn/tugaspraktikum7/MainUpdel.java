package com.slvtbn.tugaspraktikum7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainUpdel extends AppCompatActivity {
    private MyDatabase db;
    private String Sid, Snama, Sharga;
    private EditText Enama, Eharga;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_updel);
        db = new MyDatabase(this);

        Intent i = this.getIntent();
        Sid = i.getStringExtra("Iid");
        Snama = i.getStringExtra("Inama");
        Sharga = i.getStringExtra("Iharga");

        Enama = (EditText) findViewById(R.id.updel_nama);
        Eharga = (EditText) findViewById(R.id.updel_harga);

        Enama.setText(Snama);
        Eharga.setText(Sharga);

        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Sharga = String.valueOf(Eharga.getText());
                if(Snama.equals("")) {
                    Enama.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan Isi Nama", Toast.LENGTH_SHORT).show();
                }else if(Sharga.equals("")) {
                    Eharga.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan Isi Harga", Toast.LENGTH_SHORT).show();
                }else{
                    db.UpdateSkincare(new Skincare(Sid, Snama, Sharga));
                    Toast.makeText(MainUpdel.this, "Data Telah Diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.DeleteSkincare(new Skincare(Sid, Snama, Sharga));
                Toast.makeText(MainUpdel.this, "Data Telah Dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}