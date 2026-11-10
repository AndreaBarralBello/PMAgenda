package com.example.agenda.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AmigosHelper  extends SQLiteOpenHelper {

    //Constructor

    public AmigosHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override

    //Crea la tabla amigos clave (nombre) valor (teléfono)
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creaBBDD="CREATE TABLE amigos (id INTEGER PRIMARY KEY AUTOINCREMENT " +
                "NOT NULL, nombre TEXT,telefono TEXT)";
        sqLiteDatabase.execSQL(creaBBDD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
