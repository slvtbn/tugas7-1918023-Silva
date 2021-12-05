package com.slvtbn.tugaspraktikum7;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainCreate extends AppCompatActivity {
    private MyDatabase db;
    private EditText Enama, Eharga;
    private String Snama, Sharga;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        db = new MyDatabase(this);

        Enama = (EditText) findViewById(R.id.create_nama);
        Eharga = (EditText) findViewById(R.id.create_harga);

        Button btnCreate = (Button) findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Sharga = String.valueOf(Eharga.getText());

                if(Snama.equals("")) {
                    Enama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan Isi Nama", Toast.LENGTH_SHORT).show();
                }else if(Sharga.equals("")) {
                    Eharga.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan Isi Harga", Toast.LENGTH_SHORT).show();
                }else {
                    Enama.setText("");
                    Eharga.setText("");
                    Toast.makeText(MainCreate.this, "Data Telah Ditambah", Toast.LENGTH_SHORT).show();
                    db.CreateSkincare(new Skincare(null, Snama, Sharga));

                    Intent a = new Intent(MainCreate.this, MainActivity.class);
                    startActivity(a);
                }
            }
        });
    }
}
