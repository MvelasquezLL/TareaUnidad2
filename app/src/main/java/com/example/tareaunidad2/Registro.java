package com.example.tareaunidad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    daoUsuario daoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        daoUsuario = new daoUsuario(Registro.this);
        Button btnRegistrar2 = findViewById(R.id.btn_registrar2);
        Button btnVolver = findViewById(R.id.btn_volver);
        EditText correo = findViewById(R.id.txtCorreoReg);
        EditText pass1 = findViewById(R.id.txtPassReg);
        EditText pass2 = findViewById(R.id.txtPassReg2);

        btnRegistrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoR = correo.getText().toString();
                String pass1R = pass1.getText().toString();
                String pass2R = pass2.getText().toString();
                Usuario usu = daoUsuario.getUsuarioByNombre(correoR);
                if(usu != null){
                    Toast.makeText(getApplication(),"Nombre de usurio ya existente",Toast.LENGTH_SHORT).show();
                }else if(pass1R.equals(pass2R)){
                    usu = new Usuario(correoR,pass1R);
                    daoUsuario.insertar(usu);
                    Toast.makeText(getApplication(),"Cuenta creada correctamente",Toast.LENGTH_SHORT).show();
                    Intent vista = new Intent(Registro.this, MainActivity.class);
                    startActivity(vista);
                }else{
                    Toast.makeText(getApplication(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vista = new Intent(Registro.this, MainActivity.class);
                startActivity(vista);
            }
        });
    }
}