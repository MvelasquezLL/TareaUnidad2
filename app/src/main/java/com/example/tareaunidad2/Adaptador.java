package com.example.tareaunidad2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<mascota> lista;
    daoMascota dao;
    mascota m;
    Activity a;
    int id=0;
    public Adaptador(Activity a, ArrayList<mascota> lista, daoMascota dao ){
        this.lista = lista;
        this.a= a;
        this.dao= dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public Object getItem(int i) {
        m=lista.get(i);
        return null;
    }
    @Override
    public long getItemId(int i) {
        m=lista.get(i);
        return m.getId();
    }
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }
        m=lista.get(posicion);
        TextView nombre=v.findViewById(R.id.nombre);
        TextView raza=v.findViewById(R.id.raza);
        TextView tamano=v.findViewById(R.id.tamano);
        TextView peso=v.findViewById(R.id.peso);
        TextView enfermedad=v.findViewById(R.id.enfermedad);
        Button editar= v.findViewById(R.id.btn_editar);
        Button eliminar= v.findViewById(R.id.btn_eliminar);
        nombre.setText(m.getNombre());
        raza.setText(m.getRaza());
        tamano.setText(m.getTamano());
        peso.setText(m.getPeso());
        enfermedad.setText(m.getEnfermedad());
        editar.setTag(posicion);
        eliminar.setTag(posicion);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText raza = dialog.findViewById(R.id.et_raza);
                final EditText tamano = dialog.findViewById(R.id.et_tamano);
                final EditText peso = dialog.findViewById(R.id.et_peso);
                final EditText emfermedad = dialog.findViewById(R.id.et_enfermedad);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                m=lista.get(pos);
                setId(m.getId());
                nombre.setText(m.getNombre());
                raza.setText(m.getRaza());
                tamano.setText(m.getTamano());
                peso.setText(m.getPeso());
                emfermedad.setText(m.getEnfermedad());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            m = new mascota(getId(), nombre.getText().toString(),
                                    raza.getText().toString(),
                                    tamano.getText().toString(),
                                    peso.getText().toString(),
                                    emfermedad.getText().toString());
                            dao.editar(m);
                            lista=dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
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
       eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para confirmar si o no se elimine
                int pos=Integer.parseInt(view.getTag().toString());
                m=lista.get(posicion);
                setId(m.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista=dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
