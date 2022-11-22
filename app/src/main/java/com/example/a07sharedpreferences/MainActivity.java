package com.example.a07sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnGuardar;
    private Button btnBorrar;
    private ImageButton btnBorrarNombre;
    private ImageButton btnBorrarEdad;


    private SharedPreferences sharedPreferencesPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuardar= findViewById(R.id.btnGuardar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnBorrarEdad= findViewById(R.id.btnEliminaEdad);
        btnBorrarNombre = findViewById(R.id.btnEliminaNombre);


        //La inicializo:
        sharedPreferencesPersona = getSharedPreferences(Constantes.PERSONAS, MODE_PRIVATE);

        cargaDatos();


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                int edad = Integer.parseInt(txtEdad.getText().toString());


                SharedPreferences.Editor editor= sharedPreferencesPersona.edit();
                editor.putString(Constantes.NOMBRE, nombre);
                editor.putInt(Constantes.EDAD , edad);
                //commit (sincrono):para la aplicación hasta que la escritura se termine /apply (asincrono)
                editor.apply();
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferencesPersona.edit();
                editor.clear();
                editor.apply();
            }
        });

        btnBorrarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= sharedPreferencesPersona.edit();
                editor.remove(Constantes.NOMBRE);
                editor.apply();
            }
        });

        btnBorrarEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= sharedPreferencesPersona.edit();
                editor.remove(Constantes.EDAD);
                editor.apply();
            }
        });


    }

    private void cargaDatos() {
        String nombre = sharedPreferencesPersona.getString(Constantes.NOMBRE, ""); //si el tag no existe, el nombre será "" , es para no generar nullPoints
        if(!nombre.isEmpty()){
            txtNombre.setText(nombre);
        }
        int edad = sharedPreferencesPersona.getInt(Constantes.EDAD, -1); //-1 será el valor si no existe
        if(edad!=-1){
            txtEdad.setText(String.valueOf(edad));
        }
    }
}