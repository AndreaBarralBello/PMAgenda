package com.example.agenda.Amigos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.Amigo;
import com.example.agenda.R;
import com.example.agenda.bbdd.AmigosHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Amigos extends AppCompatActivity {

    static  RecyclerView rvAmigos;
    static List<Amigo> listaAmigos = new ArrayList<>();
    FloatingActionButton fabAnadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_amigos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvAmigos = findViewById(R.id.rvAmigos);
        fabAnadir = findViewById(R.id.fabAnadir);

        SQLiteDatabase db = new AmigosHelper(this, "amigos.db", null, 1).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM amigos ORDER BY nombre", null);

        cargaDatosSQLite(this);

        rvAmigos.setLayoutManager(new LinearLayoutManager(this));
        AmigosAdaptador amigosAdaptador = new AmigosAdaptador(listaAmigos);
        rvAmigos.setAdapter(amigosAdaptador);

        fabAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AmigosNuevo.class);
                startActivity(intent);
            }

        });
    }

    public static void cargaDatosSQLite(Context context) {

        listaAmigos.clear();

        SQLiteDatabase db = new AmigosHelper(context,"amigos.db",null,1).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM amigos ORDER BY nombre",null);

        while(cursor.moveToNext()){
            String id=cursor.getString(0);
            String nombre=cursor.getString(1);
            String telefono=cursor.getString(2);
            String email=cursor.getString(3);

            listaAmigos.add(new Amigo(id,nombre,telefono,email));
        }


        cursor.close();
        db.close();

        rvAmigos.getAdapter().notifyDataSetChanged();
        Log.e("Andrea",  "Recargando datos");
    }

    @Override
    protected  void onResume(){

        super.onResume();
        cargaDatosSQLite(this);
        Log.d("Andrea", "Recargando datos");
    }
}
