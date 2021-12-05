package com.slvtbn.tugaspraktikum7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_skincare";

    private static final String tb_skincare = "tb_skincare";
    private static final String tb_skincare_id = "id";
    private static final String tb_skincare_nama = "nama";
    private static final String tb_skincare_harga = "harga";
    private static final String CREATE_TABLE_SKINCARE = "CREATE TABLE " + tb_skincare +"(" + tb_skincare_id + " INTEGER PRIMARY KEY ," + tb_skincare_nama + " TEXT ," + tb_skincare_harga + " TEXT " + ")";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SKINCARE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateSkincare(Skincare data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_skincare_id, data.get_id());
        values.put(tb_skincare_nama, data.get_nama());
        values.put(tb_skincare_harga, data.get_harga());
        db.insert(tb_skincare, null, values);
        db.close();
    }

    public List<Skincare> ReadSkincare() {
        List<Skincare> listSkr = new ArrayList<Skincare>();
        String selectQuery = "SELECT * FROM " + tb_skincare;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Skincare data = new Skincare();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listSkr.add(data);
            }while(cursor.moveToNext());
        }

        db.close();
        return listSkr;
    }

    public int UpdateSkincare(Skincare data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_skincare_nama, data.get_nama());
        values.put(tb_skincare_harga, data.get_harga());

        return db.update(tb_skincare, values, tb_skincare_id + " = ?", new String[]{String.valueOf((data.get_id()))});
    }

    public void DeleteSkincare(Skincare data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_skincare, tb_skincare_id+ " = ?", new String[]{String.valueOf(data.get_id())});
        db.close();
    }

}
