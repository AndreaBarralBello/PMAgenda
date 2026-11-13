package com.example.agenda.Amigos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.agenda.R;
import com.example.agenda.bbdd.AmigosHelper;

public class AmigosEditar extends AppCompatActivity {

    EditText etNombreEditar;
    EditText etTelefonoEditar;
    EditText etEmailEditar;
    Button btnEditar;
    Button btnVolver;
    EditText etInstagramEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_amigos_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etNombreEditar= findViewById(R.id.etNombreEditar);
        etEmailEditar= findViewById(R.id.etEmailEditar);
        etTelefonoEditar= findViewById(R.id.etTelefonoEditar);
        btnEditar= findViewById(R.id.btnEditar);
        etInstagramEditar = findViewById(R.id.etInstagramEditar);
        //btnVolver= findViewById(R.id.btnVolver);



        Intent intent = getIntent();
        String itemID = intent.getStringExtra("id");

        SQLiteDatabase db= new AmigosHelper(this, "amigos.db", null,2 ).getReadableDatabase();
        Cursor cursor= db.rawQuery
                        ("SELECT nombre, telefono, email, instagram FROM amigos WHERE id="+itemID, null);
        cursor.moveToNext();
        etNombreEditar.setText(cursor.getString(0));
        etTelefonoEditar.setText(cursor.getString(1));
        etEmailEditar.setText(cursor.getString(2));
        etInstagramEditar.setText(cursor.getString(3));



        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                SQLiteDatabase db= new AmigosHelper(getBaseContext(), "amigos.db", null, 2)
                .getWritableDatabase();
                ContentValues valores = new ContentValues();
                String[] filtro={itemID};
                valores.put("nombre", etNombreEditar.getText().toString());
                valores.put("telefono", etTelefonoEditar.getText().toString());
                valores.put("instagram", etInstagramEditar.getText().toString());

                db.update("amigos", valores, "id=?", filtro);
                db.close();


                Intent intent = new Intent(getBaseContext(), Amigos.class);
                startActivity(intent);
            }
        });

    }
}