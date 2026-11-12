package com.example.agenda.Amigos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.agenda.R;
import com.example.agenda.bbdd.AmigosHelper;

public class AmigosNuevo extends AppCompatActivity {

    EditText etNombre;
    EditText etTelefono;
    EditText etEmail;
    Button btnAnadir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_amigos_nuevo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        btnAnadir = findViewById(R.id.btnAnadir);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anadirAmigo();
            }
        });

    }

    private void anadirAmigo() {
        boolean anadir = true;

        if (etNombre.getText().toString().isEmpty()) {
            anadir = false;
            Toast.makeText(getBaseContext(), "Tienes que poner un NOMBRE", Toast.LENGTH_LONG).show();
        }
        if (etTelefono.getText().toString().isEmpty()) {
            anadir = false;
            Toast.makeText(getBaseContext(), "Tienes que poner un TELEFONO", Toast.LENGTH_LONG).show();
        }
        if (etEmail.getText().toString().isEmpty()) {
            anadir = false;
            Toast.makeText(getBaseContext(), "Tienes que poner un EMAIL", Toast.LENGTH_LONG).show();
        }
        if (anadir) {
            SQLiteDatabase db= new AmigosHelper(getBaseContext(), "amigos.db", null, 1).getWritableDatabase();
            ContentValues valores = new ContentValues();

            valores.put("nombre", etNombre.getText().toString());
            valores.put("telefono", etTelefono.getText().toString());
            valores.put("email", etEmail.getText().toString());

            db.insert("amigos", null, valores);
            db.close();

            Intent intent = new Intent(getBaseContext(), Amigos.class);
            startActivity(intent);
        }
    }
}

