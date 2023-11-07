package com.example.tareaunidad2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class crudVeterinario extends AppCompatActivity {

    daoMascota dao;
    Adaptador adapter;
    ArrayList<mascota> lista;
    mascota m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_veterinario);

        dao= new daoMascota(crudVeterinario.this);
        lista=dao.verTodo();
        adapter=new Adaptador(this, lista, dao);
        ListView list= findViewById(R.id.lista);
        Button insertar = findViewById(R.id.btn_insertar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(crudVeterinario.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText raza = dialog.findViewById(R.id.et_raza);
                final EditText tamano = dialog.findViewById(R.id.et_tamano);
                final EditText peso = dialog.findViewById(R.id.et_peso);
                final EditText enfermedad = dialog.findViewById(R.id.et_enfermedad);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                guardar.setText("Agregar");
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            m = new mascota(nombre.getText().toString(),
                                    raza.getText().toString(),
                                    tamano.getText().toString(),
                                    peso.getText().toString(),
                                    enfermedad.getText().toString());
                            dao.insertar(m);
                            lista=dao.verTodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    public void goVistaMenu(View view){
        Intent vista = new Intent(this,Menu.class);
        startActivity(vista);
    }
}