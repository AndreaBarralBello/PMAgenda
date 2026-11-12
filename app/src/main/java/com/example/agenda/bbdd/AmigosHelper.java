package com.example.agenda.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AmigosHelper extends SQLiteOpenHelper {


    public AmigosHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creaBBDD="CREATE TABLE amigos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT,telefono TEXT,email TEXT)";
        sqLiteDatabase.execSQL(creaBBDD);
        String sql = "INSERT INTO amigos values (null, 'Tao','614561654','tao@tao.es')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
