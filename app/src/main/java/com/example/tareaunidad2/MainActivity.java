package com.example.tareaunidad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    daoUsuario daoUsuario;

    ProgressBar pb1;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoUsuario = new daoUsuario(MainActivity.this);
        Button btnIngresar = findViewById(R.id.btn_ingresar);
        Button btnRegistrar = findViewById(R.id.btn_ragistrar);
        EditText correo = findViewById(R.id.txtCorreo);
        EditText pass = findViewById(R.id.txtPass);
        pb1 = findViewById(R.id.progressBar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoS = correo.getText().toString();
                Usuario usu = daoUsuario.getUsuarioByNombre(correoS);
                String passR = pass.getText().toString();
                String contra = null;
                if(usu!=null){
                    contra = usu.getPassword();
                }
                try {
                    if(usu == null){
                        Toast.makeText(getApplication(),"Usuario no existe",Toast.LENGTH_SHORT).show();
                    }else if (!contra.equals(passR)){
                        Toast.makeText(getApplication(),"Usuario u contraseña incorrectos",Toast.LENGTH_SHORT).show();
                    }else{
                        pb1.setVisibility(View.VISIBLE);
                        Timer timer = new Timer();
                        TimerTask timerTask = new TimerTask() {

                            @Override
                            public void run() {
                                counter++;
                                pb1.setProgress(counter);
                                if(counter==100){
                                    timer.cancel();
                                    Intent vista = new Intent(MainActivity.this, Menu.class);
                                    startActivity(vista);
                                }
                            }
                        };
                        timer.schedule(timerTask, 50,50);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vista = new Intent(MainActivity.this, Registro.class);
                startActivity(vista);
            }
        });
    }
}