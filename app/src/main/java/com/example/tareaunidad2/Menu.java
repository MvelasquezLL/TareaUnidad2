package com.example.tareaunidad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void goVistaLista(View view){
        Intent vista = new Intent(this,crudVeterinario.class);
        startActivity(vista);
    }
    public void goVistaMapa(View view){
        Intent vista = new Intent(this,Maps.class);
        startActivity(vista);
    }
    public void goVistaLogin(View view){
        Intent vista = new Intent(this,MainActivity.class);
        startActivity(vista);
    }
}