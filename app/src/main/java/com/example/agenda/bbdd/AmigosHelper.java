package com.example.agenda.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


//SQLiteOpenHelper no es la BD, es una clase auxiliar que te permite acceder a la BD

//la BD es amigos.db
//las consultas son sobre SQLiteOpenHelper, no sobre el helper
public class AmigosHelper extends SQLiteOpenHelper {

    //En android podemos guardar datos en un archivo .db que es una base de datos SQLite relacional
    //esta API ejecuta por debajo SQL, que vive en el almacenamiento del móvil
    //y se crea la 1º vez que la app lo pide


    public AmigosHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //onCreate se ejecuta solo la primera vez
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creaBBDD="CREATE TABLE amigos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT,telefono TEXT,email TEXT)";
        sqLiteDatabase.execSQL(creaBBDD);
        String sql = "INSERT INTO amigos values (null, 'Tao','614561654','tao@tao.es')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if(newVersion > oldVersion){
    //Añadimos una nueva tabla instagram
            String nuevaBBDD= "ALTER TABLE amigos ADD instagram TEXT";
            sqLiteDatabase.execSQL(nuevaBBDD);

        }

    }
}
